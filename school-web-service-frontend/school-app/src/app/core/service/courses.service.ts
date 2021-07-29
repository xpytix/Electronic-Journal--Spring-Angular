import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from 'src/app/shared/course';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http:HttpClient) { }

  public getCourses(): Observable<Course[]>{
    return this.http.get<Course[]>(`${this.apiServerUrl}/course`)
  }
  public getCourse(courseId: number): Observable<Course>{
    return this.http.get<Course>(`${this.apiServerUrl}/course/${courseId}`)
  }
  public addCourse(course: Course): Observable<Course>{
    console.log(course);

    return this.http.post<Course>(`${this.apiServerUrl}/course`, course)
  }
  public addCourseWithTeacher(teacherId: number, course: Course): Observable<Course>{
    console.log(course);

    return this.http.post<Course>(`${this.apiServerUrl}/course/${teacherId}`, course)
  }
  public updateCourse(teacherId:number, course: Course): Observable<Course>{
    console.log("funkcja update: " +  course);
    return this.http.put<Course>(`${this.apiServerUrl}/course/${teacherId}`, course)
  }
  public deleteCourse(courseId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/course/${courseId}`)
  }
}
