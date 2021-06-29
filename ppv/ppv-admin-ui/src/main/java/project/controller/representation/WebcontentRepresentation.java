package project.controller.representation;

import lombok.Getter;
import lombok.Setter;
import project.model.User;
import project.model.Webcontent;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class WebcontentRepresentation {

    private Long id;

    @NotEmpty
    private String ico;

    @NotEmpty
    private String platform;

    @NotEmpty
    private String title;

    private Integer amount;

    @NotEmpty
    private String link;

    private List<User> users;

    public WebcontentRepresentation() {

    }

    public WebcontentRepresentation(Webcontent webcontent) {
        this.id = webcontent.getId();
        this.ico = webcontent.getIco();
        this.platform = webcontent.getPlatform();
        this.title = webcontent.getTitle();
        this.amount = webcontent.getAmount();
        this.link = webcontent.getLink();
        this.users = webcontent.getUsers();
    }

}
