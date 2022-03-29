package com.example.currencycheckerbot.dto;

/*
includes list of objects ExchangeRatesOnDate for different currencies
GetCursOnDateXmlResult
*/

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)// marks how to get/set parameter value
@XmlRootElement(name = "GetExchangeRatesOnDateXmlResult")// root element
@Data// getters and setters
public class GetExchangeRatesOnDateXmlResult {
    @XmlElementWrapper(name = "RatesOnDates", namespace = "")
    @XmlElement(name = "ExchangeRatesOnDate", namespace = "")
    private List<ExchangeRatesOnDate> ratesOnDates = new ArrayList<>();
}
