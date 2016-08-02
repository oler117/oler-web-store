package com.webstore.common.service.auth;

import com.webstore.common.model.auth.User;
import com.webstore.common.model.auth.UserTempToken;
import com.webstore.common.model.auth.UserTempTokenType;
import com.webstore.common.repository.jpa.auth.UserRepository;
import com.webstore.common.repository.jpa.auth.UserTempTokenRepository;
import com.webstore.common.service.PropertyService;
import com.webstore.common.service.mailservice.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.UUID;

/**
 * Created by oler117 on 30.07.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTempTokenRepository userTempTokenRepository;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private MailService mailService;

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    @Transactional
    public void createNewUser(User newUser) {

        User user = userRepository.saveAndFlush(newUser);
        Integer newUserId = user.getId();

        // Creating temporary confirmation token
        final String confirmationToken = UUID.randomUUID().toString().replace("-", "");
        UserTempToken userTempToken = new UserTempToken(newUserId,
                UserTempTokenType.TOKEN_REGISTRATION_CONFIRM,
                confirmationToken);
        userTempTokenRepository.saveAndFlush(userTempToken);

        sendConfirmationEmail(newUserId, newUser.getUsername(), confirmationToken);
    }

    @Override
    @Transactional
    public boolean activateAccount(Integer userId, String token) {

        User user = userRepository.findOne(userId);
        if (!user.isActive()) {
            UserTempToken userTempToken = userTempTokenRepository.findByToken(token);
            if (userTempToken != null && userId.equals(userTempToken.getUserId())) {
                user.setActive(true);
                userTempTokenRepository.delete(userTempToken.getId());
                userRepository.saveAndFlush(user);
            } else {
                return false;
            }
        } else {
            //already activated
        }
        return true;
    }

    private void sendConfirmationEmail(Integer newUserId, String newUserEmail, String confirmationToken) {
        final String senderEmail = propertyService.getProperty("email.confirmation.senderemail");
        final String senderPsw = propertyService.getProperty("email.confirmation.senderpsw");
        final String subject = propertyService.getProperty("email.confirmation.subject");
        final String confirmationLink = "http://localhost:8080/user/confirm?"
                + "uid=" + newUserId + "&"
                + "key=" + confirmationToken;
        final String htmlBody = MessageFormat.format(
                propertyService.getProperty("email.confirmation.htmlbodytemplate"),
                confirmationLink
        );
        mailService.sendEmailViaGmailSMTP(senderEmail, senderPsw, newUserEmail,
                subject, htmlBody);
    }
}
