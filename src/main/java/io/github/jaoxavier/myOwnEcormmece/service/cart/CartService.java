package io.github.jaoxavier.myOwnEcormmece.service.cart;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Cart;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Order;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.ProductItems;
import io.github.jaoxavier.myOwnEcormmece.exception.product.ProductHasInsufficientStock;
import io.github.jaoxavier.myOwnEcormmece.repository.CartRepository;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.CartItemDTO;
import io.github.jaoxavier.myOwnEcormmece.service.client.ClientService;
import io.github.jaoxavier.myOwnEcormmece.service.product.ProductItemService;
import io.github.jaoxavier.myOwnEcormmece.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductItemService productItemService;

    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }

    public Cart getCartById(Integer client_id){
        Client client = clientService.getClient(client_id);

        if (client.getCart() == null) {
            return createNewCart(client);
        }
        return client.getCart();
    }

    private Cart createNewCart(Client client) {
        Cart cart = Cart
                .builder()
                .client(client)
                .products(new ArrayList<>())
                .build();
        return saveCart(cart);
    }

    public Cart addItemOnCart(Cart cart, CartItemDTO dto) {
        ProductItems item = getProductItems(cart, dto);

        if (productService.verifyIfProductHasNoStock(item)){
            throw new ProductHasInsufficientStock("The Stock of " + item.getProduct().getName() + " is insufficient.");
        }

        if (isProductAlreadyOnCart(cart, dto)) return saveCart(cart);

        cart.getProducts().add(item);
        cart.setTotal_price(getTotalValue(cart.getProducts()));
        return saveCart(cart);
    }

    private boolean isProductAlreadyOnCart(Cart cart, CartItemDTO dto) {
        for (ProductItems product : cart.getProducts()) {
            if (product.getProduct().getId().equals(dto.getProduct_id())){
                product.setQuantity(product.getQuantity() + dto.getQuantity());
                cart.setTotal_price(getTotalValue(cart.getProducts()));
                return true;
            }
        }
        return false;
    }

    private ProductItems getProductItems(Cart cart, CartItemDTO dto) {
        Product product = productService.getProductById(dto.getProduct_id());
        ProductItems item = ProductItems
                .builder()
                .cart(cart)
                .product(product)
                .quantity(dto.getQuantity())
                .build();
        return item;
    }


    public double getTotalValue(List<ProductItems> cart){
        double total_price = 0.0;

        for (ProductItems product : cart) {
            total_price += product.getProduct().getPrice() * product.getQuantity();
        }

        return total_price;
    }

    public void cleanCart(Integer client_id) {
        Integer cart_id = clientService.getClient(client_id).getCart().getId();
        productItemService.deleteProductsOnCart(cart_id);
    }
}
