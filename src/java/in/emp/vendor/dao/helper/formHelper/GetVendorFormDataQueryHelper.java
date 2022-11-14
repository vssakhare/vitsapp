/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.formHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.util.ApplicationUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class GetVendorFormDataQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorFormDataQueryHelper.class);
    private VendorBean vendorBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetVendorFormDataQueryHelper(VendorBean vendorBeanObj) {
        this.vendorBeanObj = vendorBeanObj; 
    }

    public GetVendorFormDataQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorBeanObj = this.vendorPrezDataObj.getVendorBean();
    }

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    public Object getDataObject(ResultSet results) throws Exception {
        VendorBean vendorbean = new VendorBean();
       
        try {

           
            vendorBeanObj.setZone(results.getString("ZONE")); 
            vendorBeanObj.setCircle(results.getString("CIRCLE")); 
            vendorBeanObj.setDivision(results.getString("DIVISION"));             
            vendorBeanObj.setPONumber(results.getString("PO_NUMBER"));
            vendorBeanObj.setPOType(results.getString("PO_TYPE"));
            vendorBeanObj.setPODesc(results.getString("PO_DESC"));            
            vendorBeanObj.setPOCreationDate(results.getDate("PO_CREATION_DATE"));
            vendorBeanObj.setValidityStart(results.getDate("VALIDITY_START"));                    
            vendorBeanObj.setValidityEnd(results.getDate("VALIDITY_END")); 
            vendorBeanObj.setVendorInvoiceNumber(results.getString("VENDOR_INV_NO"));
            vendorBeanObj.setMsedclInvoiceNumber(results.getString("MSEDCL_INV_NO"));
            vendorBeanObj.setInvoiceAmount(results.getString("INVOICE_AMOUNT"));
            vendorBeanObj.setPaidAmt(results.getString("PAID_AMT"));            
            vendorBeanObj.setSaveFlag(results.getString("STATUS"));
            vendorBeanObj.setInvoiceDate(results.getDate("INVOICE_DATE")); 
            vendorBeanObj.setSESDate(results.getDate("SESDATE")); 
            vendorBeanObj.setSesNum(results.getString("SESNUM"));
            vendorBeanObj.setSesAmt(results.getString("SESAMT"));
            vendorBeanObj.setBalAmt(results.getString("BAL_AMOUNT"));
            vendorBeanObj.setTotalPOAmt(results.getString("TOTAL_PO_AMT"));
            vendorBeanObj.setProfitCenter(results.getString("PROFIT_CENTER"));
            vendorBeanObj.setDispVendorNumber(results.getString("VENDOR_NUMBER"));
            vendorBeanObj.setDispVendorName(results.getString("VENDOR_NAME"));
               vendorBeanObj.setMigo_dt(results.getDate("MIGODATE")); 
            vendorBeanObj.setMigo_doc(results.getString("MIGONUM"));
            vendorBeanObj.setMigo_amt(results.getString("MIGOAMT"));
            vendorBeanObj.setCLDocDt(results.getDate("CL_DOC_DT")); 
            vendorBeanObj.setSESDesc(results.getString("SES_DESC"));
            vendorBeanObj.setCLDocNo(results.getString("CL_DOC_NO"));
            
            vendorBeanObj.setInvDt(results.getDate("LIABILITY_DATE"));
            vendorBeanObj.setTaxCode(results.getString("TAX_CODE"));
             vendorBeanObj.setTaxAmount(results.getString("TAX_AMOUNT"));
           
            vendorBeanObj.setItTdsAmount(results.getString("IT_TDS_AMOUNT"));
             vendorBeanObj.setGstTds(results.getString("GST_TDS"));
            vendorBeanObj.setRetensionAmount(results.getString("RETENSION_AMOUNT"));
           // vendorBeanObj.setProjectCode(results.getString("PROJECT_CODE"));
          //  vendorBeanObj.setProjectScheme(results.getString("PROJECT_SCHEME"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorFormDataQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

                 if(vendorBeanObj.getUserType().equals("Vendor")) {
            sql.append(" SELECT PS.ZONE, PS.CIRCLE, PS.DIVISION, PS.PO_NUMBER, PS.PO_TYPE, ");
            sql.append("  PS.PO_DESC, TO_DATE(TO_CHAR (PS.PO_CREATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PO_CREATION_DATE, ");
            sql.append(" PS.VALIDITY_START, PS.VALIDITY_END,  PS.VENDOR_INV_NO, case when ps_inv_no is not null then ps_inv_no else msedcl_inv_no end as MSEDCL_INV_NO, ");
            sql.append(" PS.INV_AMT INVOICE_AMOUNT, PS.PAID_AMT, ");
            sql.append(" nvl(PS.STATUS,'Technical')STATUS , ");
            sql.append("  PS.INVOICE_DATE, ");
            sql.append(" PS.SES_NO SESNUM,TO_DATE(TO_CHAR (PS.SES_CREATE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') SESDATE, "); 
            sql.append(" PS.SES_AMOUNT SESAMT,  "); 
            sql.append(" PS.MIGO_DOC MIGONUM,TO_DATE(TO_CHAR (PS.MIGO_DT,'DD-MM-YYYY'),'DD-MM-YYYY') MIGODATE, "); 
            sql.append(" PS.MIGO_AMT MIGOAMT,  "); 
            sql.append(" PS.BAL_AMOUNT,PS.TOTAL_PO_AMT, SUBSTR(PROFIT_CENTER, 7,10 ) AS PROFIT_CENTER, PS.VENDOR_NUMBER, PS.VENDOR_NAME, "); 
            sql.append(" TO_DATE(TO_CHAR (PS.CL_DOC_DT,'DD-MM-YYYY'),'DD-MM-YYYY') CL_DOC_DT, PS.SES_DESC SES_DESC, PS.CL_DOC_NO, PS.INV_DT LIABILITY_DATE,PS.TAX_CODE TAX_CODE,PS.TAX_AMOUNT TAX_AMOUNT, ");
            sql.append("PS.IT_TDS_AMOUNT IT_TDS_AMOUNT,PS.GST_TDS GST_TDS,PS.RETENSION_AMOUNT RETENSION_AMOUNT "); 
            sql.append(" FROM XXMIS_ERP_VENDOR_LIST PS "); 
       
          
            sql.append(" WHERE   TO_NUMBER(PS.VENDOR_NUMBER) = ? ");
            if(!ApplicationUtils.isBlank(vendorBeanObj.getInvoiceNumber())){
              sql.append(" AND nvl(invoice_number, NVL(PS.VENDOR_INV_NO,NVL(PS.SES_VEN_INV_NO,PS.MIGO_VEN_INV_NO)))= ? ");
             //to get vendor invoice number from sap table based on nvl of ses ->migo-> msedcl inv no -> vendor inv no from vendor dtl table

            }
            if(!ApplicationUtils.isBlank(vendorBeanObj.getPONumber()))
            {
                sql.append(" AND PO_NUMBER = ? ");
            }
             if(!ApplicationUtils.isBlank(vendorBeanObj.getSesNum()))
             {
            sql.append(" AND (ses_no = ?  or MIGO_DOC =  ?) ");
             }
             logger.log(Level.INFO, "GetVendorFormDataQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
               statement.setString(1, vendorBeanObj.getVendorNumber());
           if(!ApplicationUtils.isBlank(vendorBeanObj.getInvoiceNumber())){
            statement.setString(2, vendorBeanObj.getInvoiceNumber());  
     
           } if(!ApplicationUtils.isBlank(vendorBeanObj.getPONumber())){
            statement.setString(3, vendorBeanObj.getPONumber());  
     
           }
            if(!ApplicationUtils.isBlank(vendorBeanObj.getSesNum()))
             {statement.setString(4, vendorBeanObj.getSesNum());  
             statement.setString(5, vendorBeanObj.getSesNum()); 
             }
            }
             if(vendorBeanObj.getUserType().equals("Emp")) { 
                 sql.append(" SELECT PS.ZONE, PS.CIRCLE, PS.DIVISION, PS.PO_NUMBER, PS.PO_TYPE, ");
            sql.append("  PS.PO_DESC, TO_DATE(TO_CHAR (PS.PO_CREATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PO_CREATION_DATE, ");
            sql.append(" PS.VALIDITY_START, PS.VALIDITY_END,  PS.VENDOR_INV_NO, case when ps_inv_no is not null then ps_inv_no else msedcl_inv_no end as MSEDCL_INV_NO, ");
            sql.append(" PS.INV_AMT INVOICE_AMOUNT, PS.PAID_AMT, ");
            sql.append(" (CASE WHEN NVL(PS.SES_NO, PS.MIGO_DOC) IN 'Pending' THEN 'Pending With Technical'  ");
            sql.append(" WHEN PS.MSEDCL_INV_NO LIKE 'Pending' THEN 'Pending With Accounts' ");
            sql.append(" WHEN PS.CL_DOC_NO IN 'Pending' THEN 'Pending For Payment' ");
            sql.append(" WHEN PS.CL_DOC_NO NOT IN 'Pending' THEN 'Paid' END) STATUS, PS.INVOICE_DATE, ");
                sql.append(" PS.SES_NO SESNUM,TO_DATE(TO_CHAR (PS.SES_CREATE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') SESDATE, "); 
            sql.append(" PS.SES_AMOUNT SESAMT,  "); 
            sql.append(" PS.MIGO_DOC MIGONUM,TO_DATE(TO_CHAR (PS.MIGO_DT,'DD-MM-YYYY'),'DD-MM-YYYY') MIGODATE, "); 
            sql.append(" PS.MIGO_AMT MIGOAMT,PS.BAL_AMOUNT,  "); 
            sql.append(" PS.TOTAL_PO_AMT, SUBSTR(PROFIT_CENTER, 7,10 ) AS PROFIT_CENTER, PS.VENDOR_NUMBER, PS.VENDOR_NAME, "); 
            sql.append(" TO_DATE(TO_CHAR (PS.CL_DOC_DT,'DD-MM-YYYY'),'DD-MM-YYYY') CL_DOC_DT, PS.SES_DESC SES_DESC, PS.CL_DOC_NO, PS.INV_DT LIABILITY_DATE,PS.TAX_CODE TAX_CODE,PS.TAX_AMOUNT TAX_AMOUNT, ");
            sql.append("PS.IT_TDS_AMOUNT IT_TDS_AMOUNT,PS.GST_TDS GST_TDS,PS.RETENSION_AMOUNT RETENSION_AMOUNT,PS.PROJECT_CODE PROJECT_CODE,PS.PROJECT_SCHEME PROJECT_SCHEME "); 
            sql.append(" FROM PO_STATUS_NEW PS "); 
                 sql.append(" WHERE   TO_NUMBER(PS.VENDOR_NUMBER) = ? ");
                 if(!ApplicationUtils.isBlank(vendorBeanObj.getSesNum())){
                 sql.append(" AND (ses_no = ?  or MIGO_DOC =  ?) ");
                 }
                  else
            {
                sql.append(" WHERE PO_NUMBER = ? ");
            }
            
logger.log(Level.INFO, "GetVendorFormDataQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
         
            
                 statement.setString(1, vendorBeanObj.getVendorNumber());
           if(!ApplicationUtils.isBlank(vendorBeanObj.getSesNum())){
            statement.setString(2, vendorBeanObj.getSesNum());  
            statement.setString(3, vendorBeanObj.getSesNum()); 
           }
           else{
                statement.setString(2, vendorBeanObj.getPONumber());  
            }
        }
    
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorFormDataQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

    
}