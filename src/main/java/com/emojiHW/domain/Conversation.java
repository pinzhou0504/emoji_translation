package com.emojiHW.domain;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "conversations")
public class Conversation {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "conversations_id_seq")
    @SequenceGenerator(name = "conversations_id_seq", sequenceName = "conversations_id_seq", allocationSize = 1)

    private Long id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "conversation",cascade = CascadeType.ALL)
    private List<Emoji> emoji;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    public void setConversation(String s) {

    }

    public long getId() {
        return 0;
    }
}
