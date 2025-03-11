package com.egen.inventory_service.service.predicate;

import com.egen.inventory_service.entities.QSales;
import com.egen.inventory_service.model.SalesSearchDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.Objects;

public class SalesPredicate {

    private static final QSales qSales = QSales.sales;

    public static Predicate search(SalesSearchDto searchDto) {
        BooleanBuilder builder = new BooleanBuilder();

        if (Objects.nonNull(searchDto.getSalesDate())) {
            builder.and(qSales.salesDate.eq(searchDto.getSalesDate()));
        }
        if (Objects.nonNull(searchDto.getCustomerId())) {
            builder.and(qSales.customerId.eq(searchDto.getCustomerId()));
        }
        if (Objects.nonNull(searchDto.getDueAmount())) {
            builder.and(qSales.dueAmount.goe(searchDto.getDueAmount()));
        }
        return builder;
    }
}
