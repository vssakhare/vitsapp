package in.emp.security.bean;

// imports
import java.lang.*;
import java.sql.*;
import java.util.HashMap;

import in.emp.common.UserAuditBean;
import java.util.LinkedList;

/* Public class to set and get the data from the result set  */

public class SecUserBean extends UserAuditBean implements java.io.Serializable
{
		long user_id;
		String login_id;
		String password;
		//String last_name;
		private String empName;
		String email_address;
		String initials;
		String status_cd;
		HashMap function_Map;
                private LinkedList accessLocationList;
                private String accessLocationString;
                private LinkedList defaultLocList;
                private String accessLocationListString;

    public LinkedList getDefaultLocList() {
        return defaultLocList;
    }

    public void setDefaultLocList(LinkedList defaultLocList) {
        this.defaultLocList = defaultLocList;
    }
		
	/*public constructor*/
	
	public SecUserBean()
	{

	} /*End of constructor*/

	/* Getters and Setter Methods*/
	
	public void setUserId(long userId)
	{
		user_id = userId;
	}
	public long getUserId()
	{
		return user_id;
	}

	public void setLoginId(String loginId)
	{
		login_id = loginId;
	}
	public String getLoginId()
	{
		return login_id;
	}

	public void setPassword(String pword)
	{
		password = pword;
	}
	public String getPassword()
	{
		return password;
	}

	/*public void setLastName(String lname )
	{
		last_name = lname;
	}
	public String getLastName()
	{
		return last_name;
	}*/

	/*public void setFirstName(String fname)
	{
		first_name = fname;
	}
	public String getFirstName()
	{
		return first_name;
	}*/
	
	public void setEmployeeName(String fname)
	{
		empName = fname;
	}
	public String getEmployeeName()
	{
		return empName;
	}

        public void setEmailAddress(String email)
	{
		email_address = email;
	}
	public String getEmailAddress()
	{
		return email_address;
	}
	
	public void setInitials(String init)
	{
		initials = init;
	}
	public String getInitials()
	{
		return initials;
	}
	
	public void setStatusCd(String status)
	{
		status_cd = status;
	}
	public String getStatusCd()
	{
		return status_cd;
	}
	public void setUserFunctionMap(HashMap functionMap)
	{
		function_Map = functionMap;
	}
	public HashMap getUserFunctionMap()
	{
		return function_Map;
	}

        /**
         * @return the accessLocation
         */
        public LinkedList getAccessLocationList() {
                return accessLocationList;
        }

        /**
         * @param accessLocation the accessLocation to set
         */
        public void setAccessLocationList(LinkedList accessLocationList) {
                this.accessLocationList = accessLocationList;
        }

        /**
         * @return the accessLocationString
         */
        public String getAccessLocationString() {
                return accessLocationString;
        }

        /**
         * @param accessLocationString the accessLocationString to set
         */
        public void setAccessLocationString(String accessLocationString) {
                this.accessLocationString = accessLocationString;
        }

    /**
     * @return the accessLocationListString
     */
    public String getAccessLocationListString() {
        return accessLocationListString;
    }

    /**
     * @param accessLocationListString the accessLocationListString to set
     */
    public void setAccessLocationListString(String accessLocationListString) {
        this.accessLocationListString = accessLocationListString;
    }


} /* End Of Class */

