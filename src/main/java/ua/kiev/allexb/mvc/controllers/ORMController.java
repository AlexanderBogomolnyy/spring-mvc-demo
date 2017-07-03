package ua.kiev.allexb.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.kiev.allexb.mvc.model.User;
import ua.kiev.allexb.mvc.services.ORMService;

import javax.persistence.PersistenceException;
import java.util.List;

@Controller
public class ORMController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ORMController.class);

    @Autowired
    private ORMService ormService;

    @RequestMapping(value = "/ormFindAllUsers", method= RequestMethod.GET)
    public ModelAndView ormFindAllUsers() {
        LOGGER.info("ORMController ormFindAllUsers is called");
        List<User> users = ormService.findAllUsersJPA();
        return new ModelAndView("/orm/orm", "resultObject", users);
    }

    @RequestMapping(value = "/queryFindByIdUser/{userid}", method = RequestMethod.GET)
    public ModelAndView queryFindByIdUser(@PathVariable("userid") int userid) {
        LOGGER.info("ORMController queryFindByIdUser is called");
        User user = ormService.findUserById(userid);
        return new ModelAndView("/orm/orm", "resultObject", user);
    }

    @RequestMapping(value = "/ormUpdateUser/iduser/{iduser}/enabled/{enabled}", method=RequestMethod.GET)
    public ModelAndView ormUpdateUser(
            @PathVariable(value="iduser") int iduser,
            @PathVariable(value="enabled") boolean enabled) {
        LOGGER.info("ORMController ormUpdateUser is called");
        boolean result = ormService.updateUser(iduser, enabled);
        return new ModelAndView("/orm/orm", "resultObject", result);
    }
    @RequestMapping(value = "/ormDeleteUser/iduser/{iduser}", method=RequestMethod.GET)
    public ModelAndView ormDeleteUser(@PathVariable(value="iduser") int iduser) {
        LOGGER.info("ORMController jdbcDelete is called");
        boolean result = ormService.deleteUser(iduser);
        return new ModelAndView("/orm/orm", "resultObject", result);
    }

    @RequestMapping(value = "/ormInsertUser/username/{username}/password/{password}/enabled/{enabled}"
            , method=RequestMethod.GET)
    public ModelAndView ormInsertUser(
            @PathVariable(value="username") String username,
            @PathVariable(value="password") String password,
            @PathVariable(value="enabled") boolean enabled) {
        LOGGER.info("ORMController ormInsertUser is called");
        boolean result = false;
        try {
            result = ormService.insertUser(username, password, enabled);
        } catch (PersistenceException ex) {
            LOGGER.error("An user with the same parameters is already exists.", ex);
        }
        return new ModelAndView("/orm/orm", "resultObject", result);
    }

}
