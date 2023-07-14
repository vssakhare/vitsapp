/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.manager;

import in.emp.legal.bean.FeeTypeBean;
import in.emp.legal.bean.FeeTypeDtlsBean;
import in.emp.legal.bean.LegalInvoiceBean;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.legal.bean.OrganizationMasterBean;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.ClearingDocDetails;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.bean.VendorStatuBean;
import in.emp.vendor.dao.VendorDao;
import in.emp.vendor.dao.OracleVendorDao;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import in.emp.vendor.bean.HOBean;
import in.emp.vendor.bean.PoLineStatusBean;
import in.emp.vendor.bean.ProjBean;
import in.emp.vendor.bean.SMSResponseBean;
import in.emp.legal.bean.HOSectionMatrixBean;
import in.emp.legal.bean.LegalCommunicationBean;
import in.emp.legal.bean.LegalSummaryBean;

import in.emp.sms.bean.TemplateIdBean;
/**
 *
 * @author Prajakta
 */
public class VendorManager implements VendorDelegate {

    private static String CLASS_NAME = VendorManager.class.getName();
    private static Logger logger = Logger.getLogger(VendorManager.class);

    public VendorManager() {
    }
    
    public POBean getPOLocationList(POBean poBeanObj) {
        POBean poBeanObj1 = new POBean();
        VendorDao vendorDaoObj = new OracleVendorDao();
         try {
            logger.log(Level.INFO, " VendorManager :: getPOLocationList() :: method called");
            poBeanObj1=(POBean)vendorDaoObj.getPOLocationList(poBeanObj);
         }
         catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOLocationList() :: Exception :: " + ex);

        }

