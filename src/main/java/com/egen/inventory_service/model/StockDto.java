package com.egen.inventory_service.model;

//@Getter
//@Setter
public class StockDto {

    private Long itemId;
    private Integer stock;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
