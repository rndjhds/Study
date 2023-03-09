package com.fourvaluesoft.mock.openbanking.account.deposit;

import java.util.List;

public class AccountDeposit {

    private String apiTranId;

    private Integer apiTranDtm;

    private String rspCode;

    private String rspMessage;

    private String wdBankCodeStd;

    private String wdBankCodeSub;

    private String wdBankName;

    private String wdAccountNumMasked;

    private String wdPrintContent;

    private String wdAccountHolderName;

    private Integer resCnt;

    private List<AccountDepositResList> resList;

    public String getApiTranID() {
        return apiTranId;
    }

    public void setApiTranID(String apiTranId) {
        this.apiTranId = apiTranId;
    }

    public Integer getApiTranDtm() {
        return apiTranDtm;
    }

    public void setApiTranDtm(Integer apiTranDtm) {
        this.apiTranDtm = apiTranDtm;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMessage() {
        return rspMessage;
    }

    public void setRspMessage(String rspMessage) {
        this.rspMessage = rspMessage;
    }

    public String getWdBankCodeStd() {
        return wdBankCodeStd;
    }

    public void setWdBankCodeStd(String wdBankCodeStd) {
        this.wdBankCodeStd = wdBankCodeStd;
    }

    public String getWdBankCodeSub() {
        return wdBankCodeSub;
    }

    public void setWdBankCodeSub(String wdBankCodeSub) {
        this.wdBankCodeSub = wdBankCodeSub;
    }

    public String getWdBankName() {
        return wdBankName;
    }

    public void setWdBankName(String wdBankName) {
        this.wdBankName = wdBankName;
    }

    public String getWdAccountNumMasked() {
        return wdAccountNumMasked;
    }

    public void setWdAccountNumMasked(String wdAccountNumMasked) {
        this.wdAccountNumMasked = wdAccountNumMasked;
    }

    public String getWdPrintContent() {
        return wdPrintContent;
    }

    public void setWdPrintContent(String wdPrintContent) {
        this.wdPrintContent = wdPrintContent;
    }

    public String getWdAccountHolderName() {
        return wdAccountHolderName;
    }

    public void setWdAccountHolderName(String wdAccountHolderName) {
        this.wdAccountHolderName = wdAccountHolderName;
    }

    public Integer getResCnt() {
        return resCnt;
    }

    public void setResCnt(Integer resCnt) {
        this.resCnt = resCnt;
    }

    public List<AccountDepositResList> getResList() {
        return resList;
    }

    public void setResList(List<AccountDepositResList> resList) {
        this.resList = resList;
    }
}
