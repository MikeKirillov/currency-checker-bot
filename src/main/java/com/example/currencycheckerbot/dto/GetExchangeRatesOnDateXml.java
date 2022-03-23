package com.example.currencycheckerbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlRootElement(name = "GetExchangeRatesOnDateXml", namespace = "http://web.cbr.ru/")
@Data// getters and setters
public class GetExchangeRatesOnDateXml {

    @XmlElement(name = "On_date", required = true, namespace = "http://web.cbr.ru/")// marks XML-tag for this parameter
    protected XMLGregorianCalendar onDate;
}
