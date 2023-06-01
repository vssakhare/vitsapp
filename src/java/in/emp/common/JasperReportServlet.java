/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;

import in.emp.util.ApplicationUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class JasperReportServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(JasperReportServlet.class);

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
        response.setContentType("text/html;charset=UTF-8");
        long p_mid = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String reportType;
        ServletOutputStream stream = null;
        String Report_Path = "";
        Map parameters = new HashMap();
        Connection conn = null;
        String fileType = "";
        String folderName = "";
        String reportName = "";
        File reportFile = null;
        byte[] bytes = null;
        try {
            logger.log(Level.INFO, " JasperReportServlet :: processRequest() :: method called");

            conn = ApplicationUtils.getConnection();
            Report_Path = this.getServletContext().getRealPath("/reports/");
            Report_Path += "/";

            fileType = request.getParameter("reportType");
            if (fileType.equalsIgnoreCase("XLSX")) {
                reportType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            } else {
                reportType = "applications/vnd.pdf";
            }

            if (!ApplicationUtils.isBlank(request.getParameter("reportName"))) {
                reportName = request.getParameter("reportName");
            }

            if (reportName.equals(ApplicationConstants.REPORT_ERS_APPLICATION_PRINT)) {
                parameters = getERSApplicationPrint(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_FINANCIAL_PAYSLIP)) {
                parameters = getPayslip(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_FINANCIAL_ITCALCSHEET)) {
                parameters = getITCalcSheet(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_FINANCIAL_BOE)) {
                parameters = getBOEReport(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_SENIORITY_LIST)) {
                parameters = getSeniorityList(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_MEDICLAIM_COV_LETTER)) {
                parameters = printMediclaimCovLetter(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_MSEDCL_EMP)) {
                parameters = MSEDCLEmpReport(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_MSEDCL_VENDOR)) {
                parameters = MSEDCLVendorReport(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_VENDOR_DETAIL)) {//2
                parameters = Vendor_detail_report(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_FOR_VENDOR)) {//3
                parameters = MSEDCL_Vendor_detail_Report(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_EMPLOYEE_DETAIL)) {
                parameters = Employee_detail_reports(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_VENDOR_STATISTICS)) {//1
                parameters = Vendor_Statistics_Reports(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_EMPLOYEE_STATISTICS)) {
                parameters = Employee_Statistics_Reports(request);
            } else if (reportName.equals("dtlsofapplid")) {
                parameters.put("reportName", reportName);
                parameters.put("reportFileName", "dtlsofapplid");
                parameters.put("applid", request.getParameter("applid"));
            } else if (reportName.equals("dtlsofutrno")) {
                parameters.put("reportName", reportName);
                parameters.put("reportFileName", "dtlsofutrno");
                parameters.put("utrno", request.getParameter("utrno"));
            } else if (reportName.equals(ApplicationConstants.REPORT_EMPLOYEE_LEGAL_SUBMITTED)) {
                parameters = Employee_Legal_Submitted_Reports(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_EMPLOYEE_LEGAL_RETURNED)) {
                parameters = Employee_Legal_Returned_Reports(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_VENDOR_LEGAL_SUBMITTED)) {
                parameters = Vendor_Legal_Submitted_Reports(request);
            } else if (reportName.equals(ApplicationConstants.REPORT_VENDOR_LEGAL_RETURNED)) {
                parameters = Vendor_Legal_Returned_Reports(request);
            } 

            if (!ApplicationUtils.isBlank(parameters.get("folderName"))) {
                folderName = (String) parameters.get("folderName");
                Report_Path += folderName + "/";
            }
            System.out.println("reportFileName:" + parameters.get("reportFileName"));


            reportFile = new File(Report_Path + "/" + (String) parameters.get("reportFileName") + ".jasper");
            reportName = (String) parameters.get("reportName");
            System.out.println("reportName:" + reportName);

            parameters.put("SUBREPORT_DIR", Report_Path);
            System.out.println("Path:" + reportFile.getAbsolutePath());
            JasperPrint jprint = JasperFillManager.fillReport(reportFile.getAbsolutePath(), parameters, conn);
            
            System.out.println("fileType : "+ fileType);
            //System.out.println(parameters.toString());


            if (fileType.equalsIgnoreCase("PDF")) {
                bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
            } else if(fileType.equalsIgnoreCase("XLSX")){
               JRXlsxExporter exporter = new JRXlsxExporter();
exporter.setExporterInput(new SimpleExporterInput(jprint));

exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
configuration.setDetectCellType(true);//Set configuration as you like it!!
configuration.setCollapseRowSpan(false);
configuration.setWhitePageBackground(false);
exporter.setConfiguration(configuration);
exporter.exportReport();
 bytes = baos.toByteArray();
                /*JRXlsxExporter exporter = new JRXlsxExporter();

                exporter.setExporterInput(new SimpleExporterInput(jprint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
                
                exporter.exportReport();
                bytes = baos.toByteArray();*/
                

            }

            //-- Setting response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setHeader("Content-disposition", "attachment; filename=" + reportName + "." + fileType);

            //-- Setting the content type
            response.setContentType(reportType);

            //-- The contentlength is needed for MSIE
            response.setContentLength(bytes.length);

            //-- Write ByteArrayOutputStream to the ServletOutputStream
            stream = response.getOutputStream();
            stream.write(bytes);
            stream.flush();
        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: processRequest() :: Exception :: " + ex);
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception ignored) {
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                    stream = null;
                } catch (Exception ignored) {
                }
            }
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

    private Map getERSApplicationPrint(HttpServletRequest request) {
        Map parameters = new HashMap();
        String fName2 = "";
        String reportName = "";
        String AppId = "";
        String reportFileName = "EmpERSApplFormPrint";
        String EmpNumber = "";
        String folderName = "ERSAppPrint";
        try {
            logger.log(Level.INFO, " JasperReportServlet :: getERSApplicationPrint() :: method called");

            reportName = request.getParameter("reportName");

            if (!ApplicationUtils.isBlank(request.getParameter("AppId"))) {
                AppId = request.getParameter("AppId");
            }
            if (!ApplicationUtils.isBlank(request.getParameter("EmpNumber"))) {
                EmpNumber = request.getParameter("EmpNumber");
            }

            fName2 = "_" + AppId + "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);
            parameters.put("AppId", AppId);
            parameters.put("EmpNumber", EmpNumber);
            parameters.put("folderName", folderName);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: getERSApplicationPrint() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

    private Map getPayslip(HttpServletRequest request) {
        Map parameters = new HashMap();
        String fName2 = "";
        String reportName = "";
        String yyyymm = "";
        String reportFileName = ApplicationConstants.REPORT_FINANCIAL_PAYSLIP;
        String emp_no = "";
        String folderName = ApplicationConstants.REPORT_FINANCIAL_PAYSLIP;
        String imagePath = "";
        try {
            logger.log(Level.INFO, " JasperReportServlet :: getPayslip() :: method called");

            reportName = request.getParameter("reportName");

            if (!ApplicationUtils.isBlank(request.getParameter("txtYYYYMM"))) {
                yyyymm = request.getParameter("txtYYYYMM");
            }
            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                emp_no = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
            }
            imagePath = this.getServletContext().getRealPath("/images/");
            imagePath += "/";

            fName2 = "_" + yyyymm + "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);
            parameters.put("yyyymm", yyyymm);
            parameters.put("emp_no", emp_no);
            parameters.put("folderName", folderName);
            parameters.put("IMAGE_DIR", imagePath);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: getPayslip() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

    private Map getITCalcSheet(HttpServletRequest request) {
        Map parameters = new HashMap();
        String fName2 = "";
        String reportName = "";
        String yyyymm = "";
        String reportFileName = ApplicationConstants.REPORT_FINANCIAL_ITCALCSHEET;
        String emp_no = "";
        String folderName = ApplicationConstants.REPORT_FINANCIAL_ITCALCSHEET;
        try {
            logger.log(Level.INFO, " JasperReportServlet :: getITCalcSheet() :: method called");

            reportName = request.getParameter("reportName");

            if (!ApplicationUtils.isBlank(request.getParameter("txtYYYYMM"))) {
                yyyymm = request.getParameter("txtYYYYMM");
            }
            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                emp_no = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
            }

            fName2 = "_" + yyyymm + "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);
            parameters.put("yyyymm", yyyymm);
            parameters.put("emp_no", emp_no);
            parameters.put("folderName", folderName);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: getITCalcSheet() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

    private Map getBOEReport(HttpServletRequest request) {
        Map parameters = new HashMap();
        String fName2 = "";
        String reportName = "";
        String yyyymm = "";
        String reportFileName = ApplicationConstants.REPORT_FINANCIAL_BOE;
        String emp_no = "";
        String folderName = ApplicationConstants.REPORT_FINANCIAL_BOE;
        try {
            logger.log(Level.INFO, " JasperReportServlet :: getBOEReport() :: method called");

            reportName = request.getParameter("reportName");

            if (!ApplicationUtils.isBlank(request.getParameter("txtYYYYMM"))) {
                yyyymm = request.getParameter("txtYYYYMM");
            }
            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                emp_no = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
            }

            fName2 = "_" + yyyymm + "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);
            parameters.put("yyyymm", yyyymm);
            parameters.put("emp_no", emp_no);
            parameters.put("folderName", folderName);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: getBOEReport() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

    private Map getSeniorityList(HttpServletRequest request) {
        Map parameters = new HashMap();
        String fName2 = "";
        String reportName = "";
        String reportFileName = ApplicationConstants.REPORT_SENIORITY_LIST;
        String designation = "";
        String folderName = ApplicationConstants.REPORT_SENIORITY_LIST;
        try {
            logger.log(Level.INFO, " JasperReportServlet :: getSeniorityList() :: method called");

            reportName = request.getParameter("reportName");

            if (!ApplicationUtils.isBlank(request.getParameter("txtDesig"))) {
                designation = request.getParameter("txtDesig");
            }

            fName2 = "_" + designation + "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);
            parameters.put("designation", designation);
            parameters.put("folderName", folderName);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: getSeniorityList() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

    private Map printMediclaimCovLetter(HttpServletRequest request) {
        Map parameters = new HashMap();
        String fName2 = "";
        String reportName = "";
        String AppId = "";
        String reportFileName = ApplicationConstants.REPORT_MEDICLAIM_COV_LETTER_FILENAME;
        String EmpNumber = "";
        String folderName = "MediclaimReports";
        try {
            logger.log(Level.INFO, " JasperReportServlet :: printMediclaimCovLetter() :: method called");

            reportName = request.getParameter("reportName");

            if (!ApplicationUtils.isBlank(request.getParameter("AppId"))) {
                AppId = request.getParameter("AppId");
            }
            if (!ApplicationUtils.isBlank(request.getParameter("EmpNumber"))) {
                EmpNumber = request.getParameter("EmpNumber");
            }

            fName2 = "_" + AppId + "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);
            parameters.put("appl_id", AppId);
            parameters.put("cpf_no", EmpNumber);
            parameters.put("folderName", folderName);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: printMediclaimCovLetter() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

    private Map MSEDCLEmpReport(HttpServletRequest request) {
        Map parameters = new HashMap();

        String reportName = "";
        String fName2 = "";
        String reportFileName = ApplicationConstants.REPORT_MSEDCL_EMP;
        String folderName = ApplicationConstants.REPORT_MSEDCL_EMP;
        String imagePath = "";
        String officeCode = "";
        String txtFrmDate = "";
        String txtToDate = "";
        String txtVendorNumber = "";
        String txtPONumber = "";

        try {
            logger.log(Level.INFO, " JasperReportServlet :: MSEDCLEmpReport() :: method called");

            reportName = request.getParameter("reportName");



            if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                txtFrmDate = (String) request.getParameter("txtFrmDt");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                txtToDate = (String) request.getParameter("txtToDt");
            }

            if (!ApplicationUtils.isBlank((request.getParameter("txtVendorNumber")))) {
                txtVendorNumber = (String) request.getParameter("txtVendorNumber");
                if (txtVendorNumber.equalsIgnoreCase("ALL")) {
                    txtVendorNumber = "%";
                }
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
                txtPONumber = (String) request.getParameter("txtPONumber");
                if (txtPONumber.equalsIgnoreCase("ALL")) {
                    txtPONumber = "%";
                }
            }

            if (!ApplicationUtils.isBlank((request.getParameter("txtLocation")))) {
                officeCode = (String) request.getParameter("txtLocation");
                if (officeCode.equalsIgnoreCase("ALL")) {
                    officeCode = "%";
                }

            }

//            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
//                officeCode = (String) request.getSession().getAttribute(ApplicationConstants.OFFICE_CODE_SESSION);
//            }
            imagePath = this.getServletContext().getRealPath("/images/");
            imagePath += "/";
//            
            fName2 = "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);
            parameters.put("FROM_DATE", txtFrmDate);
            parameters.put("TO_DATE", txtToDate);
            parameters.put("OFFICE_CODE", officeCode);
            parameters.put("VENDOR_NUMBER", txtVendorNumber);
            parameters.put("PO_NUMBER", txtPONumber);

            parameters.put("folderName", folderName);
            parameters.put("IMAGE_DIR", imagePath);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: MSEDCLEmpReport() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

    private Map MSEDCLVendorReport(HttpServletRequest request) {
        Map parameters = new HashMap();
//       
//        String reportName = "";
//        String fName2 = "";
//        String reportFileName = ApplicationConstants.REPORT_MSEDCL_VENDOR;
//        String emp_no = "";
//        String folderName = ApplicationConstants.REPORT_MSEDCL_VENDOR;
//        String imagePath = "";
//        String txtFrmDate = "";
//        String txtToDate = "";
        try {
            logger.log(Level.INFO, " JasperReportServlet :: MSEDCLVendorReport() :: method called");

//            reportName = request.getParameter("reportName");
//            
//            
//            
//             if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
//                txtFrmDate = (String) request.getParameter("txtFrmDt");
//            }
//            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
//                 txtToDate=  (String) request.getParameter("txtToDt");
//            }
//            
//            
//            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
//                emp_no = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
//            }
//            imagePath = this.getServletContext().getRealPath("/images/");
//            imagePath += "/";
////            
//            fName2 =  "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
//            reportName = reportName + "_" + fName2;
//
//            parameters.put("reportName", reportName);
//            parameters.put("reportFileName", reportFileName);
//            parameters.put("fromDate", txtFrmDate);
//            parameters.put("toDate", txtToDate);
//            parameters.put("vendorNumber", Integer.parseInt(emp_no));
//            parameters.put("folderName",folderName);
//            parameters.put("IMAGE_DIR",imagePath);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: MSEDCLVendorReport() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

    private Map Vendor_detail_report(HttpServletRequest request) {
        Map parameters = new HashMap();

        String reportName = "";
        String fName2 = "";
        String reportFileName = ApplicationConstants.REPORT_VENDOR_DETAIL;
        String emp_no = "";
        String folderName = ApplicationConstants.REPORT_MSEDCL_VENDOR;
        String imagePath = "";
        String txtFrmDate = "";
        String txtToDate = "";
        String txtLoc = "";
        String txtPONumber ="";
        try {
            logger.log(Level.INFO, " JasperReportServlet :: Vendor_detail_report() :: method called");

            reportName = request.getParameter("reportName");



            if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                txtFrmDate = (String) request.getParameter("txtFrmDt");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                txtToDate = (String) request.getParameter("txtToDt");
            }

            if (!ApplicationUtils.isBlank((request.getParameter("txtLoc")))) {
                txtLoc = (String) request.getParameter("txtLoc");
            }
if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
                txtPONumber = (String) request.getParameter("txtPONumber");
            }
            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                emp_no = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
            }
            imagePath = this.getServletContext().getRealPath("/images/");
            imagePath += "/";
//            
            fName2 = "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);
            parameters.put("FROM_DATE", txtFrmDate);
            parameters.put("TO_DATE", txtToDate);
            parameters.put("PLANT", txtLoc);
            parameters.put("PO_NUMBER", txtPONumber);
            parameters.put("VENDOR_NUMBER", emp_no);
            parameters.put("folderName", folderName);
            parameters.put("IMAGE_DIR", imagePath);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: Vendor_detail_report() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

    private Map MSEDCL_Vendor_detail_Report(HttpServletRequest request) {
        Map parameters = new HashMap();

        String reportName = "";
        String fName2 = "";
        String reportFileName = ApplicationConstants.REPORT_FOR_VENDOR;
        String emp_no = "";
        String folderName = ApplicationConstants.REPORT_MSEDCL_VENDOR;
        String imagePath = "";
        String txtFrmDate = "";
        String txtToDate = "";
        String txtLoc = "";
         String txtPONumber ="";
        try {
            logger.log(Level.INFO, " JasperReportServlet :: MSEDCL_Vendor_detail_Report() :: method called");

            reportName = request.getParameter("reportName");


            if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                txtFrmDate = (String) request.getParameter("txtFrmDt");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                txtToDate = (String) request.getParameter("txtToDt");
            }

            if (!ApplicationUtils.isBlank((request.getParameter("txtLoc")))) {
                txtLoc = (String) request.getParameter("txtLoc");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
                txtPONumber = (String) request.getParameter("txtPONumber");
            }
            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                emp_no = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
            }
            imagePath = this.getServletContext().getRealPath("/images/");
            imagePath += "/";
//            
            fName2 = "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);
            parameters.put("FROM_DATE", txtFrmDate);
            parameters.put("TO_DATE", txtToDate);
            parameters.put("PLANT", txtLoc);
            parameters.put("PO_NUMBER", txtPONumber);
            parameters.put("VENDOR_NUMBER", emp_no);
            parameters.put("folderName", folderName);
            parameters.put("IMAGE_DIR", imagePath);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: MSEDCL_Vendor_detail_Report() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

    private Map Employee_detail_reports(HttpServletRequest request) {
        Map parameters = new HashMap();

        String reportName = "";
        String fName2 = "";
        String reportFileName = ApplicationConstants.REPORT_EMPLOYEE_DETAIL;
        String folderName = ApplicationConstants.REPORT_MSEDCL_EMP;
        String imagePath = "";

        String officeCode = "";
        String txtFrmDate = "";
        String txtToDate = "";
        String txtVendorNumber = "";
        String txtPONumber = "";


        try {
            logger.log(Level.INFO, " JasperReportServlet :: Employee_detail_reports() :: method called");

            reportName = request.getParameter("reportName");


            if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                txtFrmDate = (String) request.getParameter("txtFrmDt");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                txtToDate = (String) request.getParameter("txtToDt");
            }

            if (!ApplicationUtils.isBlank((request.getParameter("txtVendorNumber")))) {
                txtVendorNumber = (String) request.getParameter("txtVendorNumber");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
                txtPONumber = (String) request.getParameter("txtPONumber");
            }

            if (!ApplicationUtils.isBlank((request.getParameter("txtLocation")))) {
                officeCode = (String) request.getParameter("txtLocation");
            }


            imagePath = this.getServletContext().getRealPath("/images/");
            imagePath += "/";
//            
            fName2 = "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);

            parameters.put("FROM_DATE", txtFrmDate);
            parameters.put("TO_DATE", txtToDate);
            parameters.put("OFFICE_CODE", officeCode);
            parameters.put("VENDOR_NUMBER", txtVendorNumber);
            parameters.put("PO_NUMBER", txtPONumber);

            parameters.put("folderName", folderName);
            parameters.put("IMAGE_DIR", imagePath);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: Employee_detail_reports() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

    private Map Vendor_Statistics_Reports(HttpServletRequest request) {
        Map parameters = new HashMap();

        String reportName = "";
        String fName2 = "";
        String reportFileName = ApplicationConstants.REPORT_VENDOR_STATISTICS;
        String emp_no = "";
        String folderName = ApplicationConstants.REPORT_MSEDCL_VENDOR;
        String imagePath = "";
        String txtFrmDate = "";
        String txtToDate = "";
        String txtLoc = "";
        String txtPONumber ="";
        try {
            logger.log(Level.INFO, " JasperReportServlet :: Vendor_Statistics_Reports() :: method called");

            reportName = request.getParameter("reportName");



            if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                txtFrmDate = (String) request.getParameter("txtFrmDt");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                txtToDate = (String) request.getParameter("txtToDt");
            }

            if (!ApplicationUtils.isBlank((request.getParameter("txtLoc")))) {
                txtLoc = (String) request.getParameter("txtLoc");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
                txtPONumber = (String) request.getParameter("txtPONumber");
            }
            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                emp_no = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
            }
            imagePath = this.getServletContext().getRealPath("/images/");
            imagePath += "/";
//            
            fName2 = "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);
            parameters.put("PLANT", txtLoc);
            parameters.put("PO_NUMBER", txtPONumber);
            parameters.put("FROM_DATE", txtFrmDate);
            parameters.put("TO_DATE", txtToDate);
            parameters.put("VENDOR_NUMBER", emp_no);
            parameters.put("folderName", folderName);
            parameters.put("IMAGE_DIR", imagePath);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " JasperReportServlet :: Vendor_Statistics_Reports() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

    private Map Employee_Statistics_Reports(HttpServletRequest request) {
        Map parameters = new HashMap();

        String reportName = "";
        String fName2 = "";
        String reportFileName = ApplicationConstants.REPORT_EMPLOYEE_STATISTICS;
        String folderName = ApplicationConstants.REPORT_MSEDCL_EMP;
        String imagePath = "";

        String officeCode = "";
        String txtFrmDate = "";
        String txtToDate = "";
        String txtVendorNumber = "";
        String txtPONumber = "";

        try {
            logger.log(Level.INFO, " JasperReportServlet :: Employee_detail_reports() :: method called");

            reportName = request.getParameter("reportName");



            if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                txtFrmDate = (String) request.getParameter("txtFrmDt");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                
                txtToDate = (String) request.getParameter("txtToDt");
                
            }

            if (!ApplicationUtils.isBlank((request.getParameter("txtVendorNumber")))) {
                txtVendorNumber = (String) request.getParameter("txtVendorNumber");
                if(txtVendorNumber.equals("ALL")){
                txtVendorNumber="%";
            }
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
                txtPONumber = (String) request.getParameter("txtPONumber");
                if(txtPONumber.equals("ALL")){
                    txtPONumber="%";
                }
            }

            if (!ApplicationUtils.isBlank((request.getParameter("txtLocation")))) {
                officeCode = (String) request.getParameter("txtLocation");
                if(officeCode.equals("ALL")){
                    officeCode="%";
                }
            }


//            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
//                officeCode = (String) request.getSession().getAttribute(ApplicationConstants.OFFICE_CODE_SESSION);
//            }
            imagePath = this.getServletContext().getRealPath("/images/");
            imagePath += "/";
            
            fName2 = "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);

            parameters.put("FROM_DATE", txtFrmDate);
            parameters.put("TO_DATE", txtToDate);
            parameters.put("OFFICE_CODE", officeCode);
            parameters.put("VENDOR_NUMBER", txtVendorNumber);
            parameters.put("PO_NUMBER", txtPONumber);

            parameters.put("folderName", folderName);
          parameters.put("IMAGE_DIR", imagePath);

        } catch (Exception ex) {
            //ex.printStackTrace();
            logger.log(Level.ERROR, " JasperReportServlet :: Employee_detail_reports() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }
     private Map Employee_Legal_Returned_Reports(HttpServletRequest request) {
       Map parameters = new HashMap();

        String reportName = "";
        String fName2 = "";
        String reportFileName = ApplicationConstants.REPORT_EMPLOYEE_LEGAL_RETURNED;
        String folderName = ApplicationConstants.REPORT_MSEDCL_LEGAL_EMP;
        String imagePath = "";

        String officeCode = "";
        String txtFrmDate = "";
        String txtToDate = "";
        String txtVendorNumber = "";
        

        try {
            logger.log(Level.INFO, " JasperReportServlet :: Employee_Legal_Submitted_Reports() :: method called");

           
            reportName = request.getParameter("reportName");



            if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                txtFrmDate = (String) request.getParameter("txtFrmDt");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                
                txtToDate = (String) request.getParameter("txtToDt");
                
            }

          
            if (!ApplicationUtils.isBlank((request.getParameter("txtVendorNumber")))) {
                txtVendorNumber = (String) request.getParameter("txtVendorNumber");
                if(txtVendorNumber.equals("ALL")){
                txtVendorNumber="%";
            }
            }
            
          

            if (!ApplicationUtils.isBlank((request.getParameter("txtLocation")))) {
                officeCode = (String) request.getParameter("txtLocation");
                if(officeCode.equals("ALL")){
                    officeCode="%";
                }
            }


            imagePath = this.getServletContext().getRealPath("/images/");
            imagePath += "/";
            
            fName2 = "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);

            parameters.put("FROM_DATE", txtFrmDate);
            parameters.put("TO_DATE", txtToDate);
            parameters.put("LOCATION_ID", officeCode);
            parameters.put("VENDOR_NUMBER", txtVendorNumber);
            parameters.put("folderName", folderName);
            parameters.put("IMAGE_DIR", imagePath);

        } catch (Exception ex) {
            //ex.printStackTrace();
            logger.log(Level.ERROR, " JasperReportServlet :: Employee_detail_reports() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }  
     
            private Map Employee_Legal_Submitted_Reports(HttpServletRequest request) {
        Map parameters = new HashMap();

        String reportName = "";
        String fName2 = "";
        String reportFileName = ApplicationConstants.REPORT_EMPLOYEE_LEGAL_SUBMITTED;
        String folderName = ApplicationConstants.REPORT_MSEDCL_LEGAL_EMP;
        String imagePath = "";

        String officeCode = "";
        String txtFrmDate = "";
        String txtToDate = "";
        String txtVendorNumber = "";
    

        try {
            logger.log(Level.INFO, " JasperReportServlet :: Employee_Legal_Submitted_Reports() :: method called");

            reportName = request.getParameter("reportName");



            if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                txtFrmDate = (String) request.getParameter("txtFrmDt");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                
                txtToDate = (String) request.getParameter("txtToDt");
                
            }

            if (!ApplicationUtils.isBlank((request.getParameter("txtVendorNumber")))) {
                txtVendorNumber = (String) request.getParameter("txtVendorNumber");
                if(txtVendorNumber.equals("ALL")){
                txtVendorNumber="%";
            }
            }
          

            if (!ApplicationUtils.isBlank((request.getParameter("txtLocation")))) {
                officeCode = (String) request.getParameter("txtLocation");
                if(officeCode.equals("ALL")){
                    officeCode="%";
                }
            }


            imagePath = this.getServletContext().getRealPath("/images/");
            imagePath += "/";
            
            fName2 = "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);

            parameters.put("FROM_DATE", txtFrmDate);
            parameters.put("TO_DATE", txtToDate);
            parameters.put("LOCATION_ID", officeCode);
            parameters.put("VENDOR_NUMBER", txtVendorNumber);
            parameters.put("folderName", folderName);
            parameters.put("IMAGE_DIR", imagePath);

        } catch (Exception ex) {
            //ex.printStackTrace();
            logger.log(Level.ERROR, " JasperReportServlet :: Employee_Legal_Submitted_Reports() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }
              private Map Vendor_Legal_Returned_Reports(HttpServletRequest request) {
       Map parameters = new HashMap();

        String reportName = "";
        String fName2 = "";
        String reportFileName = ApplicationConstants.REPORT_VENDOR_LEGAL_RETURNED;
        String folderName = ApplicationConstants.REPORT_MSEDCL_LEGAL_VENDOR;
        String imagePath = "";

        String officeCode = "";
        String txtFrmDate = "";
        String txtToDate = "";
        String txtVendorNumber = "";
        
        String txtInvNo="";

        try {
            logger.log(Level.INFO, " JasperReportServlet :: Vendor_Legal_Returned_Reports() :: method called");

           
            reportName = request.getParameter("reportName");

            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                txtVendorNumber = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
            }

            if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                txtFrmDate = (String) request.getParameter("txtFrmDt");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                
                txtToDate = (String) request.getParameter("txtToDt");
                
            }

          
            if (!ApplicationUtils.isBlank((request.getParameter("txtInvNo")))) {
                txtInvNo = (String) request.getParameter("txtInvNo");
                if(txtInvNo.equals("ALL")){
                txtInvNo="%";
            }
            }
            
          

            if (!ApplicationUtils.isBlank((request.getParameter("txtLocation")))) {
                officeCode = (String) request.getParameter("txtLocation");
                if(officeCode.equals("ALL")){
                    officeCode="%";
                }
            }


            imagePath = this.getServletContext().getRealPath("/images/");
            imagePath += "/";
            
            fName2 = "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);
            parameters.put("VENDOR_NUMBER", txtVendorNumber);
            parameters.put("FROM_DATE", txtFrmDate);
            parameters.put("TO_DATE", txtToDate);
            parameters.put("LOCATION_ID", officeCode);
            parameters.put("INVOICE_NO", txtInvNo);
            parameters.put("folderName", folderName);
            parameters.put("IMAGE_DIR", imagePath);

        } catch (Exception ex) {
            //ex.printStackTrace();
            logger.log(Level.ERROR, " JasperReportServlet :: Vendor_Legal_Returned_Reports() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }  
     
            private Map Vendor_Legal_Submitted_Reports(HttpServletRequest request) {
        Map parameters = new HashMap();

        String reportName = "";
        String fName2 = "";
        String reportFileName = ApplicationConstants.REPORT_VENDOR_LEGAL_SUBMITTED;
        String folderName = ApplicationConstants.REPORT_MSEDCL_LEGAL_VENDOR;
        String imagePath = "";

        String officeCode = "";
        String txtFrmDate = "";
        String txtToDate = "";
        String txtVendorNumber = "";
        String txtInvNo="";

        try {
            logger.log(Level.INFO, " JasperReportServlet :: Vendor_Legal_Submitted_Reports() :: method called");

            reportName = request.getParameter("reportName");
            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                txtVendorNumber = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
            }


            if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                txtFrmDate = (String) request.getParameter("txtFrmDt");
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                
                txtToDate = (String) request.getParameter("txtToDt");
                
            }

            if (!ApplicationUtils.isBlank((request.getParameter("txtInvNo")))) {
                txtInvNo = (String) request.getParameter("txtInvNo");
                if(txtInvNo.equals("ALL")){
                txtInvNo="%";
            }
            }
          

            if (!ApplicationUtils.isBlank((request.getParameter("txtLocation")))) {
                officeCode = (String) request.getParameter("txtLocation");
                if(officeCode.equals("ALL")){
                    officeCode="%";
                }
            }


            imagePath = this.getServletContext().getRealPath("/images/");
            imagePath += "/";
            
            fName2 = "_" + new SimpleDateFormat(ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT_HRS_MIN_FD).format(new Date());
            reportName = reportName + "_" + fName2;

            parameters.put("reportName", reportName);
            parameters.put("reportFileName", reportFileName);
            parameters.put("VENDOR_NUMBER", txtVendorNumber);
            parameters.put("FROM_DATE", txtFrmDate);
            parameters.put("TO_DATE", txtToDate);
            parameters.put("LOCATION_ID", officeCode);
            parameters.put("INVOICE_NO", txtInvNo);
            parameters.put("folderName", folderName);
            parameters.put("IMAGE_DIR", imagePath);

        } catch (Exception ex) {
            //ex.printStackTrace();
            logger.log(Level.ERROR, " JasperReportServlet :: Vendor_Legal_Submitted_Reports() :: Exception :: " + ex);
        } finally {
            return parameters;
        }
    }

}
