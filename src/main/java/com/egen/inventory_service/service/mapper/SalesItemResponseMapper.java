package com.egen.inventory_service.service.mapper;

import com.egen.inventory_service.entities.SalesItem;
import com.egen.inventory_service.model.SalesItemResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SalesItemResponseMapper implements Function<SalesItem, SalesItemResponse> {
    @Override
    public SalesItemResponse apply(SalesItem salesItem) {
        SalesItemResponse salesItemResponse = new SalesItemResponse();
        BeanUtils.copyProperties(salesItem, salesItemResponse);
        return salesItemResponse;
    }
}
