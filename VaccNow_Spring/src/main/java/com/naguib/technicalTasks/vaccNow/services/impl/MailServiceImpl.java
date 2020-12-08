package com.naguib.technicalTasks.vaccNow.services.impl;

import com.naguib.technicalTasks.vaccNow.services.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    @Async
    public CompletableFuture<String> sentMail(String to,String subject, String mailBody) {
        LOGGER.info("start sending email");
        System.out.println(fromMail);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(mailBody);
        javaMailSender.send(message);
        LOGGER.info("end sending email");
        return CompletableFuture.completedFuture("mail is sent");
    }
}

