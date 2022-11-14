/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.user.bean;

import in.emp.common.UserAuditBean;

/**
 *
 * @author Administrator
 */
public class BuBean  extends UserAuditBean implements java.io.Serializable
{

        private long locationId;
        private String buCode;
        private String locationCode;
        private String locationDescription;

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
         * @return the locationDescription
         */
        public String getLocationDescription() {
                return locationDescription;
        }

        /**
         * @param locationDescription the locationDescription to set
         */
        public void setLocationDescription(String locationDescription) {
                this.locationDescription = locationDescription;
        }

}
