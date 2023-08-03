package org.springybot.botModel;

import java.util.Arrays;
import java.util.List;

public class Config {

    public final static List<String> allowedUpdates = Arrays.asList("update_id", "message", "edited_message",
            "channel_post", "edited_channel_post", "inline_query", "chosen_inline_result",
            "callback_query", "shipping_query", "pre_checkout_query", "poll", "poll_answer",
            "my_chat_member", "chat_member");

}
