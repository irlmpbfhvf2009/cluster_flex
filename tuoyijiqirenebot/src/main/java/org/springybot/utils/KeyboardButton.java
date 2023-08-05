package org.springybot.utils;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class KeyboardButton {

    public final ReplyKeyboardMarkup markupInline() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("使用方法");
        row.add("查看示例");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("我要充值");
        row.add("个人中心");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public final InlineKeyboardMarkup markupInline2() {
        InlineKeyboardButton dk1 = new InlineKeyboardButton();
        InlineKeyboardButton dk2 = new InlineKeyboardButton();
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        dk1.setText("官方频道🏘️");
        dk1.setUrl("https://t.me/+47pmioaF7Wk2MTk1");
        dk2.setText("官方客服💁");
        dk2.setUrl("http://t.me/shjvhjoh");
        rowInline.add(dk1);
        rowInline.add(dk2);
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public final InlineKeyboardMarkup markupInline3() {
        InlineKeyboardButton price3 = new InlineKeyboardButton();
        InlineKeyboardButton price6 = new InlineKeyboardButton();
        InlineKeyboardButton price12 = new InlineKeyboardButton();
        InlineKeyboardButton price18 = new InlineKeyboardButton();
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        price3.setText("3");
        price3.setCallbackData("price3");
        price6.setText("6");
        price6.setCallbackData("price6");
        price12.setText("12");
        price12.setCallbackData("price12");
        price18.setText("18");
        price18.setCallbackData("price18");
        rowInline.add(price3);
        rowInline.add(price6);
        rowInline.add(price12);
        rowInline.add(price18);
        InlineKeyboardButton price30 = new InlineKeyboardButton();
        InlineKeyboardButton price50 = new InlineKeyboardButton();
        InlineKeyboardButton price100 = new InlineKeyboardButton();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        price30.setText("30");
        price30.setCallbackData("price30");
        price50.setText("50");
        price50.setCallbackData("price50");
        price100.setText("100");
        price100.setCallbackData("price100");
        rowInline2.add(price30);
        rowInline2.add(price50);
        rowInline2.add(price100);
        InlineKeyboardButton tests = new InlineKeyboardButton();
        tests.setText("人工口令充值");
        tests.setUrl("https://t.me/+47pmioaF7Wk2MTk1");
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        rowInline3.add(tests);

        rowsInline.add(rowInline);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public final InlineKeyboardMarkup markupInline4() {
        InlineKeyboardButton dk1 = new InlineKeyboardButton();
        InlineKeyboardButton dk2 = new InlineKeyboardButton();
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        dk1.setText("检测支付状态");
        dk1.setCallbackData("payStatus");
        dk2.setText("官方客服");
        dk2.setUrl("http://t.me/shjvhjoh");
        rowInline.add(dk1);
        rowInline2.add(dk2);
        rowsInline.add(rowInline);
        rowsInline.add(rowInline2);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

}
