package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.enums.Gender;
import com.example.EduCenter_BE.constant.enums.StudentStatus;
import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Student;
import com.example.EduCenter_BE.exception.BusinessException;
import com.example.EduCenter_BE.repository.StudentRepository;
import com.example.EduCenter_BE.request.student.CreateStudentRequest;
import com.example.EduCenter_BE.request.student.UpdateStudentRequest;
import com.example.EduCenter_BE.response.StudentResponse;
import com.example.EduCenter_BE.service.interfaces.StudentService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(CreateStudentRequest request) {
        String codeStudent = request.getStudentCode();
        String emailStudent = request.getEmail();
        String phoneStudent = request.getPhone();

        Student checkStudentByCode = studentRepository.findStudentByCode(codeStudent);
        Student checkStudentByEmail = studentRepository.findStudentByEmail(emailStudent);
        Student checkStudentByPhone = studentRepository.findStudentByPhone(phoneStudent);

        if (!Objects.isNull(checkStudentByCode) || !Objects.isNull(checkStudentByEmail)
                || !Objects.isNull(checkStudentByPhone)) {
            throw new BusinessException(Message.STUDENT_EXISTED);
        }

        Student student = new Student();
        student.setStudentCode(request.getStudentCode());
        student.setFullName(request.getFullName());
        student.setDateOfBirth(request.getDob());
        student.setGender(request.getGender());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setEmail(request.getEmail());
        student.setStatus(request.getStatus());
        student.setLevel(request.getLevel());
        student.setNote(request.getNote());

        return studentRepository.save(student);
    }

    @Override
    public Page<StudentResponse> getAllStudents(Pageable pageable) {
        return studentRepository.findAllStudents(pageable).map(StudentResponse::new);
    }

    @Override
    public StudentResponse getStudentByCode(String studentCode) {
        Student student = studentRepository.findStudentByCode(studentCode);

        if (Objects.isNull(student)) {
            throw new BusinessException(Message.STUDENT_DOES_NOT_EXIST);
        }

        return new StudentResponse(student);
    }

    @Override
    public StudentResponse updateStudent(UpdateStudentRequest request, String studentCode) {
        Student student = studentRepository.findStudentByCode(studentCode);
        Student email = studentRepository.findStudentByEmail(request.getEmail());
        Student phone = studentRepository.findStudentByPhone(request.getPhone());

        if (Objects.isNull(student)) {
            throw new BusinessException(Message.STUDENT_DOES_NOT_EXIST);
        }

        if (email != null && !email.getId().equals(student.getId())) {
            throw new BusinessException(Message.EMAIL_EXISTED);
        }

        if (phone != null && !phone.getId().equals(student.getId())) {
            throw new BusinessException(Message.PHONE_EXISTED);
        }

        student.setFullName(request.getFullName());
        student.setDateOfBirth(request.getDob());
        student.setGender(request.getGender());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setEmail(request.getEmail());
        student.setStatus(request.getStatus());
        student.setLevel(request.getLevel());
        student.setNote(request.getNote());

        student.setUpdatedBy(1L);

        Student updatedStudent = studentRepository.save(student);

        return new StudentResponse(updatedStudent);
    }

    @Override
    public String deleteStudent(String studentCode) {
        Student student = studentRepository.findStudentByCode(studentCode);

        if (Objects.isNull(student)) {
            throw new BusinessException(Message.STUDENT_DOES_NOT_EXIST);
        }

        student.setIsDeleted(true);
        student.setDeletedAt(LocalDateTime.now());
        student.setDeletedBy(1L);
        studentRepository.save(student);

        return Message.DELETED_SUCCESSFULLY;
    }

    @Override
    public Long totalStudentCount() {
        return studentRepository.countAllStudents();
    }

    @Override
    public String importStudents(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException("File không được để trống");
        }

        // Validate file extension
        String filename = file.getOriginalFilename();
        if (filename == null || (!filename.endsWith(".xlsx") && !filename.endsWith(".xls"))) {
            throw new BusinessException("File phải có định dạng .xlsx hoặc .xls");
        }

        List<Student> students = new ArrayList<>();
        int successCount = 0;
        int errorCount = 0;
        StringBuilder errorMessages = new StringBuilder();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            // Skip header row (row 0) and start from row 1
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)
                    continue;

                try {
                    Student student = parseStudentFromRow(row, i);
                    if (student != null) {
                        students.add(student);
                        successCount++;
                    }
                } catch (Exception e) {
                    errorCount++;
                    errorMessages.append("Dòng ").append(i + 1).append(": ").append(e.getMessage()).append("; ");
                }
            }

            // Save all valid students
            if (!students.isEmpty()) {
                studentRepository.saveAll(students);
            }

        } catch (IOException e) {
            throw new BusinessException("Lỗi khi đọc file Excel: " + e.getMessage());
        }

        if (errorCount > 0) {
            return String.format("Import hoàn tất: %d thành công, %d lỗi. Chi tiết lỗi: %s",
                    successCount, errorCount, errorMessages.toString());
        }

        return String.format("Import thành công %d học viên", successCount);
    }

    private Student parseStudentFromRow(Row row, int rowIndex) {
        try {
            // Column 0: Họ và tên (required)
            String fullName = getCellValueAsString(row.getCell(0));
            if (fullName == null || fullName.trim().isEmpty()) {
                throw new BusinessException("Họ và tên không được để trống");
            }

            // Column 1: Ngày sinh (dd/MM/yyyy)
            LocalDate dob = parseDateCell(row.getCell(1));
            if (dob == null) {
                throw new BusinessException("Ngày sinh không hợp lệ");
            }

            // Column 2: Giới tính (Nam/Nữ)
            String genderStr = getCellValueAsString(row.getCell(2));
            Gender gender = parseGender(genderStr);

            // Column 3: Số điện thoại
            String phone = getCellValueAsString(row.getCell(3));
            if (phone == null || phone.trim().isEmpty()) {
                throw new BusinessException("Số điện thoại không được để trống");
            }

            // Check if phone already exists
            Student existingByPhone = studentRepository.findStudentByPhone(phone);
            if (existingByPhone != null) {
                throw new BusinessException("Số điện thoại đã tồn tại");
            }

            // Column 4: Email
            String email = getCellValueAsString(row.getCell(4));
            if (email == null || email.trim().isEmpty()) {
                throw new BusinessException("Email không được để trống");
            }

            // Check if email already exists
            Student existingByEmail = studentRepository.findStudentByEmail(email);
            if (existingByEmail != null) {
                throw new BusinessException("Email đã tồn tại");
            }

            // Column 5: Địa chỉ
            String address = getCellValueAsString(row.getCell(5));

            // Column 6: Trình độ
            String level = getCellValueAsString(row.getCell(6));

            // Create student
            Student student = new Student();
            student.setStudentCode(generateStudentCode());
            student.setFullName(fullName.trim());
            student.setDateOfBirth(dob);
            student.setGender(gender);
            student.setPhone(phone.trim());
            student.setEmail(email.trim());
            student.setAddress(address != null ? address.trim() : "");
            student.setLevel(level != null ? level.trim() : "");
            student.setStatus(StudentStatus.ACTIVE);
            student.setNote("Import từ Excel");

            return student;

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }
                // If it's a numeric value that should be a string (like phone number)
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    private LocalDate parseDateCell(Cell cell) {
        if (cell == null) {
            return null;
        }

        try {
            if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                return cell.getLocalDateTimeCellValue().toLocalDate();
            } else if (cell.getCellType() == CellType.STRING) {
                String dateStr = cell.getStringCellValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(dateStr, formatter);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private Gender parseGender(String genderStr) {
        if (genderStr == null || genderStr.trim().isEmpty()) {
            return Gender.OTHER;
        }

        String normalized = genderStr.trim().toLowerCase();
        if (normalized.equals("nam") || normalized.equals("male")) {
            return Gender.MALE;
        } else if (normalized.equals("nữ") || normalized.equals("nu") || normalized.equals("female")) {
            return Gender.FEMALE;
        }
        return Gender.OTHER;
    }

    private String generateStudentCode() {
        // Generate unique student code: SV + timestamp + random
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(7);
        String random = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        return "SV" + timestamp + random;
    }

}
