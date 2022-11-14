package in.emp.master.bean;

// -- Imports
import in.emp.common.UserAuditBean;
import java.io.*;
import java.lang.*;
import java.sql.*;




public class  LocationTypeBean extends UserAuditBean implements java.io.Serializable 
{

	private long locationTypeId;
	private String locationTypeCd;
	private String locationTypeDesc;
	
	//-- public constructor
	public LocationTypeBean()
	{
	} // end of constructor

	public String getLocationTypeCd() {
		return locationTypeCd;
	}

	public void setLocationTypeCd(String locationTypeCd) {
		this.locationTypeCd = locationTypeCd;
	}

	public String getLocationTypeDesc() {
		return locationTypeDesc;
	}

	public void setLocationTypeDesc(String locationTypeDesc) {
		this.locationTypeDesc = locationTypeDesc;
	}

	public long getLocationTypeId() {
		return locationTypeId;
	}

	public void setLocationTypeId(long locationTypeId) {
		this.locationTypeId = locationTypeId;
	}

} // End of Class--
