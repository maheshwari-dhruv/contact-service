package org.portfolio.website.contactservice.integration;

import org.portfolio.website.contactservice.integration.dto.ContactResponseDTO;


public interface NotificationEmail {
    ContactResponseDTO sendNotificationMail(String email, String name, String message) throws Exception;
}
