/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;

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
public class GetVendorLegalApplFileQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorLegalApplFileQueryHelper.class);
    private VendorApplFileBean vendorapplFileBeanObj;
    private VendorApplFilePrezData vendorapplFilePrezDataObj;

    public GetVendorLegalApplFileQueryHelper(VendorApplFileBean vendorapplFileBeanObj) {
        this.vendorapplFileBeanObj = vendorapplFileBeanObj;
    }

    public GetVendorLegalApplFileQueryHelper(VendorApplFilePrezData vendorapplFilePrezDataObj) {
        this.vendorapplFilePrezDataObj = vendorapplFilePrezDataObj;
        this.vendorapplFileBeanObj = this.vendorapplFilePrezDataObj.getVendorapplFileBean();
    }

    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        VendorApplFileBean vendorapplFileBean = new VendorApplFileBean();
        try {
            logger.log(Level.INFO, "GetVendorLegalApplFileQueryHelper ::: getQueryResults() :: method called ::");

            vendorapplFileBean.setId(results.getString("ID"));
            vendorapplFileBean.setApplicationId(results.getString("APPL_ID"));
            vendorapplFileBean.setSrNo(results.getInt("SR_NO"));
            vendorapplFileBean.setFileName(results.getString("FILE_NAME"));
            vendorapplFileBean.setFileType(results.getString("FILE_TYPE"));
           // vendorapplFileBean.setFileContents(results.getBytes("FILE_BLOB"));
            vendorapplFileBean.setRemark(results.getString("REMARK"));
            vendorapplFileBean.setOption(results.getString("TYPE_OF_FILE"));
            vendorapplFileBean.setPath(results.getString("PATH"));
            vendorapplFileBean.setDELETION_FLAG(results.getString("DELETION_FLAG"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorLegalApplFileQueryHelper :: getDataObject() :: Exception :: " + ex);
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
            logger.log(Level.INFO, "GetVendorLegalApplFileQueryHelper ::: getQueryResults() :: method called ::");

            sql.append(" SELECT EIF.ID, EIF.APPL_ID, EIF.SR_NO, ");
            sql.append(" EIF.FILE_NAME, EIF.FILE_TYPE, EIF.REMARK,EIF.TYPE_OF_FILE,EIF.PATH,EIF.DELETION_FLAG ");
            sql.append(" FROM XXMIS_ERP_LEGAL_INVOICE_FILES EIF ");
            sql.append(" WHERE EIF.APPL_ID =  ? ");
            sql.append(" AND EIF.ID =  ? ");

            logger.log(Level.INFO, "GetVendorLegalApplFileQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, vendorapplFileBeanObj.getApplicationId());
            statement.setString(2, vendorapplFileBeanObj.getId());
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorApplFileQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}