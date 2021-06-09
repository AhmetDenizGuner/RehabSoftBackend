package com.hacettepe.rehabsoft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "form_dynamic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "form_dynamic_seq", initialValue = 1, allocationSize = 1)
public class FormDynamic extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "explanation")
    private String explanation;

    @OneToMany(mappedBy = "formDynamic" , cascade = CascadeType.REMOVE)
    private Collection<FormField> formFieldCollection;


    //@OneToMany(mappedBy = "formDynamic", cascade = CascadeType.ALL)
    //private Collection<AssignedForm> assignedFormCollection;
}
