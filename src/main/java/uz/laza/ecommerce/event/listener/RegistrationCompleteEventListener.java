package uz.laza.ecommerce.event.listener;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import uz.laza.ecommerce.event.RegistrationCompleteEvent;
import uz.laza.ecommerce.main.mail.EmailService;
import uz.laza.ecommerce.main.user.User;
import uz.laza.ecommerce.main.user.UserService;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author Sardorbek Yorqulov
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final UserService userService;
    private final EmailService emailService;

    private User theUser;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. Get the newly registered user
        theUser = event.getUser();
        //3. Save the verification token for the user
        String verificationToken = userService.saveUserVerificationToken(theUser);
        //4 Build the verification url to be sent to the user
        String url = event.getApplicationUrl() + verificationToken;
        //5. Send the email.
        try {
            emailService.sendVerificationEmail(url, theUser);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Click the link to verify your registration :  {}", url);
    }

}
