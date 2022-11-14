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
import in.emp.vendor.bean.PoStatusBean;
import in.emp.vendor.dao.helper.FtpFileReadHelper.getPOStatusTxnhelper;
import in.emp.vendor.manager.VendorManager;
import java.io.BufferedReader;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Level;

/**
 *
 * @author Pooja Jadhav
 */
public class ReadPOStatus {

    private static Logger logger = Logger.getLogger(ReadPOStatus.class);
    VendorDelegate vendorMgrObj = new VendorManager();
    private static Connection conn = null;
    List list;

    public void readPOFile() throws ParseException, Exception {
        JSch jsch = new JSch();
        Session session = null;
         int targetSize = 900;
         int i=0;
        try {
             session = jsch.getSession("vptserp", "ftp-vpts-erp.mahadiscom.in", 22);
            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch.setConfig(config);
            //session.setPassword("pass@123");
            session.setPassword("Erp#V321");
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
              Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
          String sapToVitsFullFilePath = new SimpleDateFormat("yyyyMMdd'.txt'").format(cal.getTime());
          //  String sapToVitsFullFilePath = new SimpleDateFormat("yyyyMMdd'.txt'").format(new Date());
          //  System.out.println("/data/VPTS/PO_Status_" + sapToVitsFullFilePath);
            InputStream stream = sftpChannel.get("/data/VPTS/PO_Status_" + sapToVitsFullFilePath);
              String SFTPPROCESSEDDIR = "/data/VPTS/PO_STATUS_Processed";// copy files to processed folder
         String FileName="PO_Status_" + sapToVitsFullFilePath;
         String SFTPERRORDIR = "/data/VPTS/PO_STATUS_Error";
         String Folder=new SimpleDateFormat("yyyy-MM").format(new Date());
          String[] folders = Folder.split("-");
             List<PoStatusBean> lstErpToVitsFileFormat=null;
         lstErpToVitsFileFormat = new ArrayList<PoStatusBean>();
            
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));

                String line;
                 while ((line = br.readLine()) != null) {
                   ++i;
                 String[] values = line.split("[|]", 53);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                    PoStatusBean postatusbeanobj = new PoStatusBean();
                    postatusbeanobj.setZONE(values[0]);
                    postatusbeanobj.setCIRCLE(values[1]);
                    postatusbeanobj.setDIVISION(values[2]);
                    postatusbeanobj.setPLANT(values[3]);
                    postatusbeanobj.setPO_NUMBER(values[4]);
                    postatusbeanobj.setPO_DESC(values[5]);
                    postatusbeanobj.setPO_TYPE_CODE(values[6]);
                    postatusbeanobj.setPO_TYPE(values[7]);
                    if (!ApplicationUtils.isBlank(values[8])) {
                        postatusbeanobj.setPO_CREATION_DATE(formatter.parse(values[8]));
                    }
                    if (!ApplicationUtils.isBlank(values[9])) {
                        postatusbeanobj.setPO_DOC_DATE(formatter.parse(values[9]));
                    }
                    if (!ApplicationUtils.isBlank(values[10])) {
                        postatusbeanobj.setVALIDITY_START(formatter.parse(values[10]));
                    }
                    if (!ApplicationUtils.isBlank(values[11])) {
                        postatusbeanobj.setVALIDITY_END(formatter.parse(values[11]));
                    }
                    postatusbeanobj.setTOTAL_PO_AMT(values[12]);
                    postatusbeanobj.setVENDOR_NUMBER(values[13]);
                    postatusbeanobj.setVENDOR_NAME(values[14]);
                    postatusbeanobj.setSES_NO(values[15]);
                    postatusbeanobj.setSES_DESC(values[16]);
                    if (!ApplicationUtils.isBlank(values[17])) {
                        postatusbeanobj.setSES_CREATE_DATE(formatter.parse(values[17]));
                    }
                    if (!ApplicationUtils.isBlank(values[18])) {
                        postatusbeanobj.setSES_DOC_DATE(formatter.parse(values[18]));
                    }
                    postatusbeanobj.setSES_AMOUNT(values[19]);
                    postatusbeanobj.setRELEASE_STATUS(values[20]);
                    postatusbeanobj.setMIGO_DOC(values[21]);
                    postatusbeanobj.setDOC_YEAR(values[22]);
                    postatusbeanobj.setMIGO_AMT(values[23]);
                    postatusbeanobj.setPROFIT_CENTER(values[24]);
                    postatusbeanobj.setBAL_AMOUNT(values[25]);
                    postatusbeanobj.setVENDOR_INV_NO(values[26]);
                    if (!ApplicationUtils.isBlank(values[27])) {
                        postatusbeanobj.setINVOICE_DATE(formatter.parse(values[27]));
                    }
                    postatusbeanobj.setMSEDCL_INV_NO(values[28]);
                    postatusbeanobj.setINV_AMT(values[29]);
                    postatusbeanobj.setCL_DOC_NO(values[30]);
                    postatusbeanobj.setPAID_AMT(values[31]);
                    postatusbeanobj.setPO_SHORT_CLOSE(values[32]);
                    if (!ApplicationUtils.isBlank(values[33])) {
                        postatusbeanobj.setCL_DOC_DT(formatter.parse(values[33]));
                    }
                    if (!ApplicationUtils.isBlank(values[34])) {
                        postatusbeanobj.setMIGO_DT(formatter.parse(values[34]));
                    }
                    if (!ApplicationUtils.isBlank(values[35])) {
                        postatusbeanobj.setINV_DT(formatter.parse(values[35]));
                    }
                    postatusbeanobj.setTAX_CODE(values[36]);
                    postatusbeanobj.setTAX_AMOUNT(values[37]);
                    postatusbeanobj.setIT_TDS_AMOUNT(values[38]);
                    postatusbeanobj.setGST_TDS(values[39]);
                    postatusbeanobj.setRETENSION_AMOUNT(values[40]);
                    postatusbeanobj.setPROJECT_CODE(values[41]);
                    postatusbeanobj.setPROJECT_SCHEME(values[42]);
                    if (!ApplicationUtils.isBlank(values[43])) {
                        postatusbeanobj.setCREATE_DATE(formatter.parse(values[43]));

                    }
                      postatusbeanobj.setMSEDCL_PSINV_NO(values[44]);
                      postatusbeanobj.setSES_VEN_INV_NO(values[45]);
                   postatusbeanobj.setMIGO_VEN_INV_NO(values[46]);
                   postatusbeanobj.setPurchasing_group(values[47]);
                     postatusbeanobj.setMSEDCL_INV_NO_FLAG(values[48]);
                    postatusbeanobj.setMSEDCL_INV_NO_REVERSAL(values[49]);
                   postatusbeanobj.setSES_FLAG(values[50]);
                    postatusbeanobj.setPurchasing_desc(values[51]);
                    // postatusbeanobj.setPO_DESC_N(values[52]);
                      postatusbeanobj.setTENDER_LOA_LOE(values[52]);
                  // postatusbeanobj.setMIGO_FLAG(values[49]);
                        lstErpToVitsFileFormat.add(postatusbeanobj);
                        
                  }
              
