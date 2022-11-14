package in.emp.handler;

//-- MDA Imports
import in.emp.arch.GenericFormHandler;
import in.emp.util.ApplicationUtils;
import in.emp.common.ApplicationConstants;
import in.emp.util.PagingManager;
import in.emp.security.bean.SecUserBean;
import in.emp.system.SystemDelegate;
import in.emp.system.beans.SystemParameterData;
import in.emp.system.manager.SystemManager;
import in.emp.user.UserDelegate;
import in.emp.user.manager.UserManager;
import in.emp.user.bean.UserPrezData;
import in.emp.user.bean.UserBean;
import in.emp.user.bean.UserParameterBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.*;
import java.text.*;
import java.sql.Date;
import java.sql.SQLException;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class  UserHandler implements GenericFormHandler
{
	//private static Logger logger = new Logger(UserHandler.class.getName());
	private static String CLASS_NAME = UserHandler.class.getName();
	private static Logger logger = Logger.getLogger(UserHandler.class);


	/**
	 * API to process a user request
	 * @param request object of HttpServletRequest
	 * @return String the UI action name
	 * @throws Exception
	 */

	public String execute(HttpServletRequest request) throws Exception
	{
		String uiActionName = "";
		String sReturnPage = "";
				
		try
		{
			logger.log(Level.INFO, "UserHandler :: execute() :: method called");
		
			uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME);
			String subAction = request.getParameter("subAction");
			
			// Getting the parameters from request object for User application

			
			if (!ApplicationUtils.isBlank(uiActionName))
			{
				if (uiActionName.equals(ApplicationConstants.UIACTION_GET_USER))
				{
					sReturnPage = getUserSearchData(request);
				}
				else if (uiActionName.equals(ApplicationConstants.UIACTION_USER_SEARCH_POST))
				{
					
					if (!ApplicationUtils.isBlank(subAction) && subAction.equals("paging"))
					{
						setSortingInfo(request);
						PagingManager.continuePaging(request,CLASS_NAME);
						sReturnPage = ApplicationConstants.UIACTION_USER_SEARCH_POST;
					}
					else if (!ApplicationUtils.isBlank(subAction) && subAction.equals("sort"))
					{
						sReturnPage = getUserSearchResults(request);
					}
					else
					{
						sReturnPage = getUserSearchResults(request);
					}				
				}
				else if (uiActionName.equals(ApplicationConstants.UIACTION_POST_CREATE_USER))
				{
					if(request.getParameter("cancelOperation") != null && request.getParameter("cancelOperation").equals("true"))
						sReturnPage = ApplicationConstants.UIACTION_USER_SEARCH_POST;
					else
						sReturnPage = createUpdateUser(request);
						
					if (sReturnPage.equals(ApplicationConstants.UIACTION_USER_SEARCH_POST))
					{
						sReturnPage = getUserSearchResults(request);
					}
				}
				else if (uiActionName.equals(ApplicationConstants.UIACTION_UPDATE_USER_STATUS))
				{
					updateUserStatus(request);
					sReturnPage = getUserSearchResults(request);
				}
				else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_CREATE_USER))
				{
					sReturnPage = getUserDetails(request);
				}
                                else if(uiActionName.equals(ApplicationConstants.UIACTION_GET_USER_PARAMETER_HOME))
				{
                                    sReturnPage = getUserParameter(request, ApplicationConstants.UIACTION_GET_USER_PARAMETER_HOME);
                                }
                                 else if(uiActionName.equals(ApplicationConstants.UIACTION_GET_USER_PARAMETER))
				{
                                    sReturnPage = getUserParameter(request, ApplicationConstants.UIACTION_GET_USER_PARAMETER);
                                }
                                 else if(uiActionName.equals(ApplicationConstants.UIACTION_POST_USER_PARAMETER))
				{
                                    sReturnPage = saveUserParameter(request);
                                }
			}
			
			logger.log(Level.INFO, "UserHandler :: execute() :: sReturnPage :: " + sReturnPage);
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "UserHandler :: execute() :: Exception :: " + ex);
		}

		return sReturnPage;

	} //end execute method
	
	/**
	 * Private API to set the sorting information
	 * @param	HttpServletRequest request
	 * @Returns 
	 */

	private static void setSortingInfo(HttpServletRequest request) 
	{
		try
		{
			logger.log(Level.INFO, "UserHandler :: setSortingInfo() :: method called");
			String sortColumn = null;
			String sortOrder    = null;
			boolean isPostSearch = false;

			String uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME);
			String subAction = request.getParameter("subAction");
			
			if(!ApplicationUtils.isBlank(uiActionName) && uiActionName.equals(ApplicationConstants.UIACTION_USER_SEARCH_POST) && ApplicationUtils.isBlank(subAction))
					isPostSearch = true;

			if(!isPostSearch && request.getParameter("columnName") != null)
				sortColumn = request.getParameter("columnName");
			else
				sortColumn = ApplicationConstants.USER_DEFAULT_SORT_COLUMN;

			if(!isPostSearch && request.getParameter("sortOrder") != null)
				sortOrder = request.getParameter("sortOrder");
			else
				sortOrder = ApplicationConstants.DEFAULT_SORT_ORDER_ASC;

			String tableName[] = sortColumn.split("\\.");	
			request.setAttribute("columnName",sortColumn);
			request.setAttribute("sortOrder",sortOrder);
			request.setAttribute("sortColTableName",tableName[0]);
		}
		catch (Exception e)
		{
			logger.log(Level.ERROR, "UserHandler :: setSortingInfo() :: Exception  :: " + e);
		}
	} // End oF Method	

	/*
	* Private api to get the reqired data for load the serach page/
	*/
	private String getUserSearchData(HttpServletRequest request)throws Exception
	{
		String sReturnPage = ApplicationConstants.UIACTION_GET_USER;
		UserPrezData userPrezData = null;
		UserDelegate userMgrObj = null;
		HttpSession userSession = request.getSession();
		try
		{	
			logger.log(Level.INFO, "UserHandler :: getUserSearchData() :: method called");
			
			//-- create a delegate object to get the serach results for seletced search criteria
			userMgrObj = new UserManager();

			//-- Calling Manager--
			userPrezData = userMgrObj.getUserSearchData();	

			//-- setting userPrezData object in session--
			if(userSession.getAttribute("userPrezData") != null)
			{
				userSession.removeAttribute("userPrezData");
				userSession.setAttribute("userPrezData",userPrezData);
			}
			else
			{
				userSession.setAttribute("userPrezData",userPrezData);
			}

			sReturnPage = ApplicationConstants.UIACTION_GET_USER;
			logger.log(Level.INFO, "UserHandler :: getUserSearchData() :: sReturnPage :: " + sReturnPage);
		}
		catch (Exception ex)
		{	
			logger.log(Level.ERROR, "UserHandler :: getUserSearchData() :: Exception :: " + ex);
			request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_COMMON_ERROR_FEATCHING_SEARCH_DATA);
			request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE_EX,ex);
			sReturnPage = ApplicationConstants.UIACTION_GET_USER;
		}

		return sReturnPage;
	}

	/*
	*  Private api to get the search results from selected criteria.
	*/
	private String getUserSearchResults(HttpServletRequest request)throws Exception
	{
		String sReturnPage = ApplicationConstants.UIACTION_USER_SEARCH_POST;
		UserPrezData userPrezData = null;
		UserBean userBeanObj = null;
		UserDelegate userMgrObj = null;
		String subAction = ""; 
		try
		{	
			logger.log(Level.INFO, "UserHandler :: getUserSearchResults() :: method called");

			String uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME);
			subAction = request.getParameter("subAction");

			if(request.getSession().getAttribute("userPrezData") != null)
			{
				userPrezData = (UserPrezData)request.getSession().getAttribute("userPrezData");
			}
			else
			{
				userPrezData = new UserPrezData();
			}
				
			setSortingInfo(request);

			if(uiActionName != null && uiActionName.equals(ApplicationConstants.UIACTION_USER_SEARCH_POST))
			{
				userBeanObj = fetchSearchCriteriaFromRequest(request);
				userPrezData.setUserBean(userBeanObj);
			}
			

			userMgrObj = new UserManager();

			//-- Calling Manager
			userPrezData = userMgrObj.getUserSearchResults(userPrezData);	
			
			request.getSession().setAttribute("userPrezData",userPrezData);
		
			// -- calling Page Manager--	
			//-- Page persistence change starts --
			if(uiActionName.equals(ApplicationConstants.UIACTION_POST_CREATE_USER))
			{
				if(request.getParameter("lastRow") != null)
					PagingManager.startPaging(request, (userPrezData.getUserList()), false, CLASS_NAME,((userPrezData.getUserBean()).getTotalRecords()));
				else
					PagingManager.startPaging(request, (userPrezData.getUserList()), true, CLASS_NAME,((userPrezData.getUserBean()).getTotalRecords()));
			}
			else
				PagingManager.startPaging(request, (userPrezData.getUserList()), true, CLASS_NAME,((userPrezData.getUserBean()).getTotalRecords()));
			//-- Page persistence change ends --

			sReturnPage = ApplicationConstants.UIACTION_USER_SEARCH_POST;

			logger.log(Level.INFO, "UserHandler :: getUserSearchResults() :: sReturnPage :: " + sReturnPage);
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "UserHandler :: getUserSearchResults() :: Exception :: " + ex);
			request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_SEARCH_RESULTS_FAILURE);
			request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE_EX,ex);
			sReturnPage = ApplicationConstants.UIACTION_USER_SEARCH_POST;
			//ex.printStackTrace();
		}

		return sReturnPage;
	}

	/** 
	 * Private api to update user status
	 * @param			HttpServletRequest request
	 * @Returns		
	 * @throws			Exception
	 */
	private void updateUserStatus(HttpServletRequest request)throws Exception
	{
		UserPrezData userPrezData = null;
		UserDelegate userDelegate = null;
		UserBean userBeanObj = null;
		SecUserBean secUserBean = null;
		try
		{	
			logger.log(Level.INFO, "UserHandler :: updateUserStatus() :: method called");
			
			if(request.getSession().getAttribute("userPrezData") != null)
			{
				userPrezData = (UserPrezData)request.getSession().getAttribute("userPrezData");
				userBeanObj = userPrezData.getUserBean();
				userBeanObj.setMin(0);
				userBeanObj.setMax(Integer.parseInt(System.getProperty("CACHE_PAGE_SIZE"))*Integer.parseInt(System.getProperty("UI_PAGE_SIZE"))); 
				userPrezData.setUserBean(userBeanObj);
				request.getSession().setAttribute("userPrezData", userPrezData);
			}

			// -- fetching data --
			userBeanObj = fetchUpdateUserDataFromRequest(request);
						
			// -- getting the logged-in user detail --
			secUserBean = (SecUserBean)request.getSession().getAttribute(ApplicationConstants.USER_SESSION_NAME);
			userBeanObj.setUserId(secUserBean.getUserId());
			
			// -- create a delegate object --
			userDelegate = new UserManager();
			
			// -- Calling Manager--
			userDelegate.updateUserStatus(userBeanObj);		
			
			request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_USER_STATUS_SUCCESS);
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "UserHandler :: updateUserStatus() :: Exception :: " + ex);
			request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_USER_STATUS_FAILURE);
			request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE_EX,ex);
			//ex.printStackTrace();
		}		
	}

	/** 
	 * Private api to gets details of an existing user.
	 * @param				HttpServletRequest request
	 * @Returns String	sReturnPage
	 * @throws				Exception if occurs
	 */
	private String getUserDetails(HttpServletRequest request)throws Exception
	{
		String sReturnPage = ApplicationConstants.UIACTION_GET_CREATE_USER;
		UserPrezData userPrezData = null;
		UserBean userBeanObj = null;
		UserDelegate userDelegate = null;
		SecUserBean secUserBean = null;
		HttpSession userSession = request.getSession();
		try
		{	
			logger.log(Level.INFO, "UserHandler :: getUserDetails() :: method called");
					
			userBeanObj = new UserBean();
			if(request.getParameter("userId") != null && request.getParameter("userId") != "")
			{
				userBeanObj.setUserId(Integer.parseInt(request.getParameter("userId")));
				//logger.log(Level.INFO, "UserHandler :: getUserDetails() :: RoleID :: " + userBeanObj.getUserId());
			}

			// -- create a delegate object to get the device results -- 
			userDelegate = new UserManager();
			
			// -- Calling Manager--
			userPrezData = userDelegate.getUserDetails(userBeanObj);	
			if(request.getAttribute("secUserData") != null)
			{
				request.removeAttribute("secUserData");
				request.setAttribute("secUserData",userPrezData);
			}
			else
			{
				request.setAttribute("secUserData",userPrezData);
			}
			sReturnPage = ApplicationConstants.UIACTION_GET_CREATE_USER;
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "UserHandler :: getUserDetails() :: Exception :: " + ex);
			request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_COMMON_ERROR_DETAIL_FETCH_VIEW_UPDATE);
			request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE_EX,ex);
			sReturnPage = ApplicationConstants.UIACTION_GET_CREATE_USER;
			//ex.printStackTrace();
		}		
		return sReturnPage;
	}

	/**
	 *  Private API to Create or update the user.
	 * @param request		HttpServletRequest
	 * @throws Exception	if an error occurs
	 * @return String			sReturnPage
	 */
	private String createUpdateUser(HttpServletRequest request) throws Exception
	{
		String sReturnPage = "";
		SecUserBean secUserBean = null;
		UserPrezData userPrezData = null;
		UserBean userBeanObj = null;
		UserDelegate userDelegate = null;
		LinkedList selectedRoleList = null;
		String strSelRoleSelected[];
		String strSelectedRole ="";
                LinkedList selectedAreaList = null;
		String strSelAreaSelected[];
		String strSelectedArea ="";
		boolean isCreate = false;
		HttpSession userSession = request.getSession();
		try
		{
			logger.log(Level.INFO, "UserHandler :: createUpdateUser() :: method called");
		
			userPrezData = new UserPrezData();
			userBeanObj = new UserBean();
			selectedRoleList = new LinkedList();
                        selectedAreaList =new LinkedList();

			secUserBean =  (SecUserBean)userSession.getAttribute(ApplicationConstants.USER_SESSION_NAME);

		   if(request.getParameter("userId") != null && request.getParameter("userId") != "")
			{
				userBeanObj.setUserId(Integer.parseInt(request.getParameter("userId")));
			}
			else
			{
				isCreate = true;
			}

			if(request.getParameter("txtuserloginid") != null && request.getParameter("txtuserloginid") != "")
			{
				userBeanObj.setUserLoginId(request.getParameter("txtuserloginid"));
			}

			if(request.getParameter("txtuserpassword") != null && request.getParameter("txtuserpassword") != "")
			{
				userBeanObj.setUserPassword(request.getParameter("txtuserpassword"));
			}

			if(request.getParameter("txtuserempid") != null && request.getParameter("txtuserempid") != "")
			{
				userBeanObj.setUserEmpId(new Long((String)request.getParameter("txtuserempid")).longValue());
			}
			else if(request.getParameter("employeeId") != null && request.getParameter("employeeId") != "")
			{
				userBeanObj.setUserEmpId(new Long((String)request.getParameter("employeeId")).longValue());
			}

			/*if(request.getParameter("statusCd") != null && request.getParameter("statusCd") != "")
			{
				userBeanObj.setStatus(request.getParameter("statusCd"));
			}*/
			
			if(secUserBean.getUserId() > 0)
			{
					userBeanObj.setUpdatedBy(secUserBean.getUserId());
					userBeanObj.setCreatedBy(secUserBean.getUserId());
			}
			else
			{
					userBeanObj.setUpdatedBy(new Long(1));
					userBeanObj.setCreatedBy(new Long(1));
			}
			
			/*logger.log(Level.INFO, " UserHandler :: createUpdateUser() :: isCreate == " + isCreate);
			logger.log(Level.INFO, " UserHandler :: createUpdateUser() :: User Id == " + userBeanObj.getUserId());
			logger.log(Level.INFO, " UserHandler :: createUpdateUser() :: Login Id == "+userBeanObj.getUserLoginId());
			logger.log(Level.INFO, " UserHandler :: createUpdateUser() :: Password == "+userBeanObj.getUserPassword());
			logger.log(Level.INFO, " UserHandler :: createUpdateUser() :: Emp ID == " + userBeanObj.getUserEmpId());*/
			

			strSelectedRole = request.getParameter("selectedAllFunction");
			//logger.log(Level.INFO, "UserHandler :: createUpdateUser() :: selected function Ids :: " + strSelectedRole);
			
			if(strSelectedRole != null && strSelectedRole.length() > 0)
			{
				strSelRoleSelected = strSelectedRole.split(",");
				if(strSelRoleSelected.length > 0)
				{
					for(int i = 0 ; i <strSelRoleSelected.length ; i++)
					{
						if(strSelRoleSelected[i] != null)
						{							
							Long roleId = new Long((String)strSelRoleSelected[i]).longValue();
							selectedRoleList.add(roleId);
						}
					}
				}
			}

                        strSelectedArea = request.getParameter("selectedAllArea");

			if(strSelectedArea != null && strSelectedArea.length() > 0)
			{
				strSelAreaSelected = strSelectedArea.split(",");
				if(strSelAreaSelected.length > 0)
				{
					for(int i = 0 ; i <strSelAreaSelected.length ; i++)
					{
						if(strSelAreaSelected[i] != null)
						{
							Long areaId = new Long((String)strSelAreaSelected[i]).longValue();
							selectedAreaList.add(areaId);
						}
					}
				}
			}

			
						
			// --set data in UserPrezData--
			userPrezData.setUserBean(userBeanObj);
			userPrezData.setSelectedRoleList(selectedRoleList);
                        userPrezData.setSelectedAreaList(selectedAreaList);

			// --call Manager--
			userDelegate = new UserManager();
			userPrezData = userDelegate.createUpdateUser(userPrezData);
				
			if(isCreate)
			{
				request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_USER_CREATE_SUCCESS);
			}
			else
			{
				request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_USER_UPDATE_SUCCESS);
			}

			//sReturnPage = ApplicationConstants.UIACTION_USER_SEARCH_POST;	
			logger.log(Level.INFO, "UserHandler :: createUpdateUser() :: sReturnPage :: "+sReturnPage);
		}
		catch(SQLException sqlEx)
		{
			logger.log(Level.ERROR, "UserHandler :: createUpdateUser() :: SQL Exception :: " + sqlEx);
			if((sqlEx.getErrorCode()) == 1)
			{
				request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_USER_ALREADY_EXISTS);
			}
			else
			{
				if(isCreate)
				{
					request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_USER_CREATE_FAILURE);
				}
				else
				{
					request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_USER_UPDATE_FAILURE);
				}
				request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE_EX, sqlEx);
			}
			//sReturnPage = ApplicationConstants.UIACTION_USER_SEARCH_POST;
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "UserHandler :: createUpdateUser() :: Exception :: " + ex);
			if(isCreate)
			{
				request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_USER_CREATE_FAILURE);
			}
			else
			{
				request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_USER_UPDATE_FAILURE);
			}
			request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE_EX,ex);
			sReturnPage = ApplicationConstants.UIACTION_USER_SEARCH_POST;
		}
		return sReturnPage;
	}

	/** 
	 * Private api to get the serach criteria  from request
	 * @param		HttpServletRequest request
	 * @Returns	UserBean Object
	 * @throws		Exception
	 */
	private UserBean fetchSearchCriteriaFromRequest(HttpServletRequest request)throws Exception
	{
		UserBean userBeanObj = null;
		try
		{
			logger.log(Level.INFO, "UserHandler :: fetchSearchCriteriaFromRequest() :: method called");

			userBeanObj = fetchDataFromRequest(request);
			String subAction = request.getParameter("subAction");
			if(ApplicationUtils.isBlank(subAction))
			{
					
				if(request.getParameter("officeLocationType") != null && request.getParameter("officeLocationType") != "")
					userBeanObj.setLocationTypeId(new Long((String)request.getParameter("officeLocationType")).longValue());
				if(request.getParameter("officeLocation") != null && request.getParameter("officeLocation") != "")
					userBeanObj.setLocationId(new Long((String)request.getParameter("officeLocation")).longValue());
				
				
				if(request.getParameter("zone") != null && request.getParameter("zone") != "")
					userBeanObj.setZoneId(new Long((String)request.getParameter("zone")).longValue());
					
				if(request.getParameter("circle") != null && request.getParameter("circle") != "")
					userBeanObj.setCircleId(new Long((String)request.getParameter("circle")).longValue());
				
				if(request.getParameter("division") != null && request.getParameter("division") != "")
					userBeanObj.setDivisionId(new Long((String)request.getParameter("division")).longValue());
							
				if(request.getParameter("subDivision") != null && request.getParameter("subDivision") != "")
					userBeanObj.setSubDivisionId(new Long((String)request.getParameter("subDivision")).longValue());

			}
			else
			{
				if(request.getSession().getAttribute("userPrezData") != null)
				{
					UserPrezData userPrezData = (UserPrezData)request.getSession().getAttribute("userPrezData");
					if(userPrezData.getUserBean() != null)
					{
						userBeanObj.setLocationTypeId((userPrezData.getUserBean()).getLocationTypeId());
						userBeanObj.setLocationId((userPrezData.getUserBean()).getLocationId());
						  userBeanObj.setZoneId((userPrezData.getUserBean()).getZoneId());
						  userBeanObj.setCircleId((userPrezData.getUserBean()).getCircleId());
						  userBeanObj.setDivisionId((userPrezData.getUserBean()).getDivisionId());
						  userBeanObj.setSubDivisionId((userPrezData.getUserBean()).getSubDivisionId());
					}
				}
			}

		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "UserHandler :: fetchSearchCriteriaFromRequest() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}
		return userBeanObj;
	} //fetchSearchCriteriaFromRequest ends
	
	/** 
	 * Private api to get the user id(s) whose status is required to change and new status from request
	 * @param		HttpServletRequest request
	 * @Returns	UserBean Object
	 * @throws		Exception
	 */
	private UserBean fetchUpdateUserDataFromRequest(HttpServletRequest request)throws Exception
	{
		UserBean userBeanObj = null;
		String[] strCheckedUserIdArr = null;
		String userIdStr = "";
		try
		{
			logger.log(Level.INFO, "UserHandler :: fetchUpdateUserDataFromRequest() :: method called");
			
			userBeanObj = new UserBean();

			if(request.getParameterValues("search_checkbox") != null)
			{
				strCheckedUserIdArr = request.getParameterValues("search_checkbox");
				for(int i=0;i<strCheckedUserIdArr.length;i++)
				{
					if(strCheckedUserIdArr[i] != "")
					{
						if(userIdStr == "")
						{	
							userIdStr += strCheckedUserIdArr[i] ;
						}
						else
						{
							userIdStr = userIdStr + ","+ strCheckedUserIdArr[i] ;
						}
					}
				}
				userBeanObj.setUserIdStr(userIdStr);
			}

			if(request.getParameter("statusCdAction") != null)
			{
				if(!request.getParameter("statusCdAction").trim().equals("-1"))
				{
					String statusStr = (String)request.getParameter("statusCdAction");
					userBeanObj.setUserStatus(statusStr);
				}
			}
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "UserHandler :: fetchUpdateUserDataFromRequest() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}
		return userBeanObj;
	} //fetchUpdateUserDataFromRequest ends

	/** 
	 * Private api to get the data from request
	 * @param		HttpServletRequest request
	 * @Returns	UserBean Object
	 * @throws		Exception
	 */
	private UserBean fetchDataFromRequest(HttpServletRequest request)throws Exception
	{
		UserBean userBeanObj = null;
		try
		{
			logger.log(Level.INFO, "UserHandler :: fetchDataFromRequest() :: method called");
			
			//-- Setting the sorting order and sorting column in request--
			//setSortingInfo(request);

			//-- get data from request and set into UserBean data object
			userBeanObj = new UserBean();

			userBeanObj.setSortColumnName((String)request.getAttribute("columnName"));
			userBeanObj.setSortOrder((String)request.getAttribute("sortOrder"));
			userBeanObj.setSortColTableName((String)request.getAttribute("sortColTableName"));
			userBeanObj.setMin(0);
			userBeanObj.setMax(Integer.parseInt(System.getProperty("CACHE_PAGE_SIZE"))*Integer.parseInt(System.getProperty("UI_PAGE_SIZE"))); 
			userBeanObj.setLastRowValue("");
			userBeanObj.setLastValueDataType("");
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "UserHandler :: fetchDataFromRequest() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}
		return userBeanObj;
	} //fetchDataFromRequest ends

    private String getUserParameter(HttpServletRequest request,String uiAction) throws Exception{
        String sReturnPage = ApplicationConstants.UIACTION_GET_USER_PARAMETER_HOME;
        UserParameterBean userParameterBean = new UserParameterBean();
        UserDelegate userDelegate = null;
        String popup = "";
        try
        {
            if(request.getParameter("popup") != null)
            {
               popup =  request.getParameter("popup");
            }
            logger.log(Level.INFO, "UserHandler :: getUserParameter() :: method called");
            userDelegate = new UserManager();
            userParameterBean = userDelegate.getUserParameter(userParameterBean);
    
            request.setAttribute("userParamBean", userParameterBean);
            request.setAttribute("popup", popup);
            
        }
        catch (Exception ex)
        {
                logger.log(Level.ERROR, "UserHandler :: getUserParameter() :: Exception :: " + ex);
                //ex.printStackTrace();
                throw ex;
        }
        return uiAction;
        
    }

    private String saveUserParameter(HttpServletRequest request) {
        String sReturnPage = ApplicationConstants.UIACTION_POST_USER_PARAMETER;
        UserParameterBean userParameterBean = new UserParameterBean();
        UserDelegate userDelegate = null;
        LinkedList userParameterList = new LinkedList();
        String [] paramId = null;
        String[] paramValue = null;
        String popup = "";
        try
        {
            logger.log(Level.INFO, "UserHandler :: getUserParameter() :: method called");
            if(request.getParameter("popup") != null)
            {
               popup =  request.getParameter("popup");
            }
            paramId = request.getParameterValues("paramId");
            paramValue = request.getParameterValues("editValue");

            if(paramId != null && paramId.length >0)
            {
                for(int i=0; i<paramId.length; i++)
                {
                        if(paramValue[i].equals("0") || paramValue[i].equals(""))
                        {
                            continue;
                        }
                        else
                        {
                            UserParameterBean userParamBeanObj = new UserParameterBean();
                            userParamBeanObj.setParamId(ApplicationUtils.stringToLong(paramId[i]));
                            userParamBeanObj.setParamValue(paramValue[i]);
                            userParameterList.add(userParamBeanObj);

                        }
                }
            }
              if(userParameterList.size() > 0)
              {
                  userParameterBean.setUserParameterList(userParameterList);
              }


            userDelegate = new UserManager();
            userParameterBean = userDelegate.editUserparameter(userParameterBean);

            SystemDelegate systemDelegate = new SystemManager();
            List systemParameterList = systemDelegate.getAllSystemParameters();
            SystemParameterData systemParameterData = null;

            if(systemParameterList != null && systemParameterList.size() > 0)
            {
                    Iterator systemParameterItr = systemParameterList.iterator();
                    while (systemParameterItr.hasNext())
                    {
                            systemParameterData = (SystemParameterData)systemParameterItr.next();
                            //System.out.println(systemParameterData.getParameterName()+" = "+systemParameterData.getParameterValue());
                            System.setProperty(systemParameterData.getParameterName(), systemParameterData.getParameterValue());
                    }
            }
            if(popup.equalsIgnoreCase("NO"))
            {
                sReturnPage = getUserParameter(request, ApplicationConstants.UIACTION_GET_USER_PARAMETER);
                request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, "Parameter Saved Sucessfully");
            }
            else
            {
                request.setAttribute("saveUserParam", "saved");
                request.setAttribute("popup", popup);
            }
           
         }
        catch (Exception ex)
        {
                logger.log(Level.ERROR, "UserHandler :: getUserParameter() :: Exception :: " + ex);
                //ex.printStackTrace();
        }
        return sReturnPage;
    }
}//class ends

