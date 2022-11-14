/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.PoLineDetail;

/**
 *
 * @author Pooja Jadhav
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//-- Java imports
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.*;
import in.emp.common.ApplicationConstants;
import in.emp.common.Partition;

import in.emp.dao.TxnHelper;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.PoLineStatusBean;

import in.emp.vendor.bean.VendorStatuBean;
import java.sql.CallableStatement;
import org.apache.commons.collections.ListUtils;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja
 */
public class getSelectedPOLineStatusTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(getSelectedPOLineStatusTxnHelper.class);
    private PoLineStatusBean poLinestatusbeanobj;
    LinkedList<PoLineStatusBean> selectedpoLineDetails;
    private VendorPrezData vendorPrezDataObj;

    /**
     * @public Constructor TAclaimsTxnHelper()
     * @param vendorBeanObj object of TAclaimsBean
     */
    public getSelectedPOLineStatusTxnHelper() {

    }

    public getSelectedPOLineStatusTxnHelper(LinkedList selectedpoLineDetails) {
        this.selectedpoLineDetails = selectedpoLineDetails;
    }//constructor ends

    /**
     * @public Constructor TAclaimsTxnHelper()
     *
     * @param vendorPrezDataObj	TAclaimsPrezData
     */
    public getSelectedPOLineStatusTxnHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.poLinestatusbeanobj = this.vendorPrezDataObj.getPolinestatusList();
    }//constructor ends

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    //
    public Object createObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        int[] count;
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "getSelectedPOLineStatusTxnHelper ::: createObject() :: method called ::");
            if (selectedpoLineDetails != null && selectedpoLineDetails.size() > 0) {
                logger.log(Level.INFO, "selectedpoLineDetails.size() :: 	" + selectedpoLineDetails.size());
                deleteObject(conn); // call delete to remove all the previous selected line items 
                sql.append(" INSERT INTO PO_LINE_INV_DETAILS ");
                sql.append(" ( PO_LINE_ID,Contract_Document,Short_Text,Plant,VENDOR_NUMBER,APPL_ID,INVOICE_NUMBER,CREATED_TIME_STAMP,PURCHASING_GROUP,PURCHASING_DESC )	"); // 9 here
                sql.append(" VALUES ");
                sql.append(" ( ?,?,?,?,?,?,?,SYSTIMESTAMP,?,? ) "); // 1 + 9 till here

                statement = conn.prepareStatement(sql.toString());
                for (PoLineStatusBean poLinestatusbeanobj : selectedpoLineDetails) {
                    statement.setString(1, poLinestatusbeanobj.getPoLineId());
                    statement.setString(2, poLinestatusbeanobj.getContract_Document());
                    statement.setString(3, poLinestatusbeanobj.getShort_Text());
                    statement.setString(4, poLinestatusbeanobj.getPlant());
                    statement.setString(5, poLinestatusbeanobj.getVendor_Number());
                    statement.setString(6, poLinestatusbeanobj.getAPPL_ID());
                    statement.setString(7, poLinestatusbeanobj.getINVOICE_NUMBER());
                     statement.setString(8, poLinestatusbeanobj.getPurchasing_group());
                       statement.setString(9, poLinestatusbeanobj.getPurchasing_desc());
                    statement.addBatch();
                }

                logger.log(Level.INFO, "getSelectedPOLineStatusTxnHelper ::: createObject() :: SQL :: " + sql.toString());
                count = new int[selectedpoLineDetails.size()];

                count = statement.executeBatch();

                conn.commit();
            }
//System.out.println("Calling Procedure po After changes");
            //proc_stmt = conn.prepareCall("{ call PO_RECORDS_INSERT_FROM_SAP }");
            // proc_stmt.executeQuery();
            //  System.out.println("Finished Calling PO Procedure");
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "getSelectedPOLineStatusTxnHelper ::: createObject() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;

        }
        return vendorPrezDataObj;
    }//createObject() ends

    /**
     * Public API to update data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    
    //FIRST UPDATE OBJECT IS CALLED 
    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "getSelectedPOLineStatusTxnHelper ::: updateObject() :: method called ::");

            createObject(conn);
            logger.log(Level.INFO, "getSelectedPOLineStatusTxnHelper ::: updateObject() :: SQL :: " + sql.toString());

        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "getSelectedPOLineStatusTxnHelper ::: updateObject() :: Exception :: " + ex);
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
        int[] count;
        
        try {
            logger.log(Level.INFO, "getSelectedPOLineStatusTxnHelper ::: deleteObject() :: method called ::");
            if (selectedpoLineDetails != null && selectedpoLineDetails.size() > 0) {
                logger.log(Level.INFO, "selectedpoLineDetails.size() :: 	" + selectedpoLineDetails.size());

             sql.append(" UPDATE   PO_LINE_INV_DETAILS ");
            sql.append(" SET STATUS_CD='D'");
            sql.append(" WHERE APPL_ID=?");
                statement = conn.prepareStatement(sql.toString());
                for (PoLineStatusBean poLinestatusbeanobj : selectedpoLineDetails) {
                      statement.setString(1, poLinestatusbeanobj.getAPPL_ID());
                    statement.addBatch();
                }

                logger.log(Level.INFO, "getSelectedPOLineStatusTxnHelper ::: deleteObject() :: SQL :: " + sql.toString());
                count = new int[selectedpoLineDetails.size()];

                count = statement.executeBatch();

                conn.commit();
            }
} catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "getSelectedPOLineStatusTxnHelper ::: deleteObject() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;

        }
    }
        //deleteObject() ends

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
