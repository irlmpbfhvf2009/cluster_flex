package org.springybot.model;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springybot.botModel.BaseWebhookBot;
import org.springybot.utils.KeyboardButton;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.springframework.core.io.Resource;

public class Tuoyijiqirenebot extends BaseWebhookBot {

    @Value("${telegram.botUsername}")
    private String botUsername;

    private static Integer msgId=0;

    public Tuoyijiqirenebot(DefaultBotOptions options, String botToken) {
        super(options, botToken);
    }

    @Override
    protected void handlePrivateMessage() throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        KeyboardButton keyboardButton = new KeyboardButton();

        switch (text.toLowerCase()) {
            case "/start":
                sendMessage.setChatId(chatId_str);
                sendMessage.setText("欢迎👏使用脱衣机器人请点击下方按钮");
                sendMessage.setReplyMarkup(keyboardButton.markupInline());
                this.execute(sendMessage);
                sendMessage.setChatId(chatId_str);
                sendMessage.setText("想脱母🐶的衣服你先要关注以下关注频道哦👇，那么请关注频道后再使用邀请链接");
                sendMessage.setReplyMarkup(keyboardButton.markupInline2());
                this.execute(sendMessage);
                break;
            case "使用方法":
                sendMessage.setChatId(chatId_str);
                sendMessage.setText(
                        "发送一张照片给机器人🤖\n\n" +
                                "需要等待1分钟左右(有时需要更久)，机器人会把处理完成的照片发给您\n\n" +
                                "处理一张照片的费用是1usdt\n\n" +
                                "点击查看示例，查看机器人处理效果\n\n" +
                                "点击我要充值，进行充值\n\n" +
                                "点击个人中心，查看自己账户余额，是否有免费试用机会，以及邀请用户数\n\n" +
                                "脱衣服公告群: https://t.me/+47pmioaF7Wk2MTk1");
                this.executeAsync(sendMessage);
                break;
            case "查看示例":
                try {
                    SendPhoto sendPhoto = new SendPhoto();
                    InputFile photo;
                    sendPhoto.setChatId(chatId_str);
                    sendPhoto.setCaption("处理前");
                    Resource photoResource = new ClassPathResource("static/photos/photo_2023-08-06_03-20-05.jpg");
                    photo = new InputFile(photoResource.getInputStream(), "photo_2023-08-06_03-20-05.jpg");
                    sendPhoto.setPhoto(photo);
                    this.execute(sendPhoto);
                    sendPhoto.setCaption("处理后");
                    photoResource = new ClassPathResource("static/photos/photo_2023-08-06_03-25-22.jpg");
                    photo = new InputFile(photoResource.getInputStream(), "photo_2023-08-06_03-25-22.jpg");
                    sendPhoto.setPhoto(photo);
                    this.executeAsync(sendPhoto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "我要充值":
                sendMessage.setChatId(chatId_str);
                sendMessage.setText("请选择充值金额:");
                sendMessage.setReplyMarkup(keyboardButton.markupInline3());
                try {
                    Tuoyijiqirenebot.msgId = this.executeAsync(sendMessage).get().getMessageId();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
            case "个人中心":
                sendMessage.setChatId(chatId_str);
                String userId = "`" + chatId_str + "`";
                sendMessage.setText("个人中心🎁:\n\n" +
                        "【用户ID】: " + userId + "\n" +
                        "【账户余额】: 0.0");
                sendMessage.setParseMode(ParseMode.MARKDOWN);
                this.executeAsync(sendMessage);
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
    protected void handleCallbackQuery() throws TelegramApiException {
        String data = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setMessageId(Tuoyijiqirenebot.msgId);
        this.executeAsync(editMessageText);
        KeyboardButton keyboardButton = new KeyboardButton();
        editMessageText.setChatId(chatId_str);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId_str);
        String price = "";
        switch (data) {
            case "price3":
                price = "`3.001`";
                break;
            case "price6":
                price = "6.001";
                break;
            case "price12":
                price = "12.001";
                break;
            case "price18":
                price = "18.001";
                break;
            case "price30":
                price = "30.001";
                break;
            case "price50":
                price = "50.001";
                break;
            case "price100":
                price = "100.001";
                break;
            case "payStatus":
                sendMessage.setText("暂时没有检测到支付信息");
                break;
        }
        if (!price.equals("")) {
            editMessageText.setText("此订单10分钟内有效，过期后请重新生成订单。\n" +
                    "➖➖➖➖➖➖➖➖➖➖\n" +
                    "转账地址: `THJXhJ1hX83V8my5ew3toaWeDjqhhe8viv` (TRC-20网络)\n" +
                    "转账金额: " + price + " USDT\n" +
                    "➖➖➖➖➖➖➖➖➖➖\n" +
                    "请注意转账金额务必与上方的转账金额一致，否则无法自动到账\n" +
                    "支付完成后, 请等待5分钟左右查询，自动到账。");
            editMessageText.setParseMode(ParseMode.MARKDOWN);
            editMessageText.setReplyMarkup(keyboardButton.markupInline4());
        }
        this.executeAsync(sendMessage);
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
