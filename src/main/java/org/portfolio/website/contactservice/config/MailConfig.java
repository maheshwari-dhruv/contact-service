package org.portfolio.website.contactservice.config;

import lombok.extern.slf4j.Slf4j;
import org.portfolio.website.contactservice.domain.enums.HttpCodes;
import org.portfolio.website.contactservice.domain.exception.MailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@ComponentScan(basePackages = { "org.portfolio.website.contactservice.integration" })
@PropertySource(value={"classpath:application.properties"})
@Configuration
@Slf4j
public class MailConfig {
    @Value("${spring.mail.host}")
    private String mailServerHost;

    @Value("${spring.mail.port}")
    private Integer mailServerPort;

    @Value("${spring.mail.username}")
    private String mailServerUsername;

    @Value("${spring.mail.password}")
    private String mailServerPassword;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailServerAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailServerStartTls;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        try {
            mailSender.setHost(mailServerHost);
            mailSender.setPort(mailServerPort);
            mailSender.setUsername(mailServerUsername);
            mailSender.setPassword(mailServerPassword);

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", mailServerAuth);
            props.put("mail.smtp.starttls.enable", mailServerStartTls);
            props.put("mail.debug", "false");

        } catch (MailAuthenticationException | MailSendException e) {
            log.error("Mail Connection Error: {}", e.getMessage());
            throw new MailException(e.getMessage(), HttpCodes.INTERNAL_SERVER_ERROR);
        }

        return mailSender;
    }
}
