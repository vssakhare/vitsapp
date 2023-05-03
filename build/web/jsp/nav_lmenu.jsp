<%@page import="in.emp.util.ApplicationUtils"%>
<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="java.util.HashMap"%>
<%
            boolean isLoggedIn = false;
            String selected = request.getParameter("selected");
            String classHome = "hor_nav_normal";
            String classSearch = "hor_nav_normal";
            String classSchedule = "hor_nav_normal";
            String classAnalysis = "hor_nav_normal";
            String classAdmin = "hor_nav_normal";
            String classFileMgmt = "hor_nav_normal";
            String classReadings = "hor_nav_normal";

            boolean showScheduleTab = false;
            boolean homeTab = false;
            boolean showAdminSecurityMenu = false;
            boolean showAdminMeterMenu = false;
            boolean showAdminModemMenu = false;
            boolean showAnalysisTab = false;
            boolean showAdminTab = false;
            boolean showMeterDataTab = false;
            boolean showMeterTab = false;
            boolean showMeterModemConnTab = false;
            //-- File Mgmt
            boolean showConsFileLload = false;
            boolean showLoadXMLFileTab = false;
            boolean showFileMgmtTab = false;
            boolean showLoadFileHistroyTab = false;
            //-- Report
            boolean showLoadGraphReport = false;
            boolean showStatisticsReport = false;//getStatistics
            boolean showRepPTUnbN = false;
            boolean showRepPTUnbS = false;
            boolean showMeterDetailsRepTab = false;
            boolean showInstParamsRepTab = false;
            boolean showTamperEventRepTab = false;
            boolean showModemStatistics = false;
            boolean showR65 = false;
            boolean showCTunbSevere=false;
            boolean showCTReversal=false;
            boolean showLowDemand = false;
            boolean showMaxDemand = false;
            boolean showModemPopup = false;
            //-- Readings
            boolean showReadingsTab = false;
            boolean showReadingsList = false;
            boolean showManualReadings = false;
            boolean showTamperNotRestored = false;
            boolean showIntermittentTamper = false;

            String modemInfoPopUrl = "";            


            if (selected.equals("home_css")) {
                classHome = "hor_nav_normal_selected";
            } else if (selected.equals("search_css")) {
                classSearch = "hor_nav_normal_selected";
            } else if (selected.equals("schedule_css")) {
                classSchedule = "hor_nav_normal_selected";
            } else if (selected.equals("analysis_css")) {
                classAnalysis = "hor_nav_normal_selected";
            } else if (selected.equals("filemgmt_css")) {
                classFileMgmt = "hor_nav_normal_selected";
            } else if (selected.equals("admin_css")) {
                classAdmin = "hor_nav_normal_selected";
            } else if (selected.equals("reading_css")) {
                classReadings = "hor_nav_normal_selected";
            }


            if (session.getAttribute(ApplicationConstants.USER_SESSION_NAME) != null) {
                isLoggedIn = true;
            }

            HashMap functionMap = new HashMap();
            if (request.getSession().getAttribute("userFunctionMap") != null) {
                functionMap = (HashMap) request.getSession().getAttribute("userFunctionMap");
            }

            //-- Start showScheduleTab
            if (functionMap.containsKey(ApplicationConstants.UIACTION_SCHEDULE_SEARCH_GET)) {
                showScheduleTab = true;
            }

            //-- End showScheduleTab

            //-- Start searchTab
            //-- start MeterData Tab
            if (functionMap.containsKey(ApplicationConstants.UIACTION_SEARCH_GET)) {
                showMeterDataTab = true;
            }

            //-- Start Meter Tab
            if (functionMap.containsKey(ApplicationConstants.UIACTION_NETWORK_LOCATION_GET)) {
                showMeterTab = true;
            }
            //-- End searchTab

            //-- Start AnalysisTab
            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_LOAD_GRAPH_REPORT)) {
                showLoadGraphReport = true;
            }

            if (functionMap.containsKey(ApplicationConstants.UIACTION_REPORT_STATS_GET)) {
                showStatisticsReport = true;
            }

            if (functionMap.containsKey(ApplicationConstants.UIACTION_REPORT_PT_UNB_N_GET)) {
                showRepPTUnbN = true;
            }

            if (functionMap.containsKey(ApplicationConstants.UIACTION_REPORT_PT_UNB_S_GET)) {
                showRepPTUnbS = true;
            }

            if (functionMap.containsKey(ApplicationConstants.UIACTION_REPORT_METER_DETAILS_GET)) {
                showMeterDetailsRepTab = true;
            }
            if (functionMap.containsKey(ApplicationConstants.UIACTION_REPORT_MODEM_DETAILS_GET)) {
                showModemStatistics = true;
            }
            if (functionMap.containsKey(ApplicationConstants.UIACTION_REPORT_R65)) {
                showR65 = true;
            }
            if (functionMap.containsKey(ApplicationConstants.UIACTION_REPORT_CT_UNB_S_GET)) {
                showCTunbSevere = true;
            }
            if (functionMap.containsKey(ApplicationConstants.UIACTION_REPORT_CT_REVERSAL_GET)) {
                showCTReversal = true;
            }
            if (functionMap.containsKey(ApplicationConstants.UIACTION_REPORT_GET_LOW_DEMAND)) {
                showLowDemand = true;
            }
            if (functionMap.containsKey(ApplicationConstants.UIACTION_REPORT_GET_MAX_DEMAND)) {
                showMaxDemand = true;
            }
            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_TAMPER_NOT_STORED_CONSWISE) || functionMap.containsKey(ApplicationConstants.UIACTION_GET_TAMPER_NOT_STORED_TAMPWISE)) {
                showTamperNotRestored = true;
            }

            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_INTERMITTENT_TAMPER)) {
                showIntermittentTamper = true;
            }

            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_MODEM_POPUP)) {
                showModemPopup = true;
            }

            if (functionMap.containsKey(ApplicationConstants.UIACTION_REPORT_TAMPER_EVENTS_GET)) {
                showTamperEventRepTab = true;
            }


            if (functionMap.containsKey(ApplicationConstants.UIACTION_REPORT_INST_PARAMS_GET)) {
                showInstParamsRepTab = true;
            }
            //,

            if (showLoadGraphReport || showStatisticsReport || showRepPTUnbN || showRepPTUnbS || showModemStatistics || showR65 || showInstParamsRepTab || showModemPopup) {
                showAnalysisTab = true;
            }
            //-- End AnalysisTab

            //-- Start FileMgmtTab
            if (functionMap.containsKey(ApplicationConstants.UIACTION_CONSUMER_FILE_GET)) {
                showConsFileLload = true;
            }

            if (functionMap.containsKey(ApplicationConstants.UIACTION_XML_FILE_GET)) {
                showLoadXMLFileTab = true;
            }
            if (showLoadXMLFileTab || showConsFileLload) {
                showFileMgmtTab = true;
            }
            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_LOAD_FILE_HISTORY)) {
                showLoadFileHistroyTab = true;
            }
            //-- End FileMgmtTab

            //-- Start AdminTab
            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_ROLE) || functionMap.containsKey(ApplicationConstants.UIACTION_GET_USER)) {
                showAdminSecurityMenu = true;
            }

            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_METER) || functionMap.containsKey(ApplicationConstants.UIACTION_GET_METER_TYPE)) {
                showAdminMeterMenu = true;
            }

            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_MODEM) || functionMap.containsKey(ApplicationConstants.UIACTION_GET_MODEM_TYPE)) {
                showAdminModemMenu = true;
            }

            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_DEVICE_CONNECTION)) {
                showMeterModemConnTab = true;
            }

            if (showAdminSecurityMenu || showAdminMeterMenu || showAdminModemMenu || showMeterModemConnTab) {
                showAdminTab = true;
            }
            //-- End AdminTab

            //-- Readings Tab
            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_READINGS)) {
                showReadingsList = true;
            }
            if (functionMap.containsKey(ApplicationConstants.UIACTION_MANUAL_READINGS)) {
                showManualReadings = true;
            }

            if (showReadingsList) {
                showReadingsTab = true;
            }

            //-- End Readings Tab
