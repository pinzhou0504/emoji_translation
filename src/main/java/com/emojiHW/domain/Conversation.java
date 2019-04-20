package com.emojiHW.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "conversations")
public class Conversation {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "conversations_id_seq")
    @SequenceGenerator(name = "conversations_id_seq",sequenceName = "conversations_id_seq",allocationSize = 1)

    private Long conversations;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username_id")
    private User username;
}
