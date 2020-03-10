package com.yxr.bookbusiness.mode;

public class Stock {
    private Long ord;

    private Integer stockNum;

    private Long bookOrd;

    public Long getOrd() {
        return ord;
    }

    public void setOrd(Long ord) {
        this.ord = ord;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Long getBookOrd() {
        return bookOrd;
    }

    public void setBookOrd(Long bookOrd) {
        this.bookOrd = bookOrd;
    }
}