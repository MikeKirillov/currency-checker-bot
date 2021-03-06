package com.example.currencycheckerbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

/*
the only one request data class. other dto classes are for response
name of class from CBR api
*/

@XmlRootElement(name = "GetCursOnDateXML", namespace = "http://web.cbr.ru/")
@Data// getters and setters
public class GetCursOnDateXML {

    @XmlElement(name = "On_date", required = true, namespace = "http://web.cbr.ru/")// marks XML-tag for this parameter
    protected XMLGregorianCalendar onDate;
}
