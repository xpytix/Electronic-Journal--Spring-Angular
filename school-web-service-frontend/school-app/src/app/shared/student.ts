import { Optional } from "@angular/core";
import { Course } from "./course";
import { Credit } from "./credit";

export interface Student{
    id: number;
    email: String;
    firstName: String;
    lastName: String;
    dateOfBirth:String;
    credits: Array<Credit>;

}