package com.ZCoin;

public class User {
    private String name;
    private String emailId;
    private long mobileNo;
    private long hID;
    private long zId;
    private double ZC;
    private String password;
    private double amount;

    public double getZC() {
        return ZC;
    }

    public void setZC(double ZC) {
        this.ZC = ZC;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public long getzId() {
        return zId;
    }

    public void setzId(long zId) {
        this.zId = zId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public long gethID() {
        return hID;
    }

    public void sethID(long hID) {
        this.hID = hID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
