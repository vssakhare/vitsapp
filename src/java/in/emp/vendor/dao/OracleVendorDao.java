/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao;

//-- java imports
import java.util.LinkedList;
import java.util.HashMap;

//-- Logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import in.emp.dao.BaseDao;
import in.emp.dao.QueryHelper;
import in.emp.hrms.bean.HRMSUserBean;
import in.emp.hrms.dao.helper.VendorLoginTxnHelper;
import in.emp.legal.bean.FeeTypeBean;
import in.emp.legal.bean.FeeTypeDtlsBean;
import in.emp.legal.bean.HOSectionMatrixBean;
import in.emp.legal.bean.LegalCommunicationBean;
import in.emp.legal.bean.LegalInvoiceBean;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.legal.bean.OrganizationMasterBean;
import in.emp.legal.dao.helper.queryHelper.GetErpLegalInvoiceDetailsList;
import in.emp.legal.dao.helper.queryHelper.GetErpLegalInvoiceStatusList;
import in.emp.legal.dao.helper.queryHelper.GetFeeTypeList;
import in.emp.legal.dao.helper.queryHelper.GetHOLegalSmsEmailQueryHelper;
import in.emp.legal.dao.helper.queryHelper.GetLegalInvoiceFeeTypeDtlListQueryHelper;
import in.emp.legal.dao.helper.queryHelper.GetLegalSmsTrackerListQueryHelper;
import in.emp.legal.dao.helper.queryHelper.OrganizatonMasterQueryHelper;
import in.emp.legal.dao.helper.txnhelper.ErpLegalInvoiceDetailsTxnHandler;
import in.emp.legal.dao.helper.txnhelper.ErpLegalInvoiceStatusTxnHelper;
import in.emp.legal.dao.helper.txnhelper.FeeTypeDtlsTxnHelper;
import in.emp.legal.dao.helper.txnhelper.updateLegalCommunicationLog;
import in.emp.vendor.bean.ClearingDocDetails;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.PoStatusBean;
import in.emp.vendor.bean.HOBean;
import in.emp.vendor.bean.PoLineStatusBean;
import in.emp.vendor.bean.ProjBean;
import in.emp.vendor.bean.SMSResponseBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.bean.VendorStatuBean;
import in.emp.vendor.dao.helper.FtpFileReadHelper.PutRetentionInvoiceStatusQueryHelper;
import in.emp.vendor.dao.helper.GetClearingDocDetailsQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetInvoiceListQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetLocationListQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetPOListQueryHelper;

import in.emp.vendor.dao.helper.listHelper.GetVendorDtlListQueryHelper;
import in.emp.vendor.dao.helper.formHelper.GetVendorFormDataQueryHelper;
import in.emp.vendor.dao.helper.formHelper.GetVendorFormQueryHelper;
import in.emp.vendor.dao.helper.formHelper.GetVendorInputFormDataQueryHelper;
import in.emp.vendor.dao.helper.formHelper.GetVendorInputFormQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetVendorInputListQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetContactListQueryHelper;
import in.emp.vendor.dao.helper.formHelper.GetEmpPsFormDataQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetHigherContactListQueryHelper;
import in.emp.vendor.dao.helper.GetInvoicedetailsQueryHelper;
import in.emp.vendor.dao.helper.GetSmsTrackerListQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetVendorListQueryHelper;
import in.emp.vendor.dao.helper.txnHelper.VendorApplFormTxnHelper;
import in.emp.vendor.dao.helper.txnHelper.VendorApplTxnHelper;
import in.emp.vendor.dao.helper.FtpFileReadHelper.getPOStatusTxnhelper;
import in.emp.vendor.dao.helper.getVendorSmsStatus;
import in.emp.vendor.dao.helper.FtpFileReadHelper.getVendorStatusTxnHelper;
import in.emp.vendor.dao.helper.GetHOSmsDetailsQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetLocationHierarchyListQueryHelper;
import in.emp.vendor.dao.helper.GetOfficeCodeDetailsQueryHelper;
import in.emp.vendor.dao.helper.GetPODetailsQueryHelper;
import in.emp.vendor.dao.helper.GetPOInvDetailsQueryHelper;
import in.emp.vendor.dao.helper.PoLineDetail.GetPOLineDetailsQueryHelper;
import in.emp.vendor.dao.helper.psHelper.GetProjDetailsQueryHelper;
import in.emp.vendor.dao.helper.psHelper.GetProjInvDetailsQueryHelper;
import in.emp.vendor.dao.helper.psHelper.GetProjListQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetSummaryListQueryHelper;
import in.emp.vendor.dao.helper.GetTableListQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetVendorInvListQueryHelper;
import in.emp.vendor.dao.helper.formHelper.GetVendorNonpoInputFormDataQueryHelper;
import in.emp.vendor.dao.helper.formHelper.GetVendorPsFormDataQueryHelper;
import in.emp.vendor.dao.helper.formHelper.GetVendorPsInputFormDataQueryHelper;
import in.emp.vendor.dao.helper.txnHelper.VendorNonpoApplFormTxnHelper;
import in.emp.vendor.dao.helper.txnHelper.VendorProjApplFormTxnHelper;
import in.emp.vendor.dao.helper.FtpFileReadHelper.getPSStatusTxnhelper;
import in.emp.vendor.dao.helper.FtpFileReadHelper.putInvoiceStatusQueryhelper;
import java.util.List;
import in.emp.vendor.dao.helper.GetTableListQueryHelper;
import in.emp.vendor.dao.helper.formHelper.GetVendorVerifiedInputFormQuerHelper;
import in.emp.vendor.dao.helper.PoLineDetail.GetPOLineInvTxnHelper;
import in.emp.vendor.dao.helper.PoLineDetail.GetSubmitAtLocationListQueryHelper;
import in.emp.vendor.dao.helper.PoLineDetail.getSelectedPOLineStatusTxnHelper;
import in.emp.vendor.dao.helper.FtpFileReadHelper.getPOLineStatusTxnhelper;
import in.emp.vendor.dao.helper.GetForwardToOfficeCodeDetailsQueryHelper;
import in.emp.vendor.dao.helper.GetPlantCodeDetailsQueryHelper;
import in.emp.vendor.dao.helper.formHelper.GetVendorRetentionInputFormDataQueryHelper;
import in.emp.vendor.dao.helper.txnHelper.getSmsResponseTrackerTxnHelper;

