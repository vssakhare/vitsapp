/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.sms;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.SMSResponseBean;
import in.emp.vendor.bean.SmsDTO;
import in.emp.vendor.manager.VendorManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.net.ssl.HttpsURLConnection;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class SmsController {
    
private static Logger logger = Logger.getLogger(SmsController.class);
     SMSResponseBean smsresponsebeanbeanobj = new SMSResponseBean();
    public void sendSMS(SmsDTO smsdto, String templateId,List lstcredential) throws Exception
    {
        SmsDTO smsdtoNew    =   null;
        DateFormat sdf  =   new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss a");
        String smsData;
        if(smsdto != null)
        {
            //Check if this phone number is blacklisted.
           
                //Mobile number is not blacklisted.
               //log.info("Sending SMS to - "+ smsdto.getMobileNumber());
                smsdtoNew           =   getSmsDTO(templateId,lstcredential);
                if(lstcredential.get(0).toString()=="639748"){//username for otp 
                 smsData  =  createSmsString(smsdtoNew, smsdto.getMobileNumber(), smsdto.getLstParams());
                //message of otp will be sent
                }
               else
                {
                  smsData  =  createSmsStringTemplate(smsdtoNew, smsdto.getMobileNumber(), smsdto.getLstParams());  
                //messages other than otp
                }
                
                if(smsdtoNew != null)
                {
                      if(smsdtoNew.getTemplateId()!=null)
                      { smsdto.setTemplateId(smsdtoNew.getTemplateId());}
                    sendSMS(smsdtoNew.getUrl(), smsData,smsdto);
                        
                    
                }
            
            
        }
    }   



    public SmsDTO getSmsDTO(String templateId,List lstcredential) throws Exception
    {
        SmsDTO smsDTO   =   new SmsDTO();
        String userName =   null;
        String userPass =   null;
        String smsId    =   null;
        String smsUrl   =   null;
        String smsTemplateId   =   null;
       for(int i=0;i<lstcredential.size();i++)
                {
                     userName= lstcredential.get(0).toString();
                     userPass = lstcredential.get(1).toString();
                      smsUrl    =   lstcredential.get(2).toString();      
                    
                }
            //userName    =   "639748";

           // userPass    =   "mse@12";
 
            smsId    =   "MSEDCL";
      
            //smsUrl    =   "http://otp.zone:7501/failsafe/HttpLink";
       
            smsTemplateId    =  templateId;
        
        smsDTO.setUser(userName);
        smsDTO.setPasswd(userPass);
        smsDTO.setSid(smsId);
        smsDTO.setUrl(smsUrl);
        smsDTO.setTemplateId(smsTemplateId);
        
        return smsDTO;
    }
private String createSmsStringTemplate(SmsDTO smsdto, String mobileNumber, List lstParams)
    {
        String smsData =    "";
        int counter =   0;
        String message = "";
       String F1="",F2="",F3="",F4="",F5="",F6="";
       
        Iterator itr    =   null;
        if(smsdto   !=  null)
        {
            smsData = "aid="+ smsdto.getUser() +"&pin="+ smsdto.getPasswd()+ "&signature="+smsdto.getSid()+"&mnumber="+mobileNumber;
           
            
            if(lstParams != null && lstParams.size() > 0)
            {
                
                
                for(int i=0;i<lstParams.size();i++)
                {
                      F1 = lstParams.get(0).toString();
                     F2 = lstParams.get(1).toString();
                     if(lstParams.size()>=3)
                    {
                      F3 = lstParams.get(2).toString();}
                       if(lstParams.size()>=4)
                    {
                    F4 = lstParams.get(3).toString();}
                    if(lstParams.size()>=5)
                    { F5 = lstParams.get(4).toString();}
                    if(lstParams.size()==6)
                    {
                       F6 = lstParams.get(5).toString(); 
                    }
                }
           ;
               smsData+="&F1="+F1+"&F2="+F2;
                if(lstParams.size()>=3)
                {
                   smsData+="&F3="+F3; 
                }
                if(lstParams.size()>=4)
                {
                     smsData+="&F4="+F4;
                }
                if(lstParams.size()>=5)
                {
                   smsData+="&F5="+F5;
                }
                if(lstParams.size()==6)
                {
                   smsData+="&F6="+F6;
                }
            }
            smsData+="&templateid="+smsdto.getTemplateId();
        }
        return smsData;
    }
private String createSmsString(SmsDTO smsdto, String mobileNumber, List lstParams)
    {
        String smsData =    "";
        int counter =   0;
        String message = "";
        String param1=    "";
        String param2=    "";
       
        Iterator itr    =   null;
        if(smsdto   !=  null)
        {
          // smsData = "aid="+ smsdto.getUser() +"&pin="+ smsdto.getPasswd()+ "&mnumber="+mobileNumber+"&signature="+smsdto.getSid();//OLD URL
           smsData =  "&dest="+mobileNumber+"&send=MSEDCL";//NEW URL
         // smsData +=  "&message=OTP for login to Vendor Invoice Tracking system for User ID ";//OLD URL
              smsData +=  "&text=OTP for login to Vendor Invoice Tracking system for User ID ";//NEW URL
            
            if(lstParams != null && lstParams.size() > 0)
            {
                itr =   lstParams.iterator();
                /*while(itr.hasNext())
                {
                    String param    =   itr.next().toString();
                    //smsData+=  param;
                }*/
                for(int i=0;i<lstParams.size();i++)
                {
                     param1 = lstParams.get(0).toString();
                     param2 = lstParams.get(1).toString();
                    
                }
               System.out.println(param1);
               System.out.println(param2);
               smsData+=" "+param1+" is "+param2+".MSEDCL";
                System.out.println("smsdata:"+smsData);
            }
        }
        return smsData;
    }

private void sendSMS(String urlString, String smsData,SmsDTO smsdto) throws Exception
    {
        String decodedString    =   "";
        String retval = "";
        OutputStreamWriter out =null;
        URL url =   null;
        DateFormat sdf  =   new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss a");
        String proxy	=	null;
        
        if(urlString != null)
        {
            url =   new URL(urlString);
		//proxy="";
        //  proxy	="10.0.3.111";//comment for cloud
	          
				String port =	"8080";
	            Properties systemProperties = System.getProperties();
	     //       systemProperties.setProperty("http.proxyHost",proxy);//comment for cloud
            System.out.println("url is:" +url);
          //  systemProperties.setProperty("http.proxyPort",port); //comment for cloud
        //   Proxy proxy1 = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy, Integer.parseInt(port)));//comment for cloud
		//HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection(proxy1);//comment for cloud
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();//uncomment for cloud
            urlconnection.setRequestMethod("POST");
            urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            urlconnection.setDoOutput(true);
           // try{
             out = new OutputStreamWriter(urlconnection.getOutputStream());
            logger.log(Level.INFO,   "Time - " + sdf.format(new Date()) + "  smsData - "+smsData);
            System.out.println( "Time - " + sdf.format(new Date()) +"smsdata is :"+smsData);
           
       
            out.write(smsData);
            System.out.println( "Time - " + sdf.format(new Date()) +"smsdata is send :"+smsData);
            out.close();
          //  }
           // catch(Exception e)
           // {
           //     e.printStackTrace();
          // }
            BufferedReader in = new BufferedReader( new InputStreamReader(urlconnection.getInputStream()));
            while ((decodedString = in.readLine()) != null)
		retval += decodedString;
            System.out.println("retval"+retval);
            if(retval!=null)
            {   if (retval.indexOf("~") > 0)
                    { retval = retval.substring(retval.indexOf("=") + 1,retval.indexOf("~"));
                    smsresponsebeanbeanobj.setRESPONSE_ID(retval);
                    }
               else
                { retval = retval.substring(retval.indexOf("=") + 1);
                    smsresponsebeanbeanobj.setRESPONSE_ID(retval);
                    }
            }
             System.out.println("calling trackSmsResponse");
                 trackSmsResponse(smsdto);//save into smsresponsetracker
		logger.log(Level.INFO,   "Time - " + sdf.format(new Date()) + " " + retval + " smsData - "+smsData);
                System.out.println("Time - " + sdf.format(new Date()) + " " + retval + " smsData - "+smsData);
           }
        
}

private void trackSmsResponse(SmsDTO smsdto)
{

       VendorDelegate vendorMgrObj = new VendorManager();
    String F1="",F2="",F3="",F4="",F5="",F6="";
 try{
                    if(smsdto.getLstParams() != null && smsdto.getLstParams().size() > 0)
            {
                
                 System.out.println("size of smsdto"+smsdto.getLstParams().size());
                for(int i=0;i<smsdto.getLstParams().size();i++)
                {
                      F1 = smsdto.getLstParams().get(0).toString();
                      System.out.println(F1);
                      smsresponsebeanbeanobj.setF1(F1);
                     F2 = smsdto.getLstParams().get(1).toString();
                     smsresponsebeanbeanobj.setF2(F2);
                     System.out.println(F2);
                     if(smsdto.getLstParams().size()>=3)
                    {
                      F3 = smsdto.getLstParams().get(2).toString();
                      System.out.println(F3);
                      smsresponsebeanbeanobj.setF3(F3);
                    }
                       if(smsdto.getLstParams().size()>=4)
                    {
                    F4 = smsdto.getLstParams().get(3).toString();
                    System.out.println(F4);
                    smsresponsebeanbeanobj.setF4(F4);
                    }
                    if(smsdto.getLstParams().size()>=5)
                    { F5 = smsdto.getLstParams().get(4).toString();
                     smsresponsebeanbeanobj.setF5(F5);
                     System.out.println(F5);
                    }
                    if(smsdto.getLstParams().size()==6)
                    {
                       F6 = smsdto.getLstParams().get(5).toString();
                       System.out.println(F6);
                           smsresponsebeanbeanobj.setF6(F6);
                    }
                    
                }
                smsresponsebeanbeanobj.setMOBILE_NUMBER(smsdto.getMobileNumber());
                if(smsdto.getTemplateId()!=null)
                {smsresponsebeanbeanobj.setTEMPLATEID(smsdto.getTemplateId());}
                smsresponsebeanbeanobj = vendorMgrObj.getSmsResponseTrackerTxnHelper(smsresponsebeanbeanobj);  
            }
                    
            }catch(Exception e){
               e.printStackTrace();
            }
} 
     
}