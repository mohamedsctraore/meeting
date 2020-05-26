package meetings;

import administration.domain.MeetingGroupProposal;
import administration.domain.MeetingGroupProposalStatus;
import meetings.domain.MeetingGroup;
import meetings.domain.Member;
import useraccess.domain.UserRole;

public class MeetingGroupService {

    public static MeetingGroupProposal creerMeetingGroupProposal(String nom) {
        MeetingGroupProposal proposal = new MeetingGroupProposal();
        proposal.setGroupProposalStatus(MeetingGroupProposalStatus.INVERIFICATION);
        return proposal;
    }

    public static void ajouterMemberMeetingGroup(MeetingGroup meetingGroup, Member member, UserRole role) {
        meetingGroup.addMember(role, member);
    }
}
