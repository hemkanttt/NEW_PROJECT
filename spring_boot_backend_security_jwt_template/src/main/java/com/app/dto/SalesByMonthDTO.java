package com.app.dto;

public class SalesByMonthDTO {

    private Integer month;
    private Double totalSales;

    public SalesByMonthDTO(Integer month, Double totalSales) {
        this.month = month;
        this.totalSales = totalSales;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }
}