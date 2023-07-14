/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;

import in.emp.dao.QueryHelper;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.util.TaxAmountDisplayFromSap;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class GetZhrtLegalFeeReportQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetZhrtLegalFeeReportQueryHelper.class);
    private LegalInvoiceInputBean lvendorInputBeanObj;

    public GetZhrtLegalFeeReportQueryHelper() {

    }

    public GetZhrtLegalFeeReportQueryHelper(LegalInvoiceInputBean lvendorInputBeanObj) {
        this.lvendorInputBeanObj = lvendorInputBeanObj;
    }

    public Object getDataObject(ResultSet result) throws Exception {
        LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
        try {
            logger.log(Level.INFO, "GetZhrtLegalFeeReportQueryHelper ::: getDataObject() :: method called ::");

            legalInvoiceInputBean.setStartPostDocNo(result.getString("start_post_doc_no"));
            legalInvoiceInputBean.setStartPayDoneErpDoc(result.getString("start_pay_done_erp_doc"));
            legalInvoiceInputBean.setFiscalYear(result.getString("ZZPOST_FISCAL"));
            legalInvoiceInputBean.setParkPostDocNo(result.getString("ZZPARK_POST_DOC_NO"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetZhrtLegalFeeReportQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }

        return legalInvoiceInputBean;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        CallableStatement proc_stmt = null;
        try {
                         sql.append("SELECT distinct substr(zf.ZZPARK_POST_DOC_NO,1,2) as start_post_doc_no,");

            sql.append("substr(zf.ZZPAY_DONE_ERP_DOC,1,2) as start_pay_done_erp_doc,");
            
          //  sql.append("ZZPOST_FISCAL,ZZPARK_POST_DOC_NO FROM ZHRT_LEGAL_FEE zf where ZZPARK_POST_DOC_NO in('1600039018')");
            
          sql.append("ZZPOST_FISCAL,ZZPARK_POST_DOC_NO FROM ZHRT_LEGAL_FEE zf where ZZPARK_POST_DOC_NO||ZZPOST_FISCAL not in (");

           sql.append("select LIABILITY_DOC_NO||FISCAL_YEAR from ZHRT_LEGAL_FEE_TAX_DETAILS)");
            
            sql.append("AND substr(zf.ZZPARK_POST_DOC_NO,1,2) is not null AND substr(zf.ZZPARK_POST_DOC_NO,1,2) ='16' AND substr(zf.ZZPAY_DONE_ERP_DOC,1,2) is not null  AND substr(zf.ZZPAY_DONE_ERP_DOC,1,2)='17' AND ZZPOST_FISCAL !='0'");
            
logger.log(Level.INFO, "GetZhrtLegalFeeReportQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());

            rs = statement.executeQuery();
           

        } catch (Exception ex) {
            connection.rollback();
            logger.log(Level.ERROR, "GetZhrtLegalFeeReportQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
