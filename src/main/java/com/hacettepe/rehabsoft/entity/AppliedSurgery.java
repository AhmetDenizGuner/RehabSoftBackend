package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "applied_surgery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "applied_surgery_seq", initialValue = 1, allocationSize = 1)

public class AppliedSurgery extends BaseEntity {
    @Column(name = "surgery_name")
    private String surgeryName;

    @Column(name = "epicrisis_image_url")
    private String epicrisisImageUrl;

    @ManyToOne
    @JoinColumn( name="general_evaluation_form_id")
    private GeneralEvaluationForm generalEvaluationForm;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "applying_date")
    private LocalDateTime applyingDate;
}
