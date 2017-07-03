package ua.kiev.allexb.mvc.repository;

import ua.kiev.allexb.mvc.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAll();

    User getById(int id);

    boolean insert(String username, String password, boolean enabled);

    boolean insert(User user);

    boolean update(int id, boolean enabled);

    boolean update(User user);

    boolean delete(int id);
}
