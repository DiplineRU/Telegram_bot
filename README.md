ru text

*Telegram — один из самых удобных и многофункциональных мессенджеров на сегодняшний день. Изначально Telegram предназначался для безопасного общения, в условиях высокого уровня шифрования данных. Но со временем приложение обросло функционалом до такой степени, что в нем стало возможным создавать полноценные обучающие каналы, сервисы по поиску музыки, автоматические переводчики и оперативные службы СМИ.*

**Задание**

В данной курсовой работе вам предстоит разработать бота для Telegram, который умел бы принимать от пользователя сообщения в формате 
01.01.2022 20:00 Сделать домашнюю работу
и присылать пользователю сообщение в 20:00 1 января 2022 года с текстом “Сделать домашнюю работу”.

**Задача 1. Сборка телеграм-бота**

**1.1 Зарегистрировать своего телеграм бота**

Добавить к себе в телеграм бота https://t.me/BotFather, активировать его командой 
/start
 и следовать инструкциями. Главная цель этой задачи — получить токен вида 
6290793636:AAGIqfVDV9My8UfMQGfvG5arXo8ZAaY6Bhs
, который будет использоваться вами в дальнейшем при разработке своего бота для доступа к Bot API Telegram.

**1.2 Клонирование репозитория**

Цель задачи — получить у себя на компьютере шаблон приложения с настроенным для работы Spring Boot’ом.

Здесь вам необходимо форкнуть репозиторий и клонировать его к себе в директорию с Java проектами.

**1.3 Сконфигурировать склонированное приложение**

Цель задачи — получить работающий проект на вашем компьютере, который можно запустить в IDEA.

Необходимо создать базу данных и пользователя, которая будет использоваться вашим приложением. Используя application.properties настроить подключение к базе данных и передать токен, полученный в 1ой задаче.

**1.4 Научиться реагировать на команду /start и выводить приветственное сообщение**

Теперь, когда у вас есть запускающийся проект, самое время в классе 
TelegramBotUpdatesListener
 в методе 
public int process(List<Update> updates)
 научиться обрабатывать команду 
/start
, отправляемую пользователем нашему боту в Telegram. Для этого необходимо проитерироваться по всем апдейтам, которые наш метод принимает в качестве аргумента и, в случае, если мы получили сообщение 
/start
 — вывести приветственное сообщение. Текст сообщения — на ваш вкус.

**Задача 2**

**2.1 Создать использую liquibase таблицу notification_task**

Цель задачи — получить после старта приложения таблицу нужной нам структуры в БД.

Теперь настало самое время понять, как мы будем хранить наши таски в базе данных и спроектировать для этого схему базы данных. Предположу, что нам потребуется иметь первичный ключ в таблице, идентификатор чата, в который нужно отправить уведомление, текст уведомления и дату+время, когда требуется отправить уведомление. Возможно, вы захотите хранить какие-то дополнительные данные. Примерно такой структуры нужно создать таблицу использую liquibase.

**2.2 Создать сущность notification task**

Цель задачи — получить класс с аннотацией 
@Entity
, который будет повторять структуру нашей таблицы в БД и будет пригоден для использования в коде нашего приложения.

После того, как мы создали таблицу, необходимо создать сущность, с которой мы будем работать в коде нашего приложения.

**2.3 Создать репозиторий для сущности notification task**

Цель задачи — получить интерфейс, с помощью которого можно сохранять и доставать из БД наши сущности.

Следующий шаг — создать расширение 
JpaRepository<T, ID>
, который был бы пригоден для работу с сущностью, созданной в предыдущем шаге.

**2.4 Научиться парсить сообщения с создаваемым напоминанием и сохранять их в БД**

Цель задачи — получить записи в базе данных, соответствующие отправленным сообщениям.

Наконец-то мы добрались до самого важно задания — мы должны научить обрабатывать получаемый от пользователя сообщения вида 
01.01.2022 20:00 Сделать домашнюю работу
 , вычленять из него дату+время (01.01.2022 20:00) и текст напоминания (Сделать домашнюю работу), создавать на основе этих данных сущность и сохранять её в БД.

Для распознавания в строке подстроки с датой и подстроки с сообщением нам потребуются регулярные выражения.

После того, как мы отделили дату и текст напоминания во входящем сообщении, нам необходимо сконструировать сущность.

Имея сконструированную сущность необходимо сохранить её использую методы репозитория.

