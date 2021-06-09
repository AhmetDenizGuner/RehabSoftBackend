package com.hacettepe.rehabsoft.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "coexisting_disease")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "coexisting_disease_seq", initialValue = 1, allocationSize = 1)
public class CoexistingDiseases extends BaseEntity{

    @Column(name = "disease_name")
    private String diseaseName;

}
