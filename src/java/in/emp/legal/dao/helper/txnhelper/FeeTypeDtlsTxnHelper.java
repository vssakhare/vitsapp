/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.txnhelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.TxnHelper;
import in.emp.legal.bean.FeeTypeDtlsBean;
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
public class FeeTypeDtlsTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(FeeTypeDtlsTxnHelper.class);
    private FeeTypeDtlsBean feeTypeDtlsBean;

   // List<LegalInvoiceInputBean> listErpToVitsFileFormat;

    //public FeeTypeDtlsTxnHelper(List listErpToVitsFileFormat) {
   //     this.listErpToVitsFileFormat = listErpToVitsFileFormat;
   // }//constructor ends

    public FeeTypeDtlsTxnHelper(FeeTypeDtlsBean feeTypeDtlsBean) {
        this.feeTypeDtlsBean = feeTypeDtlsBean;
    }

    public FeeTypeDtlsTxnHelper() {
    }

    @Override
    public Object createObject(Connection conn) throws Exception {
        int count;
        PreparedStatement statement = null;
        //PreparedStatement statement1 = null;
        CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "FeeTypeDtlsTxnHelper ::: createObject() :: method called ::");
            System.out.println("ErpLegalInvoiceStatusTxnHelper ::: createObject() :: method called ::");
            feeTypeDtlsBean.setFeeTypeDtlsId(ApplicationUtils.getNextSequenceId(conn, "SEQ_XXMIS_ERP_LEGAL_INVOICE_FEE_TYPE_DTLS").intValue());
            sql.append("INSERT INTO XXMIS_ERP_LEGAL_INVOICE_FEE_TYPE_DTLS ");
            sql.append(" (fee_type_dtls_id  , fee_type ,  amount   , APPL_ID, remark, ");
            sql.append(" CREATED_TIME_STAMP ,  UPDATED_TIME_STAMP   ) ");
            sql.append(" VALUES (?,?,?,?,?,SYSTIMESTAMP,SYSTIMESTAMP )");
            

            statement = conn.prepareStatement(sql.toString());
            statement.setInt(1, feeTypeDtlsBean.getFeeTypeDtlsId());
            statement.setString(2, feeTypeDtlsBean.getFeeType());
            statement.setInt(3, feeTypeDtlsBean.getAmount());
            statement.setInt(4, feeTypeDtlsBean.getApplId());
            statement.setString(5, feeTypeDtlsBean.getRemark());
            
            logger.log(Level.INFO, "FeeTypeDtlsTxnHelper ::: createObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            if (count == 0) {
                feeTypeDtlsBean.setFeeTypeDtlsId(0);
            }

            if (count > 0) {

//                vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
              //  String appl_id = legalInvoiceBean.getApplId() + "";
                //int appl_id1 = Integer.parseInt(appl_id);
//               proc_stmt = conn.prepareCall("{ call PROC_SUMMARY_UPD_PS(?) }");
//               proc_stmt.setString(1, legalInvoiceBean.getApplId());
//                proc_stmt.executeQuery();
                conn.commit();

            }
            System.out.println("count::" + count + "   app_id::" + feeTypeDtlsBean.getApplId());

        } catch (Exception e) {
            e.printStackTrace();
         feeTypeDtlsBean.setFeeTypeDtlsId(0);
        }

        return feeTypeDtlsBean;

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
            logger.log(Level.INFO, "FeeTypeDtlsTxnHelper ::: updateObject() :: method called ::");
            
           //fee_type_dtls_id  , fee_type ,  amount   , APPL_ID, 
             sql.append(" UPDATE XXMIS_ERP_LEGAL_INVOICE_FEE_TYPE_DTLS ");
            sql.append(" SET FEE_TYPE = ?, AMOUNT = ?, remark= ? ,UPDATED_TIME_STAMP = SYSTIMESTAMP "); // 16-18 till here      
            sql.append(" WHERE APPL_ID = ?  and fee_type_dtls_id= ?  ");
         
                       logger.log(Level.INFO, "FeeTypeDtlsTxnHelper :: getQueryResults() :: SQL :: " + sql.toString());
            statement = conn.prepareStatement(sql.toString());
          
            statement.setString(1, feeTypeDtlsBean.getFeeType());
              statement.setInt(2, feeTypeDtlsBean.getAmount());
              statement.setString(3, feeTypeDtlsBean.getRemark());
              statement.setInt(4, feeTypeDtlsBean.getApplId());
                statement.setInt(5, feeTypeDtlsBean.getFeeTypeDtlsId());
          
            count = statement.executeUpdate();
           

           conn.commit();
    }
              catch (Exception ex) {
                  ex.printStackTrace();
            logger.log(Level.ERROR, "FeeTypeDtlsTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }

    }

    @Override
    public void deleteObject(Connection conn) throws Exception {
      int count = 0;
        int[] counter = null;
        int length = 0;
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "FeeTypeDtlsTxnHelper ::: deleteObject() :: method called ::");

            sql.append(" DELETE FROM XXMIS_ERP_LEGAL_INVOICE_FEE_TYPE_DTLS ");
            sql.append(" WHERE fee_type_dtls_id = ? "); // 1 here
          
            logger.log(Level.INFO, "FeeTypeDtlsTxnHelper :: deleteObject() :: SQL :: " + sql.toString());
            
            statement = conn.prepareStatement(sql.toString());
            statement.setInt(1, feeTypeDtlsBean.getFeeTypeDtlsId());
          
            
            count = statement.executeUpdate();
            conn.commit();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "FeeTypeDtlsTxnHelper ::: deleteObject() :: Exception :: " + ex);
            conn.rollback();
            throw ex;
        }
    }

    @Override
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
