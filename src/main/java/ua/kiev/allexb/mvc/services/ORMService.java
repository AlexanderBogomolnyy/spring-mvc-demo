package ua.kiev.allexb.mvc.services;

import ua.kiev.allexb.mvc.model.User;

import java.util.List;

public interface ORMService {

    List<User> findAllUsersJPA();

    User findUserById(int id);

    boolean updateUser(int id, boolean enabled);

    boolean updateUser(User user);

    boolean insertUser(String username, String password, boolean enabled);

    boolean insertUser(User user);

    boolean deleteUser(int idUser);

}