%>
<ul class="hor_menu_holder">
    <SCRIPT LANGUAGE="JavaScript">
        <!--

        var winOpened = false ;
        var a;



        function openWinPopup(popupurl,name,len,flag)
        {
            a = window.open(popupurl,name,len);
            winOpened = true;
        }


        //-->
    </SCRIPT>
    <!-- logout link -->
    <%
        if (isLoggedIn) {
    %>
    <li class="hor_nav_normal"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_LOGIN)%>" onclick="logout('<%=ApplicationConstants.UIACTION_GET_LOGIN%>');"><strong>Logout</strong></a></li>
    <li class="hor_nav_divline">&nbsp;&nbsp;</li>
    <%
        }
    %>
    <!-- logout link close -->

    <%
        if (isLoggedIn) {
            if (homeTab) {
    %>
    <li class= <%=classHome%> ><a href= "<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>"><strong> Home</strong></a></li>
    <%
                    } else {
    %>
    <li class= <%=classHome%> ><a href= "<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>"><strong> Home</strong></a></li>
    <%
                }
            } else {
    %>
    <li class= <%=classHome%> ><a href= "<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>"><strong> Home</strong></a></li>
    <%
        }
    %>

    <li class="hor_nav_divline"></li>

    <%
        if (isLoggedIn) {
            if (showMeterTab || showMeterDataTab) {

    %>
    <li class=<%=classSearch%>><a href= "#nogo" onMouseOver="divMenu.show('meterSearch', this, 40, 20)"  onmouseout="divMenu.hide('meterSearch')"><strong> Search </strong></a></li>
    <li class="hor_nav_divline"></li>
    <%
}
if (showScheduleTab) {
    %>
    <li class=<%=classSchedule%>><a href= "<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_SCHEDULE_SEARCH_GET)%>"><strong> Schedule </strong></a></li>
    <li class="hor_nav_divline"></li>
    <%
}
} else {
    %>
    <li class=<%=classSearch%>><strong><a href="#nogo"> Search </a></strong></li>
    <li class="hor_nav_divline"></li>
    <li class=<%=classSchedule%>><strong><a href="#nogo"> Schedule </a></strong></li>
    <li class="hor_nav_divline"></li>
    <%
        }
        if (isLoggedIn) {
            if (showAnalysisTab) {
    %>
    <li class="<%=classAnalysis%>"><a href="#nogo" onMouseOver="divMenu.show('mReports', this, 40, 20)"  onmouseout="divMenu.hide('mReports')"><strong> Reports </strong></a></li>
    <li class="hor_nav_divline"></li>
    <%
                }
            } else {
    %>
    <li class=<%=classAnalysis%>><strong><a href="#nogo"> Analysis </a></strong></li>
    <li class="hor_nav_divline"></li>
    <%
        }

        if (isLoggedIn) {
            if (showFileMgmtTab) {
    %>
    <li class="<%=classFileMgmt%>"><a href="#nogo" onMouseOver="divMenu.show('mFileMgmt', this, 40, 20)"  onmouseout="divMenu.hide('mFileMgmt')"><strong> File Management </strong></a></li>
    <li class="hor_nav_divline"></li>
    <%          }
        }

        if (isLoggedIn) {
            if (showReadingsTab) {
    %>
    <li class="<%=classReadings%>"><a href="#nogo" onMouseOver="divMenu.show('mReadings', this, 40, 20)"  onmouseout="divMenu.hide('mReadings')"><strong> Readings </strong></a></li>
    <li class="hor_nav_divline"></li>
    <%          }
        }

        if (isLoggedIn) {
            if (showAdminTab) {
    %>
    <li class="<%=classAdmin%>"><a href="#nogo" onMouseOver="divMenu.show('mAdmin', this, 40, 20)"  onmouseout="divMenu.hide('mAdmin')"><strong>Administration</strong></a></li>
    <li class="hor_nav_divline"></li>
    <%
                }
            } else {
    %>
    <li class="<%=classAdmin%>"><strong><a href="#nogo"> Administration </a></strong></li>
    <li class="hor_nav_divline"></li>
    <%
        }
    %>

