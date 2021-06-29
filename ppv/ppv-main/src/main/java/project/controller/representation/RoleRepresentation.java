package project.controller.representation;

import lombok.Getter;
import lombok.Setter;
import project.model.Role;
import project.model.User;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class RoleRepresentation {

    private Long id;

    @NotEmpty
    private String name;

    private List<User> users;

    public RoleRepresentation() {

    }

    public RoleRepresentation(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.users = role.getUsers();
    }

}
