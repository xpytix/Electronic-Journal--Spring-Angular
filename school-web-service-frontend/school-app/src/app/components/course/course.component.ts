import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CoursesService } from 'src/app/core/service/courses.service';
import { CreditService } from 'src/app/core/service/credit.service';
import { Course } from 'src/app/shared/course';
import { Credit } from 'src/app/shared/credit';
@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
  public course!: Course;
  error: any;
  public courses!: Course[];
  public courseCredits!: Credit[];
  constructor(
    private route:ActivatedRoute,
    private coursesService:CoursesService,
    private creditService: CreditService
  ) {}

  ngOnInit(): void {
    this.getCourses();
    const courseId = Number(this.route.snapshot.paramMap.get('id'));
    this.getCourse(courseId);
  }

  public getCourse(courseId: number):void{
    this.coursesService.getCourse(courseId).subscribe(
      response => this.course = response,
      error => this.error = error    )
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
  
  public deleteCourse(creditId: number):void{
    console.log(creditId);
   
    this.creditService.onDeleteCourseFromStudent(creditId).subscribe( 
      (response: void) => {
        console.log(response);
        this.getCourses();
        const courseId = Number(this.route.snapshot.paramMap.get('id'));
        this.getCourse(courseId);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      
    )
    
  }
}
