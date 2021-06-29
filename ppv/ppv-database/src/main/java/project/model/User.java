package project.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32, nullable = false, unique = true)
    private String username;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(length = 32, nullable = false)
    private String email;

    @Column(columnDefinition = "decimal(19,2) default 0.00")
    private BigDecimal balance;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_webcontent",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "webcontent_id"))
    private List<Webcontent> webcontent;

}
