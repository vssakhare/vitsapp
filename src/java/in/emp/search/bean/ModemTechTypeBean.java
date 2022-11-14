package in.emp.search.bean;

// imports
import java.sql.*;
import in.emp.common.UserAuditBean;

public class ModemTechTypeBean extends UserAuditBean implements java.io.Serializable 
{
        private int MODEM_TECH_ID;
	private String MODEM_TECH_CD;
	private String MODEM_TECH_DESC;
	private String STATUS_CD;
	private long CREATED_BY;
	private long CREATED_DT;
	private Date UPDATED_BY;
	private Date UPDATED_DT;

    public long getCREATED_BY() {
        return CREATED_BY;
    }

    public void setCREATED_BY(long CREATED_BY) {
        this.CREATED_BY = CREATED_BY;
    }

    public long getCREATED_DT() {
        return CREATED_DT;
    }

    public void setCREATED_DT(long CREATED_DT) {
        this.CREATED_DT = CREATED_DT;
    }

    public String getMODEM_TECH_CD() {
        return MODEM_TECH_CD;
    }

    public void setMODEM_TECH_CD(String MODEM_TECH_CD) {
        this.MODEM_TECH_CD = MODEM_TECH_CD;
    }

    public String getMODEM_TECH_DESC() {
        return MODEM_TECH_DESC;
    }

    public void setMODEM_TECH_DESC(String MODEM_TECH_DESC) {
        this.MODEM_TECH_DESC = MODEM_TECH_DESC;
    }

    public int getMODEM_TECH_ID() {
        return MODEM_TECH_ID;
    }

    public void setMODEM_TECH_ID(int MODEM_TECH_ID) {
        this.MODEM_TECH_ID = MODEM_TECH_ID;
    }

    public String getSTATUS_CD() {
        return STATUS_CD;
    }

    public void setSTATUS_CD(String STATUS_CD) {
        this.STATUS_CD = STATUS_CD;
    }

    public Date getUPDATED_BY() {
        return UPDATED_BY;
    }

    public void setUPDATED_BY(Date UPDATED_BY) {
        this.UPDATED_BY = UPDATED_BY;
    }

    public Date getUPDATED_DT() {
        return UPDATED_DT;
    }

    public void setUPDATED_DT(Date UPDATED_DT) {
        this.UPDATED_DT = UPDATED_DT;
    }
        
        

    

} // End of Class