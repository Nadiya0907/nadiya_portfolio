package com.nadz.portfolio.controller;

import com.nadz.portfolio.dto.*;
import com.nadz.portfolio.service.ContactService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ContactResponse> submit(@Valid @RequestBody ContactRequest req,
                                                  HttpServletRequest http) {
        String ip = http.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank()) ip = http.getRemoteAddr();
        Long id = service.save(req, ip, http.getHeader("User-Agent"));
        return ResponseEntity.ok(new ContactResponse(true,
                "Message received. I'll get back to you within 24 hours.", id));
    }
}
