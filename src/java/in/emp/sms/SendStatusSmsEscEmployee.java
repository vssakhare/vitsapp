/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.sms;

/**
 *
 * @author Pooja Jadhav
 */
import in.emp.common.ApplicationConstants;
import in.emp.sms.bean.TemplateIdBean;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.HOBean;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.SmsDTO;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.manager.VendorManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javaldapapp.AssignOfficeDTO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class SendStatusSmsEscEmployee {

    private static Logger logger = Logger.getLogger(SendStatusSmsEscEmployee.class);
    private static Connection conn = null;

    public static void SendStatusEscSms() {
        try {

            logger.log(Level.INFO, "Escalation Employee Status sms Scheduler :: run() :: method called .. ");
            System.out.println("Escalation Employee Status sms Scheduler :: run() :: method called ");
            LinkedList<VendorInputBean> FileList = new LinkedList();
            VendorInputBean vendorInputBeanObj = new VendorInputBean();
            VendorDelegate vendorMgrObj = new VendorManager();

            SmsEmployee smshigheremp = new SmsEmployee();
            AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();

            SmsDTO objVendor = new SmsDTO();
            SmsDTO objSmsHigherEmp = new SmsDTO();
            SmsController sms = new SmsController();
            HOBean hobeanobj = new HOBean();
            POBean poBeanObj = new POBean();
            try {
                FileList = vendorMgrObj.getEscStatusSmsList(vendorInputBeanObj);
            } catch (Exception ex) {
                logger.log(Level.ERROR, "SendStatusSmsEscEmployee :: SendStatusEscSms() :: Exception :: " + ex);

            }
            if (FileList != null) {

                for (VendorInputBean v : FileList) {
                    String parent_office_code = v.getParent_Office_Code();
                    String dept = "";
                    String designation = "";
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
                    lstParam2.add(v.getDaysDelayed());
                    lstParam2.add(v.getempName());
                    objSmsHigherEmp.setLstParams(lstParam2);
                    if (v.getStatus().equals("Pending With Technical")) {
                        dept = "Technical";
                    } else if (v.getStatus().equals("Pending With Accounts")) {
                        dept = "Accounts";
                    }
                    if (!(parent_office_code.equals("null"))) {
                        assignOfficeDTO = smshigheremp.LdapDepartment(parent_office_code, dept, designation);// parent office incharge and technical person 
                        vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                        vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                        vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                        vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                        objSmsHigherEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                    }
                    /*      if (!ApplicationUtils.isBlank(v.getPurchasing_group())){
                           
                           if ( v.getPurchasing_group().equals("Z00"))
                {
                  poBeanObj.setPONumber(v.getPONumber());
                  poBeanObj.setSelectedModule(v.getSelectedModuleType());
                  poBeanObj = vendorMgrObj.getPlantCodeDetails(poBeanObj);
               assignOfficeDTO = smshigheremp.LdapDepartment(poBeanObj.getParentOfficeode(),dept,designation);// parent office incharge and technical person 
                vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                objSmsHigherEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo()); 
                
                
                }
                           
                           else{         
                hobeanobj.setPURCHASING_GROUP(v.getPurchasing_group());
                hobeanobj = vendorMgrObj.getHOSmsDetails(hobeanobj);
                if(hobeanobj!=null){
                if (!ApplicationUtils.isBlank(hobeanobj.getPLANT()))
                {
                   if ( hobeanobj.getPLANT().equals(ApplicationConstants.HO_OFFICE_PLANT))
                   {
                      assignOfficeDTO = smshigheremp.Ldapcpf(hobeanobj.getESCALATION_EMP_CPF());
                vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                objSmsHigherEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo()); 
                       
                   }
                   else{
                      if (!ApplicationUtils.isBlank(hobeanobj.getESCALATION_EMP_DESIGNATION())){
                       designation= hobeanobj.getESCALATION_EMP_DESIGNATION();  
                      }  
                assignOfficeDTO = smshigheremp.LdapDepartment(parent_office_code,dept,designation);// parent office incharge and technical person 
                vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                objSmsHigherEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo()); 
                       
                   }
                }
                }
                                   }
              
                   
                       }*/

                    try {
                        StringBuilder sql = new StringBuilder();
                        PreparedStatement psq = null;
                        conn = ApplicationUtils.getConnection();
                        if ((v.getStatus().equals("Pending With Technical")) && !(v.getEsc_tech_sms_flag().equals("Y"))) {
                            templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID10);
                            templateBeanObj = vendorMgrObj.getTemplateDetails(templateBeanObj);
                            //sms.sendSMS(objSmsHigherEmp, "476962", lstcredential);
                            sms.sendSMS(objSmsHigherEmp, templateBeanObj.getTemplate_Id(), lstcredential);
                            sql.append("UPDATE sms_sent_tracker set ESC_TECH_SMS_SENT = 'Y',ESC_TECH_EMP_NAME = ?,ESC_TECH_EMP_DESGN = ?,ESC_TECH_EMP_CPF = ?,ESC_TECH_EMP_MOB =?,ESC_TECH_SMS_DATE=SYSDATE WHERE  APPL_ID = ?");

                        } else if ((v.getStatus().equals("Pending With Accounts")) && !(v.getEsc_tech_sms_flag().equals("Y"))) {
                            templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID12);
                            templateBeanObj = vendorMgrObj.getTemplateDetails(templateBeanObj);
                            //sms.sendSMS(objSmsHigherEmp, "476964", lstcredential);
                            sms.sendSMS(objSmsHigherEmp, templateBeanObj.getTemplate_Id(), lstcredential);
                            sql.append(" UPDATE sms_sent_tracker set ESC_ACC_SMS_SENT = 'Y',ESC_ACC_EMP_NAME = ?,ESC_ACC_EMP_DESGN = ?,ESC_ACC_EMP_CPF = ?,ESC_ACC_EMP_MOB =?, ESC_ACC_SMS_DATE=SYSDATE WHERE  APPL_ID = ? ");
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
            ex.printStackTrace();
        }

    }
}
