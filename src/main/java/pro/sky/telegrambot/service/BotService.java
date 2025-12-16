package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BotService {
    public Integer getKey(){
        Random random = new Random();
        return random.nextInt(10000000);
    }
}
