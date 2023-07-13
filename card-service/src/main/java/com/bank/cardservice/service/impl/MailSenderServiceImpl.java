package com.bank.cardservice.service.impl;

import com.bank.cardservice.service.MailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender emailSender;

    public MailSenderServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    @Override
    public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment) throws MessagingException, MessagingException, FileNotFoundException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(toAddress);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
        messageHelper.addAttachment("MoneyTransferCheck.pdf", file);
        emailSender.send(mimeMessage);

    }
}
