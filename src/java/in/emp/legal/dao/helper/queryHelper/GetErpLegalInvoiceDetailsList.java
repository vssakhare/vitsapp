/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.util.ApplicationUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Rikma Rai
 */
public class GetErpLegalInvoiceDetailsList implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetErpLegalInvoiceStatusList.class);
    private LegalInvoiceInputBean legalInvoiceInputBean = null;

    public GetErpLegalInvoiceDetailsList(LegalInvoiceInputBean legalInvoiceInputBean) {
        this.legalInvoiceInputBean = legalInvoiceInputBean;
    }

//    public GetErpLegalInvoiceStatusList(VendorPrezData vendorPrezDataObj) {
//        this.vendorPrezDataObj = vendorPrezDataObj;
//        this.vendorBean = this.vendorPrezDataObj.getVendorBean();
//    }
    public Object getDataObject(ResultSet result) throws Exception {
        LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
        try {
            logger.log(Level.INFO, "GetErpLegalInvoiceStatusList ::: getDataObject() :: method called ::");
            legalInvoiceInputBean.setApplId(result.getInt("APPL_ID"));
            legalInvoiceInputBean.setVendorNumber(result.getString("VENDOR_NUMBER"));
            legalInvoiceInputBean.setVendorName(result.getString("VENDOR_NAME"));
            legalInvoiceInputBean.setCourtCaseNo(result.getString("COURT_CASE_NO"));
            legalInvoiceInputBean.setCaseRefNo(result.getString("CASE_REF_NO"));
            legalInvoiceInputBean.setCourtName(result.getString("COURT_NAME"));
            legalInvoiceInputBean.setCaseDescription(result.getString("CASE_DESCRIPTION"));
            legalInvoiceInputBean.setDealingOfficeCode(result.getString("DEALING_OFFICE_CODE"));
            legalInvoiceInputBean.setDealingOfficeName(result.getString("DEALING_OFFICE_NAME"));
            legalInvoiceInputBean.setPartyNames(result.getString("PARTY_NAMES"));
            legalInvoiceInputBean.setVsPartyNames(result.getString("VS_PARTY_NAMES"));
            legalInvoiceInputBean.setCaseTypeDesc(result.getString("CASE_TYPE_DESC"));
            legalInvoiceInputBean.setInvoiceNumber(result.getString("INVOICE_NUMBER"));
            legalInvoiceInputBean.setInvoiceDate(result.getDate("INVOICE_DATE"));
            legalInvoiceInputBean.setInvoiceAmount(result.getString("INVOICE_AMOUNT"));
            legalInvoiceInputBean.setVendorInvDate(result.getDate("VENDOR_INV_DATE"));
            legalInvoiceInputBean.setMsedclInwardNo(result.getString("MSEDCL_INWARD_NUMBER"));
            legalInvoiceInputBean.setMsedclInwardDate(result.getDate("MSEDCL_INWARD_DATE"));
            //legalInvoiceInputBean.setInvSubmitDate(result.getDate("INV_SUBMIT_DATE"));
            legalInvoiceInputBean.setCreatedById(result.getString("CREATED_BY_ID"));
            legalInvoiceInputBean.setCreatedByDesignation(result.getString("CREATED_BY_DESIGNATION"));
            legalInvoiceInputBean.setCreatedByName(result.getString("CREATED_BY_NAME"));
            legalInvoiceInputBean.setCreatedByUsertype(result.getString("CREATED_BY_USERTYPE"));
            legalInvoiceInputBean.setSaveFlag(result.getString("SAVE_FLAG"));
            legalInvoiceInputBean.setCreatedTimeStamp(result.getDate("CREATED_TIME_STAMP"));
            legalInvoiceInputBean.setUpdatedTimeStamp(result.getDate("UPDATED_TIME_STAMP"));
            legalInvoiceInputBean.setStatus(result.getString("STATUS"));
            legalInvoiceInputBean.setVendorInwardDate(result.getDate("VENDOR_INWARD_DT"));
            legalInvoiceInputBean.setFeeType(result.getString("FEE_TYPE"));
            legalInvoiceInputBean.setRejectReason(result.getString("REASON"));
            legalInvoiceInputBean.setIsWithCourtCaseNo(result.getString("IS_WITH_COURT_CASE_NO"));
            legalInvoiceInputBean.setCorporateOffice(result.getString("CORPORATE_OFFICE"));
//            legalInvoiceInputBean.setRegionText(result.getString("REGION"));
//            legalInvoiceInputBean.setZoneText(result.getString("ZONE"));
//            legalInvoiceInputBean.setCircleText(result.getString("CIRCLE"));
//            legalInvoiceInputBean.setDivisionText(result.getString("DIVISION"));
//            legalInvoiceInputBean.setSubDivisionText(result.getString("SUBDIVISION"));
//            legalInvoiceInputBean.setSectionCode(result.getString("SUBDIVISION"));
//            legalInvoiceInputBean.setSectionText(result.getString("SUBDIVISION"));
//            legalInvoiceInputBean.setSubStationCode(result.getString("SUBDIVISION"));
//            legalInvoiceInputBean.setSubStationText(result.getString("SUBDIVISION"));
             legalInvoiceInputBean.setRegionText(result.getString("REGION_NAME"));
             legalInvoiceInputBean.setRegionCode(result.getString("REGION_ID"));
            legalInvoiceInputBean.setZoneText(result.getString("ZONE_NAME"));
            legalInvoiceInputBean.setZoneCode(result.getString("ZONE_ID"));
            legalInvoiceInputBean.setCircleText(result.getString("CIRCLE_NAME"));
            legalInvoiceInputBean.setCircleCode(result.getString("CIRCLE_ID"));
            legalInvoiceInputBean.setDivisionText(result.getString("DIVISION_NAME"));
            legalInvoiceInputBean.setDivisionCode(result.getString("DIVISION_ID"));
            legalInvoiceInputBean.setSubDivisionText(result.getString("SUB_DIVISION_NAME"));
            legalInvoiceInputBean.setSubDivisionCode(result.getString("SUB_DIVISION_ID"));
            legalInvoiceInputBean.setSectionCode(result.getString("SECTION_ID"));
            legalInvoiceInputBean.setSectionText(result.getString("SECTION_NAME"));
            legalInvoiceInputBean.setSubStationCode(result.getString("SUB_STATION_ID"));
            legalInvoiceInputBean.setSubStationText(result.getString("SUB_STATION_NAME"));
            legalInvoiceInputBean.setStatusFee(result.getString("STATUS_FEE"));
            legalInvoiceInputBean.setParkPostDocNo(result.getString("ZZPARK_POST_DOC_NO"));
            legalInvoiceInputBean.setPayDoneErpDoc(result.getString("ZZPAY_DONE_ERP_DOC"));
            legalInvoiceInputBean.setStartPostDocNo(result.getString("start_post_doc_no"));
            legalInvoiceInputBean.setStartPayDoneErpDoc(result.getString("start_pay_done_erp_doc"));
            legalInvoiceInputBean.setStartPayDoneErpDoc1(result.getString("start_pay_done_erp_doc1"));
            legalInvoiceInputBean.setMobileNo(result.getString("MOBILE_NO"));
            legalInvoiceInputBean.setEmailId(result.getString("EMAIL_ID"));
            legalInvoiceInputBean.setUTR_NO(result.getString("ZZUTR_NO"));
            legalInvoiceInputBean.setPaymentDate(result.getString("ZZFEE_DT_OF_PAYMENT"));
            legalInvoiceInputBean.setDeptCode(result.getString("DEPT_CODE"));
            legalInvoiceInputBean.setDeptName(result.getString("DEPT_NAME"));
            legalInvoiceInputBean.setLiabilityDocNo(result.getString("ZZPARK_POST_DOC_NO"));
            legalInvoiceInputBean.setLiabilityDocDate(result.getString("ZZPARK_POST_DATE"));
            legalInvoiceInputBean.setLiabilityDocAmt(result.getString("ZZPARK_DOC_AMT"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetErpLegalInvoiceStatusList :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return legalInvoiceInputBean;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i = 1;
        try {
           String status= legalInvoiceInputBean.getSaveFlag();
            System.out.println("status :" + legalInvoiceInputBean.getSaveFlag());
            logger.log(Level.INFO, "GetErpLegalInvoiceStatusList ::: getQueryResults() :: method called ::");
//            sql.append(" SELECT * FROM ERP_LEGAL_INVOICE_STATUS ");
//            sql.append("  SELECT * FROM XXMIS_ERP_LEGAL_INVOICE_DETAILS ");
       
  




          
         if (status != null && status.equalsIgnoreCase("Accepted")){
             sql.append(" select LD.*,OM.*,zf.STATUS_FEE ,zf.ZZPARK_POST_DOC_NO,zf.ZZPAY_DONE_ERP_DOC ,zf.ZZUTR_NO,zf.ZZFEE_DT_OF_PAYMENT,zf.ZZPARK_POST_DOC_NO,zf.ZZPARK_POST_DATE,zf.ZZPARK_DOC_AMT," +
             //sql.append(" select LD.*,OM.*,zf.* ," +
"  substr(zf.ZZPARK_POST_DOC_NO,1,2) as start_post_doc_no, substr(zf.ZZPAY_DONE_ERP_DOC,1,2) as start_pay_done_erp_doc , substr(zf.zzpay_done_erp_doc,1,3) AS start_pay_done_erp_doc1"
                     + " from xxmis_erp_legal_invoice_details LD,xxmis_organization_master OM , zhrt_legal_fee zf ");
            sql.append(" where LD.dealing_office_code=OM.organization_id "
                    + " and TO_NUMBER(LD.VENDOR_NUMBER)=zf.vendor" +
                        " and  LD.CASE_REF_NO=zf.caserefno " +
                        " and  LD.INVOICE_NUMBER=zf.invoice_legal " +
                        " and  LD.INVOICE_DATE=zf.invoice_date " +
                        " and  LD.FEE_TYPE=zf.adv_fee_type");
                        if (legalInvoiceInputBean.getCreatedByUsertype() != null) {
                           if (legalInvoiceInputBean.getCreatedByUsertype().equalsIgnoreCase("Vendor")) {
                              if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("vendor")) {
//                        sql.append(" WHERE VENDOR_NUMBER=? ");
                                sql.append(" AND VENDOR_NUMBER=? ");
                    } else if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("applId")) {
//                        sql.append(" WHERE APPL_ID=?  ");
                                sql.append(" AND APPL_ID=?  ");
                    }
//                    else if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("Emp")) {
//                        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getLocationId())) {
//                            sql.append(" WHERE DEALING_OFFICE_CODE=?  ");
//                        }
//                    }
                } else if (legalInvoiceInputBean.getCreatedByUsertype().equalsIgnoreCase("Emp")) {
                    if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("applId")) {
//                        sql.append(" WHERE APPL_ID=?  ");
                        sql.append(" AND APPL_ID=?  ");
                    } else if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("Emp")) {
                        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getLocationId())) {
//                            sql.append(" WHERE DEALING_OFFICE_CODE  IN (select h.organization_id from hr_all_organization_units h, ");
//                            sql.append("          hr_all_organization_units h1, hri_org_hrchy_summary hr ");
//                            sql.append("          where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
//                            sql.append("          and hr.organization_id =?  and h1.organization_id=hr.organization_id )  ");
//sql.append(" where dealing_office_code in(select organization_id from xxmis_organization_master m ");
                        sql.append(" AND dealing_office_code in(select organization_id from xxmis_organization_master m ");
                        sql.append(" where (Region_id=? or zone_id=? or circle_id=? or division_id=? or sub_division_id=?)) ");
                                                    sql.append(" AND (SAVE_FLAG in ('Submitted','Accepted','Returned') OR  (SAVE_FLAG='Saved' AND CREATED_BY_USERTYPE='Emp'))");
                        }
                    }
                    
                    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getVendorNumber())) {
                        sql.append(" AND VENDOR_NUMBER = ?  ");
                    }
