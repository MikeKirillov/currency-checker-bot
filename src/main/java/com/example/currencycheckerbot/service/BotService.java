package com.example.currencycheckerbot.service;

import com.example.currencycheckerbot.dto.ValuteCursOnDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;

@Service// BotService is service class
@Slf4j// add logging from Lombok
@RequiredArgsConstructor
public class BotService extends TelegramLongPollingBot {
    private final CentralRussianBankService centralRussianBankService;
    @Value("${bot.api.key}")
    // Сюда будет вставлено значение из application.properties, в котором будет указан api key, полученный от BotFather
    private String apiKey;
    @Value("${bot.name}")
    // Как будут звать нашего бота
    private String name;

    /*
        Это основной метод, который связан с обработкой сообщений
    */
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        try {
            // Данный класс представляет собой реализацию команды отправки сообщения, которую за нас выполнит ранее подключенная библиотека
            SendMessage response = new SendMessage();
            // ID чата, в который необходимо отправить ответ
            Long chatId = message.getChatId();
            // Устанавливаем ID, полученный из предыдущего этапа сюда, чтобы сообщить, в какой чат необходимо отправить сообщение
            response.setChatId(String.valueOf(chatId));

            // Тут начинается самое интересное - мы сравниваем, что прислал пользователь, и какие команды мы можем обработать. Пока что у нас только одна команда
            if ("/currentrates".equalsIgnoreCase(message.getText())) {
                // Получаем все курсы валют на текущий момент и проходимся по ним в цикле
                for (ValuteCursOnDate valuteCursOnDate : centralRussianBankService.getCurrenciesFromCbr()) {
                    // В данной строчке мы собираем наше текстовое сообщение
                    response.setText(
                            StringUtils.defaultIfBlank(response.getText(), "")
                                    + valuteCursOnDate.getName() + " - "
                                    + valuteCursOnDate.getCourse() + "\n"
                    );
                    // StringUtils.defaultBlank – это метод из библиотеки Apache Commons, который нам нужен для того,
                    // чтобы на первой итерации нашего цикла была вставлена пустая строка вместо null, а на следующих
                    // итерациях не перетерся текст, полученный из предыдущих итерации. Подключение библиотеки см. ниже
                }
            }
            // Теперь мы сообщаем, что пора ответ отправлять
            execute(response);
            // Ниже очень примитивная обработка исключений, чуть позже мы это поправим
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        Данный метод будет вызван сразу после того, как данный бин будет создан - это обеспечено аннотацией Spring PostConstruct
    */
    @PostConstruct
    public void start() {
        log.info("username: {}, token: {}", name, apiKey);
    }

    /*
        Данный метод просто возвращает данные об имени бота и его необходимо переопределять
    */
    @Override
    public String getBotUsername() {
        return name;
    }

    /*
        Данный метод возвращает API ключ для взаимодействия с Telegram
    */
    @Override
    public String getBotToken() {
        return apiKey;
    }
}
