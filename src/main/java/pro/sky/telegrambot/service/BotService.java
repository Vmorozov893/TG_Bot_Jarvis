package pro.sky.telegrambot.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Person;
import pro.sky.telegrambot.repository.Repository;

import java.util.Optional;
import java.util.Random;

@Service
public class BotService {

    private final Repository repository;

    public BotService(Repository repository) {
        this.repository = repository;
    }

    public Integer newPerson(long chatId) {
        Optional<Person> u = repository.findByChatId(chatId);
        if (u.isEmpty()) {
            Person newPlayer = new Person(getKey(), chatId, 0);
            repository.save(newPlayer);
            return newPlayer.getKey();
        } else {
            Person player = u.get();
            return player.getKey();
        }
    }
    public String friends(long chatId, Integer key) {
        int u = repository.updateFriendChatIdByKey(key,chatId);
        if (u==0) {
            return "иди нахуй хакер";
        } else {
            return "друг добавлен";
        }
    }
    public String deleteFriend(long chatId){
        int u = repository.deleteFriend(chatId);
        if(u==0){
            return "у вас их и не было";
        }
        else{
            return "друг удален";
        }
    }


    private Integer getKey() {
        Random random = new Random();
        return random.nextInt(89_999_999)+10_000_000;
    }
}
