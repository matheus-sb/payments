package br.com.wirecard.payments.mocks;

import br.com.wirecard.payments.models.BoletoPayment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class BoletoGeneratorMock {
    private List<String> boletos = Arrays.asList(
            "23790504004199062421941008109203779330000019900",
            "23790504004199062421941008109203180130000045000",
            "23790504004199062421941008109203780430000080000"
    );

    public String generateBoleto(BoletoPayment boleto) {
        Random r = new Random();
        return boletos.get(r.nextInt(boletos.size()));
    }
}
