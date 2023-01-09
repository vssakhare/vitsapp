/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao;

//-- java imports
import in.emp.hrms.bean.HRMSUserBean;
import in.emp.legal.bean.FeeTypeBean;
import in.emp.legal.bean.HOSectionMatrixBean;
import in.emp.legal.bean.LegalCommunicationBean;
import in.emp.legal.bean.LegalInvoiceBean;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.legal.bean.OrganizationMasterBean;
import in.emp.vendor.bean.ClearingDocDetails;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.HOBean;
import in.emp.vendor.bean.PoLineStatusBean;
import in.emp.vendor.bean.PoStatusBean;
import in.emp.vendor.bean.ProjBean;
import in.emp.vendor.bean.SMSResponseBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import java.util.LinkedList;


import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.bean.VendorStatuBean;
import java.util.List;

/**
 *
 * @author Prajakta
 */
public interface VendorDao {

    public LinkedList getVendorList(VendorBean vendorBeanObj) throws Exception;
     public LinkedList getVendorVerifiedList(VendorBean vendorBeanObj) throws Exception;
    public LinkedList getSummaryList(VendorBean vendorBeanObj) throws Exception;
     public LinkedList getTableList(VendorInputBean vendorInputBeanObj) throws Exception;
    public LinkedList getPOList(POBean poBeanObj) throws Exception; 
     public LinkedList getzoneList(POBean poBeanObj) throws Exception; 
      public LinkedList getcircleList(POBean poBeanObj) throws Exception; 
       public LinkedList getSubmitAtList(PoLineStatusBean poLinestatusbeanobj) throws Exception; 
       public LinkedList getdivisionList(POBean poBeanObj) throws Exception; 
              public LinkedList getsubdivisionList(POBean poBeanObj) throws Exception; 
  public LinkedList getClearingDocDetails(ClearingDocDetails clearingDocDetailsObj) throws Exception;  
   public LinkedList getPOLineDetails(PoLineStatusBean poLineStatusBeanObj) throws Exception; 
    public LinkedList getProjList(ProjBean projBeanObj) throws Exception; 
    public POBean getPOLocationList(POBean poBeanObj) throws Exception;
        public POBean getPOLocation(POBean poBeanObj) throws Exception; 
    public ProjBean getProjLocationList(ProjBean projBeanObj) throws Exception;
       public POBean getPODetails(POBean poBeanObj) throws Exception; 
           public SMSResponseBean getSmsResponseTrackerTxnHelper(SMSResponseBean smsresponsebeanbeanobj) throws Exception; 
    public ProjBean getProjDetails(ProjBean projBeanObj) throws Exception;
     public ProjBean getProjectDetails(ProjBean projBeanObj) throws Exception;
    public POBean getPOInvDetails(POBean poBeanObj) throws Exception; 
    public ProjBean getProjInvDetails(ProjBean projBeanObj) throws Exception;
    public LinkedList getVendorDtlList(POBean poBeanObj) throws Exception;
    
    public LinkedList getlocationList(POBean poBeanObj) throws Exception;
    public LinkedList getVendorinvList(POBean poBeanObj) throws Exception;
    public LinkedList getInvoiceList(VendorBean vendorBeanObj) throws Exception;
    
    public LinkedList getVendorInputList(VendorInputBean vendorInputBeanObj) throws Exception;    
        public VendorInputBean getVendorVerifiedInputForm(VendorInputBean vendorInputBeanObj) throws Exception;    

    public VendorInputBean getVendorInputForm(VendorInputBean vendorInputBeanObj) throws Exception;

    public VendorInputBean getVendorInputFormData(VendorInputBean vendorInputBeanObj) throws Exception;
      public VendorInputBean getVendorPsInputFormData(VendorInputBean vendorInputBeanObj) throws Exception;
            public VendorInputBean getVendorNonpoInputFormData(VendorInputBean vendorInputBeanObj) throws Exception;

    public VendorBean getVendorFormData(VendorBean vendorBeanObj) throws Exception;
        public VendorBean getVendorPsFormData(VendorBean vendorBeanObj) throws Exception;
     public VendorBean getEmpPsFormData(VendorBean vendorBeanObj) throws Exception;
    public VendorPrezData VendorApplFormTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception;
   public void  GetPOLineInvTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception;
  public VendorPrezData VendorProjApplFormTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception;
    public VendorPrezData VendorNonpoApplFormTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception;
 
