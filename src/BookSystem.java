import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class BookSystem {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private ArrayList<Transaction> transactions;
    private User logInUser;
    List<Book> result;


    // private List<String> favoriteBooks;
    public User getLogInUser() {
        return logInUser;
    }

    public void setLogInUser(User logInUser) {
        this.logInUser = logInUser;
    }


    public BookSystem(ArrayList<Book> books, ArrayList<User> users) {
        this.books = books;
        this.users = users;
        this.transactions = new ArrayList<>();
        this.books = new ArrayList<>();
    }




    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter author:");
        String author = scanner.nextLine();
        System.out.println("Enter genre FICTION, NONFICTION, SCIENCE, ART:");
        BookGenre genre = null;
        while (genre == null) {
            try {
                genre = BookGenre.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid genre.Enter FICTION, NONFICTION, SCIENCE, or ART.");
            }
        }
        System.out.println("Enter publication date:");
        String publicationDate = scanner.nextLine();

        Book newBook = new Book(title, author, genre, publicationDate);

        books.add(newBook);
        System.out.println("Book added successfully,Book details:");
        System.out.println(newBook);
    }


    public void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter bookID to delete:");
        String bookID = scanner.nextLine();

        boolean found = false;
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                books.remove(book);
                System.out.println("Book deleted successfully");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found");
        }
    }


    public void updateBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter bookID to update:");
        String bookID = scanner.nextLine();

        boolean found = false;
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                System.out.println("Enter new title:");
                book.setTitle(scanner.nextLine());
                System.out.println("Enter new author:");
                book.setAuthor(scanner.nextLine());
                System.out.println("Enter new genre FICTION, NONFICTION, SCIENCE, ART:");
                BookGenre genre = null;
                while (genre == null) {
                    try {
                        genre = BookGenre.valueOf(scanner.nextLine().toUpperCase());
                        book.setGenre(genre);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid genre. Enter FICTION, NONFICTION, SCIENCE, or ART.");
                    }
                }
                System.out.println("Enter new publication date:");
                book.setPublicationDate(scanner.nextLine());
                System.out.println("Book updated successfully");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found");
        }
    }


    public void borrowBook() throws BookNotAvailableException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter userID:");
        String userID = scanner.nextLine();
        System.out.println("Enter bookID to borrow:");
        String bookID = scanner.nextLine();

        boolean bookFound = false;
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {

                if (book != null && book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("Book with ID " + bookID + " borrowed by userID " + userID);
                } else {

                    throw new BookNotAvailableException("Book with Id " + bookID + " is not available for borrowing.");
                }
            }
        }
        if (!bookFound) {
            System.out.println("Book not found");
            return;
        }

        boolean userFound = false;
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                Transaction transaction = new Transaction(userID, bookID, TransactionType.BORROW);
                transactions.add(transaction);
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            System.out.println("User not found");
        } else {
            System.out.println("Book borrowed successfully");
        }
    }


    public void returnBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter bookID to return:");
        String bookID = scanner.nextLine();

        boolean found = false;
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                book.setAvailable(true);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found");
            return;
        }

        for (Transaction transaction : transactions) {
            if (transaction.getBookID().equals(bookID) && transaction.getReturnDate() == null) {
                Object date = transaction.setReturnDate;
                System.out.println("Book returned successfully");
                return;
            }
        }
        System.out.println("Transaction not found");
    }



    public ArrayList<Book> searchBooksByTitle(String title) {
        ArrayList<Book> result = new ArrayList<>();
        if (title == null) {
            return result;
        }
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;


    }


    public List<Book> searchBooksByGenre(BookGenre genre) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre() == genre) {
                result.add(book);
            }
        }
        for (Book book : result) {
            System.out.println(book);
        }
        return result;
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }


    public void sortBooksByTitle() {
        books.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));

    }

    public ArrayList<Book> getAllBorrowedBooks() {
        ArrayList<Book> borrowedBooks = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType() == TransactionType.BORROW) {
                Book borrowedBook = findBookById(transaction.getBookID());
                if (borrowedBook != null) {
                    borrowedBooks.add(borrowedBook);
                }
            }
        }
        return borrowedBooks;
    }

    private Book findBookById(String bookID) {
        return null;
    }

    public ArrayList<Book> getOverdueBooks() throws BookNotAvailableException {
        ArrayList<Book> overdueBooks = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType() == TransactionType.BORROW) {
                Book borrowedBook = findBookById(transaction.getBookID());
                if (borrowedBook != null) {
                    LocalDateTime returnDate = transaction.getReturnDate();
                    long overduePeriodInDays = 0;
                    if (returnDate.plusDays(overduePeriodInDays).isBefore(now)) {
                        overdueBooks.add(borrowedBook);
                    }
                }
            }
        }
        return overdueBooks;


    }

    public void bookMenu() throws BookNotAvailableException
    {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Book Menu:");
            System.out.println("1.Add book");
            System.out.println("2.Delete book");
            System.out.println("3.Update book");
            System.out.println("4.Borrow book");
            System.out.println("5.Return book");
            System.out.println("6.Search book by genre");
            System.out.println("7.Sort books by title");
            System.out.println("8.Display transaction history");
            System.out.println("9.Show all borrowed books");
            System.out.println("10.Show overdue books");
            System.out.println("11.Search book by title");
            System.out.println("12.Search book by author");
            System.out.println("0.Back to main menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    System.out.print("Enter genre FICTION, NONFICTION, SCIENCE, ART: ");
                    String genreInput = scanner.nextLine().toUpperCase();
                    try {
                        BookGenre genre = BookGenre.valueOf(genreInput);
                        ArrayList<Book> booksByGenre = (ArrayList<Book>) searchBooksByGenre(genre);
                        if (!booksByGenre.isEmpty()) {
                            for (Book b : booksByGenre) {
                                System.out.println(b);
                            }
                        } else {
                            System.out.println("No books found for this genre");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid genre entered");
                    }
                    break;
                case 7:
                    sortBooksByTitle();
                case 8:
                    displayTransactionHistory();
                    break;
                case 9:
                    ArrayList<Book> borrowedBooks = getAllBorrowedBooks();
                    System.out.println("All borrowed books:");
                    for (Book book : borrowedBooks) {
                        System.out.println(book);
                    }
                    break;
                case 10:
                    ArrayList<Book> overdueBook = getOverdueBooks();
                    System.out.println("Overdue books:");
                    for (Book book : overdueBook) {
                        System.out.println(book);
                    }
                    break;
                case 11:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    ArrayList<Book> booksByTitle = searchBooksByTitle(title);
                    break;
                case 12:
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    ArrayList<Book> booksByAuthor = (ArrayList<Book>) searchBooksByAuthor(author);
                    break;
//                case 13:
//                    System.out.print("Enter Book ID to add to favorites: ");
//                    String bookID = scanner.nextLine();
//                    BookSystem.addFavoriteBook(bookID);
                case 0:
                    System.out.println("Returning to main menu");
                    break;
                default:
                    System.out.println("Invalid choice, please try again");
                    break;
            }
        }
    }
}

//    public void addFavoriteBook(String bookID) {
//        if (logInUser != null) {
//            logInUser.addFavoriteBook(bookID);
//            System.out.println("Book added to favorites!");
//        } else {
//            System.out.println("No user is currently log in.");
//        }
//    }
//    public void displayFavoriteBooks() {
//        System.out.println("Favorite books for user: " + books);
//        for (String bookID : favoriteBooks) {
//            System.out.println("->" + bookID);
//        }
//    }



