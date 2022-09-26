package ru.sozaev.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sozaev.springcourse.models.Item;
import ru.sozaev.springcourse.models.Person;
import ru.sozaev.springcourse.repositories.ItemsRepository;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class ItemsService {
    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findByItemName(String itemName) {
        return itemsRepository.findByItemName(itemName);
    }

    public List<Item> findByOwner (Person owner) {
        return itemsRepository.findByOwner(owner);
    }
}
