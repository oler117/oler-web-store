package com.webstore.common.service.mailservice;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * Created by oler117 on 30.07.2016.
 */
@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = Logger.getLogger(MailServiceImpl.class);

    private static final String EMAIL_SENT_SUCCESS_MSG = "Email with subject {0} to {1} sent successfully.";
    private static final String EMAIL_SENDING_ERROR_MSG = "Error sending email with subject {0} to {1}!";

    /**
     * ENABLE LESS SECURE APPS IN GMAIL ACC SETTINGS!!!
     */
    public void sendEmailViaGmailSMTP(String gmailUsernameEmail, String gmailPassword,
                                      String adresatEamil, String subject, String body) {

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", GMAIL_SMTP_HOST);
        props.put("mail.smtp.user", gmailUsernameEmail);
        props.put("mail.smtp.password", gmailPassword);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(gmailUsernameEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(adresatEamil));
            message.setSubject(subject);
            message.setContent(body, "text/html; charset=utf-8");

            Transport transport = session.getTransport("smtp");
            transport.connect(GMAIL_SMTP_HOST, gmailUsernameEmail, gmailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            logger.info(MessageFormat.format(EMAIL_SENT_SUCCESS_MSG, subject, adresatEamil));
        } catch (MessagingException e) {
            logger.error(MessageFormat.format(EMAIL_SENDING_ERROR_MSG, subject, adresatEamil));
            throw new RuntimeException(e);
        }
    }

}
