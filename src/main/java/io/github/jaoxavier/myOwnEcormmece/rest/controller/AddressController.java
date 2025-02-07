package io.github.jaoxavier.myOwnEcormmece.rest.controller;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Address;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.repository.AddressRepository;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.CreateAddressDTO;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.EditAddressDTO;
import io.github.jaoxavier.myOwnEcormmece.service.address.AddressService;
import io.github.jaoxavier.myOwnEcormmece.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Integer id){
        return addressService.getAddressById(id);
    }

    @GetMapping("/client/{client_id}")
    public List<Address> getAddressByClient(@PathVariable Integer client_id){
        return addressService.getAddressByClientId(client_id);
    }

    @PatchMapping("/edit")
    public Address editAddress(@RequestBody EditAddressDTO dto){
        Address address = addressService.getAddressById(dto.getId());

        address.setRecipient((dto.getRecipient() == null ? address.getRecipient() : dto.getRecipient()));
        address.setStreet((dto.getStreet() == null ? address.getStreet() : dto.getStreet()));
        address.setNumber((dto.getNumber() == null ? address.getNumber() : dto.getNumber()));
        address.setComplement((dto.getComplement() == null ? address.getComplement() : dto.getComplement()));
        address.setCity((dto.getCity() == null ? address.getCity() : dto.getCity()));
        address.setState((dto.getState() == null ? address.getState() : dto.getState()));
        address.setZipcode((dto.getZipcode() == null ? address.getZipcode() : dto.getZipcode()));
        address.setCountry((dto.getCountry() == null ? address.getCountry() : dto.getCountry()));

        return addressService.saveAddress(address);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable Integer id){
        Address address = addressService.getAddressById(id);
        addressService.deleteAddress(address);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody CreateAddressDTO dto){
        Client client = clientService.getClient(dto.getClient_id());

        Address address = Address
                .builder()
                .client(client)
                .recipient(
                        dto.getRecipient() == null ? client.getName() : dto.getRecipient()
                )
                .street(dto.getStreet())
                .number(dto.getNumber())
                .complement(dto.getComplement())
                .city(dto.getCity())
                .state(dto.getState())
                .zipcode(dto.getZipcode())
                .country(dto.getCountry())
                .build();

        return addressService.saveAddress(address);
    }
}
