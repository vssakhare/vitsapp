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
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.manager.VendorManager;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream ;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class WriteInvoiceStatus {
     private static Logger logger = Logger.getLogger(WriteInvoiceStatus.class);
    public static void WriteInvoiceFile() throws ParseException, Exception {
        
         JSch jsch = new JSch();
        Session session = null;
       List list;
        try {
         
        VendorDelegate vendorMgrObj = new VendorManager();
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
            session = jsch.getSession("vptserp", "ftp-vpts-erp.mahadiscom.in", 22);
            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch.setConfig(config);
            session.setPassword("Erp#V321");
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
             SimpleDateFormat sdfr = new SimpleDateFormat("dd-MMM-yyyy");
            String VitsToSapFullFilePath = new SimpleDateFormat("yyyyMMdd'.csv'").format(new Date());
             LinkedList<VendorInputBean> FileList = new LinkedList();
             String dateString3="";
        try{
            FileList = vendorMgrObj.putInvoiceStatus(vendorInputBeanObj);
          if (FileList.size() != 0)
            {
                 OutputStream  stream = sftpChannel.put("/data/VPTS/Invoice_Files/INVOICE_DATA_" + VitsToSapFullFilePath);
//                OutputStream  stream = new FileOutputStream("E:/INVOICE_DATA_" + VitsToSapFullFilePath);
                   BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(stream));
             for (VendorInputBean v : FileList) {
            pw.write(v.getApplId()+",");
            pw.write(v.getSerialNo()+",");
            String dateString1 = sdfr.format( v.getVendorUpdatedDate() );
            pw.write(dateString1+",");
                    pw.write(v.getVendorNumber()+",");
                    pw.write(v.getVendorName()+",");
                    pw.write(v.getProjectId()+",");
                    pw.write(v.getPONumber()+",");
                    pw.write(v.getINVOICE_TYPE()+",");
                    
                  //   pw.write(",");//inv create flag blank
                  //  pw.write(v.getPOType()+",");
                    pw.write(v.getPODesc()+",");
                    pw.write(v.getVendorInvoiceNumber()+",");
                    String dateString2 = sdfr.format( v.getVendorInvoiceDate() );
                    pw.write(dateString2+",");
                    pw.write(v.getVendorInvoiceAmount()+",");
                    pw.write(v.getInwardNumber()+",");
                    if( v.getInwardDate()!=null){
                     dateString3 = sdfr.format( v.getInwardDate() );
                    }
                    else{
                       dateString3 =""; 
                    }
                    pw.write(dateString3+",");
                    pw.write(v.getStatus());
                         
                    pw.newLine();
             }
              pw.close();
            }
        }catch(Exception e)
                {
                    logger.log(Level.WARN, "WriteInvoiceStatus :: Exception :: " + e); 
                    //e.printStackTrace();
                } 
       
            sftpChannel.exit();
            session.disconnect();
}
           catch (JSchException e) {
            logger.log(Level.WARN, "WriteInvoiceStatus :: Exception :: " + e);
            //e.printStackTrace();
        } 
        writeRetensionInvoiceStatus();
    }

    private static void writeRetensionInvoiceStatus() {
        JSch jsch = new JSch();
        Session session = null;
       List list;
        try {
            System.out.println("called");
        VendorDelegate vendorMgrObj = new VendorManager();
        VendorBean vendorBeanObj = new VendorBean();
            session = jsch.getSession("vptserp", "ftp-vpts-erp.mahadiscom.in", 22);
            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch.setConfig(config);
            session.setPassword("Erp#V321");
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
             SimpleDateFormat sdfr = new SimpleDateFormat("dd.MM.yyyy");
            String VitsToSapFullFilePath = new SimpleDateFormat("ddMMyyyy'.txt'").format(new Date());
             LinkedList<VendorBean> FileList = new LinkedList();
             String dateString3="";
        try{
            vendorBeanObj.setSubAction("writeFile");
            FileList = vendorMgrObj.putRetentionInvoiceStatus(vendorBeanObj);
          if (FileList.size() != 0)
            {
                 OutputStream  stream = sftpChannel.put("/data/VPTS/Retention/Retention_Claim_" + VitsToSapFullFilePath);
//                 OutputStream  stream = new FileOutputStream("E:/Retention_Claim_" + VitsToSapFullFilePath);
                   BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(stream));
             for (VendorBean v : FileList) {
            pw.write(v.getApplId()+"|");
            pw.write(v.getSesNum()+"|");
            String dateString1 = sdfr.format( v.getClaimDate() );
            pw.write(dateString1+"|");
                    pw.write(v.getVendorNumber()+"|");
                    pw.write(v.getVendorName()+"|");
                    pw.write(v.getProjectCode()+"|");
                    pw.write(v.getINV_TYP()+"|");
                    pw.write("Retention"+"|");
                    
                  //   pw.write(",");//inv create flag blank
                  //  pw.write(v.getPOType()+",");
                    pw.write(v.getInvoiceNumber()+"|");
                    String dateString2 = sdfr.format( v.getInvoiceDate() );
                    pw.write(dateString2+"|");
                    pw.write(v.getInvoiceAmount()+"|");
                    pw.write(v.getInwardNumber()+"|");
                    if( v.getInwardDate()!=null){
                     dateString3 = sdfr.format( v.getInwardDate() );
                    }
                    else{
                       dateString3 =""; 
                    }
                    pw.write(dateString3+"|");
                    pw.write(v.getMsedclInvoiceNumber()+"|");
                    if(v.getWRPST()!=null){
                    pw.write(v.getWRPST()+"|");
                    }else{
                      pw.write("|");
                    }

                    pw.write(v.getWRET_AMT());
                         
                    pw.newLine();
             }
              pw.close();
            }
        }catch(Exception e)
                {
                    logger.log(Level.WARN, "WriteInvoiceStatus :: Exception :: " + e); 
                    e.printStackTrace();
                } 
       
            sftpChannel.exit();
            session.disconnect();
}
           catch (JSchException e) {
            logger.log(Level.WARN, "WriteInvoiceStatus :: Exception :: " + e);
            //e.printStackTrace();
        } 
    }
}

