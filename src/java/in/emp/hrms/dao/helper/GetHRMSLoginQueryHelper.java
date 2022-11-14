/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.hrms.dao.helper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.hrms.bean.HRMSUserPrezData;
import in.emp.hrms.bean.HRMSUserBean;
import in.emp.util.ApplicationUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class GetHRMSLoginQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetHRMSLoginQueryHelper.class); 
    
    private HRMSUserBean hrmsUserBeanObj;
    private HRMSUserPrezData hrmsUserPrezDataObj;
    
    public GetHRMSLoginQueryHelper(HRMSUserBean hrmsUserBeanObj) {
        this.hrmsUserBeanObj = hrmsUserBeanObj;
    }

    public GetHRMSLoginQueryHelper(HRMSUserPrezData hrmsUserPrezDataObj) {
        this.hrmsUserPrezDataObj = hrmsUserPrezDataObj;
        this.hrmsUserBeanObj = this.hrmsUserPrezDataObj.getHrmsUserBeanObj();
    }
    
    public Object getDataObject(ResultSet results) throws Exception {
        HRMSUserBean hrmsUserBeanObj = new HRMSUserBean();
        String success = "";
        try {
            logger.log(Level.INFO, "GetHRMSLoginQueryHelper :: getDataObject() :: method called ::    ");
            
            success = results.getString("VALLOGIN");
            
            
                hrmsUserBeanObj.setValLogin(success);
                hrmsUserBeanObj.setEmpNumber(this.hrmsUserBeanObj.getEmpNumber());
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetHRMSLoginQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return hrmsUserBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
       // connection = ApplicationUtils.getConnection();
        try {
            //sql.append(" SELECT ERPPORTAL.VALIDATE_LOGIN( ?, ? ) VALLOGIN ");//dev
            sql.append(" SELECT VALIDATE_LOGIN( ?, ? ) VALLOGIN ");//prod
            sql.append(" FROM DUAL ");

            logger.log(Level.INFO, "GetHRMSLoginQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, hrmsUserBeanObj.getEmpNumber());
            statement.setString(2, hrmsUserBeanObj.getPass());

            rs = statement.executeQuery();
            return rs;
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetHRMSLoginQueryHelper :: getQueryResults() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }
    }
    
}