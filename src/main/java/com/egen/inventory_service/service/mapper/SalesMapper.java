package com.egen.inventory_service.service.mapper;

import com.egen.inventory_service.entities.Sales;
import com.egen.inventory_service.model.SalesDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SalesMapper implements Function<SalesDto, Sales> {

    @Override
    public Sales apply(SalesDto salesDto) {
        Sales sales = new Sales();
        BeanUtils.copyProperties(salesDto, sales);
        return sales;
    }
}
