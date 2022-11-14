/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.listHelper;

import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.POBean;
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
public class GetPOLocationQueryHelper  implements QueryHelper {
      private static Logger logger = Logger.getLogger(GetPOLocationQueryHelper.class);
       private POBean poBean;
    private VendorPrezData vendorPrezDataObj;

    public GetPOLocationQueryHelper(POBean poBean) {  
        this.poBean = poBean;
    }

    public GetPOLocationQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

   

    public Object getDataObject(ResultSet results) throws Exception {
        POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "GetPOLocationQueryHelper ::: getDataObject() :: method called ::");
            
            poBeanObj.setPlant(results.getString("PLANT"));
            poBeanObj.setPlantDesc(results.getString("PLANT_DESC"));
            poBeanObj.setPONumber(results.getString("PO_NUMBER"));
          poBeanObj.setPURCHASING_GROUP(results.getString("purchasing_group"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPOLocationQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetPOLocationQueryHelper ::: getQueryResults() :: method called ::");           
           if(poBean.getUserType().equals("Vendor")) {
                  sql.append(" SELECT DISTINCT EVL.PO_NUMBER, nvl(EVL.PO_DESC,' ')PO_DESC,EVL.PLANT,EVL.PLANT_DESC,EVL.PURCHASING_GROUP ,'PM' MODULE ");          
           sql.append(" FROM XXMIS_ERP_PURCHASING_LOCATION EVL,ORGANISATION_MASTER ORG WHERE  "); 
            if(!ApplicationUtils.isBlank(poBean.getVendorNumber())){
           sql.append("  TO_NUMBER(EVL.VENDOR_NUMBER) = ? AND ");
            }
                 sql.append(  "  ORG.PLANT=EVL.PLANT ");
           if(!ApplicationUtils.isBlank(poBean.getPONumber())){
           sql.append(" and EVL.PO_NUMBER = ?  ");
          }  
         
           }
           logger.log(Level.INFO, "GetPOLocationQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());  
            
               if(!ApplicationUtils.isBlank(poBean.getVendorNumber()) ){
                   statement.setString(1, poBean.getVendorNumber());
               
               }
                if(!ApplicationUtils.isBlank(poBean.getPONumber())){
             statement.setString(2, poBean.getPONumber());
          }  
            
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPOLocationQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
