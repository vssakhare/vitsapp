/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.txnHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.TxnHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Rikma Rai
 */
public class VendorRetentionTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(VendorApplFormTxnHelper.class);
    private VendorInputBean vendorBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public VendorRetentionTxnHelper(VendorInputBean vendorBeanObj) {
        this.vendorBeanObj = vendorBeanObj;
    }

    public VendorRetentionTxnHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorBeanObj = this.vendorPrezDataObj.getVendorInputBean();
    }

    @Override
    public Object createObject(Connection conn) throws Exception {
        int count = 0;
        PreparedStatement statement = null;
        //PreparedStatement statement1 = null;
        CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        vendorPrezDataObj = new VendorPrezData();
        try {
            logger.log(Level.INFO, "VendorRetentionTxnHelper ::: createObject() :: method called ::");
            System.out.println("VendorRetentionTxnHelper ::: createObject() :: method called ::");

            // vendorBeanObj = vendorPrezDataObj.getVendorBean();
            System.out.println("appl_id::" + vendorBeanObj.getApplId());
            if (vendorBeanObj.getApplId() == null || vendorBeanObj.getApplId().equals("")) {
                vendorBeanObj.setApplId(ApplicationUtils.getNextSequenceId(conn, "SEQ_VENDOR_RT_DETAILS").toString());
                System.out.println("before appl_id::" + vendorBeanObj.getApplId() + "   count::" + count);
            }
            //   getSelectedModuleType is used to differentiate between project system and po number         
            // sql.append(" INSERT INTO XXMIS_ERP_VENDOR_DTL ");
            //sql.append(" ( ID, APPL_ID, PO_NUMBER, INVOICE_NUMBER, INVOICE_AMOUNT, "); // 6 here
            //sql.append(" INVOICE_DATE, CREATED_TIME_STAMP, INWARD_NUMBER, INWARD_DATE, SAVE_FLAG, UPDATED_TIME_STAMP, INVOICE_FROM_DT, INVOICE_TO_DT, VENDOR_NUMBER, VENDOR_NAME ) "); // 10 till here            
            // sql.append(" VALUES ");
            // sql.append(" ( TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD') || 000000 ) + XXMIS_ERP_VENDOR_DTL_SEQ.NEXTVAL, ?, "); // 1 here            
            // sql.append("  ?, ?, ?, "); // 1 + 2 here
            //sql.append(" ?, SYSTIMESTAMP, ?, ?, '10', null, ?, ?, ?, ? ) "); 

            sql.append("INSERT INTO VENDOR_RETENTION_DETAILS ");
            sql.append(" ( ID , APPL_ID, SERIAL_NO, CLAIM_DATE , VENDOR_NUMBER  , VENDOR_NAME  , "); // 6 here
            sql.append(" PROJECT_ID  , INVOICE_TYPE  , SHORT_TEXT  , INVOICE_NUMBER  , ");
            sql.append(" INVOICE_DATE , GROSS_VALUE , INWARD_NUMBER  , INWARD_DATE ,   ");
            //sql.append(" WS_RETENTION_DOC  , O_RETENTION_DOC  , CEN_RETENTION_DOC  , ");
            sql.append(" RETENTION_DOC  , TOTAL_AMOUNT , INV_NO,STATUS,SUBMIT_AT_LOCATION,SUBMIT_AT_DESC,PROJECT_DESC ) ");
            if(vendorBeanObj.getFullOrPartialRetention().equalsIgnoreCase("full")){
                sql.append(" (SELECT SEQ_VENDOR_RT_DETAILS.NEXTVAL,?,?,SYSDATE ,PS.VENDOR_NUMBER,PS.VENDOR_NAME,PS.PROJECT_CODE,'RT', ");
                sql.append(" 'RETENTION CLAIM CHARGES',PS.MSEDCL_INV_NO,?,");
                sql.append(" ?,?,?, ");
                sql.append(" ?,(nvl(WRET_AMT,0)+nvl(ORET_AMT,0)+nvl(CRET_AMT,0)+nvl(SRET_AMT,0)),");
                sql.append(" ?,?,?,?,?  ");
                sql.append(" FROM PS_PO_STATUS_NEW PS WHERE PS.PROJECT_CODE = ? AND PS.MSEDCL_INV_NO = ?)  ");
                            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, vendorBeanObj.getApplId());
            statement.setString(2, vendorBeanObj.getSerialNo());
            statement.setDate(3, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBeanObj.getInvoiceSubmitDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(4, vendorBeanObj.getVendorInvoiceAmount());
            statement.setString(5, vendorBeanObj.getInwardNumber());
            statement.setDate(6, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBeanObj.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            
            if (vendorBeanObj.getWRPST() != null) {
                statement.setString(7, vendorBeanObj.getWRPST());
            }
            else if (vendorBeanObj.getORPST() != null) {
                statement.setString(7, vendorBeanObj.getORPST());
            }
            else if (vendorBeanObj.getSRPST() != null) {
                statement.setString(7, vendorBeanObj.getSRPST());
            }
            else if (vendorBeanObj.getCRPST() != null) {
                statement.setString(7, vendorBeanObj.getCRPST());
            }
            statement.setString(8, vendorBeanObj.getVendorInvoiceNumber());
            statement.setString(9, vendorBeanObj.getSaveFlag());
            statement.setString(10, vendorBeanObj.getSubmitAtPlant());
            statement.setString(11, vendorBeanObj.getSubmitAtDesc());
            statement.setString(12, vendorBeanObj.getProjectDesc());
            statement.setString(13, vendorBeanObj.getProjectId());
            statement.setString(14, vendorBeanObj.getMsedclInvoiceNumber());
            logger.log(Level.INFO, "VendorRetentionTxnHelper ::: createObject() :: SQL :: " + sql.toString());
            }
            else if(vendorBeanObj.getFullOrPartialRetention().equalsIgnoreCase("partial")){
                sql.append(" VALUES (SEQ_VENDOR_RT_DETAILS.NEXTVAL,?,?,SYSDATE ,?,?,?,'RT', ");
                sql.append(" 'RETENTION CLAIM CHARGES',?,?,");
                sql.append(" ?,?,?, ");
                sql.append(" ?,?,");
                sql.append(" ?,?,?,?,? ) ");
                statement = conn.prepareStatement(sql.toString());
                statement.setString(1, vendorBeanObj.getApplId());
                statement.setString(2, vendorBeanObj.getSerialNo());
                statement.setString(3, vendorBeanObj.getVendorNumber());
                statement.setString(4, vendorBeanObj.getVendorName());
                statement.setString(5, vendorBeanObj.getProjectId());
                statement.setString(6, vendorBeanObj.getMsedclInvoiceNumber());
                statement.setDate(7, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBeanObj.getInvoiceSubmitDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                statement.setString(8, vendorBeanObj.getVendorInvoiceAmount());
                statement.setString(9, vendorBeanObj.getInwardNumber());
                statement.setDate(10, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBeanObj.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                statement.setString(11, vendorBeanObj.getWRPST());
                statement.setString(12, vendorBeanObj.getWRET_AMT());
                statement.setString(13, vendorBeanObj.getVendorInvoiceNumber());
                statement.setString(14, vendorBeanObj.getSaveFlag());
                statement.setString(15, vendorBeanObj.getSubmitAtPlant());
                statement.setString(16, vendorBeanObj.getSubmitAtDesc());
                statement.setString(17, vendorBeanObj.getProjectDesc());
                logger.log(Level.INFO, "VendorRetentionTxnHelper ::: createObject() :: SQL :: " + sql.toString());
            }

            count = statement.executeUpdate();
            if (count == 0) {
                vendorBeanObj.setApplId("");
            }

            if (count > 0) {

                vendorPrezDataObj.setVendorInputBean(vendorBeanObj);

            }
            System.out.println("after appl_id::" + vendorBeanObj.getApplId() + "   count::" + count);
            conn.commit();
            return vendorBeanObj;

        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
            logger.log(Level.ERROR, "VendorApplFormTxnHelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        }

    }

    @Override
    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;

        String sf = "10";
        try {
            logger.log(Level.INFO, "VendorProjApplFormTxnHelper ::: updateObject() :: method called ::");

            if (vendorBeanObj.getSaveFlag() != null) {
                if (vendorBeanObj.getSaveFlag().equals("Saved")) {
                    sf = "10";
                } else if (vendorBeanObj.getSaveFlag().equals("Submitted")) {
                    sf = "20";
                } else if (vendorBeanObj.getSaveFlag().equals("Verified")) {
                    sf = "30";
                } else if (vendorBeanObj.getSaveFlag().equals("Rejected")) {
                    sf = "40";
                }
            }
            if (sf.equals("10") && vendorBeanObj.getModuleSaveFlag().equals("NON_PO")) {

                if (count == 1) {
                    deleteObject(conn);
                }
            }
            if (sf.equals("10") && (!vendorBeanObj.getModuleSaveFlag().equals("NON_PO"))) {

            }
            if ((sf.equals("30")) || (sf.equals("40")) || (sf.equals("20"))) {
                sql.append(" update vendor_retention_details");
                sql.append("          set status=?,INWARD_NUMBER=?,INWARD_DATE=?");
                //sql.append(" INVOICE_FROM_DT = ?, INVOICE_TO_DT = ? "); // 16-18 till here 
                /*if(sf.equals("30")) {
           sql.append(" INVOICE_APPROVAL_DATE = ? ,  "); 
           }
            else {
           sql.append(" INVOICE_REJECTION_DATE = ? ,  "); 
           }
              
           sql.append("  SUBMIT_AT_LOCATION = ?,IS_REJECTED = ? ,IS_APPROVED = ? ,VENDOR_INWARD_DATE = ?,MODULE_TYPE = ?,INVOICE_TYPE=? ");*/

                sql.append(" WHERE APPL_ID = ? "); // 19 till here
                statement = conn.prepareStatement(sql.toString());
                statement.setString(1, vendorBeanObj.getSaveFlag());
                statement.setString(2, vendorBeanObj.getInwardNumber());
                statement.setDate(3, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBeanObj.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                statement.setString(4, vendorBeanObj.getApplId());

                count = statement.executeUpdate();
                if (count > 0 && sf.equals("30")) {
                    StringBuilder sqlUpdate = new StringBuilder();
                    sqlUpdate.append(" UPDATE SUMMARY_STATUS ");
                    sqlUpdate.append(" set invoice_status='Pending For Payment' ");
                    sqlUpdate.append(" where appl_id='" + vendorBeanObj.getApplId() + "'");
                    System.out.println("query::" + sqlUpdate.toString());
                    PreparedStatement stmt = conn.prepareStatement(sqlUpdate.toString());
                    count = stmt.executeUpdate();
                }
                conn.commit();
            }
            // vendorPrezDataObj.setVendorBean(vendorInputBeanObj);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.ERROR, "VendorProjApplFormTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }

        //leaveapplFormBeanObj = leaveapplFormPrezDataObj.getLeaveApplFormBean();
    }//updateObject() ends

    @Override
    public void deleteObject(Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