</ul>

<!-- Start Reports subnavigation Tabs -->
<div class="menudiv2" id="mReports">
    <%
        /*if(showLoadGraphReport)
        {
            %>
    <!--<ul id="hor_nav_sub">
             <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_LOAD_GRAPH_REPORT)%>">Load Graph</a></li>
     </ul>
    <%
        }

        if(showStatisticsReport)
        {
            %>
                <ul id="hor_nav_sub">
                        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_STATS_GET)%>">Statastics Report</a></li>
                </ul>-->
    <%
        }*/

        if (showRepPTUnbN) {
    %>
    <ul id="hor_nav_sub">
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_PT_UNB_N_GET)%>">Report 1 - PT Unbalance (Nominal)</a></li>
    </ul>
    <%
        }

        if (showRepPTUnbS) {
    %>
    <ul id="hor_nav_sub">
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_PT_UNB_S_GET)%>">Report 2 - PT Unbalance (Severe)</a></li>
    </ul>
    <%
        }

        if (showMeterDetailsRepTab) {
    %>
    <ul id="hor_nav_sub">
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_LIST_INSTALLPOINT_GET)%>">Install Point List</a></li>
    </ul>

    <%
        }
        if (showModemStatistics) {
    %>
    <ul id="hor_nav_sub">
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_MODEM_DETAILS_GET)%>">Modem Statistics</a></li>
    </ul>

    <%
}
if (showR65) {
    %>
    <ul id="hor_nav_sub">
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_R65)%>">R65</a></li>
    </ul>
    <%
}
        if (showCTunbSevere) {
    %>
    <ul id="hor_nav_sub">
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_CT_UNB_S_GET)%>">CT Unbalance Severe</a></li>
    </ul>
    <%
}
        if (showCTReversal) {
    %>
    <ul id="hor_nav_sub">
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_CT_REVERSAL_GET)%>">CT Reversal</a></li>
    </ul>
    <%
}
if (showLowDemand) {
    %>
    <ul id="hor_nav_sub">
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_GET_LOW_DEMAND)%>">Low Average Demand</a></li>
    </ul>
    <%
}
if (showMaxDemand) {
    %>
    <ul id="hor_nav_sub">
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_GET_MAX_DEMAND)%>">Max Average Demand</a></li>
    </ul>
    <%
}
if (showTamperNotRestored) {
    %>
    <ul id="hor_nav_sub">
        <li class="hor_nav_normal2_arrow"><a href= "#nogo" onMouseOver="divMenu.show('tamperSearch', this, 10, 10)"  onmouseout="divMenu.hide('tamperSearch')"> Tamper Not Restored </a></li>

    </ul>

    <% }
            if (showIntermittentTamper) {%>
    <ul id="hor_nav_sub">
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_INTERMITTENT_TAMPER)%>">Intermittent Tamper Event</a></li>
    </ul>
    <%
}
if (showModemPopup) {
modemInfoPopUrl = "'" + ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_MODEM_POPUP) + "','1017','status=yes,scrollbars=yes,width=1100,height=600,top=100,left=200'";
    %>
    <ul id="hor_nav_sub">
        <li class="hor_nav_normal2_arrow"><a href="javascript:openWinPopup(<%=modemInfoPopUrl%>)">Modem Connection Status</a></li>
    </ul>
    <%
}
/*if(showTamperEventRepTab)
{
            %>
    <!-- <ul id="hor_nav_sub">
             <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_TAMPER_EVENTS_GET)%>">Tamper Events</a></li>
     </ul>
    <% }

        if(showInstParamsRepTab)
        {
            %>
                <ul id="hor_nav_sub">
                        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_INST_PARAMS_GET)%>">Instance Parameter Report</a></li>
                </ul>
    <%
        }

        if(showInstParamsRepTab)
            {
            %>
         <ul id="hor_nav_sub">
                        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_FEEDER_LIST_GET)%>">Feeder List</a></li>
          </ul>-->
    <%
        }*/
    %>