        return poBeanObj1;
               
    }
       public POBean getPOLocation(POBean poBeanObj) {
        POBean poBeanObj1 = new POBean();
        VendorDao vendorDaoObj = new OracleVendorDao();
         try {
            logger.log(Level.INFO, " VendorManager :: getPOLocationList() :: method called");
            poBeanObj1=(POBean)vendorDaoObj.getPOLocation(poBeanObj);
         }
         catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOLocationList() :: Exception :: " + ex);

        }

        return poBeanObj1;
               
    }
     public ProjBean getProjLocationList(ProjBean projBeanObj) {
        ProjBean projBeanObj1 = new ProjBean();
        VendorDao vendorDaoObj = new OracleVendorDao();
         try {
            logger.log(Level.INFO, " VendorManager :: getProjLocationList() :: method called");
            projBeanObj1=(ProjBean)vendorDaoObj.getProjLocationList(projBeanObj);
         }
         catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getProjLocationList() :: Exception :: " + ex);

        }

        return projBeanObj1;
               
    }
     public POBean getPODetails(POBean poBeanObj) {
        POBean poBeanObj1 = new POBean();
        VendorDao vendorDaoObj = new OracleVendorDao();
         try {
            logger.log(Level.INFO, " VendorManager :: getPODetails() :: method called");
            poBeanObj1=(POBean)vendorDaoObj.getPODetails(poBeanObj);
         }
         catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPODetails() :: Exception :: " + ex);

        }

        return poBeanObj1;
               
    }
      public SMSResponseBean getSmsResponseTrackerTxnHelper(SMSResponseBean smsresponsebeanbeanobj) {
       
        VendorDao vendorDaoObj = new OracleVendorDao();
         try {
            logger.log(Level.INFO, " VendorManager :: getSmsResponseTrackerTxnHelper() :: method called");
            smsresponsebeanbeanobj=(SMSResponseBean)vendorDaoObj.getSmsResponseTrackerTxnHelper(smsresponsebeanbeanobj);
         }
         catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getSmsResponseTrackerTxnHelper() :: Exception :: " + ex);

        }

        return smsresponsebeanbeanobj;
               
    }
     public ProjBean getProjDetails(ProjBean projBeanObj) {
        ProjBean projBeanObj1 = new ProjBean();
        VendorDao vendorDaoObj = new OracleVendorDao();
         try {
            logger.log(Level.INFO, " VendorManager :: getProjDetails() :: method called");
            projBeanObj1=(ProjBean)vendorDaoObj.getProjDetails(projBeanObj);
         }
         catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getProjDetails() :: Exception :: " + ex);

        }

        return projBeanObj1;
               
    }
        public ProjBean getProjectDetails(ProjBean projBeanObj) {
        ProjBean projBeanObj1 = new ProjBean();
        VendorDao vendorDaoObj = new OracleVendorDao();
         try {
            logger.log(Level.INFO, " VendorManager :: getProjectDetails() :: method called");
            projBeanObj1=(ProjBean)vendorDaoObj.getProjectDetails(projBeanObj);
         }
         catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getProjectDetails() :: Exception :: " + ex);

        }

        return projBeanObj1;
               
    }
      public POBean getPOInvDetails(POBean poBeanObj) {
        POBean poBeanObj1 = new POBean();
        VendorDao vendorDaoObj = new OracleVendorDao();
         try {
            logger.log(Level.INFO, " VendorManager :: getPOInvDetails() :: method called");
            poBeanObj1=(POBean)vendorDaoObj.getPOInvDetails(poBeanObj);
         }
         catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOInvDetails() :: Exception :: " + ex);

        }

        return poBeanObj1;
               
    }
     public ProjBean getProjInvDetails(ProjBean projBeanObj) {
        ProjBean projBeanObj1 = new ProjBean();
        VendorDao vendorDaoObj = new OracleVendorDao();
         try {
            logger.log(Level.INFO, " VendorManager :: getProjInvDetails() :: method called");
            projBeanObj1=(ProjBean)vendorDaoObj.getProjInvDetails(projBeanObj);
         }
         catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getProjInvDetails() :: Exception :: " + ex);

        }

        return projBeanObj1;
               
    }
    public VendorPrezData getPOList(POBean poBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
       
        LinkedList POList = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getPOList() :: method called");

//            vendorPrezDataObj.setVendorBean(poBeanObj);
          
            POList =  (LinkedList) vendorDaoObj.getPOList(poBeanObj);
            vendorPrezDataObj.setPOList(POList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
     public LinkedList getPONumberList(POBean poBeanObj) {
      
        VendorDao vendorDaoObj = new OracleVendorDao();
       
        LinkedList POList = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getPONumberList() :: method called");


          
            POList =  (LinkedList) vendorDaoObj.getPOList(poBeanObj);
          

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPONumberList() :: Exception :: " + ex);

        }
        return POList;
    }
    public VendorPrezData getzoneList(POBean poBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
       
        LinkedList zoneList = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getzoneList() :: method called");
         zoneList =  (LinkedList) vendorDaoObj.getzoneList(poBeanObj);
            vendorPrezDataObj.setZoneList(zoneList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getzoneList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
    public VendorPrezData getClearingDocDetails(ClearingDocDetails clearingDocDetailsObj) {
        VendorDao vendorDaoObj = new OracleVendorDao();
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        LinkedList clearingDocDetails = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getClearingDocDetails() :: method called");
            clearingDocDetails = (LinkedList) vendorDaoObj.getClearingDocDetails(clearingDocDetailsObj);
            vendorPrezDataObj.setClearingDocDetails(clearingDocDetails);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getClearingDocDetails() :: Exception :: " + ex);
        }
        return vendorPrezDataObj;
    }
    public LinkedList getSummaryListDetails(LegalSummaryBean legalSummaryBeanObj) {
        VendorDao vendorDaoObj = new OracleVendorDao();
       LinkedList summaryListDetails = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getSummaryListDetails() :: method called");
            summaryListDetails = (LinkedList) vendorDaoObj.getSummaryListDetails(legalSummaryBeanObj);
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getSummaryListDetails() :: Exception :: " + ex);
        }
        return summaryListDetails;
    }
       public LinkedList getPOLineDetails(PoLineStatusBean poLineStatusBeanObj) {
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList poLineDetails = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getPOLineDetails() :: method called");
            poLineDetails = (LinkedList) vendorDaoObj.getPOLineDetails(poLineStatusBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOLineDetails() :: Exception :: " + ex);
        }
        return poLineDetails;
    }
    public LinkedList getcircleList(POBean poBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
       
        LinkedList circleList = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getcircleList() :: method called");

          
            circleList =  (LinkedList) vendorDaoObj.getcircleList(poBeanObj);
           // vendorPrezDataObj.setCircleList(circleList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getcircleList() :: Exception :: " + ex);

        }
        return circleList;
    }
    public LinkedList getSubmitAtList(PoLineStatusBean poLinestatusbeanobj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
       
        LinkedList submitAtList = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getSubmitAtList() :: method called");

          
            submitAtList =  (LinkedList) vendorDaoObj.getSubmitAtList(poLinestatusbeanobj);
          

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getSubmitAtList() :: Exception :: " + ex);

        }
        return submitAtList;
    }
    public VendorPrezData getdivisionList(POBean poBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
       
        LinkedList divisionList = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getdivisionList() :: method called");

//            vendorPrezDataObj.setVendorBean(poBeanObj);
          
            divisionList =  (LinkedList) vendorDaoObj.getdivisionList(poBeanObj);
            vendorPrezDataObj.setDivisionList(divisionList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getdivisionList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
    public VendorPrezData getsubdivisionList(POBean poBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
       
        LinkedList subdivisionList = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getdivisionList() :: method called");

//            vendorPrezDataObj.setVendorBean(poBeanObj);
          
            subdivisionList =  (LinkedList) vendorDaoObj.getsubdivisionList(poBeanObj);
            vendorPrezDataObj.setSubdivisionList(subdivisionList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getdivisionList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
   public VendorPrezData getProjList(ProjBean projBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
       
        LinkedList ProjList = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getProjList() :: method called");
            ProjList =  (LinkedList) vendorDaoObj.getProjList(projBeanObj);
            vendorPrezDataObj.setProjList(ProjList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
     public VendorPrezData getVendorDtlList(POBean poBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
       
        LinkedList VendorDtlList = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getVendorDtlList() :: method called");

        
            VendorDtlList =  (LinkedList) vendorDaoObj.getVendorDtlList(poBeanObj);  
            vendorPrezDataObj.setVendorDtlList(VendorDtlList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorDtlList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
        
    public VendorPrezData getlocationList(POBean poBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
       
        LinkedList LocationList = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getlocationList() :: method called");

          
            LocationList =  (LinkedList) vendorDaoObj.getlocationList(poBeanObj);  
            vendorPrezDataObj.setLocationList(LocationList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getlocationList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
    
      public VendorPrezData getVendorinvList(POBean poBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
       
        LinkedList VendorInvList = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getVendorInvList() :: method called");

          
            VendorInvList =  (LinkedList) vendorDaoObj.getVendorinvList(poBeanObj);  
            vendorPrezDataObj.setVendorInvList(VendorInvList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorInvList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
    
    
    
    public VendorPrezData getInvoiceList(VendorBean vendorBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList vendorInvoiceList = new LinkedList();
       
        try {
            logger.log(Level.INFO, " VendorManager :: getInvoiceList() :: method called");

           // vendorPrezDataObj.setVendorBean(vendorBeanObj); 
            vendorInvoiceList = (LinkedList) vendorDaoObj.getInvoiceList(vendorBeanObj);
            vendorPrezDataObj.setVendorInvoiceList(vendorInvoiceList);
//             vendorBeanObj = vendorDaoObj.getInvoiceList(vendorBeanObj);
//             vendorPrezDataObj.setVendorBean(vendorBeanObj);
           // POList = = (LinkedList) vendorDaoObj.getPOList(vendorBeanObj);
            

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getInvoiceList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
    
     public  void getVendorSmsStatus(VendorStatuBean vendorStatusBeanObj){
          VendorDao vendorDaoObj = new OracleVendorDao();
          try {
            logger.log(Level.INFO, " VendorManager :: getVendorSmsStatus() :: method called");
            vendorDaoObj.getVendorSmsStatus(vendorStatusBeanObj);
          } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getContactNumber() :: Exception :: " + ex);

        }
     }
     
     
       public void updateLegalCommunicationLog(LegalCommunicationBean legalCommunicationBean)
               {
          VendorDao vendorDaoObj = new OracleVendorDao();
          try {
            logger.log(Level.INFO, " VendorManager :: getVendorSmsStatus() :: method called");
            vendorDaoObj.updateLegalCommunicationLog(legalCommunicationBean);
          } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getContactNumber() :: Exception :: " + ex);

        }
     }
   
   public VendorBean getContactNumber(VendorBean vendorBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList vendorContactList = new LinkedList();
       
        try {
            logger.log(Level.INFO, " VendorManager :: getContactNumber() :: method called");

             vendorBeanObj = vendorDaoObj.getContactNumber(vendorBeanObj);
             
            //vendorContactList = (LinkedList) vendorDaoObj.getContactNumber(vendorBeanObj);
            //vendorPrezDataObj.setVendorContactList(vendorContactList); 
             
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getContactNumber() :: Exception :: " + ex);

        }
        return vendorBeanObj;
    }
    public LinkedList<VendorInputBean> getHigherContactList(VendorInputBean vendorInputBeanObj) throws Exception {
       VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList<VendorInputBean> fileList = new LinkedList<VendorInputBean>();
        try {
            logger.log(Level.INFO, " VendorManager :: getHigherContactList() :: method called");

            fileList = vendorDaoObj.getHigherContactList(vendorInputBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getHigherContactList() :: Exception :: " + ex);
        }
        return fileList;
    }   
      public LinkedList<VendorInputBean> getAuthContactList(VendorInputBean vendorInputBeanObj) throws Exception {
       VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList<VendorInputBean> fileList = new LinkedList<VendorInputBean>();
        try {
            logger.log(Level.INFO, " VendorManager :: getAuthContactList() :: method called");

            fileList = vendorDaoObj.getAuthContactList(vendorInputBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getAuthContactList() :: Exception :: " + ex);
        }
        return fileList;
    } 
        public LinkedList<VendorInputBean> getEscStatusSmsList(VendorInputBean vendorInputBeanObj) throws Exception {
       VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList<VendorInputBean> fileList = new LinkedList<VendorInputBean>();
        try {
            logger.log(Level.INFO, " VendorManager :: getEscStatusSmsList() :: method called");

            fileList = vendorDaoObj.getEscStatusSmsList(vendorInputBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getEscStatusSmsList() :: Exception :: " + ex);
        }
        return fileList;
    }   
              public LinkedList<VendorInputBean> getSmsTrackerList(VendorInputBean vendorInputBeanObj) throws Exception {
       VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList<VendorInputBean> fileList = new LinkedList<VendorInputBean>();
        try {
            logger.log(Level.INFO, " VendorManager :: getSmsTrackerList() :: method called");

            fileList = vendorDaoObj.getSmsTrackerList(vendorInputBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getSmsTrackerList() :: Exception :: " + ex);
        }
        return fileList;
    }
              
     public LinkedList<LegalInvoiceInputBean> getLegalSmsTrackerList(LegalInvoiceInputBean legalInvoiceInputBeanObj) throws Exception {
       VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList<LegalInvoiceInputBean> fileList = new LinkedList<LegalInvoiceInputBean>();
        try {
            logger.log(Level.INFO, " VendorManager :: getSmsTrackerList() :: method called");

            fileList = vendorDaoObj.getLegalSmsTrackerList(legalInvoiceInputBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getSmsTrackerList() :: Exception :: " + ex);
        }
        return fileList;
    }
   public LinkedList<LegalInvoiceInputBean> getLegalEmailSmsTrackerList() throws Exception {
       VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList<LegalInvoiceInputBean> fileList = new LinkedList<LegalInvoiceInputBean>();
        try {
            logger.log(Level.INFO, " VendorManager :: getSmsTrackerList() :: method called");

            fileList = vendorDaoObj.getLegalEmailSmsTrackerList();
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getSmsTrackerList() :: Exception :: " + ex);
        }
        return fileList;
    }
   public LinkedList<LegalInvoiceInputBean> getZhrtLegalFeeReport() throws Exception {
       VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList<LegalInvoiceInputBean> fileList = new LinkedList<LegalInvoiceInputBean>();
        try {
            logger.log(Level.INFO, " VendorManager :: getZhrtLegalFeeReport() :: method called");

            fileList = vendorDaoObj.getZhrtLegalFeeReport();
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getZhrtLegalFeeReport() :: Exception :: " + ex);
        }
        return fileList;
    }
   public VendorInputBean getInvoicedetails(VendorInputBean vendorInputBeanObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();

        try {
            logger.log(Level.INFO, " VendorManager :: getInvoicedetails() :: method called");

          
                vendorInputBeanObj = vendorDaoObj.getInvoicedetails(vendorInputBeanObj);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getInvoicedetails() :: Exception :: " + ex);
        }
        return vendorInputBeanObj;
    }
   
    public LinkedList<VendorInputBean> putInvoiceStatus( VendorInputBean vendorInputBeanObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList<VendorInputBean> list = new LinkedList<VendorInputBean>();
        

        try {
            logger.log(Level.INFO, " VendorManager :: putInvoiceStatus() :: method called");

          
                list=(LinkedList<VendorInputBean>)vendorDaoObj.putInvoiceStatus(vendorInputBeanObj);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: putInvoiceStatus() :: Exception :: " + ex);
        }
       return list;
    }
     public List getPOStatus(List lstErpToVitsFileFormat) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        List list;
        

        try {
            logger.log(Level.INFO, " VendorManager :: getPOStatus() :: method called");

          
                list=vendorDaoObj.getPOStatus(lstErpToVitsFileFormat);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOStatus() :: Exception :: " + ex);
        }
       return lstErpToVitsFileFormat;
    }
       public void getPOStatusProcedure() throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        try {
            logger.log(Level.INFO, " VendorManager :: getPOStatusProcedure() :: method called");
            vendorDaoObj.getPOStatusProcedure();
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOStatusProcedure() :: Exception :: " + ex);
        }
       
    }
      public LinkedList getSelectedLineStatus(LinkedList selectedpoLineDetails) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList list;
        

        try {
            logger.log(Level.INFO, " VendorManager :: getSelectedLineStatus() :: method called");

          
                list=vendorDaoObj.getSelectedLineStatus(selectedpoLineDetails);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getSelectedLineStatus() :: Exception :: " + ex);
        }
       return selectedpoLineDetails;
    }
       public List getPOLineStatus(List lstErpToVitsFileFormat) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        List list;
        

        try {
            logger.log(Level.INFO, " VendorManager :: getPOLineStatus() :: method called");

          
                list=vendorDaoObj.getPOLineStatus(lstErpToVitsFileFormat);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOLineStatus() :: Exception :: " + ex);
        }
       return lstErpToVitsFileFormat;
    }
        public void getPOLineStatusProcedure() throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        try {
            logger.log(Level.INFO, " VendorManager :: getPOLineStatusProcedure() :: method called");
            vendorDaoObj.getPOLineStatusProcedure();
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOLineStatusProcedure() :: Exception :: " + ex);
        }
       
    }
      public List getPSStatus(List lstErpToVitsFileFormat) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        List list;
        

        try {
            logger.log(Level.INFO, " VendorManager :: getPOStatus() :: method called");

          
                list=vendorDaoObj.getPSStatus(lstErpToVitsFileFormat);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOStatus() :: Exception :: " + ex);
        }
       return lstErpToVitsFileFormat;
    }
   public List getVendorStatus(List listErpToVitsFileFormat) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        List list;
        

        try {
            logger.log(Level.INFO, " VendorManager :: getVendorStatus() :: method called");

          
                list=vendorDaoObj.getVendorStatus(listErpToVitsFileFormat);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorStatus() :: Exception :: " + ex);
        }
   return listErpToVitsFileFormat;
    }
        public void getVendorStatusProcedure() throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        try {
            logger.log(Level.INFO, " VendorManager :: getVendorStatusProcedure() :: method called");
            vendorDaoObj.getVendorStatusProcedure();
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorStatusProcedure() :: Exception :: " + ex);
        }
       
    }
   public HOBean getHOSmsDetails(HOBean HOBeanObj) {
       
        VendorDao vendorDaoObj = new OracleVendorDao(); 
        try {
            logger.log(Level.INFO, " VendorManager :: getHOSmsDetails() :: method called");

             HOBeanObj = vendorDaoObj.getHOSmsDetails(HOBeanObj);
             
            //vendorContactList = (LinkedList) vendorDaoObj.getContactNumber(vendorBeanObj);
            //vendorPrezDataObj.setVendorContactList(vendorContactList); 
             
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getHOSmsDetails() :: Exception :: " + ex);

        }
        return HOBeanObj;
    }
   public POBean getOfficeCodeDetails(POBean POBeanObj) {
    
        VendorDao vendorDaoObj = new OracleVendorDao();
     
       
        try {
            logger.log(Level.INFO, " VendorManager :: getOfficeCodeDetails() :: method called");

             POBeanObj = vendorDaoObj.getOfficeCodeDetails(POBeanObj);
             
             
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getOfficeCodeDetails() :: Exception :: " + ex);

        }
        return POBeanObj;
    }
    public POBean getPlantCodeDetails(POBean POBeanObj) {
    
        VendorDao vendorDaoObj = new OracleVendorDao();
     
       
        try {
            logger.log(Level.INFO, " VendorManager :: getPlantCodeDetails() :: method called");

             POBeanObj = vendorDaoObj.getPlantCodeDetails(POBeanObj);
             
             
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPlantCodeDetails() :: Exception :: " + ex);

        }
        return POBeanObj;
    }
    public POBean getForwardedToOfficeCodeDetails(POBean POBeanObj) {
    
        VendorDao vendorDaoObj = new OracleVendorDao();
         try {
            logger.log(Level.INFO, " VendorManager :: getForwardedToOfficeCodeDetails() :: method called");

             POBeanObj = vendorDaoObj.getForwardedToOfficeCodeDetails(POBeanObj);
           
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getForwardedToOfficeCodeDetails() :: Exception :: " + ex);
}
        return POBeanObj;
    }
    public LinkedList getPlantDetails(POBean POBeanObj) {
    
        VendorDao vendorDaoObj = new OracleVendorDao();
         LinkedList PlantList = new LinkedList();
         try {
            logger.log(Level.INFO, " VendorManager :: getPlantDetails() :: method called");

             PlantList = (LinkedList)vendorDaoObj.getPlantDetails(POBeanObj);
           
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPlantDetails() :: Exception :: " + ex);
}
        return PlantList;
    }
     public VendorPrezData getVendorList(VendorBean vendorBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList vendorList = new LinkedList();
       
        try {
            logger.log(Level.INFO, " VendorManager :: getVendorList() :: method called");

            vendorPrezDataObj.setVendorBean(vendorBeanObj);
            vendorList = (LinkedList) vendorDaoObj.getVendorList(vendorBeanObj);
           // POList = = (LinkedList) vendorDaoObj.getPOList(vendorBeanObj);
            vendorPrezDataObj.setVendorList(vendorList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
    public VendorPrezData getVendorVerifiedList(VendorBean vendorBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList vendorList = new LinkedList();
       
        try {
            logger.log(Level.INFO, " VendorManager :: getVendorList() :: method called");

            vendorPrezDataObj.setVendorBean(vendorBeanObj);
            vendorList = (LinkedList) vendorDaoObj.getVendorVerifiedList(vendorBeanObj);
            vendorPrezDataObj.setVendorList(vendorList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
    public VendorBean getVendorApplForm(VendorBean vendorBeanObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();

        try {
            logger.log(Level.INFO, " VendorManager :: getVendorForm() :: method called");
           if(vendorBeanObj.getUserType().equals("Vendor")) {
              if (vendorBeanObj.getInvoiceNumber()!= null){
                vendorBeanObj = vendorDaoObj.getVendorFormData(vendorBeanObj);
              } 
           }
           
            if(vendorBeanObj.getUserType().equals("Emp")) {
               if ((vendorBeanObj.getSesNum()!= null) && (vendorBeanObj.getPOType()!= null)) {
                vendorBeanObj = vendorDaoObj.getVendorFormData(vendorBeanObj);
             } 
           }
            
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorForm() :: Exception :: " + ex);
        }
        return vendorBeanObj;
    }
     public VendorBean getVendorPsApplForm(VendorBean vendorBeanObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();

        try {
            logger.log(Level.INFO, " VendorManager :: getVendorForm() :: method called");
           
              if (vendorBeanObj.getMsedclInvoiceNumber()!= null){
                vendorBeanObj = vendorDaoObj.getVendorPsFormData(vendorBeanObj);
              }
           
           
            
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorForm() :: Exception :: " + ex);
        }
        return vendorBeanObj;
    }
     public VendorBean getEmpPsApplForm(VendorBean vendorBeanObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();

        try {
            logger.log(Level.INFO, " VendorManager :: getVendorForm() :: method called");
           
              if (vendorBeanObj.getVendorInvoiceNumber()!= null){
                vendorBeanObj = vendorDaoObj.getEmpPsFormData(vendorBeanObj);
              }
           
           
            
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorForm() :: Exception :: " + ex);
        }
        return vendorBeanObj;
    }
    
    
     public VendorPrezData getVendorInputList(VendorInputBean vendorInputBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList vendorList = new LinkedList();
       
        try {
            logger.log(Level.INFO, " VendorManager :: getVendorInputList() :: method called");

            vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
            vendorList = (LinkedList) vendorDaoObj.getVendorInputList(vendorInputBeanObj);
           // POList = = (LinkedList) vendorDaoObj.getPOList(vendorBeanObj);
            vendorPrezDataObj.setVendorInputList(vendorList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorInputList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
      public VendorInputBean getVendorVerifiedInputForm(VendorInputBean vendorInputBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
        try {
            logger.log(Level.INFO, " VendorManager :: getVendorInputList() :: method called");

            vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
            vendorInputBeanObj =  vendorDaoObj.getVendorVerifiedInputForm(vendorInputBeanObj);
           // POList = = (LinkedList) vendorDaoObj.getPOList(vendorBeanObj);
          //  vendorPrezDataObj.setVendorInputBean(vendorList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorInputList() :: Exception :: " + ex);

        }
        return vendorInputBeanObj;
    }
    public VendorInputBean getVendorInputForm(VendorInputBean vendorInputBeanObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();

        try {
            logger.log(Level.INFO, " VendorManager :: getVendorForm() :: method called");

           if (vendorInputBeanObj.getApplId()!= null){
                vendorInputBeanObj = vendorDaoObj.getVendorInputFormData(vendorInputBeanObj);
           } 
           else
           {
                vendorInputBeanObj = vendorDaoObj.getVendorInputForm(vendorInputBeanObj);
           }
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorForm() :: Exception :: " + ex);
        }
        return vendorInputBeanObj;
    }
    public VendorInputBean getVendorPsInputForm(VendorInputBean vendorInputBeanObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();

        try {
            logger.log(Level.INFO, " VendorManager :: getVendorForm() :: method called");

           if (vendorInputBeanObj.getApplId()!= null){
                vendorInputBeanObj = vendorDaoObj.getVendorPsInputFormData(vendorInputBeanObj);
           } 
           else
           {
                vendorInputBeanObj = vendorDaoObj.getVendorInputForm(vendorInputBeanObj);
           }
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorForm() :: Exception :: " + ex);
        }
        return vendorInputBeanObj;
    }
     public VendorInputBean getVendorNonpoInputForm(VendorInputBean vendorInputBeanObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();

        try {
            logger.log(Level.INFO, " VendorManager :: getVendorForm() :: method called");

           if (vendorInputBeanObj.getApplId()!= null){
                vendorInputBeanObj = vendorDaoObj.getVendorNonpoInputFormData(vendorInputBeanObj);
           } 
           else
           {
                vendorInputBeanObj = vendorDaoObj.getVendorInputForm(vendorInputBeanObj);
           }
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorForm() :: Exception :: " + ex);
        }
        return vendorInputBeanObj;
    }
     public void GetPOLineInvTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        try {
            logger.log(Level.INFO, " VendorManager :: GetPOLineInvTxnHelper() :: method called");
           
            vendorDaoObj.GetPOLineInvTxnHelper(vendorPrezDataObj);
           // return vendorPrezDataObj;

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: GetPOLineInvTxnHelper() :: Exception :: " + ex);
            throw ex;
        }
        
    }
     
    public VendorPrezData VendorApplFormTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        try {
            logger.log(Level.INFO, " VendorManager :: VendorApplFormTxnHelper() :: method called");
           
            vendorDaoObj.VendorApplFormTxnHelper(vendorPrezDataObj);
            return vendorPrezDataObj;

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: VendorApplFormTxnHelper() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }
        
    }
     public VendorPrezData VendorProjApplFormTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        try {
            logger.log(Level.INFO, " VendorManager :: VendorProjApplFormTxnHelper() :: method called");
           
            vendorDaoObj.VendorProjApplFormTxnHelper(vendorPrezDataObj);
            return vendorPrezDataObj;

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: VendorProjApplFormTxnHelper() :: Exception :: " + ex);
            throw ex;
        }
        
    }
     public VendorPrezData VendorNonpoApplFormTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        try {
            logger.log(Level.INFO, " VendorManager :: VendorNonpoApplFormTxnHelper() :: method called");
           
            vendorDaoObj.VendorNonpoApplFormTxnHelper(vendorPrezDataObj);
            return vendorPrezDataObj;

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: VendorNonpoApplFormTxnHelper() :: Exception :: " + ex);
            throw ex;
        }
        
    }
//        
// public VendorBean VendorApplTxnHelper(VendorBean vendorFormBeanObj) {
//        VendorDao vendorlistDaoObj = new OracleVendorDao();
//        try {
//            logger.log(Level.INFO, " VendorManager :: VendorApplTxnHelper() :: method called");
//
//            vendorFormBeanObj = vendorlistDaoObj.VendorApplTxnHelper(vendorFormBeanObj);
//
//        } catch (Exception ex) {
//            logger.log(Level.ERROR, " VendorManager :: VendorApplTxnHelper() :: Exception :: " + ex);
//        }
//        return vendorFormBeanObj;
//    }
//   
    
     public VendorPrezData getSummaryList(VendorBean vendorBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList summaryList = new LinkedList();
       
        try {
            logger.log(Level.INFO, " VendorManager :: getSummaryList() :: method called");

          
            summaryList = (LinkedList) vendorDaoObj.getSummaryList(vendorBeanObj);
          
            vendorPrezDataObj.setSummaryList(summaryList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getSummaryList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
     

       public LinkedList getLegalSummaryList(LegalInvoiceInputBean legalInvoiceInputBean) {
    
   
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList summaryList = new LinkedList();
       
        try {
            logger.log(Level.INFO, " VendorManager :: getSummaryList() :: method called");

          
            summaryList = (LinkedList) vendorDaoObj.getLegalSummaryList(legalInvoiceInputBean);
          
   

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getSummaryList() :: Exception :: " + ex);

        }
        return summaryList;
    
    
    
}
     
     

 public VendorPrezData getTableList(VendorInputBean vendorInputBeanObj) {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList vendorList = new LinkedList();
       
        try {
            logger.log(Level.INFO, " VendorManager :: getSummaryList() :: method called");

          
            vendorList = (LinkedList) vendorDaoObj.getTableList( vendorInputBeanObj);
          vendorPrezDataObj.setVendorInputList(vendorList);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getSummaryList() :: Exception :: " + ex);

        }
        return vendorPrezDataObj;
    }
 
 public LinkedList getRetentionDetails(VendorBean bean) throws Exception{
     VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList list = null;
        

        try {
            logger.log(Level.INFO, " VendorManager :: getSelectedLineStatus() :: method called");

          
                list=vendorDaoObj.getRetentionDetails(bean);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getSelectedLineStatus() :: Exception :: " + ex);
        }
       return list;
 }

    @Override
    public VendorInputBean saveClaimedRetentionDetails(VendorInputBean vendorBean) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        VendorInputBean bean=null;
         try {
            logger.log(Level.INFO, " VendorManager :: getSelectedLineStatus() :: method called");

          
                bean=(VendorInputBean)vendorDaoObj.saveClaimedRetentionDetails(vendorBean);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getSelectedLineStatus() :: Exception :: " + ex);
        }
         return bean;
    }
    
    public LinkedList<VendorBean> putRetentionInvoiceStatus(VendorBean vendorBeanObj) {
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList<VendorBean> list = new LinkedList<VendorBean>();
        

        try {
            logger.log(Level.INFO, " VendorManager :: putInvoiceStatus() :: method called");

          
                list=(LinkedList<VendorBean>)vendorDaoObj.putRetentionInvoiceStatus(vendorBeanObj);
                System.out.println("size::"+list.size());
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: putInvoiceStatus() :: Exception :: " + ex);
        }
       return list;
    }

    @Override
    public VendorInputBean getVendorRetentionInputForm(VendorInputBean vendorInputBeanObj) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();

        try {
            logger.log(Level.INFO, " VendorManager :: getVendorForm() :: method called");
           if (vendorInputBeanObj.getApplId()!= null){
                vendorInputBeanObj = vendorDaoObj.getVendorRetentionInputFormData(vendorInputBeanObj);
           } 
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorForm() :: Exception :: " + ex);
        }
        return vendorInputBeanObj;
    }
    @Override
    public List saveRetentionDetailsResponse(List<VendorInputBean> listErpToVitsFileFormat) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        List list;
        

        try {
            logger.log(Level.INFO, " VendorManager :: saveRetentionDetailsResponse() :: method called");

          
                list=vendorDaoObj.saveRetentionDetailsResponse(listErpToVitsFileFormat);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: saveRetentionDetailsResponse() :: Exception :: " + ex);
        }
   return listErpToVitsFileFormat;
    }
     public void saveZhrtLegalFeeTaxDetails(List<LegalInvoiceInputBean> legalInvoiceInputList) throws Exception {
        VendorDao vendorDaoObj = new OracleVendorDao();
        
        

        try {
            logger.log(Level.INFO, " VendorManager :: saveZhrtLegalFeeTaxDetails() :: method called");

          
                vendorDaoObj.saveZhrtLegalFeeTaxDetails(legalInvoiceInputList);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: saveZhrtLegalFeeTaxDetails() :: Exception :: " + ex);
        }

    }
   public void updateRetentionDetailsResponseProcedure()throws Exception{
       VendorDao vendorDaoObj = new OracleVendorDao();
        try {
            logger.log(Level.INFO, " VendorManager :: updateRetentionDetailsResponseProcedure() :: method called");
            vendorDaoObj.updateRetentionDetailsResponseProcedure();
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: updateRetentionDetailsResponseProcedure() :: Exception :: " + ex);
        }
       
   }
    public void updateZhrtLegalFeeReportProcedure()throws Exception{
       VendorDao vendorDaoObj = new OracleVendorDao();
        try {
            logger.log(Level.INFO, " VendorManager :: updateZhrtLegalFeeReportProcedure() :: method called");
            vendorDaoObj.updateZhrtLegalFeeReportProcedure();
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: updateZhrtLegalFeeReportProcedure() :: Exception :: " + ex);
        }
       
   }
   public LinkedList getPartialRetentionDetails(VendorBean vendorBean)throws Exception{
       VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList list = null;
        

        try {
            logger.log(Level.INFO, " VendorManager :: getPartialRetentionDetails() :: method called");

          
                list=vendorDaoObj.getPartialRetentionDetails(vendorBean);
           
        }catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.ERROR, " VendorManager :: getPartialRetentionDetails() :: Exception :: " + ex);
        }
       return list;
   }

    @Override
    public List saveLegalInvoiceStatus(List<LegalInvoiceBean> listErpToVitsFileFormat) throws Exception{
       VendorDao vendorDaoObj = new OracleVendorDao();
        List list;
        

        try {
            logger.log(Level.INFO, " VendorManager :: saveRetentionDetailsResponse() :: method called");

          
                list=vendorDaoObj.saveLegalInvoiceStatus(listErpToVitsFileFormat);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: saveRetentionDetailsResponse() :: Exception :: " + ex);
        }
   return listErpToVitsFileFormat;
    }
    
    @Override
    public List getCourtCaseDetailsForVendor(LegalInvoiceBean legalInvoiceBean){
        VendorDao vendorDaoObj = new OracleVendorDao();
        List list = null;
        

        try {
            logger.log(Level.INFO, " VendorManager :: saveRetentionDetailsResponse() :: method called");

          
                list=vendorDaoObj.getCourtCaseDetailsForVendor(legalInvoiceBean);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: saveRetentionDetailsResponse() :: Exception :: " + ex);
        }
   return list;
    }
     @Override
    public LegalInvoiceInputBean saveLegalInvoiceForm(LegalInvoiceInputBean legalInvoiceInputBean)throws Exception{
        VendorDao vendorDaoObj = new OracleVendorDao();
        LegalInvoiceInputBean bean=null;
         try {
            logger.log(Level.INFO, " VendorManager :: saveLegalInvoiceForm() :: method called");
                bean=(LegalInvoiceInputBean)vendorDaoObj.saveLegalInvoiceForm(legalInvoiceInputBean);
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: saveLegalInvoiceForm() :: Exception :: " + ex);
        }
         return bean;
    }
    
     @Override
     public FeeTypeDtlsBean saveLFeeTypeDtlsForm(FeeTypeDtlsBean feeTypeDtlsBean)throws Exception{
        VendorDao vendorDaoObj = new OracleVendorDao();
        FeeTypeDtlsBean bean=null;
         try {
            logger.log(Level.INFO, " VendorManager :: saveLFeeTypeDtlsForm() :: method called");
                bean=(FeeTypeDtlsBean)vendorDaoObj.saveLFeeTypeDtlsForm(feeTypeDtlsBean);
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: saveLegalInvoiceForm() :: Exception :: " + ex);
        }
         return bean;
    }
    
    
    
     @Override
    public List<LegalInvoiceInputBean> getLegalInvoiceInputList(LegalInvoiceInputBean legalInvoiceInputBean)throws Exception{
        VendorDao vendorDaoObj = new OracleVendorDao();
        List list = null;
        

        try {
            logger.log(Level.INFO, " VendorManager :: getLegalInvoiceInputList() :: method called");

          
                list=vendorDaoObj.getLegalInvoiceInputList(legalInvoiceInputBean);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getLegalInvoiceInputList() :: Exception :: " + ex);
        }
   return list;
    }
    @Override
    public List<FeeTypeBean> getLegalFeeType(FeeTypeBean bean)throws Exception{
         List list = null;
         VendorDao vendorDaoObj = new OracleVendorDao();

        try {
            logger.log(Level.INFO, " VendorManager :: getLegalFeeType() :: method called");

          
                list=vendorDaoObj.getLegalFeeType(bean);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getLegalFeeType() :: Exception :: " + ex);
        }
   return list;
    }
    @Override
    public List getLegalHierarchyLocation(OrganizationMasterBean organizationMasterBean)throws Exception{
        List list = null;
         VendorDao vendorDaoObj = new OracleVendorDao();

        try {
            logger.log(Level.INFO, " VendorManager :: getLegalHierarchyLocation() :: method called");
                list=vendorDaoObj.getLegalHierarchyLocation(organizationMasterBean);
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getLegalHierarchyLocation() :: Exception :: " + ex);
        }
   return list;
    }
    

    public HOSectionMatrixBean  getHOLegalSmsDetails(HOSectionMatrixBean HOSectionMatrixBeanObj) {
       
        VendorDao vendorDaoObj = new OracleVendorDao(); 
        try {
            logger.log(Level.INFO, " VendorManager :: getHOSmsDetails() :: method called");

             HOSectionMatrixBeanObj = vendorDaoObj.getHoLegalSmsDetails(HOSectionMatrixBeanObj);
             
            //vendorContactList = (LinkedList) vendorDaoObj.getContactNumber(vendorBeanObj);
            //vendorPrezDataObj.setVendorContactList(vendorContactList); 
             
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getHOLegalSmsDetails() :: Exception :: " + ex);

        }
        return HOSectionMatrixBeanObj;
    }
    
    
     
     public LinkedList getVendorLegalInvoiceFeeTypeDtlList( FeeTypeDtlsBean feeTypeDtlBeanObj){
      
        OracleVendorDao vendorDaoObj = null;
     //   FeeTypeDtlsBean feeTypeDtlBean = null;
        LinkedList vendorFeeTypeDtlList = null;
     
        try {
            logger.log(Level.INFO, " VendorManager :: getVendorLegalInvoiceFeeTypeDtlList() :: method called");

            //-- create the dao object
            vendorDaoObj = new OracleVendorDao();

            vendorFeeTypeDtlList = vendorDaoObj.getLegalInvoiceFeeTypeDtlList(feeTypeDtlBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorLegalInvoiceFeeTypeDtlList() :: Exception :: " + ex);

        }
        return vendorFeeTypeDtlList;
    }
     
     
        public FeeTypeDtlsBean  feeTypeDtlDelHelper(FeeTypeDtlsBean feeTypeDtlsBean) {
       
        OracleVendorDao vendorDaoObj = null;
        try {
            logger.log(Level.INFO, " VendorManager :: feeTypeDtlDelHelper() :: method called");

            //-- create the dao object
            vendorDaoObj = new OracleVendorDao();

            feeTypeDtlsBean = vendorDaoObj.feeTypeDtlDelHelper(feeTypeDtlsBean);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorApplFileManager :: legalInvoiceFileDelHelper() :: Exception :: " + ex);
        }
        return feeTypeDtlsBean;
    }
     public TemplateIdBean getTemplateDetails(TemplateIdBean templateBeanObj) {
         VendorDao vendorDaoObj = new OracleVendorDao();
         try {
         templateBeanObj=vendorDaoObj.getTemplateDetails(templateBeanObj);
         }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorApplFileManager :: legalInvoiceFileDelHelper() :: Exception :: " + ex);
        }
         
         return  templateBeanObj;
     }
     
     
     
}