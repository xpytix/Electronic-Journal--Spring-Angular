import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from 'src/app/shared/student';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StudentsService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http:HttpClient) { }

  public getStudents(): Observable<Student[]>{
    return this.http.get<Student[]>(`${this.apiServerUrl}/student`)
  }
  public getStudent(studentId: number): Observable<Student>{
    return this.http.get<Student>(`${this.apiServerUrl}/student/${studentId}`)
  }
  public addStudent(student: Student): Observable<Student>{
    console.log(student);

    return this.http.post<Student>(`${this.apiServerUrl}/student`, student)
  }
  public addCourseToStudent(courseId:number,student: Student): Observable<Student>{
    console.log(student);
    return this.http.post<Student>(`${this.apiServerUrl}/student/${courseId}`, student)
  }
  
  public updateStudent(student: Student): Observable<Student>{
    console.log("funkcja update: " +  student);
    return this.http.put<Student>(`${this.apiServerUrl}/student`, student)
  }
  public deleteStudent(studentId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/student/${studentId}`)
  }
}
