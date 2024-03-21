package uz.laza.ecommerce.main.wishlist;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.laza.ecommerce.exception.ItemNotFoundException;
import uz.laza.ecommerce.main.product.Product;
import uz.laza.ecommerce.main.product.ProductRepository;
import uz.laza.ecommerce.main.product.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository repository;
    private final ProductService productService;

    public WishListResponse create(Integer productId) {
        Product product = productService.get(productId);
        WishList wishList = new WishList();
        wishList.setUserId(wishList.getCreatedBy());
        wishList.setProductId(productId);
        return toDTO(repository.save(wishList));
    }

    public WishListResponse getById(Integer id) {
        return toDTO(get(id));
    }

    public WishListResponse delete(Integer id) {
        WishList wishList = get(id);
        repository.delete(wishList);
        return null;
    }

    public PageImpl<WishListResponse> paginationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<WishListResponse> dtoList = new ArrayList<>();

        Page<WishList> entityPage = repository.findAll(pageable);
        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public WishList get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    return new ItemNotFoundException("Not found !");
                });
    }

    public WishListResponse toDTO(WishList entity) {
        return WishListResponse.builder()
                .userId(entity.getUserId())
                .createdDate(entity.getCreatedDate())
                .lastModified(entity.getLastModified())
                .id(entity.getId())
                .build();
    }

}
