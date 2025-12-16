package pro.sky.telegrambot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.service.ServiceController;

@RestController
@RequestMapping("durak")
public class DurakController {

    private final ServiceController serviceController;
    public DurakController(ServiceController serviceController) {
        this.serviceController=serviceController;
    }

    @GetMapping("razd")
    public String razdacha(){
        serviceController.razdacha();
        return "Раздали";
    }

    @GetMapping("enter")
    public String enter(){
        return serviceController.enter();
    }

    @GetMapping("person1")
    public String person1(){
        return serviceController.enter();
    }

}
