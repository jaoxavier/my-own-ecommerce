package io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Boolean isCompany;

    @NotNull
    @Pattern(
            regexp = "^(\\d{3}-\\d{2}-\\d{4}|\\d{2}-\\d{7})$",
            message = "SSN or EIN is REQUIRED"
    )
    private String number_ssn_ein;

    @NotNull(message = "First Name is required")
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Email(message = "E-mail is invalid")
    @NotNull(message = "E-mail is required")
    private String email;

    @Pattern(regexp = "^\\(\\d{3}\\) \\d{3}-\\d{4}$")
    private String phone_number;

    @Pattern(
            regexp = "^\\(\\d{3}\\) \\d{3}-\\d{4}$",
            message = "Cell Phone Number should be in the format (XXX) XXX-XXXX"
    )
    @NotNull(message = "Cell Number is required")
    private String cell_number;

    @Temporal(TemporalType.DATE)
    @Past(message = "Birthdate should be in the past")
    @NotNull(message = "Birthdate is required")
    private LocalDate birthdate;
    private LocalDateTime register_at;

}
