package in.emp.search.bean;

// imports
import java.sql.*;
import in.emp.common.UserAuditBean;

public class DeviceTypeBean extends UserAuditBean implements java.io.Serializable 
{

	int deviceTypeId;
	String deviceTypeCd;
	String deviceTypeDesc;
	String mfgName;
	String isCommDevice;
	String commDeviceConfigPath;
	String statusCd;
	long createdBy;
	long updatedBy;
	Date createdDt;
	Date updatedDt;
        int  townId;
        int projectId;
        String townName;
        String townCode;
        String projectName;
        String projectCd;
        int serverId;
        String serverName;
        String serverLoc;

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getServerLoc() {
        return serverLoc;
    }

    public void setServerLoc(String serverLoc) {
        this.serverLoc = serverLoc;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

        
    public String getProjectCd() {
        return projectCd;
    }

    public void setProjectCd(String projectCd) {
        this.projectCd = projectCd;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
        
        

// public constructor 

	public DeviceTypeBean()
	{
	} // End of constructor

	public void setDeviceTypeId(int device_type_id)
	{
		deviceTypeId = device_type_id;
	}
	public int getDeviceTypeId()
	{
		return deviceTypeId;
	}

	public void setDeviceTypeCd(String device_type_cd)
	{
		deviceTypeCd = device_type_cd;
	}
	public String getDeviceTypeCd()
	{
		return deviceTypeCd;
	}
	
	public void setDeviceTypeDesc(String device_type_desc)
	{
		deviceTypeDesc = device_type_desc;
	}
	public String getDeviceTypeDesc()
	{
		return deviceTypeDesc;
	}
	
	public void setIsCommDevice(String is_comm_device)
	{
		isCommDevice = is_comm_device;
	}
	public String getIsCommDevice()
	{
		return isCommDevice;
	}

	public void setDeviceMfgName(String device_mfg_name)
	{
		mfgName = device_mfg_name;
	}
	public String getDeviceMfgName()
	{
		return mfgName;
	}

	public void setCommDeviceConfigPath(String comm_device_config_path)
	{
		commDeviceConfigPath = comm_device_config_path;
	}
	public String getCommDeviceConfigPath()
	{
		return commDeviceConfigPath;
	}

	public void setStatusCd(String status_cd)
	{
		statusCd = status_cd;
	}
	public String getStatusCd()
	{
		return statusCd;
	}

	public void setCreatedBy(long created_by)
	{
		createdBy = created_by;
	}
	public long getCreatedBy()
	{
		return createdBy;
	}

	public void setUpdatedBy(long updated_by)
	{
		updatedBy = updated_by;
	}
	public long getUpdatedBy()
	{
		return updatedBy;
	}

	public void setCreatedDt(Date created_dt)
	{
		createdDt = created_dt;
	}
	public Date getCreatedDt()
	{
		return createdDt;
	}

	public void setUpdatedDt(Date updated_dt)
	{
		updatedDt = updated_dt;
	}
	public Date getUpdatedDt()
	{
		return updatedDt;
	}

} // End of Class