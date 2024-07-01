package io.github.jaoxavier.myOwnEcormmece.service.address;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Address;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.exception.address.AddressDoesntExistsException;
import io.github.jaoxavier.myOwnEcormmece.repository.AddressRepository;
import io.github.jaoxavier.myOwnEcormmece.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientService clientService;

    public Address getAddressById(Integer id){
        Optional<Address> opt_address = addressRepository.findById(id);

        if (opt_address.isEmpty()){
            throw new AddressDoesntExistsException("Address doesn't exists");
        }

        return opt_address.get();
    }

    public List<Address> getAddressByClient(Integer clientId) {
        Client client = clientService.getClient(clientId);
        return addressRepository.findByClient(client);
    }
}
