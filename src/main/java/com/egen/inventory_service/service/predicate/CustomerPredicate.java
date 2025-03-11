package com.egen.inventory_service.service.predicate;

import com.egen.inventory_service.entities.QCustomer;
import com.egen.inventory_service.model.CustomerSearchDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.Objects;

public class CustomerPredicate {

    private static final QCustomer qCustomer = QCustomer.customer;

    public static Predicate search(CustomerSearchDto searchDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if (Objects.nonNull(searchDto.getName())) {
            builder.and(qCustomer.name.eq(searchDto.getName()));
        }
        if (Objects.nonNull(searchDto.getMobile())) {
            builder.and(qCustomer.mobile.containsIgnoreCase(searchDto.getMobile()));
        }
        if (Objects.nonNull(searchDto.getEmail())) {
            builder.and(qCustomer.email.containsIgnoreCase(searchDto.getEmail()));
        }
        return builder;
    }
}
