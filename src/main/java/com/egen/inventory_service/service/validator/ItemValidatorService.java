package com.egen.inventory_service.service.validator;

import com.egen.inventory_service.entities.Item;
import com.egen.inventory_service.entities.Stock;
import com.egen.inventory_service.model.ItemDto;
import com.egen.inventory_service.repository.ItemRepository;
import com.egen.inventory_service.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
//@RequiredArgsConstructor
public class ItemValidatorService {
    private final ItemRepository itemRepository;
    private final StockRepository stockRepository;

    public ItemValidatorService(ItemRepository itemRepository, StockRepository stockRepository) {
        this.itemRepository = itemRepository;
        this.stockRepository = stockRepository;
    }

    public Item ifFoundByIdReturnElseThrow(Long id) {
        return itemRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String
                        .format("Sales with id [%s] not found", id)));
    }

    public Stock ifFoundByStockIdReturnElseThrow(Long id) {
        return stockRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String
                        .format("Sales with id [%s] not found", id)));
    }

    public void ifNoItemFoundThrow(ItemDto itemDto) {
        if (CollectionUtils.isEmpty(itemDto.getStockList())) {
            throw new RuntimeException("No items found !!!");
        }
    }
}
