package io.github.jaoxavier.myOwnEcormmece.rest.dto;

import lombok.Data;

@Data
public class CartItemDTO {

    private Integer client_id;
    private Integer product_id;
    private Integer quantity;
}
