package io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.enums.Status;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.OrderItems;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "ORD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> products;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORD_DTH")
    private LocalDateTime order_date;

    @NotNull
    @Column(name = "ORD_TOTAL_VAL")
    private Double total_value;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORD_STA")
    @NotNull
    private Status status;
}
