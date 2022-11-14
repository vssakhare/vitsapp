/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform;

import in.emp.home.correctionform.bean.CorrectionFormBean;
import java.util.LinkedList;

/**
 *
 * @author Prajakta
 */
public interface CorrectionFormDelegate {

    public LinkedList<CorrectionFormBean> getCorrectionFormList(CorrectionFormBean correctionFormBeanObj) throws Exception;

    public CorrectionFormBean postCorrectionFormList(CorrectionFormBean correctionFormBeanObj) throws Exception;

    public CorrectionFormBean getCorrectionForm(CorrectionFormBean correctionFormBeanObj) throws Exception;

    public CorrectionFormBean postCorrectionForm(CorrectionFormBean correctionFormBeanObj) throws Exception;
}