  //public LinkedList getContactNumber(VendorBean vendorBeanObj) throws Exception;
    public VendorBean getContactNumber(VendorBean vendorBeanObj) throws Exception;
    
    public void getVendorSmsStatus(VendorStatuBean vendorStatusBeanObj) throws Exception;
    
     public POBean getOfficeCodeDetails(POBean POBeanObj) throws Exception;
       public POBean getPlantCodeDetails(POBean POBeanObj) throws Exception;
      public POBean getForwardedToOfficeCodeDetails(POBean POBeanObj) throws Exception;
           public LinkedList getPlantDetails(POBean POBeanObj) throws Exception;
          public HOBean getHOSmsDetails(HOBean HOBeanObj) throws Exception;
          public HOSectionMatrixBean getHoLegalSmsDetails(HOSectionMatrixBean HoLegalBean) throws Exception;

     
         public VendorInputBean getInvoicedetails(VendorInputBean vendorInputBeanObj) throws Exception;

         //public VendorInputBean getHigherContactList(VendorInputBean vendorInputBeanObj) throws Exception;
    public LinkedList<VendorInputBean> getHigherContactList(VendorInputBean vendorInputBeanObj) throws Exception;
    public LinkedList<VendorInputBean> getAuthContactList(VendorInputBean vendorInputBeanObj) throws Exception;

         public LinkedList<VendorInputBean> getEscStatusSmsList(VendorInputBean vendorInputBeanObj) throws Exception;
    
    public LinkedList<VendorInputBean> getSmsTrackerList(VendorInputBean vendorInputBeanObj) throws Exception;
     public LinkedList<LegalInvoiceInputBean> getLegalSmsTrackerList(LegalInvoiceInputBean legalInvoiceInputBeanObj) throws Exception;

    public List getPOStatus(List lstErpToVitsFileFormat) throws Exception;
     public void getPOStatusProcedure() throws Exception;
      public List getPOLineStatus(List lstErpToVitsFileFormat) throws Exception;
          public void getPOLineStatusProcedure() throws Exception;

    public List getPSStatus(List lstErpToVitsFileFormat) throws Exception;
    public LinkedList<VendorInputBean> putInvoiceStatus( VendorInputBean vendorInputBeanObj) throws Exception;
    public LinkedList getSelectedLineStatus(LinkedList selectedpoLineDetails) throws Exception;
     public List getVendorStatus(List listErpToVitsFileFormat) throws Exception;
     public void getVendorStatusProcedure() throws Exception;

    public LinkedList getRetentionDetails(VendorBean bean)throws Exception;
public VendorInputBean saveClaimedRetentionDetails(VendorInputBean vendorBean) throws Exception ;
public LinkedList<VendorBean> putRetentionInvoiceStatus( VendorBean vendorBeanObj) throws Exception;

    public VendorInputBean getVendorRetentionInputFormData(VendorInputBean vendorInputBeanObj)throws Exception;

    public List saveRetentionDetailsResponse(List<VendorInputBean> listErpToVitsFileFormat)throws Exception;

    public void updateRetentionDetailsResponseProcedure()throws Exception;

    public LinkedList getPartialRetentionDetails(VendorBean vendorBean)throws Exception;

    public List saveLegalInvoiceStatus(List<LegalInvoiceBean> listErpToVitsFileFormat)throws Exception;

    public List getCourtCaseDetailsForVendor(LegalInvoiceBean legalInvoiceBean)throws Exception;
    public LegalInvoiceInputBean saveLegalInvoiceForm(LegalInvoiceInputBean legalInvoiceInputBean)throws Exception;

    public List getLegalInvoiceInputList(LegalInvoiceInputBean legalInvoiceInputBean)throws Exception;

    public List getLegalFeeType(FeeTypeBean bean)throws Exception;

    public List getLegalHierarchyLocation(OrganizationMasterBean organizationMasterBean)throws Exception;
    
    public void updateLegalCommunicationLog(LegalCommunicationBean legalCommunicationBean) throws Exception;
    
}

