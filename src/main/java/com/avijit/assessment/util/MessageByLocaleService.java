package com.avijit.assessment.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;
/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */

@Service
public class MessageByLocaleService {


    private final MessageSource messageSource;

    public MessageByLocaleService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String id) {
        String message = "";
        Locale locale = LocaleContextHolder.getLocale();
        try {
            message = messageSource.getMessage(id, null, locale);
        } catch (Exception ignored) {

        }
        return message;
    }
}