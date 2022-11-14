/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfoUpdation.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationBean;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationaddressBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import in.emp.common.ApplicationConstants;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author prajakta
 */
public class GetPersonalinfoupdationQueryHelper implements QueryHelper {
    //private static Logger logger = new Logger(GetDCUDailyStatReportQueryHelper.class.getName());

    private static Logger logger = Logger.getLogger(GetPersonalinfoupdationQueryHelper.class);
    private PersonalinfoupdationBean pinfoupdationBeanObj;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
    String readDateTo;

    /**
     * @public Constructor GetDCUSearchResultListQueryHelper()
     */
    public GetPersonalinfoupdationQueryHelper(PersonalinfoupdationBean pinfoupdationBeanObj) {
        this.pinfoupdationBeanObj = pinfoupdationBeanObj;
        // readDateTo = ApplicationUtils.dateToString(dcuBeanObj.getReadDateTo(), "dd-MMM-yy");
    } // End of Constructor

    /*public API to get the Data object from the result set
     @param Result Set Object
     @ Return Object
     @Throws Exception*/
    public Object getDataObject(ResultSet results) throws Exception {
        PersonalinfoupdationBean personalinfoupdationBean = new PersonalinfoupdationBean();
        PersonalinfoupdationaddressBean personalinfoupdationaddressBean = new PersonalinfoupdationaddressBean();  
        LinkedList MobileNoList=new LinkedList();     
        try {
           
            personalinfoupdationBean.setPersonId(results.getString("PERSON_ID"));
            personalinfoupdationBean.setEmpNumber(results.getString("EMP_NUMBER"));
            personalinfoupdationBean.setEmpName(results.getString("FULL_NAME"));

            personalinfoupdationBean.setMobileNo(results.getString("mobile_no1"));//---------
                StringTokenizer mn= new StringTokenizer(results.getString("mobile_no"),"|");            
                while (mn.hasMoreTokens()) {
                MobileNoList.add(mn.nextToken());
                }
               personalinfoupdationBean.setMobileNoList(MobileNoList);                    
           // personalinfoupdationBean.setTempAddr(results.getString("TempAddress1"));//---------
            StringTokenizer st= new StringTokenizer(results.getString("TempAddress"));            
                personalinfoupdationaddressBean.setAddressLine1(st.nextToken("|"));
                personalinfoupdationaddressBean.setAddressLine2(st.nextToken("|"));
                personalinfoupdationaddressBean.setAddressLine3(st.nextToken("|"));
                personalinfoupdationaddressBean.setAddressLine4(st.nextToken("|"));
                personalinfoupdationaddressBean.setCity(st.nextToken("|"));                
                personalinfoupdationaddressBean.setState(st.nextToken("|"));
                personalinfoupdationaddressBean.setPincode(st.nextToken("|"));                
                personalinfoupdationaddressBean.setCountry(st.nextToken("|"));
            personalinfoupdationBean.setTempAddress(personalinfoupdationaddressBean);         
            personalinfoupdationBean.setPEmail(results.getString("EMAIL_P"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPersonalinfoupdationQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return personalinfoupdationBean;
    } // End of Method

    /**
     * Public API to get query results
     *
     * @param conn object of Connection
     * @return object of ResultSet
     * @throws Exception
     */
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        String sumStatements = "";
        String maxStatements = "";
        String decodeStatements = "";
        try {

            sql.append(" SELECT EPI.PERSON_ID,EPI.EMP_NUMBER, EPI.FULL_NAME, EPI.EMAIL_P,");
            sql.append(" replace(EPI.mobile_no, '|', ', ') mobile_no1, EPI.mobile_no mobile_no,EPI.TempAddress TempAddress, replace(EPI.TempAddress, '|', ', ') TempAddress1  ");
            sql.append(" FROM XXMIS_EMP_PERSONAL_INFO EPI ");
            sql.append(" WHERE EPI.EMP_NUMBER = ? ");

            logger.log(Level.INFO, "GetPersonalinfoupdationQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, pinfoupdationBeanObj.getEmpNumber());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPersonalinfoupdationQueryHelper :: getQueryResults() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }
        return rs;
    }
}
