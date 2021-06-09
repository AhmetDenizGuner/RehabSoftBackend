package com.hacettepe.rehabsoft.service.implementations;

import com.hacettepe.rehabsoft.dto.AssignedFormDto;
import com.hacettepe.rehabsoft.dto.FormTemplateDto;
import com.hacettepe.rehabsoft.entity.*;
import com.hacettepe.rehabsoft.repository.*;
import com.hacettepe.rehabsoft.service.FormDynamicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service(value = "dynamicFormService")
public class FormDynamicServiceImpl implements FormDynamicService {

    @Autowired
    AssignedFormRepository assignedFormRepository;

    @Autowired
    FormFieldRepository formFieldRepository;

    @Autowired
    FormFieldDefaultValueRepository formFieldDefaultValueRepository;

    @Autowired
    FormAnswersRepository formAnswersRepository;

    @Autowired
    FormTemplateRepository formTemplateRepository;

    @Autowired
    FormDynamicRepository formDynamicRepository;

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;


    @Override
    @Transactional(readOnly = true)
    public List<AssignedFormDto> getAssignedFormHistory(String tcKimlikNo) {
        List<AssignedForm> assignedFormList = assignedFormRepository.findAllByPatientOrderByCreationDateDesc(patientRepository.getPatientByTcKimlikNo(tcKimlikNo));
        List<AssignedFormDto> assignedFormDtoList =  Arrays.asList(modelMapper.map(assignedFormList, AssignedFormDto[].class));

        if( isAssignedFormDtoListEmpty(assignedFormDtoList)){
            log.warn("Belirtilen hastaya ait form-anket kaydı bulunmuyor");
            return Collections.emptyList();
        }

        return assignedFormDtoList;
    }

