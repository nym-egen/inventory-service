package com.egen.inventory_service.service.validator;

import com.egen.inventory_service.entities.Sales;
import com.egen.inventory_service.entities.SalesItem;
import com.egen.inventory_service.model.SalesDto;
import com.egen.inventory_service.repository.SalesItemRepository;
import com.egen.inventory_service.repository.SalesRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
//@RequiredArgsConstructor
public class SalesValidatorService {

    private final SalesRepository salesRepository;
    private final SalesItemRepository salesItemRepository;

    public SalesValidatorService(SalesRepository salesRepository, SalesItemRepository salesItemRepository) {
        this.salesRepository = salesRepository;
        this.salesItemRepository = salesItemRepository;
    }

    public Sales ifFoundByIdReturnElseThrow(Long id) {
        return salesRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String
                        .format("Sales with id [%s] not found", id)));
    }

    public SalesItem ifFoundBySalesItemIdReturnElseThrow(Long id) {
        return salesItemRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String
                        .format("Sales with id [%s] not found", id)));
    }

    public void ifNoItemFoundThrow(SalesDto salesDto) {
        if (CollectionUtils.isEmpty(salesDto.getSalesItemList())) {
            throw new RuntimeException("No items found !!!");
        }
    }
}
