/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.HOBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class GetHOSmsDetailsQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetHOSmsDetailsQueryHelper.class);
    private HOBean hoBean;
    private VendorPrezData vendorPrezDataObj;

    public GetHOSmsDetailsQueryHelper(HOBean hoBean) {
        this.hoBean = hoBean;
    }

    public GetHOSmsDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;

    }

    public Object getDataObject(ResultSet results) throws Exception {
        HOBean hoBeanObj = new HOBean();
        try {
            logger.log(Level.INFO, "GetHOSmsDetailsQueryHelper ::: getDataObject() :: method called ::");

            hoBeanObj.setEMP_CPF(results.getString("TECH_EMPLOYEE"));
            hoBeanObj.setESCALATION_EMP_CPF(results.getString("TECHHEAD_EMPLOYEE"));
            hoBeanObj.setEMP_DESIGNATION(results.getString("tech_designation"));
            hoBeanObj.setESCALATION_EMP_DESIGNATION(results.getString("techhead_designation"));
            hoBeanObj.setPLANT(results.getString("PLANT"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetHOSmsDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return hoBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

            sql.append(" select TECH_EMPLOYEE,TECHHEAD_EMPLOYEE,tech_designation,techhead_designation,PLANT ");
            sql.append("  FROM emp_escalation_matrix where purchasing_group =? ");

            logger.log(Level.INFO, "GetHOSmsDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, hoBean.getPURCHASING_GROUP());

            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetHOSmsDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
