/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper;

import in.emp.vendor.dao.helper.formHelper.GetVendorInputFormQueryHelper;
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
public class GetInvoicedetailsQueryHelper implements QueryHelper { 

    private static Logger logger = Logger.getLogger(GetVendorInputFormQueryHelper.class); 
    private VendorInputBean vendorInputBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetInvoicedetailsQueryHelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }

    public GetInvoicedetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
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
        VendorInputBean vendorbean = new VendorInputBean();
       
        try {

           vendorbean.setVendorNumber(results.getString("VENDOR_NUMBER"));
           vendorbean.setVendorName(results.getString("VENDOR_NAME"));
           vendorbean.setVendorInvoiceNumber(results.getString("INVOICE_NUMBER"));
           vendorbean.setVendorUpdatedDate(results.getDate("INVOICE_SUBMIT_DATE"));

            

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetInvoicedetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorbean;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
if(vendorInputBeanObj.getSelectedModuleType().equals("PS")){
            sql.append(" select INVOICE_NUMBER,VENDOR_NUMBER,VENDOR_NAME,INVOICE_SUBMIT_DATE ");           
            sql.append(" from XXMIS_ERP_PS_VENDOR_INPUT_LIST ");
            sql.append(" where appl_id = ? ");
}else{
    sql.append(" select INVOICE_NUMBER,VENDOR_NUMBER,VENDOR_NAME,INVOICE_SUBMIT_DATE ");           
            sql.append(" from XXMIS_ERP_VENDOR_INPUT_LIST ");
            sql.append(" where appl_id = ? ");
}
            

            logger.log(Level.INFO, "GetInvoicedetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, vendorInputBeanObj.getApplId());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetInvoicedetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

    
}