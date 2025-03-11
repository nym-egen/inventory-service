package com.egen.inventory_service.service.command;

import com.egen.inventory_service.entities.Item;
import com.egen.inventory_service.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class ItemCommand {

    private final ItemRepository itemRepository;

    public ItemCommand(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void save(Item item) {
        itemRepository.save(item);
    }

    public Item saveAndReturn(Item item) {
        return itemRepository.save(item);
    }

    public void update(Item item) {
        itemRepository.save(item);
    }

    public Item updateAndReturn(Item item) {
        return itemRepository.save(item);
    }

    public void delete(Item item) {
        itemRepository.delete(item);
    }
}
