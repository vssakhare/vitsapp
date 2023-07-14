package in.emp.arch;

import jakarta.servlet.http.HttpServletRequest  ;

/**
 * Handler interface for GenericForm
 */

public interface GenericFormHandler 
{

   /**
	 * Public API to execute
	 * @param HttpServletRequest request
	 * @return String
	 * @throws Exception
	 */
	public String execute(HttpServletRequest request) throws Exception;

}
