package io.github.jaoxavier.myOwnEcormmece.service.product;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.OrderItems;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import io.github.jaoxavier.myOwnEcormmece.exception.product.ProductCantBeFindException;
import io.github.jaoxavier.myOwnEcormmece.exception.product.ProductHasInsufficientStock;
import io.github.jaoxavier.myOwnEcormmece.repository.ProductRepository;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.RawProductDTO;
import io.github.jaoxavier.myOwnEcormmece.service.order.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderProductService orderProductService;


    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product getProductById(Integer id){
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new ProductCantBeFindException("Order can't be find by ID");
        }

        return product.get();
    }

    public List<OrderItems> getProducts(List<RawProductDTO> products) {
        List<OrderItems> product_list = new ArrayList<>();

        for (RawProductDTO product : products) {
            Product verified_product = getProductById(product.getProduct_id());

            if (product.getQuantity() > verified_product.getStock()) {
                throw new ProductHasInsufficientStock("The Stock of " + verified_product.getName() + " is insufficient.");
            }

            verified_product = updateProductStock(verified_product, product.getQuantity());

            product_list.add(orderProductService.createOrderProduct(verified_product, product));
        }

        return product_list;
    }

    public Double getTotalValue(List<OrderItems> products) {
        double total_price = 0.0;

        for (OrderItems product : products) {
            Product verified_product = getProductById(product.getProduct_id());
            total_price += product.getQuantity() * verified_product.getPrice();
        }

        return total_price;
    }

    private Product updateProductStock(Product verified_product, Integer quantity){
        verified_product.setStock(verified_product.getStock() - quantity);
        return saveProduct(verified_product);
    }
}
