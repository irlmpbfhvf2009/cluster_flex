package org.springybot.model;

import org.springframework.beans.factory.annotation.Value;
import org.springybot.botModel.BaseWebhookBot;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Tuoyijiqirenebot extends BaseWebhookBot {

    @Value("${telegram.botUsername}")
    private String botUsername;

    public Tuoyijiqirenebot(DefaultBotOptions options, String botToken) {
        super(options, botToken);
    }

    @Override
    protected void handlePrivateMessage() {
        SendMessage sendMessage = new SendMessage(chatId_str, text);
        this.executeAsync(sendMessage);
    }

    @Override
    protected void handleGroupMessage() {
    }

    @Override
    protected void handlePhotoMessage() {
    }

    @Override
    protected void handleCallbackQuery() {
    }

    @Override
    protected void handleChannelPost() {
    }

    @Override
    protected void handleChatMemberUpdate() {
    }

    @Override
    public String getBotPath() {
        return botUsername;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

}
