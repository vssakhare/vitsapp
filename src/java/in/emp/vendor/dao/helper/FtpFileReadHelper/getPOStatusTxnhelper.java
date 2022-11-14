/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.FtpFileReadHelper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.*;
import in.emp.common.ApplicationConstants;
import in.emp.common.Partition;

import in.emp.dao.TxnHelper;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.PoStatusBean;
import in.emp.vendor.bean.VendorStatuBean;
import java.sql.CallableStatement;
import org.apache.commons.collections.ListUtils;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja
 */
public class getPOStatusTxnhelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(getPOStatusTxnhelper.class);
    private PoStatusBean postatusbeanobj;
    List<PoStatusBean>	lstErpToVitsFileFormat;
    private VendorPrezData vendorPrezDataObj;

    /**
     * @public Constructor TAclaimsTxnHelper()
     * @param vendorBeanObj object of TAclaimsBean
     */
    public getPOStatusTxnhelper()
    {
        
    }
    
    public getPOStatusTxnhelper(List lstErpToVitsFileFormat) {
        this.lstErpToVitsFileFormat = lstErpToVitsFileFormat;
    }//constructor ends

    /**
     * @public Constructor TAclaimsTxnHelper()
     *
     * @param vendorPrezDataObj	TAclaimsPrezData
     */
    public getPOStatusTxnhelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.postatusbeanobj = this.vendorPrezDataObj.getPostatusList();
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
      
   int[] count;
       StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "getPOStatusTxnhelper ::: createObject() :: method called ::");
            
