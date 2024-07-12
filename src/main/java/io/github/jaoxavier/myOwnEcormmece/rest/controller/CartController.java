package io.github.jaoxavier.myOwnEcormmece.rest.controller;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Cart;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.ProductItems;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.CartItemDTO;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.RemoveProductCartDTO;
import io.github.jaoxavier.myOwnEcormmece.service.cart.CartService;
import io.github.jaoxavier.myOwnEcormmece.service.product.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Objects;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductItemService productItemService;

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Integer id){
        return cartService.getCartById(id);
    }

    @GetMapping("/client/{client_id}")
    public Cart getCartByClientId(@PathVariable Integer client_id){
        return cartService.getCartById(client_id);
    }

    @PatchMapping("/remove")
    public Cart removeItemFromCart(@RequestBody RemoveProductCartDTO dto){
        Cart cart = cartService.getCartById(dto.getClient_id());

        Iterator<ProductItems> iterator = cart.getProducts().iterator();

        while (iterator.hasNext()) {
            ProductItems product = iterator.next();

            if (Objects.equals(product.getProduct().getId(), dto.getProduct_id())) {
                int new_quantity = product.getQuantity() - dto.getQuantity();

                if (new_quantity <= 0) {
                    productItemService.deleteProductById(product.getId());
                    iterator.remove();
                    cart.setTotal_price(cartService.getTotalValue(cart.getProducts()));
                    return cartService.saveCart(cart);
                }

                product.setQuantity(new_quantity);
                cart.setTotal_price(cartService.getTotalValue(cart.getProducts()));
            }
        }
        return cartService.saveCart(cart);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cart addOnCart(@RequestBody CartItemDTO dto){
        Cart cart = cartService.getCartById(dto.getClient_id());
        return cartService.addItemOnCart(cart, dto);
    }
}