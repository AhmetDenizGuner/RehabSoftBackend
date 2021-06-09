package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "other_orthesis_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "other_orthesis_info_seq", initialValue = 1, allocationSize = 1)
public class OtherOrthesisInfo extends BaseEntity{

    @Column(name = "orthesis_name")
    private String orthesisName;

    @Column(name = "orthesis_url")
    private String orthesisUrl;

    @ManyToOne
    @JoinColumn( name="general_evaluation_form_id")
    private GeneralEvaluationForm generalEvaluationForm;
}
