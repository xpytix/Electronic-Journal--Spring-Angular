import { Course } from "./course";
import { Student } from "./student";

export interface Credit{
    id: number;
    grade: number;
    attempt: boolean;
    student: Student;
    course: Course;
}