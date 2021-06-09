package com.hacettepe.rehabsoft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "firebase_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "firebase_token_seq", initialValue = 1, allocationSize = 1)
public class FirebaseToken extends BaseEntity{
    @Column(name = "firebase_token_content")
    private String firebaseTokenContent;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
