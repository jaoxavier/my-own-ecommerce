package io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.enums.Gender;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.enums.Situation;
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
    @Column(name = "CLI_CD")
    private Integer id;

    @NotNull
    @Column(name = "IS_CPNY")
    private Boolean isCompany;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CLI_ST")
    private Situation situation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "IN_DTH")
    private LocalDateTime inactive_date;

    @NotNull
    @Pattern(
            regexp = "^(\\d{3}-\\d{2}-\\d{4}|\\d{2}-\\d{7})$",
            message = "SSN or EIN is REQUIRED"
    )
    @Column(name = "NR_SSN_EIN")
    private String number_ssn_ein;

    @NotNull(message = "First Name is required")
    @Column(name = "CLI_NM")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLI_GEN")
    private Gender gender;

    @Email(message = "E-mail is invalid")
    @NotNull(message = "E-mail is required")
    @Column(name = "CLI_EML")
    private String email;

    @Pattern(regexp = "^\\(\\d{3}\\) \\d{3}-\\d{4}$")
    @Column(name = "CLI_PHN")
    private String phone_number;

    @Pattern(
            regexp = "^\\(\\d{3}\\) \\d{3}-\\d{4}$",
            message = "Cell Phone Number should be in the format (XXX) XXX-XXXX"
    )
    @NotNull(message = "Cell Number is required")
    @Column(name = "CLI_CEL")
    private String cell_number;

    @Temporal(TemporalType.DATE)
    @Past(message = "Birthdate should be in the past")
    @NotNull(message = "Birthdate is required")
    @Column(name = "CLI_BTH_DT")
    private LocalDate birthdate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CLI_RGT_AT")
    private LocalDateTime register_at;
}
