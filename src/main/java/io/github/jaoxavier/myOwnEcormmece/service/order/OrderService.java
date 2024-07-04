package io.github.jaoxavier.myOwnEcormmece.service.order;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Order;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.integration.OrderList;
import io.github.jaoxavier.myOwnEcormmece.exception.order.OrderCantBeSavedExcepion;
import io.github.jaoxavier.myOwnEcormmece.repository.OrderListRepository;
import io.github.jaoxavier.myOwnEcormmece.repository.OrderRepository;
import io.github.jaoxavier.myOwnEcormmece.service.client.ClientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderListService orderListService;
    @Autowired
    private ClientService clientService;

    public List<Order> getOrderByClient(Integer client_id){
        Client client = clientService.getClient(client_id);
        return orderRepository.findByClient(client.getId());
    }

    @Transactional
    public Order saveOrder(Order order){
        Order saved_order = orderRepository.save(order);

        if (saved_order.getId() == null){
            throw new OrderCantBeSavedExcepion("Order can't be saved");
        }

        OrderList orderList = orderListService.saveOrderList(saved_order);

        if (orderList.getId() == null){
            throw new OrderCantBeSavedExcepion("OrderList can't be saved");
        }

        return saved_order;
    }

}
