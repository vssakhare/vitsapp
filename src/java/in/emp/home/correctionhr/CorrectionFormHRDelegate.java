/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionhr;

import in.emp.home.correctionform.bean.CorrectionFormBean;
import java.util.LinkedList;

/**
 *
 * @author Prajakta
 */
public interface CorrectionFormHRDelegate {

    public LinkedList<CorrectionFormBean> getCorrectionFormHRList(CorrectionFormBean correctionFormBeanObj) throws Exception;

    public CorrectionFormBean getCorrectionFormHR(CorrectionFormBean correctionFormBeanObj) throws Exception;

    public CorrectionFormBean postCorrectionFormHR(CorrectionFormBean correctionFormBeanObj) throws Exception;
}
