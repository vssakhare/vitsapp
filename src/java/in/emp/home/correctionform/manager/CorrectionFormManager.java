/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform.manager;

import in.emp.home.correctionform.CorrectionFormDelegate;
import in.emp.home.correctionform.bean.CorrectionFormBean;
import in.emp.home.correctionform.dao.CorrectionFormDao;
import in.emp.home.correctionform.dao.CorrectionFormDaoImpl;
import in.emp.util.ApplicationUtils;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class CorrectionFormManager implements CorrectionFormDelegate {

    private static String CLASS_NAME = CorrectionFormManager.class.getName();
    private static Logger logger = Logger.getLogger(CorrectionFormManager.class);

    public LinkedList<CorrectionFormBean> getCorrectionFormList(CorrectionFormBean correctionFormBeanObj) throws Exception {
        CorrectionFormDao correctionFormDao = new CorrectionFormDaoImpl();
        LinkedList<CorrectionFormBean> applList = new LinkedList<CorrectionFormBean>();
        try {
            logger.log(Level.INFO, " CorrectionFormManager :: getCorrectionFormList() :: method called");

            applList = correctionFormDao.getCorrectionFormList(correctionFormBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormManager :: getCorrectionFormList() :: Exception :: " + ex);
        }
        return applList;
    }

    public CorrectionFormBean postCorrectionFormList(CorrectionFormBean correctionFormBeanObj) throws Exception {
        CorrectionFormDao correctionFormDao = new CorrectionFormDaoImpl();
        try {
            logger.log(Level.INFO, " CorrectionFormManager :: postCorrectionForm() :: method called");

            correctionFormBeanObj = correctionFormDao.postCorrectionForm(correctionFormBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormManager :: postCorrectionForm() :: Exception :: " + ex);
        }
        return correctionFormBeanObj;
    }

    public CorrectionFormBean getCorrectionForm(CorrectionFormBean correctionFormBeanObj) throws Exception {
        CorrectionFormDao correctionFormDao = new CorrectionFormDaoImpl();
        try {
            logger.log(Level.INFO, " CorrectionFormManager :: getCorrectionForm() :: method called");
            if (!ApplicationUtils.isBlank(correctionFormBeanObj.getApplicationId())) {
                correctionFormBeanObj = correctionFormDao.getCorrectionForm(correctionFormBeanObj);
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormManager :: getCorrectionForm() :: Exception :: " + ex);
        }
        return correctionFormBeanObj;
    }

    public CorrectionFormBean postCorrectionForm(CorrectionFormBean correctionFormBeanObj) throws Exception {
        CorrectionFormDao correctionFormDao = new CorrectionFormDaoImpl();
        try {
            logger.log(Level.INFO, " CorrectionFormManager :: postCorrectionForm() :: method called");

            correctionFormBeanObj = correctionFormDao.postCorrectionForm(correctionFormBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormManager :: postCorrectionForm() :: Exception :: " + ex);
        }
        return correctionFormBeanObj;
    }
}
