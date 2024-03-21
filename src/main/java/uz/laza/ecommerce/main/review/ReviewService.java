package uz.laza.ecommerce.main.review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.laza.ecommerce.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewResponse create(ReviewRequest request) {
        Review review = new Review();
        review.setRating(request.getRating());
        review.setDescription(request.getDescription());
        review.setProductId(request.getProductId());
        return toDTO(repository.save(review));
    }

    public ReviewResponse getById(Integer id) {
        return toDTO(get(id));
    }

    public ReviewResponse update(Integer id, ReviewRequest request) {
        Review review  = get(id);
        review.setDescription(request.getDescription());

        return toDTO(repository.save(review));
    }

    public ReviewResponse delete(Integer id) {
        Review review = get(id);
        repository.delete(review);
        return null;
    }

    public PageImpl<ReviewResponse> getList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<ReviewResponse> dtoList = new ArrayList<>();

        Page<Review> entityPage = repository.findAll(pageable);
        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public Review get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    return new ItemNotFoundException("Not found !");
                });
    }

    public ReviewResponse toDTO(Review entity) {
        return ReviewResponse.builder()
                .rating(entity.getRating())
                .productId(entity.getProductId())
                .description(entity.getDescription())
                .build();
    }


}
