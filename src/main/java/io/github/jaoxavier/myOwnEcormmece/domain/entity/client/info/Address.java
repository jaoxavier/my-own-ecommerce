package io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info;

import jakarta.persistence.*;
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
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
