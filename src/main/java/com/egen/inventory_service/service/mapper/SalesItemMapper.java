package com.egen.inventory_service.service.mapper;

import com.egen.inventory_service.entities.SalesItem;
import com.egen.inventory_service.model.SalesItemDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SalesItemMapper implements Function<SalesItemDto, SalesItem> {
    @Override
    public SalesItem apply(SalesItemDto salesItemDto) {
        SalesItem salesItem = new SalesItem();
        BeanUtils.copyProperties(salesItemDto, salesItem);
        return salesItem;
    }
}
