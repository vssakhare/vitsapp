/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform.dao;

import in.emp.dao.BaseDao;
import in.emp.home.correctionform.bean.CorrectionFileBean;
import in.emp.home.correctionform.dao.helper.CorrectionFileTxnHelper;
import in.emp.home.correctionform.dao.helper.GetCorrectionFileListQueryHelper;
import in.emp.home.correctionform.dao.helper.GetCorrectionFileQueryHelper;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class CorrectionFileDaoImpl extends BaseDao implements CorrectionFileDao {
private static Logger logger = Logger.getLogger(CorrectionFileDaoImpl.class);

    public LinkedList<CorrectionFileBean> getCorrectionFileList(CorrectionFileBean correctionFileBeanObj) throws Exception {
        LinkedList<CorrectionFileBean> fileList = new LinkedList<CorrectionFileBean>();
        try {
            logger.log(Level.INFO, " CorrectionFileDaoImpl :: getCorrectionFileList() :: method called");

            fileList = (LinkedList<CorrectionFileBean>) getObjectList(new GetCorrectionFileListQueryHelper(correctionFileBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFileDaoImpl :: getCorrectionFileList() :: Exception :: " + ex);
        }
        return fileList;
    }

    public CorrectionFileBean getCorrectionFile(CorrectionFileBean correctionFileBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, " CorrectionFileDaoImpl :: getCorrectionFile() :: method called");

            correctionFileBeanObj = (CorrectionFileBean) getDataObject(new GetCorrectionFileQueryHelper(correctionFileBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFileDaoImpl :: getCorrectionFile() :: Exception :: " + ex);
        }
        return correctionFileBeanObj;
    }

    public CorrectionFileBean CorrectionFileTxnHelper(CorrectionFileBean correctionFileBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, " CorrectionFileDaoImpl :: CorrectionFileTxnHelper() :: method called");

            correctionFileBeanObj = (CorrectionFileBean) createObject(new CorrectionFileTxnHelper(correctionFileBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFileDaoImpl :: CorrectionFileTxnHelper() :: Exception :: " + ex);
        }
        return correctionFileBeanObj;
    }

    public CorrectionFileBean CorrectionFileDelHelper(CorrectionFileBean correctionFileBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, " CorrectionFileDaoImpl :: CorrectionFileDelHelper() :: method called");

            removeObject(new CorrectionFileTxnHelper(correctionFileBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFileDaoImpl :: CorrectionFileDelHelper() :: Exception :: " + ex);
        }
        return correctionFileBeanObj;
    }
}
