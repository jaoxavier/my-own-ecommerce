package io.github.jaoxavier.myOwnEcormmece.service.order;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.OrderItems;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.RawProductDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService {

    public OrderItems createOrderProduct(Product verifiedProduct, RawProductDTO product) {
        return OrderItems
                .builder()
                .product_id(verifiedProduct.getId())
                .quantity(product.getQuantity())
                .price(verifiedProduct.getPrice())
                .build();
    }
}
