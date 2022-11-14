    /*
    * To change this template, choose Tools | Templates
    * and open the template in the editor.
    */
    package in.emp.vendor.dao.helper;

import in.emp.vendor.dao.helper.listHelper.GetVendorInputListQueryHelper;
    import in.emp.common.ApplicationConstants;
    import in.emp.dao.QueryHelper;
    import in.emp.util.ApplicationUtils;
    import in.emp.vendor.bean.VendorInputBean;
    import in.emp.vendor.bean.VendorPrezData;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import org.apache.log4j.Level;
    import org.apache.log4j.Logger;

    /**
    *
    * @author Prajakta
    */
    public class GetTableListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorInputListQueryHelper.class);
    private VendorInputBean vendorInputBean;
    private VendorPrezData vendorPrezDataObj;

    public GetTableListQueryHelper(VendorInputBean vendorInputBean) {
        this.vendorInputBean = vendorInputBean;
    }

    public GetTableListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorInputBean = this.vendorPrezDataObj.getVendorInputBean();
    }

    public Object getDataObject(ResultSet results) throws Exception {
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        try {
            logger.log(Level.INFO, "GetTableListQueryHelper ::: getDataObject() :: method called ::");

            vendorInputBeanObj.setApplId(results.getString("APPL_ID")); 
            vendorInputBeanObj.setApplDate(results.getDate("APPL_DATE"));

            vendorInputBeanObj.setPONumber(results.getString("PO_NUMBER"));
            vendorInputBeanObj.setPODesc(results.getString("PO_DESC"));

            vendorInputBeanObj.setVendorInvoiceAmount(results.getString("INVOICE_AMOUNT"));
            vendorInputBeanObj.setVendorInvoiceNumber(results.getString("INVOICE_NUMBER"));  
            vendorInputBeanObj.setVendorInvoiceDate(results.getDate("INVOICE_DATE"));
           
    
            vendorInputBeanObj.setSaveFlag(results.getString("STATUS"));
  
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorInputListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInputBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i = 1;
        try {
            logger.log(Level.INFO, "GetTableListQueryHelper ::: getQueryResults() :: method called ::");

           if(vendorInputBean.getUserType().equals("Vendor")){
            sql.append("select APPL_ID,APPL_DATE,PO_NUMBER,PO_DESC,INVOICE_AMOUNT,INVOICE_NUMBER,INVOICE_DATE,STATUS from ");
            sql.append(" (SELECT EVIL.APPL_ID, TO_DATE(TO_CHAR(EVIL.CREATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') APPL_DATE, EVIL.PO_NUMBER, EVIL.PO_DESC, EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE,EVIL.MODULE_SAVE_FLAG, ");//to differentiate between proj id and po number 
            sql.append(" EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_DATE, ");  
           sql.append(" EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INWARD_DATE, "); 
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_FROM_DT, ");         
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_TO_DT, EVIL.STATUS, ");
           sql.append(" case when STATUS = 'Submitted' THEN TO_NUMBER(trunc(sysdate) - to_date(to_char(nvl(INVOICE_RESUBMIT_DATE,INVOICE_SUBMIT_DATE), 'yyyy-mm-dd'),'yyyy-mm-dd')) else null end PENDING_SINCE ");
           sql.append(" FROM XXMIS_ERP_VENDOR_INPUT_LIST EVIL ");
           sql.append(" WHERE NVL(EVIL.STATUS,'Saved') IN ('Submitted','Verified','Rejected','Completed')" );
           sql.append(" AND TO_NUMBER(EVIL.VENDOR_NUMBER) = ? ");
       sql.append(" UNION ALL ");
        sql.append(" SELECT EVIL.APPL_ID, TO_DATE(TO_CHAR(EVIL.CREATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') APPL_DATE, EVIL.PROJECT_ID, EVIL.PROJECT_DESC, EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE,EVIL.MODULE_SAVE_FLAG, ");//to differentiate between proj id and po number 
            sql.append(" EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_DATE, ");  
           sql.append(" EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INWARD_DATE, "); 
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_FROM_DT, ");         
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_TO_DT, EVIL.STATUS, ");
           sql.append(" case when STATUS = 'Submitted' THEN TO_NUMBER(trunc(sysdate) - to_date(to_char(nvl(INVOICE_RESUBMIT_DATE,INVOICE_SUBMIT_DATE), 'yyyy-mm-dd'),'yyyy-mm-dd')) else null end PENDING_SINCE ");
           sql.append(" FROM XXMIS_ERP_PS_VENDOR_INPUT_LIST EVIL ");
            sql.append(" WHERE NVL(EVIL.STATUS,'Saved') IN ('Submitted','Verified','Rejected','Completed')" );
           sql.append(" AND TO_NUMBER(EVIL.VENDOR_NUMBER) = ? )");
         sql.append(" where rownum<=5");
         
        sql.append(" ORDER BY  APPL_ID DESC,STATUS ");

           logger.log(Level.INFO, "GetVendorInputListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
      statement.setString(i++, vendorInputBean.getVendorNumber());
        statement.setString(i++, vendorInputBean.getVendorNumber());
   
    }
                if(vendorInputBean.getUserType().equals("Emp")){
           sql.append("select APPL_ID,APPL_DATE,PO_NUMBER,PO_DESC,INVOICE_AMOUNT,INVOICE_NUMBER,INVOICE_DATE,STATUS from ");
            sql.append("( SELECT EVIL.APPL_ID, TO_DATE(TO_CHAR(EVIL.CREATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') APPL_DATE,TO_DATE(TO_CHAR(EVIL.UPDATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') CHANGED_DATE, EVIL.PO_NUMBER, EVIL.PO_DESC, EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE, ");
           sql.append(" EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_DATE, ");         
           sql.append(" EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INWARD_DATE, "); 
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_FROM_DT, ");   
           sql.append(" TO_NUMBER(trunc(sysdate) - to_date(to_char(nvl(INVOICE_RESUBMIT_DATE,INVOICE_SUBMIT_DATE), 'yyyy-mm-dd'),'yyyy-mm-dd'))PENDING_SINCE, ");
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_TO_DT, EVIL.STATUS, EVIL.VENDOR_NUMBER, EVIL.VENDOR_NAME,nvl(NVL(DIVISION,CIRCLE),zone)LOCATION  ");         
           sql.append(" FROM XXMIS_ERP_AUTH_INPUT_LIST EVIL  ");            
           sql.append(" WHERE EVIL.OFFICE_CODE IN (select h.organization_id from hr.hr_all_organization_units@DBLINK_HRMS h, ");
           sql.append(" hr.hr_all_organization_units@DBLINK_HRMS h1, hri.hri_org_hrchy_summary@DBLINK_HRMS hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id ) ");
       
          sql.append(" AND NVL(EVIL.STATUS,'Saved') IN ('Submitted','Verified','Rejected','Completed')" );
        //sql.append(" ORDER BY EVIL.CREATED_TIME_STAMP DESC ");
        sql.append("UNION ALL");
        sql.append(" SELECT EVIL.APPL_ID, TO_DATE(TO_CHAR(EVIL.CREATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') APPL_DATE,TO_DATE(TO_CHAR(EVIL.UPDATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') CHANGED_DATE, EVIL.PROJECT_ID, EVIL.PROJECT_DESC, EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE, ");
           sql.append(" EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_DATE, ");         
           sql.append(" EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INWARD_DATE, "); 
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_FROM_DT, ");   
           sql.append(" TO_NUMBER(trunc(sysdate) - to_date(to_char(nvl(INVOICE_RESUBMIT_DATE,INVOICE_SUBMIT_DATE), 'yyyy-mm-dd'),'yyyy-mm-dd'))PENDING_SINCE, ");
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_TO_DT, EVIL.STATUS, EVIL.VENDOR_NUMBER, EVIL.VENDOR_NAME,nvl(NVL(DIVISION,CIRCLE),zone)LOCATION  ");         
           sql.append(" FROM XXMIS_ERP_PS_AUTH_INPUT_LIST EVIL  ");            
           sql.append(" WHERE EVIL.OFFICE_CODE IN (select h.organization_id from hr.hr_all_organization_units@DBLINK_HRMS h, ");
           sql.append(" hr.hr_all_organization_units@DBLINK_HRMS h1, hri.hri_org_hrchy_summary@DBLINK_HRMS hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id ) ");
       
          sql.append(" AND NVL(EVIL.STATUS,'Saved') IN ('Submitted','Verified','Rejected','Completed'))" );
           sql.append(" where rownum<=5");
        sql.append(" ORDER BY APPL_ID DESC ");
           logger.log(Level.INFO, "GetVendorInputListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            
            statement.setString(i++, vendorInputBean.getLocationId());
            statement.setString(i++, vendorInputBean.getLocationId());
        
    }
           rs = statement.executeQuery();
       
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorInputListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
    }
