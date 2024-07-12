package io.github.jaoxavier.myOwnEcormmece.rest.dto;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {
    private Integer client_id;
}
