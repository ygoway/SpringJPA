package com.example.hw6maven.service;

import com.example.hw6maven.entity.User;
import com.example.hw6maven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import static com.example.hw6maven.service.validation.Validations.EMAIL_VALIDATION;
import static com.example.hw6maven.service.validation.Validations.ONLY_NUMERIC;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name");
        String fistName = scanner.nextLine();
        System.out.println("Enter your lastname");
        String lastName = scanner.nextLine();
        System.out.println("Enter your email");
        String email;
        do {
            email = scanner.nextLine();
            if(!email.matches(EMAIL_VALIDATION)) {
                System.out.println("Invalid user email, pls try again");
            }
        } while (!email.matches(EMAIL_VALIDATION));
        System.out.println("Enter your age");
        int age = scanner.nextInt();

        List<User> userEmail = userRepository.getByEmail(email);
        if(!userEmail.isEmpty()){
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
        String email;
        do {
            email = scanner.nextLine();
            if(!email.matches(EMAIL_VALIDATION)) {
                System.out.println("Invalid user email, pls try again");
            }
        } while (!email.matches(EMAIL_VALIDATION));
        System.out.println(userRepository.getByEmail(email));
    }

    public void updateUserById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your ID");
        Long id;
        String stringID;
        do {
            stringID = scanner.nextLine();
            id = Long.parseLong(stringID);
            if(!stringID.matches(ONLY_NUMERIC)){
                System.out.println("Invalid ID format, try again!");
            }
        }while (!stringID.matches(ONLY_NUMERIC));
        System.out.println("Change ur name");
        String fistName = scanner.nextLine();
        System.out.println("Change ur lastname");
        String lastName = scanner.nextLine();
        System.out.println("Change ur email");
        String email;
        do {
            email = scanner.nextLine();
            if(!email.matches(EMAIL_VALIDATION)) {
                System.out.println("Invalid user email, pls try again");
            }
        } while (!email.matches(EMAIL_VALIDATION));
        System.out.println("Change ur age");
        int age = scanner.nextInt();

        List<User> userEmail = userRepository.getByEmail(email);
        if(!userEmail.isEmpty()) {
            System.out.println("User with email " + email + " already exist!");
        } else {
            User user = userRepository.updateUserById(id, fistName, lastName, email, age);
            System.out.println("Successful changes : " + user);
        }
    }
}
