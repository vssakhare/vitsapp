package in.emp.search.bean;

// imports
import java.sql.*;
import in.emp.common.UserAuditBean;

public class InstallPointTypeBean extends UserAuditBean implements java.io.Serializable
{

	int installPointTypeId;
	String installPointTypeCd;

// public constructor 

	public InstallPointTypeBean()
	{
	} // End of constructor

	public void setInstallPointTypeId(int install_Point_Type_Id)
	{
		installPointTypeId = install_Point_Type_Id;
	}
	public int getInstallPointTypeId()
	{
		return installPointTypeId;
	}

	public void setInstallPointTypeCd(String install_Point_Type_Cd)
	{
		installPointTypeCd = install_Point_Type_Cd;
	}
	public String getInstallPointTypeCd()
	{
		return installPointTypeCd;
	}

} // End of Class