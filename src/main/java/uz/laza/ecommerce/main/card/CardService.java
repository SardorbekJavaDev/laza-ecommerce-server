package uz.laza.ecommerce.main.card;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import uz.laza.ecommerce.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository repository;

    public CardResponse create(CardRequest request) {
        Card card = new Card();
        card.setNumber(request.getNumber());
        card.setOwner(request.getOwner());
        card.setCvv(request.getCvv());
        card.setExp(request.getExp());
        card.setType(CardType.VISA); // TODO Create automatic check algorithm
        return toDTO(repository.save(card));
    }

    public CardResponse getById(Integer id) {
        return toDTO(get(id));
    }

    public CardResponse update(Integer id, CardRequest request) {
        Card card = get(id);
        card.setNumber(request.getNumber());
        card.setOwner(request.getOwner());
        card.setCvv(request.getCvv());
        card.setExp(request.getExp());
        card.setType(CardType.VISA); // TODO Create automatic check algorithm
        return toDTO(repository.save(card));
    }

    public CardResponse delete(Integer id) {
        Card card = get(id);
        repository.delete(card);
        return null;
    }

    public PageImpl<CardResponse> paginationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<CardResponse> dtoList = new ArrayList<>();

        Page<Card> entityPage = repository.findAll(pageable);
        entityPage.forEach(entity -> dtoList.add(toDTO(entity)));

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public Card get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    return new ItemNotFoundException("Not found !");
                });
    }

    public CardResponse toDTO(Card entity) {
        return CardResponse.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .owner(entity.getOwner())
                .cvv(entity.getCvv())
                .exp(entity.getExp())
                .createdDate(entity.getCreatedDate())
                .lastModified(entity.getLastModified())
                .build();
    }

}
