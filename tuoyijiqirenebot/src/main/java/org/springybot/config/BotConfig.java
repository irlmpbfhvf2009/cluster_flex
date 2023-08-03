package org.springybot.config;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springybot.botModel.Config;
import org.springybot.model.Tuoyijiqirenebot;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfig {

    @Value("${telegram.botToken}")
    @NotNull
    @NotEmpty
    private String botToken;

    @Value("${telegram.webhookUrl}")
    @NotNull
    @NotEmpty
    private String webhook_url;

    // @Autowired
    // private SpringyBotServiceFeignClient serviceFeignClient;
    @Bean
    SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(webhook_url).build();
    }

    @Bean
    Tuoyijiqirenebot tuoyijiqirenebot() {
        DefaultBotOptions options = new DefaultBotOptions();
        options.setAllowedUpdates(Config.allowedUpdates);
        Tuoyijiqirenebot bot = new Tuoyijiqirenebot(options, botToken);
        // serviceFeignClient.cacheSpringyBotDataToRedis(botToken);
        return bot;
    }

    @Bean
    void telegramBotsApi() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(tuoyijiqirenebot(), setWebhookInstance());
        tuoyijiqirenebot().execute(setWebhookInstance());
    }

}