package backend.Item;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vendor/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * This is our create
     *
     * */
    @PostMapping("/create")
    public ResponseEntity<String> createItem(@RequestBody ItemDTO itemDTO) {
        if (!itemDTO.isValid()) {
            return ResponseEntity.badRequest().body("""
            \nInvalid Item
            Make sure the following fields are valid and present
            name,
            description,
            stockCount,
            vendorId
            """);
        }


        Item item = itemService.create(itemDTO);
        if (item == null) {
            return ResponseEntity.badRequest().body(
                    """
                            \nInvalid Item
                            Seems like vendorId is non-existent or item already exists
                            
                            Make sure the following fields are valid and present
                            name,
                            description,
                            stockCount,
                            vendorId.
                            
                            """
            );
        }

        return ResponseEntity.ok("Item with id: <"+item.getId()+"> created successfully!\n");
    }

    /**
     * This is our read
     * */
    @GetMapping("/read/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        Optional<Item> item = itemService.findById(id);
       return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * This is our update
     * */
    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateItem(@RequestBody ItemDTO itemDTO, @PathVariable Long id) {
        if (!itemDTO.isValid()) {
            return ResponseEntity.badRequest().body("""
            \nInvalid Item details
            Make sure the following fields are valid and present
            name,
            description,
            stockCount,
            vendorId
            """);
        }


        Item item = itemService.update(itemDTO, id);
        if (item == null) {
            return ResponseEntity.badRequest().body(
                    """
                            \nInvalid Item details
                            Seems like vendorId or itemId is non-existent or item already exists
                            
                            Also make sure the following fields are valid and present
                            name,
                            description,
                            stockCount,
                            vendorId.
                            
                            """
            );
        }

        return ResponseEntity.ok("Item with id: <"+item.getId()+"> updated successfully!\n");
    }


    /**
     * This is our delete
     * */
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        Optional<Item> item = itemService.findById(id);
        if (item.isPresent()) {
            itemService.delete(id);
            return ResponseEntity.ok("Item with id: <"+id+"> deleted successfully!\n");
        }

        return ResponseEntity.notFound().build();
    }
}
