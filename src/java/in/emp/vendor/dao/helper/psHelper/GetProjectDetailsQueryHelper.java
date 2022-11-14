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
public class GetProjectDetailsQueryHelper  implements QueryHelper {
      private static Logger logger = Logger.getLogger(GetProjDetailsQueryHelper.class);
    private ProjBean projBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetProjectDetailsQueryHelper(ProjBean projBeanObj) {  
        this.projBeanObj = projBeanObj;
    }

    public GetProjectDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
        ProjBean projBeanObj = new ProjBean();
        try {
            logger.log(Level.INFO, "GetProjectDetailsQueryHelper ::: getDataObject() :: method called ::");
            projBeanObj.setPROJECT_CODE(results.getString("PROJECT_CODE"));
            projBeanObj.setMaterial_PO(results.getString("MATERIAL_PO"));           
           projBeanObj.setCentages_PO(results.getString("CENTAGES_PO"));
            projBeanObj.setService_PO(results.getString("SERVICE_PO"));
            
            
          

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetProjectDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return projBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetProjectDetailsQueryHelper ::: getQueryResults() :: method called ::");           
          
           sql.append(" SELECT  DISTINCT PROJECT_CODE,MATERIAL_PO,CENTAGES_PO,SERVICE_PO ");           
                     
           sql.append(" FROM PS_PO_STATUS_NEW   "); 
           
        if(!ApplicationUtils.isBlank(projBeanObj.getVendorNumber()) ){
             sql.append(" WHERE  TO_NUMBER(VENDOR_NUMBER) = ?  ");
        }
           if(!ApplicationUtils.isBlank(projBeanObj.getPo_number())){
           sql.append(" AND MATERIAL_PO = ?  ");
          }  
           if(!ApplicationUtils.isBlank(projBeanObj.getPo_number())){
           sql.append(" OR CENTAGES_PO = ?  ");
          }  
           if(!ApplicationUtils.isBlank(projBeanObj.getPo_number())){
           sql.append(" OR SERVICE_PO = ?  ");
          }  
           logger.log(Level.INFO, "GetProjDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());  
            
               if(!ApplicationUtils.isBlank(projBeanObj.getVendorNumber()) ){
                   statement.setString(1, projBeanObj.getVendorNumber());
               
               }
                if(!ApplicationUtils.isBlank(projBeanObj.getPo_number())){
             statement.setString(2, projBeanObj.getPo_number());
          }   if(!ApplicationUtils.isBlank(projBeanObj.getPo_number())){
             statement.setString(3, projBeanObj.getPo_number());
          }   if(!ApplicationUtils.isBlank(projBeanObj.getPo_number())){
             statement.setString(4, projBeanObj.getPo_number());
          }  
            
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetProjectDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
