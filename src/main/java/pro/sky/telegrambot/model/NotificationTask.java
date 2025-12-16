package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificationtask")
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "chatid")
    private long chatId;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @Column(name = "message")
    private String message;

    public NotificationTask() {
    }

    public NotificationTask(long chatId, LocalDateTime dateTime, String message) {
        this.chatId = chatId;
        this.dateTime = dateTime;
        this.message = message;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message + " в " + dateTime.getHour()+":"+dateTime.getMinute()+" "
                +dateTime.getDayOfMonth()+"."+dateTime.getMonthValue()+"."+dateTime.getYear();
    }
}
