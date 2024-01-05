package dev.tienvv.demo.BE_Vinpearl.model;

public class VnPayRequest {
    private String orderInfo;

    private long amount;

    private String returnUrl;

    private String backCode;

    private String language;

    private String ipAddr;

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getBackCode() {
        return backCode;
    }

    public void setBackCode(String backCode) {
        this.backCode = backCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
}
