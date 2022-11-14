/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.home.personalInfo.bean;

/**
 *
 * @author Prajakta
 */
public class PersonalinfocvcBean implements java.io.Serializable{

    private String CVCPresent;
    private String CVCCommName;
    private String CVCStatus;
    private String CVCNo;
    private String CVCDate;

    public String getCVCCommName() {
        return CVCCommName;
    }

    public void setCVCCommName(String CVCCommName) {
        this.CVCCommName = CVCCommName;
    }

    public String getCVCDate() {
        return CVCDate;
    }

    public void setCVCDate(String CVCDate) {
        this.CVCDate = CVCDate;
    }

    public String getCVCNo() {
        return CVCNo;
    }

    public void setCVCNo(String CVCNo) {
        this.CVCNo = CVCNo;
    }

    public String getCVCPresent() {
        return CVCPresent;
    }

    public void setCVCPresent(String CVCPresent) {
        this.CVCPresent = CVCPresent;
    }

    public String getCVCStatus() {
        return CVCStatus;
    }

    public void setCVCStatus(String CVCStatus) {
        this.CVCStatus = CVCStatus;
    }

}
