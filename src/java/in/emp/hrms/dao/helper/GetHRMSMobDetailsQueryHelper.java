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
public class GetHRMSMobDetailsQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetHRMSMobDetailsQueryHelper.class); 
    
    private HRMSUserBean hrmsUserBeanObj;
    private HRMSUserPrezData hrmsUserPrezDataObj;
    
    public GetHRMSMobDetailsQueryHelper(HRMSUserBean hrmsUserBeanObj) {
        this.hrmsUserBeanObj = hrmsUserBeanObj;
    }

    public GetHRMSMobDetailsQueryHelper(HRMSUserPrezData hrmsUserPrezDataObj) {
        this.hrmsUserPrezDataObj = hrmsUserPrezDataObj;
        this.hrmsUserBeanObj = this.hrmsUserPrezDataObj.getHrmsUserBeanObj();
    }
    
    public Object getDataObject(ResultSet results) throws Exception {
        HRMSUserBean hrmsUserBeanObj = new HRMSUserBean();
        String success = "";
        try {
            logger.log(Level.INFO, "GetHRMSMobDetailsQueryHelper :: getDataObject() :: method called ::    ");
            
            
            
            
                hrmsUserBeanObj.setMobileNo(results.getString("MOBILE_NO"));
                hrmsUserBeanObj.setEmpNumber(this.hrmsUserBeanObj.getEmpNumber());
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetHRMSMobDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return hrmsUserBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        //connection = ApplicationUtils.getConnection();
        try {
         
            sql.append(" SELECT NVL(TRIM(EMPMOBILE(GET_PERSON_ID( ? ))), EMPPERSONALMOBILE(GET_PERSON_ID( ? ))) MOBILE_NO  ");
            sql.append(" FROM DUAL ");

            logger.log(Level.INFO, "GetHRMSMobDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, hrmsUserBeanObj.getEmpNumber());
             statement.setString(2, hrmsUserBeanObj.getEmpNumber());

            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetHRMSMobDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }
        return rs;
    }
    
}