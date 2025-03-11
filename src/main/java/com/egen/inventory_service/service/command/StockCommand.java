package com.egen.inventory_service.service.command;

import com.egen.inventory_service.entities.Stock;
import com.egen.inventory_service.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class StockCommand {
    private final StockRepository stockRepository;

    public StockCommand(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void save(Stock stock) {
        stockRepository.save(stock);
    }

    public Stock saveAndReturn(Stock stock) {
        return stockRepository.save(stock);
    }

    public void update(Stock stock) {
        stockRepository.save(stock);
    }

    public Stock updateAndReturn(Stock stock) {
        return stockRepository.save(stock);
    }

    public void delete(Stock stock) {
        stockRepository.delete(stock);
    }
}
