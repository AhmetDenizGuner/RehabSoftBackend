package com.hacettepe.rehabsoft.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "form_template")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "form_template_seq", initialValue = 1, allocationSize = 1)
public class FormTemplate extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "form_dynamic_id")
    private FormDynamic formDynamic;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
