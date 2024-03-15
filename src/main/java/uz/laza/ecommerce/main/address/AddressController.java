package uz.laza.ecommerce.main.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {


    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AddressRequest request) {
        return ResponseEntity.ok(addressService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(addressService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody AddressRequest request) {
        return ResponseEntity.ok(addressService.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(addressService.delete(id));
    }

    @GetMapping("/") // TODO make by category
    public ResponseEntity<?> getProductList(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(addressService.paginationList(page, size));
    }


}
