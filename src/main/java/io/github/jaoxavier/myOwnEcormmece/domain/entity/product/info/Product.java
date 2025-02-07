package io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JsonBackReference
    private Client client;

    @NotNull
    private String name;

    @NotNull
    private String brand;

    @NotNull
    private Double price;

    @NotNull
    @Min(value = 0)
    private Integer stock;
}
