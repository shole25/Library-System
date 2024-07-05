import java.util.*;

public class UserSystem {
    private ArrayList<User> users;

    public UserSystem(ArrayList<User> users) {
        this.users = users;
    }


    public String signUp() {
        Scanner sr = new Scanner(System.in);


        String userID = UUID.randomUUID().toString();

        System.out.println("Enter name:");
        String name = sr.nextLine();
        System.out.println("Enter password:");
        String password = sr.nextLine();
        System.out.println("Enter address:");
        String address = sr.nextLine();
        System.out.println("Enter email:");
        String email = sr.nextLine();
        boolean isContinue = true;
        while (isContinue) {
            String Email = sr.nextLine();
            if (email.contains("@")) {

                User.setEmail(email);
                isContinue = false;

            } else {
                System.err.println("Must be simvol @");
            }
        }
        System.out.println("Enter phone number:");
        int phone = sr.nextInt();


        UserRole userRole = null;
        while (userRole == null) {
            System.out.println("Enter user role (LIBRARIAN, MEMBER):");
            String roleInput = sr.nextLine().toUpperCase();
            System.out.println("You entered: " + roleInput);
            if (roleInput.equals("LIBRARIAN")) {
                userRole = UserRole.LIBRARIAN;
            } else if (roleInput.equals("MEMBER")) {
                userRole = UserRole.MEMBER;
            } else {
                System.out.println("Invalid role. please enter LIBRARIAN or MEMBER.");
            }
        }


        User newUser = new User(userID, name, address, phone, email, password, userRole);
        users.add(newUser);
        System.out.println("User registered successfully User details:");
        System.out.println(newUser);
        return userID;
    }


    public boolean login() {
        Scanner sr = new Scanner(System.in);
        System.out.println("Enter email:");
        String email = sr.nextLine();
        System.out.println("Enter password:");
        String password = sr.nextLine();

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Login successful");
                return true;
            }
        }
        System.out.println("Invalid email or password!");
        return false;
    }


    public void addUser() {
        signUp();
    }


    public void deleteUser() {
        Scanner sr = new Scanner(System.in);
        System.out.println("Enter userID for delete:");
        String userID = sr.nextLine();

        User userToRemove = null;
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                userToRemove = user;
                break;
            }
        }

        if (userToRemove != null) {
            users.remove(userToRemove);
            System.out.println("User deleted successfully");
        } else {
            System.out.println("User not found");
        }
    }


    public void updateUser() {
        Scanner sr = new Scanner(System.in);
        System.out.println("Enter userID for update:");
        String userID = sr.nextLine();

        User userToUpdate = null;
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                userToUpdate = user;
                break;
            }
        }

        if (userToUpdate != null) {
            System.out.println("Enter new name:");
            userToUpdate.setName(sr.nextLine());
            System.out.println("Enter new address:");
            userToUpdate.setAddress(sr.nextLine());
            System.out.println("Enter new phone number:");
            userToUpdate.setPhone(sr.nextInt());
            System.out.println("Enter new email:");
            userToUpdate.setEmail(sr.nextLine());
            System.out.println("Enter new password:");
            userToUpdate.setPassword(sr.nextLine());
            System.out.println("User updated successfully");
        } else {
            System.out.println("User not found");
        }
    }


    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1.Sign Up");
            System.out.println("2.Login");
            System.out.println("3.Add user");
            System.out.println("4.Delete user");
            System.out.println("5.Update user");
            System.out.println("6.Search user by name");
            System.out.println("7.Search user by id");
            System.out.println("8.Sort users by name");
            System.out.println("0.Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    signUp();
                    break;
                case 2:
                    if (login()) {
                        System.out.println("Accessing system functionalities");
                    }
                    break;
                case 3:
                    addUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    updateUser();
                    break;
                case 6:
                 searchUsersByName(String.valueOf(users));
                    break;
                case 7:
                   searchUserByID();
                    break;
                case 8:
                    sortUsersByName();
                    break;
                case 0:
                    System.out.println("Exiting program");
                    return;
                default:
                    System.out.println("Invalid choice, Please try again.");
                    break;
            }
        }
    }

    public User searchUsersByName(String name) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                result.add(user);
            }
        }

        return null;
    }


    public User searchUserByID() {
        for (User user : users) {
            if (user.getUserID().equals(users)) {
                return user;
            }
        }
        return null;
    }

    public void sortUsersByName() {
        users.sort((u1, u2) -> u1.getName().compareToIgnoreCase(u2.getName()));


    }

}
