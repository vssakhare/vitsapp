/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.bean;

/**
 *
 * @author Rikma Rai
 */
public class FeeTypeBean implements java.io.Serializable{
    private Integer feeIndex;
    private Integer caseType;
    private String feeType;
    private String sacCode;
    

    public Integer getFeeIndex() {
        return feeIndex;
    }

    public void setFeeIndex(Integer feeIndex) {
        this.feeIndex = feeIndex;
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getSacCode() {
        return sacCode;
    }

    public void setSacCode(String sacCode) {
        this.sacCode = sacCode;
    }
    
    
}
