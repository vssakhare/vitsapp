<%-- 
    Document   : erp_view_file
    Created on : 29 Mar, 2017, 11:59:39 AM
    Author     : Prajakta
--%>

<%@page import="com.jcraft.jsch.ChannelSftp"%>
<%@page import="com.jcraft.jsch.Channel"%>
<%@page import="java.util.Properties"%>
<%@page import="com.jcraft.jsch.Session"%>
<%@page import="com.jcraft.jsch.JSch"%>
<%@page import="in.emp.common.FileBean"%>
<%@page import="in.emp.common.ApplicationConstants"%>

<%@ page import = "java.io.*"%>
<%@page import="java.sql.Blob"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8"/>
        <title>Attachment</title>
        <%
                   try{
                       byte[] FileContent = null;
                    String FileType = null;
                    FileBean FileObj = null;
                     JSch jsch = new JSch();
                    Session sessionfile = null;
            sessionfile = jsch.getSession("vptserp", "ftp-vpts-erp.mahadiscom.in", 22);
           final Properties configuration = new Properties();
            configuration.put("StrictHostKeyChecking", "no");
            JSch.setConfig(configuration);
            sessionfile.setPassword("Erp#V321");
            sessionfile.connect();
             Channel channel = sessionfile.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
                
           
                   
                    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA) != null) {
                        FileObj = (FileBean) request.getSession().getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA);
                    } 
                  
                 
                    String filepath=FileObj.getPath();
                    String filename=FileObj.getFileName();
               
                 
                    ServletContext      context  = getServletConfig().getServletContext();
                    String              mimetype = context.getMimeType( filename );
                    FileType = FileObj.getFileType();
                            if (FileType.equalsIgnoreCase("JPG")|| FileType.equalsIgnoreCase("JPEG")) {
                                response.setContentType("image/gif");
                            } else if (FileType.equalsIgnoreCase("PDF")) {
                                response.setContentType("application/pdf");
                            } else if (FileType.equalsIgnoreCase("PNG")) {
                                response.setContentType("image/png");
                            }
                    response.setHeader( "Content-Disposition", "inline; filename=" + filename + "." + FileType );//inline open        
                 //response.setHeader( "Content-Disposition", "attachment; filename=" + filename + "." + FileType );//download
                      response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	              response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility    
	            
                            
                    InputStream stream = sftpChannel.get(filepath);
                    System.out.println("FilePath:"+filepath);
                  //  InputStream stream = sftpChannel.getInputStream();
                  DataInputStream in = new DataInputStream(stream);
                byte[] bbuf = new byte[1024];
                int length   = 0;
                OutputStream o=null;
                    while ((in != null) && ((length = in.read(bbuf)) != -1))
                    {
                          o = response.getOutputStream();
                         
                         o.write(bbuf,0,length);
                         
                    }
                    
                    in.close(); 
                         o.flush();
                         o.close();
                   
                   }catch (Exception ex) {
	               // log.error(ex.getMessage(),ex) ;
	                //ex.printStackTrace();
	            } 
        %>
</head>
<body>
 
</body>
</html>
