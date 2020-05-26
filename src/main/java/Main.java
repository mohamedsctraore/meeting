import administration.AdministratorService;
import administration.domain.MeetingGroupProposal;
import administration.domain.MeetingGroupProposalDecision;
import meetings.MeetingGroupService;
import meetings.MeetingService;
import meetings.domain.*;
import useraccess.domain.User;
import useraccess.domain.UserRole;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, User> databaseUsers = new HashMap<>();
    private static Map<String, MeetingGroupProposal> databaseMeetingGroupProposal = new HashMap<>();
    private static Map<String, MeetingGroup> databaseMeetingGroup = new HashMap<>();
    private static Map<String, Meeting> databaseMeeting = new HashMap<>();

    public static void main(String[] args) {
        String clavier;
        int choix;
        User userConnecte;
        Member member;

        do {
            // Page Inscription Connexion
            choix = menuOuverture();

            switch (choix) {
                // Inscription
                case 1 :
                    System.out.println("Entrez votre nom");
                    String nom = scanner.nextLine();
                    System.out.println("Entrez votre password");
                    String password = scanner.nextLine();
                    Member user = new Member(nom, password);
                    databaseUsers.put(nom, user);
                    break;
                case 2 :
                    // Connexion
                    do {
                        System.out.println("Entrez votre nom");
                        nom = scanner.nextLine();
                        System.out.println("Entrez votre password");
                        password = scanner.nextLine();
                        userConnecte = seConnecter(nom, password);
                    } while (userConnecte == null);

                    userConnecte = databaseUsers.get(nom);
                    member = (Member) userConnecte;

                    do {
                        // Page Accueil
                        choix = menuAccueil();

                        switch (choix) {
                            // Creation MeetingGroup
                            case 1:
                                System.out.println("Entrez le nom de votre MeetingGroup");
                                clavier = scanner.nextLine();
                                MeetingGroupProposal meetingGroupProposal =
                                        MeetingGroupService.creerMeetingGroupProposal(clavier);
                                databaseMeetingGroupProposal.put(clavier, meetingGroupProposal);
                                break;
                                // Rejoindre Meeting Group
                            case 2:
                                System.out.println("Entrez le nom du MeetingGroup");
                                clavier = scanner.nextLine();
                                MeetingGroup meetingGroup = databaseMeetingGroup.get(clavier);
                                Map<UserRole, Member> members = meetingGroup.getMembers();
                                boolean presenceMeetingGroup = members.containsValue(member);

                                if (!presenceMeetingGroup) {
                                    System.out.println("Vous n'êtes pas membre de ce meeting group");
                                    break;
                                }

                                do {
                                    choix = menuRejoindreMeetingGroup();

                                    switch (choix) {
                                        // Creer Meeting
                                        case 1:
                                            System.out.println("Entrez le nom du Meeting Group");
                                            clavier = scanner.nextLine();
                                            Meeting newMeeting = MeetingService.creerMeeting(clavier);
                                            MeetingAttendee host = new MeetingAttendee();
                                            host.setMember(member);
                                            newMeeting.addMeetingAttendee(host);
                                            databaseMeeting.put(clavier, newMeeting);
                                            break;
                                        // Rejoindre Meeting
                                        case 2:
                                            boolean isPresent = false;
                                            System.out.println("Entrez le nom du Meeting");
                                            clavier = scanner.nextLine();
                                            Meeting meet = databaseMeeting.get(clavier);
                                            List<MeetingAttendee> meetingAttendees = meet.getMeetingAttendees();
                                            MeetingAttendee attendee;

                                            for (MeetingAttendee meetingAttendee : meetingAttendees) {
                                                isPresent = meetingAttendee.getMember().equals(member);
                                            }

                                            if (isPresent) {
                                                do {
                                                    choix = menuMeeting();

                                                    switch (choix) {
                                                        // Envoyer Message
                                                        case 1:
                                                            System.out.println("Saisir le message à envoyer");
                                                            clavier = scanner.nextLine();
                                                            Messages message = new Messages();
                                                            message.setMessage(clavier);

                                                            MeetingService.envoyerMessage(meet, member, message);
                                                            break;
                                                        // Lire dernier message
                                                        case 2:
                                                            Messages messages =
                                                                    MeetingService.lireDernierMessage(meet);
                                                            System.out.println(member.getNom() + " : " +
                                                                    messages.getMessage());
                                                            break;
                                                        // Lire tous les messages
                                                        case 3:
                                                            List<Messages> messages1 =
                                                                    MeetingService.lireTousLesMessages(meet);
                                                            for (Messages messageMeeting : messages1) {
                                                                System.out.println(member.getNom() + " : " +
                                                                        messageMeeting.getMessage());
                                                            }
                                                            break;
                                                        case 4:
                                                            break;
                                                    }
                                                } while (choix < 4);
                                            }
                                            break;
                                        case 3:
                                            break;
                                    }

                                } while (choix < 3);
                                break;
                                //Administration
                            case 3:
                                do {
                                    choix = menuAdministration();

                                    switch (choix) {
                                        // Validation creation Meeting Group
                                        case 1:
                                            System.out.println("Entrez le nom du MeetingGroup");
                                            clavier = scanner.nextLine();
                                            String nomMeetingGroup = clavier;
                                            MeetingGroupProposal proposal =
                                                    databaseMeetingGroupProposal.get(clavier);
                                            System.out.println("Souhaitez vous valider ? 1 Oui 2 Non");
                                            clavier = scanner.nextLine();

                                            int validation = Integer.parseInt(clavier);
                                            if (validation == 1) {
                                                MeetingGroup group = AdministratorService.
                                                        changerStatusMeetingGroupProposal(member, proposal,
                                                                MeetingGroupProposalDecision.ACCEPT);
                                                if (group != null) {
                                                    databaseMeetingGroup.put(nomMeetingGroup, group);
                                                }
                                            } else {
                                                AdministratorService.
                                                        changerStatusMeetingGroupProposal(member, proposal,
                                                                MeetingGroupProposalDecision.REJECT);
                                            }

                                            break;
                                            // Validation User Registration
                                        case 2:

                                            break;
                                        case 3:
                                            break;

                                    }
                                } while (choix < 3);

                                break;
                            case 4:
                                break;
                        }
                    } while (choix < 4);
                    break;
                    // Quitter
                case 3 :
                    break;
            }
        }
        while (choix < 3);

    }

    static int menuOuverture() {
        int choix = 0;
        System.out.println("###### BIENVENUE MEETUP ######");
        System.out.println();
        System.out.println();
        System.out.println("1 : S'inscrire");
        System.out.println("2 : Se connecter");
        System.out.println("3 : Quitter");
        while ((choix != 1) && (choix != 2) && (choix != 3)) {
            try {
                choix = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre compris entre 1 et 3");
            }

            scanner.nextLine();
        }
        return choix;
    }

    static int menuAccueil() {
        int choix = 0;
        System.out.println("###### ACCUEIL ######");
        System.out.println();
        System.out.println();
        System.out.println("1 : Creer Meeting Group");
        System.out.println("2 : Rejoindre Meeting Group");
        System.out.println("3 : Administration");
        System.out.println("4 : Accueil");
        while ((choix != 1) && (choix != 2) && (choix != 3) && (choix != 4)) {
            try {
                choix = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre compris entre 1 et 4");
            }

            scanner.nextLine();
        }
        return choix;
    }

    static int menuAdministration() {
        int choix = 0;
        System.out.println("###### ADMINISTRATION ######");
        System.out.println();
        System.out.println();
        System.out.println("1 : Validation Meeting Group");
        System.out.println("2 : Validation User Registration");
        System.out.println("3 : Menu Accueil");
        while ((choix != 1) && (choix != 2) && (choix != 3)) {
            try {
                choix = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre compris entre 1 et 3");
            }

            scanner.nextLine();
        }
        return choix;
    }

    static int menuRejoindreMeetingGroup() {
        int choix = 0;
        System.out.println("###### MEETING GROUP ######");
        System.out.println();
        System.out.println();
        System.out.println("1 : Creer Meeting");
        System.out.println("2 : Rejoindre Meeting");
        System.out.println("3 : Retour");
        while ((choix != 1) && (choix != 2) && (choix != 3)) {
            try {
                choix = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre compris entre 1 et 3");
            }

            scanner.nextLine();
        }
        return choix;
    }

    static int menuMeeting() {
        int choix = 0;
        System.out.println("###### MEETING ######");
        System.out.println();
        System.out.println();
        System.out.println("1 : Envoyer Message");
        System.out.println("2 : Lire dernier Message");
        System.out.println("3 : Lire tous les messages");
        System.out.println("4 : Retour");
        while ((choix != 1) && (choix != 2) && (choix != 3) && (choix != 4)) {
            try {
                choix = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre compris entre 1 et 4");
            }

            scanner.nextLine();
        }
        return choix;
    }

    static int menuMeetingAttendee() {
        int choix = 0;
        System.out.println("###### MEETING ######");
        System.out.println();
        System.out.println();
        System.out.println("1 : Participer au meeting");
        System.out.println("2 : Retour");
        while ((choix != 1) && (choix != 2)) {
            try {
                choix = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre compris entre 1 et 2");
            }

            scanner.nextLine();
        }
        return choix;
    }

    static User seConnecter(String nom, String password) {
        boolean exists = databaseUsers.containsKey(nom);
        if (exists) {
            return databaseUsers.get(nom);
        }
        return null;
    }
}