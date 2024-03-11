package uz.laza.ecommerce.main.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @PostMapping("/{productId}") // userID
    public ResponseEntity<?> create(@PathVariable Integer productId) {
        return ResponseEntity.ok(wishListService.create(productId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishListResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(wishListService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(wishListService.delete(id));
    }

    @GetMapping("/") // TODO make by category
    public ResponseEntity<?> getProductList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(wishListService.paginationList(page, size));
    }


}
