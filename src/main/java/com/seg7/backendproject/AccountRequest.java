package com.seg7.backendproject;

import javax.validation.constraints.NotNull;

public class AccountRequest {
    private String category;
    private String remark;
    private String attach;
    @NotNull
    private int price;

    public String getCategory() {
        return this.category;
    }

    public int getPrice() {
        return this.price;
    }

    public String getAttach() {
        return this.attach;
    }

    public String getRemark() {
        return this.remark;
    }

}
