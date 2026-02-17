export interface Score {
    id: number;
    studentCode: string;
    studentName: string;
    classroomName: string;
    type: ScoreType;
    value: number;
    note: string;
}

export type ScoreType = "HOMEWORK" | "TEST" | "MIDTERM" | "FINAL";

export const ScoreTypeLabels: Record<ScoreType, string> = {
    HOMEWORK: "Bài tập",
    TEST: "Kiểm tra",
    MIDTERM: "Giữa kỳ",
    FINAL: "Cuối kỳ",
};
