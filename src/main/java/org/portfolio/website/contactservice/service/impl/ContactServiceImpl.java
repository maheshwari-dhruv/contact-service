package org.portfolio.website.contactservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.portfolio.website.contactservice.domain.dto.request.ContactRequest;
import org.portfolio.website.contactservice.domain.dto.response.ContactResponse;
import org.portfolio.website.contactservice.service.ContactService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {
    @Override
    public ContactResponse sendMail(ContactRequest contactRequest) {
        log.debug("Request: {}", contactRequest);
        return null;
    }
}
