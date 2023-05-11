/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor;

import in.emp.hrms.bean.HRMSUserBean;
import in.emp.legal.bean.FeeTypeBean;
import in.emp.legal.bean.FeeTypeDtlsBean;
import in.emp.legal.bean.HOSectionMatrixBean;
import in.emp.legal.bean.LegalCommunicationBean;
import in.emp.legal.bean.LegalInvoiceBean;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.legal.bean.OrganizationMasterBean;
import in.emp.sms.bean.TemplateIdBean;
import in.emp.vendor.bean.ClearingDocDetails;
import in.emp.vendor.bean.HOBean;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.PoLineStatusBean;
import in.emp.vendor.bean.PoStatusBean;
import in.emp.vendor.bean.ProjBean;
import in.emp.vendor.bean.SMSResponseBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorStatuBean;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Prajakta
 */
public interface VendorDelegate {

    public VendorPrezData getVendorList(VendorBean vendorBeanObj) throws Exception;
       public VendorPrezData  getVendorVerifiedList(VendorBean vendorBeanObj) throws Exception;
    public VendorPrezData getSummaryList(VendorBean vendorBeanObj) throws Exception;

  

      public LinkedList getLegalSummaryList(LegalInvoiceInputBean legalInvoiceInputBean) throws Exception;

    public VendorPrezData getTableList(VendorInputBean vendorInputBeanObj) throws Exception;
    public VendorPrezData getPOList(POBean poBeanObj) throws Exception;
     public LinkedList getPONumberList(POBean poBeanObj) throws Exception;
    public VendorPrezData getProjList(ProjBean projBeanObj) throws Exception;
    public POBean getPOLocationList(POBean poBeanObj) throws Exception; 
    public POBean getPOLocation(POBean poBeanObj) throws Exception;

    public ProjBean getProjLocationList(ProjBean projbeanobj) throws Exception;
    public POBean getPODetails(POBean poBeanObj) throws Exception;
    public ProjBean getProjDetails(ProjBean projbeanobj) throws Exception;
    public ProjBean getProjectDetails(ProjBean projbeanobj) throws Exception;
    public POBean getPOInvDetails(POBean poBeanObj) throws Exception;
    public ProjBean getProjInvDetails(ProjBean projbeanobj) throws Exception;
    public VendorPrezData getVendorDtlList(POBean poBeanObj) throws Exception;  
    public VendorPrezData getClearingDocDetails(ClearingDocDetails clearingDocDetailsObj) throws Exception;  
    public LinkedList getPOLineDetails(PoLineStatusBean poLineStatusBeanObj) throws Exception; 
    public VendorPrezData getlocationList(POBean poBeanObj) throws Exception;
         public VendorPrezData getzoneList(POBean poBeanObj) throws Exception;
            public LinkedList getcircleList(POBean poBeanObj) throws Exception;
            public LinkedList getSubmitAtList(PoLineStatusBean poLinestatusbeanobj) throws Exception;
               public VendorPrezData getdivisionList(POBean poBeanObj) throws Exception;
                       public VendorPrezData getsubdivisionList(POBean poBeanObj) throws Exception;
        public VendorPrezData getVendorinvList(POBean poBeanObj) throws Exception; 

    public VendorPrezData getInvoiceList(VendorBean vendorBeanObj) throws Exception;
    public SMSResponseBean getSmsResponseTrackerTxnHelper(SMSResponseBean smsresponsebeanbeanobj) throws Exception;
    public VendorBean getVendorApplForm(VendorBean vendorBeanObj) throws Exception;
    public VendorBean getVendorPsApplForm(VendorBean vendorBeanObj) throws Exception;
    public VendorBean getEmpPsApplForm(VendorBean vendorBeanObj) throws Exception;

    public VendorPrezData getVendorInputList(VendorInputBean vendorInputBeanObj) throws Exception;
    public VendorInputBean  getVendorVerifiedInputForm(VendorInputBean vendorInputBeanObj) throws Exception;

    public VendorInputBean getVendorInputForm(VendorInputBean vendorInputBeanObj) throws Exception;
    public VendorInputBean getVendorPsInputForm(VendorInputBean vendorInputBeanObj) throws Exception;
    public VendorInputBean getVendorNonpoInputForm(VendorInputBean vendorInputBeanObj) throws Exception;
   
    public VendorPrezData VendorApplFormTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception;
    public void GetPOLineInvTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception;
    public VendorPrezData VendorProjApplFormTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception;
    public VendorPrezData VendorNonpoApplFormTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception;

        public VendorBean getContactNumber(VendorBean vendorBeanObj) throws Exception;
       
