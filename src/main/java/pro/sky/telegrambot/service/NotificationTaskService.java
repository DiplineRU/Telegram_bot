package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationTaskService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationTaskService.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private final NotificationTaskRepository notificationTaskRepository;
    private final TelegramBot telegramBot;
    private static final Pattern PATTERN = Pattern.compile(
            "(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)"
    );

    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository, TelegramBot telegramBot) {
        this.notificationTaskRepository = notificationTaskRepository;
        this.telegramBot = telegramBot;
    }

    public void saveNotificationTask(Long chatId, String message) {
        NotificationTask task = parseMessage(chatId, message);
        notificationTaskRepository.save(task);
    }

    public static NotificationTask parseMessage(Long chatId, String message) {
        Matcher matcher = PATTERN.matcher(message);
        if (matcher.matches()) {
            String dateTimeString = matcher.group(1); // Дата и время
            String text = matcher.group(3); // Текст

            LocalDateTime notificationDateTime = LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
            return new NotificationTask(chatId, text, notificationDateTime);
        } else {
            throw new IllegalArgumentException("Некорректный формат сообщения. Ожидается: dd.MM.yyyy HH:mm Текст напоминания");
        }
    }

    public void sendNotifications(LocalDateTime notificationDateTime) {
        logger.info("Checking notifications for time: {}", notificationDateTime);
        List<NotificationTask> tasks = notificationTaskRepository.findAll();
        logger.info("Found {} tasks to notify", tasks.size());

        for (NotificationTask task : tasks) {
            if (!task.isSent()) {
                sendNotification(task);
                task.setSent(true);
                notificationTaskRepository.save(task);
            }
        }
    }

    private void sendNotification(NotificationTask task) {
        String messageText = "Напоминание: " + task.getMessage();
        SendMessage message = new SendMessage(task.getChatId(), messageText);
        telegramBot.execute(message);
        logger.info("Notification sent to chatId: {}, message: {}", task.getChatId(), task.getMessage());
    }
}
