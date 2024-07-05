package io.github.jaoxavier.myOwnEcormmece.rest.dto;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.enums.Status;
import lombok.Data;

@Data
public class EditOrderDTO {
    private Integer order_id;
    private Double total_value;
    private Status status;
}
