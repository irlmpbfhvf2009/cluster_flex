package org.springybot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springybot.model.Tuoyijiqirenebot;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class TelegramBotController {

    @Autowired
    private Tuoyijiqirenebot tuoyijiqirenebot;

    @PostMapping("/tuoyijiqirenebot")
    public void onUpdateReceived(@RequestBody Update update) {
        tuoyijiqirenebot.onWebhookUpdateReceived(update);
    }
}
