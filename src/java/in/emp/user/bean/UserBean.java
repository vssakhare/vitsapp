package in.emp.user.bean;

// MDA packages
import in.emp.common.UserAuditBean;

// java imports

import java.io.*;
import java.lang.*;
import java.sql.*;


public class UserBean  extends UserAuditBean implements java.io.Serializable
{
		
	private long userId;
	private double totalRecords;
	private long locationTypeId;
	private long locationId;
	private long userEmpId;
	private String userLoginId;
	private String userPassword;
        private String confPass;
	private String userName;
	//private String userLastName;
	private String userLocation;
	private String userOrganization;
	private String userIdStr;
	private String userStatus;
		
	private int minValue;
	private int maxValue;
        private long changPassCount;
	private String sortColTableName;
	private String sortColumnName;
	private String sortOrder;
	private String lastRowValue;
	private String lastValueDataType;
        
        private String searchCriteria;
        private String searchvalue;
        private String selectedAuthFlag;
	private long zoneId;
	private long circleId;
	private long divisionId;
	private long subDivisionId;
        private long substationId;
        private long feederId;
        private long dtcId;
        private long consumerId;
 
        private String oldPass;
        private String newPass;
        
        private String readingMonth;
        private String project;
        private String town;
        
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
      
        
        
        

    public String getReadingMonth() {
        return readingMonth;
    }

    public void setReadingMonth(String readingMonth) {
        this.readingMonth = readingMonth;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }
        
	
	public void setUserId(long userId)
	{
		this.userId = userId;
	}
	public long getUserId()
	{
		return this.userId;
	}
	
	public void setTotalRecords(double totalRecords)
	{
		this.totalRecords = totalRecords;
	}
	public double getTotalRecords()
	{
		return this.totalRecords;
	}	

	public void setLocationTypeId(long locationTypeId)
	{
		this.locationTypeId = locationTypeId;
	}
	public long  getLocationTypeId()
	{
		return this.locationTypeId;
	}

	public void setLocationId(long locationId)
	{
		this.locationId = locationId;
	}
	public long  getLocationId()
	{
		return this.locationId;
	}

	public void setUserEmpId(long userEmpId)
	{
		this.userEmpId = userEmpId;
	}
	public long  getUserEmpId()
	{
		return this.userEmpId;
	}

	public void setUserLoginId(String userLoginId)
	{
		this.userLoginId = userLoginId;
	}
	public String getUserLoginId()
	{
		return this.userLoginId;
	}

	public void setUserPassword(String userPassword) 
	{
		this.userPassword = userPassword;
	}
	public java.lang.String getUserPassword()
	{
		return this.userPassword;
	}

/*	public void setUserFirstName(String userFirstName)
	{
		this.userFirstName = userFirstName;
	}
	public String getUserFirstName()
	{
		return this.userFirstName;
	}

	public void setUserLastName(String userLastName)
	{
		this.userLastName = userLastName;
	}
	public String getUserLastName()
	{
		return this.userLastName;
	}*/

	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getUserName()
	{
		return this.userName;
	}

	public void setUserLocation(String userLocation)
	{
		this.userLocation = userLocation;
	}
	public String getUserLocation()
	{
		return this.userLocation;
	}

	public void setUserOrganization(String userOrganization)
	{
		this.userOrganization = userOrganization;
	}
	public String getUserOrganization()
	{
		return this.userOrganization;
	}

	public void setUserIdStr(String userIdStr)
	{
		this.userIdStr = userIdStr;
	}
	public String getUserIdStr()
	{
		return this.userIdStr;
	}

	public void setUserStatus(String userStatus)
	{
		this.userStatus = userStatus;
	}
	public String getUserStatus()
	{
		return this.userStatus;
	}


	public void setMin(int minValue)
	{
		this.minValue = minValue;
	}
	public int getMin()
	{
		return this.minValue;
	}	

	public void setMax(int maxValue)
	{
		this.maxValue = maxValue;
	}
	public int getMax()
	{
		return this.maxValue;
	}

	public void setSortColTableName(String sortColTableName)
	{
		this.sortColTableName = sortColTableName;
	}
	public String getSortColTableName()
	{
		return this.sortColTableName;
	}	

	public void setSortColumnName(String sortColumnName)
	{
		this.sortColumnName = sortColumnName;
	}
	public String getSortColumnName()
	{
		return this.sortColumnName;
	}
	
	public void setSortOrder(String sortOrder)
	{
		this.sortOrder = sortOrder;
	}
	public String getSortOrder()
	{
		return this.sortOrder;
	}

	public void setLastRowValue(String lastRowValue)
	{
		this.lastRowValue = lastRowValue;
	}
	public String getLastRowValue()
	{
		return this.lastRowValue;
	}

	public void setLastValueDataType(String lastValueDataType)
	{
		this.lastValueDataType = lastValueDataType;
	}
	public String getLastValueDataType()
	{
		return this.lastValueDataType;
	}

	public void setZoneId(long zoneId)
	{
		this.zoneId = zoneId;
	}
	public long getZoneId()
	{
		return zoneId;
	}

	public void setCircleId(long circleId)
	{
		this.circleId = circleId;
	}
	public long getCircleId()
	{
		return circleId;
	}

	public void setDivisionId(long divisionId)
	{
		this.divisionId = divisionId;
	}
	public long getDivisionId()
	{
		return divisionId;
	}

	public void setSubDivisionId(long subDivisionId)
	{
		this.subDivisionId = subDivisionId;
	}
	public long getSubDivisionId()
	{
		return subDivisionId;
	}

    /**
     * @return the substationId
     */
    public long getSubstationId() {
        return substationId;
    }

    /**
     * @param substationId the substationId to set
     */
    public void setSubstationId(long substationId) {
        this.substationId = substationId;
    }

    /**
     * @return the feederId
     */
    public long getFeederId() {
        return feederId;
    }

    /**
     * @param feederId the feederId to set
     */
    public void setFeederId(long feederId) {
        this.feederId = feederId;
    }

    /**
     * @return the dtcId
     */
    public long getDtcId() {
        return dtcId;
    }

    /**
     * @param dtcId the dtcId to set
     */
    public void setDtcId(long dtcId) {
        this.dtcId = dtcId;
    }

    /**
     * @return the consumerId
     */
    public long getConsumerId() {
        return consumerId;
    }

    /**
     * @param consumerId the consumerId to set
     */
    public void setConsumerId(long consumerId) {
        this.consumerId = consumerId;
    }

    /**
     * @return the conf pass
     */
    public String getConfPass() {
        return confPass;
    }

    /**
     * @param conf pass the conf pass to set
     */
    public void setConfPass(String confPass) {
        this.confPass = confPass;
    }

    /**
     * @return the changPassCount
     */
    public long getChangPassCount() {
        return changPassCount;
    }

    /**
     * @param changPassCount the changPassCount to set
     */
    public void setChangPassCount(long changPassCount) {
        this.changPassCount = changPassCount;
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
     * @return the searchvalue
     */
    public String getSearchvalue() {
        return searchvalue;
    }

    /**
     * @param searchvalue the searchvalue to set
     */
    public void setSearchvalue(String searchvalue) {
        this.searchvalue = searchvalue;
    }

    /**
     * @return the selectedAuthFlag
     */
    public String getSelectedAuthFlag() {
        return selectedAuthFlag;
    }

    /**
     * @param selectedAuthFlag the selectedAuthFlag to set
     */
    public void setSelectedAuthFlag(String selectedAuthFlag) {
        this.selectedAuthFlag = selectedAuthFlag;
    }
}	
