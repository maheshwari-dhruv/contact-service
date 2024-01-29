package org.portfolio.website.contactservice.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.portfolio.website.contactservice.domain.dto.base.GenericResponse;
import org.portfolio.website.contactservice.domain.dto.request.ContactRequest;
import org.portfolio.website.contactservice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/sendmail")
    public ResponseEntity<GenericResponse<?>> sendNotificationEmail(@RequestBody ContactRequest contactRequest) {
        return new ResponseEntity<>(GenericResponse.success(contactService.sendMail(contactRequest)), HttpStatus.OK);
    }
}
