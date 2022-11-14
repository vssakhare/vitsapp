/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;


import in.emp.home.correctionform.bean.CorrectionFormPrezData;
import in.emp.home.personalInfo.bean.PersonalinfoBean;
import in.emp.home.personalInfo.bean.PersonalinfoPrezData;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Prajakta
 */
public class imageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        response.setContentType("image/gif");
        response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
        OutputStream o = response.getOutputStream();
        byte[] empImg = null;
        ImageBean imageBean = new PersonalinfoBean();
        try {
            /* TODO output your page here. You may use following sample code. */
            if (request.getSession().getAttribute(ApplicationConstants.PERSONAL_INFO_SESSION_DATA) != null) {
                
                imageBean = (ImageBean) ((PersonalinfoPrezData) request.getSession().getAttribute(ApplicationConstants.PERSONAL_INFO_SESSION_DATA)).getPinfoBean();
                
            } else if (request.getSession().getAttribute(ApplicationConstants.CORRECTION_SESSION_DATA) != null) {
                imageBean = (ImageBean) ((CorrectionFormPrezData) request.getSession().getAttribute(ApplicationConstants.CORRECTION_SESSION_DATA)).getpInfoBean();
            }

            if ((imageBean.getEmpImg() != null) && !(imageBean.getEmpImg().equals("null")) && !(imageBean.getEmpImg().equals(""))) {
                empImg = imageBean.getEmpImg();
            }
            o.write(empImg);
        } catch (Exception ex) {
            
        } finally {
            o.flush();
            o.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
