/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.bean;

/**
 *
 * @author Vaishali S
 */


import java.util.Date;

public class LegalCommunicationBean {
    
  private Number   T_COMMUNICATION_LOG_ID;
  private Date CREATED;
 private String ISACTIVE	;
 private String ERROR;
 private String COMMUNICATION_TYPE;
 private String RECIPIENTS_INFO;
 private String SUBJECT;
 private String RECIPIENT_TYPE;
 private String RECIPIENT_NUMBER ;

    public Number getT_COMMUNICATION_LOG_ID() {
        return T_COMMUNICATION_LOG_ID;
    }

    public void setT_COMMUNICATION_LOG_ID(Number T_COMMUNICATION_LOG_ID) {
        this.T_COMMUNICATION_LOG_ID = T_COMMUNICATION_LOG_ID;
    }

    public Date getCREATED() {
        return CREATED;
    }

    public void setCREATED(Date CREATED) {
        this.CREATED = CREATED;
    }

    public String getISACTIVE() {
        return ISACTIVE;
    }

    public void setISACTIVE(String ISACTIVE) {
        this.ISACTIVE = ISACTIVE;
    }

    public String getERROR() {
        return ERROR;
    }

    public void setERROR(String ERROR) {
        this.ERROR = ERROR;
    }

    public String getCOMMUNICATION_TYPE() {
        return COMMUNICATION_TYPE;
    }

    public void setCOMMUNICATION_TYPE(String COMMUNICATION_TYPE) {
        this.COMMUNICATION_TYPE = COMMUNICATION_TYPE;
    }

    public String getRECIPIENTS_INFO() {
        return RECIPIENTS_INFO;
    }

    public void setRECIPIENTS_INFO(String RECIPIENTS_INFO) {
        this.RECIPIENTS_INFO = RECIPIENTS_INFO;
    }

    public String getSUBJECT() {
        return SUBJECT;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    public String getRECIPIENT_TYPE() {
        return RECIPIENT_TYPE;
    }

    public void setRECIPIENT_TYPE(String RECIPIENT_TYPE) {
        this.RECIPIENT_TYPE = RECIPIENT_TYPE;
    }

    public String getRECIPIENT_NUMBER() {
        return RECIPIENT_NUMBER;
    }

    public void setRECIPIENT_NUMBER(String RECIPIENT_NUMBER) {
        this.RECIPIENT_NUMBER = RECIPIENT_NUMBER;
    }
 
}
