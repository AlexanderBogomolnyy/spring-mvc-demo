package ua.kiev.allexb.mvc.services;

import java.util.Map;


public interface EmailService {

    boolean sendEmail(final String templateName, final Map<String, Object> model);

}