        public void getVendorSmsStatus(VendorStatuBean vendorStatusBeanObj) throws Exception;
        public POBean getPlantCodeDetails(POBean POBeanObj) throws Exception;
        public POBean getOfficeCodeDetails(POBean POBeanObj) throws Exception;
        public POBean getForwardedToOfficeCodeDetails(POBean POBeanObj) throws Exception;
        public LinkedList getPlantDetails(POBean POBeanObj) throws Exception;
        public HOBean getHOSmsDetails(HOBean HOBeanObj) throws Exception;
        public HOSectionMatrixBean  getHOLegalSmsDetails(HOSectionMatrixBean HOSectionMatrixBeanObj)throws Exception;

        public VendorInputBean getInvoicedetails(VendorInputBean vendorInputBeanObj) throws Exception;
    
  //public VendorInputBean getHigherContactList(VendorInputBean vendorInputBeanObj) throws Exception;
    public LinkedList<VendorInputBean> getEscStatusSmsList(VendorInputBean VendorInputBeanObj) throws Exception;
       public LinkedList<VendorInputBean> getHigherContactList(VendorInputBean VendorInputBeanObj) throws Exception;
       public LinkedList<VendorInputBean> getAuthContactList(VendorInputBean VendorInputBeanObj) throws Exception;

 public LinkedList<VendorInputBean> getSmsTrackerList(VendorInputBean VendorInputBeanObj) throws Exception;
 public LinkedList<LegalInvoiceInputBean> getLegalSmsTrackerList(LegalInvoiceInputBean vendorInputBeanObj) throws Exception;
 public LinkedList<LegalInvoiceInputBean> getLegalEmailSmsTrackerList() throws Exception;
       public List getPOStatus(List lstErpToVitsFileFormat) throws Exception;
              public void getPOStatusProcedure() throws Exception;

       public List getPOLineStatus(List lstErpToVitsFileFormat) throws Exception;
       public void getPOLineStatusProcedure() throws Exception;

       public List getPSStatus(List lstErpToVitsFileFormat) throws Exception;
       public LinkedList<VendorInputBean> putInvoiceStatus( VendorInputBean vendorInputBeanObj) throws Exception;
       public List getVendorStatus(List listErpToVitsFileFormat) throws Exception;
        public void getVendorStatusProcedure() throws Exception;
        public LinkedList getSelectedLineStatus(LinkedList selectedpoLineDetails) throws Exception;
        
        public LinkedList getRetentionDetails(VendorBean bean) throws Exception;
        public VendorInputBean saveClaimedRetentionDetails(VendorInputBean vendorBean) throws Exception ;

    public LinkedList<VendorBean> putRetentionInvoiceStatus(VendorBean vendorBeanObj);
        public VendorInputBean getVendorRetentionInputForm(VendorInputBean vendorInputBeanObj) throws Exception;

    public List saveRetentionDetailsResponse(List<VendorInputBean> pobean) throws Exception;

    public void updateRetentionDetailsResponseProcedure()throws Exception;

    public LinkedList getPartialRetentionDetails(VendorBean vendorBean)throws Exception;

    public List saveLegalInvoiceStatus(List<LegalInvoiceBean> listErpToVitsFileFormat)throws Exception;

    public List getCourtCaseDetailsForVendor(LegalInvoiceBean legalInvoiceBean)throws Exception;

    public LegalInvoiceInputBean saveLegalInvoiceForm(LegalInvoiceInputBean legalInvoiceInputBean)throws Exception;

    public List<LegalInvoiceInputBean> getLegalInvoiceInputList(LegalInvoiceInputBean legalInvoiceInputBean)throws Exception;

    public List<FeeTypeBean> getLegalFeeType(FeeTypeBean bean)throws Exception;

    public List getLegalHierarchyLocation(OrganizationMasterBean organizationMasterBean)throws Exception;
    
     public void updateLegalCommunicationLog(LegalCommunicationBean legalCommunicationBean) throws Exception;
     
     public LinkedList getVendorLegalInvoiceFeeTypeDtlList( FeeTypeDtlsBean feeTypeDtlBeanObj) throws Exception;
     
     public FeeTypeDtlsBean saveLFeeTypeDtlsForm(FeeTypeDtlsBean feeTypeDtlsBean)throws Exception;
     
       public FeeTypeDtlsBean  feeTypeDtlDelHelper(FeeTypeDtlsBean feeTypeDtlsBean) throws Exception;
       
       public TemplateIdBean getTemplateDetails(TemplateIdBean TemplateBeanObj) throws Exception;
       
       

     
     
}
