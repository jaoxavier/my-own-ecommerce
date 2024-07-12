package io.github.jaoxavier.myOwnEcormmece.service.product;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.ProductItems;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import io.github.jaoxavier.myOwnEcormmece.exception.product.ProductCantBeFindException;
import io.github.jaoxavier.myOwnEcormmece.exception.product.ProductHasInsufficientStock;
import io.github.jaoxavier.myOwnEcormmece.repository.ProductRepository;
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

    public List<ProductItems> getProducts(List<ProductItems> products) {
        List<ProductItems> product_list = new ArrayList<>();

        for (ProductItems product_item : products){
            if (verifyIfProductHasNoStock(product_item)){
                throw new ProductHasInsufficientStock("The product " + product_item.getProduct().getName() + " has insufficient stock!");
            }

            Product product = updateProductStock(product_item);
            product_list.add(productItemService.createProductItem(product, product_item));
        }

        return product_list;
    }

    public boolean verifyIfProductHasNoStock(ProductItems product) {
        Product verified_product = product.getProduct();
        return product.getQuantity() > verified_product.getStock();
    }

    public Double getTotalValue(List<ProductItems> products) {
        double total_price = 0.0;

        for (ProductItems product : products) {
            Product verified_product = getProductById(product.getProduct().getId());
            total_price += product.getQuantity() * verified_product.getPrice();
        }

        return total_price;
    }

    private Product updateProductStock(ProductItems item){
        Product verified_product = item.getProduct();
        verified_product.setStock(verified_product.getStock() - item.getQuantity());
        return saveProduct(verified_product);
    }
}
