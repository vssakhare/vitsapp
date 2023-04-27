/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.sms.bean;

/**
 *
 * @author Pooja Jadhav
 */
public class TemplateIdBean implements java.io.Serializable {

    private String template_Id;
    private String template_Id_Desc;
    private String on_Event;
    private String sms_Sent_To;
    private String sms_Template;

    public String getTemplate_Id() {
        return template_Id;
    }

    public void setTemplate_Id(String template_Id) {
        this.template_Id = template_Id;
    }

    public String getTemplate_Id_Desc() {
        return template_Id_Desc;
    }

    public void setTemplate_Id_Desc(String template_Id_Desc) {
        this.template_Id_Desc = template_Id_Desc;
    }

    public String getOn_Event() {
        return on_Event;
    }

    public void setOn_Event(String on_Event) {
        this.on_Event = on_Event;
    }

    public String getSms_Sent_To() {
        return sms_Sent_To;
    }

    public void setSms_Sent_To(String sms_Sent_To) {
        this.sms_Sent_To = sms_Sent_To;
    }

    public String getSms_Template() {
        return sms_Template;
    }

    public void setSms_Template(String sms_Template) {
        this.sms_Template = sms_Template;
    }

}
