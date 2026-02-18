package com.jeneesh.splitmaster.Split.Master.dto;

public class ExpenseSplitView {
    private Long SplitId;
    private String desc;
    private double totalAmount;
    private double amountpaid;

    public ExpenseSplitView(Long splitId, String desc, double totalAmount,double amountpaid) {
        SplitId = splitId;
        this.desc = desc;
        this.totalAmount = totalAmount;
        this.amountpaid = amountpaid;
    }

    public ExpenseSplitView() {
    }

    @Override
    public String toString() {
        return "ExpenseSplitView{" +
                "SplitId=" + SplitId +
                ", desc='" + desc + '\'' +
                ", totalAmount=" + totalAmount +
                ", amountpaid=" + amountpaid +
                '}';
    }

    public Long getSplitId() {
        return SplitId;
    }

    public void setSplitId(Long splitId) {
        SplitId = splitId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(double amountpaid) {
        this.amountpaid = amountpaid;
    }
}
