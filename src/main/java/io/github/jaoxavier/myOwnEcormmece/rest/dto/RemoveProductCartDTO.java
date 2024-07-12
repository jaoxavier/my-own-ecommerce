package io.github.jaoxavier.myOwnEcormmece.rest.dto;

import lombok.Data;

@Data
public class RemoveProductCartDTO {
    private Integer client_id;
    private Integer product_id;
    private Integer quantity;
}
