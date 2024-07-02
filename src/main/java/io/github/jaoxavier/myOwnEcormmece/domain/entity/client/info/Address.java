package io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    private String recipient;

    @NotNull
    private String street;

    @NotNull
    private String number;

    @NotNull
    private String complement;

    @NotNull
    private String city;

    @NotNull
    @Size(min = 2, max = 2)
    private String state;

    @NotNull
    @Size(min = 5, max = 10)
    @Pattern(
            regexp = "^[0-9]{5}(?:-[0-9]{4})?$",
            message = "Invalid ZIP Code"
    )
    private String zipcode;

    @NotNull
    private String country;
}
