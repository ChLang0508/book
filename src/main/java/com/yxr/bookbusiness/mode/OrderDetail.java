package com.yxr.bookbusiness.mode;

import java.math.BigDecimal;

public class OrderDetail {
    private Long ord;

    private Long orderOrd;

    private Long bookOrd;

    private String bookName;

    private String ISBN;

    private BigDecimal bookPrice;

    private String bookPress;

    public Long getOrd() {
        return ord;
    }

    public void setOrd(Long ord) {
        this.ord = ord;
    }

    public Long getOrderOrd() {
        return orderOrd;
    }

    public void setOrderOrd(Long orderOrd) {
        this.orderOrd = orderOrd;
    }

    public Long getBookOrd() {
        return bookOrd;
    }

    public void setBookOrd(Long bookOrd) {
        this.bookOrd = bookOrd;
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

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress == null ? null : bookPress.trim();
    }
}