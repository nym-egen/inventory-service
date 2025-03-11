package com.egen.inventory_service.model;

import java.time.LocalDate;
import java.util.List;

//@Getter
//@Setter
public class SalesSearchDto {

    private LocalDate salesDate;
    private Long customerId;
    private Double dueAmount;
    private List<SalesItemDto> salesItemList;
    private Integer page;
    private Integer size;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

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
