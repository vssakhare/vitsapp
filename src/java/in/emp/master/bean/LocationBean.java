package in.emp.master.bean;

import in.emp.common.UserAuditBean;
import java.io.*;
import java.lang.*;
import java.sql.*;




public class LocationBean extends UserAuditBean implements java.io.Serializable 
{
	/* bean to collect Data from LK_LOCATION table	*/
	
	private long locationId;
	private long parentLocationId;
	private long locationTypeId;
	private String locationCd;
	private String locationDesc;
	
	public String getLocationCd() {
		return locationCd;
	}
	public void setLocationCd(String locationCd) {
		this.locationCd = locationCd;
	}
	public String getLocationDesc() {
		return locationDesc;
	}
	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	public long getLocationTypeId() {
		return locationTypeId;
	}
	public void setLocationTypeId(long locationTypeId) {
		this.locationTypeId = locationTypeId;
	}
	public long getParentLocationId() {
		return parentLocationId;
	}
	public void setParentLocationId(long parentLocationId) {
		this.parentLocationId = parentLocationId;
	}
	
}	
