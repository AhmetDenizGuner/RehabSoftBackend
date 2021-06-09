DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE SEQUENCE roles_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE roles_seq
    OWNER TO postgres;

CREATE SEQUENCE public.users_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.users_seq
    OWNER TO postgres;

CREATE SEQUENCE public.doctor_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.doctor_seq
    OWNER TO postgres;

CREATE SEQUENCE public.admins_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.admins_seq
    OWNER TO postgres;

CREATE SEQUENCE public.phone_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.phone_seq
    OWNER TO postgres;

CREATE SEQUENCE public.il_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.il_seq
    OWNER TO postgres;

CREATE SEQUENCE public.ilce_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.ilce_seq
    OWNER TO postgres;

CREATE SEQUENCE public.parent_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.parent_seq
    OWNER TO postgres;

CREATE SEQUENCE public.general_evaluation_form_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.general_evaluation_form_seq
    OWNER TO postgres;

CREATE SEQUENCE public.disease_of_mother_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.disease_of_mother_seq
    OWNER TO postgres;

CREATE SEQUENCE public.hyperbilirubinemia_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.hyperbilirubinemia_seq
    OWNER TO postgres;

CREATE SEQUENCE public.after_birth_reason_celebral_palsy_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.after_birth_reason_celebral_palsy_seq
    OWNER TO postgres;

CREATE SEQUENCE public.botox_treatment_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.botox_treatment_seq
    OWNER TO postgres;

CREATE SEQUENCE public.applied_surgery_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.applied_surgery_seq
    OWNER TO postgres;

CREATE SEQUENCE public.patient_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.patient_seq
    OWNER TO postgres;



CREATE SEQUENCE public.orthesis_info_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.orthesis_info_seq
    OWNER TO postgres;



CREATE SEQUENCE public.other_orthesis_info_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.orthesis_info_seq
    OWNER TO postgres;


CREATE SEQUENCE public.used_medicine_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.used_medicine_seq
    OWNER TO postgres;

CREATE SEQUENCE public.coexisting_disease_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.coexisting_disease_seq
    OWNER TO postgres;

CREATE SEQUENCE public.visual_impairment_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.visual_impairment_seq
    OWNER TO postgres;

CREATE SEQUENCE public.expactations_about_program_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.expactations_about_program_seq
    OWNER TO postgres;

CREATE SEQUENCE public.disease_of_mother_pregnancy_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.disease_of_mother_pregnancy_seq
    OWNER TO postgres;

CREATE SEQUENCE public.after_birth_reason_cerebral_palsy_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.after_birth_reason_cerebral_palsy_seq
    OWNER TO postgres;

CREATE SEQUENCE public.exercise_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.exercise_seq
    OWNER TO postgres;

CREATE SEQUENCE public.exercise_image_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.exercise_image_seq
    OWNER TO postgres;


CREATE SEQUENCE public.exercise_video_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.exercise_video_seq
    OWNER TO postgres;


CREATE SEQUENCE public.hearing_impairment_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.hearing_impairment_seq
    OWNER TO postgres;

CREATE SEQUENCE public.expectations_about_program_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.expectations_about_program_seq
    OWNER TO postgres;

CREATE SEQUENCE public.form_field_default_value_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.form_field_default_value_seq
    OWNER TO postgres;

CREATE SEQUENCE public.form_field_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.form_field_seq
    OWNER TO postgres;

CREATE SEQUENCE public.form_dynamic_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.form_dynamic_seq
    OWNER TO postgres;

CREATE SEQUENCE public.form_template_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.form_template_seq
    OWNER TO postgres;

CREATE SEQUENCE public.assigned_form_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.assigned_form_seq
    OWNER TO postgres;

CREATE SEQUENCE public.form_answers_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.form_answers_seq
    OWNER TO postgres;

--new

CREATE SEQUENCE public.epilepsy_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.epilepsy_seq
    OWNER TO postgres;
--new

CREATE SEQUENCE public.physiotherapy_past_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.physiotherapy_past_seq
    OWNER TO postgres;
--new

CREATE SEQUENCE public.physiotherapy_central_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.physiotherapy_central_seq
    OWNER TO postgres;
--- tables start----





