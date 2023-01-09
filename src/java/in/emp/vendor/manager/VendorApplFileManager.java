/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.manager;

import in.emp.master.dao.MasterDao;
import in.emp.vendor.VendorApplFileDelegate;
import in.emp.vendor.bean.VendorApplFileBean;
import in.emp.vendor.bean.VendorApplFilePrezData;
import in.emp.vendor.dao.OracleVendorApplFileDao;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class VendorApplFileManager implements VendorApplFileDelegate {

    private static String CLASS_NAME = VendorApplFileManager.class.getName();
    private static Logger logger = Logger.getLogger(VendorApplFileManager.class);

    public VendorApplFileManager() {
    }

    public LinkedList getVendorApplFileList(VendorApplFileBean vendorapplFileBeanObj) {
        VendorApplFilePrezData vendorapplFilePrezDataObj = null;
        OracleVendorApplFileDao vendorapplFileDaoObj = null;
        VendorApplFileBean vendorapplFileBean = null;
        LinkedList vendorapplFileList = null;
        MasterDao masterDao = null;
        try {
            logger.log(Level.INFO, " VendorApplFileManager :: getVendorApplFileList() :: method called");

            //-- create the dao object
            vendorapplFileDaoObj = new OracleVendorApplFileDao();

            vendorapplFileList = vendorapplFileDaoObj.getVendorApplFileList(vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorApplManagerFile :: getVendorApplFileList() :: Exception :: " + ex);

        }
        return vendorapplFileList;
    }

         public LinkedList getVendorPOFileList(VendorApplFileBean vendorapplFileBeanObj) {
        VendorApplFilePrezData vendorapplFilePrezDataObj = null;
        OracleVendorApplFileDao vendorapplFileDaoObj = null;
        VendorApplFileBean vendorapplFileBean = null;
        LinkedList vendorapplFileList = null;
        MasterDao masterDao = null;
        try {
            logger.log(Level.INFO, " VendorPOFileManager :: getVendorPOFileList() :: method called");

            //-- create the dao object
            vendorapplFileDaoObj = new OracleVendorApplFileDao();

            vendorapplFileList = vendorapplFileDaoObj.getVendorPOFileList(vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorPOManagerFile :: getVendorPOFileList() :: Exception :: " + ex);

        }
        return vendorapplFileList;
    }
   

    
    public VendorApplFileBean getVendorApplFile(VendorApplFileBean vendorapplFileBeanObj) {
        VendorApplFilePrezData vendorapplFilePrezDataObj = null;
        OracleVendorApplFileDao vendorapplFileDaoObj = null;
        MasterDao masterDao = null;
        try {
            logger.log(Level.INFO, " VendorApplFileManager :: getVendorApplFile() :: method called");

            //-- create the dao object
            vendorapplFileDaoObj = new OracleVendorApplFileDao();

            vendorapplFileBeanObj = vendorapplFileDaoObj.getVendorApplFile(vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorApplManagerFile :: getVendorApplFile() :: Exception :: " + ex);

        }
        return vendorapplFileBeanObj;
    }
    public VendorApplFileBean getVendorPOFile(VendorApplFileBean vendorapplFileBeanObj) {
        VendorApplFilePrezData vendorapplFilePrezDataObj = null;
        OracleVendorApplFileDao vendorapplFileDaoObj = null;
        MasterDao masterDao = null;
        try {
            logger.log(Level.INFO, " VendorApplFileManager :: getVendorPOFile() :: method called");

            //-- create the dao object
            vendorapplFileDaoObj = new OracleVendorApplFileDao();

            vendorapplFileBeanObj = vendorapplFileDaoObj.getVendorPOFile(vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorApplManagerFile :: getVendorPOFile() :: Exception :: " + ex);

        }
        return vendorapplFileBeanObj;
    }

    public VendorApplFileBean VendorApplFileTxnHelper(VendorApplFileBean vendorapplFileBeanObj) {
        VendorApplFilePrezData vendorapplFilePrezDataObj = null;
        OracleVendorApplFileDao vendorapplFileDaoObj = null;
        LinkedList townType = null;
        MasterDao masterDao = null;
        try {
            logger.log(Level.INFO, " VendorApplFileManager :: VendorApplFileTxnHelper() :: method called");

            //-- create the dao object
            vendorapplFileDaoObj = new OracleVendorApplFileDao();

            vendorapplFileBeanObj = vendorapplFileDaoObj.VendorApplFileTxnHelper(vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorApplFileManager :: VendorApplFileTxnHelper() :: Exception :: " + ex);
        }
        return vendorapplFileBeanObj;
    }
    public VendorApplFileBean VendorPOFileTxnHelper(VendorApplFileBean vendorapplFileBeanObj) {
        VendorApplFilePrezData vendorapplFilePrezDataObj = null;
        OracleVendorApplFileDao vendorapplFileDaoObj = null;
        LinkedList townType = null;
        MasterDao masterDao = null;
        try {
            logger.log(Level.INFO, " VendorPOFileManager :: VendorPOFileTxnHelper() :: method called");

            //-- create the dao object
            vendorapplFileDaoObj = new OracleVendorApplFileDao();

            vendorapplFileBeanObj = vendorapplFileDaoObj.VendorPOFileTxnHelper(vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorPOFileManager :: VendorPOFileTxnHelper() :: Exception :: " + ex);
        }
        return vendorapplFileBeanObj;
    }

    public VendorApplFileBean VendorApplFileDelHelper(VendorApplFileBean vendorapplFileBeanObj) {
        VendorApplFilePrezData vendorapplFilePrezDataObj = null;
        OracleVendorApplFileDao vendorapplFileDaoObj = null;
        try {
            logger.log(Level.INFO, " VendorApplFileManager :: VendorApplFileDelHelper() :: method called");

            //-- create the dao object
            vendorapplFileDaoObj = new OracleVendorApplFileDao();

            vendorapplFileBeanObj = vendorapplFileDaoObj.VendorApplFileDelHelper(vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorApplFileManager :: VendorApplFileDelHelper() :: Exception :: " + ex);
        }
        return vendorapplFileBeanObj;
    }
    public VendorApplFileBean VendorPOFileDelHelper(VendorApplFileBean vendorapplFileBeanObj) {
        VendorApplFilePrezData vendorapplFilePrezDataObj = null;
        OracleVendorApplFileDao vendorapplFileDaoObj = null;
        try {
            logger.log(Level.INFO, " VendorPOFileManager :: VendorPOFileDelHelper() :: method called");

            //-- create the dao object
            vendorapplFileDaoObj = new OracleVendorApplFileDao();

            vendorapplFileBeanObj = vendorapplFileDaoObj.VendorPOFileDelHelper(vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorPOFileManager :: VendorPOFileDelHelper() :: Exception :: " + ex);
        }
        return vendorapplFileBeanObj;
    }
     public VendorApplFileBean VendorLegalApplFileTxnHelper(VendorApplFileBean vendorapplFileBeanObj)throws Exception{
         VendorApplFilePrezData vendorapplFilePrezDataObj = null;
        OracleVendorApplFileDao vendorapplFileDaoObj = null;
        LinkedList townType = null;
        MasterDao masterDao = null;
        try {
            logger.log(Level.INFO, " VendorApplFileManager :: VendorApplFileTxnHelper() :: method called");

            //-- create the dao object
            vendorapplFileDaoObj = new OracleVendorApplFileDao();

            vendorapplFileBeanObj = vendorapplFileDaoObj.VendorLegalApplFileTxnHelper(vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorApplFileManager :: VendorApplFileTxnHelper() :: Exception :: " + ex);
        }
        return vendorapplFileBeanObj;
     }

     public VendorApplFileBean legalInvoiceFileDelHelper(VendorApplFileBean vendorapplFileBeanObj) {
       
        OracleVendorApplFileDao vendorapplFileDaoObj = null;
        try {
            logger.log(Level.INFO, " VendorApplFileManager :: legalInvoiceFileDelHelper() :: method called");

            //-- create the dao object
            vendorapplFileDaoObj = new OracleVendorApplFileDao();

            vendorapplFileBeanObj = vendorapplFileDaoObj.legalInvoiceFileDelHelper(vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorApplFileManager :: legalInvoiceFileDelHelper() :: Exception :: " + ex);
        }
        return vendorapplFileBeanObj;
    }
     
     
    @Override
    public LinkedList getVendorLegalApplFileList(VendorApplFileBean vendorapplFileBeanObj) {
        VendorApplFilePrezData vendorapplFilePrezDataObj = null;
        OracleVendorApplFileDao vendorapplFileDaoObj = null;
        VendorApplFileBean vendorapplFileBean = null;
        LinkedList vendorapplFileList = null;
        MasterDao masterDao = null;
        try {
            logger.log(Level.INFO, " VendorApplFileManager :: getVendorApplFileList() :: method called");

            //-- create the dao object
            vendorapplFileDaoObj = new OracleVendorApplFileDao();

            vendorapplFileList = vendorapplFileDaoObj.getVendorLegalApplFileList(vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorApplManagerFile :: getVendorApplFileList() :: Exception :: " + ex);

        }
        return vendorapplFileList;
    }
    
    
    
    public VendorApplFileBean getVendorLegalApplFile(VendorApplFileBean vendorapplFileBeanObj) {
        
        OracleVendorApplFileDao vendorapplFileDaoObj = null;
        MasterDao masterDao = null;
        try {
            logger.log(Level.INFO, " VendorApplFileManager :: getVendorLegalApplFile() :: method called");

            //-- create the dao object
            vendorapplFileDaoObj = new OracleVendorApplFileDao();

            vendorapplFileBeanObj = vendorapplFileDaoObj.getVendorLegalApplFile(vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorApplManagerFile :: getVendorLegalApplFile() :: Exception :: " + ex);

        }
        return vendorapplFileBeanObj;
    }
    
}
