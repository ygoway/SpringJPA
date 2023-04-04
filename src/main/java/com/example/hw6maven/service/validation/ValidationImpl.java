package com.example.hw6maven.service.validation;

import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.example.hw6maven.service.validation.ValidationConstant.EMAIL_VALIDATION;
import static com.example.hw6maven.service.validation.ValidationConstant.ONLY_NUMERIC;

@Component
public class ValidationImpl {

    public void emailValidation (String email) {
        Scanner scanner = new Scanner(System.in);
        while(!email.matches(EMAIL_VALIDATION)) {
            System.out.println("Invalid user email, pls try again");
            email = scanner.nextLine();
        }
    }

    public Long idValidation (String textID) {
        Scanner scanner = new Scanner(System.in);
        Long id;
        while (!textID.matches(ONLY_NUMERIC)) {
            System.out.println("Invalid ID format, try again!");
            textID = scanner.nextLine();
        }
        id = Long.parseLong(textID);
        return id;
    }
}
