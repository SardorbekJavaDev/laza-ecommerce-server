package uz.laza.ecommerce.main.address;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.laza.ecommerce.exception.ItemNotFoundException;
import uz.laza.ecommerce.main.product.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;
    private final ProductService productService;

    public AddressResponse create(AddressRequest request) {
        Address address = new Address();
        address.setAddress(request.getAddress());
        address.setCountry(request.getCountry());
        address.setCity(request.getCity());
        address.setName(request.getName());
        address.setPhone(request.getPhone());
        address.setIsPrimary(request.getIsPrimary());
        return toDTO(repository.save(address));
    }

    public AddressResponse getById(Integer id) {
        return toDTO(get(id));
    }

    public AddressResponse update(Integer id, AddressRequest request) {
        Address address = get(id);
        address.setAddress(request.getAddress());
        address.setCountry(request.getCountry());
        address.setCity(request.getCity());
        address.setName(request.getName());
        address.setPhone(request.getPhone());
        address.setIsPrimary(request.getIsPrimary());
        return toDTO(repository.save(address));
    }

    public AddressResponse delete(Integer id) {
        Address address = get(id);
        repository.delete(address);
        return null;
    }

    public PageImpl<AddressResponse> paginationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<AddressResponse> dtoList = new ArrayList<>();

        Page<Address> entityPage = repository.findAll(pageable);
        entityPage.forEach(entity -> dtoList.add(toDTO(entity)));

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public Address get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    return new ItemNotFoundException("Not found !");
                });
    }

    public AddressResponse toDTO(Address entity) {
        return AddressResponse.builder()
                .country(entity.getCountry())
                .name(entity.getName())
                .city(entity.getCity())
                .city(entity.getCity())
                .isPrimary(entity.getIsPrimary())
                .build();
    }

}
