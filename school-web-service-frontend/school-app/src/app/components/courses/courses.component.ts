import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CoursesService } from 'src/app/core/service/courses.service';
import { TeachersService } from 'src/app/core/service/teachers.service';
import { Course } from 'src/app/shared/course';
import { Teacher } from 'src/app/shared/teacher';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  public courses!: Course[];
  public teachers!: Teacher[];
  public teacher_Id!: any;
  public editCourse!: Course;
  public deleteCourse!: Course;

  constructor(private coursesService: CoursesService, private teachersService: TeachersService){}
  ngOnInit(): void {
    this.getCourses();
    this.getTeachers();
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
  public onAddCourse(teacherId:number,addForm:NgForm):void{
    document.getElementById('add-course-form')?.click();
    this.coursesService.addCourseWithTeacher(teacherId,addForm.value).subscribe(
      (response: Course) =>{
        this.getCourses();
        this.getTeachers();
      },
      (error:HttpErrorResponse)=>{
        alert(error.message);
        addForm.reset();
      }
    )
  }
  public onUpdateCourse(teacherId:number,course: Course): void {     
    this.coursesService.updateCourse(teacherId, course).subscribe(
      (response: Course) => {
        this.getCourses();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteCourse(courseId: number): void {
    this.coursesService.deleteCourse(courseId).subscribe(
      (response: void) => {
        console.log(response);
        this.getCourses();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onOpenModal(course: Course | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addCourseModal');
    }
    if (mode === 'edit') {     
      if(course !=null)
      this.editCourse = course;
      button.setAttribute('data-target', '#editCourseModal');
    }
    if (mode === 'delete') {
      if(course !=null)
      this.deleteCourse = course;
      button.setAttribute('data-target', '#deleteCourseModal');
    }
    
    if(container!=null) container.appendChild(button);
    button.click();
  }

  public getTeacherId(event: any):void{
    this.teacher_Id = event.target.value;
    console.log(this.teacher_Id);
  }

  
}

