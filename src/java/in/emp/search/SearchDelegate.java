package in.emp.search;

//-- Java Imports
import in.emp.search.bean.SearchBean;
import in.emp.search.bean.SearchPrezData;
import in.emp.search.bean.DeviceDataBean;
import in.emp.search.bean.LocationBean;

import java.util.LinkedList;
/**
 * Interface for SearchDelegate
 *
 * @author   MDA
 */
public interface SearchDelegate 
{

	/*
	* API to get the search data for populating Drop downs
	* @throws Exception		if an error occurs 
	* @see				in.mda.search.SearchDelegate
	* @returns			SearchPrezData
	*/
	public SearchPrezData getSearchData(SearchBean SearchBeanObj)throws Exception;

		/*
	* API to get the Total Number of Records on selected criteria
	* @param SearchBeanObj		the SearchBean
	* @throws Exception		if an error occurs 
	* @see				in.mda.search.SearchDelegate
	* @returns			 SearchBean
	*/
	public SearchBean getSearchTotalRecords(SearchBean SearchBeanObj) throws Exception;

	  /**
	* API to display Device item data 
	* @param DeviceDataBean	
	* @throws Exception	if an error occurs
	* @returns		DeviceDataBean		
	*/
	public DeviceDataBean displayDeviceData(DeviceDataBean deviceDataBean) throws Exception;

	/*
	* public api to get the locationList based on the LocationBean Passed
	*@param Locationbean
	*@returns LinkedList
	*@throws Exception
	*/
	public LinkedList getLocations(LocationBean locationBean)throws Exception;
}//-- End class