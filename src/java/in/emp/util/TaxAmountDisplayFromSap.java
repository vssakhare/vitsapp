/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pooja jadhav
 */

package in.emp.util;
import in.emp.legal.bean.LegalInvoiceInputBean;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import functions.rfc.sap.document.sap_com.Z_FI_DOCUMENT_READStub.FI_DOCUMENT_READ;
import functions.rfc.sap.document.sap_com.Z_FI_DOCUMENT_READStub;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.axis2.transport.http.HttpTransportProperties.Authenticator;


public class TaxAmountDisplayFromSap {

    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static LegalInvoiceInputBean   consumeSapWebservice(LegalInvoiceInputBean legalInvoiceInputBean) throws Exception {
       
        try {
            Z_FI_DOCUMENT_READStub stub = new Z_FI_DOCUMENT_READStub();
            FI_DOCUMENT_READ req = new FI_DOCUMENT_READ();
            createRecovery(legalInvoiceInputBean, req);
            stub = setAuthenticator(stub);
            Z_FI_DOCUMENT_READStub.FI_DOCUMENT_READResponse responseObj = null;
            try {
                responseObj =  stub.fI_DOCUMENT_READ(req);
               } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Z_FI_DOCUMENT_READStub.TABLE_OF_BKPF table_Of_ZFI_BKPF_RETURN_MSG = responseObj.getT_BKPF();
            Z_FI_DOCUMENT_READStub.TABLE_OF_BSEG table_Of_ZFI_BSEG_RETURN_MSG = responseObj.getT_BSEG();
            System.out.println(table_Of_ZFI_BSEG_RETURN_MSG.getItem());
            if (table_Of_ZFI_BSEG_RETURN_MSG != null) {
               
                Z_FI_DOCUMENT_READStub.BSEG[] bseg_arr  = table_Of_ZFI_BSEG_RETURN_MSG.getItem();
                getResponseFromWebService(bseg_arr, responseObj,legalInvoiceInputBean);
            }
            
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
             throw new Exception("Exception Occurred During Web Service Call");
        }
return legalInvoiceInputBean;
    }

