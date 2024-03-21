package uz.laza.ecommerce.main.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(reviewService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Integer id, @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(reviewService.delete(id));
    }

    @GetMapping("/") // TODO make by user ID, product ID, rating
    public ResponseEntity<?> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(reviewService.getList(page, size));
    }


}
