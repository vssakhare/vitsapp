/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.user.bean;

import java.util.LinkedList;

/**
 *
 * @author Administrator
 */
public class UserParameterBean {

    private long paramId;
    private String paramName;
    private String paramValue;
    private LinkedList userParameterList;

    /**
     * @return the paramId
     */
    public long getParamId() {
        return paramId;
    }

    /**
     * @param paramId the paramId to set
     */
    public void setParamId(long paramId) {
        this.paramId = paramId;
    }

    /**
     * @return the paraName
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * @param paraName the paraName to set
     */
    public void setParamName(String paraName) {
        this.paramName = paraName;
    }

    /**
     * @return the paramValue
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * @param paramValue the paramValue to set
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * @return the userParameterList
     */
    public LinkedList getUserParameterList() {
        return userParameterList;
    }

    /**
     * @param userParameterList the userParameterList to set
     */
    public void setUserParameterList(LinkedList userParameterList) {
        this.userParameterList = userParameterList;
    }

}
