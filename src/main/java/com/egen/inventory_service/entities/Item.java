package com.egen.inventory_service.entities;

import jakarta.persistence.*;

import java.util.List;

//@Getter
//@Setter
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "i_name", nullable = false)
    private String name;

    @Column(name = "sales_price", nullable = false)
    private Double salesPrice;

    @Column(name = "i_details", nullable = false)
    private String details;

    @OneToMany(mappedBy = "item")
    private List<Stock> stockList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
