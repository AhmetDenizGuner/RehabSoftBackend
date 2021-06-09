package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "scheduled_exercise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "scheduled_exercise_seq", initialValue = 1, allocationSize = 1)
public class ScheduledExercise extends BaseEntity  {


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;

    @Column(name = "is_applied")
    private Boolean isApplied;

    @ManyToOne
    @JoinColumn(name = "physiotherapy_program_id")
    private PhysiotherapyProgram physiotherapyProgram;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

}
