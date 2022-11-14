package in.emp.sms;
import java.util.*;
import java.text.SimpleDateFormat;
   
public class SmsScheduler extends TimerTask 
{
   private static Timer timer;
    private static TimerTask timerTask;
    private final static long FREQUENCY = 1000*60*60*24;
    private static int MAHAONLINE_EXECUTION_HH = 9;
    private static int MAHAONLINE_EXECUTION_MM = 00;
   public static void startScheduler() throws Exception {
        try { 

            timerTask = new SmsScheduler();
            timer = new Timer("EmployeeSmSScheduler");
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
        System.out.println("Send SMS Scheduler First Run Time ..... "+result.getTime());
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
                System.out.println("SendSmsScheduler timerTask Stopped..... "+new Date());
            }

            if (timer != null) {
                timer.cancel();
                System.out.println("SendSmsScheduler timer Stopped..... "+new Date());
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
 public void run() {
        try {
            SimpleDateFormat formatter= new SimpleDateFormat();
          Date date=new Date();
         System.out.println("SendSms Task executed"+formatter.format(date));
            System.out.println("SendSmsScheduler Scheduled ..... "+new Date());           
                 SendSMS.SendHigherAuthSms();
        } catch (Exception ex) {
            System.out.println("Exception SendSmsScheduler run : "+ex.getMessage());
        }
    }
   
  }