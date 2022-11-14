package in.emp.handler;

//-- MDA Imports
import in.emp.arch.GenericFormHandler;
import in.emp.util.ApplicationUtils;
import in.emp.common.ApplicationConstants;
import in.emp.search.bean.SearchBean;
import in.emp.search.SearchDelegate;
import in.emp.search.manager.SearchManager;
import in.emp.search.bean.SearchPrezData;
import in.emp.security.bean.SecUserBean;
import in.emp.util.PagingManager;
import in.emp.search.bean.DeviceDataBean;
//import in.mda.util.XmlUtils;

// Java imports--
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.text.*;
import java.sql.Date;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Handler class for security
 */
public class SearchHandler implements GenericFormHandler {
    //private static Logger logger = new Logger(SearchHandler.class.getName());

    private static String CLASS_NAME = SearchHandler.class.getName();
    private static Logger logger = Logger.getLogger(SearchHandler.class);

    /**
     * API to process a user request
     *
     * @param request object of HttpServletRequest
     * @return String the UI action name
     * @throws Exception
     */
    public String execute(HttpServletRequest request) throws Exception {
        String uiActionName = "";
        String sReturnPage = "";

        try {
            logger.log(Level.INFO, "SearchHandler :: execute() :: method called");

            uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME);
            String subAction = request.getParameter("subAction");

            if (uiActionName == null) {
                uiActionName = (String) request.getAttribute(ApplicationConstants.UIACTION_NAME);
            }


            if (!ApplicationUtils.isBlank(uiActionName)) {
                if (uiActionName.equals(ApplicationConstants.UIACTION_SEARCH_GET)) {
                    sReturnPage = getSearchData(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_SEARCH_POST)) {


                    if (!ApplicationUtils.isBlank(subAction) && subAction.equals("paging")) {
                        setSortingInfo(request);
                        PagingManager.continuePaging(request, CLASS_NAME);
                        sReturnPage = ApplicationConstants.UIACTION_SEARCH_GET;
                    } else if (!ApplicationUtils.isBlank(subAction) && subAction.equals("sort")) {
                        sReturnPage = getSearchResults(request);
                    } else {
                        sReturnPage = getSearchResults(request);
                    }

                    /*
                     else if (subAction.equals("schedule"))
                     {
                     sReturnPage = schedule(request);
                     }
                     else if (subAction.equals("autoschedule"))
                     {
                     sReturnPage = schedule(request);
                     }
                     else if (subAction.equals("autoscheduleall"))
                     {
                     sReturnPage = schedule(request);
                     }*/

                } else if (uiActionName.equals(ApplicationConstants.UIACTION_DISPLAY_DEVICE_DATA)) {
                    sReturnPage = displayDeviceData(request);
                } else if (uiActionName.equals(ApplicationConstants.SCHEDULE_WISE_DEVICE_LOG)) {
                    sReturnPage = displayScheduleWiseLog(request);
                }
            } else {
                sReturnPage = ApplicationConstants.UIACTION_SEARCH_GET;
            }

            logger.log(Level.INFO, "SearchHandler :: execute() :: sReturnPage == " + sReturnPage);

            //System.out.println("SearchHandler :: sReturnPage :: " + sReturnPage);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SearchHandler :: execute() :: Exception :: " + ex);
            sReturnPage = ApplicationConstants.UIACTION_SEARCH_GET;
        }

