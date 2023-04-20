/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.controller;

import in.emp.common.AjaxControlServlet;
import in.emp.common.ApplicationConstants;
import in.emp.common.SendMail;
import in.emp.legal.bean.FeeTypeDtlsBean;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.sms.SmsController;
import in.emp.sms.SmsEmployee;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.HOBean;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.PoLineStatusBean;
import in.emp.vendor.bean.SmsDTO;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.bean.VendorStatuBean;
import in.emp.vendor.manager.VendorManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import java.util.*;
import javaldapapp.AssignOfficeDTO;
import in.emp.vendor.bean.AssignOfficeBean;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import in.emp.legal.bean.HOSectionMatrixBean;
import in.emp.legal.bean.LegalCommunicationBean;
import in.emp.sms.bean.TemplateIdBean;
/**
 *
 * @author Pooja Jadhav
 */
public class VendorFormController {

    private static Connection conn = null;
    private static Logger logger = Logger.getLogger(VendorFormController.class);

    public static JSONObject getSaveFormStatus(VendorPrezData vendorPrezDataObj, HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        HttpSession vendorSession = request.getSession();
        VendorDelegate vendorMgrObj = new VendorManager();
        String Appl_id;
        try {
            if (ApplicationUtils.getRequestParameter(request, "module_type").equals(ApplicationConstants.PROJECT_SYSTEM)) {
                vendorPrezDataObj = vendorMgrObj.VendorProjApplFormTxnHelper(vendorPrezDataObj);
            } else {
                vendorPrezDataObj = vendorMgrObj.VendorApplFormTxnHelper(vendorPrezDataObj);

            }

            if (vendorPrezDataObj.getVendorInputBean().getApplId() != "") {
                obj.put("Message1", "Form Saved Successfully with ID " + vendorPrezDataObj.getVendorInputBean().getApplId());
                try {
                    Appl_id = vendorPrezDataObj.getVendorInputBean().getApplId();
                    if (Appl_id != null) {
                        savePOLineDetails(request, Appl_id);//save the po line items
                        if (vendorPrezDataObj.getVendorInputBean().getINVOICE_TYPE() != null
                                && vendorPrezDataObj.getVendorInputBean().getINVOICE_TYPE().equalsIgnoreCase("Retention Claim Charges")) {
                            saveClaimedRetentionDetailsFormStatus(vendorPrezDataObj, request);
                        }
                    }
                } catch (Exception e) {

                }
            } else {
                obj.put("Message1", "Invoice number already created..Please Check!!! ");
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return obj;
    }

    public static JSONObject getRejectedFormStatus(VendorPrezData vendorPrezDataObj, HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        String saveFlag = vendorPrezDataObj.getVendorInputBean().getSaveFlag();
        VendorDelegate vendorMgrObj = new VendorManager();
        try {
            if (ApplicationUtils.getRequestParameter(request, "module_type").equals(ApplicationConstants.PROJECT_SYSTEM)) {
                vendorPrezDataObj = vendorMgrObj.VendorProjApplFormTxnHelper(vendorPrezDataObj);
            } else {
                vendorPrezDataObj = vendorMgrObj.VendorApplFormTxnHelper(vendorPrezDataObj);

            }
            obj.put("Message1", "Form Rejected Successfully");
        } catch (Exception e) {

        }
         vendorRejectedSmsSendProcess(request, saveFlag); 
        return obj;
    }

    public static JSONObject getForwardedFormStatus(POBean POBeanObj, VendorPrezData vendorPrezDataObj, VendorInputBean vendorInputBeanObj, HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        String saveFlag = vendorPrezDataObj.getVendorInputBean().getSaveFlag();
        VendorDelegate vendorMgrObj = new VendorManager();
        HOBean hobeanObj = new HOBean();
        SmsEmployee smsemp = new SmsEmployee();//ldap class to retrieve assignofficedto
        AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
        AssignOfficeBean assignOfficebeanObj = new AssignOfficeBean();
        SmsDTO objSmsEmp = new SmsDTO();
        try {
            POBeanObj.setFormStatus("Forward");//to differentiate between submit at location and forward at location
            try {
                if (!ApplicationUtils.isBlank(request.getParameter("Purchasing_group"))) {
                    if (request.getParameter("Purchasing_group").equals("Z00")) {
                        POBeanObj = vendorMgrObj.getPlantCodeDetails(POBeanObj);
                        //   vendorInputBeanObj.setOffice_Code(POBeanObj.getOfficeode());
                        //   vendorInputBeanObj.setParent_Office_Code(POBeanObj.getParentOfficeode());
                        assignOfficeDTO = smsemp.Ldap(POBeanObj.getOfficeode());//office incharge and technical person 
                        vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                        vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                        vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                        vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                        objSmsEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                    } else {

                        POBeanObj.setPlant(ApplicationUtils.getRequestParameter(request, "ForwardToPlant"));//to retrieve forward to plant office code details 
                        POBeanObj = vendorMgrObj.getOfficeCodeDetails(POBeanObj);//get the details of plant office code and parent office code

                        hobeanObj.setPURCHASING_GROUP(ApplicationUtils.getRequestParameter(request, "Purchasing_group"));
                        hobeanObj = vendorMgrObj.getHOSmsDetails(hobeanObj);
                        if (!ApplicationUtils.isBlank(hobeanObj.getPLANT())) {
                            if (hobeanObj.getPLANT().equals(ApplicationConstants.HO_OFFICE_PLANT)) {

                                assignOfficebeanObj = smsemp.Ldapcpf(hobeanObj.getEMP_CPF());
                                vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                                vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                                vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                                vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                                objSmsEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                            } else {
                                assignOfficeDTO = smsemp.Ldap(POBeanObj.getOfficeode());//office incharge and technical person 
                                vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                                vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                                vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                                vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                                objSmsEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());

                            }
                        }
                    }
                }
            } catch (Exception e) {

            }
            vendorPrezDataObj = vendorMgrObj.VendorApplFormTxnHelper(vendorPrezDataObj);

            obj.put("Message1", "Form Forwarded Successfully");
        } catch (Exception e) {

        }
        try {
            //  empSubmitSmsSendProcess(request, objSmsEmp);//invoking employee sms process
            //UNCOMMENT FOR CLOUD

        } catch (Exception e) {
            //  System.out.println("in if empSubmitSmsSendProcess catch");
            e.printStackTrace();
        }
        return obj;
    }

    public static JSONObject getVerifiedFormStatus(VendorPrezData vendorPrezDataObj, HttpServletRequest request) throws Exception {

        JSONObject obj = new JSONObject();
        VendorDelegate vendorMgrObj = new VendorManager();
        String saveFlag = vendorPrezDataObj.getVendorInputBean().getSaveFlag();
        if (ApplicationUtils.getRequestParameter(request, "module_type").equals(ApplicationConstants.PROJECT_SYSTEM)) {
            vendorPrezDataObj = vendorMgrObj.VendorProjApplFormTxnHelper(vendorPrezDataObj);
        } else {
            vendorPrezDataObj = vendorMgrObj.VendorApplFormTxnHelper(vendorPrezDataObj);
        }

        obj.put("Message1", "Form Verified Successfully");
         vendorVerifiedSmsSendProcess(request, saveFlag);
        return obj;
    }

    public static JSONObject getSubmittedFormStatus(POBean POBeanObj, VendorInputBean vendorInputBeanObj, VendorInputBean vendorInputBeanObj1, VendorPrezData vendorPrezDataObj, HttpServletRequest request) throws Exception {
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession vendorSession = request.getSession();

        JSONObject obj = new JSONObject();
        HOBean hobeanObj = new HOBean();
        AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
        AssignOfficeBean assignOfficebeanObj = new AssignOfficeBean();
        SmsEmployee smsemp = new SmsEmployee();//ldap class to retrieve assignofficedto
        SmsDTO objSmsEmp = new SmsDTO();
        SmsDTO objSmsPoCreator = new SmsDTO();
        String Appl_id;
        try {
            //get office code and parent office code based on submitted at plant in case of pm mm and 
            POBeanObj.setFormStatus("Submit");//to differentiate between submit at location and forward at location

            try {
                String Purchasing_group = request.getParameter("Purchasing_group");
                // if (!ApplicationUtils.isBlank(Purchasing_group)){
                if (!Purchasing_group.equals("null")) {
                    if (request.getParameter("Purchasing_group").equals("Z00")) {
                        POBeanObj = vendorMgrObj.getPlantCodeDetails(POBeanObj);
                        vendorInputBeanObj.setOffice_Code(POBeanObj.getOfficeode());
                        vendorInputBeanObj.setParent_Office_Code(POBeanObj.getParentOfficeode());
                        assignOfficeDTO = smsemp.Ldap(POBeanObj.getOfficeode());//office incharge and technical person 
                        vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                        vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                        vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                        vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                        objSmsEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                    } else {
                        hobeanObj.setPURCHASING_GROUP(ApplicationUtils.getRequestParameter(request, "Purchasing_group"));
                        hobeanObj = vendorMgrObj.getHOSmsDetails(hobeanObj);//get the details of employee from emp_escalation_matrix
                        if (!ApplicationUtils.isBlank(hobeanObj.getPLANT())) {
                            if (hobeanObj.getPLANT().equals(ApplicationConstants.HO_OFFICE_PLANT)) {
                                assignOfficebeanObj = smsemp.Ldapcpf(hobeanObj.getEMP_CPF());
                                vendorInputBeanObj.setOffice_Code(assignOfficebeanObj.getOfficeCode());
                                vendorInputBeanObj.setParent_Office_Code(assignOfficebeanObj.getParentOfficeCode());
                                vendorInputBeanObj.setempCpf(assignOfficebeanObj.getOfficerCpfNo());
                                vendorInputBeanObj.setempName(assignOfficebeanObj.getOfficerName());
                                vendorInputBeanObj.setDesignation(assignOfficebeanObj.getOfficerDesignation());
                                vendorInputBeanObj.setContactNumber(assignOfficebeanObj.getOfficerContactNo());
                                objSmsEmp.setMobileNumber(assignOfficebeanObj.getOfficerContactNo());

                            } else {
                                POBeanObj.setPlant(hobeanObj.getPLANT());
                                POBeanObj = vendorMgrObj.getOfficeCodeDetails(POBeanObj);//get the details of plant office code and parent office code
                                vendorInputBeanObj.setOffice_Code(POBeanObj.getOfficeode());
                                vendorInputBeanObj.setParent_Office_Code(POBeanObj.getParentOfficeode());
                                assignOfficeDTO = smsemp.Ldap(POBeanObj.getOfficeode());//office incharge and technical person 
                                vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                                vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                                vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                                vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                                objSmsEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());

                            }
                        }
                    }
                } else {
                    assignOfficebeanObj = smsemp.LdapDb_Code(vendorInputBeanObj.getSubmitAtPlant().substring(1));//To get billing db code
                    vendorInputBeanObj.setOffice_Code(assignOfficebeanObj.getOfficeCode());
                    vendorInputBeanObj.setParent_Office_Code(assignOfficebeanObj.getParentOfficeCode());
                    vendorInputBeanObj.setempCpf(assignOfficebeanObj.getOfficerCpfNo());
                    vendorInputBeanObj.setempName(assignOfficebeanObj.getOfficerName());
                    vendorInputBeanObj.setDesignation(assignOfficebeanObj.getOfficerDesignation());
                    vendorInputBeanObj.setContactNumber(assignOfficebeanObj.getOfficerContactNo());
                    objSmsEmp.setMobileNumber(assignOfficebeanObj.getOfficerContactNo());
                }
//Process to send sms of invoice submission to po creator also
                if (!ApplicationUtils.getRequestParameter(request, "module_type").equals(ApplicationConstants.PROJECT_SYSTEM)) {
                    if (request.getParameter("txtOnloadPurchasing_group").equals("Z00")) {
                        POBeanObj = vendorMgrObj.getPlantCodeDetails(POBeanObj);
                        assignOfficeDTO = smsemp.Ldap(POBeanObj.getOfficeode());
                        objSmsPoCreator.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                    } else {
                        hobeanObj.setPURCHASING_GROUP(ApplicationUtils.getRequestParameter(request, "txtOnloadPurchasing_group"));
                        hobeanObj = vendorMgrObj.getHOSmsDetails(hobeanObj);//get the details of employee from emp_escalation_matrix
                        if (!ApplicationUtils.isBlank(hobeanObj.getPLANT())) {
                            if (hobeanObj.getPLANT().equals(ApplicationConstants.HO_OFFICE_PLANT)) {
                                assignOfficebeanObj = smsemp.Ldapcpf(hobeanObj.getEMP_CPF());
                                objSmsPoCreator.setMobileNumber(assignOfficebeanObj.getOfficerContactNo());

                            } else {
                                POBeanObj.setPlant(hobeanObj.getPLANT());
                                POBeanObj = vendorMgrObj.getOfficeCodeDetails(POBeanObj);
                                assignOfficeDTO = smsemp.Ldap(POBeanObj.getOfficeode());//office incharge and technical person 
                                objSmsPoCreator.setMobileNumber(assignOfficeDTO.getOfficerContactNo());

                            }
                        }
                    }
                }

            } catch (Exception e) {

            }

            System.out.println(assignOfficeDTO);
            if (ApplicationUtils.getRequestParameter(request, "module_type").equals(ApplicationConstants.PROJECT_SYSTEM)) {

                vendorPrezDataObj = vendorMgrObj.VendorProjApplFormTxnHelper(vendorPrezDataObj);
            } else {
                String POLineselected = ApplicationUtils.getRequestParameter(request, "po_line");
                if (!(ApplicationUtils.isBlank(POLineselected))) {
                    Appl_id = vendorPrezDataObj.getVendorInputBean().getApplId();
                    if (Appl_id != null) {
                        savePOLineDetails(request, Appl_id);//save the po line items
                    }
                }

                vendorPrezDataObj = vendorMgrObj.VendorApplFormTxnHelper(vendorPrezDataObj);
            }
            obj.put("Message1", "Form Submitted Successfully with Application ID " + vendorPrezDataObj.getVendorInputBean().getApplId());

            try {
                vendorSubmitSmsSendProcess(request, vendorInputBeanObj);
//invoking vendor sms send process
            } catch (Exception e) {
                // System.out.println("in if vendorSubmitSmsSendProcess catch");
                e.printStackTrace();
            }
            try {
                  empSubmitSmsSendProcess(request, objSmsEmp);
                if (!ApplicationUtils.getRequestParameter(request, "module_type").equals(ApplicationConstants.PROJECT_SYSTEM)) {
                      poCreatorSubmitSmsSendProcess(request, objSmsEmp);
                }
//invoking employee sms process

            } catch (Exception e) {
                //  System.out.println("in if empSubmitSmsSendProcess catch");
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: sendingSubmissionSMS() :: Exception :: " + e);
            e.printStackTrace();
        }
        return obj;
    }

    public static void vendorSubmitSmsSendProcess(HttpServletRequest request, VendorInputBean vendorInputBeanObj) throws Exception {
        VendorDelegate vendorMgrObj = new VendorManager();
        List<String> vendorlstcredential = new ArrayList<String>();
        VendorStatuBean vendorStatusBeanObj = new VendorStatuBean();
        VendorBean vendorBeanObj1 = new VendorBean();
        HttpSession vendorSession = request.getSession();
        SmsDTO objSmsVendor = new SmsDTO();
        VendorInputBean vendorInputBeanObj1 = new VendorInputBean();
        List<String> lstParams = new ArrayList<String>();
        TemplateIdBean templateBeanObj =new TemplateIdBean();
        Date sysdate = new Date();
        SmsController sms = new SmsController();
        try {
            //vendor sms send process

            DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
    
            //vendorlstcredential.add("607971");
            //vendorlstcredential.add("mse12");
            //vendorlstcredential.add("https://japi.instaalerts.zone/failsafe/HttpTemplateLink");
            vendorlstcredential.add(ApplicationConstants.OTHER_URL);
            vendorInputBeanObj1.setSelectedModuleType(ApplicationUtils.getRequestParameter(request, "module_type"));
            vendorInputBeanObj1.setApplId(ApplicationUtils.getRequestParameter(request, "txtApplId"));
            vendorInputBeanObj1 = vendorMgrObj.getInvoicedetails(vendorInputBeanObj1);//get details of invoice for sending sms from xxmis_erp_vendor_input_list

            try {
                vendorBeanObj1.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                vendorBeanObj1.setPassword("");// to get the details only on user id and not on password
                vendorBeanObj1 = vendorMgrObj.getContactNumber(vendorBeanObj1);// get the contact details of vendor
            } catch (Exception e) {

            }
            vendorStatusBeanObj.setEMAIL(vendorBeanObj1.getMailId());
            vendorStatusBeanObj.setPHN_LL(vendorBeanObj1.getVendorContactNumber());
            vendorStatusBeanObj.setAPPL_ID(ApplicationUtils.getRequestParameter(request, "txtApplId"));
            vendorStatusBeanObj.setSave_Flag(vendorInputBeanObj.getSaveFlag());
            vendorStatusBeanObj.setVENDOR_INVOICE_NUMBER(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));
            if (ApplicationUtils.getRequestParameter(request, "module_type").equals(ApplicationConstants.PROJECT_SYSTEM)) {

                vendorStatusBeanObj.setPO_NUMBER(ApplicationUtils.getRequestParameter(request, "txtprojId"));
            } else {
                vendorStatusBeanObj.setPO_NUMBER(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
            }
            vendorStatusBeanObj.setVENDOR_CODE((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            //vendorStatusBeanObj.setINVOICE_SUBMIT_DATE((ApplicationUtils.stringToDate((String) request.getParameter("txtInvSubmitDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT)));
//send sms to vendor

            //vendor sms process
            lstParams.add(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));//populating lstparams for sending sms to vendor 
            //  lstParams.add(df3.format(vendorInputBeanObj1.getVendorUpdatedDate()));
            lstParams.add(ApplicationConstants.VITS_URL);
            //user id password url link
            objSmsVendor.setLstParams(lstParams);
            objSmsVendor.setMobileNumber(vendorBeanObj1.getVendorContactNumber());
//DIABLED FOR SMS 
            if (objSmsVendor.getMobileNumber() != null) {
                templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID2);
                 templateBeanObj=vendorMgrObj.getTemplateDetails(templateBeanObj);
                //   sms.sendSMS(objSmsVendor, "476830", vendorlstcredential); 
                sms.sendSMS(objSmsVendor, templateBeanObj.getTemplate_Id(), vendorlstcredential);
                vendorStatusBeanObj.setSUBMITTED_SMS_FLAG("Y");
                vendorStatusBeanObj.setSUBMITTED_SMS_DATE(sysdate);
            } else {
                vendorStatusBeanObj.setSUBMITTED_SMS_FLAG(" ");
                vendorStatusBeanObj.setSUBMITTED_SMS_DATE(null);
            }

        } catch (Exception e) {
            vendorStatusBeanObj.setSUBMITTED_SMS_FLAG(" ");
            vendorStatusBeanObj.setSUBMITTED_SMS_DATE(null);
        }
        String invoiceNumber = ApplicationUtils.getRequestParameter(request, "txtInvoiceNum");
        String Subject = "Invoice Submitted Succesfully at Vendor Invoice Tracking System";
        String MailMessage = "  Invoice no. " + invoiceNumber + " is submitted on " + vendorInputBeanObj1.getVendorUpdatedDate() + ". It will be processed within 3 working days. To track status , please visit https://vits.mahadiscom.in/VendorBillTracking/erp.";
//MAIL PROCESS AND SMS PROCESS IS DISABLED FOR TEST SERVER
        if (vendorBeanObj1.getMailId() != null) {
            int success = SendMail.sendmail(vendorBeanObj1.getMailId(), Subject, MailMessage);//testing mail sent or not
            if (success == 1) {
                vendorStatusBeanObj.setSUBMITTED_MAIL_FLAG("Y");
                vendorStatusBeanObj.setSUBMITTED_MAIL_DATE(sysdate);
            } else {
                vendorStatusBeanObj.setSUBMITTED_MAIL_FLAG(" ");
                vendorStatusBeanObj.setSUBMITTED_MAIL_DATE(null);
            }
        } else {
            vendorStatusBeanObj.setSUBMITTED_MAIL_FLAG(" ");
            vendorStatusBeanObj.setSUBMITTED_MAIL_DATE(null);
        }
        vendorMgrObj.getVendorSmsStatus(vendorStatusBeanObj);//insert info regarding vendor sms details in table sms_sent_tracker.
    }

    public static void empSubmitSmsSendProcess(HttpServletRequest request, SmsDTO objSmsEmp) {
        VendorDelegate vendorMgrObj = new VendorManager();
        List<String> lstParam = new ArrayList<String>();
        List<String> lstcredential = new ArrayList<String>();
        SmsController sms = new SmsController();
        HttpSession vendorSession = request.getSession();
        VendorInputBean vendorInputBeanObj1 = new VendorInputBean();
         TemplateIdBean templateBeanObj =new TemplateIdBean();
        try {   //employee sms send process
            vendorInputBeanObj1.setSelectedModuleType(ApplicationUtils.getRequestParameter(request, "module_type"));
            vendorInputBeanObj1.setApplId(ApplicationUtils.getRequestParameter(request, "txtApplId"));
            vendorInputBeanObj1 = vendorMgrObj.getInvoicedetails(vendorInputBeanObj1);//get details of invoice for sending sms from xxmis_erp_vendor_input_list

            lstParam.add(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));//setting params for sending sms to employee

            lstParam.add(vendorInputBeanObj1.getVendorNumber());

            lstParam.add(vendorInputBeanObj1.getVendorName());
            DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
            lstParam.add(df3.format(vendorInputBeanObj1.getVendorUpdatedDate()));
            //user id password url link
           // lstcredential.add("607971");
           // lstcredential.add("mse12");
            //lstcredential.add("https://japi.instaalerts.zone/failsafe/HttpTemplateLink");
            lstcredential.add(ApplicationConstants.OTHER_URL);
            objSmsEmp.setLstParams(lstParam);
            //DIABLED FOR SMS 
            if (objSmsEmp.getMobileNumber() != null) {
                 templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID3);
                 templateBeanObj=vendorMgrObj.getTemplateDetails(templateBeanObj);

                //   sms.sendSMS(objSmsEmp, "476809",lstcredential);
                 sms.sendSMS(objSmsEmp,templateBeanObj.getTemplate_Id(),lstcredential);

                try {
                    StringBuilder sql = new StringBuilder();
                    PreparedStatement psq = null;
                    conn = ApplicationUtils.getConnection();
                    if (ApplicationUtils.getRequestParameter(request, "module_type").equals(ApplicationConstants.PROJECT_SYSTEM)) {
                        sql.append(" UPDATE XXMIS_ERP_PS_VENDOR_DTL set INV_SUBMIT_EMPSMS_SENT_FLAG = 'Y',INV_SUBMIT_SMSEMP_DATE = SYSTIMESTAMP WHERE APPL_ID = ? ");
                    } else {
                        sql.append(" UPDATE XXMIS_ERP_VENDOR_DTL set INV_SUBMIT_EMPSMS_SENT_FLAG = 'Y',INV_SUBMIT_SMSEMP_DATE = SYSTIMESTAMP WHERE APPL_ID = ? ");

                    }
                    psq = conn.prepareStatement(sql.toString());

                    psq.setString(1, ApplicationUtils.getRequestParameter(request, "txtApplId"));
                    psq.executeUpdate();
                    conn.commit();
//DISABLED FOR SMS 
                    if (psq != null) {
                        psq.close();
                    }
                } catch (SQLException e2) {
                    //e2.printStackTrace();
                }
            }
        } catch (Exception e) {
////e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception ignored) {
                }
            }
        }

    }

    
    
    public static void notifyLegalEmpInvSubmit(HttpServletRequest request, SmsDTO objSmsEmp, LegalInvoiceInputBean legalInvoiceInputBean, String mailTo) {
        VendorDelegate vendorMgrObj = new VendorManager();
        List<String> lstParam = new ArrayList<String>();
        List<String> lstcredential = new ArrayList<String>();
        SmsController sms = new SmsController();
        TemplateIdBean templateBeanObj =new TemplateIdBean();
         Date sysdate = new Date();
        try {   //employee sms send process
            

            lstParam.add(legalInvoiceInputBean.getInvoiceNumber());//setting params for sending sms to employee

            lstParam.add(legalInvoiceInputBean.getVendorNumber());

            lstParam.add(legalInvoiceInputBean.getVendorName());
            DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
            lstParam.add(df3.format(legalInvoiceInputBean.getInvSubmitDate()));
            //user id password url link
           // lstcredential.add("607971");
           // lstcredential.add("mse12");
           // lstcredential.add("https://japi.instaalerts.zone/failsafe/HttpTemplateLink");
           lstcredential.add(ApplicationConstants.OTHER_URL);
            objSmsEmp.setLstParams(lstParam);
            //DIABLED FOR SMS 
           
            
            if (objSmsEmp.getMobileNumber() != null)
            {
                   LegalCommunicationBean legalCommunicationSMSBean = new LegalCommunicationBean();
                if (mailTo.equals("EMP")){
                     templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID3);
                     templateBeanObj=vendorMgrObj.getTemplateDetails(templateBeanObj);
                  //sms.sendSMS(objSmsEmp, "476809",lstcredential);
                   sms.sendSMS(objSmsEmp, templateBeanObj.getTemplate_Id(),lstcredential);
                  legalCommunicationSMSBean.setRECIPIENT_TYPE("EMP");
                  legalCommunicationSMSBean.setSUBJECT("476809");
                }
                else if (mailTo.equals("VENDOR"))
                     templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID2);
                     templateBeanObj=vendorMgrObj.getTemplateDetails(templateBeanObj);
                { // sms.sendSMS(objSmsEmp, "476830", lstcredential);
                sms.sendSMS(objSmsEmp,templateBeanObj.getTemplate_Id(),lstcredential);
                  legalCommunicationSMSBean.setRECIPIENT_TYPE("VENDOR");
                  legalCommunicationSMSBean.setSUBJECT("476830");
                }
                
                    legalCommunicationSMSBean.setRECIPIENTS_INFO(objSmsEmp.getMobileNumber());
                    legalCommunicationSMSBean.setCOMMUNICATION_TYPE("SMS");
                    legalCommunicationSMSBean.setCREATED(sysdate);
                
                    vendorMgrObj.updateLegalCommunicationLog(legalCommunicationSMSBean);
                      
            }
            
            // Send mail to employee
                    String invoiceNumber = ApplicationUtils.getRequestParameter(request, "txtInvoiceNum");
        String Subject = "Legal Invoice Submitted Succesfully at Vendor Invoice Tracking System";
         LegalCommunicationBean legalCommunicationBean = new LegalCommunicationBean();
           
        String MailMessage = "  Invoice no. " + invoiceNumber + " is submitted on " + legalInvoiceInputBean.getInvSubmitDate() + ". It will be processed within 3 working days. To track status , please visit https://vits.mahadiscom.in/VendorBillTracking/erp.";
