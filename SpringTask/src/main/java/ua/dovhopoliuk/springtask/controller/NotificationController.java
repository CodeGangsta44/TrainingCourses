package ua.dovhopoliuk.springtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.*;
import ua.dovhopoliuk.springtask.dto.NotificationDTO;
import ua.dovhopoliuk.springtask.entity.Notification;
import ua.dovhopoliuk.springtask.entity.Role;
import ua.dovhopoliuk.springtask.entity.User;
import ua.dovhopoliuk.springtask.service.NotificationService;
import ua.dovhopoliuk.springtask.service.UserService;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/notifications")
public class NotificationController {

    private NotificationService notificationService;
    private UserService userService;
    private ReloadableResourceBundleMessageSource messageSource;

    @Autowired
    public NotificationController(NotificationService notificationService,
                                  ReloadableResourceBundleMessageSource messageSource,
                                  UserService userService) {
        this.notificationService = notificationService;
        this.messageSource = messageSource;
        this.userService = userService;
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

    @DeleteMapping(value = "/{notification}")
    public void deleteNotification(@PathVariable Notification notification) {
        User user = userService.getCurrentUser();

        System.out.println(notification.getAddressedUser().equals(user));
        System.out.println(user.getRoles().contains(Role.ADMIN));

        if (notification.getAddressedUser().equals(user) || user.getRoles().contains(Role.ADMIN)) {
            notificationService.deleteNotification(notification);
        }

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
