package com.hacettepe.rehabsoft.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.hacettepe.rehabsoft.entity.*;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Data Transfer Object for General Evaluation Form")
public class GeneralEvaluationFormDto {

    private String gender;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthDate;

    private Integer ageAsMonth;

    private Integer numberOfSiblings;

    private Integer whichChild;

    private Integer heightCm;

    private Long weightGr;

    private Integer headRoundCm;

    private Integer mothersGivenBirthAgeYear;

    private String mothersEducationLevel;

    private String typeOfPregnancy;

    private String fathersEducationLevel;

    private Integer multiplePregnancy;

    private Boolean isRelativeMarriage;

    private Boolean isBloodIncompatibility;

    private Integer birthWeek;

    private String birthType;

    private Integer birthWeight;

    private Long birthHeadAroundCm;

    private Integer apgarScore;

    private Boolean isBirthAnoxia;

    private Boolean isBirthEmpurpling;

    private Boolean isBirthCrying;

    private Boolean isHighBloodPressorPregnancy;

    private String pregnancyInfectionInfo;

    private String pregnancyMedicineUsageInfo;

    private Boolean isPregnancyDrinking;

    private Boolean isPregnanycSmoking;

    private Boolean oxygenSupport;


    private Long id;//ilk olarak bos olacak.Servisin save metodunda atama yapılacak

    private Integer intensiveCare;

    private Boolean isNewbornRetinopathy;

    private Boolean isRespiratuvarDistressSyndrom;

    private Boolean isBronchopulmonaryDysplasia;

    private Boolean isHypoglycaemia;

    //DiseaseOfMotherPregnancy
    private DiseaseOfMotherPregnancyDto diseaseOfMotherPregnancy;


    //Hyperbilirubinemia
    //private Boolean isPhototeraphy;
    //private Integer hospitalDayTime;
    private HyperbilirubinemiaDto hyperbilirubinemia;


    //AfterBirthReasonCerebralPalsy
    private AfterBirthReasonCerebralPalsyDto afterBirthReasonCerebralPalsy;

    //Botox_Treatment
    private BotoxTreatmentDto botoxTreatment;



    //Many To Many
    private Collection<AppliedSurgeryDto> appliedSurgeryCollection;


    //One to many
    private Collection<OrthesisInfoDto> orthesisInfoCollection;
    private Collection<OtherOrthesisInfoDto> otherOrthesisInfoCollection;
    private Collection<UsedMedicineDto> usedMedicineCollection;

    //ManyToMany
    private Collection<CoexistingDiseasesDto> coexistingDiseasesCollection;



    //VisualImpairement
    private VisualImpairmentDto visualImpairment;

    //OneTomany

    //HearingImpairment
    //private Boolean isUseHearingAid;
    private HearingImpairmentDto hearingImpairment;

    //ExpectationsAboutProgram
    private Collection<ExpectationsAboutProgramDto> expectationsAboutProgramCollection;

    private EpilepsyDto epilepsy;

    private PhysiotherapyPastDto physiotherapyPast;

}
