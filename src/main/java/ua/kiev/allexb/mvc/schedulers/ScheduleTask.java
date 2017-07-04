package ua.kiev.allexb.mvc.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);

    @Scheduled(fixedDelay = 10000)
    public void fixedDelaySchedule() {
        LOGGER.info("fixedDelaySchedule every 10 seconds" + new Date());
    }

    //every 30 seconds (seconds, minutes, hours, day of month, month, day of week, year(optional))
    @Scheduled(cron = "0/30 * * * * ?")
    public void cronSchedule() {
        LOGGER.info("cronSchedule every 30 seconds" + new Date());
    }

}
