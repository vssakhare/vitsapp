/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.txnHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.TxnHelper;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.util.ApplicationUtils;
import static in.emp.util.ApplicationUtils.parseStringToDouble;
import in.emp.util.TaxAmountDisplayFromSap;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.bean.VendorStatuBean;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class SaveZhrtLegalFeeTaxDetailsTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(VendorApplFormTxnHelper.class);
      private VendorInputBean vendorBeanObj;
    private VendorPrezData vendorPrezDataObj;
     List<LegalInvoiceInputBean>	legalInvoiceInputList;

    public SaveZhrtLegalFeeTaxDetailsTxnHelper(List legalInvoiceInputList) {
        this.legalInvoiceInputList = legalInvoiceInputList;
    }//constructor ends

    public SaveZhrtLegalFeeTaxDetailsTxnHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
       this.vendorBeanObj = this.vendorPrezDataObj.getVendorInputBean();
    }
    public SaveZhrtLegalFeeTaxDetailsTxnHelper(VendorInputBean vendorBeanObj) {
        this.vendorBeanObj=vendorBeanObj;
    }

    public SaveZhrtLegalFeeTaxDetailsTxnHelper() {
    }

    @Override
    public Object createObject(Connection conn) throws Exception {
        int[] count ;
        PreparedStatement statement = null;
        //PreparedStatement statement1 = null;
        CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        vendorPrezDataObj = new VendorPrezData();
        try {
            logger.log(Level.INFO, "SaveZhrtLegalFeeTaxDetailsTxnHelper ::: createObject() :: method called ::");
            System.out.println("SaveZhrtLegalFeeTaxDetailsTxnHelper ::: createObject() :: method called ::");

            // vendorBeanObj = vendorPrezDataObj.getVendorBean();
            
if(legalInvoiceInputList != null && legalInvoiceInputList.size() >0)
	{
            
            sql.append("INSERT INTO ZHRT_LEGAL_FEE_TAX_DETAILS  ");
            sql.append(" (  TDS_AMOUNT,CGST_AMOUNT,CGST_TDS_AMOUNT,SGST_AMOUNT,SGST_TDS_AMOUNT,IGST_AMOUNT,IGST_TDS_AMOUNT,LIABILITY_DOC_NO,FISCAL_YEAR,CREATED_TIME_STAMP) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?,SYSTIMESTAMP) ");
               statement = conn.prepareStatement(sql.toString());
             for (LegalInvoiceInputBean  legalInvoiceInputBeanObj: legalInvoiceInputList) {
                  try {
               legalInvoiceInputBeanObj = TaxAmountDisplayFromSap.consumeSapWebservice(legalInvoiceInputBeanObj);  
                  
            statement.setDouble(1, parseStringToDouble(legalInvoiceInputBeanObj.getTdsAmount()));
                                
                                    statement.setDouble(2, parseStringToDouble(legalInvoiceInputBeanObj.getCgstAmount()));
                               
                                    statement.setDouble(3, parseStringToDouble(legalInvoiceInputBeanObj.getCgstTdsAmount()));
                              
                                    statement.setDouble(4, parseStringToDouble(legalInvoiceInputBeanObj.getSgstAmount()));
                              
                                    statement.setDouble(5, parseStringToDouble(legalInvoiceInputBeanObj.getSgstTdsAmount()));
                               
                                    statement.setDouble(6, parseStringToDouble(legalInvoiceInputBeanObj.getIgstAmount()));
                               
                                    statement.setDouble(7, parseStringToDouble(legalInvoiceInputBeanObj.getIgstTdsAmount()));
                                
                                
                                statement.setString(8, String.valueOf(legalInvoiceInputBeanObj.getParkPostDocNo()));
                                statement.setString(9, String.valueOf(legalInvoiceInputBeanObj.getFiscalYear()));
            logger.log(Level.INFO, "SaveZhrtLegalFeeTaxDetailsTxnHelper ::: createObject() :: SQL :: " + sql.toString());
           
             statement.addBatch();
              } catch (Exception ex) {
                                            logger.log(Level.ERROR, "SaveZhrtLegalFeeTaxDetailsTxnHelper :: consumeSapWebservice() :: Exception .. " + ex.getMessage());
                                        }
             }
             count = new int[legalInvoiceInputList.size()];

                count = statement.executeBatch();
            System.out.println("count::"+count);
         conn.commit(); 
        }
           

        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
            logger.log(Level.ERROR, "SaveZhrtLegalFeeTaxDetailsTxnHelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        }
return vendorPrezDataObj;
    }

    @Override
    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
          CallableStatement proc_stmt = null;
        try {
            System.out.println("Starting Calling PROC_INSERT_LEGAL_FEE_REPORT");
            logger.log(Level.INFO, "SaveZhrtLegalFeeTaxDetailsTxnHelper ::: updateObject() :: method called ::");

           proc_stmt = conn.prepareCall("{ call PROC_INSERT_LEGAL_FEE_REPORT() }");
        proc_stmt.executeQuery();
        System.out.println("Finished Calling PROC_INSERT_LEGAL_FEE_REPORT");
            logger.log(Level.INFO, "SaveZhrtLegalFeeTaxDetailsTxnHelper ::: updateObject() :: SQL :: " + sql.toString());

            
            
        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
            logger.log(Level.ERROR, "SaveZhrtLegalFeeTaxDetailsTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }
    }

    @Override
    public void deleteObject(Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
