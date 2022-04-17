package com.example.currencycheckerbot.controllers;

import com.example.currencycheckerbot.dto.ValuteCursOnDate;
import com.example.currencycheckerbot.service.CentralRussianBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController {
    private final CentralRussianBankService centralRussianBankService;

    @PostMapping("/getCurrencies")
    public List<ValuteCursOnDate> getExchangeRatesOnDate() throws DatatypeConfigurationException {
        return centralRussianBankService.getCurrenciesFromCbr();
    }
}
