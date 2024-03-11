package uz.laza.ecommerce.main.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import uz.laza.ecommerce.exception.AppBadRequestException;
import uz.laza.ecommerce.exception.ItemNotFoundException;
import uz.laza.ecommerce.main.user.User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static uz.laza.ecommerce.main.mail.EmailType.VERIFICATION;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {


    private final JavaMailSender mailSender;


    private final EmailRepository emailRepository;

    public void send(String toEmail, String title, String content, EmailType type) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            message.setSubject(title);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setText(content, true);
            mailSender.send(message);

            Email entity = new Email();
            entity.setToEmail(toEmail);
            entity.setType(type);
            emailRepository.save(entity);
        } catch (MessagingException | MailException e) {
            log.error("Mail not send {}", e.getMessage());
            throw new AppBadRequestException(e.getMessage());
        }
    }

    public PageImpl<EmailResponse> paginationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<EmailResponse> dtoList = new ArrayList<>();
        Page<Email> entityPage = emailRepository.findAll(pageable);
        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public PageImpl<EmailResponse> special(EmailRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(Sort.Direction.DESC, "createdDate"));

        List<EmailResponse> dtoList = new ArrayList<>();
        Page<Email> entityPage = emailRepository.findByToEmailAndType(request.getToEmail(), request.getType(), pageable);
        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public Boolean delete(Integer id) {
        Email entity = emailRepository.findById(id).orElseThrow(() -> {
            log.warn("Not found {}", id);
            return new ItemNotFoundException("Not found!");
        });

        emailRepository.delete(entity);
        return true;
    }

    private EmailResponse toDTO(Email entity) {
        EmailResponse response = new EmailResponse();
        response.setId(entity.getId());
        response.setToEmail(entity.getToEmail());
        response.setType(entity.getType());
        response.setCreatedDate(entity.getCreatedDate());
        return response;
    }


//    ##################################### Email Templates ###########################################

    public void sendVerificationEmail(String url, User theUser) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p> Hi, " + theUser.getFirstname() + ", </p>" +
                "<p>Thank you for registering with us," + "" +
                "Please, follow the link below to complete your registration.</p>" +
                "<a href=\"" + url + "\">Verify your email to activate your account</a>" +
                "<p> Thank you <br> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("dailycodework@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
        Email entity = new Email();
        entity.setToEmail(theUser.getEmail());
        entity.setType(VERIFICATION);
        emailRepository.save(entity);
    }
}
