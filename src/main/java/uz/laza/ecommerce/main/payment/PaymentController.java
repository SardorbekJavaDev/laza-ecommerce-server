package uz.laza.ecommerce.main.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(paymentService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(paymentService.delete(id));
    }

    @GetMapping("/") // TODO make by category
    public ResponseEntity<?> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(paymentService.getList(page, size));
    }


}
