package com.egen.inventory_service.service.mapper;

import com.egen.inventory_service.entities.Sales;
import com.egen.inventory_service.model.SalesResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SalesResponseMapper implements Function<Sales, SalesResponse> {

    @Override
    public SalesResponse apply(Sales sales) {
        SalesResponse salesResponse = new SalesResponse();
        BeanUtils.copyProperties(sales, salesResponse);
        return salesResponse;
    }
}
