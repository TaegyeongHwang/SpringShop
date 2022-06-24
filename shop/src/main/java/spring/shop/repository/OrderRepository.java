package spring.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.shop.domain.Order;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {

        em.persist(order);
    }

    public List<Order> findAll() {

        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }
}
