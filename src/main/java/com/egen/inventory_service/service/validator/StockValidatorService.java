package com.egen.inventory_service.service.validator;

import com.egen.inventory_service.entities.SalesItem;
import com.egen.inventory_service.entities.Stock;
import com.egen.inventory_service.model.SalesItemDto;
import com.egen.inventory_service.repository.ItemRepository;
import com.egen.inventory_service.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class StockValidatorService {

    private final StockRepository stockRepository;
    private final ItemRepository itemRepository;

    public StockValidatorService(StockRepository stockRepository, ItemRepository itemRepository) {
        this.stockRepository = stockRepository;
        this.itemRepository = itemRepository;
    }

    public Stock ifFoundByIdReturnElseThrow(Long id) {
        return stockRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String
                        .format("Customer with id [%s] not found", id)));
    }

    public void ifStockDecreaseFoundThrow(SalesItem salesItem, SalesItemDto salesItemDto) {
        itemRepository.findById(salesItemDto.getItemId()).ifPresent(item -> {
            salesItem.setItem(item);
            Stock stock = stockRepository.findByItemId(item.getId()).orElseThrow(() ->
                    new RuntimeException("No stock found !!!"));
            if (stock.getStock() < salesItemDto.getQuantity()) {
                throw new RuntimeException("Not enough stock !!!");
            }
            stock.setStock(stock.getStock() - salesItemDto.getQuantity());
            stockRepository.save(stock);
        });
        salesItem.setQuantity(salesItemDto.getQuantity());
        salesItem.setPrice(salesItemDto.getPrice());
    }

    public void ifStockIncreaseFoundThrow(SalesItem salesItem) {
        itemRepository.findById(salesItem.getItemId()).ifPresent(item -> {
            salesItem.setItem(item);
            Stock stock = stockRepository.findByItemId(item.getId()).orElseThrow(() ->
                    new RuntimeException("No stock found !!!"));
            stock.setStock(stock.getStock() + salesItem.getQuantity());
            stockRepository.save(stock);
        });
    }
}
