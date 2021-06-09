package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "video_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "video_request_seq", initialValue = 1, allocationSize = 1)
public class VideoRequest extends BaseEntity {

    @Column(name = "request_content")
    private String requestContent;

    @Column(name = "request_title")
    private String requestTitle;



    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToMany
    @JoinTable(
            name = "exercise_video_request",
            joinColumns = @JoinColumn(name = "video_request_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    private Collection<Exercise> exerciseCollection;

    @OneToOne(mappedBy = "videoRequest")
    private ResponseVideoRequest responseVideoRequest;


}