</div>
<div class="menudiv2_sub" id="tamperSearch" style="height:44px; width:275px" >
    <ul id="hor_nav_sub">
        <%
            if (showTamperNotRestored) {
        %>
        <li class="hor_nav_sub2"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_TAMPER_NOT_STORED_TAMPWISE)%>">Tamper Event(Not Restored) wise Consumer Details </a></li>
        <li class="hor_nav_sub2"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_TAMPER_NOT_STORED_CONSWISE)%>">Consumer Wise Tamper Event(Not Restored) Details </a></li>
        <% }%>
    </ul>

</div>
<!-- Start File Management subnavigation Tabs -->
<div class="menudiv2" id="mFileMgmt">
    <ul id="hor_nav_sub">
        <%
            if (showLoadXMLFileTab) {
        %>
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_XML_FILE_GET)%>">Load XML Files</a></li>
        <%
            }

            /*if(showConsFileLload)
            {*/
        %>
                   <!-- <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_CONSUMER_FILE_GET)%>">Load Consumer File</a></li>-->
        <%
            /*}*/            if (showLoadFileHistroyTab) {
        %>
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_LOAD_FILE_HISTORY)%>">Load File Histroy</a></li>
        <%
            }


        %>
        <ul id="hor_nav_sub">
            <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_REPORT_FEEDER_LIST_GET)%>">Consumer Billing File</a></li>
        </ul>
    </ul>


