package io.github.jaoxavier.myOwnEcormmece.rest.controller;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Address;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.exception.client.ClientDoesntExistsException;
import io.github.jaoxavier.myOwnEcormmece.repository.AddressRepository;
import io.github.jaoxavier.myOwnEcormmece.repository.ClientRepository;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.CreateAddressDTO;
import io.github.jaoxavier.myOwnEcormmece.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody CreateAddressDTO dto){
        Optional<Client> opt_client = clientService.getClient(dto.getClient_id());

        if(opt_client.isEmpty()){
            throw new ClientDoesntExistsException("The Client doesn't exists");
        }

        Client client = opt_client.get();

        Address address = Address
                .builder()
                .client(client)
                .recipient(
                        dto.getRecipient() == null ? client.getFirst_name() + " " + client.getLast_name() : dto.getRecipient()
                )
                .street(dto.getStreet())
                .number(dto.getNumber())
                .complement(dto.getComplement())
                .city(dto.getCity())
                .state(dto.getState())
                .zipcode(dto.getZipcode())
                .country(dto.getCountry())
                .build();

        return addressRepository.save(address);
    }
}
