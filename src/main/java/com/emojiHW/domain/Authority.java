package com.emojiHW.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.management.relation.Role;
import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table (name = "Authorities")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "authorities_id_seq")
    @SequenceGenerator(name = "authorities_id_seq", sequenceName = "authorities_id_seq", allocationSize = 1)

    private Long id;

    @Column(name = "role")
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
//    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(String role){this.role = role;}

    public String getRole(){ return  this.role;}

    public Long getId() { return id; }


    @Override
    public String getAuthority() {
        return role;
    }
}
