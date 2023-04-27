/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.sms;

import in.emp.common.ApplicationConstants;
import in.emp.sms.bean.TemplateIdBean;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.AssignOfficeBean;
import in.emp.vendor.bean.HOBean;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.SmsDTO;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.manager.VendorManager;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javaldapapp.AssignOfficeDTO;
import org.apache.log4j.Level;

/**
 *
 * @author pooja jadhav
 */
// Created to send sms maanually to those employee to whom sms was not sent through portal.
public class SendSubmittedSmsEmployee {

    private static Logger logger = Logger.getLogger(SendSMS.class);
    private static Connection conn = null;

    public static void SendAuthSms() {
        try {

            logger.log(Level.INFO, "Employee sms Scheduler :: run() :: method called .. ");
            System.out.println("\nEmployee sms Scheduler :: run() :: method called ");
            LinkedList<VendorInputBean> FileList = new LinkedList();
            VendorInputBean vendorInputBeanObj = new VendorInputBean();
            VendorDelegate vendorMgrObj = new VendorManager();

            SmsEmployee smshigheremp = new SmsEmployee();
            AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
            AssignOfficeBean assignOfficebeanObj = new AssignOfficeBean();
            SmsDTO objSmsHigherEmp = new SmsDTO();
            SmsDTO objSmsEmp = new SmsDTO();
            SmsController sms = new SmsController();

            try {
                FileList = vendorMgrObj.getAuthContactList(vendorInputBeanObj);
            } catch (Exception ex) {
                logger.log(Level.ERROR, "SendSMS :: SendHigherAuthSms() :: Exception :: " + ex);
            }
            if (FileList != null) {

                for (VendorInputBean v : FileList) {
                    HOBean hobeanobj = new HOBean();
                    POBean poBeanObj = new POBean();
                    String parent_office_code = v.getParent_Office_Code();
                    String office_code = v.getOffice_Code();
                    VendorInputBean vendorInputBeanObj1 = new VendorInputBean();
                    TemplateIdBean templateBeanObj = new TemplateIdBean();
                    List<String> lstcredential = new ArrayList<String>();
                    List<String> lstParam2 = new ArrayList<String>();
                    //lstcredential.add("607971");
                    //lstcredential.add("mse12");
                    //lstcredential.add("http://121.241.247.144:7501/failsafe/HttpTemplateLink");
                    //lstcredential.add("https://japi.instaalerts.zone/failsafe/HttpTemplateLink");
                    lstcredential.add(ApplicationConstants.OTHER_URL);
                    lstcredential.add(ApplicationConstants.BULK_SMS_Y);
                    lstParam2.add(v.getVendorInvoiceNumber());

                    lstParam2.add(v.getVendorNumber());
                    lstParam2.add(v.getVendorName());
                    //lstParam2.add(v.getempName());
                    lstParam2.add(v.getDaysDelayed());
                    //objSmsHigherEmp.setLstParams(lstParam2);
                    objSmsEmp.setLstParams(lstParam2);
                    if (!(v.getPurchasing_group().equals("null"))) {
                        if (!ApplicationUtils.isBlank(v.getPurchasing_group())) {
                            if (v.getPurchasing_group().equals("Z00")) {
                                poBeanObj.setPONumber(v.getPONumber());
                                poBeanObj.setSelectedModule(v.getSelectedModuleType());
                                poBeanObj = vendorMgrObj.getPlantCodeDetails(poBeanObj);

                                assignOfficeDTO = smshigheremp.Ldap(poBeanObj.getOfficeode());//parent office incharge and technical person 
                                vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                                vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                                vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                                vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                                //objSmsHigherEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo()); 
                                //  assignOfficeDTO = smshigheremp.Ldap(office_code);//get the details of auth employee also to send the same message to auth employee also along with higher authority
                                objSmsEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                            } else {
                                hobeanobj.setPURCHASING_GROUP(v.getPurchasing_group());
                                hobeanobj = vendorMgrObj.getHOSmsDetails(hobeanobj);
                                if (hobeanobj != null) {
                                    if (!ApplicationUtils.isBlank(hobeanobj.getPLANT())) {
                                        if (hobeanobj.getPLANT().equals(ApplicationConstants.HO_OFFICE_PLANT)) {
                                            assignOfficebeanObj = smshigheremp.Ldapcpf(hobeanobj.getEMP_CPF());
                                            vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                                            vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                                            vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                                            vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                                            // objSmsHigherEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo()); 
                                            //  assignOfficeDTO = smshigheremp.Ldapcpf(hobeanobj.getEMP_CPF());//get the details of auth employee also to send the same message to auth employee also along with higher authority
                                            objSmsEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());

                                        } else {
                                            assignOfficeDTO = smshigheremp.Ldap(office_code);//parent office incharge and technical person 
                                            vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                                            vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                                            vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                                            vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                                            //objSmsHigherEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo()); 
                                            // assignOfficeDTO = smshigheremp.Ldap(office_code);//get the details of auth employee also to send the same message to auth employee also along with higher authority
                                            objSmsEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                                        }
                                    }
                                }
                            }
                            try {
                                templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID3);
                                templateBeanObj = vendorMgrObj.getTemplateDetails(templateBeanObj);

                                //sms.sendSMS(objSmsEmp, "476809", lstcredential);//send  sms to auth employee.
                                sms.sendSMS(objSmsEmp, templateBeanObj.getTemplate_Id(), lstcredential);
                                try {
                                    StringBuilder sql = new StringBuilder();
                                    PreparedStatement psq = null;
                                    conn = ApplicationUtils.getConnection();
                                    if (v.getSelectedModuleType().equals("PS")) {
                                        sql.append(" UPDATE XXMIS_ERP_PS_VENDOR_DTL set INV_SUBMIT_EMPSMS_SENT_FLAG = 'Y',AUTH_EMPLOYEE_NAME = ?,AUTH_EMPLOYEE_DESIGNATION = ?,AUTH_EMPLOYEE = ?,AUTH_EMPLOYEE_MOBILE_NO =?,INV_SUBMIT_SMSEMP_DATE=SYSTIMESTAMP WHERE APPL_ID = ? ");
                                    } else {
                                        sql.append(" UPDATE XXMIS_ERP_VENDOR_DTL set INV_SUBMIT_EMPSMS_SENT_FLAG = 'Y',AUTH_EMPLOYEE_NAME = ?,AUTH_EMPLOYEE_DESIGNATION = ?,AUTH_EMPLOYEE = ?,AUTH_EMPLOYEE_MOBILE_NO =?,INV_SUBMIT_SMSEMP_DATE=SYSTIMESTAMP WHERE APPL_ID = ? ");

                                    }
                                    psq = conn.prepareStatement(sql.toString());

                                    psq.setString(1, vendorInputBeanObj.getempName());
                                    psq.setString(2, vendorInputBeanObj.getDesignation());
                                    psq.setString(3, vendorInputBeanObj.getempCpf());
                                    psq.setString(4, vendorInputBeanObj.getContactNumber());
                                    psq.setString(5, v.getApplId());
                                    psq.executeUpdate();
                                    conn.commit();

                                    if (psq != null) {
                                        psq.close();
                                    }
                                } catch (SQLException e2) {
                                    e2.printStackTrace();
                                }

                            } catch (Exception e) {
                                logger.log(Level.ERROR, "AjaxControlServlet :: sendingSMS() :: Exception :: " + e);
                                e.printStackTrace();
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
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SendSMS :: run() :: Exception .. " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
