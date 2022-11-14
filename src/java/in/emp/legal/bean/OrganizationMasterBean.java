/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.bean;

/**
 *
 * @author Rikma Rai
 */
public class OrganizationMasterBean {

    private String organizationId;
    private String orgIdSap;
    private String officeType;
    private String officeLevel;
    private String regionId;
    private String regionName;
    private String zoneId;
    private String zoneName;
    private String circleId;
    private String circleName;
    private String divisionId;
    private String divisionNAme;
    private String subDivId;
    private String subDivName;
    private String selectedOfficeCode;
    
    

    public OrganizationMasterBean() {
    }

    public OrganizationMasterBean(String organizationId, String orgIdSap, String officeType, String officeLevel, String regionId, String regionName, String zoneId, String zoneName, String circleId, String circleName, String divisionId, String divisionNAme, String subDivId, String subDivName) {
        this.organizationId = organizationId;
        this.orgIdSap = orgIdSap;
        this.officeType = officeType;
        this.officeLevel = officeLevel;
        this.regionId = regionId;
        this.regionName = regionName;
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.circleId = circleId;
        this.circleName = circleName;
        this.divisionId = divisionId;
        this.divisionNAme = divisionNAme;
        this.subDivId = subDivId;
        this.subDivName = subDivName;
    }

    public String getSelectedOfficeCode() {
        return selectedOfficeCode;
    }

    public void setSelectedOfficeCode(String selectedOfficeCode) {
        this.selectedOfficeCode = selectedOfficeCode;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrgIdSap() {
        return orgIdSap;
    }

    public void setOrgIdSap(String orgIdSap) {
        this.orgIdSap = orgIdSap;
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }

    public String getOfficeLevel() {
        return officeLevel;
    }

    public void setOfficeLevel(String officeLevel) {
        this.officeLevel = officeLevel;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionNAme() {
        return divisionNAme;
    }

    public void setDivisionNAme(String divisionNAme) {
        this.divisionNAme = divisionNAme;
    }

    public String getSubDivId() {
        return subDivId;
    }

    public void setSubDivId(String subDivId) {
        this.subDivId = subDivId;
    }

    public String getSubDivName() {
        return subDivName;
    }

    public void setSubDivName(String subDivName) {
        this.subDivName = subDivName;
    }
    
    
}
