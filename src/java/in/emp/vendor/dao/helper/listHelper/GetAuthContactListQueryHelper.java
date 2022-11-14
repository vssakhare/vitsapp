package in.emp.vendor.dao.helper.listHelper;

import in.emp.vendor.dao.helper.formHelper.GetVendorInputFormQueryHelper;
import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class GetAuthContactListQueryHelper implements QueryHelper {
    private static Logger logger = Logger.getLogger(GetAuthContactListQueryHelper.class); 
    private VendorInputBean vendorInputBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetAuthContactListQueryHelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }

    public GetAuthContactListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorInputBeanObj = this.vendorPrezDataObj.getVendorInputBean();
    }

    
    public Object getDataObject(ResultSet results) throws Exception {
        VendorInputBean vendorBeanObj = new VendorInputBean();
        try {
            logger.log(Level.INFO, "GetAuthContactListQueryHelper ::: getDataObject() :: method called ::");
      
            vendorBeanObj.setApplId(results.getString("appl_id"));
           vendorBeanObj.setPONumber(results.getString("po_number"));
           vendorBeanObj.setSelectedModule(results.getString("MODULE_TYPE"));
           vendorBeanObj.setVendorInvoiceNumber(results.getString("invoice_number"));
           vendorBeanObj.setVendorNumber(results.getString("vendor_number"));
           vendorBeanObj.setVendorName(results.getString("vendor_name"));
           vendorBeanObj.setVendorUpdatedDate(results.getDate("UPDATED_TIME_STAMP"));
            vendorBeanObj.setOffice_Code(results.getString("office_code"));
             vendorBeanObj.setempName(results.getString("AUTH_employee_name"));
            vendorBeanObj.setParent_Office_Code(results.getString("parent_office_code"));
           vendorBeanObj.setDaysDelayed(results.getString("days_delayed"));
           vendorBeanObj.setLocation(results.getString("LOCATION"));
            vendorBeanObj.setSelectedModule(results.getString("MODULE"));
            vendorBeanObj.setSelectedModuleType(results.getString("MODULE_TYPE"));
           vendorBeanObj.setSaveFlag(results.getString("save_flag"));
            vendorBeanObj.setHigherAuthsmsFlag(results.getString("higher_auth_sms_sent_flag"));
            vendorBeanObj.setsmsFlag(results.getString("INV_SUBMIT_EMPSMS_SENT_FLAG"));
              vendorBeanObj.setPurchasing_group(results.getString("PURCHASING_GROUP"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetAuthContactListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            
            sql.append(" select appl_id,po_number,MODULE_TYPE,invoice_number,vendor_number,vendor_name,updated_time_stamp,office_code ,parent_office_code,higher_auth_sms_sent_flag,INV_SUBMIT_EMPSMS_SENT_FLAG,AUTH_employee_name,PURCHASING_GROUP,  ");
            sql.append("  to_char(updated_time_stamp, 'yyyy-mm-dd')days_delayed ,LOCATION,MODULE,module_type,  ");
            sql.append(" save_flag from xxmis_erp_vendor_dtl where save_flag='20'   ");
            sql.append(" UNION ");
            sql.append(" select appl_id,PROJECT_ID,MODULE_TYPE,invoice_number,vendor_number,vendor_name,updated_time_stamp,office_code ,parent_office_code,higher_auth_sms_sent_flag,INV_SUBMIT_EMPSMS_SENT_FLAG,AUTH_employee_name,PURCHASING_GROUP ,  ");
            sql.append("  to_char(updated_time_stamp, 'yyyy-mm-dd')days_delayed ,LOCATION,MODULE,module_type,  ");
            sql.append(" save_flag from xxmis_erp_PS_vendor_dtl where save_flag='20'   ");
      
            logger.log(Level.INFO, "GetHigherContactLIstQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            
                        
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetAuthContactListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}

