package in.emp.search.bean;

import java.util.LinkedList;
import in.emp.search.bean.LocationBean;

public class SearchPrezData implements java.io.Serializable
{
	LinkedList locationList;
	LinkedList locationTypeList;
	LinkedList searchResultList;
	LinkedList deviceTypeList;
        LinkedList projectList;
        LinkedList townList;
	LinkedList installPointTypeList;
	SearchBean searchBeanObj = null;
	LinkedList defaultLocList;
        private LinkedList resultCodeList;
        private LinkedList groupList;

    public LinkedList getProjectList() {
        return projectList;
    }

    public void setProjectList(LinkedList projectList) {
        this.projectList = projectList;
    }

    public LinkedList getTownList() {
        return townList;
    }

    public void setTownList(LinkedList townList) {
        this.townList = townList;
    }
        


	/*Public constructor*/
	public SearchPrezData()
	{

	} // End of constructor

	// Setter and Getter method
	
	public void setLocationList(LinkedList loc_list)
	{
		locationList = loc_list;
	}

	public LinkedList getLocationList()
	{
		return locationList;
	}

	public void setLocationTypeList(LinkedList loc_type_list)
	{
		locationTypeList = loc_type_list;
	}

	public LinkedList getLocationTypeList()
	{
		return locationTypeList;
	}

	public void setSearchResultList(LinkedList search_result_list)
	{
		searchResultList = search_result_list;
	}

	public LinkedList getSearchResultList()
	{
		return searchResultList;
	}

	public void setSearchBean(SearchBean searchBean)
	{
		searchBeanObj = searchBean;
	}

	public SearchBean getSearchBean()
	{
		return searchBeanObj;
	}

	public void setdeviceTypeList(LinkedList device_type_list)
	{
		deviceTypeList = device_type_list;
	}

	public LinkedList getdeviceTypeList()
	{
		return deviceTypeList;
	}

	public void setInstallPointTypeList(LinkedList install_Point_Type_List)
	{
		installPointTypeList = install_Point_Type_List;
	}

	public LinkedList getInstallPointTypeList()
	{
		return installPointTypeList;
	}

	public void setDefaultLocList(LinkedList default_Loc_List)
	{
		defaultLocList = default_Loc_List;
	}

	public LinkedList getDefaultLocList()
	{
		return defaultLocList;
	}

    /**
     * @return the resultCodeList
     */
    public LinkedList getResultCodeList() {
        return resultCodeList;
    }

    /**
     * @param resultCodeList the resultCodeList to set
     */
    public void setResultCodeList(LinkedList resultCodeList) {
        this.resultCodeList = resultCodeList;
    }

    /**
     * @return the groupList
     */
    public LinkedList getGroupList() {
        return groupList;
    }

    /**
     * @param groupList the groupList to set
     */
    public void setGroupList(LinkedList groupList) {
        this.groupList = groupList;
    }

} //End of Class