if(lstErpToVitsFileFormat != null && lstErpToVitsFileFormat.size() >0)
	{
	logger.log(Level.INFO, "lstErpToVitsFileFormat.size() ::"+lstErpToVitsFileFormat.size());
        System.out.println("lstErpToVitsFileFormat.size() ::"+lstErpToVitsFileFormat.size());
       
  
  // System.out.println("Inserting records to PO_STATUS_NEW_TEMP2");
sql.append(" INSERT INTO PO_STATUS_NEW_TEMP2 ");
            sql.append(" ( PO_STATUS_ID,zone, circle,division, plant,po_number, po_desc,po_type_code, po_type,po_creation_date, po_doc_date,validity_start, validity_end,total_po_amt,vendor_number, "); // 14 here
            sql.append(" vendor_name,ses_no,ses_desc, ses_create_date, ses_doc_date, ses_amount,release_status,migo_doc,doc_year,migo_amt,profit_center, bal_amount,vendor_inv_no, "); // 13 till here
            sql.append(" invoice_date,msedcl_inv_no,inv_amt,cl_doc_no,paid_amt,po_short_close,cl_doc_dt, migo_dt, inv_dt, tax_code,tax_amount,it_tds_amount,gst_tds,retension_amount, "); // 14 here
             sql.append(" project_code,project_scheme,abap_create_date,db_insert_date,ps_inv_no,SES_VEN_INV_NO,MIGO_VEN_INV_NO,MSEDCL_INV_NO_FLAG,MSEDCL_INV_NO_REVERSAL,SES_FLAG,PURCHASING_GROUP,PURCHASING_DESC,PO_DESC_N,TENDER_LOA_LOE_NO) "); //11 here
            sql.append(" VALUES ");
            sql.append(" ( XXMIS_ERP_PO_STATUS_ID_SEQ.NEXTVAL,?, ?, ?, "); // 3 here
            sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, nvl(trim(?),0), ?, ?, ?, ?, ?, ?,nvl( trim(?),0), ?, ?, ?, nvl(trim(?),0), ?,nvl(rtrim(?,'-'),0), ?, ?, ?, nvl(trim(?),0), ?,nvl(trim(?),0), ?, ?, ?, ?,  "); // 33 till here
            sql.append(" ?, nvl(trim(?),0), nvl(trim(?),0), nvl(trim(?),0), nvl(trim(?),0), ?,?,?, SYSTIMESTAMP,?,?,?,?,?,? ,?,?,?,?) "); // 16till here


            statement = conn.prepareStatement(sql.toString());
             for (PoStatusBean postatusbeanobj : lstErpToVitsFileFormat) {
            statement.setString(1, postatusbeanobj.getZONE());
            statement.setString(2, postatusbeanobj.getCIRCLE());
            statement.setString(3, postatusbeanobj.getDIVISION());
            statement.setString(4, postatusbeanobj.getPLANT());
            statement.setString(5, postatusbeanobj.getPO_NUMBER());
            statement.setString(6, postatusbeanobj.getPO_DESC());
            statement.setString(7, postatusbeanobj.getPO_TYPE_CODE());
            statement.setString(8, postatusbeanobj.getPO_TYPE());

            statement.setDate(9, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(postatusbeanobj.getPO_CREATION_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            statement.setDate(10, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(postatusbeanobj.getPO_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            statement.setDate(11, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(postatusbeanobj.getVALIDITY_START(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            statement.setDate(12, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(postatusbeanobj.getVALIDITY_END(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            statement.setString(13, postatusbeanobj.getTOTAL_PO_AMT());
            statement.setString(14, postatusbeanobj.getVENDOR_NUMBER());
            statement.setString(15, postatusbeanobj.getVENDOR_NAME());
            statement.setString(16, postatusbeanobj.getSES_NO());
            statement.setString(17, postatusbeanobj.getSES_DESC());

            statement.setDate(18, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(postatusbeanobj.getSES_CREATE_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            statement.setDate(19, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(postatusbeanobj.getSES_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            statement.setString(20, postatusbeanobj.getSES_AMOUNT());
            statement.setString(21, postatusbeanobj.getRELEASE_STATUS());
            statement.setString(22, postatusbeanobj.getMIGO_DOC());
            statement.setString(23, postatusbeanobj.getDOC_YEAR());
            statement.setString(24, postatusbeanobj.getMIGO_AMT());
            statement.setString(25, postatusbeanobj.getPROFIT_CENTER());
            statement.setString(26, postatusbeanobj.getBAL_AMOUNT());
            statement.setString(27, postatusbeanobj.getVENDOR_INV_NO());
            statement.setDate(28, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(postatusbeanobj.getINVOICE_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            statement.setString(29, postatusbeanobj.getMSEDCL_INV_NO());
            statement.setString(30, postatusbeanobj.getINV_AMT());
            statement.setString(31, postatusbeanobj.getCL_DOC_NO());
            statement.setString(32, postatusbeanobj.getPAID_AMT());
            statement.setString(33, postatusbeanobj.getPO_SHORT_CLOSE());

            statement.setDate(34, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(postatusbeanobj.getCL_DOC_DT(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            statement.setDate(35, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(postatusbeanobj.getMIGO_DT(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            statement.setDate(36, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(postatusbeanobj.getINV_DT(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(37, postatusbeanobj.getTAX_CODE());
            statement.setString(38, postatusbeanobj.getTAX_AMOUNT());
            statement.setString(39, postatusbeanobj.getIT_TDS_AMOUNT());
            statement.setString(40, postatusbeanobj.getGST_TDS());
            statement.setString(41, postatusbeanobj.getRETENSION_AMOUNT());
            statement.setString(42, postatusbeanobj.getPROJECT_CODE());
            statement.setString(43, postatusbeanobj.getPROJECT_SCHEME());
            statement.setDate(44, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(postatusbeanobj.getCREATE_DATE() , ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT)) ;  
            statement.setString(45, postatusbeanobj.getMSEDCL_PSINV_NO());
            statement.setString(46,postatusbeanobj.getSES_VEN_INV_NO());
             statement.setString(47,postatusbeanobj.getMIGO_VEN_INV_NO());
             statement.setString(48,postatusbeanobj.getMSEDCL_INV_NO_FLAG());
             statement.setString(49,postatusbeanobj.getMSEDCL_INV_NO_REVERSAL());
             statement.setString(50,postatusbeanobj.getSES_FLAG());
             statement.setString(51,postatusbeanobj.getPurchasing_group());
               statement.setString(52,postatusbeanobj.getPurchasing_desc());
                statement.setString(53,postatusbeanobj.getPO_DESC_N());
                statement.setString(54,postatusbeanobj.getTENDER_LOA_LOE());
            // statement.setString(50,postatusbeanobj.getMIGO_FLAG());
          //  logger.log(Level.INFO, "getPOStatusTxnhelper ::: createObject() :: SQL :: " + sql.toString());
             statement.addBatch();
                }

            count = new int[lstErpToVitsFileFormat.size()];

                count = statement.executeBatch();
          
         conn.commit(); 
 
        }

        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "VendorApplTxnHelper ::: createObject() :: Exception :: " + ex);
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
         CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "getPOStatusTxnhelper ::: updateObject() :: method called ::");

           System.out.println("Calling Procedure po After changes");
        proc_stmt = conn.prepareCall("{ call PO_RECORDS_INSERT_FROM_SAP }");
        logger.log(Level.INFO, "getPOStatusTxnhelper ::: PO_RECORDS_INSERT_FROM_SAP() :: PROC called ::");
       proc_stmt.executeQuery();
       logger.log(Level.INFO, "getPOStatusTxnhelper ::: PO_RECORDS_INSERT_FROM_SAP() :: PROC ENDED ::");
       System.out.println("Finished Calling PO Procedure");
       
       
              
          //  logger.log(Level.INFO, "getPOStatusTxnhelper ::: updateObject() :: SQL :: " + sql.toString());

            
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "VendorApplTxnHelper ::: updateObject() :: Exception :: " + ex);
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
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "getPOStatusTxnhelper ::: deleteObject() :: method called ::");

            sql.append("truncate table po_status_new_temp2 ");
            
            statement = conn.prepareStatement(sql.toString());
           

            logger.log(Level.INFO, "getPOStatusTxnhelper ::: deleteObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();

            conn.commit();
            System.out.println("deleted records of table po_status_new_temp2");
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "getPOStatusTxnhelper ::: deleteObject() :: Exception :: " + ex);
            throw ex;
        }finally {
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