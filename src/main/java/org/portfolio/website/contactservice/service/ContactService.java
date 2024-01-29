package org.portfolio.website.contactservice.service;

import org.portfolio.website.contactservice.domain.dto.request.ContactRequest;
import org.portfolio.website.contactservice.domain.dto.response.ContactResponse;

public interface ContactService {
    ContactResponse sendMail(ContactRequest contactRequest);
}
