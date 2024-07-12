package io.github.jaoxavier.myOwnEcormmece.service.product;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.ProductItems;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import io.github.jaoxavier.myOwnEcormmece.repository.ProductItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductItemService {

    @Autowired
    private ProductItemsRepository productItemsRepository;

    public ProductItems createProductItem(Product verifiedProduct, ProductItems product) {
        return ProductItems
                .builder()
                .product(verifiedProduct)
                .quantity(product.getQuantity())
                .build();
    }

    public void deleteProductById(Integer id){
        productItemsRepository.deleteById(id);
    }

    public void deleteProductsOnCart(Integer cart_id){
        productItemsRepository.deleteByCart(cart_id);
    }
}
