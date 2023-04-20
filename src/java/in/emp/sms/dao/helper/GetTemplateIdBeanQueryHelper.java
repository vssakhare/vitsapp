/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.sms.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.sms.bean.TemplateIdBean;
import in.emp.user.bean.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;

import org.apache.log4j.Logger;

/**
 *
 * @author pooja jadhav
 */
public class GetTemplateIdBeanQueryHelper implements QueryHelper{
    private static Logger logger = Logger.getLogger(GetTemplateIdBeanQueryHelper.class);
	private TemplateIdBean templateIdBeanObj;
        public GetTemplateIdBeanQueryHelper(TemplateIdBean templateIdBeanObj)
	{	
		this.templateIdBeanObj = templateIdBeanObj;
	} // End of Constructor
	public Object getDataObject(ResultSet results) throws Exception 
	{
		TemplateIdBean templateIdBeanObj = new TemplateIdBean();
		try
		{
			
			
			templateIdBeanObj.setTemplate_Id(results.getString("template_Id"));
			templateIdBeanObj.setTemplate_Id_Desc(results.getString("template_Id_Desc"));
			templateIdBeanObj.setSms_Sent_To(results.getString("sms_Sent_To"));
			
			
			
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetTemplateIdBeanQueryHelper ::: getDataObject() :: Exception :: "+ex);
			throw ex;
		}
		return templateIdBeanObj;
	} // End of Method

		/**
	 * Public API to get query results
	 * @param conn object of Connection
	 * @return object of ResultSet
	 * @throws Exception
	 */
	public ResultSet getQueryResults(Connection connection) throws Exception 
	{
		PreparedStatement statement = null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
	
		try 
		{
			sql.append(" SELECT ");
			sql.append(" template_Id, template_Id_Desc, sms_Sent_To");
			sql.append(" FROM ");
			sql.append(" TEMPLATE_DETAILS ");
			sql.append("WHERE ");
			if(templateIdBeanObj.getTemplate_Id_Desc()!=null)
			{
			sql.append(" template_Id_Desc = '"+templateIdBeanObj.getTemplate_Id_Desc()+"'");
                        
			}
			
			
						
			logger.log(Level.INFO, "GetTemplateIdBeanQueryHelper ::: getQueryResults() :: SQL :: "+sql.toString());
			
			statement = connection.prepareStatement(sql.toString());
			rs = statement.executeQuery();		
			
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetTemplateIdBeanQueryHelper ::: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
}
