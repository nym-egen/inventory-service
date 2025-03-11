package com.egen.inventory_service.model;

import com.egen.inventory_service.entities.Stock;

import java.util.List;

//@Getter
//@Setter
public class ItemDto {

    private String name;
    private Double salesPrice;
    private String details;
    private List<Stock> stockList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }
}
