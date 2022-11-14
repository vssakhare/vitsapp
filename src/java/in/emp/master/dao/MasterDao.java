package in.emp.master.dao;

//-- Java Imports
import in.emp.master.bean.MasterBean;
import in.emp.master.bean.LocationBean;
import in.emp.master.bean.LocationTypeBean;

import java.util.LinkedList;



/**
 * Data access object interface for returning document related value objects from the database.
 *
 * @author	MDA
 */

public interface MasterDao
{


	/*
	* API to get the all master list
	* @param String		the tableName
	* @throws Exception		if an error occurs 
	* @returns			List
	*/
	public LinkedList getMasterList(String tableName, String column1, String column2, String column3, String whereClause)throws Exception;
        
        public LinkedList getMasterListSSO(String tableName, String column1, String column2, String column3, String whereClause)throws Exception;

        public LinkedList getMasterListForNDM(String tableName, String column1, String column2, String column3, String statusCDColumn, String whereClause)throws Exception;
	/*
	* API to get the Location list from the Database
	* @throws Exception		if an error occurs 
	* @returns			LinkedList
	*/
	public LinkedList getLocations(LocationBean locationBean) throws Exception;

}//end class