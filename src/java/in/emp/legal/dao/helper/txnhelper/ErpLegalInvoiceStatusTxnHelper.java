/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.txnhelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.TxnHelper;
import in.emp.legal.bean.LegalInvoiceBean;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.dao.helper.txnHelper.VendorApplFormTxnHelper;
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
public class ErpLegalInvoiceStatusTxnHelper implements TxnHelper {
    private static Logger logger = Logger.getLogger(ErpLegalInvoiceDetailsTxnHandler.class);
    private LegalInvoiceBean legalInvoiceBean;
   
     List<LegalInvoiceBean>	listErpToVitsFileFormat;

    public ErpLegalInvoiceStatusTxnHelper(List listErpToVitsFileFormat) {
        this.listErpToVitsFileFormat = listErpToVitsFileFormat;
    }//constructor ends

    
    public ErpLegalInvoiceStatusTxnHelper(LegalInvoiceBean legalInvoiceBean) {
        this.legalInvoiceBean=legalInvoiceBean;
    }

    public ErpLegalInvoiceStatusTxnHelper() {
    }

    @Override
    public Object createObject(Connection conn) throws Exception {
        int[] count ;
        PreparedStatement statement = null;
        //PreparedStatement statement1 = null;
        CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "ErpLegalInvoiceStatusTxnHelper ::: createObject() :: method called ::");
            System.out.println("ErpLegalInvoiceStatusTxnHelper ::: createObject() :: method called ::");

