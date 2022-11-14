/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform.manager;

import in.emp.home.correctionform.CorrectionFileDelegate;
import in.emp.home.correctionform.bean.CorrectionFileBean;
import in.emp.home.correctionform.dao.CorrectionFileDao;
import in.emp.home.correctionform.dao.CorrectionFileDaoImpl;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class CorrectionFileManager implements CorrectionFileDelegate {

    private static String CLASS_NAME = CorrectionFileManager.class.getName();
    private static Logger logger = Logger.getLogger(CorrectionFileManager.class);

    public LinkedList<CorrectionFileBean> getCorrectionFileList(CorrectionFileBean correctionFileBeanObj) throws Exception {
        CorrectionFileDao correctionFileDaoObj = new CorrectionFileDaoImpl();
        LinkedList<CorrectionFileBean> fileList = new LinkedList<CorrectionFileBean>();
        try {
            logger.log(Level.INFO, " CorrectionFileManager :: getCorrectionFileList() :: method called");

            fileList = correctionFileDaoObj.getCorrectionFileList(correctionFileBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFileManager :: getCorrectionFileList() :: Exception :: " + ex);
        }
        return fileList;
    }

    public CorrectionFileBean getCorrectionFile(CorrectionFileBean correctionFileBeanObj) throws Exception {
        CorrectionFileDao correctionFileDaoObj = new CorrectionFileDaoImpl();
        try {
            logger.log(Level.INFO, " CorrectionFileManager :: getCorrectionFile() :: method called");

            correctionFileBeanObj = correctionFileDaoObj.getCorrectionFile(correctionFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFileManager :: getCorrectionFile() :: Exception :: " + ex);
        }
        return correctionFileBeanObj;
    }

    public CorrectionFileBean CorrectionFileDelHelper(CorrectionFileBean correctionFileBeanObj) throws Exception {
        CorrectionFileDao correctionFileDaoObj = new CorrectionFileDaoImpl();
        try {
            logger.log(Level.INFO, " CorrectionFileManager :: CorrectionFileDelHelper() :: method called");

            correctionFileBeanObj = correctionFileDaoObj.CorrectionFileDelHelper(correctionFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFileManager :: CorrectionFileDelHelper() :: Exception :: " + ex);
        }
        return correctionFileBeanObj;
    }

    public CorrectionFileBean CorrectionFileTxnHelper(CorrectionFileBean correctionFileBeanObj) throws Exception {
        CorrectionFileDao correctionFileDaoObj = new CorrectionFileDaoImpl();
        try {
            logger.log(Level.INFO, " CorrectionFileManager :: CorrectionFileTxnHelper() :: method called");

            correctionFileBeanObj = correctionFileDaoObj.CorrectionFileTxnHelper(correctionFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " CorrectionFileManager :: CorrectionFileTxnHelper() :: Exception :: " + ex);
        }
        return correctionFileBeanObj;
    }
}
