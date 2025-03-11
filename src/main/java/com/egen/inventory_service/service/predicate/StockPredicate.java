package com.egen.inventory_service.service.predicate;

import com.egen.inventory_service.entities.QStock;
import com.egen.inventory_service.model.StockSearchDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.Objects;

public class StockPredicate {

    private static final QStock qstock = QStock.stock1;

    public static Predicate search(StockSearchDto searchDto) {
        BooleanBuilder builder = new BooleanBuilder();

        if (Objects.nonNull(searchDto.getItemId())) {
            builder.and(qstock.itemId.eq(searchDto.getItemId()));
        }
        if (Objects.nonNull(searchDto.getStock())) {
            builder.and(qstock.stock.goe(searchDto.getStock()));
        }
        return builder;
    }
}
