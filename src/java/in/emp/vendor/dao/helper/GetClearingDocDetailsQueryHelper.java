/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper;

/**
 *
 * @author Pooja Jadhav
 */




import in.emp.vendor.dao.helper.formHelper.GetVendorInputFormQueryHelper;
import in.emp.dao.QueryHelper;

import in.emp.vendor.bean.VendorPrezData;

import in.emp.vendor.bean.ClearingDocDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger; 

public class GetClearingDocDetailsQueryHelper implements QueryHelper { 

    private static Logger logger = Logger.getLogger(GetVendorInputFormQueryHelper.class); 
    private ClearingDocDetails clearingDocDetailsObj;
    private VendorPrezData vendorPrezDataObj;

    public GetClearingDocDetailsQueryHelper(ClearingDocDetails clearingDocDetailsObj) {
        this.clearingDocDetailsObj = clearingDocDetailsObj;
    }

    public GetClearingDocDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
       
    }

    public Object getDataObject(ResultSet results) throws Exception {
        ClearingDocDetails clearingDocDetailsObj = new ClearingDocDetails();
       
        try {

           clearingDocDetailsObj.setINVOICE_NUMBER(results.getString("INVOICE_NUMBER"));
           clearingDocDetailsObj.setCL_DOC_DT(results.getDate("CL_DOC_DT"));
           clearingDocDetailsObj.setCL_DOC_NO(results.getString("CL_DOC_NO"));
           clearingDocDetailsObj.setINVOICE_AMOUNT(results.getString("INVOICE_AMOUNT"));
             clearingDocDetailsObj.setINVOICE_DATE(results.getDate("INVOICE_DATE"));
           clearingDocDetailsObj.setPO_NUMBER(results.getString("PO_NUMBER"));
         

            

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetClearingDocDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return clearingDocDetailsObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {

            sql.append(" SELECT DISTINCT INVOICE_NUMBER,INVOICE_DATE,INVOICE_AMOUNT,PO_NUMBER,CL_DOC_NO,CL_DOC_DT  ");           
            sql.append("  FROM XXMIS_ERP_VENDOR_VERIFIED_LIST  ");
            sql.append(" WHERE CL_DOC_NO = ? ");

            

            logger.log(Level.INFO, "GetClearingDocDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, clearingDocDetailsObj.getCL_DOC_NO());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetClearingDocDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

    
}