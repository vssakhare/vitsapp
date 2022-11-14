/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

/**
 *
 * @author Pooja Jadhav
 */
public class ProjBean implements java.io.Serializable{
    private String   PROJECT_CODE ;
    private String   PROJECT_DESC  ;
    private String   PLANT  ;      
    private String   ZONE ;        
    private String   CIRCLE    ;   
    private String   DIVISION    ; 
    private String   Scheme_code ; 
    private String   Scheme_Desc ; 
    private String VendorNumber;
    private String UserType;
  private String   PLANT_DESC  ;
private String Vendor_Invoice_Number;
private String Total_Po_Amt;
private String Bal_amt;
private String Total_Inv_Amt;
private String Total_Inv_Cnt;
private String Total_Invoicable_Amt;
private String zone_code;
private String circle_code;
private String purchasing_desc;
private String Purchasing_group;
private String Po_number;
private String Material_PO;
private String Centages_PO;
private String Service_PO;
    public String getPo_number() {
        return Po_number;
    }

    public void setPo_number(String Po_number) {
        this.Po_number = Po_number;
    }

    public String getMaterial_PO() {
        return Material_PO;
    }

    public void setMaterial_PO(String Material_PO) {
        this.Material_PO = Material_PO;
    }

    public String getCentages_PO() {
        return Centages_PO;
    }

    public void setCentages_PO(String Centages_PO) {
        this.Centages_PO = Centages_PO;
    }

    public String getService_PO() {
        return Service_PO;
    }

    public void setService_PO(String Service_PO) {
        this.Service_PO = Service_PO;
    }

    public String getPurchasing_group() {
        return Purchasing_group;
    }

    public void setPurchasing_group(String Purchasing_group) {
        this.Purchasing_group = Purchasing_group;
    }

    public String getPurchasing_desc() {
        return purchasing_desc;
    }

    public void setPurchasing_desc(String purchasing_desc) {
        this.purchasing_desc = purchasing_desc;
    }

    public String getZone_code() {
        return zone_code;
    }

    public void setZone_code(String zone_code) {
        this.zone_code = zone_code;
    }

    public String getCircle_code() {
        return circle_code;
    }

    public void setCircle_code(String circle_code) {
        this.circle_code = circle_code;
    }

    public String getTotal_Invoicable_Amt() {
        return Total_Invoicable_Amt;
    }

    public void setTotal_Invoicable_Amt(String Total_Invoicable_Amt) {
        this.Total_Invoicable_Amt = Total_Invoicable_Amt;
    }

    public String getTotal_Inv_Cnt() {
        return Total_Inv_Cnt;
    }

    public void setTotal_Inv_Cnt(String Total_Inv_Cnt) {
        this.Total_Inv_Cnt = Total_Inv_Cnt;
    }

    public String getTotal_Inv_Amt() {
        return Total_Inv_Amt;
    }

    public void setTotal_Inv_Amt(String Total_Inv_Amt) {
        this.Total_Inv_Amt = Total_Inv_Amt;
    }

    public String getTotal_Po_Amt() {
        return Total_Po_Amt;
    }

    public void setTotal_Po_Amt(String Total_Po_Amt) {
        this.Total_Po_Amt = Total_Po_Amt;
    }

    public String getBal_amt() {
        return Bal_amt;
    }

    public void setBal_amt(String Bal_amt) {
        this.Bal_amt = Bal_amt;
    }

    public String getVendor_Invoice_Number() {
        return Vendor_Invoice_Number;
    }

    public void setVendor_Invoice_Number(String Vendor_Invoice_Number) {
        this.Vendor_Invoice_Number = Vendor_Invoice_Number;
    }
  
    public String getPLANT_DESC() {
        return PLANT_DESC;
    }

    public void setPLANT_DESC(String PLANT_DESC) {
        this.PLANT_DESC = PLANT_DESC;
    }
      
    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    public String getVendorNumber() {
        return VendorNumber;
    }

    public void setVendorNumber(String VendorNumber) {
        this.VendorNumber = VendorNumber;
    }
    

    public String getPROJECT_CODE() {
        return PROJECT_CODE;
    }

    public void setPROJECT_CODE(String PROJECT_CODE) {
        this.PROJECT_CODE = PROJECT_CODE;
    }

    public String getPROJECT_DESC() {
        return PROJECT_DESC;
    }

    public void setPROJECT_DESC(String PROJECT_DESC) {
        this.PROJECT_DESC = PROJECT_DESC;
    }

    public String getPLANT() {
        return PLANT;
    }

    public void setPLANT(String PLANT) {
        this.PLANT = PLANT;
    }

    public String getZONE() {
        return ZONE;
    }

    public void setZONE(String ZONE) {
        this.ZONE = ZONE;
    }

    public String getCIRCLE() {
        return CIRCLE;
    }

    public void setCIRCLE(String CIRCLE) {
        this.CIRCLE = CIRCLE;
    }

    public String getDIVISION() {
        return DIVISION;
    }

    public void setDIVISION(String DIVISION) {
        this.DIVISION = DIVISION;
    }

    public String getScheme_code() {
        return Scheme_code;
    }

    public void setScheme_code(String Scheme_code) {
        this.Scheme_code = Scheme_code;
    }

    public String getScheme_Desc() {
        return Scheme_Desc;
    }

    public void setScheme_Desc(String Scheme_Desc) {
        this.Scheme_Desc = Scheme_Desc;
    }
}
