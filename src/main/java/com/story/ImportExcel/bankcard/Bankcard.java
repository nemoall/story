package com.story.ImportExcel.bankcard;

/**
 * blingbling ✨
 */
public class Bankcard {
    private String bankName;
    private String bankAccountName;
    private String bankAccount;
    private String isDefault;
    private String shopCode;

    public Bankcard() {
    }

    public Bankcard(String bankName, String bankAccountName, String bankAccount, String isDefault, String shopCode) {
        this.bankName = bankName;
        this.bankAccountName = bankAccountName;
        this.bankAccount = bankAccount;
        this.isDefault = isDefault;
        this.shopCode = shopCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }
}
