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
public class GetPODetailsQueryHelper  implements QueryHelper {
      private static Logger logger = Logger.getLogger(GetPODetailsQueryHelper.class);
    private POBean poBean;
    private VendorPrezData vendorPrezDataObj;

    public GetPODetailsQueryHelper(POBean poBean) {  
        this.poBean = poBean;
    }

    public GetPODetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
      POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "GetProjListQueryHelper ::: getDataObject() :: method called ::");
            poBeanObj.setPONumber(results.getString("PO_NUMBER"));
            poBeanObj.setPODesc(results.getString("PO_DESC"));           
            poBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER"));
            poBeanObj.setPo_amt(results.getString("TOTAL_PO_AMT"));
            poBeanObj.setBal_po_amt(results.getString("BAL_AMOUNT"));
            
          

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPODetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetPODetailsQueryHelper ::: getQueryResults() :: method called ::");           
          
           sql.append(" SELECT DISTINCT PO_NUMBER,PO_DESC,  VENDOR_NUMBER ,TOTAL_PO_AMT,(TOTAL_PO_AMT- sum((case when ses_amount!=0 then ses_amount when ses_amount=0 then migo_amt end) ))BAL_AMOUNT ");          
           sql.append(" FROM PO_STATUS_NEW EPL  ");           
                   
        if(!ApplicationUtils.isBlank(poBean.getPONumber())){
            sql.append(" WHERE PO_NUMBER = ?   ");
            } 
             if(!ApplicationUtils.isBlank(poBean.getVendorNumber()) ){
             sql.append(" AND  TO_NUMBER(VENDOR_NUMBER) = ?  ");
             }
           
           sql.append(" group by PO_NUMBER,PO_DESC,  VENDOR_NUMBER ,TOTAL_PO_AMT ");
           logger.log(Level.INFO, "GetProjDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());  
            if(!ApplicationUtils.isBlank(poBean.getPONumber())){
             statement.setString(1, poBean.getPONumber());
          }  
               if(!ApplicationUtils.isBlank(poBean.getVendorNumber()) ){
                   statement.setString(2, poBean.getVendorNumber());
               
               }
                
            
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPODetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
