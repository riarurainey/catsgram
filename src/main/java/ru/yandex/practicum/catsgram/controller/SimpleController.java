package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.service.HackCatService;


@RestController
public class SimpleController {

    private static final Logger log = LoggerFactory.getLogger(SimpleController.class);
    private final HackCatService hackCatService;

    @Autowired
    public SimpleController(HackCatService hackCatService) {
        this.hackCatService = hackCatService;
    }

    @GetMapping("/do-hack")

    public void doHack() {
        String pass = hackCatService.doHackNow();
        if (pass == null) {
            log.info("Password not found");
        } else {
            log.info("Password was found: " + pass);
        }

    }

    @GetMapping("/home")
    public String homePage() {
        log.info("Request received");
        return "Catsgram";
    }
}
