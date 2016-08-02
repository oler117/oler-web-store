package com.webstore.common.service.mailservice;

/**
 * Created by oler117 on 30.07.2016.
 */
public interface MailService {

    String GMAIL_SMTP_HOST = "smtp.gmail.com";

    void sendEmailViaGmailSMTP(String gmailUsernameEmail, String gmailPassword,
                               String adresatEamil, String subject, String body);

}
