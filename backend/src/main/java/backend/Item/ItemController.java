package backend.Item;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping
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


        if (!itemService.create(itemDTO)) {
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

        return ResponseEntity.ok("Item <"+itemDTO+"> created successfully!\n");
    }

    /**
     * This is our read
     * */

    /**
     * This is our update
     * */

    /**
     * This is our delete
     * */
}
