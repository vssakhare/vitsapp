/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.scheduler;

import in.emp.common.ApplicationConstants;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.util.ApplicationUtils;
import static in.emp.util.ApplicationUtils.parseStringToDouble;
import in.emp.util.TaxAmountDisplayFromSap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.sql.Connection;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.manager.VendorManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.CallableStatement;
/**
 *
 * @author Pooja Jadhav
 */
public class UpdateTaxDetailsZhrtLegalFee {

    private static Logger logger = Logger.getLogger(UpdateTaxDetailsZhrtLegalFee.class);
    private static Connection conn = null;

    public static void UpdateTaxDetails() throws Exception {

        VendorDelegate vendorMgrObj = new VendorManager();
        try {
            logger.log(Level.INFO, "Legal Vendor sms Scheduler :: run() :: method called .. ");
            System.out.println("\nLegal Vendor sms Scheduler :: run() :: method called ");
               List<LegalInvoiceInputBean> legalInvoiceInputList = vendorMgrObj.getZhrtLegalFeeReport();
               vendorMgrObj.saveZhrtLegalFeeTaxDetails(legalInvoiceInputList);
               vendorMgrObj.updateZhrtLegalFeeReportProcedure();
            
              } catch (Exception ex) {
            logger.log(Level.ERROR, "UpdateTaxDetailsZhrtLegalFee :: run() :: Exception .. " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}               