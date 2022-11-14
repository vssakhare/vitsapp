/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.txnHelper;

//-- Java imports
import java.sql.PreparedStatement;

import java.sql.Types;
import java.sql.Connection;
import java.util.*;
import in.emp.common.ApplicationConstants;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.CallableStatement;

import in.emp.dao.TxnHelper;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.util.ApplicationUtils;
import in.emp.util.TxnUtility;
//import in.emp.vendor.dao.helper.TAclaimsFormJdtlTxnHelper;


//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
//import org.json.simple.JSONObject;

/**
 *
 * @author Prajakta
 */
public class VendorApplFormTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(VendorApplFormTxnHelper.class);
    private VendorInputBean vendorInputBeanObj;
    private VendorPrezData vendorPrezDataObj;

    /**
     * @public Constructor VendorApplFormTxnHelper()
     * @param vendorInputBeanObj object of VendorInputBean
     */
    public VendorApplFormTxnHelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }//constructor ends

    /**
     * @public Constructor VendorApplFormTxnHelper()
     *
     * @param vendorPrezDataObj	VendorPrezData
     */
    public VendorApplFormTxnHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorInputBeanObj = this.vendorPrezDataObj.getVendorInputBean();
    }//constructor ends

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    @Override
    public Object createObject(Connection conn) throws Exception {
        int count = 0;
        PreparedStatement statement = null;
        //PreparedStatement statement1 = null;
         CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "VendorApplFormTxnHelper ::: createObject() :: method called ::");
            
             vendorInputBeanObj = vendorPrezDataObj.getVendorInputBean();
       
                            vendorInputBeanObj.setApplId(ApplicationUtils.getNextSequenceId(conn, "XXMIS_ERP_VENDOR_DTL_SEQ").toString());
              //   getSelectedModuleType is used to differentiate between project system and po number         
          // sql.append(" INSERT INTO XXMIS_ERP_VENDOR_DTL ");
            //sql.append(" ( ID, APPL_ID, PO_NUMBER, INVOICE_NUMBER, INVOICE_AMOUNT, "); // 6 here
            //sql.append(" INVOICE_DATE, CREATED_TIME_STAMP, INWARD_NUMBER, INWARD_DATE, SAVE_FLAG, UPDATED_TIME_STAMP, INVOICE_FROM_DT, INVOICE_TO_DT, VENDOR_NUMBER, VENDOR_NAME ) "); // 10 till here            
           // sql.append(" VALUES ");
           // sql.append(" ( TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD') || 000000 ) + XXMIS_ERP_VENDOR_DTL_SEQ.NEXTVAL, ?, "); // 1 here            
           // sql.append("  ?, ?, ?, "); // 1 + 2 here
           //sql.append(" ?, SYSTIMESTAMP, ?, ?, '10', null, ?, ?, ?, ? ) "); 
            
            sql.append( "insert into XXMIS_ERP_VENDOR_DTL " );
            sql.append(" ( ID, APPL_ID, PO_NUMBER, INVOICE_NUMBER, INVOICE_AMOUNT, "); // 6 here
            sql.append(" INVOICE_DATE, CREATED_TIME_STAMP, INWARD_NUMBER, INWARD_DATE, SAVE_FLAG, UPDATED_TIME_STAMP, INVOICE_FROM_DT, INVOICE_TO_DT, ");
            sql.append(" VENDOR_NUMBER, VENDOR_NAME,LOCATION,VENDOR_INWARD_DATE,MODULE_TYPE,MODULE_SAVE_FLAG,SUBMIT_AT_LOCATION,SUBMIT_AT_DESC,PURCHASING_DESC,PURCHASING_GROUP,SELECTED_PLANT,CREATED_BY_ID,CREATED_BY_NAME,CREATED_BY_DESIGNATION,CREATED_BY_USERTYPE  ) ");
            sql.append(" SELECT  TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD') || 000000 ) + XXMIS_ERP_VENDOR_DTL_SEQ.CURRVAL, ?, "); // 1 here            
            sql.append("  ?, ?, ?, "); // 1 + 2 here
            sql.append(" ?, SYSTIMESTAMP, ?, ?, '10', SYSTIMESTAMP, ?, ?, ?, ?, ?, ?, ?,'PM_MM',?,?,?,?,?,?,?,?,?  FROM dual ");
            sql.append("  WHERE NOT EXISTS(SELECT 1 FROM XXMIS_ERP_VENDOR_DTL,fiscal_year where VENDOR_NUMBER=? and PO_NUMBER=? ");
            sql.append(" and REGEXP_REPLACE(UPPER(INVOICE_NUMBER), '[^0-9A-Za-z]', '')=REGEXP_REPLACE(UPPER(?), '[^0-9A-Za-z]', '') ");
            sql.append(" and ? between ");
             sql.append(" to_date(fiscal_year.FISCAL_START,'DD-MON-RRRR') ");
                  
             sql.append("   AND  to_date(fiscal_year.FISCAL_END,'DD-MON-RRRR')  ");
              sql.append("      ) ");
   
            
            // 7 + 3 till here
            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, vendorInputBeanObj.getApplId());           
            statement.setString(2, vendorInputBeanObj.getPONumber());
            statement.setString(3, vendorInputBeanObj.getVendorInvoiceNumber());
            statement.setString(4, vendorInputBeanObj.getVendorInvoiceAmount());
            statement.setDate(5, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
          statement.setString(6, vendorInputBeanObj.getInwardNumber());
            statement.setDate(7, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
             statement.setDate(8, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
              statement.setDate(9, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
              statement.setString(10, vendorInputBeanObj.getVendorNumber()); 
               statement.setString(11, vendorInputBeanObj.getVendorName()); 
                statement.setString(12, vendorInputBeanObj.getLocation()); 
               /* if(!ApplicationUtils.isBlank(vendorInputBeanObj.getSelectedModule())){
                statement.setString(13, vendorInputBeanObj.getSelectedModule());
                }
                else 
                {statement.setString(13," ");}*/
                 statement.setDate(13, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
        statement.setString(14, vendorInputBeanObj.getSelectedModuleType());
             statement.setString(15, vendorInputBeanObj.getSubmitAtPlant());   
              statement.setString(16, vendorInputBeanObj.getSubmitAtDesc());  
                 statement.setString(17, vendorInputBeanObj.getPurchasingDesc());
  statement.setString(18, vendorInputBeanObj.getPurchasing_group());
 statement.setString(19, vendorInputBeanObj.getSELECTEDPLANT());
statement.setString(20, vendorInputBeanObj.getEMP_ID());
statement.setString(21, vendorInputBeanObj.getEMP_NAME());
statement.setString(22, vendorInputBeanObj.getEMP_DESIGNATION());
statement.setString(23, vendorInputBeanObj.getCREATED_USER_TYPE());
//insert from here  
            statement.setString(24, vendorInputBeanObj.getVendorNumber());
              statement.setString(25, vendorInputBeanObj.getPONumber());
             statement.setString(26, vendorInputBeanObj.getVendorInvoiceNumber());
                         statement.setDate(27, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
             
                         logger.log(Level.INFO, "VendorApplFormTxnHelper ::: createObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            if (count == 0)
            vendorInputBeanObj.setApplId("");
                    
            if (count > 0)
            {
                
                vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
              
                    System.out.println("Calling Procedure PROC_SUMMARY_UPD BEFORE changes");
                 String  appl_id=vendorInputBeanObj.getApplId();
                 int appl_id1=Integer.parseInt(appl_id);  
               proc_stmt = conn.prepareCall("{ call PROC_SUMMARY_UPD(?) }");
               proc_stmt.setString(1, vendorInputBeanObj.getApplId());
                proc_stmt.executeQuery();
                  System.out.println(" Calling Procedure PROC_SUMMARY_UPD AFTER changes ");
                    conn.commit();
            }
             return vendorPrezDataObj;
          
            
        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
            logger.log(Level.ERROR, "VendorApplFormTxnHelper ::: createObject() :: Exception :: " + ex);
             throw ex;
        }

    }//createObject() ends

    /**
     * Public API to update data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    @Override
    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
         CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
      
        String sf = "10";
        try {
            logger.log(Level.INFO, "VendorApplFormTxnHelper ::: updateObject() :: method called ::");
            vendorInputBeanObj = vendorPrezDataObj.getVendorInputBean();
            
            if (vendorInputBeanObj.getSaveFlag() != null) {
                if (vendorInputBeanObj.getSaveFlag().equals("Saved")) {
                    sf = "10";
                } else if (vendorInputBeanObj.getSaveFlag().equals("Submitted")) {
                    sf = "20";
                }
                else if (vendorInputBeanObj.getSaveFlag().equals("Verified")) {
                    sf = "30";
                }
                else if (vendorInputBeanObj.getSaveFlag().equals("Rejected")) {
                    sf = "40";
                }
                 else if (vendorInputBeanObj.getSaveFlag().equals("Forwarded")) {
                    sf = "50";
                }
            }
             if(sf.equals("10") && vendorInputBeanObj.getModuleSaveFlag().equals("NON_PO")){
                 sql.append( "insert into XXMIS_ERP_VENDOR_DTL " );
            sql.append(" ( ID, APPL_ID, PO_NUMBER, INVOICE_NUMBER, INVOICE_AMOUNT, "); // 6 here
            sql.append(" INVOICE_DATE, CREATED_TIME_STAMP, INWARD_NUMBER, INWARD_DATE, SAVE_FLAG, UPDATED_TIME_STAMP, INVOICE_FROM_DT, INVOICE_TO_DT, VENDOR_NUMBER, VENDOR_NAME,LOCATION,VENDOR_INWARD_DATE,MODULE_TYPE,MODULE_SAVE_FLAG,SUBMIT_AT_LOCATION,SUBMIT_AT_DESC,PURCHASING_DESC,PURCHASING_GROUP,SELECTED_PLANT) ");
            sql.append(" SELECT  TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD') || 000000 ) + XXMIS_ERP_VENDOR_DTL_SEQ.NEXTVAL, ?, "); // 1 here            
            sql.append("  ?, ?, ?, "); // 1 + 2 here
            sql.append(" ?, SYSTIMESTAMP, ?, ?, '10', SYSTIMESTAMP, ?, ?, ?, ?, ?, ?,  ?,'PM_MM',?,?,?,?,?  FROM dual ");
            sql.append("  WHERE NOT EXISTS(SELECT 1 FROM XXMIS_ERP_VENDOR_DTL,fiscal_year where VENDOR_NUMBER=? and PO_NUMBER=? and  REGEXP_REPLACE(UPPER(INVOICE_NUMBER), '[^0-9A-Za-z]', '')=REGEXP_REPLACE(UPPER(?), '[^0-9A-Za-z]', '') ");
           sql.append(" and ? between  ");
   sql.append(" to_date(fiscal_year.FISCAL_START,'DD-MON-RRRR') ");
                  
             sql.append("   AND  to_date(fiscal_year.FISCAL_END,'DD-MON-RRRR')  ");
              sql.append("      ) ");
            
            // 7 + 3 till here
            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, vendorInputBeanObj.getApplId());           
            statement.setString(2, vendorInputBeanObj.getPONumber());
            statement.setString(3, vendorInputBeanObj.getVendorInvoiceNumber());
            statement.setString(4, vendorInputBeanObj.getVendorInvoiceAmount());
            statement.setDate(5, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
          statement.setString(6, vendorInputBeanObj.getInwardNumber());
            statement.setDate(7, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
             statement.setDate(8, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
              statement.setDate(9, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
              statement.setString(10, vendorInputBeanObj.getVendorNumber()); 
               statement.setString(11, vendorInputBeanObj.getVendorName()); 
                statement.setString(12, vendorInputBeanObj.getLocation()); 
               /* if(!ApplicationUtils.isBlank(vendorInputBeanObj.getSelectedModule())){
                statement.setString(13, vendorInputBeanObj.getSelectedModule());
                }
                else 
                {statement.setString(13," ");}*/
                 statement.setDate(13, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
        statement.setString(14, vendorInputBeanObj.getSelectedModuleType());
           statement.setString(15, vendorInputBeanObj.getSubmitAtPlant());
           statement.setString(16, vendorInputBeanObj.getSubmitAtDesc());
 statement.setString(17, vendorInputBeanObj.getPurchasingDesc());
 statement.setString(18, vendorInputBeanObj.getPurchasing_group());
statement.setString(19, vendorInputBeanObj.getSELECTEDPLANT());//insert from here
            statement.setString(20, vendorInputBeanObj.getVendorNumber());
              statement.setString(21, vendorInputBeanObj.getPONumber());
             statement.setString(22, vendorInputBeanObj.getVendorInvoiceNumber());
                         statement.setDate(23, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
              //statement.setString(22, vendorInputBeanObj.getSubmitAtPlant());
                         count = statement.executeUpdate();
               if(count==1){
                deleteObject(conn);
            }
             }
              if(sf.equals("10") && (!vendorInputBeanObj.getModuleSaveFlag().equals("NON_PO"))){
            sql.append(" UPDATE XXMIS_ERP_VENDOR_DTL ");
            sql.append(" SET PO_NUMBER = ?, INVOICE_NUMBER = ?,  INVOICE_AMOUNT = ?, "); // 3 here
            sql.append(" INVOICE_DATE = ? , INWARD_NUMBER = ?, INWARD_DATE = ? , "); // 6 till here
            sql.append(" SAVE_FLAG = ?, UPDATED_TIME_STAMP = SYSTIMESTAMP, LOCATION = ?,INVOICE_RESUBMIT_DATE = ?,  "); // 16-18 till here      
                
            //sql.append(" INVOICE_RESUBMITTED_COUNTER = ?,INVOICE_FROM_DT = ?, INVOICE_TO_DT = ? "); // 16-18 till here 
         sql.append(" SUBMIT_AT_LOCATION = ? ,SUBMIT_AT_DESC=?,VENDOR_INWARD_DATE = ?,MODULE_TYPE = ? ");
          sql.append(" WHERE APPL_ID = ?  "); // 19 till here
            sql.append(" AND NVL(SAVE_FLAG,'10') in('10','40' ) ");
        

            statement = conn.prepareStatement(sql.toString());           
            statement.setString(1, vendorInputBeanObj.getPONumber());
            statement.setString(2, vendorInputBeanObj.getVendorInvoiceNumber());
            statement.setString(3, vendorInputBeanObj.getVendorInvoiceAmount());  
            statement.setDate(4, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(5, vendorInputBeanObj.getInwardNumber());
            statement.setDate(6, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(7, sf);  
             statement.setString(8,vendorInputBeanObj.getLocation());
             if(vendorInputBeanObj.getStatus().equals("Rejected"))//if Status was rejected previously update the invoice resubmit date to sysdate 
            {           
            statement.setDate(9,new java.sql.Date(System.currentTimeMillis()));

            }
         
            else 
            {
                statement.setDate(9,null);
            }
             
            statement.setString(10,vendorInputBeanObj.getSubmitAtPlant());
            statement.setString(11,vendorInputBeanObj.getSubmitAtDesc());
            statement.setDate(12, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            //statement.setDate(8, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
           // statement.setDate(9, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(13, vendorInputBeanObj.getSelectedModuleType());
            statement.setString(14, vendorInputBeanObj.getApplId());
         
            count = statement.executeUpdate();
           
             }
             if((sf.equals("30"))||(sf.equals("40"))||(sf.equals("50")))
             {
                 int i=1;
sql.append(" UPDATE XXMIS_ERP_VENDOR_DTL ");
             sql.append(" SET PO_NUMBER = ?, INVOICE_NUMBER = ?,  INVOICE_AMOUNT = ?, "); // 3 here
            sql.append(" INVOICE_DATE = ? , INWARD_NUMBER = ?, INWARD_DATE = ? , "); // 6 till here
            sql.append(" SAVE_FLAG = ?, UPDATED_TIME_STAMP = SYSTIMESTAMP, "); // 16-18 till here      
            sql.append(" INVOICE_FROM_DT = ?, INVOICE_TO_DT = ?,REASON = ? , LOCATION = ?,  "); // 16-18 till here      
            sql.append(" PROCESSING_EMPLOYEE_CPF = ?, PROCESSING_EMPLOYEE_NAME = ?,  ");
            sql.append(" PROCESSING_EMPLOYEE_DESIGNATN = ?, PROCESSING_EMPLOYEE_OFF_CODE = ?,  ");
            //sql.append(" INVOICE_FROM_DT = ?, INVOICE_TO_DT = ? "); // 16-18 till here 
          if(sf.equals("30")) {
           sql.append(" INVOICE_APPROVAL_DATE = ? ,  "); 
           }
            else {
           sql.append(" INVOICE_REJECTION_DATE = ? ,  "); 
           }
              
           sql.append(" SUBMIT_AT_LOCATION = ?,IS_REJECTED = ? ,IS_APPROVED = ? ,VENDOR_INWARD_DATE = ?,MODULE_TYPE = ? ");
            if(sf.equals("50")) {
              sql.append(",FORWARDED_TO_PLANT = ?,    ");
               sql.append("FORWARDED_TO_DESC = ?  , ");
                 sql.append(" AUTH_EMPLOYEE = ?, AUTH_EMPLOYEE_MOBILE_NO = ?, ");
            sql.append(" AUTH_EMPLOYEE_NAME = ?, AUTH_EMPLOYEE_DESIGNATION = ? ");
            }
            
            sql.append(" WHERE APPL_ID = ? "); // 19 till here
            //sql.append(" AND NVL(SAVE_FLAG,'30') = '30' "); 
  
            statement = conn.prepareStatement(sql.toString());           
            statement.setString(i++, vendorInputBeanObj.getPONumber());
            statement.setString(i++, vendorInputBeanObj.getVendorInvoiceNumber());
            statement.setString(i++, vendorInputBeanObj.getVendorInvoiceAmount());  
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++, vendorInputBeanObj.getInwardNumber());
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++, sf);  
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++, vendorInputBeanObj.getReason());
            statement.setString(i++,vendorInputBeanObj.getLocation());
             statement.setString(i++,vendorInputBeanObj.getEMP_ID());
              statement.setString(i++,vendorInputBeanObj.getEMP_NAME());
               statement.setString(i++,vendorInputBeanObj.getDesignation());
                statement.setString(i++,vendorInputBeanObj.getOffice_Code());
            
            
            
             if(sf.equals("30"))  {
            statement.setDate(i++,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceApprDAte(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            }
            else
            {
            statement.setDate(i++,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceREjDAte(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            }
            statement.setString(i++,vendorInputBeanObj.getSubmitAtPlant());
            statement.setString(i++,vendorInputBeanObj.getIsRejectedFlag());
            statement.setString(i++,vendorInputBeanObj.getIsApprovedFlag());
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++, vendorInputBeanObj.getSelectedModuleType());
              if(sf.equals("50")) {
                  statement.setString(i++,vendorInputBeanObj.getForwardToPlant());
                      statement.setString(i++,vendorInputBeanObj.getForwardToDesc());
                      statement.setString(i++,vendorInputBeanObj.getempCpf());
            statement.setString(i++,vendorInputBeanObj.getContactNumber());
            statement.setString(i++,vendorInputBeanObj.getempName());
            statement.setString(i++,vendorInputBeanObj.getDesignation());
              }
            statement.setString(i++, vendorInputBeanObj.getApplId());
           
            count = statement.executeUpdate();
             }
           // vendorPrezDataObj.setVendorBean(vendorInputBeanObj);
             if((sf.equals("20")))
             {        int i = 1; 
             sql.append(" UPDATE XXMIS_ERP_VENDOR_DTL ");
            sql.append(" SET PO_NUMBER = ?, INVOICE_NUMBER = ?,  INVOICE_AMOUNT = ?, "); // 3 here
            sql.append(" INVOICE_DATE = ? , INWARD_NUMBER = ?, INWARD_DATE = ? , "); // 6 till here
            sql.append(" SAVE_FLAG = ?, UPDATED_TIME_STAMP = SYSTIMESTAMP, "); // 16-18 till here      
            sql.append(" INVOICE_FROM_DT = ?, INVOICE_TO_DT = ?, "); // 16-18 till here           
            sql.append(" INV_SUBMIT_EMPSMS_SENT_FLAG = ?, OFFICE_CODE = ?,PARENT_OFFICE_CODE = ?, ");
            sql.append(" AUTH_EMPLOYEE = ?, AUTH_EMPLOYEE_MOBILE_NO = ?, ");
            sql.append(" AUTH_EMPLOYEE_NAME = ?, AUTH_EMPLOYEE_DESIGNATION = ?,LOCATION = ?, ");
             if(!ApplicationUtils.isBlank(vendorInputBeanObj.getSELECTEDPLANT())){
                 sql.append(" SELECTED_PLANT = ?, ");  
             }
            sql.append(" INVOICE_SUBMITTED_COUNTER = NVL(( SELECT INVOICE_SUBMITTED_COUNTER FROM XXMIS_ERP_VENDOR_DTL WHERE APPL_ID = ? ),0) + 1, ");  
           sql.append(" SUBMIT_AT_LOCATION = ?,SUBMIT_AT_DESC=?,PURCHASING_DESC=?,VENDOR_INWARD_DATE = ? ,INVOICE_SUBMIT_DATE  = ?,MODULE_TYPE = ?   ");
         
            sql.append(" WHERE APPL_ID = ? ");
             
                       logger.log(Level.INFO, "GetVendorApplFormTxnHelper :: getQueryResults() :: SQL :: " + sql.toString());
            statement = conn.prepareStatement(sql.toString());           
            statement.setString(i++, vendorInputBeanObj.getPONumber());
            statement.setString(i++, vendorInputBeanObj.getVendorInvoiceNumber());
            statement.setString(i++, vendorInputBeanObj.getVendorInvoiceAmount());  
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++, vendorInputBeanObj.getInwardNumber());
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++, sf);  
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++,vendorInputBeanObj.getsmsFlag());
            statement.setString(i++,vendorInputBeanObj.getOffice_Code());
             statement.setString(i++,vendorInputBeanObj.getParent_Office_Code());
            statement.setString(i++,vendorInputBeanObj.getempCpf());
            statement.setString(i++,vendorInputBeanObj.getContactNumber());
            statement.setString(i++,vendorInputBeanObj.getempName());
            statement.setString(i++,vendorInputBeanObj.getDesignation());
            statement.setString(i++,vendorInputBeanObj.getLocation());
              if(!ApplicationUtils.isBlank(vendorInputBeanObj.getSELECTEDPLANT())){
             statement.setString(i++,vendorInputBeanObj.getSELECTEDPLANT());
             }
           statement.setString(i++,vendorInputBeanObj.getApplId());
            
           // statement.setDate(18,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceResubmit(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            statement.setString(i++,vendorInputBeanObj.getSubmitAtPlant());
                        statement.setString(i++,vendorInputBeanObj.getSubmitAtDesc());
              statement.setString(i++,vendorInputBeanObj.getPurchasingDesc());   
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceSubmitDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                    statement.setString(i++,vendorInputBeanObj.getSelectedModuleType());
            statement.setString(i++, vendorInputBeanObj.getApplId());
            
            count = statement.executeUpdate();
             }
              if (count == 0)
            vendorInputBeanObj.setApplId("");
            if (count > 0)
            {
                  System.out.println("Calling Procedure PROC_SUMMARY_UPD BEFORE changes");
                 String  appl_id=vendorInputBeanObj.getApplId();
                 int appl_id1=Integer.parseInt(appl_id);  
               proc_stmt = conn.prepareCall("{ call PROC_SUMMARY_UPD(?) }");
               proc_stmt.setString(1, vendorInputBeanObj.getApplId());
                proc_stmt.executeQuery();
                  System.out.println(" Calling Procedure PROC_SUMMARY_UPD AFTER changes "); 
                conn.commit();
                
            }
            
           
    }
              catch (Exception ex) {
                  ex.printStackTrace();
            logger.log(Level.ERROR, "VendorApplFormTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }

            //leaveapplFormBeanObj = leaveapplFormPrezDataObj.getLeaveApplFormBean();
    }//updateObject() ends

    /**
     * Public API to delete data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    @Override
    public void deleteObject(Connection conn) throws Exception {
        int count = 0;
        PreparedStatement statement = null;
       try{
        StringBuilder sql = new StringBuilder();
         sql.append("update XXMIS_ERP_NON_PO_VENDOR_DTL SET UPDATED_IN_MAIN_FLAG ='Y' WHERE APPL_ID = ? ");
        statement = conn.prepareStatement(sql.toString());
        statement.setString(1, vendorInputBeanObj.getApplId());
         count = statement.executeUpdate();
           if (count > 0)
            {
                 conn.commit();
            }
       }
             catch (Exception ex) {
            logger.log(Level.ERROR, "VendorApplFormTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }
    }//deleteObject() ends
    
    /**
     * Public API to update ObjectAttribute
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    @Override
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
    }//updateObjectAttribute ends
}
