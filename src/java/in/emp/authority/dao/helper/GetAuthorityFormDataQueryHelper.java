/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.authority.dao.helper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.authority.bean.AuthorityBean;
import in.emp.authority.bean.AuthorityPrezData;
import in.emp.util.ApplicationUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class GetAuthorityFormDataQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetAuthorityFormDataQueryHelper.class);
    private AuthorityBean authorityBeanObj;
    private AuthorityPrezData authorityPrezDataObj;

    public GetAuthorityFormDataQueryHelper(AuthorityBean authorityBeanObj) {
        this.authorityBeanObj = authorityBeanObj;
    }

    public GetAuthorityFormDataQueryHelper(AuthorityPrezData authorityPrezDataObj) {
        this.authorityPrezDataObj = authorityPrezDataObj;
        this.authorityBeanObj = this.authorityPrezDataObj.getAuthorityBean();
    }

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    public Object getDataObject(ResultSet results) throws Exception {
        AuthorityBean authoritybean = new AuthorityBean();
       
        try {

           
            authorityBeanObj.setApplId(results.getString("APPL_ID")); 
            authorityBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER"));
            authorityBeanObj.setVendorName(results.getString("VENDOR_NAME"));
            authorityBeanObj.setPONumber(results.getString("PO_NUMBER"));
            authorityBeanObj.setInvoiceNumber(results.getString("INVOICE_NUMBER"));
            authorityBeanObj.setInvoiceAmount(results.getString("INVOICE_AMOUNT"));
            authorityBeanObj.setInvoiceDate(results.getDate("INVOICE_DATE"));                    
            authorityBeanObj.setSaveFlag(results.getString("STATUS"));
            authorityBeanObj.setInwardNumber(results.getString("INWARD_NUMBER"));
            authorityBeanObj.setInwardDate(results.getDate("INWARD_DATE")); 
            
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetAuthorityFormDataQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return authorityBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

            sql.append(" SELECT EVDTL.APPL_ID, EVDTL.SAVE_FLAG,  EVDTL.VENDOR_NUMBER,  EVDTL.VENDOR_NAME, EVDTL.PO_NUMBER, ");
            sql.append(" EVDTL.INVOICE_NUMBER,  EVDTL.INVOICE_AMOUNT, TO_DATE(TO_CHAR (EVDTL.INVOICE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') INVOICE_DATE, ");
            sql.append(" (CASE WHEN (EVDTL.SAVE_FLAG = '05') THEN  'Deleted' ");
            sql.append(" WHEN (EVDTL.SAVE_FLAG = '10') THEN 'Saved' ");
            sql.append(" WHEN (EVDTL.SAVE_FLAG = '20') THEN 'Pending' ");
            sql.append(" ELSE 'Closed'  END) STATUS, ");
            sql.append(" EVDTL.INWARD_NUMBER, TO_DATE(TO_CHAR (EVDTL.INWARD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') INWARD_DATE  ");
            sql.append(" FROM XXMIS_ERP_VENDOR_DTL EVDTL ");
            sql.append(" WHERE EVDTL.APPL_ID = ? ");           
            sql.append(" AND NVL(EVDTL.SAVE_FLAG, 10) NOT IN ( 05 )");

            logger.log(Level.INFO, "GetAuthorityFormDataQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());           
            statement.setString(1, authorityBeanObj.getApplId());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetAuthorityFormDataQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

    
}