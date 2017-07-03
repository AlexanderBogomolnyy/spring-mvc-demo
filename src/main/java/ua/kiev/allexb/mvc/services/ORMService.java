package ua.kiev.allexb.mvc.services;

import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.allexb.mvc.model.User;

@Repository
@Transactional
public class ORMService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> queryFindAllUsersJPA() {
        System.out.println("ORMService queryFindAllUsersJPA is called");

        String query = "FROM User ORDER BY iduser";
        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        return typedQuery.getResultList();
    }

    public User queryFindUserById(int id) {
        System.out.println("ORMService queryFindUserById is called");

        return entityManager.find(User.class, id);
    }

    public boolean updateUser(int id, boolean enabled) {
        System.out.println("ORMService updateUser(id,enabled) is called");

        String query = "UPDATE user SET enabled = ? WHERE iduser = ?";
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter(1, enabled);
        nativeQuery.setParameter(2, id);
        int result = nativeQuery.executeUpdate();
        return result > 0; // result show how many rows was updated.
    }

    public boolean updateUser(User user) {
        System.out.println("ORMService updateUser(user) is called");

        String query = "UPDATE user SET username=:userName, password=:userPassword, enabled=:isEnabled " +
                "WHERE iduser=:idUser";
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter("userName", user.getUsername());
        nativeQuery.setParameter("userPassword", user.getPassword());
        nativeQuery.setParameter("isEnabled", user.isEnabled());
        nativeQuery.setParameter("idUser", user.getIdUser());

        System.out.println(user);

        int result = nativeQuery.executeUpdate();
        return result > 0; // result show how many rows was updated.
    }

    public boolean insertUser(String username, String password, boolean enabled) {
        System.out.println("ORMExample insertUser(username, password, enabled) is called");

        String qlString = "INSERT INTO user (username,password,enabled) VALUES (?,?,?)";
        Query query = entityManager.createNativeQuery(qlString);
        query.setParameter(1, username);
        query.setParameter(2, password);
        query.setParameter(3, enabled);
        int result = query.executeUpdate();
        return result > 0;
    }

    public boolean insertUser(User user) {
        System.out.println("ORMExample insertUser(user) is called");

        return this.insertUser(user.getUsername(), user.getPassword(), user.isEnabled());
    }

    public boolean deleteUser(int idUser) {
        System.out.println("ORMExample deleteUser is called");

        String qlString = "DELETE FROM user WHERE iduser=?";
        Query query = entityManager.createNativeQuery(qlString);
        query.setParameter(1, idUser);
        int result = query.executeUpdate();

        return result > 0;
    }

}
