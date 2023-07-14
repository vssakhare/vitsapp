/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.listHelper;

import in.emp.dao.QueryHelper;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class GetLocationListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetLocationListQueryHelper.class);
    private POBean poBean;
    private VendorPrezData vendorPrezDataObj;

    public GetLocationListQueryHelper(POBean poBean) {  
        this.poBean = poBean;
    }

    public GetLocationListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
        POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "GetLocationListQueryHelper ::: getDataObject() :: method called ::");
           
            if(poBean.getUserType().equals("Vendor")) {
              poBeanObj.setPlantCode(results.getString("SUBMIT_AT_LOCATION"));
          
              poBeanObj.setPODesc(results.getString("SUBMIT_AT_DESC"));  
            }
            
            if(poBean.getUserType().equals("Emp")) {
              poBeanObj.setLocationCode(results.getString("LOCATION_CODE"));
              poBeanObj.setLocationName(results.getString("LOCATION_NAME"));
              poBeanObj.setLocationType(results.getString("LOCATION_TYPE"));
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLocationListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetLocationListQueryHelper ::: getQueryResults() :: method called ::");           
           if(poBean.getUserType().equals("Vendor")) {
           sql.append(" SELECT DISTINCT SUBMIT_AT_LOCATION,SUBMIT_AT_DESC  ");          
           sql.append(" FROM XXMIS_ERP_VENDOR_INPUT_LIST V,ORGANISATION_MASTER ORG ");           
           sql.append(" WHERE TO_NUMBER(V.VENDOR_NUMBER) = ? AND SUBMIT_AT_DESC IS NOT NULL and ORG.PLANT=V.SUBMIT_AT_LOCATION ");
           }
           
           
           if(poBean.getUserType().equals("Emp")) {
           sql.append(" select DISTINCT h.organization_id LOCATION_CODE, h.name LOCATION_NAME, h.type LOCATION_TYPE  ");          
           sql.append(" from hr_all_organization_units h, hr_all_organization_units h1, hri_org_hrchy_summary hr  ");        
           sql.append(" where  h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'  ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id  ");
           sql.append(" and h.type in ('HO','REG','ZON','CIR','DIV') ");
         
          
           }
          
                     
           logger.log(Level.INFO, "GetLocationListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());  
            
             if(poBean.getUserType().equals("Vendor")) {
             statement.setString(1, poBean.getVendorNumber());
              }
             
             if(poBean.getUserType().equals("Emp")) {
             statement.setString(1, poBean.getLocationId());
              }
             
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLocationListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
