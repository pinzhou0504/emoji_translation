package com.emojiHW.domain;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "users")
public class User {
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
    private List<Conversation> conversation;

    public void setUser(String sSmith) {
    }

    public long getId() {
        return id;
    }


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
