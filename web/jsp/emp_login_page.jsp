<%-- 
    Document   : emp_login_page
    Created on : 25 Apr, 2016, 4:30:08 PM
    Author     : pooja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>VendorPaymentTrackingSystem</title>
       
        <!-- BOOTSTRAP STYLES-->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONTAWESOME STYLES-->
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
           <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
      <link href="assets/css/custom.css" rel="stylesheet" />
         <!-- EMP STYLES-->
        <!-- <link href="css/emp.css" rel="stylesheet" />-->
         <link href="assets/css/Responsive.css" rel="stylesheet" />
          <link href="assets/css/homeSlider.css" rel="stylesheet" />
           <link href="assets/css/login.css" rel="stylesheet" />
            <link href="assets/css/style.css" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->
        
        <jsp:include page="nav_jscss.jsp" />

        <!-- JQUERY SCRIPTS -->
        
        <!-- BOOTSTRAP SCRIPTS -->
        <script src="assets/js/bootstrap.min.js"></script>
        <!-- CUSTOM SCRIPTS -->
        <script src="assets/js/custom.js"></script>
        <!-- jReject CSS -->
        <link href="css/jquery.reject.css" rel="stylesheet" />
        <!-- jReject SCRIPTS -->
        <script src="<%=ApplicationConstants.JS_PATH%>jquery.reject.js"></script>
        <script src="<%=ApplicationConstants.JS_PATH%>emp_login_page.js"></script>
      <%-- Set language based on user's choice --%>

    </head>
    <script type="text/javascript">
        bsc();
        $( document ).ready(function() {
var $content = $('#page-inner');
     $window=$(window).on('resize', function(){
       var height = $(this).height()*0.745;
       $content.height(height);
    }).trigger('resize'); //on page load
});
    </script>
    <body>
    
        <section id="Full-page" >
            <div id="wrapper" >
            <nav class="navbar navbar-inverse navbar-fixed-top">
      
            <div class="navbar-header">
              
                <a class="navbar-brand" href="#"><img alt=""  src="images/Mahadiscom_Logo.png"></a>
                <!-- <a href="javascript:void(0)" class="LoginLink LoginLinkMobile"><span class="glyphicon glyphicon-lock"></span> Login</a> -->
            </div>
            <div class="" id="myNavbar">
             <ul id="SmallNav" class="nav navbar-nav navbar-right sidenav">
                    <li> <a href ="#" onclick="loadHome()"><span class="glyphicon glyphicon-exclamation-sig"></span>Home</a></li>
                    <li> <a href ="#" onclick="loadDisclaimer()"><span class="glyphicon glyphicon-exclamation-sign"></span>Disclaimer</a></li>
                     <li><a href ="#" onclick="loadContactUs()"><span class="glyphicon glyphicon-earphone"></span>Contact Us</a></li>
                     <li><a href="#"><span class="glyphicon glyphicon-question-sign"></span>Help</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span>FAQ</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-cog"></span>Terms & Condition</a></li>                     
                </ul>
                 <c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session" />
