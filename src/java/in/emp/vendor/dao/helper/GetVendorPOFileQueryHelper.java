/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.vendor.bean.VendorApplFileBean;
import in.emp.vendor.bean.VendorApplFilePrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import in.emp.common.ApplicationConstants;

/**
 *
 * @author Prajakta
 */
public class GetVendorPOFileQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorApplFileQueryHelper.class);
    private VendorApplFileBean vendorapplFileBeanObj;
    private VendorApplFilePrezData vendorapplFilePrezDataObj;

    public GetVendorPOFileQueryHelper(VendorApplFileBean vendorapplFileBeanObj) {
        this.vendorapplFileBeanObj = vendorapplFileBeanObj;
    }

    public GetVendorPOFileQueryHelper(VendorApplFilePrezData vendorapplFilePrezDataObj) {
        this.vendorapplFilePrezDataObj = vendorapplFilePrezDataObj;
        this.vendorapplFileBeanObj = this.vendorapplFilePrezDataObj.getVendorapplFileBean();
    }

    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        VendorApplFileBean vendorapplFileBean = new VendorApplFileBean();
        try {
            logger.log(Level.INFO, "GetVendorPOFileQueryHelper ::: getQueryResults() :: method called ::");

            vendorapplFileBean.setId(results.getString("ID"));
            vendorapplFileBean.setPo_Number(results.getString("PO_NUMBER"));
            vendorapplFileBean.setSrNo(results.getInt("SR_NO"));
            vendorapplFileBean.setFileName(results.getString("FILE_NAME"));
            vendorapplFileBean.setFileType(results.getString("FILE_TYPE"));
            //vendorapplFileBean.setFileContents(results.getBytes("FILE_BLOB"));
            vendorapplFileBean.setRemark(results.getString("REMARK"));
            vendorapplFileBean.setOption(results.getString("TYPE_OF_FILE"));
            vendorapplFileBean.setPath(results.getString("PATH"));
            vendorapplFileBean.setDELETION_FLAG(results.getString("DELETION_FLAG"));
             vendorapplFileBean.setApplicationId(results.getString("APPL_ID"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorPOFileQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorapplFileBean;
    }

    @Override
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetVendorPOFileQueryHelper ::: getQueryResults() :: method called ::");

            sql.append(" SELECT EIF.ID, EIF.PO_NUMBER, EIF.SR_NO, ");
            sql.append(" EIF.FILE_NAME, EIF.FILE_TYPE,  EIF.REMARK,EIF.TYPE_OF_FILE,EIF.DELETION_FLAG,EIF.APPL_ID,EIF.PATH ");
            sql.append(" FROM XXMIS_PO_INVOICE_FILES EIF ");
            sql.append(" WHERE EIF.PO_NUMBER =  ? ");
            sql.append(" AND EIF.ID =  ? ");

            logger.log(Level.INFO, "GetVendorPOFileQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, vendorapplFileBeanObj.getPo_Number());
            statement.setString(2, vendorapplFileBeanObj.getId());
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorPOFileQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}