package com.egen.inventory_service.service;


import com.egen.inventory_service.entities.Sales;
import com.egen.inventory_service.entities.SalesItem;
import com.egen.inventory_service.model.SalesDto;
import com.egen.inventory_service.model.SalesResponse;
import com.egen.inventory_service.model.SalesSearchDto;
import com.egen.inventory_service.repository.SalesItemRepository;
import com.egen.inventory_service.repository.SalesRepository;
import com.egen.inventory_service.service.mapper.SalesMapper;
import com.egen.inventory_service.service.mapper.SalesResponseMapper;
import com.egen.inventory_service.service.query.SalesQueryService;
import com.egen.inventory_service.service.validator.CustomerValidatorService;
import com.egen.inventory_service.service.validator.SalesValidatorService;
import com.egen.inventory_service.service.validator.StockValidatorService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class SalesService {

    private final CustomerValidatorService customerValidatorService;
    private final SalesValidatorService salesValidatorService;
    private final SalesRepository salesRepository;
    private final SalesItemRepository salesItemRepository;
    private final StockValidatorService stockValidatorService;
    private final SalesQueryService salesQueryService;
    private final SalesMapper salesMapper;
    private final SalesResponseMapper salesResponseMapper;

    public SalesService(CustomerValidatorService customerValidatorService, SalesValidatorService salesValidatorService, SalesRepository salesRepository, SalesItemRepository salesItemRepository, StockValidatorService stockValidatorService, SalesQueryService salesQueryService, SalesMapper salesMapper, SalesResponseMapper salesResponseMapper) {
        this.customerValidatorService = customerValidatorService;
        this.salesValidatorService = salesValidatorService;
        this.salesRepository = salesRepository;
        this.salesItemRepository = salesItemRepository;
        this.stockValidatorService = stockValidatorService;
        this.salesQueryService = salesQueryService;
        this.salesMapper = salesMapper;
        this.salesResponseMapper = salesResponseMapper;
    }

    @Transactional
    public void createSales(SalesDto salesDto) {
        Sales sales = salesMapper.apply(salesDto);

        if (salesDto.getCustomerId() != null) {
            sales.setCustomer(customerValidatorService.ifFoundByIdReturnElseThrow(salesDto.getCustomerId()));
        }
        salesRepository.save(sales);

        salesValidatorService.ifNoItemFoundThrow(salesDto);

        List<SalesItem> salesItemList = salesDto.getSalesItemList().stream()
                .map(item -> {
                    var salesItem = new SalesItem();
                    salesItem.setSales(sales);
                    stockValidatorService.ifStockDecreaseFoundThrow(salesItem, item);
                    return salesItem;
                }).collect(Collectors.toList());
        salesItemRepository.saveAll(salesItemList);
    }

    @Transactional
    public void updateSales(Long id, SalesDto salesDto) {
        Sales sales = salesValidatorService.ifFoundByIdReturnElseThrow(id);
        BeanUtils.copyProperties(salesDto, sales);
        if (salesDto.getCustomerId() != null) {
            sales.setCustomer(customerValidatorService.ifFoundByIdReturnElseThrow(salesDto.getCustomerId()));
        }
        salesRepository.save(sales);

        salesValidatorService.ifNoItemFoundThrow(salesDto);

        List<SalesItem> deleteSalesItems = salesItemRepository.findAllBySalesId(sales.getId());
        deleteSalesItems.forEach(stockValidatorService::ifStockIncreaseFoundThrow);
        salesItemRepository.deleteAll(deleteSalesItems);

        List<SalesItem> salesItemList = salesDto.getSalesItemList().stream()
                .map(item -> {
                    SalesItem salesItem = salesValidatorService.ifFoundBySalesItemIdReturnElseThrow(id);
                    stockValidatorService.ifStockDecreaseFoundThrow(salesItem, item);
                    return salesItem;
                }).toList();
        salesItemRepository.saveAll(salesItemList);
    }

    public SalesResponse getSalesById(Long id) {
        Sales sales = salesValidatorService.ifFoundByIdReturnElseThrow(id);
//        List<SalesItem> salesItems = salesItemRepository.findBySalesId(sales.getId());
        return salesResponseMapper.apply(sales);
    }

    @Transactional
    public void deleteSalesById(Long id) {
        Sales sales = salesValidatorService.ifFoundByIdReturnElseThrow(id);

        List<SalesItem> salesItemList = sales.getSalesItemList().stream()
                .map(itm -> {
                    SalesItem salesItem = new SalesItem();
                    salesItem.setSales(sales);
                    stockValidatorService.ifStockIncreaseFoundThrow(itm);
                    salesItemRepository.deleteById(itm.getId());
                    return salesItem;
                }).toList();
        salesRepository.delete(sales);
    }

    public List<SalesResponse> searchList(SalesSearchDto searchDto) {
        return salesQueryService.searchList(searchDto).stream()
                .map(sales -> {
                    SalesResponse salesResponse = salesResponseMapper.apply(sales);
                    salesResponse.setCustomerName(sales.getCustomer().getName());
                    salesResponse.setTotalAmount(sales.getSalesItemList().stream()
                            .mapToDouble(value -> value.getQuantity() * value.getPrice()).sum());
                    return salesResponse;
                }).toList();
    }

    public Page<SalesResponse> searchPage(SalesSearchDto salesSearchDto) {

        Page<Sales> page = salesQueryService.searchPage(salesSearchDto);
        List<SalesResponse> resultList = page.getContent()
                .stream().map(sales -> {
                    SalesResponse salesResponse = salesResponseMapper.apply(sales);
                    salesResponse.setCustomerName(sales.getCustomer().getName());
                    salesResponse.setTotalAmount(sales.getSalesItemList().stream()
                            .mapToDouble(value -> value.getQuantity() * value.getPrice()).sum());
                    return salesResponse;
                }).toList();
        return new PageImpl<>(resultList, page.getPageable(), page.getTotalElements());
    }
}