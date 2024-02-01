package org.portfolio.website.contactservice.integration.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.portfolio.website.contactservice.integration.NotificationMail;
import org.portfolio.website.contactservice.integration.dto.EmailDTO;
import org.portfolio.website.contactservice.utils.SentDurationUtil;
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

    /**
     * Sends a notification email.
     *
     * @param emailDTO The email data transfer object.
     * @return True if the email is sent successfully, false otherwise.
     */
    @Override
    public Boolean sendNotificationMail(EmailDTO emailDTO) {
        long startTime = System.currentTimeMillis();

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(emailDTO.getFrom(), "No Reply @ Portfolio Website");
            helper.setTo(emailDTO.getTo());
            helper.setSubject(emailDTO.getSubject());
            helper.setText(emailDTO.getBody(), true);

            mailSender.send(message);

            log.info("Email sent successfully to: {}", emailDTO.getTo());
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Exception while sending email: {}", e.getMessage());
            return false;
        } finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            log.debug("Sent Duration: {}", SentDurationUtil.formatMailSentDuration(duration));
        }
    }
}