 private static LegalInvoiceInputBean getResponseFromWebService(Z_FI_DOCUMENT_READStub.BSEG[] bseg_arr ,
            Z_FI_DOCUMENT_READStub.FI_DOCUMENT_READResponse responseObj,LegalInvoiceInputBean legalInvoiceInputBean)throws Exception {
        if (bseg_arr != null) {
            //System.out.println(bseg_arr.length);
            for(int i=0;i<bseg_arr.length;i++){
            Z_FI_DOCUMENT_READStub.BSEG bseg = bseg_arr[i];
            Z_FI_DOCUMENT_READStub.Char10 sapGlCode = bseg.getHKONT();
              System.out.println("sapGlCode::::::::::" + sapGlCode);
            if(sapGlCode.toString().equals("0010902205"))
            {   Z_FI_DOCUMENT_READStub.Curr132 sapTdsAmount = bseg.getDMBTR();
                legalInvoiceInputBean.setTdsAmount(sapTdsAmount.getCurr132().toString());
            }else if(sapGlCode.toString().equals("0010902272")){
                Z_FI_DOCUMENT_READStub.Curr132 sapCgstAmount = bseg.getDMBTR();
                legalInvoiceInputBean.setCgstAmount(sapCgstAmount.getCurr132().toString());
            }else if(sapGlCode.toString().equals("0010902273")){
                Z_FI_DOCUMENT_READStub.Curr132 sapSgstAmount = bseg.getDMBTR();
                legalInvoiceInputBean.setSgstAmount(sapSgstAmount.getCurr132().toString());
            }else if(sapGlCode.toString().equals("0010902276")){
                Z_FI_DOCUMENT_READStub.Curr132 sapCgstTdsAmount = bseg.getDMBTR();
                legalInvoiceInputBean.setCgstTdsAmount(sapCgstTdsAmount.getCurr132().toString());
            }else if(sapGlCode.toString().equals("0010902277")){
                Z_FI_DOCUMENT_READStub.Curr132 sapSgstTdsAmount = bseg.getDMBTR();
                legalInvoiceInputBean.setSgstTdsAmount(sapSgstTdsAmount.getCurr132().toString()); 
            }else if(sapGlCode.toString().equals("0010902274")){
                 Z_FI_DOCUMENT_READStub.Curr132 sapIgstAmount = bseg.getDMBTR();
                legalInvoiceInputBean.setIgstAmount(sapIgstAmount.getCurr132().toString()); 
            }else if(sapGlCode.toString().equals("0010902278")){
                 Z_FI_DOCUMENT_READStub.Curr132 sapIgstTdsAmount = bseg.getDMBTR();
                legalInvoiceInputBean.setIgstTdsAmount(sapIgstTdsAmount.getCurr132().toString()); 
            }
        }
            System.out.println("TdsAmount::::::::::" + legalInvoiceInputBean.getTdsAmount());
            System.out.println("CgstAmount::::::::::" + legalInvoiceInputBean.getCgstAmount());
            System.out.println("SgstAmount::::::::::" + legalInvoiceInputBean.getSgstAmount());
            System.out.println("CgstTdsAmount::::::::::" + legalInvoiceInputBean.getCgstTdsAmount());
            System.out.println("SgstTdsAmount::::::::::" + legalInvoiceInputBean.getSgstTdsAmount());
            System.out.println("IgstAmount::::::::::" + legalInvoiceInputBean.getIgstAmount());
            System.out.println("IgstTdsAmount::::::::::" + legalInvoiceInputBean.getIgstTdsAmount());
                  
          // Check for response
           
        }
return legalInvoiceInputBean;
    }
   private static void createRecovery(LegalInvoiceInputBean legalInvoiceInputBean, FI_DOCUMENT_READ req) throws Exception{
     Z_FI_DOCUMENT_READStub.Char10 docNoComm = new Z_FI_DOCUMENT_READStub.Char10();
        docNoComm.setChar10(legalInvoiceInputBean.getLiabilityDocNo());
        //docNoComm.setChar10("1600026100");
        req.setI_BELNR(docNoComm);
        
        Z_FI_DOCUMENT_READStub.Char4 payTermComm = new Z_FI_DOCUMENT_READStub.Char4();
        payTermComm.setChar4("1000");
        req.setI_BUKRS(payTermComm);
        
        Z_FI_DOCUMENT_READStub.Numeric4 fiscalYear = new Z_FI_DOCUMENT_READStub.Numeric4();
        fiscalYear.setNumeric4(legalInvoiceInputBean.getFiscalYear());
        //fiscalYear.setNumeric4("2021");
        req.setI_GJAHR(fiscalYear);
         System.out.println("zfi_doc_read_input.getI_BELNR()"+ req.getI_BELNR());
         System.out.println("zfi_doc_read_input.getI_BUKRS()"+  req.getI_BUKRS());
         System.out.println("zfi_doc_read_input.getI_GJAHR()"+  req.getI_GJAHR());
       }

   private static Z_FI_DOCUMENT_READStub setAuthenticator(Z_FI_DOCUMENT_READStub stub) throws Exception {
        System.out.println("SetAuthenticator");
        HttpTransportProperties.Authenticator basicAuthenticator = new HttpTransportProperties.Authenticator();
        List<String> authSchemes = new ArrayList<String>();
        authSchemes.add(Authenticator.BASIC);
        basicAuthenticator.setAuthSchemes(authSchemes);
        basicAuthenticator.setUsername("Webservice"); // Set your value
       basicAuthenticator.setPassword("SAP@4712$"); //PROD 
       // basicAuthenticator.setPassword("7654321"); //DEV
      
    //  basicAuthenticator.setUsername(occsSystemParameterDAOImpl.getSysParamValue(ApplicationUtils.PARAM_NAME_WEBSERVICE_USERNAME));
     // basicAuthenticator.setPassword(occsSystemParameterDAOImpl.getSysParamValue(ApplicationUtils.PARAM_NAME_WEBSERVICE_PASSWORD));
      
        basicAuthenticator.setPreemptiveAuthentication(true);
        stub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, basicAuthenticator);
        Options opts = stub._getServiceClient().getOptions();
        opts.setTimeOutInMilliSeconds(1800);
        opts.setProperty(HTTPConstants.HTTP_PROTOCOL_VERSION, HTTPConstants.HEADER_PROTOCOL_11);
        opts.setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, Boolean.FALSE);
        opts.setProperty(org.apache.axis2.Constants.Configuration.CHARACTER_SET_ENCODING, "UTF-16");
        stub._getServiceClient().setOptions(opts);
        return stub;
    }
}
