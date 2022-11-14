/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import in.emp.common.ApplicationConstants;
import org.apache.log4j.Logger;
/**
 *
 * @author Pooja Jadhav
 */
public class UploadVendorFile {
    private static Logger logger = Logger.getLogger(UploadVendorFile.class);

    public  String UploadFile(byte[] FILETOTRANSFER,String filename,String location,String foldername) {
        //String SFTPHOST = "10.0.2.188";
        String SFTPHOST = "ftp-vpts-erp.mahadiscom.in";
        int SFTPPORT = 22;
        String SFTPUSER = "vptserp";
       
        //String SFTPPASS = "pass@123";
        String SFTPPASS = "Erp#V321";

        String SFTPWORKINGDIR =ApplicationConstants.SFTPWORKINGDIR+location;  
        // String SFTPWORKINGDIR = "/home/sap_interface/VPTS/VENDOR_FILES/"+location;
        String Folder=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String InFolder = foldername;

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
           String[] folders = Folder.split("-");

        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);
              // Build romote path subfolders inclusive:
  

  for (String folder : folders) {
    if (folder.length() > 0 && !folder.contains(".")) {
      // This is a valid folder:
      try {
        channelSftp.cd(folder);
      } catch (SftpException e) {
        // No such folder yet:
        channelSftp.mkdir(folder);
        channelSftp.cd(folder);
      }
    }
  }

  
         if (InFolder.length() > 0 && !InFolder.contains(".")) {
             try{
               channelSftp.cd(InFolder);  
             }catch (SftpException ex) {
        channelSftp.mkdir(InFolder);
                channelSftp.cd(InFolder);
             }
         }
      
    
  
              File file =null;
       FileInputStream fis=null;

             file = writeByteArrayTo(FILETOTRANSFER, filename);
                                        fis=new FileInputStream(file);
                                        channelSftp.put(fis, filename);
                                       // log.info("file uploaded successfully....");
                                      file.delete();

       
            
       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SFTPWORKINGDIR+"/"+folders[0]+"/"+folders[1]+"/"+folders[2]+"/"+InFolder+"/"+filename;
       
    }
   
    
        public File writeByteArrayTo(byte[] media, String fileName){
          
                File file = new File(ApplicationConstants.Sftppath+fileName);
                FileOutputStream fOut = null;
                try{
                        fOut =  new FileOutputStream(file);
                        fOut.write(media);
                }catch (Exception e) {
                        //log.error("Exception", e);
                    //e.printStackTrace();
                        return null;
                }finally {
                        closeOutputStream(fOut);
                }
                return file;
        }
         private void closeOutputStream(OutputStream out){
                if(out ==null){
                        return;
                }
                try{
                        out.flush();
                        out.close();
                }catch (Exception e) {
                       // log.error("Exception", e);
                }
        }



    }

