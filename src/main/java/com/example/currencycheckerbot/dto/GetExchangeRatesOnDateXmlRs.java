package com.example.currencycheckerbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
the only one response data class
GetCursOnDateXmlResponse
*/

@XmlRootElement(name = "GetExchangeRatesOnDateXmlRs", namespace = "http://web.cbr.ru/")// root element
@XmlAccessorType(XmlAccessType.FIELD)// marks how to get/set parameter value
@Data// getters and setters
public class GetExchangeRatesOnDateXmlRs {
    @XmlElement(name = "GetExchangeRatesOnDateXmlResult", namespace = "http://web.cbr.ru/")// xml-element name
    private GetExchangeRatesOnDateXmlResult getExchangeRatesOnDateXmlResult;
}
