package com.egen.inventory_service.service;

import com.egen.inventory_service.entities.Item;
import com.egen.inventory_service.entities.Stock;
import com.egen.inventory_service.model.ItemDto;
import com.egen.inventory_service.model.ItemResponse;
import com.egen.inventory_service.model.ItemSearchDto;
import com.egen.inventory_service.repository.ItemRepository;
import com.egen.inventory_service.repository.StockRepository;
import com.egen.inventory_service.service.mapper.ItemMapper;
import com.egen.inventory_service.service.mapper.ItemResponseMapper;
import com.egen.inventory_service.service.query.ItemQueryService;
import com.egen.inventory_service.service.validator.ItemValidatorService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final StockRepository stockRepository;
    private final ItemValidatorService itemValidatorService;
    private final ItemQueryService itemQueryService;
    private final ItemMapper itemMapper;
    private final ItemResponseMapper itemResponseMapper;

    public ItemService(ItemRepository itemRepository, StockRepository stockRepository, ItemValidatorService itemValidatorService, ItemQueryService itemQueryService, ItemMapper itemMapper, ItemResponseMapper itemResponseMapper) {
        this.itemRepository = itemRepository;
        this.stockRepository = stockRepository;
        this.itemValidatorService = itemValidatorService;
        this.itemQueryService = itemQueryService;
        this.itemMapper = itemMapper;
        this.itemResponseMapper = itemResponseMapper;
    }

    @Transactional
    public void createItem(ItemDto itemDto) {
        Item item = itemMapper.apply(itemDto);
        itemRepository.save(item);

        itemValidatorService.ifNoItemFoundThrow(itemDto);

        List<Stock> stockList = itemDto.getStockList().stream()
                .map(stk -> {
                    Stock stock = new Stock();
                    BeanUtils.copyProperties(stk, stock);
                    stock.setItem(item);
                    return stock;
                }).toList();
        stockRepository.saveAll(stockList);
    }

    @Transactional
    public void updateItem(Long id, ItemDto itemDto) {
        Item item = itemValidatorService.ifFoundByIdReturnElseThrow(id);
        BeanUtils.copyProperties(itemDto, item);

        itemValidatorService.ifNoItemFoundThrow(itemDto);

        List<Stock> stockList = itemDto.getStockList().stream()
                .map(stk -> {
                    Stock stock = itemValidatorService.ifFoundByStockIdReturnElseThrow(id);
                    BeanUtils.copyProperties(stk, stock, "id");
                    stock.setItem(item);
                    return stock;
                }).toList();
        itemRepository.save(item);
        stockRepository.saveAll(stockList);
    }

    public ItemResponse getItemById(Long id) {
        Item item = itemValidatorService.ifFoundByIdReturnElseThrow(id);
//        Stock stockList = itemValidatorService.ifFoundByStockIdReturnElseThrow(item.getId());
        return itemResponseMapper.apply(item);
    }

    @Transactional
    public void deleteItemById(Long id) {
        Item item = itemValidatorService.ifFoundByIdReturnElseThrow(id);
        stockRepository.deleteAll(item.getStockList());
        itemRepository.delete(item);
    }

    public List<ItemResponse> searchList(ItemSearchDto searchDto) {
        return itemQueryService.searchList(searchDto).stream()
                .map(item -> {
                    ItemResponse itemResponse = itemResponseMapper.apply(item);
                    itemResponse.setStock(item.getStockList().stream()
                            .mapToInt(Stock::getStock).sum());
                    return itemResponse;
                }).toList();
    }

    public Page<ItemResponse> searchPage(ItemSearchDto itemSearchDto) {
        Page<Item> items = itemQueryService.searchPage(itemSearchDto);
        List<ItemResponse> resultList = items.getContent()
                .stream().map(item -> {
                    ItemResponse itemResponse = itemResponseMapper.apply(item);
                    itemResponse.setStock(item.getStockList().stream()
                            .mapToInt(Stock::getStock).sum());
                    return itemResponse;
                }).toList();
        return new PageImpl<>(resultList, items.getPageable(), items.getTotalElements());
    }
//    public List<ItemResponse> searchPage(ItemSearchDto itemSearchDto) {
//        return Utils.convertToList(itemRepository
//                        .findAll(ItemPredicate.search(itemSearchDto), Sort.by("name")))
//                .stream().map(item -> {
//                    ItemResponse itemResponse = new ItemResponse();
//                    BeanUtils.copyProperties(item, itemResponse);
//                    itemResponse.setStock(item.getStockList().stream()
//                            .mapToInt(Stock::getStock).sum());
//                    return itemResponse;
//                }).toList();
//    }
}
