<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.reports.bean.TamperEventsBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.emp.search.bean.SearchBean"%>
<%@page import="java.util.LinkedList"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@page import="in.emp.reports.bean.ReportBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page  import="java.awt.*" %>
<%@ page  import="java.io.*" %>
<%@ page  import="org.jfree.chart.*" %>
<%@ page  import="org.jfree.chart.axis.*" %>
<%@ page  import="org.jfree.chart.entity.*" %>
<%@ page  import="org.jfree.chart.labels.*" %>
<%@ page  import="org.jfree.chart.plot.*" %>
<%@ page  import="org.jfree.chart.renderer.category.*" %>
<%@ page  import="org.jfree.chart.urls.*" %>
<%@ page  import="org.jfree.data.category.*" %>
<%@ page  import="org.jfree.data.general.*" %>
<%

ReportBean reportBean = new ReportBean();
LinkedList reportList = null;
String newBu = "";
String newCircle= "";
String newBu2="";
String event_name = "";
String path=request.getSession().getServletContext().getRealPath("/")+"images/"; 
int  Current_Missing= 0;
int  x_value= 0;
 if (request.getSession().getAttribute("reportBean") != null) {
        reportBean = (ReportBean) request.getSession().getAttribute("reportBean");
        System.out.println("reportBean.getReadDateFrom()"+reportBean.getReadDateFrom());
        System.out.println("event on graph"+reportBean.getEventId());


            reportList = (LinkedList) reportBean.getReportList();
 
    }
 Iterator reportListItr = null;
    Iterator locItr = null;
    if (reportList != null && reportList.size() > 0) {
        reportListItr = reportList.iterator();
        locItr = reportList.iterator();
    }
    DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
   //System.out.println("soutdate"+ApplicationUtils.dateToString(reportBean.getReadDateFrom(), "dd-MMM-yy"));
                        
while (locItr.hasNext()) {
TamperEventsBean tamperEventsBeanObj = (TamperEventsBean) locItr.next();
newBu = tamperEventsBeanObj.getZoneCode();//
newCircle = tamperEventsBeanObj.getCircleCode();
System.out.println("zone"+newBu);
System.out.println("newCircle"+newCircle);
if(newCircle==null && newBu !=null){
if(reportBean.getEventId()==1)   { 
x_value = Integer.parseInt(tamperEventsBeanObj.getPotential_Missing());
event_name="Potential Missing";
}
else if(reportBean.getEventId()==2)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getLoad_Unbalance());
event_name="Load Unbalance";
}
else if(reportBean.getEventId()==3)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getPower_Failed());
event_name="Power Failed";
}
else if(reportBean.getEventId()==4)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getVoltage_Unbalance());
event_name="Voltage Unbalance";
}
else if(reportBean.getEventId()==5)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getCT_Short());
event_name="CT Short";
}
else if(reportBean.getEventId()==6)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getCT_Open());
event_name="CT Open";
}
else if(reportBean.getEventId()==7)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getMagnet());
event_name="Magnet";
}
else if(reportBean.getEventId()==8)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getNeutral_Disturbance());
event_name="Neutral Disturbance";
}
else if(reportBean.getEventId()==9)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getHigh_Neutral_Current());
event_name="High Neutral Current";
}
else if(reportBean.getEventId()==10)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getCurrent_Missing());
event_name="Current Missing";
}
else if(reportBean.getEventId()==11)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getHigh_Voltage());
event_name="High Voltage";
}
else if(reportBean.getEventId()==12)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getHigh_Harmonics_Dist_Current());
event_name="High Total Harmonics Distortion Current";
}
else if(reportBean.getEventId()==13)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getMeter_Cover_Open());
event_name="Meter Cover Open";
}
else if(reportBean.getEventId()==14)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getRTC_Change());
event_name="RTC Change";
}
else if(reportBean.getEventId()==15)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getMeter_Transaction_Event());
event_name="Meter Transaction Event";
}
else if(reportBean.getEventId()==17)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getOverload_Current());
event_name="Overload (Current)";
}
else if(reportBean.getEventId()==18)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getCT_Reversed());
event_name="CT Reversed";
}
else if(reportBean.getEventId()==19)    {
x_value = Integer.parseInt(tamperEventsBeanObj.getOther_tamper());
event_name="Others";
}

event_name=event_name+"Tamper Statistics";
newBu2=newBu+" Zone"+'('+x_value+')' ;
          dataSet.addValue(x_value, "series",newBu2);
}
}
        JFreeChart chart = ChartFactory.createBarChart(
            event_name,
            null,
            null,
            dataSet,
            PlotOrientation.VERTICAL,
            false,
            false,
            false
        );
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
 
        chart.setBackgroundPaint(ChartColor.WHITE);

            try {
                final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
                final File file1 = new File(path+"barchart.png");
                ChartUtilities.saveChartAsPNG(file1, chart, 800, 400, info);
            } catch (Exception e) {
                out.println(e);
            }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <!meta  http-equiv="refresh" content="1">
        <title>Tamper Statistics Report</title>
    </head>   
    <body>
        <center>
            From <%=ApplicationUtils.dateToString(reportBean.getReadDateFrom(), "dd-MMM-yy")%> To <%=ApplicationUtils.dateToString(reportBean.getReadDateTo(), "dd-MMM-yy")%>
        <IMG SRC="<%=ApplicationConstants.IMAGE_PATH%>barchart.png" WIDTH="800" HEIGHT="400" BORDER="0" USEMAP="#chart">
        </center>
    </body>
</html>
