package com.emojiHW.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "conversations")
public class Conversation implements Serializable {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "conversations_id_seq")
    @SequenceGenerator(name = "conversations_id_seq", sequenceName = "conversations_id_seq", allocationSize = 1)
    private Long id;


    @Column(name = "content")
    private String content;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "conversation", cascade = CascadeType.ALL)
    private Emoji emoji;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public Long getId() {
        return id;
    }

    public void setContent(String s) {
        this.content = s;
    }

    public String getContent(){
        return this.content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Emoji getEmoji(){ return emoji;}

    public void setEmoji(Emoji emoji){ this.emoji = emoji;}
}