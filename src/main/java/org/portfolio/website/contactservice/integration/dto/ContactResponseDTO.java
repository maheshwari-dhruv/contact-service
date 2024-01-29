package org.portfolio.website.contactservice.integration.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactResponseDTO {
    private String message;
    private LocalDateTime dateTime;
}
