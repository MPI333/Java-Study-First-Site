package project.controller.representation;

import lombok.Getter;
import lombok.Setter;
import project.model.Role;
import project.model.User;
import project.model.Webcontent;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class UserRepresentation {

    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

    private BigDecimal balance = new BigDecimal("0.00");

    private List<Role> roles;

    private List<Webcontent> webcontent;

    public UserRepresentation() {

    }

    public UserRepresentation(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.balance = user.getBalance();
        this.roles = user.getRoles();
        this.webcontent = user.getWebcontent();
    }

    public boolean hasWebcontentWithId(Long searchableId) {
        for (Webcontent w : webcontent) {
            if (w.getId().equals(searchableId)) {
                return true;
            }
        }
        return false;
    }

}
