package org.portfolio.website.contactservice.integration.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.portfolio.website.contactservice.integration.NotificationMail;
import org.portfolio.website.contactservice.integration.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Slf4j
@Component
public class NotificationMailImpl implements NotificationMail {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Boolean sendNotificationMail(EmailDTO emailDTO) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(emailDTO.getFrom(), "No Reply @ Portfolio Website");
            helper.setTo(emailDTO.getTo());
            helper.setSubject(emailDTO.getSubject());

            // TODO: update body with email template
            helper.setText(emailDTO.getBody(), false);

            mailSender.send(message);

            // TODO: Update return value
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Exception: {}", e.getMessage());
            return false;
        }
    }
}
