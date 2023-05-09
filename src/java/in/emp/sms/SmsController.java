/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.sms;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.emp.arch.GenericFormHandler;
import in.emp.common.ApplicationConstants;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.SMSResponseBean;
import in.emp.vendor.bean.SmsDTO;
import in.emp.vendor.manager.VendorManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

public class SmsController {

    private static Logger logger = Logger.getLogger(SmsController.class);
    SMSResponseBean smsresponsebeanbeanobj = new SMSResponseBean();

    public void sendSMS(SmsDTO smsdto, String templateId, List lstcredential) throws Exception {

        SmsDTO smsdtoNew = null;

        String smsData;
        if (smsdto != null) {
            //Check if this phone number is blacklisted.

            //Mobile number is not blacklisted.
            //log.info("Sending SMS to - "+ smsdto.getMobileNumber());
            smsdtoNew = getSmsDTO(templateId, lstcredential, smsdto);
            if (lstcredential.get(0).toString() == ApplicationConstants.OTP_URL) {//username for otp 
                smsData = createSmsString(smsdtoNew, smsdto.getMobileNumber(), smsdto.getLstParams());
                //message of otp will be sent
            } else {
                if (lstcredential.get(1).toString() == ApplicationConstants.BULK_SMS_Y) {
                    smsData = createSmsStringBulkTemplate(smsdtoNew, smsdto.getMobileNumber(), smsdto.getLstParams());
                } else {
                    smsData = createSmsStringTemplate(smsdtoNew, smsdto.getMobileNumber(), smsdto.getLstParams());
                }
                //messages other than otp
            }

            if (smsdtoNew != null) {
                if (smsdtoNew.getTemplateId() != null) {
                    smsdto.setTemplateId(smsdtoNew.getTemplateId());
                }
                if (lstcredential.get(0).toString() == ApplicationConstants.OTP_URL) {
                    sendSMS(smsdtoNew.getUrl(), smsData, smsdto, lstcredential);
                } else {
                    String enableSmsEmail = System.getProperty("ENABLE_SMS_EMAIL");
                    if (ApplicationUtils.validateString(enableSmsEmail)) {
                        if (enableSmsEmail.equalsIgnoreCase("true")) {
                            sendSMS(smsdtoNew.getUrl(), smsData, smsdto, lstcredential);
                        }
                    }
                }
            }
        }
    }

    public SmsDTO getSmsDTO(String templateId, List lstcredential, SmsDTO smsdto) throws Exception {
        SmsDTO smsDTO = new SmsDTO();
        String userName = null;
        String userPass = null;
        String smsId = null;
        String smsUrl = null;
        String smsTemplateId = null;

        for (int i = 0; i < lstcredential.size(); i++) {
            // userName = lstcredential.get(0).toString();
            // userPass = lstcredential.get(1).toString();
            smsUrl = lstcredential.get(0).toString();

        }
        //userName    =   "639748";

        // userPass    =   "mse@12";
        smsId = "MSEDCL";

        //smsUrl    =   "http://otp.zone:7501/failsafe/HttpLink";
        smsTemplateId = templateId;

        //  smsDTO.setUser(userName);
        //  smsDTO.setPasswd(userPass);
        //  smsDTO.setSid(smsId);
        smsDTO.setUrl(smsUrl);
        smsDTO.setTemplateId(smsTemplateId);
        smsDTO.setRequest(smsdto.getRequest());

        return smsDTO;
    }

