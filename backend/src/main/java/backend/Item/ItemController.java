package backend.Item;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
    @PostMapping("")
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
                            Seems like vendorId is non-existent
                            
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
    @GetMapping("{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        Optional<Item> item = itemService.findById(id);
       return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * This is our update
     * */

    /**
     * This is our delete
     * */
}
