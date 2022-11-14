/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

/**
 *
 * @author Pooja Jadhav
 */
public class HOBean  implements java.io.Serializable{
     private String SAP_MODULE;
      private String emp_name;
       private String EMP_CPF;
        private String EMP_DESIGNATION;
        private String EMP_CONTACT_NUMBER;
private String ESCALATION_EMP_CPF;
private String PURCHASING_GROUP;
private String ESCALATION_EMP_DESIGNATION;
    public String getESCALATION_EMP_DESIGNATION() {
        return ESCALATION_EMP_DESIGNATION;
    }

    public void setESCALATION_EMP_DESIGNATION(String ESCALATION_EMP_DESIGNATION) {
        this.ESCALATION_EMP_DESIGNATION = ESCALATION_EMP_DESIGNATION;
    }


private String  PLANT;
    public String getPLANT() {
        return PLANT;
    }

    public void setPLANT(String PLANT) {
        this.PLANT = PLANT;
    }

    public String getPURCHASING_GROUP() {
        return PURCHASING_GROUP;
    }

    public void setPURCHASING_GROUP(String PURCHASING_GROUP) {
        this.PURCHASING_GROUP = PURCHASING_GROUP;
    }

    public String getESCALATION_EMP_CPF() {
        return ESCALATION_EMP_CPF;
    }

    public void setESCALATION_EMP_CPF(String ESCALATION_EMP_CPF) {
        this.ESCALATION_EMP_CPF = ESCALATION_EMP_CPF;
    }
        

    public String getSAP_MODULE() {
        return SAP_MODULE;
    }

    public void setSAP_MODULE(String SAP_MODULE) {
        this.SAP_MODULE = SAP_MODULE;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEMP_CPF() {
        return EMP_CPF;
    }

    public void setEMP_CPF(String EMP_CPF) {
        this.EMP_CPF = EMP_CPF;
    }

    public String getEMP_DESIGNATION() {
        return EMP_DESIGNATION;
    }

    public void setEMP_DESIGNATION(String EMP_DESIGNATION) {
        this.EMP_DESIGNATION = EMP_DESIGNATION;
    }

    public String getEMP_CONTACT_NUMBER() {
        return EMP_CONTACT_NUMBER;
    }

    public void setEMP_CONTACT_NUMBER(String EMP_CONTACT_NUMBER) {
        this.EMP_CONTACT_NUMBER = EMP_CONTACT_NUMBER;
    }
        
}
