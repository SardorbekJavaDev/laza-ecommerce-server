package uz.laza.ecommerce.main.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {


    @Autowired
    private BrandService brandService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BrandRequest request) {
        return ResponseEntity.ok(brandService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(brandService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody BrandRequest request) {
        return ResponseEntity.ok(brandService.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(brandService.delete(id));
    }

    @GetMapping("/") // TODO make by category
    public ResponseEntity<?> getProductList(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(brandService.paginationList(page, size));
    }


}
