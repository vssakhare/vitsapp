/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.legal.bean.LegalInvoiceInputBean;
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
public class GetLegalSummaryListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetLegalSummaryListQueryHelper.class);
    private LegalInvoiceInputBean legalInvoiceInputBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetLegalSummaryListQueryHelper(LegalInvoiceInputBean legalInvoiceInputBeanObj) {
        this.legalInvoiceInputBeanObj = legalInvoiceInputBeanObj;
    }

    public GetLegalSummaryListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;

    }

    public Object getDataObject(ResultSet results) throws Exception {
        LegalInvoiceInputBean legalInvoiceInputBeanObjOne = new LegalInvoiceInputBean();
        try {
            logger.log(Level.INFO, "GetLegalSummaryListQueryHelper ::: getDataObject() :: method called ::");

            legalInvoiceInputBeanObjOne.setZone(results.getString("ZONE"));
            legalInvoiceInputBeanObjOne.setCircle(results.getString("CIRCLE"));
            legalInvoiceInputBeanObjOne.setDivision(results.getString("DIVISION"));
            legalInvoiceInputBeanObjOne.setSubDivisionText(results.getString("SUB_DIVISION"));
            legalInvoiceInputBeanObjOne.setP_Tech_MORE_THAN30DAYS(results.getString("P_Tech_MORE_THAN30DAYS"));
            legalInvoiceInputBeanObjOne.setP_TechLESSTHAN30DAYS(results.getString("P_TechLESSTHAN30DAYS"));
            legalInvoiceInputBeanObjOne.setP_Acc_MORETHAN30DAYS(results.getString("P_Acc_MORETHAN30DAYS"));
            legalInvoiceInputBeanObjOne.setP_Acc_LESSTHAN30DAYS(results.getString("P_Acc_LESSTHAN30DAYS"));
            legalInvoiceInputBeanObjOne.setP_Cash_MORE_THAN30DAYS(results.getString("P_Cash_MORE_THAN30DAYS"));
            legalInvoiceInputBeanObjOne.setP_Cash_LESS_THAN30DAYS(results.getString("P_Cash_LESS_THAN30DAYS"));
            legalInvoiceInputBeanObjOne.setPaid(results.getString("Paid"));
            legalInvoiceInputBeanObjOne.setpTot(results.getString("Total"));
            legalInvoiceInputBeanObjOne.setvSubmit(results.getString("V_submit"));
            legalInvoiceInputBeanObjOne.setUNPAID_SUBMITTED(results.getString("unpaid_submitted"));
            if (legalInvoiceInputBeanObj.getUserType().equals("Emp")) {
            legalInvoiceInputBeanObjOne.setDeptName(results.getString("DEPT_NAME"));
            }
            // legalInvoiceInputBeanObj.setFeeType(results.getString("sFee_type"));
            //legalInvoiceInputBeanObj.setV_submit_FeeType(results.getString("V_submit_FeeType"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLegalSummaryListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return legalInvoiceInputBeanObjOne;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i = 1;
        try {
            logger.log(Level.INFO, "GetLegalSummaryListQueryHelper ::: getQueryResults() :: method called ::");

            if (legalInvoiceInputBeanObj.getUserType().equals("Emp")) {
                sql.append("			  	               select zone, circle, division,SUB_DIVISION,DEPT_NAME, sum(P_Tech_MORE_THAN30DAYS) P_Tech_MORE_THAN30DAYS ,sum(P_TechLESSTHAN30DAYS) P_TechLESSTHAN30DAYS ,sum(P_Acc_MORETHAN30DAYS) P_Acc_MORETHAN30DAYS ,SUM(P_Acc_LESSTHAN30DAYS)P_Acc_LESSTHAN30DAYS,   			    ");
                sql.append("			          	SUM(P_Cash_MORE_THAN30DAYS) P_Cash_MORE_THAN30DAYS,  																										    ");
                sql.append("			              SUM(P_Cash_LESS_THAN30DAYS)P_Cash_LESS_THAN30DAYS, SUM(Paid)Paid,(sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + sum(P_Acc_MORETHAN30DAYS) + sum(P_Acc_LESSTHAN30DAYS)+ SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS)) Total, 			");
                sql.append("			              sFee_type,   sum(submitted)V_submit,SUM(unpaid_submitted)unpaid_submitted,sum(sFee_type_cnt)V_submit_FeeType	from																								");
                sql.append("			                           (  																																");
                sql.append("			           select   x.ZONE_NAME ZONE,x.CIRCLE_NAME CIRCLE,x.DIVISION_NAME DIVISION,x.SUB_DIVISION_NAME SUB_DIVISION ,x.DEPT_NAME, count(  																				");
                sql.append("			            case when SAP_STATUS in('With Technical') AND  NVL((trunc(SYSDATE) - trunc(case   when nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)>sysdate  then least(nvl(date_of_approval,zf.invoice_date),nvl(reciept_date,zf.invoice_date))   else  nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)     end )),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS,   													      	      	    ");
                sql.append("				     count(case when SAP_STATUS in('With Technical') AND NVL((trunc(SYSDATE) - trunc(case   when nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)>sysdate  then least(nvl(date_of_approval,zf.invoice_date),nvl(reciept_date,zf.invoice_date))   else  nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)     end )),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS ,   													      	    ");
                sql.append("				     count(case when SAP_STATUS ='With Accounts' AND  NVL((trunc(SYSDATE) - trunc(DATE_OF_SUBMISSION)),0)>30  THEN 1 END) P_Acc_MORETHAN30DAYS,   															      							    ");
                sql.append("				      count(case when SAP_STATUS ='With Accounts' AND  NVL((trunc(SYSDATE) - trunc(DATE_OF_SUBMISSION)),0)<=30 THEN 1 END) P_Acc_LESSTHAN30DAYS,   															      							    ");
                sql.append("				     count(case when SAP_STATUS in ('With Cash','Payment Document Reversed') AND  NVL((trunc(SYSDATE) - trunc(ZZPARK_POST_DATE)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS,   															      							    ");
                sql.append("				     count(case when SAP_STATUS in('With Cash','Payment Document Reversed') AND  NVL((trunc(SYSDATE) - trunc(ZZPARK_POST_DATE)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS, 															      							    ");
                sql.append("			          count(case when SAP_STATUS in ('Payment Done','Payment Adjusted')  THEN 1 END) Paid,  																									      	    ");
                sql.append("			         count(*) Total,   0 Submitted ,0 unpaid_submitted, '' sFee_type,0 sFee_type_cnt    																								");
                sql.append("				     from (SELECT om.ZONE_NAME,om.CIRCLE_NAME,om.DIVISION_NAME,om.SUB_DIVISION_NAME,	LD.DEPT_NAME,						       																    ");
                sql.append("			                  om.SECTION_NAME,om.SUB_STATION_NAME, 													       																");
                sql.append("			                  ld.INVOICE_DATE,ld.INVOICE_NUMBER,ld.CASE_REF_NO,ld.VENDOR_NUMBER, 									       																");
                sql.append("			                 f.FEE_TYPE as sFee_type ,f.SAP_STATUS	SAP_STATUS													       											");
                sql.append("			                 from xxmis_erp_legal_invoice_details LD join   xxmis_erp_legal_invoice_fee_type_dtls  f 																					    ");
                sql.append("			on     f.APPL_ID=LD.APPL_ID,xxmis_organization_master OM  																											    ");
                sql.append("			where nvl(LD.dealing_office_code,261)=OM.organization_id  																											    ");
                sql.append("			AND dealing_office_code in(select organization_id from xxmis_organization_master m 																								    ");
                sql.append("			where (Region_id=? or zone_id=? or circle_id=? or division_id=? or sub_division_id=?))  																					    ");
                sql.append("			AND SAVE_FLAG in ('Accepted') 	");
                if (!((ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceToDate())))) {
                        sql.append(" AND INVOICE_DATE BETWEEN ? AND  ? ");
                    }
                sql.append("			)x  LEFT JOIN zhrt_legal_fee zf ON to_number(x.vendor_number) = zf.vendor AND x.case_ref_no = zf.caserefno																						    ");
                sql.append("			AND x.invoice_number = zf.invoice_legal AND x.invoice_date = zf.invoice_date  AND x.sFee_type = zf.adv_fee_type													       									");
                sql.append("			                 group by  x.ZONE_NAME,x.CIRCLE_NAME,x.DIVISION_NAME,x.SUB_DIVISION_name,x.DEPT_NAME	               																					    ");
                sql.append("			                          																															    ");
                sql.append("			                          																															    ");
                sql.append("			     UNION                     																														      	    ");
                sql.append("			                        select  x.ZONE_NAME ZONE,x.CIRCLE_NAME CIRCLE,x.DIVISION_NAME DIVISION,x.SUB_DIVISION_NAME SUB_DIVISION,x.DEPT_NAME,0,0,0,0,0,0,0,0,count(*) Submitted ,count(case when  SAP_STATUS NOT IN ('Payment Done','Payment Adjusted') then 1 end) unpaid_Submitted,'' sFee_type,0 sFee_type_cnt									");
                sql.append("			                 from (SELECT om.ZONE_NAME,om.CIRCLE_NAME,om.DIVISION_NAME,om.SUB_DIVISION_NAME,om.SECTION_NAME,om.SUB_STATION_NAME, LD.DEPT_NAME,																			");
                sql.append("			                 ld.INVOICE_DATE,ld.INVOICE_NUMBER,ld.CASE_REF_NO,ld.VENDOR_NUMBER,f.FEE_TYPE as sFee_type,f.SAP_STATUS	SAP_STATUS 																						");
                sql.append("			                         from xxmis_erp_legal_invoice_details LD join   xxmis_erp_legal_invoice_fee_type_dtls  f 																				    ");
                sql.append("			on     f.APPL_ID=LD.APPL_ID,xxmis_organization_master OM  																											    ");
                sql.append("			where nvl(LD.dealing_office_code,261)=OM.organization_id  																											    ");
                sql.append("			AND dealing_office_code in(select organization_id from xxmis_organization_master m 																								    ");
                sql.append("			where (Region_id=? or zone_id=? or circle_id=? or division_id=? or sub_division_id=?))  																					    ");
                sql.append("			AND SAVE_FLAG in ('Accepted') ");
                if (!((ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceToDate())))) {
                        sql.append(" AND INVOICE_DATE BETWEEN ? AND  ? ");
                    }
                sql.append("			)x LEFT JOIN zhrt_legal_fee zf ON to_number(x.vendor_number) = zf.vendor AND x.case_ref_no = zf.caserefno																						    ");
                sql.append("			AND x.invoice_number = zf.invoice_legal AND x.invoice_date = zf.invoice_date  AND x.sFee_type = zf.adv_fee_type													       									");
                sql.append("			                 group by  x.ZONE_NAME,x.CIRCLE_NAME,x.DIVISION_NAME,x.SUB_DIVISION_name,x.DEPT_NAME																				  			    ");
                sql.append("			                																									  							    ");
                sql.append("			    UNION 																																	    ");
                sql.append("			               																															      	      	    ");
                sql.append("			         select zone,'' circle,'' DIVISION,'' SUB_DIVISION,'' DEPT_NAME,sum(P_Tech_MORE_THAN30DAYS), sum(P_TechLESSTHAN30DAYS), sum(P_Acc_MORETHAN30DAYS),SUM(P_Acc_LESSTHAN30DAYS),SUM(P_Cash_MORE_THAN30DAYS),SUM(P_Cash_LESS_THAN30DAYS), sum(Paid),  						");
                sql.append("			      	   (sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + sum(P_Acc_MORETHAN30DAYS) + sum(P_Acc_LESSTHAN30DAYS)+ SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS))total,0 SUBMITTED, 0 unpaid_submitted, '' sFee_type,0 sFee_type_cnt from   			");
                sql.append("			      	   (select x.ZONE_NAME||' TOTAL' zone, x.CIRCLE_NAME, x.DIVISION_NAME, count(    																									");
                sql.append("			      	     case when SAP_STATUS in('With Technical') AND  NVL((trunc(SYSDATE) - trunc(case   when nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)>sysdate  then least(nvl(date_of_approval,zf.invoice_date),nvl(reciept_date,zf.invoice_date))   else  nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)     end )),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS,   													      	      	    ");
                sql.append("				     count(case when SAP_STATUS in('With Technical') AND NVL((trunc(SYSDATE) - trunc(case   when nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)>sysdate  then least(nvl(date_of_approval,zf.invoice_date),nvl(reciept_date,zf.invoice_date))   else  nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)     end )),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS ,   													      	    ");
                sql.append("				     count(case when SAP_STATUS ='With Accounts' AND  NVL((trunc(SYSDATE) - trunc(DATE_OF_SUBMISSION)),0)>30  THEN 1 END) P_Acc_MORETHAN30DAYS,   															      							    ");
                sql.append("				      count(case when SAP_STATUS ='With Accounts' AND  NVL((trunc(SYSDATE) - trunc(DATE_OF_SUBMISSION)),0)<=30 THEN 1 END) P_Acc_LESSTHAN30DAYS,   															      							    ");
                sql.append("				     count(case when SAP_STATUS in ('With Cash','Payment Document Reversed') AND  NVL((trunc(SYSDATE) - trunc(ZZPARK_POST_DATE)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS,   															      							    ");
                sql.append("				     count(case when SAP_STATUS in('With Cash','Payment Document Reversed') AND  NVL((trunc(SYSDATE) - trunc(ZZPARK_POST_DATE)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS, 															      							    ");
                sql.append("			          count(case when SAP_STATUS in ('Payment Done','Payment Adjusted')  THEN 1 END) Paid,  	 count(*) Total,   																							      	    ");
                sql.append("			      	    0 SUBMITTED, 0 unpaid_submitted from (SELECT om.ZONE_NAME,om.CIRCLE_NAME,om.DIVISION_NAME,om.SUB_DIVISION_NAME,							       													");
                sql.append("			                  om.SECTION_NAME,om.SUB_STATION_NAME,LD.DEPT_NAME, 													       																");
                sql.append("			                  ld.INVOICE_DATE,ld.INVOICE_NUMBER,ld.CASE_REF_NO,ld.VENDOR_NUMBER, 									       																");
                sql.append("			                 f.FEE_TYPE as sFee_type ,f.SAP_STATUS	SAP_STATUS																       								");
                sql.append("			                         from xxmis_erp_legal_invoice_details LD join   xxmis_erp_legal_invoice_fee_type_dtls  f 																				    ");
                sql.append("			on     f.APPL_ID=LD.APPL_ID,xxmis_organization_master OM  																											    ");
                sql.append("			where nvl(LD.dealing_office_code,261)=OM.organization_id  																											    ");
                sql.append("			AND dealing_office_code in(select organization_id from xxmis_organization_master m 																								    ");
                sql.append("			where (Region_id=? or zone_id=? or circle_id=? or division_id=? or sub_division_id=?))  																					    ");
                sql.append("			AND SAVE_FLAG in ('Accepted')");
                if (!((ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceToDate())))) {
                        sql.append(" AND INVOICE_DATE BETWEEN ? AND  ? ");
                    }
                sql.append("			)x LEFT JOIN zhrt_legal_fee zf ON to_number(x.vendor_number) = zf.vendor AND x.case_ref_no = zf.caserefno																						    ");
                sql.append("			AND x.invoice_number = zf.invoice_legal AND x.invoice_date = zf.invoice_date  AND x.sFee_type = zf.adv_fee_type													       									");
                sql.append("			                 group by  x.ZONE_NAME,x.CIRCLE_NAME,x.DIVISION_NAME,x.SUB_DIVISION_name,x.DEPT_NAME												       												");
                sql.append("			                 																							      									    ");
                sql.append("			                 )      										      																					    ");
                sql.append("			      	    group by rollup(zone)																													      	    ");
                sql.append("			  UNION 																																	    ");
                sql.append("			                select ZONE,'' circle,'' DIVISION,'' SUB_DIVISION,'' DEPT_NAME,  0 ,0 ,0, 0 ,0,0,0,0,sum(submitted),sum(unpaid_Submitted),'' sFee_type,0 sFee_type_cnt     from    															");
                sql.append("			                       (																																");
                sql.append("			                       select x.ZONE_NAME||' TOTAL' zone, x.CIRCLE_NAME, x.DIVISION_NAME,x.SUB_DIVISION_NAME,0 P_Tech_MORE_THAN30DAYS,0 P_Tech_LESS_THAN30DAYS, 0 P_Acc_MORETHAN30DAYS,0 P_Acc_LESSTHAN30DAYS, 0 P_Cash_MORE_THAN30DAYS,0 P_Cash_LESS_THAN30DAYS, 0 Total,    		");
                sql.append("			                      count(*) Submitted ,count(case when  SAP_STATUS NOT IN ('Payment Done','Payment Adjusted') then 1 end) unpaid_Submitted,'' sFee_type,0 sFee_type_cnt 														       										");
                sql.append("			                	  FROM (SELECT om.ZONE_NAME,om.CIRCLE_NAME,om.DIVISION_NAME,om.SUB_DIVISION_NAME,LD.DEPT_NAME,							       															");
                sql.append("			                  om.SECTION_NAME,om.SUB_STATION_NAME, 													       																");
                sql.append("			                  ld.INVOICE_DATE,ld.INVOICE_NUMBER,ld.CASE_REF_NO,ld.VENDOR_NUMBER, 									       																");
                sql.append("			                 f.FEE_TYPE as sFee_type 	,f.SAP_STATUS	SAP_STATUS														       															");
                sql.append("			                         from xxmis_erp_legal_invoice_details LD join   xxmis_erp_legal_invoice_fee_type_dtls  f 																				    ");
                sql.append("			on     f.APPL_ID=LD.APPL_ID,xxmis_organization_master OM  																											    ");
                sql.append("			where nvl(LD.dealing_office_code,261)=OM.organization_id  																											    ");
                sql.append("			AND dealing_office_code in(select organization_id from xxmis_organization_master m 																								    ");
                sql.append("			where (Region_id=? or zone_id=? or circle_id=? or division_id=? or sub_division_id=?))  																					    ");
                sql.append("			AND SAVE_FLAG in ('Accepted') ");
                if (!((ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceToDate())))) {
                        sql.append(" AND INVOICE_DATE BETWEEN ? AND  ? ");
                    }
                sql.append("			)x LEFT JOIN zhrt_legal_fee zf ON to_number(x.vendor_number) = zf.vendor AND x.case_ref_no = zf.caserefno																						    ");
                sql.append("			AND x.invoice_number = zf.invoice_legal AND x.invoice_date = zf.invoice_date  AND x.sFee_type = zf.adv_fee_type													       									");
                sql.append("			                 group by  x.ZONE_NAME,x.CIRCLE_NAME,x.DIVISION_NAME,x.SUB_DIVISION_name,x.DEPT_NAME								       																");
                sql.append("			                 	     )     																       															");
                sql.append("			                          group by rollup(zone)																														");
                sql.append("			 																							  											    ");
                sql.append("                         ) GROUP BY ZONE,CIRCLE,DIVISION,SUB_DIVISION,DEPT_NAME,sFee_type ORDER BY ZONE																									    ");

                logger.log(Level.INFO, "GetLegalSummaryListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

                statement = connection.prepareStatement(sql.toString());

                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                if (!((ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceToDate())))) {
                            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceInputBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceInputBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                        }
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                 statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                if (!((ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceToDate())))) {
                            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceInputBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceInputBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                        }
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                if (!((ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceToDate())))) {
                            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceInputBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceInputBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                        }
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                 statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                statement.setString(i++, legalInvoiceInputBeanObj.getLocationId());
                if (!((ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getInvoiceToDate())))) {
                            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceInputBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceInputBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                        }
            } else if (legalInvoiceInputBeanObj.getUserType().equals("Vendor")) {

                sql.append(" 	               select zone, circle, division,SUB_DIVISION,  sum(P_Tech_MORE_THAN30DAYS) P_Tech_MORE_THAN30DAYS ,sum(P_TechLESSTHAN30DAYS) P_TechLESSTHAN30DAYS ,sum(P_Acc_MORETHAN30DAYS) P_Acc_MORETHAN30DAYS ,SUM(P_Acc_LESSTHAN30DAYS)P_Acc_LESSTHAN30DAYS,   			      	      ");
                sql.append("         	SUM(P_Cash_MORE_THAN30DAYS) P_Cash_MORE_THAN30DAYS,  																										      	      ");
                sql.append("             SUM(P_Cash_LESS_THAN30DAYS)P_Cash_LESS_THAN30DAYS, SUM(Paid)Paid,(sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + sum(P_Acc_MORETHAN30DAYS) + sum(P_Acc_LESSTHAN30DAYS)+ SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS)) Total, 			      ");
                sql.append("             sFee_type,   sum(submitted)V_submit,SUM(unpaid_submitted)unpaid_submitted,sum(sFee_type_cnt)V_submit_FeeType	from																									");
                sql.append("                          (  																																      ");
                sql.append("          select   x.ZONE_NAME ZONE,x.CIRCLE_NAME CIRCLE,x.DIVISION_NAME DIVISION,x.SUB_DIVISION_NAME SUB_DIVISION , count(  																					");
                sql.append("            case when SAP_STATUS in('With Technical') AND  NVL((trunc(SYSDATE) - trunc(case   when nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)>sysdate  then least(nvl(date_of_approval,zf.invoice_date),nvl(reciept_date,zf.invoice_date))   else  nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)     end )),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS,   													      	      	    ");
                sql.append("				     count(case when SAP_STATUS in('With Technical') AND NVL((trunc(SYSDATE) - trunc(case   when nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)>sysdate  then least(nvl(date_of_approval,zf.invoice_date),nvl(reciept_date,zf.invoice_date))   else  nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)     end )),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS ,   													      	    ");
                sql.append("				     count(case when SAP_STATUS ='With Accounts' AND  NVL((trunc(SYSDATE) - trunc(DATE_OF_SUBMISSION)),0)>30  THEN 1 END) P_Acc_MORETHAN30DAYS,   															      							    ");
                sql.append("				      count(case when SAP_STATUS ='With Accounts' AND  NVL((trunc(SYSDATE) - trunc(DATE_OF_SUBMISSION)),0)<=30 THEN 1 END) P_Acc_LESSTHAN30DAYS,   															      							    ");
                sql.append("				     count(case when SAP_STATUS in ('With Cash','Payment Document Reversed') AND  NVL((trunc(SYSDATE) - trunc(ZZPARK_POST_DATE)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS,   															      							    ");
                sql.append("				     count(case when SAP_STATUS in('With Cash','Payment Document Reversed') AND  NVL((trunc(SYSDATE) - trunc(ZZPARK_POST_DATE)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS, 															      							    ");
                sql.append("			          count(case when SAP_STATUS in ('Payment Done','Payment Adjusted')  THEN 1 END) Paid,  	 																									      	      ");
                sql.append("         count(*) Total,   0 Submitted ,0 unpaid_submitted, '' sFee_type,0 sFee_type_cnt    																							");
                sql.append("	     from (SELECT om.ZONE_NAME,om.CIRCLE_NAME,om.DIVISION_NAME,om.SUB_DIVISION_NAME,							       																      ");
                sql.append("                  om.SECTION_NAME,om.SUB_STATION_NAME, 													       															");
                sql.append("                  ld.INVOICE_DATE,ld.INVOICE_NUMBER,ld.CASE_REF_NO,ld.VENDOR_NUMBER, 									       															");
                sql.append("                 f.FEE_TYPE as sFee_type ,f.SAP_STATUS	SAP_STATUS													       										");
                sql.append("                 from xxmis_erp_legal_invoice_details LD join   xxmis_erp_legal_invoice_fee_type_dtls  f 							       															");
                sql.append("                 on     f.APPL_ID=LD.APPL_ID,xxmis_organization_master OM  											       															");
                sql.append("                 where nvl(LD.dealing_office_code,261)=OM.organization_id  AND VENDOR_NUMBER=? and ld.SAVE_FLAG='Accepted' 			       															");
                sql.append("                 ORDER BY LD.APPL_ID DESC )x LEFT JOIN zhrt_legal_fee zf ON to_number(x.vendor_number) = zf.vendor AND 					       															");
                sql.append("                 x.case_ref_no = zf.caserefno  AND x.invoice_number = zf.invoice_legal AND x.invoice_date = zf.invoice_date  AND 				       															");
                sql.append("                 x.sFee_type = zf.adv_fee_type														       															");
                sql.append("                 group by  x.ZONE_NAME,x.CIRCLE_NAME,x.DIVISION_NAME,x.SUB_DIVISION_name	               																					      ");
                sql.append("                          																															      ");
                sql.append("                          																															      ");
                sql.append("     UNION                     																														      ");
                sql.append("                        select  x.ZONE_NAME ZONE,x.CIRCLE_NAME CIRCLE,x.DIVISION_NAME DIVISION,x.SUB_DIVISION_NAME SUB_DIVISION,0,0,0,0,0,0,0,0,count(*) Submitted ,count(case when  SAP_STATUS NOT IN ('Payment Done','Payment Adjusted') then 1 end) unpaid_Submitted,'' sFee_type,0 sFee_type_cnt									");
                sql.append("                 from (SELECT om.ZONE_NAME,om.CIRCLE_NAME,om.DIVISION_NAME,om.SUB_DIVISION_NAME,om.SECTION_NAME,om.SUB_STATION_NAME, 																		");
                sql.append("                 ld.INVOICE_DATE,ld.INVOICE_NUMBER,ld.CASE_REF_NO,ld.VENDOR_NUMBER,f.FEE_TYPE as sFee_type 	,f.SAP_STATUS	SAP_STATUS																					");
                sql.append("                 from xxmis_erp_legal_invoice_details LD join   xxmis_erp_legal_invoice_fee_type_dtls  f on   																					");
                sql.append("                 f.APPL_ID=LD.APPL_ID,xxmis_organization_master OM  where nvl(LD.dealing_office_code,261)=OM.organization_id  																			");
                sql.append("                 AND VENDOR_NUMBER=? and ld.SAVE_FLAG='Accepted'																										");
                sql.append("                 ORDER BY LD.APPL_ID DESC )x LEFT JOIN zhrt_legal_fee zf ON to_number(x.vendor_number) = zf.vendor AND 																				");
                sql.append("                 x.case_ref_no = zf.caserefno  AND x.invoice_number = zf.invoice_legal AND x.invoice_date = zf.invoice_date  AND 																			");
                sql.append("                 x.sFee_type = zf.adv_fee_type																													");
                sql.append("                 group by  x.ZONE_NAME,x.CIRCLE_NAME,x.DIVISION_NAME,x.SUB_DIVISION_NAME																								");
                sql.append("    UNION 																																	      ");
                sql.append("               																															      	      ");
                sql.append("         select zone,'' circle,'' DIVISION,'' SUB_DIVISION,sum(P_Tech_MORE_THAN30DAYS), sum(P_TechLESSTHAN30DAYS), sum(P_Acc_MORETHAN30DAYS),SUM(P_Acc_LESSTHAN30DAYS),SUM(P_Cash_MORE_THAN30DAYS),SUM(P_Cash_LESS_THAN30DAYS), sum(Paid),  					");
                sql.append("      	   (sum(P_Tech_MORE_THAN30DAYS) + sum(P_TechLESSTHAN30DAYS) + sum(P_Acc_MORETHAN30DAYS) + sum(P_Acc_LESSTHAN30DAYS)+ SUM (P_Cash_MORE_THAN30DAYS) + SUM(P_Cash_LESS_THAN30DAYS))total,0 SUBMITTED, 0 unpaid_submitted, '' sFee_type,0 sFee_type_cnt from   		");
                sql.append("      	   (select x.ZONE_NAME||' TOTAL' zone, x.CIRCLE_NAME, x.DIVISION_NAME,  count(   																								");
                sql.append("      	     case when SAP_STATUS in('With Technical') AND  NVL((trunc(SYSDATE) - trunc(case   when nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)>sysdate  then least(nvl(date_of_approval,zf.invoice_date),nvl(reciept_date,zf.invoice_date))   else  nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)     end )),0)>30 THEN 1 END) P_Tech_MORE_THAN30DAYS,   													      	      	    ");
                sql.append("				     count(case when SAP_STATUS in('With Technical') AND NVL((trunc(SYSDATE) - trunc(case   when nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)>sysdate  then least(nvl(date_of_approval,zf.invoice_date),nvl(reciept_date,zf.invoice_date))   else  nvl(nvl(zf.date_of_approval,zf.reciept_date),zf.invoice_date)     end )),0)<=30 THEN 1  END) P_TechLESSTHAN30DAYS ,   													      	    ");
                sql.append("				     count(case when SAP_STATUS ='With Accounts' AND  NVL((trunc(SYSDATE) - trunc(DATE_OF_SUBMISSION)),0)>30  THEN 1 END) P_Acc_MORETHAN30DAYS,   															      							    ");
                sql.append("				      count(case when SAP_STATUS ='With Accounts' AND  NVL((trunc(SYSDATE) - trunc(DATE_OF_SUBMISSION)),0)<=30 THEN 1 END) P_Acc_LESSTHAN30DAYS,   															      							    ");
                sql.append("				     count(case when SAP_STATUS in ('With Cash','Payment Document Reversed') AND  NVL((trunc(SYSDATE) - trunc(ZZPARK_POST_DATE)),0)>30 THEN 1 END) P_Cash_MORE_THAN30DAYS,   															      							    ");
                sql.append("				     count(case when SAP_STATUS in('With Cash','Payment Document Reversed') AND  NVL((trunc(SYSDATE) - trunc(ZZPARK_POST_DATE)),0)<=30 THEN 1 END) P_Cash_LESS_THAN30DAYS, 															      							    ");
                sql.append("			          count(case when SAP_STATUS in ('Payment Done','Payment Adjusted')  THEN 1 END) Paid,  	 count(*) Total,   																							      	      ");
                sql.append("      	    0 SUBMITTED, 0 unpaid_submitted from (SELECT om.ZONE_NAME,om.CIRCLE_NAME,om.DIVISION_NAME,om.SUB_DIVISION_NAME,							       												");
                sql.append("                  om.SECTION_NAME,om.SUB_STATION_NAME, 													       															");
                sql.append("                  ld.INVOICE_DATE,ld.INVOICE_NUMBER,ld.CASE_REF_NO,ld.VENDOR_NUMBER, 									       															");
                sql.append("                 f.FEE_TYPE as sFee_type ,f.SAP_STATUS	SAP_STATUS																       							");
                sql.append("                 from xxmis_erp_legal_invoice_details LD join   xxmis_erp_legal_invoice_fee_type_dtls  f 							       															");
                sql.append("                 on     f.APPL_ID=LD.APPL_ID,xxmis_organization_master OM  											       															");
                sql.append("                 where nvl(LD.dealing_office_code,261)=OM.organization_id  AND VENDOR_NUMBER=? and ld.SAVE_FLAG='Accepted' 			       															");
                sql.append("                 ORDER BY LD.APPL_ID DESC )x LEFT JOIN zhrt_legal_fee zf ON to_number(x.vendor_number) = zf.vendor AND 					       															");
                sql.append("                 x.case_ref_no = zf.caserefno  AND x.invoice_number = zf.invoice_legal AND x.invoice_date = zf.invoice_date  AND 				       															");
                sql.append("                 x.sFee_type = zf.adv_fee_type														       															");
                sql.append("                 group by  x.ZONE_NAME,x.CIRCLE_NAME,x.DIVISION_NAME,x.SUB_DIVISION_name																							      ");
                sql.append("                 )      										      																					      ");
                sql.append("      	    group by rollup(zone)																													      ");
                sql.append("  UNION 																																	      ");
                sql.append("                select ZONE,'' circle,'' DIVISION,'' SUB_DIVISION,  0 ,0 ,0, 0 ,0,0,0,0,sum(submitted),sum(unpaid_Submitted),'' sFee_type,0 sFee_type_cnt     from    															");
                sql.append("                       (																																");
                sql.append("                       select x.ZONE_NAME||' TOTAL' zone, x.CIRCLE_NAME, x.DIVISION_NAME,x.SUB_DIVISION_NAME,0 P_Tech_MORE_THAN30DAYS,0 P_Tech_LESS_THAN30DAYS, 0 P_Acc_MORETHAN30DAYS,0 P_Acc_LESSTHAN30DAYS, 0 P_Cash_MORE_THAN30DAYS,0 P_Cash_LESS_THAN30DAYS, 0 Total,    	");
                sql.append("                      count(*) Submitted ,count(case when  SAP_STATUS NOT IN ('Payment Done','Payment Adjusted') then 1 end) unpaid_Submitted,'' sFee_type,0 sFee_type_cnt 														       										");
                sql.append("                	  FROM (SELECT om.ZONE_NAME,om.CIRCLE_NAME,om.DIVISION_NAME,om.SUB_DIVISION_NAME,							       															");
                sql.append("                  om.SECTION_NAME,om.SUB_STATION_NAME, 													       															");
                sql.append("                  ld.INVOICE_DATE,ld.INVOICE_NUMBER,ld.CASE_REF_NO,ld.VENDOR_NUMBER, 									       															");
                sql.append("                 f.FEE_TYPE as sFee_type 	,f.SAP_STATUS	SAP_STATUS														       															");
                sql.append("                 from xxmis_erp_legal_invoice_details LD join   xxmis_erp_legal_invoice_fee_type_dtls  f 							       															");
                sql.append("                 on     f.APPL_ID=LD.APPL_ID,xxmis_organization_master OM  											       															");
                sql.append("                 where nvl(LD.dealing_office_code,261)=OM.organization_id  AND VENDOR_NUMBER=? and ld.SAVE_FLAG='Accepted' 			       															");
                sql.append("                 ORDER BY LD.APPL_ID DESC )x LEFT JOIN zhrt_legal_fee zf ON to_number(x.vendor_number) = zf.vendor AND 					       															");
                sql.append("                 x.case_ref_no = zf.caserefno  AND x.invoice_number = zf.invoice_legal AND x.invoice_date = zf.invoice_date  AND 				       															");
                sql.append("                 x.sFee_type = zf.adv_fee_type														       															");
                sql.append("                 group by  x.ZONE_NAME,x.CIRCLE_NAME,x.DIVISION_NAME,x.SUB_DIVISION_name								       																");
                sql.append("                 	     )     																       															");
                sql.append("                          group by rollup(zone)																													");
//sql.append("  UNION 																																  	      ");
//sql.append("                              select  x.ZONE_NAME ZONE,x.CIRCLE_NAME CIRCLE,x.DIVISION_NAME DIVISION,x.SUB_DIVISION_NAME SUB_DIVISION,0,0,0,0,0,0,0,0,0,0 unpaid_submitted,sFee_type,count(*) sFee_type_cnt										");			  
//sql.append("                 from (SELECT om.ZONE_NAME,om.CIRCLE_NAME,om.DIVISION_NAME,om.SUB_DIVISION_NAME,om.SECTION_NAME,om.SUB_STATION_NAME, 																		");	  
//sql.append("                 ld.INVOICE_DATE,ld.INVOICE_NUMBER,ld.CASE_REF_NO,ld.VENDOR_NUMBER,f.FEE_TYPE as sFee_type 																						");	  
//sql.append("                 from xxmis_erp_legal_invoice_details LD join   xxmis_erp_legal_invoice_fee_type_dtls  f on   																					");	  
//sql.append("                 f.APPL_ID=LD.APPL_ID,xxmis_organization_master OM  where nvl(LD.dealing_office_code,261)=OM.organization_id  																			");	  
//sql.append("                 AND VENDOR_NUMBER=? and ld.SAVE_FLAG='Accepted'																										");	  
//sql.append("                 ORDER BY LD.APPL_ID DESC )x LEFT JOIN zhrt_legal_fee zf ON to_number(x.vendor_number) = zf.vendor AND 																				");	  
//sql.append("                 x.case_ref_no = zf.caserefno  AND x.invoice_number = zf.invoice_legal AND x.invoice_date = zf.invoice_date  AND 																			");	  
//sql.append("                 x.sFee_type = zf.adv_fee_type																													");	  
//sql.append("                 group by  x.ZONE_NAME,x.CIRCLE_NAME,x.DIVISION_NAME,x.SUB_DIVISION_NAME,sFee_type																							");	  
                sql.append("                         ) GROUP BY ZONE,CIRCLE,DIVISION,SUB_DIVISION,sFee_type ORDER BY ZONE																							");

                logger.log(Level.INFO, "GetLegalSummaryListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

                statement = connection.prepareStatement(sql.toString());

                 statement.setString(i++, legalInvoiceInputBeanObj.getVendorNumber());
                  statement.setString(i++, legalInvoiceInputBeanObj.getVendorNumber());
                 statement.setString(i++, legalInvoiceInputBeanObj.getVendorNumber());
                  statement.setString(i++, legalInvoiceInputBeanObj.getVendorNumber());
            }

            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLegalSummaryListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
