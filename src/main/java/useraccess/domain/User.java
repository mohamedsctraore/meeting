package useraccess.domain;

public class User {
    private String nom;
    private String password;
    private UserRegistrationStatus registrationStatus;

    public User() {
    }

    public User(String nom, String password) {
        this.nom = nom;
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(UserRegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