**2.5 Научиться по шедулеру раз в минуту выбирать записи из БД, для которых должны быть отправлены нотификации**

Цель задачи — иметь коллекцию записей из БД у которых дата и время отправки нотификации сейчас.

Шедулинг — механизм выполнения некоторых методов по расписанию, подробнее об этом в методичке.

Необходимо выполнять раз в минуту метод, который ищет записи в БД с датой выполнения — текущая минута.

Помимо этого, нужно написать в репозитории метод, который ищет записи, у которых время совпадает с текущим.

**2.6 Научиться для выбранных записей рассылать уведомления**

Для полученной коллекции записей из прошлой задачи необходимо рассылать уведомления в нужные чаты.

eng text

*Telegram is one of the most convenient and multifunctional messengers today. Initially, Telegram was intended for secure communication, in conditions of a high level of data encryption. But over time, the application has grown in functionality to such an extent that it has become possible to create full-fledged training channels, music search services, automatic translators and online media services.*

**Task**

In this course work, you will have to develop a bot for Telegram, which would be able to receive messages from the user in the format 
01.01.2022 20:00 Do your homework
and send a message to the user at 20:00 on January 1, 2022 with the text “Do your homework.”

**Task 1. Build a telegram bot**

**1.1 Register your telegram bot**

Add a bot to your telegram account https://t.me/BotFather , activate it
with the /start command
and follow the instructions. The main goal of this task is to get a token of the type
6290793636:AAGIqfVDV9My8UfMQGfvG5arXo8ZAaY6Bhs
, which you will use in the future when developing your bot to access the Telegram Bot API.

**1.2 Cloning the repository**

The goal of the task is to get an application template on your computer with Spring Boot configured for operation.

Here you need to fork the repository and clone it to your Java projects directory.

**1.3 Configure a tilted application**

The goal of the task is to get a working project on your computer that can be run in IDEA.

You need to create a database and a user that will be used by your application. Using application.properties, set up a database connection and transfer the token received in the 1st task.

**1.4 Learn how to respond to the /start command and output a welcome message**

Now that you have a startup project, it's time to be in class. 
The
TelegramBotUpdatesListener in
the public int process(List<Update> updates) method
learns how to process
the /start command
sent by the user to our bot in Telegram. To do this, we need to check all the updates that our method accepts as an argument and, in case we receive
the /start message.
 — display a welcome message. The text of the message is according to your taste.

**Task 2**

**2.1 Create a notification_task table using liquibase**

The goal of the task is to get a table of the structure we need in the database after starting the application.

Now it's time to understand how we will store our tasks in the database and design a database schema for this. I assume that we will need to have a primary key in the table, the ID of the chat to which the notification needs to be sent, the text of the notification, and the date+time when the notification needs to be sent. You may want to store some additional data. I need to create a table using liquibase with approximately this structure.

**2.2 Create a notification task entity**

The purpose of the task is to get a class with an annotation 
@Entity
, which will repeat the structure of our table in the database and will be suitable for use in our application code.

After we have created the table, we need to create an entity that we will work with in our application code.

**2.3 Create a repository for the notification task entity**

The purpose of the task is to get an interface with which you can save and retrieve our entities from the database.

The next step is to create an extension. 
JpaRepository<T, ID>
, which would be suitable for working with the entity created in the previous step.

**2.4 Learn how to parse messages with a reminder being created and save them to a database**

The purpose of the task is to get records in the database corresponding to the sent messages.

Finally, we got to the most important task — we have to teach how to process messages received from the user. 
01.01.2022 20:00 Do your homework
, extract the date + time from it (01.01.2022 20:00) and the text of the reminder (Do your homework), create an entity based on this data and save it to the database.

To recognize a substring with a date and a substring with a message in a string, we need regular expressions.

After we have separated the date and the reminder text in the incoming message, we need to construct an entity.

Having a constructed entity, it is necessary to save it using repository methods.

**2.5 Learn how to use a shader once a minute to select records from the database for which notifications should be sent**

The purpose of the task is to have a collection of records from the database that have the date and time when the notification was sent now.

Shadowing is a mechanism for executing certain methods on a schedule, more about this in the tutorial.

It is necessary to execute once a minute a method that searches for records in the database with the date of execution — the current minute.

In addition, you need to write a method in the repository that searches for records that have the same time as the current one.

**2.6 Learn how to send notifications for selected entries**

For the received collection of records from the previous task, you need to send notifications to the necessary chats.
