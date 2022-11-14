/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.security.dao.helper;

import in.emp.dao.TxnHelper;
import in.emp.util.ApplicationUtils;
import in.emp.util.TxnUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Ithelpdesk
 */
public class CpfPortalSessionTxnHelper implements TxnHelper{
    
       private static Logger logger = Logger.getLogger(CpfPortalSessionTxnHelper.class);    
    LinkedList list=null; 
   String cpfNo="";
   long applId;
   
   public CpfPortalSessionTxnHelper(String cpfNo) {
        this.cpfNo = cpfNo;
    }//constructor ends

    
    public Object createObject(Connection conn) throws Exception {
         int count = 0;
        try {
            logger.log(Level.INFO, "CpfPortalSessionTxnHelper ::: createObject() :: method called ::");

            applId=ApplicationUtils.getNextSequenceId(conn, "EMP_CPF_PORTAL_SESSION_SEQ");
            

            count += saveSessionDtl(conn);
            if (count > 0) {               
                TxnUtility.commitTransaction(conn);                                                              
            }
            else{
                TxnUtility.rollbackTransaction(conn); 
            }
                         
            //prezData.setLoaninfoBean(nomPrezData);
             
        } catch (Exception ex) {
            TxnUtility.rollbackTransaction(conn);
            logger.log(Level.ERROR, "CpfPortalSessionTxnHelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        }
        return "";
    }

    public int saveSessionDtl(Connection conn)throws Exception{
        int cnt=0;
                
          PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
      
        try {
            logger.log(Level.INFO, "CpfPortalSessionTxnHelper ::: saveSessionDtl() :: method called ::");

            sql.append(" INSERT INTO emp_cpf_portal_session ");
            sql.append(" (ID  ,CPF_NO     ,cre_date )");                          
            sql.append(" VALUES ");            
            sql.append(" ( ?,?,SYSTIMESTAMP )");             

            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, applId);            
            statement.setString(2, cpfNo);            
            

            logger.log(Level.INFO, "CpfPortalSessionTxnHelper ::: saveSessionDtl() :: SQL :: " + sql.toString());

            cnt = statement.executeUpdate();

            return cnt;
        } catch (Exception ex) {
            logger.log(Level.ERROR, "CpfPortalSessionTxnHelper ::: saveSessionDtl() :: Exception :: " + ex);
            throw ex;
        }
    }

   
   public void updateObject(Connection conn) throws Exception {
       
    }

     
    public void deleteObject(Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
