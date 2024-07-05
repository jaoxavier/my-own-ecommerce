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
    @Column(name = "ADS_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CLI_ID")
    private Client client;

    private String recipient;

    @NotNull
    @Column(name = "ADS_STREET")
    private String street;

    @NotNull
    @Column(name = "ADS_NR")
    private String number;

    @NotNull
    @Column(name = "ADS_COMPLEMENT")
    private String complement;

    @NotNull
    @Column(name = "ADS_CITY")
    private String city;

    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "ADS_STATE")
    private String state;

    @NotNull
    @Size(min = 5, max = 10)
    @Pattern(
            regexp = "^[0-9]{5}(?:-[0-9]{4})?$",
            message = "Invalid ZIP Code"
    )
    @Column(name = "ADS_ZIP")
    private String zipcode;

    @NotNull
    @Column(name = "ADS_COUNTRY")
    private String country;
}
