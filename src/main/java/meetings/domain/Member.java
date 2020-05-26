package meetings.domain;

import useraccess.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Member extends User {
    private List<Messages> messages = new ArrayList<>();

    public Member(String nom, String password) {
        super(nom, password);
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public void addMessage(Messages message) {
        this.messages.add(message);
    }
}
