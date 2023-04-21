/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.sms;

import in.emp.common.SendMail;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.VendorDelegate;
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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class SendSmsVendor {

    private static Logger logger = Logger.getLogger(SendSmsVendor.class);
    private static Connection conn = null;

    public static void SendSms() throws Exception {
        LinkedList<VendorInputBean> SmsFileList = new LinkedList();
  VendorDelegate vendorMgrObj = new VendorManager();
  VendorInputBean vendorInputBeanObj = new VendorInputBean();
   
        try {
              logger.log(Level.INFO, "Vendor sms Scheduler :: run() :: method called .. ");
            System.out.println("\nVendor sms Scheduler :: run() :: method called ");
            SmsFileList = vendorMgrObj.getSmsTrackerList(vendorInputBeanObj);
             
       if(SmsFileList != null){
         for (VendorInputBean v : SmsFileList) {
             String sql="";
             String VendorMailId="";
             String MobileNo="";
             String InvoiceNumber="";
              List<String> lstcredential = new ArrayList<String>();
                List<String> lstParam2 = new ArrayList<String>();
                 SmsDTO objSmsVendor = new SmsDTO();
            SmsController sms = new SmsController();
                  DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
                lstcredential.add("607971");
                lstcredential.add("mse12");
               lstcredential.add("https://japi.instaalerts.zone/failsafe/HttpTemplateLink");
                lstParam2.add(v.getVendorInvoiceNumber());
                objSmsVendor.setLstParams(lstParam2);
                objSmsVendor.setMobileNumber(v.getContactNumber());
              VendorMailId=v.getEmailId();
              InvoiceNumber=v.getVendorInvoiceNumber();
//currently designed only for migo as ven inv no is added only while doing migo in sap process
             if((v.getStatus().equals("Pending With Accounts") )&& !(v.getAccounts_sms_flag().equals("Y")) && v.getSormDate()!=null && v.getMsedclInvoiceNumber()!=null)
             {
               
              //lstParam.add(df3.format(UpdatedDate));
                 lstParam2.add(df3.format(v.getSormDate()));
                 sms.sendSMS(objSmsVendor, "476831", lstcredential);
                sql=  " UPDATE sms_sent_tracker set Accounts_SMS_SENT = 'Y', Accounts_SMS_SENT_DATE=SYSDATE WHERE VENDOR_INVOICE_NUMBER = ? AND VENDOR_NUMBER = ? AND APPL_ID = ?";
             try{
                  String Subject="Invoice processed by Technical at Vendor Invoice Tracking Portal";
                  String MailMessage="Invoice no" +InvoiceNumber+" has been processed  by Technical on "+v.getSormDate()+" and sent to accounts for necessary action.";
             
                  int success=SendMail.sendmail(VendorMailId,Subject,MailMessage);
              if(success==1)
              {sql= " UPDATE sms_sent_tracker set Accounts_SMS_SENT = 'Y',Accounts_MAIL_SENT = 'Y', Accounts_SMS_SENT_DATE=SYSDATE,Accounts_MAIL_SENT_DATE=SYSDATE WHERE VENDOR_INVOICE_NUMBER = ? AND VENDOR_NUMBER = ? AND APPL_ID = ?";}
             }
             catch(Exception e){
                 
             }
             }
             else if ((v.getStatus().equals("Pending For Payment") )&& !(v.getCashier_sms_flag().equals("Y")) && v.getVendorInvoiceDate()!=null)
             {
                 lstParam2.add(df3.format(v.getVendorInvoiceDate()));
                  sms.sendSMS(objSmsVendor, "476832", lstcredential);
                  sql=  " UPDATE sms_sent_tracker set Cashier_SMS_SENT = 'Y',Cashier_SMS_SENT_DATE=SYSDATE WHERE VENDOR_INVOICE_NUMBER = ? AND VENDOR_NUMBER = ? AND APPL_ID = ?";
                   try{
                  String Subject="Invoice processed by Technical at Vendor Invoice Tracking Portal";
                  String MailMessage= "Invoice No." +InvoiceNumber+" has been processed by Accounts on"+v.getVendorInvoiceDate()+"and sent to cashier .Payment will be done as per availability of funds.";
              int success=SendMail.sendmail(VendorMailId,Subject,MailMessage);
              if(success==1)
              { sql=  " UPDATE sms_sent_tracker set Cashier_SMS_SENT = 'Y',Cashier_SMS_SENT_DATE=SYSDATE,Cashier_MAIL_SENT = 'Y',Cashier_MAIL_SENT_DATE=SYSDATE WHERE VENDOR_INVOICE_NUMBER = ? AND VENDOR_NUMBER = ? AND APPL_ID = ?";}
             }
             catch(Exception e){
                 
             }
             }
            
             
                     
                         try {
                       if(sql!=null && sql!=""){
                        PreparedStatement psq = null;
                        conn = ApplicationUtils.getConnection();
                         logger.log(Level.INFO, "GetSendSmsVendorQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

                        psq = conn.prepareStatement(sql.toString());
                        
                        psq.setString(1, v.getMsedclInvoiceNumber());
                     
                         psq.setString(2, v.getVendorNumber());
                           psq.setString(3, v.getApplId());
                        psq.executeUpdate();
                        conn.commit();

                        if (psq != null) {
                            psq.close();
                        }
                       }
                    } catch (SQLException e2) {
                       // e2.printStackTrace();
                    }finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception ignored) {
                }
            }}


              
                }
        }
        }
        catch (Exception ex) {
            logger.log(Level.ERROR, "SendSMS :: run() :: Exception .. " + ex.getMessage());
            //ex.printStackTrace();
        }
}
         }

    

