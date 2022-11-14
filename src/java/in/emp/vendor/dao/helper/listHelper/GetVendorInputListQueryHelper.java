    /*
    * To change this template, choose Tools | Templates
    * and open the template in the editor.
    */
    package in.emp.vendor.dao.helper.listHelper;

    import in.emp.common.ApplicationConstants;
    import in.emp.dao.QueryHelper;
    import in.emp.util.ApplicationUtils;
    import in.emp.vendor.bean.VendorInputBean;
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
    public class GetVendorInputListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorInputListQueryHelper.class);
    private VendorInputBean vendorInputBean;
    private VendorPrezData vendorPrezDataObj;

    public GetVendorInputListQueryHelper(VendorInputBean vendorInputBean) {
        this.vendorInputBean = vendorInputBean;
    }

    public GetVendorInputListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorInputBean = this.vendorPrezDataObj.getVendorInputBean();
    }

    public Object getDataObject(ResultSet results) throws Exception {
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        try {
            logger.log(Level.INFO, "GetVendorInputListQueryHelper ::: getDataObject() :: method called ::");

            vendorInputBeanObj.setApplId(results.getString("APPL_ID")); 
            vendorInputBeanObj.setApplDate(results.getDate("APPL_DATE"));

            vendorInputBeanObj.setPONumber(results.getString("PO_NUMBER"));
            vendorInputBeanObj.setPODesc(results.getString("PO_DESC"));

            vendorInputBeanObj.setVendorInvoiceAmount(results.getString("INVOICE_AMOUNT"));
            vendorInputBeanObj.setVendorInvoiceNumber(results.getString("INVOICE_NUMBER"));  
            vendorInputBeanObj.setVendorInvoiceDate(results.getDate("INVOICE_DATE"));
           
            vendorInputBeanObj.setInwardNumber(results.getString("INWARD_NUMBER"));  
            vendorInputBeanObj.setInwardDate(results.getDate("INWARD_DATE"));


            vendorInputBeanObj.setInvoiceFromDate(results.getDate("INVOICE_FROM_DT"));                    
            vendorInputBeanObj.setInvoiceToDate(results.getDate("INVOICE_TO_DT")); 
            vendorInputBeanObj.setSaveFlag(results.getString("STATUS"));
              if(vendorInputBean.getUserType().equals("Vendor")){
            vendorInputBeanObj.setInvoiceStatus(results.getString("INVOICE_STATUS"));
              }
            vendorInputBeanObj.setPendingSince(results.getString("PENDING_SINCE"));
            vendorInputBeanObj.setSelectedModuleType(results.getString("MODULE_TYPE"));
             if(vendorInputBean.getUserType().equals("Vendor")){
            vendorInputBeanObj.setModuleSaveFlag(results.getString("MODULE_SAVE_FLAG"));
            vendorInputBeanObj.setINVOICE_TYPE(results.getString("INVOICE_TYPE"));
             }
            //vendorInputBeanObj.setReason(results.getString("REASON"));
              if(vendorInputBean.getUserType().equals("Emp")){
                 vendorInputBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER"));
                 vendorInputBeanObj.setVendorName(results.getString("VENDOR_NAME")); 
                  vendorInputBeanObj.setVendorUpdatedDate(results.getDate("CHANGED_DATE"));
                  vendorInputBeanObj.setLocation(results.getString("LOCATION"));
                  //vendorInputBeanObj.setTotalPoAmt(results.getString("TOTAL_PO_AMT"));
                  vendorInputBeanObj.setForwardToPlant(results.getString("FORWARDED_TO_PLANT"));
                  vendorInputBeanObj.setForwardToDesc(results.getString("FORWARDED_TO_DESC"));
                  
              }

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

           if(vendorInputBean.getUserType().equals("Vendor")){
           sql.append(" SELECT  EVIL.APPL_ID, TO_DATE(TO_CHAR(EVIL.CREATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') APPL_DATE, EVIL.PO_NUMBER, EVIL.PO_DESC, EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE,EVIL.MODULE_SAVE_FLAG, ");//to differentiate between proj id and po number 
            sql.append(" EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_DATE, ");  
           sql.append(" EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INWARD_DATE, "); 
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_FROM_DT, ");         
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_TO_DT, EVIL.STATUS,EVIL.INVOICE_STATUS,' ' INVOICE_TYPE, ");
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
           sql.append(" AND EVIL.INVOICE_NUMBER = TRIM(?) ");
          }
               if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceDate())){
           sql.append(" AND EVIL.INVOICE_DATE = TRIM(?) ");
          }
          //if(!((ApplicationUtils.isBlank(vendorInputBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorInputBean.getInvoiceToDate()))) ){
            //sql.append(" and EVIL.INVOICE_DATE BETWEEN ? AND  ? ");
          //}
       // sql.append(" ORDER BY  EVIL.APPL_ID DESC,EVIL.STATUS ");
               
               //COMMENTED FOR USING SUMMARY STATUS TABLE 
    /*   sql.append(" UNION ALL ");
        sql.append(" SELECT EVIL.APPL_ID, TO_DATE(TO_CHAR(EVIL.CREATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') APPL_DATE, EVIL.PROJECT_ID, EVIL.PROJECT_DESC, EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE,EVIL.MODULE_SAVE_FLAG, ");//to differentiate between proj id and po number 
            sql.append(" EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_DATE, ");  
           sql.append(" EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INWARD_DATE, "); 
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_FROM_DT, ");         
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_TO_DT, EVIL.STATUS,EVIL.INVOICE_STATUS, INVOICE_TYPE,");
           sql.append(" case when STATUS = 'Submitted' THEN TO_NUMBER(trunc(sysdate) - to_date(to_char(nvl(INVOICE_RESUBMIT_DATE,INVOICE_SUBMIT_DATE), 'yyyy-mm-dd'),'yyyy-mm-dd')) else null end PENDING_SINCE ");
           sql.append(" FROM PS_VENDOR_VERIFIED_INPUT_LIST EVIL ");
           sql.append(" WHERE TO_NUMBER(EVIL.VENDOR_NUMBER) = ? ");
            if(!ApplicationUtils.isBlank(vendorInputBean.getPONumber())){
           sql.append(" AND EVIL.PROJECT_ID = TRIM(?) ");//combined for both po number and proj id
          } 
             if(!ApplicationUtils.isBlank(vendorInputBean.getApplId())){
           sql.append(" AND EVIL.APPL_ID = TRIM(?) ");
          }
              if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())){
           sql.append(" AND EVIL.INVOICE_NUMBER = TRIM(?) ");
          }
               if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceDate())){
           sql.append(" AND EVIL.INVOICE_DATE = TRIM(?) ");
          }*/
          sql.append(" UNION ALL ");
        sql.append(" SELECT EVIL.APPL_ID, TO_DATE(TO_CHAR(EVIL.CREATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') APPL_DATE,'' PROJECT_ID,''PROJECT_DESC, EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE,EVIL.MODULE_SAVE_FLAG, ");//to differentiate between proj id and po number 
            sql.append(" EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_DATE, ");  
           sql.append(" EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INWARD_DATE, "); 
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_FROM_DT, ");         
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_TO_DT, EVIL.STATUS, '' INVOICE_STATUS,' ' INVOICE_TYPE, ");
           sql.append(" null PENDING_SINCE ");
           sql.append(" FROM ERP_NON_PO_VENDOR_INPUT_LIST EVIL ");
           sql.append(" WHERE TO_NUMBER(EVIL.VENDOR_NUMBER) = ? ");
            sql.append(" AND UPDATED_IN_MAIN_FLAG IS NULL ");
             if(!ApplicationUtils.isBlank(vendorInputBean.getApplId())){
           sql.append(" AND EVIL.APPL_ID = TRIM(?) ");
          }
              if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())){
           sql.append(" AND EVIL.INVOICE_NUMBER = TRIM(?) ");
          }
               if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceDate())){
           sql.append(" AND EVIL.INVOICE_DATE = TRIM(?) ");
          }
          
        sql.append(" ORDER BY  APPL_ID DESC,STATUS ");

           logger.log(Level.INFO, "GetVendorInputListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
  /*   statement.setString(i++, vendorInputBean.getVendorNumber());
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
        
        }*/
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
        //third union
          statement.setString(i++, vendorInputBean.getVendorNumber());
        if(!ApplicationUtils.isBlank(vendorInputBean.getApplId())){
           statement.setString(i++, vendorInputBean.getApplId()); 
        }
       if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())){
      statement.setString(i++, vendorInputBean.getVendorInvoiceNumber());  
       }
        if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceDate())){
            statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBean.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
        
        }
       //if(!((ApplicationUtils.isBlank(vendorInputBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorInputBean.getInvoiceToDate()))) ){
      //statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
      //statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
       //}   
    }
                if(vendorInputBean.getUserType().equals("Emp")){
           sql.append(" SELECT EVIL.APPL_ID, TO_DATE(TO_CHAR(EVIL.CREATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') APPL_DATE,TO_DATE(TO_CHAR(EVIL.UPDATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') CHANGED_DATE, EVIL.PO_NUMBER, EVIL.PO_DESC, EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE, ");
           sql.append(" EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_DATE, ");         
           sql.append(" EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INWARD_DATE, "); 
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_FROM_DT, ");   
           sql.append(" TO_NUMBER(trunc(sysdate) - to_date(to_char(nvl(INVOICE_RESUBMIT_DATE,INVOICE_SUBMIT_DATE), 'yyyy-mm-dd'),'yyyy-mm-dd'))PENDING_SINCE, ");
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_TO_DT, EVIL.STATUS, EVIL.VENDOR_NUMBER, EVIL.VENDOR_NAME,nvl(NVL(DIVISION,CIRCLE),zone)LOCATION ,EVIL.FORWARDED_TO_PLANT, EVIL.FORWARDED_TO_DESC ");         
           sql.append(" FROM SUMMARY_STATUS EVIL left outer join PO_LINE_INV_DETAILS_ORG a ");            
           sql.append(" on a.appl_id = evil.appl_id  where  (EVIL.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h, ");
           sql.append(" hr_all_organization_units h1, hri_org_hrchy_summary hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id ) ");
           sql.append(" or ");
           sql.append(" a.office_code in (select h.organization_id from hr_all_organization_units h, ");
            sql.append(" hr_all_organization_units h1, hri_org_hrchy_summary hr ");
            sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
            sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id) ) ");
           //  sql.append(" and h.type in ('HO','ZON','CIR','DIV') )  ");  
           //sql.append(" and h.type like decode(?,'ALL','%',?) )  "); 
           if(!ApplicationUtils.isBlank(vendorInputBean.getVendorNumber())){
           sql.append(" AND EVIL.VENDOR_NUMBER = ?  ");
          } 
            if(!ApplicationUtils.isBlank(vendorInputBean.getPONumber())){
           sql.append(" AND EVIL.PO_NUMBER = ?  ");
          } 
            
             if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())){
           sql.append(" AND EVIL.INVOICE_NUMBER = ?  ");
          }
          if(!((ApplicationUtils.isBlank(vendorInputBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorInputBean.getInvoiceToDate()))) ){
            sql.append(" AND EVIL.INVOICE_SUBMIT_DATE BETWEEN ? AND  ? ");
          }
          if(!ApplicationUtils.isBlank(vendorInputBean.getSaveFlag())){
          sql.append(" AND NVL(EVIL.STATUS,'Saved') = ? ");
          }
          sql.append(" AND NVL(EVIL.STATUS,'Saved') IN ('Submitted','Verified','Rejected','Completed','Forwarded') ");
         sql.append(" group by EVIL.APPL_ID, TO_DATE(TO_CHAR(EVIL.CREATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') ,");
            sql.append("TO_DATE(TO_CHAR(EVIL.UPDATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') , EVIL.PO_NUMBER, EVIL.PO_DESC");
            sql.append(", EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE, ");
            sql.append("EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') ,   ");       
            sql.append("EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') ,  ");
            sql.append("TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') ,    ");
            sql.append("TO_NUMBER(trunc(sysdate) - to_date(to_char(nvl(INVOICE_RESUBMIT_DATE,INVOICE_SUBMIT_DATE), 'yyyy-mm-dd'),'yyyy-mm-dd')), ");
            sql.append("TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') , EVIL.STATUS, EVIL.VENDOR_NUMBER, EVIL.VENDOR_NAME,nvl(NVL(DIVISION,CIRCLE),zone) ,EVIL.FORWARDED_TO_PLANT, EVIL.FORWARDED_TO_DESC ");
         //sql.append(" ORDER BY EVIL.CREATED_TIME_STAMP DESC ");
     /*   sql.append(" UNION ALL ");
        sql.append(" SELECT EVIL.APPL_ID, TO_DATE(TO_CHAR(EVIL.CREATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') APPL_DATE,TO_DATE(TO_CHAR(EVIL.UPDATED_TIME_STAMP, 'DD-MM-YYYY'), 'DD-MM-YYYY') CHANGED_DATE, EVIL.PO_NUMBER, EVIL.PROJECT_DESC, EVIL.INVOICE_AMOUNT,EVIL.MODULE_TYPE, ");
           sql.append(" EVIL.INVOICE_NUMBER, TO_DATE(TO_CHAR(EVIL.INVOICE_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_DATE, ");         
           sql.append(" EVIL.INWARD_NUMBER, TO_DATE(TO_CHAR(EVIL.INWARD_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') INWARD_DATE, "); 
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_FROM_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_FROM_DT, ");   
           sql.append(" TO_NUMBER(trunc(sysdate) - to_date(to_char(nvl(INVOICE_RESUBMIT_DATE,INVOICE_SUBMIT_DATE), 'yyyy-mm-dd'),'yyyy-mm-dd'))PENDING_SINCE, ");
           sql.append(" TO_DATE(TO_CHAR(EVIL.INVOICE_TO_DT, 'DD-MM-YYYY'), 'DD-MM-YYYY') INVOICE_TO_DT, EVIL.STATUS, EVIL.VENDOR_NUMBER, EVIL.VENDOR_NAME,nvl(NVL(DIVISION,CIRCLE),zone)LOCATION,'' FORWARDED_TO_PLANT, '' FORWARDED_TO_DESC ");         
           sql.append(" FROM XXMIS_ERP_PS_AUTH_INPUT_LIST EVIL  ");            
           sql.append(" WHERE EVIL.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h, ");
           sql.append(" hr_all_organization_units h1, hri_org_hrchy_summary hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id ) ");
         //  sql.append(" and h.type in ('HO','ZON','CIR','DIV') )  ");  
           //sql.append(" and h.type like decode(?,'ALL','%',?) )  "); 
           if(!ApplicationUtils.isBlank(vendorInputBean.getVendorNumber())){
           sql.append(" AND EVIL.VENDOR_NUMBER = ?  ");
          } 
            if(!ApplicationUtils.isBlank(vendorInputBean.getPONumber())){
           sql.append(" AND EVIL.PO_NUMBER = ?  ");
          } 
            
             if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())){
           sql.append(" AND EVIL.INVOICE_NUMBER = ?  ");
          }
          if(!((ApplicationUtils.isBlank(vendorInputBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorInputBean.getInvoiceToDate()))) ){
            sql.append(" AND EVIL.INVOICE_SUBMIT_DATE BETWEEN ? AND  ? ");
          }
          if(!ApplicationUtils.isBlank(vendorInputBean.getSaveFlag())){
          sql.append(" AND NVL(EVIL.STATUS,'Saved') = ? ");
          }
          sql.append(" AND NVL(EVIL.STATUS,'Saved') IN ('Submitted','Verified','Rejected','Completed') ");
        sql.append(" ORDER BY APPL_ID DESC ");*/
           logger.log(Level.INFO, "GetVendorInputListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            
            statement.setString(i++, vendorInputBean.getLocationId());
            //statement.setString(i++, vendorInputBean.getLocationOpt());
            //statement.setString(i++, vendorInputBean.getLocationOpt());
        statement.setString(i++, vendorInputBean.getLocationId());
      if(!ApplicationUtils.isBlank(vendorInputBean.getVendorNumber())){
      statement.setString(i++, vendorInputBean.getVendorNumber());    
       }
            
       if(!ApplicationUtils.isBlank(vendorInputBean.getPONumber())){
      statement.setString(i++, vendorInputBean.getPONumber());    
       }
       if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())){
      statement.setString(i++, vendorInputBean.getVendorInvoiceNumber());  
       }
       if(!((ApplicationUtils.isBlank(vendorInputBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorInputBean.getInvoiceToDate()))) ){
      statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
      statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
       } 
       if(!ApplicationUtils.isBlank(vendorInputBean.getSaveFlag())){
           statement.setString(i++, vendorInputBean.getSaveFlag());
       }
       //UNION
      /* statement.setString(i++, vendorInputBean.getLocationId());
            //statement.setString(i++, vendorInputBean.getLocationOpt());
            //statement.setString(i++, vendorInputBean.getLocationOpt());
      
      if(!ApplicationUtils.isBlank(vendorInputBean.getVendorNumber())){
      statement.setString(i++, vendorInputBean.getVendorNumber());    
       }
            
       if(!ApplicationUtils.isBlank(vendorInputBean.getPONumber())){
      statement.setString(i++, vendorInputBean.getPONumber());    
       }
       if(!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())){
      statement.setString(i++, vendorInputBean.getVendorInvoiceNumber());  
       }
       if(!((ApplicationUtils.isBlank(vendorInputBean.getInvoiceFromDate())) && (ApplicationUtils.isBlank(vendorInputBean.getInvoiceToDate()))) ){
      statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
      statement.setDate(i++,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
       } 
       if(!ApplicationUtils.isBlank(vendorInputBean.getSaveFlag())){
           statement.setString(i++, vendorInputBean.getSaveFlag());
       }*/
    }
           rs = statement.executeQuery();
       
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetVendorInputListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
    }
