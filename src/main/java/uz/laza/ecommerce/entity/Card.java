package uz.laza.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.laza.ecommerce.enums.CardType;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "card")
public class Card extends Brand {
    @Column
    private String owner;
    @Column
    private String number;
    @Column
    private String cvv;
    @Column
    private LocalDate exp;
    @Column
    @Enumerated(EnumType.STRING)
    private CardType type;

    @Column(name = "user_id")
    private UUID userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",updatable = false,insertable = false)
    private User user;

}
