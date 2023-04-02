package com.example.hw6maven.repository;

import com.example.hw6maven.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public User upsert(User user) {
        return entityManager.merge(user);
    }

    @Transactional
    public List<User> upsertAllUsers() {
        List<User> allUsers = Arrays.asList(
                new User(1L, "Petro", "Haishnuk", "haishnuk@gmail.com", 20),
                new User(2L, "Valya", "Oprushko", "oprushko@gmail.com", 22),
                new User(3L, "Stepan", "Stypka", "stypka@gmail.com", 33)
        );

        List<User> mergedUsers = new ArrayList<>();
        for (User user : allUsers) {                     // if use streams need
            mergedUsers.add(entityManager.merge(user)); // config parallel streams so as not to reduce productivity
        }
        return mergedUsers;
    }

    @Transactional
    public void removeUserByID(Long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            System.out.println("User not exist");
        } else {
            entityManager.remove(user);
            System.out.println("User " + id + " has deleted");
        }
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }


    public List<User> getByEmail(String email) {
        Query query = entityManager.createQuery("FROM User WHERE email=:email");
        query.setParameter("email", email);
        return query.getResultList();
    }

    @Transactional
    public User updateUserById (Long id, String firstName, String lastName, String email, int age) {
        User user = entityManager.find(User.class, id);
        if(user == null) {
            System.out.println("User not exist");
            return null;
        } else {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setAge(age);
            return entityManager.merge(user);
        }
    }
}
