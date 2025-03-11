package com.egen.inventory_service.service;

import com.egen.inventory_service.entities.Stock;
import com.egen.inventory_service.model.StockResponse;
import com.egen.inventory_service.model.StockSearchDto;
import com.egen.inventory_service.service.mapper.StockMapper;
import com.egen.inventory_service.service.mapper.StockResponseMapper;
import com.egen.inventory_service.service.query.StockQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class StockService {

    private final StockQueryService stockQueryService;
    private final StockMapper stockMapper;
    private final StockResponseMapper stockResponseMapper;

    public StockService(StockQueryService stockQueryService, StockMapper stockMapper, StockResponseMapper stockResponseMapper) {
        this.stockQueryService = stockQueryService;
        this.stockMapper = stockMapper;
        this.stockResponseMapper = stockResponseMapper;
    }

    public List<StockResponse> searchList(StockSearchDto searchDto) {
        return stockQueryService.searchList(searchDto).stream()
                .map(stockResponseMapper).toList();
    }

    public Page<StockResponse> searchPage(StockSearchDto searchDto) {
        Page<Stock> page = stockQueryService.searchPage(searchDto);
        List<StockResponse> resultList = stockQueryService.searchList(searchDto).stream()
                .map(stockResponseMapper).toList();
        return new PageImpl<>(resultList, page.getPageable(), page.getTotalElements());
    }
}
