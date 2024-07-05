package io.github.jaoxavier.myOwnEcormmece.rest.dto;

import lombok.Data;

@Data
public class EditAddressDTO {
    private Integer id;
    private String recipient;
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
