package in.emp.search.bean;

import java.util.HashMap;
import in.emp.common.UserAuditBean;

public class DeviceDataBean extends UserAuditBean implements java.io.Serializable {

    private int minValue;
    private int maxValue;
    private long userId;
    private String sortColTableName;
    private String sortColumnName;
    private String sortOrder;
    private String lastRowValue;
    private String lastValueDataType;
    private double totalRecords;
    private long workflowItemId;
    private long deviceId;
    private long deviceDataStatusId;
    private long deviceDataHistoryId;
    private long locationId;
    private String xmlFileName;
    private String wfStatus;
    private String deviceDataStatusCd;
    private String deviceDataStatusDesc;
    private String locationCd;
    private String locationDesc;
    private String deviceSerialNum;
    private String deviceTypeCd;
    private String deviceTypeDesc;
    private String insPointTypeCd;
    private String workflowItemIdStr;
    private long locationTypeId;
    private String operationType;
    private String htmlStringXmlFile;
    private String xslFilePath;
    private String wipXmlFileRef;
    private long deviceTypeId;
    HashMap xPathMap;
    private String oldXmlFileRef;
    private boolean xmlEditMode;
    private java.sql.Date updatedDt;
    private String uiPageType;
    private String devInstallPointId;
    private String devInstallPointType;
    private long devInstallPointTypeId;
    private long feederId;
    private long consumerId;
    private long transformerId;
    private String installPointName;
    private long zoneId;
    private long circleId;
    private long divisionId;
    private long subDivisionId;
    private String xmlFilePath;

    public String getXmlFilePath() {
        return xmlFilePath;
    }

    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    /*
     * public Constructor of class DeviceDataBean
     */
    public DeviceDataBean() {
    }// End of constructor

    // Setter and Getter method
    public void setWorkflowItemId(long workflowId) {
        workflowItemId = workflowId;
    }

    public long getWorkflowItemId() {
        return workflowItemId;
    }

