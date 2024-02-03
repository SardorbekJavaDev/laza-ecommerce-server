package uz.laza.ecommerce.main.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.laza.ecommerce.config.JwtService;
import uz.laza.ecommerce.main.token.Token;
import uz.laza.ecommerce.main.token.TokenRepository;
import uz.laza.ecommerce.main.token.TokenType;

import java.security.Principal;
import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }

    public String saveUserVerificationToken(User user) {
        var verificationToken = jwtService.generateVerificationToken(user);
        var token = Token.builder()
                .user(user)
                .token(verificationToken)
                .tokenType(TokenType.VERIFICATION)
                .expired(false)
                .revoked(false)
                .build();
        Token save = tokenRepository.save(token);
        return save.getToken();
    }

    public String validateToken(String theToken) {
        Optional<Token> optional = tokenRepository.findByToken(theToken);
        if (optional.isEmpty()) {
            return "Invalid verification token";
        }
        User user = optional.get().getUser();
        user.setEnabled(true);
        repository.save(user);
        return "Success !";
    }
}
