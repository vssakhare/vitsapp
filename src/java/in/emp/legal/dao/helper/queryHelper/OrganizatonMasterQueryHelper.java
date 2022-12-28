/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;

import in.emp.dao.QueryHelper;
import in.emp.legal.bean.OrganizationMasterBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Rikma Rai
 */
public class OrganizatonMasterQueryHelper implements QueryHelper{

    private static Logger logger = Logger.getLogger(OrganizatonMasterQueryHelper.class);
    OrganizationMasterBean organizationMasterBean = null;

    public OrganizatonMasterQueryHelper(OrganizationMasterBean oraganizationMasterBean) {
        this.organizationMasterBean = oraganizationMasterBean;
    }

//    public GetErpLegalInvoiceStatusList(VendorPrezData vendorPrezDataObj) {
//        this.vendorPrezDataObj = vendorPrezDataObj;
//        this.vendorBean = this.vendorPrezDataObj.getVendorBean();
//    }
    public Object getDataObject(ResultSet result) throws Exception {
        OrganizationMasterBean organizationMasterBean = new OrganizationMasterBean();
        try {
            logger.log(Level.INFO, "GetErpLegalInvoiceStatusList ::: getDataObject() :: method called ::");
            

            organizationMasterBean.setOrganizationId(result.getString("ORGANIZATION_ID"));
            organizationMasterBean.setOrgIdSap(result.getString("ORG_ID_SAP"));
            organizationMasterBean.setOfficeType(result.getString("OFFICE_TYPE"));
            organizationMasterBean.setOfficeLevel(result.getString("OFFICE_LEVEL"));
            organizationMasterBean.setRegionId(result.getString("REGION_ID"));
            organizationMasterBean.setRegionName(result.getString("REGION_NAME"));
            organizationMasterBean.setZoneId(result.getString("ZONE_ID"));
            organizationMasterBean.setZoneName(result.getString("ZONE_NAME"));
            organizationMasterBean.setCircleId(result.getString("CIRCLE_ID"));
            organizationMasterBean.setCircleName(result.getString("CIRCLE_NAME"));
            organizationMasterBean.setDivisionId(result.getString("DIVISION_ID"));
            organizationMasterBean.setDivisionNAme(result.getString("DIVISION_NAME"));
            organizationMasterBean.setSubDivId(result.getString("SUB_DIVISION_ID"));
            organizationMasterBean.setSubDivName(result.getString("SUB_DIVISION_NAME"));
            organizationMasterBean.setDeptId(result.getString("ORGANIZATION_ID"));
            organizationMasterBean.setDeptName(result.getString("ORGANIZATION_NAME"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetErpLegalInvoiceStatusList :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return organizationMasterBean;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i = 1;
        try {
            logger.log(Level.INFO, "GetErpLegalInvoiceStatusList ::: getQueryResults() :: method called ::");
//            sql.append(" SELECT * FROM ERP_LEGAL_INVOICE_STATUS ");
            sql.append(" select ORGANIZATION_ID,ORGANIZATION_NAME,ORG_ID_SAP,OFFICE_TYPE,OFFICE_LEVEL,REGION_ID,REGION_NAME,ZONE_ID,ZONE_NAME,CIRCLE_ID,CIRCLE_NAME,DIVISION_ID,DIVISION_NAME,SUB_DIVISION_ID,SUB_DIVISION_NAME ");
            sql.append(" from xxmis_organization_master ");
            sql.append(" where office_type=? ");
            if(organizationMasterBean.getOfficeType().equalsIgnoreCase("ZON")){
                sql.append(" and region_id=? ");
            }else if(organizationMasterBean.getOfficeType().equalsIgnoreCase("CIR")){
                sql.append(" and zone_id=? ");
            }else if(organizationMasterBean.getOfficeType().equalsIgnoreCase("DIV")){
                sql.append(" and circle_id=? ");
            }else if(organizationMasterBean.getOfficeType().equalsIgnoreCase("SUB")){
                sql.append(" and DIVISION_ID=? ");
            }else if(organizationMasterBean.getOfficeType().equalsIgnoreCase("DEPT")){
                sql.append(" and region_id=? and office_level=3 and org_id_sap is not null");
            }    
//            else if(organizationMasterBean.getOfficeType().equalsIgnoreCase("SUB")){
//                sql.append(" and SUB_DIVISION_ID=? ");
//            }
            statement = connection.prepareStatement(sql.toString());
            statement.setString(i++, organizationMasterBean.getOfficeType());
            if(organizationMasterBean.getSelectedOfficeCode()!=null && organizationMasterBean.getOfficeType()!=null){
                statement.setString(i++, organizationMasterBean.getSelectedOfficeCode());
            }
            System.out.println("sql::::::::"+sql+"  office_type::"+organizationMasterBean.getOfficeType()+"   selected:::"+organizationMasterBean.getSelectedOfficeCode());
//            if(organizationMasterBean.getOfficeType().equalsIgnoreCase("ZON")){
//                statement.setString(i++, organizationMasterBean.getRegionId());
//            }else if(organizationMasterBean.getOfficeType().equalsIgnoreCase("CIR")){
//                statement.setString(i++, organizationMasterBean.getZoneId());
//            }else if(organizationMasterBean.getOfficeType().equalsIgnoreCase("DIV")){
//                statement.setString(i++, organizationMasterBean.getCircleId());
//            }
//            else if(organizationMasterBean.getOfficeType().equalsIgnoreCase("DIV")){
//                statement.setString(i++, organizationMasterBean.getDivisionId());
//            }else if(organizationMasterBean.getOfficeType().equalsIgnoreCase("SUB")){
//                statement.setString(i++, organizationMasterBean.getSubDivId());
//            }
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetErpLegalInvoiceStatusList :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
