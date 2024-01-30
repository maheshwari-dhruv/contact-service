package org.portfolio.website.contactservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.portfolio.website.contactservice.domain.dto.request.ContactRequest;
import org.portfolio.website.contactservice.domain.dto.response.ContactResponse;
import org.portfolio.website.contactservice.domain.enums.HttpCodes;
import org.portfolio.website.contactservice.integration.NotificationMail;
import org.portfolio.website.contactservice.integration.ReplyBackMail;
import org.portfolio.website.contactservice.integration.dto.EmailDTO;
import org.portfolio.website.contactservice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {
    @Value("${spring.mail.username}")
    private String username;

    @Value("${receive.mail.account}")
    private String receiverUsername;

    @Autowired
    private NotificationMail notificationMail;

    @Autowired
    private ReplyBackMail replyBackMail;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public ContactResponse sendMail(ContactRequest contactRequest) {
        log.debug("Request: {}", contactRequest);
        EmailDTO notificationEmail = mappedRequestToEmailDTO(contactRequest);
        Boolean result = notificationMail.sendNotificationMail(notificationEmail);

        if (Boolean.FALSE.equals(result)) {
            return createResponse(HttpCodes.INTERNAL_SERVER_ERROR.getHttpCode(), "Error sending notification mail!");
        }

        EmailDTO replyBackEmail = createEmailDTOForReplyBack(contactRequest);
        Boolean replyBackResult = replyBackMail.sendReplyBackMail(replyBackEmail);

        if (Boolean.FALSE.equals(replyBackResult)) {
            return createResponse(HttpCodes.INTERNAL_SERVER_ERROR.getHttpCode(), "Error sending reply back mail!");
        }

        return createResponse(HttpCodes.SUCCESS.getHttpCode(), "Success");
    }

    private ContactResponse createResponse(int httpCode, String s) {
        return ContactResponse.builder()
                .statusCode(httpCode)
                .message(s)
                .dateTime(LocalDateTime.now())
                .build();
    }

    private EmailDTO createEmailDTOForReplyBack(ContactRequest contactRequest) {
        String htmlContent = templateEngine.process("reply-back-mail-template", createReplyBackMailBody());

        return EmailDTO.builder()
                .from(username)
                .to(contactRequest.getEmail())
                .subject(createReplyBackMailSubject(contactRequest.getName()))
                .body(htmlContent)
                .build();
    }

    private IContext createReplyBackMailBody() {
        String body = """
                Thanks a bunch for dropping me a line!🌟 I appreciate your interest in connecting.
                I'm currently going through my messages and will get back to you promptly. I'm eager to connect & chat with you!
                Big thanks once again, and catch you on the flip side!
                Cheers,
                Dhruv
                """;

        Context context = new Context();
        context.setVariable("body", body);
        return context;
    }

    private String createReplyBackMailSubject(String name) {
        return "Thanks for connecting! " + name;
    }

    private EmailDTO mappedRequestToEmailDTO(ContactRequest contactRequest) {
        String htmlContent = templateEngine.process("notification-mail-template", createNotificationMailBody(contactRequest));

        return EmailDTO.builder()
                .from(username)
                .to(receiverUsername)
                .subject(createNotificationMailSubject(contactRequest.getName()))
                .body(htmlContent)
                .build();
    }

    private IContext createNotificationMailBody(ContactRequest contactRequest) {
        Context context = new Context();
        context.setVariable("name", contactRequest.getName());
        context.setVariable("email", contactRequest.getEmail());
        context.setVariable("body", contactRequest.getMessage());
        return context;
    }

    private String createNotificationMailSubject(String name) {
        return name + " wants to connect!";
    }
}
