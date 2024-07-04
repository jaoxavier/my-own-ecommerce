package io.github.jaoxavier.myOwnEcormmece.domain.entity.order.integration;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "CLI_ID")
    private Integer client_id;
    @NotNull
    @Column(name = "ORD_ID")
    private Integer order_id;
    @NotNull
    @Column(name = "ORD_DTH")
    private LocalDateTime order_date;
}
