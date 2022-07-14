package com.felipesantos.dsmeta.services;

import com.felipesantos.dsmeta.entities.Sale;
import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmsService {

    private final SaleService saleService;

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    public void sendSms(Long id) {
        Sale sale = saleService.findById(id);
        String monthAndYear = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();

        StringBuilder saleMessage = new StringBuilder("O vendedor ")
                .append(sale.getSellerName())
                .append(" foi destaque em ")
                .append(monthAndYear)
                .append(" com um total de R$ ")
                .append(String.format("%.2f", sale.getAmount()))
                .append(" em vendas! Ohhhhhh Miiiiiiihhhh");

        Twilio.init(twilioSid, twilioKey);

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, saleMessage.toString()).create();

        log.info("SID Message {}", message.getSid());
    }
}

