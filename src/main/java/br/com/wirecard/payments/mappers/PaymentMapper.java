package br.com.wirecard.payments.mappers;

import br.com.wirecard.payments.dtos.*;
import br.com.wirecard.payments.models.*;

public class PaymentMapper implements Mapper<PaymentDto, Payment> {

    @Override
    public Payment convertFromDtoToObject(PaymentDto dto) {
        if (dto.isBoletoPayment()) {
            BoletoPaymentDto boletoDto = (BoletoPaymentDto) dto;
            return convertFromBoletoDtoToBoleto(boletoDto);
        }

        CreditCardPaymentDto creditCardDto = (CreditCardPaymentDto) dto;
        return convertFromCreditCardDtoToCreditCard(creditCardDto);
    }

    private Payment convertFromCreditCardDtoToCreditCard(CreditCardPaymentDto creditCardDto) {
        Client client = getClient(creditCardDto.getClient());
        Buyer buyer = getBuyer(creditCardDto.getBuyer());


        return CreditCardPayment.builder()
                .client(client)
                .buyer(buyer)
                .cardHolderName(creditCardDto.getCardHolderName())
                .cardNumber(creditCardDto.getCardNumber())
                .cardExpirationDate(creditCardDto.getCardExpirationDate())
                .cardCCV(creditCardDto.getCardCCV())
                .amount(creditCardDto.getAmount())
                .build();
    }

    private Payment convertFromBoletoDtoToBoleto(BoletoPaymentDto boletoDto) {
        Client client = getClient(boletoDto.getClient());
        Buyer buyer = getBuyer(boletoDto.getBuyer());

        return BoletoPayment.builder()
                .client(client)
                .buyer(buyer)
                .amount(boletoDto.getAmount())
                .build();
    }

    private Buyer getBuyer(BuyerDto buyerDto) {
        return Buyer.builder()
                .name(buyerDto.getName())
                .email(buyerDto.getEmail())
                .cpf(buyerDto.getCpf())
                .build();
    }

    private Client getClient(ClientDto clientDto) {
        return Client.builder()
                    .id(clientDto.getId())
                    .build();
    }

    @Override
    public PaymentDto convertFromObjectToDto(Payment payment) {
        if (payment.isBoletoPayment()) {
            BoletoPayment boleto = (BoletoPayment) payment;
            return convertFromBoletoToBoletoDto(boleto);
        }

        CreditCardPayment creditCard = (CreditCardPayment) payment;
        return convertFromCreditCardToCreditCardDto(creditCard);
    }

    private PaymentDto convertFromCreditCardToCreditCardDto(CreditCardPayment creditCard) {
        ClientDto clientDto = getClientDto(creditCard.getClient());
        BuyerDto buyerDto = getBuyerDto(creditCard.getBuyer());


        return CreditCardPaymentDto.builder()
                .id(creditCard.getId())
                .client(clientDto)
                .buyer(buyerDto)
                .cardHolderName(creditCard.getCardHolderName())
                .cardNumber(creditCard.getCardNumber())
                .cardExpirationDate(creditCard.getCardExpirationDate())
                .amount(creditCard.getAmount())
                .build();
    }

    private PaymentDto convertFromBoletoToBoletoDto(BoletoPayment boleto) {
        ClientDto clientDto = getClientDto(boleto.getClient());
        BuyerDto buyerDto = getBuyerDto(boleto.getBuyer());

        return BoletoPaymentDto.builder()
                .id(boleto.getId())
                .client(clientDto)
                .buyer(buyerDto)
                .amount(boleto.getAmount())
                .number(boleto.getNumber())
                .build();
    }

    private BuyerDto getBuyerDto(Buyer buyer) {
        return BuyerDto.builder()
                .id(buyer.getId())
                .name(buyer.getName())
                .email(buyer.getEmail())
                .cpf(buyer.getCpf())
                .build();
    }

    private ClientDto getClientDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .build();
    }
}
