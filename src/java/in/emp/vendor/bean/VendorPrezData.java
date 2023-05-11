/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

import java.util.LinkedList;

/**
 *
 * @author Prajakta
 */
public class VendorPrezData implements java.io.Serializable {
    private LinkedList vendorContactNumber;
    private VendorBean vendorBean;
   private LinkedList vendorDtlList;
    private LinkedList locationList;
    private LinkedList summaryList;
    private LinkedList applicationList;
   private LinkedList projList;
   private LinkedList vendorInvList;

   
      private LinkedList clearingDocDetails;

  
       private LinkedList poLineDetails;
 private PSBean psstatusList;
   

    private VendorInputBean vendorInputBean;
    private LinkedList vendorList;
    private LinkedList POList;
    private LinkedList vendorInvoiceList;
    private LinkedList vendorInputList;
   private PoStatusBean postatusList;

   private PoLineStatusBean polinestatusList;
   private VendorStatuBean vendorstatusList;
private LinkedList zoneList;
   private LinkedList circleList;
   private LinkedList divisionList;
private LinkedList subdivisionList;
private LinkedList sesList;
private LinkedList migoList;
private LinkedList invList;
private LinkedList paymentList;
private LinkedList legalSummaryList;

    public LinkedList getLegalSummaryList() {
        return legalSummaryList;
    }

    public void setLegalSummaryList(LinkedList legalSummaryList) {
        this.legalSummaryList = legalSummaryList;
    }

  public LinkedList getPoLineDetails() {
        return poLineDetails;
    }

    public void setPoLineDetails(LinkedList poLineDetails) {
        this.poLineDetails = poLineDetails;
    }
    public PoLineStatusBean getPolinestatusList() {
        return polinestatusList;
    }

    public void setPolinestatusList(PoLineStatusBean polinestatusList) {
        this.polinestatusList = polinestatusList;
    }
 public LinkedList getClearingDocDetails() {
        return clearingDocDetails;
    }

    public void setClearingDocDetails(LinkedList clearingDocDetails) {
        this.clearingDocDetails = clearingDocDetails;
    }
    public LinkedList getSesList() {
        return sesList;
    }

    public void setSesList(LinkedList sesList) {
        this.sesList = sesList;
    }

    public LinkedList getMigoList() {
        return migoList;
    }

    public void setMigoList(LinkedList migoList) {
        this.migoList = migoList;
    }

    public LinkedList getInvList() {
        return invList;
    }

    public void setInvList(LinkedList invList) {
        this.invList = invList;
    }

    public LinkedList getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(LinkedList paymentList) {
        this.paymentList = paymentList;
    }

    public LinkedList getSubdivisionList() {
        return subdivisionList;
    }

    public void setSubdivisionList(LinkedList subdivisionList) {
        this.subdivisionList = subdivisionList;
    }
   

    public LinkedList getZoneList() {
        return zoneList;
    }

    public void setZoneList(LinkedList zoneList) {
        this.zoneList = zoneList;
    }

    public LinkedList getCircleList() {
        return circleList;
    }

    public void setCircleList(LinkedList circleList) {
        this.circleList = circleList;
    }

    public LinkedList getDivisionList() {
        return divisionList;
    }

    public void setDivisionList(LinkedList divisionList) {
        this.divisionList = divisionList;
    }
 
    public LinkedList getVendorInvList() {
        return vendorInvList;
    }

    public void setVendorInvList(LinkedList vendorInvList) {
        this.vendorInvList = vendorInvList;
    }

    public PSBean getPsstatusList() {
        return psstatusList;
    }

    public void setPsstatusList(PSBean psstatusList) {
        this.psstatusList = psstatusList;
    }
  
    public LinkedList getProjList() {
        return projList;
    }

    public void setProjList(LinkedList projList) {
        this.projList = projList;
    }



    public VendorStatuBean getVendorstatusList() {
        return vendorstatusList;
    }

    public void setVendorstatusList(VendorStatuBean vendorstatusList) {
        this.vendorstatusList = vendorstatusList;
    }
 public PoStatusBean getPostatusList() {
        return postatusList;
    }

    public void setPostatusList(PoStatusBean postatusList) {
        this.postatusList = postatusList;
    }
    public LinkedList getVendorContactList(){
        return vendorContactNumber;
    }
    
    public  void setVendorContactList(LinkedList vendorContactNumber)
    {
        this.vendorContactNumber=vendorContactNumber;
    }
    public LinkedList getSummaryList() {
        return summaryList;
    }

    public void setSummaryList(LinkedList summaryList) {
        this.summaryList = summaryList;
    }
     public LinkedList getapplicationList() {
        return applicationList;
    }

    public void setapplicationList(LinkedList applicationList) {
        this.applicationList = applicationList;
    }
    public LinkedList getLocationList() {
        return locationList;
    }

    public void setLocationList(LinkedList locationList) {
        this.locationList = locationList;
    }
        
    public LinkedList getVendorDtlList() {
        return vendorDtlList;
    }

    public void setVendorDtlList(LinkedList vendorDtlList) {
        this.vendorDtlList = vendorDtlList;
    }

    public LinkedList getVendorInputList() {
        return vendorInputList;
    }

    public void setVendorInputList(LinkedList vendorInputList) {
        this.vendorInputList = vendorInputList;
    }

    public VendorInputBean getVendorInputBean() {
        return vendorInputBean;
    }

    public void setVendorInputBean(VendorInputBean vendorInputBean) {
        this.vendorInputBean = vendorInputBean;
    }

    public VendorBean getVendorBean() {
        return vendorBean;
    }

    public void setVendorBean(VendorBean vendorBean) {
        this.vendorBean = vendorBean;
    }

    public LinkedList getVendorList() {
        return vendorList;
    }

    public void setVendorList(LinkedList vendorList) {
        this.vendorList = vendorList;
    }

    public LinkedList getPOList() {
        return POList;
    }

    public void setPOList(LinkedList POList) {
        this.POList = POList;
    }

    public LinkedList getVendorInvoiceList() {
        return vendorInvoiceList;
    }

    public void setVendorInvoiceList(LinkedList vendorInvoiceList) {
        this.vendorInvoiceList = vendorInvoiceList;
    }

   
    

 
}
