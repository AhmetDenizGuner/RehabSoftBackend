package com.hacettepe.rehabsoft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "form_field")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "form_field_seq", initialValue = 1, allocationSize = 1)
public class FormField extends BaseEntity{

    @Column(name = "form_field_name")
    private String fieldName;

    @Column(name = "field_type")
    private String fieldType;

    @Column(name = "form_field_order")
    private int fieldOrder;

    @ManyToOne
    @JoinColumn(name = "form_dynamic_id")
    private FormDynamic formDynamic;

    @OneToMany(mappedBy = "formField", cascade = CascadeType.REMOVE)
    private Collection<FormFieldDefaultValue> formFieldDefaultValueCollection;


}
