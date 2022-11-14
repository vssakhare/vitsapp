/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.ldap;

import java.net.Socket;
import javaldapapp.AssignOfficeDTO;
import javaldapapp.JavaLDapConnection;

/**
 *
 * @author ipchaudhari
 */
public class LDAP {

    AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
    private String uid = ""; //"02626276";
    private String p = ""; //"Password@1";

    public static void main(String[] args) {
            LDAP ldap = new LDAP();
            ldap.getDetails();
    }

    public AssignOfficeDTO getLogin( String uid, String pass ) throws Exception {
//        String ip = "10.0.15.27";
//        int port = 389;
        LDAP ldapTest = new LDAP();
//        Socket client = null;
        ldapTest.uid = uid;
        ldapTest.p = pass;

        try {
//            client = new Socket(ip, port);
//            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            ldapTest.getDetails();

//            client.close();
           

            return ldapTest.assignOfficeDTO;
        } catch (Exception e) {
//            if (!(client.isClosed())) {
//            client.close();
//            }
            throw e;
        } finally {
//            if (!(client.isClosed())) {
//                client.close();
//            }
        }

    }

    public void getDetails() {
        JavaLDapConnection javaLDapConnection = new JavaLDapConnection();


        assignOfficeDTO = (AssignOfficeDTO) javaLDapConnection.authenticate(uid, p);


    }
}
