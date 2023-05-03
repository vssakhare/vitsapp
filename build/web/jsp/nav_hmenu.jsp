<%@page import="java.util.Calendar"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.emp.security.bean.SecUserBean"%>
<%
    String selected = request.getParameter("selected");
    String classHome = "";
    String classLeaveApp = "";
    String classClaims = "";
    String classFinancial = "";
    String classCPF = "";
    String classAppraisal = "";
    String classTrainingExam = "";
    String classContact = "";
    long menu_Home = 0;
    long menu_LeaveApp = 0;
    long menu_Claims = 0;
    long menu_Financial = 0;
    long menu_CPF = 0;
    long menu_Appraisal = 0;
    long menu_TrainingExam = 0;
    long menu_Contact = 0;

    //String granularAcl = (String) session.getAttribute(ApplicationConstants.GRANULAR_ACL_SESSION);
    String granularAcl = "";//(String) session.getAttribute(ApplicationConstants.GRANULAR_ACL_SESSION);
    if (session.getAttribute(ApplicationConstants.GRANULAR_ACL_SESSION) != null) {
        granularAcl = (String) session.getAttribute(ApplicationConstants.GRANULAR_ACL_SESSION);
    } else {
        granularAcl = "";
    }

//    if (granularAcl.indexOf(ApplicationConstants.UIACTION_MENU_DCU) > -1) {
//        menu_dcu = 1;
//    }
    if (selected.equals("home_css")) {
                    classHome = "class=\"selected\"";
                } else if (selected.equals("leave_css")) {
                    classLeaveApp = "class=\"selected\"";
                } else if (selected.equals("claim_css")) {
                    classClaims = "class=\"selected\"";
                } else if (selected.equals("finance_css")) {
                    classFinancial = "class=\"selected\"";
                } else if (selected.equals("cpf_css")) {
                    classCPF = "class=\"selected\"";
                } else if (selected.equals("appraisal_css")) {
                    classAppraisal = "class=\"selected\"";
                } else if (selected.equals("training_css")) {
                    classTrainingExam = "class=\"selected\"";
                } else if (selected.equals("contact_css")) {
                    classContact = "class=\"selected\"";
                }
%>
<Script LANGUAGE="JavaScript" type="text/javascript">
<!--

    var winOpened = false;
    var a;



    function openWinPopup(popupurl, name, len, flag)
    {
        a = window.open(popupurl, name, len);
        winOpened = true;
    }


//-->
</Script>
<div id="nav_container">

    <!--Horizontal Main-Navigation Begins -->
    <div id="slidemenu" class="hor_nav_bar">
        <ul>
            <% if (menu_Home == 0)%>
            <li <%=classHome%>><a href="#" title="Home" onMouseOver="divMenu.show('dropmenu_Home', this, 0, 29)" onMouseOut="divMenu.hide('dropmenu_Home')"><span>Home</span></a></li>
         
            <% if (menu_LeaveApp == 0)%>
            <li <%=classLeaveApp%>><a href="#" title="Leave Applications" onMouseOver="divMenu.show('dropmenu_LeaveApp', this, 0, 29)" onMouseOut="divMenu.hide('dropmenu_LeaveApp')"><span>Leave Applications</span></a></li>

             <% if (menu_Claims == 0)%>
            <li <%=classClaims%>><a href="#" title="Claims" onMouseOver="divMenu.show('dropmenu_Claims', this, 0, 29)" onMouseOut="divMenu.hide('dropmenu_Claims')"><span>Claims</span></a></li>

             <% if (menu_Financial == 0)%>
            <li <%=classFinancial%>><a href="#" title="Financial Applications" onMouseOver="divMenu.show('dropmenu_Financial', this, 0, 29)" onMouseOut="divMenu.hide('dropmenu_Financial')"><span>Financial Applications</span></a></li>

             <% if (menu_CPF == 0)%>
            <li <%=classCPF%>><a href="#" title="CPF" onMouseOver="divMenu.show('dropmenu_CPF', this, 0, 29)" onMouseOut="divMenu.hide('dropmenu_CPF')"><span>CPF</span></a></li>

             <% if (menu_Appraisal == 0)%>
            <li <%=classAppraisal%>><a href="#" title="Employee Appraisal" onMouseOver="divMenu.show('dropmenu_Appraisal', this, 0, 29)" onMouseOut="divMenu.hide('dropmenu_Appraisal')"><span>Employee Appraisal</span></a></li>

             <% if (menu_TrainingExam == 0)%>
            <li <%=classTrainingExam%>><a href="#" title="Training & Exam Applications" onMouseOver="divMenu.show('dropmenu_TrainingExam', this, 0, 29)" onMouseOut="divMenu.hide('dropmenu_TrainingExam')"><span>Training & Exam Applications</span></a></li>

             <% if (menu_Contact == 0)%>
            <li <%=classContact%>><a href="#" title="Contact Us" onMouseOver="divMenu.show('dropmenu_Contact', this, 0, 29)" onMouseOut="divMenu.hide('dropmenu_Contact')"><span>Contact Us</span></a></li>

        </ul>
    </div>
    <!--Horizontal Main-Navigation Ends -->
