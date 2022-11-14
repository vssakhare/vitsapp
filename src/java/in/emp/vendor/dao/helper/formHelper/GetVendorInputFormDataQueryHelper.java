/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.formHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.vendor.bean.VendorInputBean;
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
public class GetVendorInputFormDataQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorInputFormDataQueryHelper.class);
    private VendorInputBean vendorInputBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetVendorInputFormDataQueryHelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }

    public GetVendorInputFormDataQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorInputBeanObj = this.vendorPrezDataObj.getVendorInputBean();
    }

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    public Object getDataObject(ResultSet results) throws Exception {
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
       
        try {

           
            vendorInputBeanObj.setApplId(results.getString("APPL_ID")); 
            vendorInputBeanObj.setPONumber(results.getString("PO_NUMBER"));
            vendorInputBeanObj.setPODesc(results.getString("PO_DESC"));
            
            vendorInputBeanObj.setVendorInvoiceAmount(results.getString("INVOICE_AMOUNT"));
            vendorInputBeanObj.setVendorInvoiceNumber(results.getString("INVOICE_NUMBER")); 
            vendorInputBeanObj.setSelectedModuleType(results.getString("MODULE_TYPE"));//set to differentiate between project id and po number
            vendorInputBeanObj.setVendorInvoiceDate(results.getDate("INVOICE_DATE"));
            vendorInputBeanObj.setVendorInvoiceResubmit(results.getDate("INVOICE_RESUBMIT_DATE"));
            vendorInputBeanObj.setIsRejectedFlag(results.getString("IS_REJECTED"));
            vendorInputBeanObj.setIsApprovedFlag(results.getString("IS_APPROVED"));
            vendorInputBeanObj.setInwardNumber(results.getString("INWARD_NUMBER"));  
            vendorInputBeanObj.setInwardDate(results.getDate("INWARD_DATE"));
            vendorInputBeanObj.setVendorInwardDate(results.getDate("VENDOR_INWARD_DATE"));
            vendorInputBeanObj.setInvoiceFromDate(results.getDate("INVOICE_FROM_DT"));                    
            vendorInputBeanObj.setInvoiceToDate(results.getDate("INVOICE_TO_DT")); 
            vendorInputBeanObj.setSaveFlag(results.getString("STATUS"));
            vendorInputBeanObj.setDispVendorNumber(results.getString("VENDOR_NUMBER"));
            vendorInputBeanObj.setDispVendorName(results.getString("VENDOR_NAME"));
           vendorInputBeanObj.setReason(results.getString("REASON"));
          vendorInputBeanObj.setInvoiceSubmitDate(results.getDate("INVOICE_SUBMIT_DATE"));
             //vendorInputBeanObj.setTaxAmount(results.getString("TAX_AMOUNT"));
            // vendorInputBeanObj.setPlant_desc(results.getString("PLANT_DESC"));
             vendorInputBeanObj.setOffice_Code(results.getString("OFFICE_CODE"));
               vendorInputBeanObj.setLocation(results.getString("LOCATION"));
           //  vendorInputBeanObj.setSelectedModule(results.getString("MODULE"));
               vendorInputBeanObj.setInvoiceApprDAte(results.getDate("INVOICE_APPROVAL_DATE"));
               vendorInputBeanObj.setInvoiceREjDAte(results.getDate("INVOICE_REJECTION_DATE"));
               vendorInputBeanObj.setInvResubmitCounter(results.getString("INVOICE_SUBMITTED_COUNTER"));
                vendorInputBeanObj.setModuleSaveFlag(results.getString("module_save_flag"));
     vendorInputBeanObj.setTotalPoAmt(results.getString("TOTAL_PO_AMT"));
 vendorInputBeanObj.setBalPoAmt(results.getString("BAL_AMOUNT"));
 vendorInputBeanObj.setSubmitAtPlant(results.getString("SUBMIT_AT_LOCATION"));
 vendorInputBeanObj.setSubmitAtDesc(results.getString("SUBMIT_AT_DESC"));
 vendorInputBeanObj.setPurchasingDesc(results.getString("PURCHASING_DESC"));
  vendorInputBeanObj.setPurchasing_group(results.getString("PURCHASING_GROUP"));
 vendorInputBeanObj.setSELECTEDPLANT(results.getString("SELECTED_PLANT")); 
 vendorInputBeanObj.setForwardToPlant(results.getString("FORWARDED_TO_PLANT")); 
 vendorInputBeanObj.setForwardToDesc(results.getString("FORWARDED_TO_DESC")); 
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorInputFormDataQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInputBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

           sql.append(" SELECT DISTINCT EVIL.APPL_ID, EVIL.PO_NUMBER, EVIL.PO_DESC, EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE,EVIL.module_save_flag,EVIL.SELECTED_PLANT, ");
           sql.append(" EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_DATE, ");         
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_RESUBMIT_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_RESUBMIT_DATE,EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INWARD_DATE, "); 
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_FROM_DT, ");         
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_TO_DT, EVIL.STATUS, ");         
           sql.append(" EVIL.VENDOR_NUMBER, EVIL.VENDOR_NAME ,EVIL.REASON,EVIL.PARENT_OFFICE_CODE,EVIL.OFFICE_CODE,EVIL.LOCATION,EVIL.IS_REJECTED,IS_APPROVED,VENDOR_INWARD_DATE,INVOICE_SUBMIT_DATE,INVOICE_REJECTION_DATE,INVOICE_APPROVAL_DATE,EVIL.INVOICE_SUBMITTED_COUNTER,EVIL.TOTAL_PO_AMT,EVIL.BAL_AMOUNT,EVIL.SUBMIT_AT_LOCATION,EVIL.SUBMIT_AT_DESC,EVIL.PURCHASING_GROUP,EVIL.PURCHASING_DESC,EVIL.FORWARDED_TO_PLANT,EVIL.FORWARDED_TO_DESC  ");
           sql.append(" FROM XXMIS_ERP_VENDOR_INPUT_LIST EVIL  ");
           sql.append(" WHERE EVIL.APPL_ID = ?  ");
           if(vendorInputBeanObj.getUserType().equals("Vendor")) {
           sql.append(" AND TO_NUMBER(EVIL.VENDOR_NUMBER) = ?  "); 
           }

            logger.log(Level.INFO, "GetVendorInputFormDataQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
             
            statement.setString(1, vendorInputBeanObj.getApplId());
            if(vendorInputBeanObj.getUserType().equals("Vendor")) {
            statement.setString(2, vendorInputBeanObj.getVendorNumber());
            }
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorInputFormDataQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

    
}