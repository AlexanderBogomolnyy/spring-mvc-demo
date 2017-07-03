package ua.kiev.allexb.mvc.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.allexb.mvc.model.User;
import ua.kiev.allexb.mvc.repository.UserRepository;
import ua.kiev.allexb.mvc.services.ORMService;

@Service
public class BaseORMService implements ORMService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseORMService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsersJPA() {
        LOGGER.info("ORMService findAllUsersJPA is called");
        return userRepository.getAll();
    }

    @Override
    public User findUserById(int id) {
        LOGGER.info("ORMService findUserById is called");
        return userRepository.getById(id);
    }

    @Override
    public boolean updateUser(int id, boolean enabled) {
        LOGGER.info("ORMService updateUser(id,enabled) is called");
        return userRepository.update(id, enabled);
    }

    @Override
    public boolean updateUser(User user) {
        LOGGER.info("ORMService updateUser(user) is called");
        return userRepository.update(user);
    }

    @Override
    public boolean insertUser(String username, String password, boolean enabled) {
        LOGGER.info("ORMExample insertUser(username, password, enabled) is called");
        return userRepository.insert(username, password, enabled);
    }

    @Override
    public boolean insertUser(User user) {
        LOGGER.info("ORMExample insertUser(user) is called");
        return userRepository.insert(user);
    }

    @Override
    public boolean deleteUser(int idUser) {
        LOGGER.info("ORMExample deleteUser is called");
        return userRepository.delete(idUser);
    }

}
