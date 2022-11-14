package in.emp.vendor.dao.helper.listHelper;

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

public class getPlantDetailsQueryHelper implements QueryHelper {
    private static Logger logger = Logger.getLogger(getPlantDetailsQueryHelper.class);
    private POBean poBean;
    private VendorPrezData vendorPrezDataObj;

    public getPlantDetailsQueryHelper(POBean poBean) {
        this.poBean = poBean;
    }

    public getPlantDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }
    
    public Object getDataObject(ResultSet results) throws Exception {
        POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "getPlantDetailsQueryHelper ::: getDataObject() :: method called ::");
      
                      
            poBeanObj.setPlantDesc(results.getString("PLANT_DESC"));
            poBeanObj.setPlant(results.getString("PLANT"));
           } catch (Exception ex) {
            logger.log(Level.ERROR, "getPlantDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj;
    }
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
      
                sql.append(" SELECT DISTINCT(BILLING_DB_CODE)  PLANT_DESC, PLANT FROM ( ");
          //  sql.append(" SELECT 'D'||DIV||'-'||D_NAME BILLING_DB_CODE ,DIV PLANT ");
//  sql.append(" FROM BULT WHERE CIRCLE IN (select billing_db_code from organisation_master where plant=? ) ");
 // sql.append(" UNION " );
  sql.append(" SELECT 'S'||SUBDIV||'-'||S_NAME BILLING_DB_CODE,SUBDIV PLANT ");
  sql.append(" FROM BULT WHERE DIV IN (select billing_db_code from organisation_master where plant=? ) ");
  sql.append(" ) " );
           
            
          
           
            logger.log(Level.INFO, "getPlantDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
          
                statement.setString(1,poBean.getPlant()); 
              //  statement.setString(2,poBean.getPlant()); 
                       
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "getPlantDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
