package io.github.jaoxavier.myOwnEcormmece.service.client;

import io.github.jaoxavier.myOwnEcormmece.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Boolean isEmailAlreadyCreated(String email){
        return clientRepository.findByEmail(email).isPresent();
    }
}