    @Override
    @Transactional
    public boolean assignForm(AssignedFormDto assignedFormDto, String tcKimlikNo) {
        log.warn("Dynamic Form assign Form metoduna girdi");

        AssignedForm assignedForm = modelMapper.map(assignedFormDto, AssignedForm.class);
        assignedForm.setPatient(patientRepository.getPatientByTcKimlikNo(tcKimlikNo));

        assignedFormRepository.save(assignedForm);

        for(FormField field : assignedForm.getFormDynamic().getFormFieldCollection()){
            field.setFormDynamic(assignedForm.getFormDynamic());
            formFieldRepository.save(field);
            if(field.getFieldType().equals("SECMELI") || field.getFieldType().equals("COKLU_SECMELI") ){
                for(FormFieldDefaultValue defaultValue : field.getFormFieldDefaultValueCollection()){
                    defaultValue.setFormField(field);
                    formFieldDefaultValueRepository.save(defaultValue);
                }
            }
        }

        return true;
        //diger logicleri buraya ekleyecegiz.Dönüs tipini string yapmak daha dogru olur!!!!
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignedFormDto> getAssignedFormNotAnswered(String tcKimlikNo) {
        List<AssignedForm> assignedFormList = assignedFormRepository.findAllByPatientAndIsAnsweredOrderByCreationDateDesc(patientRepository.getPatientByTcKimlikNo(tcKimlikNo),false);
        List<AssignedFormDto> assignedFormDtoList =  Arrays.asList(modelMapper.map(assignedFormList, AssignedFormDto[].class));

        if(isAssignedFormDtoListEmpty(assignedFormDtoList)){
            log.warn("Cevap bekleyen form-anket talebiniz bulunmuyor");
            return Collections.emptyList();
        }

        return assignedFormDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignedFormDto> getAssignedFormAnswered(String tcKimlikNo) {
        List<AssignedForm> assignedFormList = assignedFormRepository.findAllByPatientAndIsAnsweredOrderByCreationDateDesc(patientRepository.getPatientByTcKimlikNo(tcKimlikNo),true);
        List<AssignedFormDto> assignedFormDtoList =  Arrays.asList(modelMapper.map(assignedFormList, AssignedFormDto[].class));

        if(isAssignedFormDtoListEmpty(assignedFormDtoList)){
            log.warn("Cevapladığınız form-anket talebiniz bulunmuyor");
            return Collections.emptyList();
        }

        return assignedFormDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public AssignedFormDto getAssignedFormById(int id) {
        long lid = id;
        AssignedForm assignedForm = assignedFormRepository.findById(lid).get();
        AssignedFormDto assignedFormDto = modelMapper.map(assignedForm,AssignedFormDto.class);

        if (assignedFormDto == null){
            log.warn("Forma erişilemedi !!!");
            return null;
        }
        return assignedFormDto;
    }

    @Override
    @Transactional
    public boolean answerTheForm(AssignedFormDto assignedFormDto, String formID) {

        long lFormID = Integer.parseInt(formID);
        AssignedForm assignedFormWanswers = modelMapper.map(assignedFormDto, AssignedForm.class);
        AssignedForm assignedForm = assignedFormRepository.findById(lFormID).get();
        assignedForm.setFormAnswersCollection(assignedFormWanswers.getFormAnswersCollection());
        for(FormAnswers answer : assignedFormWanswers.getFormAnswersCollection()){
            answer.setAssignedForm(assignedForm);
            answer.setFormField(formFieldRepository.findById(answer.getFormField().getId()).get());
            formAnswersRepository.save(answer);
        }
        assignedForm.setAnswered(true);
        assignedFormRepository.save(assignedForm);

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormTemplateDto> getFormTemplatesbyDoctor(String userName){
        List<FormTemplate> formTemplates = formTemplateRepository.findByUserOrderByCreationDateDesc(userRepository.findByUsername(userName));
        List<FormTemplateDto> formTemplateDtoList = Arrays.asList(modelMapper.map(formTemplates,FormTemplateDto[].class));
        if(formTemplateDtoList==null || formTemplateDtoList.isEmpty()){
            log.warn("Henüz bir form örneği oluşturulmadı");
            return Collections.emptyList();
        }
        return formTemplateDtoList;
    }

    @Override
    @Transactional
    public boolean addFormTemplate(FormTemplateDto formTemplateDto,String userName){
        FormTemplate formTemplate = modelMapper.map(formTemplateDto, FormTemplate.class);

        FormDynamic formDynamic = formTemplate.getFormDynamic();
        formDynamicRepository.save(formDynamic);

        for(FormField field : formTemplate.getFormDynamic().getFormFieldCollection() ){
            field.setFormDynamic(formDynamic);
            formFieldRepository.save(field);
            if(field.getFieldType().equals("SECMELI") || field.getFieldType().equals("COKLU_SECMELI") ){
                for(FormFieldDefaultValue defaultValue : field.getFormFieldDefaultValueCollection()){
                    defaultValue.setFormField(field);
                    formFieldDefaultValueRepository.save(defaultValue);
                }
            }
        }

        formTemplate.setFormDynamic(formDynamic);
        formTemplate.setUser(userRepository.findByUsername(userName));
        formTemplateRepository.save(formTemplate);

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public FormTemplateDto findTemplateByID(int id){
        long lid = id;
        FormTemplate formTemplate = formTemplateRepository.findById(lid).get();
        FormTemplateDto formTemplateDto = modelMapper.map(formTemplate, FormTemplateDto.class);

        if (formTemplateDto == null){
            log.warn("Forma erişilemedi !!!");
            return null;
        }
        return formTemplateDto;

    }

    @Override
    @Transactional
    public boolean assignTemplateForm(String patientTcNo, String templateID){
        AssignedForm assignedForm = new AssignedForm();
        assignedForm.setAnswered(false);
        assignedForm.setPatient(patientRepository.getPatientByTcKimlikNo(patientTcNo));
        long lTemplateID = Integer.parseInt(templateID);
        assignedForm.setFormDynamic(formTemplateRepository.findById(lTemplateID).get().getFormDynamic());
        assignedFormRepository.save(assignedForm);
        return true;
    }

    public boolean isAssignedFormDtoListEmpty(List<AssignedFormDto> assignedFormDtoList){
        return assignedFormDtoList == null || assignedFormDtoList.isEmpty();
    }
}
