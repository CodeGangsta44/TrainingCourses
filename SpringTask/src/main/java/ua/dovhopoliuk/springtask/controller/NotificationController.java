package ua.dovhopoliuk.springtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.dovhopoliuk.springtask.dto.NotificationDTO;
import ua.dovhopoliuk.springtask.entity.Notification;
import ua.dovhopoliuk.springtask.service.NotificationService;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/notifications")
public class NotificationController {

    private NotificationService notificationService;
    private ReloadableResourceBundleMessageSource messageSource;

    @Autowired
    public NotificationController(NotificationService notificationService, ReloadableResourceBundleMessageSource messageSource) {
        this.notificationService = notificationService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public List<NotificationDTO> getAllNotifications() {
        return notificationService.getAllNotifications().stream()
                .map(this::createNotificationDTOFromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/me")
    public List<NotificationDTO> getNotificationsOfCurrentUser() {
        return notificationService.getCurrentUserNotifications().stream()
                .map(this::createNotificationDTOFromEntity)
                .collect(Collectors.toList());
    }

    private NotificationDTO createNotificationDTOFromEntity(Notification notification) {
        String topicPattern = messageSource.getMessage(
                notification.getTopicKey(),
                null,
                LocaleContextHolder.getLocale());

        String messagePattern = messageSource.getMessage(
                notification.getMessageKey(),
                null,
                LocaleContextHolder.getLocale());

        String topic = MessageFormat.format(topicPattern, notification.getTopicValues().toArray());
        String message = MessageFormat.format(messagePattern, notification.getMessageValues().toArray());

        return NotificationDTO.builder()
                .id(notification.getId())
                .notificationDateTime(notification.getNotificationDateTime())
                .topic(topic)
                .message(message)
                .build();
    }
}
