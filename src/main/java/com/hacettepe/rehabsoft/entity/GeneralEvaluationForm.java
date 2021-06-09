package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "general_evaluation_form")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "general_evaluation_form_seq", initialValue = 1, allocationSize = 1)
public class GeneralEvaluationForm extends BaseEntity {

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "age_as_months")
    private Integer ageAsMonth;

    @Column(name = "number_of_siblings")
    private Integer numberOfSiblings;

    @Column(name = "which_child")
    private Integer whichChild;

    @Column(name = "height_cm")
    private Integer heightCm;

    @Column(name = "weight_gr")
    private Long weightGr;

    @Column(name = "head_round_cm")
    private Integer headRoundCm;

    @Column(name = "mothers_given_birth_age_year")
    private Integer mothersGivenBirthAgeYear;

    @Column(name = "mothers_education_level")
    private String mothersEducationLevel;

    @Column(name = "type_of_pregnancy")
    private String typeOfPregnancy;

    @Column(name = "fathers_education_level")
    private String fathersEducationLevel;

    @Column(name = "multiple_pregnancy")
    private Integer multiplePregnancy;

    @Column(name = "is_relative_marriage")
    private Boolean isRelativeMarriage;

    @Column(name = "is_blood_incompatibility")
    private Boolean isBloodIncompatibility;

    @Column(name = "birth_week")
    private Integer birthWeek;

    @Column(name = "birth_type")
    private String birthType;

    @Column(name = "birth_weight")
    private Integer birthWeight;

    @Column(name = "birth_head_around_cm")
    private Long birthHeadAroundCm;

    @Column(name = "apgar_score")
    private Integer apgarScore;

    @Column(name = "is_birth_anoxia")
    private Boolean isBirthAnoxia;

    @Column(name = "is_birth_empurpling")
    private Boolean isBirthEmpurpling;

    @Column(name = "is_birth_crying")
    private Boolean isBirthCrying;

    @Column(name = "is_high_blood_pressor_pregnancy")
    private Boolean isHighBloodPressorPregnancy;

    @Column(name = "pregnancy_infection_info")
    private String pregnancyInfectionInfo;

    @Column(name = "pregnancy_medicine_usage_info")
    private String pregnancyMedicineUsageInfo;

    @Column(name = "is_pregnancy_drinking")
    private Boolean isPregnancyDrinking;

    @Column(name = "is_pregnanyc_smoking")
    private Boolean isPregnanycSmoking;

    @Column(name = "is_oxygen_support")
    private Boolean oxygenSupport;


    @Column(name = "intensive_care")
    private Integer intensiveCare;

    @Column(name = "is_newborn_retinopathy")
    private Boolean isNewbornRetinopathy;

    @Column(name = "is_bronchopulmonary_dysplasia")
    private Boolean isBronchopulmonaryDysplasia;

    @Column(name = "is_respiratuvar_distress_syndrom")
    private Boolean isRespiratuvarDistressSyndrom;

    @Column(name = "is_hypoglycaemia")
    private Boolean isHypoglycaemia;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL,mappedBy = "generalEvaluationForm")
    private DiseaseOfMotherPregnancy diseaseOfMotherPregnancy;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL,mappedBy = "generalEvaluationForm")
    private Hyperbilirubinemia hyperbilirubinemia;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL,mappedBy = "generalEvaluationForm")
    private AfterBirthReasonCerebralPalsy afterBirthReasonCerebralPalsy;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL,mappedBy = "generalEvaluationForm")
    private BotoxTreatment botoxTreatment;


    @OneToMany(mappedBy = "generalEvaluationForm", cascade = CascadeType.ALL)
    private Collection<AppliedSurgery> appliedSurgeryCollection;

    @OneToMany(mappedBy = "generalEvaluationForm", cascade = CascadeType.ALL)
    private Collection<OrthesisInfo> orthesisInfoCollection;

    @OneToMany(mappedBy = "generalEvaluationForm", cascade = CascadeType.ALL)
    private Collection<OtherOrthesisInfo> otherOrthesisInfoCollection;

    @OneToMany(mappedBy = "generalEvaluationForm", cascade = CascadeType.ALL)
    private Collection<UsedMedicine> usedMedicineCollection;

    @ManyToMany
    @JoinTable(name = "general_evaluation_form_coexisting_disease", joinColumns = {
            @JoinColumn(name = "general_evaluation_form_id") }, inverseJoinColumns = {
            @JoinColumn(name = "coexisting_disease_id") })
    private Collection<CoexistingDiseases> coexistingDiseasesCollection;


    @OneToOne(cascade = javax.persistence.CascadeType.ALL,mappedBy = "generalEvaluationForm")
    private VisualImpairment visualImpairment;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL,mappedBy = "generalEvaluationForm")
    private HearingImpairment hearingImpairment;

    @OneToMany(mappedBy = "generalEvaluationForm", cascade = CascadeType.ALL)
    private Collection<ExpectationsAboutProgram> expectationsAboutProgramCollection;


    @OneToOne
    @JoinColumn( name="patient_id")
    private Patient patient;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL,mappedBy = "generalEvaluationForm")
    private Epilepsy epilepsy;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL,mappedBy = "generalEvaluationForm")
    private PhysiotherapyPast physiotherapyPast;

}
