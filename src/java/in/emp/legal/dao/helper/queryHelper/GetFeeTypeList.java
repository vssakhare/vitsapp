/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.QueryHelper;
import in.emp.legal.bean.FeeTypeBean;
import in.emp.util.ApplicationUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Rikma Rai
 */
public class GetFeeTypeList implements QueryHelper {
private static Logger logger = Logger.getLogger(GetErpLegalInvoiceStatusList.class);
    private FeeTypeBean feeTypeBean = null;

    public GetFeeTypeList(FeeTypeBean feeTypeBean) {
        this.feeTypeBean = feeTypeBean;
    }
    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        FeeTypeBean feeTypeBean = new FeeTypeBean();
        try {
        feeTypeBean.setFeeIndex(results.getInt("ZZFEE_INDEX"));
        feeTypeBean.setCaseType(results.getInt("ZZCASE_TYPE"));
        feeTypeBean.setFeeType(results.getString("ZZFEE_TYPE"));
        feeTypeBean.setSacCode(results.getString("ZZSAC_CODE"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetFeeTypeList :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return feeTypeBean;
    }

    @Override
    public ResultSet getQueryResults(Connection connection) throws Exception {
       PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i = 1;
        try {
            logger.log(Level.INFO, "GetFeeTypeList ::: getQueryResults() :: method called ::");
//            sql.append(" SELECT * FROM ERP_LEGAL_INVOICE_STATUS ");
            sql.append("  SELECT * FROM ZHRT_ADV_FEE_TYP WHERE ZZCASE_TYPE=? ORDER BY ZZFEE_INDEX ");
            
            statement = connection.prepareStatement(sql.toString());
            if ( feeTypeBean.getCaseType()!= null) {
                statement.setInt(i++, feeTypeBean.getCaseType());
            } 
            System.out.println("sql::" + sql);
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetFeeTypeList :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
    
}
