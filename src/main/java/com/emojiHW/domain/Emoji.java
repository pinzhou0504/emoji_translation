package com.emojiHW.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "emojis")
public class Emoji {
        @Id
        @GeneratedValue(strategy = SEQUENCE, generator = "emojis_id_seq")
        @SequenceGenerator(name = "emojis_id_seq",sequenceName = "emojis_id_seq",allocationSize = 1)
        private Long id;

        @Column(name = "code")
        private String code;

        @Column(name = "cldr_short_name")
        private String cLDRShortName;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "conversation_id")
        private Conversation conversation;


        public Long getId() { return id; }

        public void setCode(String s) {
                this.code=s;
        }

        public String getCode(String s){
                return this.code;
        }

        public void setcLDRShortName(String s) {this.cLDRShortName=s;}

        public String getcLDRShortName(String s) { return this.cLDRShortName;}

}
