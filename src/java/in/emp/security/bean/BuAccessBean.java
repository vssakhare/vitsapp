/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.security.bean;

import in.emp.common.UserAuditBean;

/**
 *
 * @author Administrator
 */
public class BuAccessBean extends UserAuditBean implements java.io.Serializable
{
        private long locationId;
        private String buCode;
        private String locationCode;
        private long locationTypeId;
        private long parentLocationId;
        private String locationDesc;

        /**
         * @return the locationId
         */
        public long getLocationId() {
                return locationId;
        }

        /**
         * @param locationId the locationId to set
         */
        public void setLocationId(long locationId) {
                this.locationId = locationId;
        }

        /**
         * @return the buCode
         */
        public String getBuCode() {
                return buCode;
        }

        /**
         * @param buCode the buCode to set
         */
        public void setBuCode(String buCode) {
                this.buCode = buCode;
        }

        /**
         * @return the locationCode
         */
        public String getLocationCode() {
                return locationCode;
        }

        /**
         * @param locationCode the locationCode to set
         */
        public void setLocationCode(String locationCode) {
                this.locationCode = locationCode;
        }

        /**
         * @return the locationTypeId
         */
        public long getLocationTypeId() {
                return locationTypeId;
        }

        /**
         * @param locationTypeId the locationTypeId to set
         */
        public void setLocationTypeId(long locationTypeId) {
                this.locationTypeId = locationTypeId;
        }

        /**
         * @return the parentLocationId
         */
        public long getParentLocationId() {
                return parentLocationId;
        }

        /**
         * @param parentLocationId the parentLocationId to set
         */
        public void setParentLocationId(long parentLocationId) {
                this.parentLocationId = parentLocationId;
        }

        /**
         * @return the locationDesc
         */
        public String getLocationDesc() {
                return locationDesc;
        }

        /**
         * @param locationDesc the locationDesc to set
         */
        public void setLocationDesc(String locationDesc) {
                this.locationDesc = locationDesc;
        }
        
        
}
