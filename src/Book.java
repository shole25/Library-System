import java.util.UUID;

public class Book {

    public   String title;
    private String author;
    private BookGenre genre;
    private String publicationDate;
    private boolean isAvailable;
    String bookID = UUID.randomUUID().toString();
    public Book(String title, String author, BookGenre genre, String publicationDate) {
    }
    // private List<String> favoriteBooks;



//    public void addFavoriteBook(String bookID) {
//        favoriteBooks.add(bookID);
//    }
//    public ArrayList<String> getFavoriteBooks() {
//        return (ArrayList<String>) favoriteBooks;
//    }
    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID='" + bookID + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre=" + genre +
                ", publicationDate='" + publicationDate + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
