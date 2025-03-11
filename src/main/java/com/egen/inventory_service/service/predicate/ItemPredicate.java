package com.egen.inventory_service.service.predicate;

import com.egen.inventory_service.entities.QItem;
import com.egen.inventory_service.model.ItemSearchDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.Objects;

public class ItemPredicate {

    private static final QItem qItem = QItem.item;

    public static Predicate search(ItemSearchDto searchDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if (Objects.nonNull(searchDto.getName())) {
            builder.and(qItem.name.containsIgnoreCase(searchDto.getName()));
        }
        if (Objects.nonNull((searchDto.getSalesPrice()))) {
            builder.and(qItem.salesPrice.goe(searchDto.getSalesPrice()));
        }
        if (Objects.nonNull(searchDto.getDetails())) {
            builder.and(qItem.details.containsIgnoreCase(searchDto.getDetails()));
        }
        return builder;
    }
}
