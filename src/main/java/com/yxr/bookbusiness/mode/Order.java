package com.yxr.bookbusiness.mode;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Long ord;

    private String orderCode;

    private Long userOrd;

    private Integer status;

    private Integer totalNum;

    private BigDecimal totalPrice;

    private Date createTime;

    public Long getOrd() {
        return ord;
    }

    public void setOrd(Long ord) {
        this.ord = ord;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Long getUserOrd() {
        return userOrd;
    }

    public void setUserOrd(Long userOrd) {
        this.userOrd = userOrd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}