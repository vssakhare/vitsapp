/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.biometric.bean;

/**
 *
 * @author Prajakta
 */
public class BiometricAttendDataBean implements java.io.Serializable {

    private String EmpNumber;
    private String FullName;
    private String Designation;
    private int Code;
    private BiometricAttendADataBean[] AData = new BiometricAttendADataBean[31];
    private String YYYYMM;

    public BiometricAttendADataBean[] getAData() {
        return AData;
    }

    public void setAData(BiometricAttendADataBean[] AData) {
        this.AData = AData;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public String getEmpNumber() {
        return EmpNumber;
    }

    public void setEmpNumber(String EmpNumber) {
        this.EmpNumber = EmpNumber;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getYYYYMM() {
        return YYYYMM;
    }

    public void setYYYYMM(String YYYYMM) {
        this.YYYYMM = YYYYMM;
    }
}
