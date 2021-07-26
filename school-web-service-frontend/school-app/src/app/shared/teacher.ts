import { Optional } from "@angular/core";
import { Course } from "./course";

export interface Teacher{
    id: number;
    email: String;
    firstName: String;
    lastName: String;
    dateOfBirth:String;
    courses: Array<Course>;

}