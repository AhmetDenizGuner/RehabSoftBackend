package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "physiotherapy_program")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "physiotherapy_program_seq", initialValue = 1, allocationSize = 1)
public class PhysiotherapyProgram extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "goal")
    private String goal;

    @OneToOne
    @JoinColumn(name= "patient_id")
    private Patient patient;

    @Column(name = "is_active")
    private Boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    @OneToMany(mappedBy = "physiotherapyProgram", cascade = CascadeType.REMOVE)
    private Collection<ScheduledExercise> scheduledExerciseCollection;

}
