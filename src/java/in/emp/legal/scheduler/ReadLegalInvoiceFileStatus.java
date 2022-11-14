/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.scheduler;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import in.emp.common.Partition;
import in.emp.common.ReadRetentionResponseStatus;
import in.emp.common.ReadVendorStatus;
import in.emp.legal.bean.LegalInvoiceBean;
import in.emp.legal.dao.helper.txnhelper.ErpLegalInvoiceStatusTxnHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.PoStatusBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.manager.VendorManager;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Rikma Rai
 */
public class ReadLegalInvoiceFileStatus {

    private static Logger logger = Logger.getLogger(ReadLegalInvoiceFileStatus.class);
    private static Connection conn = null;

    VendorDelegate vendorMgrObj = new VendorManager();
    List list;

    void readLegalInvoiceFile() throws ParseException, Exception {
        System.out.println("starting readLegalInvoiceFile");
        JSch jsch = new JSch();
        Session session = null;
        int targetSize = 900;
        try {
            session = jsch.getSession("vptserp", "ftp-vpts-erp.mahadiscom.in", 22);
            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch.setConfig(config);
            session.setPassword("Erp#V321");
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            String sapToVitsFullFilePath = new SimpleDateFormat("yyyyMMdd'.txt'").format(new Date());

            InputStream stream = null; //comment on cloud
            try {
//                 stream = sftpChannel.get("/data/VPTS/Retention/EV/Legal_inv_Status_"+sapToVitsFullFilePath); //uncomment on cloud
                stream = new FileInputStream("E:/Legal_inv_Status_" + sapToVitsFullFilePath);//comment on cloud
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ReadRetentionResponseStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            System.out.println("filename::" + sapToVitsFullFilePath);
            String SFTPPROCESSEDDIR = "/data/VPTS/Retention/Processed";// copy files to processed folder
            String FileName = "Legal_inv_Status_" + sapToVitsFullFilePath;
            String SFTPERRORDIR = "/data/VPTS/Retention/Error";
            String Folder = new SimpleDateFormat("yyyy-MM").format(new Date());
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            String[] folders = Folder.split("-");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            List<LegalInvoiceBean> listErpToVitsFileFormat = new ArrayList<LegalInvoiceBean>();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));

                String line;
                while ((line = br.readLine()) != null) {
                    //System.out.println(line);
                    String[] values = line.split("[|]");
                    LegalInvoiceBean legalInvoiceBean = new LegalInvoiceBean();
                    legalInvoiceBean.setCASEREFNO(Integer.parseInt(values[0]));
                    if (!ApplicationUtils.isBlank(values[1])) {
                        legalInvoiceBean.setYEAR_L(Integer.parseInt(values[1]));
                    }
                    if (!ApplicationUtils.isBlank(values[2])) {
                        legalInvoiceBean.setDOF_LC(formatter.parse(values[2]));
                    }
                    legalInvoiceBean.setVENDOR(values[3]);
                    legalInvoiceBean.setINVOICE_LEGAL(values[4]);
                    legalInvoiceBean.setADV_FEE_TYPE(values[5]);
                    if (!ApplicationUtils.isBlank(values[6])) {
                        legalInvoiceBean.setINVOICE_DATE(formatter.parse(values[6]));
                    }
                    legalInvoiceBean.setADVOCATE_NAME(values[7]);
                    legalInvoiceBean.setADVOCATE_TYPE(values[8]);
                    if (!ApplicationUtils.isBlank(values[9])) {
                        legalInvoiceBean.setINVOICE_AMOUNT(Double.parseDouble(values[9]));
                    }
                    if (!ApplicationUtils.isBlank(values[10])) {
                        legalInvoiceBean.setRECIEPT_DATE(formatter.parse(values[10]));
                    }
                    if (!ApplicationUtils.isBlank(values[11])) {
                        legalInvoiceBean.setFEE_RECOMMENDED(Double.parseDouble(values[11]));
                    }
                    legalInvoiceBean.setREASON_FOR_DEDUCTION(values[12]);
                    legalInvoiceBean.setAPPROVED_BY(values[13]);
                    if (!ApplicationUtils.isBlank(values[14])) {
                        legalInvoiceBean.setDATE_OF_APPROVAL(formatter.parse(values[14]));
                    }
                    if (!ApplicationUtils.isBlank(values[15])) {
                        legalInvoiceBean.setDATE_OF_SUBMISSION(formatter.parse(values[15]));
                    }
                    legalInvoiceBean.setSAC_CODE(values[16]);
                    legalInvoiceBean.setSTATUS_FEE(values[17]);
                    legalInvoiceBean.setZZADV_PAN_NO(values[18]);
                    if (!ApplicationUtils.isBlank(values[19])) {
                        legalInvoiceBean.setZZELIGIBLE_FEE(Double.parseDouble(values[19]));
                    }
                    legalInvoiceBean.setZZPARK_POST_DOC_NO(values[20]);
                    if (!ApplicationUtils.isBlank(values[21])) {
                        legalInvoiceBean.setZZPARK_DOC_AMT(Double.parseDouble(values[21]));
                    }
                    if (!ApplicationUtils.isBlank(values[22])) {
                        legalInvoiceBean.setZZPARK_POST_DATE(formatter.parse(values[22]));
                    }
                    legalInvoiceBean.setZZPAY_DONE_ERP_DOC(values[23]);
                    if (!ApplicationUtils.isBlank(values[24])) {
                        legalInvoiceBean.setZZPAY_DOC_AMT(Double.parseDouble(values[24]));
                    }
                    if (!ApplicationUtils.isBlank(values[25])) {
                        legalInvoiceBean.setZZFEE_DT_OF_PAYMENT(formatter.parse(values[25]));
                    }
                    legalInvoiceBean.setZZFI_COMP_CODE(values[26]);
                    if (!ApplicationUtils.isBlank(values[27])) {
                        legalInvoiceBean.setZZFI_FIS_YR(Integer.parseInt(values[27]));
                    }
                    if (!ApplicationUtils.isBlank(values[28])) {
                        legalInvoiceBean.setZZPOST_DATE(formatter.parse(values[28]));
                    }
                    if (!ApplicationUtils.isBlank(values[29])) {
                        legalInvoiceBean.setZZPOST_FISCAL(Integer.parseInt(values[29]));
                    }
                    legalInvoiceBean.setZZUTR_NO(values[30]);
                    legalInvoiceBean.setCASENO(values[31]);
                    legalInvoiceBean.setCASENOCOURT(values[32]);
                    if (!ApplicationUtils.isBlank(values[33])) {
                        legalInvoiceBean.setCORT_KEY(Integer.parseInt(values[33]));
                    }
                    legalInvoiceBean.setCOURTNAME(values[34]);
                    legalInvoiceBean.setCOOFFICE_BTRTL(values[35]);
                    legalInvoiceBean.setCOOFFICE_TEXT(values[36]);
                    legalInvoiceBean.setREGION_BTRTL(values[37]);
                    legalInvoiceBean.setREGION_BTRTL_TEXT(values[38]);
                    legalInvoiceBean.setZZONE_BTRTL(values[39]);
                    legalInvoiceBean.setZZONE_BTRTL_TEXT(values[40]);
                    legalInvoiceBean.setCIRCLE_BTRTL(values[41]);
                    legalInvoiceBean.setCIRCLE_BTRTL_TEXT(values[42]);
                    legalInvoiceBean.setDIVISION_BTRTL(values[43]);
                    legalInvoiceBean.setDIVISION_BTRTL_TEXT(values[44]);
                    legalInvoiceBean.setSUBDIV_BTRTL(values[45]);
                    legalInvoiceBean.setSUBDIV_BTRTL_TEXT(values[46]);
                    legalInvoiceBean.setSECTION_BTRTL(values[47]);
                    legalInvoiceBean.setSECTION_BTRTL_TEXT(values[48]);
                    legalInvoiceBean.setSUBSTATION(values[49]);
                    legalInvoiceBean.setSUBSTATION_TEXT(values[50]);
                    legalInvoiceBean.setDSD(values[51]);
                    legalInvoiceBean.setCASETYPE(values[52]);
                    legalInvoiceBean.setCASETYPEDESC(values[53]);
                    legalInvoiceBean.setCASEDET(values[54]);
                    if (!ApplicationUtils.isBlank(values[55])) {
                        legalInvoiceBean.setCASESTAT(Integer.parseInt(values[55]));
                    }
                    legalInvoiceBean.setCASESTATDESC(values[56]);
                    legalInvoiceBean.setCreatedBy("Admin");
                    legalInvoiceBean.setCreatedDt(new Date());

                    if (legalInvoiceBean != null) {
                        listErpToVitsFileFormat.add(legalInvoiceBean);
                    }

                    //    System.out.println(Arrays.toString(values));
                    //     System.out.println(); 
                }
                ErpLegalInvoiceStatusTxnHelper del = new ErpLegalInvoiceStatusTxnHelper();
                try {
                    conn = ApplicationUtils.getConnection();
                    del.deleteObject(conn);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(ReadLegalInvoiceFileStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                Partition<LegalInvoiceBean> partitioned = Partition.ofSize(listErpToVitsFileFormat, targetSize);
                logger.log(Level.INFO, "ReadLegalInvoiceFileStatus ::: ReadLegalInvoiceFileStatus() :: method called ::delete method ended");
                //  System.out.println("Reading Vendor File : /data/VPTS/Vendor_List_"+sapToVitsFullFilePath);
                //Partition<VendorInputBean> partitioned = Partition.ofSize(listErpToVitsFileFormat, targetSize); 
                //  list =vendorMgrObj.getVendorStatus(listErpToVitsFileFormat);
                //List<VendorInputBean>[] array = partitioned.toArray(new List[partitioned.size()]);

                System.out.println("Size of array reading through file :" + listErpToVitsFileFormat.size());
                List<LegalInvoiceBean>[] array = partitioned.toArray(new List[partitioned.size()]);
                if (array != null) {
                    int count = 0;

                    for (List<LegalInvoiceBean> legalInvoiceBeans : array) {

                        ++count;
                        try {
                            System.out.println("In Array reading chunk " + count);
                            list = vendorMgrObj.saveLegalInvoiceStatus(legalInvoiceBeans);
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(ReadLegalInvoiceFileStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }

                    }
                }

                // sftpChannel.cd(SFTPPROCESSEDDIR);//uncomment on cloud
                for (String folder : folders) {
                    if (folder.length() > 0 && !folder.contains(".")) {
                        // This is a valid folder:
//      try {
//        sftpChannel.cd(folder);
//        SFTPPROCESSEDDIR+="/"+folder;
//      } catch (SftpException e) {
//        // No such folder yet:
//        sftpChannel.mkdir(folder);
//        sftpChannel.cd(folder);
//        SFTPPROCESSEDDIR+="/"+folder;
//      }
                    }
                }

                sftpChannel.rename("/data/VPTS/Retention/EV/" + FileName, SFTPPROCESSEDDIR + "/" + FileName); //uncomment on cloud
            } catch (IOException io) {
                try {
                    sftpChannel.rename("/data/VPTS/Retention/EV/" + FileName, SFTPERRORDIR + "/" + FileName); //uncomment on cloud
                } catch (SftpException ex) {
                    logger.log(Level.WARN, "ReadLegalInvoiceFileStatus :: Exception :: " + ex);
                }
                System.out.println("Exception occurred during reading file from SFTP server due to " + io.getMessage());
                logger.log(Level.WARN, "ReadLegalInvoiceFileStatus :: Exception :: " + io);
                io.getMessage();
            } catch (Exception e) {
                try {
                    sftpChannel.rename("/data/VPTS/Retention/EV/" + FileName, SFTPERRORDIR + "/" + FileName); //uncomment on cloud
                } catch (SftpException ex) {
                    logger.log(Level.WARN, "ReadLegalInvoiceFileStatus :: Exception :: " + ex);
                }
                System.out.println("Exception occurred during reading file from SFTP server due to " + e.getMessage());

                logger.log(Level.WARN, "ReadLegalInvoiceFileStatus :: Exception :: " + e);
                e.getMessage();
                e.printStackTrace();

            }

            sftpChannel.exit();//uncomment on cloud
            session.disconnect();
        } catch (JSchException e) {
            logger.log(Level.WARN, "ReadLegalInvoiceFileStatus :: Exception :: " + e);
            // //e.printStackTrace();
        }
        /*catch (SftpException e) {//uncomment on cloud
            logger.log(Level.WARN, "ReadRetentionResponseStatus :: Exception :: " + e);
           e.printStackTrace();
        }*/

    }
}
