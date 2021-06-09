package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.FormTemplate;
import com.hacettepe.rehabsoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormTemplateRepository extends JpaRepository<FormTemplate,Long> {
    List<FormTemplate> findByUserOrderByCreationDateDesc(User user);
}
