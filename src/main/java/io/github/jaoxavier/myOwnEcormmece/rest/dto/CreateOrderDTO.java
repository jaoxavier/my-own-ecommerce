package io.github.jaoxavier.myOwnEcormmece.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {
    private Integer client_id;
    private Double total_value;
}
