<%-- 
    Document   : jspbarchart_zonewise
    Created on : May 6, 2013, 5:27:26 PM
    Author     : rgchoudhari
--%>

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
String newBu1 = "";
String newBu2="";
String newBu3="";
String newBu4="";
String newBu5="";
String newBu6="";
String newBu7="";
String newBu8="";
String newBu9="";
String newBu10="";
String newBu11="";
String newBu12="";
String newBu13="";
String newBu14="";
String newBu15="";
String newBu16="";
String newBu17="";
String newBu18="";
String newBu19="";
String title="";
String path=request.getSession().getServletContext().getRealPath("/")+"images/";  


String newCircle= "";

int  Current_Missing= 0;
int  x_value1= 0;
int  x_value2= 0;
int  x_value3= 0;
int  x_value4= 0;
int  x_value5= 0;
int  x_value6= 0;
int  x_value7= 0;
int  x_value8= 0;
int  x_value9= 0;
int  x_value10= 0;
int  x_value11= 0;
int  x_value12= 0;
int  x_value13= 0;
int  x_value14= 0;
int  x_value15= 0;
int  x_value16= 0;
int  x_value17= 0;
int  x_value18= 0;
int  x_value19= 0;

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
while (locItr.hasNext()) {
TamperEventsBean tamperEventsBeanObj = (TamperEventsBean) locItr.next();
newBu = tamperEventsBeanObj.getZoneCode();//
newCircle = tamperEventsBeanObj.getCircleCode();
System.out.println("zone"+newBu);
System.out.println("newCircle"+newCircle);

if(newCircle==null && newBu!=null ){
//if(reportBean.getEventId()==1)    
    title=newBu+" Zone Tamper Statistics";
x_value1 = Integer.parseInt(tamperEventsBeanObj.getPotential_Missing());

x_value19 = Integer.parseInt(tamperEventsBeanObj.getLoad_Imbalance());
//else if(reportBean.getEventId()==2)    
x_value2 = Integer.parseInt(tamperEventsBeanObj.getLoad_Unbalance());
//else if(reportBean.getEventId()==3)    
x_value3 = Integer.parseInt(tamperEventsBeanObj.getPower_Failed());
//else if(reportBean.getEventId()==4)    
x_value4 = Integer.parseInt(tamperEventsBeanObj.getVoltage_Unbalance());
//else if(reportBean.getEventId()==5)    
x_value5 = Integer.parseInt(tamperEventsBeanObj.getCT_Short());
//else if(reportBean.getEventId()==6)    
x_value6 = Integer.parseInt(tamperEventsBeanObj.getCT_Open());
//else if(reportBean.getEventId()==7)    
x_value7 = Integer.parseInt(tamperEventsBeanObj.getMagnet());
//else if(reportBean.getEventId()==8)    
x_value8 = Integer.parseInt(tamperEventsBeanObj.getNeutral_Disturbance());
//else if(reportBean.getEventId()==9)    
x_value9 = Integer.parseInt(tamperEventsBeanObj.getHigh_Neutral_Current());
//else if(reportBean.getEventId()==10)    
x_value10 = Integer.parseInt(tamperEventsBeanObj.getCurrent_Missing());
//else if(reportBean.getEventId()==11)    
x_value11 = Integer.parseInt(tamperEventsBeanObj.getHigh_Voltage());
//else if(reportBean.getEventId()==12)    
x_value12 = Integer.parseInt(tamperEventsBeanObj.getHigh_Harmonics_Dist_Current());
//else if(reportBean.getEventId()==13)    
x_value13 = Integer.parseInt(tamperEventsBeanObj.getMeter_Cover_Open());
//else if(reportBean.getEventId()==14)    
x_value14 = Integer.parseInt(tamperEventsBeanObj.getRTC_Change());
//else if(reportBean.getEventId()==15)    
x_value15 = Integer.parseInt(tamperEventsBeanObj.getMeter_Transaction_Event());
//else if(reportBean.getEventId()==17)    
x_value16 = Integer.parseInt(tamperEventsBeanObj.getOverload_Current());
//else if(reportBean.getEventId()==18)    
x_value17 = Integer.parseInt(tamperEventsBeanObj.getCT_Reversed());
//else if(reportBean.getEventId()==19)    
x_value18 = Integer.parseInt(tamperEventsBeanObj.getOther_tamper());


newBu1="ND"+'('+x_value8+')';
newBu2="LI"+'('+x_value19+')';
newBu3="HV"+'('+x_value11+')';
newBu4="CM"+'('+x_value10+')';
newBu5="HNC"+'('+x_value9+')';
newBu6="MGNT"+'('+x_value7+')';
newBu7="LU"+'('+x_value2+')';
newBu8="PM"+'('+x_value1+')';
newBu9="MCO"+'('+x_value13+')';
newBu10="OC"+'('+x_value16+')';
newBu11="RTC"+'('+x_value14+')';
newBu12="MTE"+'('+x_value15+')';
newBu13="PF"+'('+x_value3+')';
newBu14="VU"+'('+x_value4+')';
newBu15="CTO"+'('+x_value6+')';
newBu16="HHDC"+'('+x_value12+')';
newBu17="CTS"+'('+x_value5+')';
newBu18="OTHR"+'('+x_value18+')';
newBu19="CR"+'('+x_value17+')';



//newBu2=newBu+'('+x_value+')' ;
          //dataSet.addValue(x_value, "series",newBu2);
          
          dataSet.addValue(x_value8, "series",newBu1);
          dataSet.addValue(x_value19, "series",newBu2);
          dataSet.addValue(x_value11, "series",newBu3);
          dataSet.addValue(x_value10, "series",newBu4);
          dataSet.addValue(x_value9, "series",newBu5);
          dataSet.addValue(x_value7, "series",newBu6);
          dataSet.addValue(x_value2, "series",newBu7);
          dataSet.addValue(x_value1, "series",newBu8);
          dataSet.addValue(x_value13, "series",newBu9);
          dataSet.addValue(x_value16, "series",newBu10);
          dataSet.addValue(x_value14, "series",newBu11);
          dataSet.addValue(x_value15, "series",newBu12);
          dataSet.addValue(x_value3, "series",newBu13);
          dataSet.addValue(x_value4, "series",newBu14);
          dataSet.addValue(x_value6, "series",newBu15);
          dataSet.addValue(x_value12, "series",newBu16);
          dataSet.addValue(x_value5, "series",newBu17);
          dataSet.addValue(x_value18, "series",newBu18);
          dataSet.addValue(x_value17, "series",newBu19);
          
          
}
}
        JFreeChart chart = ChartFactory.createBarChart(
            title,
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
        <style type='text/css'>
td{font-family:Arial; color:black; font-size:9pt;}
</style>
    </head>   
    <body>
        <center>
             From <%=ApplicationUtils.dateToString(reportBean.getReadDateFrom(), "dd-MMM-yy")%> To <%=ApplicationUtils.dateToString(reportBean.getReadDateTo(), "dd-MMM-yy")%>
        <IMG SRC="<%=ApplicationConstants.IMAGE_PATH%>barchart.png" WIDTH="800" HEIGHT="400" BORDER="0" USEMAP="#chart">
        </center>
       
<table>
<tr>
<td ><B> Code Full Form</B></td>    
</tr> 
<tr>                 
<td width ="15%"><B>ND-</B>Neutral Disturbance</td>  <td width ="15%"><B>LI-</B>Load Imbalance</td> <td  width ="15%"><B>HV-</B>High Voltage</td> <td width ="15%"><B>CM-</B>Current Missing</td><td width ="15%" ><B>HNC-</B>High Neutral Current</td><td width ="15%"><B>MGNT-</B>Magnet</td>
</tr> 
<tr>
<td ><B>LU-</B>Load Unbalance</td><td ><B>PM-</B>Potential Missing</td><td ><B>MCO-</B>Meter Cover Open</td><td ><B>OC-</B>Overload (Current)</td>   <td ><B>RTC-</B>RTC Change</td>         <td ><B>MTE-</B>Meter Transaction Event</td>                                       
</tr> 
<tr>
<td ><B>PF-</B>Power Failed</td> <td ><B>VU-</B>Voltage Unbalance</td>        <td ><B>HHDC-</B>High Total Harmonics Distortion Current</td>         <td ><B>CTS-</B>CT Short</td>     <td ><B>OTHR-</B>Others</td>           <td ><B>CR-</B>CT Reversed</td>                                                           
</tr> 
</table> 
    
    </body>
</html>
