# currency-checker-bot

## Идея приложения
В рамках практики мы разработаем Телеграм-бота, который будет информировать о курсах валют и будет уведомлять при достижении определенного порога. Также ознакомимся с такой технологией, как Scheduling (планирование, то есть запланированные задачи). B добавим функционал внесения затрат и начислений.

### Первичная реализация
1. Создадим наше приложение с помощью Spring Initializr.
* Packaging: JAR
* Java: 11
* Dependencies:
  * Lombok
  * Spring Boot Actuator
  * Spring Web
  * Spring Data JPA
  * Testcontainers
* etc.
2. Распакуем скачанный архив и откроем проект в IDE, запустим наше приложение
3. Наше приложение не запускается. Причиной тому является отсутствие конфигурации базы данных. Поскольку в classpath есть зависимость на стартер Spring Boot, который отвечает за автоконфигурацию соединение с БД, то наше приложение пытается автоматически создать соединение с базой данных
4. Создадим базу при помощи интерфейса программы pgAdmin или той, с которой вы уже привыкли работать, но пока никаких таблиц создавать не будем. Сделать это можно, нажав правой кнопкой мыши на локальных серверах в программе pgAdmin
5. Добавим в файл application.properties, который располагается в папке src/main/resources, а также добавим зависимость на PostgreSQL (**postgresql**) для того, чтобы Spring Boot стартер смог правильно сконфигурировать соединение с БД
6. Проверим, что приложение запускается. Spring Boot может сказать, что порт 8080 уже занят. В таком случае следует поменять его на 9090. Для этого есть специальное свойство: **server.port = <желаемый порт>**
7. На этом шаге точно должно было заработать
8. Теперь добавим конфигурацию сборки нашего приложения через Maven. Для этого сверху справа в IntellijIDEA необходимо нажать кнопку Edit configurations (Редактирование конфигурации). И создать конфигурацию, как написано ниже: **RUN: clean install**
9. Двигаемся дальше, теперь необходимо создать веб-сервис, который будет собственно запрашивать данные у API Центрального банка.

Для этого нам нужно добавить ещё одну зависимость: **spring-boot-starter-web-services**. Данная зависимость предоставляет очень удобный механизм создания сервисов, которые взаимодействуют по протоколу SOAP.

Также для создания DTO (Data Transfer Object, то есть объектов запросов и ответов, которые мы получаем или отдаем) нам нужны следующие зависимости.

Не стоит также забывать про другую аббревиатуру DAO — Data Access Object (объекты доступа к данным), данные объекты представляют сущности нашего приложения, которые хранятся в базе:
* jaxb-api
* jaxb-core
* jaxb-impl
* jaxws-api
* jaxws-rt
* activation
* jaxws-tools

Эти библиотеки необходимы для работы с XML, их нужно добавлять вручную, так как в релизе Java 11 данные библиотеки были удалены из Java

10. Теперь создадим наши DTO для работы с API ЦБ РФ. Для этого заведем отдельный пакет DTO и создадим классы запросов и ответов
* REQUEST: 
  * GetCursOnDateXML
* RESPONSE:
  * ValuteCursOnDate
  * GetCursOnDateXmlResult
  * GetCursOnDateXmlResponse
11. Создадим реализацию интерфейса, который будет отправлять запросы в сторону API Центрального банка России (CentralRussianBankService)
12. Теперь нам необходимо добавить ещё один пакет в наше приложение — config. В этом пакете мы будем хранить бины конфигурации, их задача — конфигурировать наше приложение
13. Создадим REST контроллеры, которые будут отдавать наружу данные полученные от ЦБ РФ. Для этого заведем отдельный пакет controllers, в котором будем хранить наши контроллеры
14. Прежде чем запускать наше приложение, необходимо его собрать с помощью Maven и убедиться, что в папке target есть сгененированные классы, которые находятся в папке generated-class/ru/cbr