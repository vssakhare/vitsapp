/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.formHelper;

import in.emp.dao.QueryHelper;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Rikma Rai
 */
public class GetVendorRetentionInputFormDataQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorPsInputFormDataQueryHelper.class);
    private VendorInputBean vendorInputBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetVendorRetentionInputFormDataQueryHelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }

    public GetVendorRetentionInputFormDataQueryHelper(VendorPrezData vendorPrezDataObj) {
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
        VendorInputBean vendorInputBean = new VendorInputBean();
       
        try {

           
           vendorInputBean.setApplId(results.getString("APPL_ID"));
            vendorInputBean.setPODesc(results.getString("PROJECT_ID"));
            vendorInputBean.setSerialNo(results.getString("SERIAL_NO"));
           vendorInputBean.setApplDate(results.getDate("CLAIM_DATE"));
           vendorInputBean.setVendorNumber(results.getString("VENDOR_NUMBER"));
           vendorInputBean.setVendorName(results.getString("VENDOR_NAME"));
           vendorInputBean.setProjectId(results.getString("PROJECT_ID"));
            vendorInputBean.setINVOICE_TYPE(results.getString("INVOICE_TYPE"));
           vendorInputBean.setVendorInvoiceNumber(results.getString("INV_NO"));
           vendorInputBean.setVendorInvoiceDate(results.getDate("INVOICE_DATE"));
            vendorInputBean.setVendorInvoiceAmount(results.getString("GROSS_VALUE"));
             vendorInputBean.setInwardNumber(results.getString("INWARD_NUMBER"));
           vendorInputBean.setInwardDate(results.getDate("INWARD_DATE"));
           vendorInputBean.setMsedclInvoiceNumber(results.getString("INVOICE_NUMBER"));
           vendorInputBean.setWRPST(results.getString("WS_RETENTION_DOC"));
           vendorInputBean.setORPST(results.getString("O_RETENTION_DOC"));
           vendorInputBean.setCRPST(results.getString("CEN_RETENTION_DOC"));
           vendorInputBean.setSRPST(results.getString("CIV_RETENTION_DOC"));
            vendorInputBean.setStatus(results.getString("STATUS"));
            vendorInputBean.setSaveFlag(results.getString("STATUS"));
            vendorInputBean.setSubmitAtPlant(results.getString("SUBMIT_AT_LOCATION"));
 vendorInputBean.setSubmitAtDesc(results.getString("SUBMIT_AT_DESC"));
 vendorInputBean.setSelectedModuleType("PS");
                         
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorPsInputFormDataQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInputBean;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

           sql.append(" select ID , APPL_ID, SERIAL_NO, CLAIM_DATE , VENDOR_NUMBER  , VENDOR_NAME  ,"); 
sql.append("  	PROJECT_ID  , INVOICE_TYPE  , SHORT_TEXT  , INVOICE_NUMBER  , ");
sql.append("  	INVOICE_DATE , GROSS_VALUE , INWARD_NUMBER  , INWARD_DATE , ");
//sql.append("  	WS_RETENTION_DOC  , O_RETENTION_DOC  , CEN_RETENTION_DOC  , ");
sql.append("  	RETENTION_DOC  , TOTAL_AMOUNT ,  INV_NO ,STATUS,SUBMIT_AT_LOCATION,SUBMIT_AT_DESC,PROJECT_DESC from vendor_retention_details ");
        sql.append(" where appl_id=? and rownum=1");
           if(vendorInputBeanObj.getUserType().equals("Vendor")) {
           sql.append(" AND TO_NUMBER(VENDOR_NUMBER) = ?  "); 
           }

            logger.log(Level.INFO, "GetVendorRetentionInputFormDataQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
            System.out.println("vendorInputBeanObj.getApplId()::"+vendorInputBeanObj.getApplId());
            statement = connection.prepareStatement(sql.toString());
             System.out.println("vendor:::::"+vendorInputBeanObj.getVendorNumber());
            statement.setString(1, vendorInputBeanObj.getApplId());
            if(vendorInputBeanObj.getUserType().equals("Vendor")) {
            statement.setString(2, vendorInputBeanObj.getVendorNumber());
            }
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorRetentionInputFormDataQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

    
}
