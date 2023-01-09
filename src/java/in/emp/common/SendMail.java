
package in.emp.common;


import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class SendMail {  
     private static Logger logger = Logger.getLogger(SendMail.class);
     
  public static int sendmail(String to,String Subject,String mailmessage) {  
  
  String host="bulkmail.mahadiscom.in";  
 
  //final String from="message@mahadiscom.in";
final String from="erp.support@mahadiscom.in";
  // final String user="msedcl_cfr";
//final String password="Jeft#34vhe5";
 //final String bcc="centralpay.erp@gmail.com";
 //final String user="msedcl_message";
 final String user="msedcl_vcomm";
//final String password="6_ywbvldS";
final String password="PF$e49&%w7";
   //Get the session object  
   Properties props = new Properties();  
   props.put("mail.smtp.host",host); 
   props.put("mail.smtp.port", "587");
   props.put("mail.smtp.auth", "true"); 
   props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.ssl.protocols","TLSv1.2");
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(from));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
    //message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
     message.setSubject(Subject);  
     //message.setText(mailmessage);  
      message.setContent(mailmessage, "text/html");  
    //send the message  
     Transport.send(message);  
  
     System.out.println("mail sent successfully...");  
   return 1;
     } catch (MessagingException e) {
         e.printStackTrace();
        return 0;
     }  
 }  
     
    
 public static int sendmailOld(String to,String Subject,String mailmessage) {  
  
  String host="mail.mahadiscom.in";  
  final String user="erp.support@mahadiscom.in";
 // final String user="apmumbai25@mahadiscom.in";//change accordingly  
  //final String password="Jabong@123";//change accordingly  
   final String password="Prakashgad@321";
  //String to="apmumbai15@mahadiscom.in";//change accordingly  
 
   //Get the session object  
   Properties props = new Properties();  
   props.put("mail.smtp.host",host);  
   props.put("mail.smtp.auth", "true");  
     
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject(Subject);  
     message.setText(mailmessage);  
       
    //send the message  
     Transport.send(message);  
  
     System.out.println("mail sent successfully...");  
   return 1;
     } catch (MessagingException e) {
          logger.log(Level.ERROR, "sendmail :: Exception .. " + e.getMessage());
            //ex.printStackTrace();
        return 0;
     }  
 }  
 

}  