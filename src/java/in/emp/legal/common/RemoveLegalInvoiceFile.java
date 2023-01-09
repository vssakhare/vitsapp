/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.legal.common;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
/**
 *
 * @author Pooja Jadhav
 */
public class RemoveLegalInvoiceFile {
    private static Logger logger = Logger.getLogger(RemoveLegalInvoiceFile.class);

    public  void RemoveFile(String filepath,String filename) {
         String SFTPHOST = System.getProperty("sftp.url");
         String SFTPUSER = System.getProperty("sftp.username");
            String SFTPPASS = System.getProperty("sftp.password");
       // String SFTPHOST = "10.0.2.188";
        int SFTPPORT = Integer.parseInt(System.getProperty("sftp.port"));
        
   
       // String SFTPUSER = "vptserp";
        //String SFTPPASS = "pass@123";
        String SFTPWORKINGDIR = filepath;
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
         

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
             channelSftp.rm(filename);
             String folder=filepath.substring(filepath.lastIndexOf("/")+1);
           String NEWSFTPWORKINGDIR=filepath.substring(0, filepath.lastIndexOf("/"));
            channelSftp.cd(NEWSFTPWORKINGDIR);
           System.out.println(NEWSFTPWORKINGDIR);
             channelSftp.rmdir(folder);
             
        }
        catch (Exception ex) {
            //ex.printStackTrace();
        }
    }
}
