/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.hrms.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.hrms.bean.HRMSUserPrezData;
import in.emp.hrms.bean.HRMSRespBean;
import in.emp.hrms.bean.HRMSUserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class GetHRMSUserDetailsQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetHRMSUserDetailsQueryHelper.class);
    
    private HRMSUserBean hrmsUserBeanObj;
    private HRMSUserPrezData hrmsUserPrezDataObj;
    
    public GetHRMSUserDetailsQueryHelper(HRMSUserBean hrmsUserBeanObj) { 
        this.hrmsUserBeanObj = hrmsUserBeanObj;
    }

    public GetHRMSUserDetailsQueryHelper(HRMSUserPrezData hrmsUserPrezDataObj) {
        this.hrmsUserPrezDataObj = hrmsUserPrezDataObj;
        this.hrmsUserBeanObj = this.hrmsUserPrezDataObj.getHrmsUserBeanObj();
    }
    
    public Object getDataObject(ResultSet results) throws Exception {
        HRMSUserBean hrmsUserBeanObj = new HRMSUserBean();
        HRMSUserPrezData hrmsUserPrezDataObj = new HRMSUserPrezData();
        try {
            logger.log(Level.INFO, "GetHRMSUserDetailsQueryHelper :: getDataObject() :: method called ::    ");
            
            hrmsUserBeanObj.setEmpNumber(results.getString("VENDOR_CODE"));
            hrmsUserBeanObj.setEmpName(results.getString("VENDOR_NAME"));
            hrmsUserBeanObj.setIsLegal(results.getString("IS_LEGAL"));
            hrmsUserPrezDataObj.setHrmsUserBeanObj(hrmsUserBeanObj);
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetHRMSUserDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return hrmsUserPrezDataObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetHRMSUserDetailsQueryHelper :: getQueryResults() :: method called ::    ");
            
            sql.append(" select EVM.VENDOR_CODE, EVM.VENDOR_NAME, EVM.IS_LEGAL ");
            sql.append(" FROM ERP_VENDOR_MASTER EVM ");
            sql.append(" WHERE EVM.VENDOR_CODE =  ? ");

            logger.log(Level.INFO, "GetHRMSUserDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, hrmsUserBeanObj.getEmpNumber());

            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetHRMSUserDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }
        return rs;
    }
    
}