                getPOStatusTxnhelper del =new getPOStatusTxnhelper();
                conn = ApplicationUtils.getConnection();
           
                del.deleteObject(conn);
                logger.log(Level.INFO, "ReadPOStatus ::: readPOFile() :: method called ::delete method ended");

              Partition<PoStatusBean> partitioned = Partition.ofSize(lstErpToVitsFileFormat, targetSize);
              logger.log(Level.INFO, "ReadPOStatus ::: readPOFile() :: method called ::partition method ended");
// Storing chunks of type List<PoStatusBean> in the array
//System.out.println("Reading PO File : /data/VPTS/PO_Status_" + sapToVitsFullFilePath);

         List<PoStatusBean>[] array = partitioned.toArray(new List[partitioned.size()]);
    
      //  System.out.println("Size of array reading through file :"+array.length);
        if(array!=null)
        {
            int count=0;
        
         for(   List<PoStatusBean> pobean : array )
 {
     
         ++count;
       //  System.out.println("In Array reading chunk "+count);
         list = vendorMgrObj.getPOStatus(pobean);
     
 }

          vendorMgrObj.getPOStatusProcedure();
         
        
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
                System.out.println("Exception occurred during reading file from SFTP server on line number "+ i+ " and  column number "+ e.getMessage());
               logger.log(Level.WARN, "ReadPOStatus :: Exception :: " + e);
               e.getMessage();

            }

            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            logger.log(Level.WARN, "ReadPOStatus :: Exception :: " + e);
           System.out.println("Exception occurred during reading file from SFTP server on line number "+ i+ " and  column number "+ e.getMessage());
            System.out.println("exception while reading file from ftp JSCH");
          //  //e.printStackTrace();
        } catch (SftpException e) {
            logger.log(Level.WARN, "ReadPOStatus :: Exception :: " + e);
          System.out.println("Exception occurred during reading file from SFTP server on line number "+ i+ " and  column number "+ e.getMessage());
             System.out.println("exception while reading file from ftp SFTP");
            //e.printStackTrace();
        }
    }
}