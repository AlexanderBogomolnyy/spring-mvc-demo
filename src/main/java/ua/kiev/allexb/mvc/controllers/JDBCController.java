package ua.kiev.allexb.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.kiev.allexb.mvc.model.DBLog;
import ua.kiev.allexb.mvc.model.JDBCExample;
import ua.kiev.allexb.mvc.model.User;

import java.util.List;

@Controller
public class JDBCController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCController.class);

    @Autowired
    private JDBCExample jdbcExample;

    @RequestMapping(value = "/jdbcQueryAllUsers", method = RequestMethod.GET)
    public ModelAndView jdbcSelectAllUsers() {
        LOGGER.info("JDBCController jdbcSelectAllUsers() is called");
        List<User> users =  jdbcExample.queryAllUsers();
        return new ModelAndView("/jdbc/jdbc", "resultObject", users);
    }

    @RequestMapping(value = "/jdbcInsert/logstring/{logstring}", method=RequestMethod.GET)
    public ModelAndView jdbcInsert(@PathVariable(value="logstring") String logstring) {
        LOGGER.info("JDBCController jdbcInsert is called");
        DBLog dblog = new DBLog();
        dblog.setLOGSTRING(logstring);
        boolean result = jdbcExample.insertLog(dblog);
        return new ModelAndView("/jdbc/jdbc", "resultObject", result);
    }

    @RequestMapping(value = "/jdbcSelectLogs", method=RequestMethod.GET)
    public ModelAndView jdbcSelect() {
        LOGGER.info("JDBCController jdbcSelect is called");
        List<DBLog> dbLogs =  jdbcExample.queryAllLogs();
        return new ModelAndView("/jdbc/jdbc", "resultObject", dbLogs);

    }

    @RequestMapping(value = "/jdbcDelete/user/{iduser}", method=RequestMethod.GET)
    public ModelAndView jdbcDelete( @PathVariable(value="iduser") int iduser) {
        LOGGER.info("JDBCController jdbcDelete is called");
        boolean result = jdbcExample.deleteUSER(iduser);
        return new ModelAndView("/jdbc/jdbc", "resultObject", result);

    }

    @RequestMapping(value = "/jdbcUpdate/user/username/{username}/enabled/{enabled}", method=RequestMethod.GET)
    public ModelAndView jdbcUpdate(@PathVariable(value="username") String username, @PathVariable(value="enabled") boolean enabled) {
        LOGGER.info("JDBCController jdbcUpdate is called");
        User user = new User();
        user.setUsername(username);
        boolean result = jdbcExample.updateUserEnable(user, enabled);
        return new ModelAndView("/jdbc/jdbc", "resultObject", result);
    }

}
