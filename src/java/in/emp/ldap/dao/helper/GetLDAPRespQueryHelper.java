/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.ldap.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.ldap.bean.LDAPPrezData;
import in.emp.ldap.bean.LDAPRespBean;
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
public class GetLDAPRespQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetLDAPRespQueryHelper.class);
    private LDAPRespBean LDAPRespBeanObj;
    private LDAPPrezData LDAPPrezDataObj;

    public GetLDAPRespQueryHelper(LDAPRespBean LDAPRespBeanObj) {
        this.LDAPRespBeanObj = LDAPRespBeanObj;
    }

    public GetLDAPRespQueryHelper(LDAPPrezData LDAPPrezDataObj) {
        this.LDAPPrezDataObj = LDAPPrezDataObj;
        this.LDAPRespBeanObj = this.LDAPPrezDataObj.getLDAPRespBean();
    }

    public Object getDataObject(ResultSet results) throws Exception {
        LDAPRespBean LDAPRespBeanObj = new LDAPRespBean();
        try {
            StringTokenizer st = new StringTokenizer(results.getString("RESPONSIBILITY_NAME"), "|");
            while (st.hasMoreTokens()) {
                if (st.nextToken().trim().equals("XXMIS PAYROLL SITE USER")) {
                    LDAPRespBeanObj.setHRMS_Resp_PSU("Y");
                }
//                if (st.nextToken().equals("XXMIS PAYROLL SITE USER")) {
//                    LDAPRespBeanObj.setHRMS_Resp_DHU("Y");
//                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLDAPRespQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return LDAPRespBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            sql.append(" SELECT HRL.RESPONSIBILITY_NAME ");
            sql.append(" FROM XXMIS_EMP_HRMS_RESP_LIST HRL ");
            sql.append(" WHERE HRL.EMP_NUMBER = ? ");

            logger.log(Level.INFO, "GetLDAPRespQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, LDAPRespBeanObj.getEmpNumber());

            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLDAPRespQueryHelper :: getQueryResults() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }
        return rs;
    }
}
