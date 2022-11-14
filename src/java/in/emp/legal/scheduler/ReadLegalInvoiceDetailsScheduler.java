/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Rikma Rai
 */
public class ReadLegalInvoiceDetailsScheduler extends TimerTask{
   private static Timer timer;
    private static TimerTask timerTask;
    //private static long sleepTime = 1000*60*60*24
    private static long sleepTime = 1000*60*2;
    
    private final static long FREQUENCY = 1000*60*60*24;
    private final static int fONE_DAY = 1;
    private static int MAHAONLINE_EXECUTION_HH = 11;
    private static int MAHAONLINE_EXECUTION_MM = 47;
   public static void startScheduler() throws Exception {
        try { 
            System.out.println("ReadLegalInvoiceDetailsScheduler started");
            timerTask = new ReadLegalInvoiceDetailsScheduler();
            timer = new Timer("ReadLegalInvoiceFileStatus");
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
        System.out.println("ReadLegalInvoiceDetailsScheduler First Run Time ..... "+result.getTime());
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
                System.out.println("ReadLegalInvoiceDetailsScheduler timerTask Stopped..... "+new Date());
            }

            if (timer != null) {
                timer.cancel();
                System.out.println("ReadLegalInvoiceDetailsScheduler timer Stopped..... "+new Date());
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
 public void run() {
        try {
            SimpleDateFormat formatter= new SimpleDateFormat();
          Date date=new Date();
         System.out.println("ReadLegalInvoiceDetailsScheduler Task executed"+formatter.format(date));
            System.out.println("ReadLegalInvoiceDetailsScheduler Scheduled ..... "+new Date());
           ReadLegalInvoiceFileStatus v=new ReadLegalInvoiceFileStatus();
           v.readLegalInvoiceFile();
        } catch (Exception ex) {
            System.out.println("Exception ReadLegalInvoiceDetailsScheduler run : "+ex.getMessage());
        }
    }  
}
