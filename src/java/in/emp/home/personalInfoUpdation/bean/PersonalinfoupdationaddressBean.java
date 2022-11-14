/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.home.personalInfoUpdation.bean;

/**
 *
 * @author Prajakta
 */
public class PersonalinfoupdationaddressBean implements java.io.Serializable{

private int SrNum;
private String AddressLine1;
private String AddressLine2;
private String AddressLine3;
private String AddressLine4;
private String City;
private String State;
private String Pincode;
private String Country;
private String PriFlag;
private String AddressStyle;
private String AddressType;

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String AddressLine1) {
        this.AddressLine1 = AddressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String AddressLine2) {
        this.AddressLine2 = AddressLine2;
    }

    public String getAddressLine3() {
        return AddressLine3;
    }

    public void setAddressLine3(String AddressLine3) {
        this.AddressLine3 = AddressLine3;
    }

    public String getAddressLine4() {
        return AddressLine4;
    }

    public void setAddressLine4(String AddressLine4) {
        this.AddressLine4 = AddressLine4;
    }

    public String getAddressStyle() {
        return AddressStyle;
    }

    public void setAddressStyle(String AddressStyle) {
        this.AddressStyle = AddressStyle;
    }

    public String getAddressType() {
        return AddressType;
    }

    public void setAddressType(String AddressType) {
        this.AddressType = AddressType;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String Pincode) {
        this.Pincode = Pincode;
    }

    public String getPriFlag() {
        return PriFlag;
    }

    public void setPriFlag(String PriFlag) {
        this.PriFlag = PriFlag;
    }

    public int getSrNum() {
        return SrNum;
    }

    public void setSrNum(int SrNum) {
        this.SrNum = SrNum;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }
}
