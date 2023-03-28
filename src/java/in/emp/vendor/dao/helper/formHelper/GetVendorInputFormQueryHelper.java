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
public class GetVendorInputFormQueryHelper implements QueryHelper { 

    private static Logger logger = Logger.getLogger(GetVendorInputFormQueryHelper.class); 
    private VendorInputBean vendorInputBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetVendorInputFormQueryHelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }

    public GetVendorInputFormQueryHelper(VendorPrezData vendorPrezDataObj) {
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

           vendorbean.setVendorNumber(results.getString("VENDOR_CODE"));
           vendorbean.setVendorName(results.getString("VENDOR_NAME"));
           vendorbean.setContactNumber(results.getString("PHN_LL"));
           vendorbean.setEmailId(results.getString("EMAIL"));
            

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorInputFormQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorbean;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

            sql.append(" SELECT EVM.VENDOR_CODE, EVM.VENDOR_NAME,EVM.PHN_LL,EVM.EMAIL ");           
            sql.append(" FROM ERP_VENDOR_MASTER EVM ");
            sql.append(" WHERE EVM.VENDOR_CODE = ? ");

            logger.log(Level.INFO, "GetVendorInputFormQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, vendorInputBeanObj.getVendorNumber());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorInputFormQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

    
}