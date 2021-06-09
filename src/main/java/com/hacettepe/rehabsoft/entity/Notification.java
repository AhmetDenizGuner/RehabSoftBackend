package com.hacettepe.rehabsoft.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "notification_seq", initialValue = 1, allocationSize = 1)
public class Notification extends BaseEntity {

    @Column(name = "notification_content")
    private String notificationContent;

    @Column(name = "notification_url")
    private String notificationUrl;

    @Column(name = "notification_title")
    private String notificationTitle;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @JoinColumn(name = "status")
    private int status;
}
