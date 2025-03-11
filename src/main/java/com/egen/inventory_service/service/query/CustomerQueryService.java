package com.egen.inventory_service.service.query;

import com.egen.inventory_service.entities.Customer;
import com.egen.inventory_service.entities.QCustomer;
import com.egen.inventory_service.entities.QSales;
import com.egen.inventory_service.model.CustomerSearchDto;
import com.egen.inventory_service.service.predicate.CustomerPredicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerQueryService {

    private final EntityManager entityManager;

    public CustomerQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Page<Customer> searchPage(CustomerSearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
        QCustomer qCustomer = QCustomer.customer;
        QSales qSales = QSales.sales;
        JPAQuery<Customer> query = new JPAQuery<Customer>(entityManager).from(qCustomer)
                .where(CustomerPredicate.search(searchDto))
                .leftJoin(qCustomer.salesList, qSales).fetchJoin()
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    public List<Customer> searchList(CustomerSearchDto searchDto) {
        QCustomer qCustomer = QCustomer.customer;
        QSales qSales = QSales.sales;
        return new JPAQuery<Customer>(entityManager).from(qCustomer)
                .where(CustomerPredicate.search(searchDto))
                .leftJoin(qCustomer.salesList, qSales).fetchJoin()
                .fetch();
    }
}
