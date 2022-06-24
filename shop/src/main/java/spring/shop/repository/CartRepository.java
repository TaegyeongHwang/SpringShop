package spring.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.shop.domain.Cart;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartRepository {

    private final EntityManager em;

    public void save(Cart cart) {

        em.persist(cart);
    }

    public Cart findCart(Long no) {

        return em.createQuery("select c from Cart c join fetch c.item i where c.no = :no", Cart.class)
                .setParameter("no", no)
                .getSingleResult();
    }

    public Optional<Cart> findCartByName(String itemName) {

        List<Cart> carts = em.createQuery("select c from Cart c where c.item.name = :itemName")
                .setParameter("itemName", itemName)
                .getResultList();

        return carts.stream().findAny();
    }

    public List<Cart> findAll() {

        return em.createQuery("select c from Cart c", Cart.class)
                .getResultList();
    }

    public void remove(Cart cart) {

        em.remove(cart);
    }


}
