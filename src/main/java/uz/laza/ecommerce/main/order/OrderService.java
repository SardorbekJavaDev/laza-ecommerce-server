package uz.laza.ecommerce.main.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.laza.ecommerce.exception.ItemNotFoundException;
import uz.laza.ecommerce.main.product.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final ProductService productService;

    public OrderResponse create(OrderRequest request) {
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setCount(request.getCount());
        order.setPrice(productService.get(request.getProductId()).getPrice() * request.getCount());
        order.setStatus(OrderStatus.UNPAID); // todo IF stripe or mastercard payment is successful
        return toDTO(repository.save(order));
    }

    public OrderResponse getById(Integer id) {
        return toDTO(get(id));
    }

    public OrderResponse delete(Integer id) {
        Order order = get(id);
        repository.delete(order);
        return null;
    }

    public PageImpl<OrderResponse> getList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<OrderResponse> dtoList = new ArrayList<>();

        Page<Order> entityPage = repository.findAll(pageable);
        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public Order get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    //todo log
                    return new ItemNotFoundException("Not found !");
                });
    }

    public OrderResponse toDTO(Order entity) {
        return OrderResponse.builder()
                .productId(entity.getProductId())
                .price(entity.getPrice())
                .count(entity.getCount())
                .lastModified(entity.getLastModified())
                .createdDate(entity.getCreatedDate())
                .build();
    }


}
