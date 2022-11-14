package in.emp.search.bean;

import java.io.*;
import java.lang.*;
import java.sql.*;

import in.emp.common.UserAuditBean;


public class LocationBean extends UserAuditBean implements java.io.Serializable 
{
	/* bean to collect Data from LK_LOCATION table	*/
	
	int location_id;
	int parent_location_id;
        private String parentLocationString;
	int location_type_id;
	long created_by ;
	long	updated_by;
	String location_cd;
	String location_description;
        String accessLocation;
	String status_cd ;
	Date created_dt ;
	Date updated_dt ;


	public void setLocationId(int loc_id )
	{	
		location_id = loc_id;
	}
	public int getLocationId()
	{	
		return location_id ;
	}

	public void setParentLocationId(int p_loc_id )
	{	
		parent_location_id = p_loc_id;
	}

	public int getParentLocationId()
	{	
		return parent_location_id;
	}
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
	
	public void setLocationCd(String loc_code)
	{	
		location_cd = loc_code;
	}

	public String getLocationCd()
	{	
		return location_cd;
	}

	public void setLocationDescription(String loc_name)
	{	
		location_description = loc_name;
	}
	
	public String getLocationDescription()
	{	
		return location_description;
	}
	
	public void setStatusC(String status)
	{	
		status_cd = status;
	}

	public String getStatusC()
	{	
		return status_cd;
	}
	
	public void setDateCreatedDt(Date dt_Created_dt)
	{	
		created_dt = dt_Created_dt;
	}

	public Date getDateCreatedDt()
	{	
		return created_dt;
	}
	
	public void setDateUpdatedDt(Date dt_updated_dt)
	{	
		updated_dt = dt_updated_dt;
	}

	public Date getDateUpdatedDt()
	{	
		return updated_dt;
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
     * @return the parentLocationString
     */
    public String getParentLocationString() {
        return parentLocationString;
    }

    /**
     * @param parentLocationString the parentLocationString to set
     */
    public void setParentLocationString(String parentLocationString) {
        this.parentLocationString = parentLocationString;
    }
	
}	
