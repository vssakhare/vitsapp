/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.psHelper;

import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.VendorBean;
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
public class GetPartialRetentionDetailsQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetPartialRetentionDetailsQueryHelper.class);
    private VendorBean vendorBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetPartialRetentionDetailsQueryHelper(VendorBean vendorBeanObj) {
        this.vendorBeanObj = vendorBeanObj;
    }

    public GetPartialRetentionDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorBeanObj = this.vendorPrezDataObj.getVendorBean();
    }

    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        VendorBean vendorbean = new VendorBean();
//not used to fetch the invoice form details
        try {
            vendorbean.setProjectCode(results.getString("PROJECT_ID"));
            vendorbean.setInvoiceNumber(results.getString("INV_NO"));
            vendorbean.setInvoiceAmount(results.getString("NEW_RT_AMOUNT"));
            vendorbean.setWRPST(results.getString("NEW_RT_DOC_NO"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPartialRetentionDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorbean;
    }

    @Override
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

            sql.append(" SELECT DISTINCT PROJECT_ID,INV_NO,NEW_RT_DOC_NO,NEW_RT_AMOUNT FROM VENDOR_RETENTION_DETAILS ");
            sql.append(" WHERE PROJECT_ID=? ");
            sql.append(" AND NEW_RT_DOC_NO IS NOT NULL ");
            sql.append(" AND NEW_RT_DOC_NO NOT IN(SELECT RETENTION_DOC FROM  VENDOR_RETENTION_DETAILS) ");

            logger.log(Level.INFO, "GetPartialRetentionDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
            System.out.println("GetPartialRetentionDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
            statement = connection.prepareStatement(sql.toString());

            statement.setString(1, vendorBeanObj.getProjectCode());
//            if (!ApplicationUtils.isBlank(vendorBeanObj.getProjectCode())) {
//                statement.setString(2, vendorBeanObj.getProjectCode());
//            }
            System.out.println("vendorBeanObj.getProjectCode()::" + vendorBeanObj.getProjectCode());
            System.out.println("vendorBeanObj.getVendorNumber()::" + vendorBeanObj.getVendorNumber());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            System.out.println("GetPartialRetentionDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            logger.log(Level.ERROR, "GetPartialRetentionDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        // rs.next();
        /*  while(rs.next())
        {
            System.out.println("inv_no:::"+rs.getString("MSEDCL_INV_NO"));
        }*/
        return rs;
    }

}
