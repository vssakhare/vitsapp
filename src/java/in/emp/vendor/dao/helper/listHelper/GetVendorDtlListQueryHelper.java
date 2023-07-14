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
 * @author Prajakta
 */
public class GetVendorDtlListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorDtlListQueryHelper.class);
    private POBean poBean;
    private VendorPrezData vendorPrezDataObj;

    public GetVendorDtlListQueryHelper(POBean poBean) { 
        this.poBean = poBean;
    }

    public GetVendorDtlListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
        POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "GetVendorDtlListQueryHelper ::: getDataObject() :: method called ::");
          
            poBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER"));
            poBeanObj.setVendorName(results.getString("VENDOR_NAME"));  
           

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorDtlListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        
        ResultSet rs = null;
       int i=1;
        try {
            logger.log(Level.INFO, "GetVendorDtlListQueryHelper ::: getQueryResults() :: method called ::");           
           if(poBean.getUserType().equals("Vendor")) {//using vendor usertype in case of employee login to get the details of vendor from main table.
            sql.append("SELECT VENDOR_NUMBER,VENDOR_NAME FROM XXMIS_ERP_VENDOR");
            sql.append(" WHERE OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h, ");
            sql.append("hr_all_organization_units h1, hri_org_hrchy_summary hr ");
            sql.append("where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'"); 
            sql.append("and hr.organization_id = ? and h1.organization_id=hr.organization_id  ");
            sql.append("and h.type in ('HO','ZON','CIR','DIV') ) ");
           }
           if(poBean.getUserType().equals("Emp")) {
           sql.append(" SELECT distinct EAPL.VENDOR_NUMBER, EAPL.VENDOR_NAME ");          
           sql.append(" FROM XXMIS_ERP_AUTH_INPUT_LIST EAPL ");           
           sql.append(" WHERE EAPL.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h, ");
           sql.append(" hr_all_organization_units h1, hri_org_hrchy_summary hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id  ");
           sql.append(" and h.type in ('HO','ZON','CIR','DIV') ) ");
         //  sql.append(" and vendor_number in(select vendor_number from xxmis_erp_vendor_dtl) ");
           if(!ApplicationUtils.isBlank(poBean.getPONumber())){
           sql.append(" AND EAPL.PO_NUMBER = ?  ");
          } 
             sql.append("UNION ALL");
            sql.append(" SELECT distinct EAPL.VENDOR_NUMBER, EAPL.VENDOR_NAME ");          
           sql.append(" FROM XXMIS_ERP_PS_AUTH_INPUT_LIST EAPL ");           
           sql.append(" WHERE EAPL.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h, ");
           sql.append(" hr_all_organization_units h1, hri_org_hrchy_summary hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id  ");
           sql.append(" and h.type in ('HO','ZON','CIR','DIV') ) ");
         //  sql.append(" and vendor_number in(select vendor_number from xxmis_erp_PS_vendor_dtl) ");
           if(!ApplicationUtils.isBlank(poBean.getPONumber())){
           sql.append(" AND EAPL.PROJECT_CODE = ?  ");
          } 
           }
          
                     
           logger.log(Level.INFO, "GetVendorDtlListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());  

              if(poBean.getUserType().equals("Emp")) {
                   statement.setString(i++, poBean.getLocationId());
                     statement.setString(i++, poBean.getLocationId());
              }
               if(poBean.getUserType().equals("Vendor")) {
               if(!ApplicationUtils.isBlank(poBean.getLocationId()) ){
                   statement.setString(i++, poBean.getLocationId());
               
               }
               }
             
           rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorDtlListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
