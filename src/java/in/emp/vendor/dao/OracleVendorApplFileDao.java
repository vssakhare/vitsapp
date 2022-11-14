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
import in.emp.legal.dao.helper.queryHelper.GetVendorLegalApplFileListQueryHelper;
import in.emp.legal.dao.helper.txnhelper.VendorLegalApplFileTxnHelper;
import in.emp.vendor.bean.VendorApplFileBean;
import in.emp.vendor.bean.VendorApplFilePrezData;
import in.emp.vendor.dao.helper.txnHelper.VendorApplFileTxnHelper;
import in.emp.vendor.dao.helper.GetVendorApplFileQueryHelper;
import in.emp.vendor.dao.helper.GetVendorApplFileListQueryHelper;
import in.emp.vendor.dao.helper.listHelper.GetVendorPOFileListQueryHelper;
import in.emp.vendor.dao.helper.GetVendorPOFileQueryHelper;
import in.emp.vendor.dao.helper.txnHelper.VendorPOFileTxnHelper;

/**
 *
 * @author Prajakta
 */
public class OracleVendorApplFileDao extends BaseDao implements VendorApplFileDao {

    private static Logger logger = Logger.getLogger(OracleVendorApplFileDao.class);

    public LinkedList getVendorApplFileList(VendorApplFileBean vendorapplFileBeanObj) throws Exception {
        LinkedList FileList = null;
        try {
            logger.log(Level.INFO, "OracleVendorApplFileDao ::: getVendorApplFile() :: method called ::    ");
            FileList = (LinkedList) getObjectList(new GetVendorApplFileListQueryHelper(vendorapplFileBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorApplFileDao ::: getVendorApplFile() :: Exception :: " + ex);
            throw ex;
        }
        return FileList;
    }
      public LinkedList getVendorPOFileList(VendorApplFileBean vendorapplFileBeanObj) throws Exception {
        LinkedList FileList = null;
        try {
            logger.log(Level.INFO, "OracleVendorApplFileDao ::: getVendorApplFile() :: method called ::    ");
            FileList = (LinkedList) getObjectList(new GetVendorPOFileListQueryHelper(vendorapplFileBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorApplFileDao ::: getVendorApplFile() :: Exception :: " + ex);
            throw ex;
        }
        return FileList;
    }

    public VendorApplFileBean getVendorApplFile(VendorApplFileBean vendorapplFileBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorApplFileDao ::: getVendorApplFile() :: method called ::    ");
            vendorapplFileBeanObj = (VendorApplFileBean) getDataObject(new GetVendorApplFileQueryHelper(vendorapplFileBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorApplFileDao ::: getVendorApplFile() :: Exception :: " + ex);
            throw ex;
        }
        return vendorapplFileBeanObj;
    }
    public VendorApplFileBean getVendorPOFile(VendorApplFileBean vendorapplFileBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorApplFileDao ::: getVendorPOFile() :: method called ::    ");
            vendorapplFileBeanObj = (VendorApplFileBean) getDataObject(new GetVendorPOFileQueryHelper(vendorapplFileBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorApplFileDao ::: getVendorPOFile() :: Exception :: " + ex);
            throw ex;
        }
        return vendorapplFileBeanObj;
    }

    public VendorApplFileBean VendorApplFileTxnHelper(VendorApplFileBean vendorapplFileBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorApplFileDao ::: VendorApplFileTxnHelper() :: method called ::    ");
  if(vendorapplFileBeanObj.getPath()!="")
            {
                updateObject(new VendorApplFileTxnHelper(vendorapplFileBeanObj));
            }else{
            vendorapplFileBeanObj = (VendorApplFileBean) createObject(new VendorApplFileTxnHelper(vendorapplFileBeanObj));
        }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorApplFileDao ::: VendorApplFileTxnHelper() :: Exception :: " + ex);
            throw ex;
        }
        return vendorapplFileBeanObj;
    }
     public VendorApplFileBean VendorPOFileTxnHelper(VendorApplFileBean vendorapplFileBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorPOFileDao ::: VendorPOFileTxnHelper() :: method called ::    ");
            if(vendorapplFileBeanObj.getPath()!="")
            {
                  updateObject(new VendorPOFileTxnHelper(vendorapplFileBeanObj));
            }else{
            vendorapplFileBeanObj = (VendorApplFileBean) createObject(new VendorPOFileTxnHelper(vendorapplFileBeanObj));
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorPOFileDao ::: VendorPOFileTxnHelper() :: Exception :: " + ex);
            throw ex;
        }
        return vendorapplFileBeanObj;
    }

    public VendorApplFileBean VendorApplFileDelHelper(VendorApplFileBean vendorapplFileBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorApplFileDao ::: VendorApplFileDelHelper() :: method called ::    ");
            removeObject(new VendorApplFileTxnHelper(vendorapplFileBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorApplFileDao ::: VendorApplFileDelHelper() :: Exception :: " + ex);
            throw ex;
        }
        return vendorapplFileBeanObj;
    }
      public VendorApplFileBean VendorPOFileDelHelper(VendorApplFileBean vendorapplFileBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorPOFileDao ::: VendorPOFileDelHelper() :: method called ::    ");
            removeObject(new VendorPOFileTxnHelper(vendorapplFileBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorPOFileDao ::: VendorPOFileDelHelper() :: Exception :: " + ex);
            throw ex;
        }
        return vendorapplFileBeanObj;
    }

    public VendorApplFileBean VendorLegalApplFileTxnHelper(VendorApplFileBean vendorapplFileBeanObj) throws Exception{
        try {
            logger.log(Level.INFO, "OracleVendorApplFileDao ::: VendorLegalApplFileTxnHelper() :: method called ::    ");
  if(vendorapplFileBeanObj.getPath()!="")
            {
                updateObject(new VendorLegalApplFileTxnHelper(vendorapplFileBeanObj));
            }else{
            vendorapplFileBeanObj = (VendorApplFileBean) createObject(new VendorLegalApplFileTxnHelper(vendorapplFileBeanObj));
        }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorApplFileDao ::: VendorLegalApplFileTxnHelper() :: Exception :: " + ex);
            throw ex;
        }
        return vendorapplFileBeanObj;
    }

    public LinkedList getVendorLegalApplFileList(VendorApplFileBean vendorapplFileBeanObj) throws Exception{
          LinkedList FileList = null;
        try {
            logger.log(Level.INFO, "OracleVendorApplFileDao ::: getVendorLegalApplFileList() :: method called ::    ");
            FileList = (LinkedList) getObjectList(new GetVendorLegalApplFileListQueryHelper(vendorapplFileBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorApplFileDao ::: getVendorLegalApplFileList() :: Exception :: " + ex);
            throw ex;
        }
        return FileList;
    }
}
