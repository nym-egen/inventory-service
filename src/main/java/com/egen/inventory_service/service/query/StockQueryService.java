package com.egen.inventory_service.service.query;

import com.egen.inventory_service.entities.QStock;
import com.egen.inventory_service.entities.Stock;
import com.egen.inventory_service.model.StockSearchDto;
import com.egen.inventory_service.service.predicate.StockPredicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockQueryService {


    private final EntityManager entityManager;

    public StockQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Stock> searchList(StockSearchDto searchDto) {
        QStock qStock = QStock.stock1;
        return new JPAQuery<Stock>(entityManager).from(qStock)
                .where(StockPredicate.search(searchDto))
                .fetch();
    }

    public Page<Stock> searchPage(StockSearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
        QStock qStock = QStock.stock1;
        JPAQuery<Stock> query = new JPAQuery<Stock>(entityManager).from(qStock)
                .where(StockPredicate.search(searchDto))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}
