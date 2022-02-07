import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CoursesService } from 'src/app/core/service/courses.service';
import { CreditService } from 'src/app/core/service/credit.service';
import { StudentsService } from 'src/app/core/service/students.service';
import { Course } from 'src/app/shared/course';
import { Credit } from 'src/app/shared/credit';
import { Student } from 'src/app/shared/student';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {
  public students!: Student[];
  public editStudent!: Student;
  public addCourse!: Student;
  public deleteStudent!: Student;
  public deleteCourse?: Student;
  public course_id?: any;
  public credit_id?: any;
  public courses?: Course[];
  public coursesOfStudentNotAttend?: Course[];
  public creditsOfStudent?: Credit[];


  constructor(private studentsService: StudentsService,private  coursesService: CoursesService,private creditService: CreditService){}
  ngOnInit(): void {
    this.getStudents();
    this.getCourses();
    this.getCoursesOfStudentNotAttend();
  }
  public getStudents():void{
    this.studentsService.getStudents().subscribe( 
      (response: Student[]) =>{
        this.students = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
      
    )
  }
  public getCourses():void{
    this.coursesService.getCourses().subscribe( 
      (response: Course[]) =>{
        this.courses = response;

      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
      
    )
  }
  
  public onAddStudent(addForm:NgForm):void{
    document.getElementById('add-students-form')?.click();
    console.log(addForm.value);

    this.studentsService.addStudent(addForm.value).subscribe(
      (response: Student) =>{
        this.getStudents();
        this.getCourses();
      },
      (error:HttpErrorResponse)=>{
        alert(error.message);
        addForm.reset();
      }
    )
  }
  public onAddCourseToStudent(course_id:number, addForm:NgForm):void{
    document.getElementById('add-students-form')?.click();
    this.studentsService.addCourseToStudent(course_id, addForm.value).subscribe(
      (response: Student) =>{
        this.getStudents();
        this.getCourses();
      },
      (error:HttpErrorResponse)=>{
        alert(error.message);
        addForm.reset();
      }
    )
  }
  public onUpdateStudent(student: Student): void {     
    this.studentsService.updateStudent(student).subscribe(
      (response: Student) => {
        this.getStudents();
        this.getCourses();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onDeleteCourseFromStudent(creditId: number): void {
    this.creditService.onDeleteCourseFromStudent(creditId).subscribe(
      (response: void) => {
        console.log(response);
        this.getStudents();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onDeleteStudent(studentId: number): void {
    this.studentsService.deleteStudent(studentId).subscribe(
      (response: void) => {
        console.log(response);
        this.getStudents();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onOpenModal(student: Student | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addStudentModal');
    }
    if (mode === 'edit') {     
      if(student !=null)
      this.editStudent = student;
      button.setAttribute('data-target', '#editStudentModal');
    }
    if (mode === 'delete') {
      if(student !=null)
      this.deleteStudent = student;
      button.setAttribute('data-target', '#deleteStudentModal');
    }
    if (mode === 'addCourse') {
      if(student !=null)
      this.addCourse = student;
      button.setAttribute('data-target', '#addCourseStudentModal');
    }
    if (mode === 'deleteCourse') {
      if(student !=null)
      this.creditsOfStudent = student.credits;
      button.setAttribute('data-target', '#deleteCourseStudentModal');
    }
    if(container!=null) container.appendChild(button);
    button.click();
  }
  public getCoursesId(event: any):void{
    
    this.course_id = event.target.value;
    
    console.log(this.course_id);

    if(this.creditsOfStudent != undefined)
   { 
     console.log('hej');
     
     for (let i = 0; i < this.creditsOfStudent.length; i++)
     {
        if(this.creditsOfStudent[i].course.id !== this.course_id)
        {
          this.coursesOfStudentNotAttend?.push(this.creditsOfStudent[i].course)
        }
        
     } 
    }

  }
  public getCoursesOfStudentNotAttend():void{

  }
  public getCreditId(event: any):void{
    this.credit_id = event.target.value;
    console.log(this.course_id);

  }

}
