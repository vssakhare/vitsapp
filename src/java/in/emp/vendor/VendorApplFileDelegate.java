/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor;

import java.util.LinkedList;

import in.emp.vendor.bean.VendorApplFilePrezData;
import in.emp.vendor.bean.VendorApplFileBean;


/**
 *
 * @author Prajakta
 */
public interface VendorApplFileDelegate {

    public LinkedList getVendorApplFileList(VendorApplFileBean vendorapplFormBeanObj) throws Exception;
    
    public LinkedList getVendorPOFileList(VendorApplFileBean vendorapplFormBeanObj) throws Exception;

    public VendorApplFileBean getVendorApplFile(VendorApplFileBean vendorapplFormBeanObj) throws Exception;

     public VendorApplFileBean getVendorPOFile(VendorApplFileBean vendorapplFormBeanObj) throws Exception;
     
    public VendorApplFileBean VendorApplFileTxnHelper(VendorApplFileBean vendorapplFormBeanObj) throws Exception;
 
    public VendorApplFileBean VendorPOFileTxnHelper(VendorApplFileBean vendorapplFormBeanObj) throws Exception;
    
    public VendorApplFileBean VendorApplFileDelHelper(VendorApplFileBean vendorapplFormBeanObj) throws Exception;
    
    public VendorApplFileBean  legalInvoiceFileDelHelper(VendorApplFileBean vendorapplFormBeanObj) throws Exception;

   public VendorApplFileBean VendorPOFileDelHelper(VendorApplFileBean vendorapplFormBeanObj) throws Exception;

    public VendorApplFileBean VendorLegalApplFileTxnHelper(VendorApplFileBean vendorapplFileBeanObj)throws Exception;

    public LinkedList getVendorLegalApplFileList(VendorApplFileBean vendorapplFileBeanObj);
    
    public VendorApplFileBean getVendorLegalApplFile(VendorApplFileBean vendorapplFormBeanObj) throws Exception;


}
