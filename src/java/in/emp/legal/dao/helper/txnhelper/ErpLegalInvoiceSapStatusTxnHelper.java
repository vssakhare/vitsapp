/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.txnhelper;

import in.emp.dao.TxnHelper;
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
 * @author MSEDCL
 */
public class ErpLegalInvoiceSapStatusTxnHelper implements TxnHelper {
 
    private static Logger logger = Logger.getLogger(ErpLegalInvoiceSapStatusTxnHelper.class);
    private LegalInvoiceInputBean legalInvoiceBean;

    List<LegalInvoiceInputBean> listErpToVitsFileFormat;

    public ErpLegalInvoiceSapStatusTxnHelper(List listErpToVitsFileFormat) {
        this.listErpToVitsFileFormat = listErpToVitsFileFormat;
    }//constructor ends

    public ErpLegalInvoiceSapStatusTxnHelper(LegalInvoiceInputBean legalInvoiceBean) {
        this.legalInvoiceBean = legalInvoiceBean;
    }

    public ErpLegalInvoiceSapStatusTxnHelper() {
    }
    
    
    @Override
    public Object createObject(Connection conn) throws Exception {
        int count;
        return null;
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
            logger.log(Level.INFO, "ErpLegalInvoiceStatusTxnHelper ::: updateSapStatusObject() :: method called ::");
            
             if((legalInvoiceBean.getSaveFlag().equals("Accepted")))
             {
             sql.append(" UPDATE XXMIS_ERP_LEGAL_INVOICE_DETAILS ");
            sql.append(" SET STATUS = ?,  UPDATED_TIME_STAMP = SYSTIMESTAMP "); // 16-18 till here      
            sql.append(" WHERE APPL_ID = ? ");
         
                       logger.log(Level.INFO, "ErpLegalInvoiceStatusTxnHelper :: getQueryResults() :: SQL :: " + sql.toString());
            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, legalInvoiceBean.getStatus());
            statement.setInt(2, legalInvoiceBean.getApplId());
          
            count = statement.executeUpdate();
             }
       
             // if (count == 0)
            //legalInvoiceBean.setApplId("");
//            if (count > 0)
//            {
//                  System.out.println("Calling Procedure PROC_SUMMARY_UPD BEFORE changes");
//                 String  appl_id=vendorInputBeanObj.getApplId();
//                 int appl_id1=Integer.parseInt(appl_id);  
//               proc_stmt = conn.prepareCall("{ call PROC_SUMMARY_UPD_PS(?) }");
//               proc_stmt.setString(1, vendorInputBeanObj.getApplId());
//                proc_stmt.executeQuery();
//                  System.out.println(" Calling Procedure PROC_SUMMARY_UPD AFTER changes ");
//                 conn.commit();
//            }
           conn.commit();
    }
              catch (Exception ex) {
                  ex.printStackTrace();
            logger.log(Level.ERROR, "ErpLegalInvoiceStatusTxnHelper ::: updateSapStatusObject() :: Exception :: " + ex);
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
