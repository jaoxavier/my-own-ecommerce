package io.github.jaoxavier.myOwnEcormmece.service.order;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Order;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.integration.OrderList;
import io.github.jaoxavier.myOwnEcormmece.repository.OrderListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderListService {

    @Autowired
    private OrderListRepository orderListRepository;

    public OrderList saveOrderList(Order order){
        OrderList orderList = OrderList
                .builder()
                .order_id(order.getId())
                .client_id(order.getClient_id())
                .build();
        return orderListRepository.save(orderList);
    }
}