//          if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
//           sql.append(" AND EVL.INVOICE_NUMBER = ?  ");
//          }
                    if (!((ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceToDate())))) {
                        sql.append(" AND INVOICE_DATE BETWEEN ? AND  ? ");
                    }
                }

            }
            sql.append(" ORDER BY APPL_ID DESC ");
            
         }



         else{
            sql.append("select zf.ZZUTR_NO,zf.ZZFEE_DT_OF_PAYMENT,zf.ZZPARK_POST_DOC_NO,zf.ZZPARK_POST_DATE,zf.ZZPARK_DOC_AMT, x.*, zf.status_fee,    zf.zzpark_post_doc_no,    zf.zzpay_done_erp_doc,    substr(zf.zzpark_post_doc_no,1,2) AS start_post_doc_no," +
"    substr(zf.zzpay_done_erp_doc,1,2) AS start_pay_done_erp_doc,    substr(zf.zzpay_done_erp_doc,1,3) AS start_pay_done_erp_doc1    from (" +
"SELECT ld.*, om.ID,om.ORGANIZATION_NAME ,om.ORGANIZATION_ID,om.ORG_ID_SAP,om.OFFICE_TYPE,om.OFFICE_LEVEL,om.ADDRESS_LINE01,om.ADDRESS_LINE02,om.ADDRESS_LINE03,om.CITY," +
"om.STATE,om.PIN_CODE,om.COUNTRY,om.PERSONAL_AREA,om.PERSONAL_AREA_NAME,om.PERSONAL_SUBAREA ,om.PERSONAL_SUBAREA_NAME ,om.REGION_ID,om.REGION_ID_SAP," +
"om.REGION_NAME,om.ZONE_ID,om.ZONE_ID_SAP,om.ZONE_NAME,om.CIRCLE_ID,om.CIRCLE_ID_SAP,om.CIRCLE_NAME,om.DIVISION_ID,om.DIVISION_ID_SAP,om.DIVISION_NAME," +
"om.SUB_DIVISION_ID,om.SUB_DIVISION_ID_SAP,om.SUB_DIVISION_NAME,om.SECTION_ID,om.SECTION_ID_SAP ,om.SECTION_NAME,om.SUB_STATION_ID,om.SUB_STATION_ID_SAP," +
"om.SUB_STATION_NAME,om.PAYROLL_LOCATION,om.PAYROLL_AREA,om.PAYROLL_AREA_DESC,om.START_DATE,om.END_DATE  from xxmis_erp_legal_invoice_details LD,xxmis_organization_master OM ");
            sql.append(" where nvl(LD.dealing_office_code,261)=OM.organization_id ");
            

          /*  sql.append(" select LD.*,OM.*,  zf.status_fee,zf.zzpark_post_doc_no,   zf.zzpay_done_erp_doc, substr(zf.zzpark_post_doc_no,1,2) AS start_post_doc_no,    substr(zf.zzpay_done_erp_doc,1,2) AS start_pay_done_erp_doc, "+
            "  substr(zf.zzpay_done_erp_doc,1,3) AS start_pay_done_erp_doc1 "+
            " from  xxmis_erp_legal_invoice_details ld" +
            "   join  xxmis_organization_master om on  ld.dealing_office_code = om.organization_id " +
            "  left join  zhrt_legal_fee zf on   to_number(ld.vendor_number) = zf.vendor  AND ld.case_ref_no = zf.caserefno" +
            "   AND ld.invoice_number = zf.invoice_legal AND ld.invoice_date = zf.invoice_date   AND ld.fee_type = zf.adv_fee_type ");*/
        
                        if (legalInvoiceInputBean.getCreatedByUsertype() != null) {
                           if (legalInvoiceInputBean.getCreatedByUsertype().equalsIgnoreCase("Vendor")) {
                              if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("vendor")) {
//                        sql.append(" WHERE VENDOR_NUMBER=? ");
                                sql.append(" AND VENDOR_NUMBER=? ");
                    } else if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("applId")) {
//                        sql.append(" WHERE APPL_ID=?  ");
                                sql.append(" AND APPL_ID=?  ");
                    }
//                    else if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("Emp")) {
//                        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getLocationId())) {
//                            sql.append(" WHERE DEALING_OFFICE_CODE=?  ");
//                        }
//                    }
                } else if (legalInvoiceInputBean.getCreatedByUsertype().equalsIgnoreCase("Emp")) {
                    if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("applId")) {
//                        sql.append(" WHERE APPL_ID=?  ");
                        sql.append(" AND APPL_ID=?  ");
                    } else if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("Emp")) {
                        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getLocationId())) {
//                            sql.append(" WHERE DEALING_OFFICE_CODE  IN (select h.organization_id from hr_all_organization_units h, ");
//                            sql.append("          hr_all_organization_units h1, hri_org_hrchy_summary hr ");
//                            sql.append("          where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
//                            sql.append("          and hr.organization_id =?  and h1.organization_id=hr.organization_id )  ");
//sql.append(" where dealing_office_code in(select organization_id from xxmis_organization_master m ");
                        sql.append(" AND dealing_office_code in(select organization_id from xxmis_organization_master m ");
                        sql.append(" where (Region_id=? or zone_id=? or circle_id=? or division_id=? or sub_division_id=?)) ");
                                                    sql.append(" AND (SAVE_FLAG in ('Submitted','Accepted','Returned') OR  (SAVE_FLAG='Saved' AND CREATED_BY_USERTYPE='Emp'))");
                        }
                    }
                    
                    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getVendorNumber())) {
                        sql.append(" AND VENDOR_NUMBER = ?  ");
                    }