CREATE TABLE public."roles"
(
    "id" bigint NOT NULL default nextval('roles_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    role_name character varying(255),
    PRIMARY KEY ("id")
);

CREATE TABLE public."users"
(
    "id" bigint NOT NULL default nextval('users_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    username character varying(100),
    user_password character varying(100),
    role_id bigint,
    email character varying(320),
    reset_password_token character varying(320),
    firstname character varying(100),
    surname character varying(100),
    PRIMARY KEY ("id"),
    FOREIGN KEY (role_id)
        REFERENCES public."roles" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE public.admins
(
    "id" bigint NOT NULL default nextval('admins_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    user_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (user_id)
        REFERENCES public."users" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.doctor
(
    "id" bigint NOT NULL default nextval('doctor_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    user_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (user_id)
        REFERENCES public."users" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE public.parent
(
    "id" bigint NOT NULL default nextval('parent_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    "firstname" varchar(255),
    "surname" varchar(255),
    "email" varchar(255),
    parent_type varchar(255),
    primary key ("id")
);

CREATE TABLE public.phone
(
    "id" bigint NOT NULL default nextval('phone_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    phone_number varchar(255),
    parent_id bigint,
    primary key ("id"),
    FOREIGN KEY (parent_id)
        REFERENCES public."parent" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE public.il
(
    "id" bigint NOT NULL default nextval('il_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    il_name varchar(100),
    PRIMARY KEY ("id")
);

CREATE TABLE public.ilce
(
    "id" bigint NOT NULL default nextval('ilce_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    ilce_name varchar(100),
    il_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (il_id)
        REFERENCES public."il" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.patient
(
    "id" bigint NOT NULL default nextval('patient_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    tc_kimlik_no varchar(50),
    address varchar(1000),
    users_id bigint,
    ilce_id bigint,
    doctor_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (users_id)
        REFERENCES public."users" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (doctor_id)
        REFERENCES public."doctor" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (ilce_id)
        REFERENCES public.ilce ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.general_evaluation_form
(
    "id" bigint NOT NULL default nextval('general_evaluation_form_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    gender varchar(255),
    birth_date timestamp without time zone,
    age_as_months integer,
    number_of_siblings integer,
    which_child integer,
    height_cm integer,
    weight_gr bigint,
    head_round_cm integer,
    mothers_given_birth_age_year integer,
    mothers_education_level varchar(100),
    type_of_pregnancy varchar(50),
    fathers_education_level varchar(100),
    multiple_pregnancy integer,
    is_relative_marriage boolean,
    is_blood_incompatibility boolean,
    birth_week integer,
    birth_type varchar(50),
    --new
    birth_weight integer,
    birth_head_around_cm bigint,
    apgar_score integer,
    is_birth_anoxia boolean,
    is_birth_empurpling boolean,
    --new
    is_birth_crying boolean,
    is_high_blood_pressor_pregnancy boolean,
    pregnancy_infection_info varchar(1000),
    pregnancy_medicine_usage_info varchar(1000),
    is_pregnancy_drinking boolean,
    is_pregnanyc_smoking boolean,
    is_oxygen_support boolean,
    intensive_care integer,
    --new
    is_newborn_retinopathy boolean,
    --new
    is_respiratuvar_distress_syndrom boolean,
    --new
    is_bronchopulmonary_dysplasia boolean,
    --new
    is_hypoglycaemia boolean,
    patient_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (patient_id)
        REFERENCES public.patient ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);





CREATE TABLE public.parent_patient
(
    parent_id bigint,
    patient_id bigint,
    primary key (parent_id, patient_id),
    FOREIGN KEY (parent_id)
        REFERENCES public."parent" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (patient_id)
        REFERENCES public."patient" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.disease_of_mother_pregnancy
(
    "id" bigint NOT NULL default nextval('disease_of_mother_pregnancy_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    general_evaluation_form_id bigint,
    disease_name varchar(1000),
    PRIMARY KEY ("id"),
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.hyperbilirubinemia
(
    "id" bigint NOT NULL default nextval('hyperbilirubinemia_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    is_phototeraphy boolean,
    hospital_day_time integer,
    general_evaluation_form_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE public.after_birth_reason_cerebral_palsy
(
    "id" bigint NOT NULL default nextval('after_birth_reason_cerebral_palsy_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    occuring_month integer,
    cause varchar(255),
    cause_info varchar(255),
    general_evaluation_form_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.botox_treatment
(
    "id" bigint NOT NULL default nextval('botox_treatment_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    last_botox_date timestamp without time zone,
    botox_record_url varchar(255),
    general_evaluation_form_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.applied_surgery
(
    "id" bigint NOT NULL default nextval('applied_surgery_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    surgery_name varchar(255),
    epicrisis_image_url varchar(255),
    applying_date timestamp without time zone,
    general_evaluation_form_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.orthesis_info
(
    "id" bigint NOT NULL default nextval('orthesis_info_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    "right_part" boolean,
    "left_part" boolean,
    orthesis_name varchar(255),
    general_evaluation_form_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.other_orthesis_info
(
    "id" bigint NOT NULL default nextval('other_orthesis_info_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    orthesis_name varchar(255),
    --new
    orthesis_url varchar(255),
    general_evaluation_form_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.used_medicine
(
    "id" bigint NOT NULL default nextval('used_medicine_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    medicine_name varchar(255),
    general_evaluation_form_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.coexisting_disease
(
    "id" bigint NOT NULL default nextval('coexisting_disease_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    disease_name varchar(255),
    PRIMARY KEY ("id")
);

CREATE TABLE public.general_evaluation_form_coexisting_disease
(
    coexisting_disease_id bigint,
    general_evaluation_form_id bigint,
    PRIMARY KEY (coexisting_disease_id, general_evaluation_form_id),
    FOREIGN KEY (coexisting_disease_id)
        REFERENCES public."coexisting_disease" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.visual_impairment
(
    "id" bigint NOT NULL default nextval('visual_impairment_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    information varchar(1000),
    general_evaluation_form_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.hearing_impairment
(
    "id" bigint NOT NULL default nextval('hearing_impairment_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    is_use_hearing_aid boolean,
    general_evaluation_form_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.expectations_about_program
(
    "id" bigint NOT NULL default nextval('expectations_about_program_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    expectation_content varchar(1000),
    general_evaluation_form_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE public.exercise
(
    "id" bigint NOT NULL default nextval('exercise_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    exercise_name varchar(255),
    exercise_content varchar(255),
    user_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (user_id)
        REFERENCES public."users" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.exercise_video
(
    "id" bigint NOT NULL default nextval('exercise_video_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    video_url varchar(255),
    title varchar(255),
    exercise_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (exercise_id)
        REFERENCES public."exercise" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.exercise_image
(
    "id" bigint NOT NULL default nextval('exercise_image_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    image_url varchar(255),
    exercise_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (exercise_id)
        REFERENCES public."exercise" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.form_dynamic
(
    "id" bigint NOT NULL default nextval('form_dynamic_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    title varchar(255),
    explanation varchar(600),
    PRIMARY KEY ("id")
);

CREATE TABLE public.form_field
(
    "id" bigint NOT NULL default nextval('form_field_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    form_field_name varchar(600),
    field_type varchar(255),
	form_field_order bigint,
    form_dynamic_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (form_dynamic_id)
        REFERENCES public.form_dynamic ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.form_field_default_value
(
    "id" bigint NOT NULL default nextval('form_field_default_value_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    value_name varchar(255),
    form_field_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (form_field_id)
        REFERENCES public.form_field ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE public.assigned_form
(
    "id" bigint NOT NULL default nextval('assigned_form_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    is_answered boolean,
    form_dynamic_id bigint,
    patient_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (form_dynamic_id)
        REFERENCES public.form_dynamic ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (patient_id)
        REFERENCES public.patient ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE public.form_answers
(
    "id" bigint NOT NULL default nextval('form_answers_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    answer varchar(500),
    assigned_form_id bigint,
    form_field_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (assigned_form_id)
        REFERENCES public.assigned_form ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (form_field_id)
        REFERENCES public.form_field ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.form_template
(
    "id" bigint NOT NULL default nextval('form_template_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    form_dynamic_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (form_dynamic_id)
        REFERENCES public.form_dynamic ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
ALTER TABLE form_template
    ADD COLUMN user_id BIGINT;
ALTER TABLE form_template
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY (user_id) REFERENCES users (id);

--new
CREATE TABLE public.epilepsy
(
    "id" bigint NOT NULL default nextval('epilepsy_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    epilepsy_situation varchar(50),
    PRIMARY KEY ("id"),
    general_evaluation_form_id bigint,
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

--new
CREATE TABLE public.physiotherapy_past
(
    "id" bigint NOT NULL default nextval('physiotherapy_past_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    number_of_weekly_session integer,
    PRIMARY KEY ("id"),
    general_evaluation_form_id bigint,
    FOREIGN KEY (general_evaluation_form_id)
        REFERENCES public."general_evaluation_form" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

--new
CREATE TABLE public.physiotherapy_central
(
    "id" bigint NOT NULL default nextval('physiotherapy_central_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    physiotherapy_central_name varchar(255),
    physiotherapy_past_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (physiotherapy_past_id)
        REFERENCES public."physiotherapy_past" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


-- INSERT INTO doctor("version") VALUES (1);
-- Insert doctor into patient removed,assign doctor to patient use case activated

/*CREATE OR REPLACE FUNCTION add_doctor_procedure()
    RETURNS trigger AS
$BODY$
BEGIN
    new.doctor_id := 1;
    RETURN new;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE TRIGGER add_doctor
    BEFORE INSERT
    ON PATIENT
    FOR EACH ROW
EXECUTE PROCEDURE add_doctor_procedure(); */





---******************** version 0.2 ****************************---
CREATE SEQUENCE public.physiotherapy_program_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.physiotherapy_program_seq
    OWNER TO postgres;


CREATE SEQUENCE public.scheduled_exercise_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.scheduled_exercise_seq
    OWNER TO postgres;

CREATE TABLE public.physiotherapy_program
(
    "id" bigint NOT NULL default nextval('physiotherapy_program_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    is_active boolean,
    start_date timestamp without time zone,
    finish_date timestamp without time zone,
    goal varchar(500),
    doctor_id bigint,
    patient_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (doctor_id)
        REFERENCES public."doctor" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (patient_id)
        REFERENCES public."patient" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE public.scheduled_exercise
(
    "id" bigint NOT NULL default nextval('scheduled_exercise_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    scheduled_date  timestamp without time zone,
    is_applied boolean,
    physiotherapy_program_id bigint,
    exercise_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (physiotherapy_program_id)
        REFERENCES public."physiotherapy_program" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (exercise_id)
        REFERENCES public."exercise" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE SEQUENCE public.video_request_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.video_request_seq
    OWNER TO postgres;

CREATE TABLE public.video_request
(
    "id" bigint NOT NULL default nextval('video_request_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    request_content varchar(255),
    request_title varchar(100),
    doctor_id bigint,
    patient_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (doctor_id)
        REFERENCES public."doctor" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (patient_id)
        REFERENCES public."patient" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
CREATE TABLE public.exercise_video_request
(
    video_request_id bigint,
    exercise_id bigint,
    PRIMARY KEY (video_request_id,exercise_id ),
    FOREIGN KEY (video_request_id)
        REFERENCES public."video_request" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (exercise_id)
        REFERENCES public."exercise" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE SEQUENCE public.response_video_request_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.response_video_request_seq
    OWNER TO postgres;

CREATE TABLE public.response_video_request
(
    "id" bigint NOT NULL default nextval('response_video_request_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    response_content varchar(255),
    video_request_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (video_request_id)
        REFERENCES public."video_request" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE SEQUENCE public.requested_video_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.requested_video_seq
    OWNER TO postgres;

CREATE TABLE public.requested_video
(
    "id" bigint NOT NULL default nextval('requested_video_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    video_url varchar(255),
    response_video_request_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (response_video_request_id)
        REFERENCES public."response_video_request" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE SEQUENCE public.notification_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.notification_seq
    OWNER TO postgres;

CREATE TABLE public.notification
(
    "id" bigint NOT NULL default nextval('notification_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    notification_content varchar(255),
    notification_url varchar(255),
    notification_title varchar(100),
    status bigint,
    users_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (users_id)
        REFERENCES public."users" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

---******************** version 0.2 ****************************---







---******************** version 0.3 ****************************---

CREATE SEQUENCE public.message_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.message_seq
    OWNER TO postgres;

CREATE TABLE public.message
(
    "id" bigint NOT NULL default nextval('message_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    message_content varchar,
    message_title varchar(100),
    sender_users_id bigint,
    receiver_users_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (sender_users_id)
        REFERENCES public."users" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (receiver_users_id)
        REFERENCES public."users" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

---******************** version 0.3 ****************************---

---******************** version 0.5 ****************************---

CREATE SEQUENCE public.online_meeting_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.online_meeting_seq
    OWNER TO postgres;

CREATE TABLE public.online_meeting
(
    "id" bigint NOT NULL default nextval('online_meeting_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    doctor_user_id bigint,
    patient_user_id bigint,
    meeting_date timestamp without time zone,
    PRIMARY KEY ("id"),
    FOREIGN KEY (doctor_user_id)
        REFERENCES public."users" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (patient_user_id)
        REFERENCES public."users" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


---******************** version 0.5 ****************************---


CREATE SEQUENCE public.firebase_token_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.firebase_token_seq
    OWNER TO postgres;
CREATE TABLE public.firebase_token
(
    "id" bigint NOT NULL default nextval('firebase_token_seq'),
    creation_date timestamp without time zone,
    last_modified_date timestamp without time zone,
    "version" bigint,
    firebase_token_content character varying(255),
    user_id bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY (user_id)
        REFERENCES public."users" ("id") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
