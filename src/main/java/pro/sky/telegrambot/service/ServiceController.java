package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ServiceController {

    private List<List<Integer>> coloda = new ArrayList<>();
    private List<List<Integer>> person1 = new ArrayList<>();
    private List<List<Integer>> person2 = new ArrayList<>();
    private final List<String> picty = new ArrayList<>(List.of("Девять","Десять","Валет","Дама","Король","Туз"));
    private final List<String> masty = new ArrayList<>(List.of("Червы","Крести","Бубы","Трефы"));

    private void createColoda() { //i=9 - 24карты i=6 - 36карт i=2 - 54карты
        for (int i = 9; i <= 14; i++) { //числа так же 11-валет 12-дама 13-король 14-туз
            for (int j = 1; j <= 4; j++) { //1-червы 2-крести 3-бубы 4-трефы(вини)
                coloda.add(new ArrayList<>(List.of(i, j)));
            }
        }
    }

    private void createPersonOne() {
        Random random = new Random();
        int max = coloda.size();
        for (int i = 0; i < 6; i++) {
            int value = random.nextInt(max - i);
            person1.add(coloda.get(value));
            coloda.remove(value);
        }
    }

    private void createPersonTwo() {
        Random random = new Random();
        int max = coloda.size();
        for (int i = 0; i < 6; i++) {
            int value = random.nextInt(max - i);
            person2.add(coloda.get(value));
            coloda.remove(value);
        }
    }

    private List<String> numToString(List<Integer> e){
        return new ArrayList<>(List.of(picty.get(e.get(0)-9),masty.get(e.get(1)-1)));
    }

    private List<List<String>> toKart(List<List<Integer>> karti){
        return karti.stream().map(this::numToString).collect(Collectors.toList());
    }

    public void razdacha() {
        createColoda();
        createPersonOne();
        createPersonTwo();
    }


    public String enter() {
        return "В колоде: " + toKart(coloda) + "   У первого игрока: " + toKart(person1) + "   У второго игрока: " + toKart(person2);
    }


}
