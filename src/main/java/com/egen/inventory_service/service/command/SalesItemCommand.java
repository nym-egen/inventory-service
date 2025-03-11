package com.egen.inventory_service.service.command;

import com.egen.inventory_service.entities.SalesItem;
import com.egen.inventory_service.repository.SalesItemRepository;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class SalesItemCommand {

    private final SalesItemRepository salesItemRepository;

    public SalesItemCommand(SalesItemRepository salesItemRepository) {
        this.salesItemRepository = salesItemRepository;
    }

    public void save(SalesItem salesItem) {
        salesItemRepository.save(salesItem);
    }

    public SalesItem saveAndReturn(SalesItem salesItem) {
        return salesItemRepository.save(salesItem);
    }

    public void update(SalesItem salesItem) {
        salesItemRepository.save(salesItem);
    }

    public SalesItem updateAndReturn(SalesItem salesItem) {
        return salesItemRepository.save(salesItem);
    }

    public void delete(SalesItem salesItem) {
        salesItemRepository.delete(salesItem);
    }
}
