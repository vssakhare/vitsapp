package in.emp.vendor.dao.helper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class GetForwardToOfficeCodeDetailsQueryHelper implements QueryHelper {
    private static Logger logger = Logger.getLogger(GetForwardToOfficeCodeDetailsQueryHelper.class);
    private POBean poBean;
    private VendorPrezData vendorPrezDataObj;

    public GetForwardToOfficeCodeDetailsQueryHelper(POBean poBean) {
        this.poBean = poBean;
    }

    public GetForwardToOfficeCodeDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }
    
    public Object getDataObject(ResultSet results) throws Exception {
        POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "GetForwardToOfficeCodeDetailsQueryHelper ::: getDataObject() :: method called ::");
      
                      
            poBeanObj.setPlant(results.getString("PLANT"));
            poBeanObj.setOfficeCode(results.getString("OFFICECODE"));
            poBeanObj.setParentOfficeCode(results.getString("PARENTOFFICECODE"));
            poBeanObj.setOrgUnit(results.getString("ORGUNIT"));
           

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetForwardToOfficeCodeDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj;
    }
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
      
                sql.append("select o.plant PLANT,o.office_code OFFICECODE,o.parent_office_code PARENTOFFICECODE,o.org_unit ORGUNIT");
            sql.append(" from organisation_master o  ");
            sql.append(" where plant= ?  ");
            
          
           
            logger.log(Level.INFO, "GetForwardToOfficeCodeDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
          
                statement.setString(1,poBean.getPlant()); 
           
                       
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetForwardToOfficeCodeDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
