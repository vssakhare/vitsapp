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
 * @author pooja
 */
public class GetSummaryListQueryHelper_new implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetSummaryListQueryHelper_new.class);
    private VendorBean vendorBean;
    private VendorPrezData vendorPrezDataObj;

    public GetSummaryListQueryHelper_new(VendorBean vendorBean) {
        this.vendorBean = vendorBean;
    }

    public GetSummaryListQueryHelper_new(VendorPrezData vendorPrezDataObj) {
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
            //vendorBeanObj.setP_Acc_MORETHAN30DAYS(results.getString("P_Acc_MORETHAN30DAYS"));
            //  vendorBeanObj.setP_Acc_LESSTHAN30DAYS(results.getString("P_Acc_LESSTHAN30DAYS"));
            vendorBeanObj.setP_Cash_MORE_THAN30DAYS(results.getString("P_Cash_MORE_THAN30DAYS"));
            vendorBeanObj.setP_Cash_LESS_THAN30DAYS(results.getString("P_Cash_LESS_THAN30DAYS"));
            vendorBeanObj.setPaid(results.getString("PAID"));
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
            if (vendorBean.getUserType().equals("Emp")) {
                sql.append(" select zone,circle,division,sum(P_Tech_MORE_THAN30DAYS) P_Tech_MORE_THAN30DAYS ,sum(P_TechLESSTHAN30DAYS) P_TechLESSTHAN30DAYS ,");
                sql.append(" SUM(P_Cash_MORE_THAN30DAYS) P_Cash_MORE_THAN30DAYS,SUM(P_Cash_LESS_THAN30DAYS)P_Cash_LESS_THAN30DAYS, SUM(PAID)PAID,(sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) +  SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS)) Total,sum(submitted)V_submit from ( ");
                sql.append("  select b.ZONE,  b.CIRCLE, b.DIVISION,");
                sql.append(" count(case when B.STATUS='Verified' AND B.INVOICE_NUMBER NOT IN(SELECT vendor_inv_no FROM XXMIS_ERP_AUTH_LIST ) AND  NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS, ");
                sql.append(" count(case when B.STATUS='Verified' AND B.INVOICE_NUMBER NOT IN(SELECT vendor_inv_no FROM XXMIS_ERP_AUTH_LIST) AND NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS , ");
                sql.append(" count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS, ");
                sql.append("  count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS,");
                sql.append("  count(case when a.STATUS ='Paid' THEN 1 END)PAID  ,");
                sql.append(" count(*) Total,   0 Submitted  ");

                sql.append(" from XXMIS_ERP_AUTH_LIST A right join (select distinct ZONE,CIRCLE,DIVISION,invoice_number,po_number,status,vendor_number,OFFICE_CODE,INVOICE_APPROVAL_DATE INVOICE_SUBMIT_DATE from XXMIS_ERP_AUTH_INPUT_LIST )B ");

                sql.append(" on A.VENDOR_NUMBER = B.VENDOR_NUMBER  AND A.OFFICE_CODE=B.OFFICE_CODE and b.invoice_number=a.vendor_inv_no AND A.po_NUMBER = B.po_NUMBER where  B.STATUS='Verified' AND B.OFFICE_CODE IN ( ");

                sql.append(" select h.organization_id from hr.hr_all_organization_units@DBLINK_HRMS h, hr.hr_all_organization_units@DBLINK_HRMS h1, hri.hri_org_hrchy_summary@DBLINK_HRMS hr  ");

                sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");

                sql.append("  and hr.organization_id = ? and h1.organization_id=hr.organization_id ");

                sql.append("  and h.type in ('HO','ZON','CIR','DIV') )  group by b.ZONE, b.CIRCLE, b.DIVISION  UNION   ");

                sql.append("  select B.ZONE,B.CIRCLE,B.DIVISION,0,0,0,0,0,0,count(case when A.status='Verified' then 1 end) Submitted  ");

                sql.append(" from XXMIS_ERP_VENDOR_INPUT_LIST A,(select distinct zone,circle,division,office_code,po_number,vendor_number from XXMIS_ERP_AUTH_LIST ) B   ");

                sql.append(" WHERE A.PO_NUMBER=B.PO_NUMBER and a.vendor_number=b.vendor_number and   ");

                sql.append(" B.OFFICE_CODE in  ( select h.organization_id from hr.hr_all_organization_units@DBLINK_HRMS h, hr.hr_all_organization_units@DBLINK_HRMS h1, hri.hri_org_hrchy_summary@DBLINK_HRMS hr ");

                sql.append("  where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");

                sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id and h.type in ('HO','ZON','CIR','DIV')) ");

                sql.append(" group by ZONE,CIRCLE,DIVISION UNION ");

                sql.append("  select ZONE,'' circle,'' DIVISION,sum(P_Tech_MORE_THAN30DAYS), sum(P_TechLESSTHAN30DAYS),SUM(P_Cash_MORE_THAN30DAYS),SUM(P_Cash_LESS_THAN30DAYS),SUM(PAID)PAID,");
                sql.append("  (sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS))total,0 SUBMITTED from ");
                sql.append("   (select upper(b.ZONE)||' TOTAL' zone, b.CIRCLE, b.DIVISION, ");
                sql.append("  count(case when B.STATUS='Verified' AND B.INVOICE_NUMBER NOT IN(SELECT vendor_inv_no FROM XXMIS_ERP_AUTH_LIST) AND  NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS,");
                sql.append("  count(case when B.STATUS='Verified' AND B.INVOICE_NUMBER NOT IN(SELECT vendor_inv_no FROM XXMIS_ERP_AUTH_LIST) AND NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS ,");
                sql.append("  count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(a.inv_dt)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS,");
                sql.append("   count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS,");
                sql.append("  count(case when a.STATUS ='Paid' THEN 1 END)PAID  ,");
                sql.append(" count(*) Total, ");
                sql.append(" 0 SUBMITTED from XXMIS_ERP_AUTH_LIST A right join (select distinct ZONE,CIRCLE,DIVISION,invoice_number,po_number,status,vendor_number,OFFICE_CODE,INVOICE_APPROVAL_DATE INVOICE_SUBMIT_DATE from XXMIS_ERP_AUTH_INPUT_LIST )B ");

                sql.append(" on A.VENDOR_NUMBER = B.VENDOR_NUMBER   AND A.OFFICE_CODE=B.OFFICE_CODE   and b.invoice_number=a.vendor_inv_no AND A.po_NUMBER = B.po_NUMBER where  B.STATUS='Verified' AND B.OFFICE_CODE IN (select h.organization_id from hr.hr_all_organization_units@DBLINK_HRMS h, ");

                sql.append("  hr.hr_all_organization_units@DBLINK_HRMS h1, hri.hri_org_hrchy_summary@DBLINK_HRMS hr where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");

                sql.append(" and hr.organization_id = ?  and h1.organization_id=hr.organization_id and h.type in ('HO','ZON','CIR','DIV') ) and b.ZONE IS NOT NULL  group by b.ZONE, b.CIRCLE, b.DIVISION)   ");

                sql.append(" group by rollup(zone) UNION  select ZONE,'' circle,'' DIVISION, 0 , 0 ,0,0,0,0,sum(submitted) from   ");

                sql.append("  (select ZONE||' TOTAL' zone, CIRCLE, DIVISION, 0 P_Tech_MORE_THAN30DAYS,0 P_Tech_LESS_THAN30DAYS, 0 P_Cash_MORE_THAN30DAYS,0 P_Cash_LESS_THAN30DAYS,0 PAID, 0 Total, count(case when A.status='Verified' then 1 end) Submitted ");

                sql.append("  FROM XXMIS_ERP_VENDOR_INPUT_LIST A,(select distinct zone,circle,division,office_code,po_number,vendor_number from XXMIS_ERP_AUTH_LIST ) B ");

                sql.append("  WHERE A.PO_NUMBER=B.PO_NUMBER and a.vendor_number=b.vendor_number and B.OFFICE_CODE in  ( select h.organization_id from hr.hr_all_organization_units@DBLINK_HRMS h, ");

                sql.append("  hr.hr_all_organization_units@DBLINK_HRMS h1, hri.hri_org_hrchy_summary@DBLINK_HRMS hr where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");

                sql.append("  and hr.organization_id = ? and h1.organization_id=hr.organization_id and h.type in ('HO','ZON','CIR','DIV'))  group by ZONE,CIRCLE,DIVISION)  ");

                sql.append(" group by rollup(zone) ) GROUP BY ZONE,CIRCLE,DIVISION ORDER BY ZONE  ");

                logger.log(Level.INFO, "GetSummaryListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

                statement = connection.prepareStatement(sql.toString());

                statement.setString(i++, vendorBean.getLocationId());
                statement.setString(i++, vendorBean.getLocationId());
                statement.setString(i++, vendorBean.getLocationId());
                statement.setString(i++, vendorBean.getLocationId());
            } else if (vendorBean.getUserType().equals("Vendor")) {

                sql.append(" select zone,circle,division,sum(P_Tech_MORE_THAN30DAYS) P_Tech_MORE_THAN30DAYS ,sum(P_TechLESSTHAN30DAYS) P_TechLESSTHAN30DAYS , ");
                sql.append(" SUM(P_Cash_MORE_THAN30DAYS) P_Cash_MORE_THAN30DAYS,SUM(P_Cash_LESS_THAN30DAYS)P_Cash_LESS_THAN30DAYS,SUM(PAID)PAID, (sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS)) Total,sum(submitted)V_submit from ( ");
                sql.append("  select B.ZONE,  B.CIRCLE, B.DIVISION,");
                sql.append(" count(case when B.STATUS='Verified' AND B.INVOICE_NUMBER NOT IN(SELECT vendor_inv_no FROM XXMIS_ERP_AUTH_LIST WHERE VENDOR_NUMBER =? ) AND  NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS, ");
                sql.append(" count(case when B.STATUS='Verified'  AND B.INVOICE_NUMBER NOT IN(SELECT vendor_inv_no FROM XXMIS_ERP_AUTH_LIST WHERE VENDOR_NUMBER =? ) AND NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS , ");
                sql.append("  count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS, ");
                sql.append(" count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS,");
                sql.append("   count(case when a.STATUS ='Paid' THEN 1 END)PAID, ");
                sql.append(" count(*) Total,0 SUBMITTED   ");
                sql.append(" from XXMIS_ERP_AUTH_LIST A right join (select distinct ZONE,CIRCLE,DIVISION, invoice_number, po_number,status,vendor_number,INVOICE_APPROVAL_DATE INVOICE_SUBMIT_DATE from XXMIS_ERP_AUTH_INPUT_LIST ) B  on  A.VENDOR_NUMBER = B.VENDOR_NUMBER  and A.VENDOR_NUMBER = ?  and b.invoice_number=a.vendor_inv_no AND A.po_NUMBER = B.po_NUMBER  where  B.STATUS='Verified' group by B.ZONE,B.CIRCLE, B.DIVISION  UNION   ");

                sql.append("  select B.ZONE,B.CIRCLE,B.DIVISION,0,0,0,0,0,0,count(case when A.status='Verified' then 1 end) Submitted  ");

                sql.append(" from XXMIS_ERP_VENDOR_INPUT_LIST A,(select distinct zone,circle,division,office_code,po_number,vendor_number from XXMIS_ERP_AUTH_LIST ) B   ");

                sql.append(" WHERE A.PO_NUMBER=B.PO_NUMBER and a.vendor_number=b.vendor_number and   ");

                sql.append(" B.VENDOR_NUMBER = ?   ");

                sql.append(" group by ZONE,CIRCLE,DIVISION UNION ");

                sql.append("  select ZONE,'' circle,'' DIVISION, sum(P_Tech_MORE_THAN30DAYS), sum(P_TechLESSTHAN30DAYS),SUM(P_Cash_MORE_THAN30DAYS),SUM(P_Cash_LESS_THAN30DAYS),SUM(PAID)PAID, ");
                sql.append("  (sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) +  SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS))total,0 SUBMITTED from ");
                sql.append("   (select upper(B.ZONE)||' TOTAL' zone, B.CIRCLE, B.DIVISION, ");
                sql.append("  count(case WHEN B.STATUS='Verified' AND  B.INVOICE_NUMBER NOT IN(SELECT vendor_inv_no FROM XXMIS_ERP_AUTH_LIST WHERE VENDOR_NUMBER =? )  AND  NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS, ");
                sql.append("  count(case when B.STATUS='Verified' AND B.INVOICE_NUMBER NOT IN(SELECT vendor_inv_no FROM XXMIS_ERP_AUTH_LIST WHERE VENDOR_NUMBER =? )  AND NVL((trunc(SYSDATE) - trunc(b.INVOICE_SUBMIT_DATE)),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS , ");
                sql.append("  count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(a.inv_dt)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS,");
                sql.append("   count(case when a.STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS, ");
                sql.append("   count(case when a.STATUS ='Paid' THEN 1 END)PAID, ");
                sql.append(" count(*) Total,0 SUBMITTED  from ");

                sql.append(" XXMIS_ERP_AUTH_LIST A right join (select distinct ZONE,CIRCLE,DIVISION,invoice_number,po_number,status,vendor_number,INVOICE_APPROVAL_DATE INVOICE_SUBMIT_DATE from XXMIS_ERP_AUTH_INPUT_LIST ) B  on  A.VENDOR_NUMBER = B.VENDOR_NUMBER  and A.VENDOR_NUMBER = ?  AND A.po_NUMBER = B.po_NUMBER and b.invoice_number=a.vendor_inv_no  where B.STATUS='Verified' group by B.ZONE, B.CIRCLE, B.DIVISION)   ");

                sql.append(" group by rollup(zone) UNION  select ZONE,'' circle,'' DIVISION, 0  , 0 ,0,0,0,0,sum(submitted) from   ");
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
