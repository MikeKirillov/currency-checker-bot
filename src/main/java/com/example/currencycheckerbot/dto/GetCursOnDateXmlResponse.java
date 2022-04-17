package com.example.currencycheckerbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
the only one response data class
name of class from CBR api
*/

@XmlRootElement(name = "GetCursOnDateXmlResponse", namespace = "http://web.cbr.ru/")// root element
@XmlAccessorType(XmlAccessType.FIELD)// marks how to get/set parameter value
@Data// getters and setters
public class GetCursOnDateXmlResponse {
    @XmlElement(name = "GetCursOnDateXMLResult", namespace = "http://web.cbr.ru/")// xml-element name
    private GetCursOnDateXmlResult getCursOnDateXmlResult;
}
