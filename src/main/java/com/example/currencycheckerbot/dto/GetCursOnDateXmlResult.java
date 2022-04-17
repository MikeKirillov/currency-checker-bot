package com.example.currencycheckerbot.dto;

/*
includes list of objects ExchangeRatesOnDate for different currencies
includes in GetCursOnDateXmlResponse as object
name of class from CBR api
*/

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)// marks how to get/set parameter value
@XmlRootElement(name = "GetCursOnDateXmlResult")// root element
@Data// getters and setters
public class GetCursOnDateXmlResult {
    @XmlElementWrapper(name = "ValuteData", namespace = "")
    @XmlElement(name = "ValuteCursOnDate", namespace = "")
    private List<ValuteCursOnDate> valuteData = new ArrayList<>();
}
