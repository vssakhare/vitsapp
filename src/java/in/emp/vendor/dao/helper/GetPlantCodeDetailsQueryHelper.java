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

public class GetPlantCodeDetailsQueryHelper implements QueryHelper {
    private static Logger logger = Logger.getLogger(GetPlantCodeDetailsQueryHelper.class);
    private POBean poBean;
    private VendorPrezData vendorPrezDataObj;

    public GetPlantCodeDetailsQueryHelper(POBean poBean) {
        this.poBean = poBean;
    }

    public GetPlantCodeDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }
    
    public Object getDataObject(ResultSet results) throws Exception {
        POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "GetPlantCodeDetailsQueryHelper ::: getDataObject() :: method called ::");
            poBeanObj.setPlant(results.getString("PLANT"));
              poBeanObj.setOfficeCode(results.getString("OFFICE_CODE"));
            poBeanObj.setParentOfficeCode(results.getString("PARENT_OFFICE_CODE"));
       } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPlantCodeDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj;
    }
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
        
            if(poBean.getSelectedModule().equals("PS")){//to get the plant based on po number from po status table
                sql.append(" SELECT O.PLANT,OFFICE_CODE,PARENT_OFFICE_CODE FROM ps_po_status_new  P,ORGANISATION_MASTER O ");
                 if(!ApplicationUtils.isBlank(poBean.getPONumber())){
            sql.append(" WHERE PROJECT_CODE = ?   ");
            } 
                sql.append("   AND ROWNUM<2 AND O.PLANT=P.PLANT ");
            
            }
            else{ 
                sql.append(" SELECT O.PLANT,OFFICE_CODE,PARENT_OFFICE_CODE FROM PO_STATUS_NEW P,ORGANISATION_MASTER O ");
                  if(!ApplicationUtils.isBlank(poBean.getPONumber())){
            sql.append(" WHERE PO_NUMBER = ?   ");
            } 
                sql.append("    AND ROWNUM<2 AND O.PLANT=P.PLANT ");
            }
            
         
            logger.log(Level.INFO, "GetPlantCodeDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());

              if(!ApplicationUtils.isBlank(poBean.getPONumber())){
             statement.setString(1, poBean.getPONumber());
          }  
       
            
                       
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPlantCodeDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
