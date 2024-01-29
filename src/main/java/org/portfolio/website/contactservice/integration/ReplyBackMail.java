package org.portfolio.website.contactservice.integration;

import org.portfolio.website.contactservice.integration.dto.EmailDTO;

public interface ReplyBackMail {
    Boolean sendReplyBackMail(EmailDTO replyBackEmail);
}
