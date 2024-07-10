package io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.enums.Gender;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.enums.Situation;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Cart;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.order.info.Order;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLI_ID")
    private Integer id;

    @NotNull
    @Column(name = "COMPANY")
    private Boolean isCompany;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CLI_ST")
    private Situation situation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INACTIVE_DTH")
    private LocalDateTime inactive_date;

    @NotNull
    @Pattern(
            regexp = "^(\\d{3}-\\d{2}-\\d{4}|\\d{2}-\\d{7})$",
            message = "SSN or EIN is REQUIRED"
    )
    @Column(name = "NR_SSN_EIN")
    private String number_ssn_ein;

    @NotNull(message = "First Name is required")
    @Column(name = "CLI_NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLI_GENDER")
    private Gender gender;

    @Email(message = "E-mail is invalid")
    @NotNull(message = "E-mail is required")
    @Column(name = "CLI_EMAIL")
    private String email;

    @Pattern(regexp = "^\\(\\d{3}\\) \\d{3}-\\d{4}$")
    @Column(name = "CLI_PHONE")
    private String phone_number;

    @Pattern(
            regexp = "^\\(\\d{3}\\) \\d{3}-\\d{4}$",
            message = "Cell Phone Number should be in the format (XXX) XXX-XXXX"
    )
    @NotNull(message = "Cell Number is required")
    @Column(name = "CLI_CELL")
    private String cell_number;

    @Temporal(TemporalType.DATE)
    @Past(message = "Birthdate should be in the past")
    @NotNull(message = "Birthdate is required")
    @Column(name = "CLI_BIRTH_DT")
    private LocalDate birthdate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CLI_REGISTER_AT")
    private LocalDateTime register_at;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;
}
