/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.user.dao.helper;

import in.emp.dao.TxnHelper;
import in.emp.user.bean.UserParameterBean;
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
 * @author Administrator
 */
public class UserParameterTxnHelper implements TxnHelper{

    private static Logger logger = Logger.getLogger(UserTxnHelper.class);
    private UserParameterBean userParameterBeanObj;

    public UserParameterTxnHelper( UserParameterBean userParameterBeanObj)
    {
            this.userParameterBeanObj = userParameterBeanObj;
    }

    public Object createObject(Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateObject(Connection conn) throws Exception
    {
        PreparedStatement statement = null;
        StringBuffer sql = null;
        LinkedList userParameterList = null;
        int fldCount = 1;
        int count = 0;
        Iterator<UserParameterBean> itr = null;
        UserParameterBean uBean = null;
       
        try
        {

                logger.log(Level.INFO,"UserParameterTxnHelper :: updateObject() :: method called");
                userParameterList = userParameterBeanObj.getUserParameterList();
                if(userParameterList != null){
                itr = userParameterList.iterator();
                while(itr.hasNext())
                {
                    uBean = itr.next();
                    sql = new StringBuffer();
                    sql.append(" UPDATE SYSTEM_PARAMETER");
                    sql.append(" SET ");
                    sql.append(" PARAMETER_VALUE = ? ");
                    sql.append(" WHERE ");
                    sql.append(" SORT_ORDER = ? ");

                    statement = null;
                    statement = conn.prepareStatement(sql.toString());
                    fldCount = 1;

                    if(uBean.getParamValue() !=null)
                    {
                        statement.setString(fldCount++, uBean.getParamValue());
                    }
                    else
                    {
                        statement.setNull(fldCount++, Types.INTEGER);
                    }
                    if(uBean.getParamId() > 0)
                    {
                        statement.setLong(fldCount++, uBean.getParamId());
                    }
                    else
                    {
                        statement.setLong(fldCount++, Types.INTEGER);
                    }
                    logger.log(Level.INFO,"UserParameterTxnHelper ::: updateObject() :: SQL :: "+sql.toString());
                    count = statement.executeUpdate();
                    
                }
                conn.commit();
            }

        }
        catch (Exception ex)
        {
                //ex.printStackTrace();
                conn.rollback();
                logger.log(Level.ERROR,"UserParameterTxnHelper :: updateObject() :: Exception :: " + ex);
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
