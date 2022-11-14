/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

/**
 *
 * @author admin
 */
public class AssignOfficeBean {
 private long assignmentId;
   private long applicationId;
   private boolean escalated;
   private long thresholdId;
   private String officeTypeId;
   private String officeTypeName;
   private String officeCode;
   private String officeName;
   private String officerRef;
   private String officerCpfNo;
   private String officerName;
   private String officerDesignation;
   private String officerEmailId;
   private String officerContactNo;
   private String officeIncharge;
   private long seqId;
   private String officerGroup;
   private String billingDbCode;
   private String groupName;
   private String officerDisplayName;
   private String parentOfficeCode;
    public String getParentOfficeCode() {
        return parentOfficeCode;
    }

    public void setParentOfficeCode(String parentOfficeCode) {
        this.parentOfficeCode = parentOfficeCode;
    }

   public String getOfficerDisplayName() {
      return this.officerDisplayName;
   }

   public void setOfficerDisplayName(String officerDisplayName) {
      this.officerDisplayName = officerDisplayName;
   }

   public String getGroupName() {
      return this.groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public String getBillingDbCode() {
      return this.billingDbCode;
   }

   public void setBillingDbCode(String billingDbCode) {
      this.billingDbCode = billingDbCode;
   }

   public String getOfficerGroup() {
      return this.officerGroup;
   }

   public void setOfficerGroup(String officerGroup) {
      this.officerGroup = officerGroup;
   }

   public long getApplicationId() {
      return this.applicationId;
   }

   public void setApplicationId(long applicationId) {
      this.applicationId = applicationId;
   }

   public long getAssignmentId() {
      return this.assignmentId;
   }

   public void setAssignmentId(long assignmentId) {
      this.assignmentId = assignmentId;
   }

   public boolean isEscalated() {
      return this.escalated;
   }

   public void setEscalated(boolean escalated) {
      this.escalated = escalated;
   }

   public String getOfficeCode() {
      return this.officeCode;
   }

   public void setOfficeCode(String officeCode) {
      this.officeCode = officeCode;
   }

   public String getOfficeIncharge() {
      return this.officeIncharge;
   }

   public void setOfficeIncharge(String officeIncharge) {
      this.officeIncharge = officeIncharge;
   }

   public String getOfficeName() {
      return this.officeName;
   }

   public void setOfficeName(String officeName) {
      this.officeName = officeName;
   }

   public String getOfficeTypeId() {
      return this.officeTypeId;
   }

   public void setOfficeTypeId(String officeTypeId) {
      this.officeTypeId = officeTypeId;
   }

   public String getOfficeTypeName() {
      return this.officeTypeName;
   }

   public void setOfficeTypeName(String officeTypeName) {
      this.officeTypeName = officeTypeName;
   }

   public String getOfficerContactNo() {
      return this.officerContactNo;
   }

   public void setOfficerContactNo(String officerContactNo) {
      this.officerContactNo = officerContactNo;
   }

   public String getOfficerCpfNo() {
      return this.officerCpfNo;
   }

   public void setOfficerCpfNo(String officerCpfNo) {
      this.officerCpfNo = officerCpfNo;
   }

   public String getOfficerDesignation() {
      return this.officerDesignation;
   }

   public void setOfficerDesignation(String officerDesignation) {
      this.officerDesignation = officerDesignation;
   }

   public String getOfficerEmailId() {
      return this.officerEmailId;
   }

   public void setOfficerEmailId(String officerEmailId) {
      this.officerEmailId = officerEmailId;
   }

   public String getOfficerName() {
      return this.officerName;
   }

   public void setOfficerName(String officerName) {
      this.officerName = officerName;
   }

   public String getOfficerRef() {
      return this.officerRef;
   }

   public void setOfficerRef(String officerRef) {
      this.officerRef = officerRef;
   }

   public long getSeqId() {
      return this.seqId;
   }

   public void setSeqId(long seqId) {
      this.seqId = seqId;
   }

   public long getThresholdId() {
      return this.thresholdId;
   }

   public void setThresholdId(long thresholdId) {
      this.thresholdId = thresholdId;
   }    
}
