package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "hyperbilirubinemia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "hyperbilirubinemia_seq", initialValue = 1, allocationSize = 1)
public class Hyperbilirubinemia extends BaseEntity {

    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn( name="general_evaluation_form_id")
    private GeneralEvaluationForm generalEvaluationForm;

    @Column(name = "is_phototeraphy")
    private Boolean isPhototeraphy;

    @Column(name = "hospital_day_time")
    private Integer hospitalDayTime;
}
