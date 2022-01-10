import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Credit } from 'src/app/shared/credit';
import { Student } from 'src/app/shared/student';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CreditService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http:HttpClient) { }

  
  public onDeleteCourseFromStudent(creditId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/credit/${creditId}`)
  }

  public updateCourse(student: Student, courseId: number): Observable<Credit>{
    return this.http.put<Credit>(`${this.apiServerUrl}/credit/${courseId}`, student)
  }
}
