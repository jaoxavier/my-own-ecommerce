package io.github.jaoxavier.myOwnEcormmece.rest.dto;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateClientDTO {

    private String first_name;
    private String last_name;
    private Boolean isCompany;
    private String number_ssn_ein;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    private String phone = "(XXX) XXX-XXXX";
    private String cell;
    private LocalDate birthdate;

}
