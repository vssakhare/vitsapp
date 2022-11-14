/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.FtpFileReadHelper;

import in.emp.dao.QueryHelper;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.dao.helper.formHelper.GetVendorInputFormQueryHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Rikma Rai
 */
public class PutRetentionInvoiceStatusQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorInputFormQueryHelper.class);
    private VendorBean vendorBean;

    public PutRetentionInvoiceStatusQueryHelper(VendorBean vendorBean) {
        this.vendorBean = vendorBean;
    }

    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        VendorBean vendorBeanObj = new VendorBean();
        try {
            logger.log(Level.INFO, "putInvoiceStatusQueryhelper ::: getDataObject() :: method called ::");

            vendorBeanObj.setApplId(results.getString("APPL_ID"));
            vendorBeanObj.setSesNum(results.getString("SERIAL_NO"));
            vendorBeanObj.setClaimDate(results.getDate("CLAIM_DATE"));
            vendorBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER"));
            vendorBeanObj.setVendorName(results.getString("VENDOR_NAME"));
            vendorBeanObj.setProjectCode(results.getString("PROJECT_ID"));
            vendorBeanObj.setINV_TYP(results.getString("INVOICE_TYPE"));
            vendorBeanObj.setInvoiceNumber(results.getString("INV_NO"));
            vendorBeanObj.setInvoiceDate(results.getDate("INVOICE_DATE"));
            vendorBeanObj.setInvoiceAmount(results.getString("GROSS_VALUE"));
            vendorBeanObj.setInwardNumber(results.getString("INWARD_NUMBER"));
            vendorBeanObj.setInwardDate(results.getDate("INWARD_DATE"));
            vendorBeanObj.setMsedclInvoiceNumber(results.getString("INVOICE_NUMBER"));
            vendorBeanObj.setWRPST(results.getString("RETENTION_DOC"));
            vendorBeanObj.setWRET_AMT(results.getString("TOTAL_AMOUNT"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "putInvoiceStatusQueryhelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }

    @Override
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

            if (vendorBean.getSubAction().equalsIgnoreCase("displayDetails")) {
                sql.append(" select  APPL_ID ,'1' SERIAL_NO, trunc(CLAIM_DATE) CLAIM_DATE,VENDOR_NUMBER,VENDOR_NAME,PROJECT_ID,INVOICE_TYPE,  ");
                sql.append(" INV_NO,INVOICE_DATE,GROSS_VALUE,INWARD_NUMBER,SUM(TOTAL_AMOUNT) TOTAL_AMOUNT,trunc(INWARD_DATE) INWARD_DATE,'1' RETENTION_DOC,'1' INVOICE_NUMBER from  ");
                sql.append(" (SELECT  APPL_ID ,'1' SERIAL_NO, trunc(CLAIM_DATE) CLAIM_DATE,VENDOR_NUMBER,VENDOR_NAME,PROJECT_ID,INVOICE_TYPE,   ");
                sql.append(" INV_NO,INVOICE_DATE,GROSS_VALUE,INWARD_NUMBER,SUM(distinct AMOUNT) TOTAL_AMOUNT,trunc(INWARD_DATE) INWARD_DATE,'1' RETENTION_DOC,'1' INVOICE_NUMBER   ");
                sql.append(" from VENDOR_RETENTION_DETAILS   ");
                sql.append(" where appl_id='"+vendorBean.getApplId()+"'" ); 
                sql.append(" group by appl_id, trunc(CLAIM_DATE),VENDOR_NUMBER,VENDOR_NAME,PROJECT_ID,INVOICE_TYPE,INV_NO,INVOICE_DATE,GROSS_VALUE,INWARD_NUMBER,trunc(INWARD_DATE),fi_doc_no)  ");
                sql.append(" group by appl_id, trunc(CLAIM_DATE),VENDOR_NUMBER,VENDOR_NAME,PROJECT_ID,INVOICE_TYPE,INV_NO,INVOICE_DATE,GROSS_VALUE,INWARD_NUMBER,trunc(INWARD_DATE)  ");
            } else {
                sql.append(" SELECT * FROM VENDOR_RETENTION_DETAILS ");
                sql.append("  where  status IN ('Verified') ");
                sql.append("and  FI_DOC_NO IS NULL  ");
            }

            logger.log(Level.INFO, "PutRetentionInvoiceStatusQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());

            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "PutRetentionInvoiceStatusQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
