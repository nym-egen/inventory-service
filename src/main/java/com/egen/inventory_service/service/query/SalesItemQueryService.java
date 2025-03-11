package com.egen.inventory_service.service.query;

import com.egen.inventory_service.entities.QSalesItem;
import com.egen.inventory_service.entities.SalesItem;
import com.egen.inventory_service.model.SalesItemSearchDto;
import com.egen.inventory_service.service.predicate.SalesItemPredicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesItemQueryService {

    private final EntityManager entityManager;

    public SalesItemQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<SalesItem> searchList(SalesItemSearchDto searchDto) {
        QSalesItem qSalesItem = QSalesItem.salesItem;
        return new JPAQuery<SalesItem>(entityManager).from(qSalesItem)
                .where(SalesItemPredicate.search(searchDto))
                .fetch();
    }

    public Page<SalesItem> searchPage(SalesItemSearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
        QSalesItem qSalesItem = QSalesItem.salesItem;
        JPAQuery<SalesItem> query = new JPAQuery<SalesItem>(entityManager).from(qSalesItem)
                .where(SalesItemPredicate.search(searchDto))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}
