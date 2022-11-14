package in.emp.search.dao;

//-- java imports 
import in.emp.master.bean.MasterBean;
import java.util.LinkedList;

//-- MDA imports
import in.emp.search.bean.SearchBean;
import in.emp.search.bean.DeviceDataBean;
import in.emp.search.bean.LocationBean;

/**
 * Data access object interface for returning Search related value objects from
 * the database.
 *
 * @author	MDA
 */
public interface SearchDao {

    /*
     * API to get the Location Type lIst from the data base
     *	* @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */
    public LinkedList getLocationTypesList() throws Exception;

    /*
     * API to get the location list from the database
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */
    public LinkedList getLocationsList(LocationBean locationBean) throws Exception;

    /*
     * API to get the Device Type list from the database
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */
    public LinkedList getDeviceTypeList() throws Exception;

    /*
     * API to get the Project list from the database
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */
    public LinkedList getProjectList() throws Exception;

    public LinkedList getReasonList() throws Exception;

    public LinkedList getadjReasonList() throws Exception;

    public LinkedList getServerList() throws Exception;
    /*
     * API to get the Town list from the database
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */

    public LinkedList getTownList() throws Exception;

    /*
     * API to get the total number of records for search results for selected search criteria
     * @param SearchBean		the SearchBean
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			Object
     */
    public Object getSearchTotalRecords(SearchBean searchBeanObj) throws Exception;

    /*
     * API to get the Install POint Type list from the database
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */
    public LinkedList getInstallPointTypeList() throws Exception;

    /*
     * API to get the Install POint Type list from the database
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */
    public LinkedList getInstallPointTypeListfeddtc() throws Exception;

    /*
     * API to get the Device Type Manufacturer list from the Database
     * @param isCommDeviceMfg			String
     * @throws Exception						if an error occurs 
     * @returns									LinkedList
     */
    public LinkedList getDeviceTypeManufacturerList(String isCommDeviceMfg) throws Exception;

    /*
     * API to get the Device Type Manufacturer list from the Database
     * @param isCommDeviceMfg			String
     * @throws Exception						if an error occurs 
     * @returns									LinkedList
     */
    public LinkedList getDLMSDeviceTypeManufacturerList() throws Exception;

    /**
     * API to get the Device data for display
     *
     * @param deviceDataBean the DeviceDataBean
     * @throws Exception	if an error occurs
     * @see	in.mda.search.bean.DeviceDataBean
     * @returns	DeviceDataBean
     */
    public DeviceDataBean getDeviceData(DeviceDataBean deviceDataBean) throws Exception;

    /*
     * API to get the Location list from the Database
     * @throws Exception		if an error occurs 
     * @returns			LinkedList
     */
    public LinkedList getLocations(LocationBean locationBean) throws Exception;

    public LinkedList getReadingMonth(MasterBean masterBeanObj) throws Exception;

}//end class
