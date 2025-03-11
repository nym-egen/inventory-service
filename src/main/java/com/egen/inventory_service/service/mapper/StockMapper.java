package com.egen.inventory_service.service.mapper;

import com.egen.inventory_service.entities.Stock;
import com.egen.inventory_service.model.StockDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StockMapper implements Function<StockDto, Stock> {
    @Override
    public Stock apply(StockDto stockDto) {
        Stock stock = new Stock();
        BeanUtils.copyProperties(stockDto, stock);
        return stock;
    }
}
