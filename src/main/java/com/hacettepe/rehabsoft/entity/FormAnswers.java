package com.hacettepe.rehabsoft.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "form_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "form_answers_seq", initialValue = 1, allocationSize = 1)
public class FormAnswers extends BaseEntity {

    @Column(name = "answer")
    private String answer;

    @ManyToOne
    @JoinColumn(name = "assigned_form_id")
    private AssignedForm assignedForm;

    @ManyToOne
    @JoinColumn(name = "form_field_id")
    private FormField formField;

}
