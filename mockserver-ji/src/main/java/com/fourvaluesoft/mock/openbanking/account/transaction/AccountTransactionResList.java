package com.fourvaluesoft.mock.openbanking.account.transaction;

public class AccountTransactionResList {
    private int tranDate;

    private int tranTime;

    private String inoutType;

    private String tranType;

    private String printContent;

    private int tranAmt;

    private int afterBalanceAmt;

    private String branchName;

    public int getTranDate() {
        return tranDate;
    }

    public void setTranDate(int tranDate) {
        this.tranDate = tranDate;
    }

    public int getTranTime() {
        return tranTime;
    }

    public void setTranTime(int tranTime) {
        this.tranTime = tranTime;
    }

    public String getInoutType() {
        return inoutType;
    }

    public void setInoutType(String inoutType) {
        this.inoutType = inoutType;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getPrintContent() {
        return printContent;
    }

    public void setPrintContent(String printContent) {
        this.printContent = printContent;
    }

    public int getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(int tranAmt) {
        this.tranAmt = tranAmt;
    }

    public int getAfterBalanceAmt() {
        return afterBalanceAmt;
    }

    public void setAfterBalanceAmt(int afterBalanceAmt) {
        this.afterBalanceAmt = afterBalanceAmt;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
