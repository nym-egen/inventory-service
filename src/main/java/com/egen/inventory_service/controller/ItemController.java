package com.egen.inventory_service.controller;

import com.egen.inventory_service.model.ItemDto;
import com.egen.inventory_service.model.ItemSearchDto;
import com.egen.inventory_service.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/items")
//@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto) {
        itemService.createItem(itemDto);
        return ResponseEntity.ok("Successfully created item");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id,
                                        @RequestBody ItemDto itemDto) {
        itemService.updateItem(id, itemDto);
        return ResponseEntity.ok("Successfully Update item");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return ResponseEntity.ok("Successfully Delete item");
    }

//    @GetMapping
//    public ResponseEntity<?> getAllItems() {
//        return ResponseEntity.ok(itemService.getAllItems());
//    }

    @PostMapping("/search-list")
    public ResponseEntity<?> searchList(@RequestBody ItemSearchDto itemSearchDto) {
        return ResponseEntity.ok(itemService.searchList(itemSearchDto));
    }

    @PostMapping("/search-page")
    public ResponseEntity<?> searchPage(@RequestBody ItemSearchDto itemSearchDto) {
        return ResponseEntity.ok(itemService.searchPage(itemSearchDto));
    }
}
