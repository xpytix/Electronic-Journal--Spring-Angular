import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CreditService } from 'src/app/core/service/credit.service';
import { StudentsService } from 'src/app/core/service/students.service';
import { TeachersService } from 'src/app/core/service/teachers.service';
import { Teacher } from 'src/app/shared/teacher';

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})
export class TeacherComponent implements OnInit {
  public teachers!: Teacher[];
  public teacher!: Teacher;
  error: any;
  constructor(
    private route:ActivatedRoute,
    private studentsService:StudentsService,
    private creditService:CreditService,
    private teachersService: TeachersService){}
  ngOnInit(): void {
    // get by id
    const teacherId = Number(this.route.snapshot.paramMap.get('id'));
    this.getTeacher(teacherId);
    console.log(this.teacher);
    
    this.getTeachers();


  }
  public getTeachers():void{
    this.teachersService.getTeachers().subscribe( 
      (response: Teacher[]) =>{
        this.teachers = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
      
    )
  }
  public getTeacher(teacherId: number):void{
    this.teachersService.getTeacher(teacherId).subscribe(
      response => this.teacher = response,
      error => this.error = error
    )
  }
}
