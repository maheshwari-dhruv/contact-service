package org.portfolio.website.contactservice.integration.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDTO {
    private String from;
    private String to;
    private String subject;
    private String body;
}
