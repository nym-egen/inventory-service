package com.egen.inventory_service.service.query;

import com.egen.inventory_service.entities.Item;
import com.egen.inventory_service.entities.QItem;
import com.egen.inventory_service.entities.QStock;
import com.egen.inventory_service.model.ItemSearchDto;
import com.egen.inventory_service.service.predicate.ItemPredicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemQueryService {

    private final EntityManager entityManager;

    public ItemQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Item> searchList(ItemSearchDto searchDto) {
        QItem qItem = QItem.item;
        QStock qStock = QStock.stock1;
        return new JPAQuery<Item>(entityManager).from(qItem)
                .where(ItemPredicate.search(searchDto))
                .leftJoin(qItem.stockList, qStock).fetchJoin()
                .fetch();
    }

    public Page<Item> searchPage(ItemSearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
        QItem qItem = QItem.item;
        QStock qStock = QStock.stock1;
        JPAQuery<Item> query = new JPAQuery<Item>(entityManager).from(qItem)
                .where(ItemPredicate.search(searchDto))
                .leftJoin(qItem.stockList, qStock).fetchJoin()
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}
