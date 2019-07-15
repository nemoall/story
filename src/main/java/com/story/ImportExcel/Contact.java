package com.story.ImportExcel;

/**
 * blingbling ✨
 */
public class Contact {
    private String contactName;

    private String contactPhone;

    private String contactPosition;

    private String contactEmail;

    private String vendorCode ;

    public Contact() {
    }

    public Contact(String contactName, String contactPhone, String contactPosition, String contactEmail, String vendorCode) {
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactPosition = contactPosition;
        this.contactEmail = contactEmail;
        this.vendorCode = vendorCode;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
