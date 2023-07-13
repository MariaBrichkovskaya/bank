package com.bank.cardservice.service;

import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;

public interface MailSenderService {
    void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment) throws MessagingException, FileNotFoundException;
}
