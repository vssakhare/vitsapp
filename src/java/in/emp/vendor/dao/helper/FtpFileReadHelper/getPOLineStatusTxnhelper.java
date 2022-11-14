/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.FtpFileReadHelper;

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
 * @author pooja
 */
public class getPOLineStatusTxnhelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(getPOStatusTxnhelper.class);
    private PoLineStatusBean poLinestatusbeanobj;
    List<PoLineStatusBean>	lstErpToVitsFileFormat;
    private VendorPrezData vendorPrezDataObj;

    /**
     * @public Constructor TAclaimsTxnHelper()
     * @param vendorBeanObj object of TAclaimsBean
     */
    public getPOLineStatusTxnhelper()
    {
        
    }
    
    public getPOLineStatusTxnhelper(List lstErpToVitsFileFormat) {
        this.lstErpToVitsFileFormat = lstErpToVitsFileFormat;
    }//constructor ends

    /**
     * @public Constructor TAclaimsTxnHelper()
     *
     * @param vendorPrezDataObj	TAclaimsPrezData
     */
    public getPOLineStatusTxnhelper(VendorPrezData vendorPrezDataObj) {
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
    public Object createObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
       CallableStatement proc_stmt = null;
        int[] count;
       
        try {
            logger.log(Level.INFO, "getPOLineStatusTxnhelper ::: createObject() :: method called ::");
             //poLinestatusbeanobj.setPoLineId(ApplicationUtils.getNextSequenceId(conn, "XXMIS_ERP_PO_LINE_SEQ").toString());   
if(lstErpToVitsFileFormat != null && lstErpToVitsFileFormat.size() >0)
	{
	logger.log(Level.INFO, "lstErpToVitsFileFormat.size() ::"+lstErpToVitsFileFormat.size());
        System.out.println("lstErpToVitsFileFormat.size() ::"+lstErpToVitsFileFormat.size());
      //  System.out.println("Inserting records to PO_LINE_STATUS");
	 StringBuilder sql = new StringBuilder();
       
       
              
          sql.append(" INSERT INTO PO_LINE_STATUS_TEMP ");
            sql.append(" ( PO_LINE_ID,Contract_Document, Item	,Deletion_Indicator, Last_Changed_on,	Short_Text	, Material,	Company_Code,	 Plant	, Storage_Location,"); // 9 here
            sql.append(" Material_Group,	  Order_Quantity	,Order_Unit,Per_unit_price,Net_Order_Value ,Gross_order_value,Tax_code, "); // 7 till here
            sql.append(" Valuation_Type,Valuation_Category,Delivery_Completed ,ITEM_CATEGORY,Acct_Assignment_Cat,Outline_Agreement ,Funds_Center, "); // 7 till here
            sql.append("   Commitment_Item ,Profit_Center, "); //2 HERE
            sql.append("   Purchasing_Group ,Purchasing_Desc,Total_Value_Limit, ");//3 HERE
            sql.append("   Po_From_Date ,Po_To_Date,Purchasing_Org ,");//3 HERE
             sql.append("   Po_Type ,Vendor_Number,Vendor_Name, ");//3 HERE
            sql.append("  Po_Doc_Date,Created_By, DB_INSERT_DATE) ");//2 HERE
            sql.append(" VALUES ");
            sql.append(" ( XXMIS_ERP_PO_LINE_SEQ.NEXTVAL,?, ?, ?, ?, ?, ?, ?, ?, ?,?, "); // 10 here
            sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?,?, "); // 10 till here
            sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?, "); // 11 till here
            sql.append(" ?,?,?,?,?,sysdate) "); // 1 + 9 till here


            statement = conn.prepareStatement(sql.toString());
           
             for (PoLineStatusBean poLinestatusbeanobj : lstErpToVitsFileFormat) {
//statement.setString(1,poLinestatusbeanobj.getPoLineId());             
statement.setString(1,poLinestatusbeanobj.getContract_Document());   
statement.setString(2,poLinestatusbeanobj.getItem());		 
statement.setString(3,poLinestatusbeanobj.getDeletion_Indicator());
statement.setDate(4, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(poLinestatusbeanobj.getLast_Changed_on(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
	 
statement.setString(5,poLinestatusbeanobj.getShort_Text());	  
statement.setString(6,poLinestatusbeanobj.getMaterial());	     
statement.setString(7,poLinestatusbeanobj.getCompany_Code()); 
statement.setString(8,poLinestatusbeanobj.getPlant());		 
statement.setString(9,poLinestatusbeanobj.getStorage_Location());   
statement.setString(10,poLinestatusbeanobj.getMaterial_Group());  
statement.setString(11,poLinestatusbeanobj.getOrder_Quantity());		 
	 
statement.setString(12,poLinestatusbeanobj.getOrder_Unit());  
statement.setString(13,poLinestatusbeanobj.getPer_unit_price());	 
statement.setString(14,poLinestatusbeanobj.getNet_Order_Value());    
statement.setString(15,poLinestatusbeanobj.getGross_order_value());  
statement.setString(16,poLinestatusbeanobj.getTax_code());	     
statement.setString(17,poLinestatusbeanobj.getValuation_Type());	 
statement.setString(18,poLinestatusbeanobj.getValuation_Category());
statement.setString(19,poLinestatusbeanobj.getDelivery_Completed());
statement.setString(20,poLinestatusbeanobj.getItem_Category());	 
statement.setString(21,poLinestatusbeanobj.getAcct_Assignment_Cat());
statement.setString(22,poLinestatusbeanobj.getOutline_Agreement());  
statement.setString(23,poLinestatusbeanobj.getFunds_Center());	 
statement.setString(24,poLinestatusbeanobj.getCommitment_Item());	 
statement.setString(25,poLinestatusbeanobj.getProfit_Center());
statement.setString(26,poLinestatusbeanobj.getPurchasing_group());
statement.setString(27,poLinestatusbeanobj.getPurchasing_desc());  
statement.setString(28,poLinestatusbeanobj.getTotal_value_limit());
statement.setDate(29,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(poLinestatusbeanobj.getPo_from_date(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT)); 
statement.setDate(30,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(poLinestatusbeanobj.getPo_to_date(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));	   
statement.setString(31,poLinestatusbeanobj.getPurchasing_org());   
statement.setString(32,poLinestatusbeanobj.getPo_type());	   
statement.setString(33,poLinestatusbeanobj.getVendor_number());    
statement.setString(34,poLinestatusbeanobj.getVendor_name());	   
statement.setDate(35,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(poLinestatusbeanobj.getPo_doc_date(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));	   
statement.setString(36, poLinestatusbeanobj.getCreated_by());

 
        //    logger.log(Level.INFO, "getPOLineStatusTxnhelper ::: createObject() :: SQL :: " + sql.toString());
            
             statement.addBatch();
                }

            count = new int[lstErpToVitsFileFormat.size()];

                count = statement.executeBatch();
          
         conn.commit(); 
 
        }

        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "getPOLineStatusTxnhelper ::: createObject() :: Exception :: " + ex);
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
    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        CallableStatement proc_stmt = null;
        int count = 0;
        try {
            logger.log(Level.INFO, "getPOLineStatusTxnhelper ::: updateObject() :: method called ::");

           System.out.println("Calling Procedure PO_LINE_RECORDS_INSERT After changes");
        proc_stmt = conn.prepareCall("{ call PO_LINE_RECORDS_INSERT }");
       proc_stmt.executeQuery();
       System.out.println("Finished Calling PO_LINE_RECORDS_INSERT Procedure");
              
         //   logger.log(Level.INFO, "getPOLineStatusTxnhelper ::: updateObject() :: SQL :: " + sql.toString());

            
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "getPOLineStatusTxnhelper ::: updateObject() :: Exception :: " + ex);
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
            logger.log(Level.INFO, "getPOLineStatusTxnhelper ::: deleteObject() :: method called ::");

            sql.append("truncate table PO_LINE_STATUS_TEMP ");
            
            statement = conn.prepareStatement(sql.toString());
           

          //  logger.log(Level.INFO, "getPOLineStatusTxnhelper ::: deleteObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();

            conn.commit();
            System.out.println("deleted records of table PO_LINE_STATUS_TEMP");
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "getPOLineStatusTxnhelper ::: deleteObject() :: Exception :: " + ex);
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