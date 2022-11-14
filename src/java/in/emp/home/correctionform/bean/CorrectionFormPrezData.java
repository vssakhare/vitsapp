/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform.bean;

import in.emp.home.personalInfo.bean.PersonalinfoBean;
import java.util.LinkedList;

/**
 *
 * @author Prajakta
 */
public class CorrectionFormPrezData implements java.io.Serializable {

    CorrectionFormBean correctionFormBean = new CorrectionFormBean();
    LinkedList<CorrectionFormBean> applicationList = new LinkedList<CorrectionFormBean>();
    PersonalinfoBean pInfoBean = new PersonalinfoBean();
    LinkedList<CorrectionFileBean> fileList = new LinkedList<CorrectionFileBean>();

    public CorrectionFormBean getCorrectionFormBean() {
        return correctionFormBean;
    }

    public void setCorrectionFormBean(CorrectionFormBean correctionFormBean) {
        this.correctionFormBean = correctionFormBean;
    }

    public LinkedList<CorrectionFormBean> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(LinkedList<CorrectionFormBean> applicationList) {
        this.applicationList = applicationList;
    }

    public PersonalinfoBean getpInfoBean() {
        return pInfoBean;
    }

    public void setpInfoBean(PersonalinfoBean pInfoBean) {
        this.pInfoBean = pInfoBean;
    }

    public LinkedList<CorrectionFileBean> getFileList() {
        return fileList;
    }

    public void setFileList(LinkedList<CorrectionFileBean> fileList) {
        this.fileList = fileList;
    }
}