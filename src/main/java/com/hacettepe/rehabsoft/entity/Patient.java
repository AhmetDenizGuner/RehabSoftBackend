package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "patient_seq", initialValue = 1, allocationSize = 1)
public class Patient extends BaseEntity{

    @Column(name = "tc_kimlik_no")
    private String tcKimlikNo;

    @Column(name = "address")
    private String address;

    // @JsonBackReference
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private GeneralEvaluationForm generalEvaluationForm;

    @OneToOne
    @JoinColumn( name="users_id")
    private User user;

    @ManyToOne
    @JoinColumn( name="doctor_id")
    private Doctor doctor;

    @ManyToMany
    @JoinTable(
            name = "parent_patient",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private Collection<Parent> parentCollection;

    @OneToMany(mappedBy = "patient")
    Collection<VideoRequest> videoRequestCollection;
}
