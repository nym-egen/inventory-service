package com.egen.inventory_service.repository;

import com.egen.inventory_service.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>
        , QuerydslPredicateExecutor<Item> {

}
