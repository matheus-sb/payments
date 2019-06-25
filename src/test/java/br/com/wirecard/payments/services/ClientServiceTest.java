package br.com.wirecard.payments.services;

import br.com.wirecard.payments.exceptions.ClientNotFoundException;
import br.com.wirecard.payments.models.Client;
import br.com.wirecard.payments.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void checkIfClientExistsById() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(new Client()));

        Client client = clientService.checkIfClientExistsById(1L);

        assertNotNull(client);
    }

    @Test
    public void checkIfClientExistsByIdClientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> {
            clientService.checkIfClientExistsById(1L);
        });
    }
}