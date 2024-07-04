package io.github.jaoxavier.myOwnEcormmece.repository;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Order;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT DISTINCT O.* FROM ORDER_LIST OL JOIN ORDERS O ON OL.CLI_ID = O.CLI_ID" +
            " WHERE O.CLI_ID = :client_id ORDER BY ORD_DTH DESC", nativeQuery = true)
    List<Order> findByClient(@PathParam("client_id") Integer client_id);
}
