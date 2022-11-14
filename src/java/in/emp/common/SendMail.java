
package in.emp.common;


import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  

public class SendMail {  
    
 public static int sendmail(String to,String Subject,String mailmessage) {  
  
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
        return 0;
     }  
 }  
 

}  