package in.emp.user.bean;

import java.util.LinkedList;

public class UserPrezData implements java.io.Serializable {

    UserBean userBeanObj;
    LinkedList userList;
    LinkedList locationTypeList;
    LinkedList locationList;
    LinkedList allRoleList;
    LinkedList selectedRoleList;
    LinkedList allEmployeeList;
    LinkedList defaultLocList;
    LinkedList selectedAreaList;


    /*Public constructor*/
    public UserPrezData() {
    }

    // Setter and Getter method
    public void setUserBean(UserBean userBeanObj) {
        this.userBeanObj = userBeanObj;
    }

    public UserBean getUserBean() {
        return this.userBeanObj;
    }

    public void setUserList(LinkedList userList) {
        this.userList = userList;
    }

    public LinkedList getUserList() {
        return this.userList;
    }

    public void setLocationTypeList(LinkedList locationTypeList) {
        this.locationTypeList = locationTypeList;
    }

    public LinkedList getLocationTypeList() {
        return this.locationTypeList;
    }

    public void setLocationList(LinkedList locationList) {
        this.locationList = locationList;
    }

    public LinkedList getLocationList() {
        return this.locationList;
    }

    public void setAllRoleList(LinkedList allRoleList) {
        this.allRoleList = allRoleList;
    }

    public LinkedList getAllRoleList() {
        return this.allRoleList;
    }

    public void setSelectedRoleList(LinkedList selectedRoleList) {
        this.selectedRoleList = selectedRoleList;
    }

    public LinkedList getSelectedRoleList() {
        return this.selectedRoleList;
    }

    public void setAllEmployeeList(LinkedList allEmployeeList) {
        this.allEmployeeList = allEmployeeList;
    }

    public LinkedList getAllEmployeeList() {
        return this.allEmployeeList;
    }

    public void setDefaultLocList(LinkedList defaultLocList) {
        this.defaultLocList = defaultLocList;
    }

    public LinkedList getDefaultLocList() {
        return defaultLocList;
    }

   
    public LinkedList getSelectedAreaList() {
        return selectedAreaList;
    }

    public void setSelectedAreaList(LinkedList selectedAreaList) {
        this.selectedAreaList = selectedAreaList;
    }
} //End of Class
