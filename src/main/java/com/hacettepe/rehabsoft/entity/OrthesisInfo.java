package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "orthesis_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "orthesis_info_seq", initialValue = 1, allocationSize = 1)
public class OrthesisInfo extends BaseEntity{

    @Column(name = "right_part")
    private Boolean rightPart;

    @Column(name = "left_part")
    private Boolean leftPart;

    @Column(name = "orthesis_name")
    private String orthesisName;

    @ManyToOne
    @JoinColumn( name="general_evaluation_form_id")
    private GeneralEvaluationForm generalEvaluationForm;
}
