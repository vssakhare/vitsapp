/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;

import in.emp.dao.QueryHelper;
import in.emp.vendor.bean.VendorApplFileBean;
import in.emp.vendor.bean.VendorApplFilePrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Rikma Rai
 */
public class GetVendorLegalApplFileListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorLegalApplFileListQueryHelper.class);
    private VendorApplFileBean vendorapplFileBeanObj;
    private VendorApplFilePrezData vendorapplFilePrezDataObj;
    String readDateTo;

    public GetVendorLegalApplFileListQueryHelper(VendorApplFileBean vendorapplFileBeanObj) {
        this.vendorapplFileBeanObj = vendorapplFileBeanObj;
    }

    public GetVendorLegalApplFileListQueryHelper(VendorApplFilePrezData vendorapplFilePrezDataObj) {
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
            logger.log(Level.ERROR, "GetVendorLegalApplFileListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorapplFileBean;
    }

    @Override
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i=1;
        try {

            sql.append(" SELECT EIF.ID, EIF.APPL_ID, EIF.SR_NO, ");
            sql.append(" EIF.FILE_NAME, EIF.FILE_TYPE, EIF.REMARK,EIF.TYPE_OF_FILE,EIF.PATH,EIF.DELETION_FLAG ");
            sql.append(" FROM XXMIS_ERP_LEGAL_INVOICE_FILES EIF ");
            sql.append(" WHERE EIF.APPL_ID =  ? ");
            if(vendorapplFileBeanObj.getWhereClause()!=null && vendorapplFileBeanObj.getWhereClause().equals("id")){
                sql.append(" AND EIF.ID =  ? ");
            }
            logger.log(Level.INFO, "GetVendorLegalApplFileListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(i++, vendorapplFileBeanObj.getApplicationId());
            if(vendorapplFileBeanObj.getWhereClause()!=null && vendorapplFileBeanObj.getWhereClause().equals("id")){
                 statement.setString(i++, vendorapplFileBeanObj.getId());
            }
           
            
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorLegalApplFileListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}

    
