package in.emp.search.bean;

// imports
import java.sql.*;
import in.emp.common.UserAuditBean;

public class SearchResultBean extends UserAuditBean implements java.io.Serializable
{
	
	Date execution_start_time;
	Date execution_end_time;
	String execution_result_code;
	Date created_dt;
	Date updated_dt;
	int created_by;
	int device_id;
	long device_scehdule_history_id;
	int device_schedule_id;
	int updated_by;
	String execution_result;
	String device_type_description;
	String device_serial_no;
	Date next_execution_time;
	String install_point_type;
        private String installPointName;
        private String installPointTypeCd;
	String xml_file_name;
	int device_Location_Id;
	int device_Data_History_Id;
	int wf_Device_Data_Id;
	String wf_Device_Data_Status_Desc;
	int wf_Device_Data_Status_Id;
	long number_id;
	String inst_point_name;
        String consumerName;
        String feederName;
        String transformerName;
        private String scheduleLogPath;
        //For layout Changes
        private String ipAddress;
        private String phoneNumber;
        private String modemSerialNo;
        private String MODEM_TECH_DESC;
        private String projectName;
        private String townName;
        private String consumerStatus;

    public String getConsumerStatus() {
        return consumerStatus;
    }

    public void setConsumerStatus(String consumerStatus) {
        this.consumerStatus = consumerStatus;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
        


	/* Public Constructor*/
	public SearchResultBean()
	{
	} // End of constructor

	/* Getter and Setter Methods*/
	
	
	public void setExecutionStartTime(Date exe_start_time)
	{
		execution_start_time = exe_start_time;
	}
	public Date getExecutionStartTime()
	{
		return execution_start_time;
	}

	public void setExecutionEndTime(Date exe_end_time)
	{
		execution_end_time = exe_end_time;
	}
	public Date getExecutionEndTime()
	{
		return execution_end_time;
	}
	
	public void setDeviceScheduleId(int device_sch_id)
	{
		device_schedule_id = device_sch_id;
	}
	public int getDeviceScheduleId()
	{
		return device_schedule_id;
	}

	public void setExecutionResultCode(String exe_result_code)
	{
		execution_result_code = exe_result_code;
	}
	public String getExecutionResultCode()
	{
		return execution_result_code;
	}

	
	public void setCreatedDt(Date createdDt)
	{
		created_dt = createdDt;
	}
	public Date getCreatedDt()
	{
		return created_dt;
	}

	public void setUpdatedDt(Date updatedDt)
	{
		updated_dt = updatedDt;
	}
	public Date getUpdatedDt()
	{
		return updated_dt;
	}

	
	public void setCreatedBy(int createdBy)
	{
		created_by = createdBy;
	}
	public long getCreatedBy()
	{
		return created_by;
	}

	public void setDeviceId(int deviceId)
	{
		device_id = deviceId;
	}
	public int getDeviceId()
	{
		return device_id;
	}

	public void setDeviceScehduleHistoryId(long deviceScehduleHistoryId)
	{
		device_scehdule_history_id = deviceScehduleHistoryId;
	}
	public long getDeviceScehduleHistoryId()
	{
		return device_scehdule_history_id;
	}
	
	

	public void setUpdatedBy(int updatedBy)
	{
		updated_by = updatedBy;
	}
	public long getUpdatedBy()
	{
		return updated_by;
	}

	
	public void setExecutionResult(String executionResult)
	{
		execution_result = executionResult;
	}
	public String getExecutionResult()
	{
		return execution_result;
	}
	
	public void setDeviceTypeDescription(String deviceTypeDescription)
	{
		device_type_description = deviceTypeDescription;
	}
	public String getDeviceTypeDescription()
	{
		return device_type_description;
	}

	public void setDeviceSerialNo(String deviceSerialNo)
	{
		device_serial_no = deviceSerialNo;
	}
	public String getDeviceSerialNo()
	{
		return device_serial_no;
	}

	public void setNextExecutionTime(Date nextExecutionTime)
	{
		next_execution_time = nextExecutionTime;
	}
	public Date getNextExecutionTime()
	{
		return next_execution_time;
	}

	public void setinstallPointType(String installPointType)
	{
		install_point_type = installPointType;
	}
	public String getinstallPointType()
	{
		return install_point_type;
	}

	public void setXmlFileName(String xmlFileName)
	{
		xml_file_name = xmlFileName;
	}
	public String getXmlFileName()
	{
		return xml_file_name;
	}

	public void setDeviceLocationId(int device_Loc_Id)
	{
		device_Location_Id = device_Loc_Id;
	}
	public int getDeviceLocationId()
	{
		return device_Location_Id;
	}

	public void setDeviceDataHistoryId(int deviceDataHistoryId)
	{
		device_Data_History_Id = deviceDataHistoryId;
	}
	public int getDeviceDataHistoryId()
	{
		return device_Data_History_Id;
	}

	public void setWFDeviceDataId(int wfDeviceDataId)
	{
		wf_Device_Data_Id = wfDeviceDataId;
	}
	public int getWFDeviceDataId()
	{
		return wf_Device_Data_Id;
	}

	public void setWFDeviceDataStatusCd(String wfDeviceDataStatusDesc)
	{
		wf_Device_Data_Status_Desc = wfDeviceDataStatusDesc;
	}
	public String getWFDeviceDataStatusCd()
	{
		return wf_Device_Data_Status_Desc;
	}
	
	public void setWFDeviceDataStatusId(int wfDeviceDataStatusId)
	{
		wf_Device_Data_Status_Id = wfDeviceDataStatusId;
	}
	public int getWFDeviceDataStatusId()
	{
		return wf_Device_Data_Status_Id;
	}

	public void setNumber(long number)
	{
		number_id = number;
	}
	public long getNumber()
	{
		return number_id;
	}

	public void setName(String instPointName)
	{
		inst_point_name = instPointName;
	}
	public String getName()
	{
		return inst_point_name;
	}

    /**
     * @return the consumerName
     */
    public String getConsumerName() {
        return consumerName;
    }

    /**
     * @param consumerName the consumerName to set
     */
    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    /**
     * @return the feederName
     */
    public String getFeederName() {
        return feederName;
    }

    /**
     * @param feederName the feederName to set
     */
    public void setFeederName(String feederName) {
        this.feederName = feederName;
    }

    /**
     * @return the transformerName
     */
    public String getTransformerName() {
        return transformerName;
    }

    /**
     * @param transformerName the transformerName to set
     */
    public void setTransformerName(String transformerName) {
        this.transformerName = transformerName;
    }

    /**
     * @return the installPointTypeCd
     */
    public String getInstallPointTypeCd() {
        return installPointTypeCd;
    }

    /**
     * @param installPointTypeCd the installPointTypeCd to set
     */
    public void setInstallPointTypeCd(String installPointTypeCd) {
        this.installPointTypeCd = installPointTypeCd;
    }

    /**
     * @return the installPointName
     */
    public String getInstallPointName() {
        return installPointName;
    }

    /**
     * @param installPointName the installPointName to set
     */
    public void setInstallPointName(String installPointName) {
        this.installPointName = installPointName;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the modemSerialNo
     */
    public String getModemSerialNo() {
        return modemSerialNo;
    }

    /**
     * @param modemSerialNo the modemSerialNo to set
     */
    public void setModemSerialNo(String modemSerialNo) {
        this.modemSerialNo = modemSerialNo;
    }
    
        public String getMODEM_TECH_DESC() {
        return MODEM_TECH_DESC;
    }

    public void setMODEM_TECH_DESC(String MODEM_TECH_DESC) {
        this.MODEM_TECH_DESC = MODEM_TECH_DESC;
    }

    /**
     * @return the scheduleLogPath
     */
    public String getScheduleLogPath() {
        return scheduleLogPath;
    }

    /**
     * @param scheduleLogPath the scheduleLogPath to set
     */
    public void setScheduleLogPath(String scheduleLogPath) {
        this.scheduleLogPath = scheduleLogPath;
    }
		
} // End of class
