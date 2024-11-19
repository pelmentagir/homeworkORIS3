import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

public class MainRepository {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) throws Exception {

        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        UserRepository userRepository = new UsersRepositoryJdbcImpl(connection);

        Scanner scanner = new Scanner(System.in);

        System.out.println("How many users do you want to add?");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
        int numberOfUsers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numberOfUsers; i++) {
            System.out.println("Enter details for user " + i + ":");

            System.out.println("Enter first name:");
            String firstName = scanner.nextLine().trim();
            while (firstName.isEmpty()) {
                System.out.println("First name cannot be empty! Please enter again:");
                firstName = scanner.nextLine().trim();
            }

            System.out.println("Enter last name:");
            String lastName = scanner.nextLine().trim();
            while (lastName.isEmpty()) {
                System.out.println("Last name cannot be empty! Please enter again:");
                lastName = scanner.nextLine().trim();
            }

            System.out.println("Enter age:");
            int age = 0;
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid age.");
                scanner.next();
            }
            age = scanner.nextInt();
            scanner.nextLine();

            // Ввод города
            System.out.println("Enter city:");
            String city = scanner.nextLine().trim();
            while (city.isEmpty()) {
                System.out.println("City cannot be empty! Please enter again:");
                city = scanner.nextLine().trim();
            }

            System.out.println("Enter job title:");
            String jobTitle = scanner.nextLine().trim();
            while (jobTitle.isEmpty()) {
                System.out.println("Job title cannot be empty! Please enter again:");
                jobTitle = scanner.nextLine().trim();
            }

            System.out.println("Is the user active? (true/false):");
            boolean isActive = false;
            while (true) {
                String isActiveInput = scanner.nextLine().trim();
                if (isActiveInput.equalsIgnoreCase("true")) {
                    isActive = true;
                    break;
                } else if (isActiveInput.equalsIgnoreCase("false")) {
                    isActive = false;
                    break;
                } else {
                    System.out.println("Invalid input! Please enter 'true' or 'false'.");
                }
            }

            User user = new User(i, firstName, lastName, age, city, jobTitle, isActive);
            userRepository.save(user);
            System.out.println("User created: " + user.getFirstName() + " " + user.getLastName() + " with ID: " + user.getId());
        }

        System.out.println("Users added successfully!");

        System.out.println("\nUsers from Moscow:");
        List<User> usersFromMoscow = userRepository.findAllByCity("Moscow");
        usersFromMoscow.forEach(user -> System.out.println(user.getFirstName()));

        System.out.println("\nDevelopers:");
        List<User> developers = userRepository.findAllByJobTitle("Developer");
        developers.forEach(user -> System.out.println(user.getFirstName()));

        System.out.println("\nActive users:");
        List<User> activeUsers = userRepository.findAllByActiveStatus(true);
        activeUsers.forEach(user -> System.out.println(user.getFirstName()));
    }
}