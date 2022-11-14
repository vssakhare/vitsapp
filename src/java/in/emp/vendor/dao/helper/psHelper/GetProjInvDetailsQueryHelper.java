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
public class GetProjInvDetailsQueryHelper  implements QueryHelper {
      private static Logger logger = Logger.getLogger(GetProjDetailsQueryHelper.class);
    private ProjBean projBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetProjInvDetailsQueryHelper(ProjBean projBeanObj) {  
        this.projBeanObj = projBeanObj;
    }

    public GetProjInvDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
        ProjBean projBeanObj = new ProjBean();
        try {
            logger.log(Level.INFO, "GetProjInvDetailsQueryHelper ::: getDataObject() :: method called ::");
            projBeanObj.setPROJECT_CODE(results.getString("PROJECT_CODE"));
           projBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER"));
            projBeanObj.setTotal_Inv_Amt(results.getString("TOTAL_INVOICE_AMOUNT"));
            //projBeanObj.setTotal_Inv_Cnt(results.getString("TOTAL_INVOICABLE_AMT"));
          projBeanObj.setTotal_Invoicable_Amt(results.getString("TOTAL_INVOICABLE_AMT"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetProjInvDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return projBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetProjInvDetailsQueryHelper ::: getQueryResults() :: method called ::");           
          
                       sql.append("  SELECT PROJECT_CODE,VENDOR_NUMBER,TOTAL_INVOICE_AMOUNT,(BAL_AMOUNT-TOTAL_INVOICE_AMOUNT)TOTAL_INVOICABLE_AMT FROM   ");          
           sql.append(" (SELECT PROJECT_CODE,VENDOR_NUMBER ,   ");           
             sql.append(" sum(INVOICE_AMOUNT)TOTAL_INVOICE_AMOUNT, TOTAL_PO_AMT,BAL_AMOUNT   ");    
              sql.append(" FROM XXMIS_PS_INV_DETAILS  ");    
            
              
                   
        if(!ApplicationUtils.isBlank(projBeanObj.getVendorNumber()) ){
             sql.append(" WHERE TO_NUMBER(VENDOR_NUMBER) = ?  ");
        }
           if(!ApplicationUtils.isBlank(projBeanObj.getPROJECT_CODE())){
           sql.append(" AND PROJECT_CODE = ?  ");
          }  
           sql.append("  GROUP BY PROJECT_CODE,VENDOR_NUMBER,TOTAL_PO_AMT,BAL_AMOUNT)PS  ");
           logger.log(Level.INFO, "GetProjInvDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

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
