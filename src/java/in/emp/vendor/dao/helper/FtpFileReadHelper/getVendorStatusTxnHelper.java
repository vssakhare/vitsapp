/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.FtpFileReadHelper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.*;

import in.emp.dao.TxnHelper;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.bean.VendorStatuBean;
import java.sql.CallableStatement;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class getVendorStatusTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(getVendorStatusTxnHelper.class);
    private VendorStatuBean vendorstatusbeanobj;
    List<VendorStatuBean>	listErpToVitsFileFormat;
    private VendorPrezData vendorPrezDataObj;

    /**
     * @public Constructor TAclaimsTxnHelper()
     * @param vendorBeanObj object of TAclaimsBean
     */
    public getVendorStatusTxnHelper(List listErpToVitsFileFormat) {
        this.listErpToVitsFileFormat = listErpToVitsFileFormat;
    }//constructor ends

    /**
     * @public Constructor TAclaimsTxnHelper()
     *
     * @param vendorPrezDataObj	TAclaimsPrezData
     */
      public getVendorStatusTxnHelper()
    {
        
    }
    public getVendorStatusTxnHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorstatusbeanobj = this.vendorPrezDataObj.getVendorstatusList();
    }//constructor ends

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    public Object createObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        
      CallableStatement proc_stmt = null;
       
           int[] count;
         StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "getVendorStatusTxnHelper ::: createObject() :: method called ::");
if(listErpToVitsFileFormat != null && listErpToVitsFileFormat.size() >0)
	{
				logger.log(Level.INFO, "listErpToVitsFileFormat.size() :: 	"+listErpToVitsFileFormat.size());
				//Iterator<VendorStatuBean> itr = listErpToVitsFileFormat.iterator(); 
		sql.append(" INSERT INTO erp_vendor_master_temp ");
            sql.append(" (VENDOR_CODE ,VENDOR_NAME,GST_REG_NUM , CITY  ,PINCODE,VENDOR_REGION ,VENDOR_STATUS ,PHN_LL,PHN_MOB ,EMAIL ,PANNO,TAXCODE,VENDOR_DB_INSERT_DATE ) "); // 4 here
             
            sql.append(" VALUES ");
            sql.append(" ( ?, ?, ?, "); // 2 here
            sql.append(" ?, ?, ?, ?, ?, ?,?,?,?, SYSTIMESTAMP ) "); // 1 + 9 till here
 statement = conn.prepareStatement(sql.toString());
             for (VendorStatuBean vendorstatusbeanobj : listErpToVitsFileFormat) {

            statement.setString(1, vendorstatusbeanobj.getVENDOR_CODE());
            statement.setString(2, vendorstatusbeanobj.getVENDOR_NAME());
            statement.setString(3, vendorstatusbeanobj.getGST_REG_NUM());
            statement.setString(4, vendorstatusbeanobj.getCITY());
            statement.setString(5, vendorstatusbeanobj.getPINCODE());
            statement.setString(6, vendorstatusbeanobj.getVENDOR_REGION());
            statement.setString(7, vendorstatusbeanobj.getVENDOR_STATUS());
            statement.setString(8, vendorstatusbeanobj.getPHN_LL());
            statement.setString(9, vendorstatusbeanobj.getPHN_MOB());
            statement.setString(10, vendorstatusbeanobj.getEMAIL());
            statement.setString(11, vendorstatusbeanobj.getPAN_NO());
            statement.setString(12, vendorstatusbeanobj.getTAX_CODE());
            statement.addBatch();
                }
            
            logger.log(Level.INFO, "getVendorStatusTxnhelper ::: createObject() :: SQL :: " + sql.toString());
            
            count = new int[listErpToVitsFileFormat.size()];

                count = statement.executeBatch();
          
         conn.commit(); 
        }
//System.out.println("Calling Procedure vendor");
       

        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "getVendorStatusTxnhelper ::: createObject() :: Exception :: " + ex);
             ex.printStackTrace();
             throw ex;
           
        }
        // System.out.println("End vendor procedure");
        return vendorPrezDataObj;
    }//createObject() ends

    /**
     * Public API to update data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
          CallableStatement proc_stmt = null;
        try {
            logger.log(Level.INFO, "getVendorStatusTxnhelper ::: updateObject() :: method called ::");

           proc_stmt = conn.prepareCall("{ call PROC_VENDOR_INSERT_FROM_CURSOR }");
        proc_stmt.executeQuery();
        System.out.println("Finished Calling vendor Procedure");
            logger.log(Level.INFO, "getVendorStatusTxnhelper ::: updateObject() :: SQL :: " + sql.toString());

            
            
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "VendorApplTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }
       

    }//updateObject() ends

    /**
     * Public API to delete data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    public void deleteObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "getVendorStatusTxnhelper ::: deleteObject() :: method called ::");

            sql.append("truncate table erp_vendor_master_temp ");
           

            statement = conn.prepareStatement(sql.toString());
           
           

            logger.log(Level.INFO, "getVENDORStatusTxnhelper ::: deleteObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();

            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "getVENDORStatusTxnhelper ::: deleteObject() :: Exception :: " + ex);
            throw ex;
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception ignored) {
                }
            }
        }
    }//deleteObject() ends

    /**
     * Public API to update ObjectAttribute
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
    }//updateObjectAttribute ends
}