    public void setDeviceId(long devId) {
        deviceId = devId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setLocationId(long locId) {
        locationId = locId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setXmlFileName(String xmlfileName) {
        xmlFileName = xmlfileName;
    }

    public String getXmlFileName() {
        return xmlFileName;
    }

    public void setDeviceDataStatusId(long deviceDataStId) {
        deviceDataStatusId = deviceDataStId;
    }

    public long getDeviceDataStatusId() {
        return deviceDataStatusId;
    }

    public void setDeviceDataHistoryId(long deviceDataHistId) {
        deviceDataHistoryId = deviceDataHistId;
    }

    public long getDeviceDataHistoryId() {
        return deviceDataHistoryId;
    }

    public void setWFStatus(String wfstatus) {
        wfStatus = wfstatus;
    }

    public String getWFStatus() {
        return wfStatus;
    }

    public void setDeviceDataStatusCd(String deviceDataStCd) {
        deviceDataStatusCd = deviceDataStCd;
    }

    public String getDeviceDataStatusCd() {
        return deviceDataStatusCd;
    }

    public void setDeviceDataStatusDesc(String deviceDataStDesc) {
        deviceDataStatusDesc = deviceDataStDesc;
    }

    public String getDeviceDataStatusDesc() {
        return deviceDataStatusDesc;
    }

    public void setLocationCd(String locCd) {
        locationCd = locCd;
    }

    public String getLocationCd() {
        return locationCd;
    }

    public void setLocationDesc(String locDesc) {
        locationDesc = locDesc;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setDeviceSerialNum(String devSerialNum) {
        deviceSerialNum = devSerialNum;
    }

    public String getDeviceSerialNum() {
        return deviceSerialNum;
    }

    public void setDeviceTypeCd(String devTypeCd) {
        deviceTypeCd = devTypeCd;
    }

    public String getDeviceTypeCd() {
        return deviceTypeCd;
    }

    public void setDeviceTypeDesc(String devTypeDesc) {
        deviceTypeDesc = devTypeDesc;
    }

    public String getDeviceTypeDesc() {
        return deviceTypeDesc;
    }

    public void setInsPointTypeCd(String insPointCd) {
        insPointTypeCd = insPointCd;
    }

    public String getInsPointTypeCd() {
        return insPointTypeCd;
    }

    public void setLastValueDataType(String dataType) {
        lastValueDataType = dataType;
    }

    public String getLastValueDataType() {
        return lastValueDataType;
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

    public void setUserId(long userid) {
        userId = userid;
    }

    public long getUserId() {
        return userId;
    }

    public void setWorkflowItemIdStr(String workflowIdStr) {
        workflowItemIdStr = workflowIdStr;
    }

    public String getWorkflowItemIdStr() {
        return workflowItemIdStr;
    }

    public void setLocationTypeId(long locTypeId) {
        locationTypeId = locTypeId;
    }

    public long getLocationTypeId() {
        return locationTypeId;
    }

    public void setOperationType(String operation_type) {
        operationType = operation_type;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setHtmlString(String html_String) {
        htmlStringXmlFile = html_String;
    }

    public String getHtmlString() {
        return htmlStringXmlFile;
    }

    public void setXslFilePath(String xsl_File_Path) {
        xslFilePath = xsl_File_Path;
    }

    public String getXslFilePath() {
        return xslFilePath;
    }

    public void setDeviceTypeId(long divTypeId) {
        deviceTypeId = divTypeId;
    }

    public long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setWipXmlFileRef(String wip_Xml_File_Ref) {
        wipXmlFileRef = wip_Xml_File_Ref;
    }

    public String getWipXmlFileRef() {
        return wipXmlFileRef;
    }

    public void setXPathMap(HashMap xpathMap) {
        xPathMap = xpathMap;
    }

    public HashMap getXPathMap() {
        return xPathMap;
    }

    public void setOldXmlFileRef(String old_Xml_File_Ref) {
        oldXmlFileRef = old_Xml_File_Ref;
    }

    public String getOldXmlFileRef() {
        return oldXmlFileRef;
    }

    public void setXmlEditMode(boolean xml_Edit_Mode) {
        xmlEditMode = xml_Edit_Mode;
    }

    public boolean getXmlEditMode() {
        return xmlEditMode;
    }

    public void setUpdatedDt(java.sql.Date updated_Dt) {
        updatedDt = updated_Dt;
    }

    public java.sql.Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUiPageType(String ui_Page_Type) {
        uiPageType = ui_Page_Type;
    }

    public String getUiPageType() {
        return uiPageType;
    }

    public void setDevInstallPointId(String devInstallPointId) {
        this.devInstallPointId = devInstallPointId;
    }

    public String getDevInstallPointId() {
        return devInstallPointId;
    }

    public void setDevInstallPointType(String devInstallPointType) {
        this.devInstallPointType = devInstallPointType;
    }

    public String getDevInstallPointType() {
        return devInstallPointType;
    }

    public void setDevInstallPointTypeId(long devInstallPointTypeId) {
        this.devInstallPointTypeId = devInstallPointTypeId;
    }

    public long getDevInstallPointTypeId() {
        return devInstallPointTypeId;
    }

    public void setFeederId(long feederId) {
        this.feederId = feederId;
    }

    public long getFeederId() {
        return feederId;
    }

    public void setConsumerId(long consumerId) {
        this.consumerId = consumerId;
    }

    public long getConsumerId() {
        return consumerId;
    }

    public void setTransformerId(long transformerId) {
        this.transformerId = transformerId;
    }

    public long getTransformerId() {
        return transformerId;
    }

    public void setInstallPointName(String installPointName) {
        this.installPointName = installPointName;
    }

    public String getInstallPointName() {
        return installPointName;
    }

    public void setZoneId(long zoneId) {
        this.zoneId = zoneId;
    }

    public long getZoneId() {
        return zoneId;
    }

    public void setCircleId(long circleId) {
        this.circleId = circleId;
    }

    public long getCircleId() {
        return circleId;
    }

    public void setDivisionId(long divisionId) {
        this.divisionId = divisionId;
    }

    public long getDivisionId() {
        return divisionId;
    }

    public void setSubDivisionId(long subDivisionId) {
        this.subDivisionId = subDivisionId;
    }

    public long getSubDivisionId() {
        return subDivisionId;
    }
}//End of Class
