/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionhr.manager;

import in.emp.home.correctionhr.CorrectionFormHRDelegate;
import in.emp.home.correctionform.bean.CorrectionFormBean;
import in.emp.home.correctionhr.dao.CorrectionFormHRDao;
import in.emp.home.correctionhr.dao.CorrectionFormHRDaoImpl;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class CorrectionFormHRManager implements CorrectionFormHRDelegate {

    private static String CLASS_NAME = CorrectionFormHRManager.class.getName();
    private static Logger logger = Logger.getLogger(CorrectionFormHRManager.class);

    public LinkedList<CorrectionFormBean> getCorrectionFormHRList(CorrectionFormBean correctionFormBeanObj) throws Exception {
        CorrectionFormHRDao correctionFormHRDao = new CorrectionFormHRDaoImpl();
        LinkedList<CorrectionFormBean> applList = new LinkedList<CorrectionFormBean>();
        try {
            logger.log(Level.INFO, " CorrectionFormManager :: getCorrectionFormHRList() :: method called");

            applList = correctionFormHRDao.getCorrectionFormHRList(correctionFormBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormManager :: getCorrectionFormHRList() :: Exception :: " + ex);
        }
        return applList;
    }

    public CorrectionFormBean getCorrectionFormHR(CorrectionFormBean correctionFormBeanObj) throws Exception {
        CorrectionFormHRDao correctionFormHRDao = new CorrectionFormHRDaoImpl();
        try {
            logger.log(Level.INFO, " CorrectionFormManager :: getCorrectionFormHR() :: method called");

            correctionFormBeanObj = correctionFormHRDao.getCorrectionFormHR(correctionFormBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormManager :: getCorrectionFormHR() :: Exception :: " + ex);
        }
        return correctionFormBeanObj;
    }

    public CorrectionFormBean postCorrectionFormHR(CorrectionFormBean correctionFormBeanObj) throws Exception {
        CorrectionFormHRDao correctionFormHRDao = new CorrectionFormHRDaoImpl();
        try {
            logger.log(Level.INFO, " CorrectionFormManager :: postCorrectionFormHR() :: method called");

            correctionFormBeanObj = correctionFormHRDao.postCorrectionFormHR(correctionFormBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFormManager :: postCorrectionFormHR() :: Exception :: " + ex);
        }
        return correctionFormBeanObj;
    }
}