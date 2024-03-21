package uz.laza.ecommerce.main.product;

import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.laza.ecommerce.exception.ItemNotFoundException;
import uz.laza.ecommerce.main.brand.Brand;
import uz.laza.ecommerce.main.brand.BrandService;
import uz.laza.ecommerce.main.user.GenderType;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final BrandService brandService;

    public ProductResponse create(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setSize(request.getSize());
        product.setCount(request.getCount());
        product.setGender(request.getGenderType());
        product.setAttachId(request.getAttachId());
        return toDTO(repository.save(product));
    }

    public ProductResponse getById(Integer id) {
        return toDTO(get(id));
    }

    public ProductResponse update(Integer id, ProductRequest request) {
        Product product = get(id);
        product.setName(request.getName());
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setSize(request.getSize());
        product.setCount(request.getCount());
        product.setGender(request.getGenderType());
        product.setAttachId(request.getAttachId());

        return toDTO(repository.save(product));
    }

    public ProductResponse delete(Integer id) {
        Product product = get(id);
        repository.delete(product);
        return null;
    }

    public PageImpl<ProductResponse> paginationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<ProductResponse> dtoList = new ArrayList<>();

        Page<Product> entityPage = repository.findAll(pageable);
        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public PageImpl<ProductResponse> paginationListGeneric(Integer brandId, String gender, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<ProductResponse> dtoList = new ArrayList<>();
        Page<Product> entityPage;

        if (brandId != null && !gender.isBlank())
            entityPage = repository.findAllByGenderAndBrand(GenderType.valueOf(gender), brandService.get(brandId), pageable);

        else if (brandId == null && !gender.isBlank())
            entityPage = repository.findAllByGender(GenderType.valueOf(gender), pageable);

        else if (brandId != null)
            entityPage = repository.findAllByBrand(brandService.get(brandId), pageable);

        else
            entityPage = repository.findAll(pageable);

        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public Product get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    return new ItemNotFoundException("Not found !");
                });
    }

    public ProductResponse toDTO(Product entity) {
        return ProductResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .size(entity.getSize())
                .count(entity.getCount())
                .genderType(entity.getGender())
                .attachId(entity.getAttachId())
                .createdDate(entity.getCreatedDate())
                .lastModified(entity.getLastModified())
                .build();
    }
}
