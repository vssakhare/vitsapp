    /*
    * To change this template, choose Tools | Templates
    * and open the template in the editor.
    */
    package in.emp.vendor.dao.helper.formHelper;

    import in.emp.common.ApplicationConstants;
    import in.emp.dao.QueryHelper;
    import in.emp.util.ApplicationUtils;
    import in.emp.vendor.bean.VendorInputBean;
    import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.dao.helper.listHelper.GetVendorInputListQueryHelper;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import org.apache.log4j.Level;
    import org.apache.log4j.Logger;

    /**
    *
    * @author pooja
    */
    public class GetVendorVerifiedInputFormQuerHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorInputListQueryHelper.class);
    private VendorInputBean vendorInputBean;
    private VendorPrezData vendorPrezDataObj;

    public GetVendorVerifiedInputFormQuerHelper(VendorInputBean vendorInputBean) {
        this.vendorInputBean = vendorInputBean;
    }

    public GetVendorVerifiedInputFormQuerHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorInputBean = this.vendorPrezDataObj.getVendorInputBean();
    }

    public Object getDataObject(ResultSet results) throws Exception {
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        try {
            logger.log(Level.INFO, "GetVendorVerifiedInputFormQuerHelper ::: getDataObject() :: method called ::");

            vendorInputBeanObj.setApplId(results.getString("APPL_ID")); 
            vendorInputBeanObj.setApplDate(results.getDate("APPL_DATE"));
            vendorInputBeanObj.setSubmitAtPlant(results.getString("SUBMIT_AT_LOCATION")); 
            vendorInputBeanObj.setSubmitAtDesc(results.getString("SUBMIT_AT_DESC")); 
vendorInputBeanObj.setZone(results.getString("ZONE"));
vendorInputBeanObj.setCircle(results.getString("CIRCLE"));
vendorInputBeanObj.setDivision(results.getString("DIVISION"));
vendorInputBeanObj.setPONumber(results.getString("PO_NUMBER"));
vendorInputBeanObj.setPOCreationDate(results.getDate("PO_CREATION_DATE"));
            vendorInputBeanObj.setPODesc(results.getString("PO_DESC"));
vendorInputBeanObj.setTotalPoAmt(results.getString("TOTAL_PO_AMT"));
vendorInputBeanObj.setValidityStart(results.getDate("VALIDITY_START"));
vendorInputBeanObj.setValidityEnd(results.getDate("VALIDITY_END"));
            vendorInputBeanObj.setVendorInvoiceAmount(results.getString("INVOICE_AMOUNT"));
            vendorInputBeanObj.setVendorInvoiceNumber(results.getString("INVOICE_NUMBER"));  
            vendorInputBeanObj.setVendorInvoiceDate(results.getDate("INVOICE_DATE"));
           
            vendorInputBeanObj.setInwardNumber(results.getString("INWARD_NUMBER"));  
            vendorInputBeanObj.setInwardDate(results.getDate("INWARD_DATE"));


            vendorInputBeanObj.setInvoiceFromDate(results.getDate("INVOICE_FROM_DT"));                    
            vendorInputBeanObj.setInvoiceToDate(results.getDate("INVOICE_TO_DT")); 
            vendorInputBeanObj.setSaveFlag(results.getString("STATUS"));
            vendorInputBeanObj.setPendingSince(results.getString("PENDING_SINCE"));
            vendorInputBeanObj.setSelectedModuleType(results.getString("MODULE_TYPE"));
             vendorInputBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER"));
                 vendorInputBeanObj.setVendorName(results.getString("VENDOR_NAME")); 
            vendorInputBeanObj.setSesMigoAmt(results.getString("SES_MIGO_AMT"));
                    vendorInputBeanObj.setInvAmt(results.getString("INV_AMT"));
                    vendorInputBeanObj.setPaidAmt(results.getString("PAID_AMT"));
                    vendorInputBeanObj.setInvoiceStatus(results.getString("INVOICE_STATUS"));
                    vendorInputBeanObj.setTaxAmount(results.getString("TAX_AMOUNT"));
                    vendorInputBeanObj.setGST_TDS(results.getString("GST_TDS"));
                    vendorInputBeanObj.setIT_TDS_AMOUNT(results.getString("IT_TDS_AMOUNT"));
                    vendorInputBeanObj.setRETENSION_AMOUNT(results.getString("RETENSION_AMOUNT"));
                

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorInputListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInputBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i = 1;
        try {
            logger.log(Level.INFO, "GetVendorInputListQueryHelper ::: getQueryResults() :: method called ::");


           sql.append(" SELECT EVIL.SUBMIT_AT_LOCATION,EVIL.SUBMIT_AT_DESC,EVIL.ZONE,EVIL.CIRCLE,EVIL.DIVISION,EVIL.PO_CREATION_DATE,EVIL.TOTAL_PO_AMT,TO_DATE(NVL(EVIL.VALIDITY_START,SYSDATE), 'DD-MM-YYYY') VALIDITY_START,TO_DATE(NVL(EVIL.VALIDITY_END,SYSDATE), 'DD-MM-YYYY') VALIDITY_END, EVIL.APPL_ID, TO_DATE(TO_CHAR(EVIL.CREATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') APPL_DATE, EVIL.PO_NUMBER, EVIL.PO_DESC, EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE,EVIL.MODULE_SAVE_FLAG, ");//to differentiate between proj id and po number 
            sql.append(" EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_DATE, ");  
           sql.append(" EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INWARD_DATE, "); 
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_FROM_DT,EVIL.VENDOR_NUMBER, EVIL.VENDOR_NAME,");         
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_TO_DT, EVIL.STATUS, ");
           sql.append(" EVIL.SES_MIGO_AMT,EVIL.INV_AMT,EVIL.PAID_AMT,INVOICE_STATUS,TAX_AMOUNT,IT_TDS_AMOUNT,GST_TDS,RETENSION_AMOUNT, ");
           sql.append(" case when STATUS = 'Submitted' THEN TO_NUMBER(trunc(sysdate) - to_date(to_char(nvl(INVOICE_RESUBMIT_DATE,INVOICE_SUBMIT_DATE), 'yyyy-mm-dd'),'yyyy-mm-dd')) else null end PENDING_SINCE ");
           sql.append(" FROM summary_status EVIL ");
           sql.append(" WHERE TO_NUMBER(EVIL.VENDOR_NUMBER) = ? ");
            if(!ApplicationUtils.isBlank(vendorInputBean.getPONumber())){
           sql.append(" AND EVIL.PO_NUMBER = TRIM(?) ");
          } 
             if(!ApplicationUtils.isBlank(vendorInputBean.getApplId())){
           sql.append(" AND EVIL.APPL_ID = TRIM(?) ");
          }
              if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())){
           sql.append(" AND  REGEXP_REPLACE(UPPER(EVIL.INVOICE_NUMBER), '[^0-9A-Za-z]', '')  = TRIM(?) ");
          }
               if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceDate())){
           sql.append(" AND EVIL.INVOICE_DATE = TRIM(?) ");
          }
          sql.append(" ORDER BY  APPL_ID DESC,STATUS ");

           logger.log(Level.INFO, "GetVendorVerifiedInputFormQuerHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
      statement.setString(i++, vendorInputBean.getVendorNumber());
       if(!ApplicationUtils.isBlank(vendorInputBean.getPONumber())){
      statement.setString(i++, vendorInputBean.getPONumber());    
       }
        if(!ApplicationUtils.isBlank(vendorInputBean.getApplId())){
           statement.setString(i++, vendorInputBean.getApplId()); 
        }
       if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())){
      statement.setString(i++, vendorInputBean.getVendorInvoiceNumber());  
       }
        if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceDate())){
            statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBean.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
        
        }
            
           rs = statement.executeQuery();
       
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorVerifiedInputFormQuerHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
    }
