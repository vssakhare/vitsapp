/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.scheduler;

import in.emp.common.ApplicationConstants;
import in.emp.common.SendMail;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.sms.SmsController;
import in.emp.sms.bean.TemplateIdBean;
import in.emp.util.ApplicationUtils;
import static in.emp.util.ApplicationUtils.parseStringToDouble;
import in.emp.util.TaxAmountDisplayFromSap;
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

    private static Logger logger = Logger.getLogger(SendEmailSmsLegalVendor.class);
    private static Connection conn = null;

    public static void SendEmailSms() throws Exception {

        VendorDelegate vendorMgrObj = new VendorManager();
        LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();

        try {
            logger.log(Level.INFO, "Legal Vendor sms Scheduler :: run() :: method called .. ");
            System.out.println("\nLegal Vendor sms Scheduler :: run() :: method called ");
            List<LegalInvoiceInputBean> legalInvoiceInputList = vendorMgrObj.getLegalEmailSmsTrackerList();
            if (legalInvoiceInputList != null) {
                for (LegalInvoiceInputBean v : legalInvoiceInputList) {
                    String sql = "";
                    String VendorMailId = "";
                    String InvoiceNumber = "";
                    DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
                    VendorMailId = v.getEmailId();
                    InvoiceNumber = v.getInvoiceNumber();
                    int success = 0;
                    if (v.getStatusFee() != null && v.getStatusFee().equalsIgnoreCase("Submitted") && v.getParkPostDocNo() == null) {
                        //sapStatus="With Accounts";
                        if (v.getAccSmsEmailSent() == null) {
                            try {
                                String Subject = "Invoice is with accounts for processing at Vendor Invoice Tracking Portal";
                                String MailMessage = "Invoice no. " + InvoiceNumber + " is with account section for processing.";

                                success = SendMail.sendmail(VendorMailId, Subject, MailMessage);
                                if (success == 1) {
                                    sql = " UPDATE XXMIS_ERP_LEGAL_INVOICE_FEE_TYPE_DTLS set ACC_SMS_EMAIL_SENT = 'Y',ACC_SMS_EMAIL_TIMESTAMP=systimestamp,SAP_STATUS='With Accounts' WHERE APPL_ID = ? AND FEE_TYPE=?";
                                }

                            } catch (Exception e) {

                            }
                        }
                    } else if (v.getStartPostDocNo() != null && (v.getStartPostDocNo().equals("16")) && v.getPayDoneErpDoc() == null) {
                        if (v.getCashSmsEmailSent() == null) //sapStatus="With Cash";
                        {
                            System.out.println("sending sms & email for sapStatus=With Cash");
                            try {
                                String Subject = "Invoice is with cash for processing at Vendor Invoice Tracking Portal";
                                String MailMessage = "Invoice no. " + InvoiceNumber + " is with cash section for processing.";

                                success = SendMail.sendmail(VendorMailId, Subject, MailMessage);
                                if (success == 1) {
                                    sql = " UPDATE XXMIS_ERP_LEGAL_INVOICE_FEE_TYPE_DTLS set CASH_SMS_EMAIL_SENT = 'Y',CASH_SMS_EMAIL_TIMESTAMP=systimestamp,SAP_STATUS='With Cash' WHERE APPL_ID = ? AND FEE_TYPE=?";
                                }

                            } catch (Exception e) {

                            }
                        }

                    } else if (v.getStartPostDocNo() != null && (v.getStartPostDocNo().equals("16")) && v.getStartPayDoneErpDoc() != null && v.getStartPayDoneErpDoc().equals("17")) {
                        //sapStatus="Payment Done";
                        if (v.getPaySmsEmailSent() == null) {
                            try {
                                String Subject = "Invoice payment is done at Vendor Invoice Tracking Portal";
                                String MailMessage = "For invoice no. " + InvoiceNumber + "," + v.getFeeType() + " payment has been done.";

                                success = SendMail.sendmail(VendorMailId, Subject, MailMessage);
                             //   if (success == 1) 
                             {
                                   
                                        try {
                                           
                                            v = TaxAmountDisplayFromSap.consumeSapWebservice(v);
                                             sql = " UPDATE XXMIS_ERP_LEGAL_INVOICE_FEE_TYPE_DTLS set PAY_SMS_EMAIL_SENT = 'Y',PAY_SMS_EMAIL_TIMESTAMP=systimestamp,SAP_STATUS='Payment Done',TDS_AMOUNT = ?,CGST_AMOUNT = ?,CGST_TDS_AMOUNT = ?,SGST_AMOUNT = ?,SGST_TDS_AMOUNT = ?,IGST_AMOUNT = ?,IGST_TDS_AMOUNT = ?\n"
                                            + " WHERE APPL_ID = ? AND FEE_TYPE = ?";
                                        } catch (Exception ex) {
                                            logger.log(Level.ERROR, "SendEmailSmsLegalVendor :: run() :: Exception .. " + ex.getMessage());
                                        }

                                }

                            } catch (Exception e) {

                            }
                        }

                    } else if (v.getStartPostDocNo() != null && (v.getStartPostDocNo().equals("16")) && v.getStartPayDoneErpDoc() != null && v.getStartPayDoneErpDoc1().equals("020")) {
                        //sapStatus="Payment Adjusted"; 
                        if (v.getPayAdjSmsEmailSent() == null) {
                            try {
                                String Subject = "Invoice payment is adjusted at Vendor Invoice Tracking Portal";
                                String MailMessage = "For invoice no. " + InvoiceNumber + "," + v.getFeeType() + " payment has been adjusted.";

                                success = SendMail.sendmail(VendorMailId, Subject, MailMessage);
                                 if(success==1)
                                {
                                    sql = " UPDATE XXMIS_ERP_LEGAL_INVOICE_FEE_TYPE_DTLS set PAY_ADJ_SMS_EMAIL_SENT = 'Y',PAY_ADJ_SMS_EMAIL_TIMESTAMP=systimestamp,SAP_STATUS='Payment Adjusted' WHERE APPL_ID = ? AND FEE_TYPE=?";
                                }

                            } catch (Exception e) {

                            }
                        }

                    } else if (v.getStartPostDocNo() != null && (v.getStartPostDocNo().equals("16")) && v.getStartPayDoneErpDoc() != null && v.getStartPayDoneErpDoc1().equals("12")) {
                        //sapStatus="Payment Document Reversed"; 
                        if (v.getPayDocSmsEmailSent() == null) {
                            try {
                                String Subject = "Invoice payment document is reversed at Vendor Invoice Tracking Portal";
                                String MailMessage = "For invoice no. " + InvoiceNumber + "," + v.getFeeType() + " payment document has been reversed.";
                                success = SendMail.sendmail(VendorMailId, Subject, MailMessage);
                                 if(success==1)
                                {
                                    sql = " UPDATE XXMIS_ERP_LEGAL_INVOICE_FEE_TYPE_DTLS set PAY_DOC_REVRSD_SMS_EMAIL_SENT = 'Y',PAY_DOC_REVRSD_SMS_EMAIL_TIMESTAMP=systimestamp,SAP_STATUS='Payment Document Reversed' WHERE APPL_ID = ? AND FEE_TYPE=?";
                                }

                            } catch (Exception e) {

                            }
                        }

                    } else {
                        if (v.getTechnicalUpdated() == null) {
                            //sapStatus="With Technical";  
                            sql = " UPDATE XXMIS_ERP_LEGAL_INVOICE_FEE_TYPE_DTLS set TECHNICAL_UPDATED = 'Y',TECHNICAL_UPDATED_TIMESTAMP=systimestamp,SAP_STATUS='With Technical' WHERE APPL_ID = ? AND FEE_TYPE=?";
                        }

                    }
                    try {
                        if (sql != null && sql != "") {
                            PreparedStatement psq = null;
                            conn = ApplicationUtils.getConnection();
                            logger.log(Level.INFO, "GetSendSmsVendorQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
                            psq = conn.prepareStatement(sql);
                            if (v.getStartPostDocNo() != null && (v.getStartPostDocNo().equals("16")) && v.getStartPayDoneErpDoc() != null && v.getStartPayDoneErpDoc().equals("17") && v.getPaySmsEmailSent() == null) {
                               
                                    psq.setDouble(1, parseStringToDouble(v.getTdsAmount()));
                                
                                    psq.setDouble(2, parseStringToDouble(v.getCgstAmount()));
                               
                                    psq.setDouble(3, parseStringToDouble(v.getCgstTdsAmount()));
                              
                                    psq.setDouble(4, parseStringToDouble(v.getSgstAmount()));
                              
                                    psq.setDouble(5, parseStringToDouble(v.getSgstTdsAmount()));
                               
                               
                                    psq.setDouble(6, parseStringToDouble(v.getIgstAmount()));
                               
                                
                                    psq.setDouble(7, parseStringToDouble(v.getIgstTdsAmount()));
                                
                                
                                psq.setString(8, String.valueOf(v.getApplId()));
                                psq.setString(9, String.valueOf(v.getFeeType()));
                            } else {
                                psq.setString(1, String.valueOf(v.getApplId()));
                                psq.setString(2, String.valueOf(v.getFeeType()));
                            }
                           psq.executeUpdate();
                            conn.commit();

                            if (psq != null) {
                                psq.close();
                            }
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
            logger.log(Level.ERROR, "SendEmailSmsLegalVendor :: run() :: Exception .. " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
