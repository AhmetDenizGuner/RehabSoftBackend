package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "parent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "parent_seq", initialValue = 1, allocationSize = 1)
public class Parent extends BaseEntity{

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "parent_type")
    private String parentType;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Collection<Phone> phoneCollection;

    @ManyToMany(mappedBy = "parentCollection")
    private Collection<Patient> patientCollection;

}
