package com.egen.inventory_service.service.command;

import com.egen.inventory_service.entities.Sales;
import com.egen.inventory_service.repository.SalesRepository;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class SalesCommand {
    private final SalesRepository salesRepository;

    public SalesCommand(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public void save(Sales sales) {
        salesRepository.save(sales);
    }

    public Sales saveAndReturn(Sales sales) {
        return salesRepository.save(sales);
    }

    public void update(Sales sales) {
        salesRepository.save(sales);
    }

    public Sales updateAndReturn(Sales sales) {
        return salesRepository.save(sales);
    }

    public void delete(Sales sales) {
        salesRepository.delete(sales);
    }
}
