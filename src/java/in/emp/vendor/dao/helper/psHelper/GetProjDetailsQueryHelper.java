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
public class GetProjDetailsQueryHelper  implements QueryHelper {
      private static Logger logger = Logger.getLogger(GetProjDetailsQueryHelper.class);
    private ProjBean projBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetProjDetailsQueryHelper(ProjBean projBeanObj) {  
        this.projBeanObj = projBeanObj;
    }

    public GetProjDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
        ProjBean projBeanObj = new ProjBean();
        try {
            logger.log(Level.INFO, "GetProjListQueryHelper ::: getDataObject() :: method called ::");
            projBeanObj.setPROJECT_CODE(results.getString("PROJECT_CODE"));
            projBeanObj.setPROJECT_DESC(results.getString("PROJECT_DESC"));           
           projBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER"));
            projBeanObj.setTotal_Po_Amt(results.getString("TOTAL_PO_AMOUNT"));
            projBeanObj.setBal_amt(results.getString("BAL_AMOUNT"));
            
          

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
          
           sql.append("  SELECT     PROJECT_CODE,PROJECT_DESC ,VENDOR_NUMBER, TOTAL_PO_AMOUNT,  ( TOTAL_PO_AMOUNT- PO_INV_AMT) BAL_AMOUNT FROM ");          
           sql.append(" (SELECT  PROJECT_CODE,PROJECT_DESC ,VENDOR_NUMBER,  ");           
           sql.append(" (nvl(PO_MAT_AMT,0)+nvl(PO_CEN_AMT,0)+nvl(PO_CIV_AMT,0)) TOTAL_PO_AMOUNT,  "); 
             sql.append(" SUM(PO_INV_AMT)PO_INV_AMT  ");           
           sql.append(" FROM PS_PO_STATUS_NEW   "); 
           
        if(!ApplicationUtils.isBlank(projBeanObj.getVendorNumber()) ){
             sql.append(" WHERE  TO_NUMBER(VENDOR_NUMBER) = ?  ");
        }
           if(!ApplicationUtils.isBlank(projBeanObj.getPROJECT_CODE())){
           sql.append(" AND PROJECT_CODE = ?  ");
          }  
          sql.append(" GROUP BY PROJECT_CODE,PROJECT_DESC ,VENDOR_NUMBER,(nvl(PO_MAT_AMT,0)+nvl(PO_CEN_AMT,0)+nvl(PO_CIV_AMT,0)))  "); 
           logger.log(Level.INFO, "GetProjDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());  
            
               if(!ApplicationUtils.isBlank(projBeanObj.getVendorNumber()) ){
                   statement.setString(1, projBeanObj.getVendorNumber());
               
               }
                if(!ApplicationUtils.isBlank(projBeanObj.getPROJECT_CODE())){
             statement.setString(2, projBeanObj.getPROJECT_CODE());
          }  
            
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetProjDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
