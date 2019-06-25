package br.com.wirecard.payments.services;

import br.com.wirecard.payments.exceptions.ClientNotFoundException;
import br.com.wirecard.payments.models.Client;
import br.com.wirecard.payments.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public Client checkIfClientExistsById(Long id) {
        return repository.findById(id).orElseThrow(ClientNotFoundException::new);
    }
}
