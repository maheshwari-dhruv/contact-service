package org.portfolio.website.contactservice.domain.dto.response;

import lombok.*;
import org.portfolio.website.contactservice.domain.dto.base.BaseResponse;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactResponse extends BaseResponse {
    private String message;
    private LocalDateTime dateTime;
}
