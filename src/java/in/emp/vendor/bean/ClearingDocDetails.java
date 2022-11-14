/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;
import java.util.Date;
/**
 *
 * @author Pooja Jadhav
 */
public class ClearingDocDetails  implements java.io.Serializable{
 private String  INVOICE_NUMBER;
    private Date INVOICE_DATE;
  private String INVOICE_AMOUNT;
   private String  PO_NUMBER;
    private String CL_DOC_NO;
    private Date CL_DOC_DT  ;
    public Date getINVOICE_DATE() {
        return INVOICE_DATE;
    }

    public void setINVOICE_DATE(Date INVOICE_DATE) {
        this.INVOICE_DATE = INVOICE_DATE;
    }

    public Date getCL_DOC_DT() {
        return CL_DOC_DT;
    }

    public void setCL_DOC_DT(Date CL_DOC_DT) {
        this.CL_DOC_DT = CL_DOC_DT;
    }
  
    
    public String getINVOICE_NUMBER() {
        return INVOICE_NUMBER;
    }

    public void setINVOICE_NUMBER(String INVOICE_NUMBER) {
        this.INVOICE_NUMBER = INVOICE_NUMBER;
    }


    public String getINVOICE_AMOUNT() {
        return INVOICE_AMOUNT;
    }

    public void setINVOICE_AMOUNT(String INVOICE_AMOUNT) {
        this.INVOICE_AMOUNT = INVOICE_AMOUNT;
    }

    public String getPO_NUMBER() {
        return PO_NUMBER;
    }

    public void setPO_NUMBER(String PO_NUMBER) {
        this.PO_NUMBER = PO_NUMBER;
    }

    public String getCL_DOC_NO() {
        return CL_DOC_NO;
    }

    public void setCL_DOC_NO(String CL_DOC_NO) {
        this.CL_DOC_NO = CL_DOC_NO;
    }

  
  
}
