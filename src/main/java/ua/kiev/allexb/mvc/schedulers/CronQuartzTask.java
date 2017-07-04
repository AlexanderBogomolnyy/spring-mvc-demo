package ua.kiev.allexb.mvc.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kiev.allexb.mvc.services.EmailService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * look application-context.xml
 * 1. cronTrigger
 * 2. quartzCronJob
 * 3. bean id="cronQuartzTask"
 * 4. Quartz Scheduler
 */
public class CronQuartzTask {

    private static final String NOTIFICATION_EMAIL = "alexander.bogomolnyy@gmail.com";
    private static final String NOTIFICATION = "Cron job executed at ";

    private static final Logger LOGGER = LoggerFactory.getLogger(CronQuartzTask.class);

    private EmailService emailService;

    public CronQuartzTask(EmailService emailService) {
        this.emailService = emailService;
    }

    public void cronTaskMethod() {
        String notificztion = NOTIFICATION + Calendar.getInstance().getTime();
        emailService.sendEmail("registered.vm", prepareEmailModel(notificztion));
        LOGGER.info("Cron notification sent by email: " + notificztion);
    }

    private Map<String, Object> prepareEmailModel(String message) {
        Map<String, Object> model = new HashMap<>();
        model.put("from", "demo.spring@mvc.app");
        model.put("subject", "Hello from Spring Crone Quartz Task!");
        model.put("to", NOTIFICATION_EMAIL);
        model.put("ccList", new ArrayList<>());
        model.put("bccList", new ArrayList<>());
        model.put("userName", "SpringUser");
        model.put("urljavastudy", "javastudy.ru");
        model.put("message", message);
        return model;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

}
