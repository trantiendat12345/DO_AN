import type { Teacher } from "./Teacher";

export interface AssignTeacher {
    name: string;
    mainTeacher: Teacher;
    assistantTeacher: Array<Teacher>;
}
