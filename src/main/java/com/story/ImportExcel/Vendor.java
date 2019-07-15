package com.story.ImportExcel;

/**
 * blingbling ✨
 */
public class Vendor {
    private String vendorCode;

    private String vendorPhone;

    private String vendorName;

    private String address;

    private String identityCard;

    private String identityCardAddress;

    private String provinceId;

    private String cityId;

    private String districtId;

    public Vendor() {
    }

    public Vendor(String vendorCode, String vendorPhone, String vendorName, String address, String identityCard, String identityCardAddress, String provinceId, String cityId, String districtId) {
        this.vendorCode = vendorCode;
        this.vendorPhone = vendorPhone;
        this.vendorName = vendorName;
        this.address = address;
        this.identityCard = identityCard;
        this.identityCardAddress = identityCardAddress;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.districtId = districtId;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getIdentityCardAddress() {
        return identityCardAddress;
    }

    public void setIdentityCardAddress(String identityCardAddress) {
        this.identityCardAddress = identityCardAddress;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
}
