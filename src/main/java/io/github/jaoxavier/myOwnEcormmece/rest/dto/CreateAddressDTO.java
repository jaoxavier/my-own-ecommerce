package io.github.jaoxavier.myOwnEcormmece.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAddressDTO {

    private Integer client_id;
    private String recipient;
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;

    @NotNull
    @Size(min = 5, max = 10)
    @Pattern(
            regexp = "^[0-9]{5}(?:-[0-9]{4})?$",
            message = "Invalid ZIP Code"
    )
    private String zipcode;
    private String country;
}
