import { Credit } from "./credit";
import { Teacher } from "./teacher";

export interface Course{
    id: number;
    name: String;
    teacher: Teacher;
    credits: Array<Credit>;
}