package uz.laza.ecommerce.main.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(orderService.delete(id));
    }

    @GetMapping("/") // all
    public ResponseEntity<?> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(orderService.getList(page, size));
    }

    @GetMapping("/{status}") // by status
    public ResponseEntity<?> getListByStatus(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "5") int size,
                                             @PathVariable String status) {
        return ResponseEntity.ok(orderService.getListByStatus(status, page, size));
    }


}
