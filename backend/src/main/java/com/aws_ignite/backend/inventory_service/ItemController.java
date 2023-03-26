package com.aws_ignite.backend.inventory_service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aws_ignite.backend.inventory_service.Item;
import com.aws_ignite.backend.inventory_service.ItemRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    // @GetMapping(path = "/items")
    // public @ResponseBody Iterable<Item> getAllItems() {
    // return itemRepository.findAll();
    // }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) Map<String, Object> qparams) {
        try {
            List<Item> items = new ArrayList<Item>();

            itemRepository.findAll().forEach(items::add);
            
            Integer totalCount = items.size();

            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Expose-Headers", "X-Total-Count");
            headers.add("X-Total-Count", totalCount.toString());
            return new ResponseEntity<>(items, headers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Integer id) {
        Optional<Item> itemData = itemRepository.findById(id);

        if (itemData.isPresent()) {
            return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        try {
            Item _item = itemRepository.save(new Item(item.getSku(), item.getDescription()));
            return new ResponseEntity<>(_item, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    // @PutMapping("/items/{id}")
    // public ResponseEntity<Item> updateItem(@PathVariable("id") long id,
    // @RequestBody Item item) {
    // Optional<Item> itemData = itemRepository.findById(id);

    // if (itemData.isPresent()) {
    // Item _item = itemData.get();
    // _item.setTitle(item.getTitle());
    // _item.setDescription(item.getDescription());
    // _item.setPublished(item.isPublished());
    // return new ResponseEntity<>(itemRepository.save(_item), HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // }

    // @DeleteMapping("/items/{id}")
    // public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id)
    // {
    // try {
    // itemRepository.deleteById(id);
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    // }
    // }

    // @DeleteMapping("/items")
    // public ResponseEntity<HttpStatus> deleteAllItems() {
    // try {
    // itemRepository.deleteAll();
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    // }

    // }

    // @GetMapping("/items/published")
    // public ResponseEntity<List<Item>> findByPublished() {
    // try {
    // List<Item> items = itemRepository.findByPublished(true);

    // if (items.isEmpty()) {
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
    // return new ResponseEntity<>(items, HttpStatus.OK);
    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    // }
    // }

}
