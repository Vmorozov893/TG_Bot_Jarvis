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
import pro.sky.telegrambot.service.BotService;

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
    private BotService service;

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
                    if (messageText.compareToIgnoreCase("Иди нахуй")==0) {
                        sendMessage(chatId, "__000000___00000 \n" +
                                "_00000000_0000000 \n" +
                                "_0000000000000000 \n" +
                                "__00000000000000 \n" +
                                "____00000000000 \n" +
                                "_______00000 \n" +
                                "_________0 \n" +
                                "________*__000000___00000 \n" +
                                "_______*__00000000_0000000 \n" +
                                "______*___0000000000000000 \n" +
                                "______*____00000000000000 \n" +
                                "_______*_____00000000000 \n" +
                                "________*_______00000 \n" +
                                "_________*________0 \n" +
                                "_000000___00000___* \n" +
                                "00000000_0000000___* \n" +
                                "0000000000000000____* \n" +
                                "_00000000000000_____* \n" +
                                "___00000000000_____* \n" +
                                "______00000_______* \n" +
                                "________0________* \n" +
                                "________*__000000___00000 \n" +
                                "_______*__00000000_0000000 \n" +
                                "______*___0000000000000000 \n" +
                                "______*____00000000000000 \n" +
                                "______*______00000000000 \n" +
                                "_______*________00000 \n" +
                                "________*_________0 \n" +
                                "_________*________* \n" +
                                "___________________* \n" +
                                "____________________* \n" +
                                "_____________________* \n" +
                                "______________________* \n" +
                                "_______________________*");
                    } //сердечки
                    else if (messageText.compareToIgnoreCase("дай ключ")==0) {//messageText.matches("Дай ключ")
                        sendMessage(chatId, service.newPerson(chatId).toString());
                    }
                    else if (messageText.matches("Ключ: (\\d{8})")) {
                        Integer key = Integer.valueOf(messageText.substring(6, 14));
                        sendMessage(chatId, service.friends(chatId, key));
                    }
                    else if (messageText.compareToIgnoreCase("Удалить друга")==0) {
                        sendMessage(chatId, service.deleteFriend(chatId));
                    }
                    else if (messageText.matches("^Сообщение для друга: .*")) {
                        long friendId = service.chatIdFriend(chatId);
                        String messageForFriend = messageText.substring(21);
                        sendMessage(friendId, messageForFriend);
                    }
                    else {
                        sendMessage(chatId, "иди нахуй");
                    }
//                    if (messageText.matches("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)")) {
//                        String dataTime = messageText.substring(0, 16);
//                        String notificationTask = messageText.substring(17);
//                        LocalDateTime nowTime = LocalDateTime.now();
//                        LocalDateTime dataTimeFinal = LocalDateTime.parse(dataTime, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
//                        sendMessage(chatId, "напоминание: " + service.write(chatId, dataTimeFinal, notificationTask).toString()
//                                + " успешно создано в " + nowTime.getHour() + ":" + nowTime.getMinute() + " "
//                                + nowTime.getDayOfMonth() + "." + nowTime.getMonthValue() + "." + nowTime.getYear());
//                    }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

//    @Scheduled(cron = "0 0/1 * * * *")
//    public void run() {
//        List<NotificationTask> messages= service.findMessage();
//        for(NotificationTask e:messages){
//            sendMessage(e.getChatId(),e.getMessage());
//        }
//    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Hi, " + name + ", nice to meet you!";
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), textToSend);
        telegramBot.execute(sendMessage);
    }


}
