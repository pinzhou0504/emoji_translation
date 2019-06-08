package com.emojiHW.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.javafx.beans.IDProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "users")
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @JsonView({Views.UserConversationViews.class})
    private Long id;

    @JsonView({Views.UserViews.class,Views.UserConversationViews.class})
    @Column(unique = true)
    private String username;

    @JsonView({Views.UserViews.class,Views.UserConversationViews.class})
    @Column(name = "first_name")
    private String firstName;

    @JsonView({Views.UserViews.class,Views.UserConversationViews.class})
    @Column(name = "last_name")
    private String lastName;

    @JsonView({Views.UserViews.class,Views.UserConversationViews.class})
    @Column(name = "password")
    private String password;

    @JsonView({Views.UserViews.class,Views.UserConversationViews.class})
    @Column(name = "email")
    private String email;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @JsonView({Views.UserViews.class})
    @Column(name = "phone_number")
    private String phoneNumber;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Conversation> conversations;

//    @Transient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Authority> authorities;



//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
////    @JsonIgnore
//    private List<Authority> authorities;


    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

//    public void setAuthority(String authority){this.authorities = authorities;}
//
//    public List<Authority> getAuthority(){return authorities;}

    public Long getId() { return id; }


    public void setUsername(String s) {
        this.username=s;
    }

    @Override
    public String getUsername(){
        return this.username;
    }


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    public void setAuthorities(List<Authority> authorities){
        this.authorities = authorities;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() { return this.accountNonExpired; }

    public void setAccountNonExpired(boolean accountNonExpired){ this.accountNonExpired=accountNonExpired;}

    public boolean getAccountNonExpired(){ return this.accountNonExpired;}


    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() { return this.accountNonLocked; }

    public void setAccountNonLocked(boolean accountNonLocked){this.accountNonLocked=accountNonLocked;}

    public boolean getAccountNonLocked(){return this.accountNonLocked;}


    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() { return this.credentialsNonExpired; }

    public void setCredentialsNonExpired(boolean credentialsNonExpired){ this.credentialsNonExpired=credentialsNonExpired;}

    public boolean getCredentialsNonExpired(){ return this.credentialsNonExpired;}


    @JsonIgnore
    @Override
    public boolean isEnabled() { return this.enabled; }

    public void setEnabled(boolean enabled){ this.enabled=enabled;}

    public boolean getEnabled(){ return this.enabled;}



    public void setFirstName(String s) {this.firstName=s;}

    public String getFirstName() { return this.firstName;}

    public void setLastName(String s) {this.lastName=s;}

    public String getLastName() { return this.lastName;}

    @Override
    @JsonIgnore
    public String getPassword() { return this.password; }

    @JsonProperty
    public void setPassword(String s) {this.password=s;}

    public void setEmail(String s) {this.email=s;}

    public String getEmail() { return this.email;}

    public void setPhoneNumber(String s){this.phoneNumber=s;}

    public String getPhoneNumber(){return this.phoneNumber;}




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
