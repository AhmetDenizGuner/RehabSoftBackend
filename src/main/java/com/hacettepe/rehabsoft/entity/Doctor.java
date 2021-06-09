package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "doctor_seq", initialValue = 1, allocationSize = 1)
public class Doctor extends BaseEntity{

    @OneToMany(mappedBy = "doctor")
    Collection<Patient> patientCollection;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "doctor")
    Collection<VideoRequest> videoRequestCollection;
}
