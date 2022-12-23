/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;
import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.legal.bean.HOSectionMatrixBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author MSEDCL
 */
public class GetHOLegalSmsEmailQueryHelper implements QueryHelper {
    

    private static Logger logger = Logger.getLogger(GetHOLegalSmsEmailQueryHelper.class);
    private HOSectionMatrixBean hoLegalBean;
    //private VendorPrezData vendorPrezDataObj;

    public GetHOLegalSmsEmailQueryHelper(HOSectionMatrixBean hoLegalBean) {
        this.hoLegalBean = hoLegalBean;
    }

   

    public Object getDataObject(ResultSet results) throws Exception {
        HOSectionMatrixBean hoLegalBeanObj = new HOSectionMatrixBean();
        try {
            logger.log(Level.INFO, "GetHOLegalSmsEmailQueryHelper ::: getDataObject() :: method called ::");

            hoLegalBeanObj.setSectionName(results.getString("SECTION_NAME"));
            hoLegalBeanObj.setSectionDesc(results.getString("SEC_DESCRIPTION"));
            hoLegalBeanObj.setSectionCode(results.getString("SECTION_CODE"));
            hoLegalBeanObj.setEmpNumber(results.getString("EMPLOYEE_NUMBER"));
            hoLegalBeanObj.setEmpName(results.getString("EMPLOYEE_NAME"));
            hoLegalBeanObj.setEmpDesignation(results.getString("EMP_DESIGNATION"));
            hoLegalBeanObj.setEmpEmail(results.getString("EMP_EMAIL"));
            hoLegalBeanObj.setEmpMobile(results.getString("EMP_MOBILE"));
            hoLegalBeanObj.setGender(results.getString("GENDER"));
            hoLegalBeanObj.setAccessLevel(results.getInt("ACCESS_LEVEL"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetHOLegalSmsEmailQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return hoLegalBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

            sql.append(" select SECTION_NAME,SECTION_CODE,EMPLOYEE_NUMBER,EMPLOYEE_NAME,SEC_DESCRIPTION,EMP_DESIGNATION,EMP_EMAIL,EMP_MOBILE,GENDER,ACCESS_LEVEL  ");
            sql.append("  from XXMIS_ERP_LEGAL_HO_MATRIX where SECTION_CODE=? and ISACTIVE='Y' and ACCESS_LEVEL=4   ");
             sql.append("  and  SECTION_NAME='Testing'  "); // comment this line for cloud


            logger.log(Level.INFO, "GetHOLegalSmsEmailQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
            System.out.println(sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, hoLegalBean.getSectionCode());

            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetHOLegalSmsEmailQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
