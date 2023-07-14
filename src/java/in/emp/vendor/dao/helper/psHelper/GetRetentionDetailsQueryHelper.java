/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.psHelper;

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
 * @author Rikma Rai
 */
public class GetRetentionDetailsQueryHelper implements QueryHelper{
   private static Logger logger = Logger.getLogger(GetRetentionDetailsQueryHelper.class);
    private VendorBean vendorBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetRetentionDetailsQueryHelper(VendorBean vendorBeanObj) {
        this.vendorBeanObj = vendorBeanObj;
    }
    public GetRetentionDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorBeanObj = this.vendorPrezDataObj.getVendorBean();
    }
    
    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        VendorBean vendorbean = new VendorBean();
//not used to fetch the invoice form details
        try {

            vendorbean.setZone(results.getString("ZONE"));
            vendorbean.setCircle(results.getString("CIRCLE"));
            vendorbean.setDivision(results.getString("DIVISION"));
            vendorbean.setMsedclInvoiceNumber(results.getString("MSEDCL_INV_NO"));
            vendorbean.setProjectCode(results.getString("PROJECT_CODE"));
            vendorbean.setProjectScheme(results.getString("SCHEME_DESC"));
            vendorbean.setPROJECT_DESC(results.getString("PROJECT_DESC"));
            vendorbean.setCREATION_DATE(results.getDate("PROJECT_CREATION_DATE"));
            vendorbean.setSCHEME_CODE(results.getString("SCHEME_CODE"));
            vendorbean.setMATERIAL_PO(results.getString("MATERIAL_PO"));
            vendorbean.setCENTAGES_PO(results.getString("CENTAGES_PO"));
            vendorbean.setSERVICE_PO(results.getString("SERVICE_PO"));
            vendorbean.setMATERIAL_WS_PARK_DOC(results.getString("MATERIAL_WS_PARK_DOC"));
            vendorbean.setMAT_DOC_DATE(results.getDate("MAT_DOC_DATE"));
            vendorbean.setMAT_WS_PARK_AMT(results.getString("MAT_WS_PARK_AMT"));
            vendorbean.setMAT_WS_CLEARING_DOC_NO(results.getString("MAT_WS_CLEARING_DOC_NO"));
            vendorbean.setMAT_WS_AC_DOC_DATE(results.getDate("MAT_WS_AC_DOC_DATE"));
            vendorbean.setMAT_WS_CLEARING_AMT(results.getString("MAT_WS_CLEARING_AMT"));
            vendorbean.setCENTAGES_PARK_DOC(results.getString("CENTAGES_PARK_DOC"));
            vendorbean.setCEN_DOC_DATE(results.getDate("CEN_DOC_DATE"));
            vendorbean.setCEN_PARK_AMT(results.getString("CEN_PARK_AMT"));
            vendorbean.setCEN_CLEARING_DOC_NO(results.getString("CEN_CLEARING_DOC_NO"));
            vendorbean.setCEN_AC_DOC_DATE(results.getDate("CEN_AC_DOC_DATE"));
            vendorbean.setCEN_CLEARING_AMT(results.getString("CEN_CLEARING_AMT"));
            vendorbean.setCIVIL_PARK_DOC(results.getString("CIVIL_PARK_DOC"));
            vendorbean.setCIVIL_DOC_DATE(results.getDate("CIVIL_DOC_DATE"));
            vendorbean.setCIVIL_PARK_AMT(results.getString("CIVIL_PARK_AMT"));
            vendorbean.setCIVIL_CLEARING_DOC_NO(results.getString("CIVIL_CLEARING_DOC_NO"));
            vendorbean.setCIVIL_AC_DOC_DATE(results.getDate("CIVIL_AC_DOC_DATE"));
            vendorbean.setCIVIL_CLEARING_AMT(results.getString("CIVIL_CLEARING_AMT"));
            vendorbean.setMAT_OS_PARK_DOC_NO(results.getString("MAT_OS_PARK_DOC_NO"));
            vendorbean.setMAT_OS_DOC_DATE(results.getDate("MAT_OS_DOC_DATE"));
            vendorbean.setMAT_OS_PARK_AMT(results.getString("MAT_OS_PARK_AMT"));
            vendorbean.setMAT_OS_CLEARING_DOC_NO(results.getString("MAT_OS_CLEARING_DOC_NO"));
            vendorbean.setMAT_OS_AC_DOC_DATE(results.getDate("MAT_OS_AC_DOC_DATE"));
            vendorbean.setMAT_OS_CLEARING_AMT(results.getString("MAT_OS_CLEARING_AMT"));
            vendorbean.setMAT_OTH_PARK_DOC_NO(results.getString("MAT_OTH_PARK_DOC_NO"));
            vendorbean.setMAT_OTH_DOC_DATE(results.getDate("MAT_OTH_DOC_DATE"));
            vendorbean.setMAT_OTH_PARK_AMT(results.getString("MAT_OTH_PARK_AMT"));
            vendorbean.setMAT_OTH_CLEARING_DOC_NO(results.getString("MAT_OTH_CLEARING_DOC_NO"));
            vendorbean.setMAT_OTH_AC_DOC_DATE(results.getDate("MAT_OTH_AC_DOC_DATE"));
            vendorbean.setMAT_OTH_CLEARING_AMT(results.getString("MAT_OTH_CLEARING_AMT"));
            vendorbean.setDispVendorName(results.getString("VENDOR_NAME"));
            vendorbean.setDispVendorNumber(results.getString("VENDOR_NUMBER"));
            vendorbean.setSaveFlag(results.getString("STATUS"));
            vendorbean.setPO_MAT_AMT(results.getString("PO_MAT_AMT"));
            vendorbean.setPO_CEN_AMT(results.getString("PO_CEN_AMT"));
            vendorbean.setPO_CIV_AMT(results.getString("PO_CIV_AMT"));
            vendorbean.setPO_INV_AMT(results.getString("PO_INV_AMT"));
            vendorbean.setTOTAL_PO_AMOUNT(results.getString("TOTAL_PO_AMOUNT"));
            vendorbean.setMIGST_TX(results.getString("MIGST_TX"));
            vendorbean.setMSGST_TX(results.getString("MSGST_TX"));
            vendorbean.setOSGST_TX(results.getString("OSGST_TX"));
            vendorbean.setOIGST_TX(results.getString("OIGST_TX"));
            vendorbean.setCSGST_TX(results.getString("CSGST_TX"));
            vendorbean.setCIGST_TX(results.getString("CIGST_TX"));
            vendorbean.setSSGST_TX(results.getString("SSGST_TX"));
            vendorbean.setSIGST_TX(results.getString("SIGST_TX"));
            vendorbean.setOTSGST_TX(results.getString("OTSGST_TX"));
            vendorbean.setOTIGST_TX(results.getString("OTIGST_TX"));
            vendorbean.setWRPST(results.getString("WRPST"));
            vendorbean.setWRET_AMT(results.getString("WRET_AMT"));
            vendorbean.setORPST(results.getString("ORPST"));
            vendorbean.setORET_AMT(results.getString("ORET_AMT"));
            vendorbean.setCRPST(results.getString("CRPST"));
            vendorbean.setCRET_AMT(results.getString("CRET_AMT"));
            vendorbean.setSRPST(results.getString("SRPST"));
            vendorbean.setSRET_AMT(results.getString("SRET_AMT"));
            

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetRetentionDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorbean;
    }

    @Override
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

            sql.append(" SELECT PS.ZONE, PS.CIRCLE, PS.DIVISION, PS.PROJECT_CODE,  ");
            sql.append("  PS.PROJECT_DESC, TO_DATE(TO_CHAR (PS.CREATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PROJECT_CREATION_DATE, ");
            sql.append(" PS.SCHEME_CODE, PS.SCHEME_DESC,  PS.MATERIAL_PO, PS.CENTAGES_PO,PS.SERVICE_PO,PS.MSEDCL_INV_NO, ");
            sql.append(" (CASE WHEN NVL(TECH_STAT,'P') LIKE 'P' THEN 'Technical'  WHEN nvl(ACC_STAT,'P') LIKE 'P' THEN 'Accounts' WHEN nvl(INV_STAT,'P') LIKE 'P' THEN 'Pending For Payment'  WHEN nvl(INV_STAT,'P') LIKE 'C' THEN 'Paid' END) STATUS,  ");
            sql.append(" PS.MATERIAL_WS_PARK_DOC, PS.MAT_DOC_DATE,PS.MAT_WS_PARK_AMT,PS.MAT_WS_CLEARING_DOC_NO,PS.MAT_WS_AC_DOC_DATE,PS.MAT_WS_CLEARING_AMT, ");
            sql.append(" PS.CENTAGES_PARK_DOC,PS.CEN_DOC_DATE,PS.CEN_PARK_AMT,PS.CEN_CLEARING_DOC_NO,PS.CEN_AC_DOC_DATE,PS.CEN_CLEARING_AMT, ");
            sql.append(" PS.CIVIL_PARK_DOC,PS.CIVIL_DOC_DATE,PS.CIVIL_PARK_AMT,PS.CIVIL_CLEARING_DOC_NO,PS.CIVIL_AC_DOC_DATE,PS.CIVIL_CLEARING_AMT, ");
            sql.append(" PS.MAT_OS_PARK_DOC_NO,PS.MAT_OS_DOC_DATE,PS.MAT_OS_PARK_AMT,PS.MAT_OS_CLEARING_DOC_NO,MAT_OS_AC_DOC_DATE,PS.MAT_OS_CLEARING_AMT, ");
            sql.append(" PS.MAT_OTH_PARK_DOC_NO,PS.MAT_OTH_DOC_DATE ,PS.MAT_OTH_PARK_AMT,PS.MAT_OTH_CLEARING_DOC_NO,PS.MAT_OTH_AC_DOC_DATE,PS.MAT_OTH_CLEARING_AMT, ");
            sql.append(" PS.PO_MAT_AMT,PS.PO_CEN_AMT,PS.PO_CIV_AMT,PS.PO_INV_AMT,  (nvl(PO_MAT_AMT,0)+nvl(PO_CEN_AMT,0)+nvl(PO_CIV_AMT,0)) TOTAL_PO_AMOUNT,  ");
            sql.append("PS.MIGST_TX,PS.MSGST_TX , PS.OSGST_TX ,PS.OIGST_TX ,PS.CSGST_TX,PS.CIGST_TX,PS.SSGST_TX ,PS.SIGST_TX ,PS.OTSGST_TX,PS.OTIGST_TX ,  ");
            sql.append(" PS.VENDOR_NUMBER, PS.VENDOR_NAME,PS.WRPST,PS.WRET_AMT,PS.ORPST,PS.ORET_AMT,PS.CRPST,PS.CRET_AMT,PS.SRPST,PS.SRET_AMT ");
            sql.append(" FROM PS_PO_STATUS_NEW PS ");

            //sql.append(" WHERE TO_NUMBER(PS.VENDOR_NUMBER) = ? ");
            if (!ApplicationUtils.isBlank(vendorBeanObj.getProjectCode())) {

                sql.append(" WHERE PROJECT_CODE = ? ");
                sql.append(" AND PS.MSEDCL_INV_NO not in(select  INVOICE_NUMBER from  VENDOR_RETENTION_DETAILS WHERE PROJECT_ID=PS.PROJECT_CODE) ");
                sql.append(" AND (nvl(WRET_AMT,0)+nvl(ORET_AMT,0)+nvl(CRET_AMT,0)+nvl(SRET_AMT,0))>0");
            }

            logger.log(Level.INFO, "GetRetentionDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
            System.out.println("GetRetentionDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
            statement = connection.prepareStatement(sql.toString());

            statement.setString(1, vendorBeanObj.getProjectCode());
//            if (!ApplicationUtils.isBlank(vendorBeanObj.getProjectCode())) {
//                statement.setString(2, vendorBeanObj.getProjectCode());
//            }
            System.out.println("vendorBeanObj.getProjectCode()::"+vendorBeanObj.getProjectCode());
            System.out.println("vendorBeanObj.getVendorNumber()::"+vendorBeanObj.getVendorNumber());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            System.out.println("GetRetentionDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            logger.log(Level.ERROR, "GetRetentionDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
       // rs.next();
      /*  while(rs.next())
        {
            System.out.println("inv_no:::"+rs.getString("MSEDCL_INV_NO"));
        }*/
        return rs;
    }
    
}
