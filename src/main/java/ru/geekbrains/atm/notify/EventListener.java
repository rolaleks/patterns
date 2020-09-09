package ru.geekbrains.atm.notify;

public interface EventListener {
    public void notify(String msg, String category);
}
