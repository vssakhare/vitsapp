/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.sms;
import java.util.ArrayList;
import java.util.Hashtable;
import javaldapapp.AssignOfficeDTO;
import javax.naming.*;
import javax.naming.ldap.*;
import javax.naming.directory.*;
import in.emp.vendor.bean.AssignOfficeBean;
public class SmsEmployee
{
    
    
    public static AssignOfficeDTO Ldap(String Office_code)
    {
         AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
        Hashtable<String, String> environment = new Hashtable<String, String>();
        System.out.println("in ldap");
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, "LDAP://10.10.210.8:389/DC=rapdrp,DC=mahadiscom,DC=in");
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, "Swayam");
        environment.put(Context.SECURITY_CREDENTIALS, "Trident@1");
       try 
        {
            DirContext ctx = new InitialDirContext(environment);
            byte b = 0;
            System.out.println("Connected..");
            System.out.println(ctx.getEnvironment());
            
           String FILTER = "(&(objectClass=user)(objectcategory=person)(&(officecode="+Office_code+")(isofficeincharge=Y)(department=Technical)))";
          
           
String searchBase = "";
SearchControls ctls = new SearchControls();
ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
// Search for objects using filter and controls
NamingEnumeration answer = ctx.search(searchBase, FILTER, ctls);

        
        while (answer.hasMore()) {
          SearchResult searchResult = (SearchResult)answer.next();
          
          Attributes attributes = searchResult.getAttributes();
          
          Attribute attribute2 = searchResult.getAttributes().get("designation");
          System.out.println("designation"+attribute2);
          Attribute attribute3 = searchResult.getAttributes().get("mail");
          Attribute attribute4 = searchResult.getAttributes().get("cpfnumber");
          Attribute attribute5 = searchResult.getAttributes().get("title");
          Attribute attribute6 = searchResult.getAttributes().get("givenName");
          Attribute attribute7 = searchResult.getAttributes().get("middleName");
          Attribute attribute8 = searchResult.getAttributes().get("sn");
          Attribute attribute9 = searchResult.getAttributes().get("mobile");
          Attribute attribute10 = searchResult.getAttributes().get("telephoneNumber");
          Attribute attribute11 = searchResult.getAttributes().get("officecode");
          Attribute attribute12 = searchResult.getAttributes().get("isofficeincharge");
          Attribute attribute13 = searchResult.getAttributes().get("officetype");
          Attribute attribute14 = searchResult.getAttributes().get("ou");
          Attribute attribute15 = searchResult.getAttributes().get("department");
      Attribute attribute16 = searchResult.getAttributes().get("parentofficecode");
//SearchResult sr = (SearchResult) answer.next();
//Attributes attrs = sr.getAttributes();
//System.out.println(" Naming Context: "+ sr.getNameInNamespace());System.out.println("designation"+attribute2);
          System.out.println("mail"+attribute3);
          System.out.println("cpfnumber"+attribute4);
          System.out.println("title"+attribute5);
          System.out.println("givenName"+attribute6);
          System.out.println("middleName"+attribute7);
          System.out.println("sn"+attribute8);
          System.out.println("mobile"+attribute9);
          System.out.println("telephoneNumber"+attribute10);
          System.out.println("officecode"+attribute11);
          System.out.println("isofficeincharge"+attribute12);
          System.out.println("officetype"+attribute13);
          System.out.println("ou"+attribute14);
          System.out.println("department"+attribute15);
        System.out.println("parentofficecode"+attribute16);
        
          try {
            String str3 = "";
            str3 = (attribute5 == null) ? "" : attribute5.get(0).toString();
            str3 = str3 + ((attribute6 == null) ? "" : (" " + attribute6.get(0).toString()));
            str3 = str3 + ((attribute7 == null) ? "" : (" " + attribute7.get(0).toString()));
            str3 = str3 + ((attribute8 == null) ? "" : (" " + attribute8.get(0).toString()));
            
            assignOfficeDTO.setOfficerCpfNo((attribute4 == null) ? "" : attribute4.get(0).toString());
            assignOfficeDTO.setOfficerDesignation((attribute2 == null) ? "" : attribute2.get(0).toString());
            assignOfficeDTO.setOfficerName(str3);
            assignOfficeDTO.setOfficerContactNo((attribute10 == null) ? "" : attribute10.get(0).toString());
            assignOfficeDTO.setOfficerContactNo((attribute9 == null) ? "" : attribute9.get(0).toString());
            assignOfficeDTO.setOfficeIncharge((attribute12 == null) ? "" : attribute12.get(0).toString());
            assignOfficeDTO.setOfficerEmailId((attribute3 == null) ? "" : attribute3.get(0).toString());
            assignOfficeDTO.setOfficeCode((attribute11 == null) ? "" : attribute11.get(0).toString());
            assignOfficeDTO.setOfficeTypeId((attribute13 == null) ? "" : attribute13.get(0).toString());
            assignOfficeDTO.setOfficeName((attribute14 == null) ? "" : attribute14.get(0).toString());
            assignOfficeDTO.setOfficeTypeName((attribute15 == null) ? "" : attribute15.get(0).toString());
            assignOfficeDTO.setOfficerRef("");
          }
          
        
          catch (Exception exception) {
            exception.printStackTrace();
          } 
           b++;
          if (b > 100) {
            break;
          }
          System.out.println();
            
            
        }ctx.close();
        }
        catch (AuthenticationNotSupportedException exception) 
        {
            System.out.println("The authentication is not supported by the server");
              }

        catch (AuthenticationException exception)
        {
            System.out.println("Incorrect password or username");
        }

        catch (NamingException exception)
        {
            //System.out.println("Error when trying to create the context");
        }
       return assignOfficeDTO;
    }
    public static AssignOfficeBean LdapDb_Code(String Billing_DB_Code)
    {
         AssignOfficeBean assignOfficeDTO = new AssignOfficeBean();
        Hashtable<String, String> environment = new Hashtable<String, String>();
        System.out.println("in ldap");
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, "LDAP://10.10.210.8:389/DC=rapdrp,DC=mahadiscom,DC=in");
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, "Swayam");
        environment.put(Context.SECURITY_CREDENTIALS, "Trident@1");
       try 
        {
            DirContext ctx = new InitialDirContext(environment);
            byte b = 0;
            System.out.println("Connected..");
            System.out.println(ctx.getEnvironment());
            
          
           String FILTER = "(&(objectClass=user)(objectcategory=person)(bILLINGDBCODE="+Billing_DB_Code+")(isofficeIncharge=Y)(department=Technical))";// In prod this will be different
           
String searchBase = "";
SearchControls ctls = new SearchControls();
ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
// Search for objects using filter and controls
NamingEnumeration answer = ctx.search(searchBase, FILTER, ctls);

        
        while (answer.hasMore()) {
          SearchResult searchResult = (SearchResult)answer.next();
          
          Attributes attributes = searchResult.getAttributes();
          
          Attribute attribute2 = searchResult.getAttributes().get("designation");
          System.out.println("designation"+attribute2);
          Attribute attribute3 = searchResult.getAttributes().get("mail");
          Attribute attribute4 = searchResult.getAttributes().get("cpfnumber");
          Attribute attribute5 = searchResult.getAttributes().get("title");
          Attribute attribute6 = searchResult.getAttributes().get("givenName");
          Attribute attribute7 = searchResult.getAttributes().get("middleName");
          Attribute attribute8 = searchResult.getAttributes().get("sn");
          Attribute attribute9 = searchResult.getAttributes().get("mobile");
          Attribute attribute10 = searchResult.getAttributes().get("telephoneNumber");
          Attribute attribute11 = searchResult.getAttributes().get("officecode");
          Attribute attribute12 = searchResult.getAttributes().get("isofficeincharge");
          Attribute attribute13 = searchResult.getAttributes().get("officetype");
          Attribute attribute14 = searchResult.getAttributes().get("ou");
          Attribute attribute15 = searchResult.getAttributes().get("department");
      Attribute attribute16 = searchResult.getAttributes().get("parentofficecode");
//SearchResult sr = (SearchResult) answer.next();
//Attributes attrs = sr.getAttributes();
//System.out.println(" Naming Context: "+ sr.getNameInNamespace());System.out.println("designation"+attribute2);
          System.out.println("mail"+attribute3);
          System.out.println("cpfnumber"+attribute4);
          System.out.println("title"+attribute5);
          System.out.println("givenName"+attribute6);
          System.out.println("middleName"+attribute7);
          System.out.println("sn"+attribute8);
          System.out.println("mobile"+attribute9);
          System.out.println("telephoneNumber"+attribute10);
          System.out.println("officecode"+attribute11);
          System.out.println("isofficeincharge"+attribute12);
          System.out.println("officetype"+attribute13);
          System.out.println("ou"+attribute14);
          System.out.println("department"+attribute15);
        System.out.println("parentofficecode"+attribute16);
        
          try {
            String str3 = "";
            str3 = (attribute5 == null) ? "" : attribute5.get(0).toString();
            str3 = str3 + ((attribute6 == null) ? "" : (" " + attribute6.get(0).toString()));
            str3 = str3 + ((attribute7 == null) ? "" : (" " + attribute7.get(0).toString()));
            str3 = str3 + ((attribute8 == null) ? "" : (" " + attribute8.get(0).toString()));
            
            assignOfficeDTO.setOfficerCpfNo((attribute4 == null) ? "" : attribute4.get(0).toString());
            assignOfficeDTO.setOfficerDesignation((attribute2 == null) ? "" : attribute2.get(0).toString());
            assignOfficeDTO.setOfficerName(str3);
            assignOfficeDTO.setOfficerContactNo((attribute10 == null) ? "" : attribute10.get(0).toString());
            assignOfficeDTO.setOfficerContactNo((attribute9 == null) ? "" : attribute9.get(0).toString());
            assignOfficeDTO.setOfficeIncharge((attribute12 == null) ? "" : attribute12.get(0).toString());
            assignOfficeDTO.setOfficerEmailId((attribute3 == null) ? "" : attribute3.get(0).toString());
            assignOfficeDTO.setOfficeCode((attribute11 == null) ? "" : attribute11.get(0).toString());
            assignOfficeDTO.setOfficeTypeId((attribute13 == null) ? "" : attribute13.get(0).toString());
            assignOfficeDTO.setOfficeName((attribute14 == null) ? "" : attribute14.get(0).toString());
            assignOfficeDTO.setOfficeTypeName((attribute15 == null) ? "" : attribute15.get(0).toString());
            assignOfficeDTO.setParentOfficeCode((attribute16 == null) ? "" : attribute11.get(0).toString());
            assignOfficeDTO.setOfficerRef("");

          }
          
        
          catch (Exception exception) {
            exception.printStackTrace();
          } 
           b++;
          if (b > 100) {
            break;
          }
          System.out.println();
            
            
        }ctx.close();
        }
        catch (AuthenticationNotSupportedException exception) 
        {
            System.out.println("The authentication is not supported by the server");
              }

        catch (AuthenticationException exception)
        {
            System.out.println("Incorrect password or username");
        }

        catch (NamingException exception)
        {
            //System.out.println("Error when trying to create the context");
        }
       return assignOfficeDTO;
    }
    public static AssignOfficeBean Ldapcpf(String cpfnumber)
    {
         AssignOfficeBean assignOfficeDTO = new AssignOfficeBean();
        Hashtable<String, String> environment = new Hashtable<String, String>();
        System.out.println("in ldap");
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, "LDAP://10.10.210.8:389/DC=rapdrp,DC=mahadiscom,DC=in");
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, "Swayam");
        environment.put(Context.SECURITY_CREDENTIALS, "Trident@1");
       try 
        {
            DirContext ctx = new InitialDirContext(environment);
            byte b = 0;
            System.out.println("Connected..");
            System.out.println(ctx.getEnvironment());
            String FILTER = "(&(objectClass=user)(objectcategory=person)(&(cpfnumber="+cpfnumber+")))";
String searchBase = "";
SearchControls ctls = new SearchControls();
ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
// Search for objects using filter and controls
NamingEnumeration answer = ctx.search(searchBase, FILTER, ctls);

        
        while (answer.hasMore()) {
          SearchResult searchResult = (SearchResult)answer.next();
          
          Attributes attributes = searchResult.getAttributes();
          
          Attribute attribute2 = searchResult.getAttributes().get("designation");
          System.out.println("designation"+attribute2);
          Attribute attribute3 = searchResult.getAttributes().get("mail");
          Attribute attribute4 = searchResult.getAttributes().get("cpfnumber");
          Attribute attribute5 = searchResult.getAttributes().get("title");
          Attribute attribute6 = searchResult.getAttributes().get("givenName");
          Attribute attribute7 = searchResult.getAttributes().get("middleName");
          Attribute attribute8 = searchResult.getAttributes().get("sn");
          Attribute attribute9 = searchResult.getAttributes().get("mobile");
          Attribute attribute10 = searchResult.getAttributes().get("telephoneNumber");
          Attribute attribute11 = searchResult.getAttributes().get("officecode");
          Attribute attribute12 = searchResult.getAttributes().get("isofficeincharge");
          Attribute attribute13 = searchResult.getAttributes().get("officetype");
          Attribute attribute14 = searchResult.getAttributes().get("ou");
          Attribute attribute15 = searchResult.getAttributes().get("department");
      Attribute attribute16 = searchResult.getAttributes().get("parentofficecode");
//SearchResult sr = (SearchResult) answer.next();
//Attributes attrs = sr.getAttributes();
//System.out.println(" Naming Context: "+ sr.getNameInNamespace());System.out.println("designation"+attribute2);
          System.out.println("mail"+attribute3);
          System.out.println("cpfnumber"+attribute4);
          System.out.println("title"+attribute5);
          System.out.println("givenName"+attribute6);
          System.out.println("middleName"+attribute7);
          System.out.println("sn"+attribute8);
          System.out.println("mobile"+attribute9);
          System.out.println("telephoneNumber"+attribute10);
          System.out.println("officecode"+attribute11);
          System.out.println("isofficeincharge"+attribute12);
          System.out.println("officetype"+attribute13);
          System.out.println("ou"+attribute14);
          System.out.println("department"+attribute15);
        System.out.println("parentofficecode"+attribute16);
        
          try {
            String str3 = "";
            str3 = (attribute5 == null) ? "" : attribute5.get(0).toString();
            str3 = str3 + ((attribute6 == null) ? "" : (" " + attribute6.get(0).toString()));
            str3 = str3 + ((attribute7 == null) ? "" : (" " + attribute7.get(0).toString()));
            str3 = str3 + ((attribute8 == null) ? "" : (" " + attribute8.get(0).toString()));
            
            assignOfficeDTO.setOfficerCpfNo((attribute4 == null) ? "" : attribute4.get(0).toString());
            assignOfficeDTO.setOfficerDesignation((attribute2 == null) ? "" : attribute2.get(0).toString());
            assignOfficeDTO.setOfficerName(str3);
            assignOfficeDTO.setOfficerContactNo((attribute10 == null) ? "" : attribute10.get(0).toString());
            assignOfficeDTO.setOfficerContactNo((attribute9 == null) ? "" : attribute9.get(0).toString());
            assignOfficeDTO.setOfficeIncharge((attribute12 == null) ? "" : attribute12.get(0).toString());
            assignOfficeDTO.setOfficerEmailId((attribute3 == null) ? "" : attribute3.get(0).toString());
            assignOfficeDTO.setOfficeCode((attribute11 == null) ? "" : attribute11.get(0).toString());
            assignOfficeDTO.setOfficeTypeId((attribute13 == null) ? "" : attribute13.get(0).toString());
            assignOfficeDTO.setOfficeName((attribute14 == null) ? "" : attribute14.get(0).toString());
            assignOfficeDTO.setOfficeTypeName((attribute15 == null) ? "" : attribute15.get(0).toString());
            assignOfficeDTO.setOfficerRef("");
          }
          
        
          catch (Exception exception) {
            exception.printStackTrace();
          } 
           b++;
          if (b > 100) {
            break;
          }
          System.out.println();
            
            
        }ctx.close();
        }
        catch (AuthenticationNotSupportedException exception) 
        {
            System.out.println("The authentication is not supported by the server");
              }

        catch (AuthenticationException exception)
        {
            System.out.println("Incorrect password or username");
        }

        catch (NamingException exception)
        {
            //System.out.println("Error when trying to create the context");
        }
       return assignOfficeDTO;
    }
    
     public static AssignOfficeDTO Ldapbu(String bu)
    {
         AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
        Hashtable<String, String> environment = new Hashtable<String, String>();
        System.out.println("in ldap");
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, "LDAP://10.0.15.27:389/DC=rapdrp,DC=mahadiscom,DC=in");
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, "Swayam");
        environment.put(Context.SECURITY_CREDENTIALS, "Trident@1");
       try 
        {
            DirContext ctx = new InitialDirContext(environment);
            byte b = 0;
            System.out.println("Connected..");
            System.out.println(ctx.getEnvironment());
            String FILTER = "(&(objectClass=user)(objectcategory=person)(&(billingdbcode="+bu+"))(isofficeincharge=Y)(department=Technical))";
String searchBase = "";
SearchControls ctls = new SearchControls();
ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
// Search for objects using filter and controls
NamingEnumeration answer = ctx.search(searchBase, FILTER, ctls);

        
        while (answer.hasMore()) {
          SearchResult searchResult = (SearchResult)answer.next();
          
          Attributes attributes = searchResult.getAttributes();
          
          Attribute attribute2 = searchResult.getAttributes().get("designation");
          System.out.println("designation"+attribute2);
          Attribute attribute3 = searchResult.getAttributes().get("mail");
          Attribute attribute4 = searchResult.getAttributes().get("cpfnumber");
          Attribute attribute5 = searchResult.getAttributes().get("title");
          Attribute attribute6 = searchResult.getAttributes().get("givenName");
          Attribute attribute7 = searchResult.getAttributes().get("middleName");
          Attribute attribute8 = searchResult.getAttributes().get("sn");
          Attribute attribute9 = searchResult.getAttributes().get("mobile");
          Attribute attribute10 = searchResult.getAttributes().get("telephoneNumber");
          Attribute attribute11 = searchResult.getAttributes().get("officecode");
          Attribute attribute12 = searchResult.getAttributes().get("isofficeincharge");
          Attribute attribute13 = searchResult.getAttributes().get("officetype");
          Attribute attribute14 = searchResult.getAttributes().get("ou");
          Attribute attribute15 = searchResult.getAttributes().get("department");
      Attribute attribute16 = searchResult.getAttributes().get("parentofficecode");
//SearchResult sr = (SearchResult) answer.next();
//Attributes attrs = sr.getAttributes();
//System.out.println(" Naming Context: "+ sr.getNameInNamespace());System.out.println("designation"+attribute2);
          System.out.println("mail"+attribute3);
          System.out.println("cpfnumber"+attribute4);
          System.out.println("title"+attribute5);
          System.out.println("givenName"+attribute6);
          System.out.println("middleName"+attribute7);
          System.out.println("sn"+attribute8);
          System.out.println("mobile"+attribute9);
          System.out.println("telephoneNumber"+attribute10);
          System.out.println("officecode"+attribute11);
          System.out.println("isofficeincharge"+attribute12);
          System.out.println("officetype"+attribute13);
          System.out.println("ou"+attribute14);
          System.out.println("department"+attribute15);
        System.out.println("parentofficecode"+attribute16);
        
          try {
            String str3 = "";
            str3 = (attribute5 == null) ? "" : attribute5.get(0).toString();
            str3 = str3 + ((attribute6 == null) ? "" : (" " + attribute6.get(0).toString()));
            str3 = str3 + ((attribute7 == null) ? "" : (" " + attribute7.get(0).toString()));
            str3 = str3 + ((attribute8 == null) ? "" : (" " + attribute8.get(0).toString()));
            
            assignOfficeDTO.setOfficerCpfNo((attribute4 == null) ? "" : attribute4.get(0).toString());
            assignOfficeDTO.setOfficerDesignation((attribute2 == null) ? "" : attribute2.get(0).toString());
            assignOfficeDTO.setOfficerName(str3);
            assignOfficeDTO.setOfficerContactNo((attribute10 == null) ? "" : attribute10.get(0).toString());
            assignOfficeDTO.setOfficerContactNo((attribute9 == null) ? "" : attribute9.get(0).toString());
            assignOfficeDTO.setOfficeIncharge((attribute12 == null) ? "" : attribute12.get(0).toString());
            assignOfficeDTO.setOfficerEmailId((attribute3 == null) ? "" : attribute3.get(0).toString());
            assignOfficeDTO.setOfficeCode((attribute11 == null) ? "" : attribute11.get(0).toString());
            assignOfficeDTO.setOfficeTypeId((attribute13 == null) ? "" : attribute13.get(0).toString());
            assignOfficeDTO.setOfficeName((attribute14 == null) ? "" : attribute14.get(0).toString());
            assignOfficeDTO.setOfficeTypeName((attribute15 == null) ? "" : attribute15.get(0).toString());
            assignOfficeDTO.setOfficerRef("");
          }
          
        
          catch (Exception exception) {
            exception.printStackTrace();
          } 
           b++;
          if (b > 100) {
            break;
          }
          System.out.println();
            
            
        }ctx.close();
        }
        catch (AuthenticationNotSupportedException exception) 
        {
            System.out.println("The authentication is not supported by the server");
              }

        catch (AuthenticationException exception)
        {
            System.out.println("Incorrect password or username");
        }

        catch (NamingException exception)
        {
            //System.out.println("Error when trying to create the context");
        }
       return assignOfficeDTO;
    }
     
       public static AssignOfficeDTO LdapDepartment(String Office_code,String Dept,String Designation)
    {
         AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
        Hashtable<String, String> environment = new Hashtable<String, String>();
        System.out.println("in ldap");
        
        
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, "LDAP://10.10.210.8:389/DC=rapdrp,DC=mahadiscom,DC=in");
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, "Swayam");
        environment.put(Context.SECURITY_CREDENTIALS, "Trident@1");
       try 
        {
            DirContext ctx = new InitialDirContext(environment);
            byte b = 0;
            System.out.println("Connected..");
            System.out.println(ctx.getEnvironment());
             String FILTER ="";
            if(Dept.equals("Technical"))
                    FILTER = "(&(objectClass=user)(objectcategory=person)(&(officecode="+Office_code+")(isofficeincharge=Y)(department="+Dept+")))";
            else if(Dept.equals("Accounts"))
            FILTER = "(&(objectClass=user)(objectcategory=person)(&(officecode="+Office_code+")(department="+Dept+")(Designation="+Designation+")))";
String searchBase = "";
SearchControls ctls = new SearchControls();
ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
// Search for objects using filter and controls
NamingEnumeration answer = ctx.search(searchBase, FILTER, ctls);

        
        while (answer.hasMore()) {
          SearchResult searchResult = (SearchResult)answer.next();
          
          Attributes attributes = searchResult.getAttributes();
          
          Attribute attribute2 = searchResult.getAttributes().get("designation");
          System.out.println("designation"+attribute2);
          Attribute attribute3 = searchResult.getAttributes().get("mail");
          Attribute attribute4 = searchResult.getAttributes().get("cpfnumber");
          Attribute attribute5 = searchResult.getAttributes().get("title");
          Attribute attribute6 = searchResult.getAttributes().get("givenName");
          Attribute attribute7 = searchResult.getAttributes().get("middleName");
          Attribute attribute8 = searchResult.getAttributes().get("sn");
          Attribute attribute9 = searchResult.getAttributes().get("mobile");
          Attribute attribute10 = searchResult.getAttributes().get("telephoneNumber");
          Attribute attribute11 = searchResult.getAttributes().get("officecode");
          Attribute attribute12 = searchResult.getAttributes().get("isofficeincharge");
          Attribute attribute13 = searchResult.getAttributes().get("officetype");
          Attribute attribute14 = searchResult.getAttributes().get("ou");
          Attribute attribute15 = searchResult.getAttributes().get("department");
      Attribute attribute16 = searchResult.getAttributes().get("parentofficecode");
//SearchResult sr = (SearchResult) answer.next();
//Attributes attrs = sr.getAttributes();
//System.out.println(" Naming Context: "+ sr.getNameInNamespace());System.out.println("designation"+attribute2);
          System.out.println("mail"+attribute3);
          System.out.println("cpfnumber"+attribute4);
          System.out.println("title"+attribute5);
          System.out.println("givenName"+attribute6);
          System.out.println("middleName"+attribute7);
          System.out.println("sn"+attribute8);
          System.out.println("mobile"+attribute9);
          System.out.println("telephoneNumber"+attribute10);
          System.out.println("officecode"+attribute11);
          System.out.println("isofficeincharge"+attribute12);
          System.out.println("officetype"+attribute13);
          System.out.println("ou"+attribute14);
          System.out.println("department"+attribute15);
        System.out.println("parentofficecode"+attribute16);
        
          try {
            String str3 = "";
            str3 = (attribute5 == null) ? "" : attribute5.get(0).toString();
            str3 = str3 + ((attribute6 == null) ? "" : (" " + attribute6.get(0).toString()));
            str3 = str3 + ((attribute7 == null) ? "" : (" " + attribute7.get(0).toString()));
            str3 = str3 + ((attribute8 == null) ? "" : (" " + attribute8.get(0).toString()));
            
            assignOfficeDTO.setOfficerCpfNo((attribute4 == null) ? "" : attribute4.get(0).toString());
            assignOfficeDTO.setOfficerDesignation((attribute2 == null) ? "" : attribute2.get(0).toString());
            assignOfficeDTO.setOfficerName(str3);
            assignOfficeDTO.setOfficerContactNo((attribute10 == null) ? "" : attribute10.get(0).toString());
            assignOfficeDTO.setOfficerContactNo((attribute9 == null) ? "" : attribute9.get(0).toString());
            assignOfficeDTO.setOfficeIncharge((attribute12 == null) ? "" : attribute12.get(0).toString());
            assignOfficeDTO.setOfficerEmailId((attribute3 == null) ? "" : attribute3.get(0).toString());
            assignOfficeDTO.setOfficeCode((attribute11 == null) ? "" : attribute11.get(0).toString());
            assignOfficeDTO.setOfficeTypeId((attribute13 == null) ? "" : attribute13.get(0).toString());
            assignOfficeDTO.setOfficeName((attribute14 == null) ? "" : attribute14.get(0).toString());
            assignOfficeDTO.setOfficeTypeName((attribute15 == null) ? "" : attribute15.get(0).toString());
            assignOfficeDTO.setOfficerRef("");
          }
          
        
          catch (Exception exception) {
            exception.printStackTrace();
          } 
           b++;
          if (b > 100) {
            break;
          }
          System.out.println();
            
            
        }ctx.close();
        }
        catch (AuthenticationNotSupportedException exception) 
        {
            System.out.println("The authentication is not supported by the server");
              }

        catch (AuthenticationException exception)
        {
            System.out.println("Incorrect password or username");
        }

        catch (NamingException exception)
        {
            //System.out.println("Error when trying to create the context");
        }
       return assignOfficeDTO;
    }
}