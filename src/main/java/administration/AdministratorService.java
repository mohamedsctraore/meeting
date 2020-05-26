package administration;

import administration.domain.MeetingGroupProposal;
import administration.domain.MeetingGroupProposalDecision;
import administration.domain.MeetingGroupProposalStatus;
import meetings.domain.MeetingGroup;
import meetings.domain.Member;
import useraccess.domain.User;
import useraccess.domain.UserRegistrationStatus;
import useraccess.domain.UserRole;

public class AdministratorService {

    public static void changerStatusUserRegistration(User userRegistration,
                                                     UserRegistrationStatus registrationStatus) {
        userRegistration.setRegistrationStatus(registrationStatus);
    }

    public static MeetingGroup changerStatusMeetingGroupProposal(Member member,
                                                         MeetingGroupProposal meetingGroupProposal,
                                                         MeetingGroupProposalDecision meetingGroupProposalDecision) {
        if (meetingGroupProposalDecision.equals(MeetingGroupProposalDecision.ACCEPT)) {
            MeetingGroup meetingGroup = new MeetingGroup();
            meetingGroupProposal.setGroupProposalStatus(MeetingGroupProposalStatus.ACCEPTED);
            meetingGroup.addMember(UserRole.HOST, member);
            return meetingGroup;
        } else {
            meetingGroupProposal.setGroupProposalStatus(MeetingGroupProposalStatus.REJECTED);
        }
        return null;
    }
}
