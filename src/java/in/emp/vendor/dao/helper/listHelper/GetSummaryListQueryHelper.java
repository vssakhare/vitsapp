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
 * @author pooja jadhav
 */
public class GetSummaryListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetSummaryListQueryHelper.class);
    private VendorBean vendorBean;
    private VendorPrezData vendorPrezDataObj;

    public GetSummaryListQueryHelper(VendorBean vendorBean) { 
        this.vendorBean = vendorBean;
    }

    public GetSummaryListQueryHelper(VendorPrezData vendorPrezDataObj) {
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
            vendorBeanObj.setPaid(results.getString("Paid"));
            vendorBeanObj.setpTot(results.getString("Total"));
            vendorBeanObj.setvSubmit(results.getString("V_submit"));
            vendorBeanObj.setUNPAID_SUBMITTED(results.getString("unpaid_submitted"));
            
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

            if(vendorBean.getUserType().equals("Emp"))
            {
              sql.append("    select zone, circle, division, sum(P_Tech_MORE_THAN30DAYS) P_Tech_MORE_THAN30DAYS ,sum(P_TechLESSTHAN30DAYS) P_TechLESSTHAN30DAYS ,sum(P_Acc_MORETHAN30DAYS) P_Acc_MORETHAN30DAYS ,SUM(P_Acc_LESSTHAN30DAYS)P_Acc_LESSTHAN30DAYS,  						     ");
sql.append("        	SUM(P_Cash_MORE_THAN30DAYS) P_Cash_MORE_THAN30DAYS, 																											     ");
sql.append("            SUM(P_Cash_LESS_THAN30DAYS)P_Cash_LESS_THAN30DAYS, SUM(Paid)Paid,(sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + sum(P_Acc_MORETHAN30DAYS) + sum(P_Acc_LESSTHAN30DAYS)+ SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS)) Total, 			     ");
sql.append("            sum(submitted)V_submit,sum(unpaid_submitted)unpaid_submitted  from (  																									     ");
sql.append("           select a.ZONE,  a.CIRCLE, a.DIVISION , 																													     ");
sql.append("	           count(case when a.INVOICE_STATUS ='Pending With Technical' AND  NVL((trunc(SYSDATE) - trunc(A.INVOICE_APPROVAL_DATE)),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS,  													     ");
sql.append("	  	   count(case when a.INVOICE_STATUS ='Pending With Technical' AND NVL((trunc(SYSDATE) - trunc(A.INVOICE_APPROVAL_DATE)),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS ,  													     ");
sql.append("	  	   count(case when a.INVOICE_STATUS ='Pending With Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)>30 THEN 1 END) P_Acc_MORETHAN30DAYS,  															     ");
sql.append("	  	    count(case when a.INVOICE_STATUS ='Pending With Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)<=30 THEN 1 END) P_Acc_LESSTHAN30DAYS,  															     ");
sql.append("	  	   count(case when a.INVOICE_STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS,  															     ");
sql.append("	  	   count(case when a.INVOICE_STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS,															     ");
sql.append("	           count(case when a.INVOICE_STATUS ='Paid'  THEN 1 END) Paid, 																										     ");
sql.append("	           count(*) Total,   0 Submitted ,0 unpaid_submitted      from																										     ");
sql.append("	  ( select distinct a.ZONE,  a.CIRCLE, a.DIVISION ,a.INVOICE_STATUS	, A.INVOICE_APPROVAL_DATE,A.SORMDATE,a.inv_dt,a.appl_id																			     ");
sql.append("	  from SUMMARY_STATUS A left outer join PO_LINE_INV_DETAILS_org p            																									     ");
sql.append("	             on A.appl_id = p.appl_id																														     ");
sql.append("	             WHERE   A.STATUS IN ('Verified')  and 																												     ");
sql.append("	    (  A.OFFICE_CODE IN (  																															     ");
sql.append("	  	    select h.organization_id from hr_all_organization_units h, hr_all_organization_units h1, hri_org_hrchy_summary hr   																		     ");
sql.append("	  	    where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'  																						     ");
sql.append("	  	    and hr.organization_id = ? and h1.organization_id=hr.organization_id  																								     ");
sql.append("	  	   and h.type in ('HO','ZON','CIR','DIV') ) 																												     ");
sql.append("	         or																																	     ");
sql.append("	        p.OFFICE_CODE IN (  																															     ");
sql.append("	  	    select h.organization_id from hr_all_organization_units h, hr_all_organization_units h1, hri_org_hrchy_summary hr   																		     ");
sql.append("	  	    where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'  																						     ");
sql.append("	  	    and hr.organization_id = ? and h1.organization_id=hr.organization_id  																								     ");
sql.append("	  	   and h.type in ('HO','ZON','CIR','DIV') )   )																												     ");
sql.append("	         )A																																	     ");
sql.append("       group by a.ZONE, a.CIRCLE, a.DIVISION 																													     ");
sql.append("        	UNION     																																     ");
sql.append("          select A.ZONE,A.CIRCLE,A.DIVISION,0,0,0,0,0,0,0,0,count(case when A.STATUS='Verified' then 1 end) Submitted,count(case when A.STATUS='Verified' AND INVOICE_STATUS!='Paid' then 1 end) unpaid_Submitted      								     ");
sql.append("	 	from(																																	     ");
sql.append("	     select distinct A.ZONE,A.CIRCLE,A.DIVISION,A.STATUS,A.INVOICE_STATUS,a.appl_id     from SUMMARY_STATUS A left outer join PO_LINE_INV_DETAILS_org p  															     ");
sql.append("	     on a.appl_id = p.appl_id    																														     ");
sql.append("	 	    WHERE  A.STATUS='Verified' AND  																													     ");
sql.append("	 	    (A.OFFICE_CODE in  ( select h.organization_id from hr_all_organization_units h, hr_all_organization_units h1, hri_org_hrchy_summary hr  																     ");
sql.append("	 	     where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'   																						     ");
sql.append("	 	   and hr.organization_id = ? and h1.organization_id=hr.organization_id and h.type in ('HO','ZON','CIR','DIV'))   or																			     ");
sql.append("	 	        p.OFFICE_CODE IN (  																														     ");
sql.append("	 	  	    select h.organization_id from hr_all_organization_units h, hr_all_organization_units h1, hri_org_hrchy_summary hr   																	     ");
sql.append("	 	  	    where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'  																					     ");
sql.append("	 	  	    and hr.organization_id = ? and h1.organization_id=hr.organization_id  																							     ");
sql.append("	 	  	   and h.type in ('HO','ZON','CIR','DIV') )   )																											     ");
sql.append("	 	         ) A																																     ");
sql.append("	   group by ZONE,CIRCLE,DIVISION 																														     ");
sql.append("          UNION     																																     ");
sql.append("     select ZONE,'' circle,'' DIVISION,sum(P_Tech_MORE_THAN30DAYS), sum(P_TechLESSTHAN30DAYS), sum(P_Acc_MORETHAN30DAYS),SUM(P_Acc_LESSTHAN30DAYS),SUM(P_Cash_MORE_THAN30DAYS),SUM(P_Cash_LESS_THAN30DAYS), sum(Paid), 								     ");
sql.append("          	 (sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + sum(P_Acc_MORETHAN30DAYS) + sum(P_Acc_LESSTHAN30DAYS)+ SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS))total,0 SUBMITTED,0 unpaid_submitted from  						     ");
sql.append("          	 (select a.ZONE||' TOTAL' zone, a.CIRCLE, a.DIVISION,  																											     ");
sql.append("          	  count(case when a.INVOICE_STATUS ='Pending With Technical' AND  NVL((trunc(SYSDATE) - trunc(A.INVOICE_APPROVAL_DATE)),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS, 													     ");
sql.append("          	 count(case when a.INVOICE_STATUS ='Pending With Technical' AND NVL((trunc(SYSDATE) - trunc(A.INVOICE_APPROVAL_DATE)),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS , 													     ");
sql.append("          	 count(case when a.INVOICE_STATUS ='Pending With Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)>30 THEN 1 END) P_Acc_MORETHAN30DAYS, 																     ");
sql.append("          	 count(case when a.INVOICE_STATUS ='Pending With Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)<=30 THEN 1 END) P_Acc_LESSTHAN30DAYS, 																     ");
sql.append("          	 count(case when a.INVOICE_STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(a.inv_dt)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS, 																     ");
sql.append("          	 count(case when a.INVOICE_STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS,																     ");
sql.append("          count(case when a.INVOICE_STATUS ='Paid'  THEN 1 END) Paid, count(*) Total,  																								     ");
sql.append("          	  0 SUBMITTED, 0 unpaid_submitted 																													     ");
sql.append("             from ( select distinct a.ZONE,  a.CIRCLE, a.DIVISION ,a.INVOICE_STATUS	, A.INVOICE_APPROVAL_DATE,A.SORMDATE,a.inv_dt,a.appl_id																		     ");
sql.append("    	  from SUMMARY_STATUS A left outer join PO_LINE_INV_DETAILS_org p            																								     ");
sql.append("    	             on A.appl_id = p.appl_id																													     ");
sql.append("    	             WHERE   A.STATUS IN ('Verified')  and 																											     ");
sql.append("    	    (  A.OFFICE_CODE IN (  																														     ");
sql.append("    	  	    select h.organization_id from hr_all_organization_units h, hr_all_organization_units h1, hri_org_hrchy_summary hr   																	     ");
sql.append("    	  	    where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'  																					     ");
sql.append("    	  	    and hr.organization_id = ? and h1.organization_id=hr.organization_id  																							     ");
sql.append("    	  	   and h.type in ('HO','ZON','CIR','DIV') ) 																											     ");
sql.append("    	         or																																     ");
sql.append("    	        p.OFFICE_CODE IN (  																														     ");
sql.append("    	  	    select h.organization_id from hr_all_organization_units h, hr_all_organization_units h1, hri_org_hrchy_summary hr   																	     ");
sql.append("    	  	    where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'  																					     ");
sql.append("    	  	    and hr.organization_id = ? and h1.organization_id=hr.organization_id  																							     ");
sql.append("    	  	   and h.type in ('HO','ZON','CIR','DIV') )   )																											     ");
sql.append("    	         )A																																     ");
sql.append("            where ZONE IS NOT NULL																															     ");
sql.append("            group by a.ZONE, a.CIRCLE, a.DIVISION)     																												     ");
sql.append("      	  group by rollup(zone) 																														     ");
sql.append("         UNION 																																	     ");
sql.append("  select ZONE,'' circle,'' DIVISION, 0 ,0 ,0, 0 ,0,0,0,0,sum(submitted),sum(unpaid_Submitted) from    																					     ");
sql.append("              (select ZONE||' TOTAL' zone, CIRCLE, DIVISION, 0 P_Tech_MORE_THAN30DAYS,0 P_Tech_LESS_THAN30DAYS, 0 P_Acc_MORETHAN30DAYS,0 P_Acc_LESSTHAN30DAYS, 0 P_Cash_MORE_THAN30DAYS,0 P_Cash_LESS_THAN30DAYS, 0 Total, count(case when A.STATUS='Verified' then 1 end) Submitted     ");
sql.append("      	,count(case when A.STATUS='Verified' AND INVOICE_STATUS!='Paid' then 1 end) unpaid_Submitted  FROM																					     ");
sql.append("      (select distinct A.ZONE,A.CIRCLE, A.DIVISION,A.STATUS,A.INVOICE_STATUS,A.appl_id	FROM 																							     ");
sql.append("      	SUMMARY_STATUS A left outer join PO_LINE_INV_DETAILS_org p on a.appl_id=p.appl_id  where   A.STATUS='Verified' 																				     ");
sql.append("          AND( a.OFFICE_CODE in  ( select h.organization_id from hr_all_organization_units h,  																							     ");
sql.append("      	 hr_all_organization_units h1, hri_org_hrchy_summary hr where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'   																     ");
sql.append("      	  and hr.organization_id = ? and h1.organization_id=hr.organization_id and h.type in ('HO','ZON','CIR','DIV')) 																				     ");
sql.append("            or																																	     ");
sql.append("            p.OFFICE_CODE in  ( select h.organization_id from hr_all_organization_units h,  																							     ");
sql.append("      	 hr_all_organization_units h1, hri_org_hrchy_summary hr where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'   																     ");
sql.append("      	  and hr.organization_id = ? and h1.organization_id=hr.organization_id and h.type in ('HO','ZON','CIR','DIV')) 																				     ");
sql.append("            ))A																																	     ");
sql.append("            group by ZONE,CIRCLE,DIVISION  																														     ");
sql.append("      	   )    																																     ");
sql.append("        group by rollup(zone)																															     ");
sql.append("        ) GROUP BY ZONE,CIRCLE,DIVISION ORDER BY ZONE   																												     ");
      																																				     
           
           
           logger.log(Level.INFO, "GetSummaryListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
      
             statement.setString(i++, vendorBean.getLocationId());
             statement.setString(i++, vendorBean.getLocationId());
             statement.setString(i++, vendorBean.getLocationId());
             statement.setString(i++, vendorBean.getLocationId());
               statement.setString(i++, vendorBean.getLocationId());
             statement.setString(i++, vendorBean.getLocationId());
             statement.setString(i++, vendorBean.getLocationId());
             statement.setString(i++, vendorBean.getLocationId());
            }
            else  if(vendorBean.getUserType().equals("Vendor"))
            {
           
                
   sql.append("              select zone, circle, division, sum(P_Tech_MORE_THAN30DAYS) P_Tech_MORE_THAN30DAYS ,sum(P_TechLESSTHAN30DAYS) P_TechLESSTHAN30DAYS ,sum(P_Acc_MORETHAN30DAYS) P_Acc_MORETHAN30DAYS ,SUM(P_Acc_LESSTHAN30DAYS)P_Acc_LESSTHAN30DAYS,   			      ");
   sql.append("      	SUM(P_Cash_MORE_THAN30DAYS) P_Cash_MORE_THAN30DAYS,  																										      ");
   sql.append("          SUM(P_Cash_LESS_THAN30DAYS)P_Cash_LESS_THAN30DAYS, SUM(Paid)Paid,(sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + sum(P_Acc_MORETHAN30DAYS) + sum(P_Acc_LESSTHAN30DAYS)+ SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS)) Total,      ");
   sql.append("          sum(submitted)V_submit,sum(unpaid_submitted)unpaid_submitted from (   																								      ");
   sql.append("         select a.ZONE,  a.CIRCLE, a.DIVISION ,  count(  																										      ");
   sql.append("         case when a.INVOICE_STATUS ='Pending With Technical' AND  NVL((trunc(SYSDATE) - trunc(A.INVOICE_APPROVAL_DATE)),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS,   													      ");
sql.append("	     count(case when a.INVOICE_STATUS ='Pending With Technical' AND NVL((trunc(SYSDATE) - trunc(A.INVOICE_APPROVAL_DATE)),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS ,   													      ");
sql.append("	     count(case when a.INVOICE_STATUS ='Pending With Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)>30 THEN 1 END) P_Acc_MORETHAN30DAYS,   															      ");
sql.append("	      count(case when a.INVOICE_STATUS ='Pending With Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)<=30 THEN 1 END) P_Acc_LESSTHAN30DAYS,   															      ");
sql.append("	     count(case when a.INVOICE_STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS,   															      ");
sql.append("	     count(case when a.INVOICE_STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS, 															      ");
   sql.append("       count(case when a.INVOICE_STATUS ='Paid'  THEN 1 END) Paid,  																									      ");
   sql.append("      count(*) Total,   0 Submitted ,0 unpaid_submitted     																										      ");
sql.append("	     from SUMMARY_STATUS A WHERE   A.STATUS IN ('Verified') and A.VENDOR_NUMBER=? group by a.ZONE, a.CIRCLE, a.DIVISION  																		      ");
   sql.append("      	UNION      																															      ");
   sql.append("        select A.ZONE,A.CIRCLE,A.DIVISION,0,0,0,0,0,0,0,0,count(case when A.STATUS='Verified' then 1 end) Submitted ,count(case when A.STATUS='Verified' AND INVOICE_STATUS!='Paid' then 1 end) unpaid_Submitted    							      ");
sql.append("	      from SUMMARY_STATUS A   																														      ");
sql.append("	      WHERE  A.STATUS='Verified' AND  A.VENDOR_NUMBER=? 																										      ");
sql.append("	     group by ZONE,CIRCLE,DIVISION  																													      ");
   sql.append("        UNION         																															      ");
   sql.append("      select ZONE,'' circle,'' DIVISION,sum(P_Tech_MORE_THAN30DAYS), sum(P_TechLESSTHAN30DAYS), sum(P_Acc_MORETHAN30DAYS),SUM(P_Acc_LESSTHAN30DAYS),SUM(P_Cash_MORE_THAN30DAYS),SUM(P_Cash_LESS_THAN30DAYS), sum(Paid),  						      ");
   sql.append("   	   (sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + sum(P_Acc_MORETHAN30DAYS) + sum(P_Acc_LESSTHAN30DAYS)+ SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS))total,0 SUBMITTED, 0 unpaid_submitted from   				      ");
   sql.append("   	   (select a.ZONE||' TOTAL' zone, a.CIRCLE, a.DIVISION,   																									      ");
   sql.append("   	    count(case when a.INVOICE_STATUS ='Pending With Technical' AND  NVL((trunc(SYSDATE) - trunc(A.INVOICE_APPROVAL_DATE)),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS,  												      ");
   sql.append("   	   count(case when a.INVOICE_STATUS ='Pending With Technical' AND NVL((trunc(SYSDATE) - trunc(A.INVOICE_APPROVAL_DATE)),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS ,  												      ");
   sql.append("   	   count(case when a.INVOICE_STATUS ='Pending With Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)>30 THEN 1 END) P_Acc_MORETHAN30DAYS,  														      ");
   sql.append("   	   count(case when a.INVOICE_STATUS ='Pending With Accounts' AND NVL((trunc(SYSDATE) - trunc(SORMDATE)),0)<=30 THEN 1 END) P_Acc_LESSTHAN30DAYS,  														      ");
   sql.append("   	   count(case when a.INVOICE_STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(a.inv_dt)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS,  														      ");
   sql.append("   	   count(case when a.INVOICE_STATUS ='Pending For Payment' AND NVL((trunc(SYSDATE) - trunc(A.inv_dt)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS, 														      ");
   sql.append("      count(case when a.INVOICE_STATUS ='Paid'  THEN 1 END) Paid, count(*) Total,   																							      ");
   sql.append("   	    0 SUBMITTED, 0 unpaid_submitted from SUMMARY_STATUS A WHERE   A.STATUS IN ('Verified') AND  A.VENDOR_NUMBER=?  and ZONE IS NOT NULL  group by a.ZONE, a.CIRCLE, a.DIVISION)      										      ");
   sql.append("   	    group by rollup(zone)  																													      ");
   sql.append("       UNION  select ZONE,'' circle,'' DIVISION, 0 ,0 ,0, 0 ,0,0,0,0,sum(submitted),sum(unpaid_Submitted) from     																			      ");
   sql.append("      (select ZONE||' TOTAL' zone, CIRCLE, DIVISION, 0 P_Tech_MORE_THAN30DAYS,0 P_Tech_LESS_THAN30DAYS, 0 P_Acc_MORETHAN30DAYS,0 P_Acc_LESSTHAN30DAYS, 0 P_Cash_MORE_THAN30DAYS,0 P_Cash_LESS_THAN30DAYS, 0 Total, count(case when A.STATUS='Verified' then 1 end) Submitted   ");
sql.append("	  ,count(case when A.STATUS='Verified' AND INVOICE_STATUS!='Paid' then 1 end) unpaid_Submitted FROM SUMMARY_STATUS A where  A.STATUS='Verified' AND  A.VENDOR_NUMBER=?  group by ZONE,CIRCLE,DIVISION   								      ");
sql.append("	     )     																																      ");
   sql.append("      group by rollup(zone) ) GROUP BY ZONE,CIRCLE,DIVISION ORDER BY ZONE     																								      ");

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
