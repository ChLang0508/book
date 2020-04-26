package com.yxr.bookbusiness.mode;

import java.math.BigDecimal;
import java.util.Date;

public class Book {
    private Long ord;

    private String bookName;

    private String ISBN;

    private BigDecimal price;

    private String press;

    private Long bookClass;

    private String mark;

    private String picture;

    private Date createTime;

    private String bookClassName;

    public String getBookClassName() {
        return bookClassName;
    }

    public void setBookClassName(String bookClassName) {
        this.bookClassName = bookClassName;
    }

    public Long getOrd() {
        return ord;
    }

    public void setOrd(Long ord) {
        this.ord = ord;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN == null ? null : ISBN.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    public Long getBookClass() {
        return bookClass;
    }

    public void setBookClass(Long bookClass) {
        this.bookClass = bookClass;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}