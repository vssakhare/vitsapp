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
public class GetVendorFormQueryHelper implements QueryHelper { 

    private static Logger logger = Logger.getLogger(GetVendorFormQueryHelper.class); 
    private VendorBean vendorBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetVendorFormQueryHelper(VendorBean vendorBeanObj) {
        this.vendorBeanObj = vendorBeanObj;
    }

    public GetVendorFormQueryHelper(VendorPrezData vendorPrezDataObj) {
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

           vendorbean.setVendorNumber(results.getString("VENDOR_CODE"));
           vendorbean.setVendorName(results.getString("VENDOR_NAME"));

            

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorFormQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorbean;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

            sql.append(" SELECT EVM.VENDOR_CODE, EVM.VENDOR_NAME ");           
            sql.append(" FROM ERP_VENDOR_MASTER EVM ");
            sql.append(" WHERE TO_NUMBER(EVM.VENDOR_CODE) = ? ");

            logger.log(Level.INFO, "GetVendorFormQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, vendorBeanObj.getVendorNumber());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorFormQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

    
}