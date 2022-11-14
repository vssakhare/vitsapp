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
            legalInvoiceInputBean.setInvoiceNumber(result.getString("INVOICE_NUMBER"));
            legalInvoiceInputBean.setInvoiceDate(result.getDate("INVOICE_DATE"));
            legalInvoiceInputBean.setInvoiceAmount(result.getString("INVOICE_AMOUNT"));
            legalInvoiceInputBean.setVendorInvDate(result.getDate("VENDOR_INV_DATE"));
            legalInvoiceInputBean.setMsedclInwardNo(result.getString("MSEDCL_INWARD_NUMBER"));
            legalInvoiceInputBean.setMsedclInwardDate(result.getDate("MSEDCL_INWARD_DATE"));
            legalInvoiceInputBean.setInvSubmitDate(result.getDate("INV_SUBMIT_DATE"));
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
//            sql.append(" SELECT * FROM ERP_LEGAL_INVOICE_STATUS ");
//            sql.append("  SELECT * FROM XXMIS_ERP_LEGAL_INVOICE_DETAILS ");
sql.append(" select * from xxmis_erp_legal_invoice_details LD,xxmis_organization_master OM ");
sql.append(" where LD.dealing_office_code=OM.organization_id ");
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
            System.out.println("sql::" + sql);
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetErpLegalInvoiceStatusList :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
