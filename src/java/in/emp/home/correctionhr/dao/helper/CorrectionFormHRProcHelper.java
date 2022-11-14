/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionhr.dao.helper;

import in.emp.dao.ProcHelper;
import in.emp.home.correctionform.bean.CorrectionFormBean;
import in.emp.home.correctionform.bean.CorrectionFormPrezData;
import java.sql.CallableStatement;
import java.sql.Connection;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class CorrectionFormHRProcHelper implements ProcHelper {

    private static Logger logger = Logger.getLogger(CorrectionFormHRProcHelper.class);
    private CorrectionFormBean correctionFormBeanObj = new CorrectionFormBean();
    private CorrectionFormPrezData correctionFormPrezDataObj = new CorrectionFormPrezData();

    public CorrectionFormHRProcHelper(CorrectionFormBean correctionFormBeanObj) {
        this.correctionFormBeanObj = correctionFormBeanObj;
    }

    public CorrectionFormHRProcHelper(CorrectionFormPrezData correctionFormPrezDataObj) {
        this.correctionFormPrezDataObj = correctionFormPrezDataObj;
        this.correctionFormBeanObj = this.correctionFormPrezDataObj.getCorrectionFormBean();
    }

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    public Object callProcedure(Connection conn) throws Exception {
        int count = 0;
        CallableStatement callableStatement = null;
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "CorrectionFormHRProcHelper ::: callProcedure() :: method called ::");

            logger.log(Level.INFO, "CorrectionFormHRProcHelper :: callProcedure() :: SQL :: " + sql.toString());

        } catch (Exception ex) {
            logger.log(Level.ERROR, "CorrectionFormHRProcHelper ::: callProcedure() :: Exception :: " + ex);
            throw ex;
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
        }
        return correctionFormBeanObj;
    }
}