/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.psHelper;

import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.ProjBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class GetProjListQueryHelper  implements QueryHelper {
      private static Logger logger = Logger.getLogger(GetProjListQueryHelper.class);
    private ProjBean projBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetProjListQueryHelper(ProjBean projBeanObj) {  
        this.projBeanObj = projBeanObj;
    }

    public GetProjListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
        ProjBean projBeanObj = new ProjBean();
        try {
            logger.log(Level.INFO, "GetProjListQueryHelper ::: getDataObject() :: method called ::");
            projBeanObj.setPROJECT_CODE(results.getString("PROJECT_CODE"));
            projBeanObj.setPROJECT_DESC(results.getString("PROJECT_DESC"));           
            projBeanObj.setScheme_Desc(results.getString("SCHEME_DESC"));
            projBeanObj.setScheme_code(results.getString("SCHEME_CODE"));
            projBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER"));
            projBeanObj.setPLANT(results.getString("PLANT"));
            projBeanObj.setPLANT_DESC(results.getString("PLANT_DESC"));
            projBeanObj.setZONE(results.getString("ZONE"));
            projBeanObj.setCIRCLE(results.getString("CIRCLE"));
            projBeanObj.setDIVISION(results.getString("DIVISION"));
            projBeanObj.setCircle_code(results.getString("CIRCLE_CODE"));
            projBeanObj.setZone_code(results.getString("ZONE_CODE"));
          projBeanObj.setPurchasing_group(results.getString("purchasing_group"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetProjListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return projBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetProjListQueryHelper ::: getQueryResults() :: method called ::");           
           if(projBeanObj.getUserType().equals("Vendor")) {
           sql.append(" SELECT DISTINCT PROJECT_CODE,nvl(PROJECT_DESC,SCHEME_DESC)PROJECT_DESC ");
           sql.append( ",ZONE ,CIRCLE ,ZONE_CODE  ,CIRCLE_CODE ,DIVISION,SCHEME_CODE,SCHEME_DESC, VENDOR_NUMBER,EPL.PLANT ,NVL(DIVISION,NVL(CIRCLE,ZONE))PLANT_DESC,purchasing_group  ");          
           sql.append(" FROM XXMIS_ERP_PROJ_LIST EPL,ORGANISATION_MASTER ORG   ");  
           
           sql.append(" WHERE ORG.PLANT=EPL.PLANT ");
              if(!ApplicationUtils.isBlank(projBeanObj.getVendorNumber())){ 
           sql.append(" and TO_NUMBER(VENDOR_NUMBER) = ? ");  }        
            if(!ApplicationUtils.isBlank(projBeanObj.getPROJECT_CODE())){
           sql.append(" AND PROJECT_CODE = ?  ");
          }  
           }
           logger.log(Level.INFO, "GetProjListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());  
            
               if(!ApplicationUtils.isBlank(projBeanObj.getVendorNumber()) ){
                   statement.setString(1, projBeanObj.getVendorNumber());
               
               }
                if(!ApplicationUtils.isBlank(projBeanObj.getPROJECT_CODE())){
             statement.setString(2, projBeanObj.getPROJECT_CODE());
          }  
            
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetProjListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
