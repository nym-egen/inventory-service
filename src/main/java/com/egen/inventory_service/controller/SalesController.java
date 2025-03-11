package com.egen.inventory_service.controller;

import com.egen.inventory_service.model.SalesDto;
import com.egen.inventory_service.model.SalesSearchDto;
import com.egen.inventory_service.service.SalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sales")
//@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSales(@RequestBody SalesDto salesDto) {
        salesService.createSales(salesDto);
        return ResponseEntity.ok("Successfully created sales");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSales(@PathVariable Long id,
                                         @RequestBody SalesDto salesDto) {
        salesService.updateSales(id, salesDto);
        return ResponseEntity.ok("Successfully Updated sales");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSalesById(@PathVariable Long id) {
        return ResponseEntity.ok(salesService.getSalesById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSalesById(@PathVariable Long id) {
        salesService.deleteSalesById(id);
        return ResponseEntity.ok("Successfully Deleted sales");
    }

//    @GetMapping
//    public ResponseEntity<?> getAllSales() {
//        return ResponseEntity.ok(salesService.getAllSales());
//    }

    @PostMapping("/search-list")
    public ResponseEntity<?> searchList(@RequestBody SalesSearchDto salesSearchDto) {
        return ResponseEntity.ok(salesService.searchList(salesSearchDto));
    }

    @PostMapping("/search-page")
    public ResponseEntity<?> searchPage(@RequestBody SalesSearchDto salesSearchDto) {
        return ResponseEntity.ok(salesService.searchPage(salesSearchDto));
    }
}
