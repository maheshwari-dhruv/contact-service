package org.portfolio.website.contactservice.integration.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.portfolio.website.contactservice.domain.dto.request.ContactRequest;
import org.portfolio.website.contactservice.integration.NotificationEmail;
import org.portfolio.website.contactservice.integration.dto.ContactResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
//import java.util.Objects;

@Slf4j
@Component
public class NotificationEmailImpl implements NotificationEmail {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
//    private SimpleMailMessage simpleMailMessage;

    @Override
    public ContactResponseDTO sendNotificationMail(String email, String name, String message) throws Exception {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(username, "Dhruv Maheshwari");
            helper.setTo(contactRequest.getEmail());

            helper.setSubject(contactRequest.getName());
            helper.setText(contactRequest.getMessage(), false);
//            helper.setText(Objects.requireNonNull(simpleMailMessage.getText()), true);

            mailSender.send(message);
            log.debug("Mail Sent Successfully...");
        } catch (MessagingException | UnsupportedEncodingException ex) {
            log.error("Exception: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }

        return null;
    }
}
