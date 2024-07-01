package io.github.jaoxavier.myOwnEcormmece.rest.controller;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.exception.client.EmailAlreadyCreatedException;
import io.github.jaoxavier.myOwnEcormmece.repository.ClientRepository;
import io.github.jaoxavier.myOwnEcormmece.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.CreateClientDTO;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id){
        return clientService.getClient(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody CreateClientDTO dto){

        if(clientService.isEmailAlreadyCreated(dto.getEmail())){
            throw new EmailAlreadyCreatedException("Email is already registered");
        }

        Client client = Client
                .builder()
                .first_name(dto.getFirst_name())
                .last_name(dto.getLast_name())
                .gender(dto.getGender())
                .email(dto.getEmail())
                .phone_number(dto.getPhone())
                .cell_number(dto.getCell())
                .birthdate(dto.getBirthdate())
                .register_at(LocalDate.now())
                .build();
        return clientRepository.save(client);
    }
}
