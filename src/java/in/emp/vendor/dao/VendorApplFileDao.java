/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.vendor.dao;

import java.util.LinkedList;

import in.emp.vendor.bean.VendorApplFileBean;

/**
 *
 * @author Prajakta
 */
public interface VendorApplFileDao {

    public LinkedList getVendorApplFileList(VendorApplFileBean vendorapplFileBeanObj) throws Exception;

    public VendorApplFileBean getVendorApplFile(VendorApplFileBean vendorapplFileBeanObj) throws Exception;

    public VendorApplFileBean VendorApplFileTxnHelper(VendorApplFileBean vendorapplFileBeanObj) throws Exception;

   public VendorApplFileBean VendorApplFileDelHelper(VendorApplFileBean vendorapplFileBeanObj) throws Exception;
}
