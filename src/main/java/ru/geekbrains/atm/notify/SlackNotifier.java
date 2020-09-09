package ru.geekbrains.atm.notify;

import org.springframework.beans.factory.annotation.Value;

public class SlackNotifier implements EventListener {

    @Value("{slack.url}")
    private String appUrl;

    @Value("{slack.token}")
    private String token;

    public SlackNotifier(String appUrl, String token) {
        this.appUrl = appUrl;
        this.token = token;
    }

    public SlackNotifier() {

    }

    @Override
    public void notify(String msg, String category) {
        //TODO отправка сообщений в слак

    }
}
