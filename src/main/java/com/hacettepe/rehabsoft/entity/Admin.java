package com.hacettepe.rehabsoft.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "admins_seq", initialValue = 1, allocationSize = 1)
public class Admin extends BaseEntity{


    @OneToOne
    @JoinColumn( name="user_id")
    private User user;
}
