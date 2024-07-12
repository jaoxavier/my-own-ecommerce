package io.github.jaoxavier.myOwnEcormmece.rest.controller;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.enums.Status;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Cart;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Order;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.ProductItems;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.CreateOrderDTO;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.EditOrderDTO;
import io.github.jaoxavier.myOwnEcormmece.service.cart.CartService;
import io.github.jaoxavier.myOwnEcormmece.service.client.ClientService;
import io.github.jaoxavier.myOwnEcormmece.service.order.OrderService;
import io.github.jaoxavier.myOwnEcormmece.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @GetMapping("/client/{client_id}")
    public List<Order> getOrderByClient(@PathVariable Integer client_id){
        return orderService.getOrderByClient(client_id);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id){
        return orderService.getOrderById(id);
    }

    @PatchMapping("/edit")
    public Order editOrder(@RequestBody EditOrderDTO dto){
        Order order = orderService.getOrderById(dto.getOrder_id());
        order.setStatus(dto.getStatus() == null ? order.getStatus() : dto.getStatus());
        order.setTotal_value(dto.getTotal_value() == null ? order.getTotal_value() : dto.getTotal_value());
        return orderService.saveOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody CreateOrderDTO dto){
        Client client = clientService.getClient(dto.getClient_id());
        Cart cart = client.getCart();

        List<ProductItems> products = productService.getProducts(cart.getProducts());
        double total_price = productService.getTotalValue(products);

        Order order = Order
                .builder()
                .client(client)
                .order_date(LocalDateTime.now())
                .products(products)
                .total_value(total_price)
                .status(Status.PENDING_PAYMENT)
                .build();

        cartService.cleanCart(client.getId());

        return orderService.saveOrderAndPopulateItems(order);
    }
}
