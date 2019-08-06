package ua.dovhopoliuk.springtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dovhopoliuk.springtask.entity.Notification;
import ua.dovhopoliuk.springtask.entity.User;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByAddressedUser(User user);
    void deleteAllByAddressedUser(User user);
}
