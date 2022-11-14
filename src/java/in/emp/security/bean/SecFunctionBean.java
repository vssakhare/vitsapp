package in.emp.security.bean;

// imports
import java.lang.*;
import java.sql.*;

import in.emp.common.UserAuditBean;

/* Public class to set and get the data from the result set  */

public class SecFunctionBean extends UserAuditBean implements java.io.Serializable
{
	long function_Id;
	String function_Name;
	String uiAction_Name;
	String status_cd;
	
		
	/*public constructor*/
	
	public SecFunctionBean()
	{

	} /*End of constructor*/

	/* Getters and Setter Methods*/
	
	public void setFunctionId(long functionId)
	{
		function_Id = functionId;
	}
	public long getFunctionId()
	{
		return function_Id;
	}

	public void setFunctionName(String functionName)
	{
		function_Name = functionName;
	}
	public String getFunctionName()
	{
		return function_Name;
	}

	public void setUiActionName(String uiActionName)
	{
		uiAction_Name = uiActionName;
	}
	public String getUiActionName()
	{
		return uiAction_Name;
	}
	
	public void setStatusCd(String status)
	{
		status_cd = status;
	}
	public String getStatusCd()
	{
		return status_cd;
	}

} /* End Of Class */

