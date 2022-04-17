package com.example.currencycheckerbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
class that includes just exchange parameter list
includes in GetCursOnDateXmlResult as List of objects
*/

@XmlAccessorType(XmlAccessType.FIELD)// marks how to get/set parameter value
@XmlRootElement(name = "ValuteCursOnDate")// root element
@Data// getters and setters
public class ValuteCursOnDate {
    @XmlElement(name = "Vname")// xml-tag name
    private String name;

    @XmlElement(name = "Vnom")
    private int nominal;

    @XmlElement(name = "Vcurs")
    private double course;// exchange course

    @XmlElement(name = "Vcode")
    private String code;

    @XmlElement(name = "VchCode")
    private String chCode;
}
