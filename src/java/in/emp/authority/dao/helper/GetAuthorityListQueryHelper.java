/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.authority.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.authority.bean.AuthorityBean;
import in.emp.authority.bean.AuthorityPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class GetAuthorityListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetAuthorityListQueryHelper.class);
    private AuthorityBean authorityBean;
    private AuthorityPrezData authorityPrezDataObj;

    public GetAuthorityListQueryHelper(AuthorityBean authorityBean) {
        this.authorityBean = authorityBean;
    }

    public GetAuthorityListQueryHelper(AuthorityPrezData authorityPrezDataObj) {
        this.authorityPrezDataObj = authorityPrezDataObj;
        this.authorityBean = this.authorityPrezDataObj.getAuthorityBean();
    }

    public Object getDataObject(ResultSet results) throws Exception {
        AuthorityBean authorityBeanObj = new AuthorityBean();
         
        try {
            logger.log(Level.INFO, "GetAuthorityListQueryHelper ::: getDataObject() :: method called ::");

            authorityBean.setZone(results.getString("ZONE")); 
            authorityBean.setCircle(results.getString("CIRCLE")); 
            authorityBean.setDivision(results.getString("DIVISION"));             
            authorityBean.setPONumber(results.getString("PO_NUMBER"));
            authorityBean.setPODesc(results.getString("PO_DESC"));
            authorityBean.setPOType(results.getString("PO_TYPE"));
            authorityBean.setPOCreationDate(results.getDate("PO_CREATION_DATE"));

            authorityBean.setVendorInvoiceNumber(results.getString("VENDOR_INV_NO"));
            //authorityBean.setInvoiceAmount(results.getString("INVOICE_AMOUNT"));
            authorityBean.setValidityStart(results.getDate("VALIDITY_START"));                    
            authorityBean.setValidityEnd(results.getDate("VALIDITY_END")); 
            authorityBean.setSaveFlag(results.getString("STATUS"));
            authorityBean.setInvoiceDate(results.getDate("INVOICE_DATE")); 
           
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetAuthorityListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return authorityBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i = 1;
        try {
            logger.log(Level.INFO, "GetAuthorityListQueryHelper ::: getQueryResults() :: method called ::");

           
           sql.append(" SELECT EAL.ZONE, EAL.CIRCLE, EAL.DIVISION, EAL.PO_NUMBER, EAL.PO_DESC, ");
           sql.append(" EAL.PO_TYPE, TO_DATE(TO_CHAR(EAL.PO_CREATION_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') PO_CREATION_DATE, ");         
           sql.append(" EAL.VENDOR_INV_NO, EAL.VALIDITY_START, EAL.VALIDITY_END, EAL.STATUS, EAL.INVOICE_DATE ");
           sql.append(" FROM XXMIS_ERP_AUTH_LIST EAL ");
           sql.append(" WHERE EAL.OFFICE_CODE IN (select h.organization_id from hr_all_organization_units h, ");
           sql.append(" hr_all_organization_units h1, hri_org_hrchy_summary hr ");
           sql.append(" where h.organization_id=hr.sub_organization_id and hr.org_structure_version_id='61' ");
           sql.append(" and hr.organization_id = ? and h1.organization_id=hr.organization_id  ");
           sql.append(" and h.type in ('HO','ZON','CIR','DIV') ) ");
          
            
            logger.log(Level.INFO, "GetAuthorityListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
          statement.setString(1, authorityBean.getLocationId());
            
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetAuthorityListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
