package in.emp.search.manager;

//-- Java Imports
import java.util.LinkedList;

//-- MDA imports
import in.emp.search.SearchDelegate;
import in.emp.search.dao.SearchDao;
import in.emp.search.dao.OracleSearchDao;
import in.emp.search.bean.SearchBean;
import in.emp.search.bean.SearchPrezData;
import in.emp.search.bean.DeviceDataBean;
import in.emp.search.bean.LocationBean;
import in.emp.common.ApplicationConstants;
import in.emp.master.dao.MasterDao;
import in.emp.master.dao.OracleMasterDao;
import in.emp.security.bean.SecUserBean;

//-- logger Imports
import java.io.File;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Manager for accessing and manipulating security related objects.
 *
 * @author MDA
 */
public class SearchManager implements SearchDelegate {

    private static String CLASS_NAME = SearchManager.class.getName();
    //private static Logger logger = new Logger(CLASS_NAME);
    private static Logger logger = Logger.getLogger(SearchManager.class);

    /**
     * public Constructor of class SearchManager
     *
     */
    public SearchManager() {
    }//SearchManager() ends

    /*
     * API to get the search Data for Populating the Drop Downs
     * @throws Exception		if an error occurs
     * @see				in.mda.search.SearchDelegate
     * @returns			SearchPrezData
     */
    public SearchPrezData getSearchData(SearchBean searchBeanObj) throws Exception {
        SearchPrezData searchPrezDataObj = null;

        try {
            logger.log(Level.INFO, " SearchManager :: getSearchData() :: method called");

            //-- Calling populateAllDropDowns method--
            searchPrezDataObj = populateAllDropDowns(searchBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " SearchManager :: getSearchData() :: exception == " + ex);
            throw ex;
        }
        return searchPrezDataObj;

    } // End Of Method

    /* private API to get the list of all the data from database required for Drop Downs
     * @ throws Exception
     *@returns SearchPrezData Object
     */
    private SearchPrezData populateAllDropDowns(SearchBean searchBeanObj) throws Exception {
        LinkedList locationTypeList = null;
        LinkedList locationList = null;
        LinkedList deviceTypeList = null;
        LinkedList installPointTypeList = null;
        LinkedList projectList = null;
        LinkedList townList = null;
        SearchDao searchDaoObj = null;
        MasterDao masterDao = null;
        SearchPrezData searchPrezDataObj = new SearchPrezData();
        LocationBean locationBeanObj = new LocationBean();
        LinkedList defaultTypeLocList = null;
        LinkedList resultCodeList = null;
//        ScheduleRecBean scheduleRecBean= new ScheduleRecBean();

        try {
            logger.log(Level.INFO, " SearchManager :: populateAllDropDowns() :: method called");

            // setting location type for the first dropdown
            locationBeanObj.setLocationTypeId(ApplicationConstants.DEFAULT_LOCATION_TYPE);
            locationBeanObj.setAccessLocation(searchBeanObj.getAccessLocation());

            //-- create the search dao object to get serach results.
            searchDaoObj = new OracleSearchDao();
            masterDao = new OracleMasterDao();
         //  scheduleRecBean.setLocationQuery(searchBeanObj.getAccessLocation());

            //-- call oracle search dao

            deviceTypeList = searchDaoObj.getDeviceTypeList();
            projectList = searchDaoObj.getProjectList();
            townList = searchDaoObj.getTownList();
            installPointTypeList = searchDaoObj.getInstallPointTypeList();
            defaultTypeLocList = searchDaoObj.getLocations(locationBeanObj);
            searchPrezDataObj.setDefaultLocList(defaultTypeLocList);
            locationTypeList = searchDaoObj.getLocationTypesList();
            locationList = searchDaoObj.getLocationsList(locationBeanObj);
            resultCodeList = masterDao.getMasterList("RESULT_CODE_MASTER", "RESULT_CODE_ID", "RESULT_CODE", "RESULT_CODE_DESCRIPTION", " RESULT_CODE <> '%'");
         //   scheduleRecBean=getGroupList(scheduleRecBean);
            // Setting in the SearchPrezData bean--
            searchPrezDataObj.setLocationTypeList(locationTypeList);
            searchPrezDataObj.setLocationList(locationList);
            searchPrezDataObj.setdeviceTypeList(deviceTypeList);
            searchPrezDataObj.setProjectList(projectList);
            searchPrezDataObj.setTownList(townList);
            searchPrezDataObj.setInstallPointTypeList(installPointTypeList);
            searchPrezDataObj.setResultCodeList(resultCodeList);
          //  searchPrezDataObj.setGroupList(scheduleRecBean.getGroupList());

        } catch (Exception ex) {
            logger.log(Level.ERROR, " SearchManager :: populateAllDropDowns() :: exception == " + ex);
            throw ex;
        }
        return searchPrezDataObj;
    } // End Of Method

