import { Optional } from "@angular/core";
import { Course } from "./course";

export interface Teacher{
    Id: number;
    email: string;
    firstName: string;
    lastName: string;
    dateOfBirth:string;
    courses: Array<Course>;

}