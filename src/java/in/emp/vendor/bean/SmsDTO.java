/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

import java.util.List;
import jakarta.servlet.http.HttpServletRequest  ;

/**
 *
 * @author Pooja Jadhav
 */
public class SmsDTO implements java.io.Serializable {

    private List<String> LstParams = null;
    String userName = null;
    String userPass = null;
    String smsId = null;
    String smsUrl = null;
    String smsTemplateId = null;

    String MobileNumber = null;
    String EmailId = null;
    HttpServletRequest request = null;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String EmailId) {
        this.EmailId = EmailId;
    }

    public List<String> getLstParams() {
        return LstParams;
    }

    public void setLstParams(List<String> lstParams) {
        this.LstParams = lstParams;
    }

    public void setUser(String userName) {
        this.userName = userName;
    }

    public void setPasswd(String userPass) {
        this.userPass = userPass;
    }

    public void setSid(String smsId) {
        this.smsId = smsId;
    }

    public void setUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    public void setTemplateId(String smsTemplateId) {
        this.smsTemplateId = smsTemplateId;
    }

    public void setMobileNumber(String MobileNumber) {
        this.MobileNumber = MobileNumber;
    }

    public String getUser() {
        return userName;
    }

    public String getPasswd() {
        return userPass;
    }

    public String getSid() {
        return smsId;
    }

    public String getUrl() {
        return smsUrl;
    }

    public String getTemplateId() {
        return smsTemplateId;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

}
