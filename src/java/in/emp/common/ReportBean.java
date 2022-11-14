package in.emp.common;

import java.util.LinkedList;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ReportBean implements java.io.Serializable {

    // Report Header Details
    private long reportId;
    private String reportCode;
    private String reportDesc;
    private String reportTitle;
    private String reportSubTitle;
    private String reportTableName;
    private String reportWhereCondition;
    private String reportSortOrder;
    private LinkedList circleList;
    private String accessLocation;
    private LinkedList tamperList;
    private long eventId;
    private long flag;
    private String locationQuery;
    private String locationQuery2;
    private Date readDateFrom;
    private Date readDateTo;
    private String homeFlag;
    private String reportSql;
    private LinkedList meterDataList;
    private LinkedList reportColumnList;
    private String zoneCode;
    private String circleCode;
    private String divisionCode;
    private String subDivCode;
    private String buCode;
    private java.sql.Date fromDate;
    private java.sql.Date toDate;
    private String fromMonth;
    private String toMonth;
    private String selectedInstallPointType;
    private LinkedList reportList;
    private LinkedList defaultLocList;
    private LinkedList installPointTypeList;
    private int minValue;
    private int maxValue;
    private long userId;
    private String sortColTableName;
    private String sortColumnName;
    private String sortOrder;
    private String lastRowValue;
    private String lastValueDataType;
    private String project;
    private String constype;
    private String townid;
    private LinkedList projectList;
    long projectId;
    private String installpoint;
    private String constypeltht;
    private LinkedList loadProfileReadingDateList;
    private String reportPeriodCriteria;
    private String reportFlag;
    private String cname;
    private String eventdesc;
    private String eventfromdate;
    private String eventtodate;
    private String fromflag;
    private LinkedList deviceTypeList;
    private LinkedList DLMSdeviceTypeList;
    // Biometric - Section Details
    private String SECTION_NAME;
    private String SECTION_HEAD_NAME;
    private String SECTION_HEAD_EMP_NO;
    private String SECTION_HEAD_DESIG;

    public String getFromflag() {
        return fromflag;
    }

    public void setFromflag(String fromflag) {
        this.fromflag = fromflag;
    }

    public String getEventfromdate() {
        return eventfromdate;
    }

    public void setEventfromdate(String eventfromdate) {
        this.eventfromdate = eventfromdate;
    }

    public String getEventtodate() {
        return eventtodate;
    }

    public void setEventtodate(String eventtodate) {
        this.eventtodate = eventtodate;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEventdesc() {
        return eventdesc;
    }

    public void setEventdesc(String eventdesc) {
        this.eventdesc = eventdesc;
    }

    public String getReportFlag() {
        return reportFlag;
    }

    public void setReportFlag(String reportFlag) {
        this.reportFlag = reportFlag;
    }

    public String getConstypeltht() {
        return constypeltht;
    }

    public void setConstypeltht(String constypeltht) {
        this.constypeltht = constypeltht;
    }

    public String getInstallpoint() {
        return installpoint;
    }

    public void setInstallpoint(String installpoint) {
        this.installpoint = installpoint;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public LinkedList getProjectList() {
        return projectList;
    }

    public void setProjectList(LinkedList projectList) {
        this.projectList = projectList;
    }

    public LinkedList getTownList() {
        return townList;
    }

    public void setTownList(LinkedList townList) {
        this.townList = townList;
    }
    private LinkedList townList;

    public String getTownid() {
        return townid;
    }

    public void setTownid(String townid) {
        this.townid = townid;
    }

    public String getConstype() {
        return constype;
    }

    public void setConstype(String constype) {
        this.constype = constype;
    }

    public String getLocationQuery2() {
        return locationQuery2;
    }

    public void setLocationQuery2(String locationQuery2) {
        this.locationQuery2 = locationQuery2;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    /**
     * @return the reportList
     */
    public LinkedList getReportList() {
        return reportList;
    }

    /**
     * @param reportList the reportList to set
     */
    public void setReportList(LinkedList reportList) {
        this.reportList = reportList;
    }

    /**
     * @return the zoneCode
     */
    public String getZoneCode() {
        return zoneCode;
    }

    /**
     * @param zoneCode the zoneCode to set
     */
    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    /**
     * @return the circleCode
     */
    public String getCircleCode() {
        return circleCode;
    }

    /**
     * @param circleCode the circleCode to set
     */
    public void setCircleCode(String circleCode) {
        this.circleCode = circleCode;
    }

    /**
     * @return the divisionCode
     */
    public String getDivisionCode() {
        return divisionCode;
    }

    /**
     * @param divisionCode the divisionCode to set
     */
    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    /**
     * @return the subDivCode
     */
    public String getSubDivCode() {
        return subDivCode;
    }

    /**
     * @param subDivCode the subDivCode to set
     */
    public void setSubDivCode(String subDivCode) {
        this.subDivCode = subDivCode;
    }

    /**
     * @return the buCode
     */
    public String getBuCode() {
        return buCode;
    }

    /**
     * @param buCode the buCode to set
     */
    public void setBuCode(String buCode) {
        this.buCode = buCode;
    }

    /**
     * @return the fromDate
     */
    public java.sql.Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(java.sql.Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public java.sql.Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(java.sql.Date toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the fromMonth
     */
    public String getFromMonth() {
        return fromMonth;
    }

    /**
     * @param fromMonth the fromMonth to set
     */
    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }

    /**
     * @return the toMonth
     */
    public String getToMonth() {
        return toMonth;
    }

    /**
     * @param toMonth the toMonth to set
     */
    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    /**
     * @return the reportTitle
     */
    public String getReportTitle() {
        return reportTitle;
    }

    /**
     * @param reportTitle the reportTitle to set
     */
    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    /**
     * @return the reportSubTitle
     */
    public String getReportSubTitle() {
        return reportSubTitle;
    }

    /**
     * @param reportSubTitle the reportSubTitle to set
     */
    public void setReportSubTitle(String reportSubTitle) {
        this.reportSubTitle = reportSubTitle;
    }

    /**
     * @return the reportId
     */
    public long getReportId() {
        return reportId;
    }

    /**
     * @param reportId the reportId to set
     */
    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    /**
     * @return the reportCode
     */
    public String getReportCode() {
        return reportCode;
    }

    /**
     * @param reportCode the reportCode to set
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    /**
     * @return the reportDesc
     */
    public String getReportDesc() {
        return reportDesc;
    }

    /**
     * @param reportDesc the reportDesc to set
     */
    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    /**
     * @return the reportColumnList
     */
    public LinkedList getReportColumnList() {
        return reportColumnList;
    }

    /**
     * @param reportColumnList the reportColumnList to set
     */
    public void setReportColumnList(LinkedList reportColumnList) {
        this.reportColumnList = reportColumnList;
    }

    /**
     * @return the reportTableName
     */
    public String getReportTableName() {
        return reportTableName;
    }

    /**
     * @param reportTableName the reportTableName to set
     */
    public void setReportTableName(String reportTableName) {
        this.reportTableName = reportTableName;
    }

    /**
     * @return the reportWhereCondition
     */
    public String getReportWhereCondition() {
        return reportWhereCondition;
    }

    /**
     * @param reportWhereCondition the reportWhereCondition to set
     */
    public void setReportWhereCondition(String reportWhereCondition) {
        this.reportWhereCondition = reportWhereCondition;
    }

    /**
     * @return the reportSortOrder
     */
    public String getReportSortOrder() {
        return reportSortOrder;
    }

    /**
     * @param reportSortOrder the reportSortOrder to set
     */
    public void setReportSortOrder(String reportSortOrder) {
        this.reportSortOrder = reportSortOrder;
    }

    /**
     * @return the reportSql
     */
    public String getReportSql() {
        return reportSql;
    }

    /**
     * @param reportSql the reportSql to set
     */
    public void setReportSql(String reportSql) {
        this.reportSql = reportSql;
    }

    /**
     * @return the meterDataList
     */
    public LinkedList getMeterDataList() {
        return meterDataList;
    }

    /**
     * @param meterDataList the meterDataList to set
     */
    public void setMeterDataList(LinkedList meterDataList) {
        this.meterDataList = meterDataList;
    }

    /**
     * @return the defaultLocList
     */
    public LinkedList getDefaultLocList() {
        return defaultLocList;
    }

    /**
     * @param defaultLocList the defaultLocList to set
     */
    public void setDefaultLocList(LinkedList defaultLocList) {
        this.defaultLocList = defaultLocList;
    }

    /**
     * @return the circleList
     */
    public LinkedList getCircleList() {
        return circleList;
    }

    /**
     * @param circleList the circleList to set
     */
    public void setCircleList(LinkedList circleList) {
        this.circleList = circleList;
    }

    /**
     * @return the accessLocation
     */
    public String getAccessLocation() {
        return accessLocation;
    }

    /**
     * @param accessLocation the accessLocation to set
     */
    public void setAccessLocation(String accessLocation) {
        this.accessLocation = accessLocation;
    }

    /**
     * @return the tamperList
     */
    public LinkedList getTamperList() {
        return tamperList;
    }

    /**
     * @param tamperList the tamperList to set
     */
    public void setTamperList(LinkedList tamperList) {
        this.tamperList = tamperList;
    }

    /**
     * @return the eventId
     */
    public long getEventId() {
        return eventId;
    }

    /**
     * @param eventId the eventId to set
     */
    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    /**
     * @return the searchBeanObj
     */
//    public SearchBean getSearchBeanObj() {
    //       return searchBeanObj;
    //  }
    /**
     * @param searchBeanObj the searchBeanObj to set
     */
    //  public void setSearchBeanObj(SearchBean searchBeanObj) {
    //       this.searchBeanObj = searchBeanObj;
    //   }
    /**
     * @return the locationQuery
     */
    public String getLocationQuery() {
        return locationQuery;
    }

    /**
     * @param locationQuery the locationQuery to set
     */
    public void setLocationQuery(String locationQuery) {
        this.locationQuery = locationQuery;
    }

    /**
     * @return the flag
     */
    public long getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(long flag) {
        this.flag = flag;
    }

    /**
     * @return the readDateFrom
     */
    public Date getReadDateFrom() {
        return readDateFrom;
    }

    /**
     * @param readDateFrom the readDateFrom to set
     */
    public void setReadDateFrom(Date readDateFrom) {
        this.readDateFrom = readDateFrom;
    }

    /**
     * @return the readDateTo
     */
    public Date getReadDateTo() {
        return readDateTo;
    }

    /**
     * @param readDateTo the readDateTo to set
     */
    public void setReadDateTo(Date readDateTo) {
        this.readDateTo = readDateTo;
    }

    /**
     * @return the homeFlag
     */
    public String getHomeFlag() {
        return homeFlag;
    }

    /**
     * @param homeFlag the homeFlag to set
     */
    public void setHomeFlag(String homeFlag) {
        this.homeFlag = homeFlag;
    }

    /**
     * @return the minValue
     */
    public int getMin() {
        return minValue;
    }

    /**
     * @param minValue the minValue to set
     */
    public void setMin(int minValue) {
        this.minValue = minValue;
    }

    /**
     * @return the maxValue
     */
    public int getMax() {
        return maxValue;
    }

    /**
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return the sortColTableName
     */
    public String getSortColTableName() {
        return sortColTableName;
    }

    /**
     * @param sortColTableName the sortColTableName to set
     */
    public void setSortColTableName(String sortColTableName) {
        this.sortColTableName = sortColTableName;
    }

    /**
     * @return the sortColumnName
     */
    public String getSortColumnName() {
        return sortColumnName;
    }

    /**
     * @param sortColumnName the sortColumnName to set
     */
    public void setSortColumnName(String sortColumnName) {
        this.sortColumnName = sortColumnName;
    }

    /**
     * @return the lastRowValue
     */
    public String getLastRowValue() {
        return lastRowValue;
    }

    /**
     * @param lastRowValue the lastRowValue to set
     */
    public void setLastRowValue(String lastRowValue) {
        this.lastRowValue = lastRowValue;
    }

    /**
     * @return the lastValueDataType
     */
    public String getLastValueDataType() {
        return lastValueDataType;
    }

    /**
     * @param lastValueDataType the lastValueDataType to set
     */
    public void setLastValueDataType(String lastValueDataType) {
        this.lastValueDataType = lastValueDataType;
    }

    /**
     * @param maxValue the maxValue to set
     */
    public void setMax(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * @return the installPointTypeList
     */
    public LinkedList getInstallPointTypeList() {
        return installPointTypeList;
    }

    /**
     * @param installPointTypeList the installPointTypeList to set
     */
    public void setInstallPointTypeList(LinkedList installPointTypeList) {
        this.installPointTypeList = installPointTypeList;
    }

    /**
     * @return the selectedInstallPointType
     */
    public String getSelectedInstallPointType() {
        return selectedInstallPointType;
    }

    /**
     * @param selectedInstallPointType the selectedInstallPointType to set
     */
    public void setSelectedInstallPointType(String selectedInstallPointType) {
        this.selectedInstallPointType = selectedInstallPointType;
    }

    /**
     * @param loadProfileReadingDateList the loadProfileReadingDateList to set
     */
    public void setLoadProfileReadingDateList(LinkedList loadProfileReadingDateList) {
        this.loadProfileReadingDateList = loadProfileReadingDateList;
    }

    /**
     * @return the loadProfileReadingDateList
     */
    public LinkedList getLoadProfileReadingDateList() {
        return loadProfileReadingDateList;
    }

    /**
     * @return the reportPeriodCriteria
     */
    public String getReportPeriodCriteria() {
        return reportPeriodCriteria;
    }

    /**
     * @param reportPeriodCriteria the reportPeriodCriteria to set
     */
    public void setReportPeriodCriteria(String reportPeriodCriteria) {
        this.reportPeriodCriteria = reportPeriodCriteria;
    }

    /**
     * @return the loadSurveyMaxMinBean
     */
//    public LoadSurveyMaxMinBean getLoadSurveyMaxMinBean() {
//        return loadSurveyMaxMinBean;
//    }
    /**
     * @param loadSurveyMaxMinBean the loadSurveyMaxMinBean to set
     */
//    public void setLoadSurveyMaxMinBean(LoadSurveyMaxMinBean loadSurveyMaxMinBean) {
//        this.loadSurveyMaxMinBean = loadSurveyMaxMinBean;
//    }
    /**
     * @return the deviceTypeList
     */
    public LinkedList getDeviceTypeList() {
        return deviceTypeList;
    }

    /**
     * @param deviceTypeList the deviceTypeList to set
     */
    public void setDeviceTypeList(LinkedList deviceTypeList) {
        this.deviceTypeList = deviceTypeList;
    }

    /**
     * @return the DLMSdeviceTypeList
     */
    public LinkedList getDLMSdeviceTypeList() {
        return DLMSdeviceTypeList;
    }

    /**
     * @param DLMSdeviceTypeList the DLMSdeviceTypeList to set
     */
    public void setDLMSdeviceTypeList(LinkedList DLMSdeviceTypeList) {
        this.DLMSdeviceTypeList = DLMSdeviceTypeList;
    }

    public String getSECTION_HEAD_DESIG() {
        return SECTION_HEAD_DESIG;
    }

    public void setSECTION_HEAD_DESIG(String SECTION_HEAD_DESIG) {
        this.SECTION_HEAD_DESIG = SECTION_HEAD_DESIG;
    }

    public String getSECTION_HEAD_EMP_NO() {
        return SECTION_HEAD_EMP_NO;
    }

    public void setSECTION_HEAD_EMP_NO(String SECTION_HEAD_EMP_NO) {
        this.SECTION_HEAD_EMP_NO = SECTION_HEAD_EMP_NO;
    }

    public String getSECTION_HEAD_NAME() {
        return SECTION_HEAD_NAME;
    }

    public void setSECTION_HEAD_NAME(String SECTION_HEAD_NAME) {
        this.SECTION_HEAD_NAME = SECTION_HEAD_NAME;
    }

    public String getSECTION_NAME() {
        return SECTION_NAME;
    }

    public void setSECTION_NAME(String SECTION_NAME) {
        this.SECTION_NAME = SECTION_NAME;
    }
}
