package io.github.jaoxavier.myOwnEcormmece.service.product;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.ProductItems;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.RawProductDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductItemService {

    public ProductItems createProductItem(Product verifiedProduct, RawProductDTO product) {
        return ProductItems
                .builder()
                .product(verifiedProduct)
                .quantity(product.getQuantity())
                .build();
    }
}
