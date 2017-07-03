package ua.kiev.allexb.mvc.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.allexb.mvc.model.User;
import ua.kiev.allexb.mvc.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class HibernateUserRepository implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUserRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        LOGGER.info("Get all users method is called");
        String query = "FROM User ORDER BY iduser";
        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        return typedQuery.getResultList();
    }

    @Override
    public User getById(int id) {
        LOGGER.info("Get user by ID method is called");
        return entityManager.find(User.class, id);
    }

    @Override
    public boolean insert(String username, String password, boolean enabled) {
        LOGGER.info("Create new user by username, password, enable status method is called");
        String qlString = "INSERT INTO user (username,password,enabled) VALUES (?,?,?)";
        Query query = entityManager.createNativeQuery(qlString);
        query.setParameter(1, username);
        query.setParameter(2, password);
        query.setParameter(3, enabled);
        int result = query.executeUpdate();
        return result > 0;
    }

    @Override
    public boolean insert(User user) {
        LOGGER.info("Create new user by User type method is called");
        return this.insert(user.getUsername(), user.getPassword(), user.isEnabled());
    }

    @Override
    public boolean update(int id, boolean enabled) {
        LOGGER.info("Update user enable status by ID method is called");
        String query = "UPDATE user SET enabled = ? WHERE iduser = ?";
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter(1, enabled);
        nativeQuery.setParameter(2, id);
        int result = nativeQuery.executeUpdate();
        return result > 0;
    }

    @Override
    public boolean update(User user) {
        LOGGER.info("Update user by User type method is called");
        String query = "UPDATE user SET username = :userName, password = :userPassword, enabled = :isEnabled " +
                "WHERE iduser = :idUser";
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter("userName", user.getUsername());
        nativeQuery.setParameter("userPassword", user.getPassword());
        nativeQuery.setParameter("isEnabled", user.isEnabled());
        nativeQuery.setParameter("idUser", user.getIdUser());
        int result = nativeQuery.executeUpdate();
        return result > 0;
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Delete user by ID method is called");
        String qlString = "DELETE FROM user WHERE iduser=?";
        Query query = entityManager.createNativeQuery(qlString);
        query.setParameter(1, id);
        int result = query.executeUpdate();
        return result > 0;
    }

}
