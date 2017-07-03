package ua.kiev.allexb.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kiev.allexb.mvc.model.EntityList;
import ua.kiev.allexb.mvc.model.User;
import ua.kiev.allexb.mvc.services.ORMService;

import javax.persistence.PersistenceException;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RESTController.class);

    @Autowired
    private ORMService ormService;

    @RequestMapping(value = "/users",
            method= RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<EntityList<User>> restFindAllUsers() {
        LOGGER.info("REST controller: get all users");
        List<User> users = ormService.findAllUsersJPA();
        return new ResponseEntity<>(new EntityList<>(users), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userid}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> restFindByIdUser(@PathVariable("userid") int userid) {
        LOGGER.info("REST controller: find user by ID is called");
        User user = ormService.findUserById(userid);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{iduser}",
            method=RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> restUpdateUser(
            @PathVariable(value="iduser") int iduser,
            @RequestBody User user) {
        LOGGER.info("REST controller: user update is called");
        User currentUser = ormService.findUserById(iduser);
        if (currentUser == null) {
            System.out.println("User with id " + iduser + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentUser.setPassword(user.getPassword());
        currentUser.setEnabled(user.isEnabled());
        boolean result = ormService.updateUser(currentUser);
        if (result)
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/users/{iduser}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> restDeleteUser(@PathVariable(value="iduser") int iduser) {
        LOGGER.info("REST controller: delete user is called");
        boolean result = ormService.deleteUser(iduser);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/users",
            method=RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> restInsertUser(@RequestBody User user) {
        LOGGER.info("REST controller: insert user is called");
        try {
            boolean result = ormService.insertUser(user);
            if (result) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } catch (PersistenceException ex) {
            LOGGER.error("An user with the same parameters is already exists.", ex);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
