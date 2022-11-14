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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class GetVendorInvListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetLocationListQueryHelper.class);
    private POBean poBean;
    private VendorPrezData vendorPrezDataObj;

    public GetVendorInvListQueryHelper(POBean poBean) {  
        this.poBean = poBean;
    }

    public GetVendorInvListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
        POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "GetVendorInvListQueryHelper ::: getDataObject() :: method called ::");
           
            if(poBean.getUserType().equals("Vendor")) {
              
            }
            
            if(poBean.getUserType().equals("Emp")) {
                poBeanObj.setPlant(results.getString("PLANT"));
                poBean.setVendorNumber(results.getString("VENDOR_NUMBER"));
              poBeanObj.setVendor_Invoice_Number(results.getString("INVOICE_NUMBER"));
              poBeanObj.setVendor_Invoice_Date(results.getDate("INVOICE_DATE"));
              poBeanObj.setVendor_Invoice_Amount(results.getString("INVOICE_AMOUNT"));
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorInvListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetVendorInvListQueryHelper ::: getQueryResults() :: method called ::");           
           if(poBean.getUserType().equals("Vendor")) {
          
           }
           
           
           if(poBean.getUserType().equals("Emp")) {
           sql.append(" SELECT  APPL_ID,PLANT,VENDOR_NUMBER,INVOICE_NUMBER,INVOICE_DATE,INVOICE_AMOUNT  ");          
           sql.append(" FROM XXMIS_ERP_PS_AUTH_INPUT_LIST EAPL ");           
           sql.append(" WHERE EAPL.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h, ");
           sql.append(" hr_all_organization_units h1, hri_org_hrchy_summary hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id  ");
           sql.append(" and h.type in ('HO','ZON','CIR','DIV') ) ");
          
           }
          
                     
           logger.log(Level.INFO, "GetVendorInvListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());  
            
             if(poBean.getUserType().equals("Vendor")) {
            
              }
             
             if(poBean.getUserType().equals("Emp")) {
              statement.setString(1, poBean.getLocationId());
             }
                /* if(!ApplicationUtils.isBlank(poBean.getProject_Id())){
             statement.setString(1, poBean.getProject_Id());
              }
             }*/
             
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorInvListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
