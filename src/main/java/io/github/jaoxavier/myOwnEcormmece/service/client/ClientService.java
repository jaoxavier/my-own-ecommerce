package io.github.jaoxavier.myOwnEcormmece.service.client;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.enums.Situation;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.exception.client.ClientDoesntExistsException;
import io.github.jaoxavier.myOwnEcormmece.exception.client.EmailAlreadyCreatedException;
import io.github.jaoxavier.myOwnEcormmece.exception.client.SSNorEINinvalidException;
import io.github.jaoxavier.myOwnEcormmece.repository.ClientRepository;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.CreateClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private static final Pattern SSN_PATTERN = Pattern.compile("^(?!000|666|9\\d\\d)\\d{3}-(?!00)\\d{2}-(?!0000)\\d{4}$");
    private static final Pattern EIN_PATTERN = Pattern.compile("^\\d{2}-\\d{7}$");
    private static final String[] VALID_EIN_PREFIXES = {"01", "02", "03", "04", "05", "06", "10", "11", "12", "13", "14", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "30", "31", "32", "33", "34", "35", "36", "37", "38", "40", "41", "42", "43", "44", "45", "46", "47", "48", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "71", "72", "73", "74", "75", "76", "77", "80", "81", "82", "83", "84", "85", "86", "87", "88", "90", "91", "92", "94", "95", "96", "97", "98", "99"};

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

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

    public String verifySsnEin(CreateClientDTO dto) {
        Boolean isCompany = dto.getIsCompany() != null ? dto.getIsCompany() : false;

        if (dto.getNumber_ssn_ein() == null){
            throw new SSNorEINinvalidException("SSN or EIN is missing");
        }


        if (SSN_PATTERN.matcher(dto.getNumber_ssn_ein()).matches() && !isCompany){
            return dto.getNumber_ssn_ein();
        }

        if (isValidEIN(dto.getNumber_ssn_ein()) && isCompany){
            return dto.getNumber_ssn_ein();
        }

        throw new SSNorEINinvalidException("SSN or EIN is invalid");
    }

    private static boolean isValidEIN(String ein) {
        if (ein == null) {
            return false;
        }
        if (!EIN_PATTERN.matcher(ein).matches()) {
            return false;
        }
        String prefix = ein.substring(0, 2);
        for (String validPrefix : VALID_EIN_PREFIXES) {
            if (validPrefix.equals(prefix)) {
                return true;
            }
        }
        return false;
    }
}
