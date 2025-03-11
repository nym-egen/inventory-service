package com.egen.inventory_service.service.query;

import com.egen.inventory_service.entities.QCustomer;
import com.egen.inventory_service.entities.QSales;
import com.egen.inventory_service.entities.QSalesItem;
import com.egen.inventory_service.entities.Sales;
import com.egen.inventory_service.model.SalesSearchDto;
import com.egen.inventory_service.service.predicate.SalesPredicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesQueryService {

    private final EntityManager entityManager;

    public SalesQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Sales> searchList(SalesSearchDto searchDto) {
        QSales qSales = QSales.sales;
        QCustomer qCustomer = QCustomer.customer;
        QSalesItem qSalesItem = QSalesItem.salesItem;
        return new JPAQuery<Sales>(entityManager).from(qSales)
                .where(SalesPredicate.search(searchDto))
                .leftJoin(qSales.customer, qCustomer).fetchJoin()
                .leftJoin(qSales.salesItemList, qSalesItem).fetchJoin()
                .fetch();
    }

    public Page<Sales> searchPage(SalesSearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
        QSales qSales = QSales.sales;
        QCustomer qCustomer = QCustomer.customer;
        QSalesItem qSalesItem = QSalesItem.salesItem;
        JPAQuery<Sales> query = new JPAQuery<Sales>(entityManager).from(qSales)
                .where(SalesPredicate.search(searchDto))
                .leftJoin(qSales.customer, qCustomer).fetchJoin()
                .leftJoin(qSales.salesItemList, qSalesItem).fetchJoin()
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}
