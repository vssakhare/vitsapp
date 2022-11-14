/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.security.ws.bean;

import in.emp.ws.SMSWSBean;

/**
 *
 * @author Sachin
 */
public class SecurityOTPWSBean extends SMSWSBean implements java.io.Serializable {

    private String message = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}