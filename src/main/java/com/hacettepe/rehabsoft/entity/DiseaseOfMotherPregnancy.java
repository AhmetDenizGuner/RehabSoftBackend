package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "disease_of_mother_pregnancy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "disease_of_mother_pregnancy_seq", initialValue = 1, allocationSize = 1)
public class DiseaseOfMotherPregnancy extends BaseEntity{

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn( name="general_evaluation_form_id")
    private GeneralEvaluationForm generalEvaluationForm;

    @Column(name = "disease_name")
    private String diseaseName;
}
