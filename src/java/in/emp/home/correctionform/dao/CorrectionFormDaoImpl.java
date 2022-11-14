/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform.dao;

import in.emp.dao.BaseDao;
import in.emp.home.correctionform.bean.CorrectionFormBean;
import in.emp.home.correctionform.dao.helper.CorrectionFormTxnHelper;
import in.emp.home.correctionform.dao.helper.GetCorrectionFormListQueryHelper;
import in.emp.home.correctionform.dao.helper.GetCorrectionFormQueryHelper;
import in.emp.util.ApplicationUtils;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class CorrectionFormDaoImpl extends BaseDao implements CorrectionFormDao {

    private static Logger logger = Logger.getLogger(CorrectionFormDaoImpl.class);

    public LinkedList<CorrectionFormBean> getCorrectionFormList(CorrectionFormBean correctionFormBeanObj) throws Exception {
        LinkedList<CorrectionFormBean> applList = new LinkedList<CorrectionFormBean>();
        try {
            logger.log(Level.INFO, " CorrectionFormDaoImpl :: getCorrectionFormList() :: method called");

            applList = (LinkedList<CorrectionFormBean>) getObjectList(new GetCorrectionFormListQueryHelper(correctionFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormDaoImpl :: getCorrectionFormList() :: Exception :: " + ex);
        }
        return applList;
    }

    public CorrectionFormBean postCorrectionFormList(CorrectionFormBean correctionFormBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, " CorrectionFormDaoImpl :: postCorrectionFormList() :: method called");

            removeObject(new CorrectionFormTxnHelper(correctionFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormDaoImpl :: postCorrectionFormList() :: Exception :: " + ex);
        }
        return correctionFormBeanObj;
    }

    public CorrectionFormBean getCorrectionForm(CorrectionFormBean correctionFormBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, " CorrectionFormDaoImpl :: getCorrectionForm() :: method called");

            correctionFormBeanObj = (CorrectionFormBean) getDataObject(new GetCorrectionFormQueryHelper(correctionFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormDaoImpl :: getCorrectionForm() :: Exception :: " + ex);
        }
        return correctionFormBeanObj;
    }

    public CorrectionFormBean postCorrectionForm(CorrectionFormBean correctionFormBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, " CorrectionFormDaoImpl :: postCorrectionForm() :: method called");
            if (ApplicationUtils.isBlank(correctionFormBeanObj.getApplicationId())) {
            createObject(new CorrectionFormTxnHelper(correctionFormBeanObj));
            } else {
            updateObject(new CorrectionFormTxnHelper(correctionFormBeanObj));
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormDaoImpl :: postCorrectionForm() :: Exception :: " + ex);
        }
        return correctionFormBeanObj;
    }
}
