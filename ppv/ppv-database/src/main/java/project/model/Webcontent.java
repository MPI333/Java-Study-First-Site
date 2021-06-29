package project.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "webcontent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Webcontent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ico;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private Integer amount;

    @Column(nullable = false)
    private String link;

    @ManyToMany(mappedBy = "webcontent")
    private List<User> users;

}
