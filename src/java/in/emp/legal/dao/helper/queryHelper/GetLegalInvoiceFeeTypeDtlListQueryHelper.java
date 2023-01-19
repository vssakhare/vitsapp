/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;

/**
 *
 * @author Vaishali
 */

import in.emp.dao.QueryHelper;
import in.emp.legal.bean.FeeTypeDtlsBean;
import in.emp.vendor.bean.VendorApplFileBean;
import in.emp.vendor.bean.VendorApplFilePrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class GetLegalInvoiceFeeTypeDtlListQueryHelper implements QueryHelper {
    
  private static Logger logger = Logger.getLogger(GetLegalInvoiceFeeTypeDtlListQueryHelper.class);
    private FeeTypeDtlsBean vendorFeeTypeDtlsBeanObj;  
    
public GetLegalInvoiceFeeTypeDtlListQueryHelper(FeeTypeDtlsBean vendorFeeTypeDtlsBeanObj) {
        this.vendorFeeTypeDtlsBeanObj = vendorFeeTypeDtlsBeanObj;
    }

   
    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        FeeTypeDtlsBean vendorFeeTypeDtlsBean = new FeeTypeDtlsBean();
        try {
            
            vendorFeeTypeDtlsBean.setFeeTypeDtlsId(results.getInt("fee_type_dtls_id"));
            vendorFeeTypeDtlsBean.setFeeType(results.getString("fee_type"));
            vendorFeeTypeDtlsBean.setAmount(results.getInt("amount"));
            vendorFeeTypeDtlsBean.setApplId(results.getInt("APPL_ID"));
              vendorFeeTypeDtlsBean.setRemark(results.getString("remark"));
                  
            
            
           
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLegalInvoiceFeeTypeDtlListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorFeeTypeDtlsBean;
    }

    @Override
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i=1;
        try {

            sql.append(" SELECT EIF.fee_type_dtls_id, EIF.fee_type, EIF.amount, EIF.APPL_ID , EIF.remark  ");
            sql.append(" FROM XXMIS_ERP_LEGAL_INVOICE_FEE_TYPE_DTLS EIF ");
            sql.append(" WHERE EIF.APPL_ID =  ? ");
           // if(vendorFeeTypeDtlsBeanObj.getWhereClause()!=null && vendorFeeTypeDtlsBeanObj.getWhereClause().equals("id")){
           //     sql.append(" AND EIF.ID =  ? ");
          //  }
            logger.log(Level.INFO, "GetLegalInvoiceFeeTypeDtlListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setInt(i++, vendorFeeTypeDtlsBeanObj.getApplId());
            //if(vendorapplFileBeanObj.getWhereClause()!=null && vendorapplFileBeanObj.getWhereClause().equals("id")){
            //     statement.setString(i++, vendorapplFileBeanObj.getId());
            //}
           //
            
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLegalInvoiceFeeTypeDtlListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }    
    
    
}
