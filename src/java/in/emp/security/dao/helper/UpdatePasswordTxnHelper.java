/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.security.dao.helper;

import in.emp.dao.TxnHelper;
import in.emp.user.bean.UserBean;
import in.emp.user.bean.UserParameterBean;
import in.emp.util.ApplicationUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author rgchoudhari
 */
public class UpdatePasswordTxnHelper implements TxnHelper{

       
    private static Logger logger = Logger.getLogger(UpdatePasswordTxnHelper.class);
    public UserBean userBeanObj;
    
    public UpdatePasswordTxnHelper( UserBean userBeanObj)
    {
            this.userBeanObj = userBeanObj;
    }
    
    
     public Object createObject(Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuffer sql = null;
        LinkedList userParameterList = null;
        Iterator<UserParameterBean> itr = null;
        UserParameterBean uBean = null;    
        
        int fldCount = 1;
        int count = 0;
        
        try
        {

                logger.log(Level.INFO,"UpdatePasswordTxnHelper :: updateObject() :: method called");

                    sql = new StringBuffer();
                    sql.append(" UPDATE SEC_USER");
                    sql.append(" SET ");
                    sql.append(" PASSWORD = ?, ");
                    sql.append(" UPDATED_BY = ? ,");
                    sql.append(" UPDATED_DT = SYSDATE ");
                    sql.append(" WHERE ");
                    sql.append(" LOGIN_ID = ? and ");
                    sql.append(" PASSWORD = ?  ");

                    statement = null;
                    statement = conn.prepareStatement(sql.toString());
                    fldCount = 1;

                    if(!ApplicationUtils.isBlank(userBeanObj.getNewPass()))
                    {
                        statement.setString(fldCount++, userBeanObj.getNewPass());
                    }
                    else
                    {
                        statement.setNull(fldCount++, Types.VARCHAR);
                    }
                    
                    
                    if(!ApplicationUtils.isBlank(userBeanObj.getUserId()))
                    {
                        statement.setLong(fldCount++, userBeanObj.getUserId());
                    }
                    else
                    {
                        statement.setNull(fldCount++, Types.INTEGER);
                    }
                    
                    System.out.println("userBeanObj.getUserId()::"+userBeanObj.getUserLoginId());

                    if(!ApplicationUtils.isBlank(userBeanObj.getUserLoginId()))
                    {
                        statement.setString(fldCount++, userBeanObj.getUserLoginId());
                    }
                    else
                    {
                        statement.setNull(fldCount++, Types.VARCHAR);
                    }
                    
                    if(!ApplicationUtils.isBlank(userBeanObj.getOldPass()))
                    {
                        statement.setString(fldCount++, userBeanObj.getOldPass());
                    }
                    else
                    {
                        statement.setNull(fldCount++, Types.VARCHAR);
                    }
                    
                    
                    logger.log(Level.INFO,"UpdatePasswordTxnHelper ::: updateObject() :: SQL :: "+sql.toString());
                    
                    count = statement.executeUpdate();
                    this.userBeanObj.setChangPassCount(count);
                    if(count>0)
                        conn.commit();
            

        }
        catch (Exception ex)
        {
                //ex.printStackTrace();
                conn.rollback();
                logger.log(Level.ERROR,"UpdatePasswordTxnHelper :: updateObject() :: Exception :: " + ex);
                throw ex;
        }
        
    }

    public void deleteObject(Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