//MAIL PROCESS AND SMS PROCESS IS DISABLED FOR TEST SERVER
        if (objSmsEmp.getEmailId() != null) {
            int success = SendMail.sendmail(objSmsEmp.getEmailId(), Subject, MailMessage);//testing mail sent or not
            if (success == 1) {
                legalCommunicationBean.setRECIPIENTS_INFO(objSmsEmp.getEmailId());
                
                 if (mailTo.equals("EMP")){
                       legalCommunicationBean.setRECIPIENT_TYPE("EMP");
                     
                 }
                 else if (mailTo.equals("VENDOR")){
                       legalCommunicationBean.setRECIPIENT_TYPE("VENDOR");
                     
                 }
                
                  legalCommunicationBean.setSUBJECT(Subject);
                  legalCommunicationBean.setCOMMUNICATION_TYPE("Email");
                  legalCommunicationBean.setCREATED(sysdate);
            } else {
                 legalCommunicationBean.setRECIPIENTS_INFO(objSmsEmp.getEmailId());
                 if (mailTo.equals("EMP")){
                       legalCommunicationBean.setRECIPIENT_TYPE("EMP");
                     
                 }
                 else if (mailTo.equals("VENDOR")){
                       legalCommunicationBean.setRECIPIENT_TYPE("VENDOR");
                     
                 }
                  legalCommunicationBean.setSUBJECT(Subject);
                    legalCommunicationBean.setCOMMUNICATION_TYPE("Email");
                    legalCommunicationBean.setCREATED(sysdate);
                     legalCommunicationBean.setERROR("Email sending Failed");
            }
            
             vendorMgrObj.updateLegalCommunicationLog(legalCommunicationBean);//insert info regarding vendor sms details in table sms_sent_tracker.
        } else {
          
        }
       
            
            
            
        } catch (Exception e) {
////e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception ignored) {
                }
            }
        }

    }
    
    public static void poCreatorSubmitSmsSendProcess(HttpServletRequest request, SmsDTO objSmsEmp) {
        VendorDelegate vendorMgrObj = new VendorManager();
        List<String> lstParam = new ArrayList<String>();
        List<String> lstcredential = new ArrayList<String>();
        SmsController sms = new SmsController();
        HttpSession vendorSession = request.getSession();
        VendorInputBean vendorInputBeanObj1 = new VendorInputBean();
         TemplateIdBean templateBeanObj =new TemplateIdBean();
        try {   //employee sms send process
            vendorInputBeanObj1.setSelectedModuleType(ApplicationUtils.getRequestParameter(request, "module_type"));
            vendorInputBeanObj1.setApplId(ApplicationUtils.getRequestParameter(request, "txtApplId"));
            vendorInputBeanObj1 = vendorMgrObj.getInvoicedetails(vendorInputBeanObj1);//get details of invoice for sending sms from xxmis_erp_vendor_input_list

            lstParam.add(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));//setting params for sending sms to employee

            lstParam.add(vendorInputBeanObj1.getVendorNumber());

            lstParam.add(vendorInputBeanObj1.getVendorName());
            DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
            lstParam.add(df3.format(vendorInputBeanObj1.getVendorUpdatedDate()));
            //user id password url link
            //lstcredential.add("607971");
            //lstcredential.add("mse12");
            //lstcredential.add("https://japi.instaalerts.zone/failsafe/HttpTemplateLink");
            lstcredential.add(ApplicationConstants.OTHER_URL);
            objSmsEmp.setLstParams(lstParam);
            //DIABLED FOR SMS 
            if (objSmsEmp.getMobileNumber() != null) {
                templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID3);
                templateBeanObj=vendorMgrObj.getTemplateDetails(templateBeanObj);
                //  sms.sendSMS(objSmsEmp, "476809",lstcredential);
                    sms.sendSMS(objSmsEmp,templateBeanObj.getTemplate_Id(),lstcredential);
                try {
                    StringBuilder sql = new StringBuilder();
                    PreparedStatement psq = null;
                    conn = ApplicationUtils.getConnection();
                    if (ApplicationUtils.getRequestParameter(request, "module_type").equals(ApplicationConstants.PROJECT_SYSTEM)) {
                        sql.append(" UPDATE XXMIS_ERP_PS_VENDOR_DTL set INV_SUBMIT_EMPSMS_SENT_FLAG = 'Y',INV_SUBMIT_SMSEMP_DATE = SYSTIMESTAMP WHERE APPL_ID = ? ");
                    } else {
                        sql.append(" UPDATE XXMIS_ERP_VENDOR_DTL set INV_SUBMIT_POCRTR_SMSSENT = 'Y',INV_SUBMIT_POCRTR_SMSDATE = SYSTIMESTAMP WHERE APPL_ID = ? ");

                    }
                    psq = conn.prepareStatement(sql.toString());

                    psq.setString(1, ApplicationUtils.getRequestParameter(request, "txtApplId"));
                    psq.executeUpdate();
                    conn.commit();
//DISABLED FOR SMS 
                    if (psq != null) {
                        psq.close();
                    }
                } catch (SQLException e2) {
                    //e2.printStackTrace();
                }
            }
        } catch (Exception e) {
////e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception ignored) {
                }
            }
        }

    }

    public static void vendorRejectedSmsSendProcess(HttpServletRequest request, String saveFlag) {

        List<String> lstcredential = new ArrayList<String>();
        SmsDTO objSms = new SmsDTO();
        String VendorContactNo = "";
        String VendorEmailId = "";
        String MailMessage = null;
        List<String> lstParamsRej = new ArrayList<String>();
        Date sysdate = new Date();
        VendorDelegate vendorMgrObj = new VendorManager();
        VendorBean vendorBeanObj1 = new VendorBean();
        VendorStatuBean vendorStatusBeanObj = new VendorStatuBean();
        TemplateIdBean templateBeanObj =new TemplateIdBean();
        HttpSession vendorSession = request.getSession();
        try {

            SmsController sms = new SmsController();
            lstParamsRej.add(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));
            lstParamsRej.add((String) ApplicationUtils.getRequestParameter(request, "vendor_number"));

            lstParamsRej.add((String) ApplicationUtils.getRequestParameter(request, "vendor_name"));

            lstParamsRej.add(ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            lstParamsRej.add(ApplicationUtils.getRequestParameter(request, "txtReason"));

            lstParamsRej.add(ApplicationConstants.VITS_URL);
            objSms.setLstParams(lstParamsRej);
            //username password and url
            //lstcredential.add("607971");//username
            //lstcredential.add("mse12");//password
            //lstcredential.add("https://japi.instaalerts.zone/failsafe/HttpTemplateLink");//link for sms
            lstcredential.add(ApplicationConstants.OTHER_URL);
            try {
                vendorBeanObj1.setVendorNumber((String) ApplicationUtils.getRequestParameter(request, "vendor_number"));
                vendorBeanObj1.setPassword("");//password set to null to get the details of vendor number without specifying password.
                vendorBeanObj1 = vendorMgrObj.getContactNumber(vendorBeanObj1);
                VendorContactNo = vendorBeanObj1.getVendorContactNumber();//get contact no
                VendorEmailId = vendorBeanObj1.getMailId();//get mail id
            } catch (Exception e) {

            }
            try {
                objSms.setMobileNumber(VendorContactNo);//if contact number is null
            } catch (Exception e) {
            }
            //forming mail message
            String VendorNumber = (String) ApplicationUtils.getRequestParameter(request, "vendor_number");
            String VendorName = (String) ApplicationUtils.getRequestParameter(request, "vendor_name");
            String invoiceNumber = ApplicationUtils.getRequestParameter(request, "txtInvoiceNum");
            String RejReason = ApplicationUtils.getRequestParameter(request, "txtReason");
            String InvoicerejDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
            String Subject = "Invoice Rejected at Vendor Invoice Tracking System";
            MailMessage = "  Invoice no. " + invoiceNumber + "  submitted by Vendor " + VendorNumber + "," + VendorName + "  has been rejected on " + InvoicerejDate + " due to " + RejReason + " reason. Resubmit invoice at https://vits.mahadiscom.in/VendorBillTracking/erp";

            try {
                vendorStatusBeanObj.setVENDOR_INVOICE_NUMBER(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));
                vendorStatusBeanObj.setPO_NUMBER(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
                vendorStatusBeanObj.setVENDOR_CODE((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));

                vendorStatusBeanObj.setEMAIL(vendorBeanObj1.getMailId());
                vendorStatusBeanObj.setPHN_LL(vendorBeanObj1.getVendorContactNumber());
                vendorStatusBeanObj.setAPPL_ID(ApplicationUtils.getRequestParameter(request, "txtApplId"));
                vendorStatusBeanObj.setSave_Flag(saveFlag);
                if (objSms.getMobileNumber() != null) {//UNCOMMENT FOR CLOUD
                    templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID5);
                    templateBeanObj=vendorMgrObj.getTemplateDetails(templateBeanObj);
                //    sms.sendSMS(objSms, "476834", lstcredential);//objsms contains matter to be messaged//templateid//username passwrd link
                    sms.sendSMS(objSms, templateBeanObj.getTemplate_Id(), lstcredential);
                    vendorStatusBeanObj.setREJECTED_SMS_FLAG("Y");
                    vendorStatusBeanObj.setREJECTED_SMS_DATE(sysdate);
                } else {
                    vendorStatusBeanObj.setREJECTED_SMS_FLAG(" ");
                    vendorStatusBeanObj.setREJECTED_SMS_DATE(null);
                }

            } catch (Exception e) {
                vendorStatusBeanObj.setREJECTED_SMS_FLAG(" ");
                vendorStatusBeanObj.setREJECTED_SMS_DATE(null);

            }
            if (vendorBeanObj1.getMailId() != null) {
                int success = SendMail.sendmail(VendorEmailId, Subject, MailMessage);
                if (success == 1) {
                    vendorStatusBeanObj.setREJECTED_MAIL_FLAG("Y");
                    vendorStatusBeanObj.setREJECTED_MAIL_DATE(sysdate);
                } else {
                    vendorStatusBeanObj.setREJECTED_MAIL_FLAG(" ");
                    vendorStatusBeanObj.setREJECTED_MAIL_DATE(null);
                }
            } else {
                vendorStatusBeanObj.setREJECTED_MAIL_FLAG(" ");
                vendorStatusBeanObj.setREJECTED_MAIL_DATE(null);
            }
            vendorMgrObj.getVendorSmsStatus(vendorStatusBeanObj);//insert info regarding vendor sms details in table sms_sent_tracker.
        } catch (Exception e) {

        }

    }

    public static void vendorVerifiedSmsSendProcess(HttpServletRequest request, String saveFlag) {
        try {
            List<String> lstParamsAppr = new ArrayList<String>();
            HttpSession vendorSession = request.getSession();
            VendorBean vendorBeanObj1 = new VendorBean();
            String invoiceNumber = ApplicationUtils.getRequestParameter(request, "txtInvoiceNum");

            List<String> lstcredential = new ArrayList<String>();
            SmsController sms = new SmsController();
            SmsDTO objSms = new SmsDTO();
            String VendorContactNo = "";
            String VendorEmailId = "";
            String MailMessage = null;
            Date sysdate = new Date();
            VendorDelegate vendorMgrObj = new VendorManager();
            VendorStatuBean vendorStatusBeanObj = new VendorStatuBean();
             TemplateIdBean templateBeanObj =new TemplateIdBean();
            //matter in sms
            lstParamsAppr.add(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));
            lstParamsAppr.add((String) ApplicationUtils.getRequestParameter(request, "vendor_number"));

            lstParamsAppr.add((String) ApplicationUtils.getRequestParameter(request, "vendor_name"));
            lstParamsAppr.add(ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            lstParamsAppr.add(ApplicationConstants.VITS_URL);
            //username password and url link
            //lstcredential.add("607971");
            //lstcredential.add("mse12");
           // lstcredential.add("https://japi.instaalerts.zone/failsafe/HttpTemplateLink");
             lstcredential.add(ApplicationConstants.OTHER_URL);
            objSms.setLstParams(lstParamsAppr);
            try {
                vendorBeanObj1.setVendorNumber((String) ApplicationUtils.getRequestParameter(request, "vendor_number"));
                vendorBeanObj1.setPassword("");//retrieve mobile and mail details without password
                vendorBeanObj1 = vendorMgrObj.getContactNumber(vendorBeanObj1);
                VendorContactNo = vendorBeanObj1.getVendorContactNumber();//mob no
                VendorEmailId = vendorBeanObj1.getMailId();//mail id

            } catch (Exception e) {

            }
            try {
                objSms.setMobileNumber(VendorContactNo);//if contact number is null
            } catch (Exception e) {

            }
            String Subject = "Invoice Verified Succesfully at Vendor Invoice Tracking System";
            String VendorNumber = (String) ApplicationUtils.getRequestParameter(request, "vendor_number");
            String VendorName = (String) ApplicationUtils.getRequestParameter(request, "vendor_name");
            String InvoiceapprDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
            MailMessage = "  Invoice no. " + invoiceNumber + " submitted by Vendor " + VendorNumber + "," + VendorName + " is accepted on " + InvoiceapprDate + ". Track status at https://vits.mahadiscom.in/VendorBillTracking/erp ";

            try {
                vendorStatusBeanObj.setVENDOR_INVOICE_NUMBER(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));
                vendorStatusBeanObj.setPO_NUMBER(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
                vendorStatusBeanObj.setVENDOR_CODE((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));

                vendorStatusBeanObj.setEMAIL(vendorBeanObj1.getMailId());
                vendorStatusBeanObj.setPHN_LL(vendorBeanObj1.getVendorContactNumber());
                vendorStatusBeanObj.setAPPL_ID(ApplicationUtils.getRequestParameter(request, "txtApplId"));
                vendorStatusBeanObj.setSave_Flag(saveFlag);
                //DIABLED FOR SMS 
                if (objSms.getMobileNumber() != null) {
                     templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID6);
                     templateBeanObj=vendorMgrObj.getTemplateDetails(templateBeanObj);

                    //   sms.sendSMS(objSms, "476833", lstcredential);
                     sms.sendSMS(objSms,templateBeanObj.getTemplate_Id(), lstcredential);
                    vendorStatusBeanObj.setVERIFIED_SMS_FLAG("Y");
                    vendorStatusBeanObj.setVERIFIED_SMS_DATE(sysdate);
                } else {
                    vendorStatusBeanObj.setVERIFIED_SMS_FLAG(" ");
                    vendorStatusBeanObj.setVERIFIED_SMS_DATE(null);
                }

            } catch (Exception e) {
                vendorStatusBeanObj.setVERIFIED_SMS_FLAG(" ");
                vendorStatusBeanObj.setVERIFIED_SMS_DATE(null);
            }
            //DIABLED FOR SMS 
            if (vendorBeanObj1.getMailId() != null) {
                int success = SendMail.sendmail(VendorEmailId, Subject, MailMessage);
                if (success == 1) {
                    vendorStatusBeanObj.setVERIFIED_MAIL_FLAG("Y");
                    vendorStatusBeanObj.setVERIFIED_MAIL_DATE(sysdate);
                } else {
                    vendorStatusBeanObj.setVERIFIED_MAIL_FLAG(" ");
                    vendorStatusBeanObj.setVERIFIED_MAIL_DATE(null);
                }
            } else {
                vendorStatusBeanObj.setVERIFIED_MAIL_FLAG(" ");
                vendorStatusBeanObj.setVERIFIED_MAIL_DATE(null);
            }
            vendorMgrObj.getVendorSmsStatus(vendorStatusBeanObj);//insert info regarding vendor sms details in table sms_sent_tracker.
        } catch (Exception e) {
        }

    }

    public static void savePOLineDetails(HttpServletRequest request, String APPL_ID) throws Exception {
        JSONObject obj = new JSONObject();
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        LinkedList poLineDetails = new LinkedList();
        LinkedList selectedpoLineDetails = new LinkedList();
        HttpSession vendorSession = request.getSession();
        LinkedList list;
        VendorDelegate vendorMgrObj = new VendorManager();
        String POLineselected = ApplicationUtils.getRequestParameter(request, "po_line");
        String vendor_number = ApplicationUtils.getRequestParameter(request, "vendor_number");
        String INVOICE_NUMBER = ApplicationUtils.getRequestParameter(request, "txtInvoiceNum");
        System.out.println(POLineselected);

        //GET THE PO_LINE_ID FROM JSP PAGE 
        String[] values = POLineselected.split("[,]", POLineselected.length());
        System.out.println(Arrays.toString(values));
        //GET THE PO LINE DETAILS FROM AJAX CALL 
        if (vendorSession.getAttribute(ApplicationConstants.VENDOR_PO_LINE_SESSION_DATA) != null) {
            poLineDetails = (LinkedList) vendorSession.getAttribute(ApplicationConstants.VENDOR_PO_LINE_SESSION_DATA);
            // poLineDetails = (LinkedList) vendorPrezDataObj.getPoLineDetails();
        }
        if (poLineDetails != null) {

            for (int i = 0; i < values.length; i++) {
                for (PoLineStatusBean po : (LinkedList<PoLineStatusBean>) poLineDetails) {
                    //CHECK FOR LINE ITEMS CHECKED BY USING PO_LINE_ID IN   poLineDetails AND   POLineselected    
                    if (po.getPoLineId().equals(values[i])) {
                        po.setVendor_Number(vendor_number);
                        po.setAPPL_ID(APPL_ID);
                        po.setINVOICE_NUMBER(INVOICE_NUMBER);
                        selectedpoLineDetails.add(po);

                    }
                }

            }
            list = vendorMgrObj.getSelectedLineStatus(selectedpoLineDetails);
        }

    }

    public static JSONObject saveClaimedRetentionDetailsFormStatus(VendorPrezData vendorPrezDataObj, HttpServletRequest request) {
        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_INPUT_FORM;
        JSONObject obj = new JSONObject();
        VendorDelegate vendorMgrObj = new VendorManager();
        VendorInputBean bean = null;
        String message = "";
        VendorInputBean savedVendorBean = null;
        VendorBean vendorBean = null;
        int counter=0;
        String fullOrPartial="";
        if (!ApplicationUtils.isBlank((request.getParameter("retentionCheckBoxValue")))) {
            try {
                String retentionCheckBoxValue = ApplicationUtils.getRequestParameter(request, "retentionCheckBoxValue");
                if (!ApplicationUtils.isBlank((request.getParameter("fullOrPartial")))) {
                    fullOrPartial=ApplicationUtils.getRequestParameter(request, "fullOrPartial");
                }
                System.out.println("fullOrPartial::"+fullOrPartial);
                if (retentionCheckBoxValue != null && !retentionCheckBoxValue.equals("")) {
                    String[] invoiceAndProjectIdList = retentionCheckBoxValue.split("#");
                    for (int i = 0; i < invoiceAndProjectIdList.length; i++) {
                        bean = vendorPrezDataObj.getVendorInputBean();
                        if (!invoiceAndProjectIdList.equals("")) {
                            String[] invoiceAndProjectId = invoiceAndProjectIdList[i].split(",");
                           // for (int j = 0; j < invoiceAndProjectId.length; j++) {
                                String invoiceNo = invoiceAndProjectId[0];
                                String projectId = invoiceAndProjectId[1];
                                bean.setMsedclInvoiceNumber(invoiceNo);
                                bean.setProjectId(projectId);
                                VendorBean inputBean = new VendorBean();
                                inputBean.setVendorNumber(bean.getVendorNumber());
                                inputBean.setMsedclInvoiceNumber(bean.getMsedclInvoiceNumber());
                                if(fullOrPartial.equalsIgnoreCase("full")){                 // if full retention then insert using ps_PO_Status_new table
                                    vendorBean = vendorMgrObj.getVendorPsApplForm(inputBean);
                                    bean.setFullOrPartialRetention("full");
                                }else if(fullOrPartial.equalsIgnoreCase("partial")){// else directly insert records
                                    vendorBean=new VendorBean();
                                    vendorBean.setWRPST(invoiceAndProjectId[2]);
                                    bean.setWRET_AMT(invoiceAndProjectId[3]);
                                    bean.setFullOrPartialRetention("partial");
                                }
                           // }
                            if (vendorBean.getWRPST() != null) {// added to insert seperate record for each wrpst,orpst,crpst,srpst

                                bean.setSerialNo(++counter+"");
                                bean.setWRPST(vendorBean.getWRPST());
                                bean.setORPST(null);
                                bean.setSRPST(null);
                                bean.setCRPST(null);
                                if (savedVendorBean != null && savedVendorBean.getApplId() != null) {
                                    bean.setApplId(savedVendorBean.getApplId());
                                }
                                savedVendorBean = vendorMgrObj.saveClaimedRetentionDetails(bean);
                            }
                            if (vendorBean.getORPST() != null) {// added to insert seperate record for each wrpst,orpst,crpst,srpst
                                bean.setSerialNo(++counter+"");
                                bean.setORPST(vendorBean.getORPST());
                                bean.setWRPST(null);
                                bean.setSRPST(null);
                                bean.setCRPST(null);
                                if (savedVendorBean != null && savedVendorBean.getApplId() != null) {
                                    bean.setApplId(savedVendorBean.getApplId());
                                }
                                savedVendorBean = vendorMgrObj.saveClaimedRetentionDetails(bean);
                            }
                            if (vendorBean.getSRPST() != null) {// added to insert seperate record for each wrpst,orpst,crpst,srpst
                                bean.setSerialNo(++counter+"");
                                bean.setSRPST(vendorBean.getSRPST());
                                 bean.setWRPST(null);
                                bean.setORPST(null);
                                bean.setCRPST(null);
                                if (savedVendorBean != null && savedVendorBean.getApplId() != null) {
                                    bean.setApplId(savedVendorBean.getApplId());
                                }
                                savedVendorBean = vendorMgrObj.saveClaimedRetentionDetails(bean);
                            }
                            if (vendorBean.getCRPST() != null) {// added to insert seperate record for each wrpst,orpst,crpst,srpst
                               bean.setSerialNo(++counter+"");
                                bean.setCRPST(vendorBean.getCRPST());
                                 bean.setWRPST(null);
                                bean.setSRPST(null);
                                bean.setORPST(null);
                                if (savedVendorBean != null && savedVendorBean.getApplId() != null) {
                                    bean.setApplId(savedVendorBean.getApplId());
                                }
                                savedVendorBean = vendorMgrObj.saveClaimedRetentionDetails(bean);
                            }

                            
                        }

                    }
                    vendorPrezDataObj.setVendorInputBean(savedVendorBean);
                } else {

                    message = "Please select retention invoice to be submitted.";
                }
            } catch (Exception ex) {
                message="Error Occured.";
                java.util.logging.Logger.getLogger(AjaxControlServlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        message = "Retention Claim details is saved with application Id " + savedVendorBean.getApplId();
        obj.put("Message1", message);
        return obj;
    }

    public static JSONObject verifiedClaimedRetentionDetailsFormStatus(VendorPrezData vendorPrezDataObj, HttpServletRequest request) {
        JSONObject obj = new JSONObject();
        VendorDelegate vendorMgrObj = new VendorManager();
        String saveFlag = vendorPrezDataObj.getVendorInputBean().getSaveFlag();
        try {
            //if (ApplicationUtils.getRequestParameter(request, "module_type").equals(ApplicationConstants.PROJECT_SYSTEM)) {
            vendorMgrObj.saveClaimedRetentionDetails(vendorPrezDataObj.getVendorInputBean());
            // } 
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(VendorFormController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        obj.put("Message1", "Form Verified Successfully");
        vendorVerifiedSmsSendProcess(request, saveFlag); 
        return obj;
    }
    public static JSONObject submittedClaimedRetentionDetailsFormStatus(VendorPrezData vendorPrezDataObj, HttpServletRequest request) {
        JSONObject obj = new JSONObject();
        VendorDelegate vendorMgrObj = new VendorManager();
        String saveFlag = vendorPrezDataObj.getVendorInputBean().getSaveFlag();
        try {
            //if (ApplicationUtils.getRequestParameter(request, "module_type").equals(ApplicationConstants.PROJECT_SYSTEM)) {
            vendorMgrObj.saveClaimedRetentionDetails(vendorPrezDataObj.getVendorInputBean());
            // } 
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(VendorFormController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        obj.put("Message1", "Form Verified Successfully");
        //  vendorVerifiedSmsSendProcess(request, saveFlag); //UNCOMMENT FOR CLOUD
        return obj;
    }

    public static JSONObject getSubmittedLegalInvoiceFormStatus(LegalInvoiceInputBean legalInvoiceInputBean, HttpServletRequest request) {
         JSONObject obj = new JSONObject();
         HttpSession vendorSession = request.getSession();
        VendorDelegate vendorMgrObj = new VendorManager();
        
        
        HOSectionMatrixBean hoLegalbeanObj = new HOSectionMatrixBean();
        AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
        AssignOfficeBean assignOfficebeanObj = new AssignOfficeBean();
        SmsEmployee smsemp = new SmsEmployee();//ldap class to retrieve assignofficedto
        SmsDTO objSmsEmp = new SmsDTO();
          SmsDTO objSmsVendor = new SmsDTO();
        SmsDTO objSmsPoCreator = new SmsDTO();
        String Appl_id;
        
        
        
        try {
            
            
        try {
                String dealingOffice = legalInvoiceInputBean.getDealingOfficeName().substring(0, legalInvoiceInputBean.getDealingOfficeName().indexOf("-"));
                String sectionCode = legalInvoiceInputBean.getDeptCode();
                // if (!ApplicationUtils.isBlank(Purchasing_group)){
                if (!dealingOffice.equals("null")) {
                    if (!(dealingOffice.equals("261"))) {
                     
                    //    assignOfficeDTO = smsemp.Ldap(dealingOffice);//office incharge and technical person 
                    
                        objSmsEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                         objSmsEmp.setEmailId(assignOfficeDTO.getOfficerEmailId());
                    } else {
                        hoLegalbeanObj.setSectionCode(sectionCode);
                        hoLegalbeanObj = vendorMgrObj.getHOLegalSmsDetails(hoLegalbeanObj);//get the details of employee from emp_escalation_matrix
                        
                        if (!ApplicationUtils.isBlank(hoLegalbeanObj.getEmpNumber())) {
                            
                              objSmsEmp.setMobileNumber(hoLegalbeanObj.getEmpMobile());
                              objSmsEmp.setEmailId(hoLegalbeanObj.getEmpEmail());
                            
                              
                        }
  
                    }
                } else {
                 obj.put("Message1", "Please Check dealing office for Invoice!!! ");
                 return obj;
                }


            } catch (Exception e) {

            }
                       
            
            
            
            legalInvoiceInputBean = vendorMgrObj.saveLegalInvoiceForm(legalInvoiceInputBean);
            
            obj.put("Message1", "Form Submitted Successfully with Application ID " + legalInvoiceInputBean.getApplId());
             obj.put("status","Submitted");
             
          
              // Notify respective vendor via mail n sms--------------------------------
            try {
                  VendorBean vendorBeanObj1 = new VendorBean();
                try {
                    
                vendorBeanObj1.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                vendorBeanObj1.setPassword("");// to get the details only on user id and not on password
                vendorBeanObj1 = vendorMgrObj.getContactNumber(vendorBeanObj1);// get the contact details of vendor
            } catch (Exception e) {

            }                
                
                objSmsVendor.setMobileNumber(vendorBeanObj1.getVendorContactNumber());
                objSmsVendor.setEmailId(vendorBeanObj1.getMailId());
                
                  notifyLegalEmpInvSubmit(request, objSmsVendor,legalInvoiceInputBean,"VENDOR");
             
            } catch (Exception e) {
                //  System.out.println("in if empSubmitSmsSendProcess catch");
                e.printStackTrace();
            }
           //--------------------------
             
             
             // Notify respective employee via mail n sms--------------------------------
            try {
                  notifyLegalEmpInvSubmit(request, objSmsEmp,legalInvoiceInputBean,"EMP");//uncomment for cloud
             
            } catch (Exception e) {
                //  System.out.println("in if empSubmitSmsSendProcess catch");
                e.printStackTrace();
            }
           //--------------------------
             
             
             
        } catch (Exception ex) {
             obj.put("Message1", "Something went wrong..Please Check!!! ");
             logger.info("Error in getSubmittedLegalInvoiceFormStatus:"+ex.getMessage());
             ex.printStackTrace();
        }
         
          return obj;
    }

    public static JSONObject getSaveLegalInvoiceFormStatus(LegalInvoiceInputBean legalInvoiceInputBean,  LinkedList<FeeTypeDtlsBean> feeTypeDtlsBeanList,HttpServletRequest request) {
        JSONObject obj = new JSONObject();
        System.out.println("getSaveLegalInvoiceFormStatus");
        HttpSession vendorSession = request.getSession();
        VendorDelegate vendorMgrObj = new VendorManager();
        FeeTypeDtlsBean feeTypeDtlsBean=new FeeTypeDtlsBean();
        Integer Appl_id;
        try {
               Appl_id=legalInvoiceInputBean.getApplId();
                legalInvoiceInputBean = vendorMgrObj.saveLegalInvoiceForm(legalInvoiceInputBean);
                
                
                  for (FeeTypeDtlsBean fBean : (LinkedList<FeeTypeDtlsBean> ) feeTypeDtlsBeanList)
                      
                  { 
                      
                          if (legalInvoiceInputBean.getApplId()+"" != "" && legalInvoiceInputBean.getApplId().compareTo(0)!=0 ) 
                                   fBean.setApplId(legalInvoiceInputBean.getApplId());
                         else
                                   fBean.setApplId(Appl_id);
                          
                          
                     feeTypeDtlsBean=   vendorMgrObj.saveLFeeTypeDtlsForm(fBean);
                              
                  }
                
                
            if (legalInvoiceInputBean.getApplId()+"" != "" && legalInvoiceInputBean.getApplId().compareTo(0)!=0 ) {
                obj.put("Message1", "Form Saved Successfully with ID " + legalInvoiceInputBean.getApplId());
                obj.put("status","Saved");
            } else {
                
                legalInvoiceInputBean.setApplId(Appl_id);
                obj.put("Message1", "Form Data updated succesfully!!! ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("Message1", "Something went wrong..Please Check!!! ");
        }
        return obj;
    }

    public static JSONObject getVerifiedLegalInvoiceFormStatus(LegalInvoiceInputBean legalInvoiceInputBean, HttpServletRequest request) {
        JSONObject obj = new JSONObject();
        VendorDelegate vendorMgrObj = new VendorManager();
        String saveFlag = legalInvoiceInputBean.getSaveFlag();
       
        try {
            legalInvoiceInputBean = vendorMgrObj.saveLegalInvoiceForm(legalInvoiceInputBean);
             obj.put("Message1", "Form Accepted Successfully");
             
            
                
                  notifyLegalVendorInvAccepted(request, legalInvoiceInputBean);//uncomment for cloud
             
         
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(VendorFormController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             obj.put("Message1", "Something went wrong..Please Check!!! ");
        }

       
        //  vendorVerifiedSmsSendProcess(request, saveFlag); //UNCOMMENT FOR CLOUD
        return obj;
    }
    
    public static void notifyLegalVendorInvAccepted(HttpServletRequest request, LegalInvoiceInputBean legalInvoiceInputBean) {
        try {
            List<String> lstParamsAppr = new ArrayList<String>();
            HttpSession vendorSession = request.getSession();
            VendorBean vendorBeanObj1 = new VendorBean();
            String invoiceNumber = ApplicationUtils.getRequestParameter(request, "txtInvoiceNum");

            List<String> lstcredential = new ArrayList<String>();
            SmsController sms = new SmsController();
            SmsDTO objSms = new SmsDTO();
            String VendorContactNo = "";
            String VendorEmailId = "";
            String MailMessage = null;
            Date sysdate = new Date();
            VendorDelegate vendorMgrObj = new VendorManager();
            VendorStatuBean vendorStatusBeanObj = new VendorStatuBean();
            TemplateIdBean templateBeanObj =new TemplateIdBean();
            //matter in sms
            lstParamsAppr.add(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));
            lstParamsAppr.add(legalInvoiceInputBean.getVendorNumber());

            lstParamsAppr.add(legalInvoiceInputBean.getVendorName());
            lstParamsAppr.add(ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            lstParamsAppr.add(ApplicationConstants.VITS_URL);
            //username password and url link
            //lstcredential.add("607971");
            //lstcredential.add("mse12");
            //lstcredential.add("https://japi.instaalerts.zone/failsafe/HttpTemplateLink");
            lstcredential.add(ApplicationConstants.OTHER_URL);
            objSms.setLstParams(lstParamsAppr);
            try {
                vendorBeanObj1.setVendorNumber(legalInvoiceInputBean.getVendorNumber());
                vendorBeanObj1.setPassword("");//retrieve mobile and mail details without password
                vendorBeanObj1 = vendorMgrObj.getContactNumber(vendorBeanObj1);
                VendorContactNo = vendorBeanObj1.getVendorContactNumber();//mob no
                VendorEmailId = vendorBeanObj1.getMailId();//mail id

            } catch (Exception e) {

            }
            try {
                objSms.setMobileNumber(VendorContactNo);//if contact number is null
            } catch (Exception e) {

            }
            String Subject = "Invoice Verified Succesfully at Vendor Invoice Tracking System";
            String VendorNumber = (String) ApplicationUtils.getRequestParameter(request, "vendor_number");
            String VendorName = (String) ApplicationUtils.getRequestParameter(request, "vendor_name");
            String InvoiceapprDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
            MailMessage = "  Invoice no. " + invoiceNumber + " submitted by Vendor " + VendorNumber + "," + VendorName + " is accepted on " + InvoiceapprDate + ". Track status at https://vits.mahadiscom.in/VendorBillTracking/erp ";

            try {
                
         //       vendorStatusBeanObj.setSave_Flag(saveFlag);
                //DIABLED FOR SMS 
                if (objSms.getMobileNumber() != null) {
                     templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID6);
                     templateBeanObj=vendorMgrObj.getTemplateDetails(templateBeanObj);

                       //sms.sendSMS(objSms, "476833", lstcredential);
                    sms.sendSMS(objSms,templateBeanObj.getTemplate_Id(), lstcredential);
                    LegalCommunicationBean legalCommunicationSMSBean = new LegalCommunicationBean();
                    legalCommunicationSMSBean.setRECIPIENT_TYPE("VENDOR");
                    legalCommunicationSMSBean.setSUBJECT("476833");
                    legalCommunicationSMSBean.setRECIPIENTS_INFO(objSms.getMobileNumber());
                    legalCommunicationSMSBean.setCOMMUNICATION_TYPE("SMS");
                    legalCommunicationSMSBean.setCREATED(sysdate);

                     vendorMgrObj.updateLegalCommunicationLog(legalCommunicationSMSBean);
                       
                       
                       
                } else {
                    
                }

            } catch (Exception e) {
                
            }
            //DIABLED FOR SMS 
            if (vendorBeanObj1.getMailId() != null) {
                int success = SendMail.sendmail(VendorEmailId, Subject, MailMessage);
                LegalCommunicationBean legalCommunicationBean = new LegalCommunicationBean();
                if (success == 1) {
                     
                    legalCommunicationBean.setRECIPIENT_TYPE("VENDOR");
                    legalCommunicationBean.setSUBJECT(Subject);
                    legalCommunicationBean.setRECIPIENTS_INFO(vendorBeanObj1.getMailId());
                    legalCommunicationBean.setCOMMUNICATION_TYPE("Email");
                    legalCommunicationBean.setCREATED(sysdate);

                } else {
                     legalCommunicationBean.setRECIPIENT_TYPE("VENDOR");
                    legalCommunicationBean.setSUBJECT(Subject);
                    legalCommunicationBean.setCOMMUNICATION_TYPE("Email");
                     legalCommunicationBean.setRECIPIENTS_INFO(vendorBeanObj1.getMailId());
                    legalCommunicationBean.setCREATED(sysdate);
                     legalCommunicationBean.setERROR("Email sending Failed");
                    
                  
                }
                
                  vendorMgrObj.updateLegalCommunicationLog(legalCommunicationBean);
            } else {
                
            }
           
        } catch (Exception e) {
        }

    }

    public static JSONObject getRejectedLegalInvoiceFormStatus(LegalInvoiceInputBean legalInvoiceInputBean, HttpServletRequest request) {
         JSONObject obj = new JSONObject();
        VendorDelegate vendorMgrObj = new VendorManager();
        String saveFlag = legalInvoiceInputBean.getSaveFlag();
        try {
            legalInvoiceInputBean = vendorMgrObj.saveLegalInvoiceForm(legalInvoiceInputBean);
             obj.put("Message1", "Form returned Successfully");
             
             
               notifyLegalVendorInvRejected(request, legalInvoiceInputBean);//uncomment for cloud
             
             
             
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(VendorFormController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             obj.put("Message1", "Something went wrong..Please Check!!! ");
        }

       
       
        
        return obj;
    }

    
    public static void notifyLegalVendorInvRejected(HttpServletRequest request, LegalInvoiceInputBean legalInvoiceInputBean) {
        try {
            List<String> lstParamsAppr = new ArrayList<String>();
            HttpSession vendorSession = request.getSession();
            VendorBean vendorBeanObj1 = new VendorBean();
            String invoiceNumber = ApplicationUtils.getRequestParameter(request, "txtInvoiceNum");

            List<String> lstcredential = new ArrayList<String>();
            SmsController sms = new SmsController();
            SmsDTO objSms = new SmsDTO();
            String VendorContactNo = "";
            String VendorEmailId = "";
            String MailMessage = null;
            Date sysdate = new Date();
            VendorDelegate vendorMgrObj = new VendorManager();
            VendorStatuBean vendorStatusBeanObj = new VendorStatuBean();
             TemplateIdBean templateBeanObj =new TemplateIdBean();

            //matter in sms
            lstParamsAppr.add(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));
            lstParamsAppr.add(legalInvoiceInputBean.getVendorNumber());

            lstParamsAppr.add(legalInvoiceInputBean.getVendorName());
            lstParamsAppr.add(ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            
            lstParamsAppr.add(ApplicationUtils.getRequestParameter(request, "txtReason"));
            lstParamsAppr.add(ApplicationConstants.VITS_URL);
            //username password and url link
            //lstcredential.add("607971");
            //lstcredential.add("mse12");
            //lstcredential.add("https://japi.instaalerts.zone/failsafe/HttpTemplateLink");
            lstcredential.add(ApplicationConstants.OTHER_URL);
            objSms.setLstParams(lstParamsAppr);
            try {
                vendorBeanObj1.setVendorNumber(legalInvoiceInputBean.getVendorNumber());
                vendorBeanObj1.setPassword("");//retrieve mobile and mail details without password
                vendorBeanObj1 = vendorMgrObj.getContactNumber(vendorBeanObj1);
                VendorContactNo = vendorBeanObj1.getVendorContactNumber();//mob no
                VendorEmailId = vendorBeanObj1.getMailId();//mail id

            } catch (Exception e) {

            }
            try {
                objSms.setMobileNumber(VendorContactNo);//if contact number is null
            } catch (Exception e) {

            }
           
           String VendorNumber = (String) ApplicationUtils.getRequestParameter(request, "vendor_number");
            String VendorName = (String) ApplicationUtils.getRequestParameter(request, "vendor_name");
            //String invoiceNumber = ApplicationUtils.getRequestParameter(request, "txtInvoiceNum");
            String RejReason = ApplicationUtils.getRequestParameter(request, "txtReason");
            String InvoicerejDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
            String Subject = "Invoice Rejected at Vendor Invoice Tracking System";
            MailMessage = "  Invoice no. " + invoiceNumber + "  submitted by Vendor " + VendorNumber + "," + VendorName + "  has been rejected on " + InvoicerejDate + " due to " + RejReason + " reason. Resubmit invoice at https://vits.mahadiscom.in/VendorBillTracking/erp";
           

            try {
               
         //       vendorStatusBeanObj.setSave_Flag(saveFlag);
                //DIABLED FOR SMS 
                if (objSms.getMobileNumber() != null) {
                      templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID5);
                      templateBeanObj=vendorMgrObj.getTemplateDetails(templateBeanObj);

                       //sms.sendSMS(objSms, "476834", lstcredential);
                  sms.sendSMS(objSms, templateBeanObj.getTemplate_Id(), lstcredential);
                    LegalCommunicationBean legalCommunicationSMSBean = new LegalCommunicationBean();
                    legalCommunicationSMSBean.setRECIPIENT_TYPE("VENDOR");
                    legalCommunicationSMSBean.setSUBJECT("476834");
                    legalCommunicationSMSBean.setRECIPIENTS_INFO(objSms.getMobileNumber());
                    legalCommunicationSMSBean.setCOMMUNICATION_TYPE("SMS");
                    legalCommunicationSMSBean.setCREATED(sysdate);

                     vendorMgrObj.updateLegalCommunicationLog(legalCommunicationSMSBean);
                       
                       
                       
                } else {
                    
                }

            } catch (Exception e) {
                
            }
            //DIABLED FOR SMS 
            if (vendorBeanObj1.getMailId() != null) {
                int success = SendMail.sendmail(VendorEmailId, Subject, MailMessage);
                LegalCommunicationBean legalCommunicationBean = new LegalCommunicationBean();
                if (success == 1) {
                     
                    legalCommunicationBean.setRECIPIENT_TYPE("VENDOR");
                    legalCommunicationBean.setSUBJECT(Subject);
                    legalCommunicationBean.setRECIPIENTS_INFO(vendorBeanObj1.getMailId());
                    legalCommunicationBean.setCOMMUNICATION_TYPE("Email");
                    legalCommunicationBean.setCREATED(sysdate);

                } else {
                     legalCommunicationBean.setRECIPIENT_TYPE("VENDOR");
                    legalCommunicationBean.setSUBJECT(Subject);
                    legalCommunicationBean.setCOMMUNICATION_TYPE("Email");
                     legalCommunicationBean.setRECIPIENTS_INFO(vendorBeanObj1.getMailId());
                    legalCommunicationBean.setCREATED(sysdate);
                     legalCommunicationBean.setERROR("Email sending Failed");
                    
                  
                }
                
                  vendorMgrObj.updateLegalCommunicationLog(legalCommunicationBean);
            } else {
                
            }
           
        } catch (Exception e) {
        }

    }

    

}
