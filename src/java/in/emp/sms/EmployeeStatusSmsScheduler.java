/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.sms;
import java.util.*;
import java.text.SimpleDateFormat;
/**
 *
 * @author Pooja Jadhav
 */
public class EmployeeStatusSmsScheduler extends TimerTask {
    private static Timer timer;
    private static TimerTask timerTask;
    private final static long FREQUENCY = 1000*60*60*24;
    private static int MAHAONLINE_EXECUTION_HH = 9;
    private static int MAHAONLINE_EXECUTION_MM = 15;
   public static void startScheduler() throws Exception {
        try { 

            timerTask = new SmsScheduler();
            timer = new Timer("EmployeeStatusSmsScheduler");
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
        System.out.println("Send EmployeeStatusSmsScheduler  First Run Time ..... "+result.getTime());
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
                System.out.println("EmployeeStatusSmsScheduler timerTask Stopped..... "+new Date());
            }

            if (timer != null) {
                timer.cancel();
                System.out.println("EmployeeStatusSmsScheduler timer Stopped..... "+new Date());
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
 public void run() {
        try {
            SimpleDateFormat formatter= new SimpleDateFormat();
          Date date=new Date();
         System.out.println("EmployeeStatusSmsScheduler Task executed"+formatter.format(date));
            System.out.println("EmployeeStatusSmsScheduler Scheduled ..... "+new Date());           
                 SendStatusSmsEmployee.SendStatusSms();
        } catch (Exception ex) {
            System.out.println("Exception EmployeeStatusSmsScheduler run : "+ex.getMessage());
        }
    }
   
}
