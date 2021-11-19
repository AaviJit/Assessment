package com.avijit.assessment.util;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */

public abstract class BaseService {

    @Autowired
    private  MessageByLocaleService messageByLocaleService;

    protected String getMessage(String id) {
        return messageByLocaleService.getMessage(id);
    }

}