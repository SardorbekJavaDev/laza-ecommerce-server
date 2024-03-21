package uz.laza.ecommerce.main.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/card")
public class CardController {


    @Autowired
    private CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CardRequest request) {
        return ResponseEntity.ok(cardService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(cardService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CardRequest request) {
        return ResponseEntity.ok(cardService.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(cardService.delete(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(cardService.paginationList(page, size));
    }


}
