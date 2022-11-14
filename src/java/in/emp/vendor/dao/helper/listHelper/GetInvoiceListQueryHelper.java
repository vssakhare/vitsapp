/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.listHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
public class GetInvoiceListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetInvoiceListQueryHelper.class);
    private VendorBean vendorBean;
    private VendorPrezData vendorPrezDataObj;

    public GetInvoiceListQueryHelper(VendorBean vendorBean) {
        this.vendorBean = vendorBean;
    }

    public GetInvoiceListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
        VendorBean vendorBeanObj = new VendorBean();
        try {
            logger.log(Level.INFO, "GetInvoiceListQueryHelper ::: getDataObject() :: method called ::");
            
            vendorBeanObj.setVendorInvoiceNumber(results.getString("VENDOR_INV_NO"));
           

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetInvoiceListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetInvoiceListQueryHelper ::: getQueryResults() :: method called ::");           
           if(vendorBean.getUserType().equals("Vendor") && (!vendorBean.getInvoiceFlag().equals("true"))) {
          // sql.append(" SELECT DISTINCT nvl(PS.VENDOR_INV_NO,'Invoice Number not available') VENDOR_INV_NO FROM PO_STATUS_NEW PS ");          
               sql.append(" SELECT DISTINCT EVIL.VENDOR_INV_NO VENDOR_INV_NO FROM XXMIS_ERP_VENDOR_INV_LIST EVIL ");          
               sql.append(" WHERE TO_NUMBER(EVIL.VENDOR_NUMBER) = ? AND EVIL.PO_NUMBER = ? ");
         //  sql.append(" and PS.INVOICE_DATE BETWEEN ? AND  ? ");
           }
           if(vendorBean.getUserType().equals("Vendor") && vendorBean.getInvoiceFlag().equals("true")) {
          // sql.append(" SELECT DISTINCT nvl(PS.VENDOR_INV_NO,'Invoice Number not available') VENDOR_INV_NO FROM PO_STATUS_NEW PS ");          
               sql.append(" select distinct vendor_inv_no from XXMIS_ERP_VENDOR_INV_LIST where vendor_number = ? ");          
               sql.append(" union select distinct INVOICE_NUMBER  from XXMIS_ERP_VENDOR_DTL where vendor_number = ?  ");
            } 
           if(vendorBean.getUserType().equals("Emp")) {
           sql.append(" SELECT  VENDOR_INV_NO FROM XXMIS_ERP_AUTH_INV_LIST  EAIL ");          
           sql.append(" WHERE  EAIL.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h, ");
           sql.append(" hr_all_organization_units h1, hri_org_hrchy_summary hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id  ");
           sql.append(" and h.type in ('HO','ZON','CIR','DIV') ) ");
     
           if(!ApplicationUtils.isBlank(vendorBean.getPONumberHdr())){
           sql.append(" AND EAIL.PO_NUMBER = ?  ");
          } 
          
         //  sql.append(" and PS.INVOICE_DATE BETWEEN ? AND  ? ");
           }
          
                     
           logger.log(Level.INFO, "GetInvoiceListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString()); 
            if(vendorBean.getUserType().equals("Vendor") && (!vendorBean.getInvoiceFlag().equals("true"))) {
             statement.setString(1, vendorBean.getVendorNumber());
             statement.setString(2, vendorBean.getPONumberHdr());
            }
            if(vendorBean.getUserType().equals("Vendor") && vendorBean.getInvoiceFlag().equals("true")) {
             statement.setString(1, vendorBean.getVendorNumber());
               statement.setString(2, vendorBean.getVendorNumber());
             
            }
            if(vendorBean.getUserType().equals("Emp")) {            
             
             statement.setString(1, vendorBean.getLocationId());
          
            
             if(!ApplicationUtils.isBlank(vendorBean.getPONumberHdr()) ){
                   statement.setString(2, vendorBean.getPONumberHdr());
               
               }
            }
           //  statement.setDate(3,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
           //  statement.setDate(4,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
             
            
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetInvoiceListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
