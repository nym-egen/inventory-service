package com.egen.inventory_service.service.predicate;

import com.egen.inventory_service.entities.QSalesItem;
import com.egen.inventory_service.model.SalesItemSearchDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.Objects;

public class SalesItemPredicate {

    private static final QSalesItem qSalesItem = QSalesItem.salesItem;

    public static Predicate search(SalesItemSearchDto searchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (Objects.nonNull(searchDto.getItemId())) {
            builder.and(qSalesItem.itemId.eq(searchDto.getItemId()));
        }
        if (Objects.nonNull(searchDto.getQuantity())) {
            builder.and(qSalesItem.quantity.goe(searchDto.getQuantity()));
        }
        return builder;
    }
}
