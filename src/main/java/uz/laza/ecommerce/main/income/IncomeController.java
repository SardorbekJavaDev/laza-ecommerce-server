package uz.laza.ecommerce.main.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/income")
public class IncomeController {


    @Autowired
    private IncomeService incomeservice;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody IncomeRequest request) {
        return ResponseEntity.ok(incomeservice.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(incomeservice.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody IncomeRequest request) {
        return ResponseEntity.ok(incomeservice.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(incomeservice.delete(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(incomeservice.paginationList(page, size));
    }


}
