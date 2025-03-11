package com.egen.inventory_service.service;

import com.egen.inventory_service.entities.SalesItem;
import com.egen.inventory_service.model.SalesItemResponse;
import com.egen.inventory_service.model.SalesItemSearchDto;
import com.egen.inventory_service.service.mapper.SalesItemMapper;
import com.egen.inventory_service.service.mapper.SalesItemResponseMapper;
import com.egen.inventory_service.service.query.SalesItemQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class SalesItemService {

    private final SalesItemQueryService salesItemQueryService;
    private final SalesItemMapper salesItemMapper;
    private final SalesItemResponseMapper salesItemResponseMapper;

    public SalesItemService(SalesItemQueryService salesItemQueryService, SalesItemMapper salesItemMapper, SalesItemResponseMapper salesItemResponseMapper) {
        this.salesItemQueryService = salesItemQueryService;
        this.salesItemMapper = salesItemMapper;
        this.salesItemResponseMapper = salesItemResponseMapper;
    }

    public List<SalesItemResponse> searchList(SalesItemSearchDto searchDto) {
        return salesItemQueryService.searchList(searchDto).stream()
                .map(salesItemResponseMapper).toList();
    }

    public Page<SalesItemResponse> searchPage(SalesItemSearchDto searchDto) {
        Page<SalesItem> page = salesItemQueryService.searchPage(searchDto);
        List<SalesItemResponse> resultList = salesItemQueryService.searchList(searchDto).stream()
                .map(salesItemResponseMapper).toList();
        return new PageImpl<>(resultList, page.getPageable(), page.getTotalElements());
    }
}
