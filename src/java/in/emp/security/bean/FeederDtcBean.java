/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.security.bean;

/**
 *
 * @author rgchoudhari
 */
public class FeederDtcBean {
    private String feederId;
    private String feederCode;
    private String feederName;
    private String dtcId;
    private String dtcCode;
    private String dtcName;
    private String ssId;
    private String ssCode;
    private String ssName;
    private String location_id;
     private String location_cd;
    private String location_desc;
    
    public String getLocation_cd() {
        return location_cd;
    }

    public void setLocation_cd(String location_cd) {
        this.location_cd = location_cd;
    }

    public String getLocation_desc() {
        return location_desc;
    }

    public void setLocation_desc(String location_desc) {
        this.location_desc = location_desc;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }
   
    
    public String getSsCode() {
        return ssCode;
    }

    public void setSsCode(String ssCode) {
        this.ssCode = ssCode;
    }

    public String getSsId() {
        return ssId;
    }

    public void setSsId(String ssId) {
        this.ssId = ssId;
    }

    public String getSsName() {
        return ssName;
    }

    public void setSsName(String ssName) {
        this.ssName = ssName;
    }
    

    public String getFeederCode() {
        return feederCode;
    }

    public void setFeederCode(String feederCode) {
        this.feederCode = feederCode;
    }

    public String getFeederName() {
        return feederName;
    }

    public void setFeederName(String feederName) {
        this.feederName = feederName;
    }
    

    public String getDtcCode() {
        return dtcCode;
    }

    public void setDtcCode(String dtcCode) {
        this.dtcCode = dtcCode;
    }

    public String getDtcId() {
        return dtcId;
    }

    public void setDtcId(String dtcId) {
        this.dtcId = dtcId;
    }

    public String getDtcName() {
        return dtcName;
    }

    public void setDtcName(String dtcName) {
        this.dtcName = dtcName;
    }

    public String getFeederId() {
        return feederId;
    }

    public void setFeederId(String feederId) {
        this.feederId = feederId;
    }
    
}
