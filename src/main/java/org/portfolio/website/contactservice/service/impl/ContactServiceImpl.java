package org.portfolio.website.contactservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.portfolio.website.contactservice.domain.dto.request.ContactRequest;
import org.portfolio.website.contactservice.domain.dto.response.ContactResponse;
import org.portfolio.website.contactservice.integration.NotificationEmail;
import org.portfolio.website.contactservice.integration.ReplyBackEmail;
import org.portfolio.website.contactservice.integration.dto.ContactResponseDTO;
import org.portfolio.website.contactservice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private NotificationEmail notificationEmail;

//    @Autowired
//    private ReplyBackEmail replyBackEmail;

    @Override
    public ContactResponse sendMail(ContactRequest contactRequest) throws Exception {
        log.debug("Request: {}", contactRequest);
        ContactResponseDTO contactResponseDTO = notificationEmail.sendNotificationMail(contactRequest.getEmail(), contactRequest.getName(), contactRequest.getMessage());
//        replyBackEmail.sendReplyBackEmail();
        return null;
    }
}
