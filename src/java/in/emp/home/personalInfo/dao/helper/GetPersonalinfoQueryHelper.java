/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfo.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.home.personalInfo.bean.PersonalinfoBean;
import in.emp.home.personalInfo.bean.PersonalinfoaddressBean;
import in.emp.home.personalInfo.bean.PersonalinfocvcBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author rushi
 */
public class GetPersonalinfoQueryHelper implements QueryHelper {
    //private static Logger logger = new Logger(GetDCUDailyStatReportQueryHelper.class.getName());

    private static Logger logger = Logger.getLogger(GetPersonalinfoQueryHelper.class);
    private PersonalinfoBean pinfoBeanObj;

    /**
     * @public Constructor GetDCUSearchResultListQueryHelper()
     */
    public GetPersonalinfoQueryHelper(PersonalinfoBean pinfoBeanObj) {
        this.pinfoBeanObj = pinfoBeanObj;
        // readDateTo = ApplicationUtils.dateToString(dcuBeanObj.getReadDateTo(), "dd-MMM-yy");
    } // End of Constructor

    /*public API to get the Data object from the result set
     @param Result Set Object
     @ Return Object
     @Throws Exception*/
    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        PersonalinfoBean personalinfoBean = new PersonalinfoBean();
        PersonalinfoaddressBean personalinfoaddressBean = new PersonalinfoaddressBean();
        PersonalinfoaddressBean personalinfoaddressBean2 = new PersonalinfoaddressBean();
        PersonalinfocvcBean personalinfocvcBean = new PersonalinfocvcBean();
        LinkedList MobileNoList = new LinkedList();
        LinkedList CpfNomList = new LinkedList();
        LinkedList GradNomList = new LinkedList();
        LinkedList hmPhoneList = new LinkedList();
        try {
            logger.log(Level.INFO, "GetPersonalinfoQueryHelper ::: getDataObject() :: method called ::    ");

            personalinfoBean.setPersonId(results.getString("PERSON_ID"));
            personalinfoBean.setEmpNumber(results.getString("EMP_NUMBER"));
            personalinfoBean.setEmpName(results.getString("FULL_NAME"));
            personalinfoBean.setDateofJoining(results.getDate("DOJ"));
            personalinfoBean.setDateofBirth(results.getDate("DOB"));
            personalinfoBean.setLocation(results.getString("LOCATION"));
            personalinfoBean.setQualifications(results.getString("QUALIFICATION"));

            personalinfoBean.setMobileNo(results.getString("mobile_no1"));
            StringTokenizer mn = new StringTokenizer(results.getString("mobile_no"), "|");
            while (mn.hasMoreTokens()) {
                MobileNoList.add(mn.nextToken());
            }
            personalinfoBean.setMobileNoList(MobileNoList);
            // personalinfoBean.setAltNo(results.getString("EmpOfficePh"));
            personalinfoBean.setPhoneNo(results.getString("EmpOfficePh1"));
            StringTokenizer pn = new StringTokenizer(results.getString("EmpOfficePh"));
            personalinfoBean.setPhoneNo1(pn.nextToken("|"));
            personalinfoBean.setPhoneNo2(pn.nextToken("|"));
            personalinfoBean.setHomeNo(results.getString("HomePhone"));
            StringTokenizer hmPhone = new StringTokenizer(results.getString("HomePhone"), "|");
            if (hmPhone.hasMoreTokens()) {
                hmPhoneList.add(hmPhone.nextToken());
            }
            personalinfoBean.setHomePhoneList(hmPhoneList);

            personalinfoBean.setTempAddr(results.getString("TempAddress1"));
            StringTokenizer st = new StringTokenizer(results.getString("TempAddress"));
            personalinfoaddressBean.setAddressLine1(st.nextToken("|"));
            personalinfoaddressBean.setAddressLine2(st.nextToken("|"));
            personalinfoaddressBean.setAddressLine3(st.nextToken("|"));
            personalinfoaddressBean.setAddressLine4(st.nextToken("|"));
            personalinfoaddressBean.setCity(st.nextToken("|"));
            personalinfoaddressBean.setState(st.nextToken("|"));
            personalinfoaddressBean.setPincode(st.nextToken("|"));
            personalinfoaddressBean.setCountry(st.nextToken("|"));
            personalinfoBean.setTempAddress(personalinfoaddressBean);

            personalinfoBean.setPerAddr(results.getString("PrmntAddress1"));
            StringTokenizer stp = new StringTokenizer(results.getString("PrmntAddress"));
            personalinfoaddressBean2.setAddressLine1(stp.nextToken("|"));
            personalinfoaddressBean2.setAddressLine2(stp.nextToken("|"));
            personalinfoaddressBean2.setAddressLine3(stp.nextToken("|"));
            personalinfoaddressBean2.setAddressLine4(stp.nextToken("|"));
            personalinfoaddressBean2.setCity(stp.nextToken("|"));
            personalinfoaddressBean2.setState(stp.nextToken("|"));
            personalinfoaddressBean2.setPincode(stp.nextToken("|"));
            personalinfoaddressBean2.setCountry(stp.nextToken("|"));
            personalinfoBean.setPermAddress(personalinfoaddressBean2);

            personalinfoBean.setDesignation(results.getString("DESIGNATION"));
            personalinfoBean.setAgeYY(results.getInt("AGE_YY"));
            personalinfoBean.setZoneName(results.getString("ZNAME"));
            personalinfoBean.setCircleName(results.getString("CNAME"));
            personalinfoBean.setDivisionName(results.getString("DNAME"));
            personalinfoBean.setSubDivisionName(results.getString("SNAME"));
            personalinfoBean.setCadre(results.getString("CADRE"));
            personalinfoBean.setCCategory(results.getString("Sub_CASTECATEGORY"));
            //personalinfoBean.setCasteVerDet(results.getString("CASTVERDTLS"));

            StringTokenizer cvd = new StringTokenizer(results.getString("CASTVERDTLS"));
            personalinfocvcBean.setCVCPresent(cvd.nextToken("|"));
            personalinfocvcBean.setCVCCommName(cvd.nextToken("|"));
            personalinfocvcBean.setCVCStatus(cvd.nextToken("|"));
            personalinfocvcBean.setCVCNo(cvd.nextToken("|"));
            personalinfocvcBean.setCVCDate(cvd.nextToken("|"));
            personalinfoBean.setCValidDetails(personalinfocvcBean);

            personalinfoBean.setHomeTown(results.getString("HOMETOWN"));
            personalinfoBean.setOEmail(results.getString("EMAIL"));
            personalinfoBean.setPEmail(results.getString("EMAIL_P"));
            personalinfoBean.setGender(results.getString("GENDER"));
            personalinfoBean.setMarStatus(results.getString("MARITAL_STATUS"));
            personalinfoBean.setBloodGroup(results.getString("BLOOD_GROUP"));
            personalinfoBean.setEmpStatus(results.getString("EMP_STATUS"));
            personalinfoBean.setPaygroup(results.getString("PAY_GROUP"));
            personalinfoBean.setDateofRetirement(results.getDate("DOR"));
            //personalinfoBean.setPFNominee(results.getString("CPFNomnee"));

            StringTokenizer pfNom = new StringTokenizer(results.getString("CPFNomnee"), "|");
            while (pfNom.hasMoreTokens()) {
                CpfNomList.add(pfNom.nextToken());
            }
            personalinfoBean.setCpfNomList(CpfNomList);

            personalinfoBean.setGratNominee(results.getString("GratNomnee"));
            StringTokenizer gradNom = new StringTokenizer(results.getString("GratNomnee"), "|");
            while (gradNom.hasMoreTokens()) {
                GradNomList.add(gradNom.nextToken());
            }
            personalinfoBean.setGradNomList(GradNomList);

            personalinfoBean.setServiceYY(results.getInt("SR_YEARS"));
            personalinfoBean.setServiceMM(results.getInt("SR_MONTHS"));
            personalinfoBean.setPMobileNo(results.getString("PMOBILE_NO"));
            personalinfoBean.setPanNo(results.getString("PAN_NO"));
            personalinfoBean.setAadharNo(results.getString("AADHAR"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPersonalinfoQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return personalinfoBean;
    } // End of Method

    /**
     * Public API to get query results
     *
     * @param conn object of Connection
     * @return object of ResultSet
     * @throws Exception
     */
    @Override
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetPersonalinfoQueryHelper ::: getQueryResults() :: method called ::    ");

            sql.append(" SELECT EPI.PERSON_ID,EPI.EMP_NUMBER, EPI.FULL_NAME, EPI.DOJ, EPI.DOB, EPI.LOCATION,EPI.QUALIFICATION,  ");
            sql.append(" replace(EPI.HomePhone, '|', ', ') HomePhone , replace(EPI.mobile_no, '|', ', ') mobile_no1, EPI.mobile_no mobile_no, replace(EPI.EmpOfficePh, '|', ', ') EmpOfficePh1,EPI.EmpOfficePh ,EPI.TempAddress TempAddress, replace(EPI.TempAddress, '|', ', ') TempAddress1, replace(EPI.PrmntAddress, '|', ', ') PrmntAddress1,EPI.PrmntAddress PrmntAddress,   ");
            sql.append(" EPI.CPFNomnee,EPI.GratNomnee,");
            sql.append(" EPI.DESIGNATION, EPI.AGE_YY, EPI.ZNAME, EPI.CNAME, EPI.DNAME, EPI.SNAME, ");
            sql.append(" EPI.CADRE, replace(EPI.Sub_CASTECATEGORY, '|', ', ') Sub_CASTECATEGORY, EPI.CASTVERDTLS CASTVERDTLS ,EPI.HOMETOWN, EPI.EMAIL, EPI.EMAIL_P, ");

            sql.append(" EPI.GENDER, EPI.MARITAL_STATUS, EPI.BLOOD_GROUP, EPI.EMP_STATUS, ");
            sql.append(" EPI.SR_YEARS, EPI.SR_MONTHS, EPI.PAY_GROUP,EPI.DOR, EPI.PMOBILE_NO, ");
            sql.append(" EPI.PAN_NO, EPI.AADHAR ");
            sql.append(" FROM XXMIS_EMP_PERSONAL_INFO EPI ");
            sql.append(" WHERE EPI.EMP_NUMBER = ? ");

            logger.log(Level.INFO, "GetPersonalinfoQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, pinfoBeanObj.getEmpNumber());
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPersonalinfoQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