        return sReturnPage;
    }//end of method

    /*
     * Private api to get the reqired data for load the serach page/
     */
    private String getSearchData(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_SEARCH_GET;
        SearchDelegate searchDelegateObject = null;
        SearchPrezData searchPrezData = null;
        SearchBean searchBeanObj = null;
        HttpSession searchSession = request.getSession();
        //SecUserBean secUserBean = null;
        String office_cd = "";
        String office_type_id = "";
        String office_loc_id = "";
        HttpSession session = request.getSession(true);
        try {
            logger.log(Level.INFO, "SearchHandler :: getSearchData() :: method called");
            searchBeanObj = new SearchBean();
            //secUserBean = (SecUserBean) request.getSession().getAttribute(ApplicationConstants.USER_SESSION_NAME);
            //searchBeanObj.setAccessLocation(secUserBean.getAccessLocationListString());

            office_cd = (String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION);
            office_type_id = (String) session.getAttribute(ApplicationConstants.OFFICE_TYPE_SESSION);
            office_loc_id = (String) session.getAttribute(ApplicationConstants.OFFICE_LOCATION_ID_SESSION);

            searchBeanObj.setAccessLocation(office_loc_id);

            //== create a delegate object to get the serach results for seletced search criteria
            searchDelegateObject = new SearchManager();

            //System.out.println("SearchHandler :: calling manager :: sReturnPage :: " + sReturnPage);

            //-- Calling Manager--
            searchPrezData = searchDelegateObject.getSearchData(searchBeanObj);

            // -- Setting SearchPrezData in session
            searchSession.setAttribute("searchPrezData", searchPrezData);

            sReturnPage = ApplicationConstants.UIACTION_SEARCH_GET;
            logger.log(Level.INFO, "SearchHandler :: getSearchData() :: sReturnPage :: " + sReturnPage);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SearchHandler :: getSearchData() :: Exception :: " + ex);
            request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_SEARCH_DETAILS_FAILURE);
            request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE_EX, ex);
            sReturnPage = ApplicationConstants.UIACTION_SEARCH_GET;
        }

        return sReturnPage;
    }

    /*
     *  Private api to get the search results from selected criteria.
     */
    private String getSearchResults(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_SEARCH_GET;
        SearchDelegate searchMgrObject = null;
        SearchBean searchBeanObj = null;
        LinkedList searchResultList = null;
        SearchPrezData searchPrezData = null;
        HttpSession searchSession = request.getSession();
        SecUserBean secUserBean = null;
        String LocationQuery = "";
        String lockCode = "";
        String office_cd = "";
        String office_type_id = "";
        String office_loc_id = "";
        HttpSession session = request.getSession(true);

        try {
            logger.log(Level.INFO, "SearchHandler :: getSearchResults() :: method called");

            String subAction = request.getParameter("subAction");

            //-- get all selected data from request and set into SearchBean object
            if (ApplicationUtils.isBlank(subAction)) {
                searchBeanObj = featchDataFromRequest(request);
            } else {
                searchPrezData = (SearchPrezData) searchSession.getAttribute("searchPrezData");
                searchBeanObj = searchPrezData.getSearchBean();
                setSortingInfo(request);
                searchBeanObj.setSortColumnName((String) request.getAttribute("columnName"));
                searchBeanObj.setSortOrder((String) request.getAttribute("sortOrder"));
                //searchBeanObj.setSortColTableName((String)request.getAttribute("sortColTableName"));
                searchBeanObj.setMin(0);
                searchBeanObj.setMax(Integer.parseInt(System.getProperty("CACHE_PAGE_SIZE")) * Integer.parseInt(System.getProperty("UI_PAGE_SIZE")));
                searchBeanObj.setLastRowValue("");
                searchBeanObj.setLastValueDataType("");
            }
            // secUserBean = (SecUserBean) request.getSession().getAttribute(ApplicationConstants.USER_SESSION_NAME);
            //searchBeanObj.setAccessLocation(secUserBean.getAccessLocationString());
            office_cd = (String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION);
            office_type_id = (String) session.getAttribute(ApplicationConstants.OFFICE_TYPE_SESSION);
            office_loc_id = (String) session.getAttribute(ApplicationConstants.OFFICE_LOCATION_ID_SESSION);

            searchBeanObj.setAccessLocation(office_loc_id);

            if (searchBeanObj.getSubDivisionId() > 0) {
                lockCode = searchBeanObj.getSubDivisionId() + "";
            } else if (searchBeanObj.getDivisionId() > 0) {
                lockCode = searchBeanObj.getDivisionId() + "";
            } else if (searchBeanObj.getCircleId() > 0) {
                lockCode = searchBeanObj.getCircleId() + "";
            } else if (searchBeanObj.getZoneId() == 0) {
                lockCode = searchBeanObj.getAccessLocation();
            } else if (searchBeanObj.getZoneId() > 0) {
                lockCode = searchBeanObj.getZoneId() + "";
            }
            LocationQuery = (" select location_id from (select lm.location_id from location lm start with location_id in (" + lockCode + ") connect by prior location_id=Parent_location_id) ");

            //  searchBeanObj.setSchDetId(new Long((String)request.getParameter("sch_det_id")));
            //  System.out.println("sch_det_id:::"+searchBeanObj.getSchDetId());
            searchBeanObj.setLocationQuery(LocationQuery);
            searchMgrObject = new SearchManager();

            //-- Calling Manager--
//            searchPrezData = searchMgrObject.getSearchResults(searchBeanObj); // Change here later // Prajakta

            //-- setting SearchPrezData object in session
            if (ApplicationUtils.isBlank(subAction)) {
                searchSession.setAttribute("searchPrezData", searchPrezData);
            }

            // -- calling Page Manager--
//			PagingManager.startPaging(request, (searchPrezData.getSearchResultList()), true, CLASS_NAME);
            PagingManager.startPaging(request, (searchPrezData.getSearchResultList()), true, CLASS_NAME, ((searchPrezData.getSearchBean()).getTotalRecords()));

            /*	if(request.getParameter("lastRow") != null)
             {
             PagingManager.startPaging(request, searchResultList, false, CLASS_NAME);
             }
             else
             {
             PagingManager.startPaging(request, searchResultList, true, CLASS_NAME);
             }*/

            //---setting success message if xml file was succecssfully updated
		/*	if (request.getParameter("isFileUpdated") != null && request.getParameter("isFileUpdated").equals("updateSuccess"))
             request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_WF_UPDATE_WORKITEM_SUCCESS);
             */
            sReturnPage = ApplicationConstants.UIACTION_SEARCH_GET;
            //System.out.println("SearchHandler :: getSearchResults :: sReturnPage :: " + sReturnPage);
            logger.log(Level.INFO, "SearchHandler :: getSearchResults() :: sReturnPage :: " + sReturnPage);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SearchHandler :: getSearchResults() :: Exception :: " + ex);

            request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_SEARCH_RESULTS_FAILURE);
            request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE_EX, ex);

            sReturnPage = ApplicationConstants.UIACTION_SEARCH_GET;
            //ex.printStackTrace();
        }

        return sReturnPage;
    }

    /* public API to set the sorting information
     @param HttpServletRequest request
     @Return SearchBean Object*/
    public static void setSortingInfo(HttpServletRequest request) {
        try {
            String sortColumn = null;
            String sortOrder = null;
            String subAction = request.getParameter("subAction");

            if (!ApplicationUtils.isBlank(subAction)) {
                sortColumn = request.getParameter("columnName");
                sortOrder = request.getParameter("sortOrder");
            }

            if (ApplicationUtils.isBlank(sortColumn)) {
                sortColumn = ApplicationConstants.SEARCH_DEFAULT_SORT_COLUMN;
            }
            if (ApplicationUtils.isBlank(sortOrder)) {
                sortOrder = ApplicationConstants.DEFAULT_SORT_ORDER_DESC;
            }

            //String tableName[] = sortColumn.split("\\.");

            request.setAttribute("columnName", sortColumn);
            request.setAttribute("sortOrder", sortOrder);
            //request.setAttribute("sortColTableName",tableName[0]);

        } catch (Exception e) {
            logger.log(Level.ERROR, "SearchHandler :: getSortingInfo() :: Exception  :: " + e);
        }

    } // End oF Method

    /*
     *  Private api to get the data from request
     @param HttpServletRequest request
     @Returns SearchBean Object
     @throws Exception
     */
    private SearchBean featchDataFromRequest(HttpServletRequest request) throws Exception {
        SearchBean searchBeanObj = new SearchBean();
        SecUserBean secUserBean = null;
        try {
            logger.log(Level.INFO, "SearchHandler :: featchDataFromRequest() :: method called");
            secUserBean = (SecUserBean) request.getSession().getAttribute(ApplicationConstants.USER_SESSION_NAME);


            //-- Setting the sorting order and sorting column in request--
            setSortingInfo(request);

            //-- get data from request and set into SearchBean data object
            searchBeanObj.setDeviceType(request.getParameter("devicetype"));

            if (request.getParameter("project") != null && request.getParameter("project") != "") {
                searchBeanObj.setProjectId(new Long((String) request.getParameter("project")).longValue());
            }

            if (request.getParameter("town") != null && request.getParameter("town") != "") {
                searchBeanObj.setTownId(new Long((String) request.getParameter("town")).longValue());
            }

            if (request.getParameter("zone") != null && request.getParameter("zone") != "") {
                if (request.getParameter("zone").equals("-1")) {
                    searchBeanObj.setZoneId(0);
                } else {
                    searchBeanObj.setZoneId(new Long((String) request.getParameter("zone")).longValue());
                }
            }

            if (request.getParameter("circle") != null && request.getParameter("circle") != "") {
                searchBeanObj.setCircleId(new Long((String) request.getParameter("circle")).longValue());
            }

            if (request.getParameter("division") != null && request.getParameter("division") != "") {
                searchBeanObj.setDivisionId(new Long((String) request.getParameter("division")).longValue());
            }

            if (request.getParameter("subDivision") != null && request.getParameter("subDivision") != "") {
                searchBeanObj.setSubDivisionId(new Long((String) request.getParameter("subDivision")).longValue());
            }

            if (request.getParameter("groupresult") != null && !ApplicationUtils.isBlank(request.getParameter("groupresult"))) {
                searchBeanObj.setGroupValue(Long.parseLong(request.getParameter("groupresult")));
            }

            searchBeanObj.setSchedulePeriod(request.getParameter("scheduleperiod"));
            searchBeanObj.setSortColumnName((String) request.getAttribute("columnName"));
            searchBeanObj.setSortOrder((String) request.getAttribute("sortOrder"));
            //searchBeanObj.setSortColTableName((String)request.getAttribute("sortColTableName"));
            searchBeanObj.setMin(0);
            searchBeanObj.setMax(Integer.parseInt(System.getProperty("CACHE_PAGE_SIZE")) * Integer.parseInt(System.getProperty("UI_PAGE_SIZE")));
            searchBeanObj.setInstallPointType(request.getParameter("installpointtype"));

            if (request.getParameter("numberid") != null && request.getParameter("numberid") != "") {
                searchBeanObj.setNumberId(request.getParameter("numberid").trim());
            }


            // Checking for null values and setting time in bean from request

            if (request.getParameter("scheduledfrom") != null && request.getParameter("scheduledfromhr") != null && request.getParameter("scheduledfrommin") != null) {
                java.sql.Date scheduledFrom = convertToSqlDate(request.getParameter("scheduledfrom"), request.getParameter("scheduledfromhr"), request.getParameter("scheduledfrommin"));
                searchBeanObj.setScheduleFrom(scheduledFrom);
            }

            if (request.getParameter("scheduledto") != null && request.getParameter("scheduledtohr") != null && request.getParameter("scheduledtomin") != null) {
                java.sql.Date scheduledTo = convertToSqlDate(request.getParameter("scheduledto"), request.getParameter("scheduledtohr"), request.getParameter("scheduledtomin"));
                searchBeanObj.setScheduleTo(scheduledTo);
            }
            if (request.getParameter("statusresult") != null && request.getParameter("statusresult") != "") {
                searchBeanObj.setStatusCode(request.getParameter("statusresult").trim());
            }

            if (request.getParameter("resultCode") != null && request.getParameter("resultCode") != "") {
                if (request.getParameter("resultCode").trim().equals("-1")) {
                    searchBeanObj.setResultCode("%");
                } else {
                    searchBeanObj.setResultCode(request.getParameter("resultCode").trim());
                }
            }

            if (request.getParameter("sch_det_id") != null && request.getParameter("sch_det_id") != "") {
                if (request.getParameter("sch_det_id").trim().equals("-1")) {
                    searchBeanObj.setSchDetId(0);
                } else {
                    searchBeanObj.setSchDetId(Long.parseLong(request.getParameter("sch_det_id").trim()));
                    System.out.println("2345" + searchBeanObj.getSchDetId());
                }
            }
            if (!ApplicationUtils.isBlank(request.getParameter("uiType")) && request.getParameter("uiType").equals("redirectNetwork")) {
                searchBeanObj = (SearchBean) ApplicationUtils.getParentLocations(searchBeanObj, (String) request.getParameter("uiType"));
            }

            if (request.getParameter("CONSUMERTypeID") != null) {
                searchBeanObj.setConsumerTypeId(request.getParameter("CONSUMERTypeID"));
            }

            if (request.getParameter("installpointtype") != null) {
                if (request.getParameter("installpointtype").equals("3")) {
                    if (request.getParameter("searchCriteria3") != null && !ApplicationUtils.isBlank(request.getParameter("searchCriteria3"))) {
                        searchBeanObj.setSearchCriteria(request.getParameter("searchCriteria3"));
                    }
                } else if (request.getParameter("installpointtype").equals("2")) {
                    if (request.getParameter("searchCriteria2") != null && !ApplicationUtils.isBlank(request.getParameter("searchCriteria2"))) {
                        searchBeanObj.setSearchCriteria(request.getParameter("searchCriteria2"));
                    }
                } else if (request.getParameter("installpointtype").equals("1")) {
                    //  System.out.println("in feeder");
                    if (request.getParameter("searchCriteria1") != null && !ApplicationUtils.isBlank(request.getParameter("searchCriteria1"))) {
                        searchBeanObj.setSearchCriteria(request.getParameter("searchCriteria1"));
                    }
                } else {
                    if (request.getParameter("searchCriteria") != null && !ApplicationUtils.isBlank(request.getParameter("searchCriteria"))) {
                        searchBeanObj.setSearchCriteria(request.getParameter("searchCriteria"));
                    }
                }
            }

            if (request.getParameter("value") != null && !ApplicationUtils.isBlank(request.getParameter("value"))) {
                searchBeanObj.setSearchValue(request.getParameter("value"));
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SearchHandler :: featchDataFromRequest() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }

        return searchBeanObj;

    } //End of method

    /* Method to converts the date String to Sql date
     param String date, hours and mins
     returns Sql Date */
    private Date convertToSqlDate(String date, String hrs, String min) {
        DateFormat df = new SimpleDateFormat("dd-MMM-yy HH:mm");
        java.sql.Date sqlDate = null;
        java.util.Date utilDate;
        String strDate;
        String hours = "00";
        String mins = "00";

        if (!ApplicationUtils.isBlank(hrs)) {
            hours = hrs;
        }

        if (!ApplicationUtils.isBlank(min)) {
            mins = min;
        }

        if (!ApplicationUtils.isBlank(date)) {
            strDate = date + " " + hours + ":" + mins;
            utilDate = new java.util.Date();

            try {
                utilDate = df.parse(strDate);
                sqlDate = new java.sql.Date(utilDate.getTime());
            } catch (Exception e) {
                //e.printStackTrace();
            }
        } else {
            sqlDate = null;
        }

        return sqlDate;

    } // End of method

    /*public API to get the Schedule Details from Http Request
     @param HttpServletRequest
     @Returns String UIACTION ReturnPage
     @Throws Exception
     */
    /*	public String schedule(HttpServletRequest request) throws Exception
     {
     String sReturnPage = ApplicationConstants.UIACTION_SEARCH_GET;

     try
     {
     logger.log(Level.INFO,"ScheduleHandler :: scheduleDetails :: method called ");
     System.out.println("ScheduleHandler :: scheduleDetails() :: method called ");
     System.out.println("ScheduleHandler :: scheduleDetails() :: ****printing data*****");
     //-- printing Data from request--

     if (request.getParameterValues("search_checkbox") != null)
     {

     String[] meterValues = request.getParameterValues("search_checkbox");
     System.out.print("ScheduleHandler ::schedule() :: Meter ::");
     for (int i=0;i<meterValues.length ;i++ )
     {
     if ((i+1) == meterValues.length)
     {
     System.out.print(meterValues[i]);
     }
     else
     System.out.print(meterValues[i] + ",");
     }
     }
     else
     System.out.print("ScheduleHandler ::schedule() :: Meter :: Not Selected");

     System.out.println();
     System.out.println("ScheduleHandler ::schedule() :: Get Energy Data :: " + request.getParameter("checkbox1"));
     System.out.println("ScheduleHandler ::schedule() :: Show Events :: " + request.getParameter("checkbox2"));
     System.out.println("ScheduleHandler ::schedule() :: Instance Parameter :: " + request.getParameter("checkbox3"));
     System.out.println("ScheduleHandler ::schedule() :: Settings :: " + request.getParameter("checkbox4"));

     sReturnPage = getSearchResults(request);
     //	sReturnPage = ApplicationConstants.UIACTION_SEARCH_GET;
     System.out.println("ScheduleHandler :: schedule() :: sReturnPage :: " + sReturnPage);
     }
     catch (Exception ex)
     {
     logger.log(Level.ERROR,"ScheduleHandler :: schedule() :: Exception " + ex);
     sReturnPage = ApplicationConstants.UIACTION_SEARCH_GET;
     }
     return sReturnPage;
     } //-- End of Method
     */
    /**
     * Private api to Display Device Data in Search page
     *
     * @param HttpServletRequest Object
     * @throws Exception
     * @returns String
     */
    private String displayDeviceData(HttpServletRequest request) throws Exception {

        String sReturnPage = ApplicationConstants.UIACTION_DISPLAY_DEVICE_DATA;
        SearchDelegate searchDelegateObject = null;
        DeviceDataBean deviceDataBeanObj = null;


        try {
            //System.out.println("SearchHandler :: displayDeviceData ----- called ");
            logger.log(Level.INFO, "SearchHandler :: displayDeviceData() :: method called");
            // set data from request in bean

            if ((String) request.getParameter("oprType") != null) {
                deviceDataBeanObj = fetchDataForDisplayDeviceData(request);
            }

            // getting delegate object
            if (deviceDataBeanObj != null) {
                searchDelegateObject = new SearchManager();
                deviceDataBeanObj = searchDelegateObject.displayDeviceData(deviceDataBeanObj);

                // setting the htmlStrind in request
                System.out.println("Name " + request.getParameter("installPointName"));
                request.setAttribute("DeviceDataBean", deviceDataBeanObj);
                request.setAttribute("installPointName", request.getParameter("installPointName"));
                request.setAttribute("installPointNo", request.getParameter("installPointNo"));
                request.setAttribute("serialNumber", request.getParameter("serialNumber"));
                request.setAttribute("devType", request.getParameter("devType"));
                request.setAttribute("installPointType", request.getParameter("installPointType"));


            }

            sReturnPage = ApplicationConstants.UIACTION_DISPLAY_DEVICE_DATA;
            logger.log(Level.INFO, "SearchHandler :: displayDeviceData() :: sReturnPage :: " + sReturnPage);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SearchHandler :: displayDeviceData() :: Exception :: " + ex);
            //ex.printStackTrace();
            request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_DISPLAY_DEVICE_DATA_FAILURE);
            request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE_EX, ex);
            sReturnPage = ApplicationConstants.UIACTION_DISPLAY_DEVICE_DATA;
            //ex.printStackTrace();
        }
        //System.out.println("SearchHandler :: displayDeviceData ----- ReturnPage ::  "+ sReturnPage);
        return sReturnPage;
    }

    /**
     * Private api to fetch Data required to disp device Data
     *
     * @param HttpServletRequest Object
     * @throws Exception
     * @returns DeviceDataBean object
     */
    private DeviceDataBean fetchDataForDisplayDeviceData(HttpServletRequest request) throws Exception {
        DeviceDataBean deviceDataBeanObj = new DeviceDataBean();
        String xslFile = null;
        String xslFilePath = null;

        try {
            //System.out.println("SearchHandler ::: fetchDataForDispDevData() :: Called ");
            logger.log(Level.INFO, "SearchHandler :: fetchDataForDispDevData() :: Called ");

            // setting data in bean---
            deviceDataBeanObj.setOperationType((String) request.getParameter("oprType"));

            // setting xsl file path
            xslFilePath = request.getScheme() + "://" + request.getHeader("Host") + "" + request.getContextPath() + "/xsl";
            if ((String) request.getParameter("oprType") != null) {
                xslFile = null;//XmlUtils.getXslFilePath((String) request.getParameter("oprType"), xslFilePath);
            }

            deviceDataBeanObj.setXslFilePath(xslFile);

            if (request.getParameter("devSchHistoryId") != null) {
                deviceDataBeanObj.setDeviceDataHistoryId(Long.parseLong(request.getParameter("devSchHistoryId")));
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SearchHandler :: fetchDataForDispDevData() :: Exception :: " + ex);
            throw ex;
        }
        return deviceDataBeanObj;
    } // End Of Method

    private String displayScheduleWiseLog(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.SCHEDULE_WISE_DEVICE_LOG;
        String filePath = "";
        StringBuffer data = new StringBuffer();
        if (request.getParameter("schedule_Wise_Log") != null) {
            filePath = request.getParameter("schedule_Wise_Log");
        }

        BufferedReader input = null;
        try {
            logger.log(Level.INFO, "SearchHandler :: displayScheduleWiseLog() :: method called");
            String path = "";
            String fileName = "";
            StringBuilder contents = new StringBuilder();
            input = new BufferedReader(new FileReader(new File(filePath)));
            String line = null;

            String[] splitLine = null;
            String[] doubleSplit = null;
            data.append("<table class=\"sub_heading_bg_blue\"  cellspacing=\"0\" cellpadding=\"0\" border=\"1\">");
            data.append("<tr class=\"data_table\"><td align=\"center\">Date</td><td align=\"center\" >Device Id</td><td align=\"center\" >Meter No</td><td align=\"center\" >IPAddress</td><td align=\"center\" >Status</td><TD align=\"center\" >Details</td></tr> ");
            while ((line = input.readLine()) != null) {
                splitLine = line.split("::");
                contents.append(line);
                contents.append("<br>");
                if (splitLine.length == 3) {
                    doubleSplit = splitLine[2].split("#.#");
                    if (doubleSplit.length == 5) {
                        data.append("<tr class=\"td_even\">");
                        data.append("<td >" + splitLine[0] + "</td>");
                        data.append("<td >" + doubleSplit[0] + "</td>");
                        data.append("<td >" + doubleSplit[1] + "</td>");
                        data.append("<td >" + doubleSplit[2] + "</td>");
                        data.append("<td >" + doubleSplit[3] + "</td>");
                        data.append("<td >" + doubleSplit[4] + "</td>");
                        data.append("</tr>");
                    }
                }
            }
            data.append("</table>");
            if (splitLine.length == 3 && doubleSplit.length == 5) {
                request.setAttribute("schedule_Wise_Log", data.toString());
            } else {
                request.setAttribute("schedule_Wise_Log", contents.toString());
            }
            //request.setAttribute("schedule_Wise_Log",contents.toString());

        } catch (Exception ex) {
            logger.log(Level.ERROR, " SearchHandler :: displayScheduleWiseLog() :: exception == " + ex);
            throw ex;
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return sReturnPage;
    }
} //end of class

