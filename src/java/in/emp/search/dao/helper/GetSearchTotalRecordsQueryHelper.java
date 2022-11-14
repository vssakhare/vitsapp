package in.emp.search.dao.helper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.text.*;

//-- MDA Imports
import in.emp.dao.QueryHelper;
import in.emp.search.bean.SearchBean;
import in.emp.util.ApplicationUtils;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * Query helper class to get list of all uesrs
 */
public class GetSearchTotalRecordsQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetSearchTotalRecordsQueryHelper.class);

	private SearchBean searchBean;
	private String schedule_from;
	private String schedule_to;
	private String location_id;
	
	/**
	 * @public Constructor GetSearchResultsQueryHelper()
	 * @param searchBeanObj object of SerachBean
	 */
	public GetSearchTotalRecordsQueryHelper(SearchBean searchBean)
	{
		this.searchBean = searchBean;
	}

	/**
	 * Public API to get data object.
	 * @param results object of ResultSet
	 * @return Object
	 * @throws Exception
	 */
	public Object getDataObject(ResultSet results) throws Exception 
	{
		try
		{
			//logger.log(Level.INFO, "GetSearchTotalRecordsQueryHelper :: getDataObject() :: method called");
			// Setting Search Total Records Data in the Search Bean object from result set
			searchBean.setTotalRecords(results.getDouble("CNT"));
				
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetSearchTotalRecordsQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}
		return searchBean;

	}//getDataObject() ends 
	
	/**
	 * Public API to get query results
	 * @param conn object of Connection
	 * @return object of ResultSet
	 * @throws Exception
	 */
	public ResultSet getQueryResults(Connection connection) throws Exception 
	{
		PreparedStatement statement = null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
		DateFormat df = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
		try 
		{
			
//			sql.append("SELECT COUNT(1) CNT ");
//			sql.append("FROM ");
//			/*sql.append("DEVICE, ");
//			sql.append("DEVICE_SCHEDULE, ");
//			sql.append("LOCATION, ");
//			sql.append("DEVICE_CONNECTION, ");
//			sql.append("CONSUMER, ");
//			sql.append("TRANSFORMER, ");
//			sql.append("FEEDER, ");
//			sql.append("SUBSTATION, ");
//			sql.append("DEV_INSTALL_POINT, ");
//			sql.append("INSTALL_POINT_TYPE, ");
//			sql.append("DEVICE_TYPE, ");
//			sql.append("DEVICE_DATA_HISTORY, ");
//			sql.append("DEVICE_SCHEDULE_HISTORY ");*/
//                        sql.append("DEVICE DV, DEVICE D, ");
//			sql.append("DEVICE_SCHEDULE, ");
//			sql.append("LOCATION, ");
//			sql.append("DEVICE_CONNECTION, ");
//			sql.append("CONSUMER, ");
//			sql.append("NDM_TRANSFORMER, ");
//			sql.append("NDM_FEEDER, ");
//			sql.append("NDM_SUBSTATION, ");
//			sql.append("DEV_INSTALL_POINT, ");
//			sql.append("INSTALL_POINT_TYPE, ");
//			sql.append("DEVICE_TYPE, ");
//			sql.append("DEVICE_DATA_HISTORY, ");
//                        sql.append("MODEM_TECHNOLOGY mod, ");
//			sql.append("DEVICE_SCHEDULE_HISTORY,PROJECT P,TOWN_MASTER TM ");//TOWN T ,
//                        sql.append(", DLMS_METER_TYPE DMT,DLMS_CLASS DC ");
//                         if(searchBean.getGroupValue() >0){
//                          sql.append(",GROUP_MASTER,GROUP_MEMBERS ");
//                        }
//			sql.append("WHERE ");
//			/*sql.append("DEVICE_SCHEDULE.DEVICE_ID = DEVICE.DEVICE_ID ");
//			sql.append("AND LOCATION.LOCATION_ID = DEVICE.DEVICE_LOCATION_ID ");
//			sql.append("AND DEVICE_TYPE.DEVICE_TYPE_ID = DEVICE.DEVICE_TYPE_ID ");
//			sql.append("AND INSTALL_POINT_TYPE.INSTALL_POINT_TYPE_ID = DEV_INSTALL_POINT.INSTALL_POINT_TYPE_ID ");
//			sql.append("AND DEV_INSTALL_POINT.CONSUMER_ID = CONSUMER.CONSUMER_ID(+) "); 
//			sql.append("AND DEV_INSTALL_POINT.TRANSFORMER_ID = TRANSFORMER.TRANSFORMER_ID(+) ");
//			sql.append("AND DEV_INSTALL_POINT.FEEDER_ID = FEEDER.FEEDER_ID(+) ");
//			sql.append("AND DEV_INSTALL_POINT.SUBSTATION_ID = SUBSTATION.SUBSTATION_ID(+) ");
//			sql.append("AND DEV_INSTALL_POINT.DEVICE_CONNECTION_ID = DEVICE_CONNECTION.DEVICE_CONNECTION_ID ");
//			sql.append("AND DEVICE_CONNECTION.DEVICE_ID = DEVICE.DEVICE_ID ");
//			sql.append("AND DEVICE_DATA_HISTORY.DEVICE_SCHEDULE_HISTORY_ID(+) = DEVICE_SCHEDULE_HISTORY.DEVICE_SCHEDULE_HISTORY_ID "); 
//			sql.append("AND DEVICE_SCHEDULE_HISTORY.EXECUTION_RESULT_CODE IS NOT NULL ");
//			sql.append("AND DEVICE_SCHEDULE_HISTORY.DEVICE_SCHEDULE_ID = DEVICE_SCHEDULE.DEVICE_SCHEDULE_ID ");
//			sql.append("AND DEVICE_SCHEDULE_HISTORY.STATUS_CD <> 'D' ");
//			sql.append(" AND DEV_INSTALL_POINT.STATUS_CD = 'A' ");
//			sql.append("AND DEVICE_SCHEDULE.STATUS_CD <> 'D' ");
//			sql.append("AND DEVICE.STATUS_CD <> 'D' ");
//			sql.append(" AND DEVICE_CONNECTION.STATUS_CD = 'A' ");*/
//
//                        sql.append("DEVICE_SCHEDULE.DEVICE_ID = DV.DEVICE_ID AND DV.SCHEDULABLE_YN = 'Y'" );
//                        sql.append(" AND DEVICE_CONNECTION.COMM_DEVICE_ID = D.DEVICE_ID AND D.SCHEDULABLE_YN = 'N'" );
//			sql.append("AND LOCATION.LOCATION_ID = DV.DEVICE_LOCATION_ID ");
//			sql.append("AND DEVICE_TYPE.DEVICE_TYPE_ID = DV.DEVICE_TYPE_ID ");
//                        sql.append("AND DV.DLMS_METER_TYPE_ID =DMT.DEVICE_TYPE_CD (+) AND DV.DLMS_CLASS_ID=DC.CLASS_ID(+) ");
//			sql.append("AND INSTALL_POINT_TYPE.INSTALL_POINT_TYPE_ID = DEV_INSTALL_POINT.INSTALL_POINT_TYPE_ID ");
//			sql.append("AND DEV_INSTALL_POINT.CONSUMER_ID = CONSUMER.CONSUMER_ID(+) ");
//			sql.append("AND DEV_INSTALL_POINT.TRANSFORMER_ID = NDM_TRANSFORMER.TRANSFORMER_ID_N(+) ");
//			sql.append("AND DEV_INSTALL_POINT.FEEDER_ID = NDM_FEEDER.FEEDER_ID_N(+) ");
//			sql.append("AND DEV_INSTALL_POINT.SUBSTATION_ID = NDM_SUBSTATION.SUBSTATION_ID_N(+) ");
//                        sql.append("AND DEV_INSTALL_POINT.PROJECT_ID=P.PROJECT_ID(+) ");
//                        //sql.append("AND DEV_INSTALL_POINT.TOWN_ID=T.TOWN_ID(+) ");
//                        sql.append("AND DEV_INSTALL_POINT.TOWN_ID=TM.TOWN_ID(+) ");
//			sql.append("AND DEV_INSTALL_POINT.DEVICE_CONNECTION_ID = DEVICE_CONNECTION.DEVICE_CONNECTION_ID ");
//			sql.append("AND DEVICE_CONNECTION.DEVICE_ID = DV.DEVICE_ID ");
//			sql.append("AND DEVICE_DATA_HISTORY.DEVICE_SCHEDULE_HISTORY_ID(+) = DEVICE_SCHEDULE_HISTORY.DEVICE_SCHEDULE_HISTORY_ID ");
//			sql.append("AND DEVICE_SCHEDULE_HISTORY.EXECUTION_RESULT_CODE IS NOT NULL ");
//                        sql.append("AND D.MODEM_TECH_ID = mod.MODEM_TECH_ID "); 
//			sql.append("AND DEVICE_SCHEDULE_HISTORY.DEVICE_SCHEDULE_ID = DEVICE_SCHEDULE.DEVICE_SCHEDULE_ID ");
//			sql.append("AND DEVICE_SCHEDULE_HISTORY.STATUS_CD <> 'D' ");
//			sql.append("AND DEV_INSTALL_POINT.STATUS_CD = 'A' ");
//			sql.append("AND DEVICE_SCHEDULE.STATUS_CD <> 'D' ");
//			sql.append("AND DV.STATUS_CD <> 'D' ");
//			sql.append("AND DEVICE_CONNECTION.STATUS_CD in ('A','I') ");
//                        sql.append("AND DEVICE_SCHEDULE_HISTORY.EXECUTION_END_TIME IS NOT NULL " );
//		
//			if (searchBean.getDeviceType() != null && !searchBean.getDeviceType().equals("-1"))		
//			{
//				sql.append("AND DEVICE_TYPE.DEVICE_TYPE_ID =" + searchBean.getDeviceType());
//			}
//
//	/*		if (searchBean.getLocationType() != null && !searchBean.getLocationType().equals("-1"))		
//			{
//				sql.append("AND LK_LOCATION.LOCATION_TYPE_ID =" + searchBean.getLocationType());
//			} 
//*/
//	/*		if (searchBean.getLocation() != null && !searchBean.getLocation().equals("-1"))		
//			{
//				sql.append(" AND DEVICE.DEVICE_LOCATION_ID IN (SELECT LOCATION_ID FROM LOCATION START WITH LOCATION_ID = " + searchBean.getLocation() + " CONNECT BY PRIOR LOCATION_ID = PARENT_LOCATION_ID)");	
//		//		sql.append("AND LK_LOCATION.LOCATION_ID =" + searchBean.getLocation());
//			}
//    */		
//			
//                        
//                          if (searchBean.getProjectId() > 0)		
//			{
//				sql.append("AND DEV_INSTALL_POINT.PROJECT_ID =" + searchBean.getProjectId());
//			}
//                        
//                        if (searchBean.getTownId() > 0)		
//			{
//				sql.append("AND DEV_INSTALL_POINT.TOWN_ID =" + searchBean.getTownId());
//			}
//                        
//                        
//                        if (searchBean.getResultCode() != null && !searchBean.getResultCode().equals("-1"))
//			{
//				//sql.append("AND EXECUTION_RESULT_CODE ='" + searchBean.getResultCode()+"'");
//                                if(searchBean.getResultCode().equals("%"))
//                                        sql.append(" AND EXECUTION_RESULT_CODE LIKE '" + searchBean.getResultCode()+"'");
//                                else
//                                 {
//                                    if(searchBean.getResultCode().equals("L"))
//                                        sql.append(" AND EXECUTION_RESULT_CODE IN ('" + searchBean.getResultCode()+"','R') ");
//                                    else
//                                        sql.append(" AND EXECUTION_RESULT_CODE = '" + searchBean.getResultCode()+"'");
//                                } 
//			}
//                        if (searchBean.getLocationQuery()!="")
//			{
//
//				sql.append(" AND DEV_INSTALL_POINT.SUBDIVISION_ID in( " + searchBean.getLocationQuery()+")");
//
//			}
//                        if (searchBean.getScheduleFrom() != null && searchBean.getScheduleFrom() != null)
//			{
//				schedule_from = df.format(searchBean.getScheduleFrom());
//                                schedule_to = df.format(searchBean.getScheduleTo());
//				//sql.append(" AND DEVICE_SCHEDULE_HISTORY.EXECUTION_END_TIME >= TO_DATE('"+schedule_from+"','yyyy/mm/dd HH24:MI')");
//                                sql.append(" AND DEVICE_SCHEDULE_HISTORY.EXECUTION_END_TIME BETWEEN TO_DATE('"+schedule_from+"','yyyy/mm/dd HH24:MI')  AND TO_DATE('"+schedule_to+"','yyyy/mm/dd HH24:MI') ");                                 
//			}
//                        
//                        if (searchBean.getSchDetId() >0 && searchBean.getSchDetId() >0)
//			{								
//                                sql.append(" AND DEVICE_SCHEDULE_HISTORY.SCHEDULE_DET_ID ="+searchBean.getSchDetId());   
//                                System.out.println("getSchDetId:::"+searchBean.getSchDetId());
//			}
//			
//			//if (searchBean.getScheduleTo() != null)
//			//{
//			//	schedule_to = df.format(searchBean.getScheduleTo());
//			//	sql.append(" AND DEVICE_SCHEDULE_HISTORY.EXECUTION_END_TIME <= TO_DATE('"+schedule_to+"','yyyy/mm/dd HH24:MI')");				
//		//	}
//                        
//                        
//			if (searchBean.getSchedulePeriod() != null && !searchBean.getSchedulePeriod().equals("-1"))
//			{
//				if (searchBean.getSchedulePeriod().equals("1"))
//				{
//					sql.append(" AND TO_DATE(DEVICE_SCHEDULE_HISTORY.EXECUTION_END_TIME,'yyyy/mm/dd') = TO_DATE(SYSDATE , 'yyyy/mm/dd') ");
//
//				}
//				else if (searchBean.getSchedulePeriod().equals("2"))
//				{
//					sql.append(" AND TO_DATE(DEVICE_SCHEDULE_HISTORY.EXECUTION_END_TIME,'yyyy/mm/dd') = TO_DATE((SYSDATE -1) , 'yyyy/mm/dd') ");
//				}
//				else if (searchBean.getSchedulePeriod().equals("3"))
//				{
//					sql.append(" AND TO_DATE(DEVICE_SCHEDULE_HISTORY.EXECUTION_END_TIME,'yyyy/mm/dd') BETWEEN TO_DATE((SYSDATE - 7), 'yyyy/mm/dd') AND TO_DATE(SYSDATE, 'yyyy/mm/dd') ");
//				}
//			} 
//			if (searchBean.getInstallPointType() != null && !searchBean.getInstallPointType().equals("-1"))		
//			{
//				sql.append("AND INSTALL_POINT_TYPE.INSTALL_POINT_TYPE_ID =" + searchBean.getInstallPointType());
//			} 
//                        
//                         if (searchBean.getCONSUMERTypeID() != null && searchBean.getInstallPointType().equals("3")) {
//                          if (searchBean.getCONSUMERTypeID().equals("2") ) 
//                            sql.append("AND CONSUMER.CONSUMER_TYPE_ID = 2 ");
//                          if (searchBean.getCONSUMERTypeID().equals("1") ) 
//                            sql.append("AND CONSUMER.CONSUMER_TYPE_ID = 1 ");
//                          if (searchBean.getCONSUMERTypeID().equals("-1") ) 
//                            sql.append("AND CONSUMER.CONSUMER_TYPE_ID in(1,2) ");
//                       }
//
//			if (!ApplicationUtils.isBlank(searchBean.getNumberId()) && !searchBean.getNumberId().equals("All"))		
//			{
//				sql.append(" AND ( DEV_INSTALL_POINT.CONSUMER_ID like '" + searchBean.getNumberId() + "' ");
//				sql.append("OR DEV_INSTALL_POINT.FEEDER_ID like '" + searchBean.getNumberId() + "' ");
//				sql.append("OR DEV_INSTALL_POINT.TRANSFORMER_ID like '" + searchBean.getNumberId() + "' ");
//				sql.append("OR DEV_INSTALL_POINT.SUBSTATION_ID like '" + searchBean.getNumberId() + "' )");
//			}
//                        if((searchBean.getSearchCriteria() != null && !ApplicationUtils.isBlank(searchBean.getSearchCriteria())) && ((searchBean.getSearchValue() != null && !ApplicationUtils.isBlank(searchBean.getSearchValue()))))
//                        {
//                            sql.append(" AND ");
//                            sql.append(searchBean.getSearchCriteria());
//                            sql.append(" LIKE UPPER('%");
//                            sql.append(searchBean.getSearchValue());
//                            sql.append("%')");
//                        }
//                             if(searchBean.getGroupValue() >0 )
//                        {
//                            sql.append(" AND ");
//                            sql.append(" GROUP_MASTER.GROUP_ID = GROUP_MEMBERS.GROUP_ID");
//                            sql.append(" AND ");
//                            sql.append(" GROUP_MASTER.GROUP_ID ="+searchBean.getGroupValue());
//                            sql.append(" AND ");
//                            sql.append(" GROUP_MEMBERS.DEVICE_ID = DV.DEVICE_ID ");
//                        }

          
          sql.append("SELECT COUNT(1) CNT ");
            sql.append(" FROM ");
            sql.append(" (WITH DEVICE_DETAILS AS ");
            sql.append(" (SELECT DS.DEVICE_SCHEDULE_ID, ");
            sql.append(" DMTR.DEVICE_ID, ");
            sql.append(" DMTR.DEVICE_SERIAL_NO, ");
            sql.append(" CONS.CONSUMER_NUMBER, ");
            sql.append(" CONS.CONSUMER_STATUS, ");
            sql.append(" NT.TRANSFORMER_CD_C, ");
            sql.append(" NF.FEEDER_NAME_C, ");
            sql.append(" NF.FEEDER_CD_C, ");
            sql.append(" NS.SUBSTATION_NAME_C, ");
            sql.append(" NS.SUBSTATION_CD_C, ");
            sql.append(" IPT.INSTALL_POINT_TYPE_ID, ");
            sql.append(" IPT.INSTALL_POINT_TYPE_CD, ");
            sql.append(" DECODE(DMT.DEVICE_TYPE_DESC,NULL, DT.DEVICE_TYPE_DESC, DT.DEVICE_TYPE_DESC||'-'||DMT.DEVICE_TYPE_DESC||'('|| DCLS.CLASS_NAME||')') DEVICE_TYPE_DESC , ");
            sql.append(" DMDM.IPADDRESS, ");
            sql.append(" DMDM.TELEPHONE_NUMBER, ");
            sql.append(" DMDM.DEVICE_SERIAL_NO MDM_SR, ");
            sql.append(" DS.NEXT_EXECUTION_TIME, ");
            sql.append(" CONS.CONSUMER_NAME, ");
            sql.append(" NT.TRANFORMER_NAME_C, ");
            sql.append(" DIP.PROJECT_ID, ");
            sql.append(" P.PROJECT_NAME, ");
            sql.append(" DIP.TOWN_ID, ");
            sql.append(" TM.TOWN_NAME ");
            sql.append(" FROM DEVICE DMTR, ");
            sql.append(" DEVICE DMDM, ");
            sql.append(" DEVICE_CONNECTION DC, ");
            sql.append(" DEVICE_SCHEDULE DS, ");
            sql.append(" CONSUMER CONS, ");
            sql.append(" NDM_TRANSFORMER NT, ");
            sql.append(" NDM_FEEDER NF, ");
            sql.append(" NDM_SUBSTATION NS, ");
            sql.append(" DEV_INSTALL_POINT DIP, ");
            sql.append(" INSTALL_POINT_TYPE IPT, ");
            sql.append(" DEVICE_TYPE DT, ");
            sql.append(" PROJECT P, ");
            sql.append(" TOWN_MASTER TM , ");
            sql.append(" DLMS_METER_TYPE DMT, ");
            sql.append(" DLMS_CLASS DCLS, ");
            sql.append(" ( ");
            if (searchBean.getLocationQuery() != "") {

                sql.append(searchBean.getLocationQuery());

            }
            sql.append(" ) LOC ");
            sql.append(" WHERE DS.DEVICE_ID            = DMTR.DEVICE_ID ");
            sql.append(" AND DMTR.SCHEDULABLE_YN       = 'Y' ");
            sql.append(" AND DC.COMM_DEVICE_ID         = DMDM.DEVICE_ID ");
            sql.append(" AND DMDM.SCHEDULABLE_YN       = 'N' ");
            sql.append(" AND DT.DEVICE_TYPE_ID         = DMTR.DEVICE_TYPE_ID ");
            sql.append(" AND DMTR.DLMS_METER_TYPE_ID   = DMT.DEVICE_TYPE_CD (+) ");
            sql.append(" AND DMTR.DLMS_CLASS_ID        = DCLS.CLASS_ID(+) ");
            sql.append(" AND IPT.INSTALL_POINT_TYPE_ID = DIP.INSTALL_POINT_TYPE_ID ");
            sql.append(" AND DIP.CONSUMER_ID           = CONS.CONSUMER_ID(+) ");
            sql.append(" AND DIP.TRANSFORMER_ID        = NT.TRANSFORMER_ID_N(+) ");
            sql.append(" AND DIP.FEEDER_ID             = NF.FEEDER_ID_N(+) ");
            sql.append(" AND DIP.SUBSTATION_ID         = NS.SUBSTATION_ID_N(+) ");
            sql.append(" AND DIP.PROJECT_ID            = P.PROJECT_ID(+) ");
            sql.append(" AND DIP.TOWN_ID               = TM.TOWN_ID(+) ");
            sql.append(" AND DIP.DEVICE_CONNECTION_ID  = DC.DEVICE_CONNECTION_ID ");
            sql.append(" AND DC.DEVICE_ID              = DMTR.DEVICE_ID ");
            sql.append(" AND DIP.STATUS_CD             = 'A' ");
            sql.append(" AND DS.STATUS_CD             <> 'D' ");
            sql.append(" AND DMTR.STATUS_CD           <> 'D' ");
            sql.append(" AND DC.STATUS_CD             IN ('A','I') ");
            sql.append(" AND DIP.SUBDIVISION_ID        =LOC.LOCATION_ID ");
            
            if (searchBean.getStatusCode() == null || searchBean.getStatusCode().equals("Z")) {
                sql.append(" AND DC.STATUS_CD IN ('A','I') ");
            } else if (searchBean.getStatusCode().equals("W")) {
                sql.append("  AND DC.STATUS_CD IN ('A','I') AND CONS.CONSUMER_STATUS IN('W') ");
            } else {
                sql.append(" AND DC.STATUS_CD = '" + searchBean.getStatusCode() + "' ");
            }
            
            if (searchBean.getDeviceType() != null && !searchBean.getDeviceType().equals("-1")) {
                sql.append(" AND DT.DEVICE_TYPE_ID =" + searchBean.getDeviceType());
            }
            
            if (searchBean.getProjectId() > 0) {
                sql.append(" AND DIP.PROJECT_ID =" + searchBean.getProjectId());
            }

            if (searchBean.getTownId() > 0) {
                sql.append(" AND DIP.TOWN_ID =" + searchBean.getTownId());
            }
            
            if (searchBean.getInstallPointType() != null && !searchBean.getInstallPointType().equals("-1")) {
                sql.append(" AND IPT.INSTALL_POINT_TYPE_ID =" + searchBean.getInstallPointType());
            }

            if (searchBean.getConsumerTypeId() != null && searchBean.getInstallPointType().equals("3")) {
                if (searchBean.getConsumerTypeId().equals("2")) {
                    sql.append(" AND CONS.CONSUMER_TYPE_ID = 2 ");
                }
                if (searchBean.getConsumerTypeId().equals("1")) {
                    sql.append(" AND CONS.CONSUMER_TYPE_ID = 1 ");
                }
                if (searchBean.getConsumerTypeId().equals("-1")) {
                    sql.append(" AND CONS.CONSUMER_TYPE_ID in(1,2) ");
                }
            }

            if (!ApplicationUtils.isBlank(searchBean.getNumberId()) && !searchBean.getNumberId().equals("All")) {
                sql.append(" AND ( DIP.CONSUMER_ID like '" + searchBean.getNumberId() + "' ");
                sql.append(" OR DIP.FEEDER_ID like '" + searchBean.getNumberId() + "' ");
                sql.append(" OR DIP.TRANSFORMER_ID like '" + searchBean.getNumberId() + "' ");
                sql.append(" OR DIP.SUBSTATION_ID like '" + searchBean.getNumberId() + "' )");
            }
            if ((searchBean.getSearchCriteria() != null && !ApplicationUtils.isBlank(searchBean.getSearchCriteria())) && ((searchBean.getSearchValue() != null && !ApplicationUtils.isBlank(searchBean.getSearchValue())))) {
                sql.append(" AND ");
                sql.append(searchBean.getSearchCriteria());
                sql.append(" LIKE UPPER('%");
                sql.append(searchBean.getSearchValue());
                sql.append("%')");
            }
//sql.append(" --AND DMTR.DEVICE_SERIAL_NO LIKE UPPER('%09051988%') ");
            sql.append(" )SELECT * FROM DEVICE_DETAILS ) DD, ");

            sql.append(" (WITH DEVICE_SCHEDULE_HISTORY_VIEW AS ");
            sql.append(" (SELECT DSH.DEVICE_SCHEDULE_HISTORY_ID, ");
            sql.append(" DSH.DEVICE_SCHEDULE_ID, ");
            sql.append(" DSH.DEVICE_ID, ");
            sql.append(" DSH.EXECUTION_START_TIME, ");
            sql.append(" DSH.EXECUTION_END_TIME, ");
            sql.append(" DSH.EXECUTION_RESULT_CODE, ");
            sql.append(" DSH.EXECUTION_RESULT, ");
            sql.append(" DSH.SCHEDULE_WISE_LOG ");
            sql.append(" FROM DEVICE_SCHEDULE_HISTORY DSH ");
            sql.append(" WHERE DSH.EXECUTION_START_TIME > TRUNC(ADD_MONTHS(SYSDATE,-6)) ");

            sql.append(" AND DSH.EXECUTION_END_TIME                         IS NOT NULL ");
            sql.append(" AND DSH.EXECUTION_RESULT_CODE                      IS NOT NULL ");
            sql.append(" AND (DSH.DEVICE_ID,DSH.DEVICE_SCHEDULE_HISTORY_ID) IN ");
            sql.append(" (SELECT DEVICE_ID, ");
            sql.append(" MAX(DEVICE_SCHEDULE_HISTORY_ID) DEVICE_SCHEDULE_HISTORY_ID ");
            sql.append(" FROM DEVICE_SCHEDULE_HISTORY ");
            sql.append(" WHERE EXECUTION_START_TIME > TRUNC(ADD_MONTHS(SYSDATE,-6)) ");
            sql.append(" AND EXECUTION_END_TIME    IS NOT NULL ");
            sql.append(" AND EXECUTION_RESULT_CODE IS NOT NULL ");
            
            if (searchBean.getResultCode() != null && !searchBean.getResultCode().equals("-1") && !searchBean.getResultCode().equals("%")) {

                if (searchBean.getResultCode().equals("L")) {
                    sql.append(" AND EXECUTION_RESULT_CODE IN ('" + searchBean.getResultCode() + "','R') ");
                } else {
                    sql.append(" AND EXECUTION_RESULT_CODE = '" + searchBean.getResultCode() + "'");
                }
                            if (searchBean.getScheduleFrom() != null && searchBean.getScheduleFrom() != null) {
                schedule_from = df.format(searchBean.getScheduleFrom());
                schedule_to = df.format(searchBean.getScheduleTo());
                //sql.append(" AND DEVICE_SCHEDULE_HISTORY.EXECUTION_END_TIME >= TO_DATE('"+schedule_from+"','yyyy/mm/dd HH24:MI')");
                sql.append(" AND EXECUTION_END_TIME BETWEEN TO_DATE('" + schedule_from + "','yyyy/mm/dd HH24:MI')  AND TO_DATE('" + schedule_to + "','yyyy/mm/dd HH24:MI') ");
            }


            if (searchBean.getSchDetId() > 0 && searchBean.getSchDetId() > 0) {
                sql.append(" AND SCHEDULE_DET_ID =" + searchBean.getSchDetId());
                //System.out.println("getSchDetId:::" + searchBean.getSchDetId());
            }

            if (searchBean.getSchedulePeriod() != null && !searchBean.getSchedulePeriod().equals("-1")) {
                if (searchBean.getSchedulePeriod().equals("1")) {
                    sql.append(" AND trunc(EXECUTION_END_TIME) = trunc(SYSDATE) ");

                } else if (searchBean.getSchedulePeriod().equals("2")) {
                    sql.append(" AND trunc(EXECUTION_END_TIME) = trunc(SYSDATE -1) ");
                } else if (searchBean.getSchedulePeriod().equals("3")) {
                    sql.append(" AND trunc(EXECUTION_END_TIME) between trunc(SYSDATE - 7) AND trunc(SYSDATE-1) ");
                }
            }

            }
            sql.append(" GROUP BY DEVICE_ID) ");
            sql.append(" )SELECT * FROM DEVICE_SCHEDULE_HISTORY_VIEW) DSH ");
            sql.append(" WHERE DSH.DEVICE_ID       =DD.DEVICE_ID ");
            sql.append(" AND DSH.DEVICE_SCHEDULE_ID=DD.DEVICE_SCHEDULE_ID ");

            if (!ApplicationUtils.isBlank(searchBean.getSortColumnName()) && !ApplicationUtils.isBlank(searchBean.getLastRowValue()) && !ApplicationUtils.isBlank(searchBean.getLastValueDataType())) {
                if (!ApplicationUtils.isBlank(searchBean.getSortOrder()) && (searchBean.getSortOrder()).equals("ASC")) {
                    sql.append(ApplicationUtils.getQueryConditionString(searchBean.getSortColumnName(), searchBean.getLastRowValue(), searchBean.getLastValueDataType(), ">"));
                } else {
                    sql.append(ApplicationUtils.getQueryConditionString(searchBean.getSortColumnName(), searchBean.getLastRowValue(), searchBean.getLastValueDataType(), "<"));
                }
            }


//            if (searchBean.getGroupValue() > 0) {
//                sql.append(" AND ");
//                sql.append(" GROUP_MASTER.GROUP_ID = GROUP_MEMBERS.GROUP_ID");
//                sql.append(" AND ");
//                sql.append(" GROUP_MASTER.GROUP_ID =" + searchBean.getGroupValue());
//                sql.append(" AND ");
//                sql.append(" GROUP_MEMBERS.DEVICE_ID = DMTR.DEVICE_ID ");
//            }

            //-- Appending Order By Clause--
           
			statement = connection.prepareStatement(sql.toString());
			logger.log(Level.INFO, "GetSearchTotalRecordsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
			rs = statement.executeQuery();
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetSearchTotalRecordsQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
}