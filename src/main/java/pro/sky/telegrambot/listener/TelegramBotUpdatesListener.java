package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.service.NotificationTaskService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private NotificationTaskService service;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            String messageText = update.message().text();
            long chatId = update.message().chat().id();
            switch (messageText) {
                case "/start":
                    startCommandReceived(chatId, update.message().chat().firstName());
                    break;
                default:
                    if (messageText.matches("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)")) {
                        String dataTime = messageText.substring(0, 16);
                        String notificationTask = messageText.substring(17);
                        LocalDateTime nowTime = LocalDateTime.now();
                        LocalDateTime dataTimeFinal = LocalDateTime.parse(dataTime, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                        sendMessage(chatId, "напоминание: " + service.write(chatId, dataTimeFinal, notificationTask).toString()
                                + " успешно создано в " + nowTime.getHour() + ":" + nowTime.getMinute() + " "
                                + nowTime.getDayOfMonth() + "." + nowTime.getMonthValue() + "." + nowTime.getYear());
                    }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        List<NotificationTask> messages= service.findMessage();
        for(NotificationTask e:messages){
            sendMessage(e.getChatId(),e.getMessage());
        }
    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Hi, " + name + ", nice to meet you!";
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), textToSend);
        telegramBot.execute(sendMessage);
    }


}
