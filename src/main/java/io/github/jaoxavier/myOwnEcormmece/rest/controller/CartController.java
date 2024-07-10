package io.github.jaoxavier.myOwnEcormmece.rest.controller;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Cart;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.ProductItems;
import io.github.jaoxavier.myOwnEcormmece.repository.CartRepository;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.CartItemDTO;
import io.github.jaoxavier.myOwnEcormmece.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Integer id){
        return cartService.getCartById(id);
    }

    @GetMapping("/client/{client_id}")
    public Cart getCartByClientId(@PathVariable Integer client_id){
        return cartService.getCartByClient(client_id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cart addOnCart(@RequestBody CartItemDTO dto){
        Cart cart = cartService.getCartById(dto.getClient_id());
        return cartService.addItemOnCart(cart, dto);
    }
}