            // legalInvoiceBean = vendorPrezDataObj.getVendorBean();
            
if(listErpToVitsFileFormat != null && listErpToVitsFileFormat.size() >0)
	{
            sql.append("INSERT INTO ERP_LEGAL_INVOICE_STATUS ");
           	 sql.append(" ( CASEREFNO , YEAR_L , DOF_LC , VENDOR , INVOICE_LEGAL, ADV_FEE_TYPE , ");
	 sql.append(" INVOICE_DATE  , ADVOCATE_NAME , ADVOCATE_TYPE , INVOICE_AMOUNT , ");
	 sql.append(" RECIEPT_DATE  , FEE_RECOMMENDED , REASON_FOR_DEDUCTION , APPROVED_BY , ");
	 sql.append(" DATE_OF_APPROVAL , DATE_OF_SUBMISSION  , SAC_CODE , STATUS_FEE , ");
	 sql.append(" ZZADV_PAN_NO , ZZELIGIBLE_FEE , ZZPARK_POST_DOC_NO, ZZPARK_DOC_AMT , ");
	 sql.append(" ZZPARK_POST_DATE  , ZZPAY_DONE_ERP_DOC , ZZPAY_DOC_AMT, ");
	 sql.append(" ZZFEE_DT_OF_PAYMENT , ZZFI_COMP_CODE   , ZZFI_FIS_YR, ZZPOST_DATE , ");
	 sql.append(" ZZPOST_FISCAL, ZZUTR_NO  , CASENO   , CASENOCOURT ,  CORT_KEY , ");
	 sql.append(" COURTNAME ,  COOFFICE_BTRTL,   COOFFICE_TEXT ,  REGION_BTRTL ,  ");
	 sql.append(" REGION_BTRTL_TEXT ,  ZZONE_BTRTL ,  ZZONE_BTRTL_TEXT   , CIRCLE_BTRTL "); 
	 sql.append(" , CIRCLE_BTRTL_TEXT ,  DIVISION_BTRTL ,  DIVISION_BTRTL_TEXT , "); 
	 sql.append(" SUBDIV_BTRTL  , SUBDIV_BTRTL_TEXT ,  SECTION_BTRTL  , ");
	 sql.append(" SECTION_BTRTL_TEXT  , SUBSTATION   , SUBSTATION_TEXT ,  DSD  , CASETYPE  ");
	 sql.append(" , CASETYPEDESC , CASEDET , CASESTAT , CASESTATDESC ,CREATED_DT,CREATED_BY)");
            sql.append("  VALUES (");
            sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");
            statement = conn.prepareStatement(sql.toString());
             for (LegalInvoiceBean  legalInvoiceBean: listErpToVitsFileFormat) {
                 int i=1;
            statement.setInt(i++,legalInvoiceBean.getCASEREFNO());
	statement.setInt(i++,legalInvoiceBean.getYEAR_L());
	statement.setDate(i++,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getDOF_LC(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
	statement.setString(i++,legalInvoiceBean.getVENDOR());
	statement.setString(i++,legalInvoiceBean.getINVOICE_LEGAL());
	statement.setString(i++,legalInvoiceBean.getADV_FEE_TYPE());
	statement.setDate(i++,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getINVOICE_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
	statement.setString(i++,legalInvoiceBean.getADVOCATE_NAME());
	statement.setString(i++,legalInvoiceBean.getADVOCATE_TYPE());
	statement.setDouble(i++,legalInvoiceBean.getINVOICE_AMOUNT());
	statement.setDate(i++,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getRECIEPT_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
	statement.setDouble(i++,legalInvoiceBean.getFEE_RECOMMENDED());
	statement.setString(i++,legalInvoiceBean.getREASON_FOR_DEDUCTION());
	statement.setString(i++,legalInvoiceBean.getAPPROVED_BY());
	statement.setDate(i++,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getDATE_OF_APPROVAL(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
	statement.setDate(i++,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getDATE_OF_SUBMISSION(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
	statement.setString(i++,legalInvoiceBean.getSAC_CODE());
	statement.setString(i++,legalInvoiceBean.getSTATUS_FEE());
	statement.setString(i++,legalInvoiceBean.getZZADV_PAN_NO());
	statement.setDouble(i++,legalInvoiceBean.getZZELIGIBLE_FEE());
	statement.setString(i++,legalInvoiceBean.getZZPARK_POST_DOC_NO());
	statement.setDouble(i++,legalInvoiceBean.getZZPARK_DOC_AMT());
	statement.setDate(i++,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getZZPARK_POST_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
	statement.setString(i++,legalInvoiceBean.getZZPAY_DONE_ERP_DOC());
	statement.setDouble(i++,legalInvoiceBean.getZZPAY_DOC_AMT());
	statement.setDate(i++,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getZZFEE_DT_OF_PAYMENT(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
	statement.setString(i++,legalInvoiceBean.getZZFI_COMP_CODE());
	statement.setInt(i++,legalInvoiceBean.getZZFI_FIS_YR());
	statement.setDate(i++,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getZZPOST_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
	statement.setInt(i++,legalInvoiceBean.getZZPOST_FISCAL());
	statement.setString(i++,legalInvoiceBean.getZZUTR_NO());
	statement.setString(i++,legalInvoiceBean.getCASENO());
	statement.setString(i++,legalInvoiceBean.getCASENOCOURT());
	statement.setInt(i++,legalInvoiceBean.getCORT_KEY());
	statement.setString(i++,legalInvoiceBean.getCOURTNAME());
	statement.setString(i++,legalInvoiceBean.getCOOFFICE_BTRTL());
	statement.setString(i++,legalInvoiceBean.getCOOFFICE_TEXT());
	statement.setString(i++,legalInvoiceBean.getREGION_BTRTL());
	statement.setString(i++,legalInvoiceBean.getREGION_BTRTL_TEXT());
	statement.setString(i++,legalInvoiceBean.getZZONE_BTRTL());
	statement.setString(i++,legalInvoiceBean.getZZONE_BTRTL_TEXT());
	statement.setString(i++,legalInvoiceBean.getCIRCLE_BTRTL());
	statement.setString(i++,legalInvoiceBean.getCIRCLE_BTRTL_TEXT());
	statement.setString(i++,legalInvoiceBean.getDIVISION_BTRTL());
	statement.setString(i++,legalInvoiceBean.getDIVISION_BTRTL_TEXT());
	statement.setString(i++,legalInvoiceBean.getSUBDIV_BTRTL());
	statement.setString(i++,legalInvoiceBean.getSUBDIV_BTRTL_TEXT());
	statement.setString(i++,legalInvoiceBean.getSECTION_BTRTL());
	statement.setString(i++,legalInvoiceBean.getSECTION_BTRTL_TEXT());
	statement.setString(i++,legalInvoiceBean.getSUBSTATION());
	statement.setString(i++,legalInvoiceBean.getSUBSTATION_TEXT());
	statement.setString(i++,legalInvoiceBean.getDSD());
	statement.setString(i++,legalInvoiceBean.getCASETYPE());
	statement.setString(i++,legalInvoiceBean.getCASETYPEDESC());
	statement.setString(i++,legalInvoiceBean.getCASEDET());
	statement.setInt(i++,legalInvoiceBean.getCASESTAT());
	statement.setString(i++,legalInvoiceBean.getCASESTATDESC());
statement.setDate(i++,ApplicationUtils.stringToDate(ApplicationUtils.dateToString(legalInvoiceBean.getCreatedDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
statement.setString(i++,legalInvoiceBean.getCreatedBy());
            logger.log(Level.INFO, "ErpLegalInvoiceStatusTxnHelper ::: createObject() :: SQL :: " + sql.toString());
                 System.out.println("add batch case no::"+legalInvoiceBean.getCASEREFNO());
             statement.addBatch();
                 System.out.println("added to batch ");
             }
             count = new int[listErpToVitsFileFormat.size()];

                count = statement.executeBatch();
            System.out.println("count::"+count);
         conn.commit(); 
        }
           

        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
            logger.log(Level.ERROR, "ErpLegalInvoiceStatusTxnHelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        }
return listErpToVitsFileFormat;
    }

    @Override
    public void updateObject(Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "ErpLegalInvoiceStatusTxnHelper ::: deleteObject() :: method called ::");

            sql.append("truncate table ERP_LEGAL_INVOICE_STATUS ");
            
            statement = conn.prepareStatement(sql.toString());
           

            logger.log(Level.INFO, "ErpLegalInvoiceStatusTxnHelper ::: deleteObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();

            conn.commit();
            System.out.println("deleted records of table ERP_LEGAL_INVOICE_STATUS");
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "ErpLegalInvoiceStatusTxnHelper ::: deleteObject() :: Exception :: " + ex);
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
    }

    @Override
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
