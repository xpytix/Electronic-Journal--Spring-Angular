import { Optional } from "@angular/core";
import { Course } from "./course";
import { User } from "./user";

export interface Teacher{
    id: number;
    email: String;
    firstName: String;
    lastName: String;
    dateOfBirth:String;
    courses: Array<Course>;
    user: User;
}