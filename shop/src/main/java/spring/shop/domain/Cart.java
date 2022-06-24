package spring.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "CART_SEQ_GENERATOR",
        sequenceName = "CART_SEQ",
        initialValue = 1,
        allocationSize = 1)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_SEQ_GENERATOR")
    @Column(name = "cart_no")
    private Long no;

    @Column(nullable = false)
    private int count;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_no")
    private Item item;

}
