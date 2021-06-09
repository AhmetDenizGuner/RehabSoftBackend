package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "phone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "phone_seq", initialValue = 1, allocationSize = 1)
public class Phone extends BaseEntity{

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn( name="parent_id")
    private Parent parent;
}
