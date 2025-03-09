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
    /**
     * This is our create
     *
     * */
    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody ItemDTO itemDTO) {
        System.out.printf("Creating Item: %s", itemDTO);

        return ResponseEntity.ok("Creating Item");
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
