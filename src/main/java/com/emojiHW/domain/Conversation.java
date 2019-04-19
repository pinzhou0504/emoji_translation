package com.emojiHW.domain;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Conversation {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username_id")
    private User username;
}
