package uz.laza.ecommerce.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import uz.laza.ecommerce.main.user.User;

/**
 * @author Sampson Alfred
 */
@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;

    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
