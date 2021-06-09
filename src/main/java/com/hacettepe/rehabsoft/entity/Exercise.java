package com.hacettepe.rehabsoft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "exercise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "exercise_seq", initialValue = 1, allocationSize = 1)
public class Exercise extends BaseEntity{

    @Column(name = "exercise_name")
    private String exerciseName;

    @Column(name = "exercise_content")
    private String exerciseContent;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private Collection<ExerciseVideo> exerciseVideoCollection;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private Collection<ExerciseImage> exerciseImageCollection;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "exerciseCollection")
    private Collection<VideoRequest> videoRequestCollection;
}
