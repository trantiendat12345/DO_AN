export interface Teacher {
    teacherCode: string;
    fullName: string;
    phone: string;
    email: string;
    status: string;
    note: string;
}

// Thông tin giáo viên cho Teacher Portal
export interface TeacherInfo {
    teacherCode: string;
    fullName: string;
    phone: string;
    email: string;
    status: string;
    totalClasses: number;
    totalStudents: number;
}

// Thông tin lớp học của giáo viên
export interface TeacherClassroom {
    id: number;
    name: string;
    courseName: string;
    startDate: string;
    endDate: string;
    maxStudent: number;
    currentStudent: number;
    status: string;
    teacherRole: string;
}

// Lịch dạy của giáo viên
export interface TeacherSchedule {
    id: number;
    classroomName: string;
    date: string;
    startTime: string;
    endTime: string;
}

// Học viên trong lớp
export interface StudentInClass {
    studentCode: string;
    fullName: string;
    dob: string;
    gender: string;
    phone: string;
    email: string;
    address: string;
    status: string;
    level: string;
    note: string;
    classroomName: string;
}

// Điểm trong lớp
export interface ScoreInClass {
    id: number;
    studentCode: string;
    studentName: string;
    classroomName: string;
    type: string;
    value: number;
    note: string;
}
