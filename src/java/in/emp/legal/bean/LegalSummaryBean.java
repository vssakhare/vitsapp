/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.bean;

import in.emp.authority.bean.AuthorityBean;
import java.util.Date;

/**
 *
 * @author Prajakta
 */
public class LegalSummaryBean  implements java.io.Serializable {

   
      private String   P_Tech_MORE_THAN30DAYS ;
   private String  P_TechLESSTHAN30DAYS	;
  private String  P_Acc_MORETHAN30DAYS	 ;
  private String  P_Acc_LESSTHAN30DAYS;
   private String P_Cash_MORE_THAN30DAYS ;
   private String P_Cash_LESS_THAN30DAYS  ;
 private String pTot;
    private String vSubmit;

    public String getPaid() {
        return Paid;
    }

    public void setPaid(String Paid) {
        this.Paid = Paid;
    }

    public String getUNPAID_SUBMITTED() {
        return UNPAID_SUBMITTED;
    }

    public void setUNPAID_SUBMITTED(String UNPAID_SUBMITTED) {
        this.UNPAID_SUBMITTED = UNPAID_SUBMITTED;
    }
     private String Paid;
     private String   UNPAID_SUBMITTED;
    public String getP_Tech_MORE_THAN30DAYS() {
        return P_Tech_MORE_THAN30DAYS;
    }

    public void setP_Tech_MORE_THAN30DAYS(String P_Tech_MORE_THAN30DAYS) {
        this.P_Tech_MORE_THAN30DAYS = P_Tech_MORE_THAN30DAYS;
    }

    public String getP_TechLESSTHAN30DAYS() {
        return P_TechLESSTHAN30DAYS;
    }

    public void setP_TechLESSTHAN30DAYS(String P_TechLESSTHAN30DAYS) {
        this.P_TechLESSTHAN30DAYS = P_TechLESSTHAN30DAYS;
    }

    public String getP_Acc_MORETHAN30DAYS() {
        return P_Acc_MORETHAN30DAYS;
    }

    public void setP_Acc_MORETHAN30DAYS(String P_Acc_MORETHAN30DAYS) {
        this.P_Acc_MORETHAN30DAYS = P_Acc_MORETHAN30DAYS;
    }

    public String getP_Acc_LESSTHAN30DAYS() {
        return P_Acc_LESSTHAN30DAYS;
    }

    public void setP_Acc_LESSTHAN30DAYS(String P_Acc_LESSTHAN30DAYS) {
        this.P_Acc_LESSTHAN30DAYS = P_Acc_LESSTHAN30DAYS;
    }

    public String getP_Cash_MORE_THAN30DAYS() {
        return P_Cash_MORE_THAN30DAYS;
    }

    public void setP_Cash_MORE_THAN30DAYS(String P_Cash_MORE_THAN30DAYS) {
        this.P_Cash_MORE_THAN30DAYS = P_Cash_MORE_THAN30DAYS;
    }

    public String getP_Cash_LESS_THAN30DAYS() {
        return P_Cash_LESS_THAN30DAYS;
    }

    public void setP_Cash_LESS_THAN30DAYS(String P_Cash_LESS_THAN30DAYS) {
        this.P_Cash_LESS_THAN30DAYS = P_Cash_LESS_THAN30DAYS;
    }
   

   

   
    public String getpTot() {
        return pTot;
    }

    public void setpTot(String pTot) {
        this.pTot = pTot;
    }

    public String getvSubmit() {
        return vSubmit;
    }

    public void setvSubmit(String vSubmit) {
        this.vSubmit = vSubmit;
    }
    
    
}