import in.emp.vendor.dao.helper.formHelper.getVendorVerifiedFormQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetAuthContactListQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetEscalationSmsStatusQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetPOLocationQueryHelper;
import in.emp.vendor.dao.helper.listHelper.getPlantDetailsQueryHelper;
import in.emp.vendor.dao.helper.psHelper.GetPartialRetentionDetailsQueryHelper;
import in.emp.vendor.dao.helper.psHelper.GetProjectDetailsQueryHelper;
import in.emp.vendor.dao.helper.psHelper.GetRetentionDetailsQueryHelper;
import in.emp.vendor.dao.helper.txnHelper.SaveVendorRetentionDetailsResponseTxnHelper;
import in.emp.vendor.dao.helper.txnHelper.VendorRetentionTxnHelper;

/**
 *
 * @author Sachin
 */
public class OracleVendorDao extends BaseDao implements VendorDao {

    private static Logger logger = Logger.getLogger(OracleVendorDao.class);
    private LinkedList<VendorInputBean> list;

    /**
     * API to get the search data for populating Drop downs
     *
     * @throws Exception	if an error occurs
     * @see	in.mda.device.DeviceDelegate
     * @returns	LinkedList
     */
    public LinkedList getVendorList(VendorBean vendorBeanObj) throws Exception {
        LinkedList vendorList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorList() :: method called ::");

            vendorList = (LinkedList) getObjectList(new GetVendorListQueryHelper(vendorBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorList() :: Exception :: " + ex);
            throw ex;
        }
        return vendorList;
    }

    public LinkedList getVendorVerifiedList(VendorBean vendorBeanObj) throws Exception {
        LinkedList vendorList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorList() :: method called ::");

            vendorList = (LinkedList) getObjectList(new getVendorVerifiedFormQueryHelper(vendorBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorList() :: Exception :: " + ex);
            throw ex;
        }
        return vendorList;
    }

    public LinkedList getSummaryList(VendorBean vendorBeanObj) throws Exception {
        LinkedList summaryList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getSummaryList() :: method called ::");

            summaryList = (LinkedList) getObjectList(new GetSummaryListQueryHelper(vendorBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getSummaryList() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }
        return summaryList;
    }

    public LinkedList getPOList(POBean poBeanObj) throws Exception {
        LinkedList POList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getPOList() :: method called ::");

            POList = (LinkedList) getObjectList(new GetPOListQueryHelper(poBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getPOList() :: Exception :: " + ex);
            throw ex;
        }
        return POList;
    }

    public LinkedList getzoneList(POBean poBeanObj) throws Exception {
        LinkedList zoneList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getzoneList() :: method called ::");

            zoneList = (LinkedList) getObjectList(new GetLocationHierarchyListQueryHelper(poBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getzoneList() :: Exception :: " + ex);
            throw ex;
        }
        return zoneList;
    }

    public LinkedList getcircleList(POBean poBeanObj) throws Exception {
        LinkedList circleList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getcircleList() :: method called ::");

            circleList = (LinkedList) getObjectList(new GetLocationHierarchyListQueryHelper(poBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getcircleList() :: Exception :: " + ex);
            throw ex;
        }
        return circleList;
    }
public LinkedList getSubmitAtList(PoLineStatusBean poLinestatusbeanobj) throws Exception {
        LinkedList submitAtList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getSubmitAtList() :: method called ::");

            submitAtList = (LinkedList) getObjectList(new GetSubmitAtLocationListQueryHelper(poLinestatusbeanobj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getSubmitAtList() :: Exception :: " + ex);
            throw ex;
        }
        return submitAtList;
    }

    public LinkedList getdivisionList(POBean poBeanObj) throws Exception {
        LinkedList divisionList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getdivisionList() :: method called ::");

            divisionList = (LinkedList) getObjectList(new GetLocationHierarchyListQueryHelper(poBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getdivisionList() :: Exception :: " + ex);
            throw ex;
        }
        return divisionList;
    }

    public LinkedList getsubdivisionList(POBean poBeanObj) throws Exception {
        LinkedList subdivisionList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getdivisionList() :: method called ::");

            subdivisionList = (LinkedList) getObjectList(new GetLocationHierarchyListQueryHelper(poBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getdivisionList() :: Exception :: " + ex);
            throw ex;
        }
        return subdivisionList;
    }

    public LinkedList getProjList(ProjBean projBeanObj) throws Exception {
        LinkedList ProjList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getProjList() :: method called ::");

            ProjList = (LinkedList) getObjectList(new GetProjListQueryHelper(projBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getProjList() :: Exception :: " + ex);
            throw ex;
        }
        return ProjList;
    }

    public LinkedList getClearingDocDetails(ClearingDocDetails clearingDocDetailsObj) {
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList clearingDocDetails = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getClearingDocDetails() :: method called");
            clearingDocDetails = (LinkedList) getObjectList(new GetClearingDocDetailsQueryHelper(clearingDocDetailsObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getClearingDocDetails() :: Exception :: " + ex);
        }
        return clearingDocDetails;
    }

    public LinkedList getPOLineDetails(PoLineStatusBean poLineStatusBeanObj) {
        VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList poLineDetails = new LinkedList();
        try {
            logger.log(Level.INFO, " VendorManager :: getPOLineDetails() :: method called");
            poLineDetails = (LinkedList) getObjectList(new GetPOLineDetailsQueryHelper(poLineStatusBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOLineDetails() :: Exception :: " + ex);
        }
        return poLineDetails;
    }

    public POBean getPOLocationList(POBean poBeanObj) throws Exception {
        POBean poBeanObj1 = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getPOLocationList() :: method called ::");

            poBeanObj1 = (POBean) getDataObject(new GetPOListQueryHelper(poBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getPOLocationList() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj1;
    }
public POBean getPOLocation(POBean poBeanObj) throws Exception {
        POBean poBeanObj1 = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getPOLocationList() :: method called ::");

            poBeanObj1 = (POBean) getDataObject(new GetPOLocationQueryHelper(poBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getPOLocationList() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj1;
    }
    public ProjBean getProjLocationList(ProjBean projBeanObj) throws Exception {
        ProjBean projBeanObj1 = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getProjLocationList() :: method called ::");

            projBeanObj1 = (ProjBean) getDataObject(new GetProjListQueryHelper(projBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getProjLocationList() :: Exception :: " + ex);
            throw ex;
        }
        return projBeanObj1;
    }

    public POBean getPODetails(POBean poBeanObj) throws Exception {
        POBean poBeanObj1 = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getPODetails() :: method called ::");

            poBeanObj1 = (POBean) getDataObject(new GetPODetailsQueryHelper(poBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getPODetails() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj1;
    }

    public SMSResponseBean getSmsResponseTrackerTxnHelper(SMSResponseBean smsresponsebeanbeanobj) throws Exception {

        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getPODetails() :: method called ::");

            smsresponsebeanbeanobj = (SMSResponseBean) createObject(new getSmsResponseTrackerTxnHelper(smsresponsebeanbeanobj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getPODetails() :: Exception :: " + ex);
            throw ex;
        }
        return smsresponsebeanbeanobj;
    }

    public ProjBean getProjDetails(ProjBean projBeanObj) throws Exception {
        ProjBean projBeanObj1 = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getProjDetails() :: method called ::");

            projBeanObj1 = (ProjBean) getDataObject(new GetProjDetailsQueryHelper(projBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getProjDetails() :: Exception :: " + ex);
            throw ex;
        }
        return projBeanObj1;
    }
  public ProjBean getProjectDetails(ProjBean projBeanObj) throws Exception {
        ProjBean projBeanObj1 = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getProjectDetails() :: method called ::");

            projBeanObj1 = (ProjBean) getDataObject(new GetProjectDetailsQueryHelper(projBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getProgetProjectDetailsjDetails() :: Exception :: " + ex);
            throw ex;
        }
        return projBeanObj1;
    }
    public POBean getPOInvDetails(POBean poBeanObj) throws Exception {
        POBean poBeanObj1 = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getPOInvDetails() :: method called ::");

            poBeanObj1 = (POBean) getDataObject(new GetPOInvDetailsQueryHelper(poBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getPOInvDetails() :: Exception :: " + ex);
            throw ex;
        }
        return poBeanObj1;
    }

    public ProjBean getProjInvDetails(ProjBean projBeanObj) throws Exception {
        ProjBean projBeanObj1 = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getProjInvDetails() :: method called ::");

            projBeanObj1 = (ProjBean) getDataObject(new GetProjInvDetailsQueryHelper(projBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getProjInvDetails() :: Exception :: " + ex);
            throw ex;
        }
        return projBeanObj1;
    }

    public LinkedList getVendorDtlList(POBean poBeanObj) throws Exception {
        LinkedList VDtlList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorDtlList() :: method called ::");

            VDtlList = (LinkedList) getObjectList(new GetVendorDtlListQueryHelper(poBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorDtlList() :: Exception :: " + ex);
            throw ex;
        }
        return VDtlList;
    }

    public LinkedList getlocationList(POBean poBeanObj) throws Exception {
        LinkedList LocationList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorDtlList() :: method called ::");

            LocationList = (LinkedList) getObjectList(new GetLocationListQueryHelper(poBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorDtlList() :: Exception :: " + ex);
            throw ex;
        }
        return LocationList;
    }

    public LinkedList getVendorinvList(POBean poBeanObj) throws Exception {
        LinkedList VendorinvList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorinvList() :: method called ::");

            VendorinvList = (LinkedList) getObjectList(new GetVendorInvListQueryHelper(poBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorinvList() :: Exception :: " + ex);
            throw ex;
        }
        return VendorinvList;
    }

    public void getVendorSmsStatus(VendorStatuBean vendorStatusBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorSmsStatus() :: method called ::");
            removeObject(new getVendorSmsStatus(vendorStatusBeanObj)); //this call updates or inserts according to the output of deleteobject
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorSmsStatus() :: Exception :: " + ex);
            throw ex;
        }

    }
    
    
    public void updateLegalCommunicationLog(LegalCommunicationBean legalCommunicationBean) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorSmsStatus() :: method called ::");
            createObject(new updateLegalCommunicationLog(legalCommunicationBean)); //this call updates or inserts according to the output of deleteobject
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorSmsStatus() :: Exception :: " + ex);
            throw ex;
        }

    }

    public VendorBean getContactNumber(VendorBean vendorBeanObj) throws Exception {
        LinkedList vendorContactList = null;

        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getContactNumber() :: method called ::");

            //vendorContactList = (LinkedList) getObjectList(new GetContactListQueryHelper(vendorBeanObj)); 
            vendorBeanObj = (VendorBean) getDataObject(new GetContactListQueryHelper(vendorBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getContactNumber() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }

    public POBean getOfficeCodeDetails(POBean POBeanObj) throws Exception {

        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getOfficeCodeDetails() :: method called ::");

            POBeanObj = (POBean) getDataObject(new GetOfficeCodeDetailsQueryHelper(POBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getOfficeCodeDetails() :: Exception :: " + ex);
            throw ex;
        }
        return POBeanObj;
    }
      public POBean getPlantCodeDetails(POBean POBeanObj) throws Exception {

        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getPlantCodeDetails() :: method called ::");

            POBeanObj = (POBean) getDataObject(new GetPlantCodeDetailsQueryHelper(POBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getPlantCodeDetails() :: Exception :: " + ex);
            throw ex;
        }
        return POBeanObj;
    }
public POBean getForwardedToOfficeCodeDetails(POBean POBeanObj) throws Exception {

        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getForwardedToOfficeCodeDetails() :: method called ::");

            POBeanObj = (POBean) getDataObject(new GetForwardToOfficeCodeDetailsQueryHelper(POBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getForwardedToOfficeCodeDetails() :: Exception :: " + ex);
            throw ex;
        }
        return POBeanObj;
    }
public LinkedList getPlantDetails(POBean POBeanObj) throws Exception {
LinkedList PlantList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getPlantDetails() :: method called ::");

            PlantList = (LinkedList) getObjectList(new getPlantDetailsQueryHelper(POBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getPlantDetails() :: Exception :: " + ex);
            throw ex;
        }
        return PlantList;
    }

    public HOBean getHOSmsDetails(HOBean HOBeanObj) throws Exception {

        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getHOSmsDetails() :: method called ::");

            HOBeanObj = (HOBean) getDataObject(new GetHOSmsDetailsQueryHelper(HOBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getHOSmsDetails() :: Exception :: " + ex);
            throw ex;
        }
        return HOBeanObj;
    }

    public LinkedList getInvoiceList(VendorBean vendorBeanObj) throws Exception {
        LinkedList vendorInvoiceList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getInvoiceList() :: method called ::");

            vendorInvoiceList = (LinkedList) getObjectList(new GetInvoiceListQueryHelper(vendorBeanObj));
            // vendorBeanObj = (VendorBean) getDataObject(new GetInvoiceListQueryHelper(vendorBeanObj)); 
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getInvoiceList() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInvoiceList;
    }

    public VendorInputBean getVendorInputForm(VendorInputBean vendorInputBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorInputForm() :: method called ::    ");
            vendorInputBeanObj = (VendorInputBean) getDataObject(new GetVendorInputFormQueryHelper(vendorInputBeanObj));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorInputForm() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInputBeanObj;
    }

    public LinkedList<VendorInputBean> getHigherContactList(VendorInputBean vendorInputBeanObj) throws Exception {
        LinkedList<VendorInputBean> fileList = new LinkedList<VendorInputBean>();
        try {
            logger.log(Level.INFO, " OracleVendorDao :: getHigherContactList() :: method called");

            fileList = (LinkedList<VendorInputBean>) getObjectList(new GetHigherContactListQueryHelper(vendorInputBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " OracleVendorDao :: getHigherContactList() :: Exception :: " + ex);
        }
        return fileList;
    }
     public LinkedList<VendorInputBean> getAuthContactList(VendorInputBean vendorInputBeanObj) throws Exception {
        LinkedList<VendorInputBean> fileList = new LinkedList<VendorInputBean>();
        try {
            logger.log(Level.INFO, " OracleVendorDao :: getAuthContactList() :: method called");

            fileList = (LinkedList<VendorInputBean>) getObjectList(new GetAuthContactListQueryHelper(vendorInputBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " OracleVendorDao :: getAuthContactList() :: Exception :: " + ex);
        }
        return fileList;
    }
  public LinkedList<VendorInputBean> getEscStatusSmsList(VendorInputBean vendorInputBeanObj) throws Exception {
        LinkedList<VendorInputBean> fileList = new LinkedList<VendorInputBean>();
        try {
            logger.log(Level.INFO, " OracleVendorDao :: getEscStatusSmsList() :: method called");

            fileList = (LinkedList<VendorInputBean>) getObjectList(new GetEscalationSmsStatusQueryHelper(vendorInputBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " OracleVendorDao :: getEscStatusSmsList() :: Exception :: " + ex);
        }
        return fileList;
    }
    public LinkedList<VendorInputBean> getSmsTrackerList(VendorInputBean vendorInputBeanObj) throws Exception {
        LinkedList<VendorInputBean> fileList = new LinkedList<VendorInputBean>();
        try {
            logger.log(Level.INFO, " OracleVendorDao :: getSmsTrackerList() :: method called");

            fileList = (LinkedList<VendorInputBean>) getObjectList(new GetSmsTrackerListQueryHelper(vendorInputBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " OracleVendorDao :: getSmsTrackerList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return fileList;
    }
    
     public LinkedList<LegalInvoiceInputBean> getLegalSmsTrackerList(LegalInvoiceInputBean legalInvoiceInputBeanObj) throws Exception {
              LinkedList<LegalInvoiceInputBean> fileList = new LinkedList<LegalInvoiceInputBean>();
        try {
            logger.log(Level.INFO, " OracleVendorDao :: getLegalSmsTrackerList() :: method called");

            fileList = (LinkedList<LegalInvoiceInputBean>) getObjectList(new GetLegalSmsTrackerListQueryHelper(legalInvoiceInputBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " OracleVendorDao :: getSmsTrackerList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return fileList;
    }
    
    public VendorInputBean getInvoicedetails(VendorInputBean vendorInputBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getInvoicedetails() :: method called ::    ");
            vendorInputBeanObj = (VendorInputBean) getDataObject(new GetInvoicedetailsQueryHelper(vendorInputBeanObj));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getInvoicedetails() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInputBeanObj;
    }

    public List getPOStatus(List lstErpToVitsFileFormat) throws Exception {
        try {
            List list;
            logger.log(Level.INFO, "OracleVendorDao ::: getPOStatus() :: method called ::    ");
            createObject(new getPOStatusTxnhelper(lstErpToVitsFileFormat));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getPOStatus() :: Exception :: " + ex);
            throw ex;
        }
        return lstErpToVitsFileFormat;
    }
  public void getPOStatusProcedure() throws Exception {
      
        try {
            logger.log(Level.INFO, " VendorManager :: getPOStatusProcedure() :: method called");
              updateObject(new getPOStatusTxnhelper());
          //  vendorDaoObj.getPOStatusProcedure();
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOStatusProcedure() :: Exception :: " + ex);
        }
       
    }
    public List getVendorStatus(List lstErpToVitsFileFormat) throws Exception {
        try {
            List list;
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorStatus() :: method called ::    ");
            createObject(new getVendorStatusTxnHelper(lstErpToVitsFileFormat));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorStatus() :: Exception :: " + ex);
            throw ex;
        }
        return lstErpToVitsFileFormat;
    }
  public void getVendorStatusProcedure() throws Exception {
      
        try {
            logger.log(Level.INFO, " VendorManager :: getVendorStatus() :: method called");
              updateObject(new getVendorStatusTxnHelper());
          //  vendorDaoObj.getPOStatusProcedure();
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorStatus() :: Exception :: " + ex);
        }
       
    }
    public List getPOLineStatus(List lstErpToVitsFileFormat) throws Exception {
        try {
            List list;
            logger.log(Level.INFO, "OracleVendorDao ::: getPOLineStatus() :: method called ::    ");
            createObject(new getPOLineStatusTxnhelper(lstErpToVitsFileFormat));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getPOLineStatus() :: Exception :: " + ex);
            throw ex;
        }
        return lstErpToVitsFileFormat;
    }
 public void getPOLineStatusProcedure() throws Exception {
      
        try {
            logger.log(Level.INFO, " VendorManager :: getPOLineStatusProcedure() :: method called");
              updateObject(new getPOLineStatusTxnhelper());
          //  vendorDaoObj.getPOStatusProcedure();
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getPOLineStatusProcedure() :: Exception :: " + ex);
        }
       
    }
    @Override
    public LinkedList getSelectedLineStatus(LinkedList selectedpoLineDetails) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getSelectedLineStatus() :: method called ::    ");
            updateObject(new getSelectedPOLineStatusTxnHelper(selectedpoLineDetails));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getSelectedLineStatus() :: Exception :: " + ex);
            throw ex;
        }
        return selectedpoLineDetails;
    }

    public List getPSStatus(List lstErpToVitsFileFormat) throws Exception {
        try {
            List list;
            logger.log(Level.INFO, "OracleVendorDao ::: getPOStatus() :: method called ::    ");
            updateObject(new getPSStatusTxnhelper(lstErpToVitsFileFormat));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getPOStatus() :: Exception :: " + ex);
            throw ex;
        }
        return lstErpToVitsFileFormat;
    }

    public LinkedList<VendorInputBean> putInvoiceStatus(VendorInputBean vendorInputBeanObj) throws Exception {
        LinkedList<VendorInputBean> list = new LinkedList<VendorInputBean>();
        try {

            logger.log(Level.INFO, "OracleVendorDao ::: putInvoiceStatus() :: method called ::    ");

            list = (LinkedList<VendorInputBean>) getObjectList(new putInvoiceStatusQueryhelper(vendorInputBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: putInvoiceStatus() :: Exception :: " + ex);
            throw ex;
        }
        return list;
    }

   /* public List getVendorStatus(List listErpToVitsFileFormat) throws Exception {
        try {
            List list;
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorStatus() :: method called ::    ");
            updateObject(new getVendorStatusTxnHelper(listErpToVitsFileFormat));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorStatus() :: Exception :: " + ex);
            throw ex;
        }
        return listErpToVitsFileFormat;
    }*/

    public VendorInputBean getVendorInputFormData(VendorInputBean vendorInputBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorInputFormData() :: method called ::    ");
            vendorInputBeanObj = (VendorInputBean) getDataObject(new GetVendorInputFormDataQueryHelper(vendorInputBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorInputFormData() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInputBeanObj;
    }

    public VendorInputBean getVendorPsInputFormData(VendorInputBean vendorInputBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorInputFormData() :: method called ::    ");
            vendorInputBeanObj = (VendorInputBean) getDataObject(new GetVendorPsInputFormDataQueryHelper(vendorInputBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorInputFormData() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInputBeanObj;
    }

    public VendorInputBean getVendorNonpoInputFormData(VendorInputBean vendorInputBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorInputFormData() :: method called ::    ");
            vendorInputBeanObj = (VendorInputBean) getDataObject(new GetVendorNonpoInputFormDataQueryHelper(vendorInputBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorInputFormData() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInputBeanObj;
    }

    public VendorBean getVendorFormData(VendorBean vendorBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorFormData() :: method called ::    ");
            vendorBeanObj = (VendorBean) getDataObject(new GetVendorFormDataQueryHelper(vendorBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorFormData() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }

    public VendorBean getVendorPsFormData(VendorBean vendorBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorFormData() :: method called ::    ");
            vendorBeanObj = (VendorBean) getDataObject(new GetVendorPsFormDataQueryHelper(vendorBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorFormData() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }

    public VendorBean getEmpPsFormData(VendorBean vendorBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorFormData() :: method called ::    ");
            vendorBeanObj = (VendorBean) getDataObject(new GetEmpPsFormDataQueryHelper(vendorBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorFormData() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }

    public VendorInputBean getVendorVerifiedInputForm(VendorInputBean vendorInputBeanObj) throws Exception {

        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorVerifiedInputForm() :: method called ::");

            vendorInputBeanObj = (VendorInputBean) getDataObject(new GetVendorVerifiedInputFormQuerHelper(vendorInputBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorVerifiedInputForm() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInputBeanObj;
    }

    public LinkedList getVendorInputList(VendorInputBean vendorInputBeanObj) throws Exception {
        LinkedList vendorList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorInputList() :: method called ::");

            vendorList = (LinkedList) getObjectList(new GetVendorInputListQueryHelper(vendorInputBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorInputList() :: Exception :: " + ex);
            throw ex;
        }
        return vendorList;
    }

    public LinkedList getTableList(VendorInputBean vendorInputBeanObj) throws Exception {
        LinkedList tableList = null;
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getSummaryList() :: method called ::");

            tableList = (LinkedList) getObjectList(new GetTableListQueryHelper(vendorInputBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getSummaryList() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }
        return tableList;
    }
 public void GetPOLineInvTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: GetPOLineInvTxnHelper() :: method called ::    ");
       
                updateObject(new GetPOLineInvTxnHelper(vendorPrezDataObj));
           

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: GetPOLineInvTxnHelper() :: Exception :: " + ex);
            throw ex;
        }
       
    }
    public VendorPrezData VendorApplFormTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: postVendor() :: method called ::    ");
            if (vendorPrezDataObj.getVendorInputBean().getApplId() != "") {
                updateObject(new VendorApplFormTxnHelper(vendorPrezDataObj));
            } else {
                vendorPrezDataObj = (VendorPrezData) createObject(new VendorApplFormTxnHelper(vendorPrezDataObj));
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: postVendor() :: Exception :: " + ex);
            throw ex;
        }
        return vendorPrezDataObj;
    }

    public VendorPrezData VendorProjApplFormTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: postVendor() :: method called ::    ");
            if (vendorPrezDataObj.getVendorInputBean().getApplId() != "") {
                updateObject(new VendorProjApplFormTxnHelper(vendorPrezDataObj));
            } else {
                vendorPrezDataObj = (VendorPrezData) createObject(new VendorProjApplFormTxnHelper(vendorPrezDataObj));
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: postVendor() :: Exception :: " + ex);
            throw ex;
        }
        return vendorPrezDataObj;
    }

    public VendorPrezData VendorNonpoApplFormTxnHelper(VendorPrezData vendorPrezDataObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: postVendor() :: method called ::    ");
            if (vendorPrezDataObj.getVendorInputBean().getApplId() != "") {
                updateObject(new VendorNonpoApplFormTxnHelper(vendorPrezDataObj));
            } else {
                vendorPrezDataObj = (VendorPrezData) createObject(new VendorNonpoApplFormTxnHelper(vendorPrezDataObj));
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: postVendor() :: Exception :: " + ex);
            throw ex;
        }
        return vendorPrezDataObj;
    }
//   
//    @Override
//    public VendorBean VendorApplTxnHelper(VendorBean vendorBeanObj) throws Exception {
//        try {
//            logger.log(Level.INFO, "OracleVendorDao ::: TAclaimsTxnHelper() :: method called ::    ");
//
//            removeObject(new VendorApplTxnHelper(vendorBeanObj));
//
//        } catch (Exception ex) {
//            logger.log(Level.ERROR, "OracleVendorDao ::: TAclaimsTxnHelper() :: Exception :: " + ex);
//            throw ex;
//        }
//        return vendorBeanObj;
//    }
    
    public LinkedList getRetentionDetails(VendorBean bean)throws Exception{
        LinkedList<VendorBean> list = new LinkedList<VendorBean>();
        try {

            logger.log(Level.INFO, "OracleVendorDao ::: getRetentionDetails() :: method called ::    ");
            list = (LinkedList<VendorBean>) getObjectList(new GetRetentionDetailsQueryHelper(bean));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getRetentionDetails() :: Exception :: " + ex);
            throw ex;
        }
        return list;
    }
     public VendorInputBean saveClaimedRetentionDetails(VendorInputBean vendorInputBean) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: postVendor() :: method called ::    ");
            if(vendorInputBean.getSaveFlag().equals("Saved")){
                vendorInputBean = (VendorInputBean) createObject(new VendorRetentionTxnHelper(vendorInputBean));
            }else{
                updateObject(new VendorRetentionTxnHelper(vendorInputBean));
            }
            

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: postVendor() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInputBean;
    }
     public LinkedList<VendorBean> putRetentionInvoiceStatus( VendorBean vendorBeanObj) throws Exception{
         LinkedList<VendorBean> list = new LinkedList<VendorBean>();
        try {

            logger.log(Level.INFO, "OracleVendorDao ::: putInvoiceStatus() :: method called ::    ");

            list = (LinkedList<VendorBean>) getObjectList(new PutRetentionInvoiceStatusQueryHelper(vendorBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: putInvoiceStatus() :: Exception :: " + ex);
            throw ex;
        }
        return list;
     }
     public VendorInputBean getVendorRetentionInputFormData(VendorInputBean vendorInputBeanObj)throws Exception{
         try {
            logger.log(Level.INFO, "OracleVendorDao ::: getVendorRetentionInputFormData() :: method called ::    ");
            vendorInputBeanObj = (VendorInputBean) getDataObject(new GetVendorRetentionInputFormDataQueryHelper(vendorInputBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getVendorRetentionInputFormData() :: Exception :: " + ex);
            throw ex;
        }
        return vendorInputBeanObj;
     }
     public List saveRetentionDetailsResponse(List<VendorInputBean> listErpToVitsFileFormat)throws Exception{
         try {
            List list;
            logger.log(Level.INFO, "OracleVendorDao ::: saveRetentionDetailsResponse() :: method called ::    ");
            createObject(new SaveVendorRetentionDetailsResponseTxnHelper(listErpToVitsFileFormat));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: saveRetentionDetailsResponse() :: Exception :: " + ex);
            throw ex;
        }
        return listErpToVitsFileFormat;
     }
     public void updateRetentionDetailsResponseProcedure()throws Exception{
         try {
            logger.log(Level.INFO, " VendorManager :: getVendorStatus() :: method called");
              updateObject(new SaveVendorRetentionDetailsResponseTxnHelper());
          //  vendorDaoObj.getPOStatusProcedure();
           
        }catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getVendorStatus() :: Exception :: " + ex);
        }
     }
     
     public LinkedList getPartialRetentionDetails(VendorBean vendorBean)throws Exception{
         LinkedList<VendorBean> list = new LinkedList<VendorBean>();
        try {

            logger.log(Level.INFO, "OracleVendorDao ::: getPartialRetentionDetails() :: method called ::    ");

            list = (LinkedList<VendorBean>) getObjectList(new GetPartialRetentionDetailsQueryHelper(vendorBean));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getPartialRetentionDetails() :: Exception :: " + ex);
            throw ex;
        }
        return list;
     }

    @Override
    public List saveLegalInvoiceStatus(List<LegalInvoiceBean> listErpToVitsFileFormat) throws Exception{
try {
            List list;
            logger.log(Level.INFO, "OracleVendorDao ::: saveLegalInvoiceStatus() :: method called ::    ");
            createObject(new ErpLegalInvoiceStatusTxnHelper(listErpToVitsFileFormat));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: saveLegalInvoiceStatus() :: Exception :: " + ex);
            throw ex;
        }
        return listErpToVitsFileFormat;
    }
    @Override
     public List getCourtCaseDetailsForVendor(LegalInvoiceBean legalInvoiceBean)throws Exception{
         LinkedList<LegalInvoiceBean> list=null;
         try {
            
            logger.log(Level.INFO, "OracleVendorDao ::: getCourtCaseDetailsForVendor() :: method called ::    ");
            list=(LinkedList<LegalInvoiceBean>)getObjectList(new GetErpLegalInvoiceStatusList(legalInvoiceBean));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getCourtCaseDetailsForVendor() :: Exception :: " + ex);
            throw ex;
        }
        return list;
     }
     
     
     public FeeTypeDtlsBean saveLFeeTypeDtlsForm(FeeTypeDtlsBean feeTypeDtlsBean)throws Exception
     {
         try {
            logger.log(Level.INFO, "OracleVendorDao ::: saveLFeeTypeDtlsForm() :: method called ::    ");
            if(feeTypeDtlsBean.getFeeTypeDtlsId().equals(0)){
                feeTypeDtlsBean = (FeeTypeDtlsBean) createObject(new FeeTypeDtlsTxnHelper(feeTypeDtlsBean));
            }else{
                updateObject(new FeeTypeDtlsTxnHelper(feeTypeDtlsBean));
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: saveLFeeTypeDtlsForm() :: Exception :: " + ex);
            throw ex;
        }
        return feeTypeDtlsBean;
     }
     
     public LegalInvoiceInputBean saveLegalInvoiceForm(LegalInvoiceInputBean legalInvoiceInputBean)throws Exception
     {
         try {
            logger.log(Level.INFO, "OracleVendorDao ::: saveLegalInvoiceForm() :: method called ::    ");
            if(legalInvoiceInputBean.getSaveFlag().equals("Saved")){
                legalInvoiceInputBean = (LegalInvoiceInputBean) createObject(new ErpLegalInvoiceDetailsTxnHandler(legalInvoiceInputBean));
            }else{
                updateObject(new ErpLegalInvoiceDetailsTxnHandler(legalInvoiceInputBean));
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: saveLegalInvoiceForm() :: Exception :: " + ex);
            throw ex;
        }
        return legalInvoiceInputBean;
     }
     public List getLegalInvoiceInputList(LegalInvoiceInputBean legalInvoiceInputBean)throws Exception{
         LinkedList<LegalInvoiceInputBean> list=null;
         try {
            
            logger.log(Level.INFO, "OracleVendorDao ::: getCourtCaseDetailsForVendor() :: method called ::    ");
            list=(LinkedList<LegalInvoiceInputBean>)getObjectList(new GetErpLegalInvoiceDetailsList(legalInvoiceInputBean));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
            
             for (int i = 0; i < list.size(); i++) {
                 LegalInvoiceInputBean lBean=list.get(i);
           String sapStatus=getLegalInvoiceStatusFromSAP(lBean);
           
           list.get(i).setStatus(sapStatus);
   
           
        }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getCourtCaseDetailsForVendor() :: Exception :: " + ex);
            throw ex;
        }
        return list;
     }
     
     
     private String getLegalInvoiceStatusFromSAP(LegalInvoiceInputBean liBean){
    String sapStatus="";
    if (liBean.getSaveFlag().equalsIgnoreCase("Accepted"))
    {
                if (liBean.getStatusFee() != null && liBean.getStatusFee().equalsIgnoreCase("Submitted")&& liBean.getParkPostDocNo()== null){
                    sapStatus="With Accounts";
                }
                else if (liBean.getStartPostDocNo() != null &&(liBean.getStartPostDocNo().equals("16" )) && liBean.getPayDoneErpDoc() == null){
                     sapStatus="With Cash";
                }

               else if (liBean.getStartPostDocNo() != null && (liBean.getStartPostDocNo().equals("16" ))&& liBean.getStartPayDoneErpDoc() !=null && liBean.getStartPayDoneErpDoc().equals("17")){
                     sapStatus="Payment Done";
                }

               else if (liBean.getStartPostDocNo() != null && (liBean.getStartPostDocNo().equals("16" )) && liBean.getStartPayDoneErpDoc() !=null && liBean.getStartPayDoneErpDoc1().equals("020")){
                     sapStatus="Payment Adjusted";
                }

              else  if (liBean.getStartPostDocNo() != null && (liBean.getStartPostDocNo().equals("16" )) && liBean.getStartPayDoneErpDoc() !=null && liBean.getStartPayDoneErpDoc().equals("12")){
                     sapStatus="Payment Document Reversed";
                }
              else {
               sapStatus="With Technical/Legal";
              }
    } else {
        sapStatus=liBean.getSaveFlag();
    }
    
           
  return  sapStatus;
}
     
     public List getLegalFeeType(FeeTypeBean bean)throws Exception{
         LinkedList<FeeTypeBean> list=null;
         try {
            
            logger.log(Level.INFO, "OracleVendorDao ::: getLegalFeeType() :: method called ::    ");
            list=(LinkedList<FeeTypeBean>)getObjectList(new GetFeeTypeList(bean));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getLegalFeeType() :: Exception :: " + ex);
            throw ex;
        }
        return list;
     }
     public List getLegalHierarchyLocation(OrganizationMasterBean organizationMasterBean)throws Exception{
         LinkedList<OrganizationMasterBean> list=null;
         try {
            
            logger.log(Level.INFO, "OracleVendorDao ::: getLegalHierarchyLocation() :: method called ::    ");
            list=(LinkedList<OrganizationMasterBean>)getObjectList(new OrganizatonMasterQueryHelper(organizationMasterBean));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getLegalHierarchyLocation() :: Exception :: " + ex);
            throw ex;
        }
        return list;
     }
     

    public HOSectionMatrixBean getHoLegalSmsDetails(HOSectionMatrixBean HOSectionMatrixBeanObj) throws Exception {

        try {
            logger.log(Level.INFO, "OracleVendorDao ::: getHoLegalSmsDetails() :: method called ::");

            HOSectionMatrixBeanObj = (HOSectionMatrixBean) getDataObject(new GetHOLegalSmsEmailQueryHelper(HOSectionMatrixBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getHoLegalSmsDetails() :: Exception :: " + ex);
            throw ex;
        }
        return HOSectionMatrixBeanObj;
    }
    
    public LinkedList getLegalInvoiceFeeTypeDtlList(FeeTypeDtlsBean feeTypeDtlsBean)throws Exception{
         LinkedList<FeeTypeDtlsBean> list=null;
         try {
            
            logger.log(Level.INFO, "OracleVendorDao ::: getLegalInvoiceFeeTypeDtlList() :: method called ::    ");
            list=(LinkedList<FeeTypeDtlsBean>)getObjectList(new GetLegalInvoiceFeeTypeDtlListQueryHelper(feeTypeDtlsBean));
            // leaveapplFormBeanObj = (VendorBean) getDataObject(new GetVendorQueryHelper(leaveapplFormBeanObj));
            
       
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: getLegalInvoiceFeeTypeDtlList() :: Exception :: " + ex);
            throw ex;
        }
        return list;
     }
    
    
    public FeeTypeDtlsBean feeTypeDtlDelHelper(FeeTypeDtlsBean feeTypeDtlsBean) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: feeTypeDtlDelHelper() :: method called ::    ");
            removeObject(new FeeTypeDtlsTxnHelper(feeTypeDtlsBean));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: feeTypeDtlDelHelper() :: Exception :: " + ex);
            throw ex;
        }
        return feeTypeDtlsBean;
    }
     
}
