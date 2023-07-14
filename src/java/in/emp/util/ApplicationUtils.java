package in.emp.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.*;
import java.text.*;
import jakarta.servlet.http.HttpServletRequest  ;
import java.net.*;
import java.io.*;

import in.emp.common.ApplicationConstants;
import in.emp.common.AttributeData;
import in.emp.master.MasterDelegate;
import in.emp.search.bean.LocationBean;
import in.emp.search.bean.LocationTypeBean;
import in.emp.search.bean.SearchResultBean;
import in.emp.search.bean.DeviceTypeBean;
import in.emp.search.bean.InstallPointTypeBean;
import in.emp.system.manager.SystemManager;
import in.emp.system.SystemDelegate;
import in.emp.system.beans.SystemMessageData;
import in.emp.search.bean.SearchBean;


//-- logger Imports
import in.emp.master.bean.MasterBean;
import in.emp.master.dao.MasterDao;
import in.emp.master.dao.OracleMasterDao;
import in.emp.master.manager.MasterManager;
import in.emp.search.bean.ModemTechTypeBean;
import in.emp.security.bean.FeederDtcBean;
import in.emp.security.bean.SecUserBean;
import in.emp.security.bean.SecFunctionBean;
import in.emp.system.SecurityInterface;
import in.emp.system.dao.SecurityDTO;
import in.emp.system.dao.SecurityImpl;
import in.emp.user.bean.BuBean;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class	ApplicationUtils
 *
 * @author
 * @version LastModifedDate LastModfiedBy
 */
public class ApplicationUtils {

    private static Logger logger = Logger.getLogger(ApplicationUtils.class);
    private static Context initContext = null;
    private static Context envContext = null;
    private static DataSource dataSource = null;
    private static Connection conn = null;
    private static final int BLOCK_SIZE = 24680;
    private static String DriveLetter = "D";
    private static String ipAddress = "";

    static {


        try {
            System.out.println("Hello");
            initContext = new InitialContext();
            envContext = (Context) initContext.lookup(ApplicationConstants.INIT_CONTEXT);
            dataSource = (DataSource) envContext.lookup(ApplicationConstants.CONN_POOL_NAME);
            ///For Shareable Connection
            //dataSource = (OracleDataSource) envContext.lookup(ApplicationConstants.CONN_POOL_NAME);
            if (dataSource == null) {
                System.out.println("DS look up :-(");
            } else {
                System.out.println("ApplicationConstants.CONN_POOL_NAME");
                System.out.println("DS look up :-)");
            }

        } catch (Exception ex) {
            dataSource = null;
            //ex.printStackTrace();
        }
    }

    /**
     * This public static API use to get the connection from database.
     *
     * @param void
     * @return Connection, java.sql.Connection reference of a database
     * @throws Exception
     */
    public static String encodeURIComponent(String param) throws Exception {
        String ret = "";
        try {
            ret = URLEncoder.encode(param, "UTF-8").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\+", "%20").replaceAll("\\%27", "'").replaceAll("\\%21", "!").replaceAll("\\%7E", "~");
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return ret;

    }
    public static String getGranularAcl(HttpServletRequest request) {
        String name = "";
        String value = "";
        String granularAcl = "";
        Enumeration en = request.getHeaderNames();
        try {

            while (en != null && en.hasMoreElements()) {
                name = (String) en.nextElement();
                value = request.getHeader(name);
                if (name.equalsIgnoreCase(ApplicationConstants.GRANULAR_ACL_SM)) {
                    granularAcl = value;
                }
            }

            for (int i = 0; i < 10; i++) {
                Enumeration en1 = request.getHeaderNames();
                while (en1 != null && en1.hasMoreElements()) {
                    name = (String) en1.nextElement();
                    value = request.getHeader(name);
                    if (name.equalsIgnoreCase(ApplicationConstants.GRANULAR_ACL_SM + i)) {
                        granularAcl += "#" + value;
                    }
                }
            }

        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return granularAcl;
    }

    public static String getLocationID(HttpServletRequest request) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        String LocID = "";
        String office_cd = "";
        String office_type_id = "";
        String parrent_office_id = "";
        String uname = "";
        HttpSession session = request.getSession(true);
        try {
            conn = getConnection();
            StringBuffer sql = new StringBuffer();
            office_cd = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);
            //office_cd = (String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION);
            office_type_id = (String) session.getAttribute(ApplicationConstants.OFFICE_TYPE_SESSION);

            parrent_office_id = (String) session.getAttribute(ApplicationConstants.PARENT_OFFICE_BILLING_DB_CODE_SESSION);
            uname = (String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION);

            // System.out.println("rushi_officecd:-"+office_cd+"officetype:"+office_type_id+"paraentofficetype:"+parrent_office_id+"uname:"+uname);
            //System.out.println("rushi2:-"+office_type_id);
            //System.out.println("rushi3:-"+parrent_office_id);
            if ((office_cd.equals("0") || office_cd.equals("000")) && office_type_id.equals("3")) {
                //THIS PROVISION IS FOR TESTING DIVISION LEVEL EMPLOYEE
                // System.out.println("inside if rushi");
                office_type_id = "2";
                // office_cd=parrent_office_id;
                session.setAttribute(ApplicationConstants.OFFICE_TYPE_SESSION, office_type_id);
                session.setAttribute(ApplicationConstants.OFFICE_LOCATION_ID_SESSION, parrent_office_id);
                office_cd = parrent_office_id;
            }




            sql.append(" SELECT LOCATION_ID FROM LOCATION WHERE LOCATION_CD=LPAD('" + office_cd + "',3,'0') AND LOCATION_TYPE_ID='" + office_type_id + "' ");
            // sql.append(" SELECT LOCATION_ID FROM LOCATION WHERE LOCATION_CD=LPAD('261',3,'0') AND LOCATION_TYPE_ID=4 ");
            logger.log(Level.INFO, "ApplicationUtils :: getReportHeaders() :: SQL :: " + sql.toString());

            logger.log(Level.INFO, "ApplicationUtils :: rushi :: " + office_cd);
            logger.log(Level.INFO, "ApplicationUtils :: rushi2 :: " + office_type_id);
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            if (rs.next()) {
                LocID = rs.getString("LOCATION_ID");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationID() :: Exception  :: " + ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }

        return LocID;
    }

    public static List getBuList(HttpSession session) throws Exception {
        List buList = new LinkedList();
        try {
            logger.debug("Retrieving bu information from Session Object...");
            String billingUnit = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);
            SecurityInterface securityInterface;
            boolean isBuFound = false;

            if (ApplicationUtils.validateString(billingUnit)) {
                if (Integer.parseInt(billingUnit) > 0) {
                    isBuFound = false;
                } else {
                    isBuFound = true;
                }
            } else {
                isBuFound = true;
            }

            logger.debug("Billing code found ==>" + billingUnit + " :: isBuFound ==>" + isBuFound);
            String officeType = (String) session.getAttribute(ApplicationConstants.OFFICE_TYPE_SESSION);
            logger.debug("officeType ==>" + officeType);

            if (ApplicationUtils.validateString(officeType)) {

                long officeTyp = Long.parseLong(officeType);
                logger.debug("officeTyp ==>" + officeTyp);
                securityInterface = new SecurityImpl();


                // Case 0: User belongs to a DIV/CIR/ZON i.e. office type = "DIV" or "CIR" or "ZON"
                // office type = "ZON"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.ZONAL_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.HEAD_OFFICE_TYPE_ID) {
                    String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);
                    logger.debug("Case: 0: billingDbCode ==>" + billingDbCode);

                    SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(billingDbCode), ApplicationConstants.HEAD_OFFICE_TYPE_ID);
                    if (billingDbCode != null) {
                        buList = securityInterface.getOfficeLocBuList(securityDTO);
                    }
                }

                // Case 1: User belongs to a DIV/CIR/ZON i.e. office type = "DIV" or "CIR" or "ZON"
                // office type = "ZON"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.ZONAL_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.ZONE_TYPE_ID) {
                    String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);
                    logger.debug("Case: 1: billingDbCode ==>" + billingDbCode);

                    SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(billingDbCode), ApplicationConstants.ZONE_TYPE_ID);
                    if (billingDbCode != null) {
                        buList = securityInterface.getOfficeLocBuList(securityDTO);
                    }
                }

                // Case 2: User belongs to a DIV/CIR/ZON i.e. office type = "DIV" or "CIR" or "ZON"
                // office type = "CIR"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.CIRCLE_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.CIRCLE_TYPE_ID) {
                    String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);
                    logger.debug("Case: 2: billingDbCode ==>" + billingDbCode);

                    SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(billingDbCode), ApplicationConstants.CIRCLE_TYPE_ID);
                    if (billingDbCode != null) {
                        buList = securityInterface.getOfficeLocBuList(securityDTO);
                    }
                }

                // Case 3: User belongs to a DIV/CIR/ZON i.e. office type = "DIV" or "CIR" or "ZON"
                // office type = "DIV"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.DIVISION_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.DIVISION_TYPE_ID) {
                    String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);
                    logger.debug("Case: 3: billingDbCode ==>" + billingDbCode);

                    SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(billingDbCode), ApplicationConstants.DIVISION_TYPE_ID);
                    if (billingDbCode != null) {
                        buList = securityInterface.getOfficeLocBuList(securityDTO);
                    }
                }

                // Case 4: User belongs to a SUB i.e. office type = "SUB"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.SUBDIVISION_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.SUBDIVISION_TYPE_ID) {
                    String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);
                    logger.debug("Case: 4: billingDbCode ==>" + billingDbCode);

                    SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(billingDbCode), ApplicationConstants.SUBDIVISION_TYPE_ID);
                    if (billingDbCode != null) {
                        buList = securityInterface.getOfficeLocBuList(securityDTO);
                    }
                }

                // Case 5: User belongs to SEC i.e. Office Type = "SEC"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.SECTION_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.SECTION_TYPE_ID) {
                    String officeCode = (String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION);
                    logger.debug("Case: 5: officeCode ==>" + officeCode);

                    SecurityDTO securityDTO = new SecurityDTO(Long.parseLong(officeCode), 0, 0);
                    if (officeCode != null) {
                        buList = securityInterface.getOfficeCodeBuList(securityDTO);
                    }
                }

                // Case 6: User belongs to SS i.e. Office Type = "SS"
                // if(officeType.equalsIgnoreCase(ApplicationConstants.SUBSTATION_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.SUBSTATION_TYPE_ID) {
                    String parentOfficeCode = (String) session.getAttribute(ApplicationConstants.PARENT_OFFICE_CODE_SESSION);
                    logger.debug("Case: 6: parentOfficeCode ==>" + parentOfficeCode);

                    SecurityDTO securityDTO = new SecurityDTO(Long.parseLong(parentOfficeCode), 0, 0);
                    if (parentOfficeCode != null) {
                        buList = securityInterface.getOfficeCodeBuList(securityDTO);
                    }
                }


                // Case 7: User belongs to a "TEST" i.e. testing division of MSEDCL i.e. office type = "TEST"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.TESTING_OFFICE_TYPE)){
//                                        if (officeTyp == ApplicationConstants.CIRCLE_TYPE_ID) {
//                                                String parentBillingDbCode = (String) session.getAttribute(ApplicationConstants.PARENT_OFFICE_BILLING_DB_CODE_SESSION);
//                                                logger.debug("Case: 5: parentBillingDbCode ==>" + parentBillingDbCode);
//
//                                                SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(parentBillingDbCode), ApplicationConstants.CIRCLE_TYPE_ID);
//                                                if (parentBillingDbCode != null) {
//                                                        buList = securityInterface.getOfficeLocBuList(securityDTO);
//                                                }
//                                        }
            }
            //}
        } catch (Exception exp) {
            logger.error("Exception occured in retrieving details from Session ", exp);
            throw exp;
        }

        logger.debug("Size: buList ==> " + buList.size());
        return buList;
    }

    public static List getCircleList(HttpSession session) throws Exception {
        List cirList = new LinkedList();

        try {
            logger.debug("Retrieving cir information from Session Object...");

            String billingUnit = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);

            SecurityInterface securityInterface;
            boolean isBuFound = false;

            if (ApplicationUtils.validateString(billingUnit)) {
                if (Integer.parseInt(billingUnit) > 0) {
                    isBuFound = false;
                } else {
                    isBuFound = true;
                }
            } else {
                isBuFound = true;
            }

            logger.debug("Billing code found ==>" + billingUnit + " :: isBuFound ==>" + isBuFound);

            //if (isBuFound) {
            String officeType = (String) session.getAttribute(ApplicationConstants.OFFICE_TYPE_SESSION);
            logger.debug("officeType ==>" + officeType);

            if (ApplicationUtils.validateString(officeType)) {

                long officeTyp = Long.parseLong(officeType);
                logger.debug("officeTyp ==>" + officeTyp);
                securityInterface = new SecurityImpl();

                // Case 0: User belongs to a DIV/CIR/ZON i.e. office type = "DIV" or "CIR" or "ZON"
                // office type = "ZON"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.ZONAL_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.HEAD_OFFICE_TYPE_ID) {
                    String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);
                    logger.debug("Case: 0: billingDbCode ==>" + billingDbCode);

                    SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(billingDbCode), ApplicationConstants.HEAD_OFFICE_TYPE_ID);
                    if (billingDbCode != null) {
                        cirList = securityInterface.getOfficeLocCircleList(securityDTO);
                    }
                }

                // Case 1: User belongs to a DIV/CIR/ZON i.e. office type = "DIV" or "CIR" or "ZON"
                // office type = "ZON"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.ZONAL_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.ZONE_TYPE_ID) {
                    String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);
                    logger.debug("Case: 1: billingDbCode ==>" + billingDbCode);

                    SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(billingDbCode), ApplicationConstants.ZONE_TYPE_ID);
                    if (billingDbCode != null) {
                        cirList = securityInterface.getOfficeLocCircleList(securityDTO);
                    }
                }

                // Case 2: User belongs to a DIV/CIR/ZON i.e. office type = "DIV" or "CIR" or "ZON"
                // office type = "CIR"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.CIRCLE_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.CIRCLE_TYPE_ID) {
                    String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);
                    logger.debug("Case: 2: billingDbCode ==>" + billingDbCode);

                    SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(billingDbCode), ApplicationConstants.CIRCLE_TYPE_ID);
                    if (billingDbCode != null) {
                        cirList = securityInterface.getOfficeLocCircleList(securityDTO);
                    }
                }

                // Case 3: User belongs to a DIV/CIR/ZON i.e. office type = "DIV" or "CIR" or "ZON"
                // office type = "DIV"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.DIVISION_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.DIVISION_TYPE_ID) {
                    String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);
                    logger.debug("Case: 3: billingDbCode ==>" + billingDbCode);

                    SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(billingDbCode), ApplicationConstants.DIVISION_TYPE_ID);
                    if (billingDbCode != null) {
                        cirList = securityInterface.getOfficeLocCircleList(securityDTO);
                    }
                }

                // Case 4: User belongs to a SUB i.e. office type = "SUB"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.SUBDIVISION_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.SUBDIVISION_TYPE_ID) {
                    String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);
                    logger.debug("Case: 4: billingDbCode ==>" + billingDbCode);

                    SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(billingDbCode), ApplicationConstants.SUBDIVISION_TYPE_ID);
                    if (billingDbCode != null) {
                        cirList = securityInterface.getOfficeLocCircleList(securityDTO);
                    }
                }

                // Case 5: User belongs to SEC i.e. Office Type = "SEC"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.SECTION_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.SECTION_TYPE_ID) {
                    String officeCode = (String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION);
                    logger.debug("Case: 5: officeCode ==>" + officeCode);

                    SecurityDTO securityDTO = new SecurityDTO(Long.parseLong(officeCode), 0, 0);
                    if (officeCode != null) {
                        cirList = securityInterface.getOfficeCodeCircleList(securityDTO);
                    }
                }

                // Case 6: User belongs to SS i.e. Office Type = "SS"
                // if(officeType.equalsIgnoreCase(ApplicationConstants.SUBSTATION_OFFICE_TYPE)){
                if (officeTyp == ApplicationConstants.SUBSTATION_TYPE_ID) {
                    String parentOfficeCode = (String) session.getAttribute(ApplicationConstants.PARENT_OFFICE_CODE_SESSION);
                    logger.debug("Case: 6: parentOfficeCode ==>" + parentOfficeCode);

                    SecurityDTO securityDTO = new SecurityDTO(Long.parseLong(parentOfficeCode), 0, 0);
                    if (parentOfficeCode != null) {
                        cirList = securityInterface.getOfficeCodeCircleList(securityDTO);
                    }
                }


                // Case 7: User belongs to a "TEST" i.e. testing division of MSEDCL i.e. office type = "TEST"
