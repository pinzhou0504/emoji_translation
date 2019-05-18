package com.emojiHW.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "emojis")
public class Emoji implements Serializable {
        @Id
        @GeneratedValue(strategy = SEQUENCE, generator = "emojis_id_seq")
        @SequenceGenerator(name = "emojis_id_seq",sequenceName = "emojis_id_seq",allocationSize = 1)
        private Long id;

        @Column(name = "code")
        private String code;

        @Column(name = "cldr_short_name")
        private String cLDRShortName;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "conversation_id")
        @JsonIgnore
        private Conversation conversation;


        public Long getId() { return id; }

        public void setCode(String s) {
                this.code=s;
        }

        public String getCode(){
                return this.code;
        }

        public void setcLDRShortName(String s) {this.cLDRShortName=s;}

        public String getcLDRShortName() { return this.cLDRShortName;}

        public Conversation getConversation(){ return conversation;}

        public void setConversation(Conversation conversation){ this.conversation = conversation;}

}
