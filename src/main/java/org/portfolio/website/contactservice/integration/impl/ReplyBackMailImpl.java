package org.portfolio.website.contactservice.integration.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.portfolio.website.contactservice.integration.ReplyBackMail;
import org.portfolio.website.contactservice.integration.dto.EmailDTO;
import org.portfolio.website.contactservice.utils.SentDurationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Slf4j
@Component
public class ReplyBackMailImpl implements ReplyBackMail {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Sends a reply back email.
     *
     * @param replyBackEmail The email data transfer object.
     * @return True if the email is sent successfully, false otherwise.
     */
    @Override
    public Boolean sendReplyBackMail(EmailDTO replyBackEmail) {
        long startTime = System.currentTimeMillis();

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(replyBackEmail.getFrom(), "Dhruv Maheshwari");
            helper.setTo(replyBackEmail.getTo());
            helper.setSubject(replyBackEmail.getSubject());
            helper.setText(replyBackEmail.getBody(), true);

            mailSender.send(message);

            log.info("Email sent successfully to: {}", replyBackEmail.getTo());
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
