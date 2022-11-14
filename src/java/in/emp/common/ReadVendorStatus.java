/*
 * To change this template, choose Tools | Templates
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
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.VendorStatuBean;
import in.emp.vendor.dao.helper.FtpFileReadHelper.getVendorStatusTxnHelper;
import in.emp.vendor.manager.VendorManager;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.Connection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Level;

/**
 *
 * @author Pooja Jadhav
 */
public class ReadVendorStatus {
    private static Logger logger = Logger.getLogger(ReadVendorStatus.class);
     private static Connection conn = null;
 
     VendorDelegate vendorMgrObj = new VendorManager();
     List list;
     public  void readVendorFile() throws ParseException, Exception
    {
        
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
              String sapToVitsFullFilePath = new SimpleDateFormat("yyyyMMdd'.txt'").format(new Date());
InputStream stream = sftpChannel.get("/data/VPTS/Vendor_List_"+sapToVitsFullFilePath);
 String SFTPPROCESSEDDIR = "/data/VPTS/VENDOR_Processed";// copy files to processed folder
         String FileName="Vendor_List_" + sapToVitsFullFilePath;
         String SFTPERRORDIR = "/data/VPTS/VENDOR_Error";
         String Folder=new SimpleDateFormat("yyyy-MM").format(new Date());
          String[] folders = Folder.split("-");
List<VendorStatuBean>	listErpToVitsFileFormat	=	new ArrayList<VendorStatuBean>();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                
                String line;
                while ((line = br.readLine()) != null) {
                    //System.out.println(line);
                     String[]  values = line.split("[|]",12);
                      VendorStatuBean vendorstatusbeanobj= new VendorStatuBean();
                                            vendorstatusbeanobj.setVENDOR_CODE(values[0]);               
                                            vendorstatusbeanobj.setVENDOR_NAME(values[1]);
                                            vendorstatusbeanobj.setGST_REG_NUM(values[2]);
                                            vendorstatusbeanobj.setCITY(values[3]);
                                            vendorstatusbeanobj.setPINCODE(values[4]);
                                            vendorstatusbeanobj.setVENDOR_REGION(values[5]);
                                            vendorstatusbeanobj.setVENDOR_STATUS(values[6]);
                                            vendorstatusbeanobj.setPHN_LL(values[7]); 
                                            vendorstatusbeanobj.setPHN_MOB(values[8]);
                                            vendorstatusbeanobj.setEMAIL(values[9]);
                                            vendorstatusbeanobj.setPAN_NO(values[10]);
                                          
                                            vendorstatusbeanobj.setTAX_CODE(values[11]);
                                            
                                            
                                    if (vendorstatusbeanobj != null) {
                                        listErpToVitsFileFormat.add(vendorstatusbeanobj);
                                    }
                                       
                                //    System.out.println(Arrays.toString(values));
					
  
                                   //     System.out.println(); 
                     
                }
                  getVendorStatusTxnHelper del =new getVendorStatusTxnHelper();
                conn = ApplicationUtils.getConnection();
           
                del.deleteObject(conn);
                logger.log(Level.INFO, "ReadVENDORStatus ::: ReadVENDORStatusFile() :: method called ::delete method ended");
              //  System.out.println("Reading Vendor File : /data/VPTS/Vendor_List_"+sapToVitsFullFilePath);
               Partition<VendorStatuBean> partitioned = Partition.ofSize(listErpToVitsFileFormat, targetSize); 
              //  list =vendorMgrObj.getVendorStatus(listErpToVitsFileFormat);
 List<VendorStatuBean>[] array = partitioned.toArray(new List[partitioned.size()]);
    
      //  System.out.println("Size of array reading through file :"+array.length);
        if(array!=null)
        {
            int count=0;
        
         for(   List<VendorStatuBean> pobean : array )
 {
     
         ++count;
       //  System.out.println("In Array reading chunk "+count);
         list = vendorMgrObj.getVendorStatus(pobean);
     
 }

          vendorMgrObj.getVendorStatusProcedure();
         
        
        }
            sftpChannel.cd(SFTPPROCESSEDDIR);
            for (String folder : folders) {
    if (folder.length() > 0 && !folder.contains(".")) {
      // This is a valid folder:
      try {
        sftpChannel.cd(folder);
        SFTPPROCESSEDDIR+="/"+folder;
      } catch (SftpException e) {
        // No such folder yet:
        sftpChannel.mkdir(folder);
        sftpChannel.cd(folder);
        SFTPPROCESSEDDIR+="/"+folder;
      }
    }
  }
            
    sftpChannel.rename("/data/VPTS/"+FileName, SFTPPROCESSEDDIR+"/"+FileName); 
            } catch (IOException io) {
                 sftpChannel.rename("/data/VPTS/"+FileName, SFTPERRORDIR+"/"+FileName); 
                System.out.println("Exception occurred during reading file from SFTP server due to " + io.getMessage());
               logger.log(Level.WARN, "ReadPOStatus :: Exception :: " + io);
               io.getMessage();

            } catch (Exception e) {
                 sftpChannel.rename("/data/VPTS/"+FileName, SFTPERRORDIR+"/"+FileName); 
                System.out.println("Exception occurred during reading file from SFTP server due to " + e.getMessage());
               
                logger.log(Level.WARN, "ReadPOStatus :: Exception :: " + e);
                e.getMessage();

            }

            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            logger.log(Level.WARN, "ReadPOStatus :: Exception :: " + e);
           // //e.printStackTrace();
        } catch (SftpException e) {
            logger.log(Level.WARN, "ReadPOStatus :: Exception :: " + e);
           e.printStackTrace();
        }

        
      }

  

}