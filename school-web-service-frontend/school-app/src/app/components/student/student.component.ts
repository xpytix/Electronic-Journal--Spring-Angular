import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'jquery';
import { CreditService } from 'src/app/core/service/credit.service';
import { StudentsService } from 'src/app/core/service/students.service';
import { Course } from 'src/app/shared/course';
import { Credit } from 'src/app/shared/credit';
import { Student } from 'src/app/shared/student';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {
  public students!: Student[];
  public student!: Student;
  public editStudent!:Student;
  public averageGrade!:number;
  public credits!: Credit[];

  

  error: any;

  constructor(
    private route:ActivatedRoute,
    private studentsService:StudentsService,
    private creditService:CreditService
  ) {}
  ngOnInit(): void {
    // get by id
    const studentId = Number(this.route.snapshot.paramMap.get('id'));
    this.getStudent(studentId);
    //get average
    this.averageGrade = this.student.credits.reduce((a, b) => a + b.grade, 0)/ this.student.credits.length;
    console.log('siemkla'+ this.averageGrade);

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
  public getStudent(studentId: number):void{
    this.studentsService.getStudent(studentId).subscribe(
      response => this.student = response,
      error => this.error = error
    )
  }
  public updateCourse(courseId: number):void{
    this.creditService.updateCourse(this.editStudent, courseId ).subscribe(
      (response: Credit) => {
        this.getStudent(this.editStudent.id);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onUpdateStudent(student: Student, studentId: number): void {     
    this.studentsService.updateStudent(student).subscribe(
      (response: Student) => {
        this.getStudent(studentId);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onDeleteCourseFromStudent(creditId: any):void{

    
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
}
