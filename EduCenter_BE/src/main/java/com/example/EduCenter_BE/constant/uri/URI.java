package com.example.EduCenter_BE.constant.uri;

public class URI {

    // URI
    public final static String AUTH = "/api/auth";

    public final static String ADMIN = "/admin";

    //LOGIN
    public final static String LOGIN = "/login";

    //URI PARAM CREATE
    public final static String CREATE_STUDENT = "/create-student";

    public final static String CREATE_ACCOUNT = "/create-account";

    public final static String CREATE_ROLE = "/create-role";

    public final static String CREATE_COURSE = "/create-course";

    public final static String CREATE_PARENT = "/create-parent";

    public final static String CREATE_TEACHER = "/create-teacher";

    public final static String CREATE_CLASSROOM = "/create-classroom";

    // URI PARAM ADD
    public final static String ADD_STUDENT_TO_CLASSROOM = "/add-student-to-classroom";

    // URI PARAM GET
    public final static String GET_ALL_STUDENTS = "/get-all-students";

    public final static String GET_STUDENT_BY_CODE = "/get-student/{code}";

    public final static String GET_ALL_TEACHERS = "/get-all-teachers";

    public final static String GET_TEACHER_BY_CODE = "/get-teacher/{code}";

    public final static String GET_ALL_CLASSROOMS = "/get-all-classrooms";

    public final static String GET_CLASSROOM_BY_NAME = "/get-classroom/{name}";

    public final static String GET_ALL_ACCOUNTS = "/get-all-accounts";

    public final static String GET_ACCOUNT_BY_CODE = "/get-account";

    public final static String GET_ACCOUNT_BY_USERNAME = "/get-account/{username}";

    public final static String GET_ALL_COURSES = "/get-all-courses";

    public final static String GET_COURSE_BY_NAME = "/get-course/{name}";

    // TOTAL
    public final static String TOTAL_STUDENT = "/total-student";

    public final static String TOTAL_TEACHER = "/total-teacher";

    public final static String TOTAL_CLASSROOM = "/total-classroom";

    public final static String TOTAL_PARENT = "/total-parent";

    // URI PARAM UPDATE
    public final static String UPDATE_STUDENT = "/update-student/{code}";

    public final static String UPDATE_TEACHER = "/update-teacher/{code}";

    public final static String UPDATE_CLASSROOM = "/update-classroom/{name}";

    public final static String UPDATE_ACCOUNT = "/update-account/{username}";

    public final static String UPDATE_COURSE = "/update-course/{nameCourse}";

    // URI PARAM DELETE
    public final static String DELETE_STUDENT = "/delete-student/{code}";

    public final static String DELETE_TEACHER = "/delete-teacher/{code}";

    public final static String DELETE_CLASSROOM = "/delete-classroom/{name}";

    public final static String DELETE_ACCOUNT = "/delete-account/{username}";

    public final static String DELETE_COURSE = "/delete-course/{nameCourse}";

}