</c:if>
                   <div class="clearfix"></div> 
            <form class="navbar-form pull-center navbarright" style="align-items: center">
                   <label class="mahadiscom">Maharashtra State Electricity Distribution Co. Ltd</label>
                    <div class="input-group">
                        <!-- <input type="search" class="form-control" placeholder="Search"> -->
                       <!--  <span class="input-group-btn">
                            <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                        </span> -->
                    </div>  
                      <div class="headerWidget" >
  <c:choose>
    <c:when test="${empty sessionScope['javax.servlet.jsp.jstl.fmt.locale.session']}">
      <c:choose>
        <c:when test="${pageContext.request.locale.language ne 'mr'}"><div style="font-weight:bold;">English</div></c:when>
        <c:otherwise>
          <c:url var="url" value="chooseLanguage">
            <c:param name="language" value="en"/>
          </c:url>
          <div class="bubble link"><a href="${url}" style="color: #000">English</a></div>
        </c:otherwise>
      </c:choose><div> |</div>
      <c:choose>
        <c:when test="${pageContext.request.locale.language eq 'mr'}"><div style="font-weight:bold;">मराठी</div></c:when>
        <c:otherwise>
          <c:url var="url" value="chooseLanguage">
            <c:param name="language" value="mr"/>
          </c:url>
          <div class="bubble link"><a href="${url}" style="color: #000">मराठी</a></div>
        </c:otherwise>
      </c:choose>
    </c:when>
    <c:otherwise>
      <c:choose>
        <c:when test="${sessionScope['javax.servlet.jsp.jstl.fmt.locale.session'] ne 'mr'}"><div style="font-weight:bold;">English</div></c:when>
        <c:otherwise>
          <c:url var="url" value="chooseLanguage">
            <c:param name="language" value="en"/>
          </c:url>
          <div class="bubble link"><a href="${url}" style="color: #000">English</a></div>
        </c:otherwise>
      </c:choose> <div>|</div>
      <c:choose>
        <c:when test="${sessionScope['javax.servlet.jsp.jstl.fmt.locale.session'] eq 'mr'}"><div style="font-weight:bold;">मराठी</div></c:when>
        <c:otherwise>
          <c:url var="url" value="chooseLanguage">
            <c:param name="language" value="mr"/>
          </c:url>
          <div class="bubble link"><a href="${url}" style="color: #000">मराठी</a></div>
        </c:otherwise>
      </c:choose>
    </c:otherwise>
  </c:choose>
