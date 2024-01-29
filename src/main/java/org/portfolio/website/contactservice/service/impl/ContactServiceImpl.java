package org.portfolio.website.contactservice.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.portfolio.website.contactservice.domain.dto.request.ContactRequest;
import org.portfolio.website.contactservice.domain.dto.response.ContactResponse;
import org.portfolio.website.contactservice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {
    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public ContactResponse sendMail(ContactRequest contactRequest) {
        log.debug("Request: {}", contactRequest);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(username, "Dhruv Maheshwari");
            helper.setTo(contactRequest.getEmail());
            helper.setSubject("Test Mail From " + contactRequest.getName());
            helper.setText(contactRequest.getMessage(), false);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
