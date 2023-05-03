<%--
    Document   : erp_vendor_appl_form
    Created on : Dec 10, 2017, 4:06:44 PM
    Author     : pooja
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="in.emp.vendor.bean.VendorBean"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%

    String recordsVar = "No Records To Display !!!";
    String uiAction = "";

    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        uiAction = request.getParameter("uiActionName");
    }

    VendorPrezData vendorPrezDataObj = new VendorPrezData();
    VendorBean vendorBean = new VendorBean();
    VendorBean vendorRetentionBean = null;
    String SaveFlag = "";
    int flag = 0;

    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_APPL_FORM_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_APPL_FORM_SESSION_DATA);

        vendorBean = (VendorBean) vendorPrezDataObj.getVendorBean();

    }
    if (vendorBean.getInvoice_Type().equalsIgnoreCase("Retention Claim Charges")) {
        if (request.getSession().getAttribute(ApplicationConstants.VENDOR_RETENTION_APPL_FORM_SESSION_DATA) != null) {
            vendorRetentionBean = (VendorBean) request.getSession().getAttribute(ApplicationConstants.VENDOR_RETENTION_APPL_FORM_SESSION_DATA);
        }
    }
    String Zone = "";
    String Circle = "";
    String Division = "";
    int i = 0;
    // Set <String>clearing_amt = new HashSet<String>();
    HashMap<String, String> clearing_amt = new HashMap<String, String>();
    String Status = "";

    String VendorNum = "";
    String VendorName = "";

    String MSEDCLInvNo = "";
    String MSEDCLInvDate = "";
    String VENDORInvDate = "";
    String PROJECT_ID = "";
    String PROJECT_DESC = "";
    String CREATION_DATE = "";
    String PLANT = "";
    String SCHEME_CODE = "";
    String MATERIAL_PO = "";
    String CENTAGES_PO = "";
    String SERVICE_PO = "";
    String VENDOR_NAME = "";
    String VENDOR_NUMBER = "";
    String MSEDCL_INV_NO = "";
    String UserType = "";
    String MATERIAL_WS_PARK_DOC = "";
    String MAT_DOC_DATE = "";
    String MAT_WS_PARK_AMT = "";
    String MAT_WS_CLEARING_DOC_NO = "";
    String MAT_WS_AC_DOC_DATE = "";
    String MAT_WS_CLEARING_AMT = "";
    String CENTAGES_PARK_DOC = "";
    String CEN_DOC_DATE = "";
    String CEN_PARK_AMT = "";
    String CEN_CLEARING_DOC_NO = "";
    String CEN_AC_DOC_DATE = "";
    String CEN_CLEARING_AMT = "";
    String CIVIL_PARK_DOC = "";
    String CIVIL_DOC_DATE = "";
    String CIVIL_PARK_AMT = "";
    String CIVIL_CLEARING_DOC_NO = "";
    String CIVIL_AC_DOC_DATE = "";
    String CIVIL_CLEARING_AMT = "";
    String MAT_OS_PARK_DOC_NO = "";
    String MAT_OS_DOC_DATE = "";
    String MAT_OS_PARK_AMT = "";
    String MAT_OS_CLEARING_DOC_NO = "";
    String MAT_OS_AC_DOC_DATE = "";
    String MAT_OS_CLEARING_AMT = "";
    String MSEDCL_INVOICE_NUMBER = "";
    String MAT_OTH_PARK_DOC_NO = "";
    String MAT_OTH_DOC_DATE = "";
    String MAT_OTH_PARK_AMT = "";
    String MAT_OTH_CLEARING_DOC_NO = "";
    String MAT_OTH_AC_DOC_DATE = "";
    String MAT_OTH_CLEARING_AMT = "";
    String MAT_PO_AMOUNT = "";
    String CEN_PO_AMOUNT = "";
    String CIV_PO_AMOUNT = "";
    String INV_PO_AMOUNT = "";
    String ProjectCode = "";
    String ProjectScheme = "";
    String TotalPoAmount = "";
    String InvoiceNumber = "";
    String TAX_CODE = "";
    String INV_TYP = "";
    String WTAX_AMT = "";
    String WIT_TDS = "";
    String WGST_TDS = "";
    String WRPST = "";
    String WRET_AMT = "";
    String OTAX_AMT = "";
    String OIT_TDS = "";
    String OGST_TDS = "";
    String ORPST = "";
    String ORET_AMT = "";
    String CTAX_AMT = "";
    String CIT_TDS = "";
    String CGST_TDS = "";
    String CRPST = "";
    String CRET_AMT = "";
    String STAX_AMT = "";
    String SIT_TDS = "";
    String SGST_TDS = "";
    String SRPST = "";
    String SRET_AMT = "";
    String OTTAX_AMT = "";
    String OTIT_TDS = "";
    String OTGST_TDS = "";
    String OTRPST = "";
    String OTRET_AMT = "";
    String SubStatus = "";
    String INVOICE_TYPE = "";
    String MIGST_TX = "";
    String MSGST_TX = "";
    String OSGST_TX = "";
    String OIGST_TX = "";
    String CSGST_TX = "";
    String CIGST_TX = "";
    String SSGST_TX = "";
    String SIGST_TX = "";
    String OTSGST_TX = "";
    String OTIGST_TX = "";

    if (vendorBean != null) {
        if (!ApplicationUtils.isBlank(vendorBean.getZone())) {
            Zone = vendorBean.getZone();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCircle())) {
            Circle = vendorBean.getCircle();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getDivision())) {
            Division = vendorBean.getDivision();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getProjectCode())) {
            PROJECT_ID = vendorBean.getProjectCode();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getPROJECT_DESC())) {
            PROJECT_DESC = vendorBean.getPROJECT_DESC();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMsedclInvoiceNumber())) {
            MSEDCL_INVOICE_NUMBER = vendorBean.getMsedclInvoiceNumber();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCREATION_DATE())) {
            CREATION_DATE = ApplicationUtils.dateToString(vendorBean.getCREATION_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_DOC_DATE())) {
            MAT_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getMAT_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_WS_AC_DOC_DATE())) {
            MAT_WS_AC_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getMAT_WS_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getSCHEME_CODE())) {
            SCHEME_CODE = vendorBean.getSCHEME_CODE();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getSaveFlag())) {
            Status = vendorBean.getSaveFlag();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getTECH_STAT())) {
            SubStatus = vendorBean.getTECH_STAT();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMsedclInvoiceNumber())) {
            MSEDCLInvNo = vendorBean.getMsedclInvoiceNumber();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMsedclInvoiceDate())) {
            MSEDCLInvDate = ApplicationUtils.dateToString(vendorBean.getMsedclInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceDate())) {
            VENDORInvDate = ApplicationUtils.dateToString(vendorBean.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMATERIAL_PO())) {
            MATERIAL_PO = vendorBean.getMATERIAL_PO();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCENTAGES_PO())) {
            CENTAGES_PO = vendorBean.getCENTAGES_PO();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSERVICE_PO())) {
            SERVICE_PO = vendorBean.getSERVICE_PO();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCEN_AC_DOC_DATE())) {
            CEN_AC_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getCEN_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_DOC_DATE())) {
            CIVIL_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getCIVIL_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_CLEARING_DOC_NO())) {
            CIVIL_CLEARING_DOC_NO = vendorBean.getCIVIL_CLEARING_DOC_NO();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMATERIAL_WS_PARK_DOC())) {
            MATERIAL_WS_PARK_DOC = vendorBean.getMATERIAL_WS_PARK_DOC();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_WS_PARK_AMT())) {
            MAT_WS_PARK_AMT = vendorBean.getMAT_WS_PARK_AMT();
            MAT_WS_PARK_AMT = ApplicationUtils.formatAmount(Double.valueOf(MAT_WS_PARK_AMT));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getUserType())) {
            UserType = vendorBean.getUserType();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_WS_CLEARING_DOC_NO())) {
            MAT_WS_CLEARING_DOC_NO = vendorBean.getMAT_WS_CLEARING_DOC_NO();
            if (!ApplicationUtils.isBlank(vendorBean.getMAT_WS_CLEARING_AMT())) {
                MAT_WS_CLEARING_AMT = vendorBean.getMAT_WS_CLEARING_AMT();
            }
            clearing_amt.put(MAT_WS_CLEARING_DOC_NO, MAT_WS_CLEARING_AMT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getDispVendorNumber())) {
            VendorNum = vendorBean.getDispVendorNumber();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getDispVendorName())) {
            VendorName = vendorBean.getDispVendorName();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_WS_CLEARING_AMT())) {
            MAT_WS_CLEARING_AMT = vendorBean.getMAT_WS_CLEARING_AMT();

            MAT_WS_CLEARING_AMT = ApplicationUtils.formatAmount(Double.valueOf(MAT_WS_CLEARING_AMT));

        }

        if (!ApplicationUtils.isBlank(vendorBean.getCENTAGES_PARK_DOC())) {
            CENTAGES_PARK_DOC = vendorBean.getCENTAGES_PARK_DOC();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCEN_CLEARING_DOC_NO())) {
            CEN_CLEARING_DOC_NO = vendorBean.getCEN_CLEARING_DOC_NO();
            if (!ApplicationUtils.isBlank(vendorBean.getCEN_CLEARING_AMT())) {
                CEN_CLEARING_AMT = vendorBean.getCEN_CLEARING_AMT();
            }
            clearing_amt.put(CEN_CLEARING_DOC_NO, CEN_CLEARING_AMT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCEN_DOC_DATE())) {
            CEN_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getCEN_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_AC_DOC_DATE())) {
            CIVIL_AC_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getCIVIL_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_PARK_DOC())) {
            CIVIL_PARK_DOC = vendorBean.getCIVIL_PARK_DOC();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCEN_PARK_AMT())) {
            CEN_PARK_AMT = vendorBean.getCEN_PARK_AMT();
            CEN_PARK_AMT = ApplicationUtils.formatAmount(Double.valueOf(CEN_PARK_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCEN_CLEARING_AMT())) {
            CEN_CLEARING_AMT = vendorBean.getCEN_CLEARING_AMT();

            CEN_CLEARING_AMT = ApplicationUtils.formatAmount(Double.valueOf(CEN_CLEARING_AMT));

        }
        if (!ApplicationUtils.isBlank(vendorBean.getProjectCode())) {
            ProjectCode = vendorBean.getProjectCode();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getProjectScheme())) {
            ProjectScheme = vendorBean.getProjectScheme();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_PARK_DOC_NO())) {
            MAT_OS_PARK_DOC_NO = vendorBean.getMAT_OS_PARK_DOC_NO();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_CLEARING_DOC_NO())) {
            MAT_OS_CLEARING_DOC_NO = vendorBean.getMAT_OS_CLEARING_DOC_NO();
            if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_CLEARING_AMT())) {
                MAT_OS_CLEARING_AMT = vendorBean.getMAT_OS_CLEARING_AMT();
            }
            clearing_amt.put(MAT_OS_CLEARING_DOC_NO, MAT_OS_CLEARING_AMT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_DOC_DATE())) {
            MAT_OS_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getMAT_OS_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_AC_DOC_DATE())) {
            MAT_OS_AC_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getMAT_OS_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_PARK_AMT())) {
            MAT_OS_PARK_AMT = vendorBean.getMAT_OS_PARK_AMT();
            MAT_OS_PARK_AMT = ApplicationUtils.formatAmount(Double.valueOf(MAT_OS_PARK_AMT));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_CLEARING_AMT())) {
            MAT_OS_CLEARING_AMT = vendorBean.getMAT_OS_CLEARING_AMT();

            MAT_OS_CLEARING_AMT = ApplicationUtils.formatAmount(Double.valueOf(MAT_OS_CLEARING_AMT));

        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_CLEARING_DOC_NO())) {
            CIVIL_CLEARING_DOC_NO = vendorBean.getCIVIL_CLEARING_DOC_NO();
            if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_CLEARING_AMT())) {
                CIVIL_CLEARING_AMT = vendorBean.getCIVIL_CLEARING_AMT();
            }
            clearing_amt.put(CIVIL_CLEARING_DOC_NO, CIVIL_CLEARING_AMT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_PARK_AMT())) {
            CIVIL_PARK_AMT = vendorBean.getCIVIL_PARK_AMT();
            CIVIL_PARK_AMT = ApplicationUtils.formatAmount(Double.valueOf(CIVIL_PARK_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_CLEARING_AMT())) {
            CIVIL_CLEARING_AMT = vendorBean.getCIVIL_CLEARING_AMT();

            CIVIL_CLEARING_AMT = ApplicationUtils.formatAmount(Double.valueOf(CIVIL_CLEARING_AMT));

        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_PARK_DOC_NO())) {
            MAT_OTH_PARK_DOC_NO = vendorBean.getMAT_OTH_PARK_DOC_NO();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_CLEARING_DOC_NO())) {
            MAT_OTH_CLEARING_DOC_NO = vendorBean.getMAT_OTH_CLEARING_DOC_NO();
            if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_CLEARING_AMT())) {
                MAT_OTH_CLEARING_AMT = vendorBean.getMAT_OTH_CLEARING_AMT();
            }
            clearing_amt.put(MAT_OTH_CLEARING_DOC_NO, MAT_OTH_CLEARING_AMT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_DOC_DATE())) {
            MAT_OTH_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getMAT_OTH_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_AC_DOC_DATE())) {
            MAT_OTH_AC_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getMAT_OTH_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_PARK_AMT())) {
            MAT_OTH_PARK_AMT = vendorBean.getMAT_OTH_PARK_AMT();
            MAT_OTH_PARK_AMT = ApplicationUtils.formatAmount(Double.valueOf(MAT_OTH_PARK_AMT));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_CLEARING_AMT())) {
            MAT_OTH_CLEARING_AMT = vendorBean.getMAT_OTH_CLEARING_AMT();

            MAT_OTH_CLEARING_AMT = ApplicationUtils.formatAmount(Double.valueOf(MAT_OTH_CLEARING_AMT));

        }
        if (!ApplicationUtils.isBlank(vendorBean.getPO_MAT_AMT())) {
            MAT_PO_AMOUNT = vendorBean.getPO_MAT_AMT();
            MAT_PO_AMOUNT = ApplicationUtils.formatAmount(Double.valueOf(MAT_PO_AMOUNT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getPO_CEN_AMT())) {
            CEN_PO_AMOUNT = vendorBean.getPO_CEN_AMT();
            CEN_PO_AMOUNT = ApplicationUtils.formatAmount(Double.valueOf(CEN_PO_AMOUNT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getPO_CIV_AMT())) {
            CIV_PO_AMOUNT = vendorBean.getPO_CIV_AMT();
            CIV_PO_AMOUNT = ApplicationUtils.formatAmount(Double.valueOf(CIV_PO_AMOUNT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getPO_INV_AMT())) {
            INV_PO_AMOUNT = vendorBean.getPO_INV_AMT();
            INV_PO_AMOUNT = ApplicationUtils.formatAmount(Double.valueOf(INV_PO_AMOUNT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getTOTAL_PO_AMOUNT())) {
            TotalPoAmount = vendorBean.getTOTAL_PO_AMOUNT();
            TotalPoAmount = ApplicationUtils.formatAmount(Double.valueOf(TotalPoAmount));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())) {
            InvoiceNumber = vendorBean.getVendorInvoiceNumber();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getTaxCode())) {
            TAX_CODE = vendorBean.getTaxCode();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getINV_TYP())) {
            INV_TYP = vendorBean.getINV_TYP();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getInvoice_Type())) {
            INVOICE_TYPE = vendorBean.getInvoice_Type();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getWTAX_AMT())) {
            WTAX_AMT = vendorBean.getWTAX_AMT();
            WTAX_AMT = ApplicationUtils.formatAmount(Double.valueOf(WTAX_AMT));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getWIT_TDS())) {
            WIT_TDS = vendorBean.getWIT_TDS();
            WIT_TDS = ApplicationUtils.formatAmount(Double.valueOf(WIT_TDS));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getWGST_TDS())) {
            WGST_TDS = vendorBean.getWGST_TDS();
            WGST_TDS = ApplicationUtils.formatAmount(Double.valueOf(WGST_TDS));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getWRPST())) {
            WRPST = vendorBean.getWRPST();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getWRET_AMT())) {
            WRET_AMT = vendorBean.getWRET_AMT();
            WRET_AMT = ApplicationUtils.formatAmount(Double.valueOf(WRET_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTAX_AMT())) {
            OTAX_AMT = vendorBean.getOTAX_AMT();
            OTAX_AMT = ApplicationUtils.formatAmount(Double.valueOf(OTAX_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOIT_TDS())) {
            OIT_TDS = vendorBean.getOIT_TDS();
            OIT_TDS = ApplicationUtils.formatAmount(Double.valueOf(OIT_TDS));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getOGST_TDS())) {
            OGST_TDS = vendorBean.getOGST_TDS();
            OGST_TDS = ApplicationUtils.formatAmount(Double.valueOf(OGST_TDS));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getORPST())) {
            ORPST = vendorBean.getORPST();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getORET_AMT())) {
            ORET_AMT = vendorBean.getORET_AMT();
            ORET_AMT = ApplicationUtils.formatAmount(Double.valueOf(ORET_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCTAX_AMT())) {
            CTAX_AMT = vendorBean.getCTAX_AMT();
            CTAX_AMT = ApplicationUtils.formatAmount(Double.valueOf(CTAX_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIT_TDS())) {
            CIT_TDS = vendorBean.getCIT_TDS();
            CIT_TDS = ApplicationUtils.formatAmount(Double.valueOf(CIT_TDS));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCGST_TDS())) {
            CGST_TDS = vendorBean.getCGST_TDS();
            CGST_TDS = ApplicationUtils.formatAmount(Double.valueOf(CGST_TDS));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCRPST())) {
            CRPST = vendorBean.getCRPST();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCRET_AMT())) {
            CRET_AMT = vendorBean.getCRET_AMT();
            CRET_AMT = ApplicationUtils.formatAmount(Double.valueOf(CRET_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSTAX_AMT())) {
            STAX_AMT = vendorBean.getSTAX_AMT();
            STAX_AMT = ApplicationUtils.formatAmount(Double.valueOf(STAX_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSIT_TDS())) {
            SIT_TDS = vendorBean.getSIT_TDS();
            SIT_TDS = ApplicationUtils.formatAmount(Double.valueOf(SIT_TDS));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSGST_TDS())) {
            SGST_TDS = vendorBean.getSGST_TDS();
            SGST_TDS = ApplicationUtils.formatAmount(Double.valueOf(SGST_TDS));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSRPST())) {
            SRPST = vendorBean.getSRPST();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSRET_AMT())) {
            SRET_AMT = vendorBean.getSRET_AMT();
            SRET_AMT = ApplicationUtils.formatAmount(Double.valueOf(SRET_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTTAX_AMT())) {
            OTTAX_AMT = vendorBean.getOTTAX_AMT();
            OTTAX_AMT = ApplicationUtils.formatAmount(Double.valueOf(OTTAX_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTIT_TDS())) {
            OTIT_TDS = vendorBean.getOTIT_TDS();
            OTIT_TDS = ApplicationUtils.formatAmount(Double.valueOf(OTIT_TDS));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTGST_TDS())) {
            OTGST_TDS = vendorBean.getOTGST_TDS();
            OTGST_TDS = ApplicationUtils.formatAmount(Double.valueOf(OTGST_TDS));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTRPST())) {
            OTRPST = vendorBean.getOTRPST();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTRET_AMT())) {
            OTRET_AMT = vendorBean.getOTRET_AMT();
            OTRET_AMT = ApplicationUtils.formatAmount(Double.valueOf(OTRET_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMIGST_TX())) {
            MIGST_TX = vendorBean.getMIGST_TX();
            MIGST_TX = ApplicationUtils.formatAmount(Double.valueOf(MIGST_TX));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMSGST_TX())) {
            MSGST_TX = vendorBean.getMSGST_TX();
            MSGST_TX = ApplicationUtils.formatAmount(Double.valueOf(MSGST_TX));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOSGST_TX())) {
            OSGST_TX = vendorBean.getOSGST_TX();
            OSGST_TX = ApplicationUtils.formatAmount(Double.valueOf(OSGST_TX));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOIGST_TX())) {
            OIGST_TX = vendorBean.getOIGST_TX();
            OIGST_TX = ApplicationUtils.formatAmount(Double.valueOf(OIGST_TX));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCSGST_TX())) {
            CSGST_TX = vendorBean.getCSGST_TX();
            CSGST_TX = ApplicationUtils.formatAmount(Double.valueOf(CSGST_TX));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIGST_TX())) {
            CIGST_TX = vendorBean.getCIGST_TX();
            CIGST_TX = ApplicationUtils.formatAmount(Double.valueOf(CIGST_TX));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSSGST_TX())) {
            SSGST_TX = vendorBean.getSSGST_TX();
            SSGST_TX = ApplicationUtils.formatAmount(Double.valueOf(SSGST_TX));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSIGST_TX())) {
            SIGST_TX = vendorBean.getSIGST_TX();
            SIGST_TX = ApplicationUtils.formatAmount(Double.valueOf(SIGST_TX));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTSGST_TX())) {
            OTSGST_TX = vendorBean.getOTSGST_TX();
            OTSGST_TX = ApplicationUtils.formatAmount(Double.valueOf(OTSGST_TX));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTIGST_TX())) {
            OTIGST_TX = vendorBean.getOTIGST_TX();
            OTIGST_TX = ApplicationUtils.formatAmount(Double.valueOf(OTIGST_TX));
        }

    }
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Vendor Payment Tracking System</title>
        <!-- BOOTSTRAP STYLES-->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONTAWESOME STYLES-->
        <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
        <link href="assets/css/custom.css" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->
        <jsp:include page="nav_jscss.jsp" />
        <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>erp_vendor_appl_form.js"></script>
    </head>
    <body>

        <div id="wrapper">
            <%@ include file="nav_emp_header.jsp"%>
            <!-- /. NAV TOP  -->
            <%@ include file="emp_nav_vmenu.jsp"%>
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner" style="min-height:500px;">

                    <%@ include file="navJs.jsp"%>

                    <div class="content_container">
                        <table class="table" width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- Start of Network Search results table -->
                            <tr> <!-- Start of Network Search Results tr -->
                                <td class="bg_white">  <!-- Start of Network Search Results td -->
                                    <!--<div class="form">-->  <!-- Start of  div  form -->
                                    <div class="table-responsive" align="center" >  <!-- Start of  content_container_sub div  -->
                                        <div class="col-md-12"><h3><fmt:message key='Invoice Form'/></h3></div>
                                        <div >&nbsp;</div>
                                    </div>
                                </td>
                            </tr>

                        </table>  <!-- End of Network Search results table -->

                        <div class="content_container_sub">  <!-- Start of  content_container_sub div  -->

                            <fieldset class="fldst_border">
                                <legend>Project Details:</legend>
                                <div class="row">

                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='PROJECT ID'/></span>
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                        <input type="text" class="form-control text-left" name="txtProjNum" id="txtProjNum" value="<%=PROJECT_ID%>" readonly="true" >
                                    </span>
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='PROJECT Scheme'/></span>
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                        <input type="text" class="form-control text-left" name="txtProjNum" id="txtProjNum" value="<%=ProjectScheme%>" readonly="true" >
                                    </span>
                                </div>
                                <div class="row">
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Zone'/></span>
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                        <input type="text" class="form-control text-left" name="txtZone" id="txtZone" value="<%=Zone%>" readonly="true" >
                                    </span></div>
                                <div class="row">
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Circle'/></span>
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                        <input type="text" class="form-control text-left" readonly="true" value="<%=Circle%>" size="20" id="txtCircle" name="txtCircle" >
                                    </span>
                                </div>
                                <div class="row">
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Division'/></span>
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                        <input type="text" class="form-control text-left" name="txtDiv" id="txtDiv" value="<%=Division%>" readonly="true" >
                                    </span>
                                </div>
                            </fieldset>
                            <fieldset class="fldst_border">
                                <legend>Vendor Details:</legend>



                                <div class="row">

                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='SAP Vendor Code'/></span>
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                        <input type="text" class="form-control text-left" name="txtVNum" id="txtVNum" value="<%=VendorNum%>" readonly="true" > 
                                    </span>

                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Vendor Name'/>.</span>
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                        <input name="txtVName" id="txtVName" type="text" size="20" class="form-control text-left" value="<%=VendorName%>" maxlength="15" readonly="true" > 
                                    </span>

                                </div>
                            </fieldset>
                            <fieldset class="fldst_border">
                                <legend>PO Details:</legend>


                                <div class="row">
                                    <div class="col-lg-12 col-md-12">
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr class="success">                                                                                       
                                                        <th class="text-center"> PO type</th> 
                                                        <th class="text-center"><fmt:message key='PO Number'/></th> 
                                                        <th class="text-center"><fmt:message key='PO AMOUNT'/></th>  

                                                    </tr>
                                                    <tbody>
                                                        <tr class="info" >
                                                            <td width="20%" class="text-center">Material PO</td>
                                                            <td width="20%" class="text-center"><%=MATERIAL_PO%></td>
                                                            <td width="20%" class="text-center"><%=MAT_PO_AMOUNT%></td> 
                                                        </tr>
                                                        <tr class="info" >
                                                            <td width="20%" class="text-center">Centages PO</td>
                                                            <td width="20%" class="text-center"><%=CENTAGES_PO%></td>
                                                            <td width="20%" class="text-center"><%=CEN_PO_AMOUNT%></td> 
                                                        </tr>
                                                        <tr class="info" >
                                                            <td width="20%" class="text-center">Civil PO</td>
                                                            <td width="20%" class="text-center"><%=SERVICE_PO%></td>
                                                            <td width="20%" class="text-center"><%=CIV_PO_AMOUNT%></td> 
                                                        </tr>
                                                    </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>


                            </fieldset>

                            <fieldset class="fldst_border">
                                <legend>Invoice Details:</legend>  
                                <div class="row">                      <div class="row">
                                        <div class="col-lg-12 col-md-12">
                                            <div class="table-responsive">
                                                <table class="table">
                                                    <thead>
                                                        <tr class="success">                                                                                       
                                                            <th class="text-center"> <fmt:message key='Vendor Invoice Number'/></th> 
                                                            <th class="text-center"> <fmt:message key='Vendor Invoice Date'/></th> 
                                                            <th class="text-center"> <fmt:message key='Invoice Type'/></th> 
                                                            <%  if (UserType.equals("Emp")) {%>     <th class="text-center"> <fmt:message key='MSEDCL Invoice Number'/></th><%}%>  
                                                                <%if (!INVOICE_TYPE.equalsIgnoreCase("Retention Claim Charges")) {%>
                                                            <th class="text-center"><fmt:message key='MSEDCL Invoice Amount'/></th> 
                                                            <th class="text-center"><fmt:message key='MSEDCL Invoice Date'/></th>  
                                                                <%}%>
                                                            <th class="text-center"><fmt:message key='Status'/></th>                                                 
                                                        </tr>
                                                        <tbody>
                                                            <tr class="info" >
                                                                <td width="20%" class="text-center"><%=InvoiceNumber%></td>

                                                                <td width="20%" class="text-center"><%=VENDORInvDate%></td>
                                                                <td width="20%" class="text-center"><%=INVOICE_TYPE%></td>
                                                                <%  if (UserType.equals("Emp")) {%>      <td width="20%" class="text-center"><%=MSEDCL_INVOICE_NUMBER%></td><%}%> 
                                                                <%if (!INVOICE_TYPE.equalsIgnoreCase("Retention Claim Charges")) {%>
                                                                <td width="20%" class="text-center"><%=INV_PO_AMOUNT%></td>
                                                                <td width="20%" class="text-center"><%=MSEDCLInvDate%></td>
                                                                <%}%>
                                                                <%if (SubStatus.equals("S"))//S status for ses pending
                                                {%>
                                                                <td width="20%" class="text-center"><%=Status + "(SES Pending)"%></td> 
                                                                <%} else {%>
                                                                <td width="20%" class="text-center"><%=Status%></td>
                                                                <%} %>
                                                            </tr>

                                                        </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                            </fieldset>
                            <%if (!INVOICE_TYPE.equalsIgnoreCase("Retention Claim Charges")) {%>
                            <fieldset class="fldst_border">
                                <legend>Accounting Details:</legend> 
                                <div class="row">
                                    <div class="col-lg-12 col-md-12">
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr class="success">                                                                                       
                                                        <th class="text-center" rowspan="2"> <fmt:message key='Doc Type'/></th> 
                                                        <%  if (UserType.equals("Emp")) {%>       <th class="text-center" rowspan="2"> <fmt:message key='FI Invoice Doc'/></th> <%}%> 
                                                        <th class="text-center" rowspan="2"><fmt:message key='Date'/></th> 
                                                        <th class="text-center" rowspan="2"><fmt:message key='Amount'/></th>  
                                                        <th class="text-center" rowspan="2"><fmt:message key='Tax Code'/></th> 
                                                        <th class="text-center" colspan="2"><fmt:message key='Tax'/></th> 
                                                        <th class="text-center" colspan="2"> <fmt:message key='TDS'/></th> 
                                                       <!-- <th class="text-center" rowspan="2"> <fmt:message key='GST TDS'/></th> -->
                                                        <%  if (UserType.equals("Emp")) {%>       <th class="text-center" rowspan="2"><fmt:message key='Retention Doc'/></th> <%}%> 
                                                        <th class="text-center" rowspan="2"><fmt:message key='Retention Amt'/></th>  
                                                    </tr>
                                                    <tr class="success">
                                                        <th class="text-center">IGST</th>
                                                        <th class="text-center">SGST</th>
                                                        <th class="text-center">IT TDS</th>
                                                        <th class="text-center">GST TDS</th>        
                                                    </tr>
                                                    <tbody>
                                                        <tr class="info" >
                                                            <td width="10%" class="text-center">Material (Within State)</td>
                                                            <%  if (UserType.equals("Emp")) {%> <td width="10%" class="text-center"><%=MATERIAL_WS_PARK_DOC%></td><%}%> 
                                                            <td width="10%" class="text-center"><%=MAT_DOC_DATE%></td>

                                                            <td width="10%" class="text-center"><%=MAT_WS_PARK_AMT%></td>

                                                            <%if (MATERIAL_WS_PARK_DOC != null && !(MATERIAL_WS_PARK_DOC.equals("NA")) && !(MATERIAL_WS_PARK_DOC.equals("Pending"))) {%>
                                                            <td width="10%" class="text-center"><%=TAX_CODE%></td> 
                                                            <%} else {%> 
                                                            <td width="10%" class="text-center"> </td> 
                                                            <%}%>
                                                        <!-- <td width="10%" class="text-center"><%=WTAX_AMT%></td> -->
                                                            <td width="10%" class="text-center"><%=MIGST_TX%></td>
                                                            <td width="10%" class="text-center"><%=MSGST_TX%></td>
                                                            <td width="10%" class="text-center"><%=WIT_TDS%></td>
                                                            <td width="10%" class="text-center"><%=WGST_TDS%></td> 
                                                            <%  if (UserType.equals("Emp")) {%>    <td width="10%" class="text-center"><%=WRPST%></td> <%}%> 
                                                            <td width="10%" class="text-center"><%=WRET_AMT%></td> 

                                                        </tr>
                                                        <tr class="info" >
                                                            <td width="10%" class="text-center">Material (Outside State)</td>
                                                            <%  if (UserType.equals("Emp")) {%>  <td width="10%" class="text-center"><%=MAT_OS_PARK_DOC_NO%></td><%}%> 
                                                            <td width="20%" class="text-center"><%=MAT_OS_DOC_DATE%></td>
                                                            <td width="10%" class="text-center"><%=MAT_OS_PARK_AMT%></td>
                                                            <%if (MAT_OS_PARK_DOC_NO != null && !(MAT_OS_PARK_DOC_NO.equals("NA")) && !(MAT_OS_PARK_DOC_NO.equals("Pending"))) {%>
                                                            <td width="10%" class="text-center"><%=TAX_CODE%></td> 
                                                            <%} else {%> 
                                                            <td width="10%" class="text-center"> </td> 
                                                            <%}%> 
                                                         <!-- <td width="10%" class="text-center"><%=OTAX_AMT%></td>--> 
                                                            <td width="10%" class="text-center"><%=OIGST_TX%></td>
                                                            <td width="10%" class="text-center"><%=OSGST_TX%></td>
                                                            <td width="10%" class="text-center"><%=OIT_TDS%></td> 
                                                            <td width="10%" class="text-center"><%=OGST_TDS%></td> 
                                                            <%  if (UserType.equals("Emp")) {%>   <td width="10%" class="text-center"><%=ORPST%></td> <%}%> 
                                                            <td width="10%" class="text-center"><%=ORET_AMT%></td> 

                                                        </tr>
                                                        <tr class="info" >
                                                            <td width="10%" class="text-center">Centages</td>
                                                            <%  if (UserType.equals("Emp")) {%>   <td width="10%" class="text-center"><%=CENTAGES_PARK_DOC%></td><%}%> 
                                                            <td width="10%" class="text-center"><%=CEN_DOC_DATE%></td>
                                                            <td width="10%" class="text-center"><%=CEN_PARK_AMT%></td>
                                                            <%if (CENTAGES_PARK_DOC != null && !(CENTAGES_PARK_DOC.equals("NA")) && !(CENTAGES_PARK_DOC.equals("Pending"))) {%>
                                                            <td width="10%" class="text-center"><%=TAX_CODE%></td> 
                                                            <%} else {%> 
                                                            <td width="10%" class="text-center"> </td> 
                                                            <%}%>
                                                         <!-- <td width="10%" class="text-center"><%=CTAX_AMT%></td> -->
                                                            <td width="10%" class="text-center"><%=CIGST_TX%></td>
                                                            <td width="10%" class="text-center"><%=CSGST_TX%></td>
                                                            <td width="10%" class="text-center"><%=CIT_TDS%></td> 
                                                            <td width="10%" class="text-center"><%=CGST_TDS%></td> 
                                                            <%  if (UserType.equals("Emp")) {%>    <td width="10%" class="text-center"><%=CRPST%></td> <%}%> 
                                                            <td width="10%" class="text-center"><%=CRET_AMT%></td> 

                                                        </tr>
                                                        <tr class="info" >
                                                            <td width="10%" class="text-center">Civil</td>
                                                            <%  if (UserType.equals("Emp")) {%>   <td width="10%" class="text-center"><%=CIVIL_PARK_DOC%></td><%}%> 
                                                            <td width="10%" class="text-center"><%=CIVIL_DOC_DATE%></td>
                                                            <td width="10%" class="text-center"><%=CIVIL_PARK_AMT%></td>
                                                            <%if (CIVIL_PARK_DOC != null && !(CIVIL_PARK_DOC.equals("NA")) && !(CIVIL_PARK_DOC.equals("Pending"))) {%>
                                                            <td width="10%" class="text-center"><%=TAX_CODE%></td> 
                                                            <%} else {%> 
                                                            <td width="10%" class="text-center"> </td> 
                                                            <%}%> 
                                                        <!--  <td width="10%" class="text-center"><%=STAX_AMT%></td> -->
                                                            <td width="10%" class="text-center"><%=SIGST_TX%></td>
                                                            <td width="10%" class="text-center"><%=SSGST_TX%></td>
                                                            <td width="10%" class="text-center"><%=SIT_TDS%></td> 
                                                            <td width="10%" class="text-center"><%=SGST_TDS%></td> 
                                                            <%  if (UserType.equals("Emp")) {%>     <td width="10%" class="text-center"><%=SRPST%></td> <%}%> 
                                                            <td width="10%" class="text-center"><%=SRET_AMT%></td> 


                                                        </tr>
                                                        <tr class="info" >
                                                            <td width="10%" class="text-center">Other Charges</td>
                                                            <%  if (UserType.equals("Emp")) {%>     <td width="10%" class="text-center"><%=MAT_OTH_PARK_DOC_NO%></td><%}%> 
                                                            <td width="10%" class="text-center"><%=MAT_OTH_DOC_DATE%></td>
                                                            <td width="10%" class="text-center"><%=MAT_OTH_PARK_AMT%></td>
                                                            <%if (MAT_OTH_PARK_DOC_NO != null && !(MAT_OTH_PARK_DOC_NO.equals("NA")) && !(MAT_OTH_PARK_DOC_NO.equals("Pending"))) {%>
                                                            <td width="10%" class="text-center"><%=TAX_CODE%></td> 
                                                            <%} else {%> 
                                                            <td width="10%" class="text-center"> </td> 
                                                            <%}%>
                                                       <!--   <td width="10%" class="text-center"><%=OTTAX_AMT%></td> -->
                                                            <td width="10%" class="text-center"><%=OTIGST_TX%></td>
                                                            <td width="10%" class="text-center"><%=OTSGST_TX%></td>
                                                            <td width="10%" class="text-center"><%=OTIT_TDS%></td>
                                                            <td width="10%" class="text-center"><%=OTGST_TDS%></td> 
                                                            <%  if (UserType.equals("Emp")) {%>     <td width="10%" class="text-center"><%=OTRPST%></td> <%}%> 
                                                            <td width="10%" class="text-center"><%=OTRET_AMT%></td> 
                                                        </tr>

                                                        <tr class="info" align="center">
                                                            <%     Double clearing_amt_total = 0.0;
                                                                for (String f : clearing_amt.values()) {
                                                                    if (f != null && !(f.equals(""))) {
                                                                        clearing_amt_total += Double.parseDouble(f);
                                                                    }
                                                                }
                                                                String str = String.valueOf(clearing_amt_total);
                                                                String clearing_amt_total_str = ApplicationUtils.formatAmount(Double.valueOf(str));
                                                            %>


                                                            <td width="20%" class="text-center">Invoice Clearing Amount</td>
                                                            <td colspan="1" class="text-center"><%=clearing_amt_total_str%></td>
                                                            <td colspan="11" >
                                                        </tr>
                                                    </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <%}%>
                            <fieldset class="fldst_border">
                                <legend>Payment/Adjustment Details:</legend> 
                                <div class="row">
                                    <div class="col-lg-12 col-md-12">
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr class="success">                                                                                       
                                                        <th class="text-center"> <fmt:message key='Clearing Doc Type'/></th> 
                                                        <%  if (UserType.equals("Emp")) {%>           <th class="text-center"> <fmt:message key='Clearing Doc'/></th> <%}%> 
                                                        <th class="text-center"><fmt:message key='Date'/></th> 
                                                        <th class="text-center"><fmt:message key='Amount'/></th> 
                                                        <th class="text-center"><fmt:message key='Status'/></th> 

                                                    </tr>
                                                    <tbody>
                                                        <%if (!INVOICE_TYPE.equalsIgnoreCase("Retention Claim Charges")) {%>

                                                        <tr class="info" >
                                                            <td width="20%" class="text-center">Material (Within State)</td>
                                                            <%  if (UserType.equals("Emp")) {%>        <td width="20%" class="text-center"><%=MAT_WS_CLEARING_DOC_NO%></td><%}%> 
                                                            <td width="20%" class="text-center"><%=MAT_WS_AC_DOC_DATE%></td>
                                                            <td width="20%" class="text-center"><%=MAT_WS_CLEARING_AMT%></td>
                                                            <%if (MAT_WS_CLEARING_DOC_NO != null && !(MAT_WS_CLEARING_DOC_NO.equals("NA")) && !(MAT_WS_CLEARING_DOC_NO.equals("")) && !(MAT_WS_CLEARING_DOC_NO.equals("Pending"))) {
                                                        if (MAT_WS_CLEARING_DOC_NO.startsWith("17")) {%>
                                                            <td width="20%" class="text-center">Paid</td> 
                                                            <%} else {
                                                            %>
                                                            <td width="20%" class="text-center">Amount is Adjusted</td> 
                                                            <%
                                                   }
                                               } else {%> 
                                                            <td width="20%" class="text-center"> </td> 
                                                            <%}%> 

                                                        </tr>
                                                        <tr class="info" >
                                                            <td width="20%" class="text-center">Material (Outside State)</td>
                                                            <%  if (UserType.equals("Emp")) {%>      <td width="20%" class="text-center"><%=MAT_OS_CLEARING_DOC_NO%></td><%}%> 
                                                            <td width="20%" class="text-center"><%=MAT_OS_AC_DOC_DATE%></td>
                                                            <td width="20%" class="text-center"><%=MAT_OS_CLEARING_AMT%></td>
                                                            <%if (MAT_OS_CLEARING_DOC_NO != null && !(MAT_OS_CLEARING_DOC_NO.equals("NA")) && !(MAT_OS_CLEARING_DOC_NO.equals("")) && !(MAT_OS_CLEARING_DOC_NO.equals("Pending"))) {
                                             if (MAT_OS_CLEARING_DOC_NO.startsWith("17")) {%>
                                                            <td width="20%" class="text-center">Paid</td> 
                                                            <%} else {
                                                            %>
                                                            <td width="20%" class="text-center">Amount is Adjusted</td> 
                                                            <%
                                                   }
                                               } else {%> 
                                                            <td width="20%" class="text-center"> </td> 
                                                            <%}%> 

                                                        </tr>
                                                        <tr class="info" >
                                                            <td width="20%" class="text-center">Centages</td>
                                                            <%  if (UserType.equals("Emp")) {%>        <td width="20%" class="text-center"><%=CEN_CLEARING_DOC_NO%></td><%}%> 
                                                            <td width="20%" class="text-center"><%=CEN_AC_DOC_DATE%></td>
                                                            <td width="20%" class="text-center"><%=CEN_CLEARING_AMT%></td>
                                                            <%if (CEN_CLEARING_DOC_NO != null && !(CEN_CLEARING_DOC_NO.equals("NA")) && !(CEN_CLEARING_DOC_NO.equals("")) && !(CEN_CLEARING_DOC_NO.equals("Pending"))) {
                                                  if (CEN_CLEARING_DOC_NO.startsWith("17")) {%>
                                                            <td width="20%" class="text-center">Paid</td> 
                                                            <%} else {
                                                            %>
                                                            <td width="20%" class="text-center">Amount is Adjusted</td> 
                                                            <%
                                                   }
                                               } else {%> 
                                                            <td width="20%" class="text-center"> </td> 
                                                            <%}%> 

                                                        </tr>
                                                        <tr class="info" >
                                                            <td width="20%" class="text-center">Civil</td>
                                                            <%  if (UserType.equals("Emp")) {%>        <td width="20%" class="text-center"><%=CIVIL_CLEARING_DOC_NO%></td><%}%> 
                                                            <td width="20%" class="text-center"><%=CIVIL_AC_DOC_DATE%></td>
                                                            <td width="20%" class="text-center"><%=CIVIL_CLEARING_AMT%></td>
                                                            <%if (CIVIL_CLEARING_DOC_NO != null && !(CIVIL_CLEARING_DOC_NO.equals("NA")) && !(CIVIL_CLEARING_DOC_NO.equals("")) && !(CIVIL_CLEARING_DOC_NO.equals("Pending"))) {

                                                     if (CIVIL_CLEARING_DOC_NO.startsWith("17")) {%>
                                                            <td width="20%" class="text-center">Paid</td> 
                                                            <%} else {
                                                            %>
                                                            <td width="20%" class="text-center">Amount is Adjusted</td> 
                                                            <%
                                                   }

                                               } else {%> 
                                                            <td width="20%" class="text-center"> </td> 
                                                            <%}%> 

                                                        </tr>
                                                        <tr class="info" >
                                                            <td width="20%" class="text-center">Other Charges</td>
                                                            <%  if (UserType.equals("Emp")) {%>     <td width="20%" class="text-center"><%=MAT_OTH_CLEARING_DOC_NO%></td> <%}%> 
                                                            <td width="20%" class="text-center"><%=MAT_OTH_AC_DOC_DATE%></td>
                                                            <td width="20%" class="text-center"><%=MAT_OTH_CLEARING_AMT%></td>
                                                            <%if (MAT_OTH_CLEARING_DOC_NO != null && !(MAT_OTH_CLEARING_DOC_NO.equals("NA")) && !(MAT_OTH_CLEARING_DOC_NO.equals("")) && !(MAT_OTH_CLEARING_DOC_NO.equals("Pending"))) {
                                                     if (MAT_OTH_CLEARING_DOC_NO.startsWith("17")) {%>
                                                            <td width="20%" class="text-center">Paid</td> 
                                                            <%} else {
                                                            %>
                                                            <td width="20%" class="text-center">Amount is Adjusted</td> 
                                                            <%
                                                   }
                                               } else {%> 
                                                            <td width="20%" class="text-center"> </td> 
                                                            <%}%>  </tr>
                                                            <%} else {%>
                                                        <td width="20%" class="text-center">Retention Claim Charges</td> 
                                                        <td width="20%" class="text-center"><%= VENDORInvDate%></td>
                                                        <%if(Status.equalsIgnoreCase("Pending For Payment") ){ %>
                                                            <td width="20%" class="text-center"><%= vendorRetentionBean.getInvoiceAmount() %></td>
                                                        <%}else if(Status.equalsIgnoreCase("Paid") ){
                                                                if((new BigDecimal(vendorRetentionBean.getInvoiceAmount()).compareTo(new BigDecimal(vendorRetentionBean.getWRET_AMT())))<0){%>
                                                                    <td width="20%" class="text-center"><%= vendorRetentionBean.getInvoiceAmount() %></td>
                                                                <%}else{%>
                                                                <td width="20%" class="text-center"><%= vendorRetentionBean.getWRET_AMT() %></td>
                                                        <%}}%>
                                                        <td width="20%" class="text-center"><%= Status%></td>
                                                        <%}%>

                                                    </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>





                            <div class="row">                                      
                                <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5">
                                    <% if (UserType.equals("Vendor")) {%>
                                    <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST)%>" class="btn btn-danger"><fmt:message key='Back'/></a>
                                    <% } else if (UserType.equals("Emp")) {%>
                                    <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_AUTH_PO_LIST)%>" class="btn btn-danger"><fmt:message key='Back'/></a>
                                    <% }%>

                                </span>





                            </div>



                            <hr/>

                            <!-- /. PAGE inner  -->
                        </div>
                        <!-- /. PAGE wrapper  -->
                    </div>
                    <!-- /.  wrapper  -->
                </div>
            </div>
        </div>
        <%@include file="nav_emp_footer.jsp" %>

        <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
        <!-- JQUERY SCRIPTS -->

        <!-- BOOTSTRAP SCRIPTS -->
        <script src="assets/js/bootstrap.min.js"></script>
        <!-- CUSTOM SCRIPTS -->
        <script src="assets/js/custom.js"></script>

    </body>
</html>