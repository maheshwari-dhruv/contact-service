package org.portfolio.website.contactservice.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.portfolio.website.contactservice.domain.dto.base.GenericResponse;
import org.portfolio.website.contactservice.domain.dto.request.ContactRequest;
import org.portfolio.website.contactservice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/sendmail")
    public ResponseEntity<GenericResponse<?>> sendContactEmail(@Valid @RequestBody ContactRequest contactRequest) {
        try {
            return new ResponseEntity<>(GenericResponse.success(contactService.sendMail(contactRequest)), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error sending mail: {}", e.getMessage());
            return new ResponseEntity<>(GenericResponse.error(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
