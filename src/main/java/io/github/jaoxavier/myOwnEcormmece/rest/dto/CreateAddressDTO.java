package io.github.jaoxavier.myOwnEcormmece.rest.dto;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
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
    private String zipcode;
    private String country;
}
