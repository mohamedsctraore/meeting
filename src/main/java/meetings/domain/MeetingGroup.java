package meetings.domain;

import useraccess.domain.UserRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetingGroup {
    private Map<UserRole, Member> members = new HashMap<>();
    private List<Meeting> meetings;

    public Map<UserRole, Member> getMembers() {
        return members;
    }

    public void setMembers(Map<UserRole, Member> members) {
        this.members = members;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public void addMember(UserRole role, Member member) {
        this.members.put(role, member);
    }

    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }
}
