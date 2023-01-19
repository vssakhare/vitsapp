/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.bean;

import java.util.Date;


/**
 *
 * @author Vaishali S
 */
public class FeeTypeDtlsBean implements java.io.Serializable{
   
    private Integer feeTypeDtlsId;
    private String feeType;
     private Integer amount   ;
    private Integer applId;
    private Date created;
    private Date updated;
    private String whereClause="";
    private String remark="";

    

    public Integer getFeeTypeDtlsId() {
        return feeTypeDtlsId;
    }

    public void setFeeTypeDtlsId(Integer feeTypeDtlsId) {
        this.feeTypeDtlsId = feeTypeDtlsId;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getApplId() {
        return applId;
    }

    public void setApplId(Integer applId) {
        this.applId = applId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    

    
    
}

