/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfoUpdation.dao.helper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.ProcHelper;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationBean;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationPrezData;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationaddressBean;
import in.emp.util.ApplicationUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class postPiUpdationFormProcHelper implements ProcHelper {

    private static Logger logger = Logger.getLogger(postPiUpdationFormProcHelper.class);
    private PersonalinfoupdationBean personalinfoupdationBeanObj;
   // private PersonalinfoupdationaddressBean personalinfoupdationaddressBeanObj;
    private PersonalinfoupdationPrezData personalinfoupdationPrezDataObj;

    public postPiUpdationFormProcHelper(PersonalinfoupdationBean personalinfoupdationBeanObj) {
        this.personalinfoupdationBeanObj = personalinfoupdationBeanObj;
    }

    public postPiUpdationFormProcHelper(PersonalinfoupdationPrezData personalinfoupdationPrezDataObj) {
        this.personalinfoupdationPrezDataObj = personalinfoupdationPrezDataObj;
        this.personalinfoupdationBeanObj = this.personalinfoupdationPrezDataObj.getPinfoupdationBean();
    }

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    public Object callProcedure(Connection conn) throws Exception {     
        int count = 0;
        StringBuilder sql = new StringBuilder();
        CallableStatement callableStatement = null;
        int vRet = 0;
        try {
            logger.log(Level.INFO, "postPiUpdationFormProcHelper ::: callProcedure() :: method called ::");

           // sql.append("{? = call XXMIS_EMPPORTAL.EMP_PI_UPDATE(PEmailFlag => ?, PEmail => ?, PMobFlag => ?, PMobileNumber => ?, TAddrFlag => ?, TAddrLine1 => ?, TAddrLine2 => ?, TAddrLine3 => ?,TAddrLine4 => ?, TCity => ?, TState => ?, TPincode => ? )}"); 

//            callableStatement = conn.prepareCall(sql.toString());
//            callableStatement.registerOutParameter(1, java.sql.Types.INTEGER);
//            callableStatement.setString(2, leavecancBeanObj.getEmpNumber());
//            callableStatement.setDate(3, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(leavecancBeanObj.getSearchFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
//            callableStatement.setDate(4, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(leavecancBeanObj.getSearchToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT),ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
//            callableStatement.setString(5, leavecancBeanObj.getApplicationId());
//            
          callableStatement = conn.prepareCall("{call apps.XXMIS_EMPPORTAL.EMP_PI_UPDATE( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )}");
               callableStatement.setString(1, personalinfoupdationBeanObj.getPEmailFlag());
               callableStatement.setString(2, personalinfoupdationBeanObj.getPEmail());
               
               callableStatement.setString(3, personalinfoupdationBeanObj.getPMobFlag());
               callableStatement.setString(4, personalinfoupdationBeanObj.getMobileNo());
               
               callableStatement.setString(5, personalinfoupdationBeanObj.getTempAddrFlag());
               callableStatement.setString(6, personalinfoupdationBeanObj.getTempAddress().getAddressLine1());
               
               callableStatement.setString(7, personalinfoupdationBeanObj.getTempAddress().getAddressLine2());
               callableStatement.setString(8,  personalinfoupdationBeanObj.getTempAddress().getAddressLine3());
               
              
               callableStatement.setString(9, personalinfoupdationBeanObj.getTempAddress().getAddressLine4());
               
               callableStatement.setString(10, personalinfoupdationBeanObj.getTempAddress().getCity());
               
               callableStatement.setString(11, personalinfoupdationBeanObj.getTempAddress().getState());
               callableStatement.setString(12,  personalinfoupdationBeanObj.getTempAddress().getPincode());
               
               callableStatement.setString(13,  personalinfoupdationBeanObj.getEmpNumber());
               //////
               callableStatement.setString(14, personalinfoupdationBeanObj.getPMStatusFlag());
               callableStatement.setString(15,  personalinfoupdationBeanObj.getPMStatus());
               
               callableStatement.setString(16,  personalinfoupdationBeanObj.getPBloodGroupFlag());
               callableStatement.setString(17,  personalinfoupdationBeanObj.getPBloodGroup());
               
               callableStatement.registerOutParameter(18, java.sql.Types.VARCHAR);
               callableStatement.registerOutParameter(19, java.sql.Types.VARCHAR);
               callableStatement.registerOutParameter(20, java.sql.Types.VARCHAR);
            
            count = callableStatement.executeUpdate();
            
            String EmailUpMsg = callableStatement.getString(18);
            String MobUpMsg = callableStatement.getString(19);
            String AddrUpMsg = callableStatement.getString(20);
            
            if (!ApplicationUtils.isBlank(EmailUpMsg))
            {
               personalinfoupdationBeanObj.setPIUpdateMsg(EmailUpMsg + " !! \n");
            }
            
            if (!ApplicationUtils.isBlank(MobUpMsg))
            {
               personalinfoupdationBeanObj.setPIUpdateMsg(MobUpMsg + " !! \n");
            }
                   
            if (!ApplicationUtils.isBlank(AddrUpMsg))
            {
               personalinfoupdationBeanObj.setPIUpdateMsg(AddrUpMsg +" !! " );
            }
            conn.commit();
            
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "postPiUpdationFormProcHelper ::: callProcedure() :: Exception :: " + ex);
            throw ex;
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
        }
        return personalinfoupdationBeanObj;
    }
    
}