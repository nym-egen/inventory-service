package com.egen.inventory_service.service.mapper;

import com.egen.inventory_service.entities.Item;
import com.egen.inventory_service.model.ItemResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ItemResponseMapper implements Function<Item, ItemResponse> {
    @Override
    public ItemResponse apply(Item item) {
        ItemResponse itemResponse = new ItemResponse();
        BeanUtils.copyProperties(item, itemResponse);
        return itemResponse;
    }
}
