package co.edu.unal.photosappback.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String profileDescription;
    private String pass;
    private String email;

    public User() {  }

    public User(String userName, String firstName, String lastName, String profileDescription, String pass, String email) {
        this.setUserName(userName);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setProfileDescription(profileDescription);
        this.setPass(pass);
        this.setEmail(email);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user_name", nullable = false)
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "profile_description", nullable = false)
    public String getProfileDescription() {
        return profileDescription;
    }
    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Column(name = "pass", nullable = false)
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    @Column(name = "email", nullable = false)
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