//					 if(officeType.equalsIgnoreCase(ApplicationConstants.TESTING_OFFICE_TYPE)){
//                                        if (officeTyp == ApplicationConstants.CIRCLE_TYPE_ID) {
//                                                String parentBillingDbCode = (String) session.getAttribute(ApplicationConstants.PARENT_OFFICE_BILLING_DB_CODE_SESSION);
//                                                logger.debug("Case: 5: parentBillingDbCode ==>" + parentBillingDbCode);
//
//                                                SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(parentBillingDbCode), ApplicationConstants.CIRCLE_TYPE_ID);
//                                                if (parentBillingDbCode != null) {
//                                                        cirList = securityInterface.getOfficeLocBuList(securityDTO);
//                                                }
//                                        }
            }
            //}
        } catch (Exception exp) {
            logger.error("Exception occured in retrieving details from Session ", exp);
            throw exp;
        }

        logger.debug("Size: buList ==> " + cirList.size());
        return cirList;
    }

    public static Connection getConnection() throws Exception {

        try {
            Connection connection = null;
            if (dataSource == null) {
                // Error during initialization of InitialContext or Datasource
                throw new Exception("###### Fatal Exception ###### - DataSource is not initialized.Pls check the stdout/logs.");
            } else {
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
            }
            return connection;
        } catch (Exception ex) {
            //ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * This public static API use to get currentTimestamp attribute
     *
     * @param conn	java.sql.Connection reference
     * @return	current Timestamp of a connected database
     */
    public static Timestamp getCurrentTimestamp() {
        Timestamp returnTimestamp = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT SYSDATE FROM DUAL");
            rs = ps.executeQuery();
            if (rs.next()) {
                returnTimestamp = rs.getTimestamp(1);
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getCurrentTimestamp() :: Exception  :: " + ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return returnTimestamp;
    }

    /**
     * This public static API use to convertjava.sql.Date into String.
     *
     * @param ts, java.sql.Date	reference
     * @param format, formate which is suppose to display
     * @return String, java.lang.String value of java.sql.Date object
     */
    public static String dateToString(java.util.Date ts, String format) {
        String dateString = null;
        if (isBlank(format) || ts == null) {
            return null;
        }
        try {
            SimpleDateFormat formatSpec = new SimpleDateFormat(format);
            dateString = formatSpec.format(ts);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return dateString;
    }

    /**
     * This public static API use to check whether Object is Null or not.
     *
     * @param tr, java.lang.Object (mostly java.lang.String)
     * @return boolean, status of java.lang.Object
     */
    public static boolean isBlank(Object str) {
        return (str == null || str.toString().trim().isEmpty());
    }

    /**
     * This public static API use to get the value of Label from resource
     * Bundle.
     *
     * @param request, HttpServletRequest reference
     * @param resourceName, resource name
     * @param key, key of a resource
     * @return String, value of a resource
     */
    public static String getLabel(HttpServletRequest request, String resourceName, String key) {
        String labelValue = null;
        String keyValue = "";

        keyValue = new String(key);
        if (request.getSession().getAttribute(resourceName) != null) {
            HashMap resourceMap = (HashMap) request.getSession().getAttribute(resourceName);
            if (resourceMap != null && resourceMap.size() > 0) {
                labelValue = (String) resourceMap.get(keyValue);
            }
        }
        return labelValue;
    }

    /**
     * This public static API use to get the Render URL
     *
     * @param request, HttpServletRequest reference
     * @param name, name of url
     * @param value, value of url
     * @return String, render url
     * @throws Exception
     */
    public static String getRenderURL(HttpServletRequest request, String name, String value) throws Exception {
        String renderUrl = null;
        try {
            renderUrl = ApplicationConstants.SERVLET_CONTEXT + "?" + name + "=" + value;
        } catch (Exception ex) {
            throw ex;
        }
        return renderUrl;
    }

    /**
     * This public static API use to get the ACTION URL
     *
     * @param request, HttpServletRequest reference
     * @return String, action url
     */
    public static String getActionURL(HttpServletRequest request) throws Exception {
        String actionUrl = null;
        try {
            actionUrl = ApplicationConstants.SERVLET_CONTEXT;
        } catch (Exception ex) {
            throw ex;
        }
        return actionUrl;
    }

    /**
     * This public static API use to get the ACTION URL
     *
     * @param request, HttpServletRequest reference
     * @return String, action url
     */
    public static String getFileURL(HttpServletRequest request) throws Exception {
        String actionUrl = null;
        try {
            actionUrl = ApplicationConstants.GENFILE_CONTEXT;
        } catch (Exception ex) {
            throw ex;
        }
        return actionUrl;
    }

    /**
     * Public static API to convert file separators OS specific.
     *
     * @param url	the URL
     */
    public static String validateURL(String url) {
        String correctedURL = "";
        try {
            if (validateString(url)) {
                url = url.trim();

                StringBuffer sb = new StringBuffer();
                String delimiter = "";

                if (url.indexOf("\\") != -1) {
                    delimiter = "\\";

                    StringTokenizer urlTokenizer = new StringTokenizer(url, delimiter);
                    while (urlTokenizer.hasMoreTokens()) {
                        String token = (String) urlTokenizer.nextToken();
                        sb.append(token);
                        sb.append(File.separator);
                    }

                    correctedURL = sb.toString();
                    correctedURL = correctedURL.substring(0, correctedURL.length() - 1);
                } else {
                    correctedURL = url;
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: validateURL() :: Exception  :: " + ex);
        }
        return correctedURL;
    }

    /**
     * Public static API to check whether Object is Null or not.
     *
     * @param str	the String object
     * @return	the boolean object
     */
    public static boolean validateString(String str) {
        boolean isStringValid = true;
        try {
            if (str == null || str.toString().trim().length() == 0 || str.equals("")) {
                isStringValid = false;
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: isBlank() :: Exception :: " + ex);
        }
        return isStringValid;
    }

    public static String getRequestParameters(HttpServletRequest request, String parameterName) throws Exception {
        String paramValue = null;

        try {
            if (!(isBlank(parameterName))) {
                if (request.getParameter(parameterName) != null && request.getParameter(parameterName).length() > 0) {
                    paramValue = request.getParameter(parameterName);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return paramValue;
    }

    public static void setSessionParameters(HttpServletRequest request, String parameterName, Object parameterValue) throws Exception {
        try {
            if (!(isBlank(parameterName)) && parameterValue != null) {
                request.getSession().setAttribute(parameterName, parameterValue);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Object getSessionParameters(HttpServletRequest request, String parameterName) throws Exception {
        Object obj = null;
        try {
            if (!(isBlank(parameterName))) {
                if (request.getSession().getAttribute(parameterName) != null) {
                    obj = (Object) request.getSession().getAttribute(parameterName);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return obj;
    }

    public static void setRequestAttribute(HttpServletRequest request, String attributeName, Object attributeValue) throws Exception {
        try {
            if (!(isBlank(attributeName)) && attributeValue != null) {
                request.setAttribute(attributeName, attributeValue);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Object getRequestAttribute(HttpServletRequest request, String attributeName) throws Exception {
        Object obj = null;
        try {
            if (!(isBlank(attributeName))) {
                if (request.getAttribute(attributeName) != null) {
                    obj = (Object) request.getAttribute(attributeName);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return obj;
    }

    public static String getDataLength(String inpData, int reqdLength, char padChar) {
        return lPad("" + inpData.length(), reqdLength, padChar);
    }

    public static String getDate(String dtFormat) {
        if (dtFormat == "") {
            dtFormat = "dd-MM-yyyy";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dtFormat);
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    public static String lPad(String input, int length, char pad) {
        int padLength = length - input.length();
        String s;
        for (s = new String(""); s.length() < padLength; s = s + String.valueOf(pad));
        input = s + input;
        if (input.length() != length) {
            return input;
        } else {
            return input;
        }
    }

    public static boolean decodeCmd(String eCommand) {
        Map eCommandsError = new HashMap();
        Map eCommandsSuccess = new HashMap();
        Map eCommandsFinished = new HashMap();
        boolean done = false;
        boolean isError = false;
        String eCmd = null;
        String eCmdQua = null;
        String eAddQua = null;
        if (eCommand.substring(0, 2).equals("02")) {
            eCmd = "Acknowledgement received for ";
        } else if (eCommand.substring(0, 2).equals("04")) {
            eCmd = "Report Progress";
        }
        if (eCommand.substring(2, 4).equals("00")) {
            eCmdQua = "Reading Meter Command ";
        } else if (eCommand.substring(2, 4).equals("01")) {
            eCmdQua = "MRI Download Command ";
        } else if (eCommand.substring(2, 4).equals("02")) {
            eCmdQua = "MRI Prepare Command ";
        } else if (eCommand.substring(2, 4).equals("03")) {
            eCmdQua = "CDF Conversion Command ";
        }
        eAddQua = eCommand.substring(4, 6);
        if (eCommand.substring(0, 2).equals("02")) {
            eCommandsSuccess.put("00", "Accepted");
            eCommandsError.put("01", "Failed due to Duplicate Instance.");
            eCommandsError.put("02", "Failed due to Invalid Configuration File.");
            eCommandsError.put("03", "Invalid / Unknown Command.");
            eCommandsError.put("04", "Command not Supported.");
            eCommandsError.put("04", "Failed due to Invalid Instance ID.");
        } else if (eCommand.substring(0, 2).equals("04")) {
            eCommandsSuccess.put("00", "In Progress");
            eCommandsSuccess.put("01", "Connection Established");
            eCommandsSuccess.put("02", "Meter Reading Started");
            eCommandsSuccess.put("05", "Idle State");
            eCommandsFinished.put("03", "Meter Reading Finished");
            eCommandsFinished.put("04", "CDF Conservation Successful");
            eCommandsFinished.put("77", "MRI Data Transfer Successful");
            eCommandsError.put("51", "Cannot Establish Connection - No Dail Tone");
            eCommandsError.put("52", "Cannot Establish Connection - Local Modem not Responding");
            eCommandsError.put("53", "Cannot Establish Connection - Line Busy");
            eCommandsError.put("54", "Cannot Establish Connection - Port Not Available");
            eCommandsError.put("55", "Cannot Establish Connection - No Hand Shaking");
            eCommandsError.put("56", "Line Disconnected");
            eCommandsError.put("57", "CDF Conservation Failed - File Structure Corrupted");
            eCommandsError.put("58", "CDF Conservation Failed - File Write Error");
            eCommandsError.put("59", "CDF Conservation Failed - File Not Found");
            eCommandsError.put("60", "User Abort");
            eCommandsError.put("61", "Process Stopped - Meter Serial No. Mismatch");
            eCommandsError.put("62", "Meter Reading Failed");
            eCommandsError.put("63", "Conversion Failed");
            eCommandsError.put("64", "File(s) are not Available for Data Conversion");
            eCommandsError.put("65", "Meter Reading Failed - Check sum Error");
            eCommandsError.put("66", "Meter Reading Failed - Data Collection Error");
            eCommandsError.put("67", "Invalid Header");
            eCommandsError.put("68", "Source Folder Path Not Found");
            eCommandsError.put("69", "Unit Code is not Set. Continuing with Remaining Meters.");
            eCommandsError.put("70", "Tariff Cant be Zero");
            eCommandsError.put("71", "Can't Continue with Parsing");
            eCommandsError.put("72", "Unsupported Meter Version");
            eCommandsError.put("73", "Not Enough Free Space");
            eCommandsError.put("74", "Meter is Inactive - Cannot Collect Data");
            eCommandsError.put("75", "Cannot Establish Connection - No Carrier");
            eCommandsError.put("76", "Cannot Establish Connection - No Connection");
            eCommandsError.put("78", "MRI Data Transfer Failed");
            eCommandsError.put("79", "MRI Not Responding");
        }
        String eAddQuaStr = null;
        if (eCommandsSuccess.containsKey(eAddQua)) {
            eAddQuaStr = (String) eCommandsSuccess.get(eAddQua);
            done = false;
            isError = false;
        } else if (eCommandsError.containsKey(eAddQua)) {
            eAddQuaStr = (String) eCommandsError.get(eAddQua);
            done = true;
            isError = true;
        } else if (eCommandsFinished.containsKey(eAddQua)) {
            eAddQuaStr = (String) eCommandsFinished.get(eAddQua);
            done = true;
            isError = false;
        }

        logger.log(Level.INFO, "CPServer :: decodeCmd() ::  eCommand : " + eCommand + " | eCmd : " + eCmd + " | eCmdQua : " + eCmdQua + " | eAddQua : " + eAddQua + " | eAddQuaStr : " + eAddQuaStr + " | Done : " + done + " | isError : " + isError);
        return done;
    }

    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
    public static String getLocTypeOptionStringFromList(LinkedList locationTypeList, String selectedStr) throws Exception {
        LocationTypeBean locTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            strBuff.append("<option selected value = -1> All </option>");
            if (locationTypeList != null && locationTypeList.size() > 0) {
                itr = locationTypeList.iterator();
                while (itr.hasNext()) {

                    locTypeBeanObj = (LocationTypeBean) itr.next();
                    if (locTypeBeanObj.getLocationTypeId() != 0) {
                        if (Integer.valueOf(selectedStr).intValue() == (locTypeBeanObj.getLocationTypeId())) {
                            strBuff.append("<option selected value = " + locTypeBeanObj.getLocationTypeId() + ">" + locTypeBeanObj.getLocationTypeDescription() + "</option>");
                        } else {
                            strBuff.append("<option value = " + locTypeBeanObj.getLocationTypeId() + ">" + locTypeBeanObj.getLocationTypeDescription() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    /**
     * This API returns HashMap contain key is locationId and value is String of
     * location options..
     *
     * @parm List of locations and selected location string
     * @returns HashMap throws Exception
     */
    public static LinkedHashMap getHashMapFromList(LinkedList locationList, String selectedLocStr) throws Exception {
        //logger.log(Level.INFO, "ApplicationUtil :: getHashMapFromList() ::  " );
        LinkedHashMap map = new LinkedHashMap();
        String locTypeId = "";
        String locOptionString = "";
        String previousOptStr = "";

        LocationBean locBeanObj = null;
        try {
            if (locationList != null && locationList.size() > 0) {
                for (int i = 0; i < locationList.size(); i++) {
                    locBeanObj = (LocationBean) locationList.get(i);

                    locTypeId = locBeanObj.getLocationTypeId() + "";
                    locOptionString = convertDataToOptionString(locBeanObj, selectedLocStr);
                    if (map.containsKey(locTypeId)) {
                        previousOptStr = (String) map.get(locTypeId);
                        previousOptStr += locOptionString;
                        locOptionString = previousOptStr;
                    } else {
                        previousOptStr = "<option selected value = -1> -Select- </option>";
                        previousOptStr += (String) map.get(locTypeId);
                        previousOptStr += locOptionString;
                        locOptionString = previousOptStr;
                    }

                    map.put(locTypeId, locOptionString);

                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getHashMapFromList() :: Exception  :: " + ex);
        }

        return map;
    }

    /**
     * Public API to generate option string from list with key as value.
     *
     * @param Bean Object	the LocationBean object
     * @param selectedVal	the value selected
     * @return	the String object
     */
    public static String convertDataToOptionString(LocationBean locBeanObj, String selOfficeLocation) {
        StringBuffer sb = new StringBuffer();
        try {
            if (locBeanObj != null) {
                String locationName = locBeanObj.getLocationDescription() + "";
                String locationId = locBeanObj.getLocationId() + "";

                if (locationId.equals(selOfficeLocation)) {
                    sb = sb.append("<option value='" + locationId + "' selected>" + locationName + "</option>");
                } else {
                    sb = sb.append("<option value='" + locationId + "'>" + locationName + "</option>");
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: convertDataToOptionString() :: Exception  :: " + ex);
        }

        return sb.toString();
    }

    /*public method to get the schedule period
     @PARAM String selected stringoption
     @RETURN STRING */
    public static String getSchedulePeriod(String selectedSchPeriod) {
        /*StringBuffer strBuff = new StringBuffer();
         if (selectedSchPeriod.equals("-1"))
         {
         strBuff.append("<option selected value = -1> -Select-</option> <option value = 1> Today </option> <option value = 2> Yesterday</option><option value = 3>Last Week</option><option value = 4>Last Fortnight</option><option value = 5>Last Month</option><option value = 6>Last Year</option>");
         }
         else if (selectedSchPeriod.equals("1"))
         {
         strBuff.append("<option value = -1> -Select-</option> <option selected value = 1> Today </option> <option value = 2> Yesterday</option><option value = 3>Last Week</option><option value = 4>Last Fortnight</option><option value = 5>Last Month</option><option value = 6>Last Year</option>");
         }
         else if (selectedSchPeriod.equals("2") )
         {
         strBuff.append("<option value = -1> -Select-</option> <option value = 1> Today </option> <option selected value = 2> Yesterday</option><option value = 3>Last Week</option><option value = 4>Last Fortnight</option><option value = 5>Last Month</option><option value = 6>Last Year</option>");
         }
         else if (selectedSchPeriod.equals("3"))
         {
         strBuff.append("<option value = -1> -Select-</option> <option value = 1> Today </option> <option value = 2> Yesterday</option><option selected value = 3>Last Week</option><option value = 4>Last Fortnight</option><option value = 5>Last Month</option><option value = 6>Last Year</option>");
         }
         else if (selectedSchPeriod.equals("4"))
         {
         strBuff.append("<option value = -1> -Select-</option> <option value = 1> Today </option> <option value = 2> Yesterday</option><option selected value = 3>Last Week</option><option selected value = 4>Last Fortnight</option><option value = 5>Last Month</option><option value = 6>Last Year</option>");
         }
         else if (selectedSchPeriod.equals("5"))
         {
         strBuff.append("<option value = -1> -Select-</option> <option value = 1> Today </option> <option value = 2> Yesterday</option><option selected value = 3>Last Week</option><option value = 4>Last Fortnight</option><option selected value = 5>Last Month</option><option value = 6>Last Year</option>");
         }
         else if (selectedSchPeriod.equals("6"))
         {
         strBuff.append("<option value = -1> -Select-</option> <option value = 1> Today </option> <option value = 2> Yesterday</option><option selected value = 3>Last Week</option><option value = 4>Last Fortnight</option><option value = 5>Last Month</option><option selected value = 6>Last Year</option>");
         }
         return strBuff.toString();*/

        ///AnalysisPrezData analysisPrezDataObj = new AnalysisPrezData();
        String strOptions = "";
        try {
            MasterDao masterDao = null;
            masterDao = new OracleMasterDao();
            LinkedList graphScheduleList = masterDao.getMasterList("GRAPH_SCHEDULE_PERIOD", "SCHEDULE_ID", "SCHEDULE_ID", "SCHEDULE_PERIOD", "");

            strOptions = ApplicationUtils.getMasterCodeOptionString(graphScheduleList, selectedSchPeriod);

            logger.log(Level.INFO, "ApplicationUtils :: getSchedulePeriod() :: Values :::" + strOptions);

            ///analysisPrezDataObj.setGraphScheduleList(graphScheduleList);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " ApplicationUtils :: getSchedulePeriod() :: Exception :: " + ex);
            ///throw ex;
        }
        return strOptions;



    } // End of Method ---

    public static String getScheduleName(String selectedSchPeriod) {

        String strOptions = "";
        try {
            MasterDao masterDao = null;
            masterDao = new OracleMasterDao();
            LinkedList graphScheduleList = masterDao.getMasterList("SCHEDULER", "SCHEDULE_ID", "SCHEDULE_ID", "SCHEDULE_NAME", "");

            strOptions = ApplicationUtils.getMasterCodeOptionString(graphScheduleList, selectedSchPeriod);

            logger.log(Level.INFO, "ApplicationUtils :: getScheduleName() :: Values :::" + strOptions);


        } catch (Exception ex) {
            logger.log(Level.ERROR, " ApplicationUtils :: getScheduleName() :: Exception :: " + ex);
        }
        return strOptions;



    } // End of Method ---

    public static String getSchedulePeriodDifference(String fromDate, String fromDateFMT, String toDate, String toDateFMT) {
        String returnString = "10";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        try {
            Timestamp dtFrom = ApplicationUtils.stringToTimestamp(fromDate, fromDateFMT);
            Timestamp dtTo = ApplicationUtils.stringToTimestamp(toDate, toDateFMT);
            String from = ApplicationUtils.timeStampToString(dtFrom, "");   ///Plz dont mention outFormat here like "dd-MMM-yyyy HH:MM:SS" it is giving wrong output. check min field.
            String to = ApplicationUtils.timeStampToString(dtTo, "");

            sql.append("SELECT ");
            sql.append("ROUND(TO_DATE('" + to + "','DD-MON-YYYY HH:MI:SS') ");
            sql.append(" - TO_DATE('" + from + "','DD-MON-YYYY HH:MI:SS')) AS DATE_DIFF ");
            sql.append("FROM ");
            sql.append("DUAL ");
            System.out.println("Date Query:::" + sql.toString());

            con = getConnection();
            pst = con.prepareStatement(sql.toString());
            rs = pst.executeQuery();

            while (rs.next()) {
                returnString = rs.getString("DATE_DIFF");
            }



            logger.log(Level.INFO, "ApplicationUtils :: getSchedulePeriodDifference() :: SQL :::" + sql.toString());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getSchedulePeriodDifference() :: Exception :: " + ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return returnString;

    }///End of Method.

    /*public method to get the Search period for Dropdown
     @PARAM String selected stringoption
     @RETURN STRING */
    public static String getSearchPeriod(String selectedSearchPeriod) {
        StringBuffer strBuff = new StringBuffer();
        if (selectedSearchPeriod.equals("-1")) {
            strBuff.append("<option selected value = -1> -Select-</option> <option value = 1> Today </option> <option value = 2> Yesterday </option><option value = 3> Last Week </option>");
        } else if (selectedSearchPeriod.equals("1")) {
            strBuff.append("<option value = -1> -Select-</option> <option selected value = 1> Today </option> <option value = 2> Yesterday </option> <option value = 3> Last Week </option>");
        } else if (selectedSearchPeriod.equals("2")) {
            strBuff.append("<option value = -1> -Select-</option> <option  value = 1> Today </option> <option selected value = 2> Yesterday </option> <option value = 3> Last Week </option>");
        } else if (selectedSearchPeriod.equals("3")) {
            strBuff.append("<option value = -1> -Select-</option> <option value = 1> Today </option> <option value = 2> Yesterday </option> <option selected value = 3> Last Week </option>");
        }

        return strBuff.toString();
    } // End of Method ---

    /*public method to get the Recently updated Device
     @RETURN LinkedList of Five Recently updated device object*/
    public static LinkedList getRecentlyUpdatedDevice() throws Exception {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        LinkedList devicelist = new LinkedList();
        SearchResultBean searchResultBeanObj = null;
        StringBuffer sql = new StringBuffer();

        try {
            sql.append("SELECT A.* 	FROM( ");
            sql.append("SELECT DEVICE.DEVICE_ID, ");
            sql.append("DEVICE.DEVICE_SERIAL_NO ");
            sql.append("FROM DEVICE_SCHEDULE, ");
            sql.append("DEVICE ");
            sql.append("where DEVICE.DEVICE_ID = DEVICE_SCHEDULE.DEVICE_ID ");
            sql.append("AND UPPER(DEVICE_SCHEDULE.OPERATION_IN_PROGRESS) = UPPER('Y') ");
            sql.append("ORDER BY DEVICE_SCHEDULE.UPDATED_DT DESC ");
            sql.append(" )A ");
            sql.append(" WHERE ROWNUM <=5 ");

            con = getConnection();
            pst = con.prepareStatement(sql.toString());
            rs = pst.executeQuery();

            while (rs.next()) {

                searchResultBeanObj = new SearchResultBean();

                // setting data from the result set in the Device Bean Object

                searchResultBeanObj.setDeviceSerialNo(rs.getString("DEVICE_SERIAL_NO"));
                searchResultBeanObj.setDeviceId(rs.getInt("DEVICE_ID"));

                // adding object to list
                devicelist.add(searchResultBeanObj);
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return devicelist;
    } // End of method


    /* public API to get Hours value from 1 to 12
     @param selected hour value
     @return <option> String*/
    public static String getHoursOptionString(String selectedHour) {
        StringBuffer strBuff = new StringBuffer();
        try {
            if (selectedHour.equals("-1")) {
                strBuff = strBuff.append("<option value= '-1' selected> </option>");
            }
            for (int i = 0; i < 12; i++) {
                if ((i + 1) == Integer.parseInt(selectedHour)) {
                    strBuff = strBuff.append("<option value=\'" + (i + 1) + "\' selected>" + (i + 1) + "</option>");
                } else {
                    strBuff = strBuff.append("<option value=\'" + (i + 1) + "\'>" + (i + 1) + "</option>");
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return strBuff.toString();

    }//-- End of Method

    public static String getMinutesOptionString(String selectedMinute) {
        StringBuffer strBuff = new StringBuffer();
        try {
            if (selectedMinute.equals("-1")) {
                strBuff = strBuff.append("<option value= '-1' selected> </option>");
            }
            for (int i = 0; i < 60; i++) {
                if ((i) == Integer.parseInt(selectedMinute)) {
                    strBuff = strBuff.append("<option value=\'" + (i) + "\' selected>" + (i) + "</option>");
                } else {
                    strBuff = strBuff.append("<option value=\'" + (i) + "\'>" + (i) + "</option>");
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return strBuff.toString();
    }// End of Method

    /*public Method Thad gets the option String for AM & PM*/
    public static String getAmPmOptionString(String selectedAmPm) {
        StringBuffer strBuff = new StringBuffer();

        if (selectedAmPm.equals("-1")) {
            strBuff.append("<option selected value = -1> </option> <option value = 1> AM </option> <option value = 2> PM </option>");
        } else if (selectedAmPm.equals("1")) {
            strBuff.append("<option value = -1> </option> <option selected value = 1> AM </option> <option value = 2> PM </option> ");
        } else if (selectedAmPm.equals("2")) {
            strBuff.append("<option value = -1> </option> <option  value = 1> AM </option> <option selected value = 2> PM </option>");
        }

        return strBuff.toString();
    } // End of Method ---

    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
    public static String getDevTypeOptionStringFromList(LinkedList deviceTypeList, String selectedStr) throws Exception {
        DeviceTypeBean devTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            strBuff.append("<option selected value = -1> All </option>");
            if (deviceTypeList != null && deviceTypeList.size() > 0) {
                itr = deviceTypeList.iterator();
                while (itr.hasNext()) {

                    devTypeBeanObj = (DeviceTypeBean) itr.next();
                    if (devTypeBeanObj.getDeviceTypeId() != 0) {
                        if (Integer.valueOf(selectedStr).intValue() == (devTypeBeanObj.getDeviceTypeId())) {
                            strBuff.append("<option selected value = " + devTypeBeanObj.getDeviceTypeId() + ">" + devTypeBeanObj.getDeviceTypeCd() + " - " + devTypeBeanObj.getDeviceTypeDesc() + "</option>");
                        } else {
                            strBuff.append("<option value = " + devTypeBeanObj.getDeviceTypeId() + ">" + devTypeBeanObj.getDeviceTypeCd() + " - " + devTypeBeanObj.getDeviceTypeDesc() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
    public static String getProjectOptionStringFromList(LinkedList projectList, String selectedStr) throws Exception {
        DeviceTypeBean devTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            strBuff.append("<option value = -1> All </option>");
            if (projectList != null && projectList.size() > 0) {
                itr = projectList.iterator();
                while (itr.hasNext()) {

                    devTypeBeanObj = (DeviceTypeBean) itr.next();
                    if (devTypeBeanObj.getProjectId() != 0) {
                        if (Integer.valueOf(selectedStr).intValue() == (devTypeBeanObj.getProjectId())) {
                            strBuff.append("<option selected value = " + devTypeBeanObj.getProjectId() + ">" + devTypeBeanObj.getProjectId() + " - " + devTypeBeanObj.getProjectName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + devTypeBeanObj.getProjectId() + ">" + devTypeBeanObj.getProjectId() + " - " + devTypeBeanObj.getProjectName() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method
    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/

    public static String getProjectOptionStringFromListall(LinkedList projectList, String selectedStr) throws Exception {
        DeviceTypeBean devTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            //strBuff.append("<option value = -1> All </option>");
            if (projectList != null && projectList.size() > 0) {
                itr = projectList.iterator();
                while (itr.hasNext()) {

                    devTypeBeanObj = (DeviceTypeBean) itr.next();
                    if (devTypeBeanObj.getProjectId() != 0) {
                        if (Integer.valueOf(selectedStr).intValue() == (devTypeBeanObj.getProjectId())) {
                            strBuff.append("<option selected value = " + devTypeBeanObj.getProjectId() + ">" + devTypeBeanObj.getProjectId() + " - " + devTypeBeanObj.getProjectName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + devTypeBeanObj.getProjectId() + ">" + devTypeBeanObj.getProjectId() + " - " + devTypeBeanObj.getProjectName() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
    public static String getReasonOptionStringFromList(LinkedList projectList, String selectedStr) throws Exception {
        DeviceTypeBean devTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {

            if (projectList != null && projectList.size() > 0) {
                itr = projectList.iterator();
                while (itr.hasNext()) {

                    devTypeBeanObj = (DeviceTypeBean) itr.next();
                    if (devTypeBeanObj.getProjectId() != 0) {
                        if (Integer.valueOf(selectedStr).intValue() == (devTypeBeanObj.getProjectId())) {
                            strBuff.append("<option selected value = " + devTypeBeanObj.getProjectId() + ">" + devTypeBeanObj.getProjectId() + " - " + devTypeBeanObj.getProjectName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + devTypeBeanObj.getProjectId() + ">" + devTypeBeanObj.getProjectId() + " - " + devTypeBeanObj.getProjectName() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
    public static String getServerOptionString(LinkedList serverList, String selectedStr) throws Exception {
        DeviceTypeBean devTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            //strBuff.append("<option value = -1> All </option>");
            if (serverList != null && serverList.size() > 0) {
                itr = serverList.iterator();
                while (itr.hasNext()) {

                    devTypeBeanObj = (DeviceTypeBean) itr.next();
                    if (devTypeBeanObj.getServerId() != 0) {
                        if (Integer.valueOf(selectedStr).intValue() == (devTypeBeanObj.getServerId())) {
                            strBuff.append("<option selected value = " + devTypeBeanObj.getServerId() + ">" + devTypeBeanObj.getServerLoc() + " - " + devTypeBeanObj.getServerName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + devTypeBeanObj.getServerId() + ">" + devTypeBeanObj.getServerLoc() + " - " + devTypeBeanObj.getServerName() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method
    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/

    public static String getTownOptionStringFromList(LinkedList townList, String selectedStr) throws Exception {
        DeviceTypeBean devTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            strBuff.append("<option selected value = -1> All </option>");
            if (townList != null && townList.size() > 0) {
                itr = townList.iterator();
                while (itr.hasNext()) {

                    devTypeBeanObj = (DeviceTypeBean) itr.next();
                    if (devTypeBeanObj.getTownId() != 0) {
                        if (Integer.valueOf(selectedStr).intValue() == (devTypeBeanObj.getTownId())) {
                            strBuff.append("<option selected value = " + devTypeBeanObj.getTownId() + ">" + devTypeBeanObj.getTownId() + " - " + devTypeBeanObj.getTownName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + devTypeBeanObj.getTownId() + ">" + devTypeBeanObj.getTownId() + " - " + devTypeBeanObj.getTownName() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
    public static String getTownOptionString(LinkedList townList, String selectedStr) throws Exception {
        MasterBean masterBean = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();

        try {
            strBuff.append("<option selected value = -1> All </option>");
            if (townList != null && townList.size() > 0) {
                itr = townList.iterator();
                while (itr.hasNext()) {
                    masterBean = (MasterBean) itr.next();
                    if (masterBean.getId() != 0) {
                        if (selectedStr.equals(masterBean.getId() + "")) {
                            strBuff.append("<option selected value = " + masterBean.getId() + ">" + masterBean.getName() + " - " + masterBean.getId() + "</option>");
                        } else {
                            strBuff.append("<option value = " + masterBean.getId() + ">" + masterBean.getName() + " - " + masterBean.getId() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return strBuff.toString();
    }// End of Method

    /**
     * This public static API use to get next Sequence ID for a sequencre Name
     *
     * @param sequenceName	the sequence name
     * @return conn	the connection object
     * @throws Exception	if an error occurs
     *
     */
    public static Long getNextSequenceId(Connection conn, String sequenceName) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        Long nextKey = null;
        String sql = "";

        try {
            sql = "select " + sequenceName + ".nextval from DUAL";
            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                nextKey = new Long(results.getLong(1));
            } else {
                throw new SQLException("sequence failed to return a value");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getNextSequenceId() :: Exception  :: " + ex);
            throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }

        }
        return nextKey;

    } // End of method

    public static String getVoltageDesc(String voltageId) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        String voltageDesc = null;
        String sql = "";
        Connection conn = null;

        try {
            conn = getConnection();
            sql = "select voltage_level_desc from voltage_level where voltage_level_id=" + voltageId;
            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                voltageDesc = new String(results.getString(1));
            } else {
                voltageDesc = "";
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getVoltageDesc() :: Exception  :: " + ex);
            voltageDesc = "";
            //throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return voltageDesc;

    }

    public static String getLocationDesc(String locId) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        String locationDesc = null;
        String sql = "";
        Connection conn = null;

        try {
            conn = getConnection();
            sql = "select location_desc from location where location_id=" + locId;
            pStmt = conn.prepareStatement(sql);

            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                locationDesc = new String(results.getString(1));
            } else {
                throw new SQLException("sequence failed to return a value");
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationDesc() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationDesc() :: Exception  :: " + ex);
            throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return locationDesc;

    }

    public static String getLocationDesc1(String zoneId, int locType) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        String locationDesc = null;
        String sql = "";
        Connection conn = null;

        try {
            conn = getConnection();
            sql = "select location_desc from location where location_cd='" + zoneId + "' and location_type_id=" + locType
                    + " and status_cd='A' ";
            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                locationDesc = new String(results.getString(1));
            } else {
                throw new SQLException("sequence failed to return a value");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationDesc1() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationDesc1() :: Exception  :: " + ex);
            throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return locationDesc;

    }

    public static String getLocationDesc2(String buCode) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        String locationDesc = null;
        String sql = "";
        Connection conn = null;

        try {
            conn = getConnection();
            sql = "select lm.location_desc from location lm,location_bu_mapping lbm where lbm.bu_code=" + buCode + " and lbm.loc_id=lm.location_id "
                    + " and lm.status_cd='A' and bu_status='Y'";
            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                locationDesc = new String(results.getString(1));
            } else {
                throw new SQLException("sequence failed to return a value");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationDesc2() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationDesc2() :: Exception  :: " + ex);
            //ex.printStackTrace();
            throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return locationDesc;

    }

    public static Long getTamperCount(String installId, String fileId) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        long count = 0;
        String sql = "";


        try {
            conn = getConnection();
            sql = "SELECT  COUNT(nvl(TE.EVENT_CODE,0)) EVENT_COUNT"
                    + " FROM TAMPER_EVENTS TE, TAMPER_EVENT_CODE_MASTER TECM WHERE DEVICE_INSTALL_POINT_ID = " + installId
                    + " AND TE.EVENT_CODE = TECM.EVENT_CODE  AND FILE_LOAD_ID = " + fileId;

            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                count = results.getLong("EVENT_COUNT");
            } else {
                throw new SQLException("getTamperCount failed to return a value");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getTamperCount() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: getTamperCount() :: Exception  :: " + ex);
            throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return count;

    }

    public static Long getInstaCount(String fileId) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        long count = 0;
        String sql = "";


        try {
            conn = getConnection();
            sql = "SELECT count(*) cnt FROM INST_PARAMS WHERE FILE_LOAD_ID = " + fileId;

            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                count = results.getLong("cnt");
            } else {
                throw new SQLException("getInstaCount failed to return a value");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getInstaCount() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: getInstaCount() :: Exception  :: " + ex);
            throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }
        }
        return count;

    }

    public static long stringToLong(String str) throws NumberFormatException {
        long val = -1;
        try {
            if (!isBlank(str)) {
                val = Long.parseLong(str.trim());
            }
        } catch (NumberFormatException nfe) {
            //nfe.printStackTrace();
            //throw nfe;
        }
        return val;
    }

    public static double stringToDouble(String str) throws NumberFormatException {
        double val = -1;
        try {
            if (!isBlank(str)) {
                val = Double.parseDouble(str.trim());
            }
        } catch (NumberFormatException nfe) {
            //nfe.printStackTrace();
            //throw nfe;
        }
        return val;
    }
    public static double parseStringToDouble(String value) {
    return value == null || value.isEmpty() ? 0.0 : Double.parseDouble(value);
}

    public static long minsToMilliSecs(long mins) {
        return (mins * 60 * 1000);
    } // End of method

    public static long milliSecsToMin(long milliSecs) {
        return (milliSecs / (60 * 1000));
    } // End of method

    public static Date addMinsToDate(Date dt, long mins) {
        java.sql.Date newDt = null;
        try {
            long milliSecs = dt.getTime();
            milliSecs = milliSecs + minsToMilliSecs(mins);
            newDt = new java.sql.Date(milliSecs);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return newDt;
    } // End of method

    /**
     * This public static API use to get current SQL Date
     *
     * @return	current date of a corresponding connected database
     */
    public static java.sql.Date getCurrentDate() {
        java.sql.Date dt = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Timestamp ts = getCurrentTimestamp(conn);
            if (ts != null) {
                dt = new java.sql.Date(ts.getTime());
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getCurrentDate() :: Exception  :: " + ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception ex) {
            }
        }
        return dt;
    }

    /**
     * This public static API use to get currentTimestamp attribute
     *
     * @param conn	java.sql.Connection reference
     * @return	current Timestamp of a connected database
     */
    public static Timestamp getCurrentTimestamp(Connection conn) {
        Timestamp returnTimestamp = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT SYSDATE FROM DUAL");
            rs = ps.executeQuery();
            if (rs.next()) {
                returnTimestamp = rs.getTimestamp(1);
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getCurrentTimestamp() :: Exception  :: " + ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }
        return returnTimestamp;

    } // End of method

    /**
     * This public static API use to get current SQL Date
     *
     * @return	current date of a corresponding connected database
     */
    public static java.sql.Date getCurrentDate(Connection conn) {
        java.sql.Date dt = null;
        try {
            Timestamp ts = getCurrentTimestamp(conn);
            if (ts != null) {
                dt = new java.sql.Date(ts.getTime());
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getCurrentDate() :: Exception  :: " + ex);
        }
        return dt;

    } //End Of Method

    public static String getSYSParameter(String type) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String urlString = null;

        try {
            conn = getConnection();
            pst = conn.prepareStatement("SELECT PARAMETER_VALUE FROM SYSTEM_PARAMETER WHERE PARAMETER_NAME = ?");

            pst.setString(1, type);

            rs = pst.executeQuery();
            if (rs.next()) {
                urlString = rs.getString("PARAMETER_VALUE");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }

            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

            try {
                if (conn != null) {
                    conn = null;
                }
            } catch (Exception e) {
            }
        }
        return urlString;
    } // End Of String

    public static boolean checkObject(String deviceList, String ip) throws Exception {
        PreparedStatement st = null;
        StringBuffer sql = null;
        ResultSet rs = null;
        boolean exec = false;
        Connection conn = null;
        try {
            conn = getConnection();
            sql = new StringBuffer();
            sql.append(" SELECT count(1) FROM DEVICE D,DEVICE DM,DEVICE_CONNECTION DC WHERE"
                    + " D.DEVICE_ID=DC.DEVICE_ID AND DM.DEVICE_ID =DC.COMM_DEVICE_ID "
                    + "AND DM.IPADDRESS LIKE '" + ip + "%' "
                    + "and d.device_id in(" + deviceList + ")");
            st = conn.prepareStatement(sql.toString());
            rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    exec = true;
                }
            }

        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            rs.close();
            st.close();
            conn.close();

        }
        return exec;
    }

    /*public API to get the query string for where clause
     *@param column name, column value, dataType and operator
     *@returns String
     */
    public static String getQueryConditionString(String colName, String value, String dataType, String operator) {
        StringBuffer str = new StringBuffer();

        try {
            /*str.append(" AND ");
             str.append(colName);
             str.append(operator);
            
             if (dataType.equals("Date"))
             {
             str.append("TO_DATE (");
             str.append(" '"+ value +"' ");
             str.append(",'yyyy/mm/dd HH24:MI') ");
             }
             else
             str.append(" '"+ value +"' ");*/
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return str.toString();
    } //End Of Method

    /* Method gets the InstallPoint type optionString from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
    public static String getInstallPointTypeOptionString(LinkedList installPointTypeList, String selectedStr) throws Exception {
        InstallPointTypeBean installPointTypeBeanObj = null;
        Iterator itrt;
        StringBuffer strBuff = new StringBuffer();

        try {
            strBuff.append("<option selected value = -1> All </option>");
            if (installPointTypeList != null && installPointTypeList.size() > 0) {
                itrt = installPointTypeList.iterator();
                while (itrt.hasNext()) {
                    installPointTypeBeanObj = (InstallPointTypeBean) itrt.next();
                    if (installPointTypeBeanObj.getInstallPointTypeId() != 0) {
                        if (Integer.valueOf(selectedStr).intValue() == (installPointTypeBeanObj.getInstallPointTypeId())) {
                            strBuff.append("<option selected value = " + installPointTypeBeanObj.getInstallPointTypeId() + ">" + installPointTypeBeanObj.getInstallPointTypeCd() + "</option>");
                        } else {
                            strBuff.append("<option value = " + installPointTypeBeanObj.getInstallPointTypeId() + ">" + installPointTypeBeanObj.getInstallPointTypeCd() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method
    /* Method gets the InstallPoint type optionString from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/

    public static String getInstallPointTypeOptionString_feddtc(LinkedList installPointTypeList, String selectedStr) throws Exception {
        InstallPointTypeBean installPointTypeBeanObj = null;
        Iterator itrt;
        StringBuffer strBuff = new StringBuffer();

        try {
            //strBuff.append("<option selected value = -1> All </option>");
            if (installPointTypeList != null && installPointTypeList.size() > 0) {
                itrt = installPointTypeList.iterator();
                while (itrt.hasNext()) {
                    installPointTypeBeanObj = (InstallPointTypeBean) itrt.next();
                    if (installPointTypeBeanObj.getInstallPointTypeId() != 0) {
                        if (Integer.valueOf(selectedStr).intValue() == (installPointTypeBeanObj.getInstallPointTypeId())) {
                            strBuff.append("<option selected value = " + installPointTypeBeanObj.getInstallPointTypeId() + ">" + installPointTypeBeanObj.getInstallPointTypeCd() + "</option>");
                        } else {
                            strBuff.append("<option value = " + installPointTypeBeanObj.getInstallPointTypeId() + ">" + installPointTypeBeanObj.getInstallPointTypeCd() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method
    /*public method to get the System Messages
     @RETURN SystemMessageData Bean */

    public static SystemMessageData getSystemMessageDetails(String sysMessageCd) throws Exception {
        SystemMessageData systemMessageData = null;
        try {
            SystemDelegate systemDelegate = new SystemManager();
            systemMessageData = systemDelegate.getSystemMessageDetails(sysMessageCd);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " ApplicationUtils :: getSystemMessageDetails() :: Exception :: " + ex);
        }
        return systemMessageData;
    }

    public static String readStreamData(InputStream stream, long fileSize) throws Exception {

        byte[] byteData = null;
        String strFileContent = null;
        try {
            byte[] fileChunk = new byte[BLOCK_SIZE];
            ByteArrayOutputStream fileData = new ByteArrayOutputStream();
            long bytesToRead = fileSize;
            while (bytesToRead > 0) {
                stream.read(fileChunk);

                if (bytesToRead >= BLOCK_SIZE) {
                    fileData.write(fileChunk);
                } else {
                    fileData.write(fileChunk, 0, (int) bytesToRead);
                }

                bytesToRead -= BLOCK_SIZE;
            }

            strFileContent = new String(fileData.toString());
            //byteData = fileData.toByteArray();
            fileData.close();
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        //return byteData;
        return strFileContent;
    }

    /**
     * This public static API use to convert java.lang.String to java.sql.Date.
     *
     * @param dateStr	date java.lang.String
     * @param format	format of date
     * @return	java.sql.Date object
     */
    public static java.sql.Date stringToDate(String dateStr, String format) {
        java.sql.Date dt = null;
        try {
            if (validateString(format) && validateString(dateStr)) {
                SimpleDateFormat formatSpec = new SimpleDateFormat(format);
                dt = new java.sql.Date(formatSpec.parse(dateStr).getTime());
            }
        } catch (Exception ex) {
           // logger.log(Level.ERROR, "ApplicationUtils :: stringToDate() :: Exception  :: " + ex);
        }
        return dt;
    }

    /**
     * Public API to stripSpecialChars value in String Replace: by &quot; &#39;
     * &amp; &lt; &gt;
     *
     * @param objectName	the String object
     * @return	the String object
     *
     */
    public static String escapeString(String objectName) {
        try {
            if (!isBlank(objectName)) {
                objectName = objectName.replaceAll("&", "&amp;");
                objectName = objectName.replaceAll("\"", "&quot;");
                objectName = objectName.replaceAll("'", "&#39;");
                objectName = objectName.replaceAll("<", "&lt;");
                objectName = objectName.replaceAll(">", "&gt;");
                //objectName = objectName.replaceAll(" ","&nbsp;");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: escapeString() :: Exception  :: " + ex);
        }
        return objectName;
    }

    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
    public static String getLocationTypeOptionString(LinkedList locationTypeList, String selectedStr) throws Exception {
        LocationTypeBean locTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getLocationTypeOptionString() :: ");
            strBuff.append("<option selected value = -1>- Select -</option>");
            if (locationTypeList != null && locationTypeList.size() > 0) {
                itr = locationTypeList.iterator();
                while (itr.hasNext()) {
                    locTypeBeanObj = (LocationTypeBean) itr.next();
                    if (locTypeBeanObj.getLocationTypeId() != 0) {
                        if (Integer.valueOf(selectedStr).intValue() == (locTypeBeanObj.getLocationTypeId())) {
                            strBuff.append("<option selected value = " + locTypeBeanObj.getLocationTypeId() + ">" + locTypeBeanObj.getLocationTypeDescription() + "</option>");
                        } else {
                            strBuff.append("<option value = " + locTypeBeanObj.getLocationTypeId() + ">" + locTypeBeanObj.getLocationTypeDescription() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getLocationTypeOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
    public static String getDeviceTypeOptionString(LinkedList deviceTypeList, String selectedStr) throws Exception {
        DeviceTypeBean devTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getDeviceTypeOptionString() :: ");
            strBuff.append("<option selected value = -1>- Select -</option>");
            if (deviceTypeList != null && deviceTypeList.size() > 0) {
                itr = deviceTypeList.iterator();
                while (itr.hasNext()) {
                    devTypeBeanObj = (DeviceTypeBean) itr.next();
                    if (devTypeBeanObj.getDeviceTypeId() != 0) {
                        if (Integer.valueOf(selectedStr).intValue() == (devTypeBeanObj.getDeviceTypeId())) {
                            strBuff.append("<option selected value = " + devTypeBeanObj.getDeviceTypeId() + ">" + devTypeBeanObj.getDeviceTypeCd() + " - " + devTypeBeanObj.getDeviceTypeDesc() + "</option>");
                        } else {
                            strBuff.append("<option value = " + devTypeBeanObj.getDeviceTypeId() + ">" + devTypeBeanObj.getDeviceTypeCd() + " - " + devTypeBeanObj.getDeviceTypeDesc() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getDeviceTypeOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getModemTechTypeOptionString(LinkedList modemTypeList, String selectedStr) throws Exception {
        ModemTechTypeBean modemTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getModemTechTypeOptionString() :: ");
            strBuff.append("<option selected value = -1>- Select -</option>");
            if (modemTypeList != null && modemTypeList.size() > 0) {
                itr = modemTypeList.iterator();
                while (itr.hasNext()) {
                    modemTypeBeanObj = (ModemTechTypeBean) itr.next();
                    if (modemTypeBeanObj.getMODEM_TECH_ID() != 99) {
                        if (Integer.valueOf(selectedStr).intValue() == (modemTypeBeanObj.getMODEM_TECH_ID())) {
                            strBuff.append("<option selected value = " + modemTypeBeanObj.getMODEM_TECH_ID() + ">" + modemTypeBeanObj.getMODEM_TECH_CD() + " - " + modemTypeBeanObj.getMODEM_TECH_DESC() + "</option>");
                        } else {
                            strBuff.append("<option value = " + modemTypeBeanObj.getMODEM_TECH_ID() + ">" + modemTypeBeanObj.getMODEM_TECH_CD() + " - " + modemTypeBeanObj.getMODEM_TECH_DESC() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getModemTechTypeOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
    public static String getDeviceTypeManufacturerOptionString(LinkedList deviceTypeMfgList, String selectedStr) throws Exception {
        DeviceTypeBean devTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getDeviceTypeManufacturerOptionString() :: ");
            strBuff.append("<option selected value = -1> All </option>");
            if (deviceTypeMfgList != null && deviceTypeMfgList.size() > 0) {
                itr = deviceTypeMfgList.iterator();
                while (itr.hasNext()) {
                    devTypeBeanObj = (DeviceTypeBean) itr.next();
                    if (devTypeBeanObj.getDeviceMfgName() != null) {
                        if (selectedStr.equals(devTypeBeanObj.getDeviceMfgName())) {
                            strBuff.append("<option selected value = \"" + devTypeBeanObj.getDeviceMfgName() + "\">" + devTypeBeanObj.getDeviceMfgName() + "</option>");
                        } else {
                            strBuff.append("<option value =\"" + devTypeBeanObj.getDeviceMfgName() + "\">" + devTypeBeanObj.getDeviceMfgName() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getDeviceTypeManufacturerOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getDeviceTypeCdOptionString(LinkedList deviceTypeMfgList, String selectedStr) throws Exception {
        DeviceTypeBean devTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getDeviceTypeCdOptionString() :: ");
            strBuff.append("<option selected value = -1> All </option>");
            if (deviceTypeMfgList != null && deviceTypeMfgList.size() > 0) {
                itr = deviceTypeMfgList.iterator();
                while (itr.hasNext()) {
                    devTypeBeanObj = (DeviceTypeBean) itr.next();
                    if (devTypeBeanObj.getDeviceTypeCd() != null) {
                        if (selectedStr.equals(devTypeBeanObj.getDeviceTypeCd())) {
                            strBuff.append("<option selected value = \"" + devTypeBeanObj.getDeviceTypeCd() + "\"> " + devTypeBeanObj.getDeviceTypeCd() + "-" + devTypeBeanObj.getDeviceMfgName() + "</option>");
                        } else {
                            strBuff.append("<option value =\"" + devTypeBeanObj.getDeviceTypeCd() + "\">" + devTypeBeanObj.getDeviceTypeCd() + "-" + devTypeBeanObj.getDeviceMfgName() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getDeviceTypeCdOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getDLMSDeviceTypeCdOptionString(LinkedList deviceTypeMfgList, String selectedStr) throws Exception {
        DeviceTypeBean devTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getDeviceTypeCdOptionString() :: ");
            strBuff.append("<option selected value = -1> - Select - </option>");
            if (deviceTypeMfgList != null && deviceTypeMfgList.size() > 0) {
                itr = deviceTypeMfgList.iterator();
                while (itr.hasNext()) {
                    devTypeBeanObj = (DeviceTypeBean) itr.next();
                    if (devTypeBeanObj.getDeviceTypeCd() != null) {
                        if (selectedStr.equals(devTypeBeanObj.getDeviceTypeCd())) {
                            strBuff.append("<option selected value = \"" + devTypeBeanObj.getDeviceTypeCd() + "\"> " + devTypeBeanObj.getDeviceTypeCd() + "-" + devTypeBeanObj.getDeviceMfgName() + "</option>");
                        } else {
                            strBuff.append("<option value =\"" + devTypeBeanObj.getDeviceTypeCd() + "\">" + devTypeBeanObj.getDeviceTypeCd() + "-" + devTypeBeanObj.getDeviceMfgName() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getDeviceTypeCdOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getDeviceTypeIdOptionString(LinkedList deviceTypeMfgList, String selectedStr) throws Exception {
        DeviceTypeBean devTypeBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getDeviceTypeIdOptionString() :: ");

            if (deviceTypeMfgList != null && deviceTypeMfgList.size() > 0) {
                itr = deviceTypeMfgList.iterator();
                while (itr.hasNext()) {
                    devTypeBeanObj = (DeviceTypeBean) itr.next();
                    if (devTypeBeanObj.getDeviceTypeId() > 0) {
                        if (selectedStr.equals(devTypeBeanObj.getDeviceTypeId() + "")) {
                            strBuff.append("<option selected value = \"" + devTypeBeanObj.getDeviceTypeId() + "\">" + devTypeBeanObj.getDeviceMfgName() + "</option>");
                        } else {
                            strBuff.append("<option value =\"" + devTypeBeanObj.getDeviceTypeId() + "\">" + devTypeBeanObj.getDeviceMfgName() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getDeviceTypeIdOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    /* *
     *Method returns the baud rate option string
     *@param			String
     *@returns			String
     */
    public static String getBaudRateOptionString(String strBaudRate) throws Exception {
        LinkedList baudRateList;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getBaudRateOptionString() :: ");

            baudRateList = new LinkedList();
            baudRateList.add("110");
            baudRateList.add("300");
            baudRateList.add("1200");

            baudRateList.add("2400");
            baudRateList.add("4800");
            baudRateList.add("9600");
            baudRateList.add("19200");
            baudRateList.add("38400");
            baudRateList.add("57600");
            baudRateList.add("115200");
            baudRateList.add("230400");
            baudRateList.add("460800");
            baudRateList.add("961600");

            logger.log(Level.INFO, "ApplicationUtils:: getBaudRateOptionString() :: baudRateList size :: " + baudRateList.size());

            itr = baudRateList.iterator();

            strBuff.append("<option selected value = -1>- Select -</option>");

            while (itr.hasNext()) {
                String baudRate = (String) itr.next();
                if (baudRate != null) {
                    if (baudRate.equals(strBaudRate)) {
                        strBuff.append("<option selected value = " + baudRate + ">" + baudRate + "</option>");
                    } else {
                        strBuff.append("<option value = " + baudRate + ">" + baudRate + "</option>");
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getBaudRateOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }
        return strBuff.toString();
    }

    /**
     * Public API to get columns name and values string
     *
     * @param attributeMap	the HaskMap object
     * @return String
     * @throws Exception	if an error occurs
     */
    public static String generateColumnString(HashMap attributeMap) throws Exception {
        StringBuffer columnStr = new StringBuffer();
        try {
            AttributeData attributeData = null;
            Set columnsSet = attributeMap.keySet();
            Iterator itr = columnsSet.iterator();
            String columnValue = "NULL";
            String columnType = "";
            Object key = null;
            while (itr.hasNext()) {
                columnValue = "NULL";
                columnType = "";
                key = (Object) itr.next();
                attributeData = (AttributeData) attributeMap.get(key);
                columnType = attributeData.getColumnType();

                if (attributeData.getColumnValue() != null) {
                    if (columnType.equals("String")) {
                        columnValue = "'" + attributeData.getColumnValue() + "'";
                    } else if (columnType.equals("Long")) {
                        columnValue = attributeData.getColumnValue() + "";
                    } else if (columnType.equals("Date")) {
                        columnValue = "to_date('" + attributeData.getColumnValue() + "','MM/DD/YYYY')";
                    } else if (columnType.equals("SYSDATE")) {
                        columnValue = " SYSDATE ";
                    } else if (columnType.equals("NULL")) {
                        columnValue = "NULL";
                    }
                }

                columnStr.append(" " + key + " = " + columnValue + " , ");
            }
        } catch (Exception ex) {
            ////ex.printStackTrace();
            logger.log(Level.ERROR, "ApplicationUtils :: generateColumnString() :: Exception  :: " + ex);
            throw ex;
        }

        return columnStr.toString();
    }

    public static String getLoadProfileOptionString(String selValue) {
        StringBuffer sb = new StringBuffer();
        try {
            if (selValue != null && selValue.equals("NO")) {
                sb.append("<option value=\"NO\" selected>No</option>");
                sb.append("<option value=\"PARTIAL\">Partial</option>");
                sb.append("<option value=\"FULL\">Full</option>");
            } else if (selValue != null && selValue.equals("PARTIAL")) {
                sb.append("<option value=\"NO\">No</option>");
                sb.append("<option value=\"PARTIAL\" selected>Partial</option>");
                sb.append("<option value=\"FULL\">Full</option>");
            } else if (selValue != null && selValue.equals("FULL")) {
                sb.append("<option value=\"NO\">No</option>");
                sb.append("<option value=\"PARTIAL\">Partial</option>");
                sb.append("<option value=\"FULL\" selected>Full</option>");
            } else {
                sb.append("<option value=\"NO\">No</option>");
                sb.append("<option value=\"PARTIAL\">Partial</option>");
                sb.append("<option value=\"FULL\">Full</option>");
            }


        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getLoadProfileOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return sb.toString();
    }

    public static java.sql.Date convertToSqlDate(String date, String hrs, String min) {
        java.sql.Date sqlDate = null;
        String strDate;
        String hours = "00";
        String mins = "00";
        try {
            if (!ApplicationUtils.isBlank(hrs)) {
                hours = hrs;
            }
            if (!ApplicationUtils.isBlank(min)) {
                mins = min;
            }
            if (!ApplicationUtils.isBlank(date)) {
                strDate = date + " " + hours + ":" + mins;
                sqlDate = ApplicationUtils.stringToDate(strDate, "dd-MMM-yyyy HH:mm");
            } else {
                sqlDate = null;
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return sqlDate;
    } // End of method

    /* Method gets the InstallPoint type drop-down optionString from the LinkedList
     * @Param			LinkedList
     * @Param			String
     * @return			String
     */
    public static String getInstallPointTypeDropDownString(LinkedList installPointTypeList, String selectedStr) throws Exception {
        InstallPointTypeBean installPointTypeBeanObj = null;
        Iterator itrt;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getDeviceDropDownString() :: metho called");

            strBuff.append("<option selected value = -1>- Select -</option>");
            if (installPointTypeList != null && installPointTypeList.size() > 0) {
                itrt = installPointTypeList.iterator();
                while (itrt.hasNext()) {
                    installPointTypeBeanObj = (InstallPointTypeBean) itrt.next();
                    if (installPointTypeBeanObj.getInstallPointTypeId() != 0) {
                        if (Integer.valueOf(selectedStr).intValue() == (installPointTypeBeanObj.getInstallPointTypeId())) {
                            strBuff.append("<option selected value = \"" + installPointTypeBeanObj.getInstallPointTypeId() + "\">" + installPointTypeBeanObj.getInstallPointTypeCd() + "</option>");
                        } else {
                            strBuff.append("<option value = \"" + installPointTypeBeanObj.getInstallPointTypeId() + "\">" + installPointTypeBeanObj.getInstallPointTypeCd() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getInstallPointTypeDropDownString() :: Exception :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    /**
     * Public static API to remove trailing whitespaces
     *
     * @param str the String
     * @return	the String
     */
    public static String rtrim(String str) {
        try {
            if (validateString(str)) {
                str = str.replaceAll("^\\s+", "");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: rtrim() :: Exception :: " + ex);
        }
        return str;
    }

    /* Method gets the data from the LinkedList
     @Param LinkedList
     @Returns Map*/
    public static LinkedHashMap getOptionMapFromList(LinkedList locationList) throws Exception {
        LocationBean locationBeanObj = null;
        Iterator itr;
        LinkedHashMap map = new LinkedHashMap();
        LinkedList list = null;
        try {
            if (locationList != null && locationList.size() > 0) {
                itr = locationList.iterator();
                while (itr.hasNext()) {

                    locationBeanObj = (LocationBean) itr.next();
                    if (!map.containsKey(locationBeanObj.getParentLocationId() + "")) {
                        list = new LinkedList();
                        list.add(locationBeanObj);
                    } else {
                        list = (LinkedList) map.get(locationBeanObj.getParentLocationId() + "");
                        list.add(locationBeanObj);
                    }
                    map.put(locationBeanObj.getParentLocationId() + "", list);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return map;
    }// End of Method

    public static String getLocationOptionString(LinkedList locationList, String selectedStr) throws Exception {
        LocationBean locBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getLocationOptionString() :: ");
            if (locationList != null && locationList.size() > 0) {
                itr = locationList.iterator();
                while (itr.hasNext()) {
                    locBeanObj = (LocationBean) itr.next();
                    if (locBeanObj.getLocationId() > 0) {
                        if (Integer.parseInt(selectedStr) == (locBeanObj.getLocationId())) {
                            strBuff.append("<option selected value = " + locBeanObj.getLocationId() + ">" + locBeanObj.getLocationDescription() + "</option>");
                        } else {
                            strBuff.append("<option value = " + locBeanObj.getLocationId() + ">" + locBeanObj.getLocationDescription() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getLocationOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }

        return strBuff.toString();
    }// End of Method

    public static String getDtcOptionALLString(LinkedList dtcList, String selectedStr) throws Exception {
        FeederDtcBean netBeanObj = new FeederDtcBean();
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getDtcOptionALLString() :: ");
            strBuff.append("<option selected value = -1>-Select-</option>");
            if (dtcList != null && dtcList.size() > 0) {
                itr = dtcList.iterator();
                while (itr.hasNext()) {
                    netBeanObj = (FeederDtcBean) itr.next();
                    if (netBeanObj.getDtcId() != null) {

                        if (selectedStr.equals(netBeanObj.getDtcId())) {
                            strBuff.append("<option selected value = " + netBeanObj.getDtcCode() + ">" + netBeanObj.getDtcId() + "-" + netBeanObj.getDtcName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + netBeanObj.getDtcCode() + ">" + netBeanObj.getDtcId() + "-" + netBeanObj.getDtcName() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getDtcOptionALLString() :: Exception  :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }

        return strBuff.toString();
    }// End of Method

    public static String getFeederOptionALLString(LinkedList feederList, String selectedStr) throws Exception {
        FeederDtcBean netBeanObj = new FeederDtcBean();
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getFeederOptionALLString() :: ");
            strBuff.append("<option selected value = -1>-Select-</option>");
            // strBuff.append("<option selected value = %>ALL</option>" );
            if (feederList != null && feederList.size() > 0) {
                itr = feederList.iterator();
                while (itr.hasNext()) {
                    netBeanObj = (FeederDtcBean) itr.next();
                    if (netBeanObj.getFeederId() != null) {

                        if (selectedStr.equals(netBeanObj.getFeederId())) {
                            strBuff.append("<option selected value = " + netBeanObj.getFeederId() + ">" + netBeanObj.getFeederCode() + "-" + netBeanObj.getFeederName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + netBeanObj.getFeederId() + ">" + netBeanObj.getFeederCode() + "-" + netBeanObj.getFeederName() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getFeederOptionALLString() :: Exception  :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }

        return strBuff.toString();
    }// End of Method

    public static String getSsOptionALLString(LinkedList ssList, String selectedStr) throws Exception {
        FeederDtcBean netBeanObj = new FeederDtcBean();
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getFeederOptionALLString() :: ");
            strBuff.append("<option selected value = -1>-Select-</option>");
            // strBuff.append("<option selected value = %>ALL</option>" );
            if (ssList != null && ssList.size() > 0) {
                itr = ssList.iterator();
                while (itr.hasNext()) {
                    netBeanObj = (FeederDtcBean) itr.next();
                    if (netBeanObj.getSsId() != null) {

                        if (selectedStr.equals(netBeanObj.getSsId())) {
                            strBuff.append("<option selected value = " + netBeanObj.getSsId() + ">" + netBeanObj.getSsCode() + "-" + netBeanObj.getSsName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + netBeanObj.getSsId() + ">" + netBeanObj.getSsCode() + "-" + netBeanObj.getSsName() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getSsOptionALLString() :: Exception  :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }

        return strBuff.toString();
    }// End of Method

    public static String getSubdivOptionALLString(LinkedList subdivList, String selectedStr) throws Exception {
        FeederDtcBean netBeanObj = new FeederDtcBean();
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getFeederOptionALLString() :: ");
            strBuff.append("<option selected value = -1>-Select-</option>");
            // strBuff.append("<option selected value = %>ALL</option>" );
            if (subdivList != null && subdivList.size() > 0) {
                itr = subdivList.iterator();
                while (itr.hasNext()) {
                    netBeanObj = (FeederDtcBean) itr.next();
                    if (netBeanObj.getLocation_id() != null) {

                        if (selectedStr.equals(netBeanObj.getLocation_id())) {
                            strBuff.append("<option selected value = " + netBeanObj.getLocation_id() + ">" + netBeanObj.getLocation_cd() + "-" + netBeanObj.getLocation_desc() + "</option>");
                        } else {
                            strBuff.append("<option value = " + netBeanObj.getLocation_id() + ">" + netBeanObj.getLocation_cd() + "-" + netBeanObj.getLocation_desc() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getSubdivOptionALLString() :: Exception  :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }

        return strBuff.toString();
    }// End of Method

    public static String getDivOptionALLString(LinkedList divList, String selectedStr) throws Exception {
        FeederDtcBean netBeanObj = new FeederDtcBean();
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getDivOptionALLString() :: ");
            strBuff.append("<option selected value = -1>-Select-</option>");
            // strBuff.append("<option selected value = %>ALL</option>" );
            if (divList != null && divList.size() > 0) {
                itr = divList.iterator();
                while (itr.hasNext()) {
                    netBeanObj = (FeederDtcBean) itr.next();
                    if (netBeanObj.getLocation_id() != null) {

                        if (selectedStr.equals(netBeanObj.getLocation_id())) {
                            strBuff.append("<option selected value = " + netBeanObj.getLocation_id() + ">" + netBeanObj.getLocation_cd() + "-" + netBeanObj.getLocation_desc() + "</option>");
                        } else {
                            strBuff.append("<option value = " + netBeanObj.getLocation_id() + ">" + netBeanObj.getLocation_cd() + "-" + netBeanObj.getLocation_desc() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getDivOptionALLString() :: Exception  :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }

        return strBuff.toString();
    }// End of Method

    public static String getLocationOptionALLString(LinkedList locationList, String selectedStr) throws Exception {
        LocationBean locBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getLocationOptionALLString() :: ");
            //strBuff.append("<option selected value = -1>-Select-</option>" );
            // strBuff.append("<option selected value = %>ALL</option>" );
            if (locationList != null && locationList.size() > 0) {
                itr = locationList.iterator();
                while (itr.hasNext()) {
                    locBeanObj = (LocationBean) itr.next();
                    if (locBeanObj.getLocationId() > 0) {

                        if (Integer.parseInt(selectedStr) == (locBeanObj.getLocationId())) {
                            strBuff.append("<option selected value = " + locBeanObj.getLocationId() + ">" + locBeanObj.getLocationDescription() + "</option>");
                        } else {
                            strBuff.append("<option value = " + locBeanObj.getLocationId() + ">" + locBeanObj.getLocationDescription() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getLocationOptionALLString() :: Exception  :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }

        return strBuff.toString();
    }// End of Method

    /*
     * public API to get the all parent locations for a given location
     * @param SearchBeanObj & String uiType
     * @returns Object SearchBean Object
     * throws Exception
     */
    public static Object getParentLocations(SearchBean searchBeanObj, String uiType) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet result = null;
        StringBuffer sqlStr = null;
        StringBuffer sqlSubquery = null;
        StringBuffer fromSubStr = null;
        StringBuffer whereSubStr = null;

        try {
            logger.log(Level.INFO, "ApplicationUtil :: getParentLocations() :: method called");

            sqlSubquery = new StringBuffer();
            fromSubStr = new StringBuffer();
            whereSubStr = new StringBuffer();

            //uiType = "sadfsdf";
            if (uiType != null) {
                if (uiType.equals("redirectNetwork")) {
                    if (searchBeanObj.getInstallPointType() != null && searchBeanObj.getInstallPointType().equals("1")) {

                        fromSubStr.append(" ,FEEDER ");
                        fromSubStr.append(" ,SUBSTATION instype ");
                        fromSubStr.append(" ,SUBSTATION_FEEDER ");
                        whereSubStr.append(" AND FEEDER.FEEDER_ID = DEV_INSTALL_POINT.FEEDER_ID");
                        whereSubStr.append(" AND FEEDER.FEEDER_ID = SUBSTATION_FEEDER.FEEDER_ID");
                        whereSubStr.append(" AND instype.SUBSTATION_ID = SUBSTATION_FEEDER.SUBSTATION_ID");
                        whereSubStr.append(" AND FEEDER.FEEDER_ID = " + searchBeanObj.getNumberId());
                    } else if (searchBeanObj.getInstallPointType() != null && searchBeanObj.getInstallPointType().equals("2")) {
                        fromSubStr.append(" ,TRANSFORMER instype");
                        whereSubStr.append(" AND instype.TRANSFORMER_ID = DEV_INSTALL_POINT.TRANSFORMER_ID");
                        whereSubStr.append(" AND instype.TRANSFORMER_ID = " + searchBeanObj.getNumberId());
                    } else if (searchBeanObj.getInstallPointType() != null && searchBeanObj.getInstallPointType().equals("3")) {
                        fromSubStr.append(" ,CONSUMER instype");
                        whereSubStr.append(" AND instype.CONSUMER_ID = DEV_INSTALL_POINT.CONSUMER_ID");
                        whereSubStr.append(" AND instype.CONSUMER_ID = " + searchBeanObj.getNumberId());
                    }
                }
            }

            if (!ApplicationUtils.isBlank(fromSubStr.toString()) && !ApplicationUtils.isBlank(whereSubStr.toString())) {
                sqlSubquery.append(" SELECT instype.LOCATION_ID ");
                sqlSubquery.append(" FROM DEV_INSTALL_POINT ");
                sqlSubquery.append(fromSubStr.toString());
                sqlSubquery.append(" WHERE DEV_INSTALL_POINT.INSTALL_POINT_TYPE_ID = " + searchBeanObj.getInstallPointType());
                sqlSubquery.append(whereSubStr.toString());
            } else {
                sqlSubquery.append("-1");
            }

            sqlStr = new StringBuffer();
            sqlStr.append(" SELECT LOCATION.LOCATION_ID ,LOCATION.LOCATION_TYPE_ID ");
            sqlStr.append(" FROM LOCATION ");
            sqlStr.append(" WHERE 1=1 ");
            sqlStr.append(" AND PARENT_LOCATION_ID > 0  ");
            sqlStr.append(" START WITH LOCATION_ID IN ( " + sqlSubquery.toString() + " )");
            sqlStr.append(" CONNECT BY PRIOR PARENT_LOCATION_ID = LOCATION_ID ");
            sqlStr.append(" ORDER BY LOCATION_ID ASC");

            logger.log(Level.INFO, "ApplicationUtil :: getParentLocations() :: SQL STR :: " + sqlStr.toString());
            conn = getConnection();
            pst = conn.prepareStatement(sqlStr.toString());
            result = pst.executeQuery();

            while (result.next()) {
                if (result.getLong("LOCATION_TYPE_ID") == 1) {
                    searchBeanObj.setZoneId(result.getLong("LOCATION_ID"));
                } else if (result.getLong("LOCATION_TYPE_ID") == 2) {
                    searchBeanObj.setCircleId(result.getLong("LOCATION_ID"));
                } else if (result.getLong("LOCATION_TYPE_ID") == 3) {
                    searchBeanObj.setDivisionId(result.getLong("LOCATION_ID"));
                } else if (result.getLong("LOCATION_TYPE_ID") == 4) {
                    searchBeanObj.setSubDivisionId(result.getLong("LOCATION_ID"));
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getParentLocations() :: Exception  :: " + ex);
            //ex.printStackTrace();
            throw ex;
        } finally {
            try {
                if (result != null) {
                    result.close();
                }

                if (pst != null) {
                    pst.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }

        return searchBeanObj;
    }

    public static LinkedHashMap getParameterCodesMap() {
        LinkedHashMap codesMap = new LinkedHashMap();
        try {
            codesMap.put("P1-1-1-1-0", "R-phase");
            codesMap.put("P1-1-2-1-0", "Y-phase");
            codesMap.put("P1-1-3-1-0", "B-phase");
            codesMap.put("P1-2-1-1-0", "R-phase");
            codesMap.put("P1-2-2-1-0", "Y-phase");
            codesMap.put("P1-2-3-1-0", "B-phase");
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return codesMap;
    }

    public static Timestamp stringToTimestamp(String dt, String format) throws java.text.ParseException {
        Timestamp ts = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        java.util.Date d = sdf.parse(dt);
        ts = new Timestamp(d.getTime());
        return ts;
    }

    public static LinkedList getAccessBUList(String locationId) {

        LinkedList buList = new LinkedList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        BuBean buBeanObj = null;
        try {
            conn = getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT LBM.BU_CODE, LM.LOCATION_ID, LM.LOCATION_CD, LM.LOCATION_DESC ");
            sql.append("FROM LOCATION_BU_MAPPING LBM, LOCATION LM ");
            sql.append("WHERE LBM.LOC_ID IN (" + locationId + ") ");
            sql.append("AND LBM.LOC_ID = LM.LOCATION_ID AND BU_STATUS = 'Y' UNION ");
            sql.append("SELECT LBM.BU_CODE, LM.LOCATION_ID, LM.LOCATION_CD, LM.LOCATION_DESC ");
            sql.append("FROM LOCATION_BU_MAPPING LBM, LOCATION LM ");
            sql.append("WHERE LOC_ID IN (SELECT LOCATION_ID FROM LOCATION WHERE PARENT_LOCATION_ID IN (" + locationId + ")) ");
            sql.append("AND LBM.LOC_ID = LM.LOCATION_ID AND BU_STATUS = 'Y' UNION ");
            sql.append("SELECT LBM.BU_CODE, LM.LOCATION_ID, LM.LOCATION_CD, LM.LOCATION_DESC ");
            sql.append("FROM LOCATION_BU_MAPPING LBM, LOCATION LM ");
            sql.append("WHERE LOC_ID IN (SELECT LOCATION_ID FROM LOCATION WHERE PARENT_LOCATION_ID IN ( ");
            sql.append("SELECT LOCATION_ID FROM LOCATION WHERE PARENT_LOCATION_ID IN (" + locationId + "))) ");
            sql.append("AND LBM.LOC_ID = LM.LOCATION_ID AND BU_STATUS = 'Y' UNION ");
            sql.append("SELECT LBM.BU_CODE, LM.LOCATION_ID, LM.LOCATION_CD, LM.LOCATION_DESC ");
            sql.append("FROM LOCATION_BU_MAPPING LBM, LOCATION LM ");
            sql.append("WHERE LOC_ID IN (SELECT LOCATION_ID FROM LOCATION WHERE PARENT_LOCATION_ID IN ( ");
            sql.append("SELECT LOCATION_ID FROM LOCATION WHERE PARENT_LOCATION_ID IN ( ");
            sql.append("SELECT LOCATION_ID FROM LOCATION WHERE PARENT_LOCATION_ID IN (" + locationId + ")))) ");
            sql.append("AND LBM.LOC_ID = LM.LOCATION_ID AND BU_STATUS = 'Y' ");

            logger.log(Level.INFO, "ApplicationUtils :: getAccessBUList() :: SQL :: " + sql.toString());
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                buBeanObj = new BuBean();
                buBeanObj.setBuCode(rs.getString("BU_CODE"));
                buBeanObj.setLocationId(rs.getInt("LOCATION_ID"));
                buBeanObj.setLocationCode(rs.getString("LOCATION_CD"));
                buBeanObj.setLocationDescription(rs.getString("LOCATION_DESC"));
                buList.add(buBeanObj);
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getAccessBUList() :: Exception  :: " + ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }

        return buList;
    }

    public static String getAccessBuOptionString(LinkedList buList, String selectedStr) throws Exception {

        BuBean buBeanObj = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtil :: getAccessBuOptionString() :: ");
            if (buList != null && buList.size() > 0) {
                itr = buList.iterator();
                while (itr.hasNext()) {
                    buBeanObj = (BuBean) itr.next();
                    if (buBeanObj.getLocationId() > 0) {
                        if (Integer.parseInt(selectedStr) == (buBeanObj.getLocationId())) {
                            strBuff.append("<option selected value = " + buBeanObj.getBuCode() + ">" + buBeanObj.getLocationDescription() + "</option>");
                        } else {
                            strBuff.append("<option value = " + buBeanObj.getLocationId() + ">" + buBeanObj.getLocationDescription() + "</option>");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtil :: getAccessBuOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }

        return strBuff.toString();

    }

    /**
     * Public API to Convert x page size
     *
     * @param
     * @return	the double object
     *
     */
    public static double getXPageSizeForDetailReport() {
        double report_x_size = Double.parseDouble(ApplicationConstants.RPT_DETAIL_PAGE_SIZE_X_INCHES);
        String reportSizeStr = "";
        try {
            if (System.getProperty("RPT_DETAIL_PAGE_SIZE_X_INCHES") != null) {
                reportSizeStr = System.getProperty("RPT_DETAIL_PAGE_SIZE_X_INCHES");
                if (reportSizeStr != null && reportSizeStr.length() > 0) {
                    report_x_size = Double.parseDouble(reportSizeStr);
                    report_x_size = convertInchesToPoints(report_x_size);
                }
            }
            report_x_size = convertInchesToPoints(report_x_size);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getXPageSizeForDetailReport() :: Exception  :: " + ex);
        }
        return report_x_size;
    }

    /**
     * Public API to Convert y page size
     *
     * @param
     * @return	the double object
     *
     */
    public static double getYPageSizeForDetailReport() {
        double report_y_size = Double.parseDouble(ApplicationConstants.RPT_DETAIL_PAGE_SIZE_Y_INCHES);
        String reportSizeStr = "";
        try {
            if (System.getProperty("RPT_DETAIL_PAGE_SIZE_Y_INCHES") != null) {
                reportSizeStr = System.getProperty("RPT_DETAIL_PAGE_SIZE_Y_INCHES");
                if (reportSizeStr != null && reportSizeStr.length() > 0) {
                    report_y_size = Double.parseDouble(reportSizeStr);
                    report_y_size = convertInchesToPoints(report_y_size);
                }
            }
            report_y_size = convertInchesToPoints(report_y_size);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getYPageSizeForDetailReport() :: Exception  :: " + ex);
        }
        return report_y_size;
    }

    /**
     * Public API to Convert inches to points
     *
     * @param objectName	the double name
     * @return	the double name
     *
     *
     *
     */
    public static double convertInchesToPoints(double objectName) {
        try {
            if (objectName > 0) {

                objectName = objectName * 72;
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: convertInchesToPoints() :: Exception  :: " + ex);
        }
        return objectName;
    }

    private static boolean getBooleanValue(String bStr) {
        boolean isBoolean;
        if (bStr.equals("Y")) {
            isBoolean = true;
        } else {
            isBoolean = false;
        }
        return isBoolean;
    }

    /**
     * This public static API use to convertjava.sql.Date into String.
     *
     * @param ts, java.sql.Date	reference
     * @param format, formate which is suppose to display
     * @return String, java.lang.String value of java.sql.Date object
     */
    public static String dateToString(java.sql.Date ts, String format) {
        String dateString = null;
        if (isBlank(format) || ts == null) {
            return null;
        }
        try {
            SimpleDateFormat formatSpec = new SimpleDateFormat(format);
            dateString = formatSpec.format(ts);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return dateString;
    }

    public static Date timeStampToDate(Timestamp timestamp) {
        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
        return new Date(milliseconds);
    }

    public static String timeStampToString(Timestamp ts, String outFormat) {
        if (isBlank(outFormat)) {
            outFormat = ApplicationConstants.SQL_DATE_FORMAT;
        }

        if (ts == null) {
            return null;
        }

        Date dt = timeStampToDate(ts);

        return dateToString(dt, outFormat);
    }

    public static String timeStampToString(String ts, String inFormat, String outFormat) {
        String dateString = null;
        if (isBlank(inFormat)) {
            inFormat = ApplicationConstants.TIMESTAMP_DATE_FORMAT;
        }
        if (isBlank(outFormat)) {
            outFormat = ApplicationConstants.SQL_DATE_FORMAT;
        }
        if (ts == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
            java.util.Date d = sdf.parse(ts);
            sdf = new SimpleDateFormat(outFormat);
            dateString = sdf.format(d);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return dateString;
    }

    public static String getAccessBuList(SecUserBean secUserBean) {

        return null;
    }

    public static String getAccessLocationListStr(LinkedList buAccessList) {
        String buAccessString = null;
        Iterator itr = buAccessList.iterator();
        while (itr.hasNext()) {
            if (buAccessString == null) {
                buAccessString = (String) itr.next();
            } else {
                buAccessString += ", " + (String) itr.next();
            }
        }

        return buAccessString;
    }

    public static String getMasterCodeOptionString(LinkedList masterList, String selectedStr) throws Exception {
        MasterBean masterBean = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getMasterCodeOptionString() :: ");
            strBuff.append("<option selected value = -1>- Select -</option>");
            if (masterList != null && masterList.size() > 0) {
                itr = masterList.iterator();
                while (itr.hasNext()) {

                    masterBean = (MasterBean) itr.next();
                    if (masterBean.getId() != 0) {
                        if (selectedStr.equals(masterBean.getCode())) {
                            strBuff.append("<option selected value = " + masterBean.getCode() + ">" + masterBean.getName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + masterBean.getCode() + ">" + masterBean.getName() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getMasterCodeOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getMasterCodeOptionStringNew(LinkedList masterList, String selectedStr) throws Exception {
        MasterBean masterBean = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getMasterCodeOptionString() :: ");
            strBuff.append("<option value = -1>- Select -</option>");
            if (masterList != null && masterList.size() > 0) {
                itr = masterList.iterator();
                while (itr.hasNext()) {

                    masterBean = (MasterBean) itr.next();
                    if (masterBean.getId() != 0) {
                        if (selectedStr.equals(masterBean.getCode())) {
                            strBuff.append("<option selected value = " + masterBean.getName() + ">" + masterBean.getName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + masterBean.getName() + ">" + masterBean.getName() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getMasterCodeOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getMasterValueNameOptionString(LinkedList masterList, String selectedStr) throws Exception {
        MasterBean masterBean = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getMasterValueNameOptionString() :: ");
            strBuff.append("<option selected value = -1>- Select -</option>");
            if (masterList != null && masterList.size() > 0) {
                itr = masterList.iterator();
                while (itr.hasNext()) {

                    masterBean = (MasterBean) itr.next();
                    if (masterBean.getId() != 0) {
                        if (selectedStr.equals(masterBean.getName())) {
                            strBuff.append("<option selected value = " + masterBean.getName() + ">" + masterBean.getName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + masterBean.getName() + ">" + masterBean.getName() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getMasterValueNameOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getMasterValueNameOptionStringProject(LinkedList masterList, String selectedStr) throws Exception {
        MasterBean masterBean = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getMasterValueNameOptionString() :: ");
            strBuff.append("<option selected value = -1>- Select -</option>");
            if (masterList != null && masterList.size() > 0) {
                itr = masterList.iterator();
                while (itr.hasNext()) {

                    masterBean = (MasterBean) itr.next();
                    if (masterBean.getId() != 0) {
                        if (ApplicationUtils.stringToLong(selectedStr) == masterBean.getId()) {
                            strBuff.append("<option selected value = " + masterBean.getId() + ">" + masterBean.getName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + masterBean.getId() + ">" + masterBean.getName() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getMasterValueNameOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getMasterIdNameOptionString(LinkedList masterList, String selectedStr) throws Exception {
        MasterBean masterBean = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getMasterCodeOptionString() :: ");

            strBuff.append("<option selected value = -1>- Select -</option>");
            if (masterList != null && masterList.size() > 0) {

                itr = masterList.iterator();
                while (itr.hasNext()) {

                    masterBean = (MasterBean) itr.next();
                    if (masterBean.getId() != 0) {

                        if (selectedStr.equals(masterBean.getId() + "")) {
                            strBuff.append("<option selected value = " + masterBean.getId() + ">" + masterBean.getCode() + " - " + masterBean.getName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + masterBean.getId() + ">" + masterBean.getCode() + " - " + masterBean.getName() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getMasterCodeOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getFunctionIdNameOptionString(LinkedList masterList, String selectedStr) throws Exception {
        SecFunctionBean secFunctionBean = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getFunctionIdNameOptionString() :: ");

            strBuff.append("<option selected value = -1>- Select -</option>");
            if (masterList != null && masterList.size() > 0) {

                itr = masterList.iterator();
                while (itr.hasNext()) {

                    secFunctionBean = (SecFunctionBean) itr.next();
                    if (secFunctionBean.getFunctionId() != 0) {

                        if (selectedStr.equals(secFunctionBean.getFunctionId() + "")) {
                            strBuff.append("<option selected value = " + secFunctionBean.getFunctionId() + ">" + secFunctionBean.getFunctionName() + "</option>");
                        } else {
                            strBuff.append("<option value = " + secFunctionBean.getFunctionId() + ">" + secFunctionBean.getFunctionName() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getFunctionIdNameOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getMasterIdNameALLOptionString(LinkedList masterList, String selectedStr) throws Exception {
        MasterBean masterBean = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getMasterCodeOptionString() :: ");
            //strBuff.append("<option selected value = -1>- Select -</option>");
            //  strBuff.append("<option value = '%'>ALL</option>");
            if (masterList != null && masterList.size() > 0) {

                itr = masterList.iterator();
                while (itr.hasNext()) {

                    masterBean = (MasterBean) itr.next();
                    if (masterBean.getId() != 0) {
                        if (selectedStr.equals(masterBean.getId() + "")) {
                            strBuff.append("<option selected value = " + masterBean.getId() + ">" + masterBean.getName() + " - " + masterBean.getCode() + "</option>");
                        } else {
                            strBuff.append("<option value = " + masterBean.getId() + ">" + masterBean.getName() + " - " + masterBean.getCode() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getMasterCodeOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getMasterIdNameALLOptionStringZone(LinkedList masterList, String selectedStr) throws Exception {
        MasterBean masterBean = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getMasterCodeOptionString() :: ");
            strBuff.append("<option selected value = -1>- Select -</option>");
            //  strBuff.append("<option value = '%'>ALL</option>");
            if (masterList != null && masterList.size() > 0) {

                itr = masterList.iterator();
                while (itr.hasNext()) {

                    masterBean = (MasterBean) itr.next();
                    if (masterBean.getId() != 0) {
                        if (selectedStr.equals(masterBean.getId() + "")) {
                            strBuff.append("<option selected value = " + masterBean.getId() + ">" + masterBean.getName() + " - " + masterBean.getCode() + "</option>");
                        } else {
                            strBuff.append("<option value = " + masterBean.getId() + ">" + masterBean.getName() + " - " + masterBean.getCode() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getMasterCodeOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getMasterIdNameALLOptionString1(LinkedList masterList, String selectedStr) throws Exception {
        MasterBean masterBean = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getMasterCodeOptionString() :: ");
            //strBuff.append("<option selected value = -1>- Select -</option>");
            //  strBuff.append("<option value = '%'>ALL</option>");
            if (masterList != null && masterList.size() > 0) {

                itr = masterList.iterator();
                while (itr.hasNext()) {

                    masterBean = (MasterBean) itr.next();
                    if (masterBean.getId() != 0) {
                        if (selectedStr.equals(masterBean.getId() + "")) {
                            strBuff.append("<option selected value = " + masterBean.getId() + ">" + masterBean.getCode() + "</option>");
                        } else {
                            strBuff.append("<option value = " + masterBean.getId() + ">" + masterBean.getCode() + "</option>");
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getMasterCodeOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static String getMasterIdNameOption1String(LinkedList masterList) throws Exception {
        MasterBean masterBean = null;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getMasterCodeOptionString() :: ");


            if (masterList != null && masterList.size() > 0) {

                itr = masterList.iterator();
                while (itr.hasNext()) {
                    masterBean = (MasterBean) itr.next();
                    if (masterBean.getId() != 0) {
                        strBuff.append("<option value = " + masterBean.getId() + ">" + masterBean.getCode() + " - " + masterBean.getName() + "</option>");
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getMasterCodeOptionString() :: Exception  :: " + ex);
            //ex.printStackTrace();
        }

        return strBuff.toString();
    }// End of Method

    public static Long getRemoteProtocolID(long commDeviceTypeId) {
        long commProtocolId = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement("SELECT REMOTE_COMM_PROTOCOL_ID FROM DEVICE_TYPE_PROTOCOL WHERE COMM_DEVICE_TYPE_ID = " + commDeviceTypeId);
            rs = ps.executeQuery();
            if (rs.next()) {
                commProtocolId = rs.getLong("REMOTE_COMM_PROTOCOL_ID");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getRemoteProtocolID() :: Exception  :: " + ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
        return commProtocolId;
    }

    public static boolean fileMoveTo(String srcName, String destName) throws Exception {
        boolean moved = false;
        File destFile = new File(destName);
        String destFolder = destFile.getParent();
        boolean isCreated = createDir(destFolder);
        try {
            File f = new File(srcName);
            moved = f.renameTo(new File(destName));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: fileMoveTo() :: Exception == " + ex.getMessage());
            throw ex;
        }
        return moved;
    }

    public static boolean createDir(String path) throws Exception {
        boolean isCreate = false;
        try {
            File f = new File(path);
            isCreate = f.mkdirs();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: makeDir() :: Exception == " + ex);
            throw ex;
        }
        return isCreate;
    }

    public static boolean checkRunningCommand(long deviceId) {
        Connection con = null;
        PreparedStatement pst = null;
        StringBuffer sql = new StringBuffer();
        Boolean check = false;
        int count = 0;

        try {
            sql.append(" SELECT COUNT(1) FROM ");
            sql.append(" API_IN_PROCESS ");
            sql.append(" WHERE DEVICE_TYPE_ID = ( SELECT dEVICE_TYPE_ID FROM DEVICE WHERE DEVICE_ID = ?) ");
            sql.append(" AND STATUS_CD = 'A'");


            con = getConnection();

            logger.log(Level.INFO, "ApplicationUtil :: updateAllConnectionPoolTable() :: update sql  :: " + sql);

            pst = con.prepareStatement(sql.toString());


            pst.setLong(1, deviceId);


            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

            if (count > 0) {
                check = true;
            }

        } catch (Exception ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
            }
            logger.log(Level.ERROR, "ApplicationUtil :: updateAllConnectionPoolTable() :: Exception  :: " + ex);
            //ex.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return check;
    }

    public static int updateCommandTableForStartAPI(String commandId) throws Exception {

        PreparedStatement pStmt = null;
        int results = 0;
        String sql = "";

        Connection conn = null;

        try {
            conn = getConnection();

            /**
             * SELECT * FROM COMMAND WHERE DEVICE_TYPE_ID IN( SELECT
             * DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID=6 )
             *
             */
            ///sql = "UPDATE COMMAND SET SS_STATUS = 1 WHERE COMMAND_ID = "+commandId;
            ///sql = "UPDATE COMMAND SET SS_STATUS = 1 , SS_ACTION = 0 WHERE DEVICE_TYPE_ID IN ( SELECT DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID = "+commandId+" )";
            ///This is working but need to some enhancement
            ///sql = "UPDATE COMMAND SET SS_STATUS = 1 WHERE DEVICE_TYPE_ID IN ( SELECT DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID = "+commandId+" )";
            sql = "UPDATE COMMAND SET SS_STATUS = 1 , SS_ACTION = 1 WHERE DEVICE_TYPE_ID IN ( SELECT DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID = " + commandId + " )";

            logger.log(Level.INFO, "ApplicationUtils :: updateCommandTableForStartAPI() :: SQL ::: " + sql.toString());

            pStmt = conn.prepareStatement(sql);

            results = pStmt.executeUpdate();

            logger.log(Level.INFO, "ApplicationUtils :: updateCommandTableForStartAPI() :: Records updated (results) ::: " + results);

            if (results > 0) {
                logger.log(Level.INFO, "ApplicationUtils :: updateCommandTableForStartAPI() :: conn.commit() ::: ");
                conn.commit();
            }


        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: updateCommandTableForStartAPI() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: updateCommandTableForStartAPI() :: Exception  :: " + ex);
            throw ex;
        } finally {

            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return results;

    }

    public static int updateCommandTableForStopAPI(String commandId) throws Exception {

        PreparedStatement pStmt = null;
        int results = 0;
        String sql = "";

        Connection conn = null;

        try {
            conn = getConnection();

            ///The following statement is commented bcoz need to update status of all 3 API (Read,Convert,Load)
            ///sql = "UPDATE COMMAND SET SS_STATUS = 0 WHERE COMMAND_ID = "+commandId;

            ///sql = "UPDATE COMMAND SET SS_STATUS = 0 , SS_ACTION = 1 WHERE DEVICE_TYPE_ID IN ( SELECT DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID = "+commandId+" )";

            ///This is working but need to some enhancement
            ///sql = "UPDATE COMMAND SET SS_STATUS = 0 WHERE DEVICE_TYPE_ID IN ( SELECT DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID = "+commandId+" )";
            sql = "UPDATE COMMAND SET SS_STATUS = 0 , SS_ACTION = 0 WHERE DEVICE_TYPE_ID IN ( SELECT DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID = " + commandId + " )";

            logger.log(Level.INFO, "ApplicationUtils :: updateCommandTableForStopAPI() :: SQL ::: " + sql.toString());

            pStmt = conn.prepareStatement(sql);

            results = pStmt.executeUpdate();

            logger.log(Level.INFO, "ApplicationUtils :: updateCommandTableForStopAPI() :: Records updated (results) ::: " + results);

            if (results > 0) {
                logger.log(Level.INFO, "ApplicationUtils :: updateCommandTableForStopAPI() :: conn.commit() ::: ");
                conn.commit();
            }


        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: updateCommandTableForStopAPI() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: updateCommandTableForStopAPI() :: Exception  :: " + ex);
            throw ex;
        } finally {

            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return results;

    }

    public static void writeToXMLGenerationLogger(String error, boolean appendLogFile) {
        try {
            File file = new File("XMLGenerationLogger.Log");
            if (ApplicationConstants.IS_XML_GENERATE_ERROR && !error.equalsIgnoreCase("")) {
                ApplicationConstants.IS_XML_GENERATE_ERROR = false;
            }
            Writer output = new BufferedWriter(new FileWriter(file, appendLogFile));
            output.write(error + "\n");
            output.close();
        } catch (Exception e) {
            logger.log(Level.ERROR, "ApplicationUtil :: writeToXMLGenerationLogger() :: Exception :: " + e);
        }
    }

    public static boolean fileIsPresent(String path) throws Exception {
        String fileName = "";
        boolean isPresent = false;
        try {
            File f = new File(path);
            isPresent = f.isFile();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getFileNameFromFolderPath() :: Exception == " + ex);
            throw ex;
        }

        return isPresent;
    }

    public static void loadSchedulers() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String urlString = "";
        URL url = null;
        URLConnection urlCon = null;
        InputStream is = null;
        try {
            conn = getConnection();
            pst = conn.prepareStatement(" SELECT DISTINCT IP_ADDRESS FROM IP_COMMAND_MAP ICP,COMMAND C WHERE ICP.STATUS_CD='A' "
                    + " AND ICP.COMMAND_ID=C.COMMAND_ID AND C.EXECUTION_SEQ=1 AND ICP.SCHEDULER_TYPE=1 order by ip_address");

            rs = pst.executeQuery();
            while (rs.next()) {
                try {
                    urlString = "http://" + rs.getString("IP_ADDRESS") + ":8084/scheduler/LoadSchedulerServlet";
                    url = new URL(urlString);
                    urlCon = (URLConnection) url.openConnection();
                    urlCon.connect();
                    is = urlCon.getInputStream();
                } catch (Exception e) {
                }
            }
        } catch (Exception ex) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }

            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

            try {
                if (conn != null) {
                    conn = null;
                }
            } catch (Exception e) {
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
            }
            try {
                if (urlCon != null) {
                    urlCon = null;
                }
            } catch (Exception e) {
            }
        }

    }

    public static String GenerateDCUConfig(String mtrIdStr, String dcuIdStr) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        StringBuilder sql = null;
        Element meterElement = null;
        long LogicalId = 18;
        String filePath = "";
        String configFilePath = null;
        long dcuId = 0;

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            if (System.getProperty("COMMON_COMMAND_CONFIG_FILE_PATH") != null) {
                configFilePath = System.getProperty("COMMON_COMMAND_CONFIG_FILE_PATH");
            }
            filePath = configFilePath + ApplicationConstants.BACKWARD_SLASH + ApplicationConstants.DEVICEID_FOLDER_NAME;

            logger.log(Level.INFO, "ApplicationUtils :: GenerateDCUConfig() :: Method Called ");

            sql = new StringBuilder();
            sql.append(" SELECT DD.DCU_ID , DD.IPADDRESS, ");
            sql.append(" DD.DCU_SERIAL_NO,");
            sql.append(" DMD.PAN_ADDRESS,  DMD.LINK_KEY, ");
            sql.append(" DMD.METER_ID,");
            sql.append(" DMD.METER_SERIAL_NO, DMD.IEEE_ADDRESS, ");
            sql.append(" DMD.STATUS_CD,");
            sql.append(" DMD.CREATED_DT, DMD.PHASE");
            sql.append(" FROM DCU_DETAILS DD,");
            sql.append(" DCU_METER_MAPPING  DMM,");
            sql.append(" DCU_METER_DETAILS DMD");
            sql.append(" WHERE DD.DCU_ID    = DMM.DCU_ID");
            sql.append(" AND DMM.METER_ID   = DMD.METER_ID");
            sql.append(" AND DMD.METER_ID IN (" + mtrIdStr + ") AND DD.DCU_ID=" + dcuIdStr);
            //sql.append(" ) ");
            System.out.println(sql.toString());
            conn = getConnection();
            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            boolean flag = true;
            //Create root node
            Element root = doc.createElement("DCU");
            while (rs.next()) {
                if (flag) {
                    dcuId = rs.getLong("DCU_ID");
                    root.setAttribute("COMMTYPE", "TCP");
                    root.setAttribute("PORT", "2007");
                    ipAddress = rs.getString("IPADDRESS");
                    root.setAttribute("IPADDR", ipAddress);
                    root.setAttribute("NAME", "DCU55");
                    doc.appendChild(root);
                    flag = false;
                    //System.out.println("Setting Root Attribute");
                }
                meterElement = doc.createElement("METER");
                meterElement.setAttribute("ACTIVE", "1");
                meterElement.setAttribute("IEEENO", rs.getString("IEEE_ADDRESS"));
                meterElement.setAttribute("LINKKEY", rs.getString("LINK_KEY"));
                meterElement.setAttribute("LOGID", LogicalId++ + "");
                meterElement.setAttribute("MAKE", "0");
                meterElement.setAttribute("PANID", rs.getString("PAN_ADDRESS"));
                meterElement.setAttribute("SERIAL", rs.getLong("METER_SERIAL_NO") + "");
                meterElement.setAttribute("PHASE", rs.getString("PHASE"));
                root.appendChild(meterElement);
                //System.out.println("Making meter node");
            }
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer trans = transfac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
            String xmlString = sw.toString();
            //System.out.println(xmlString);

            OutputStream f0;
            filePath = filePath + dcuId + ApplicationConstants.BACKWARD_SLASH + "DCU_" + ipAddress + ".xml";
            //System.out.println("Writing DCU File:: " + filePath);
            File output = new File(filePath);
            byte buf[] = xmlString.getBytes();
            f0 = new FileOutputStream(output);
            try {
                f0.write(buf);
            } catch (Exception e) {
                System.out.println("Writting Error");
                logger.log(Level.ERROR, "ApplicationUtils :: GenerateDCUConfig() :: Exception in Writing DCU :: " + e.getMessage());
                if (f0 != null) {
                    f0.close();
                }
            }
            f0.close();
            buf = null;
            System.out.println("File Generated Successfully @ " + filePath);

        } catch (Exception e) {
            //e.printStackTrace();
            logger.log(Level.ERROR, "ApplicationUtils :: GenerateDCUConfig() :: Exception == " + e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }

            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

            try {
                if (conn != null) {
                    conn = null;
                }
            } catch (Exception e) {
            }
        }
        return filePath;
    }

    public static String newInvokeAPI(String filePath) {

        String clientTypeChannel = "clientChannel";
        String serverTypeChannel = "serverChannel";
        String channelType = "channelType";
        boolean processCompleted = false;
        int port = 2985;
        String localhost = "127.0.0.1";
        //ipAddress = "10.8.0.21";
        int len = filePath.length();
        filePath = filePath.replaceAll("\\\\", "/");
        String outputLine = "1600" + len + "0104000001" + filePath;
        String inputLine = "\"" + DriveLetter + ":\\DLMS\\MIOS\\MIOS App\\MIOS API5\\DLMSAPI5 CFW.bat\" " + localhost + " " + port;
        String recieved = null;
        Runtime runtime = null;

        try {
            logger.log(Level.INFO, "ApplicationUtils :: newInvokeAPI() :: method called ");
            Thread t = new Thread();
            t.start();
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(false);

            channel.socket().bind(new InetSocketAddress(localhost, port));
            channel.socket().setReuseAddress(true);
            channel.socket().setSoTimeout(120000);

            Selector selector = SelectorProvider.provider().openSelector();
            try {
                runtime = Runtime.getRuntime();
                runtime.exec(inputLine);
                System.out.println("Invoked:: " + inputLine);
            } catch (IOException e) {
                inputLine = DriveLetter + ":\\DLMS\\MIOS\\MIOS App\\MIOS API5\\DLMSAPI5 CFW.bat " + localhost + " " + port;
                try {
                    runtime = Runtime.getRuntime();
                    runtime.exec(inputLine);
                    System.out.println("Invoked:: " + inputLine);
                } catch (Exception ex) {
                    logger.log(Level.ERROR, "ApplicationUtils :: newInvokeAPI() :: Exception :: " + e.getMessage());
                    selector.close();
                    channel.socket().close();
                    channel.close();
                    recieved = ex.getMessage();
                    return recieved;
                }
            } catch (Exception e) {
                logger.log(Level.ERROR, "ApplicationUtils :: newInvokeAPI() :: Exception :: " + e.getMessage());
                selector.close();
                channel.socket().close();
                channel.close();
                recieved = e.getMessage();
                return recieved;
            }

            SelectionKey socketServerSelectionKey = channel.register(selector, SelectionKey.OP_ACCEPT);
            Map<String, String> properties = new HashMap<String, String>();
            properties.put(channelType, serverTypeChannel);
            socketServerSelectionKey.attach(properties);

            while (!processCompleted) {
                if (selector.select(10000) == 0) {
                    continue;
                }
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                t.sleep(2000);

                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (((Map) key.attachment()).get(channelType).equals(serverTypeChannel)) {

                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        SocketChannel clientSocketChannel = serverSocketChannel.accept();
                        clientSocketChannel.socket().setReuseAddress(true);
                        clientSocketChannel.socket().setSoTimeout(120000);
                        clientSocketChannel.socket().setKeepAlive(true);

                        System.out.println("Client Connected on:: " + clientSocketChannel.socket().getRemoteSocketAddress()
                                + ":" + clientSocketChannel.socket().getLocalAddress());

                        System.out.println("Accepting Client Request");
                        clientSocketChannel.configureBlocking(false);
                        SelectionKey clientKey = clientSocketChannel.register(selector, SelectionKey.OP_WRITE, SelectionKey.OP_READ);
                        Map<String, String> clientproperties = new HashMap<String, String>();
                        clientproperties.put(channelType, clientTypeChannel);
                        clientKey.attach(clientproperties);

                    } else {

                        ByteBuffer buffer = ByteBuffer.allocate(1000);
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        int bytesRead = 0;
                        if (key.isWritable()) {
                            System.out.println("Writing To Client");
                            String cmd = "";

                            if (recieved == null) {
                                cmd = "1600000600000000\r\n";
                                buffer = ByteBuffer.wrap(cmd.getBytes());
                            } else if (recieved.equals("1600120700000000DLMSAPI5.bat")) {
                                cmd = outputLine + "\r\n";
                                buffer = ByteBuffer.wrap(cmd.getBytes());
                            } else if (recieved.equals("Init Port failed") || recieved.equals("Writing to DCU finished")) {
                                cmd = "1600000304000000\r\n";
                                processCompleted = true;
                                buffer = ByteBuffer.wrap(cmd.getBytes());
                            }
                            while (buffer.hasRemaining()) {
                                clientChannel.write(buffer);
                                System.out.print("Sent :: " + cmd);
                            }
                            buffer.clear();

                            key.interestOps(SelectionKey.OP_READ);
                        }
                        if (key.isReadable()) {
                            System.out.println("Reading Client Data");
                            if ((bytesRead = clientChannel.read(buffer)) > 0) {
                                buffer.flip();
                                recieved = Charset.defaultCharset().decode(buffer).toString();
                                recieved = recieved.trim();
                                System.out.println("Received :: " + recieved);
                                buffer.clear();
                            }
                            if (bytesRead < 0) {
                                clientChannel.close();
                            }
                            if (recieved.equals("1600120700000000DLMSAPI5.bat") || recieved.equals("Init Port failed") || recieved.equals("Writing to DCU finished")) {
                                try {
                                    key.interestOps(SelectionKey.OP_WRITE);
                                } catch (CancelledKeyException e) {
                                    processCompleted = true;
                                }
                            } else {
                                key.interestOps(SelectionKey.OP_READ);
                            }

                        }
                        selector.wakeup();
                    }
                }
            }
            selector.close();
            channel.socket().close();
            channel.close();
            runtime.exec("WMIC /OUTPUT:C:\\ProcessList2.txt PROCESS WHERE (Commandline.like\"%.jar 127.0.0.1 " + port + "%\" and Description.like\"%java.exe%\") delete").waitFor();

            System.out.println("Final Result::" + recieved);
        } catch (Exception e) {
            recieved = e.getMessage();
            logger.log(Level.ERROR, "ApplicationUtils :: newInvokeAPI() :: Exception :: " + e.getMessage());
            ////e.printStackTrace();
        }
        return recieved;
    }

    public static void UpdateDCUWritingStatus(String dcuId, String status) {
        String sql = "";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: UpdateDCUWritingStatus() :: method called");

            sql = "UPDATE DCU_DETAILS SET LAST_WRITTEN_ON=SYSDATE,LAST_WRITING_STATUS='" + status + "' WHERE DCU_ID=" + dcuId;
            //System.out.println(sql);
            con = ApplicationUtils.getConnection();
            ps = con.prepareStatement(sql);
            if (ps.executeUpdate() > 0) {
                con.commit();
            } else {
                con.rollback();
            }

        } catch (Exception ex) {
            //ex.printStackTrace();
            logger.log(Level.INFO, "AjaxControlServlet :: UpdateDCUWritingStatus() :: Exception :: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    public static void startDCUWriter(String urlString) {
        URL url = null;
        URLConnection urlCon = null;
        InputStream is = null;
        try {
            System.out.println("Connecting to ::" + urlString);
            url = new URL(urlString);
            urlCon = (URLConnection) url.openConnection();
            urlCon.connect();
            is = urlCon.getInputStream();

        } catch (Exception ex) {
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
            }
            try {
                if (urlCon != null) {
                    urlCon = null;
                }
            } catch (Exception e) {
            }
        }

    }

    public static String BlankInput(String input) throws Exception {
        String Input = "0";
        try {
            if ((input != null) && !(input.equals(""))) {
                Input = input;
            }
            return Input;
        } catch (Exception e) {
            //e.printStackTrace();
            return "0";
        } finally {
        }
    }

    public static LinkedList getLast12YYYYMM() throws Exception {
        LinkedList list = new LinkedList();
        java.util.Date sysdate = new java.util.Date();
        String[] YYYYMM = new String[12];
        int YYYY;
        int MM;
        try {
            YYYY = Integer.parseInt(getYYYY(sysdate));
            YYYY = YYYY - 1; // Last Year
            MM = Integer.parseInt(getMM(sysdate));
            //MM = MM - 1; // Prev. Month
            for (int i = 0; i < 12; i++) {
                if (MM < 12) {
                    YYYYMM[i] = (("0000" + String.valueOf(YYYY)).substring(String.valueOf(YYYY).length()) + ("00" + String.valueOf(MM + 1)).substring(String.valueOf(MM + 1).length()));
                    MM = MM + 1;
                } else if (MM == 12) {
                    YYYYMM[i] = (("0000" + String.valueOf(YYYY + 1)).substring(String.valueOf(YYYY + 1).length()) + "01");
                    YYYY = YYYY + 1;
                    MM = 1;
                }
            }
            for (int i = 0; i < 12; i++) {
                list.add(YYYYMM[11 - i]);
            }
            return list;
        } catch (Exception e) {
            //e.printStackTrace();
            return list;
        } finally {
        }
    }

    public static String getYYYY(java.util.Date date) throws Exception {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            String yyyy = ("0000" + format.format(date)).substring(format.format(date).length());
            return yyyy;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        } finally {
        }
    }

    public static String getMM(java.util.Date date) throws Exception {
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM");
            String mm = ("00" + format.format(date)).substring(format.format(date).length());
            return mm;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        } finally {
        }
    }

    public static int getDayCount(String yyyymm) throws Exception {
        String YYYYMM = yyyymm;
        float YYYY;
        int daycount = 0;
        try {
            if ((YYYYMM.substring(4).equals("01")) || (YYYYMM.substring(4).equals("03"))
                    || (YYYYMM.substring(4).equals("05")) || (YYYYMM.substring(4).equals("07"))
                    || (YYYYMM.substring(4).equals("08")) || (YYYYMM.substring(4).equals("10"))
                    || (YYYYMM.substring(4).equals("12"))) {
                daycount = 31;
            } else if ((YYYYMM.substring(4).equals("04")) || (YYYYMM.substring(4).equals("06"))
                    || (YYYYMM.substring(4).equals("09")) || (YYYYMM.substring(4).equals("11"))) {
                daycount = 30;
            } else if (YYYYMM.substring(4).equals("02")) {
                YYYY = Float.parseFloat(YYYYMM.substring(0, 4));

                if ((YYYY % 4) > 0) {
                    daycount = 28;
                } else if ((YYYY % 100) > 0) {
                    daycount = 29;
                } else if ((YYYY % 400) > 0) {
                    daycount = 28;
                } else {
                    daycount = 29;
                }
            }
            return daycount;
        } catch (Exception e) {
            //e.printStackTrace();
            return 28;
        } finally {
            return daycount;
        }
    }

    public static String NullInput(String input) throws Exception {
        String Input = " ";
        try {
            if ((input != null) && !(input.equals(""))) {
                Input = input;
            }
            return Input;
        } catch (Exception e) {
            //e.printStackTrace();
            return "0";
        } finally {
        }
    }

    public static String getWorkingHours(String intime, String outtime) throws Exception {
        Float In_Time, Out_Time;
        String WorkingHours;
        String WorkingMins;
        String WorkingTime;
        int endindex = 1;
        int sindex = 2;
        try {
            In_Time = (Float.parseFloat(intime.substring(0, 2)) + (Float.parseFloat(intime.substring(3, 5)) / 60));
            Out_Time = (Float.parseFloat(outtime.substring(0, 2)) + (Float.parseFloat(outtime.substring(3, 5)) / 60));
            WorkingHours = String.format("%.2f", (Out_Time - In_Time));
            if ((Out_Time - In_Time) >= 10.01) {
                endindex = 2;
                sindex = 3;
            } else {
                endindex = 1;
                sindex = 2;
            }
            WorkingMins = String.format("%.0f", (Float.parseFloat(WorkingHours.substring(sindex)) / 100 * 60));
            WorkingTime = WorkingHours.substring(0, endindex) + ":" + ("00" + WorkingMins).substring(WorkingMins.length());
            return WorkingTime;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        } finally {
        }
    }

    public static int getFiscalYear() {
        try {
            java.util.Date sysdate = new java.util.Date();
            int YYYY;
            int MM;
            YYYY = Integer.parseInt(getYYYY(sysdate));
            //YYYY = YYYY - 1; // Last Year
            MM = Integer.parseInt(getMM(sysdate));

            int y = MM <= 3 ? YYYY - 1 : YYYY;
            return y;
        } catch (Exception e) {
            //e.printStackTrace();
            return 0;
        } finally {
        }
    }

    public static LinkedList getTimeList() throws Exception {
        LinkedList list = new LinkedList();
        try {
            for (int i = 0; i < 24; i++) {
                list.add(("00" + String.valueOf(i)).substring(String.valueOf(i).length()) + ":00");
                list.add(("00" + String.valueOf(i)).substring(String.valueOf(i).length()) + ":30");
            }
            return list;
        } catch (Exception e) {
            //e.printStackTrace();
            return list;
        } finally {
        }
    }
    
    public static String formatAmount(double d) {
    String s = String.format(Locale.UK, "%1.2f", Math.abs(d));
    s = s.replaceAll("(.+)(...\\...)", "$1,$2");
    while (s.matches("\\d{3,},.+")) {
        s = s.replaceAll("(\\d+)(\\d{2},.+)", "$1,$2");
    }
    return d < 0 ? ("₹"+"-" + s) : "₹"+s;
}

    public static String generateCaptchaMethod1(){        
Random ranNum=new Random();
int ranNum1=ranNum.nextInt(); // Some randaom numbers are generated here
String hash1 = Integer.toHexString(ranNum1); // convert randaom numbers into hexadeciaml here        
return hash1;        
}
public static String generateCaptchatMethod2(int captchaLength){        
String saltCharacters = "abranNumcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
StringBuffer captchaStrBuff = new StringBuffer();
java.util.Random ranNum = new java.util.Random();
// build a random captchaLength chars salt        
while (captchaStrBuff.length() < captchaLength){
int index = (int) (ranNum.nextFloat() * saltCharacters.length());
captchaStrBuff.append(saltCharacters.substring(index, index+1));
}    
return captchaStrBuff.toString();   
}
//    public static Connection getAppsConnection() throws Exception {
//
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Where is your Oracle JDBC Driver?");
//            //e.printStackTrace();
//            return null;
//        }
//        Connection connection = null;
//        try {
//            if (System.getProperty("ENV_TYPE").equals("PROD")) {
//                connection = DriverManager.getConnection(
//                        "jdbc:oracle:thin:@mis2db-scan.mahadiscom.in:1524:pprod", "apps",
//                        "absm1s2");
//            } else if (System.getProperty("ENV_TYPE").equals("TEST")) {
//                connection = DriverManager.getConnection(
//                        "jdbc:oracle:thin:@mis2testdb.mahadiscom.in:1528:mistest", "apps",
//                        "mistetapps");
//            } else if (System.getProperty("ENV_TYPE").equals("DEV")) {
//                connection = DriverManager.getConnection(
//                        "jdbc:oracle:thin:@mis2devdb.mahadiscom.in:1525:misdev", "apps",
//                        "msedcldev1");
//            }
//        } catch (SQLException e) {
//            System.out.println("Connection Failed! Check output console");
//            //e.printStackTrace();
//            return null;
//        }
//        if (connection != null) {
//            return connection;
//        } else {
//            return null;
//        }
//
//    }

    public static String getRequestParameter(HttpServletRequest request, String param) throws Exception {
        String ret = "";
        try {
            if (!isBlank(request.getParameter(param))) {
                ret = (String) request.getParameter(param);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return ret;
    }

    public static String getStatusDesc(HttpServletRequest request, String statusType, String status) throws Exception {
        ServletContext context = request.getSession().getServletContext();
        LinkedList<MasterBean> statusList = new LinkedList<MasterBean>();
        String Description = "";
        MasterDelegate masterManagerObj = new MasterManager();
        try {
            if (!ApplicationUtils.isBlank(context.getAttribute("statusList"))) {
                statusList = (LinkedList<MasterBean>) context.getAttribute("statusList");
            }
            if (!(((LinkedList) context.getAttribute("statusList")).size() > 0)) {
                statusList = masterManagerObj.getMasterList("XXMIS_EMP_APPL_STATUS_MASTER", "ID", "CONCAT(TYPE,STATUS_TEXT)", "DESCRIPTION", "1 = 1");
            }

            for (MasterBean m : statusList) {
                if (Description.equals("")) {
                    if (m.getCode().equals((statusType + status))) {
                        Description = m.getName();
                    }
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return Description;
    }

//    public static String getOptionStringFromLocationList(LinkedList list, String selectedStr) throws Exception {
//        StringBuilder str = new StringBuilder();
//        try {
//            logger.log(Level.INFO, "ApplicationUtil :: getOptionStringFromList() :: ");
//            if (!isBlank(selectedStr) && selectedStr.equals("0")) {
//                str.append("<option selected value = ").append("").append(">").append(" -- ").append("</option>");
//            }
//            if (list != null && list.size() > 0) {
//                for (Object o : list) {
//                    LocationDTO l = (LocationDTO) o;
//                    if (l.getOrganizationId() != null && !l.getOrganizationId().equals("") && !l.getOrganizationId().equals("0")) {
//                        str.append("<option ");
//                        if ((selectedStr).equals(l.getOrganizationId())) {
//                            str.append(" selected ");
//                        }
//                        str.append("value = ").append(l.getOrganizationId()).append(">");
//
//                        //str.append(l.getLocationCode());
//
//                        if (!isBlank(l.getrName())) {
//                            //str.append(" -> ");
//                            str.append(l.getrName());
//                        }
//                        if (!isBlank(l.getzName())) {
//                            str.append(" -> ");
//                            str.append(l.getzName());
//                        }
//                        if (!isBlank(l.getcName())) {
//                            str.append(" -> ");
//                            str.append(l.getcName());
//                        }
//                        if (!isBlank(l.getdName())) {
//                            str.append(" -> ");
//                            str.append(l.getdName());
//                        }
//
//                        str.append("</option>");
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            logger.log(Level.ERROR, "ApplicationUtil :: getOptionStringFromList() :: Exception  :: " + ex);
//            //ex.printStackTrace();
//            throw ex;
//        }
//
//        return str.toString();
//    }// End of Method
}// End of Class

