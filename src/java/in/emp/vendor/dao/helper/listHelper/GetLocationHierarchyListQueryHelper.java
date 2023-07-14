/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.listHelper;

import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
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
 * @author pooja
 */
public class GetLocationHierarchyListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetLocationListQueryHelper.class);
    private POBean poBean;
    private VendorPrezData vendorPrezDataObj;

    public GetLocationHierarchyListQueryHelper(POBean poBean) {  
        this.poBean = poBean;
    }

    public GetLocationHierarchyListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
        POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "GetLocationHierarchyListQueryHelper ::: getDataObject() :: method called ::");
           
           
            
          
              poBeanObj.setLocationCode(results.getString("LOCATION_CODE"));
              poBeanObj.setLocationName(results.getString("LOCATION_NAME"));
              poBeanObj.setLocationType(results.getString("LOCATION_TYPE"));
           

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLocationHierarchyListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetLocationHierarchyListQueryHelper ::: getQueryResults() :: method called ::");           
          
      
          
           
           
     
           sql.append(" select distinct h.organization_id LOCATION_CODE, h.name LOCATION_NAME, h.type LOCATION_TYPE  ");          
           sql.append(" from hr_all_organization_units h, hr_all_organization_units h1, ");
           sql.append(" hri_org_hrchy_summary hr  ");        
           sql.append(" where  h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'  ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id  ");
           sql.append(" and h.type in ('HO','ZON','CIR','DIV','SUB') ");
           sql.append(" and h.organization_id not in (select h.organization_id child_org  ");
           sql.append(" from hr_all_organization_units h, hr_all_organization_units h1, hri_org_hrchy_summary hr ");           
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id=9988 and h1.organization_id=hr.organization_id)  ");
           if(!ApplicationUtils.isBlank(poBean.getPlant())){
            sql.append(" and h.type in ( ? ) ");
           }
             
         
           sql.append(" order by decode(h.type,'HO','A','REG','B','ZON','C','CIR','D','DIV','E') ");
           
          
           
          
                     
           logger.log(Level.INFO, "GetLocationHierarchyListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString()); 
            statement.setString(1, poBean.getOfficeode());
                       if(!ApplicationUtils.isBlank(poBean.getPlant())){
            statement.setString(2, poBean.getPlant());
                       }
             
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLocationHierarchyListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
