package io.github.jaoxavier.myOwnEcormmece.service.product;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.ProductItems;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import io.github.jaoxavier.myOwnEcormmece.exception.product.ProductCantBeFindException;
import io.github.jaoxavier.myOwnEcormmece.exception.product.ProductHasInsufficientStock;
import io.github.jaoxavier.myOwnEcormmece.repository.ProductRepository;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.RawProductDTO;
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
    private ProductItemService productItemService;


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

    public List<ProductItems> getProducts(List<RawProductDTO> products) {
        List<ProductItems> product_list = new ArrayList<>();

        for (RawProductDTO product : products) {
            Product verified_product = getProductById(product.getProduct_id());

            if (!verifyIfProductHasStock(product)){
                throw new ProductHasInsufficientStock("The Stock of " + verified_product.getName() + " is insufficient.");
            }

            verified_product = updateProductStock(verified_product, product.getQuantity());
            product_list.add(productItemService.createProductItem(verified_product, product));
        }

        return product_list;
    }

    public boolean verifyIfProductHasStock(RawProductDTO product) {
        Product verified_product = getProductById(product.getProduct_id());
        return product.getQuantity() <= verified_product.getStock();
    }

    public boolean verifyIfProductHasStock(ProductItems product) {
        Product verified_product = product.getProduct();
        return product.getQuantity() <= verified_product.getStock();
    }

    public Double getTotalValue(List<ProductItems> products) {
        double total_price = 0.0;

        for (ProductItems product : products) {
            Product verified_product = getProductById(product.getProduct().getId());
            total_price += product.getQuantity() * verified_product.getPrice();
        }

        return total_price;
    }

    private Product updateProductStock(Product verified_product, Integer quantity){
        verified_product.setStock(verified_product.getStock() - quantity);
        return saveProduct(verified_product);
    }
}
