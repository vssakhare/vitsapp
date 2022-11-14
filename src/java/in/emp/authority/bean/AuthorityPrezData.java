/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.authority.bean;

import java.util.LinkedList;

/**
 *
 * @author Prajakta
 */
public class AuthorityPrezData implements java.io.Serializable {

    private AuthorityBean feedbackBean;
//    private ComplaintBean vendorBean;
    private LinkedList vendorList;

    public AuthorityBean getAuthorityBean() {
        return feedbackBean;
    }

    public void setAuthorityBean(AuthorityBean feedbackBean) {
        this.feedbackBean = feedbackBean;
    }

    public AuthorityBean getFeedbackBean() {
        return feedbackBean;
    }

    public void setFeedbackBean(AuthorityBean feedbackBean) {
        this.feedbackBean = feedbackBean;
    }

    public LinkedList getAuthorityList() {
        return vendorList;
    }

    public void setAuthorityList(LinkedList vendorList) {
        this.vendorList = vendorList;
    }

    

 
}
