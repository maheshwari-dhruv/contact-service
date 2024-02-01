package org.portfolio.website.contactservice.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.portfolio.website.contactservice.domain.dto.base.BaseRequest;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class ContactRequest extends BaseRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Message cannot be blank")
    private String message;
}
