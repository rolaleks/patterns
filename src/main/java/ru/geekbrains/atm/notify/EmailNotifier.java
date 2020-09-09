package ru.geekbrains.atm.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailNotifier implements EventListener {

    private String email;

    @Value("${email-from}")
    private String emailFrom;

    @Value("${admin-email}")
    private String adminEmail;

    public EmailNotifier(String email) {
        this.email = email;
    }

    public EmailNotifier() {
        this.email = adminEmail;
    }

    @Override
    public void notify(String msg, String category) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(email);
        message.setSubject("Notification: " + category);
        message.setText(msg);
        mailSender.send(message);
    }
}
