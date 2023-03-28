/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.scheduler;

import in.emp.common.SendMail;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.sms.SmsController;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.SmsDTO;
import in.emp.vendor.manager.VendorManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class SendEmailSmsLegalVendor {

    private static Logger logger = Logger.getLogger(SendSmsLegalVendor.class);
    private static Connection conn = null;

    public static void SendEmailSms() throws Exception {
      //  LinkedList<VendorInputBean> SmsFileList = new LinkedList();
  VendorDelegate vendorMgrObj = new VendorManager();
  LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
   
        try {
              logger.log(Level.INFO, "Legal Vendor sms Scheduler :: run() :: method called .. ");
            System.out.println("\nLegal Vendor sms Scheduler :: run() :: method called ");
          //  SmsFileList = vendorMgrObj.getSmsTrackerList(vendorInputBeanObj);
                List<LegalInvoiceInputBean> legalInvoiceInputList = vendorMgrObj.getLegalEmailSmsTrackerList(legalInvoiceInputBean);
       if(legalInvoiceInputList != null){
         for (LegalInvoiceInputBean v : legalInvoiceInputList) {
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
                lstParam2.add(v.getInvoiceNumber());
                objSmsVendor.setLstParams(lstParam2);
                objSmsVendor.setMobileNumber(v.getMobileNo());
              VendorMailId=v.getEmailId();
              InvoiceNumber=v.getInvoiceNumber();
              int success=0;
//currently designed only for migo as ven inv no is added only while doing migo in sap process
             if(v.getStartPostDocNo() != null &&(v.getStartPostDocNo().equals("16" )) && v.getPayDoneErpDoc() == null && //!v.getCashSmsEmailSent().equals("Y")
                     v.getCashSmsEmailSent()==null)
             {
               //sapStatus="With Cash";
              //lstParam.add(df3.format(UpdatedDate));
                 //lstParam2.add(df3.format(v.getSormDate()));
                 System.out.println("sending sms & email for sapStatus=With Cash");
             try{sms.sendSMS(objSmsVendor, "476831", lstcredential);
                //sql=  " UPDATE XXMIS_ERP_LEGAL_INVOICE_DETAILS set CASH_SMS_EMAIL_SENT = 'Y',CASH_SMS_EMAIL_TIMESTAMP=systimestamp WHERE INVOICE_NUMBER = ? AND VENDOR_NUMBER = ? AND APPL_ID = ?";
             
             String Subject="Invoice is with cash for processing at Vendor Invoice Tracking Portal";
                  String MailMessage="Invoice no. " +InvoiceNumber+" is with cash section for processing.";
             
                 success=SendMail.sendmail(VendorMailId,Subject,MailMessage);
              if(success==1)
              {sql=" UPDATE XXMIS_ERP_LEGAL_INVOICE_DETAILS set CASH_SMS_EMAIL_SENT = 'Y',CASH_SMS_EMAIL_TIMESTAMP=systimestamp WHERE INVOICE_NUMBER = ? AND VENDOR_NUMBER = ? AND APPL_ID = ?";}
             
             }catch(Exception e){
                 
             }
                  
             
             }
             else if (v.getStartPostDocNo() != null && (v.getStartPostDocNo().equals("16" ))&& v.getStartPayDoneErpDoc() !=null && v.getStartPayDoneErpDoc().equals("17") && v.getPaySmsEmailSent()==null)
             {
             //sapStatus="Payment Done";
                 lstParam2.add(df3.format(v.getInvoiceDate()));
                try{sms.sendSMS(objSmsVendor, "476831", lstcredential);
                //sql=  " UPDATE XXMIS_ERP_LEGAL_INVOICE_DETAILS set SMS_SENT = 'YY' WHERE INVOICE_NUMBER = ? AND VENDOR_NUMBER = ? AND APPL_ID = ?";
             String Subject="Invoice payment is done at Vendor Invoice Tracking Portal";
                  String MailMessage="For invoice no. " +InvoiceNumber+","+v.getFeeType()+" payment has been done.";
             
              success=SendMail.sendmail(VendorMailId,Subject,MailMessage);
              if(success==1)
              {sql= " UPDATE XXMIS_ERP_LEGAL_INVOICE_DETAILS set PAY_SMS_EMAIL_SENT = 'Y',PAY_SMS_EMAIL_TIMESTAMP=systimestamp WHERE INVOICE_NUMBER = ? AND VENDOR_NUMBER = ? AND APPL_ID = ?";}
              
              }catch(Exception e){
                 
             }
                  
             }
             
             
            
             else if(v.getStartPostDocNo() != null && (v.getStartPostDocNo().equals("16" )) && v.getStartPayDoneErpDoc() !=null && v.getStartPayDoneErpDoc1().equals("020") && v.getPayAdjSmsEmailSent()==null)
             {
             //sapStatus="Payment Adjusted"; 
             try{sms.sendSMS(objSmsVendor, "476831", lstcredential);
                //sql=  " UPDATE XXMIS_ERP_LEGAL_INVOICE_DETAILS set SMS_SENT = 'YYY' WHERE INVOICE_NUMBER = ? AND VENDOR_NUMBER = ? AND APPL_ID = ?";
             String Subject="Invoice payment is adjusted at Vendor Invoice Tracking Portal";
                  String MailMessage="For invoice no. " +InvoiceNumber+","+v.getFeeType()+" payment has been adjusted.";
             
              success=SendMail.sendmail(VendorMailId,Subject,MailMessage);
              if(success==1)
              {sql= " UPDATE XXMIS_ERP_LEGAL_INVOICE_DETAILS set PAY_ADJ_SMS_EMAIL_SENT = 'Y',PAY_ADJ_SMS_EMAIL_TIMESTAMP=systimestamp WHERE INVOICE_NUMBER = ? AND VENDOR_NUMBER = ? AND APPL_ID = ?";}
              
               }catch(Exception e){
                 
             }
                  
                     
             }
             else if(v.getStartPostDocNo() != null && (v.getStartPostDocNo().equals("16" )) && v.getStartPayDoneErpDoc() !=null && v.getStartPayDoneErpDoc1().equals("12") && v.getPayDocSmsEmailSent()==null)
             {
             //sapStatus="Payment Document Reversed"; 
             try{sms.sendSMS(objSmsVendor, "476831", lstcredential);
                //sql=  " UPDATE XXMIS_ERP_LEGAL_INVOICE_DETAILS set SMS_SENT = 'YYYY' WHERE INVOICE_NUMBER = ? AND VENDOR_NUMBER = ? AND APPL_ID = ?";
              String Subject="Invoice payment document is reversed at Vendor Invoice Tracking Portal";
                  String MailMessage="For invoice no. " +InvoiceNumber+","+v.getFeeType()+" payment document has been reversed.";
                success=SendMail.sendmail(VendorMailId,Subject,MailMessage);
              if(success==1)
              {sql= " UPDATE XXMIS_ERP_LEGAL_INVOICE_DETAILS set PAY_DOC_REVRSD_SMS_EMAIL_SENT = 'Y',PAY_DOC_REVRSD_SMS_EMAIL_TIMESTAMP=systimestamp WHERE INVOICE_NUMBER = ? AND VENDOR_NUMBER = ? AND APPL_ID = ?";}
             
             
             }catch(Exception e){
                 
             } 
                  
             
                      
             }
             try {
                       if(sql!=null && sql!=""){
                        PreparedStatement psq = null;
                        conn = ApplicationUtils.getConnection();
                         logger.log(Level.INFO, "GetSendSmsVendorQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
                        psq = conn.prepareStatement(sql.toString());                        
                        psq.setString(1, v.getInvoiceNumber());                     
                        psq.setString(2, v.getVendorNumber());
                        psq.setString(3, String.valueOf(v.getApplId()));
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