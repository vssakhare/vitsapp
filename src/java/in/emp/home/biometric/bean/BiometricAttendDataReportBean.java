/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.biometric.bean;

/**
 *
 * @author Prajakta
 */
public class BiometricAttendDataReportBean implements java.io.Serializable {

    private BiometricAttendDataBean Block;
    private String PNFlag;
    private String HFlag;
    private String SaveFlag;

    public BiometricAttendDataBean getBlock() {
        return Block;
    }

    public void setBlock(BiometricAttendDataBean Block) {
        this.Block = Block;
    }

    public String getHFlag() {
        return HFlag;
    }

    public void setHFlag(String HFlag) {
        this.HFlag = HFlag;
    }

    public String getPNFlag() {
        return PNFlag;
    }

    public void setPNFlag(String PNFlag) {
        this.PNFlag = PNFlag;
    }

    public String getSaveFlag() {
        return SaveFlag;
    }

    public void setSaveFlag(String SaveFlag) {
        this.SaveFlag = SaveFlag;
    }
}
