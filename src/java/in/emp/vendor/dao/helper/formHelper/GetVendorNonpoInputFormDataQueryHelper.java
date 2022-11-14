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
public class GetVendorNonpoInputFormDataQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorInputFormDataQueryHelper.class);
    private VendorInputBean vendorInputBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetVendorNonpoInputFormDataQueryHelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }

    public GetVendorNonpoInputFormDataQueryHelper(VendorPrezData vendorPrezDataObj) {
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
            vendorInputBeanObj.setZone(results.getString("ZONE"));
            vendorInputBeanObj.setCircle(results.getString("CIRCLE"));
            vendorInputBeanObj.setDivision(results.getString("DIVISION"));
            vendorInputBeanObj.setSubDivision(results.getString("SUBDIVISION"));
           vendorInputBeanObj.setVendorInvoiceAmount(results.getString("INVOICE_AMOUNT"));
            vendorInputBeanObj.setVendorInvoiceNumber(results.getString("INVOICE_NUMBER")); 
            vendorInputBeanObj.setVendorInvoiceDate(results.getDate("INVOICE_DATE"));
            
            vendorInputBeanObj.setInwardNumber(results.getString("INWARD_NUMBER"));  
            vendorInputBeanObj.setInwardDate(results.getDate("INWARD_DATE"));
            vendorInputBeanObj.setVendorInwardDate(results.getDate("VENDOR_INWARD_DATE"));
            vendorInputBeanObj.setInvoiceFromDate(results.getDate("INVOICE_FROM_DT"));                    
            vendorInputBeanObj.setInvoiceToDate(results.getDate("INVOICE_TO_DT")); 
            vendorInputBeanObj.setSaveFlag(results.getString("STATUS"));
            vendorInputBeanObj.setDispVendorNumber(results.getString("VENDOR_NUMBER"));
            vendorInputBeanObj.setDispVendorName(results.getString("VENDOR_NAME"));
          
          vendorInputBeanObj.setInvoiceSubmitDate(results.getDate("INVOICE_SUBMIT_DATE"));
            vendorInputBeanObj.setSelectedModuleType(results.getString("MODULE_TYPE"));//to open the input form of non po invoice
                vendorInputBeanObj.setModuleSaveFlag(results.getString("module_save_flag"));
                         

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

           sql.append(" SELECT DISTINCT EVIL.APPL_ID,EVIL.ZONE,EVIL.CIRCLE,EVIL.DIVISION,EVIL.SUBDIVISION, EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE,EVIL.module_save_flag, ");
           sql.append(" EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_DATE, ");         
           sql.append(" EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INWARD_DATE, "); 
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_FROM_DT, ");         
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_TO_DT, EVIL.STATUS, ");         
           sql.append(" EVIL.VENDOR_NUMBER, EVIL.VENDOR_NAME,VENDOR_INWARD_DATE,INVOICE_SUBMIT_DATE  ");
           sql.append(" FROM ERP_NON_PO_VENDOR_INPUT_LIST EVIL ");
           sql.append(" WHERE EVIL.APPL_ID = ?  ");
           if(vendorInputBeanObj.getUserType().equals("Vendor")) {
           sql.append(" AND TO_NUMBER(EVIL.VENDOR_NUMBER) = ?  "); 
           }

            logger.log(Level.INFO, "GetVendorNonpoInputFormDataQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
             
            statement.setString(1, vendorInputBeanObj.getApplId());
            if(vendorInputBeanObj.getUserType().equals("Vendor")) {
            statement.setString(2, vendorInputBeanObj.getVendorNumber());
            }
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorNonpoInputFormDataQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

    
}