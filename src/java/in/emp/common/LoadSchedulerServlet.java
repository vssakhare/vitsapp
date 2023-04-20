/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;

/**
 *
 * @author Pooja Jadhav
 */

import in.emp.legal.scheduler.LegalVendorSmsScheduler;
import in.emp.legal.scheduler.ReadLegalInvoiceDetailsScheduler;
import in.emp.sms.EmployeeStatusSmsScheduler;
import in.emp.sms.EscEmpStatusSmsScheduler;
import in.emp.sms.SendSMS;
import in.emp.sms.SendSmsVendor;
import in.emp.sms.SendStatusSmsEmployee;
import in.emp.sms.SendStatusSmsEscEmployee;
import in.emp.sms.SendSubmittedSmsEmployee;
import in.emp.sms.VendorSmsScheduler;
import in.emp.sms.SmsScheduler;
import javaldapapp.AssignOfficeDTO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadSchedulerServlet extends HttpServlet
{
    public void init(ServletConfig config) throws ServletException
	{
		try
		{
                 //     SendSmsVendor.SendSms();
                // SendStatusSmsEscEmployee.SendStatusEscSms();//TEST STATUS ESCALATION SMS
                  //    SendStatusSmsEmployee.SendStatusSms(); //TEST STATUS SMS
              //     SendSMS.SendHigherAuthSms(); //TEST SUBMIT ESCALATION SMS
                 //  SendSubmittedSmsEmployee.SendAuthSms(); //send sms which  are not sent to employee during submission of invoice.
              //   ReadPOLineStatus rp = new ReadPOLineStatus();//load po line file
              //    rp.readPOLineFile();
              //  WriteInvoiceStatus wi=new WriteInvoiceStatus();
       //  wi.WriteInvoiceFile();  
       //  ReadPOStatus po=new ReadPOStatus();//load po file
                   //Comment this line before deploy.
         //  po.readPOFile();
       //     ReadPsStatus ps=new ReadPsStatus();//load ps file
                   //Comment this line before deploy.
        //     ps.readPsFile();
               //  ReadVendorStatus v=new ReadVendorStatus();//load vendor file
                  //Comment this line before deploy.
              // v.readVendorFile();
                   // SendMail.Sendmail("pooja0319@gmail.com","hi","test mail");
                    
         
                
            /*    SmsScheduler.startScheduler();//9 am higher authority regarding non processing of invoice.
                VendorSmsScheduler.startScheduler();//8.30 pm sap invoice status to vendor
                EmployeeStatusSmsScheduler.startScheduler();//9.15 am sap invoice status to employee
                EscEmpStatusSmsScheduler.startScheduler();//9.30 am sap invoice status to employee on no processing  of invoice.
                ReadPOLineScheduler.startScheduler(); //11.15 pm read  po line  status file
                ReadPOFileScheduler.startScheduler(); //2.30 am read  po status file// READ ON ANOTHER DAY TO GET SYSDATE FOR SMS
                ReadVendorFileScheduler.startScheduler();//11.05 pm read vendor file
                ReadPSFileScheduler.startScheduler(); //2.30 am read ps file
                WriteInvSatusScheduler.startScheduler();  //10.30 pm write inv file
                ReadRetentionResponseScheduler.startScheduler();
                    ReadLegalInvoiceDetailsScheduler.startScheduler();
                LegalVendorSmsScheduler.startScheduler();*/
            }
		catch(Exception ex)
		{
                    //ex.printStackTrace();
		}
	}

	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException
	{
		try
		{
			//Scheduler.resetScheduler();
		}
		catch(Exception ex)
		{
		}
	}

	public void destroy()
	{
		try
		{
                    SmsScheduler.stopScheduler();
                    VendorSmsScheduler.stopScheduler();
                    EmployeeStatusSmsScheduler.stopScheduler();
                    EscEmpStatusSmsScheduler.stopScheduler();
                    ReadPOLineScheduler.stopScheduler();
                    ReadPOFileScheduler.stopScheduler();
                    ReadVendorFileScheduler.stopScheduler();
                    ReadPSFileScheduler.stopScheduler();
                    WriteInvSatusScheduler.stopScheduler();
		}
		catch(Exception ex)
		{
		}
	}
}

