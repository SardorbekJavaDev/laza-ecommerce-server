package uz.laza.ecommerce.main.income;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.laza.ecommerce.exception.ItemNotFoundException;
import uz.laza.ecommerce.main.product.Product;
import uz.laza.ecommerce.main.product.ProductResponse;
import uz.laza.ecommerce.main.product.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepository repository;
    private final ProductService productService;

    public IncomeResponse create(IncomeRequest request) {
        Product product = productService.get(request.getProductId());

        Income income = new Income();
        income.setCount(request.getCount());
        income.setProductId(request.getProductId());
        income.setProfitPer(request.getProfitPer());
        income.setUserId(income.getCreatedBy()); // TODO get userID out of JWT
        income.setTotalPrice((request.getProfitPer() * product.getPrice() / 100 + product.getPrice())* request.getCount());
        return toDTO(repository.save(income));
    }

    public IncomeResponse getById(Integer id) {
        return toDTO(get(id));
    }

    public IncomeResponse update(Integer id, IncomeRequest request) {
        Product product = productService.get(request.getProductId());

        Income income = get(id);
        income.setCount(request.getCount());
        income.setProductId(request.getProductId());
        income.setProfitPer(request.getProfitPer());
        income.setTotalPrice((request.getProfitPer() * product.getPrice() / 100 + product.getPrice())* request.getCount());

        return toDTO(repository.save(income));
    }

    public IncomeResponse delete(Integer id) {
        Income income = get(id);
        repository.delete(income);
        return null;
    }

    public PageImpl<IncomeResponse> paginationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<IncomeResponse> dtoList = new ArrayList<>();

        Page<Income> entityPage = repository.findAll(pageable);
        entityPage.forEach(entity -> dtoList.add(toDTO(entity)));

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public Income get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    //todo log
                    return new ItemNotFoundException("Not found !");
                });
    }

    public IncomeResponse toDTO(Income entity) {
        return IncomeResponse.builder()
                .id(entity.getId())
                .count(entity.getCount())
                .profitPer(entity.getProfitPer())
                .totalPrice(entity.getTotalPrice())
                .userId(entity.getUserId())
                .createdDate(entity.getCreatedDate())
                .lastModified(entity.getLastModified())
                .product(ProductResponse.builder()
                        .id(entity.getProduct().getId())
                        .name(entity.getProduct().getName())
                        .title(entity.getProduct().getTitle())
                        .description(entity.getProduct().getDescription())
                        .price(entity.getProduct().getPrice())
                        .size(entity.getProduct().getSize())
                        .count(entity.getProduct().getCount())
                        .attachId(entity.getProduct().getAttachId())
                        .createdDate(entity.getProduct().getCreatedDate())
                        .lastModified(entity.getProduct().getLastModified())
                        .build()
                )
                .build();
    }

}
