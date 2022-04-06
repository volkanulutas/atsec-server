package com.vem.atsecserver.entity.user;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SecUser")
@Table(name = "SecUser")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private Boolean enabled;

    @Column
    private Boolean deleted;

    /*
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
     */

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}