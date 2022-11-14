/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper;

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
public class GetPOInvDetailsQueryHelper  implements QueryHelper {
      private static Logger logger = Logger.getLogger(GetPOInvDetailsQueryHelper.class);
    private POBean poBean;
    private VendorPrezData vendorPrezDataObj;

    public GetPOInvDetailsQueryHelper(POBean poBean) {  
        this.poBean = poBean;
    }

    public GetPOInvDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
      POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "GetPOInvDetailsQueryHelper ::: getDataObject() :: method called ::");
            poBeanObj.setPONumber(results.getString("PO_NUMBER"));
            poBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER"));
            poBeanObj.setTotal_Inv_Amt(results.getString("TOTAL_INVOICE_AMOUNT"));
            //poBeanObj.setTotal_Inv_Cnt(results.getString("TOTAL_INV_CNT"));
            poBeanObj.setTotal_Invoicable_Amt(results.getString("TOTAL_INVOICABLE_AMT"));

            
          

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPOInvDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetPOInvDetailsQueryHelper ::: getQueryResults() :: method called ::");           
          
           sql.append("  SELECT PO_NUMBER,VENDOR_NUMBER,TOTAL_INVOICE_AMOUNT,(BAL_AMOUNT-TOTAL_INVOICE_AMOUNT)TOTAL_INVOICABLE_AMT FROM   ");          
           sql.append(" (SELECT po_number,VENDOR_NUMBER ,   ");           
             sql.append(" sum(INVOICE_AMOUNT)TOTAL_INVOICE_AMOUNT, BAL_AMOUNT   ");    
              sql.append(" FROM XXMIS_INV_DETAILS  ");    
                
       //sql.append("  WHERE STATUS IN('Saved','Submitted','Verified','INVOICE NOT CREATED')  "); 
             if(!ApplicationUtils.isBlank(poBean.getVendorNumber()) ){
             sql.append(" WHERE TO_NUMBER(VENDOR_NUMBER) = ?  ");
             }
           if(!ApplicationUtils.isBlank(poBean.getPONumber())){
            sql.append(" AND PO_NUMBER = ?   ");
            }  
            sql.append("  GROUP BY PO_NUMBER,VENDOR_NUMBER,BAL_AMOUNT)PS  ");
         
           logger.log(Level.INFO, "GetPOInvDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());  
            
               if(!ApplicationUtils.isBlank(poBean.getVendorNumber()) ){
                   statement.setString(1, poBean.getVendorNumber());
               
               }
                if(!ApplicationUtils.isBlank(poBean.getPONumber())){
             statement.setString(2, poBean.getPONumber());
          }  
            
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPOInvDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
