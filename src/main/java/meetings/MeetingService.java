package meetings;

import meetings.domain.*;

import java.util.List;

public class MeetingService {

    public static Meeting creerMeeting(String nom) {
        Meeting meeting = new Meeting();
        meeting.setNom(nom);
        return meeting;
    }

    public static boolean ajouterMemberMeeting(Member member, Meeting meeting) {
        int nombreMembreMeetingAttendees = meeting.getMeetingAttendees().size();

        if (nombreMembreMeetingAttendees < 10) {
            MeetingAttendee meetingAttendee = new MeetingAttendee();
            meetingAttendee.setMember(member);
            meeting.addMeetingAttendee(meetingAttendee);
            return true;
        }
        return false;
    }

    public static void ajouterMemberWaitingList(Member member, Meeting meeting) {
        MeetingWaitingListMember meetingWaitingListMember = new MeetingWaitingListMember();
        meetingWaitingListMember.setMember(member);
        meeting.addMeetingWaitingListMember(meetingWaitingListMember);
    }

    public static void quitterMemberMeeting(Meeting meeting, MeetingAttendee member) {
        List<MeetingAttendee> meetingAttendees = meeting.getMeetingAttendees();
        meetingAttendees.remove(member);

        List<MeetingNotAttendee> meetingNotAttendees = meeting.getMeetingNotAttendees();
        MeetingNotAttendee meetingNotAttendee = new MeetingNotAttendee();
        meetingNotAttendee.setMember(member.getMember());
        meetingNotAttendees.add(meetingNotAttendee);
    }

    public static void envoyerMessage(Meeting meeting, Member member, Messages message) {
        member.addMessage(message);
        meeting.addMessage(message);
    }

    public static Messages lireDernierMessage(Meeting meeting) {
        List<Messages> messages = meeting.getMessages();
        return messages.get(messages.size() - 1);
    }

    public static List<Messages> lireTousLesMessages(Meeting meeting) {
        return meeting.getMessages();
    }
}
