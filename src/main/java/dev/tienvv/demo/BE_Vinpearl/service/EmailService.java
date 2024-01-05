package dev.tienvv.demo.BE_Vinpearl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        emailSender.send(message);
    }

    public void sendHtmlEmail(String to, String subject, String body) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(subject);
        helper.setTo(to);

        String content = "<b>Dear guru</b>,<br><i>Please look at this nice picture:.</i>"
                + "<br><img src='https://localhost:8443/images/email.jpg'/><br><b>Best Regards</b>";
        helper.setText(content, true);

        emailSender.send(message);
    }
}
