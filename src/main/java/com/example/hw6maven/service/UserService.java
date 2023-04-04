package com.example.hw6maven.service;

import com.example.hw6maven.entity.User;
import com.example.hw6maven.repository.UserRepository;
import com.example.hw6maven.service.validation.ValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ValidationImpl validation;

    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name");
        String fistName = scanner.nextLine();
        System.out.println("Enter your lastname");
        String lastName = scanner.nextLine();
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        validation.emailValidation(email);
        System.out.println("Enter your age");
        int age = scanner.nextInt();

        List<User> userEmail = userRepository.getByEmail(email);
        if (!userEmail.isEmpty()) {
            System.out.println("User with email " + email + " already exist!");
        } else {
            User user = userRepository.upsert(new User(fistName, lastName, email, age));
            System.out.println("User : " + user + " created");
        }
    }

    public void getAllUsers() {
        System.out.println(userRepository.getAllUsers());
    }

    public void getUserByEmail() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email");
        String email = scanner.nextLine();
        validation.emailValidation(email);
        System.out.println(userRepository.getByEmail(email));
    }

    public void updateUserById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your ID");
        String textID = scanner.nextLine();
        System.out.println("Change ur name");
        String fistName = scanner.nextLine();
        System.out.println("Change ur lastname");
        String lastName = scanner.nextLine();
        System.out.println("Change ur email");
        String email = scanner.nextLine();
        validation.emailValidation(email);
        System.out.println("Change ur age");
        int age = scanner.nextInt();

        List<User> userEmail = userRepository.getByEmail(email);
        if (!userEmail.isEmpty()) {
            System.out.println("User with email " + email + " already exist!");
        } else {
            User user = userRepository.updateUserById(validation.idValidation(textID), fistName, lastName, email, age);
            System.out.println("Successful changes : " + user);
        }
    }

    public void deleteUserById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your ID");
        String textID = scanner.nextLine();
        userRepository.removeUserByID(validation.idValidation(textID));
    }

    public void getUserById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your ID");
        String textID = scanner.nextLine();
        System.out.println(userRepository.getUserById(validation.idValidation(textID)));
    }
}
