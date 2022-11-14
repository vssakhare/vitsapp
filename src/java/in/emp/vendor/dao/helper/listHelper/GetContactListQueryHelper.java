package in.emp.vendor.dao.helper.listHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class GetContactListQueryHelper implements QueryHelper {
    private static Logger logger = Logger.getLogger(GetContactListQueryHelper.class);
    private VendorBean vendorBean;
    private VendorPrezData vendorPrezDataObj;

    public GetContactListQueryHelper(VendorBean vendorBean) {
        this.vendorBean = vendorBean;
    }

    public GetContactListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }
    
    public Object getDataObject(ResultSet results) throws Exception {
        VendorBean vendorBeanObj = new VendorBean();
        try {
            logger.log(Level.INFO, "GetContactListQueryHelper ::: getDataObject() :: method called ::");
      
            vendorBeanObj.setVendorContactNumber(results.getString("PHN_LL"));
           vendorBeanObj.setMailId(results.getString("EMAIL"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetContactListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
           
             if(!(vendorBean.getPassword().equals("welcome")) &&  !(vendorBean.getPassword().equals("")) ){
             sql.append(" SELECT VENDOR_CODE,PHN_LL,EMAIL ");
            sql.append(" FROM ERP_VENDOR_MASTER EVM,ERP_LOGIN E ");
            sql.append(" WHERE EVM.VENDOR_CODE =  ? ");
            sql.append(" AND EVM.VENDOR_CODE =E.USER_NAME ");
             sql.append("AND E.PASSWORD = ? ");
            }
             else {
                 sql.append(" SELECT VENDOR_CODE,PHN_LL,EMAIL ");
            sql.append(" FROM ERP_VENDOR_MASTER EVM ");
            sql.append(" WHERE EVM.VENDOR_CODE =  ? ");
             }
            
            logger.log(Level.INFO, "GetContactListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1,vendorBean.getVendorNumber());
                        if(!(vendorBean.getPassword().equals("welcome")) &&  !(vendorBean.getPassword().equals("")))
                        {
                            statement.setString(2,vendorBean.getPassword());
                        }
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetContactListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
