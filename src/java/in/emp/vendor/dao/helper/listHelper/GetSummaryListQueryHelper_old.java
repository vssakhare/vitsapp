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

/**
 *
 * @author Prajakta
 */
public class GetSummaryListQueryHelper_old implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetSummaryListQueryHelper.class);
    private VendorBean vendorBean;
    private VendorPrezData vendorPrezDataObj;

    public GetSummaryListQueryHelper_old(VendorBean vendorBean) { 
        this.vendorBean = vendorBean;
    }

    public GetSummaryListQueryHelper_old(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorBean = this.vendorPrezDataObj.getVendorBean();
    }

    public Object getDataObject(ResultSet results) throws Exception {
        VendorBean vendorBeanObj = new VendorBean();
        try {
            logger.log(Level.INFO, "GetSummaryListQueryHelper ::: getDataObject() :: method called ::");
             
            vendorBeanObj.setZone(results.getString("ZONE")); 
            vendorBeanObj.setCircle(results.getString("CIRCLE")); 
            vendorBeanObj.setDivision(results.getString("DIVISION"));             
            vendorBeanObj.setP_Tech_MORE_THAN30DAYS(results.getString("P_Tech_MORE_THAN30DAYS"));
            vendorBeanObj.setP_TechLESSTHAN30DAYS(results.getString("P_TechLESSTHAN30DAYS"));
            vendorBeanObj.setP_Acc_MORETHAN30DAYS(results.getString("P_Acc_MORETHAN30DAYS"));
            vendorBeanObj.setP_Acc_LESSTHAN30DAYS(results.getString("P_Acc_LESSTHAN30DAYS"));
            vendorBeanObj.setP_Cash_MORE_THAN30DAYS(results.getString("P_Cash_MORE_THAN30DAYS"));
            vendorBeanObj.setP_Cash_LESS_THAN30DAYS(results.getString("P_Cash_LESS_THAN30DAYS"));
            vendorBeanObj.setpTot(results.getString("Total"));
            vendorBeanObj.setvSubmit(results.getString("V_submit"));
            
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetSummaryListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i = 1; 
        try {
            logger.log(Level.INFO, "GetSummaryListQueryHelper ::: getQueryResults() :: method called ::");

     
//           sql.append(" select a.ZONE,  a.CIRCLE, a.DIVISION, ");
//           sql.append(" count(case when a.STATUS ='Pending with Technical' THEN 1 END) P_Tech, ");         
//           sql.append(" count(case when a.STATUS ='Pending with Accounts' THEN 1 END) P_Acc, ");
//           sql.append(" count(case when a.STATUS ='Pending with Cashier' THEN 1 END) P_Cash, count(*) Total ");            
//           sql.append(" from ERPPORTAL.XXMIS_ERP_AUTH_LIST A ");
//           sql.append(" WHERE A.OFFICE_CODE IN (select h.organization_id from hr.hr_all_organization_units h,  ");
//           sql.append(" hr.hr_all_organization_units h1, hri.hri_org_hrchy_summary hr  ");
//           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'  ");
//           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id   "); 
//           sql.append(" and h.type in ('HO','ZON','CIR','DIV') )   "); 
//           sql.append(" group by a.ZONE, a.CIRCLE, a.DIVISION UNION   ");
//           sql.append(" select ZONE,'' circle,'' DIVISION, sum(P_Tech), sum(P_Acc), sum(P_Cash), (sum(P_Tech) + sum(P_Acc) + sum(P_Cash)) total  from   ");
//           sql.append(" (select a.ZONE||' TOTAL' zone, a.CIRCLE, a.DIVISION, ");
//           sql.append(" count(case when a.STATUS ='Pending with Technical' THEN 1 END) P_Tech, ");         
//           sql.append(" count(case when a.STATUS ='Pending with Accounts' THEN 1 END) P_Acc, ");
//           sql.append(" count(case when a.STATUS ='Pending with Cashier' THEN 1 END) P_Cash, count(*) Total ");            
//           sql.append(" from ERPPORTAL.XXMIS_ERP_AUTH_LIST A ");
//           sql.append(" WHERE A.OFFICE_CODE IN (select h.organization_id from hr.hr_all_organization_units h,  ");
//           sql.append(" hr.hr_all_organization_units h1, hri.hri_org_hrchy_summary hr  ");
//           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'  ");
//           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id   "); 
//           sql.append(" and h.type in ('HO','ZON','CIR','DIV') )   "); 
//           sql.append(" group by a.ZONE, a.CIRCLE, a.DIVISION) group by rollup(zone)   ");
            
            if(vendorBean.getUserType().equals("Emp"))
            {
            sql.append(" select zone,circle,division,sum(P_Tech_MORE_THAN30DAYS) P_Tech_MORE_THAN30DAYS ,sum(P_TechLESSTHAN30DAYS) P_TechLESSTHAN30DAYS ,sum(P_Acc_MORETHAN30DAYS) P_Acc_MORETHAN30DAYS ,SUM(P_Acc_LESSTHAN30DAYS)P_Acc_LESSTHAN30DAYS, ");
            sql.append( "SUM(P_Cash_MORE_THAN30DAYS) P_Cash_MORE_THAN30DAYS,SUM(P_Cash_LESS_THAN30DAYS)P_Cash_LESS_THAN30DAYS, (sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + sum(P_Acc_MORETHAN30DAYS) + sum(P_Acc_LESSTHAN30DAYS)+ SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS)) Total,sum(submitted)V_submit from ( ");
           sql.append("  select a.ZONE,  a.CIRCLE, a.DIVISION,");
           sql.append(" count(case when a.STATUS ='Technical' AND  NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS, ");
           sql.append(" count(case when a.STATUS ='Technical' AND NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS , ");
          sql.append(" count(case when a.STATUS ='Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)>30 THEN 1 END) P_Acc_MORETHAN30DAYS, ");
          sql.append(" count(case when a.STATUS ='Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)<=30 THEN 1 END) P_Acc_LESSTHAN30DAYS, ");

          sql.append(" count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS, ");
             sql.append("  count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS, count(*) Total,   0 Submitted  ");  
           
           sql.append(" from XXMIS_ERP_AUTH_LIST A, (select distinct po_number,status,vendor_number,OFFICE_CODE,MIN(INVOICE_APPROVAL_DATE)INVOICE_SUBMIT_DATE from XXMIS_ERP_VENDOR_INPUT_LIST GROUP BY po_number,status,vendor_number,OFFICE_CODE)B ");

            sql.append(" WHERE A.VENDOR_NUMBER = B.VENDOR_NUMBER  AND B.STATUS='Verified'  AND A.po_NUMBER = B.po_NUMBER AND A.OFFICE_CODE IN ( ");
           
           sql.append(" select h.organization_id from hr.hr_all_organization_units@DBLINK_HRMS h, hr.hr_all_organization_units@DBLINK_HRMS h1, hri.hri_org_hrchy_summary@DBLINK_HRMS hr  ");
           
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           
           sql.append("  and hr.organization_id = ? and h1.organization_id=hr.organization_id ");
           
           sql.append("  and h.type in ('HO','ZON','CIR','DIV') ) AND A.OFFICE_CODE=B.OFFICE_CODE group by a.ZONE, a.CIRCLE, a.DIVISION  UNION   "); 
           
           sql.append("  select B.ZONE,B.CIRCLE,B.DIVISION,0,0,0,0,0,0,0,count(case when A.status='Verified' then 1 end) Submitted  "); 
           
           sql.append(" from XXMIS_ERP_VENDOR_INPUT_LIST A,(select distinct zone,circle,division,office_code,po_number,vendor_number from XXMIS_ERP_AUTH_LIST ) B   ");
           
           sql.append(" WHERE A.PO_NUMBER=B.PO_NUMBER and a.vendor_number=b.vendor_number and  A.status='Verified' and   ");
           
           sql.append(" B.OFFICE_CODE in  ( select h.organization_id from hr.hr_all_organization_units@DBLINK_HRMS h, hr.hr_all_organization_units@DBLINK_HRMS h1, hri.hri_org_hrchy_summary@DBLINK_HRMS hr ");
           
           sql.append("  where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' "); 
           
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id and h.type in ('HO','ZON','CIR','DIV')) ");
           
           sql.append(" group by B.ZONE,B.CIRCLE,B.DIVISION UNION ");      
           
           sql.append("  select ZONE,'' circle,'' DIVISION,sum(P_Tech_MORE_THAN30DAYS), sum(P_TechLESSTHAN30DAYS), sum(P_Acc_MORETHAN30DAYS),SUM(P_Acc_LESSTHAN30DAYS),SUM(P_Cash_MORE_THAN30DAYS),SUM(P_Cash_LESS_THAN30DAYS),");
 sql.append("  (sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + sum(P_Acc_MORETHAN30DAYS) + sum(P_Acc_LESSTHAN30DAYS)+ SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS))total,0 SUBMITTED from ");
 sql.append("   (select upper(a.ZONE)||' TOTAL' zone, a.CIRCLE, a.DIVISION, ");
 sql.append("  count(case when a.STATUS ='Technical' AND  NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS,");
 sql.append("  count(case when a.STATUS ='Technical' AND NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS ,");
 sql.append("  count(case when a.STATUS ='Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)>30 THEN 1 END) P_Acc_MORETHAN30DAYS,");
 sql.append(" count(case when a.STATUS ='Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)<=30 THEN 1 END) P_Acc_LESSTHAN30DAYS,");
 sql.append("  count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(a.inv_dt)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS,");
 sql.append("   count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS, count(*) Total, ");
           sql.append(" 0 SUBMITTED from XXMIS_ERP_AUTH_LIST A , (select distinct po_number,status,vendor_number,OFFICE_CODE,MIN(INVOICE_APPROVAL_DATE)INVOICE_SUBMIT_DATE from XXMIS_ERP_VENDOR_INPUT_LIST GROUP BY po_number,status,vendor_number,OFFICE_CODE)B ");

sql.append(" WHERE A.VENDOR_NUMBER = B.VENDOR_NUMBER  AND B.STATUS='Verified'  AND A.po_NUMBER = B.po_NUMBER AND A.OFFICE_CODE IN (select h.organization_id from hr.hr_all_organization_units@DBLINK_HRMS h, ");
           
           sql.append("  hr.hr_all_organization_units@DBLINK_HRMS h1, hri.hri_org_hrchy_summary@DBLINK_HRMS hr where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' "); 
           
           sql.append(" and hr.organization_id = ?  and h1.organization_id=hr.organization_id and h.type in ('HO','ZON','CIR','DIV') ) and ZONE IS NOT NULL AND A.OFFICE_CODE=B.OFFICE_CODE  group by a.ZONE, a.CIRCLE, a.DIVISION)   "); 
           
           sql.append(" group by rollup(zone) UNION  select ZONE,'' circle,'' DIVISION, 0 ,0 , 0 ,0,0,0,0,sum(submitted) from   ");
           
           
          sql.append("  (select B.ZONE||' TOTAL' zone, B.CIRCLE, B.DIVISION, 0 P_Tech_MORE_THAN30DAYS,0 P_Tech_LESS_THAN30DAYS, 0 P_Acc_MORETHAN30DAYS,0 P_Acc_LESSTHAN30DAYS, 0 P_Cash_MORE_THAN30DAYS,0 P_Cash_LESS_THAN30DAYS, 0 Total, count(case when A.status='Verified' then 1 end) Submitted ");
           
           sql.append("  FROM XXMIS_ERP_VENDOR_INPUT_LIST A,(select distinct zone,circle,division,office_code,po_number,vendor_number from XXMIS_ERP_AUTH_LIST ) B ");
           
           sql.append("  WHERE A.PO_NUMBER=B.PO_NUMBER and  A.status='Verified' and a.vendor_number=b.vendor_number and B.OFFICE_CODE in  ( select h.organization_id from hr.hr_all_organization_units@DBLINK_HRMS h, ");
           
           sql.append("  hr.hr_all_organization_units@DBLINK_HRMS h1, hri.hri_org_hrchy_summary@DBLINK_HRMS hr where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' "); 
           
           sql.append("  and hr.organization_id = ? and h1.organization_id=hr.organization_id and h.type in ('HO','ZON','CIR','DIV'))  group by B.ZONE,B.CIRCLE,B.DIVISION)  "); 
           
           sql.append(" group by rollup(zone) ) GROUP BY ZONE,CIRCLE,DIVISION ORDER BY ZONE  ");
           
           
           logger.log(Level.INFO, "GetSummaryListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
      
             statement.setString(i++, vendorBean.getLocationId());
             statement.setString(i++, vendorBean.getLocationId());
             statement.setString(i++, vendorBean.getLocationId());
             statement.setString(i++, vendorBean.getLocationId());
            }
            else  if(vendorBean.getUserType().equals("Vendor"))
            {
           
                sql.append(" select zone,circle,division,sum(P_Tech_MORE_THAN30DAYS) P_Tech_MORE_THAN30DAYS ,sum(P_TechLESSTHAN30DAYS) P_TechLESSTHAN30DAYS ,sum(P_Acc_MORETHAN30DAYS) P_Acc_MORETHAN30DAYS ,SUM(P_Acc_LESSTHAN30DAYS)P_Acc_LESSTHAN30DAYS, ");
                sql.append(" SUM(P_Cash_MORE_THAN30DAYS) P_Cash_MORE_THAN30DAYS,SUM(P_Cash_LESS_THAN30DAYS)P_Cash_LESS_THAN30DAYS, (sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + sum(P_Acc_MORETHAN30DAYS) + sum(P_Acc_LESSTHAN30DAYS)+ SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS)) Total,sum(submitted)V_submit from ( ");
                sql.append("  select a.ZONE,  a.CIRCLE, a.DIVISION, count(case when a.STATUS ='Technical' AND  NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS, ");
                sql.append(" count(case when a.STATUS ='Technical' AND NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS , ");
                sql.append("  count(case when a.STATUS ='Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)>30 THEN 1 END) P_Acc_MORETHAN30DAYS, ");
                sql.append(" count(case when a.STATUS ='Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)<=30 THEN 1 END) P_Acc_LESSTHAN30DAYS, ");

                sql.append("  count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS, ");
                sql.append(" count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS, count(*) Total,      0 Submitted ");

                sql.append(" from XXMIS_ERP_AUTH_LIST A, (select distinct po_number,status,vendor_number,MIN(INVOICE_APPROVAL_DATE)INVOICE_SUBMIT_DATE from XXMIS_ERP_VENDOR_INPUT_LIST GROUP BY po_number,status,vendor_number) B  where  A.VENDOR_NUMBER = B.VENDOR_NUMBER  and A.VENDOR_NUMBER = ? AND B.STATUS='Verified'  AND A.po_NUMBER = B.po_NUMBER group by a.ZONE, a.CIRCLE, a.DIVISION  UNION   ");

                sql.append("  select B.ZONE,B.CIRCLE,B.DIVISION,0,0,0,0,0,0,0,count(case when A.status='Verified' then 1 end) Submitted  ");

                sql.append(" from XXMIS_ERP_VENDOR_INPUT_LIST A,(select distinct zone,circle,division,office_code,po_number,vendor_number from XXMIS_ERP_AUTH_LIST ) B   ");

                sql.append(" WHERE A.PO_NUMBER=B.PO_NUMBER and a.vendor_number=b.vendor_number and   ");

                sql.append(" B.VENDOR_NUMBER = ?   ");

                sql.append(" group by ZONE,CIRCLE,DIVISION UNION ");

                sql.append("  select ZONE,'' circle,'' DIVISION, sum(P_Tech_MORE_THAN30DAYS), sum(P_TechLESSTHAN30DAYS), sum(P_Acc_MORETHAN30DAYS),SUM(P_Acc_LESSTHAN30DAYS),SUM(P_Cash_MORE_THAN30DAYS),SUM(P_Cash_LESS_THAN30DAYS),");
                sql.append("  (sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + sum(P_Acc_MORETHAN30DAYS) + sum(P_Acc_LESSTHAN30DAYS)+ SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS))total,0 SUBMITTED from ");
                sql.append("   (select upper(a.ZONE)||' TOTAL' zone, a.CIRCLE, a.DIVISION, ");
                sql.append("  count(case when a.STATUS ='Technical' AND  NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS, ");
                sql.append("  count(case when a.STATUS ='Technical' AND NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS , ");
                sql.append("  count(case when a.STATUS ='Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)>30 THEN 1 END) P_Acc_MORETHAN30DAYS,");
                sql.append(" count(case when a.STATUS ='Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)<=30 THEN 1 END) P_Acc_LESSTHAN30DAYS,");
                sql.append("  count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(a.inv_dt)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS,");
                sql.append("   count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS, count(*) Total,0 SUBMITTED  from ");

              

                sql.append(" XXMIS_ERP_AUTH_LIST A, (select distinct po_number,status,vendor_number,MIN(INVOICE_APPROVAL_DATE)INVOICE_SUBMIT_DATE from XXMIS_ERP_VENDOR_INPUT_LIST GROUP BY po_number,status,vendor_number) B  where  A.VENDOR_NUMBER = B.VENDOR_NUMBER  and A.VENDOR_NUMBER = ? AND B.STATUS='Verified'  AND A.po_NUMBER = B.po_NUMBER  group by a.ZONE, a.CIRCLE, a.DIVISION)   ");

                sql.append(" group by rollup(zone) UNION  select ZONE,'' circle,'' DIVISION, 0 ,0 , 0 ,0,0,0,0,sum(submitted) from   ");
                //////////////

                sql.append("  (select ZONE||' TOTAL' zone, CIRCLE, DIVISION, 0 P_Tech_MORE_THAN30DAYS,0 P_Tech_LESS_THAN30DAYS, 0 P_Acc_MORETHAN30DAYS,0 P_Acc_LESSTHAN30DAYS, 0 P_Cash_MORE_THAN30DAYS,0 P_Cash_LESS_THAN30DAYS, 0 Total, count(case when A.status='Verified' then 1 end) Submitted ");

                sql.append("  FROM XXMIS_ERP_VENDOR_INPUT_LIST A,(select distinct zone,circle,division,office_code,po_number,vendor_number from XXMIS_ERP_AUTH_LIST ) B ");

                sql.append("  WHERE A.PO_NUMBER=B.PO_NUMBER and a.vendor_number=b.vendor_number and B.VENDOR_NUMBER = ?  group by ZONE,CIRCLE,DIVISION)  ");

                sql.append(" group by rollup(zone) )   GROUP BY ZONE,CIRCLE,DIVISION ORDER BY ZONE  ");

                logger.log(Level.INFO, "GetSummaryListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
      
             statement.setString(i++, vendorBean.getVendorNumber());
             statement.setString(i++, vendorBean.getVendorNumber());
             statement.setString(i++, vendorBean.getVendorNumber());
             statement.setString(i++, vendorBean.getVendorNumber());
             
            }
            
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetSummaryListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
