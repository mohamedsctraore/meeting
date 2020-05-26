package administration.domain;

import meetings.domain.Member;

import java.util.List;

public class MeetingGroupProposal {
    private String nom;
    private MeetingGroupProposalStatus groupProposalStatus;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public MeetingGroupProposalStatus getGroupProposalStatus() {
        return groupProposalStatus;
    }

    public void setGroupProposalStatus(MeetingGroupProposalStatus groupProposalStatus) {
        this.groupProposalStatus = groupProposalStatus;
    }
}
