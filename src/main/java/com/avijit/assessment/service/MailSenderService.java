package com.avijit.assessment.service;

import com.avijit.assessment.schema.Book;
import com.avijit.assessment.schema.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৮/১১/২১
 */
@Service
public class MailSenderService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine htmlTemplateEngine;

    public MailSenderService(JavaMailSender javaMailSender, TemplateEngine htmlTemplateEngine) {
        this.javaMailSender = javaMailSender;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendBookIssueMail(Book book, User user) throws MessagingException {
        final Context ctx = new Context(new Locale("en"));
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Notification for book issue.");
        message.setFrom(fromEmail);
        message.setTo(user.getEmail());
        final String htmlContent = this.htmlTemplateEngine.process("PromotionMail.html", ctx);
        message.setText(htmlContent, true /* isHtml */);
        this.javaMailSender.send(mimeMessage);
    }
}
