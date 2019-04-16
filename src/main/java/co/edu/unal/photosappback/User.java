package co.edu.unal.photosappback;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String profileDescription;
    private String pass;
    private String email;

    public User() {  }

    public User(int id, String userName, String firstName, String lastName, String profileDescription, String pass, String email) {
        this.setId(id);
        this.setUserName(userName);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setProfileDescription(profileDescription);
        this.setPass(pass);
        this.setEmail(email);
    }

    public User(String userName, String firstName, String lastName, String profileDescription, String pass, String email) {
        this.setUserName(userName);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setProfileDescription(profileDescription);
        this.setPass(pass);
        this.setEmail(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
