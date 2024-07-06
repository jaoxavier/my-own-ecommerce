package io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;
    private Integer product_id;
    private double price;
    private Integer quantity;
}