package com.seg7.backendproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Accounts {
    private String ID;
    private LocalDateTime time; // 日期
    private String category; // 類別
    private String remark; // 備註
    private String attach; // 附件
    private int price; // 價格

    public Accounts() { // setTime
        this.time = LocalDateTime.now();
    }

    public String getTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.time.format(format);
    }

    public void setID(String id) {
        this.ID = id;
    }

    public String getID() {
        return this.ID;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getAttach() {
        return this.attach;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }
}
