/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.security.bean;

import java.util.LinkedList;

/**
 *
 * @author ipchaudhari
 */
public class ServerAPIBean
{
        public String Mfg_Name;
        public String Execution_Command;
        public String Command_Config_File_Path;
        public String Api_Port;
        public String Ss_Status;
        public String Ss_Action;
        public String Api_Id;
        public String Command_Id;
        public String Device_Type_Id;

        private LinkedList ServerAPIList;

        public String getDevice_Type_Id() {
                return Device_Type_Id;
        }

        public void setDevice_Type_Id(String Device_Type_Id) {
                this.Device_Type_Id = Device_Type_Id;
        }

        

        public LinkedList getServerAPIList() {
                return ServerAPIList;
        }

        public void setServerAPIList(LinkedList ServerAPIList) {
                this.ServerAPIList = ServerAPIList;
        }

        

        public String getCommand_Id() {
                return Command_Id;
        }

        public void setCommand_Id(String Command_Id) {
                this.Command_Id = Command_Id;
        }


        public String getApi_Id() {
                return Api_Id;
        }

        public void setApi_Id(String Api_Id) {
                this.Api_Id = Api_Id;
        }

        public String getApi_Port() {
                return Api_Port;
        }

        public void setApi_Port(String Api_Port) {
                this.Api_Port = Api_Port;
        }

        public String getCommand_Config_File_Path() {
                return Command_Config_File_Path;
        }

        public void setCommand_Config_File_Path(String Command_Config_File_Path) {
                this.Command_Config_File_Path = Command_Config_File_Path;
        }

        public String getExecution_Command() {
                return Execution_Command;
        }

        public void setExecution_Command(String Execution_Command) {
                this.Execution_Command = Execution_Command;
        }

        public String getMfg_Name() {
                return Mfg_Name;
        }

        public void setMfg_Name(String Mfg_Name) {
                this.Mfg_Name = Mfg_Name;
        }

        public String getSs_Action() {
                return Ss_Action;
        }

        public void setSs_Action(String Ss_Action) {
                this.Ss_Action = Ss_Action;
        }

        public String getSs_Status() {
                return Ss_Status;
        }

        public void setSs_Status(String Ss_Status) {
                this.Ss_Status = Ss_Status;
        }
        
}
