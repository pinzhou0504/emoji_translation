package com.emojiHW.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.javafx.beans.IDProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)

    private Long id;

    @Column(unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Conversation> conversation;


    public List<Conversation> getConversation() {
        return conversation;
    }

    public Long getId() { return id; }

    public void setUsername(String s) {
        this.username=s;
    }

    @Override
    public String getPassword() { return this.password; }

    public String getUsername(){
        return this.username;
    }



    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return null; }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() { return true; }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() { return true; }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @JsonIgnore
    @Override
    public boolean isEnabled() { return true; }

    public void setFirstName(String s) {this.firstName=s;}

    public String getFirstName() { return this.firstName;}

    public void setLastName(String s) {this.lastName=s;}

    public String getLastName() { return this.lastName;}

    public void setPassword(String s) {this.password=s;}

    public void setEmail(String s) {this.email=s;}

    public String getEmail() { return this.email;}


//    public User(){
//        firstName = "John";
//        lastName = "Smith";
//
//    }
//
//    public String toString(){
//        return String.valueOf(this.firstName)+ " " +String.valueOf(this.lastName)+ " " +id;
//    }
//
//    public void setId(Long Id){
//        this.id = Id;
//    }
//    public static void main(String [] args){
////        User u = new User();
////        u.setId(15L);
////        System.out.println(u);
//    }
}
