package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "after_birth_reason_cerebral_palsy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "after_birth_reason_cerebral_palsy_seq", initialValue = 1, allocationSize = 1)
public class AfterBirthReasonCerebralPalsy extends BaseEntity{

    @OneToOne
    @JoinColumn( name="general_evaluation_form_id")
    private GeneralEvaluationForm generalEvaluationForm;

    @Column(name = "occuring_month")
    private Integer occuringMonth;

    @Column(name = "cause")
    private String cause;

    @Column(name = "cause_info")
    private String causeInfo;
}
