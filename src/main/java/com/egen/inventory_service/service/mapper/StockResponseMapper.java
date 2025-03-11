package com.egen.inventory_service.service.mapper;

import com.egen.inventory_service.entities.Stock;
import com.egen.inventory_service.model.StockResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StockResponseMapper implements Function<Stock, StockResponse> {
    @Override
    public StockResponse apply(Stock stock) {
        StockResponse stockResponse = new StockResponse();
        BeanUtils.copyProperties(stock, stockResponse);
        return stockResponse;
    }
}
