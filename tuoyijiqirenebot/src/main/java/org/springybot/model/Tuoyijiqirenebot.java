package org.springybot.model;

import org.springframework.beans.factory.annotation.Value;
import org.springybot.botModel.BaseWebhookBot;
import org.springybot.utils.KeyboardButton;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Tuoyijiqirenebot extends BaseWebhookBot {

    @Value("${telegram.botUsername}")
    private String botUsername;

    public Tuoyijiqirenebot(DefaultBotOptions options, String botToken) {
        super(options, botToken);
    }

    @Override
    protected void handlePrivateMessage() {
        switch (text.toLowerCase()) {
            case "/start":
                try {
                    SendMessage sendMessage = new SendMessage();
                    KeyboardButton keyboardButton = new KeyboardButton();
                    sendMessage.setChatId(chatId_str);
                    sendMessage.setText("欢迎👏使用脱衣机器人请点击下方按钮");
                    sendMessage.setReplyMarkup(keyboardButton.markupInline());
                    this.execute(sendMessage);

                    SendMessage sendMessage2 = new SendMessage();
                    KeyboardButton keyboardButton2 = new KeyboardButton();
                    sendMessage2.setChatId(chatId_str);
                    sendMessage2.setText("想脱母🐶的衣服你先要关注以下关注频道哦👇，那么请关注频道后再使用邀请链接");
                    sendMessage2.setReplyMarkup(keyboardButton2.markupInline2());
                    this.execute(sendMessage2);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "使用方法":
                break;
            case "查看示例":
                break;
            case "我要充值":
                break;
            case "个人中心":
                break;
        }
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
