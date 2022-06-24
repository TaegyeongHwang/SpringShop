package spring.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.shop.domain.Item;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {

        em.persist(item);
    }

    public Item findItem(Long no) {

        return em.find(Item.class, no);
    }

    public Optional<Item> findItemByName(String itemName) {

        List<Item> items =  em.createQuery("select i from Item i where i.name = :itemName")
                .setParameter("itemName", itemName)
                .getResultList();

        return items.stream().findAny();
    }

    public List<Item> findAll() {

        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
