package com.example.currencycheckerbot.service;

import com.example.currencycheckerbot.dto.ExchangeRatesOnDate;
import com.example.currencycheckerbot.dto.GetExchangeRatesOnDateXmlRq;
import com.example.currencycheckerbot.dto.GetExchangeRatesOnDateXmlRs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/*
Данный класс наследуется от WebServiceTemplate, который предоставляет удобный способ взаимодействия с
SOAP веб-сервисами
*/

public class CentralRussianBankService extends WebServiceTemplate {
/*
    Тут случается некоторая магия Spring и в момент запуска вашего приложения,
    сюда поставляется значение из application.properties или application.yml
*/
    @Value("${cbr.api.url}")
    private String cbrApiUrl;
/*
    Создаем метод получения данных
*/
    public List<ExchangeRatesOnDate> getCurrenciesFromCbr() throws DatatypeConfigurationException {
        final GetExchangeRatesOnDateXmlRq getCursOnDateXML = new GetExchangeRatesOnDateXmlRq();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        getCursOnDateXML.setOnDate(xmlGregCal);

        GetExchangeRatesOnDateXmlRs response = (GetExchangeRatesOnDateXmlRs) marshalSendAndReceive(cbrApiUrl, getCursOnDateXML);
        if (response == null) {
            throw new IllegalArgumentException("Could not get response from CBR Service");
        }

        final List<ExchangeRatesOnDate> courses = response.getGetExchangeRatesOnDateXmlResult().getRatesOnDates();
        courses.forEach(course -> course.setName(course.getName().trim()));
        return courses;
    }
}