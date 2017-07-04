package ua.kiev.allexb.mvc.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kiev.allexb.mvc.model.DBLog;
import ua.kiev.allexb.mvc.model.JDBCExample;

import java.util.Calendar;
/**
 * look application-context.xml
 * 1. simpleTrigger
 * 2. simpleQuartzJob
 * 3. bean id="simpleQuartzTask"
 * 4. Quartz Scheduler
 */
public class QuartzTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzTask.class);

    private JDBCExample jdbcExample;

    public QuartzTask(JDBCExample jdbcExample) {
        this.jdbcExample = jdbcExample;
    }

    public void simpleTaskMethod() {
        String logString = "Current time from Quartz task: " + Calendar.getInstance().getTime();
        LOGGER.info("Test Simple Quartz Time: " + logString);
        DBLog dblog = new DBLog(logString);
        jdbcExample.insertLog(dblog);
        LOGGER.info("Test Simple Quartz Time: logging to database. " + dblog.getLogString());
    }

    public JDBCExample getJdbcExample() {
        return jdbcExample;
    }

    public void setJdbcExample(JDBCExample jdbcExample) {
        this.jdbcExample = jdbcExample;
    }
}