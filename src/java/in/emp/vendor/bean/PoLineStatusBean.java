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
public class PoLineStatusBean  implements java.io.Serializable{
  private String Contract_Document   ;
  private String Item		     ;
  private String Deletion_Indicator  ;

   
  private String PoLineId;
 
  private Date Last_Changed_on	    ;
  private String Short_Text	     ;
  private String Material	     ;
  private String Company_Code	     ;
  private String Plant		     ;
  private String Storage_Location    ;
  private String Material_Group	     ;

  

   
  private String Order_Quantity		     ;
  
  private String Order_Unit	     ;
  private String Per_unit_price	     ;
  private String Net_Order_Value     ;
  private String Gross_order_value   ;
  private String Tax_code	     ;
  private String Valuation_Type	     ;
  private String Valuation_Category	 ;
  private String Delivery_Completed	 ;
  private String Item_Category	     ;
  private String Acct_Assignment_Cat;
  private String Outline_Agreement   ;
  private String Funds_Center	     ;
  private String Commitment_Item	    ;
  private String Profit_Center	     ;
  private String Vendor_Number	     ;
   private String APPL_ID	     ;
 private String INVOICE_NUMBER	     ;
 private String PLANT_DESC;
 private String    purchasing_group   ;
 private String    purchasing_desc    ;
 private String    total_value_limit  ;
 private Date      po_from_date	      ;
 private Date      po_to_date	      ;
 private String    purchasing_org     ;
 private String    po_type	      ;
 private String    vendor_number      ;
 private String    vendor_name	      ;
 private Date      po_doc_date	      ;
 private String     created_by	      ;
    public String getPurchasing_group() {
        return purchasing_group;
    }

    public void setPurchasing_group(String purchasing_group) {
        this.purchasing_group = purchasing_group;
    }

    public String getPurchasing_desc() {
        return purchasing_desc;
    }

    public void setPurchasing_desc(String purchasing_desc) {
        this.purchasing_desc = purchasing_desc;
    }

    public String getTotal_value_limit() {
        return total_value_limit;
    }

    public void setTotal_value_limit(String total_value_limit) {
        this.total_value_limit = total_value_limit;
    }

    public Date getPo_from_date() {
        return po_from_date;
    }

    public void setPo_from_date(Date po_from_date) {
        this.po_from_date = po_from_date;
    }

    public Date getPo_to_date() {
        return po_to_date;
    }

    public void setPo_to_date(Date po_to_date) {
        this.po_to_date = po_to_date;
    }

    public String getPurchasing_org() {
        return purchasing_org;
    }

    public void setPurchasing_org(String purchasing_org) {
        this.purchasing_org = purchasing_org;
    }

    public String getPo_type() {
        return po_type;
    }

    public void setPo_type(String po_type) {
        this.po_type = po_type;
    }

    public String getVendor_number() {
        return vendor_number;
    }

