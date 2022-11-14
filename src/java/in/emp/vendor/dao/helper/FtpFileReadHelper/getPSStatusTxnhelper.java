/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.FtpFileReadHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.TxnHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.PSBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class getPSStatusTxnhelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(getPOStatusTxnhelper.class);
    private PSBean psstatusbeanobj;
    List<PSBean> lstErpToVitsFileFormat;
    private VendorPrezData vendorPrezDataObj;

    /**
     * @public Constructor TAclaimsTxnHelper()
     * @param vendorBeanObj object of TAclaimsBean
     */
    public getPSStatusTxnhelper() {

    }

    public getPSStatusTxnhelper(List lstErpToVitsFileFormat) {
        this.lstErpToVitsFileFormat = lstErpToVitsFileFormat;
    }//constructor ends

    /**
     * @public Constructor TAclaimsTxnHelper()
     *
     * @param vendorPrezDataObj	TAclaimsPrezData
     */
    public getPSStatusTxnhelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.psstatusbeanobj = this.vendorPrezDataObj.getPsstatusList();
    }//constructor ends

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    public Object createObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        CallableStatement proc_stmt = null;
        int[] count;

        try {
            logger.log(Level.INFO, "getPSStatusTxnhelper ::: createObject() :: method called ::");

            if (lstErpToVitsFileFormat != null && lstErpToVitsFileFormat.size() > 0) {
                logger.log(Level.INFO, "lstErpToVitsFileFormat.size() ::" + lstErpToVitsFileFormat.size());
                System.out.println("lstErpToVitsFileFormat.size() ::" + lstErpToVitsFileFormat.size());

                StringBuilder sql = new StringBuilder();

                //System.out.println(itr.next());
             //   System.out.println("Inserting records to PS_PO_STATUS_NEW");
                sql.append(" INSERT INTO PS_PO_STATUS_NEW ");
                sql.append(" ( PROJECT_CODE , PROJECT_DESC,    CREATION_DATE ,    PLANT ,    ZONE  ,    CIRCLE ,    DIVISION ,    SCHEME_CODE ,    SCHEME_DESC ,    MATERIAL_PO ,    CENTAGES_PO,    SERVICE_PO, "); // 4 here
                sql.append(" VENDOR_NAME ,    VENDOR_NUMBER ,    MSEDCL_INV_NO ,    TECH_STAT,    MATERIAL_WS_PARK_DOC    , MAT_DOC_DATE ,    MAT_WS_PARK_AMT,MAT_WS_CLEARING_DOC_NO  ,    MAT_WS_AC_DOC_DATE , "); // 11 till here
                sql.append(" MAT_WS_CLEARING_AMT,    CENTAGES_PARK_DOC,    CEN_DOC_DATE ,    CEN_PARK_AMT,    CEN_CLEARING_DOC_NO     ,    CEN_AC_DOC_DATE,    CEN_CLEARING_AMT , ");
                sql.append("CIVIL_PARK_DOC  ,    CIVIL_DOC_DATE  ,    CIVIL_PARK_AMT  ,    CIVIL_CLEARING_DOC_NO   ,CIVIL_AC_DOC_DATE,    CIVIL_CLEARING_AMT,    MAT_OS_PARK_DOC_NO  ,");
                sql.append("MAT_OS_DOC_DATE,    MAT_OS_PARK_AMT,MAT_OS_CLEARING_DOC_NO  ,MAT_OS_AC_DOC_DATE ,MAT_OS_CLEARING_AMT,");
                sql.append(" MAT_OTH_PARK_DOC_NO,MAT_OTH_DOC_DATE,MAT_OTH_PARK_AMT,MAT_OTH_CLEARING_DOC_NO,MAT_OTH_AC_DOC_DATE,MAT_OTH_CLEARING_AMT,INV_STAT, ");
                sql.append(" PO_MAT_AMT,PO_CEN_AMT,PO_CIV_AMT,PO_INV_AMT,INV_CREATIONDATE,INV_PSDATE,DB_INSERT_DATE,ACC_STAT,    ");
                sql.append(" INV_TYP  , TAX_CODE  , WTAX_AMT, WIT_TDS , WGST_TDS  , WRPST      , WRET_AMT , OTAX_AMT , OIT_TDS   , ");
                sql.append(" OGST_TDS   , ORPST    , ORET_AMT , CTAX_AMT , CIT_TDS   , CGST_TDS, CRPST   , CRET_AMT  , STAX_AMT   , ");
                sql.append(" SIT_TDS  ,SGST_TDS ,SRPST     ,SRET_AMT   ,OTTAX_AMT,OTIT_TDS ,OTGST_TDS  ,OTRPST      ,OTRET_AMT,SAP_APPL_ID,VENDOR_INV_NO,  ");
                sql.append(" ZONE_CODE  ,CIRCLE_CODE ,MIGST_TX     ,MSGST_TX   ,OSGST_TX,OIGST_TX ,CSGST_TX  ,CIGST_TX      ,SSGST_TX,SIGST_TX,OTSGST_TX,OTIGST_TX,PURCHASING_GROUP ) ");
                sql.append(" VALUES ");
                sql.append(" (?, ?, ?, ?, ?, ?,?,?,?,?, ");
                sql.append(" ?, ?, ?, ?, ?, ?,?,?,?,?, ");
                sql.append(" ?, ?, ?, ?, ?, ?,?,?,?,?, ");
                sql.append(" ?, ?, ?, ?, ?, ?,?,?,?,?, ");
                sql.append(" ?, ?, ?, ?, ?, ?,?,?,?,?, ");
                sql.append(" ?, ?, ?,SYSTIMESTAMP,?,  ");
                sql.append("?, ?, ?, ?, ?,?,?,?,?,");
                sql.append("?, ?, ?, ?, ?,?,?,?,?,");
                sql.append("?, ?, ?, ?, ?,?,?,?,?,?,?,");
                sql.append("?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)");
                statement = conn.prepareStatement(sql.toString());

                for (PSBean psstatusbeanobj : lstErpToVitsFileFormat) {
                    int i = 1;
                    statement.setString(i++, psstatusbeanobj.getPROJECT_ID());
                    statement.setString(i++, psstatusbeanobj.getPROJECT_DESC());
                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getCREATION_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

                    statement.setString(i++, psstatusbeanobj.getPLANT());
                    statement.setString(i++, psstatusbeanobj.getZONE());
                    statement.setString(i++, psstatusbeanobj.getCIRCLE());
                    statement.setString(i++, psstatusbeanobj.getDIVISION());
                    statement.setString(i++, psstatusbeanobj.getSCHEME_CODE());
                    statement.setString(i++, psstatusbeanobj.getSCHEME_DESC());
                    statement.setString(i++, psstatusbeanobj.getMATERIAL_PO());
                    statement.setString(i++, psstatusbeanobj.getCENTAGES_PO());
                    statement.setString(i++, psstatusbeanobj.getSERVICE_PO());
                    statement.setString(i++, psstatusbeanobj.getVENDOR_NAME());
                    statement.setString(i++, psstatusbeanobj.getVENDOR_NUMBER());
                    statement.setString(i++, psstatusbeanobj.getINVNO());
                    statement.setString(i++, psstatusbeanobj.getTECH_STAT());
                    statement.setString(i++, psstatusbeanobj.getMATERIAL_WS_PARK_DOC());
                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getMAT_WS_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                    statement.setString(i++, psstatusbeanobj.getMAT_WS_PARK_AMT());
                    statement.setString(i++, psstatusbeanobj.getMAT_WS_CLEARING_DOC_NO());
                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getMAT_WS_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

                    statement.setString(i++, psstatusbeanobj.getMAT_WS_CLEARING_AMT());
                    statement.setString(i++, psstatusbeanobj.getCENTAGES_PARK_DOC());

                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getCEN_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

                    statement.setString(i++, psstatusbeanobj.getCEN_PARK_AMT());
                    statement.setString(i++, psstatusbeanobj.getCEN_CLEARING_DOC_NO());
                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getCEN_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

                    statement.setString(i++, psstatusbeanobj.getCEN_CLEARING_AMT());
                    statement.setString(i++, psstatusbeanobj.getCIVIL_PARK_DOC());
                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getCIVIL_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

                    statement.setString(i++, psstatusbeanobj.getCIVIL_PARK_AMT());
                    statement.setString(i++, psstatusbeanobj.getCIVIL_CLEARING_DOC_NO());
                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getCIVIL_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

                    statement.setString(i++, psstatusbeanobj.getCIVIL_CLEARING_AMT());
                    statement.setString(i++, psstatusbeanobj.getMAT_OS_PARK_DOC_NO());
                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getMAT_OS_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

                    statement.setString(i++, psstatusbeanobj.getMAT_OS_PARK_AMT());
                    statement.setString(i++, psstatusbeanobj.getMAT_OS_CLEARING_DOC_NO());
                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getMAT_OS_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

                    statement.setString(i++, psstatusbeanobj.getMAT_OS_CLEARING_AMT());
                    statement.setString(i++, psstatusbeanobj.getOTH_PARK_DOC_NO());
                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getOTH_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

                    statement.setString(i++, psstatusbeanobj.getOTH_PARK_AMT());
                    statement.setString(i++, psstatusbeanobj.getOTH_CLEARING_DOC_NO());
                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getOTH_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

                    statement.setString(i++, psstatusbeanobj.getOTH_CLEARING_AMT());
                    statement.setString(i++, psstatusbeanobj.getINV_STAT());
                    statement.setString(i++, psstatusbeanobj.getPO_MAT_AMT());
                    statement.setString(i++, psstatusbeanobj.getPO_CEN_AMT());
                    statement.setString(i++, psstatusbeanobj.getPO_CIV_AMT());
                    statement.setString(i++, psstatusbeanobj.getPO_INV_AMT());
                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getINV_CREATIONDATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                    statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(psstatusbeanobj.getINV_PSDATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                    statement.setString(i++, psstatusbeanobj.getACC_STAT());
                    statement.setString(i++, psstatusbeanobj.getINV_TYP());
                    statement.setString(i++, psstatusbeanobj.getTAX_CODE());
                    statement.setString(i++, psstatusbeanobj.getWTAX_AMT());
                    statement.setString(i++, psstatusbeanobj.getWIT_TDS());
                    statement.setString(i++, psstatusbeanobj.getWGST_TDS());
                    statement.setString(i++, psstatusbeanobj.getWRPST());
                    statement.setString(i++, psstatusbeanobj.getWRET_AMT());
                    statement.setString(i++, psstatusbeanobj.getOTAX_AMT());
                    statement.setString(i++, psstatusbeanobj.getOIT_TDS());
                    statement.setString(i++, psstatusbeanobj.getOGST_TDS());
                    statement.setString(i++, psstatusbeanobj.getORPST());
                    statement.setString(i++, psstatusbeanobj.getORET_AMT());
                    statement.setString(i++, psstatusbeanobj.getCTAX_AMT());
                    statement.setString(i++, psstatusbeanobj.getCIT_TDS());
                    statement.setString(i++, psstatusbeanobj.getCGST_TDS());
                    statement.setString(i++, psstatusbeanobj.getCRPST());
                    statement.setString(i++, psstatusbeanobj.getCRET_AMT());
                    statement.setString(i++, psstatusbeanobj.getSTAX_AMT());
                    statement.setString(i++, psstatusbeanobj.getSIT_TDS());
                    statement.setString(i++, psstatusbeanobj.getSGST_TDS());
                    statement.setString(i++, psstatusbeanobj.getSRPST());
                    statement.setString(i++, psstatusbeanobj.getSRET_AMT());
                    statement.setString(i++, psstatusbeanobj.getOTTAX_AMT());
                    statement.setString(i++, psstatusbeanobj.getOTIT_TDS());
                    statement.setString(i++, psstatusbeanobj.getOTGST_TDS());
                    statement.setString(i++, psstatusbeanobj.getOTRPST());
                    statement.setString(i++, psstatusbeanobj.getOTRET_AMT());
                    statement.setString(i++, psstatusbeanobj.getSAP_APPL_ID());
                    statement.setString(i++, psstatusbeanobj.getVENDOR_INV_NO());

                    statement.setString(i++, psstatusbeanobj.getZONE_CODE());
                    statement.setString(i++, psstatusbeanobj.getCIRCLE_CODE());
                    statement.setString(i++, psstatusbeanobj.getMIGST_TX());
                    statement.setString(i++, psstatusbeanobj.getMSGST_TX());
                    statement.setString(i++, psstatusbeanobj.getOSGST_TX());
                    statement.setString(i++, psstatusbeanobj.getOIGST_TX());
                    statement.setString(i++, psstatusbeanobj.getCSGST_TX());
                    statement.setString(i++, psstatusbeanobj.getCIGST_TX());
                    statement.setString(i++, psstatusbeanobj.getSSGST_TX());
                    statement.setString(i++, psstatusbeanobj.getSIGST_TX());
                    statement.setString(i++, psstatusbeanobj.getOTSGST_TX());
                    statement.setString(i++, psstatusbeanobj.getOTIGST_TX());
                    statement.setString(i++, psstatusbeanobj.getPURCHASING_GROUP());
                  //  logger.log(Level.INFO, "getPSStatusTxnhelper ::: createObject() :: SQL :: " + sql.toString());

                    statement.addBatch();
                }

                count = new int[lstErpToVitsFileFormat.size()];

                count = statement.executeBatch();
                conn.commit();

            }
            //NOT IN USE CURRENTLY
            // System.out.println("Calling Procedure ps_insert_from_sap");
            // proc_stmt = conn.prepareCall("{ call PS_RECORDS_INSERT_FROM_SAP }");
            //proc_stmt.executeQuery();
            //System.out.println("Finished Calling PS Procedure");

        } catch (Exception ex) {
            conn.rollback();
            System.out.println("Calling PS Procedure for restore");
            proc_stmt = conn.prepareCall("{ call PS_TABLE_RESTORE_PROC }");
            proc_stmt.executeQuery();
            System.out.println("Finished Calling PS Procedure for RESTORE");
            logger.log(Level.WARN, "VendorApplTxnHelper ::: createObject() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;

        }
        return vendorPrezDataObj;
    }//createObject() ends

    /**
     * Public API to update data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "getPSStatusTxnhelper ::: updateObject() :: method called ::");

            createObject(conn);
            logger.log(Level.INFO, "getPSStatusTxnhelper ::: updateObject() :: SQL :: " + sql.toString());

        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.WARN, "getPSStatusTxnhelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }

    }//updateObject() ends

    /**
     * Public API to delete data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    public void deleteObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "getPSStatusTxnhelper ::: deleteObject() :: method called ::");
            System.out.println("Calling Procedure for backup");
            proc_stmt = conn.prepareCall("{ call PS_TABLE_BACKUP_PROC }");
            proc_stmt.executeQuery();
            System.out.println("Finished Calling Procedure for backup");

            sql.append("truncate table PS_PO_STATUS_NEW ");

            statement = conn.prepareStatement(sql.toString());

            logger.log(Level.INFO, "getPSStatusTxnhelper ::: deleteObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();

            conn.commit();
            System.out.println("deleted records of table PS_PO_STATUS_NEW_TEMP2");
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.WARN, "getPSStatusTxnhelper ::: deleteObject() :: Exception :: " + ex);
            throw ex;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception ignored) {
                }
            }
        }
    }//deleteObject() ends

    /**
     * Public API to update ObjectAttribute
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
    }//updateObjectAttribute ends
}
