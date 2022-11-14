/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.txnHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.TxnHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.bean.VendorStatuBean;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Rikma Rai
 */
public class SaveVendorRetentionDetailsResponseTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(VendorApplFormTxnHelper.class);
    private VendorInputBean vendorBeanObj;
    private VendorPrezData vendorPrezDataObj;
     List<VendorInputBean>	listErpToVitsFileFormat;

    public SaveVendorRetentionDetailsResponseTxnHelper(List listErpToVitsFileFormat) {
        this.listErpToVitsFileFormat = listErpToVitsFileFormat;
    }//constructor ends

    public SaveVendorRetentionDetailsResponseTxnHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorBeanObj = this.vendorPrezDataObj.getVendorInputBean();
    }
    public SaveVendorRetentionDetailsResponseTxnHelper(VendorInputBean vendorBeanObj) {
        this.vendorBeanObj=vendorBeanObj;
    }

    public SaveVendorRetentionDetailsResponseTxnHelper() {
    }

    @Override
    public Object createObject(Connection conn) throws Exception {
        int[] count ;
        PreparedStatement statement = null;
        //PreparedStatement statement1 = null;
        CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        vendorPrezDataObj = new VendorPrezData();
        try {
            logger.log(Level.INFO, "SaveVendorRetentionDetailsResponseTxnHelper ::: createObject() :: method called ::");
            System.out.println("SaveVendorRetentionDetailsResponseTxnHelper ::: createObject() :: method called ::");

            // vendorBeanObj = vendorPrezDataObj.getVendorBean();
            
if(listErpToVitsFileFormat != null && listErpToVitsFileFormat.size() >0)
	{
            sql.append("INSERT INTO VENDOR_RETENTION_DETAILS_RESPONSE ");
            sql.append(" (ID , APPL_ID , SERIAL_NO , CLAIM_DATE , VENDOR_NUMBER, VENDOR_NAME, PROJECT_ID, ");
            sql.append(" INVOICE_TYPE, SHORT_TEXT, INVOICE_NUMBER, INVOICE_DATE , GROSS_VALUE , ");
            sql.append(" INWARD_NUMBER, INWARD_DATE , RETENTION_DOC,  FI_DOC_NO,  ");
            sql.append(" AMOUNT ,FI_YEAR, NEW_RT_DOC_NO, NEW_RT_FI_YEAR, NEW_RT_AMOUNT , INV_NO, STATUS, ");
            sql.append(" CREATED_DT ) VALUES (");
            sql.append(" SEQ_VENDOR_RETENTION_DETAILS_RESPONSE.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'A',? )");
            statement = conn.prepareStatement(sql.toString());
             for (VendorInputBean  vendorBeanObj: listErpToVitsFileFormat) {
            statement.setString(1, vendorBeanObj.getApplId());
            statement.setString(2, vendorBeanObj.getSerialNo());
            statement.setDate(3, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBeanObj.getApplDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(4, vendorBeanObj.getVendorNumber());
            statement.setString(5, vendorBeanObj.getVendorName());
             statement.setString(6, vendorBeanObj.getProjectId());
             statement.setString(7, vendorBeanObj.getINVOICE_TYPE());
             statement.setString(8, vendorBeanObj.getShortText());
             statement.setString(9, vendorBeanObj.getMsedclInvoiceNumber());
            statement.setDate(10, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(11, vendorBeanObj.getInvAmt());
         statement.setString(12, vendorBeanObj.getInwardNumber());
         statement.setDate(13, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBeanObj.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
          statement.setString(14, vendorBeanObj.getRetentionDocNo());
           statement.setString(15, vendorBeanObj.getFiDocNo());
            statement.setString(16, vendorBeanObj.getFiAmount());
            if(vendorBeanObj.getFiDocNo()!=null && vendorBeanObj.getFiDocNo().contains("/")){
                statement.setString(17, vendorBeanObj.getFiDocNo().split("/")[1]);
            }else{
                statement.setString(17, "");
            }
             statement.setString(18, vendorBeanObj.getNewDocNo());
             if(vendorBeanObj.getNewDocNo()!=null && vendorBeanObj.getNewDocNo().contains("/")){
                statement.setString(19, vendorBeanObj.getNewDocNo().split("/")[1]);
            }else{
                statement.setString(19, "");
            }
             statement.setString(20, vendorBeanObj.getNewDocAmount());
            statement.setString(21, vendorBeanObj.getVendorInvoiceNumber());
            statement.setString(22, new SimpleDateFormat("dd-MMM-yyyy").format(new Date()));
            logger.log(Level.INFO, "SaveVendorRetentionDetailsResponseTxnHelper ::: createObject() :: SQL :: " + sql.toString());
             statement.addBatch();
             }
             count = new int[listErpToVitsFileFormat.size()];

                count = statement.executeBatch();
            System.out.println("count::"+count);
         conn.commit(); 
        }
           

        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
            logger.log(Level.ERROR, "SaveVendorRetentionDetailsResponseTxnHelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        }
return vendorPrezDataObj;
    }

    @Override
    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
          CallableStatement proc_stmt = null;
        try {
            System.out.println("Starting Calling PROC_UPDATE_RETENTION_RESPONSE");
            logger.log(Level.INFO, "SaveVendorRetentionDetailsResponseTxnHelper ::: updateObject() :: method called ::");

           proc_stmt = conn.prepareCall("{ call PROC_UPDATE_RETENTION_RESPONSE }");
        proc_stmt.executeQuery();
        System.out.println("Finished Calling PROC_UPDATE_RETENTION_RESPONSE");
            logger.log(Level.INFO, "SaveVendorRetentionDetailsResponseTxnHelper ::: updateObject() :: SQL :: " + sql.toString());

            
            
        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
            logger.log(Level.ERROR, "SaveVendorRetentionDetailsResponseTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }
    }

    @Override
    public void deleteObject(Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