</div>

<!-- Start Readings subnavigation Tabs -->
<div class="menudiv2" id="mReadings">
    <ul id="hor_nav_sub">
        <%
            if (showReadingsList) {
        %>
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_READINGS)%>">Readings</a></li>
        <%
            }
            if (showManualReadings) {
        %>
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_MANUAL_READINGS)%>">Manual Readings</a></li>
        <%
            }
        %>
    </ul>
</div>


<!-- Start Administration Tab -->
<div class="menudiv2" id="mAdmin">
    <ul id="hor_nav_sub">
        <%
            if (showAdminSecurityMenu) {
        %>
        <li class="hor_nav_normal2_arrow"><a onMouseOver="divMenu.show('mAdmin_security', this, 10, 12)" onMouseOut="divMenu.hide('mAdmin_security')" href="#nogo"><span>Security</span></a></li>
        <%            }
        %>
        <%
            if (showAdminMeterMenu) {
        %>
        <li class="hor_nav_normal2_arrow"><a onMouseOver="divMenu.show('mAdmin_meters', this, 10, 12)" onMouseOut="divMenu.hide('mAdmin_meters')" href="#nogo"><span>Meters</span></a></li>
        <%            }
        %>
        <%
            if (showAdminModemMenu) {
        %>
        <li class="hor_nav_normal2_arrow"><a onMouseOver="divMenu.show('mAdmin_modems', this, 10, 12)" onMouseOut="divMenu.hide('mAdmin_modems')" href="#nogo"><span>Modems</span></a></li>
        <%            }
        %>
        <%
            if (showMeterModemConnTab) {
        %>
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_DEVICE_CONNECTION)%>">Meter-Modem Connection</a></li>
        <%
            }
        %>
    </ul>
</div>

<!-- Start Security Menu -->
<div class="menudiv2_sub" id="mAdmin_security" style="height:44px">
    <ul id="hor_nav_sub">
        <%
            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_ROLE)) {
        %>
        <li class="hor_nav_sub2"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_ROLE)%>">Role</a></li>
        <%
            }
            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_USER)) {
        %>
        <li class="hor_nav_sub2"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_USER)%>">User</a></li>
        <%
            }
        %>
    </ul>
</div>
<!-- End Security Menu -->
<!-- Start Meter Menu -->
<div class="menudiv2_sub" id="mAdmin_meters" style="height:44px">
    <ul id="hor_nav_sub">
        <%
            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_METER_TYPE)) {
        %>
        <li class="hor_nav_sub2"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_METER_TYPE)%>">Meter Type</a></li>
        <%
            }
            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_METER)) {
        %>
        <li class="hor_nav_sub2"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_METER)%>">Meter</a></li>
        <%
            }
        %>
    </ul>
</div>
<!-- End Meter Menu -->
<!-- Start Modem Menu -->
<div class="menudiv2_sub" id="mAdmin_modems" style="height:44px">
    <ul id="hor_nav_sub">
        <%
            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_MODEM_TYPE)) {
        %>
        <li class="hor_nav_sub2"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_MODEM_TYPE)%>">Modem Type</a></li>
        <%
            }
            if (functionMap.containsKey(ApplicationConstants.UIACTION_GET_MODEM)) {
        %>
        <li class="hor_nav_sub2"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_MODEM)%>">Modem</a></li>
        <%
            }
        %>
    </ul>
</div>
<!-- End Modem Menu -->
<!-- Start Meter/MeterData Search Tab -->
<div class="menudiv2" id="meterSearch">
    <ul id="hor_nav_sub">
        <%
            if (showMeterTab) {
        %>
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_NETWORK_LOCATION_GET)%>">Meter</a></li>
        <%
            }
        %>
        <%
            if (showMeterDataTab) {
        %>
        <li class="hor_nav_normal2_arrow"><a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_SEARCH_GET)%>">Meter Data</a></li>
        <%
            }
        %>
    </ul>
</div>

