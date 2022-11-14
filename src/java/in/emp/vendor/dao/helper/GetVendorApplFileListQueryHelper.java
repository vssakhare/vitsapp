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
public class GetVendorApplFileListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorApplFileListQueryHelper.class);
    private VendorApplFileBean vendorapplFileBeanObj;
    private VendorApplFilePrezData vendorapplFilePrezDataObj;
    String readDateTo;

    public GetVendorApplFileListQueryHelper(VendorApplFileBean vendorapplFileBeanObj) {
        this.vendorapplFileBeanObj = vendorapplFileBeanObj;
    }

    public GetVendorApplFileListQueryHelper(VendorApplFilePrezData vendorapplFilePrezDataObj) {
        this.vendorapplFilePrezDataObj = vendorapplFilePrezDataObj;
    }

    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        VendorApplFileBean vendorapplFileBean = new VendorApplFileBean();
        try {
            vendorapplFileBean.setId(results.getString("ID"));
            vendorapplFileBean.setApplicationId(results.getString("APPL_ID"));
            vendorapplFileBean.setSrNo(results.getInt("SR_NO"));
            vendorapplFileBean.setFileName(results.getString("FILE_NAME"));
            vendorapplFileBean.setFileType(results.getString("FILE_TYPE"));
            vendorapplFileBean.setRemark(results.getString("REMARK"));
               vendorapplFileBean.setOption(results.getString("TYPE_OF_FILE"));
                 vendorapplFileBean.setPath(results.getString("PATH"));
            vendorapplFileBean.setDELETION_FLAG(results.getString("DELETION_FLAG"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorApplFileQueryHelper :: getDataObject() :: Exception :: " + ex);
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

            sql.append(" SELECT EIF.ID, EIF.APPL_ID, EIF.SR_NO, ");
            sql.append(" EIF.FILE_NAME, EIF.FILE_TYPE, EIF.REMARK,EIF.TYPE_OF_FILE,EIF.PATH,EIF.DELETION_FLAG ");
            sql.append(" FROM XXMIS_ERP_INVOICE_FILES EIF ");
            sql.append(" WHERE EIF.APPL_ID =  ? ");
            logger.log(Level.INFO, "GetVendorApplFileQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, vendorapplFileBeanObj.getApplicationId());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorApplFileQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
