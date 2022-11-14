/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.listHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class GetVendorListQueryHelper implements QueryHelper { 

    private static Logger logger = Logger.getLogger(GetVendorListQueryHelper.class);
    private VendorBean vendorBean;
    private VendorPrezData vendorPrezDataObj;

    public GetVendorListQueryHelper(VendorBean vendorBean) { 
        this.vendorBean = vendorBean;
    }

    public GetVendorListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorBean = this.vendorPrezDataObj.getVendorBean();
    }

    public Object getDataObject(ResultSet results) throws Exception {
        VendorBean vendorBeanObj = new VendorBean();
        try {
            logger.log(Level.INFO, "GetVendorListQueryHelper ::: getDataObject() :: method called ::");
             if(vendorBean.getUserType().equals("Emp")){
                  vendorBeanObj.setPONumber(results.getString("PO_NUMBER"));
            vendorBeanObj.setPODesc(results.getString("PO_DESC"));
            vendorBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER")); 
            vendorBeanObj.setVendorName(results.getString("VENDOR_NAME")); 
            vendorBeanObj.setApplId(results.getString("APPL_ID"));
            vendorBeanObj.setLocationName(results.getString("LOCATION"));
           vendorBeanObj.setInvoiceNumber(results.getString("INVOICE_NUMBER"));
           vendorBeanObj.setInvoiceDate(results.getDate("INVOICE_DATE"));
           vendorBeanObj.setInvoiceAmount(results.getString("INVOICE_AMOUNT"));
           vendorBeanObj.setInwardDate(results.getDate("INWARD_DATE"));
           vendorBeanObj.setInwardNumber(results.getString("INWARD_NUMBER"));
          vendorBeanObj.setINV_STATUS(results.getString("INVOICE_STATUS"));
            
          vendorBeanObj.setSELECTED_MODULE_TYPE(results.getString("MODULE_TYPE"));//MODULE TYPE TO DIFFERENTIATE BETWEEN PO NUMBER AND PROJ ID  
             }
                
               if(vendorBean.getUserType().equals("Vendor")){
            vendorBeanObj.setZone(results.getString("ZONE")); 
            vendorBeanObj.setCircle(results.getString("CIRCLE")); 
            vendorBeanObj.setDivision(results.getString("DIVISION"));             
            vendorBeanObj.setPONumber(results.getString("PO_NUMBER"));
            vendorBeanObj.setPODesc(results.getString("PO_DESC"));
            vendorBeanObj.setPOType(results.getString("PO_TYPE"));
            vendorBeanObj.setPOCreationDate(results.getDate("PO_CREATION_DATE"));
            
            vendorBeanObj.setCLDocDt(results.getDate("PAYMENT_DATE"));
            vendorBeanObj.setInvDt(results.getDate("LIABILITY_DATE"));
            vendorBeanObj.setSESDate(results.getDate("SES_DOC_DATE"));
         
            
             vendorBeanObj.setSes_ven_invno(results.getString("SES_VEN_INV_NO"));
             vendorBeanObj.setMigo_ven_invno(results.getString("MIGO_VEN_INV_NO"));
               
               vendorBeanObj.setVendorInvoiceNumber(results.getString("VENDOR_INV_NO"));
               vendorBeanObj.setMsedclInvoiceNumber(results.getString("MSEDCL_INV_NO"));
            //vendorBeanObj.setInvoiceAmount(results.getString("INVOICE_AMOUNT"));
            vendorBeanObj.setValidityStart(results.getDate("VALIDITY_START"));                    
            vendorBeanObj.setValidityEnd(results.getDate("VALIDITY_END")); 
            vendorBeanObj.setSaveFlag(results.getString("STATUS"));
            vendorBeanObj.setInvoiceDate(results.getDate("LIABILITY_DATE"));
            vendorBeanObj.setInvoiceAmount(results.getString("INV_AMT"));
            vendorBeanObj.setSesNum(results.getString("SES_NO"));
            vendorBeanObj.setMigo_doc(results.getString("MIGO_DOC"));
            vendorBeanObj.setMigo_dt(results.getDate("MIGO_DT"));
            vendorBeanObj.setSesormigo_no(results.getString("SOrMNum"));}
            
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i = 1; 
        try {
            logger.log(Level.INFO, "GetVendorListQueryHelper ::: getQueryResults() :: method called ::");
//currently not using this in case of vendor
            if(vendorBean.getUserType().equals("Vendor")){
           sql.append(" SELECT EVL.ZONE, EVL.CIRCLE, EVL.DIVISION, EVL.PO_NUMBER, EVL.PO_DESC,   ");
           sql.append(" EVL.PO_TYPE, TO_DATE(TO_CHAR(EVL.PO_CREATION_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') PO_CREATION_DATE,   ");         
           sql.append(" nvl(invoice_number, NVL(EVL.VENDOR_INV_NO,NVL(EVL.SES_VEN_INV_NO,EVL.MIGO_VEN_INV_NO)))VENDOR_INV_NO,EVL.MSEDCL_INV_NO, ");
           sql.append(" EVL.SES_VEN_INV_NO,EVL.MIGO_VEN_INV_NO,   ");
           sql.append(" EVL.VALIDITY_START, EVL.VALIDITY_END, nvl(EVL.STATUS,'Technical')STATUS, EVL.INVOICE_DATE, EVL.SES_NO,EVL.MIGO_DOC,EVL.SOrMNum, ");             //to get vendor invoice number from sap table based on nvl of ses ->migo-> msedcl inv no -> vendor inv no from vendor dtl table

           sql.append(" EVL.CL_DOC_DT PAYMENT_DATE, EVL.INV_DT LIABILITY_DATE, EVL.SES_DOC_DATE,EVL.MIGO_DT,EVL.INV_AMT  ");
           sql.append(" FROM XXMIS_ERP_VENDOR_LIST EVL  ");
           sql.append(" WHERE TO_NUMBER(EVL.VENDOR_NUMBER) = ? ");
          if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
           sql.append(" AND EVL.PO_NUMBER = ? ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
           sql.append(" AND EVL.VENDOR_INV_NO = ? ");
          }
          if(!ApplicationUtils.isBlank(vendorBean.getPlantCode())){
           sql.append(" AND EVL.PLANT = ? ");
          }
          if(!((ApplicationUtils.isBlank(vendorBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorBean.getInvoiceToDate()))) ){
            sql.append(" and EVL.INV_DT BETWEEN ? AND  ? ");
          } 
          sql.append(" UNION SELECT EVL.ZONE, EVL.CIRCLE, EVL.DIVISION, EVL.PO_NUMBER, EVL.PROJECT_DESC, ");
           sql.append(" EVL.PO_TYPE, TO_DATE(TO_CHAR(EVL.CREATION_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') PO_CREATION_DATE, ");         
           sql.append(" EVL.VENDOR_INV_NO,EVL.MSEDCL_INV_NO,EVL.SES_VEN_INV_NO,EVL.MIGO_VEN_INV_NO, ");
           sql.append("EVL.VALIDITY_START, EVL.VALIDITY_END, EVL.STATUS, EVL.INV_DT, EVL.SES_NO,EVL.MIGO_DOC,EVL.SOrMNum,  ");
           sql.append(" EVL.CL_DOC_DT PAYMENT_DATE, EVL.INV_DT LIABILITY_DATE, EVL.SES_DOC_DATE,EVL.MIGO_DT,EVL.INV_AMT ");
           sql.append(" FROM XXMIS_PS_ERP_VENDOR_LIST EVL ");
           sql.append(" WHERE TO_NUMBER(EVL.VENDOR_NUMBER) = ? ");
          if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
           sql.append(" AND EVL.PO_NUMBER = ? ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
           sql.append(" AND EVL.VENDOR_INV_NO = ? ");
          }
          if(!ApplicationUtils.isBlank(vendorBean.getPlantCode())){
           sql.append(" AND EVL.PLANT = ? ");
          }
          if(!((ApplicationUtils.isBlank(vendorBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorBean.getInvoiceToDate()))) ){
            sql.append(" and EVL.INV_DT BETWEEN ? AND  ? ");
          } 
       
          
           logger.log(Level.INFO, "GetVendorListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
      statement.setString(i++, vendorBean.getVendorNumber());
       if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
      statement.setString(i++, vendorBean.getPONumber());    
       }
       if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
      statement.setString(i++, vendorBean.getVendorInvoiceNumber());  
       }
        if(!ApplicationUtils.isBlank(vendorBean.getPlantCode())){
      statement.setString(i++, vendorBean.getPlantCode());  
       }
       if(!((ApplicationUtils.isBlank(vendorBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorBean.getInvoiceToDate()))) ){
      statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
      statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
       } 
       statement.setString(i++, vendorBean.getVendorNumber());
       if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
      statement.setString(i++, vendorBean.getPONumber());    
       }
       if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
      statement.setString(i++, vendorBean.getVendorInvoiceNumber());  
       }
        if(!ApplicationUtils.isBlank(vendorBean.getPlantCode())){
      statement.setString(i++, vendorBean.getPlantCode());  
       }
       if(!((ApplicationUtils.isBlank(vendorBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorBean.getInvoiceToDate()))) ){
      statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
      statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
       } 
      }
            
            if(vendorBean.getUserType().equals("Emp")){
                sql.append("SELECT EVL.APPL_ID,EVL.MODULE_TYPE,nvl(NVL(DIVISION,CIRCLE),zone)LOCATION,EVL.PO_NUMBER,EVL.PO_DESC, EVL.VENDOR_NUMBER, EVL.VENDOR_NAME,"); 
	 sql.append(" EVL.INVOICE_NUMBER,														     ");
	 sql.append("  EVL.INVOICE_DATE,  EVL.INVOICE_AMOUNT,EVL.INWARD_NUMBER,EVL.INWARD_DATE,								     ");
	 sql.append("  EVL.INVOICE_STATUS														     ");
	 sql.append("  FROM summary_status EVL left outer join PO_LINE_INV_DETAILS_ORG a            					     ");
	 sql.append("  on a.appl_id = EVL.appl_id where  (EVL.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h,		     ");
	 sql.append("  hr_all_organization_units h1, hri_org_hrchy_summary hr										     ");
	 sql.append("  where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'						     ");
	 sql.append("  and hr.organization_id= ? and h1.organization_id=hr.organization_id 								     ");
	 sql.append("  and h.type in ('HO','REG','ZON','CIR','DIV') )   										     ");
	 sql.append("  or																     ");
	 sql.append("  a.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h,							     ");
	 sql.append("  hr_all_organization_units h1, hri_org_hrchy_summary hr										     ");
	 sql.append("  where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61'						     ");
	 sql.append("  and hr.organization_id= ? and h1.organization_id=hr.organization_id 								     ");
	 sql.append("  and h.type in ('HO','REG','ZON','CIR','DIV') ) )  										     ");
         sql.append("   AND STATUS='Verified'   													     ");
  
          if(!ApplicationUtils.isBlank(vendorBean.getVendorNumber())){
           sql.append(" AND EVL.VENDOR_NUMBER = ?  ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
           sql.append(" AND EVL.PO_NUMBER = ?  ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
           sql.append(" AND EVL.INVOICE_NUMBER = ?  ");
          }
          if(!((ApplicationUtils.isBlank(vendorBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorBean.getInvoiceToDate()))) ){
            sql.append(" AND INVOICE_DATE BETWEEN ? AND  ? ");
          } 
     if(!ApplicationUtils.isBlank(vendorBean.getINV_STATUS())){
           sql.append(" AND EVL.INVOICE_STATUS = ?  ");
          }
     
    sql.append("  group by EVL.APPL_ID,EVL.MODULE_TYPE,nvl(NVL(DIVISION,CIRCLE),zone),EVL.PO_NUMBER,EVL.PO_DESC, EVL.VENDOR_NUMBER, EVL.VENDOR_NAME,  ");
      sql.append("     EVL.INVOICE_NUMBER, ");
        sql.append("    EVL.INVOICE_DATE,  EVL.INVOICE_AMOUNT,EVL.INWARD_NUMBER,EVL.INWARD_DATE, ");
       sql.append("     EVL.INVOICE_STATUS ");
       /*   sql.append("UNION ALL");
           sql.append(" SELECT EVL.APPL_ID,EVL.MODULE_TYPE,nvl(NVL(DIVISION,CIRCLE),zone)LOCATION,EVL.VENDOR_PO_NUMBER,EVL.PROJECT_DESC, EVL.VENDOR_NUMBER, EVL.VENDOR_NAME,  ");
                  
          sql.append(" EVL.INVOICE_NUMBER, ");
           sql.append(" EVL.INVOICE_DATE,  EVL.INVOICE_AMOUNT,EVL.INWARD_NUMBER,EVL.INWARD_DATE, ");
           sql.append(" EVL.INVOICE_STATUS ");
           sql.append(" FROM PS_VENDOR_VERIFIED_INPUT_LIST EVL  ");            
           sql.append(" WHERE EVL.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h, ");
           sql.append(" hr_all_organization_units h1, hri_org_hrchy_summary hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id=? and h1.organization_id=hr.organization_id  ");
           sql.append(" and h.type in ('HO','REG','ZON','CIR','DIV') )  ");  
              sql.append(" AND STATUS='Verified'  ");  
           
          if(!ApplicationUtils.isBlank(vendorBean.getVendorNumber())){
           sql.append(" AND EVL.VENDOR_NUMBER = ?  ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
           sql.append(" AND EVL.VENDOR_PO_NUMBER = ?  ");
          } 
          if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
           sql.append(" AND EVL.INVOICE_NUMBER = ?  ");
          }
          if(!((ApplicationUtils.isBlank(vendorBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorBean.getInvoiceToDate()))) ){
            sql.append(" AND EVL.INVOICE_DATE BETWEEN ? AND  ? ");
          } 
        if(!ApplicationUtils.isBlank(vendorBean.getINV_STATUS())){
           sql.append(" AND EVL.INVOICE_STATUS = ?   ");
          }*/
           sql.append(" ORDER BY  APPL_ID DESC");
           logger.log(Level.INFO, "GetVendorListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
      
             statement.setString(i++, vendorBean.getLocationId());
                statement.setString(i++, vendorBean.getLocationId());
            
      
             if(!ApplicationUtils.isBlank(vendorBean.getVendorNumber())){
      statement.setString(i++, vendorBean.getVendorNumber());    
       }
             
       if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
      statement.setString(i++, vendorBean.getPONumber());    
       }
       if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
      statement.setString(i++, vendorBean.getVendorInvoiceNumber());  
       }
       if(!((ApplicationUtils.isBlank(vendorBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorBean.getInvoiceToDate()))) ){
      statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
      statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
       } 
           if(!ApplicationUtils.isBlank(vendorBean.getINV_STATUS())){
               statement.setString(i++, vendorBean.getINV_STATUS()); 
          }
      /*  statement.setString(i++, vendorBean.getLocationId());
               
            
      
             if(!ApplicationUtils.isBlank(vendorBean.getVendorNumber())){
      statement.setString(i++, vendorBean.getVendorNumber());    
       }
             
       if(!ApplicationUtils.isBlank(vendorBean.getPONumber())){
      statement.setString(i++, vendorBean.getPONumber());    
       }
       if(!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())){
      statement.setString(i++, vendorBean.getVendorInvoiceNumber());  
       }
       if(!((ApplicationUtils.isBlank(vendorBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorBean.getInvoiceToDate()))) ){
      statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
      statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
       } 
           if(!ApplicationUtils.isBlank(vendorBean.getINV_STATUS())){
           statement.setString(i++, vendorBean.getINV_STATUS());  
          }*/
      }
            
            
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
