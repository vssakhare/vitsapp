/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.listHelper;

import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.VendorPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class GetPOListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetPOListQueryHelper.class);
    private POBean poBean;
    private VendorPrezData vendorPrezDataObj;

    public GetPOListQueryHelper(POBean poBean) {  
        this.poBean = poBean;
    }

    public GetPOListQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
      
    }

    public Object getDataObject(ResultSet results) throws Exception {
        POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "GetPOListQueryHelper ::: getDataObject() :: method called ::");
//            if(poBean.getUserType().equals("Emp")) {
//            poBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER"));
//            poBeanObj.setVendorName(results.getString("VENDOR_NAME"));  
//            }
            poBeanObj.setPONumber(results.getString("PO_NUMBER"));
            poBeanObj.setPODesc(results.getString("PO_DESC"));           
          //  poBeanObj.setPOType(results.getString("PO_TYPE"));
            if(poBean.getUserType().equals("Vendor")) {
                poBeanObj.setPURCHASING_GROUP(results.getString("PURCHASING_GROUP"));
            }
            poBeanObj.setPlant(results.getString("PLANT"));
            
            poBeanObj.setPlantDesc(results.getString("PLANT_DESC"));
                        if(poBean.getUserType().equals("Vendor")) {
            poBeanObj.setSelectedModuleType(results.getString("MODULE"));
                        }
          

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPOListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        
        ResultSet rs = null;
         int i = 1; 
        try {//query is used to fetch the po number in drop down list and also location while creating invoice
            logger.log(Level.INFO, "GetPOListQueryHelper ::: getQueryResults() :: method called ::");           
           if(poBean.getUserType().equals("Vendor")) {
               if(!ApplicationUtils.isBlank(poBean.getEmp_Po_YN())){
                if (poBean.getEmp_Po_YN().equals("Y")) {
         
                      sql.append("SELECT DISTINCT EVL.PO_NUMBER, nvl(EVL.PO_DESC,' ')PO_DESC,EVL.PLANT,EVL.PLANT_DESC,EVL.PURCHASING_GROUP , MODULE FROM PS_PM_PURCHASING_LOCATION EVL ");           
           sql.append("WHERE TO_NUMBER(EVL.VENDOR_NUMBER) = ? ");
                      sql.append(" AND EVL.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h, ");
           sql.append(" hr_all_organization_units h1, hri_org_hrchy_summary hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id  ");
           sql.append(" and h.type in ('HO','ZON','CIR','DIV') ) ");
                }
           }else{
                      sql.append(" SELECT DISTINCT EVL.PO_NUMBER, nvl(EVL.PO_DESC,' ')PO_DESC,EVL.PLANT,EVL.PLANT_DESC,EVL.PURCHASING_GROUP ,'PM' MODULE ");          
           sql.append(" FROM XXMIS_ERP_PURCHASING_LOCATION EVL,ORGANISATION_MASTER ORG   ");           
           sql.append(" WHERE TO_NUMBER(EVL.VENDOR_NUMBER) = ? and ORG.PLANT=EVL.PLANT ");
         /*  if(!ApplicationUtils.isBlank(poBean.getPONumber())){
           sql.append(" AND EVL.PO_NUMBER = ?  ");
          } */ 	   sql.append("   UNION 									      ");
           sql.append("   SELECT DISTINCT MATERIAL_PO PO_NUMBER,nvl(PROJECT_DESC,SCHEME_DESC)PROJECT_DESC     ");
     	   sql.append("  ,EPL.PLANT ,NVL(DIVISION,NVL(CIRCLE,ZONE))PLANT_DESC,purchasing_group ,'PS' MODULE                ");
           sql.append("  FROM XXMIS_ERP_PROJ_LIST EPL,ORGANISATION_MASTER ORG               		      ");
           sql.append("  WHERE TO_NUMBER(VENDOR_NUMBER) = ? and  	MATERIAL_PO IS NOT NULL AND						      ");
           sql.append("  ORG.PLANT=EPL.PLANT  								      ");
          		
           sql.append("  UNION 										      ");
           sql.append("   SELECT DISTINCT CENTAGES_PO PO_NUMBER,nvl(PROJECT_DESC,SCHEME_DESC)PROJECT_DESC     ");
           sql.append(" ,EPL.PLANT ,NVL(DIVISION,NVL(CIRCLE,ZONE))PLANT_DESC,purchasing_group   ,'PS' MODULE                 ");
           sql.append("  FROM XXMIS_ERP_PROJ_LIST EPL,ORGANISATION_MASTER ORG               		      ");
           sql.append("  WHERE TO_NUMBER(VENDOR_NUMBER) = ? and 	CENTAGES_PO IS NOT NULL AND						      ");
           sql.append("  ORG.PLANT=EPL.PLANT 								      ");
           sql.append("  UNION										      ");
             	
           sql.append("   SELECT DISTINCT SERVICE_PO PO_NUMBER,nvl(PROJECT_DESC,SCHEME_DESC)PROJECT_DESC      ");
           sql.append("  ,EPL.PLANT ,NVL(DIVISION,NVL(CIRCLE,ZONE))PLANT_DESC,purchasing_group     ,'PS' MODULE              ");
           sql.append("  FROM XXMIS_ERP_PROJ_LIST EPL,ORGANISATION_MASTER ORG               		      ");
           sql.append("  WHERE TO_NUMBER(VENDOR_NUMBER) = ? and   SERVICE_PO IS NOT NULL AND					      ");
           sql.append("  ORG.PLANT=EPL.PLANT  								      ");

                }
           }
           if(poBean.getUserType().equals("Emp")) {
           sql.append(" SELECT  DISTINCT EAPL.PO_NUMBER, nvl(EAPL.PO_DESC,' ')PO_DESC,'' PO_TYPE ,EAPL.LOCATION PLANT,NVL(DIVISION,NVL(CIRCLE,ZONE))PLANT_DESC ");          
           sql.append(" FROM XXMIS_ERP_VENDOR_INPUT_LIST EAPL ");           
           sql.append(" WHERE EAPL.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h, ");
           sql.append(" hr_all_organization_units h1, hri_org_hrchy_summary hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id  ");
           sql.append(" and h.type in ('HO','ZON','CIR','DIV') ) ");
           // sql.append(" and po_number in (select po_number from xxmis_erp_vendor_dtl) ");
           if(!ApplicationUtils.isBlank(poBean.getPONumber())){
           sql.append(" AND EAPL.PO_NUMBER = ?  ");
          } 
            sql.append(" UNION ALL");
             sql.append(" SELECT DISTINCT case when EAPL.PO_NUMBER is null then PROJECT_ID else EAPL.PO_NUMBER end, EAPL.PROJECT_DESC, '' PO_TYPE ,EAPL.LOCATION PLANT,NVL(DIVISION,NVL(CIRCLE,ZONE))PLANT_DESC ");          
           sql.append(" FROM XXMIS_ERP_PS_VENDOR_INPUT_LIST EAPL ");           
           sql.append(" WHERE EAPL.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h, ");
           sql.append(" hr_all_organization_units h1, hri_org_hrchy_summary hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id  ");
           sql.append(" and h.type in ('HO','ZON','CIR','DIV') ) ");
            //sql.append(" and PROJECT_CODE in (select PROJECT_ID from xxmis_ERP_PS_vendor_dtl) ");
           if(!ApplicationUtils.isBlank(poBean.getPONumber())){
           sql.append(" AND EAPL.PO_NUMBER = ?  ");
          } 
           
           }
          
                     
           logger.log(Level.INFO, "GetPOListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());  
             if(poBean.getUserType().equals("Vendor")) {
                  if(!ApplicationUtils.isBlank(poBean.getEmp_Po_YN())){
                if (poBean.getEmp_Po_YN().equals("Y")) {
                    statement.setString(i++, poBean.getVendorNumber());
                      statement.setString(i++, poBean.getLocationId());
                }
                  }
                  else{
            statement.setString(i++, poBean.getVendorNumber());
             /*if(!ApplicationUtils.isBlank(poBean.getPONumber()) ){
                   statement.setString(i++, poBean.getPONumber());
               
               }*/
            statement.setString(i++, poBean.getVendorNumber());
            statement.setString(i++, poBean.getVendorNumber());
            statement.setString(i++, poBean.getVendorNumber());
                  }
             }
              if(poBean.getUserType().equals("Emp")) {
                   statement.setString(i++, poBean.getLocationId());
              if(!ApplicationUtils.isBlank(poBean.getPONumber()) ){
                   statement.setString(i++, poBean.getPONumber());
               }
              statement.setString(i++, poBean.getLocationId());
              if(!ApplicationUtils.isBlank(poBean.getPONumber()) ){
                   statement.setString(i++, poBean.getPONumber());
               
               }
              }
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPOListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
