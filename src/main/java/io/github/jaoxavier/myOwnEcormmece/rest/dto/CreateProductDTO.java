package io.github.jaoxavier.myOwnEcormmece.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProductDTO {
    private Integer client_id;
    private String name;
    private String brand;
    private Double price;
    private Integer stock;
}
