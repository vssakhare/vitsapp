/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.servlet;

import in.emp.common.ApplicationConstants;
import in.emp.legal.bean.FeeTypeBean;
import in.emp.legal.bean.LegalInvoiceBean;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.manager.VendorManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LegalServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private VendorDelegate vendorMgrObj = new VendorManager();

    public LegalServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("actionName") != null && request.getParameter("actionName").equalsIgnoreCase("autocomplete")) {
            if (request.getParameter("autoCompleteParam") != null && request.getParameter("autoCompleteParam").equalsIgnoreCase("vendor")) {
                processVendorAutocompleteRequest(request, response);
            } else if (request.getParameter("autoCompleteParam") != null && request.getParameter("autoCompleteParam").equalsIgnoreCase("caseRefNo")) {
                processCaseRefNoAutocompleteRequest(request, response);
            } else {
                processAutocompleteRequest(request, response);
            }
        } else if (request.getParameter("actionName") != null && request.getParameter("actionName").equalsIgnoreCase("populateCaseDetails")) {
            populateCaseDetails(request, response);
        } else if (request.getParameter("actionName") != null && request.getParameter("actionName").equalsIgnoreCase("populateVendorDetails")) {
            populateVendorDetails(request, response);
        } else if (request.getParameter("actionName") != null && request.getParameter("actionName").equalsIgnoreCase("populateCaseRefDetails")) {
            populateCaseRefDetails(request, response);
        } else if (request.getParameter("actionName") != null && request.getParameter("actionName").equalsIgnoreCase("populateCaseDetailsNew")) {
            populateCaseDetailsNew(request, response);
        }

    }

    private void processAutocompleteRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        JSONArray arrayObj = new JSONArray();

        LegalInvoiceBean legalInvoiceBean = new LegalInvoiceBean();
        legalInvoiceBean.setWhereClause("vendor");
        legalInvoiceBean.setVENDOR(request.getParameter("txtVendorCode"));
        List<LegalInvoiceBean> legalInvoiceBeanList = null;
        try {
            legalInvoiceBeanList = vendorMgrObj.getCourtCaseDetailsForVendor(legalInvoiceBean);
        } catch (Exception ex) {
            Logger.getLogger(LegalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = request.getParameter("term");
        System.out.println("legalInvoiceBeanList==" + legalInvoiceBeanList.size());
//    query = query.toLowerCase();
        for (int i = 0; i < legalInvoiceBeanList.size(); i++) {
            String searchCase = legalInvoiceBeanList.get(i).getCASENOCOURT().toLowerCase();
            if (searchCase.contains(query)) {
                arrayObj.add(legalInvoiceBeanList.get(i).getCASENOCOURT());
//                arrayObj.add(legalInvoiceBeanList.get(i).getCASENOCOURT()+"-"+legalInvoiceBeanList.get(i).getCOURTNAME()+"-"+legalInvoiceBeanList.get(i).getCASETYPE()+"-"+legalInvoiceBeanList.get(0).getCASEDET());
            }
        }

        out.println(arrayObj.toString());
        out.close();
    }

    private void populateCaseDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LegalInvoiceBean legalInvoiceBean = new LegalInvoiceBean();
        PrintWriter out = response.getWriter();
        JSONObject jSONObject = new JSONObject();
        legalInvoiceBean.setVENDOR(request.getParameter("txtVendorCode"));
        if (request.getParameter("caseRefNo")!=null && !request.getParameter("caseRefNo").equals("")) {legalInvoiceBean.setCASEREFNO(Integer.valueOf(request.getParameter("caseRefNo")));
        legalInvoiceBean.setWhereClause("vendorCaseRefNo");
        }
        else legalInvoiceBean.setWhereClause("vendor");
        //System.out.println("populateCaseDetails");
        //System.out.println("caseRefNo"+request.getParameter("caseRefNo"));
        
        List<LegalInvoiceBean> legalInvoiceBeanList = null;
        try {
            legalInvoiceBeanList = vendorMgrObj.getCourtCaseDetailsForVendor(legalInvoiceBean);
        } catch (Exception ex) {
            Logger.getLogger(LegalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (legalInvoiceBeanList != null && legalInvoiceBeanList.size() > 0) {

            jSONObject.put("CaseRefNo", legalInvoiceBeanList.get(0).getCASEREFNO());
            jSONObject.put("CourtName", legalInvoiceBeanList.get(0).getCOURTNAME());
            jSONObject.put("CaseDescription", legalInvoiceBeanList.get(0).getCASEDET());
            jSONObject.put("CaseTypeDesc", legalInvoiceBeanList.get(0).getCASETYPEDESC());
            jSONObject.put("PartyNames", legalInvoiceBeanList.get(0).getMsedclPartyName());
            jSONObject.put("VsPartyNames", legalInvoiceBeanList.get(0).getVsPartyName());
            jSONObject.put("DealingOffice", legalInvoiceBeanList.get(0).getOfficeCode() + "-" + legalInvoiceBeanList.get(0).getOfficeName());
            jSONObject.put("selectedOffieCode", legalInvoiceBeanList.get(0).getOfficeCode());
            FeeTypeBean bean = new FeeTypeBean();
            bean.setCaseType(Integer.parseInt(legalInvoiceBeanList.get(0).getCASETYPE()));
            try {
                List<FeeTypeBean> feeTypeBeanList = vendorMgrObj.getLegalFeeType(bean);
                String optionList = ""; //String[] optionListArray;
                JSONArray array = new JSONArray();
                for (FeeTypeBean feeType : feeTypeBeanList) {
                    optionList += "<option value=" + feeType.getFeeType() + ">" + feeType.getFeeType() + "</option>";
                    array.add("feetype\": "+"\"<option value=" + feeType.getFeeType() + ">" + feeType.getFeeType() + "</option>");
                }
                //optionListArray = optionList.split("[\\r\\n]+");
                //System.out.println(optionList);
                jSONObject.put("feeTypeList", optionList);//jSONObject.put("feeTypeListArray", array);
            } catch (Exception ex) {
                Logger.getLogger(LegalServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        out.print(jSONObject);
        out.flush();
    }

    private void populateCaseDetailsNew(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LegalInvoiceBean legalInvoiceBean = new LegalInvoiceBean();
        PrintWriter out = response.getWriter();
        JSONObject jSONObject = new JSONObject();
        legalInvoiceBean.setVENDOR(request.getParameter("txtVendorCode"));
        legalInvoiceBean.setCASENOCOURT(request.getParameter("caseNo"));
        legalInvoiceBean.setWhereClause("vendorCaseNoNew");
        List<LegalInvoiceBean> legalInvoiceBeanList = null;
        try {
            legalInvoiceBeanList = vendorMgrObj.getCourtCaseDetailsForVendor(legalInvoiceBean);
        } catch (Exception ex) {
            Logger.getLogger(LegalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("legalInvoiceBeanList.size()"+legalInvoiceBeanList.size());
        if (legalInvoiceBeanList != null && legalInvoiceBeanList.size() > 0) {

            /*jSONObject.put("CaseRefNo", legalInvoiceBeanList.get(0).getCASEREFNO());
            jSONObject.put("CourtName", legalInvoiceBeanList.get(0).getCOURTNAME());
            jSONObject.put("CaseDescription", legalInvoiceBeanList.get(0).getCASEDET());
            jSONObject.put("PartyNames", legalInvoiceBeanList.get(0).getMsedclPartyName());
            jSONObject.put("DealingOffice", legalInvoiceBeanList.get(0).getOfficeCode() + "-" + legalInvoiceBeanList.get(0).getOfficeName());
            jSONObject.put("selectedOffieCode", legalInvoiceBeanList.get(0).getOfficeCode());
            */FeeTypeBean bean = new FeeTypeBean();
            bean.setCaseType(Integer.parseInt(legalInvoiceBeanList.get(0).getCASETYPE()));
            try {
                List<FeeTypeBean> feeTypeBeanList = vendorMgrObj.getLegalFeeType(bean);
                String optionList = "";
                for (FeeTypeBean feeType : feeTypeBeanList) {
                    optionList += "<option value=" + feeType.getFeeType() + ">" + feeType.getFeeType() + "</option>";
                }
                //jSONObject.put("feeTypeList", optionList);
            } catch (Exception ex) {
                Logger.getLogger(LegalServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //out.print(jSONObject);
        //System.out.println("populateCaseDetailsNew");
        out.print("<html><script>\n" +
"function selectFunction() {"
                + "//alert(document.querySelector('input[name=\"casenoradio\"]:checked').value);\n" + 
                " var x = document.querySelector('input[name=\"casenoradio\"]:checked').value.split('|');//alert(x[0]+' '+x[1]);\n"
                + "window.opener.document.getElementById(\"txtCourtCaseNo\").value = x[0];\n"
                + "window.opener.document.getElementById(\"txtCaseRefNo\").value = x[1];\n" +
" window.opener.document.getElementById(\"txtDealingOffice\").focus();window.opener.populateCaeseDetails();\n" + " window.close();}\n" +
"</script><body><table><thead style=\"background-color: lightblue; position: sticky; top:0\"><tr style=\"text-align:left\">"
                + "<th><input type='button' value='Select' onclick='selectFunction();'></th><th>Case No.</th><th>Case Ref. No.</th><th>Filing Date</th><th>Office Name</th>"
                + "<th>Court Name</th><th>Case Type</th><th>Case Details</th><th>MSEDCL Party Name</th></tr></thead>");
        
        for (LegalInvoiceBean lib : legalInvoiceBeanList){
            out.println("<tr><td align='center'><input type='radio' name='casenoradio' value='"+lib.getCASENOCOURT()+'|'+lib.getCASEREFNO()+"'>"
                    + "</td><td>"+lib.getCASENOCOURT()+"</td><td>"+lib.getCASEREFNO()+"</td><td>"+(lib.getDOF_LC() == null ? "N.A." : new SimpleDateFormat("dd/MM/yyyy").format(lib.getDOF_LC()))+"</td><td>"+lib.getOfficeName()+"</td><td>"
                    +lib.getCOURTNAME()+"</td><td>"+lib.getCASETYPEDESC()+"</td><td>"+lib.getCASEDET()+"</td><td>"+lib.getMsedclPartyName()+"</td></tr>");
        //out.println(lib.getCASEREFNO());
    } out.print("</table></body></html>");
        out.flush();
    }
    
    private void processVendorAutocompleteRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        JSONArray arrayObj = new JSONArray();

        LegalInvoiceBean legalInvoiceBean = new LegalInvoiceBean();
        legalInvoiceBean.setWhereClause("Emp");
//        legalInvoiceBean.setVENDOR(request.getParameter("txtVendorCode"));
        legalInvoiceBean.setLocationId((String) request.getSession().getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
        List<LegalInvoiceBean> legalInvoiceBeanList = null;
        try {
            legalInvoiceBeanList = vendorMgrObj.getCourtCaseDetailsForVendor(legalInvoiceBean);
        } catch (Exception ex) {
            Logger.getLogger(LegalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = request.getParameter("term");
//    query = query.toLowerCase();
        for (int i = 0; i < legalInvoiceBeanList.size(); i++) {
            String searchCase = legalInvoiceBeanList.get(i).getVENDOR().toLowerCase();
            if (searchCase.contains(query)) {
                arrayObj.add("0" + legalInvoiceBeanList.get(i).getVENDOR());
            }
        }

        out.println(arrayObj.toString());
        out.close();
    }

    private void populateVendorDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LegalInvoiceBean legalInvoiceBean = new LegalInvoiceBean();
        PrintWriter out = response.getWriter();
        JSONObject jSONObject = new JSONObject();
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        try {
            vendorInputBeanObj.setVendorNumber(request.getParameter("txtVendorCode"));
            vendorInputBeanObj = vendorMgrObj.getVendorInputForm(vendorInputBeanObj);
        } catch (Exception ex) {
            Logger.getLogger(LegalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (vendorInputBeanObj != null) {

            jSONObject.put("vendorName", vendorInputBeanObj.getVendorName());

        }
        out.print(jSONObject);
        out.flush();
    }
    

    private void processCaseRefNoAutocompleteRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("processCaseRefNoAutocompleteRequest");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        JSONArray arrayObj = new JSONArray();

        LegalInvoiceBean legalInvoiceBean = new LegalInvoiceBean();
        legalInvoiceBean.setWhereClause("CaseRefNo");
//        legalInvoiceBean.setVENDOR(request.getParameter("txtVendorCode"));
        legalInvoiceBean.setLocationId((String) request.getSession().getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
        //legalInvoiceBean.setVENDOR(request.getParameter("txtVendorCode"));
        List<LegalInvoiceBean> legalInvoiceBeanList = null;
        try {
            legalInvoiceBeanList = vendorMgrObj.getCourtCaseDetailsForVendor(legalInvoiceBean);
        } catch (Exception ex) {
            Logger.getLogger(LegalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = request.getParameter("term");
        System.out.println("legalInvoiceBeanList==" + legalInvoiceBeanList.size());
//    query = query.toLowerCase();
        for (int i = 0; i < legalInvoiceBeanList.size(); i++) {
            String searchCase = legalInvoiceBeanList.get(i).getCASEREFNO() + "";
            if (searchCase.contains(query)) {
                arrayObj.add(legalInvoiceBeanList.get(i).getCASEREFNO());
            }
        }
        out.println(arrayObj.toString());
        out.close();
    }
    private void populateCaseRefDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LegalInvoiceBean legalInvoiceBean = new LegalInvoiceBean();
        PrintWriter out = response.getWriter();
        JSONObject jSONObject = new JSONObject();
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        
        legalInvoiceBean.setVENDOR(request.getParameter("txtVendorCode"));
        legalInvoiceBean.setCASEREFNO(Integer.parseInt(request.getParameter("txtCaseRefNoWithout")));
        legalInvoiceBean.setWhereClause("vendorCaseRefNo");
        List<LegalInvoiceBean> legalInvoiceBeanList = null;
        try {
            legalInvoiceBeanList = vendorMgrObj.getCourtCaseDetailsForVendor(legalInvoiceBean);
        } catch (Exception ex) {
            Logger.getLogger(LegalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (legalInvoiceBeanList != null && legalInvoiceBeanList.size() > 0) {

            jSONObject.put("CourtCaseNo", legalInvoiceBeanList.get(0).getCASENOCOURT());
            jSONObject.put("CourtName", legalInvoiceBeanList.get(0).getCOURTNAME());
            jSONObject.put("CaseDescription", legalInvoiceBeanList.get(0).getCASEDET());
            jSONObject.put("PartyNames", legalInvoiceBeanList.get(0).getMsedclPartyName());
            jSONObject.put("DealingOffice", legalInvoiceBeanList.get(0).getOfficeCode() + "-" + legalInvoiceBeanList.get(0).getOfficeName());
            jSONObject.put("selectedOffieCode", legalInvoiceBeanList.get(0).getOfficeCode());
            jSONObject.put("feeTypeList", legalInvoiceBeanList.get(0).getADV_FEE_TYPE());
//            FeeTypeBean bean = new FeeTypeBean();
//            bean.setCaseType(Integer.parseInt(legalInvoiceBeanList.get(0).getCASETYPE()));
//            try {
//                List<FeeTypeBean> feeTypeBeanList = vendorMgrObj.getLegalFeeType(bean);
//                String optionList = "";
//                for (FeeTypeBean feeType : feeTypeBeanList) {
//                    optionList += "<option value=" + feeType.getFeeType() + ">" + feeType.getFeeType() + "</option>";
//                }
//                jSONObject.put("feeTypeList", optionList);
//            } catch (Exception ex) {
//                Logger.getLogger(LegalServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        out.print(jSONObject);
        out.flush();
    }

}
