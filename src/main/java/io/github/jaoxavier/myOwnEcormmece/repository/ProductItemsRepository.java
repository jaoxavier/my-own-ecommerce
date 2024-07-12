package io.github.jaoxavier.myOwnEcormmece.repository;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Cart;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.ProductItems;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ProductItemsRepository extends JpaRepository<ProductItems, Integer> {

    @Query(
            value = "DELETE FROM PRODUCT_ITEMS WHERE CART_ID = :cart_id",
            nativeQuery = true
    )
    @Modifying
    @Transactional
    void deleteByCart(Integer cart_id);

    @Query(
            value = "DELETE FROM PRODUCT_ITEMS WHERE ID = :id",
            nativeQuery = true
    )
    @Modifying
    @Transactional
    void deleteById(Integer id);
}
