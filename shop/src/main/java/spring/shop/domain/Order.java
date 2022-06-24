package spring.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "ORDER_SEQ_GENERATOR",
        sequenceName = "ORDER_SEQ",
        initialValue = 1,
        allocationSize = 1)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ_GENERATOR")
    @Column(name = "order_no")
    private Long no;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private int orderPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_no")
    private Item item;

}
