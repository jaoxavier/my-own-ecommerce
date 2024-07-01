package io.github.jaoxavier.myOwnEcormmece.service.client;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.exception.client.ClientDoesntExistsException;
import io.github.jaoxavier.myOwnEcormmece.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Boolean isEmailAlreadyCreated(String email){
        return clientRepository.findByEmail(email).isPresent();
    }

    public Client getClient(Integer id){
        Optional<Client> opt_client = clientRepository.findById(id);

        if (opt_client.isEmpty()){
            throw new ClientDoesntExistsException("The Client doesn't exists");
        }

        return opt_client.get();
    }
}
