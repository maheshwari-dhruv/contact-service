package org.portfolio.website.contactservice.domain.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.portfolio.website.contactservice.domain.dto.base.BaseRequest;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class ContactRequest extends BaseRequest {
    private String name;
    private String email;
    private String message;
}
