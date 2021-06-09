package com.hacettepe.rehabsoft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "assigned_form")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "assigned_form_seq", initialValue = 1, allocationSize = 1)
public class AssignedForm extends BaseEntity {


    @ManyToOne(cascade = CascadeType.PERSIST )
    @JoinColumn(name = "form_dynamic_id")
    private FormDynamic formDynamic;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "is_answered")
    private boolean isAnswered;

    @OneToMany(mappedBy = "assignedForm", cascade = CascadeType.REMOVE)
    private Collection<FormAnswers> formAnswersCollection;
}
