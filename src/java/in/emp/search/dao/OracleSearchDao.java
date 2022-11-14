package in.emp.search.dao;

//-- java imports 
import in.emp.master.bean.MasterBean;
import java.util.LinkedList;

//-- MDA Imports
import in.emp.dao.BaseDao;
import in.emp.search.bean.SearchBean;
import in.emp.search.dao.helper.GetLocationTypesQueryHelper;
import in.emp.search.dao.helper.GetLocationsQueryHelper;
import in.emp.search.dao.helper.GetDeviceTypesQueryHelper;
import in.emp.search.dao.helper.GetSearchTotalRecordsQueryHelper;
import in.emp.search.dao.helper.GetInstallPointTypesQueryHelper;
import in.emp.search.dao.helper.GetDeviceTypeManufacturerQueryHelper;
import in.emp.search.bean.DeviceDataBean;
import in.emp.search.dao.helper.GetDeviceDataQueryHelper;
import in.emp.search.bean.LocationBean;
import in.emp.search.dao.helper.GetProjectQueryHelper;
import in.emp.search.dao.helper.GetTownQueryHelper;
import in.emp.search.dao.helper.GetAdjReasonQueryHelper;
import in.emp.search.dao.helper.GetDLMSDeviceTypeManufacturerQueryHelper;
import in.emp.search.dao.helper.GetReadingMonthsQueryHelper;
import in.emp.search.dao.helper.GetReasonQueryHelper;
import in.emp.search.dao.helper.GetServerQueryHelper;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Data access object for returning compound related value objects from the
 * Oracle database.
 *
 * @author	MDA
 */
public class OracleSearchDao extends BaseDao implements SearchDao {

    //static Logger logger = new Logger(OracleSearchDao.class.getName());
    private static Logger logger = Logger.getLogger(OracleSearchDao.class);

