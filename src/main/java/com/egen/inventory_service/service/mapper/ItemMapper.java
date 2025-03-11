package com.egen.inventory_service.service.mapper;

import com.egen.inventory_service.entities.Item;
import com.egen.inventory_service.model.ItemDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ItemMapper implements Function<ItemDto, Item> {
    @Override
    public Item apply(ItemDto itemDto) {
        Item item = new Item();
        BeanUtils.copyProperties(itemDto, item);
        return item;
    }
}
