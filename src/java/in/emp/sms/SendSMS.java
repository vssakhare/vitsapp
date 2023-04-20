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
public class SendSMS {

    private static Logger logger = Logger.getLogger(SendSMS.class);
    private static Connection conn = null;

    public static void SendHigherAuthSms() {
        try {

            logger.log(Level.INFO, "Employee sms Scheduler :: run() :: method called .. ");
            System.out.println("\nEmployee sms Scheduler :: run() :: method called ");
            LinkedList<VendorInputBean> FileList = new LinkedList();
            VendorInputBean vendorInputBeanObj = new VendorInputBean();
            VendorDelegate vendorMgrObj = new VendorManager();

            SmsEmployee smshigheremp = new SmsEmployee();
            AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
            AssignOfficeBean assignOfficebeanObj = new AssignOfficeBean();
            SmsDTO objSmsEmp = new SmsDTO();
            SmsDTO objSmsHigherEmp = new SmsDTO();
            SmsController sms = new SmsController();

            try {
                FileList = vendorMgrObj.getHigherContactList(vendorInputBeanObj);
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
                    // lstcredential.add("mse12");
                    //lstcredential.add("http://121.241.247.144:7501/failsafe/HttpTemplateLink");
                    // lstcredential.add("https://japi.instaalerts.zone/failsafe/HttpTemplateLink");
                    lstcredential.add(ApplicationConstants.OTHER_URL);
                    lstParam2.add(v.getVendorInvoiceNumber());

                    lstParam2.add(v.getVendorNumber());
                    lstParam2.add(v.getVendorName());
                    lstParam2.add(v.getempName());
                    lstParam2.add(v.getDaysDelayed());
                    objSmsHigherEmp.setLstParams(lstParam2);
                    objSmsEmp.setLstParams(lstParam2);
                    if (!(parent_office_code.equals("null"))) {
                        assignOfficeDTO = smshigheremp.Ldap(parent_office_code);//parent office incharge and technical person 
                        vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                        vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                        vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                        vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                        objSmsHigherEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                    }
                    if (!(office_code.equals("null"))) {
                        assignOfficeDTO = smshigheremp.Ldap(office_code);//get the details of auth employee also to send the same message to auth employee also along with higher authority
                        objSmsEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                    }
                    /*     if (!(v.getPurchasing_group().equals("null"))) {
                        if (v.getPurchasing_group().equals("Z00")) {
                            poBeanObj.setPONumber(v.getPONumber());
                            poBeanObj.setSelectedModule(v.getSelectedModuleType());
                            poBeanObj = vendorMgrObj.getPlantCodeDetails(poBeanObj);
                            assignOfficeDTO = smshigheremp.Ldap(poBeanObj.getParentOfficeode());//parent office incharge and technical person 
                            vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                            vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                            vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                            vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                            objSmsHigherEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                            assignOfficeDTO = smshigheremp.Ldap(poBeanObj.getOfficeode());//get the details of auth employee also to send the same message to auth employee also along with higher authority
                            objSmsEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                        } else {

                            hobeanobj.setPURCHASING_GROUP(v.getPurchasing_group());
                            hobeanobj = vendorMgrObj.getHOSmsDetails(hobeanobj);
                            if (hobeanobj != null) {
                                if (!ApplicationUtils.isBlank(hobeanobj.getPLANT())) {
                                    if (hobeanobj.getPLANT().equals(ApplicationConstants.HO_OFFICE_PLANT)) {
                                        assignOfficebeanObj = smshigheremp.Ldapcpf(hobeanobj.getESCALATION_EMP_CPF());
                                        vendorInputBeanObj.setempCpf(assignOfficebeanObj.getOfficerCpfNo());
                                        vendorInputBeanObj.setempName(assignOfficebeanObj.getOfficerName());
                                        vendorInputBeanObj.setDesignation(assignOfficebeanObj.getOfficerDesignation());
                                        vendorInputBeanObj.setContactNumber(assignOfficebeanObj.getOfficerContactNo());
                                        objSmsHigherEmp.setMobileNumber(assignOfficebeanObj.getOfficerContactNo());
                                        assignOfficebeanObj = smshigheremp.Ldapcpf(hobeanobj.getEMP_CPF());//get the details of auth employee also to send the same message to auth employee also along with higher authority
                                        objSmsEmp.setMobileNumber(assignOfficebeanObj.getOfficerContactNo());

                                    } else {
                                        assignOfficeDTO = smshigheremp.Ldap(parent_office_code);//parent office incharge and technical person 
                                        vendorInputBeanObj.setempCpf(assignOfficeDTO.getOfficerCpfNo());
                                        vendorInputBeanObj.setempName(assignOfficeDTO.getOfficerName());
                                        vendorInputBeanObj.setDesignation(assignOfficeDTO.getOfficerDesignation());
                                        vendorInputBeanObj.setContactNumber(assignOfficeDTO.getOfficerContactNo());
                                        objSmsHigherEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                                        assignOfficeDTO = smshigheremp.Ldap(office_code);//get the details of auth employee also to send the same message to auth employee also along with higher authority
                                        objSmsEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                                    }
                                }
                            }
                        }

                    }else{
                    assignOfficebeanObj = smshigheremp.LdapDb_Code(vendorInputBeanObj.getLocation().substring(1));//To get billing db code
                    vendorInputBeanObj.setempCpf(assignOfficebeanObj.getOfficerCpfNo());
                    vendorInputBeanObj.setempName(assignOfficebeanObj.getOfficerName());
                    vendorInputBeanObj.setDesignation(assignOfficebeanObj.getOfficerDesignation());
                    vendorInputBeanObj.setContactNumber(assignOfficebeanObj.getOfficerContactNo());
                    objSmsEmp.setMobileNumber(assignOfficebeanObj.getOfficerContactNo());
                    assignOfficeDTO = smshigheremp.Ldap(vendorInputBeanObj.getParent_Office_Code());
                    objSmsHigherEmp.setMobileNumber(assignOfficeDTO.getOfficerContactNo());
                    }*/
                    try {
                        templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID4);
                        templateBeanObj = vendorMgrObj.getTemplateDetails(templateBeanObj);
                        // sms.sendSMS(objSmsHigherEmp, "476810", lstcredential);//send sms to higher authority
                        // sms.sendSMS(objSmsEmp, "476810", lstcredential);//send same sms to auth employee.
                        sms.sendSMS(objSmsHigherEmp, templateBeanObj.getTemplate_Id(), lstcredential);
                        sms.sendSMS(objSmsEmp, templateBeanObj.getTemplate_Id(), lstcredential);
                        try {
                            StringBuilder sql = new StringBuilder();
                            PreparedStatement psq = null;
                            conn = ApplicationUtils.getConnection();
                            if (v.getSelectedModuleType().equals("PS")) {
                                sql.append(" UPDATE XXMIS_ERP_PS_VENDOR_DTL set higher_auth_sms_sent_cc_to_emp='Y' ,higher_auth_sms_sent_flag = 'Y',HIGHER_AUTH_EMP_NAME = ?,HIGHER_AUTH_EMP_DESGN = ?,HIGHER_AUTH_EMP_CPF = ?,HIGHER_AUTH_EMP_MOB =?,HIGHER_AUTH_SMS_SENT_DATE=SYSTIMESTAMP WHERE APPL_ID = ? ");
                            } else {
                                sql.append(" UPDATE XXMIS_ERP_VENDOR_DTL set higher_auth_sms_sent_cc_to_emp='Y' ,higher_auth_sms_sent_flag = 'Y',HIGHER_AUTH_EMP_NAME = ?,HIGHER_AUTH_EMP_DESGN = ?,HIGHER_AUTH_EMP_CPF = ?,HIGHER_AUTH_EMP_MOB =?,HIGHER_AUTH_SMS_SENT_DATE=SYSTIMESTAMP WHERE APPL_ID = ? ");

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

        } catch (Exception ex) {
            logger.log(Level.ERROR, "SendSMS :: run() :: Exception .. " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
