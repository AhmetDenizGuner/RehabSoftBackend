package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "users_seq", initialValue = 1, allocationSize = 1)
public class User extends BaseEntity{


    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "user_password")
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;


    @OneToMany(mappedBy = "user")
    Collection<FirebaseToken> firebaseTokenCollection;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Collection<Notification> notificationCollection;

    @OneToMany(mappedBy = "doctorUser")
    private Collection<OnlineMeeting> doctorUserOnlineMeetingCollection;

    @OneToMany(mappedBy = "patientUser")
    private Collection<OnlineMeeting> patientUserOnlineMeetingCollection;
}
