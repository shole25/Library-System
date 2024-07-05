import java.util.ArrayList;
import java.util.List;

public class User {
    private String userID;


    private String name;
    private String address;
    private int phone;
    private String email;
    private String password;
    private UserRole userRole;


    public User(String userID, String name) {
        this.userID = userID;
        this.name = name;

    }


    public User(String userID, String name, String address, int phone, String email, String password, UserRole userRole) {
        this.userID = userID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public  static void setEmail(String email) {
       // this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                '}';
    }


}
