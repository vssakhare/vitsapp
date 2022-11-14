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
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.PSBean;
import in.emp.vendor.dao.helper.FtpFileReadHelper.getPSStatusTxnhelper;
import in.emp.vendor.manager.VendorManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class ReadPsStatus {

    private static Logger logger = Logger.getLogger(ReadPsStatus.class);
    VendorDelegate vendorMgrObj = new VendorManager();
    private static Connection conn = null;
    List list;

    public void readPsFile() throws ParseException, Exception {
        JSch jsch = new JSch();
        Session session = null;
        int targetSize = 900;
        int i = 0;
        try {
            //session = jsch.getSession("vptserp", "10.0.2.188", 22);
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
        cal.add(Calendar.DATE, -2);
          String sapToVitsFullFilePath = new SimpleDateFormat("yyyyMMdd'.txt'").format(cal.getTime());
         //   String sapToVitsFullFilePath = new SimpleDateFormat("yyyyMMdd'.txt'").format(new Date());
            //System.out.println("/data/VPTS/PS_INV_Status_" + sapToVitsFullFilePath);
            InputStream stream = sftpChannel.get("/data/VPTS/PS_INV_Status_" + sapToVitsFullFilePath);
            String SFTPPROCESSEDDIR = "/data/VPTS/PS_Processed";//to copy files to processed folder

            String FileName = "PS_INV_Status_" + sapToVitsFullFilePath;
            String SFTPERRORDIR = "/data/VPTS/PS_Error";
            String Folder = new SimpleDateFormat("yyyy-MM").format(new Date());
            String[] folders = Folder.split("-");
            List<PSBean> lstErpToVitsFileFormat = null;
            lstErpToVitsFileFormat = new ArrayList<PSBean>();

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));

                String line;

                while ((line = br.readLine()) != null) {
                    ++i;
                    String[] values = line.split("[|]", 96);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                    PSBean psstatusbeanobj = new PSBean();
                    psstatusbeanobj.setPROJECT_ID(values[0]);
                    psstatusbeanobj.setPROJECT_DESC(values[1]);
                    if ((!ApplicationUtils.isBlank(values[2])) && !(values[2].startsWith("0000"))) {
                        psstatusbeanobj.setCREATION_DATE(formatter.parse(values[2]));
                    }
                    psstatusbeanobj.setPLANT(values[3]);
                    psstatusbeanobj.setZONE(values[4]);
                    psstatusbeanobj.setCIRCLE(values[5]);
                    psstatusbeanobj.setDIVISION(values[6]);
                    psstatusbeanobj.setSCHEME_CODE(values[7]);
                    psstatusbeanobj.setSCHEME_DESC(values[8]);
                    psstatusbeanobj.setMATERIAL_PO(values[9]);
                    psstatusbeanobj.setCENTAGES_PO(values[10]);
                    psstatusbeanobj.setSERVICE_PO(values[11]);
                    psstatusbeanobj.setVENDOR_NAME(values[12]);
                    psstatusbeanobj.setVENDOR_NUMBER(values[13]);
                    psstatusbeanobj.setINVNO(values[14]);
                    psstatusbeanobj.setTECH_STAT(values[15]);

                    psstatusbeanobj.setMATERIAL_WS_PARK_DOC(values[16]);
                    if ((!ApplicationUtils.isBlank(values[17])) && !(values[17].startsWith("0000"))) {
                        psstatusbeanobj.setMAT_WS_DOC_DATE(formatter.parse(values[17]));
                    }
                    psstatusbeanobj.setMAT_WS_PARK_AMT(values[18]);
                    psstatusbeanobj.setMAT_WS_CLEARING_DOC_NO(values[19]);
                    if ((!ApplicationUtils.isBlank(values[20])) && !(values[20].startsWith("0000"))) {
                        psstatusbeanobj.setMAT_WS_AC_DOC_DATE(formatter.parse(values[20]));
                    }
                    psstatusbeanobj.setMAT_WS_CLEARING_AMT(values[21]);

                    psstatusbeanobj.setCENTAGES_PARK_DOC(values[22]);
                    if ((!ApplicationUtils.isBlank(values[23])) && !(values[23].startsWith("0000"))) {
                        psstatusbeanobj.setCEN_DOC_DATE(formatter.parse(values[23]));
                    }
                    psstatusbeanobj.setCEN_PARK_AMT(values[24]);
                    psstatusbeanobj.setCEN_CLEARING_DOC_NO(values[25]);
                    if ((!ApplicationUtils.isBlank(values[26])) && !(values[26].startsWith("0000"))) {
                        psstatusbeanobj.setCEN_AC_DOC_DATE(formatter.parse(values[26]));
                    }
                    psstatusbeanobj.setCEN_CLEARING_AMT(values[27]);

                    psstatusbeanobj.setCIVIL_PARK_DOC(values[28]);
                    if ((!ApplicationUtils.isBlank(values[29])) && !(values[29].startsWith("0000"))) {
                        psstatusbeanobj.setCIVIL_DOC_DATE(formatter.parse(values[29]));
                    }
                    psstatusbeanobj.setCIVIL_PARK_AMT(values[30]);
                    psstatusbeanobj.setCIVIL_CLEARING_DOC_NO(values[31]);
                    if ((!ApplicationUtils.isBlank(values[32])) && !(values[32].startsWith("0000"))) {
                        psstatusbeanobj.setCIVIL_AC_DOC_DATE(formatter.parse(values[32]));
                    }
                    psstatusbeanobj.setCIVIL_CLEARING_AMT(values[33]);

                    psstatusbeanobj.setMAT_OS_PARK_DOC_NO(values[34]);
                    if ((!ApplicationUtils.isBlank(values[35])) && !(values[35].startsWith("0000"))) {
                        psstatusbeanobj.setMAT_OS_DOC_DATE(formatter.parse(values[35]));
                    }
                    psstatusbeanobj.setMAT_OS_PARK_AMT(values[36]);
                    psstatusbeanobj.setMAT_OS_CLEARING_DOC_NO(values[37]);
                    if ((!ApplicationUtils.isBlank(values[38])) && !(values[38].startsWith("0000"))) {
                        psstatusbeanobj.setMAT_OS_AC_DOC_DATE(formatter.parse(values[38]));
                    }
                    psstatusbeanobj.setMAT_OS_CLEARING_AMT(values[39]);

                    psstatusbeanobj.setOTH_PARK_DOC_NO(values[40]);
                    if ((!ApplicationUtils.isBlank(values[41])) && !(values[41].startsWith("0000"))) {
                        psstatusbeanobj.setOTH_DOC_DATE(formatter.parse(values[41]));
                    }
                    psstatusbeanobj.setOTH_PARK_AMT(values[42]);
                    psstatusbeanobj.setOTH_CLEARING_DOC_NO(values[43]);
                    if ((!ApplicationUtils.isBlank(values[44])) && !(values[44].startsWith("0000"))) {
                        psstatusbeanobj.setOTH_AC_DOC_DATE(formatter.parse(values[44]));
                    }
                    psstatusbeanobj.setOTH_CLEARING_AMT(values[45]);
                    psstatusbeanobj.setINV_STAT(values[46]);
                    psstatusbeanobj.setPO_MAT_AMT(values[47]);
                    psstatusbeanobj.setPO_CEN_AMT(values[48]);
                    psstatusbeanobj.setPO_CIV_AMT(values[49]);
                    psstatusbeanobj.setPO_INV_AMT(values[50]);
                    if ((!ApplicationUtils.isBlank(values[51])) && !(values[51].startsWith("0000"))) {
                        psstatusbeanobj.setINV_CREATIONDATE(formatter.parse(values[51]));
                    }
                    if ((!ApplicationUtils.isBlank(values[52])) && !(values[52].startsWith("0000"))) {
                        psstatusbeanobj.setINV_PSDATE(formatter.parse(values[52]));
                    }
                    psstatusbeanobj.setACC_STAT(values[53]);
                    psstatusbeanobj.setTAX_CODE(values[54]);
                    psstatusbeanobj.setINV_TYP(values[55]);
                    psstatusbeanobj.setWTAX_AMT(values[56].replaceAll("[-]*", ""));
                    psstatusbeanobj.setWIT_TDS(values[57].replaceAll("[-]*", ""));
                    psstatusbeanobj.setWGST_TDS(values[58].replaceAll("[-]*", ""));
                    psstatusbeanobj.setWRPST(values[59]);
                    psstatusbeanobj.setWRET_AMT(values[60].replaceAll("[-]*", ""));

                    psstatusbeanobj.setOTAX_AMT(values[61].replaceAll("[-]*", ""));
                    psstatusbeanobj.setOIT_TDS(values[62].replaceAll("[-]*", ""));
                    psstatusbeanobj.setOGST_TDS(values[63].replaceAll("[-]*", ""));
                    psstatusbeanobj.setORPST(values[64]);
                    psstatusbeanobj.setORET_AMT(values[65].replaceAll("[-]*", ""));

                    psstatusbeanobj.setCTAX_AMT(values[66].replaceAll("[-]*", ""));
                    psstatusbeanobj.setCIT_TDS(values[67].replaceAll("[-]*", ""));
                    psstatusbeanobj.setCGST_TDS(values[68].replaceAll("[-]*", ""));
                    psstatusbeanobj.setCRPST(values[69]);
                    psstatusbeanobj.setCRET_AMT(values[70].replaceAll("[-]*", ""));

                    psstatusbeanobj.setSTAX_AMT(values[71].replaceAll("[-]*", ""));
                    psstatusbeanobj.setSIT_TDS(values[72].replaceAll("[-]*", ""));
                    psstatusbeanobj.setSGST_TDS(values[73].replaceAll("[-]*", ""));
                    psstatusbeanobj.setSRPST(values[74]);
                    psstatusbeanobj.setSRET_AMT(values[75].replaceAll("[-]*", ""));

                    psstatusbeanobj.setOTTAX_AMT(values[76].replaceAll("[-]*", ""));
                    psstatusbeanobj.setOTIT_TDS(values[77].replaceAll("[-]*", ""));
                    psstatusbeanobj.setOTGST_TDS(values[78].replaceAll("[-]*", ""));
                    psstatusbeanobj.setOTRPST(values[79]);
                    psstatusbeanobj.setOTRET_AMT(values[80].replaceAll("[-]*", ""));
                    psstatusbeanobj.setSAP_APPL_ID(values[81].replaceAll("^0+", ""));
                    psstatusbeanobj.setVENDOR_INV_NO(values[82]);
                    psstatusbeanobj.setZONE_CODE(values[83]);
                    psstatusbeanobj.setCIRCLE_CODE(values[84]);
                    psstatusbeanobj.setMIGST_TX(values[85].replaceAll("[-]*", ""));
                    psstatusbeanobj.setMSGST_TX(values[86].replaceAll("[-]*", ""));
                    psstatusbeanobj.setOSGST_TX(values[87].replaceAll("[-]*", ""));
                    psstatusbeanobj.setOIGST_TX(values[88].replaceAll("[-]*", ""));
                    psstatusbeanobj.setCSGST_TX(values[89].replaceAll("[-]*", ""));
                    psstatusbeanobj.setCIGST_TX(values[90].replaceAll("[-]*", ""));
                    psstatusbeanobj.setSSGST_TX(values[91].replaceAll("[-]*", ""));
                    psstatusbeanobj.setSIGST_TX(values[92].replaceAll("[-]*", ""));
                    psstatusbeanobj.setOTSGST_TX(values[93].replaceAll("[-]*", ""));
                    psstatusbeanobj.setOTIGST_TX(values[94].replaceAll("[-]*", ""));
                    psstatusbeanobj.setPURCHASING_GROUP(values[95]);
                    lstErpToVitsFileFormat.add(psstatusbeanobj);

                }
                getPSStatusTxnhelper del = new getPSStatusTxnhelper();
                conn = ApplicationUtils.getConnection();
                del.deleteObject(conn);
                Partition<PSBean> partitioned = Partition.ofSize(lstErpToVitsFileFormat, targetSize);
                // Storing chunks of type List<PoStatusBean> in the array
               // System.out.println("Reading PS File : /data/VPTS/PS_INV_Status_" + sapToVitsFullFilePath);
                List<PSBean>[] array = partitioned.toArray(new List[partitioned.size()]);
              //  System.out.println("Size of array reading through file :" + array.length);
                if (array != null) {
                    int count = 0;
                    for (List<PSBean> psbean : array) {

                        ++count;
                     //   System.out.println("In Array reading chunk " + count);
                        list = vendorMgrObj.getPSStatus(psbean);

                    }
                }
                sftpChannel.cd(SFTPPROCESSEDDIR);
                for (String folder : folders) {
                    if (folder.length() > 0 && !folder.contains(".")) {
                        // This is a valid folder:
                        try {
                            sftpChannel.cd(folder);
                            SFTPPROCESSEDDIR += "/" + folder;
                        } catch (SftpException e) {
                            // No such folder yet:
                            sftpChannel.mkdir(folder);
                            sftpChannel.cd(folder);
                            SFTPPROCESSEDDIR += "/" + folder;
                        }
                    }
                }

                sftpChannel.rename("/data/VPTS/" + FileName, SFTPPROCESSEDDIR + "/" + FileName);
            } catch (IOException io) {
                sftpChannel.rename("/data/VPTS/" + FileName, SFTPERRORDIR + "/" + FileName);
                System.out.println("IO Exception occurred during reading file from SFTP server due to " + io.getMessage());
                logger.log(Level.WARN, "ReadPSStatus ::: IOException :: " + io);
                io.getMessage();

            } catch (Exception e) {
                sftpChannel.rename("/data/VPTS/" + FileName, SFTPERRORDIR + "/" + FileName);
                System.out.println("Exception occurred during reading file from SFTP server on line number " + i + " and  column number " + e.getMessage());
                logger.log(Level.WARN, "ReadPSStatus :: Exception :: " + e);
                e.getMessage();

            }
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            logger.log(Level.WARN, "ReadPSStatus :: Exception :: " + e);
            System.out.println("Exception occurred during reading file from SFTP server on line number " + i + " and  column number " + e.getMessage());

            //e.printStackTrace();
        } catch (SftpException e) {
            logger.log(Level.WARN, "ReadPSStatus :: Exception :: " + e);
            System.out.println("Exception occurred during reading file from SFTP server on line number " + i + " and  column number " + e.getMessage());

            //e.printStackTrace();
        }
    }
}