    /*
     * API to get the Location Types from database
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */
    public LinkedList getLocationTypesList() throws Exception {
        LinkedList locationTypeList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getLocationTypesList() :: method called");

            locationTypeList = (LinkedList) getObjectList(new GetLocationTypesQueryHelper());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getLocationTypesList() :: Exception ==" + ex);
            throw ex;
        }
        return locationTypeList;
    }// End of Method

    /*
     * API to get the Location list from the Database
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */
    public LinkedList getLocationsList(LocationBean locationBean) throws Exception {
        LinkedList locationList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getLocationsList() :: method called");
            locationBean.setLocationTypeId(0);
            locationList = (LinkedList) getObjectList(new GetLocationsQueryHelper(locationBean));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getLocationsList() :: Exception ==" + ex);
            throw ex;
        }
        return locationList;

    }// End of Method

    /*
     * API to get the Device Type list from the Database
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */
    public LinkedList getDeviceTypeList() throws Exception {
        LinkedList deviceTypeList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getDeviceTypeList() :: method called");

            deviceTypeList = (LinkedList) getObjectList(new GetDeviceTypesQueryHelper());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getDeviceTypeList() :: Exception ==" + ex);
            throw ex;
        }
        return deviceTypeList;

    }// End of Method

    /*
     * API to get the total Records of  search results for selected search criteria
     * @param SearchBean		the SearchBean
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			SearchBean
     */
    public Object getSearchTotalRecords(SearchBean searchBeanObj) throws Exception {
        SearchBean searchBean = null;
        try {
            logger.log(Level.INFO, "OracleScheduleDao :: getSearchTotalRecords() :: method called");

            searchBean = (SearchBean) getDataObject(new GetSearchTotalRecordsQueryHelper(searchBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleScheduleDao :: getSearchTotalRecords() :: Exception ==" + ex);
            throw ex;
        }
        return searchBean;

    } // End Of Method

    /*
     * API to get the Install point Type list from the Database
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */
    public LinkedList getInstallPointTypeList() throws Exception {
        LinkedList installPointTypeList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getInstallPointTypeList() :: method called");

            installPointTypeList = (LinkedList) getObjectList(new GetInstallPointTypesQueryHelper());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getInstallPointTypeList() :: Exception ==" + ex);
            throw ex;
        }
        return installPointTypeList;

    }// End of Method
	/*
     * API to get the Install point Type list from the Database
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */

    public LinkedList getInstallPointTypeListfeddtc() throws Exception {
        LinkedList installPointTypeList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getInstallPointTypeListfeddtc() :: method called");

            installPointTypeList = (LinkedList) getObjectList(new GetInstallPointTypesQueryHelper());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getInstallPointTypeListfeddtc() :: Exception ==" + ex);
            throw ex;
        }
        return installPointTypeList;

    }// End of Method
	/*
     * API to get the Device Type Manufacturer list from the Database
     * @param isCommDeviceMfg			String
     * @throws Exception						if an error occurs 
     * @returns									LinkedList
     */

    public LinkedList getDeviceTypeManufacturerList(String isCommDeviceMfg) throws Exception {
        LinkedList deviceTypeManufacturerList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getDeviceTypeManufacturerList() :: method called :: ");
            deviceTypeManufacturerList = (LinkedList) getObjectList(new GetDeviceTypeManufacturerQueryHelper(isCommDeviceMfg));
            logger.log(Level.INFO, "OracleSearchDao :: getDeviceTypeManufacturerList() :: list size :: " + deviceTypeManufacturerList.size());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getDeviceTypeManufacturerList() :: Exception :: " + ex);
            throw ex;
        }
        return deviceTypeManufacturerList;

    }// End of Method

    /*
     * API to get the Device Type Manufacturer list from the Database
     * @param isCommDeviceMfg			String
     * @throws Exception						if an error occurs 
     * @returns									LinkedList
     */
    public LinkedList getDLMSDeviceTypeManufacturerList() throws Exception {
        LinkedList deviceTypeManufacturerList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getDeviceTypeManufacturerList() :: method called :: ");
            deviceTypeManufacturerList = (LinkedList) getObjectList(new GetDLMSDeviceTypeManufacturerQueryHelper());
            logger.log(Level.INFO, "OracleSearchDao :: getDeviceTypeManufacturerList() :: list size :: " + deviceTypeManufacturerList.size());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getDeviceTypeManufacturerList() :: Exception :: " + ex);
            throw ex;
        }
        return deviceTypeManufacturerList;

    }// End of Method

    /**
     * API to get the Device data for display
     *
     * @param deviceDataBean the DeviceDataBean
     * @throws Exception	if an error occurs
     * @see	in.mda.search.bean.DeviceDataBean
     * @returns	DeviceDataBean
     */
    public DeviceDataBean getDeviceData(DeviceDataBean deviceDataBean) throws Exception {
        DeviceDataBean deviceDataBeanObj = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getDeviceData() :: method called");
            deviceDataBeanObj = (DeviceDataBean) getDataObject(new GetDeviceDataQueryHelper(deviceDataBean));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getDeviceData() :: Exception :: " + ex);
            throw ex;
        }
        return deviceDataBeanObj;

    } // End of Method

    /*
     * API to get the Location list from the Database
     * @throws Exception		if an error occurs 
     * @see				in.mda.search.SearchDelegate
     * @returns			LinkedList
     */
    public LinkedList getLocations(LocationBean locationBean) throws Exception {
        LinkedList locationList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getLocations() :: method called");

            locationList = (LinkedList) getObjectList(new GetLocationsQueryHelper(locationBean));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getLocations() :: Exception ==" + ex);
            throw ex;
        }
        return locationList;

    }// End of Method

    public LinkedList getReadingMonth(MasterBean masterBeanObj) throws Exception {
        LinkedList locationList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getReadingMonth() :: method called");

            locationList = (LinkedList) getObjectList(new GetReadingMonthsQueryHelper(masterBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getReadingMonth() :: Exception ==" + ex);
            throw ex;
        }
        return locationList;
    }

    public LinkedList getProjectList() throws Exception {
        LinkedList projectList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getProjectList() :: method called");

            projectList = (LinkedList) getObjectList(new GetProjectQueryHelper());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getProjectList() :: Exception ==" + ex);
            throw ex;
        }
        return projectList;
    }

    public LinkedList getReasonList() throws Exception {
        LinkedList reasonList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getReasonList() :: method called");

            reasonList = (LinkedList) getObjectList(new GetReasonQueryHelper());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getReasonList() :: Exception ==" + ex);
            throw ex;
        }
        return reasonList;
    }

    public LinkedList getadjReasonList() throws Exception {
        LinkedList reasonList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getadjReasonList() :: method called");

            reasonList = (LinkedList) getObjectList(new GetAdjReasonQueryHelper());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getadjReasonList() :: Exception ==" + ex);
            throw ex;
        }
        return reasonList;
    }

    public LinkedList getServerList() throws Exception {
        LinkedList serverList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getServerList() :: method called");

            serverList = (LinkedList) getObjectList(new GetServerQueryHelper());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getServerList() :: Exception ==" + ex);
            throw ex;
        }
        return serverList;
    }

    public LinkedList getTownList() throws Exception {
        LinkedList townList = null;
        try {
            logger.log(Level.INFO, "OracleSearchDao :: getTownList() :: method called");

            townList = (LinkedList) getObjectList(new GetTownQueryHelper());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSearchDao :: getTownList() :: Exception ==" + ex);
            throw ex;
        }
        return townList;
    }
}//end class