</div>
    <div class="hor_nav_bar_tabsline">&nbsp;</div>

    <!--Horizontal Sub-Navigation Begins -->

    <!-- Home sub menu starts -->
    <div id="dropmenu_Home" class="sub_hor_nav_bar">
        <div>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>">Home</a>               
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_PERSONALINFO_GET)%>">Personal Information</a>
        </div>
        <span>&nbsp;</span><em>&nbsp;</em>
    </div>
    <!-- Home sub menu ends -->

     <!-- Leave Applications sub menu starts -->
    <div id="dropmenu_LeaveApp" class="sub_hor_nav_bar">
        <div>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_LEAVE_HIST_GET)%>">Leave Balances</a>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_LEAVE_HIST_GET)%>">Leave History</a>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_DCU_READING_GET_DETAILS)%>">Leave Application</a>
        </div>
        <span>&nbsp;</span><em>&nbsp;</em>
    </div>
    <!-- Leave Applications sub menu starts -->
    
     <!-- Claims sub menu starts -->
    <div id="dropmenu_Claims" class="sub_hor_nav_bar">
        <div>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_TACLAIM_GET)%>">TA/LTC Bills</a>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_XLS_FILE_GET)%>">Mobile Bills</a>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_DCU_READING_GET_DETAILS)%>">OT Bills</a>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_GENRATE_DCU_FILE)%>">Medical Bills</a>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_DCU_GET_STAT_REPORT)%>">Other Bills</a>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_CLAIM_APPROVAL_GET)%>">Claim Approval</a>
        </div>
        <span>&nbsp;</span><em>&nbsp;</em>
    </div>
    <!-- Claims sub menu starts -->

     <!-- Financial Applications sub menu starts -->
    <div id="dropmenu_Financial" class="sub_hor_nav_bar">
        <div>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_DCU_GET)%>">Annual Savings Submission</a>
        </div>
        <span>&nbsp;</span><em>&nbsp;</em>
    </div>
    <!-- Financial Applications sub menu starts -->

     <!-- CPF sub menu starts -->
    <div id="dropmenu_CPF" class="sub_hor_nav_bar">
        <div>
            <a href="http://cpf1.mahadiscom.in:8180/CpfWebProject/login">CPF Portal</a>
        </div>
        <span>&nbsp;</span><em>&nbsp;</em>
    </div>
    <!-- CPF sub menu starts -->

     <!-- Employee Appraisal sub menu starts -->
    <div id="dropmenu_Appraisal" class="sub_hor_nav_bar">
        <div>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_DCU_GET)%>">Employee Self Appraisal (S.E. And Above)</a>
        </div>
        <span>&nbsp;</span><em>&nbsp;</em>
    </div>
    <!-- Employee Appraisal sub menu starts -->

     <!-- Training & Exam Applications sub menu starts -->
    <div id="dropmenu_TrainingExam" class="sub_hor_nav_bar">
        <div>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_DCU_GET)%>">Online Application for Dept. Exam</a>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_DCU_GET)%>">Request for Training</a>
            <!-- prajakta -->
	    <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_TRG_HIST_GET)%>">Training and Exams History</a>
	    <!-- Prajakta -->
        </div>
        <span>&nbsp;</span><em>&nbsp;</em>
    </div>
    <!-- Training & Exam Applications sub menu starts -->

     <!-- Contact Us sub menu starts -->
    <div id="dropmenu_Contact" class="sub_hor_nav_bar">
        <div>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_DCU_GET)%>">Contact Details</a>
            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_DCU_GET)%>">Feedback Form</a>
        </div>
        <span>&nbsp;</span><em>&nbsp;</em>
    </div>
    <!-- Contact Us sub menu starts -->

    <!--Horizontal Sub-Navigation Ends -->

    <script type="text/javascript">
        //SYNTAX: tabdropdown.init("menu_id", [integer OR "auto"])
        // tabdropdown.init("slidemenu")
    </script>

    <div class="separator"></div>




