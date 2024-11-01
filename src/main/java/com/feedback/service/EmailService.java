package com.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.security.SecureRandom;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final SecureRandom random = new SecureRandom();

    // Method to generate a 4 or 5 digit verification code
    private String generateVerificationCode(int length) {
        int code = random.nextInt((int) Math.pow(10, length)); // Generate a number with the specified number of digits
        return String.format("%0" + length + "d", code); // Format it to ensure leading zeros are added if needed
    }

    public void sendVerificationEmail(String toEmail, String verificationCode) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            String subject = "Your Password Reset Verification Code";
            String htmlContent = "<p>Your verification code is: <strong>" + verificationCode + "</strong></p>";

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
