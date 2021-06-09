package com.hacettepe.rehabsoft.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "form_field_default_value")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "form_field_default_value_seq", initialValue = 1, allocationSize = 1)
public class FormFieldDefaultValue extends BaseEntity {

    @Column(name = "value_name")
    private String valueName;

    @ManyToOne
    @JoinColumn(name = "form_field_id")
    private FormField formField;

}
