package com.example.accountbook_try;

import java.io.Serializable;


public class CostBean implements Serializable {
    private String costDate;
    private String costMoney;
    private String costTitle;

    @Override
    public String toString() {
        return "CostBean{" +
                "costDate='" + costDate + '\'' +
                ", costMoney='" + costMoney + '\'' +
                ", costTitle='" + costTitle + '\'' +
                '}';
    }

    public String getCostDate() {
        return costDate;
    }

    public String getCostMoney() {
        return costMoney;
    }

    public String getCostTitle() {
        return costTitle;
    }

    public void setCostDate(String costDate) {
        this.costDate = costDate;
    }

    public void setCostMoney(String costMoney) {
        this.costMoney = costMoney;
    }

    public void setCostTitle(String costTitle) {
        this.costTitle = costTitle;
    }

}
