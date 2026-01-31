package com.example.EduCenter_BE.request.student;

import com.example.EduCenter_BE.constant.enums.Gender;
import com.example.EduCenter_BE.constant.enums.StudentStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateStudentRequest {

    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")
    private String fullName;

    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate dob;

    private Gender gender;

    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$", message = "Số điện thoại không hợp lệ")
    private String phone;

    @Email(message = "Email không hợp lệ")
    private String email;

    @Size(max = 255, message = "Địa chỉ không được vượt quá 255 ký tự")
    private String address;

    private StudentStatus status;

    @Size(max = 50, message = "Trình độ không được vượt quá 50 ký tự")
    private String level;

    @Size(max = 500, message = "Ghi chú không được vượt quá 500 ký tự")
    private String note;

}