    private String createSmsStringTemplate(SmsDTO smsdto, String mobileNumber, List lstParams) {
        String smsData = "";
        int counter = 0;
        String message = "";
        String F1 = "", F2 = "", F3 = "", F4 = "", F5 = "", F6 = "";
        JSONObject jsonSmsData = new JSONObject();
        JSONArray jsonSmsArray = new JSONArray();
        Iterator itr = null;

        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        if (smsdto != null) {
            HttpServletRequest request = smsdto.getRequest();
            HttpSession session = request.getSession();
            jsonSmsData.put("APPLICATION_ID", ApplicationConstants.Application_Id);
            jsonSmsData.put("CARD_DATA", "OTP TEST");
            if (session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) {
                jsonSmsData.put("EMP_YN", "N");

            } else {
                jsonSmsData.put("EMP_YN", "Y");
            }
            jsonSmsData.put("MOBILE_NUMBER", mobileNumber);
            if (session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) {
                jsonSmsData.put("OFFICE_CODE", session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                jsonSmsData.put("OFFICE_TYPE", "000");
            } else {
                jsonSmsData.put("OFFICE_CODE", session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
                jsonSmsData.put("OFFICE_TYPE", session.getAttribute(ApplicationConstants.OFFICE_TYPE_ID_SESSION));
            }
            jsonSmsData.put("SCHEDULED_SUBMISSION_DT", sdf.format(date));
            jsonSmsData.put("SENDER_ID", "MSEDCL");
            jsonSmsData.put("SMS_PURPOSE", "test sms");
            jsonSmsData.put("SMS_TEMPLATE_NUMBER", smsdto.getTemplateId());

            if (lstParams != null && lstParams.size() > 0) {

                for (int i = 0; i < lstParams.size(); i++) {
                    F1 = lstParams.get(0).toString();
                    F2 = lstParams.get(1).toString();
                    jsonSmsData.put("F1", F1);
                    jsonSmsData.put("F2", F2);
                    if (lstParams.size() >= 3) {
                        F3 = lstParams.get(2).toString();
                        jsonSmsData.put("F3", F3);
                    }
                    if (lstParams.size() >= 4) {
                        F4 = lstParams.get(3).toString();
                        jsonSmsData.put("F4", F4);
                    }
                    if (lstParams.size() >= 5) {
                        F5 = lstParams.get(4).toString();
                        jsonSmsData.put("F5", F5);
                    }
                    if (lstParams.size() == 6) {
                        F6 = lstParams.get(5).toString();
                        jsonSmsData.put("F6", F6);
                    }
                }
            }

            jsonSmsArray.add(jsonSmsData);

        }
        return jsonSmsArray.toString();
    }

    private String createSmsStringBulkTemplate(SmsDTO smsdto, String mobileNumber, List lstParams) {
        String smsData = "";
        int counter = 0;
        String message = "";
        String F1 = "", F2 = "", F3 = "", F4 = "", F5 = "", F6 = "";
        JSONObject jsonSmsData = new JSONObject();
        JSONArray jsonSmsArray = new JSONArray();
        Iterator itr = null;
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        if (smsdto != null) {

            jsonSmsData.put("APPLICATION_ID", ApplicationConstants.Application_Id);
            jsonSmsData.put("CARD_DATA", "OTP TEST");
            jsonSmsData.put("EMP_YN", "Y");
            jsonSmsData.put("MOBILE_NUMBER", mobileNumber);
            jsonSmsData.put("OFFICE_CODE", "000");
            jsonSmsData.put("OFFICE_TYPE", "000");
            jsonSmsData.put("SCHEDULED_SUBMISSION_DT", sdf.format(date));
            jsonSmsData.put("SENDER_ID", "MSEDCL");
            jsonSmsData.put("SMS_PURPOSE", "test sms");
            jsonSmsData.put("SMS_TEMPLATE_NUMBER", smsdto.getTemplateId());

            if (lstParams != null && lstParams.size() > 0) {

                for (int i = 0; i < lstParams.size(); i++) {
                    F1 = lstParams.get(0).toString();
                    F2 = lstParams.get(1).toString();
                    jsonSmsData.put("F1", F1);
                    jsonSmsData.put("F2", F2);
                    if (lstParams.size() >= 3) {
                        F3 = lstParams.get(2).toString();
                        jsonSmsData.put("F3", F3);
                    }
                    if (lstParams.size() >= 4) {
                        F4 = lstParams.get(3).toString();
                        jsonSmsData.put("F4", F4);
                    }
                    if (lstParams.size() >= 5) {
                        F5 = lstParams.get(4).toString();
                        jsonSmsData.put("F5", F5);
                    }
                    if (lstParams.size() == 6) {
                        F6 = lstParams.get(5).toString();
                        jsonSmsData.put("F6", F6);
                    }
                }
            }

            jsonSmsArray.add(jsonSmsData);

        }
        return jsonSmsArray.toString();
    }

    private String createSmsString(SmsDTO smsdto, String mobileNumber, List lstParams) {
        JSONObject jsonSmsData = new JSONObject();
        String param1 = "";
        String param2 = "";

        if (smsdto != null) {
            //smsData =  "&dest="+mobileNumber+"&send=MSEDCL";//NEW URL
            //smsData +=  "&text=OTP for login to Vendor Invoice Tracking system for User ID ";//NEW URL

            if (lstParams != null && lstParams.size() > 0) {
                for (int i = 0; i < lstParams.size(); i++) {
                    param1 = lstParams.get(0).toString();
                    param2 = lstParams.get(1).toString();
                }
                jsonSmsData.put("APPLICATION_ID", ApplicationConstants.Application_Id);
                jsonSmsData.put("CARD_DATA", "OTP TEST");
                jsonSmsData.put("MOBILE_NUMBER", mobileNumber);
                jsonSmsData.put("SMS_TEMPLATE_NUMBER", smsdto.getTemplateId());
                jsonSmsData.put("F1", param1);
                jsonSmsData.put("F2", param2);
                jsonSmsData.put("SENDER_ID", ApplicationConstants.SENDER_ID);
                System.out.println(jsonSmsData.toString());
            }
        }
        return jsonSmsData.toString();
    }

    public void sendSMS(String urlString, String smsData, SmsDTO smsdto, List lstcredential) throws Exception {
        String decodedString = "";
        String retval = "";
        String token = "";
        OutputStreamWriter out = null;
        URL url = null;
        DateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss a");
        // Map<String, Object> claims = new HashMap<String, Object>();
        // byte[] checkSum = getSHA(smsData);
        //  claims.put("CHKSUM", checkSum);
        //   token = generateToken(claims, ApplicationConstants.Application_Id); //APPLICATION ID IS 22
        if (urlString != null) {
            url = new URL(urlString);
            System.out.println("url is:" + url);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("POST");
            urlconnection.setRequestProperty("Content-Type", "application/json");
            urlconnection.setRequestProperty("Requst", "application/json");
            urlconnection.setRequestProperty("X-API-BEARER", "22");
            // urlconnection.setRequestProperty("X-API-TOKEN", token);
            urlconnection.setDoOutput(true);

            out = new OutputStreamWriter(urlconnection.getOutputStream());
            logger.log(Level.INFO, "Time - " + sdf.format(new Date()) + "  smsData - " + smsData);
            System.out.println("Time - " + sdf.format(new Date()) + "smsdata is :" + smsData);

            out.write(smsData);
            System.out.println("Time - " + sdf.format(new Date()) + "smsdata is send :" + smsData);
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
            while ((decodedString = in.readLine()) != null) {

                retval += decodedString;
            }
            System.out.println("retval" + retval);
            if (retval != null) {
                if (lstcredential.get(0).toString() == ApplicationConstants.OTHER_URL) {
                    if (retval.length() > 65) {
                        retval = retval.substring(0, 65);
                    }
                }
                smsresponsebeanbeanobj.setRESPONSE_ID(retval);
            }
            System.out.println("calling trackSmsResponse");
            trackSmsResponse(smsdto);//save into smsresponsetracker
            logger.log(Level.INFO, "Time - " + sdf.format(new Date()) + " " + retval + " smsData - " + smsData);
            System.out.println("Time - " + sdf.format(new Date()) + " " + retval + " smsData - " + smsData);
        }

    }

    private void trackSmsResponse(SmsDTO smsdto) {

        VendorDelegate vendorMgrObj = new VendorManager();
        String F1 = "", F2 = "", F3 = "", F4 = "", F5 = "", F6 = "";
        try {
            if (smsdto.getLstParams() != null && smsdto.getLstParams().size() > 0) {

                System.out.println("size of smsdto" + smsdto.getLstParams().size());
                for (int i = 0; i < smsdto.getLstParams().size(); i++) {
                    F1 = smsdto.getLstParams().get(0).toString();
                    System.out.println(F1);
                    smsresponsebeanbeanobj.setF1(F1);
                    F2 = smsdto.getLstParams().get(1).toString();
                    smsresponsebeanbeanobj.setF2(F2);
                    System.out.println(F2);
                    if (smsdto.getLstParams().size() >= 3) {
                        F3 = smsdto.getLstParams().get(2).toString();
                        System.out.println(F3);
                        smsresponsebeanbeanobj.setF3(F3);
                    }
                    if (smsdto.getLstParams().size() >= 4) {
                        F4 = smsdto.getLstParams().get(3).toString();
                        System.out.println(F4);
                        smsresponsebeanbeanobj.setF4(F4);
                    }
                    if (smsdto.getLstParams().size() >= 5) {
                        F5 = smsdto.getLstParams().get(4).toString();
                        smsresponsebeanbeanobj.setF5(F5);
                        System.out.println(F5);
                    }
                    if (smsdto.getLstParams().size() == 6) {
                        F6 = smsdto.getLstParams().get(5).toString();
                        System.out.println(F6);
                        smsresponsebeanbeanobj.setF6(F6);
                    }

                }
                smsresponsebeanbeanobj.setMOBILE_NUMBER(smsdto.getMobileNumber());
                if (smsdto.getTemplateId() != null) {
                    smsresponsebeanbeanobj.setTEMPLATEID(smsdto.getTemplateId());
                }
                smsresponsebeanbeanobj = vendorMgrObj.getSmsResponseTrackerTxnHelper(smsresponsebeanbeanobj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateToken(Map<String, Object> chksum, String applicationId) {
        String token;
        Algorithm algorithm = Algorithm.HMAC256(System.getProperty("SMS_API_KEY"));

        token = JWT.create()
                .withHeader(chksum)
                .withSubject(applicationId)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(algorithm);
        return token;

    }

    private static byte[] getSHA(String input) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

}
