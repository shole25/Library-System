import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    public Object setReturnDate;
    private String transactionID;
    private String userID;
    private String bookID;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private TransactionType transactionType;

    public Transaction(String userID, String bookID, TransactionType transactionType) {
        this.transactionID = UUID.randomUUID().toString();
        this.userID = userID;
        this.bookID = bookID;
        this.transactionType = transactionType;
        this.borrowDate = LocalDateTime.now();
    }



    public String getBookID() {
        return bookID;
    }



    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }



    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID='" + transactionID + '\'' +
                ", userID='" + userID + '\'' +
                ", bookID='" + bookID + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", transactionType=" + transactionType +
                '}';
    }
}
