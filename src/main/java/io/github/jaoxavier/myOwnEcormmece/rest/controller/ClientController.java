package io.github.jaoxavier.myOwnEcormmece.rest.controller;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.enums.Situation;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.exception.client.EmailAlreadyCreatedException;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.EditedClientDTO;
import io.github.jaoxavier.myOwnEcormmece.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.CreateClientDTO;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id){
        return clientService.getClient(id);
    }

    @PatchMapping("/edit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Client editClient(@RequestBody EditedClientDTO dto){
        Client client = clientService.getClient(dto.getClient_id());
        client.setName(dto.getName() == null ? client.getName() : dto.getName());
        client.setGender(dto.getGender() == null ? client.getGender() : dto.getGender());
        client.setEmail(dto.getEmail() == null ? client.getEmail() : dto.getEmail());
        client.setPhone_number(dto.getPhone_number() == null ? client.getPhone_number() : dto.getPhone_number());
        client.setCell_number(dto.getCell_number() == null ? client.getCell_number() : dto.getCell_number());
        return clientService.saveClient(client);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id){
        Client client = clientService.getClient(id);
        clientService.deleteClient(client);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody CreateClientDTO dto){
        if(clientService.isEmailAlreadyCreated(dto.getEmail())){
            throw new EmailAlreadyCreatedException("Email is already registered");
        }

        Client client = Client
                .builder()
                .name(dto.getFirst_name() + " " + dto.getLast_name())
                .isCompany(dto.getIsCompany() != null ? dto.getIsCompany() : false)
                .situation(Situation.ACTIVE)
                .number_ssn_ein(clientService.verifySsnEin(dto))
                .gender(dto.getGender())
                .email(dto.getEmail())
                .phone_number(dto.getPhone())
                .cell_number(dto.getCell())
                .birthdate(dto.getBirthdate())
                .register_at(LocalDateTime.now())
                .build();

        return clientService.saveClient(client);
    }
}
