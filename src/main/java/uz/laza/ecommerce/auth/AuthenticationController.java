package uz.laza.ecommerce.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.laza.ecommerce.exception.ItemNotFoundException;
import uz.laza.ecommerce.exception.UserAlreadyExistsException;
import uz.laza.ecommerce.main.token.Token;
import uz.laza.ecommerce.main.token.TokenRepository;
import uz.laza.ecommerce.main.user.UserService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final TokenRepository tokenRepository;
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request,
            final HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(service.register(request, httpServletRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @GetMapping("/verify-email/{token}")
    public String verifyEmail(@PathVariable String token) {
        Optional<Token> optional = tokenRepository.findByToken(token);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("Token not found !");
        } else if (optional.get().getUser().isEnabled()) {
            return "This account has already been verified, please, login.";
        }

        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("Success !")) {
            return "Email verified successfully. Now you can login to your account";
        }
        return "Invalid verification token";
    }
}
