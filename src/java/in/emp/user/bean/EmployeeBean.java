package in.emp.user.bean;

// MDA packages
import in.emp.common.UserAuditBean;

// java imports

import java.io.*;
import java.lang.*;
import java.sql.*;


public class EmployeeBean  extends UserAuditBean implements java.io.Serializable
{
		
	private long employeeId;
	private long locationId;
	private long organizationId;
	private double totalRecords;
	private String extEmpRefNo;
	private String empName;
	//private String empLastName;
	private String empDesignation;
	private String empEmailAddress;
	private String empLocation;
	private String empOrganization;
			
	private int minValue;
	private int maxValue;
	private String sortColTableName;
	private String sortColumnName;
	private String sortOrder;
	private String lastRowValue;
	private String lastValueDataType;

	
	public void setEmployeeId(long employeeId)
	{
		this.employeeId = employeeId;
	}
	public long getEmployeeId()
	{
		return this.employeeId;
	}
	
	public void setLocationId(long locationId)
	{
		this.locationId = locationId;
	}
	public long  getLocationId()
	{
		return this.locationId;
	}

	public void setOrganizationId(long organizationId)
	{
		this.organizationId = organizationId;
	}
	public long  getOrganizationId()
	{
		return this.organizationId;
	}
	
	public void setTotalRecords(double totalRecords)
	{
		this.totalRecords = totalRecords;
	}
	public double getTotalRecords()
	{
		return this.totalRecords;
	}	

	public void setExtEmpRefNo(String extEmpRefNo)
	{
		this.extEmpRefNo = extEmpRefNo;
	}
	public String getExtEmpRefNo()
	{
		return this.extEmpRefNo;
	}

/*	public void setEmpFirstName(String empFirstName)
	{
		this.empFirstName = empFirstName;
	}
	public String getEmpFirstName()
	{
		return this.empFirstName;
	}

	public void setEmpLastName(String empLastName)
	{
		this.empLastName = empLastName;
	}
	public String getEmpLastName()
	{
		return this.empLastName;
	}*/

	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	public String getEmpName()
	{
		return this.empName;
	}


	public void setEmpDesignation(String empDesignation)
	{
		this.empDesignation = empDesignation;
	}
	public String getEmpDesignation()
	{
		return this.empDesignation;
	}

	public void setEmpEmailAddress(String empEmailAddress)
	{
		this.empEmailAddress = empEmailAddress;
	}
	public String getEmpEmailAddress()
	{
		return this.empEmailAddress;
	}

	public void setEmpLocation(String empLocation)
	{
		this.empLocation = empLocation;
	}
	public String getEmpLocation()
	{
		return this.empLocation;
	}

	public void setEmpOrganization(String empOrganization)
	{
		this.empOrganization = empOrganization;
	}
	public String getEmpOrganization()
	{
		return this.empOrganization;
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
}	
