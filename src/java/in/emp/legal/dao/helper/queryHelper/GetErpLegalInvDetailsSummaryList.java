/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.legal.bean.LegalSummaryBean;
import in.emp.util.ApplicationUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 *
 * @author Pooja
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class GetErpLegalInvDetailsSummaryList implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetErpLegalInvDetailsSummaryList.class);
    private LegalSummaryBean legalSummaryBeanObj = null;

    public GetErpLegalInvDetailsSummaryList(LegalSummaryBean legalSummaryBeanObj) {
        this.legalSummaryBeanObj = legalSummaryBeanObj;
    }

    public Object getDataObject(ResultSet result) throws Exception {
        LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
        try {
            logger.log(Level.INFO, "GetErpLegalInvDetailsSummaryList ::: getDataObject() :: method called ::");
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
            legalInvoiceInputBean.setInvoiceAmount(result.getInt("INVOICE_AMOUNT"));
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
            legalInvoiceInputBean.setPaidAmount(result.getString("ZZPAY_DOC_AMT"));
            legalInvoiceInputBean.setsFeeType(result.getString("sFee_type"));
            legalInvoiceInputBean.setsAmount(result.getInt("sAmount"));
            legalInvoiceInputBean.setPaymentDocDate(result.getString("ZZPOST_DATE"));
            legalInvoiceInputBean.setFiscalYear(result.getString("ZZPOST_FISCAL"));
            legalInvoiceInputBean.setDeductionAmount(result.getString("DEDUCTION_AMOUNT"));
            legalInvoiceInputBean.setReasonForDeduction(result.getString("REASON_FOR_DEDUCTION"));

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

            logger.log(Level.INFO, "GetErpLegalInvoiceStatusList ::: getQueryResults() :: method called ::");
          sql.append("SELECT * FROM (select zf.ZZUTR_NO,zf.ZZFEE_DT_OF_PAYMENT,zf.ZZPARK_POST_DOC_NO,zf.ZZPARK_POST_DATE,zf.ZZPARK_DOC_AMT,zf.DATE_OF_SUBMISSION,zf.date_of_approval,zf.invoice_date zf_invoice_date,zf.reciept_date,zf.ZZPAY_DOC_AMT,zf.ZZPOST_DATE, x.*, zf.status_fee, zf.zzpay_done_erp_doc,zf.ZZPOST_FISCAL ,   substr(zf.zzpark_post_doc_no,1,2) AS start_post_doc_no," +
"    substr(zf.zzpay_done_erp_doc,1,2) AS start_pay_done_erp_doc,    substr(zf.zzpay_done_erp_doc,1,3) AS start_pay_done_erp_doc1,(zf.INVOICE_AMOUNT-zf.FEE_RECOMMENDED)DEDUCTION_AMOUNT, zf.REASON_FOR_DEDUCTION   from (" +
"SELECT ld.*, om.ID,om.ORGANIZATION_NAME ,om.ORGANIZATION_ID,om.ORG_ID_SAP,om.OFFICE_TYPE,SAP_STATUS,om.OFFICE_LEVEL,om.ADDRESS_LINE01,om.ADDRESS_LINE02,om.ADDRESS_LINE03,om.CITY," +
"om.STATE,om.PIN_CODE,om.COUNTRY,om.PERSONAL_AREA,om.PERSONAL_AREA_NAME,om.PERSONAL_SUBAREA ,om.PERSONAL_SUBAREA_NAME ,om.REGION_ID,om.REGION_ID_SAP," +
"om.REGION_NAME,om.ZONE_ID,om.ZONE_ID_SAP,om.ZONE_NAME,om.CIRCLE_ID,om.CIRCLE_ID_SAP,om.CIRCLE_NAME,om.DIVISION_ID,om.DIVISION_ID_SAP,om.DIVISION_NAME," +
"om.SUB_DIVISION_ID,om.SUB_DIVISION_ID_SAP,om.SUB_DIVISION_NAME,om.SECTION_ID,om.SECTION_ID_SAP ,om.SECTION_NAME,om.SUB_STATION_ID,om.SUB_STATION_ID_SAP," +
"om.SUB_STATION_NAME,om.PAYROLL_LOCATION,om.PAYROLL_AREA,om.PAYROLL_AREA_DESC,om.START_DATE,om.END_DATE,f.FEE_TYPE as sFee_type,f.amount as sAmount  from xxmis_erp_legal_invoice_details LD join   xxmis_erp_legal_invoice_fee_type_dtls  f on     f.APPL_ID=LD.APPL_ID,xxmis_organization_master OM ");
            sql.append(" where nvl(LD.dealing_office_code,261)=OM.organization_id ");
            sql.append(" AND dealing_office_code in(select organization_id from xxmis_organization_master m ");
            sql.append(" where ( Region_id=? or zone_id=? or circle_id=? or division_id=? or sub_division_id=?)");
            
            if(legalSummaryBeanObj.getZone()!=null && legalSummaryBeanObj.getZone().contains("TOTAL")){
             sql.append(" AND  ZONE_NAME=? ");
            }
            sql.append(" )");
            
            if(legalSummaryBeanObj.getZone()!=null && !legalSummaryBeanObj.getZone().contains("TOTAL")){
            if (!ApplicationUtils.isBlank(legalSummaryBeanObj.getZone())) {
                sql.append(" AND OM.ZONE_NAME=? ");
            }
            if (!ApplicationUtils.isBlank(legalSummaryBeanObj.getCircle())) {
                sql.append(" AND OM.CIRCLE_NAME=? ");
            }
            if (!ApplicationUtils.isBlank(legalSummaryBeanObj.getDivision())) {
                sql.append(" AND OM.DIVISION_NAME=? ");
            }
            if (!ApplicationUtils.isBlank(legalSummaryBeanObj.getSubdiv())) {
                sql.append(" AND OM.SUB_DIVISION_NAME=? ");
            }
            if (!ApplicationUtils.isBlank(legalSummaryBeanObj.getDept())) {
                sql.append(" AND LD.DEPT_NAME=? ");
            }else{
                 sql.append(" AND LD.DEPT_NAME is null ");
            }
            }
            sql.append(" AND (SAVE_FLAG in ('Accepted') )");
            if (!((ApplicationUtils.isBlank(legalSummaryBeanObj.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalSummaryBeanObj.getInvoiceToDate())))) {
                        sql.append(" AND INVOICE_DATE BETWEEN ? AND  ? ");
                    }
            sql.append(" ORDER BY LD.APPL_ID DESC )x LEFT JOIN zhrt_legal_fee zf ON to_number(x.vendor_number) = zf.vendor" +
" AND x.case_ref_no = zf.caserefno  AND x.invoice_number = zf.invoice_legal AND x.invoice_date = zf.invoice_date  AND x.sFee_type = zf.adv_fee_type) ");
         
            if (legalSummaryBeanObj.getStatus_dtl().equalsIgnoreCase("vSubmit")) {

            } else if (legalSummaryBeanObj.getStatus_dtl().equalsIgnoreCase("pTech_less")) {

                sql.append(" where  SAP_STATUS in('With Technical') AND  NVL((trunc(SYSDATE) - trunc(case   when nvl(nvl(date_of_approval,reciept_date),invoice_date)>sysdate  then least(nvl(date_of_approval,zf_invoice_date),nvl(reciept_date,zf_invoice_date))   else  nvl(nvl(date_of_approval,reciept_date),zf_invoice_date)     end )),0)<=30  ");
            } else if (legalSummaryBeanObj.getStatus_dtl().equalsIgnoreCase("pTech_more")) {

                sql.append(" where  SAP_STATUS in('With Technical') AND  NVL((trunc(SYSDATE) - trunc(case   when nvl(nvl(date_of_approval,reciept_date),invoice_date)>sysdate  then least(nvl(date_of_approval,zf_invoice_date),nvl(reciept_date,zf_invoice_date))   else  nvl(nvl(date_of_approval,reciept_date),zf_invoice_date)     end )),0)>30  ");
            } else if (legalSummaryBeanObj.getStatus_dtl().equalsIgnoreCase("pAcc_less")) {

                sql.append(" where SAP_STATUS ='With Accounts' AND  NVL((trunc(SYSDATE) - trunc(DATE_OF_SUBMISSION)),0)<=30  ");
            } else if (legalSummaryBeanObj.getStatus_dtl().equalsIgnoreCase("pAcc_more")) {

                sql.append(" where SAP_STATUS ='With Accounts' AND  NVL((trunc(SYSDATE) - trunc(DATE_OF_SUBMISSION)),0)>30   ");
            } else if (legalSummaryBeanObj.getStatus_dtl().equalsIgnoreCase("pCash_less")) {

                sql.append(" where SAP_STATUS in ('With Cash','Payment Document Reversed') AND  NVL((trunc(SYSDATE) - trunc(ZZPARK_POST_DATE)),0)<=30  ");
            } else if (legalSummaryBeanObj.getStatus_dtl().equalsIgnoreCase("pCash_more")) {

                sql.append(" where SAP_STATUS in ('With Cash','Payment Document Reversed') AND  NVL((trunc(SYSDATE) - trunc(ZZPARK_POST_DATE)),0)>30 ");
            } else if (legalSummaryBeanObj.getStatus_dtl().equalsIgnoreCase("pTotal")) {
                sql.append(" where SAP_STATUS in ('With Cash','Payment Document Reversed','With Accounts','With Technical') ");

            } else if (legalSummaryBeanObj.getStatus_dtl().equalsIgnoreCase("paid")) {

                sql.append(" where SAP_STATUS in ('Payment Done','Payment Adjusted')  ");
            } 
            
           statement = connection.prepareStatement(sql.toString());
            statement.setString(i++, legalSummaryBeanObj.getLocationId());
            statement.setString(i++, legalSummaryBeanObj.getLocationId());
            statement.setString(i++, legalSummaryBeanObj.getLocationId());
            statement.setString(i++, legalSummaryBeanObj.getLocationId());
            statement.setString(i++, legalSummaryBeanObj.getLocationId());
              if(legalSummaryBeanObj.getZone()!=null && legalSummaryBeanObj.getZone().contains("TOTAL")){
                
             statement.setString(i++, legalSummaryBeanObj.getZone().substring(0,legalSummaryBeanObj.getZone().length() - 6));
             System.out.println(legalSummaryBeanObj.getZone().substring(0,legalSummaryBeanObj.getZone().length() - 6));
            }
            if(legalSummaryBeanObj.getZone()!=null && !legalSummaryBeanObj.getZone().contains("TOTAL")){
            if (!ApplicationUtils.isBlank(legalSummaryBeanObj.getZone())) {
                statement.setString(i++, legalSummaryBeanObj.getZone());
            }
            if (!ApplicationUtils.isBlank(legalSummaryBeanObj.getCircle())) {
                statement.setString(i++, legalSummaryBeanObj.getCircle());
            }
            if (!ApplicationUtils.isBlank(legalSummaryBeanObj.getDivision())) {
                statement.setString(i++, legalSummaryBeanObj.getDivision());
            }
            if (!ApplicationUtils.isBlank(legalSummaryBeanObj.getSubdiv())) {
                statement.setString(i++, legalSummaryBeanObj.getSubdiv());
            }
            if (!ApplicationUtils.isBlank(legalSummaryBeanObj.getDept())) {
               statement.setString(i++, legalSummaryBeanObj.getDept());
            }
            }
             if (!((ApplicationUtils.isBlank(legalSummaryBeanObj.getInvoiceFromDate())) && (ApplicationUtils.isBlank(legalSummaryBeanObj.getInvoiceToDate())))) {
                            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalSummaryBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalSummaryBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                        }
             System.out.println("sql##::" + sql);

            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetErpLegalInvDetailsSummaryList :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
