package com.egen.inventory_service.model;

import java.time.LocalDate;
import java.util.List;

//@Getter
//@Setter
public class SalesDto {

    private LocalDate salesDate;
    private Long customerId;
    private Double dueAmount;
    private List<SalesItemDto> salesItemList;

    public LocalDate getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(LocalDate salesDate) {
        this.salesDate = salesDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public List<SalesItemDto> getSalesItemList() {
        return salesItemList;
    }

    public void setSalesItemList(List<SalesItemDto> salesItemList) {
        this.salesItemList = salesItemList;
    }
}
