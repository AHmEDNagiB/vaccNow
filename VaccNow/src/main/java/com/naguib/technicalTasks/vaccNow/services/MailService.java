package com.naguib.technicalTasks.vaccNow.services;

import java.util.concurrent.CompletableFuture;

public interface MailService {
    CompletableFuture<String> sentMail(String to,String subject, String mailBody);
}
