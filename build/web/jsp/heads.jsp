<%-- 
    Document   : heads
    Created on : Jun 14, 2013, 1:58:23 PM
    Author     : rgchoudhari
--%>

<html>
        <head>
                <title>Request Headers</title>
                <style type="text/css">


                </style>

        </head>
        <body>
                <table>
                        <tr>
                                <td align="center" colspan="2" style="border-bottom: 1px solid #800">
                                        <h3>Request Headers</h3>
                                </td>
                        </tr>
                        <tr>
                                <th nowrap>
			Header Name
                                </th>
                                <th>
			Value
                                </th>
                        </tr>
                        <tr>
                                <td nowrap>
			Remote User
                                </td>
                                <td>
                                        <%= request.getRemoteUser()%>
                                </td>
                        </tr>
                        <tr>
                                <td nowrap>
			User Principal
                                </td>
                                <td>
                                        <%
                                                        try {
                                        %>
                                        <%= request.getUserPrincipal()%>
                                        <%
                                                        } catch (Exception e) {
                                                                //e.printStackTrace();
                                                        }
                                        %>
                                </td>
                        </tr>
                        <%
                                        int cnt = 0;
                                        java.util.Enumeration en = request.getHeaderNames();
                                        while (en != null && en.hasMoreElements()) {
                                                String name = (String) en.nextElement();
                                                String value = request.getHeader(name);
                                                cnt++;
                        %>
                        <tr>
                                <td><%=name%></td>
                                <td><%=value%></td>
                        </tr>
                        <%
                                        }
                        %>
                        <tr>
                                <td colspan="2" align="left"><%=(cnt > 0) ? ("Total: " + cnt) : "No headers found!"%>
                        </tr>
                </table>
                <br/>
                <table>
                        <tr>
                                <td align="center" colspan="2" style="border-bottom: 1px solid #800">
                                        <h3>Session Parameters</h3>
                                </td>
                        </tr>
                        <tr>
                                <th nowrap>
			Parameter Name
                                </th>
                                <th>
			Value
                                </th>
                        </tr>
                        <%
                                        cnt = 0;
                                        java.util.Enumeration en1 = session.getAttributeNames();
                                        while (en1 != null && en1.hasMoreElements()) {
                                                String name = (String) en1.nextElement();
                                                String value = "";
                                                Object val = session.getValue(name);
                                                if (val != null) {
                                                        value = val.toString();
                                                }
                                                cnt++;
                        %>
                        <tr>
                                <td><%=name%></td>
                                <td><%=value%></td>
                        </tr>
                        <%
                                        }
                        %>
                        <tr>
                                <td colspan="2" align="left"><%=(cnt > 0) ? ("Total: " + cnt) : "No session Params found!"%>
                        </tr>
                </table>
                <%
                                response.setHeader("Cache-Control", "no-cache, must-revalidate, no-store, max-age=0");
                                response.setHeader("Pragma", "no-cache");
                                response.setDateHeader("Expires", 0);
                %>
        </body>
</html>
