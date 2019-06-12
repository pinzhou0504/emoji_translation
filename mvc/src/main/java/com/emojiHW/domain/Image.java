package com.emojiHW.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "images")
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "images_id_seq")
    @SequenceGenerator(name = "images_id_seq", sequenceName = "images_id_seq", allocationSize = 1)
//    @JsonView({Views.UserConversationViews.class})
    private Long id;

//    @Column(name = "user_profile")
//    private Image userProfile;


    @Column(name = "key")
    private String key;

    @Column(name = "bucket_name")
    private String bucketName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Long getId() { return id; }

    public void setId(Long id){ this.id = id;}

//    public Image getUserProfile(){ return userProfile;}
//
//    public void setUserProfile( Image userProfile){ this.userProfile = userProfile;}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getKey(){ return key;}

    public void setKey(String key){this.key = key;}

    public String getBucketName(){ return bucketName;}

    public void setBucketName(String bucketName){this.bucketName = bucketName;}
}
