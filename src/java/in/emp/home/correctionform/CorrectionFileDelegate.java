/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform;

import in.emp.home.correctionform.bean.CorrectionFileBean;
import java.util.LinkedList;

/**
 *
 * @author Prajakta
 */
public interface CorrectionFileDelegate {

    public LinkedList<CorrectionFileBean> getCorrectionFileList(CorrectionFileBean correctionFileBeanObj) throws Exception;

    public CorrectionFileBean getCorrectionFile(CorrectionFileBean correctionFileBeanObj) throws Exception;

    public CorrectionFileBean CorrectionFileTxnHelper(CorrectionFileBean correctionFileBeanObj) throws Exception;
    
    public CorrectionFileBean CorrectionFileDelHelper(CorrectionFileBean correctionFileBeanObj) throws Exception;
}
