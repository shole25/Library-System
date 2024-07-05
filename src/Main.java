import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BookNotAvailableException {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("1", "shola", "Addres1", 123456, "shola@mail.com", "sh123", UserRole.LIBRARIAN));
        users.add(new User("2", "ozlam", "Addres2", 567893, "ozlam@mail.com", "oz123", UserRole.MEMBER));

        UserSystem userSystem = new UserSystem(users);
        ArrayList<Book> books = null;
        BookSystem bookSystem = new BookSystem(books, users);
        books = new ArrayList<>();
        books.add(new Book("Arting", "author1", BookGenre.ART, "11-02-2001"));
        books.add(new Book("Science", "author2", BookGenre.SCIENCE, "01-04-2007"));
        books.add(new Book("Fiction", "author3", BookGenre.FICTION, "15-05-2005"));
        books.add(new Book("NONFICTION", "author4", BookGenre.NONFICTION, "07-07-2012"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1.User system");
            System.out.println("2.Book system");
            System.out.println("0.Exit");
            System.out.print("Enter choice ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userSystem.mainMenu();
                    break;
                case 2:
                    bookSystem.bookMenu();
                    break;
                case 0:
                    System.out.println("Exiting program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }




}
//    Enter choice: 1
//        Enter name:
//        gunay
//        Enter password:
//        g123
//        Enter address:
//        baki
//        Enter email:
//        gun@mail.com
//
//Enter phone number:
//        123
//        Enter user role (LIBRARIAN, MEMBER):
//        You entered:
//        Invalid role. please enter LIBRARIAN or MEMBER.
//        Enter user role (LIBRARIAN, MEMBER):
//        Member
//        You entered: MEMBER
//        User registered successfully User details:
//        User{userID='4602fdfd-68c7-47de-ab22-7b15b8e243b6', name='gunay', address='baki', phone=123, email='gun@mail.com', userRole=MEMBER}
//
