package com.example.hw6maven;

import com.example.hw6maven.repository.UserRepository;
import com.example.hw6maven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Hw6mavenApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Hw6mavenApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("____________________________________");
			System.out.println("Hello, enter the numeric for info");
			System.out.println("1 Create new user");
			System.out.println("2 To get all users");
			System.out.println("3 To get user by email");
			System.out.println("4 To update user by ID");
			System.out.println("0 Exit");
			System.out.println("____________________________________");

			int menu = scanner.nextInt();
			scanner.nextLine();

			switch(menu) {
				case 1: userService.createUser();
					break;
				case 2: userService.getAllUsers();
					break;
				case 3: userService.getUserByEmail();
					break;
				case 4: userService.updateUserById();
					break;
				case 0:
					System.out.println("bye");
					scanner.close();
					System.exit(0);
				default:
					System.out.println("Wrong number, try again!");
			}
		}
	}
}
