package useraccess;

import useraccess.domain.User;
import useraccess.domain.UserRegistrationStatus;

public class UserRegistration {

    public static User creerUtilisateur(String nom, String prenom) {
        User user = new User(nom, prenom);
        user.setRegistrationStatus(UserRegistrationStatus.WAITINGFORCONFIRMATION) ;
        return user;
    }

    public static User changerStatusEnregistrement(User user, UserRegistrationStatus registrationStatus) {
        user.setRegistrationStatus(registrationStatus) ;
        return user;
    }

    public static boolean seConnecter(String nom, String password) {
        return false;
    }

    public static void seDeconnecter(User user) {
        // TODO : Implementation de la deconnection
    }
}
