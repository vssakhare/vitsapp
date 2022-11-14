package in.emp.master.bean;

import in.emp.common.UserAuditBean;


/**
 * Data object for SystemMessage.
 *
 * @author	MDA
 * 
 */
public class MasterBean extends UserAuditBean implements java.io.Serializable
{

   private long id;
   private String code;
   private String name;

   

   public long getId()
   {
      return this.id;
   }
   public void setId( long id )
   {
      this.id = id;
   }

   public String getCode()
   {
      return this.code;
   }
   public void setCode( String code )
   {
      this.code = code;
   }

   public String getName()
   {
      return this.name;
   }
   public void setName( String name )
   {
      this.name = name;
   }


}
