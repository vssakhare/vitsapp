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
import in.emp.vendor.bean.PoLineStatusBean;
import in.emp.vendor.dao.helper.FtpFileReadHelper.getPOLineStatusTxnhelper;
import in.emp.vendor.manager.VendorManager;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
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
public class ReadPOLineStatus {

    private static Logger logger = Logger.getLogger(ReadPOStatus.class);
    VendorDelegate vendorMgrObj = new VendorManager();
    private static Connection conn = null;
    List list;

    public void readPOLineFile() throws ParseException, Exception {
        JSch jsch = new JSch();
        Session session = null;
        int targetSize = 900;
        int i = 0;
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

            String sapToVitsFullFilePath = new SimpleDateFormat("yyyyMMdd'.txt'").format(new Date());
            //System.out.println("/data/VPTS/PO_Line_Status_" + sapToVitsFullFilePath);
            InputStream stream = sftpChannel.get("/data/VPTS/PO_Line_Status_" + sapToVitsFullFilePath);
            String SFTPPROCESSEDDIR = "/data/VPTS/PO_LINE_STATUS_Processed";// copy files to processed folder
            String FileName = "PO_Line_Status_" + sapToVitsFullFilePath;
            String SFTPERRORDIR = "/data/VPTS/PO_LINE_STATUS_Error";
            String Folder = new SimpleDateFormat("yyyy-MM").format(new Date());
            String[] folders = Folder.split("-");
            List<PoLineStatusBean> lstErpToVitsFileFormat = null;
            lstErpToVitsFileFormat = new ArrayList<PoLineStatusBean>();
            
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));

                String line;

                while ((line = br.readLine()) != null) {
                    ++i;
                    // System.out.println("In while loop reading records of file:/data/VPTS/PO_Status_ "+sapToVitsFullFilePath);
                    // System.out.println("In while loop reading records of file with line no: "+line);
                    String[] values = line.split("[|]", 37);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                    PoLineStatusBean poLinestatusbeanobj = new PoLineStatusBean();
                    poLinestatusbeanobj.setContract_Document(values[0]);
                    poLinestatusbeanobj.setItem(values[1]);
                    poLinestatusbeanobj.setDeletion_Indicator(values[2]);
                    if (!ApplicationUtils.isBlank(values[3])) {
                        poLinestatusbeanobj.setLast_Changed_on(formatter.parse(values[3]));

                    }
                    poLinestatusbeanobj.setShort_Text(values[4]);
                    poLinestatusbeanobj.setMaterial(values[5]);
                    poLinestatusbeanobj.setCompany_Code(values[6]);
                    poLinestatusbeanobj.setPlant(values[7]);
                    poLinestatusbeanobj.setStorage_Location(values[8]);
                    poLinestatusbeanobj.setMaterial_Group(values[9]);
                    poLinestatusbeanobj.setOrder_Quantity(values[10]);
                    poLinestatusbeanobj.setOrder_Unit(values[11]);
                    poLinestatusbeanobj.setPer_unit_price(values[12]);
                    poLinestatusbeanobj.setNet_Order_Value(values[13]);
                    poLinestatusbeanobj.setGross_order_value(values[14]);
                    poLinestatusbeanobj.setTax_code(values[15]);
                    poLinestatusbeanobj.setValuation_Type(values[16]);
                    poLinestatusbeanobj.setValuation_Category(values[17]);
                    poLinestatusbeanobj.setDelivery_Completed(values[18]);
                    poLinestatusbeanobj.setItem_Category(values[19]);
                    poLinestatusbeanobj.setAcct_Assignment_Cat(values[20]);//skipped acct_cat_desc
                    poLinestatusbeanobj.setOutline_Agreement(values[22]);
                    poLinestatusbeanobj.setFunds_Center(values[23]);
                    poLinestatusbeanobj.setCommitment_Item(values[24]);
                    poLinestatusbeanobj.setProfit_Center(values[25]);
                    poLinestatusbeanobj.setPurchasing_group(values[26]);
                    poLinestatusbeanobj.setPurchasing_desc(values[27]);
                    poLinestatusbeanobj.setTotal_value_limit(values[28]);
                     if (!ApplicationUtils.isBlank(values[29])) {
                    poLinestatusbeanobj.setPo_from_date(formatter.parse(values[29]));
                     }
                     if (!ApplicationUtils.isBlank(values[30])) {
                     poLinestatusbeanobj.setPo_to_date(formatter.parse(values[30]));
                     }
                    poLinestatusbeanobj.setPurchasing_org(values[31]);
                    poLinestatusbeanobj.setPo_type(values[32]);
                    poLinestatusbeanobj.setVendor_number(values[33]);
                    poLinestatusbeanobj.setVendor_name(values[34]);
                     if (!ApplicationUtils.isBlank(values[35])) {
                    poLinestatusbeanobj.setPo_doc_date(formatter.parse(values[35]));
                     }
                     
                    poLinestatusbeanobj.setCreated_by(values[36]);
                      

                    lstErpToVitsFileFormat.add(poLinestatusbeanobj);

                }
                getPOLineStatusTxnhelper del = new getPOLineStatusTxnhelper();
                conn = ApplicationUtils.getConnection();
                 del.deleteObject(conn);
                Partition<PoLineStatusBean> partitioned = Partition.ofSize(lstErpToVitsFileFormat, targetSize);
// Storing chunks of type List<PoStatusBean> in the array
              //  System.out.println("Reading PO File : /data/VPTS/PO_Line_Status_" + sapToVitsFullFilePath);
                List<PoLineStatusBean>[] array = partitioned.toArray(new List[partitioned.size()]);
              //  System.out.println("Size of array reading through file :" + array.length);
                if (array != null) {
                    int count = 0;
                    for (List<PoLineStatusBean> pobean : array) {

                        ++count;
                      //  System.out.println("In Array reading chunk " + count);
                        list = vendorMgrObj.getPOLineStatus(pobean);

                    }
                      vendorMgrObj.getPOLineStatusProcedure();
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

                sftpChannel.rename("/data/VPTS/"+FileName,SFTPPROCESSEDDIR+"/"+FileName);
            } catch (IOException io) {
                sftpChannel.rename("/data/VPTS/"+FileName,SFTPERRORDIR+"/"+FileName);
                System.out.println("Exception occurred during reading file from SFTP server due to " + io.getMessage());
                logger.log(Level.WARN, "ReadPOLineStatus :: Exception :: " + io);
                io.getMessage();

            } catch (Exception e) {
                 sftpChannel.rename("/data/VPTS/"+FileName,SFTPERRORDIR+"/"+FileName);
                System.out.println("Exception occurred during reading file from SFTP server on line number " + i + " and  column number " + e.getMessage());
                logger.log(Level.WARN, "ReadPOLineStatus :: Exception :: " + e);
                e.getMessage();
                //e.printStackTrace();

            }

            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            logger.log(Level.WARN, "ReadPOLineStatus :: Exception :: " + e);
            System.out.println("exception while reading file from ftp JSCH");
                System.out.println("Exception occurred during reading file from SFTP server on line number " + i + " and  column number " + e.getMessage());

              //e.printStackTrace();
        } catch (SftpException e) {
            logger.log(Level.WARN, "ReadPOLineStatus :: Exception :: " + e);
            System.out.println("exception while reading file from ftp SFTP");
                System.out.println("Exception occurred during reading file from SFTP server on line number " + i + " and  column number " + e.getMessage());

            //e.printStackTrace();
        }
    }
}
