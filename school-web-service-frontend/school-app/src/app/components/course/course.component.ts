import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CoursesService } from 'src/app/core/service/courses.service';
import { Course } from 'src/app/shared/course';
@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
  public course!: Course;
  error: any;
  public courses!: Course[];
  constructor(
    private route:ActivatedRoute,
    private coursesService:CoursesService
  ) {}

  ngOnInit(): void {
    this.loadCourse();
  }
  loadCourse(){
    // get by id
    const courseId = Number(this.route.snapshot.paramMap.get('id'));
    this.getCourse(courseId);

    //get all
    this.getCourses();
  
  }
  
  public getCourse(courseId: number):void{
    this.coursesService.getCourse(courseId).subscribe(
      response => this.course = response,
      error => this.error = error
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
}
