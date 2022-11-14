package in.emp.search.bean;

// -- Imports
import java.io.*;
import java.lang.*;
import java.sql.*;

import in.emp.common.UserAuditBean;


public class  LocationTypeBean extends UserAuditBean implements java.io.Serializable 
{

	int location_type_id;
	long created_by ;
	long	updated_by;
	String location_type_cd;
	String location_type_description;
	String status_cd ;
	Date created_dt ;
	Date updated_dt ;

	//-- public constructor
	public LocationTypeBean()
	{
	} // end of constructor

	//-- Setter and getter methods--

	public void setLocationTypeId(int loc_type_id )
	{	
		location_type_id = loc_type_id;
	}
	public int getLocationTypeId()
	{	
		return location_type_id;
	}
	
	public void setCreatedBy(long created_n )
	{	
		created_by = created_n;
	}
	public long getCreatedBy()
	{	
		return created_by;
	}

	public void setUpdatedBy(long updated_n )
	{	
		updated_by = updated_n;
	}
	public long getUpdatedBy()
	{	
		return updated_by;
	}
	
	public void setLocationTypeCd(String loc_type_code)
	{	
		location_type_cd = loc_type_code;
	}
	public String getLocationTypeCd()
	{	
		return location_type_cd;
	}

	public void setLocationTypeDescription(String loc_type_desc)
	{	
		location_type_description = loc_type_desc;
	}
	public String getLocationTypeDescription()
	{	
		return location_type_description;
	}
	
	public void setStatusCd(String status)
	{	
		status_cd = status;
	}

	public String getStatusCd()
	{	
		return status_cd;
	}
	
	public void setCreatedDt(Date created_dt)
	{	
		created_dt = created_dt;
	}

	public Date getCreatedDt()
	{	
		return created_dt;
	}
	
	public void setUpdatedDt(Date updated_dt)
	{	
		updated_dt = updated_dt;
	}

	public Date getUpdatedDt()
	{	
		return updated_dt;
	}

} // End of Class--