</div>
                </form>
              <div class="clearfix"></div> 
              <div id="slidemenu" data-hover="dropdown" data-animations="fadeIn">
              <div id="mainMenu" class="nav navbar-nav  main-nav activenav"> 
               <label class="systemLabel " style="color: white">Vendor Invoice Tracking System     </label>    
              </div>
                  
             
            </div>
      </div>
    </nav>
            
            <section class="homeSection"  >
   <div class="container-fluid nopadding" height="100%">
        <div id="first-slider">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- /. NAV TOP  -->
            <!-- /. NAV SIDE  -->
               <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                        <!--<li data-target="#carousel-example-generic" data-slide-to="3"></li>
                         <li data-target="#carousel-example-generic" data-slide-to="4"></li> -->
                    </ol>
            
            <div class="carousel-inner" role="listbox">
                        <!-- Item 1 -->
                        <div class="item active slide1">
                            <div class="row">

                            </div>
                        </div>
                        <div class="item slide2">
                            <div class="row">

                            </div>
                        </div>
                        <div class="item slide3">
                            <div class="row">

                            </div>
                        </div>

                    </div>
            
              <div class="container-fluid NoPadding overpos">
                                    <div  id="content" class="col-md-8 text-left nopadding" height="100%">
                                    </div>
            <div class="col-sm-4" >
                <div >
 					
                    <div class="card padding20" height="100%">
                        <div class="text-center">
                            <h3 class="loginheading"><b>Login</b></h3></div>
                              <% //System.out.println("-----Login Message : "+ session.getAttribute(ApplicationConstants.USER_LOGIN_MSG_SESSION));
                        String msg=null;
                        if(!ApplicationUtils.isBlank(session.getAttribute(ApplicationConstants.USER_LOGIN_MSG_SESSION))){
                            msg = (String) session.getAttribute(ApplicationConstants.USER_LOGIN_MSG_SESSION);
                        }
                        
                     %>
        
                    <input type="hidden" id="<%=ApplicationConstants.UIACTION_NAME%>" name="<%=ApplicationConstants.UIACTION_NAME%>" value="getlogin" />

                        <form id="ajax-login-form" action="login" method="post" role="form" autocomplete="off">
                             
                                        
                               <table align="center" border="0" cellspacing="0" cellpadding="1" id="table_content" class="login_screen" style="width: 100%"> <!-- Start of   table_content table -->
                            <tbody style="border: 1px solid #bce8f1;">  <% if(!ApplicationUtils.isBlank(msg)) { %>
                          <tr>
                              <td colspan="2">
                                  <input style="text-align: center" class="form-control-err-msg" name="txtMsg" id="txtMsg" value="<%=msg%>" type="text" readonly="true"  />
                              </td>
                        </tr>
                       <% } %>
                        <tr>
                             
                            <td class="Label_login"><fmt:message key='User Name'/></td>
                            
                            <td class="" style="padding-right: 20px" >  <div class=""><input style="text-align: center ;float: left; width: 100%;margin: 1rem 0 0rem; position: relative;"    name="txtUID" id="txtUID" value="" type="text"  /> </div></td>
                            
                        </tr>
                           
                        <tr>
                            
                            <td class="Label_login"><fmt:message key='Password'/></td>
                           
                            <td class="" style="padding-right: 20px">  <div class=""><input style="text-align: center ;float: left; width: 100%;margin: 1rem 0 0rem; position: relative;"  class="" name="txtP"  id="txtP" value="" type="password"  /></div></td>
                        
                        </tr>
                            
                            <tr> 
                                
                                <td class="Label_login"><fmt:message key='OTP'/></td>
                              <td class="" style="padding-right: 20px"><div class=""><input style="text-align: center ;float: left; width: 100%;margin: 1rem 0 0rem; position: relative;"  name="Otp"  id="Otp" type="text" required></input>
                            </td>
                              
                                
                        </tr>
                               <tr> 
                                
                                <td class="Label_login"></td>
                              <td style="padding-left: 50px" class=""><div class="">
                              <button   class="form-control btn btn-default" id="loginOtp" style="width:70%;height:34px;float:center;margin: 1rem 0 0rem;" onclick="generateOTP()"><fmt:message key='Generate OTP'/></button>  </div>
                              </td>
                              
                                
                        </tr>
                              <tr> 
                                
                                  <td class="Label_login"><label></label></td>
                              <td  class="">
                              </td>
                              
                                
                        </tr>
                         <tr>
                             <td class="Label_login"    ><fmt:message key='Vendor'/><input type="radio" class="form-control" name="rad_UserOpt" id="rad_VendorOpt" value="Vendor" checked="checked"/>
                               </td>
                               <td class="Label_login"><fmt:message key='Employee'/><input type="radio" class="form-control" name="rad_UserOpt" id="rad_EmployeeOpt" value="Emp" checked="checked"/>
                               </td>
                        </tr>
                               </tbody>
                    </table>   
                            <table  align="center" border="0" cellspacing="0"  class="login_screen" style="width: 80%">    
                                <tbody width="100%">
                        <tr width="100%">
                           
                            <td  align="center" class="Label_login" style="padding-top: 15px"><input type="button" value=<fmt:message key='Login'/> name="ButtonLogin" id="ButtonLogin" class="btn btn-default" onclick="login()"/></td>
                            
                        </tr>
                            
                               <tr>
                               <td class="Label_login"><a onclick="resetPassword();" title="Click here to Reset your password.">Reset Password?</a></td></tr>
                              </tbody>  </table>    <!-- End of  table_content table  -->
                  
                        </form>
                    </div>
              
                         
            </div>  </div>
                  </div>
            </div>
                          </div>  </div>
            </section>
        </div>                         <!-- /.  wrapper  -->
                    <div class="clearfix"></div>
            <div class="container-fluid">      
                        <h5 align="center"><p><b style="color : red;"><fmt:message key='Note'/>:</b><fmt:message key='Use SAP vendor code as User Name and welcome as default password for vendor login'/> and RAPDRP credentials for Employee login.</p></h5>                       
                        <h5 align="center"><p><fmt:message key='For any issues regarding Vendor Invoice Tracking System, please send an email to'/> <b style="color : red;">vits_support@mahadiscom.in </b>.</p></h5>
<h5 align="center">
    <p>
        <fmt:message key='To download Vendor Invoice Tracking System Manuals For Vendor'/>
       
            <a href="/VendorBillTracking/pdfFiles/Vendorreportmanual.pdf"><b style="color : red;"><fmt:message key='Click here'/> </b></a>
       
            
    </p>
</h5>
            
            </div>
        <%@include file="nav_emp_footer.jsp" %>

        <!-- SCRIPTS -AT THE BOTTOM TO REDUCE THE LOAD TIME-->
                        </section>
    </body>
</html>
 <script>
      function loadDisclaimer(){
          document.getElementById("content").innerHTML='<object type="text/html" data="disclaimer.html" height="410px" width="100%"></object>';
  }
  
   function loadHome(){
       window.location.reload();
  }
  
  function loadContactUs(){
          document.getElementById("content").innerHTML='<object type="text/html" data="contactUs2.html" height="410px" width="100%"></object>';
  }
  
</script>