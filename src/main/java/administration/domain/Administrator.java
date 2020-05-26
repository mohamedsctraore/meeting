package administration.domain;

import useraccess.domain.User;
import useraccess.domain.UserRole;

import java.util.List;

public class Administrator extends User {
    List<UserRole> roles;
}
