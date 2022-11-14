/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.sms;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Pooja Jadhav
 */


   
public class VendorSmsScheduler extends TimerTask 
{
   private static Timer timer;
    private static TimerTask timerTask;
    private final static long FREQUENCY = 1000*60*60*24;
    private static int MAHAONLINE_EXECUTION_HH = 20;
    private static int MAHAONLINE_EXECUTION_MM = 30;
   public static void startScheduler() throws Exception {
        try { 

            timerTask = new VendorSmsScheduler();
            timer = new Timer("VendorSmSScheduler");
            timer.schedule(timerTask, getFirstRunTime(), FREQUENCY);
           
        } catch (Exception ex) {
            throw ex;      
        }
      
   }
  private static Date getFirstRunTime()
    {
        Calendar today = new GregorianCalendar();
        Calendar result = new GregorianCalendar(
          today.get(Calendar.YEAR),
          today.get(Calendar.MONTH),
          today.get(Calendar.DATE),
          MAHAONLINE_EXECUTION_HH,
          MAHAONLINE_EXECUTION_MM
        );
        System.out.println("Send Vendor SMS Scheduler First Run Time ..... "+result.getTime());
        return result.getTime();
  }
  public static void resetScheduler() throws Exception {
        try {
            if (timerTask != null) {
                timerTask.cancel();
            }

            startScheduler();

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void stopScheduler() throws Exception {
        try {
            if (timerTask != null) {
                timerTask.cancel();
                System.out.println("SendVendorSmsScheduler timerTask Stopped..... "+new Date());
            }

            if (timer != null) {
                timer.cancel();
                System.out.println("SendVendorSmsScheduler timer Stopped..... "+new Date());
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
 public void run() {
        try {
            SimpleDateFormat formatter= new SimpleDateFormat();
          Date date=new Date();
         System.out.println("SendVendorSms Task executed"+formatter.format(date));
            System.out.println("SendVendorSmsScheduler Scheduled ..... "+new Date());           
                 SendSmsVendor.SendSms();
        } catch (Exception ex) {
            System.out.println("Exception SendVendorSmsScheduler run : "+ex.getMessage());
        }
    }
   
}