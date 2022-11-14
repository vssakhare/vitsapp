/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.formHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.dao.helper.listHelper.GetVendorListQueryHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja
 */
public class getVendorVerifiedFormQueryHelper implements QueryHelper { 

    private static Logger logger = Logger.getLogger(GetVendorListQueryHelper.class);
    private VendorBean vendorBean;
    private VendorPrezData vendorPrezDataObj;

    public getVendorVerifiedFormQueryHelper(VendorBean vendorBean) { 
        this.vendorBean = vendorBean;
    }

    public getVendorVerifiedFormQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorBean = this.vendorPrezDataObj.getVendorBean();
    }

    public Object getDataObject(ResultSet results) throws Exception {
        VendorBean vendorBeanObj = new VendorBean();
        try {   
           
            logger.log(Level.INFO, "getVendorVerifiedFormQueryHelper ::: getDataObject() :: method called ::");
           if(vendorBean.getSES_MIGO_INV_TYPE().equals("SES")){
          vendorBeanObj.setSesNum(results.getString("SES_NO"));
              vendorBeanObj.setSesAmt(results.getString("SES_AMOUNT"));
           vendorBeanObj.setSESDate(results.getDate("SES_DOC_DATE"));
                    vendorBeanObj.setSES_STATUS(results.getString("SES_STATUS"));
           }
                if(vendorBean.getSES_MIGO_INV_TYPE().equals("MIGO")){
                 vendorBeanObj.setMigo_doc(results.getString("MIGO_DOC"));
            vendorBeanObj.setMigo_dt(results.getDate("MIGO_DT"));
            vendorBeanObj.setMigo_amt(results.getString("MIGO_AMT"));
            vendorBeanObj.setMIGO_STATUS(results.getString("MIGO_STATUS"));
                }
                   if(vendorBean.getSES_MIGO_INV_TYPE().equals("INVOICE")){
              vendorBeanObj.setMsedclInvoiceNumber(results.getString("MSEDCL_INV_NO"));
             vendorBeanObj.setInvoiceDate(results.getDate("INV_DT"));
             vendorBeanObj.setInvoiceAmount(results.getString("INV_AMT"));
             vendorBeanObj.setINV_STATUS(results.getString("INV_STATUS"));
                   }
                       if(vendorBean.getSES_MIGO_INV_TYPE().equals("PAYMENT")){
           vendorBeanObj.setCLDocDt(results.getDate("CL_DOC_DT"));
           vendorBeanObj.setCLDocNo(results.getString("CL_DOC_NO"));
           vendorBeanObj.setPaid(results.getString("PAID_AMT"));
                   vendorBeanObj.setPAYMENT_STATUS(results.getString("PAYMENT_STATUS"));
                       }
  
            
            
     
                       
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, "getVendorVerifiedFormQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i = 1; 
        try {
            logger.log(Level.INFO, "getVendorVerifiedFormQueryHelper ::: getQueryResults() :: method called ::");

            if(vendorBean.getSES_MIGO_INV_TYPE().equals("SES")){
        sql.append("SELECT SES_NO,SES_DOC_DATE,SES_AMOUNT,SES_STATUS   ");
           sql.append(" from XXMIS_ERP_VENDOR_LIST  EVL ");             
           sql.append(" WHERE TO_NUMBER(EVL.VENDOR_NUMBER) = ? ");
            if(!ApplicationUtils.isBlank(vendorBean.getApplId())){
           sql.append(" AND EVL.appl_id = ? ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
           sql.append(" AND EVL.PO_NUMBER = ? ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
           sql.append(" AND  REGEXP_REPLACE(UPPER(EVL.INVOICE_NUMBER), '[^0-9A-Za-z]', '')  = ? AND ses_no is not null and ses_no not like 'Pending'");
          }
         logger.log(Level.INFO, "getVendorVerifiedFormQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
        statement = connection.prepareStatement(sql.toString());
        statement.setString(i++, vendorBean.getVendorNumber());
        if(!ApplicationUtils.isBlank(vendorBean.getApplId())){
        statement.setString(i++, vendorBean.getApplId());    
          } 
      
       if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
      statement.setString(i++, vendorBean.getPONumber());    
       }
       if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
      statement.setString(i++, vendorBean.getVendorInvoiceNumber());  
       }
       }
                if(vendorBean.getSES_MIGO_INV_TYPE().equals("MIGO")){
        sql.append(" SELECT CASE WHEN MIGO_DOC IS NOT NULL THEN CONCAT(CONCAT(MIGO_DOC,'/'),DOC_YEAR) ELSE NULL END  MIGO_DOC,MIGO_DT,MIGO_AMT,MIGO_STATUS   ");
           sql.append(" from XXMIS_ERP_VENDOR_LIST EVL ");             
           sql.append(" WHERE TO_NUMBER(EVL.VENDOR_NUMBER) = ? ");
            if(!ApplicationUtils.isBlank(vendorBean.getApplId())){
           sql.append(" AND EVL.appl_id = ? ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
           sql.append(" AND EVL.PO_NUMBER = ? ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
           sql.append(" AND  REGEXP_REPLACE(UPPER(EVL.INVOICE_NUMBER), '[^0-9A-Za-z]', '')  = ? AND MIGO_DOC is not null and MIGO_DOC not like 'Pending'");
          }
         logger.log(Level.INFO, "getVendorVerifiedFormQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
        statement = connection.prepareStatement(sql.toString());
              statement.setString(i++, vendorBean.getVendorNumber());
        if(!ApplicationUtils.isBlank(vendorBean.getApplId())){
        statement.setString(i++, vendorBean.getApplId());    
          } 

       if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
      statement.setString(i++, vendorBean.getPONumber());    
       }
       if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
      statement.setString(i++, vendorBean.getVendorInvoiceNumber());  
       }
       }
                    if(vendorBean.getSES_MIGO_INV_TYPE().equals("INVOICE")){
        sql.append("  SELECT DISTINCT MSEDCL_INV_NO,INV_DT,INV_AMT,INV_STATUS   ");
           sql.append(" from XXMIS_ERP_VENDOR_LIST  EVL ");             
           sql.append(" WHERE TO_NUMBER(EVL.VENDOR_NUMBER) = ? ");
            if(!ApplicationUtils.isBlank(vendorBean.getApplId())){
           sql.append(" AND EVL.appl_id = ? ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
           sql.append(" AND EVL.PO_NUMBER = ? ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
           sql.append(" AND  REGEXP_REPLACE(UPPER(EVL.INVOICE_NUMBER), '[^0-9A-Za-z]', '')  = ? AND MSEDCL_INV_NO is not null and MSEDCL_INV_NO not like 'Pending'");
          }
         logger.log(Level.INFO, "getVendorVerifiedFormQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
        statement = connection.prepareStatement(sql.toString());
         statement.setString(i++, vendorBean.getVendorNumber());
        if(!ApplicationUtils.isBlank(vendorBean.getApplId())){
        statement.setString(i++, vendorBean.getApplId());    
          } 
     
       if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
      statement.setString(i++, vendorBean.getPONumber());    
       }
       if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
      statement.setString(i++, vendorBean.getVendorInvoiceNumber());  
       }
       }
      if( vendorBean.getSES_MIGO_INV_TYPE().equals("PAYMENT")){
        sql.append(" SELECT DISTINCT CL_DOC_NO, CL_DOC_DT,PAID_AMT,PAYMENT_STATUS   ");
           sql.append(" from XXMIS_ERP_VENDOR_LIST EVL ");             
           sql.append(" WHERE TO_NUMBER(EVL.VENDOR_NUMBER) = ? ");
            if(!ApplicationUtils.isBlank(vendorBean.getApplId())){
           sql.append(" AND EVL.appl_id = ? ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
           sql.append(" AND EVL.PO_NUMBER = ? ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
           sql.append(" AND  REGEXP_REPLACE(UPPER(EVL.INVOICE_NUMBER), '[^0-9A-Za-z]', '')  = ? AND CL_DOC_NO is not null and CL_DOC_NO not like 'Pending'");
          }
         logger.log(Level.INFO, "getVendorVerifiedFormQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
        statement = connection.prepareStatement(sql.toString());
        statement.setString(i++, vendorBean.getVendorNumber());
        if(!ApplicationUtils.isBlank(vendorBean.getApplId())){
        statement.setString(i++, vendorBean.getApplId());    
          } 
      
       if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
      statement.setString(i++, vendorBean.getPONumber());    
       }
       if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
      statement.setString(i++, vendorBean.getVendorInvoiceNumber());  
       }
       }
      rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "getVendorVerifiedFormQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        
        return rs;
    }
}