    /* oublic API to get the number of total records
     * @ throws Exception
     *@returns SearchBean Object
     */
    public SearchBean getSearchTotalRecords(SearchBean searchBeanObj) throws Exception {
        SearchDao searchDaoObj = null;
        try {
            logger.log(Level.INFO, " SearchManager :: getSearchTotalRecords() :: method called");

            //-- create the dao object to get serach results.
            searchDaoObj = new OracleSearchDao();

            //-- call dao
            searchBeanObj = (SearchBean) searchDaoObj.getSearchTotalRecords(searchBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " SearchManager :: getSearchTotalRecords() :: exception == " + ex);
            throw ex;
        }
        return searchBeanObj;
    }  // End of Method

    /**
     * API to display Device item data
     * @param DeviceDataBean
     * @throws Exception	if an error occurs
     * @returns		DeviceDataBean
     */
    public DeviceDataBean displayDeviceData(DeviceDataBean deviceDataBean) throws Exception {
        DeviceDataBean deviceDataBeanObj = null;
        SearchDao searchDaoObj = null;
        String htmlString = null;
        boolean isEdit = false;

        try {
            logger.log(Level.INFO, " SearchManager :: displayDeviceData() :: method called");

            // calling private api to get data
            searchDaoObj = new OracleSearchDao();
            deviceDataBeanObj = searchDaoObj.getDeviceData(deviceDataBean);

            // getting Html String for the xml file
            System.out.println("deviceDataBeanObj.getXmlFileName()::" + deviceDataBeanObj.getXmlFileName());
            File f = new File(deviceDataBeanObj.getXmlFilePath());
            String fileName = deviceDataBeanObj.getXmlFileName();
            String filePath = "";
            if (!f.exists()) {
                filePath = ApplicationConstants.COMMON_COMMAND_CONFIG_FILE_PATH + ApplicationConstants.FORWARD_SLASH
                        + ApplicationConstants.DEVICEID_FOLDER_NAME + deviceDataBeanObj.getDeviceId() + ApplicationConstants.FORWARD_SLASH
                        + ApplicationConstants.XML_FOLDER_NAME + ApplicationConstants.FORWARD_SLASH + ApplicationConstants.ARCHIVE_FOLDER_NAME
                        + ApplicationConstants.FORWARD_SLASH + fileName;
                f = new File(filePath);
                if (!f.exists()) {
                    filePath = ApplicationConstants.COMMON_COMMAND_CONFIG_FILE_PATH + ApplicationConstants.FORWARD_SLASH
                            + ApplicationConstants.DEVICEID_FOLDER_NAME + deviceDataBeanObj.getDeviceId() + ApplicationConstants.FORWARD_SLASH
                            + ApplicationConstants.XML_FOLDER_NAME + ApplicationConstants.FORWARD_SLASH + ApplicationConstants.FAILED_FOLDER_NAME
                            + ApplicationConstants.FORWARD_SLASH + fileName;
                    f = new File(filePath);
                    if (!f.exists()) {
                        filePath = ApplicationConstants.COMMON_COMMAND_CONFIG_FILE_PATH + ApplicationConstants.FORWARD_SLASH
                                + ApplicationConstants.DEVICEID_FOLDER_NAME + deviceDataBeanObj.getDeviceId() + ApplicationConstants.FORWARD_SLASH
                                + ApplicationConstants.XML_FOLDER_NAME + ApplicationConstants.FORWARD_SLASH + fileName;
                        f = new File(filePath);
                    }
                }
                deviceDataBeanObj.setXmlFilePath(filePath);
            }
            //htmlString = XmlUtils.getHtmlFromXML(deviceDataBeanObj.getXmlFilePath(), deviceDataBean.getXslFilePath(), isEdit);

            //setting Html String in bean object
            deviceDataBeanObj.setHtmlString(htmlString);
            deviceDataBeanObj.setXmlEditMode(false);
            deviceDataBeanObj.setUiPageType("search");
            deviceDataBeanObj.setOperationType(deviceDataBean.getOperationType());

        } catch (Exception ex) {
            logger.log(Level.ERROR, " SearchManager :: displayDeviceData() :: Exception :: " + ex);
            throw ex;
        }
        return deviceDataBeanObj;
    } // End Of Method

    /*
     * public api to get the locationList based on the LocationBean Passed
     *@param Locationbean
     *@returns LinkedList
     *@throws Exception
     */
    public LinkedList getLocations(LocationBean locationBean) throws Exception {
        LinkedList locationList = null;
        SearchDao searchDaoObj = null;
        SecUserBean secUserBean = null;
        try {
            logger.log(Level.INFO, " SearchManager :: getLocations() :: method called");


            //-- create the dao object to get serach results.
            searchDaoObj = new OracleSearchDao();

            //-- call dao
            locationList = searchDaoObj.getLocations(locationBean);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " SearchManager :: getLocations() :: exception == " + ex);
            throw ex;
        }
        return locationList;

    } // End of method
}//-- End class
