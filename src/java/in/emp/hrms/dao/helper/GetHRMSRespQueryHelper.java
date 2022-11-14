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
public class GetHRMSRespQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetHRMSRespQueryHelper.class);
    
    private HRMSRespBean hrmsRespBeanObj;
    private HRMSUserBean hrmsUserBeanObj;
    private HRMSUserPrezData hrmsUserPrezDataObj;
    
    public GetHRMSRespQueryHelper(HRMSRespBean hrmsRespBeanObj) {
        this.hrmsRespBeanObj = hrmsRespBeanObj;
    }

    public GetHRMSRespQueryHelper(HRMSUserPrezData hrmsUserPrezDataObj) {
        this.hrmsUserPrezDataObj = hrmsUserPrezDataObj;
        this.hrmsRespBeanObj = this.hrmsUserPrezDataObj.getHrmsRespBeanObj();
        this.hrmsUserBeanObj = this.hrmsUserPrezDataObj.getHrmsUserBeanObj();
    }
    
    public Object getDataObject(ResultSet results) throws Exception {
        HRMSRespBean hrmsRespBeanObj = new HRMSRespBean();
        try {
            logger.log(Level.INFO, "GetHRMSRespQueryHelper :: getDataObject() :: method called ::    ");
            
            hrmsRespBeanObj.setHrms_Resp_String(results.getString("RESPONSIBILITY_NAME"));
            
            StringTokenizer st = new StringTokenizer(results.getString("RESPONSIBILITY_NAME"), "|");
            while (st.hasMoreTokens()) {
                String Resp = st.nextToken().trim();
                if (Resp.equals("XXMIS PAYROLL SITE USER")) {
                    hrmsRespBeanObj.setHrms_Resp_PSU("Y");
                } else if (Resp.contains("HRMS Manager")) {
                    hrmsRespBeanObj.setHrms_Resp_HM("Y");
                }
            }
            hrmsRespBeanObj.setEmpNumber(this.hrmsRespBeanObj.getEmpNumber());
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetHRMSRespQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return hrmsRespBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetHRMSRespQueryHelper :: getQueryResults() :: method called ::    ");
            
            sql.append(" SELECT FU.USER_NAME EMP_NUMBER, ");
            sql.append(" LISTAGG (FR.RESPONSIBILITY_NAME, ' | ') WITHIN GROUP (ORDER BY FR.RESPONSIBILITY_NAME) RESPONSIBILITY_NAME ");
            sql.append(" FROM APPS.FND_USER_RESP_GROUPS_DIRECT FURG ");
            sql.append(" INNER JOIN APPS.FND_USER FU ");
            sql.append(" ON FURG.USER_ID = FU.USER_ID ");
            sql.append(" AND FU.USER_NAME = UPPER( ? ) "); // 1 here
            sql.append(" INNER JOIN APPS.FND_RESPONSIBILITY_TL FR ");
            sql.append(" ON FURG.RESPONSIBILITY_ID = FR.RESPONSIBILITY_ID ");
            sql.append(" WHERE FR.LANGUAGE = 'US' "); // USERENV('LANG') "); -- Does not work on Local PC, hence disabled.
            sql.append(" AND SYSDATE BETWEEN FURG.START_DATE AND NVL (FURG.END_DATE, SYSDATE + 1) ");
            sql.append(" GROUP BY FU.USER_NAME ");

            logger.log(Level.INFO, "GetHRMSRespQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, hrmsRespBeanObj.getEmpNumber());

            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetHRMSRespQueryHelper :: getQueryResults() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }
        return rs;
    }
    
}
