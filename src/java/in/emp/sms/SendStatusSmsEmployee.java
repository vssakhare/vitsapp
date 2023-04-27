
/*
 * To change this template, choose Tools | Templates
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javaldapapp.AssignOfficeDTO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class SendStatusSmsEmployee {

    private static Logger logger = Logger.getLogger(SendStatusSmsEmployee.class);
    private static Connection conn = null;

    public static void SendStatusSms() throws Exception {
        LinkedList<VendorInputBean> SmsFileList = new LinkedList();
        VendorDelegate vendorMgrObj = new VendorManager();
        VendorInputBean vendorInputBeanObj = new VendorInputBean();

        try {
            logger.log(Level.INFO, "Employee Status  sms Scheduler :: run() :: method called .. ");
            System.out.println("Employee Status sms Scheduler :: run() :: method called ");
            SmsFileList = vendorMgrObj.getSmsTrackerList(vendorInputBeanObj);

            if (SmsFileList != null) {
                for (VendorInputBean v : SmsFileList) {
                    String sql = "";
                    String VendorMailId = "";
                    String MobileNo = "";
                    String InvoiceNumber = "";
                    String designation = "";
                    HOBean hobeanobj = new HOBean();
                    POBean poBeanObj = new POBean();
                    VendorInputBean vendorInputBeanObj1 = new VendorInputBean();
                    String office_code = v.getOffice_Code();
                    String dept = "";
                    List<String> lstcredential = new ArrayList<String>();
                    List<String> lstParam2 = new ArrayList<String>();
                    SmsDTO objSmsVendor = new SmsDTO();
                    SmsController sms = new SmsController();
                    AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
                    AssignOfficeBean assignOfficebeanObj = new AssignOfficeBean();
                    SmsEmployee smshigheremp = new SmsEmployee();
                    SmsDTO objSmsEmp = new SmsDTO();
                    TemplateIdBean templateBeanObj = new TemplateIdBean();
                    DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
                    // lstcredential.add("607971");
                    // lstcredential.add("mse12");
                    //lstcredential.add("https://japi.instaalerts.zone/failsafe/HttpTemplateLink");
                    lstcredential.add(ApplicationConstants.OTHER_URL);
                    lstcredential.add(ApplicationConstants.BULK_SMS_Y);
                    lstParam2.add(v.getVendorInvoiceNumber());
                    lstParam2.add(v.getVendorNumber());
                    lstParam2.add(v.getVendorName());
                    if (v.getStatus().equals("Pending With Technical")) {
                        dept = "Technical";
                    } else if (v.getStatus().equals("Pending With Accounts")) {
                        dept = "Accounts";
                    }
                    if (!(office_code.equals("null"))) {
                        assignOfficeDTO = smshigheremp.LdapDepartment(office_code, dept, designation);// office incharge and technical person 
                        vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                        vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                        vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                        vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                        objSmsVendor.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                    }
                    /*    if (!ApplicationUtils.isBlank(v.getPurchasing_group())){
                     if ( v.getPurchasing_group().equals("Z00"))
                {
                  poBeanObj.setPONumber(v.getPONumber());
                  poBeanObj.setSelectedModule(v.getSelectedModuleType());
                  poBeanObj = vendorMgrObj.getPlantCodeDetails(poBeanObj);
                  
                   assignOfficeDTO = smshigheremp.LdapDepartment(poBeanObj.getParentOfficeode(),dept,designation);// office incharge and technical person 
                vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                objSmsVendor.setMobileNumber(assignOfficeDTO.getOfficerContactNo()); 
                }
                   else{   
                hobeanobj.setPURCHASING_GROUP(v.getPurchasing_group());
                hobeanobj = vendorMgrObj.getHOSmsDetails(hobeanobj);
                if(hobeanobj!=null){
                if (!ApplicationUtils.isBlank(hobeanobj.getPLANT()))
                {
                   if ( hobeanobj.getPLANT().equals(ApplicationConstants.HO_OFFICE_PLANT))
                   {
                      assignOfficebeanObj = smshigheremp.Ldapcpf(hobeanobj.getEMP_CPF());
                vendorInputBeanObj.setempCpf(assignOfficebeanObj.getOfficerCpfNo());
                vendorInputBeanObj.setempName(assignOfficebeanObj.getOfficerName());
                vendorInputBeanObj.setDesignation(assignOfficebeanObj.getOfficerDesignation());
                vendorInputBeanObj.setContactNumber(assignOfficebeanObj.getOfficerContactNo());
                objSmsVendor.setMobileNumber(assignOfficebeanObj.getOfficerContactNo()); 
                       
                   }
                   else{
                          if (!ApplicationUtils.isBlank(hobeanobj.getEMP_DESIGNATION())){
                       designation= hobeanobj.getEMP_DESIGNATION();  
                      }  
                        assignOfficeDTO = smshigheremp.LdapDepartment(office_code,dept,designation);// office incharge and technical person 
                vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                objSmsVendor.setMobileNumber(assignOfficeDTO.getOfficerContactNo()); 
                       
                   }
                }
                }
                     }
              
                }*/
                    VendorMailId = v.getEmailId();
                    InvoiceNumber = v.getVendorInvoiceNumber();
                    if ((v.getStatus().equals("Pending With Technical")) && !(v.getEmp_tech_sms_flag().equals("Y")) && v.getInvoiceApprDAte() != null) {
                        lstParam2.add(df3.format(v.getInvoiceApprDAte()));
                        objSmsVendor.setLstParams(lstParam2);
                        templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID9);
                        templateBeanObj = vendorMgrObj.getTemplateDetails(templateBeanObj);

                        // sms.sendSMS(objSmsVendor, "476961", lstcredential);
                        sms.sendSMS(objSmsVendor, templateBeanObj.getTemplate_Id(), lstcredential);
                        sql = " UPDATE sms_sent_tracker set EMP_TECH_SMS_SENT = 'Y',TECH_EMP_NAME = ?,TECH_EMP_DESGN = ?,TECH_EMP_CPF = ?,TECH_EMP_MOB =?,EMP_TECH_SMS_DATE=SYSDATE WHERE  APPL_ID = ? ";

                    } else if ((v.getStatus().equals("Pending With Accounts")) && !(v.getEmp_Acc_sms_flag().equals("Y")) && v.getSormDate() != null) {
                        lstParam2.add(df3.format(v.getSormDate()));
                        objSmsVendor.setLstParams(lstParam2);
                        templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID11);
                        templateBeanObj = vendorMgrObj.getTemplateDetails(templateBeanObj);
//sms.sendSMS(objSmsVendor, "476963", lstcredential);
                        sms.sendSMS(objSmsVendor, templateBeanObj.getTemplate_Id(), lstcredential);
                        sql = " UPDATE sms_sent_tracker set EMP_ACC_SMS_SENT = 'Y',ACC_EMP_NAME = ?,ACC_EMP_DESGN = ?,ACC_EMP_CPF = ?,ACC_EMP_MOB =?, EMP_ACC_SMS_DATE=SYSDATE WHERE  APPL_ID = ? ";
                    }
                    try {
                        if (sql != null && sql != "") {
                            PreparedStatement psq = null;
                            conn = ApplicationUtils.getConnection();
                            logger.log(Level.INFO, "GetSendSmsEmployeeQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

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
                        }
                    } catch (SQLException e2) {
                        // e2.printStackTrace();
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
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SendSMS :: run() :: Exception .. " + ex.getMessage());
            //ex.printStackTrace();
        }
    }
}
