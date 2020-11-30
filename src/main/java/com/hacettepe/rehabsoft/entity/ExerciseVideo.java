package com.hacettepe.rehabsoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "exercise_video")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "exercise_video_seq", initialValue = 1, allocationSize = 1)
public class ExerciseVideo extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne
    @JoinColumn( name="exercise_id")
    private Exercise exercise;
}