//          if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
//           sql.append(" AND EVL.INVOICE_NUMBER = ?  ");
//          }
                    if (!((ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceToDate())))) {
                        sql.append(" AND INVOICE_DATE BETWEEN ? AND  ? ");
                    }
                }

            }
            sql.append(" ORDER BY APPL_ID DESC )x LEFT JOIN zhrt_legal_fee zf ON to_number(x.vendor_number) = zf.vendor" +
" AND x.case_ref_no = zf.caserefno  AND x.invoice_number = zf.invoice_legal AND x.invoice_date = zf.invoice_date  AND x.fee_type = zf.adv_fee_type ");
         }

      
            statement = connection.prepareStatement(sql.toString());
            if (legalInvoiceInputBean.getCreatedByUsertype() != null) {
                if (legalInvoiceInputBean.getCreatedByUsertype().equalsIgnoreCase("Vendor")) {
                    if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("vendor")) {
                        statement.setString(i++, legalInvoiceInputBean.getVendorNumber());
                    } else if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("applId")) {
                        statement.setInt(i++, legalInvoiceInputBean.getApplId());
                    } 
//                    else if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("Emp")) {
//                        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getLocationId())) {
//                            statement.setString(i++, legalInvoiceInputBean.getLocationId());
//                        }
//                    }
                } else if (legalInvoiceInputBean.getCreatedByUsertype().equalsIgnoreCase("Emp")) {
                    if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("applId")) {
                        statement.setInt(i++, legalInvoiceInputBean.getApplId());
                    } else if (legalInvoiceInputBean.getWhereClause().equalsIgnoreCase("Emp")) {
                        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getLocationId())) {
                            statement.setString(i++, legalInvoiceInputBean.getLocationId());
                            statement.setString(i++, legalInvoiceInputBean.getLocationId());
                            statement.setString(i++, legalInvoiceInputBean.getLocationId());
                            statement.setString(i++, legalInvoiceInputBean.getLocationId());
                            statement.setString(i++, legalInvoiceInputBean.getLocationId());
                        }
                    }
                    if (legalInvoiceInputBean.getCreatedByUsertype().equalsIgnoreCase("Emp")) {
                        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getVendorNumber())) {
                            statement.setString(i++, legalInvoiceInputBean.getVendorNumber());
                        }
//          if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
//           sql.append(" AND EVL.INVOICE_NUMBER = ?  ");
//          }
                        if (!((ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceToDate())))) {
                            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceInputBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceInputBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                        }
                    }
                }
            }
            System.out.println("sql##::" + sql);
            
          
            rs = statement.executeQuery();
          
            
            

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetErpLegalInvoiceStatusList :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
