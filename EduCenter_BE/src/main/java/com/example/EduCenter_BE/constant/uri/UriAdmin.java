package com.example.EduCenter_BE.constant.uri;

public class UriAdmin {

    // URI
    public final static String ADMIN = "/admin";

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

    public final static  String GET_STUDENT_BY_CODE = "/get-student/{code}";

    // URI PARAM UPDATE
    public final static String UPDATE_STUDENT = "/update-student/{code}";

    // URI PARAM DELETE
    public final static String DELETE_STUDENT = "/delete-student/{code}";

}
