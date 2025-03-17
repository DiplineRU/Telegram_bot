package pro.sky.telegrambot.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class NotificationScheduler {
    private final NotificationTaskService notificationTaskService;

    public NotificationScheduler(NotificationTaskService notificationTaskService) {
        this.notificationTaskService = notificationTaskService;
    }

    @Scheduled(cron = "0 0/1 * * * *") // Запуск каждую минуту
    public void checkNotifications() {

        LocalDateTime currentTime = LocalDateTime.now();
        notificationTaskService.sendNotifications(currentTime);
    }
}
