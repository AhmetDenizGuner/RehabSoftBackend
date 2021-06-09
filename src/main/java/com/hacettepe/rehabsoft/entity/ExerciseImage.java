package com.hacettepe.rehabsoft.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "exercise_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "exercise_image_seq", initialValue = 1, allocationSize = 1)
public class ExerciseImage extends BaseEntity{

    @Column(name = "image_url")
    private String imageurl;

    @ManyToOne
    @JoinColumn( name="exercise_id")
    private Exercise exercise;
}
