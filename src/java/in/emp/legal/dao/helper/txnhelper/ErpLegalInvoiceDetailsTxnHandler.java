/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.txnhelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.TxnHelper;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.util.ApplicationUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Rikma Rai
 */
public class ErpLegalInvoiceDetailsTxnHandler implements TxnHelper {

    private static Logger logger = Logger.getLogger(ErpLegalInvoiceDetailsTxnHandler.class);
    private LegalInvoiceInputBean legalInvoiceBean;

    List<LegalInvoiceInputBean> listErpToVitsFileFormat;

    public ErpLegalInvoiceDetailsTxnHandler(List listErpToVitsFileFormat) {
        this.listErpToVitsFileFormat = listErpToVitsFileFormat;
    }//constructor ends

    public ErpLegalInvoiceDetailsTxnHandler(LegalInvoiceInputBean legalInvoiceBean) {
        this.legalInvoiceBean = legalInvoiceBean;
    }

    public ErpLegalInvoiceDetailsTxnHandler() {
    }

    @Override
    public Object createObject(Connection conn) throws Exception {
        int count;
        PreparedStatement statement = null;
        //PreparedStatement statement1 = null;
        CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "ErpLegalInvoiceStatusTxnHelper ::: createObject() :: method called ::");
            System.out.println("ErpLegalInvoiceStatusTxnHelper ::: createObject() :: method called ::");
            legalInvoiceBean.setApplId(ApplicationUtils.getNextSequenceId(conn, "SEQ_ERP_LEGAL_INVOICE_DETAILS").intValue());
            sql.append("INSERT INTO XXMIS_ERP_LEGAL_INVOICE_DETAILS ");
            sql.append(" (APPL_ID  , VENDOR_NUMBER ,  VENDOR_NAME   , COURT_CASE_NO, ");
            sql.append(" CASE_REF_NO ,  COURT_NAME   , CASE_DESCRIPTION,   DEALING_OFFICE_CODE ,  ");
            sql.append(" DEALING_OFFICE_NAME,   PARTY_NAMES  , INVOICE_NUMBER ,  INVOICE_DATE, ");
            sql.append("  INVOICE_AMOUNT , VENDOR_INV_DATE , MSEDCL_INWARD_NUMBER  ,");
            //sql.append(" MSEDCL_INWARD_DATE , INV_SUBMIT_DATE , CREATED_BY_ID ,");
            sql.append(" MSEDCL_INWARD_DATE , VENDOR_INWARD_DT , CREATED_BY_ID ,");
            sql.append(" CREATED_BY_DESIGNATION ,  CREATED_BY_NAME ,  CREATED_BY_USERTYPE ,  ");
            sql.append(" SAVE_FLAG ,  CREATED_TIME_STAMP  , UPDATED_TIME_STAMP,FEE_TYPE,IS_WITH_COURT_CASE_NO,REGION,ZONE,CIRCLE,DIVISION,SUBDIVISION,CORPORATE_OFFICE,CASE_TYPE_DESC,VS_PARTY_NAMES,DEPT_NAME,DEPT_CODE,EMAIL_ID,MOBILE_NO)");
            sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,SYSTIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?,(SELECT EMAIL FROM ERP_VENDOR_MASTER WHERE VENDOR_CODE=?),(SELECT PHN_MOB FROM ERP_VENDOR_MASTER WHERE VENDOR_CODE=?))");

            statement = conn.prepareStatement(sql.toString());
            statement.setInt(1, legalInvoiceBean.getApplId());
            statement.setString(2, legalInvoiceBean.getVendorNumber());
            statement.setString(3, legalInvoiceBean.getVendorName());
            statement.setString(4, legalInvoiceBean.getCourtCaseNo());
            statement.setString(5, legalInvoiceBean.getCaseRefNo());
            statement.setString(6, legalInvoiceBean.getCourtName());
            statement.setString(7, legalInvoiceBean.getCaseDescription());
            statement.setString(8, legalInvoiceBean.getDealingOfficeCode());
            statement.setString(9, legalInvoiceBean.getDealingOfficeName());
            statement.setString(10, legalInvoiceBean.getPartyNames());
            statement.setString(11, legalInvoiceBean.getInvoiceNumber());
            statement.setDate(12, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setInt(13, legalInvoiceBean.getInvoiceAmount());
            statement.setDate(14, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getVendorInvDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            statement.setString(15, legalInvoiceBean.getMsedclInwardNo());
            statement.setDate(16, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getMsedclInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            //statement.setDate(17, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getInvSubmitDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(18, legalInvoiceBean.getCreatedById());
            statement.setString(19, legalInvoiceBean.getCreatedByDesignation());
            statement.setString(20, legalInvoiceBean.getCreatedByName());
            statement.setString(21, legalInvoiceBean.getCreatedByUsertype());
            statement.setString(22, legalInvoiceBean.getSaveFlag());
            statement.setString(23, legalInvoiceBean.getFeeType());
            statement.setString(24, legalInvoiceBean.getIsWithCourtCaseNo());
            statement.setString(25, legalInvoiceBean.getRegionText());
            statement.setString(26, legalInvoiceBean.getZoneText());
            statement.setString(27, legalInvoiceBean.getCircleText());
            statement.setString(28, legalInvoiceBean.getDivisionText());
            statement.setString(29, legalInvoiceBean.getSubDivisionText());
            statement.setString(30, legalInvoiceBean.getCorporateOffice());
            statement.setString(31, legalInvoiceBean.getCaseTypeDesc());
            statement.setString(32, legalInvoiceBean.getVsPartyNames());
            statement.setString(33, legalInvoiceBean.getDeptName());
            statement.setString(34, legalInvoiceBean.getDeptCode());
            statement.setString(35, legalInvoiceBean.getVendorNumber());
            statement.setString(36, legalInvoiceBean.getVendorNumber());
            statement.setDate(17, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getVendorInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            logger.log(Level.INFO, "ErpLegalInvoiceStatusTxnHelper ::: createObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            if (count == 0) {
                legalInvoiceBean.setApplId(0);
            }

            if (count > 0) {

//                vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
                String appl_id = legalInvoiceBean.getApplId() + "";
                int appl_id1 = Integer.parseInt(appl_id);
//               proc_stmt = conn.prepareCall("{ call PROC_SUMMARY_UPD_PS(?) }");
//               proc_stmt.setString(1, legalInvoiceBean.getApplId());
//                proc_stmt.executeQuery();
                conn.commit();

            }
            System.out.println("count::" + count + "   app_id::" + legalInvoiceBean.getApplId());

        } catch (Exception e) {
            e.printStackTrace();
            legalInvoiceBean.setApplId(0);
        }

        return legalInvoiceBean;

    }

    @Override
    public void updateObject(Connection conn) throws Exception {
         PreparedStatement statement = null;
         CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
      int i=1;
        String sf = "10";
        try {
            logger.log(Level.INFO, "ErpLegalInvoiceStatusTxnHelper ::: updateObject() :: method called ::");
                 if((legalInvoiceBean.getSaveFlag().equals("Saved")))
             {
             sql.append(" UPDATE XXMIS_ERP_LEGAL_INVOICE_DETAILS ");
            sql.append(" SET SAVE_FLAG = ?,  INVOICE_AMOUNT = ?, UPDATED_TIME_STAMP = SYSTIMESTAMP , invoice_date =? ,invoice_number=?"); // 16-18 till here      
            sql.append(" WHERE APPL_ID = ? ");
         
                       logger.log(Level.INFO, "ErpLegalInvoiceStatusTxnHelper :: getQueryResults() :: SQL :: " + sql.toString());
            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, legalInvoiceBean.getSaveFlag());
             java.sql.Date sqlDate = new java.sql.Date(legalInvoiceBean.getInvoiceDate().getTime());
              statement.setInt(2, legalInvoiceBean.getInvoiceAmount());
                statement.setDate(3,sqlDate );
                  statement.setString(4, legalInvoiceBean.getInvoiceNumber());
              statement.setInt(5, legalInvoiceBean.getApplId());
          
            count = statement.executeUpdate();
             }
             if((legalInvoiceBean.getSaveFlag().equals("Submitted")))
             {
             sql.append(" UPDATE XXMIS_ERP_LEGAL_INVOICE_DETAILS ");
            sql.append(" SET SAVE_FLAG = ?, FEE_TYPE = ?, INVOICE_AMOUNT = ?, UPDATED_TIME_STAMP = SYSTIMESTAMP "); // 16-18 till here      
            sql.append(" WHERE APPL_ID = ? ");
         
                       logger.log(Level.INFO, "ErpLegalInvoiceStatusTxnHelper :: getQueryResults() :: SQL :: " + sql.toString());
            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, legalInvoiceBean.getSaveFlag());
            statement.setString(2, legalInvoiceBean.getFeeType());
              statement.setInt(3, legalInvoiceBean.getInvoiceAmount());
              statement.setInt(4, legalInvoiceBean.getApplId());
          
            count = statement.executeUpdate();
             }
             if((legalInvoiceBean.getSaveFlag().equals("Accepted")) || (legalInvoiceBean.getSaveFlag().equals("Rejected")) || (legalInvoiceBean.getSaveFlag().equals("Returned")))
             {
             sql.append(" UPDATE XXMIS_ERP_LEGAL_INVOICE_DETAILS ");
            sql.append(" SET SAVE_FLAG = ?, UPDATED_TIME_STAMP = SYSTIMESTAMP ,APPROVED_BY=?,APPROVE_REJECT_FLAG=?,REASON=?,MSEDCL_INWARD_NUMBER=?,MSEDCL_INWARD_DATE=?"); // 16-18 till here  
            if(legalInvoiceBean.getSaveFlag().equals("Accepted")){
                sql.append(" , STATUS='With Technical Dept.' "); 
                if (legalInvoiceBean.getIsWithCourtCaseNo()!=null){if(legalInvoiceBean.getIsWithCourtCaseNo().equals("N")){
                    sql.append(" , CASE_REF_NO =?,  COURT_NAME =?  , CASE_DESCRIPTION =?,COURT_CASE_NO=?,PARTY_NAMES=?,FEE_TYPE=? "); 
                }
            }}
                sql.append(" WHERE APPL_ID = ? ");
         
                       logger.log(Level.INFO, "ErpLegalInvoiceStatusTxnHelper :: getQueryResults() :: SQL :: " + sql.toString());
            statement = conn.prepareStatement(sql.toString());
                 System.out.println(sql.toString());
            statement.setString(i++, legalInvoiceBean.getSaveFlag());
              
          statement.setString(i++, legalInvoiceBean.getApprovedBy());
          statement.setString(i++, legalInvoiceBean.getApproveRejectFlag());
          statement.setString(i++, legalInvoiceBean.getRejectReason());
          statement.setString(i++, legalInvoiceBean.getMsedclInwardNo());
          statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getMsedclInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
          if(legalInvoiceBean.getSaveFlag().equals("Accepted")) {
              if (legalInvoiceBean.getIsWithCourtCaseNo()!=null){
              if (legalInvoiceBean.getIsWithCourtCaseNo().equals("N")){
                    statement.setString(i++, legalInvoiceBean.getCaseRefNo());
                    statement.setString(i++, legalInvoiceBean.getCourtName());
                    statement.setString(i++, legalInvoiceBean.getCaseDescription());
                    statement.setString(i++, legalInvoiceBean.getCourtCaseNo());
                    statement.setString(i++, legalInvoiceBean.getPartyNames());
                    statement.setString(i++, legalInvoiceBean.getFeeType());
                }}}
          statement.setInt(i++, legalInvoiceBean.getApplId());
            count = statement.executeUpdate();
             }
             // if (count == 0)
            //legalInvoiceBean.setApplId("");
           if (count > 0)
           {
                 System.out.println("Calling Procedure PROC_FEETYPE_DETAILS_UPD BEFORE changes");
                 
               if(legalInvoiceBean.getSaveFlag().equals("Accepted")) { 
               proc_stmt = conn.prepareCall("{ call PROC_FEETYPE_DETAILS_UPD(?) }");
              proc_stmt.setInt(1, legalInvoiceBean.getApplId());
              
                proc_stmt.executeQuery();
                System.out.println(" Calling Procedure PROC_FEETYPE_DETAILS_UPD AFTER changes ");
                 
               }
            }
           conn.commit();
    }
              catch (Exception ex) {
                  ex.printStackTrace();
            logger.log(Level.ERROR, "ErpLegalInvoiceStatusTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }

    }

    @Override
    public void deleteObject(Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
