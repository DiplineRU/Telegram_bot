package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationTaskService {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private final NotificationTaskRepository notificationTaskRepository;
    private static final Pattern PATTERN = Pattern.compile(
            "(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)"
    );

    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
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
}
