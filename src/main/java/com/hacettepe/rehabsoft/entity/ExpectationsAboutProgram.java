package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "expectations_about_program")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "expectations_about_program_seq", initialValue = 1, allocationSize = 1)
public class ExpectationsAboutProgram extends BaseEntity{

    @Column(name = "expectation_content")
    private String expectationContent;

    @ManyToOne
    @JoinColumn( name="general_evaluation_form_id")
    private GeneralEvaluationForm generalEvaluationForm;
}
