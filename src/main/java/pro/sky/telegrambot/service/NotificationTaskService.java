package pro.sky.telegrambot.service;

import liquibase.pro.packaged.L;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class NotificationTaskService {
    private final NotificationTaskRepository repository;

    public NotificationTaskService(NotificationTaskRepository repository) {
        this.repository = repository;
    }

    public NotificationTask write (long chatId, LocalDateTime dataTimeFinal, String message){
        NotificationTask notificationTask = new NotificationTask(chatId,dataTimeFinal,message);
        return repository.save(notificationTask);
    }

    public List<NotificationTask> findMessage(){
        LocalDateTime nowTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        return repository.findAll().stream().filter(e-> e.getDateTime().truncatedTo(ChronoUnit.MINUTES).equals(nowTime)).toList();
    }




}
