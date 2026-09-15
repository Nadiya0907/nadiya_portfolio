package com.nadz.portfolio.service;

import com.nadz.portfolio.dto.ContactRequest;
import com.nadz.portfolio.entity.ContactMessage;
import com.nadz.portfolio.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactMessageRepository repo;
    private final JavaMailSender mailSender;

    @Value("${portfolio.contact.recipient:}")
    private String recipientEmail;

    @Value("${spring.mail.username:}")
    private String fromEmail;

    public ContactService(ContactMessageRepository repo, JavaMailSender mailSender) {
        this.repo = repo;
        this.mailSender = mailSender;
    }

    public Long save(ContactRequest req, String ip, String ua) {
        ContactMessage m = new ContactMessage();
        m.setName(req.getName().trim());
        m.setEmail(req.getEmail().trim().toLowerCase());
        m.setSubject(req.getSubject() == null || req.getSubject().isBlank()
                ? "New project inquiry" : req.getSubject().trim());
        m.setMessage(req.getMessage().trim());
        m.setIpAddress(ip);
        m.setUserAgent(ua);
        ContactMessage saved = repo.save(m);
        sendEmail(req);
        return saved.getId();
    }

    private void sendEmail(ContactRequest req) {
        if (recipientEmail == null || recipientEmail.isBlank()
                || fromEmail == null || fromEmail.isBlank()) return;
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(fromEmail);
            mail.setTo(recipientEmail);
            mail.setReplyTo(req.getEmail());
            mail.setSubject("[Portfolio] " + (req.getSubject() == null ? "New message" : req.getSubject()));
            mail.setText("From: " + req.getName() + " <" + req.getEmail() + ">\n\n" + req.getMessage());
            mailSender.send(mail);
        } catch (Exception e) {
            System.err.println("Email send failed: " + e.getMessage());
        }
    }
}
