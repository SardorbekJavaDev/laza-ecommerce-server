package uz.laza.ecommerce.main.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.laza.ecommerce.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentResponse create(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setTotalPrice(request.getTotalPrice());
        payment.setStatus(PaymentStatus.COMPLETED);  // TODO Have to write some logic if you make complete payment status
        return toDTO(repository.save(payment));
    }

    public PaymentResponse getById(Integer id) {
        return toDTO(get(id));
    }

    public PaymentResponse delete(Integer id) {
        Payment payment = get(id);
        repository.delete(payment);
        return null;
    }

    public PageImpl<PaymentResponse> getList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<PaymentResponse> dtoList = new ArrayList<>();

        Page<Payment> entityPage = repository.findAll(pageable);
        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public Payment get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    //todo log
                    return new ItemNotFoundException("Not found !");
                });
    }

    public PaymentResponse toDTO(Payment entity) {
        return PaymentResponse.builder()
                .totalPrice(entity.getTotalPrice())
                .createdDate(entity.getCreatedDate())
                .lastModified(entity.getLastModified())
                .status(entity.getStatus())
                .build();
    }


}
