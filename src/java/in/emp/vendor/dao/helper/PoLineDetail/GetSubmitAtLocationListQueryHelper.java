/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.PoLineDetail;

/**
 *
 * @author Pooja Jadhav
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.PoLineStatusBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author pooja
 */
public class GetSubmitAtLocationListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetSubmitAtLocationListQueryHelper.class);
    private PoLineStatusBean poLinestatusbeanobj;
    private VendorPrezData vendorPrezDataObj;

    public GetSubmitAtLocationListQueryHelper(PoLineStatusBean poLinestatusbeanobj) {  
        this.poLinestatusbeanobj = poLinestatusbeanobj;
    }

    public GetSubmitAtLocationListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
        PoLineStatusBean poLinestatusbeanobj = new PoLineStatusBean();
        try {
            logger.log(Level.INFO, "GetSubmitAtLocationListQueryHelper ::: getDataObject() :: method called ::");
           
           
            
          
              poLinestatusbeanobj.setPlant(results.getString("plant"));
          
           

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetSubmitAtLocationListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poLinestatusbeanobj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetSubmitAtLocationListQueryHelper ::: getQueryResults() :: method called ::");           
          
      
          
           
           
     
           sql.append(" select distinct plant    FROM PO_LINE_STATUS_TEMP ");          
         
           sql.append(" where   ");        
          
           if(!ApplicationUtils.isBlank(poLinestatusbeanobj.getContract_Document())){
            sql.append(" CONTRACT_DOCUMENT =?  ");
           }
            if(!ApplicationUtils.isBlank(poLinestatusbeanobj.getVendor_Number())){
            sql.append("and VENDOR_NUMBER =?  ");
           } 
         
           
           
          
           
          
                     
           logger.log(Level.INFO, "GetSubmitAtLocationListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString()); 
  if(!ApplicationUtils.isBlank(poLinestatusbeanobj.getContract_Document())){
            statement.setString(1, poLinestatusbeanobj.getContract_Document());
                       }
                       if(!ApplicationUtils.isBlank(poLinestatusbeanobj.getVendor_Number())){
            statement.setString(2, poLinestatusbeanobj.getVendor_Number());
                       }
             
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLocationHierarchyListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
