package ru.geekbrains.atm.notify;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogManager {

    Map<String, List<EventListener>> listeners = new HashMap<>();


    public LogManager() {
        //default listener
        subscribe("error", new EmailNotifier());
    }

    public void subscribe(String category, EventListener listener) {
        List<EventListener> users = listeners.get(category);
        users.add(listener);
    }

    public void unsubscribe(String category, EventListener listener) {
        List<EventListener> users = listeners.get(category);
        users.remove(listener);
    }

    public void notify(String msg, String category) {
        List<EventListener> categoryListeners = listeners.get(category);
        for (EventListener listener : categoryListeners) {
            listener.notify(msg, category);
        }
    }
}
