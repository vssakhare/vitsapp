package in.emp.vendor.dao.helper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class GetOfficeCodeDetailsQueryHelper implements QueryHelper {
    private static Logger logger = Logger.getLogger(GetOfficeCodeDetailsQueryHelper.class);
    private POBean poBean;
    private VendorPrezData vendorPrezDataObj;

    public GetOfficeCodeDetailsQueryHelper(POBean poBean) {
        this.poBean = poBean;
    }

    public GetOfficeCodeDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }
    
    public Object getDataObject(ResultSet results) throws Exception {
        POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "GetOfficeCodeDetailsQueryHelper ::: getDataObject() :: method called ::");
      
                       if(poBean.getFormStatus().equals("Submit"))
                       { poBeanObj.setApplId(results.getString("APPL_ID"));  
                       }
            poBeanObj.setPlant(results.getString("PLANT"));
                       
            poBeanObj.setOfficeCode(results.getString("OFFICECODE"));
            poBeanObj.setParentOfficeCode(results.getString("PARENTOFFICECODE"));
            poBeanObj.setOrgUnit(results.getString("ORGUNIT"));
           

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetOfficeCodeDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj;
    }
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            if(poBean.getFormStatus().equals("Submit")){
            if(poBean.getSelectedModule().equals("PS")){//to get the office code of 'submit at location' plant
                sql.append(" select  p.APPL_ID ,p.SUBMIT_AT_LOCATION PLANT,o.office_code OFFICECODE,o.parent_office_code PARENTOFFICECODE,o.org_unit ORGUNIT");
            sql.append(" from XXMIS_ERP_PS_VENDOR_DTL p,organisation_master o  ");
            sql.append(" where APPL_ID= ?  and p.SUBMIT_AT_LOCATION=o.plant ");
            
            }
            else{ //to get the office code of 'submit at location' plant
                sql.append(" select ' ' APPL_ID,o.plant,o.office_code OFFICECODE,o.parent_office_code PARENTOFFICECODE,o.org_unit ORGUNIT");
            sql.append(" from organisation_master o  ");
            sql.append(" where o.plant =? ");
            }
            }
            else{
                //to get the office code of 'forward at location' plant
                sql.append(" select o.plant,o.office_code OFFICECODE,o.parent_office_code PARENTOFFICECODE,o.org_unit ORGUNIT");
            sql.append(" from organisation_master o  ");
            sql.append(" where o.plant =? ");
              
            }
            logger.log(Level.INFO, "GetOfficeCodeDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
          if(poBean.getFormStatus().equals("Submit")){
               if(poBean.getSelectedModule().equals("PS")){
                statement.setString(1,poBean.getApplId());    
               }else{
                  statement.setString(1,poBean.getPlant());   
               }
                
          }
          else{
               statement.setString(1,poBean.getPlant()); 
          }
            
                       
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetOfficeCodeDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
