package org.portfolio.website.contactservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.portfolio.website.contactservice.domain.dto.request.ContactRequest;
import org.portfolio.website.contactservice.domain.dto.response.ContactResponse;
import org.portfolio.website.contactservice.domain.enums.HttpCodes;
import org.portfolio.website.contactservice.domain.exception.MailException;
import org.portfolio.website.contactservice.integration.NotificationMail;
import org.portfolio.website.contactservice.integration.ReplyBackMail;
import org.portfolio.website.contactservice.integration.dto.EmailDTO;
import org.portfolio.website.contactservice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {
    @Value("${spring.mail.username}")
    private String username;

    @Value("${receive.mail.account}")
    private String notificationFromUsername;

    @Autowired
    private NotificationMail notificationMail;

    @Autowired
    private ReplyBackMail replyBackMail;

    @Override
    public ContactResponse sendMail(ContactRequest contactRequest) {
        // request
        log.debug("Request: {}", contactRequest);

        // convert to email dto
        EmailDTO notificationEmail = mappedRequestToEmailDTO(contactRequest);

        // send dto to notification mail service
        Boolean result = notificationMail.sendNotificationMail(notificationEmail);

        // fail -> throw mail exception or create empty response
        // for now send exception
        if (Boolean.FALSE.equals(result)) {
            // TODO: After updating return value; Update exception as well
            throw new MailException("Error sending notification mail!", HttpCodes.INTERNAL_SERVER_ERROR);
        }

        // success -> create reply back email dto
        EmailDTO replyBackEmail = createEmailDTOForReplyBack(contactRequest);

        // send dto to reply back mail service
        Boolean replyBackResult = replyBackMail.sendReplyBackMail(replyBackEmail);

        // fail -> throw mail exception or create empty response
        // for now send exception
        if (Boolean.FALSE.equals(replyBackResult)) {
            // TODO: After updating return value; Update exception as well
            throw new MailException("Error sending reply back mail!", HttpCodes.INTERNAL_SERVER_ERROR);
        }

        // success -> create contact response for FE
        return ContactResponse.builder()
                .message("Success")
                .dateTime(LocalDateTime.now())
                .build();
    }

    private EmailDTO createEmailDTOForReplyBack(ContactRequest contactRequest) {
        return EmailDTO.builder()
                .from(username)
                .to(contactRequest.getEmail())
                .subject(createSubjectForReplyBackMail(contactRequest.getName()))
                .body(createBodyForReplyBackMail())
                .build();
    }

    private String createBodyForReplyBackMail() {
        return """
                Thanks a bunch for dropping me a line!🌟 I appreciate your interest in connecting.
                I'm currently going through my messages and will get back to you promptly. I'm eager to connect & chat with you!
                Big thanks once again, and catch you on the flip side!
                Cheers,
                Dhruv
                """;
    }

    private String createSubjectForReplyBackMail(String name) {
        return "Thanks for connecting! " + name;
    }

    private EmailDTO mappedRequestToEmailDTO(ContactRequest contactRequest) {
        return EmailDTO.builder()
                .from(username)
                .to(notificationFromUsername)
                .subject(createSubjectForNotificationMail(contactRequest.getName()))
                .body(createBodyForNotificationMail(contactRequest.getMessage()))
                .build();
    }

    private String createBodyForNotificationMail(String message) {
        return message;
    }

    private String createSubjectForNotificationMail(String name) {
        return name + " wants to connect!";
    }
}
