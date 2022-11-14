/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.VendorStatuBean;
import in.emp.vendor.dao.helper.FtpFileReadHelper.getVendorStatusTxnHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.sql.Connection;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.dao.helper.txnHelper.SaveVendorRetentionDetailsResponseTxnHelper;
import in.emp.vendor.manager.VendorManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;

/**
 *
 * @author Rikma Rai
 */
public class ReadRetentionResponseStatus {
private static Logger logger = Logger.getLogger(ReadVendorStatus.class);
     private static Connection conn = null;
 
     VendorDelegate vendorMgrObj = new VendorManager();
     List list;
    void readRetentionInvoiceFile() {
         JSch jsch = new JSch();
        Session session = null;
          int targetSize = 900;
        try{
            session = jsch.getSession("vptserp", "ftp-vpts-erp.mahadiscom.in", 22);
           final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch.setConfig(config);
            session.setPassword("Erp#V321");
            session.connect();
             Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
              String sapToVitsFullFilePath = new SimpleDateFormat("ddMMyyyy'.txt'").format(new Date());

InputStream stream=null; //comment on cloud
             try {
                 stream = sftpChannel.get("/data/VPTS/Retention/EV/Retention_Claim_FIDoc_"+sapToVitsFullFilePath); //uncomment on cloud
//                 stream = new FileInputStream("E:/Retention_Claim_FIDoc_"+sapToVitsFullFilePath);//comment on cloud
             } catch (Exception ex) {
                 java.util.logging.Logger.getLogger(ReadRetentionResponseStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             }
//            System.out.println("filename::"+"E:/Retention_Claim_FIDoc_"+sapToVitsFullFilePath);
         String SFTPPROCESSEDDIR = "/data/VPTS/Retention/Processed";// copy files to processed folder
         String FileName="Retention_Claim_FIDoc_" + sapToVitsFullFilePath;
         String SFTPERRORDIR = "/data/VPTS/Retention/Error";
         String Folder=new SimpleDateFormat("yyyy-MM").format(new Date());
            DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy");
          String[] folders = Folder.split("-");
List<VendorInputBean>	listErpToVitsFileFormat	=	new ArrayList<VendorInputBean>();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                
                String line;
                while ((line = br.readLine()) != null) {
                    //System.out.println(line);
                     String[]  values = line.split("[|]");
                      VendorInputBean vendorBean= new VendorInputBean();
                                            vendorBean.setApplId(values[0]);               
                                            vendorBean.setSerialNo(values[1]);
                                            vendorBean.setApplDate(dateFormat.parse(values[2]));
                                            vendorBean.setVendorNumber(values[3]);
                                            vendorBean.setVendorName(values[4]);
                                            vendorBean.setProjectId(values[5]);
                                            vendorBean.setINVOICE_TYPE(values[6]);
                                            vendorBean.setShortText(values[7]); 
                                            vendorBean.setVendorInvoiceNumber(values[8]);
                                            vendorBean.setVendorInvoiceDate(dateFormat.parse(values[9]));
                                            vendorBean.setInvAmt(values[10]);
                                            vendorBean.setInwardNumber(values[11]);
                                            vendorBean.setInwardDate(dateFormat.parse(values[12]));
                                            vendorBean.setMsedclInvoiceNumber(values[13]);
                                            vendorBean.setRetentionDocNo(values[14]);
                                            vendorBean.setFiDocNo(values[15]);
                                            vendorBean.setFiAmount(values[16]);
                                            if(values.length>17){
                                                if(values[17]!=null){
                                                    vendorBean.setNewDocNo(values[17]);
                                                }
                                                 if(values[18]!=null){
                                                   vendorBean.setNewDocAmount(values[18]); 
                                                }
                                            }
                                            
                                    if (vendorBean != null) {
                                        listErpToVitsFileFormat.add(vendorBean);
                                    }
                                       
                                //    System.out.println(Arrays.toString(values));
					
  
                                   //     System.out.println(); 
                     
                }
                conn = ApplicationUtils.getConnection();
           
                logger.log(Level.INFO, "ReadVENDORStatus ::: ReadVENDORStatusFile() :: method called ::delete method ended");
              //  System.out.println("Reading Vendor File : /data/VPTS/Vendor_List_"+sapToVitsFullFilePath);
               //Partition<VendorInputBean> partitioned = Partition.ofSize(listErpToVitsFileFormat, targetSize); 
              //  list =vendorMgrObj.getVendorStatus(listErpToVitsFileFormat);
 //List<VendorInputBean>[] array = partitioned.toArray(new List[partitioned.size()]);
    
        System.out.println("Size of array reading through file :"+listErpToVitsFileFormat.size());
        if(listErpToVitsFileFormat!=null)
        {
            int count=0;
        
//         for(   VendorInputBean pobean : listErpToVitsFileFormat )
// {
     
         ++count;
       //  System.out.println("In Array reading chunk "+count);
         list = vendorMgrObj.saveRetentionDetailsResponse(listErpToVitsFileFormat);
     
// }

          vendorMgrObj.updateRetentionDetailsResponseProcedure();
         
        
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
            
    sftpChannel.rename("/data/VPTS/Retention/EV/"+FileName, SFTPPROCESSEDDIR+"/"+FileName); //uncomment on cloud
            } catch (IOException io) {
                try {
                    sftpChannel.rename("/data/VPTS/Retention/EV/"+FileName, SFTPERRORDIR+"/"+FileName); //uncomment on cloud
                } catch (SftpException ex) {
                     logger.log(Level.WARN, "ReadRetentionResponseStatus :: Exception :: " + ex);
                }
                System.out.println("Exception occurred during reading file from SFTP server due to " + io.getMessage());
               logger.log(Level.WARN, "ReadRetentionResponseStatus :: Exception :: " + io);
               io.getMessage();
            } catch (Exception e) {
                try {
                    sftpChannel.rename("/data/VPTS/Retention/EV/"+FileName, SFTPERRORDIR+"/"+FileName); //uncomment on cloud
                } catch (SftpException ex) {
                    logger.log(Level.WARN, "ReadRetentionResponseStatus :: Exception :: " + ex);
                }
                System.out.println("Exception occurred during reading file from SFTP server due to " + e.getMessage());
               
                logger.log(Level.WARN, "ReadRetentionResponseStatus :: Exception :: " + e);
                e.getMessage();
                e.printStackTrace();

            }

            sftpChannel.exit();//uncomment on cloud
            session.disconnect();
        } catch (JSchException e) {
            logger.log(Level.WARN, "ReadRetentionResponseStatus :: Exception :: " + e);
           // //e.printStackTrace();
        } 
        /*catch (SftpException e) {//uncomment on cloud
            logger.log(Level.WARN, "ReadRetentionResponseStatus :: Exception :: " + e);
           e.printStackTrace();
        }*/

    }
    
}
