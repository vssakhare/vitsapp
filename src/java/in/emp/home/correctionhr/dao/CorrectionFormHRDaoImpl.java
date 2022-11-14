/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionhr.dao;

import in.emp.dao.BaseDao;
import in.emp.home.correctionform.bean.CorrectionFormBean;
import in.emp.home.correctionform.dao.helper.CorrectionFormTxnHelper;
import in.emp.home.correctionform.dao.helper.GetCorrectionFormListQueryHelper;
import in.emp.home.correctionform.dao.helper.GetCorrectionFormQueryHelper;
import in.emp.home.correctionhr.dao.helper.CorrectionFormHRProcHelper;
import in.emp.home.correctionhr.dao.helper.CorrectionFormHRTxnHelper;
import in.emp.home.correctionhr.dao.helper.GetCorrectionFormHRListQueryHelper;
import in.emp.home.correctionhr.dao.helper.GetCorrectionFormHRQueryHelper;
import in.emp.util.ApplicationUtils;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class CorrectionFormHRDaoImpl extends BaseDao implements CorrectionFormHRDao {

    private static Logger logger = Logger.getLogger(CorrectionFormHRDaoImpl.class);

    public LinkedList<CorrectionFormBean> getCorrectionFormHRList(CorrectionFormBean correctionFormBeanObj) throws Exception {
        LinkedList<CorrectionFormBean> applList = new LinkedList<CorrectionFormBean>();
        try {
            logger.log(Level.INFO, " CorrectionFormHRDaoImpl :: getCorrectionFormHRList() :: method called");

            applList = (LinkedList<CorrectionFormBean>) getObjectList(new GetCorrectionFormHRListQueryHelper(correctionFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormHRDaoImpl :: getCorrectionFormHRList() :: Exception :: " + ex);
        }
        return applList;
    }

    public CorrectionFormBean getCorrectionFormHR(CorrectionFormBean correctionFormBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, " CorrectionFormHRDaoImpl :: getCorrectionFormHR() :: method called");

            correctionFormBeanObj = (CorrectionFormBean) getDataObject(new GetCorrectionFormHRQueryHelper(correctionFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormHRDaoImpl :: getCorrectionFormHR() :: Exception :: " + ex);
        }
        return correctionFormBeanObj;
    }

    public CorrectionFormBean postCorrectionFormHR(CorrectionFormBean correctionFormBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, " CorrectionFormHRDaoImpl :: postCorrectionFormHR() :: method called");

            if (correctionFormBeanObj.getApplicationStatus().equals("Approved")) {
                execProcedure(new CorrectionFormHRProcHelper(correctionFormBeanObj));
            }
            updateObject(new CorrectionFormHRTxnHelper(correctionFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormHRDaoImpl :: postCorrectionFormHR() :: Exception :: " + ex);
        }
        return correctionFormBeanObj;
    }
}