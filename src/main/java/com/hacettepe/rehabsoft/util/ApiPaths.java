package com.hacettepe.rehabsoft.util;

public class ApiPaths {
    // ****** Requestler icin ****** //
    private static final String BASE_PATH = "/api";

    // ****** File operaitonlar icin ****** //
    // Localde kullanilicak
    // private static final String CLASS_PATH = "./src/main/resources";
    // Prodda kullanilicak
    private static final String CLASS_PATH = ".";

    //    public static final String LOCAL_CLIENT_BASE_PATH = "http://localhost:4200";
    //    public static final String LOCAL_CLIENT_BASE_PATH = "https://rehabsoft.netlify.app";
        public static final String LOCAL_CLIENT_BASE_PATH = "https://rehabsoft.herokuapp.com";

    public static final class AdminPath {
        public static final String CTRL = BASE_PATH + "/admin";
    }

    public static final class UserPath {
        public static final String CTRL = BASE_PATH + "/users";
    }

    public static final class DoctorPath {
        public static final String CTRL = BASE_PATH + "/doctor";
    }

    public static final class GeneralEvaluationFormPath {
        public static final String CTRL = BASE_PATH + "/patient/generalevaluationform";
    }

    public static final class PatientFormPath {
        public static final String CTRL = BASE_PATH + "/patient";
    }


    public static final class SavingBotoxImagePath {
        public static final String CTRL = CLASS_PATH+"/static/generalevaluationform/botoximage/";
    }

    public static final class SavingAppliedSurgeryImagePath {
        public static final String CTRL = CLASS_PATH+"/static/generalevaluationform/appliedsurgeryimage/";
    }


    public static final class ExercisePath {
        public static final String CTRL = BASE_PATH + "/exercises";
    }

    public static final class SavingOtherOrthesisImagePath {
        public static final String CTRL = CLASS_PATH+"/static/generalevaluationform/otherorthesisimage/";
    }

    public static final class SavingExerciseMediaPath {
        public static final String CTRL = CLASS_PATH+"/static/exercise/";
    }

    public static final class SavingResponseVideoRequestPath {
        public static final String CTRL = CLASS_PATH+"/static/responsevideorequest/";
    }

    public static final class Profile {
        public static final String CTRL = BASE_PATH + "/profile";
    }

    public static final class NotificationPath {
        public static final String CTRL = BASE_PATH + "/notification";
    }

    public static final class VideoRequestPath {
        public static final String CTRL = BASE_PATH + "/video-request";
    }

    public static final class ResponseVideoRequestPath {
        public static final String CTRL = BASE_PATH + "/response-video-request";
    }



    public static final class MessagePath {
        public static final String CTRL = BASE_PATH + "/message";
    }

    public static final class OnlineMeetingWebSocket {
        public static final String CTRL = BASE_PATH + "/websocket/online-meeting";
    }
    public static final class OnlineMeetingPath {
        public static final String CTRL = BASE_PATH + "/online-meeting";
    }
}
