/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import in.emp.legal.scheduler.ReadLegalInvoiceFileStatus;
import org.apache.log4j.Logger;
/**
 *
 * @author MSEDCL
 */
public class SFTPConnection {
    
    private static Logger logger = Logger.getLogger(SFTPConnection.class);
    
    public Map<String,Object> getSFTPConnection(){
	    Map<String,Object> map=new HashMap<>();
		try{
			JSch jsch = new JSch();
			//SFTPUploadDto dto=getSFTPCredential();
                        //   String logFilePath = System.getProperty("catalina.base") + "/logs";
                        String sftpUser=System.getProperty("sftp.username");
                        String sftpPassword=System.getProperty("sftp.password");
                          String sftpPort=System.getProperty("sftp.port");
                          String sftpUrl=System.getProperty("sftp.url");
			Session session = jsch.getSession(sftpUser,sftpUrl,Integer.parseInt(sftpPort));
			session.setPassword(sftpPassword);

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setTimeout(1000*60*10);
			session.connect();
			
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp) channel;
			map.put("channelSftp", channelSftp);
			map.put("session", session);
			}catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		return map;
    }
}
