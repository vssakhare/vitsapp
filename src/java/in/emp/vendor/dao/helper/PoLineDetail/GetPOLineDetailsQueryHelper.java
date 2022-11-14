/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.PoLineDetail;

/**
 *
 * @author Pooja Jadhav
 */
import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;

import in.emp.vendor.bean.VendorPrezData;

import in.emp.vendor.bean.ClearingDocDetails;
import in.emp.vendor.bean.PoLineStatusBean;
import in.emp.vendor.dao.helper.formHelper.GetVendorInputFormQueryHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class GetPOLineDetailsQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetVendorInputFormQueryHelper.class);
    private PoLineStatusBean poLineStatusBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public GetPOLineDetailsQueryHelper(PoLineStatusBean poLineStatusBeanObj) {
        this.poLineStatusBeanObj = poLineStatusBeanObj;
    }

    public GetPOLineDetailsQueryHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;

    }

    public Object getDataObject(ResultSet results) throws Exception {
        PoLineStatusBean poLineStatusBeanObj = new PoLineStatusBean();

        try {

            poLineStatusBeanObj.setContract_Document(results.getString("CONTRACT_DOCUMENT"));
            poLineStatusBeanObj.setShort_Text(results.getString("SHORT_TEXT"));
            poLineStatusBeanObj.setPlant(results.getString("PLANT"));
            poLineStatusBeanObj.setPLANT_DESC(results.getString("PLANT_DESC"));
            poLineStatusBeanObj.setPoLineId(results.getString("PO_LINE_ID"));
            poLineStatusBeanObj.setAPPL_ID(results.getString("APPL_ID"));
            poLineStatusBeanObj.setPurchasing_group(results.getString("PURCHASING_GROUP"));
            poLineStatusBeanObj.setPurchasing_desc(results.getString("PURCHASING_DESC"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPOLineDetailsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return poLineStatusBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
           

                sql.append(" SELECT PLS.PO_LINE_ID, PLS.CONTRACT_DOCUMENT, PLS.SHORT_TEXT,PLS.PLANT_DESC, PLS.PLANT, PLID.APPL_ID,PLS.PURCHASING_GROUP,PURCHASING_DESC ");
                sql.append(" FROM PO_LINE_STATUS_AUTH PLS ");
                sql.append(" LEFT OUTER JOIN PO_LINE_INV_DETAILS_AUTH PLID");
                sql.append(" ON PLS.CONTRACT_DOCUMENT = PLID.CONTRACT_DOCUMENT  ");
                sql.append(" AND PLS.PO_LINE_ID = PLID.PO_LINE_ID ");

                sql.append(" AND PLID.STATUS_CD = 'A' AND PLID.APPL_ID=? ");
                sql.append(" WHERE PLS.CONTRACT_DOCUMENT = ? ");

           
         

           // sql.append(" SELECT CONTRACT_DOCUMENT, ");
            //sql.append(" LISTAGG(SHORT_TEXT, ', ') WITHIN GROUP (ORDER BY CONTRACT_DOCUMENT, PLANT) SHORT_TEXT, ");
            //sql.append(" PLANT ");           
            // sql.append(" FROM po_line_status  ");
            // sql.append(" WHERE CONTRACT_DOCUMENT = ? ");
            // sql.append(" GROUP BY CONTRACT_DOCUMENT, PLANT ");
            logger.log(Level.INFO, "GetPOLineDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
           // System.out.println(sql.toString());

            statement = connection.prepareStatement(sql.toString());

            statement.setString(1, poLineStatusBeanObj.getAPPL_ID());
          statement.setString(2, poLineStatusBeanObj.getContract_Document());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetPOLineDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