    public void setVendor_number(String vendor_number) {
        this.vendor_number = vendor_number;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public Date getPo_doc_date() {
        return po_doc_date;
    }

    public void setPo_doc_date(Date po_doc_date) {
        this.po_doc_date = po_doc_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }




    public String getPLANT_DESC() {
        return PLANT_DESC;
    }

    public void setPLANT_DESC(String PLANT_DESC) {
        this.PLANT_DESC = PLANT_DESC;
    }
 
    public String getINVOICE_NUMBER() {
        return INVOICE_NUMBER;
    }

    public void setINVOICE_NUMBER(String INVOICE_NUMBER) {
        this.INVOICE_NUMBER = INVOICE_NUMBER;
    }
  
    public String getAPPL_ID() {
        return APPL_ID;
    }

    public void setAPPL_ID(String APPL_ID) {
        this.APPL_ID = APPL_ID;
    }

    public String getVendor_Number() {
        return Vendor_Number;
    }

    public void setVendor_Number(String Vendor_Number) {
        this.Vendor_Number = Vendor_Number;
    }

   public String getPoLineId() {
        return PoLineId;
    }

    public void setPoLineId(String PoLineId) {
        this.PoLineId = PoLineId;
    }
     public Date getLast_Changed_on() {
        return Last_Changed_on;
    }

    public void setLast_Changed_on(Date Last_Changed_on) {
        this.Last_Changed_on = Last_Changed_on;
    }
    public String getOrder_Quantity() {
        return Order_Quantity;
    }

    public void setOrder_Quantity(String Order_Quantity) {
        this.Order_Quantity = Order_Quantity;
    }
  
    public String getContract_Document() {
        return Contract_Document;
    }

    public void setContract_Document(String Contract_Document) {
        this.Contract_Document = Contract_Document;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String Item) {
        this.Item = Item;
    }

    public String getDeletion_Indicator() {
        return Deletion_Indicator;
    }

    public void setDeletion_Indicator(String Deletion_Indicator) {
        this.Deletion_Indicator = Deletion_Indicator;
    }

   

    public String getShort_Text() {
        return Short_Text;
    }

    public void setShort_Text(String Short_Text) {
        this.Short_Text = Short_Text;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String Material) {
        this.Material = Material;
    }

    public String getCompany_Code() {
        return Company_Code;
    }

    public void setCompany_Code(String Company_Code) {
        this.Company_Code = Company_Code;
    }

    public String getPlant() {
        return Plant;
    }

    public void setPlant(String Plant) {
        this.Plant = Plant;
    }

    public String getStorage_Location() {
        return Storage_Location;
    }

    public void setStorage_Location(String Storage_Location) {
        this.Storage_Location = Storage_Location;
    }

    public String getMaterial_Group() {
        return Material_Group;
    }

    public void setMaterial_Group(String Material_Group) {
        this.Material_Group = Material_Group;
    }

  
    public String getOrder_Unit() {
        return Order_Unit;
    }

    public void setOrder_Unit(String Order_Unit) {
        this.Order_Unit = Order_Unit;
    }

    public String getPer_unit_price() {
        return Per_unit_price;
    }

    public void setPer_unit_price(String Per_unit_price) {
        this.Per_unit_price = Per_unit_price;
    }

    public String getNet_Order_Value() {
        return Net_Order_Value;
    }

    public void setNet_Order_Value(String Net_Order_Value) {
        this.Net_Order_Value = Net_Order_Value;
    }

    public String getGross_order_value() {
        return Gross_order_value;
    }

    public void setGross_order_value(String Gross_order_value) {
        this.Gross_order_value = Gross_order_value;
    }

    public String getTax_code() {
        return Tax_code;
    }

    public void setTax_code(String Tax_code) {
        this.Tax_code = Tax_code;
    }

    public String getValuation_Type() {
        return Valuation_Type;
    }

    public void setValuation_Type(String Valuation_Type) {
        this.Valuation_Type = Valuation_Type;
    }

    public String getValuation_Category() {
        return Valuation_Category;
    }

    public void setValuation_Category(String Valuation_Category) {
        this.Valuation_Category = Valuation_Category;
    }

    public String getDelivery_Completed() {
        return Delivery_Completed;
    }

    public void setDelivery_Completed(String Delivery_Completed) {
        this.Delivery_Completed = Delivery_Completed;
    }

    public String getItem_Category() {
        return Item_Category;
    }

    public void setItem_Category(String Item_Category) {
        this.Item_Category = Item_Category;
    }

    public String getAcct_Assignment_Cat() {
        return Acct_Assignment_Cat;
    }

    public void setAcct_Assignment_Cat(String Acct_Assignment_Cat) {
        this.Acct_Assignment_Cat = Acct_Assignment_Cat;
    }

    public String getOutline_Agreement() {
        return Outline_Agreement;
    }

    public void setOutline_Agreement(String Outline_Agreement) {
        this.Outline_Agreement = Outline_Agreement;
    }

    public String getFunds_Center() {
        return Funds_Center;
    }

    public void setFunds_Center(String Funds_Center) {
        this.Funds_Center = Funds_Center;
    }

    public String getCommitment_Item() {
        return Commitment_Item;
    }

    public void setCommitment_Item(String Commitment_Item) {
        this.Commitment_Item = Commitment_Item;
    }

    public String getProfit_Center() {
        return Profit_Center;
    }

    public void setProfit_Center(String Profit_Center) {
        this.Profit_Center = Profit_Center;
    }
				     
  
}
