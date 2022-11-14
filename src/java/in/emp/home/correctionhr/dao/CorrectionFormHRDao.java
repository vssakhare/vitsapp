/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionhr.dao;

import in.emp.home.correctionform.bean.CorrectionFormBean;
import java.util.LinkedList;

/**
 *
 * @author Prajakta
 */
public interface CorrectionFormHRDao {

    public LinkedList<CorrectionFormBean> getCorrectionFormHRList(CorrectionFormBean correctionFormBeanObj) throws Exception;

    public CorrectionFormBean getCorrectionFormHR(CorrectionFormBean correctionFormBeanObj) throws Exception;

    public CorrectionFormBean postCorrectionFormHR(CorrectionFormBean correctionFormBeanObj) throws Exception;
}
