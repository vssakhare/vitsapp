/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.formHelper;

import in.emp.vendor.dao.helper.formHelper.GetVendorFormDataQueryHelper;
import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.util.ApplicationUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author pooja
 */
public class GetEmpPsFormDataQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorFormDataQueryHelper.class);
    private VendorBean vendorBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetEmpPsFormDataQueryHelper(VendorBean vendorBeanObj) {
        this.vendorBeanObj = vendorBeanObj;
    }

    public GetEmpPsFormDataQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorBeanObj = this.vendorPrezDataObj.getVendorBean();
    }

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    public Object getDataObject(ResultSet results) throws Exception {
        VendorBean vendorbean = new VendorBean();

        try {

            vendorBeanObj.setZone(results.getString("ZONE"));
            vendorBeanObj.setCircle(results.getString("CIRCLE"));
            vendorBeanObj.setDivision(results.getString("DIVISION"));
            vendorBeanObj.setMsedclInvoiceNumber(results.getString("MSEDCL_INV_NO"));
            vendorBeanObj.setProjectCode(results.getString("PROJECT_ID"));
            vendorBeanObj.setProjectScheme(results.getString("SCHEME_DESC"));
            vendorBeanObj.setPROJECT_DESC(results.getString("PROJECT_DESC"));
            vendorBeanObj.setCREATION_DATE(results.getDate("PROJECT_CREATION_DATE"));
            vendorBeanObj.setSCHEME_CODE(results.getString("SCHEME_CODE"));
            vendorBeanObj.setMATERIAL_PO(results.getString("MATERIAL_PO"));
            vendorBeanObj.setCENTAGES_PO(results.getString("CENTAGES_PO"));
            vendorBeanObj.setSERVICE_PO(results.getString("SERVICE_PO"));
            vendorBeanObj.setMATERIAL_WS_PARK_DOC(results.getString("MATERIAL_WS_PARK_DOC"));
            vendorBeanObj.setMAT_DOC_DATE(results.getDate("MAT_DOC_DATE"));
            vendorBeanObj.setMAT_WS_PARK_AMT(results.getString("MAT_WS_PARK_AMT"));
            vendorBeanObj.setMAT_WS_CLEARING_DOC_NO(results.getString("MAT_WS_CLEARING_DOC_NO"));
            vendorBeanObj.setMAT_WS_AC_DOC_DATE(results.getDate("MAT_WS_AC_DOC_DATE"));
            vendorBeanObj.setMAT_WS_CLEARING_AMT(results.getString("MAT_WS_CLEARING_AMT"));
            vendorBeanObj.setCENTAGES_PARK_DOC(results.getString("CENTAGES_PARK_DOC"));
            vendorBeanObj.setCEN_DOC_DATE(results.getDate("CEN_DOC_DATE"));
            vendorBeanObj.setCEN_PARK_AMT(results.getString("CEN_PARK_AMT"));
            vendorBeanObj.setCEN_CLEARING_DOC_NO(results.getString("CEN_CLEARING_DOC_NO"));
            vendorBeanObj.setCEN_AC_DOC_DATE(results.getDate("CEN_AC_DOC_DATE"));
            vendorBeanObj.setCEN_CLEARING_AMT(results.getString("CEN_CLEARING_AMT"));
            vendorBeanObj.setCIVIL_PARK_DOC(results.getString("CIVIL_PARK_DOC"));
            vendorBeanObj.setCIVIL_DOC_DATE(results.getDate("CIVIL_DOC_DATE"));
            vendorBeanObj.setCIVIL_PARK_AMT(results.getString("CIVIL_PARK_AMT"));
            vendorBeanObj.setCIVIL_CLEARING_DOC_NO(results.getString("CIVIL_CLEARING_DOC_NO"));
            vendorBeanObj.setCIVIL_AC_DOC_DATE(results.getDate("CIVIL_AC_DOC_DATE"));
            vendorBeanObj.setCIVIL_CLEARING_AMT(results.getString("CIVIL_CLEARING_AMT"));
            vendorBeanObj.setMAT_OS_PARK_DOC_NO(results.getString("MAT_OS_PARK_DOC_NO"));
            vendorBeanObj.setMAT_OS_DOC_DATE(results.getDate("MAT_OS_DOC_DATE"));
            vendorBeanObj.setMAT_OS_PARK_AMT(results.getString("MAT_OS_PARK_AMT"));
            vendorBeanObj.setMAT_OS_CLEARING_DOC_NO(results.getString("MAT_OS_CLEARING_DOC_NO"));
            vendorBeanObj.setMAT_OS_AC_DOC_DATE(results.getDate("MAT_OS_AC_DOC_DATE"));
            vendorBeanObj.setMAT_OS_CLEARING_AMT(results.getString("MAT_OS_CLEARING_AMT"));
            vendorBeanObj.setMAT_OTH_PARK_DOC_NO(results.getString("MAT_OTH_PARK_DOC_NO"));
            vendorBeanObj.setMAT_OTH_DOC_DATE(results.getDate("MAT_OTH_DOC_DATE"));
            vendorBeanObj.setMAT_OTH_PARK_AMT(results.getString("MAT_OTH_PARK_AMT"));
            vendorBeanObj.setMAT_OTH_CLEARING_DOC_NO(results.getString("MAT_OTH_CLEARING_DOC_NO"));
            vendorBeanObj.setMAT_OTH_AC_DOC_DATE(results.getDate("MAT_OTH_AC_DOC_DATE"));
            vendorBeanObj.setMAT_OTH_CLEARING_AMT(results.getString("MAT_OTH_CLEARING_AMT"));
            vendorBeanObj.setDispVendorName(results.getString("VENDOR_NAME"));
            vendorBeanObj.setDispVendorNumber(results.getString("VENDOR_NUMBER"));
            vendorBeanObj.setSaveFlag(results.getString("STATUS"));
            vendorBeanObj.setPO_MAT_AMT(results.getString("PO_MAT_AMT"));
            vendorBeanObj.setPO_CEN_AMT(results.getString("PO_CEN_AMT"));
            vendorBeanObj.setPO_CIV_AMT(results.getString("PO_CIV_AMT"));
            vendorBeanObj.setPO_INV_AMT(results.getString("PO_INV_AMT"));
            vendorBeanObj.setTOTAL_PO_AMOUNT(results.getString("TOTAL_PO_AMOUNT"));
            vendorBeanObj.setVendorInvoiceNumber(results.getString("INVOICE_NUMBER"));
            vendorBeanObj.setVendorInvoiceDate(results.getDate("INVOICE_DATE"));
            vendorBeanObj.setMsedclInvoiceDate(results.getDate("INV_CREATIONDATE"));
            vendorBeanObj.setTaxCode(results.getString("TAX_CODE"));
            vendorBeanObj.setINV_TYP(results.getString("INV_TYP"));
            vendorBeanObj.setWTAX_AMT(results.getString("WTAX_AMT"));
            vendorBeanObj.setWIT_TDS(results.getString("WIT_TDS"));
            vendorBeanObj.setWGST_TDS(results.getString("WGST_TDS"));
            vendorBeanObj.setWRPST(results.getString("WRPST"));
            vendorBeanObj.setWRET_AMT(results.getString("WRET_AMT"));
            vendorBeanObj.setOTAX_AMT(results.getString("OTAX_AMT"));
            vendorBeanObj.setOIT_TDS(results.getString("OIT_TDS"));
            vendorBeanObj.setOGST_TDS(results.getString("OGST_TDS"));
            vendorBeanObj.setORPST(results.getString("ORPST"));
            vendorBeanObj.setORET_AMT(results.getString("ORET_AMT"));
            vendorBeanObj.setCTAX_AMT(results.getString("CTAX_AMT"));
            vendorBeanObj.setCIT_TDS(results.getString("CIT_TDS"));
            vendorBeanObj.setCGST_TDS(results.getString("CGST_TDS"));
            vendorBeanObj.setCRPST(results.getString("CRPST"));
            vendorBeanObj.setCRET_AMT(results.getString("CRET_AMT"));
            vendorBeanObj.setSTAX_AMT(results.getString("STAX_AMT"));
            vendorBeanObj.setSIT_TDS(results.getString("SIT_TDS"));
            vendorBeanObj.setSGST_TDS(results.getString("SGST_TDS"));
            vendorBeanObj.setSRPST(results.getString("SRPST"));
            vendorBeanObj.setSRET_AMT(results.getString("SRET_AMT"));
            vendorBeanObj.setOTTAX_AMT(results.getString("OTTAX_AMT"));
            vendorBeanObj.setOTIT_TDS(results.getString("OTIT_TDS"));
            vendorBeanObj.setOTGST_TDS(results.getString("OTGST_TDS"));
            vendorBeanObj.setOTRPST(results.getString("OTRPST"));
            vendorBeanObj.setOTRET_AMT(results.getString("OTRET_AMT"));
  vendorBeanObj.setTECH_STAT(results.getString("TECH_STAT"));
            vendorBeanObj.setApplId(results.getString("APPL_ID"));
           vendorBeanObj.setInvoice_Type(results.getString("INVOICE_TYPE"));
              vendorBeanObj.setMIGST_TX(results.getString("MIGST_TX"));
            vendorBeanObj.setMSGST_TX(results.getString("MSGST_TX"));
            vendorBeanObj.setOSGST_TX(results.getString("OSGST_TX"));
            vendorBeanObj.setOIGST_TX(results.getString("OIGST_TX"));
            vendorBeanObj.setCSGST_TX(results.getString("CSGST_TX"));
            vendorBeanObj.setCIGST_TX(results.getString("CIGST_TX"));
            vendorBeanObj.setSSGST_TX(results.getString("SSGST_TX"));
            vendorBeanObj.setSIGST_TX(results.getString("SIGST_TX"));
            vendorBeanObj.setOTSGST_TX(results.getString("OTSGST_TX"));
            vendorBeanObj.setOTIGST_TX(results.getString("OTIGST_TX"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorFormDataQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

            sql.append("    SELECT PS.ZONE, PS.CIRCLE, PS.DIVISION, PS.PROJECT_ID,PS.APPL_ID,PS.INVOICE_TYPE,     ");
            sql.append("    PS.PROJECT_DESC, TO_DATE(TO_CHAR (PS.CREATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PROJECT_CREATION_DATE,    ");
            sql.append("    PS.SCHEME_CODE, PS.SCHEME_DESC,  PS.MATERIAL_PO, PS.CENTAGES_PO,PS.SERVICE_PO,PS.MSEDCL_INV_NO,     ");
            sql.append("    INVOICE_STATUS STATUS,PS.INVOICE_NUMBER, PS.INVOICE_DATE,  PS.INV_CREATIONDATE,								        ");
            sql.append("    PS.MATERIAL_WS_PARK_DOC, PS.MAT_DOC_DATE,PS.MAT_WS_PARK_AMT,PS.MAT_WS_CLEARING_DOC_NO,PS.MAT_WS_AC_DOC_DATE,PS.MAT_WS_CLEARING_AMT,   ");
            sql.append("    PS.CENTAGES_PARK_DOC,PS.CEN_DOC_DATE,PS.CEN_PARK_AMT,PS.CEN_CLEARING_DOC_NO,PS.CEN_AC_DOC_DATE,PS.CEN_CLEARING_AMT,   		       ");
            sql.append("    PS.CIVIL_PARK_DOC,PS.CIVIL_DOC_DATE,PS.CIVIL_PARK_AMT,PS.CIVIL_CLEARING_DOC_NO,PS.CIVIL_AC_DOC_DATE,PS.CIVIL_CLEARING_AMT,   	     ");
            sql.append("    PS.MAT_OS_PARK_DOC_NO,PS.MAT_OS_DOC_DATE,PS.MAT_OS_PARK_AMT,PS.MAT_OS_CLEARING_DOC_NO,MAT_OS_AC_DOC_DATE,PS.MAT_OS_CLEARING_AMT,     ");
            sql.append("    PS.MAT_OTH_PARK_DOC_NO,PS.MAT_OTH_DOC_DATE ,PS.MAT_OTH_PARK_AMT,PS.MAT_OTH_CLEARING_DOC_NO,PS.MAT_OTH_AC_DOC_DATE,PS.MAT_OTH_CLEARING_AMT,   ");
            sql.append("    PS.PO_MAT_AMT,PS.PO_CEN_AMT,PS.PO_CIV_AMT,PS.PO_INV_AMT,  (nvl(PO_MAT_AMT,0)+nvl(PO_CEN_AMT,0)+nvl(PO_CIV_AMT,0))TOTAL_PO_AMOUNT,   	      ");
            sql.append("    PS.VENDOR_NUMBER, PS.VENDOR_NAME  ,  PS.TAX_CODE ,         ");
            sql.append(" PS.INV_TYP  ,PS.WTAX_AMT,PS.WIT_TDS ,PS.WGST_TDS  ,PS.WRPST      ,PS.WRET_AMT , ");
            sql.append(" PS.OTAX_AMT ,PS.OIT_TDS   ,PS.OGST_TDS   ,PS.ORPST    ,PS.ORET_AMT ,PS.CTAX_AMT ,PS.CIT_TDS   , ");
            sql.append(" PS.CGST_TDS,PS.CRPST   ,PS.CRET_AMT  ,PS.STAX_AMT   ,PS.SIT_TDS  ,PS.SGST_TDS ,PS.SRPST     ,  ");
            sql.append(" PS.SRET_AMT   ,PS.OTTAX_AMT,PS.OTIT_TDS ,PS.OTGST_TDS  ,PS.OTRPST      ,PS.OTRET_AMT,PS.TECH_STAT , ");
   sql.append(" PS.MIGST_TX,PS.MSGST_TX , PS.OSGST_TX ,PS.OIGST_TX ,PS.CSGST_TX,PS.CIGST_TX,PS.SSGST_TX ,PS.SIGST_TX ,PS.OTSGST_TX,PS.OTIGST_TX   ");
            sql.append("    FROM PS_VENDOR_VERIFIED_INPUT_LIST PS     ");
            sql.append(" WHERE TO_NUMBER(PS.VENDOR_NUMBER) = ? ");
            if (!ApplicationUtils.isBlank(vendorBeanObj.getVendorInvoiceNumber())) {

                sql.append(" AND REGEXP_REPLACE(UPPER(PS.INVOICE_NUMBER), '[^0-9A-Za-z]', '')  = ? ");
            }
            if (!ApplicationUtils.isBlank(vendorBeanObj.getProjectCode())) {

                sql.append(" AND PROJECT_ID = ? ");
            }
            if (!ApplicationUtils.isBlank(vendorBeanObj.getApplId())) {
                sql.append(" AND APPL_ID = ? ");
            }

            logger.log(Level.INFO, "GetVendorFormDataQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());

            statement.setString(1, vendorBeanObj.getVendorNumber());
            if (!ApplicationUtils.isBlank(vendorBeanObj.getVendorInvoiceNumber())) {
                statement.setString(2, vendorBeanObj.getVendorInvoiceNumber());
            }
            if (!ApplicationUtils.isBlank(vendorBeanObj.getProjectCode())) {
                statement.setString(3, vendorBeanObj.getProjectCode());
            }
            if (!ApplicationUtils.isBlank(vendorBeanObj.getApplId())) {
                statement.setString(4, vendorBeanObj.getApplId());
            }

            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorFormDataQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
