/*
 * Created on Feb 15, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package in.emp.search.bean;

import in.emp.common.UserAuditBean;

import java.sql.Date;

public class SearchBean extends UserAuditBean implements java.io.Serializable {

    int minValue;
    int maxValue;
    String sortColTableName;
    String device_type;
    String location_type;
    String location;
    String schedule_period;
    String sortColumnName;
    String sortOrder;
    Date sch_from_dt;
    Date sch_to_dt;
    String lastRowValue;
    String lastValueDataType;
    double totalRecords;
    String install_point_type;
    String number_id;

    private long zone_Id;
    private long circle_Id;
    private long division_Id;
    private long sub_Division_Id;
    private long sub_Station_id;
    private long feeder_id;
    private long dtc_id;
    private String consumer_id;
    private String accessLocation;
    private String locationQuery;

    String profileType;
    String profileId;
    String attachedTO;
    String profileName;
    long srNo;
    long schDetId;
    private String consumerTypeId;

    private String rtcDifference;
    private String rtcStatus;
    private String statusCode;

    public String getConsumerTypeId() {
        return consumerTypeId;
    }

    public void setConsumerTypeId(String consumerTypeId) {
        this.consumerTypeId = consumerTypeId;
    }

    public long getSchDetId() {
        return schDetId;
    }

    public void setSchDetId(long schDetId) {
        this.schDetId = schDetId;
    }

    private String resultCode;

    private String searchCriteria;
    private String searchValue;
    long projectId;
    long townId;
    private long groupValue;
    long user_id;

    String create_dt;

    public String getCreate_dt() {
        return create_dt;
    }

    public void setCreate_dt(String create_dt) {
        this.create_dt = create_dt;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getGroupValue() {
        return groupValue;
    }

    public void setGroupValue(long groupValue) {
        this.groupValue = groupValue;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getTownId() {
        return townId;
    }

    public void setTownId(long townId) {
        this.townId = townId;
    }


    /*
     * public Constructor of class SearchBean
     */
    public SearchBean() {
    }

    public void setSrNo(long srNo) {
        this.srNo = srNo;
    }

    public long getSrNo() {
        return this.srNo;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileName() {
        return this.profileName;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public void setAttachedTO(String attachedTO) {
        this.attachedTO = attachedTO;
    }

    public String getAttachedTO() {
        return this.attachedTO;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public String getProfileType() {
        return this.profileType;
    }

	//-- And follows all the getters and setters 
    //Set & Get Method For Device Type
    public void setDeviceType(String d_type) {
        device_type = d_type;
    }

    public String getDeviceType() {
        return device_type;
    }

    public void setLocationType(String loc_type) {
        location_type = loc_type;
    }

    public String getLocationType() {
        return location_type;
    }

    //  Set & Get Method For Circle
    public void setLocation(String loc) {
        location = loc;
    }

    public String getLocation() {
        return location;
    }

    //  Set & Get Method For Schedule Period
    public void setSchedulePeriod(String schedule_p) {
        schedule_period = schedule_p;
    }

    public String getSchedulePeriod() {
        return schedule_period;
    }

    //  Set & Get Method For ScheduleFrom Date and Time
    public void setScheduleFrom(Date schFromDt) {
        sch_from_dt = schFromDt;
    }

    public Date getScheduleFrom() {
        return sch_from_dt;
    }

	//  Set & Get Method For ScheduleTo Date & Time..
    public void setScheduleTo(Date schToDt) {
        sch_to_dt = schToDt;
    }

    public Date getScheduleTo() {
        return sch_to_dt;
    }

    public void setSortColumnName(String columnName) {
        sortColumnName = columnName;
    }

    public String getSortColumnName() {
        return sortColumnName;
    }

    public void setSortOrder(String order) {
        sortOrder = order;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setMax(int max) {
        maxValue = max;
    }

    public int getMax() {
        return maxValue;
    }

    public void setMin(int min) {
        minValue = min;
    }

    public int getMin() {
        return minValue;
    }

    public void setSortColTableName(String setSortColTabName) {
        sortColTableName = setSortColTabName;
    }

    public String getSortColTableName() {
        return sortColTableName;
    }

    public void setTotalRecords(double tRecords) {
        totalRecords = tRecords;
    }

    public double getTotalRecords() {
        return totalRecords;
    }

    public void setLastRowValue(String lastValue) {
        lastRowValue = lastValue;
    }

    public String getLastRowValue() {
        return lastRowValue;
    }

    public void setLastValueDataType(String dataType) {
        lastValueDataType = dataType;
    }

    public String getLastValueDataType() {
        return lastValueDataType;
    }

    public void setInstallPointType(String installPointType) {
        install_point_type = installPointType;
    }

    public String getInstallPointType() {
        return install_point_type;
    }

    public void setNumberId(String num_id) {
        number_id = num_id;
    }

    public String getNumberId() {
        return number_id;
    }

    public void setZoneId(long zoneId) {
        this.zone_Id = zoneId;
    }

    public long getZoneId() {
        return zone_Id;
    }

    public void setCircleId(long circleId) {
        this.circle_Id = circleId;
    }

    public long getCircleId() {
        return circle_Id;
    }

    public void setDivisionId(long divisionId) {
        this.division_Id = divisionId;
    }

    public long getDivisionId() {
        return division_Id;
    }

    public void setSubDivisionId(long subDivisionId) {
        this.sub_Division_Id = subDivisionId;
    }

    public long getSubDivisionId() {
        return sub_Division_Id;
    }

    /**
     * @return the sub_Station_id
     */
    public long getSubStationid() {
        return sub_Station_id;
    }

    /**
     * @param sub_Station_id the sub_Station_id to set
     */
    public void setSubStationid(long sub_Station_id) {
        this.sub_Station_id = sub_Station_id;
    }

    /**
     * @return the feeder_id
     */
    public long getFeederid() {
        return feeder_id;
    }

    /**
     * @param feeder_id the feeder_id to set
     */
    public void setFeederid(long feeder_id) {
        this.feeder_id = feeder_id;
    }

    /**
     * @return the dtc_id
     */
    public long getDtcid() {
        return dtc_id;
    }

    /**
     * @param dtc_id the dtc_id to set
     */
    public void setDtcid(long dtc_id) {
        this.dtc_id = dtc_id;
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
     * @return the resulrCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @param resulrCode the resulrCode to set
     */
    public void setResultCode(String resulrCode) {
        this.resultCode = resulrCode;
    }

    /**
     * @return the searchCriteria
     */
    public String getSearchCriteria() {
        return searchCriteria;
    }

    /**
     * @param searchCriteria the searchCriteria to set
     */
    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * @return the searchValue
     */
    public String getSearchValue() {
        return searchValue;
    }

    /**
     * @param searchValue the searchValue to set
     */
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    /**
     * @return the consumer_id
     */
    public String getConsumerid() {
        return consumer_id;
    }

    /**
     * @param consumer_id the consumer_id to set
     */
    public void setConsumerid(String consumer_id) {
        this.consumer_id = consumer_id;
    }

    /**
     * @return the rtcDifference
     */
    public String getRtcDifference() {
        return rtcDifference;
    }

    /**
     * @param rtcDifference the rtcDifference to set
     */
    public void setRtcDifference(String rtcDifference) {
        this.rtcDifference = rtcDifference;
    }

    /**
     * @return the rtcStatus
     */
    public String getRtcStatus() {
        return rtcStatus;
    }

    /**
     * @param rtcStatus the rtcStatus to set
     */
    public void setRtcStatus(String rtcStatus) {
        this.rtcStatus = rtcStatus;
    }

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

}//class ends
