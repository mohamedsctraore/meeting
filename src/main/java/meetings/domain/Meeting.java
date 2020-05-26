package meetings.domain;

import java.util.ArrayList;
import java.util.List;

public class Meeting {
    private String nom;
    private MeetingLocation location;
    private List<MeetingAttendee> meetingAttendees = new ArrayList<>();
    private List<MeetingNotAttendee> meetingNotAttendees = new ArrayList<>();
    private List<MeetingWaitingListMember> meetingWaitingListMembers = new ArrayList<>();
    private List<Messages> messages = new ArrayList<>();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public MeetingLocation getLocation() {
        return location;
    }

    public void setLocation(MeetingLocation location) {
        this.location = location;
    }

    public List<MeetingAttendee> getMeetingAttendees() {
        return meetingAttendees;
    }

    public void setMeetingAttendees(List<MeetingAttendee> meetingAttendees) {
        this.meetingAttendees = meetingAttendees;
    }

    public List<MeetingNotAttendee> getMeetingNotAttendees() {
        return meetingNotAttendees;
    }

    public void setMeetingNotAttendees(List<MeetingNotAttendee> meetingNotAttendees) {
        this.meetingNotAttendees = meetingNotAttendees;
    }

    public List<MeetingWaitingListMember> getMeetingWaitingListMembers() {
        return meetingWaitingListMembers;
    }

    public void setMeetingWaitingListMembers(List<MeetingWaitingListMember> meetingWaitingListMembers) {
        this.meetingWaitingListMembers = meetingWaitingListMembers;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public void addMeetingAttendee(MeetingAttendee meetingAttendee) {
        this.meetingAttendees.add(meetingAttendee);
    }

    public void addMeetingNotAttendee(MeetingNotAttendee meetingNotAttendee) {
        this.meetingNotAttendees.add(meetingNotAttendee);
    }

    public void addMeetingWaitingListMember(MeetingWaitingListMember meetingWaitingListMember) {
        this.meetingWaitingListMembers.add(meetingWaitingListMember);
    }

    public void addMessage(Messages message) {
        this.messages.add(message);
    }
}
