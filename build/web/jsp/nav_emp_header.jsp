<%-- 
    Document   : nav_emp_header
    Created on : Apr 11, 2016, 12:31:39 PM
    Author     : Ithelpdesk
--%>

<%@page import="in.emp.common.ApplicationConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="adjust-nav"> 
        <div class="container-fluid">
            <div class="row">

                <div class="navbar-header col-sm-2">
                       <a class="" href="#"><img alt=""  src="images/Mahadiscom_Logo.png"></a>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    
                </div>    
                <div class="navbar-ehead col-sm-7" align="center">
                   
                   
              
                      <label  class="systemLabel" style="color: #C70039 ;padding:40px;font-size:20px">Vendor Invoice Tracking System     </label>    
                </div>
                
             
                    <div class="logout-espn  col-sm-3" >
            <div class="pull-right advancehide">
				
                <div class="dropdown welcome " style="padding:10px;font-size:12px">                
                    <b>  Welcome  </b>
                    <% if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) 
                    { %> 
                    <b>   <%=session.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION)%></b>
                        <a class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <p align="right"><b> <span class="glyphicon glyphicon-user userdet" style="padding-right:10px"></span><%=session.getAttribute(ApplicationConstants.USER_NAME_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.USER_NAME_SESSION)%>
                                    <span class="caret" ></span></b>    
                         </p>
                          </a>
                       
                       
                    
               
                    <% } else if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) { %> 
                    <b> <%=session.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION)%></b>
                       <a class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        
                  
                           <p><b>Designation: <%=session.getAttribute(ApplicationConstants.DESIGNATION_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.DESIGNATION_SESSION)%></b></p>
                           <p><b>Office Code: <%=session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION)%></b></p>
                           <p> <b>  <span class="glyphicon glyphicon-user userdet" style="padding-right:10px"></span><%=session.getAttribute(ApplicationConstants.USER_NAME_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.USER_NAME_SESSION)%><span class="caret"></span></b></p>
                          </a>
                           <% } %>         
     
               
                    
                      
					<ul class="dropdown-menu logindrop" aria-labelledby="dropdownMenu1">
                     
                        <li align="center"><a href="/legalInvoiceTracking/erp?uiActionName=postlogin">LOGOUT</a>
                        </li>
                    </ul>
                </div></div>
            </div>
                    
            </div>
                   
             
            </div>
                     
                  
        </div>
                    
    </div>
            
